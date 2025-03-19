CREATE TABLE IF NOT EXISTS application_configurations
(
    id                     BIGINT PRIMARY KEY AUTO_INCREMENT,
    creation_timestamp     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    application            VARCHAR(200) NOT NULL,
    profile                VARCHAR(200) NOT NULL,
    value                  JSON NOT NULL,
    UNIQUE (application, profile)
);