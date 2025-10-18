import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class QuestionManager {
    public List<Question> loadQuestionsFromFile(String filePath) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {
            // Convert JSON File to Java Object
            QuestionList questionList = gson.fromJson(reader, QuestionList.class);
            System.out.println("Successfully loaded " + questionList.getQuestions().size() + " questions.");
            return questionList.getQuestions();
        } catch (Exception e) {
            // Handle errors gracefully, e.g., if the file is not found or corrupted
            System.err.println("Error reading questions file: " + e.getMessage());
            // Return an empty list to prevent the app from crashing
            return Collections.emptyList();
        }

    }

}