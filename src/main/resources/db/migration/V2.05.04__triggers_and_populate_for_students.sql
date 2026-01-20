BEGIN;
-- populate initial students (roll_number intentionally NULL; will be manually added)
INSERT INTO students (id, roll_number)
SELECT u.id, NULL
FROM users u
WHERE u.role = 'STUDENT'
   OR u.id IN (
    SELECT student_id FROM student_enrollments
    UNION
    SELECT student_id FROM final_grades
)
ON CONFLICT (id) DO NOTHING;

-- guard trigger: enforce referenced user exists and has role = 'STUDENT'
CREATE OR REPLACE FUNCTION students_guard() RETURNS TRIGGER AS $$
DECLARE
    u_role TEXT;
BEGIN
    SELECT role INTO u_role FROM users WHERE id = NEW.id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'user % does not exist', NEW.id;
    END IF;
    IF u_role <> 'STUDENT' THEN
        RAISE EXCEPTION 'only users with role STUDENT can be added to students';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_students_guard ON students;
CREATE TRIGGER trg_students_guard
    BEFORE INSERT OR UPDATE ON students
    FOR EACH ROW
EXECUTE FUNCTION students_guard();
COMMIT;