CREATE TABLE IF NOT EXISTS quantity_measurements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operand1 DOUBLE NOT NULL,
    operand2 DOUBLE NOT NULL,
    unit1 VARCHAR(50) NOT NULL,
    unit2 VARCHAR(50) NOT NULL,
    operation VARCHAR(50) NOT NULL,
    result DOUBLE NOT NULL,
    measurement_type VARCHAR(50),
    result_unit VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);