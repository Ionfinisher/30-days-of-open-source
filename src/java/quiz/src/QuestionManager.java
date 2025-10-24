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
    }

    // Adds a new question after validating it and saves the updated list.
    public boolean addQuestion(Question newQuestion) {
        this.questions = loadQuestionsFromFile();
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

    // Deletes a question from the list by its index.
    public boolean deleteQuestion(int index) {
        this.questions = loadQuestionsFromFile();
        if (index >= 0 && index < this.questions.size()) {
            this.questions.remove(index);
            return saveQuestionsToFile();
        } else {
            System.err.println("Error: Invalid question number.");
            return false;
        }
    }

    // Updates an existing question at a specific index.
    public boolean updateQuestion(int index, Question updatedQuestion) {
        this.questions = loadQuestionsFromFile();
        if (index < 0 || index >= this.questions.size()) {
            System.err.println("Error: Invalid question number.");
            return false;
        }

        // Check if the updated text conflicts with any OTHER question
        for (int i = 0; i < this.questions.size(); i++) {
            if (i == index)
                continue;
            if (this.questions.get(i).getText().equalsIgnoreCase(updatedQuestion.getText())) {
                System.err.println("Error: Another question with this text already exists.");
                return false;
            }
        }

        // Automatically set question type before saving
        if (updatedQuestion.getOptions() != null && !updatedQuestion.getOptions().isEmpty()) {
            updatedQuestion.setType(QuestionType.MULTIPLE_CHOICE);
        } else {
            updatedQuestion.setType(QuestionType.FREE_TEXT);
        }

        this.questions.set(index, updatedQuestion);
        return saveQuestionsToFile();
    }

    // Returns the current list of all questions.
    public List<Question> getAllQuestions() {
        return loadQuestionsFromFile();
    }
}