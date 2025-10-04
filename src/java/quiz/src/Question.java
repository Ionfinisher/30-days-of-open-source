public class Question{
    private String text;
    private String correctAnswer;

    public Question(String text,String correctAnswer){
        this.text=text;
        this.correctAnswer=correctAnswer;
    }

    public String getText(){
        return text;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public void setText(String text){
        this.text=text;
    }

    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer=correctAnswer;
    }

    public boolean verifierResponse(String userResponse){
        return correctAnswer.equalsIgnoreCase(userResponse);
    }
}