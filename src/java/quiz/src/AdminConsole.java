import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminConsole {
    private final QuestionManager questionManager;
    private final Scanner scanner;
    private final String adminPassword = "admin123";

    public AdminConsole(QuestionManager questionManager, Scanner scanner) {
        this.questionManager = questionManager;
        this.scanner = scanner;
    }

    // Starts the admin console, beginning with authentication.
    public void start() {
        System.out.print("Enter admin password: ");
        String inputPassword = scanner.nextLine();

        if (!adminPassword.equals(inputPassword)) {
            System.out.println("Incorrect password. Access denied.");
            return;
        }
        System.out.println("Authentication successful. Welcome, Admin!");
        showAdminMenu();
    }

    // Displays the main menu and handles user choices in a loop.
    private void showAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add a new question");
            System.out.println("2. Modify an existing question");
            System.out.println("3. Delete a question");
            System.out.println("4. List all questions");
            System.out.println("5. View question statistics");
            System.out.println("6. Exit Admin Mode");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleAddQuestion();
                    break;
                case "2":
                    handleModifyQuestion();
                    break;
                case "3":
                    handleDeleteQuestion();
                    break;
                case "4":
                    handleListQuestions();
                    break;
                case "5":
                    handleViewStatistics();
                    break;
                case "6":
                    System.out.println("Exiting admin mode.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Displays all details for every question.
    private void handleListQuestions() {
        List<Question> questions = questionManager.getAllQuestions();
        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }
        System.out.println("\n--- All Questions ---");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("--------------------");
            System.out.println((i + 1) + ". Text: " + q.getText());
            System.out.println("   Answer: " + q.getCorrectAnswer());
            System.out.println("   Type: " + q.getType());
            System.out.println("   Options: " + (q.getOptions() != null ? q.getOptions() : "N/A"));
            System.out.println("   Time Limit: " + q.getTimeLimitInSeconds() + "s");
        }
    }

    // Prompts the admin for every field to create a new, complete question.
    private void handleAddQuestion() {
        System.out.println("\n--- Add New Question ---");
        System.out.print("Enter the question text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the correct answer: ");
        String answer = scanner.nextLine();

        System.out.print("Enter options separated by commas (leave blank for free-text): ");
        String optionsInput = scanner.nextLine();
        List<String> options = null;
        if (!optionsInput.trim().isEmpty()) {
            options = Arrays.stream(optionsInput.split(",")).map(String::trim).collect(Collectors.toList());
        }

        System.out.print("Enter the time limit in seconds (e.g., 15): ");
        int timeLimit = 15; // Default value
        try {
            timeLimit = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, using default of 15 seconds.");
        }

        Question newQuestion = new Question(text, answer, options);
        newQuestion.setTimeLimitInSeconds(timeLimit);

        if (questionManager.addQuestion(newQuestion)) {
            System.out.println("Question added successfully.");
        }
    }

    // Allows the admin to modify any field of an existing question.
    private void handleModifyQuestion() {
        handleListQuestions();
        if (questionManager.getAllQuestions().isEmpty())
            return;

        System.out.print("Enter the number of the question to modify: ");
        int questionIndex;
        try {
            questionIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (questionIndex < 0 || questionIndex >= questionManager.getAllQuestions().size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question existingQuestion = questionManager.getAllQuestions().get(questionIndex);

        System.out.println("\nCurrent text: " + existingQuestion.getText());
        System.out.print("Enter new text (or press Enter to keep current): ");
        String newText = scanner.nextLine();
        if (newText.trim().isEmpty())
            newText = existingQuestion.getText();

        System.out.println("Current answer: " + existingQuestion.getCorrectAnswer());
        System.out.print("Enter new correct answer (or press Enter to keep current): ");
        String newAnswer = scanner.nextLine();
        if (newAnswer.trim().isEmpty())
            newAnswer = existingQuestion.getCorrectAnswer();

        System.out.println("Current time limit: " + existingQuestion.getTimeLimitInSeconds() + "s");
        System.out.print("Enter new time limit (or press Enter to keep current): ");
        String timeInput = scanner.nextLine();
        int newTimeLimit = existingQuestion.getTimeLimitInSeconds();
        if (!timeInput.trim().isEmpty()) {
            try {
                newTimeLimit = Integer.parseInt(timeInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, keeping current time limit.");
            }
        }

        List<String> newOptions = existingQuestion.getOptions();
        System.out.println("Current options: " + (newOptions != null ? newOptions : "None (Free Text)"));
        System.out.print("Enter new options (comma-separated, Enter to keep, 'clear' to remove): ");
        String optionsInput = scanner.nextLine();
        if (optionsInput.trim().equalsIgnoreCase("clear")) {
            newOptions = null;
        } else if (!optionsInput.trim().isEmpty()) {
            newOptions = Arrays.stream(optionsInput.split(",")).map(String::trim).collect(Collectors.toList());
        }

        Question updatedQuestion = new Question(newText, newAnswer, newOptions);
        updatedQuestion.setTimeLimitInSeconds(newTimeLimit);

        if (questionManager.updateQuestion(questionIndex, updatedQuestion)) {
            System.out.println("Question updated successfully.");
        }
    }

    // Allows the admin to delete existing questions
    private void handleDeleteQuestion() {
        handleListQuestions();
        if (questionManager.getAllQuestions().isEmpty())
            return;
        System.out.print("Enter the number of the question to delete: ");
        try {
            int questionNumber = Integer.parseInt(scanner.nextLine());
            if (questionManager.deleteQuestion(questionNumber - 1)) {
                System.out.println("Question deleted successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // Allows the admin to view total number questions
    private void handleViewStatistics() {
        System.out.println("\n--- Question Statistics ---");
        System.out.println("Total number of questions: " + questionManager.getAllQuestions().size());
    }
}