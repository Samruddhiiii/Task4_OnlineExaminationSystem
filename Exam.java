import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Exam {
    private List<Question> questions;
    private int[] selectedAnswers;
    private int currentQuestionIndex;
    private Timer timer;
    private int remainingTime; // in seconds

    public Exam(List<Question> questions, int duration) { // duration in seconds
        this.questions = questions;
        this.selectedAnswers = new int[questions.size()];
        this.currentQuestionIndex = 0;
        this.remainingTime = duration;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                if (remainingTime <= 0) {
                    timer.cancel();
                    autoSubmit();
                }
            }
        }, 1000, 1000); // Runs every second
    }

    public void selectAnswer(int questionIndex, int answerIndex) {
        if (questionIndex >= 0 && questionIndex < selectedAnswers.length) {
            selectedAnswers[questionIndex] = answerIndex;
        }
    }

    public void autoSubmit() {
        System.out.println("Time's up! Auto-submitting the exam...");
        // Submit the exam
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        System.out.println("Exam session ended.");
    }
    
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    public void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
        }
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}
