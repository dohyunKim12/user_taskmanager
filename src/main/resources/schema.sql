---
CREATE SEQUENCE user_seq START 1;
CREATE SEQUENCE team_seq START 1;
CREATE SEQUENCE license_seq START 1;
CREATE SEQUENCE team_license_seq START 1;
CREATE SEQUENCE user_task_seq START 1;

-- User table
CREATE TABLE "user" (
                        user_id VARCHAR(10) PRIMARY KEY DEFAULT 'USR-' || LPAD(nextval('user_seq')::TEXT, 6, '0'),
                        team_id VARCHAR(10),
                        username VARCHAR(255) UNIQUE,
                        email VARCHAR(255) UNIQUE
);

-- Team table
CREATE TABLE team (
                      team_id VARCHAR(10) PRIMARY KEY DEFAULT 'TEM-' || LPAD(nextval('team_seq')::TEXT, 6, '0'),
                      team_name VARCHAR(255) UNIQUE
);

-- License table
CREATE TABLE license (
                         license_id VARCHAR(10) PRIMARY KEY DEFAULT 'LIC-' || LPAD(nextval('license_seq')::TEXT, 6, '0'),
                         license_type VARCHAR(255) UNIQUE,
                         max_val INT,
                         current_val INT
);

-- Team to License relationship (many-to-many)
CREATE TABLE team_license (
                              team_license_id VARCHAR(20) PRIMARY KEY DEFAULT 'TEM_LIC-' || LPAD(nextval('team_license_seq')::TEXT, 6, '0'),
                              team_id VARCHAR,
                              license_id VARCHAR,
                              UNIQUE (team_id, license_id),
                              FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE CASCADE,
                              FOREIGN KEY (license_id) REFERENCES license(license_id) ON DELETE CASCADE
);

-- User Task table
CREATE TABLE user_task (
                           user_task_id VARCHAR(10) PRIMARY KEY DEFAULT 'UTK-' || LPAD(nextval('user_task_seq')::TEXT, 6, '0'),
                           job_id INTEGER,
                           short_cmd VARCHAR,
                           user_id VARCHAR NOT NULL,
                           submitted_at TIMESTAMP NOT NULL,
                           started_at TIMESTAMP,
                           ended_at TIMESTAMP,
                           status VARCHAR(255) CHECK (status IN ('pending', 'running', 'completed', 'cancelled', 'failed', 'preempted')),
                           FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- User Task Details table
CREATE TABLE user_task_detail (
                                  user_task_id VARCHAR PRIMARY KEY,
                                  command VARCHAR NOT NULL,
                                  license_type VARCHAR NOT NULL,
                                  license_count INT CHECK (license_count >= 0),
                                  directory VARCHAR NOT NULL,
                                  uuid VARCHAR NOT NULL UNIQUE,
                                  timelimit FLOAT CHECK (timelimit > 0),
                                  requested_cpu FLOAT CHECK (requested_cpu > 0),
                                  FOREIGN KEY (user_task_id) REFERENCES user_task(user_task_id) ON DELETE CASCADE
);

-- Foreign key constraints for Users and Teams
ALTER TABLE "user" ADD FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE SET NULL;
