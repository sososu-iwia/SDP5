package edu.platform.course;

public class BaseCourses {

    public static class PhysicalEducation implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Physical Education: exploring fitness principles and active lifestyle techniques.");
        }

        @Override
        public String getTitle() {
            return "Physical Education";
        }
    }

    public static class SoftwareDesignPatterns implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Software Design Patterns: understanding reusable architectural solutions.");
        }

        @Override
        public String getTitle() {
            return "Software Design Patterns";
        }
    }

    public static class DesignAndAnalysisOfAlgorithms implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Design and Analysis of Algorithms: focusing on complexity and problem-solving efficiency.");
        }

        @Override
        public String getTitle() {
            return "Design and Analysis of Algorithms";
        }
    }

    public static class WebTechnologies implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Web Technologies 1 (Front End): HTML, CSS, and JavaScript fundamentals.");
        }

        @Override
        public String getTitle() {
            return "Web Technologies 1 (Front End)";
        }
    }

    public static class OperatingSystems implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Operating Systems: processes, memory management, and concurrency concepts.");
        }

        @Override
        public String getTitle() {
            return "Operating Systems";
        }
    }

    public static class Calculus1 implements Course {
        @Override
        public void deliverContent() {
            System.out.println("Calculus 1: limits, derivatives, and integral calculus.");
        }

        @Override
        public String getTitle() {
            return "Calculus 1";
        }
    }
}
