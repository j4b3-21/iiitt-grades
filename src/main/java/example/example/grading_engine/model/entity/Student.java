package example.example.grading_engine.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "roll_number")
    private String rollNumber;

    public Student() {
    }

    public Student(UUID id, String rollNumber) {
        this.id = id;
        this.rollNumber = rollNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null ? id.equals(student.id) : student.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rollNumber='" + rollNumber + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String rollNumber;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder rollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
            return this;
        }

        public Student build() {
            return new Student(id, rollNumber);
        }
    }
}
