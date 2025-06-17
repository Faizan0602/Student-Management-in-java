package Assignment;

import java.util.List;

public class Admin {
    private static final String ADMIN_PASSWORD = "admin123";

    public boolean login(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    public void viewStudents(FileManager fileManager) {
        List<Student> students = fileManager.readStudents();
        System.out.println("\n--- All Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        System.out.printf("%-20s %-30s %-15s %s%n", "Name", "Email", "Course", "Score");
        System.out.println("--------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-20s %-30s %-15s %d%n",
                    student.getName(), student.getEmail(), student.getCourse(), student.getScore());
        }
    }
}
