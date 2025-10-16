package edu.platform.course;

public class GamificationDecorator extends CourseDecorator {
    private int points = 0;

    public GamificationDecorator(Course baseCourse) {
        super(baseCourse);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        points += 50;
        System.out.println("Gamification enabled. Points earned: " + points);
    }
}
