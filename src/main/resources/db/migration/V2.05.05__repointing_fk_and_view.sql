BEGIN;
-- ensure any remaining referenced users exist in students (roll_number NULL)
INSERT INTO students (id, roll_number)
SELECT u.id, NULL
FROM users u
WHERE u.id IN (
    SELECT DISTINCT student_id FROM student_enrollments
    UNION
    SELECT DISTINCT student_id FROM final_grades
)
ON CONFLICT (id) DO NOTHING;

-- re-point FKs from users -> students
ALTER TABLE student_enrollments
    DROP CONSTRAINT IF EXISTS student_enrollments_student_id_fkey,
    DROP CONSTRAINT IF EXISTS student_enrollments_student_id_key;

ALTER TABLE student_enrollments
    ADD CONSTRAINT fk_enrollments_student FOREIGN KEY (student_id) REFERENCES students(id);

ALTER TABLE final_grades
    DROP CONSTRAINT IF EXISTS final_grades_student_id_fkey,
    DROP CONSTRAINT IF EXISTS final_grades_student_id_key;

ALTER TABLE final_grades
    ADD CONSTRAINT fk_finalgrades_student FOREIGN KEY (student_id) REFERENCES students(id);

-- create per-enrollment total view
CREATE OR REPLACE VIEW total_marks_per_enrollment AS
SELECT
    se.id AS enrollment_id,
    se.student_id,
    SUM(m.marks) AS total_marks
FROM marks m
         JOIN student_enrollments se ON m.enrollment_id = se.id
GROUP BY se.id, se.student_id;
COMMIT;
