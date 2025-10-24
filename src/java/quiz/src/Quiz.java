import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Manages the entire process of running a quiz session.
 */
public class Quiz {
    private final List<Question> questions;
    private final Scanner scanner;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.scanner = new Scanner(System.in);
        this.score = 0;
    }

    /**
     * Starts and runs the entire quiz loop from beginning to end.
     */
    public void start() {
        if (questions.isEmpty()) {
            System.out.println("No questions available to start the quiz.");
            return;
        }

        System.out.println("--- Quiz Starting! ---");

        for (Question question : questions) {
            askQuestion(question);
        }

        displayFinalScore();
        scanner.close();
    }

    /**
     * Handles the logic for asking a single question with a real-time countdown
     * timer.
     */
    private void askQuestion(Question question) {
        System.out.println("\n--------------------");
        System.out.println(question.getText());

        if (question.getOptions() != null) {
            question.getOptions().forEach(System.out::println);
        }

        int timeLimit = question.getTimeLimitInSeconds();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Waits for the user to type their answer.
        Callable<String> inputTask = () -> {
            System.out.print("Your answer: ");
            return scanner.nextLine();
        };
        Future<String> future = executor.submit(inputTask);

        // Prints the countdown timer every second.
        Runnable countdownTask = () -> {
            for (int i = timeLimit; i > 0; i--) {
                // Stop printing if the user has already answered.
                if (future.isDone()) {
                    break;
                }
                System.out.print("\rTime remaining: " + i + "s  ");
                try {
                    Thread.sleep(1000); // Wait for one second.
                } catch (InterruptedException e) {
                    break;
                }
            }
        };
        executor.submit(countdownTask);

        String userAnswer = "";
        try {
            // The main thread waits here for the input task to complete, but with a
            // timeout.
            userAnswer = future.get(timeLimit, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // This happens if the user doesn't answer in time.
            System.out.print("\rTime's up!                \n");
            future.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }

        System.out.println();

        // --- Answer Checking ---
        if (!userAnswer.isEmpty()) {
            if (question.verifierResponse(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.getCorrectAnswer());
            }
        } else {
            System.out.println("The correct answer was: " + question.getCorrectAnswer());
        }
    }

    /**
     * Displays the final score at the end of the quiz.
     */
    private void displayFinalScore() {
        System.out.println("\n--- Quiz Finished! ---");
        System.out.println("Your final score is: " + score + " out of " + questions.size());
    }
}