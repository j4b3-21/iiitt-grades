BEGIN;
CREATE TABLE IF NOT EXISTS students (
                                        id UUID PRIMARY KEY REFERENCES users(id),
                                        roll_number TEXT
);
COMMIT;