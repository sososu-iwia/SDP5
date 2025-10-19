package course.decorator;

import course.component.CourseComponent;

public abstract class CourseDecorator implements CourseComponent {
    protected CourseComponent wrappedCourse;

    public CourseDecorator(CourseComponent course) {
        this.wrappedCourse = course;
    }

    @Override
    public void deliverContent() {
        wrappedCourse.deliverContent();
    }

    @Override
    public String getDescription() {
        return wrappedCourse.getDescription();
    }

    @Override
    public int getCost() {
        return wrappedCourse.getCost();
    }
}