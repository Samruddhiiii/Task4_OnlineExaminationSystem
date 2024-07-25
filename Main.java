import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample user data
        Map<String, User> users = new HashMap<>();
        users.put("user1", new User("user1", "password1", "User One's profile"));
        users.put("user2", new User("user2", "password2", "User Two's profile"));

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User currentUser = User.login(userId, password, users);

        if (currentUser != null) {
            System.out.println("Login successful!");
            System.out.println("Profile: " + currentUser.getProfile());
            updateProfile(scanner, currentUser);
            // Continue with exam setup and start

            // Sample questions
            List<Question> questions = Arrays.asList(
                new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
                new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2)
            );

            Exam exam = new Exam(questions, 60); // 1-minute exam
            exam.start();

            // Exam interaction loop
            while (exam.getRemainingTime() > 0) {
                Question currentQuestion = exam.getCurrentQuestion();
                System.out.println(currentQuestion.getQuestionText());
                String[] options = currentQuestion.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                System.out.print("Select an answer (1-4): ");
                int answer = scanner.nextInt();
                exam.selectAnswer(exam.getCurrentQuestionIndex(), answer - 1);

                System.out.print("1. Next Question\n2. Previous Question\n3. Submit\nChoose an option: ");
                int option = scanner.nextInt();
                if (option == 1) {
                    exam.nextQuestion();
                } else if (option == 2) {
                    exam.previousQuestion();
                } else if (option == 3) {
                    exam.stop();
                    break;
                }
            }
            System.out.println("Exam completed.");
        } else {
            System.out.println("Invalid user ID or password.");
        }
    }

    private static void updateProfile(Scanner scanner, User currentUser) {
        System.out.print("Do you want to update profile or password? (yes/no): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter new profile: ");
            String newProfile = scanner.nextLine();
            currentUser.setProfile(newProfile);
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            currentUser.setPassword(newPassword);
            System.out.println("Profile and password updated successfully.");
        }
    }
}

