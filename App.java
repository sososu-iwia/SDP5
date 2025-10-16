package edu.platform;

import edu.platform.course.*;
import edu.platform.portal.StudentPortalFacade;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        StudentPortalFacade portal = new StudentPortalFacade(name);

        System.out.println("\nSelect a course to enroll:");
        System.out.println("1. Physical Education");
        System.out.println("2. Software Design Patterns");
        System.out.println("3. Design and Analysis of Algorithms");
        System.out.println("4. Web Technologies 1 (Front End)");
        System.out.println("5. Operating Systems");
        System.out.println("6. Calculus 1");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Course selectedCourse = switch (choice) {
            case 1 -> new BaseCourses.PhysicalEducation();
            case 2 -> new BaseCourses.SoftwareDesignPatterns();
            case 3 -> new BaseCourses.DesignAndAnalysisOfAlgorithms();
            case 4 -> new BaseCourses.WebTechnologies();
            case 5 -> new BaseCourses.OperatingSystems();
            case 6 -> new BaseCourses.Calculus1();
            default -> null;
        };

        if (selectedCourse == null) {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        System.out.print("Add mentor support? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            selectedCourse = new MentorSupportDecorator(selectedCourse);
        }

        System.out.print("Add certificate option? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            selectedCourse = new CertificateDecorator(selectedCourse);
        }

        System.out.print("Add gamification (points)? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            selectedCourse = new GamificationDecorator(selectedCourse);
        }

        portal.enrollInCourse(selectedCourse);
        portal.startLearning(selectedCourse);
        portal.completeCourse(selectedCourse);
    }
}
