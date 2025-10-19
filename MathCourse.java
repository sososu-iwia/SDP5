package course.component;

public class MathCourse implements CourseComponent {
    private String courseName;

    public MathCourse(String name) {
        this.courseName = name;
    }

    @Override
    public void deliverContent() {
        System.out.println("Delivering Math Course: " + courseName);
        System.out.println("  - Video lectures on algebra and calculus");
        System.out.println("  - Practice problems and exercises");
        System.out.println("  - Interactive quizzes");
    }

    @Override
    public String getDescription() {
        return "Math Course: " + courseName;
    }

    @Override
    public int getCost() {
        return 100;
    }
}