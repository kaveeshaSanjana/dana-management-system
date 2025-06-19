-- schema.sql: Production-level SQL for all tables and relations

-- VILLAGE TABLE
CREATE TABLE villages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    province VARCHAR(255),
    district VARCHAR(255),
    country VARCHAR(255),
    postalCode VARCHAR(20)
);

-- TEMPLE TABLE
CREATE TABLE temple (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);

-- FAMILY TABLE (each family belongs to one village)
CREATE TABLE family (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    familyName VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    telephone VARCHAR(20),
    userId BIGINT,
    village_id BIGINT,
    CONSTRAINT fk_family_village FOREIGN KEY (village_id) REFERENCES villages(id)
);

-- MEMBER TABLE (each member belongs to one family)
CREATE TABLE member (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) UNIQUE NOT NULL,
    address VARCHAR(255),
    nic VARCHAR(20),
    dob DATE,
    family_id BIGINT,
    CONSTRAINT fk_member_family FOREIGN KEY (family_id) REFERENCES family(id)
);

-- HEADMONK TABLE
CREATE TABLE headmonk (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) UNIQUE NOT NULL,
    temple_id BIGINT,
    CONSTRAINT fk_headmonk_temple FOREIGN KEY (temple_id) REFERENCES temple(id)
);

-- HELPER TABLE
CREATE TABLE helper (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) UNIQUE NOT NULL,
    temple_id BIGINT,
    CONSTRAINT fk_helper_temple FOREIGN KEY (temple_id) REFERENCES temple(id)
);

-- TEMPLE_VILLAGE TABLE (many-to-many between temple and village)
CREATE TABLE temple_village (
    temple_id BIGINT,
    village_id BIGINT,
    PRIMARY KEY (temple_id, village_id),
    CONSTRAINT fk_tv_temple FOREIGN KEY (temple_id) REFERENCES temple(id),
    CONSTRAINT fk_tv_village FOREIGN KEY (village_id) REFERENCES villages(id)
);

-- TEMPLE_DANA TABLE
CREATE TABLE temple_dana (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    temple_id BIGINT,
    CONSTRAINT fk_temple_dana_temple FOREIGN KEY (temple_id) REFERENCES temple(id)
);

-- TEMPLE_DANA_ASSIGNMENT TABLE
CREATE TABLE temple_dana_assignment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT,
    temple_dana_id BIGINT,
    assigned_date DATE,
    CONSTRAINT fk_tda_member FOREIGN KEY (member_id) REFERENCES member(id),
    CONSTRAINT fk_tda_temple_dana FOREIGN KEY (temple_dana_id) REFERENCES temple_dana(id)
);

-- INDEXES FOR PERFORMANCE
CREATE INDEX idx_family_village ON family(village_id);
CREATE INDEX idx_member_family ON member(family_id);
CREATE INDEX idx_headmonk_temple ON headmonk(temple_id);
CREATE INDEX idx_helper_temple ON helper(temple_id);
CREATE INDEX idx_temple_dana_temple ON temple_dana(temple_id);
CREATE INDEX idx_tda_member ON temple_dana_assignment(member_id);
CREATE INDEX idx_tda_temple_dana ON temple_dana_assignment(temple_dana_id);

-- SAMPLE DATA
INSERT INTO villages (name, province, district, country, postalCode) VALUES ('Village A', 'Province X', 'District Y', 'Country Z', '12345');
INSERT INTO temple (name, address, phone) VALUES ('Temple Alpha', '123 Main St', '011-1234567');
INSERT INTO family (familyName, address, telephone, userId, village_id) VALUES ('Smith', '456 Family Rd', '077-1234567', 1, 1);
INSERT INTO member (name, email, password, phoneNumber, address, nic, dob, family_id) VALUES ('John Smith', 'john@example.com', 'hashedpassword', '0711111111', '456 Family Rd', '123456789V', '1990-01-01', 1);
INSERT INTO headmonk (name, email, password, phoneNumber, temple_id) VALUES ('Ven. Monk', 'monk@example.com', 'hashedpassword', '0722222222', 1);
INSERT INTO helper (name, email, password, phoneNumber, temple_id) VALUES ('Helper One', 'helper@example.com', 'hashedpassword', '0733333333', 1);
INSERT INTO temple_village (temple_id, village_id) VALUES (1, 1);
INSERT INTO temple_dana (name, description, temple_id) VALUES ('Morning Dana', 'Daily morning dana', 1);
INSERT INTO temple_dana_assignment (member_id, temple_dana_id, assigned_date) VALUES (1, 1, '2025-06-19');

-- Enable automatic schema and data insertion on startup for production
-- For MySQL, use the following commands to ensure schema.sql is run automatically:
-- 1. Place this file in the resources directory as 'schema.sql'.
-- 2. In application.properties, add:
--    spring.datasource.initialization-mode=always
--    spring.datasource.schema=classpath:schema.sql
--    spring.datasource.data=classpath:data.sql
--    spring.jpa.hibernate.ddl-auto=none
-- 3. If you want to insert data automatically, create a 'data.sql' file in the same directory.

-- For PostgreSQL, use similar properties.

-- This ensures schema and data are loaded automatically on application startup in production.
