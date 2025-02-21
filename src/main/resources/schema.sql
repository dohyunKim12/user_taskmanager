CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL
);

CREATE SEQUENCE user_task_seq START 1;

CREATE TABLE user_task (
    user_task_id VARCHAR(10) PRIMARY KEY DEFAULT 'USR-' || LPAD(nextval('user_task_seq')::TEXT, 6, '0'),
    job_id INTEGER NULL,
    short_cmd VARCHAR NULL,
    user_id INT NOT NULL,
    submitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP NULL,
    ended_at TIMESTAMP NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('pending', 'running', 'completed', 'cancelled')),
    CONSTRAINT fk_user_task_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE user_task_details (
    user_task_id VARCHAR(10) PRIMARY KEY,
    command VARCHAR NOT NULL,
    license_type VARCHAR NOT NULL,
    license_count INT NOT NULL CHECK (license_count >= 0),
    directory VARCHAR NOT NULL,
    uuid VARCHAR NOT NULL UNIQUE,
    timelimit FLOAT NOT NULL CHECK (timelimit > 0),
    requested_cpu FLOAT NOT NULL CHECK (requested_cpu > 0),
    CONSTRAINT fk_user_task_details_task FOREIGN KEY (user_task_id) REFERENCES user_task(user_task_id) ON DELETE CASCADE
);
