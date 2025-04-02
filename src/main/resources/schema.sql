---
CREATE SEQUENCE task_seq START 1;
CREATE SEQUENCE user_task_seq START 1;
CREATE SEQUENCE system_task_seq START 1;
CREATE SEQUENCE user_seq START 1;
CREATE SEQUENCE team_seq START 1;
CREATE SEQUENCE license_seq START 1;
CREATE SEQUENCE team_license_seq START 1;

CREATE TYPE priority_tier_enum AS ENUM ('URGENT', 'HIGH', 'NORMAL', 'LOW');

-- USERS
CREATE TABLE users (
                       user_id VARCHAR(10) PRIMARY KEY DEFAULT 'USR-' || LPAD(nextval('user_seq')::TEXT, 6, '0'),
                       team_id VARCHAR(10),
                       username VARCHAR(255) UNIQUE,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(255),
                       FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE SET NULL
);

-- Team
CREATE TABLE team (
                      team_id VARCHAR(10) PRIMARY KEY DEFAULT 'TEM-' || LPAD(nextval('team_seq')::TEXT, 6, '0'),
                      team_name VARCHAR(255) UNIQUE
);

-- License
CREATE TABLE license (
                         license_id VARCHAR(10) PRIMARY KEY DEFAULT 'LIC-' || LPAD(nextval('license_seq')::TEXT, 6, '0'),
                         license_type VARCHAR(255) UNIQUE,
                         max_val INT,
                         current_val INT
);

-- TEAM_LICENSE (many-to-many)
CREATE TABLE team_license (
                              team_license_id VARCHAR(20) PRIMARY KEY DEFAULT 'TEM_LIC-' || LPAD(nextval('team_license_seq')::TEXT, 6, '0'),
                              team_id VARCHAR(10),
                              license_id VARCHAR(10),
                              UNIQUE (team_id, license_id),
                              FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE CASCADE,
                              FOREIGN KEY (license_id) REFERENCES license(license_id) ON DELETE CASCADE
);

-- TASK
CREATE TABLE task (
                      task_id VARCHAR(10) PRIMARY KEY DEFAULT 'TSK-' || LPAD(nextval('task_seq')::TEXT, 6, '0'),
                      task_type VARCHAR(255),
                      user_id VARCHAR(10) NOT NULL,
                      requested_cpu FLOAT NOT NULL,
                      requested_mem FLOAT NOT NULL,
                      license_type VARCHAR(255),
                      license_count INT,
                      timelimit FLOAT NOT NULL,
                      command VARCHAR(1024) NOT NULL,
                      partition VARCHAR(255) NOT NULL,
                      status VARCHAR(255) NOT NULL CHECK (status IN ('pending', 'running', 'completed', 'cancelled', 'failed', 'preempted')),
                      priority_tier priority_tier_enum NOT NULL DEFAULT 'NORMAL',
                      priority FLOAT NOT NULL,
                      submitted_at TIMESTAMP,
                      started_at TIMESTAMP,
                      ended_at TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- USER_TASK
CREATE TABLE user_task (
                           user_task_id VARCHAR(10) PRIMARY KEY DEFAULT 'UTK-' || LPAD(nextval('user_task_seq')::TEXT, 6, '0'),
                           task_id VARCHAR(10) NOT NULL UNIQUE,
                           directory VARCHAR(1024) NOT NULL,
                           env TEXT NOT NULL,
                           description VARCHAR(1024),
                           exit_code INT,
                           FOREIGN KEY (task_id) REFERENCES task(task_id) ON DELETE CASCADE
);

-- SYSTEM_TASK
CREATE TABLE system_task (
                             system_task_id VARCHAR(10) PRIMARY KEY DEFAULT 'STK-' || LPAD(nextval('system_task_seq')::TEXT, 6, '0'),
                             task_id VARCHAR(10) NOT NULL UNIQUE,
                             system_type VARCHAR(255) NOT NULL,
                             config JSON NOT NULL,
                             FOREIGN KEY (task_id) REFERENCES task(task_id) ON DELETE CASCADE
);
