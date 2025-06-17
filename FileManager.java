package Assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void createFileIfNotExist() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created new data file: " + fileName);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    students.add(Student.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return students;
    }

    public void writeStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(student.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void updateStudent(Student updatedStudent) {
        List<Student> students = readStudents();
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getEmail().equalsIgnoreCase(updatedStudent.getEmail())) {
                students.set(i, updatedStudent);
                found = true;
                break;
            }
        }

        if (!found) {
            students.add(updatedStudent);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }
}
