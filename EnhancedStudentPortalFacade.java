package course.facade;

import course.component.CourseComponent;
import course.decorator.*;
import model.Student;
import java.util.*;

public class EnhancedStudentPortalFacade {
    private Map<Student, List<CourseComponent>> enrolledCourses;
    private Map<Student, Map<CourseComponent, Integer>> courseProgress;
    private Scanner scanner;

    public EnhancedStudentPortalFacade() {
        this.enrolledCourses = new HashMap<>();
        this.courseProgress = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void enrollInCourse(Student student, CourseComponent course) {
        if (!enrolledCourses.containsKey(student)) {
            enrolledCourses.put(student, new ArrayList<>());
            courseProgress.put(student, new HashMap<>());
        }

        enrolledCourses.get(student).add(course);
        courseProgress.get(student).put(course, 0);

        System.out.println("\n========================================");
        System.out.println("         ENROLLMENT SUCCESSFUL");
        System.out.println("========================================");
        System.out.println("Student: " + student.getUsername());
        System.out.println("Course: " + course.getDescription());
        System.out.println("Cost: $" + course.getCost());
        System.out.println("========================================\n");

        sendNotification(student, "You've enrolled in: " + course.getDescription());
    }

    public void startLearning(Student student, CourseComponent course) {
        if (!isEnrolled(student, course)) {
            System.out.println("Error: Not enrolled in this course!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("         STARTING COURSE");
        System.out.println("========================================");
        System.out.println("Welcome back, " + student.getUsername() + "!");
        System.out.println();

        course.deliverContent();

        int currentProgress = courseProgress.get(student).get(course);
        int newProgress = Math.min(100, currentProgress + 25);
        courseProgress.get(student).put(course, newProgress);

        System.out.println("\nProgress updated: " + newProgress + "%");

        if (course instanceof GamificationDecorator) {
            GamificationDecorator gamified = (GamificationDecorator) course;
            gamified.awardPoints(50, student.getUsername());
            gamified.incrementStreak();
        }

        sendNotification(student, "Great progress! Keep learning!");
    }

    public void completeCourse(Student student, CourseComponent course) {
        if (!isEnrolled(student, course)) {
            System.out.println("Error: Not enrolled in this course!");
            return;
        }

        courseProgress.get(student).put(course, 100);

        System.out.println("\n========================================");
        System.out.println("     COURSE COMPLETED!");
        System.out.println("========================================");
        System.out.println("Congratulations, " + student.getUsername() + "!");
        System.out.println("You've completed: " + course.getDescription());
        System.out.println("========================================\n");

        if (course instanceof CertificateDecorator) {
            CertificateDecorator certCourse = (CertificateDecorator) course;
            certCourse.issueCertificate(student.getUsername());
        }

        if (course instanceof GamificationDecorator) {
            GamificationDecorator gamified = (GamificationDecorator) course;
            gamified.awardPoints(200, student.getUsername());
            System.out.println("Bonus completion points awarded!");
        }

        sendNotification(student, "Congratulations on completing the course!");
    }

    public void showEnrolledCourses(Student student) {
        if (!enrolledCourses.containsKey(student) || enrolledCourses.get(student).isEmpty()) {
            System.out.println("\nNo enrolled courses yet.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("     YOUR ENROLLED COURSES");
        System.out.println("========================================");

        List<CourseComponent> courses = enrolledCourses.get(student);
        for (int i = 0; i < courses.size(); i++) {
            CourseComponent c = courses.get(i);
            int progress = courseProgress.get(student).get(c);
            System.out.println((i + 1) + ". " + c.getDescription());
            System.out.println("   Progress: " + progress + "%");
            System.out.println("   Cost: $" + c.getCost());
            System.out.println();
        }
    }

    public void trackProgress(Student student) {
        if (!enrolledCourses.containsKey(student)) {
            System.out.println("\nNo courses enrolled yet.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("     LEARNING PROGRESS");
        System.out.println("========================================");
        System.out.println("Student: " + student.getUsername());
        System.out.println();

        Map<CourseComponent, Integer> progress = courseProgress.get(student);
        for (CourseComponent course : enrolledCourses.get(student)) {
            int prog = progress.get(course);
            System.out.println(course.getDescription());
            System.out.print("   [");
            for (int i = 0; i < 20; i++) {
                if (i < prog / 5) {
                    System.out.print("=");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("] " + prog + "%");
        }
        System.out.println();
    }

    private boolean isEnrolled(Student student, CourseComponent course) {
        return enrolledCourses.containsKey(student) &&
                enrolledCourses.get(student).contains(course);
    }

    private void sendNotification(Student student, String message) {
        System.out.println("[Notification to " + student.getUsername() + "]: " + message);
    }

    public List<CourseComponent> getStudentCourses(Student student) {
        return enrolledCourses.getOrDefault(student, new ArrayList<>());
    }
}