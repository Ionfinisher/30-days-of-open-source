import java.util.List;

/**
 * Represents a single question, supporting both free-text and multiple-choice
 * formats.
 */
public class Question {
    private String text;
    private String correctAnswer;
    private QuestionType Qtype;
    private List<String> options;

    // A no-argument constructor for Gson to instantiate the object.
    public Question() {
    }

    /**
     * Constructor for FREE_TEXT question (open-ended answer).
     */
    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.Qtype = QuestionType.FREE_TEXT;
        this.options = null;
    }

    /**
     * Constructor for MULTIPLE_CHOICE question.
     * 
     * @param options List of strings representing the possible choices (A, B,
     *                C...).
     */
    public Question(String text, String correctAnswer, List<String> options) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.Qtype = QuestionType.MULTIPLE_CHOICE;
        this.options = options;
    }

    /**
     * Generates a formatted string of multiple-choice options (e.g., "A. Option
     * 1\nB. Option 2\n").
     * 
     * @return Labeled options, or an empty string if not a multiple-choice
     *         question.
     */
    public String getFormattedOptions() {
        if (this.Qtype != QuestionType.MULTIPLE_CHOICE || options == null || options.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        char label = 'A';
        for (String option : options) {
            sb.append(label++).append(". ").append(option).append("\n");
        }
        return sb.toString();
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public QuestionType getType() {
        return Qtype;
    }

    public void setType(QuestionType Qtype) {
        this.Qtype = Qtype;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Verifies if the user's response is correct based on the question type.
     * Handles case-insensitive comparison for FREE_TEXT and letter validation
     * (A/B/C/D)
     * for MULTIPLE_CHOICE questions.
     *
     * @param userResponse The user's submitted answer string.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean verifierResponse(String userResponse) {
        if (this.Qtype == QuestionType.FREE_TEXT) {
            return correctAnswer.equalsIgnoreCase(userResponse);
        }

        if (this.Qtype == QuestionType.MULTIPLE_CHOICE) {
            String userChoice = userResponse.trim().toUpperCase();

            if (userChoice.length() != 1) {
                return false;
            }

            int index = userChoice.charAt(0) - 'A';

            if (index >= 0 && index < options.size()) {
                return options.get(index).equalsIgnoreCase(this.correctAnswer);
            }
        }
        return false;
    }
}