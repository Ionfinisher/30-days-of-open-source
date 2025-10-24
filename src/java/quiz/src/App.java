import java.util.List;

public class App {
    public static void main(String[] args) {
        //Set up the data manager
        String filePath = "src/java/quiz/data/questions.json";
        QuestionManager questionManager = new QuestionManager(filePath);
        List<Question> questions = questionManager.loadQuestionsFromFile(); 
        
        //Create a new Quiz instance with the loaded questions
        Quiz quiz = new Quiz(questions);

        //Start the quiz
        quiz.start();
    }
}