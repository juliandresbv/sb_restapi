CREATE TABLE IF NOT EXISTS persons (
  id VARCHAR(50) PRIMARY KEY DEFAULT gen_random_uuid()::VARCHAR,
  name VARCHAR(250),
  email VARCHAR(250) UNIQUE,
  birth_date DATE
);
