package course.decorator;

import course.component.CourseComponent;

public class MentorSupportDecorator extends CourseDecorator {
    private String mentorName;

    public MentorSupportDecorator(CourseComponent course, String mentorName) {
        super(course);
        this.mentorName = mentorName;
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("  + Personal mentor support from " + mentorName);
        System.out.println("  + 1-on-1 mentoring sessions available");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " [with Mentor: " + mentorName + "]";
    }

    @Override
    public int getCost() {
        return super.getCost() + 200;
    }

    public String getMentorName() {
        return mentorName;
    }
}