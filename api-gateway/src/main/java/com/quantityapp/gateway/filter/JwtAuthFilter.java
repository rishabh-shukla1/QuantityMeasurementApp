package com.quantityapp.gateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")
    private String secret;

    // Fully public - no JWT ever needed
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/signup",
            "/api/v1/auth/validate",
            "/actuator"
    );

    // These POST endpoints work without login (dashboard calculate)
    // But if token IS present, we still extract username from it
    private static final List<String> OPTIONAL_AUTH_PATHS = List.of(
            "/api/v1/quantities/add",
            "/api/v1/quantities/subtract",
            "/api/v1/quantities/divide",
            "/api/v1/quantities/compare",
            "/api/v1/quantities/convert"
    );

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 1. Fully public paths - just pass through
        boolean isFullyPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        if (isFullyPublic) {
            return chain.filter(exchange);
        }

        // 2. Optional auth paths (calculate without login)
        // If token present → extract username and forward it
        // If no token → still allow, but no X-Auth-User header (saved as "guest")
        boolean isOptional = OPTIONAL_AUTH_PATHS.stream().anyMatch(path::startsWith);
        if (isOptional) {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                return processWithToken(exchange, chain, authHeader.substring(7));
            }
            // No token - allow through without username
            return chain.filter(exchange);
        }

        // 3. All other paths (GET /api/v1/quantities = history) - JWT REQUIRED
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange, "Login required");
        }
        return processWithToken(exchange, chain, authHeader.substring(7));
    }

    private Mono<Void> processWithToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-Auth-User", username != null ? username : "")
                    .header("X-Auth-Role", role != null ? role : "USER")
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (ExpiredJwtException e) {
            return unauthorized(exchange, "Token has expired");
        } catch (JwtException e) {
            return unauthorized(exchange, "Invalid token");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        String body = "{\"error\": \"" + message + "\"}";
        DataBuffer buffer = response.bufferFactory()
                .wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() { return -1; }
}
