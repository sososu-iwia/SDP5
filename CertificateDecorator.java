package edu.platform.course;

public class CertificateDecorator extends CourseDecorator {

    public CertificateDecorator(Course baseCourse) {
        super(baseCourse);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("Certificate available upon completion.");
    }
}
