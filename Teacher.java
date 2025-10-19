package model;

public class Teacher {
    private String name;
    private String subject;
    private String specialty;

    public Teacher(String name, String subject, String specialty) {
        this.name = name;
        this.subject = subject;
        this.specialty = specialty;
    }

    public String getName() { return name; }
    public String getSubject() { return subject; }
    public String getSpecialty() { return specialty; }

    public void displayTeacherInfo() {
        System.out.println("\n========================================");
        System.out.println("Name: " + name);
        System.out.println("Subject: " + subject);
        System.out.println("Specialty: " + specialty);
        System.out.println("========================================\n");
    }

    @Override
    public String toString() {
        return name + " (" + subject + ")";
    }
}