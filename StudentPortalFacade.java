package edu.platform.portal;

import edu.platform.course.Course;
import java.util.ArrayList;
import java.util.List;

public class StudentPortalFacade {
    private final String studentName;
    private final List<Course> enrolledCourses = new ArrayList<>();

    public StudentPortalFacade(String studentName) {
        this.studentName = studentName;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        System.out.println("You have successfully enrolled in: " + course.getTitle());
    }

    public void startLearning(Course course) {
        System.out.println("\n--- Starting course: " + course.getTitle() + " ---");
        course.deliverContent();
        System.out.println("--- Course in progress ---");
    }

    public void completeCourse(Course course) {
        System.out.println("Course completed: " + course.getTitle());
        System.out.println("Congratulations, " + studentName + "!\n");
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}
