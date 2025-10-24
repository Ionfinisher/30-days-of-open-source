import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a single scanner to be shared by the whole application
        Scanner scanner = new Scanner(System.in);
        String filePath = "src/java/quiz/data/questions.json";
        QuestionManager questionManager = new QuestionManager(filePath);

        // Main application loop
        while (true) {
            System.out.println("\nWelcome to the Quiz Application!");
            System.out.println("1. Start Quiz");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            // Read the entire line of input as a String
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Question> questions = questionManager.getAllQuestions();
                    Quiz quiz = new Quiz(questions, scanner);
                    quiz.start();
                    break;
                case "2":
                    AdminConsole adminConsole = new AdminConsole(questionManager, scanner);
                    adminConsole.start();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}