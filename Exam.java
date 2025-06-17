package Assignment;

import java.util.Scanner;

public class Exam {
    private final String[] questions = {
        "1. Is Java a compiled language? (yes/no)",
        "2. Which keyword is used to define a class in Java? (class/object)",
        "3. Is Python an interpreted language? (yes/no)"
    };

    private final String[] correctAnswers = {
        "yes",
        "class",
        "yes"
    };

    public int getTotalQuestions() {
        return questions.length;
    }

    public int startExam(Scanner scanner) {
        int score = 0;
        System.out.println("\nPlease answer the following questions:");

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals(correctAnswers[i])) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. Correct answer: " + correctAnswers[i] + "\n");
            }
        }
        return score;
    }
}
