BEGIN;
ALTER TABLE marks
    DROP COLUMN IF EXISTS internal_marks,
    DROP COLUMN IF EXISTS external_marks,
    DROP COLUMN IF EXISTS lab_marks,
    DROP COLUMN IF EXISTS total_marks;

ALTER TABLE marks
    ADD COLUMN IF NOT EXISTS marks_type mark_component_type,
    ADD COLUMN IF NOT EXISTS marks NUMERIC(5,2) CHECK (marks BETWEEN 0 AND 100);

-- if table is empty this set is safe; retains idempotency
ALTER TABLE marks
    ALTER COLUMN marks_type SET NOT NULL,
    ALTER COLUMN marks SET NOT NULL;

ALTER TABLE marks
    DROP CONSTRAINT IF EXISTS uq_marks_enrollment_component,
    DROP CONSTRAINT IF EXISTS marks_enrollment_id_key;

ALTER TABLE marks
    ADD CONSTRAINT uq_marks_enrollment_component UNIQUE (enrollment_id, marks_type);

CREATE INDEX IF NOT EXISTS idx_marks_enrollment_id ON marks (enrollment_id);
CREATE INDEX IF NOT EXISTS idx_marks_type ON marks (marks_type);
COMMIT;