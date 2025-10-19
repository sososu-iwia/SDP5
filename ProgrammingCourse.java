package course.component;

public class ProgrammingCourse implements CourseComponent {
    private String courseName;

    public ProgrammingCourse(String name) {
        this.courseName = name;
    }

    @Override
    public void deliverContent() {
        System.out.println("Delivering Programming Course: " + courseName);
        System.out.println("  - Coding tutorials and examples");
        System.out.println("  - Hands-on projects");
        System.out.println("  - Code review sessions");
    }

    @Override
    public String getDescription() {
        return "Programming Course: " + courseName;
    }

    @Override
    public int getCost() {
        return 150;
    }
}