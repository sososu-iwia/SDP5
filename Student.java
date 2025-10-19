package model;

import course.Course;
import java.util.*;

public class Student implements Certifiable {
    private String username;
    private String password;
    private int points;
    private List<Course> enrolledCourses;
    private List<Course> completedCourses;
    private Map<String, Boolean> certificates;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 100;
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.certificates = new HashMap<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getPoints() { return points; }
    public void addPoints(int amount) { this.points += amount; }
    public void deductPoints(int amount) { this.points = Math.max(0, this.points - amount); }

    public List<Course> getEnrolledCourses() { return new ArrayList<>(enrolledCourses); }
    public List<Course> getCompletedCourses() { return new ArrayList<>(completedCourses); }

    public void addCourse(Course course) {
        if (!enrolledCourses.contains(course)) enrolledCourses.add(course);
    }

    public void removeCourse(String courseName) {
        enrolledCourses.removeIf(c -> c.getCourseName().equals(courseName));
    }

    public void addCompletedCourse(Course course) {
        if (!completedCourses.contains(course)) {
            completedCourses.add(course);
            enrolledCourses.remove(course);
        }
    }

    public boolean isEnrolled(String courseName) {
        return enrolledCourses.stream().anyMatch(c -> c.getCourseName().equals(courseName));
    }

    public boolean hasCompletedCourse(String courseName) {
        return completedCourses.stream().anyMatch(c -> c.getCourseName().equals(courseName));
    }

    public boolean canEnroll(Course course) {
        if (isEnrolled(course.getCourseName()) || hasCompletedCourse(course.getCourseName())) return false;
        if (points < course.getPointsRequired()) return false;
        if (course.hasPrerequisite()) return hasCompletedCourse(course.getPrerequisite());
        return true;
    }

    public String getEnrollmentBlockReason(Course course) {
        if (isEnrolled(course.getCourseName())) return "Already enrolled";
        if (hasCompletedCourse(course.getCourseName())) return "Already completed";
        if (points < course.getPointsRequired()) return "Need " + (course.getPointsRequired() - points) + " more points";
        if (course.hasPrerequisite() && !hasCompletedCourse(course.getPrerequisite()))
            return "Need prerequisite: " + course.getPrerequisite();
        return "Cannot enroll";
    }

    @Override
    public void awardCertificate(String subject) { certificates.put(subject, true); }

    @Override
    public boolean hasCertificate(String subject) { return certificates.getOrDefault(subject, false); }

    @Override
    public Map<String, Boolean> getCertificates() { return new HashMap<>(certificates); }

    @Override
    public void displayCertificates() {
        System.out.println("\n========================================");
        System.out.println("         CERTIFICATES");
        System.out.println("========================================");
        if (certificates.isEmpty()) {
            System.out.println("No certificates yet.");
        } else {
            for (String subject : certificates.keySet()) {
                if (certificates.get(subject)) System.out.println("  " + subject);
            }
        }
        System.out.println("========================================\n");
    }

    @Override
    public String toString() {
        return username + " (" + points + " pts)";
    }
}