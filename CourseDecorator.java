package edu.platform.course;

public abstract class CourseDecorator implements Course {
    protected Course baseCourse;

    public CourseDecorator(Course baseCourse) {
        this.baseCourse = baseCourse;
    }

    @Override
    public void deliverContent() {
        baseCourse.deliverContent();
    }

    @Override
    public String getTitle() {
        return baseCourse.getTitle();
    }
}
