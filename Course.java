package course;

import model.Student;
import model.Teacher;
import java.util.*;

public class Course implements Enrollable, Progressable {
    private String courseName;
    private Teacher teacher;
    private String difficultyLevel;
    private String prerequisite;
    private int pointsRequired;
    private String description;
    private List<String> completedStudents;
    private boolean hasCertificate;
    private int progress;
    private List<String> topics;

    public Course(String name, Teacher t, String difficulty, String prereq, int points, String desc) {
        this.courseName = name;
        this.teacher = t;
        this.difficultyLevel = difficulty;
        this.prerequisite = prereq;
        this.pointsRequired = points;
        this.description = desc;
        this.completedStudents = new ArrayList<>();
        this.hasCertificate = false;
        this.progress = 0;
        this.topics = new ArrayList<>();
        initializeTopics();
    }

    private void initializeTopics() {
        if (courseName.contains("Mathematics") || courseName.contains("Calculus")) {
            topics.addAll(Arrays.asList("Numbers & Operations", "Algebra", "Geometry", "Problem Solving"));
        } else if (courseName.contains("Programming") || courseName.contains("Software")) {
            topics.addAll(Arrays.asList("Syntax & Basics", "Control Structures", "Functions", "Projects"));
        } else if (courseName.contains("Data Structures")) {
            topics.addAll(Arrays.asList("Arrays & Lists", "Trees & Graphs", "Algorithms", "Complexity Analysis"));
        } else if (courseName.contains("History")) {
            topics.addAll(Arrays.asList("Ancient Civilizations", "Medieval Period", "Modern Era", "Analysis"));
        } else {
            topics.addAll(Arrays.asList("Grammar", "Writing", "Reading", "Critical Thinking"));
        }
    }

    @Override
    public boolean canEnroll(Student student) {
        if (student.isEnrolled(courseName) || student.hasCompletedCourse(courseName)) return false;
        if (student.getPoints() < pointsRequired) return false;
        if (hasPrerequisite()) return student.hasCompletedCourse(prerequisite);
        return true;
    }

    @Override
    public String getEnrollmentRequirements() {
        String req = "Points: " + pointsRequired;
        if (hasPrerequisite()) req += ", Prerequisite: " + prerequisite;
        return req;
    }

    @Override
    public void enrollStudent(Student student) {
        if (canEnroll(student)) student.addCourse(this);
    }

    @Override
    public void unenrollStudent(Student student) {
        student.removeCourse(courseName);
    }

    @Override
    public void updateProgress(int amount) {
        this.progress = Math.min(100, Math.max(0, progress + amount));
    }

    @Override
    public void markAsComplete() {
        this.progress = 100;
    }

    public String getCourseName() { return courseName; }
    public Teacher getTeacher() { return teacher; }
    public String getDifficultyLevel() { return difficultyLevel; }
    public String getPrerequisite() { return prerequisite; }
    public int getPointsRequired() { return pointsRequired; }
    public String getDescription() { return description; }
    public boolean hasPrerequisite() { return prerequisite != null && !prerequisite.isEmpty(); }
    public int getProgressPercentage() { return progress; }
    public void setProgressPercentage(int p) { this.progress = Math.min(100, Math.max(0, p)); }
    public boolean isCompleted() { return progress >= 100; }

    public void markComplete(String studentName) {
        this.progress = 100;
        if (!completedStudents.contains(studentName)) completedStudents.add(studentName);
    }

    public boolean hasCompletedCourse(String studentName) {
        return completedStudents.contains(studentName);
    }

    public void issueCertificate() { this.hasCertificate = true; }
    public boolean hasCertificate() { return hasCertificate; }
    public List<String> getTopics() { return new ArrayList<>(topics); }

    public void displayCourseInfo() {
        System.out.println("\n========================================");
        System.out.println("Course: " + courseName);
        System.out.println("========================================");
        System.out.println("Instructor: " + teacher.getName());
        System.out.println("Difficulty: " + difficultyLevel);
        System.out.println("Description: " + description);
        System.out.println("Points Required: " + pointsRequired);
        System.out.println(hasPrerequisite() ? "Prerequisite: " + prerequisite : "No prerequisites");
        System.out.println("Progress: " + progress + "%");
        if (hasCertificate) System.out.println("Certificate Available");
        System.out.println("\nTopics:");
        for (int i = 0; i < topics.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + topics.get(i));
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return courseName + " [" + difficultyLevel + "] - " + teacher.getName();
    }
}