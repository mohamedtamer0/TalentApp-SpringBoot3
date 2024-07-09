/* Formatted on 7/9/2024 8:10:56 PM (QP5 v5.326) */
-- Create sequences for auto-incrementing primary keys

CREATE SEQUENCE users_type_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE job_company_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE job_location_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE job_post_activity_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE job_seeker_save_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE job_seeker_apply_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE skills_seq START WITH 1 INCREMENT BY 1;

-- Create tables with triggers to use the sequences

CREATE TABLE users_type
(
    user_type_id      NUMBER PRIMARY KEY,
    user_type_name    VARCHAR2 (255)
);

CREATE OR REPLACE TRIGGER users_type_trigger
    BEFORE INSERT
    ON users_type
    FOR EACH ROW
BEGIN
    :new.user_type_id := users_type_seq.NEXTVAL;
END;
/

INSERT INTO users_type (user_type_name)
     VALUES ('Recruiter');

INSERT INTO users_type (user_type_name)
     VALUES ('Job Seeker');

CREATE TABLE users
(
    user_id              NUMBER PRIMARY KEY,
    email                VARCHAR2 (255) UNIQUE,
    is_active            NUMBER (1),
    password             VARCHAR2 (255),
    registration_date    TIMESTAMP (6),
    user_type_id         NUMBER,
    CONSTRAINT FK_users_user_type FOREIGN KEY (user_type_id)
        REFERENCES users_type (user_type_id)
);

CREATE OR REPLACE TRIGGER users_trigger
    BEFORE INSERT
    ON users
    FOR EACH ROW
BEGIN
    :new.user_id := users_seq.NEXTVAL;
END;
/

CREATE TABLE job_company
(
    id      NUMBER PRIMARY KEY,
    logo    VARCHAR2 (255),
    name    VARCHAR2 (255)
);

CREATE OR REPLACE TRIGGER job_company_trigger
    BEFORE INSERT
    ON job_company
    FOR EACH ROW
BEGIN
    :new.id := job_company_seq.NEXTVAL;
END;
/

CREATE TABLE job_location
(
    id         NUMBER PRIMARY KEY,
    city       VARCHAR2 (255),
    country    VARCHAR2 (255),
    state      VARCHAR2 (255)
);

CREATE OR REPLACE TRIGGER job_location_trigger
    BEFORE INSERT
    ON job_location
    FOR EACH ROW
BEGIN
    :new.id := job_location_seq.NEXTVAL;
END;
/

CREATE TABLE job_seeker_profile
(
    user_account_id       NUMBER PRIMARY KEY,
    city                  VARCHAR2 (255),
    country               VARCHAR2 (255),
    employment_type       VARCHAR2 (255),
    first_name            VARCHAR2 (255),
    last_name             VARCHAR2 (255),
    profile_photo         VARCHAR2 (255),
    resume                VARCHAR2 (255),
    state                 VARCHAR2 (255),
    work_authorization    VARCHAR2 (255),
    CONSTRAINT FK_job_seeker_profile_user FOREIGN KEY (user_account_id)
        REFERENCES users (user_id)
);

CREATE TABLE recruiter_profile
(
    user_account_id    NUMBER PRIMARY KEY,
    city               VARCHAR2 (255),
    company            VARCHAR2 (255),
    country            VARCHAR2 (255),
    first_name         VARCHAR2 (255),
    last_name          VARCHAR2 (255),
    profile_photo      VARCHAR2 (64),
    state              VARCHAR2 (255),
    CONSTRAINT FK_recruiter_profile_user FOREIGN KEY (user_account_id)
        REFERENCES users (user_id)
);

CREATE TABLE job_post_activity
(
    job_post_id           NUMBER PRIMARY KEY,
    description_of_job    VARCHAR2 (4000),
    job_title             VARCHAR2 (255),
    job_type              VARCHAR2 (255),
    posted_date           TIMESTAMP (6),
    remote                VARCHAR2 (255),
    salary                VARCHAR2 (255),
    job_company_id        NUMBER,
    job_location_id       NUMBER,
    posted_by_id          NUMBER,
    CONSTRAINT FK_job_post_company FOREIGN KEY (job_company_id)
        REFERENCES job_company (id),
    CONSTRAINT FK_job_post_location FOREIGN KEY (job_location_id)
        REFERENCES job_location (id),
    CONSTRAINT FK_job_post_user FOREIGN KEY (posted_by_id)
        REFERENCES users (user_id)
);

CREATE OR REPLACE TRIGGER job_post_activity_trigger
    BEFORE INSERT
    ON job_post_activity
    FOR EACH ROW
BEGIN
    :new.job_post_id := job_post_activity_seq.NEXTVAL;
END;
/

CREATE TABLE job_seeker_save
(
    id         NUMBER PRIMARY KEY,
    job        NUMBER,
    user_id    NUMBER,
    CONSTRAINT UK_job_seeker_save UNIQUE (user_id, job),
    CONSTRAINT FK_job_seeker_save_job FOREIGN KEY (job)
        REFERENCES job_post_activity (job_post_id),
    CONSTRAINT FK_job_seeker_save_user FOREIGN KEY (user_id)
        REFERENCES job_seeker_profile (user_account_id)
);

CREATE OR REPLACE TRIGGER job_seeker_save_trigger
    BEFORE INSERT
    ON job_seeker_save
    FOR EACH ROW
BEGIN
    :new.id := job_seeker_save_seq.NEXTVAL;
END;
/

CREATE TABLE job_seeker_apply
(
    id              NUMBER PRIMARY KEY,
    apply_date      TIMESTAMP (6),
    cover_letter    VARCHAR2 (255),
    job             NUMBER,
    user_id         NUMBER,
    CONSTRAINT UK_job_seeker_apply UNIQUE (user_id, job),
    CONSTRAINT FK_job_seeker_apply_job FOREIGN KEY (job)
        REFERENCES job_post_activity (job_post_id),
    CONSTRAINT FK_job_seeker_apply_user FOREIGN KEY (user_id)
        REFERENCES job_seeker_profile (user_account_id)
);

CREATE OR REPLACE TRIGGER job_seeker_apply_trigger
    BEFORE INSERT
    ON job_seeker_apply
    FOR EACH ROW
BEGIN
    :new.id := job_seeker_apply_seq.NEXTVAL;
END;
/

CREATE TABLE skills
(
    id                     NUMBER PRIMARY KEY,
    experience_level       VARCHAR2 (255),
    name                   VARCHAR2 (255),
    years_of_experience    VARCHAR2 (255),
    job_seeker_profile     NUMBER,
    CONSTRAINT FK_skills_profile FOREIGN KEY (job_seeker_profile)
        REFERENCES job_seeker_profile (user_account_id)
);

CREATE OR REPLACE TRIGGER skills_trigger
    BEFORE INSERT
    ON skills
    FOR EACH ROW
BEGIN
    :new.id := skills_seq.NEXTVAL;
END;
/