package course.decorator;

import course.component.CourseComponent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CertificateDecorator extends CourseDecorator {
    private Map<String, LocalDate> certificates;

    public CertificateDecorator(CourseComponent course) {
        super(course);
        this.certificates = new HashMap<>();
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("  + Certificate available upon completion");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " [with Certificate]";
    }

    @Override
    public int getCost() {
        return super.getCost() + 50;
    }

    public void issueCertificate(String studentName) {
        certificates.put(studentName, LocalDate.now());
        System.out.println("\n*** CERTIFICATE AWARDED ***");
        System.out.println("Student: " + studentName);
        System.out.println("Course: " + wrappedCourse.getDescription());
        System.out.println("Date: " + LocalDate.now());
        System.out.println("***************************\n");
    }

    public boolean hasCertificate(String studentName) {
        return certificates.containsKey(studentName);
    }
}