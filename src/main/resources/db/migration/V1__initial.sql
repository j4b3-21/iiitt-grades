CREATE EXTENSION IF NOT EXISTS pgcrypto;
-- =========================
-- ENUM TYPES (unchanged set; ordering preserved)
-- =========================

CREATE TYPE user_role AS ENUM (
    'STUDENT',
    'FACULTY',
    'HOD',
    'SYSTEM'
);

CREATE TYPE auth_provider AS ENUM (
    'LOCAL',
    'GOOGLE'
);

CREATE TYPE semester_type AS ENUM (
    'ODD',
    'EVEN'
);

CREATE TYPE academic_status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);

CREATE TYPE submission_status AS ENUM (
    'DRAFT',
    'SUBMITTED',
    'APPROVED',
    'LOCKED'
);

CREATE TYPE approval_decision AS ENUM (
    'APPROVE',
    'REJECT'
);

CREATE TYPE mark_component_type AS ENUM (
    'INTERNAL',
    'EXTERNAL',
    'LAB'
);

CREATE TYPE validation_result_status AS ENUM (
    'VALID',
    'INVALID'
);

CREATE TYPE grade_letter AS ENUM (
    'A', 'B', 'C', 'D', 'E', 'F'
);

CREATE TYPE grading_method AS ENUM (
    'RELATIVE',
    'ABSOLUTE'
);

CREATE TYPE audit_action AS ENUM (
    'LOGIN_SUCCESS',
    'LOGIN_FAILURE',
    'MARKS_SAVED',
    'MARKS_SUBMITTED',
    'GRADES_APPROVED',
    'GRADES_REJECTED',
    'GRADES_LOCKED',
    'EXPORT_GENERATED',
    'UNAUTHORIZED_ACCESS'
);

CREATE TYPE audit_entity_type AS ENUM (
    'USER',
    'SUBJECT',
    'MARKS',
    'GRADES',
    'EXPORT'
);

CREATE TYPE export_type AS ENUM (
    'CSV',
    'PDF'
);

CREATE TYPE export_scope AS ENUM (
    'SUBJECT',
    'STUDENT',
    'DEPARTMENT',
    'ACADEMIC_YEAR'
);

CREATE TYPE student_result_visibility AS ENUM (
    'NOT_PUBLISHED',
    'PUBLISHED'
);

-- =========================
-- TABLES
-- =========================

CREATE TABLE department(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR UNIQUE NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    role user_role NOT NULL,
    auth_provider auth_provider NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    academic_status academic_status NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE subjects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    department_id UUID NOT NULL REFERENCES department(id),
    subject_code TEXT NOT NULL,
    subject_name TEXT NOT NULL,
    credits INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (department_id, subject_code)
);

CREATE TABLE faculty_subjects (
    faculty_id UUID NOT NULL REFERENCES users(id),
    subject_id UUID NOT NULL REFERENCES subjects(id),
    academic_year TEXT NOT NULL,
    assigned_at TIMESTAMP NOT NULL DEFAULT NOW(),
    semester INTEGER NOT NULL,
    semester_type semester_type NOT NULL,
    PRIMARY KEY (faculty_id, subject_id, academic_year)
);

CREATE TABLE academic_session(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    academic_year VARCHAR NOT NULL,
    semester INTEGER NOT NULL,
    regulation_version VARCHAR NOT NULL,
    grading_policy_ver VARCHAR NOT NULL,
    UNIQUE (academic_year, semester)
);

CREATE TABLE student_enrollments(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id),
    subject_id UUID NOT NULL REFERENCES subjects(id),
    session_id UUID NOT NULL REFERENCES academic_session(id),
    UNIQUE (student_id, subject_id, session_id)
);

CREATE TABLE marks(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    enrollment_id UUID NOT NULL UNIQUE REFERENCES student_enrollments(id),
    internal_marks NUMERIC CHECK (internal_marks BETWEEN 0 AND 100),
    external_marks NUMERIC CHECK (external_marks BETWEEN 0 AND 100),
    lab_marks NUMERIC CHECK (lab_marks BETWEEN 0 AND 100),
    total_marks NUMERIC NOT NULL CHECK (total_marks BETWEEN 0 AND 100),
    entered_by UUID NOT NULL REFERENCES users(id),
    entered_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE grade_submissions (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        subject_id UUID NOT NULL REFERENCES subjects(id),
        session_id UUID NOT NULL REFERENCES academic_session(id),
        status submission_status NOT NULL DEFAULT 'DRAFT',
        submitted_at TIMESTAMP,
        approved_at TIMESTAMP,
        locked_at TIMESTAMP
);

CREATE TABLE grade_statistics(
    submission_id UUID PRIMARY KEY REFERENCES grade_submissions(id),
    mean NUMERIC NOT NULL,
    std_deviation NUMERIC NOT NULL
);

CREATE TABLE grade_boundaries(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    submission_id UUID NOT NULL REFERENCES grade_submissions(id),
    grade_letter grade_letter NOT NULL,
    min_score NUMERIC NOT NULL,
    max_score NUMERIC NOT NULL,
    CHECK (min_score <= max_score)
);

CREATE TABLE final_grades (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id),
    subject_id UUID NOT NULL REFERENCES subjects(id),
    academic_year TEXT NOT NULL,
    grade grade_letter NOT NULL,
    total_marks INTEGER NOT NULL CHECK (total_marks BETWEEN 0 AND 100),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (student_id, subject_id, academic_year)
);

CREATE TABLE audit_log (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    actor_id UUID REFERENCES users(id),
    action audit_action NOT NULL,
    entity_type audit_entity_type NOT NULL,
    entity_id UUID,
    previous_state JSONB,
    new_state JSONB,
    event_time TIMESTAMP NOT NULL DEFAULT NOW(),
    prev_hash VARCHAR,
    hash VARCHAR NOT NULL,
    occurred_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE compliance_exports(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    requested_by UUID NOT NULL REFERENCES users(id),
    export_type export_type NOT NULL DEFAULT 'CSV',
    scope_identifier export_scope NOT NULL,
    generated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    data_hash VARCHAR NOT NULL
);

CREATE TABLE grades_archive (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    enrollment_id UUID REFERENCES student_enrollments(id),
    semester INTEGER NOT NULL,
    grade_letter grade_letter NOT NULL,
    archived_at TIMESTAMP NOT NULL DEFAULT NOW(),
    policy_version VARCHAR
);


-- =========================
-- INDEXES
-- =========================


-- =========================
-- FUNCTIONS
-- =========================
CREATE OR REPLACE FUNCTION prevent_update_after_lock()
    RETURNS TRIGGER AS $$
        BEGIN
            IF OLD.status = 'LOCKED' THEN
                RAISE EXCEPTION 'Grade submission is locked and cannot be modified';
            END IF;
            RETURN NEW;
        END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION prevent_final_grade_modification()
    RETURNS TRIGGER AS $$
BEGIN
    RAISE EXCEPTION 'Final grades are immutable';
    IF TG_OP = 'DELETE' THEN
        RETURN OLD;
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;



-- =========================
-- TRIGGERS
-- =========================
CREATE TRIGGER trg_no_update_after_lock
    BEFORE UPDATE ON grade_submissions
    FOR EACH ROW
EXECUTE FUNCTION prevent_update_after_lock();

CREATE TRIGGER trg_no_update_final_grades
    BEFORE UPDATE OR DELETE ON final_grades
    FOR EACH ROW
EXECUTE FUNCTION prevent_final_grade_modification();
