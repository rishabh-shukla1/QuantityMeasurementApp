package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import entity.QuantityMeasurementEntity;
import util.ConnectionUtil;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurements " +
                "(operand1, operand2, unit1, unit2, operation, result, measurement_type, result_unit, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, entity.getOperand1());
            statement.setDouble(2, entity.getOperand2());
            statement.setString(3, entity.getUnit1());
            statement.setString(4, entity.getUnit2());
            statement.setString(5, entity.getOperation());
            statement.setDouble(6, entity.getResult());
            statement.setString(7, entity.getMeasurementType());
            statement.setString(8, entity.getResultUnit());

            if (entity.getCreatedAt() != null) {
                statement.setTimestamp(9, java.sql.Timestamp.valueOf(entity.getCreatedAt()));
            } else {
                statement.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
            }

            int rows = statement.executeUpdate();
            System.out.println("Rows inserted: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                list.add(mapRowToEntity(rs));
            }

            System.out.println("Rows fetched: " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements WHERE operation = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, operation);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToEntity(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements WHERE measurement_type = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, type);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToEntity(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int getTotalCount() {

        String sql = "SELECT COUNT(*) FROM quantity_measurements";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM quantity_measurements";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int rows = statement.executeUpdate();
            System.out.println("Rows deleted: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPoolStatistics() {
        return "Simple JDBC ConnectionUtil repository";
    }

    @Override
    public void releaseResources() {
        System.out.println("No resources to release");
    }

    private QuantityMeasurementEntity mapRowToEntity(ResultSet rs) throws Exception {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setId(rs.getLong("id"));
        entity.setOperand1(rs.getDouble("operand1"));
        entity.setOperand2(rs.getDouble("operand2"));
        entity.setUnit1(rs.getString("unit1"));
        entity.setUnit2(rs.getString("unit2"));
        entity.setOperation(rs.getString("operation"));
        entity.setResult(rs.getDouble("result"));
        entity.setMeasurementType(rs.getString("measurement_type"));
        entity.setResultUnit(rs.getString("result_unit"));

        java.sql.Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            entity.setCreatedAt(timestamp.toLocalDateTime());
        }

        return entity;
    }
}