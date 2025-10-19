package course;

import model.Student;

public interface Enrollable {
    boolean canEnroll(Student student);
    String getEnrollmentRequirements();
    void enrollStudent(Student student);
    void unenrollStudent(Student student);
}