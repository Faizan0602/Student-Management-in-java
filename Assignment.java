package Assignment;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Assignment {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileManager fileManager = new FileManager("students.txt");

    public static void main(String[] args) {
        fileManager.createFileIfNotExist();

        System.out.println("Welcome to Student Training and Exam Management System!");

        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Student Section");
            System.out.println("2. Admin Section");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> studentSection();
                    case 2 -> adminSection();
                    case 3 -> System.out.println("Exiting system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear buffer
                choice = 0;
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void studentSection() {
        System.out.println("\n--- Student Section ---");
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        System.out.println("Select a Course:");
        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.print("Enter your choice (1 or 2): ");
        String course;
        try {
            int courseChoice = scanner.nextInt();
            scanner.nextLine();
            course = (courseChoice == 1) ? "Java" : (courseChoice == 2) ? "Python" : "Java";
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Defaulting to 'Java' course.");
            scanner.nextLine();
            course = "Java";
        }

        Student student = new Student(name, email, course, 0);
        List<Student> allStudents = fileManager.readStudents();
        boolean studentExists = false;

        for (Student s : allStudents) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                student = s;
                studentExists = true;
                System.out.println("Welcome back, " + student.getName() + "!");
                break;
            }
        }

        if (!studentExists) {
            fileManager.writeStudent(student);
            System.out.println("Student registered successfully!");
        }

        System.out.println("\nStarting exam for " + student.getName() + " in " + student.getCourse() + "...");
        Exam exam = new Exam();
        int score = exam.startExam(scanner);
        student.setScore(score);
        fileManager.updateStudent(student);

        System.out.println("\n--- Exam Result ---");
        System.out.println("Student Name: " + student.getName());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Score: " + student.getScore() + " out of " + exam.getTotalQuestions());
        System.out.println("Result: " + (student.getScore() >= exam.getTotalQuestions() / 2.0 ? "Passed!" : "Failed!"));
    }

    private static void adminSection() {
        System.out.println("\n--- Admin Section ---");
        Admin admin = new Admin();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (admin.login(password)) {
            System.out.println("Admin login successful!");
            admin.viewStudents(fileManager);
        } else {
            System.out.println("Invalid Admin Password.");
        }
    }
}
