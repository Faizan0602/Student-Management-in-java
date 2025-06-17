# Student Training and Exam Management System
![Student Training and Exam System](images/Student-Training-Exam-System.jpg "Student Training and Exam System")

![Class Diagram](images/Class-Diagram.jpg "Class Diagram")

## Project Description

This is a console-based Java application designed to manage student training and examination records. It provides two main sections: a *Student Section* for students to register, select a course, and take an exam, and an *Admin Section* for administrators to view all registered students and their exam results.

The system uses a simple text file (students.txt) for persistent data storage, allowing student records and scores to be saved and loaded between application runs.

## Features

### Student Section:
* *Student Registration/Login:* Students can enter their name and email. If the email already exists, the system recognizes them; otherwise, a new student record is created.
* *Course Selection:* Students can choose between "Java" and "Python" courses (though the current exam questions are generic and apply to both).
* *Take Exam:* A short, predefined multiple-choice/short-answer exam is presented.
* *Score Calculation:* The system calculates and displays the student's score.
* *Pass/Fail Status:* Determines if the student passed or failed based on a simple pass mark (score >= 50% of total questions).
* *Score Persistence:* Student scores are updated and saved to students.txt.

### Admin Section:
* *Admin Login:* Requires a hardcoded password (admin123) to access.
* *View All Students:* Displays a formatted list of all registered students, including their name, email, chosen course, and latest exam score.

### Data Management:
* *File-based Storage:* All student data is stored in students.txt in a comma-separated format.
* *Automatic File Creation:* The students.txt file is automatically created if it doesn't exist upon application startup.
* *Data Parsing:* Handles reading and writing student records from/to the file, including updating existing records.

## How It's Working (Architecture & Logic)

The system is structured into several Java classes, each responsible for a specific part of the application's functionality:

* **Assignment.java (Main Class):**
    * This is the entry point of the application.
    * It presents the main menu (Student Section, Admin Section, Exit).
    * It handles user input for menu navigation.
    * It orchestrates the flow between the student and admin functionalities.
    * Manages the Scanner for console input and the FileManager for data persistence.

* **Student.java:**
    * Represents a student entity with properties like name, email, course, and score.
    * Provides getters and setters for these properties.
    * Includes toString() method to convert a Student object into a comma-separated string for file storage.
    * Includes a static fromFileString() method to parse a string from the file back into a Student object.

* **Admin.java:**
    * Handles admin-specific operations.
    * Contains a login() method with a hardcoded password for demonstration.
    * Provides viewStudents() functionality, which reads student data using FileManager and displays it in a formatted table.

* **Exam.java:**
    * Manages the exam logic.
    * Stores a predefined set of questions and their correct answers.
    * startExam() method presents questions to the student and calculates their score based on correct answers.

* **FileManager.java:**
    * Responsible for all file I/O operations related to students.txt.
    * createFileIfNotExist(): Ensures the data file exists, creating it if it doesn't.
    * readStudents(): Reads all student records from the file and returns them as a List<Student>. It includes basic error handling for malformed lines.
    * writeStudent(): Appends a new student's data to the file.
    * updateStudent(): Reads all students, finds and updates a specific student's record (identified by email), and then rewrites the entire file. This ensures data consistency after an exam.

## Setup and Running the Project

To compile and run this project, you will need a Java Development Kit (JDK) installed on your system.

1.  *Clone the Repository (or download the files):*
    bash
    git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
    
    (Replace with your actual GitHub URL)

    Or, if you already have the files, just navigate to your project directory:
    bash
    cd C:\Users\neha1\OneDrive\Desktop\java question\bootcamp\student_management_system
    
    (Adjust path as necessary)

2.  *Compile the Java Source Files:*
    Open your terminal or command prompt, navigate to the project directory, and compile all .java files:
    bash
    javac *.java
    
    This command will generate .class files for each Java source file.

3.  *Run the Application:*
    Execute the Assignment class, which contains the main method:
    bash
    java Assignment
    

4.  *Interact with the Console:*
    Follow the prompts in the console to navigate through the Student and Admin sections.

### Admin Password:
The default admin password is admin123.

## Future Enhancements (Optional Section - good for showing thought)

* *More Robust Input Validation:* Implement stricter checks for email format, numerical inputs, etc.
* *User Authentication (Hashing Passwords):* Instead of a hardcoded admin password, implement secure password hashing.
* *Dynamic Exam Questions:* Load questions from a file or database, allowing for different exams per course.
* *Different Exam Types:* Implement multiple-choice, true/false, fill-in-the-blanks.
* *Student Deletion/Modification (Admin):* Add functionality for admins to remove or edit student records.
* *Graphical User Interface (GUI):* Convert the console application to a desktop application using Swing or JavaFX.
* *Database Integration:* Replace file-based storage with a relational database (e.g., SQLite, MySQL) for better scalability and data management.
