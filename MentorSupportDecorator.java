package edu.platform.course;

public class MentorSupportDecorator extends CourseDecorator {

    public MentorSupportDecorator(Course baseCourse) {
        super(baseCourse);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("Mentor assigned for individual guidance.");
    }
}
