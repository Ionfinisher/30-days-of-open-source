import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionManager {
    private List<Question> questions;
    private String filePath;

    // Constructor: Initializes the manager and loads existing questions from the
    // file.
    public QuestionManager(String filePath) {
        this.filePath = filePath;
        this.questions = loadQuestionsFromFile();
    }

    // Adds a new question after validating it and saves the updated list.
    public boolean addQuestion(Question newQuestion) {
        // Validate question text is not empty
        if (newQuestion == null || newQuestion.getText() == null || newQuestion.getText().trim().isEmpty()) {
            System.err.println("Validation failed: Question text cannot be empty.");
            return false;
        }
        // Automatically set question type based on options
        if (newQuestion.getOptions() != null && !newQuestion.getOptions().isEmpty()) {
            newQuestion.setType(QuestionType.MULTIPLE_CHOICE);
        } else {
            newQuestion.setType(QuestionType.FREE_TEXT);
        }

        // Check for duplicate questions to prevent conflicts
        for (Question existingQuestion : questions) {
            if (existingQuestion.getText().equalsIgnoreCase(newQuestion.getText())) {
                System.err.println("Conflict: This question already exists.");
                return false;
            }
        }

        this.questions.add(newQuestion);
        return saveQuestionsToFile();
    }

    // Saves the current list of questions back to the JSON file.
    private boolean saveQuestionsToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        QuestionList questionListWrapper = new QuestionList();
        questionListWrapper.setQuestions(this.questions);

        try (Writer writer = new FileWriter(this.filePath)) {
            gson.toJson(questionListWrapper, writer);
            System.out.println("Successfully saved " + this.questions.size() + " questions.");
            return true;
        } catch (Exception e) {
            System.err.println("Error saving questions to file: " + e.getMessage());
            return false;
        }
    }

    // Loads all questions from the specified JSON file.
    private List<Question> loadQuestionsFromFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(this.filePath)) {
            QuestionList questionList = gson.fromJson(reader, QuestionList.class);
            if (questionList != null && questionList.getQuestions() != null) {
                System.out.println("Successfully loaded " + questionList.getQuestions().size() + " questions.");
                return new ArrayList<>(questionList.getQuestions());
            }
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error reading questions file: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}