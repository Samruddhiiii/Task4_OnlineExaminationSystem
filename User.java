import java.util.HashMap;
import java.util.Map;

public class User {
    private String userId;
    private String password;
    private String profile;

    public User(String userId, String password, String profile) {
        this.userId = userId;
        this.password = password;
        this.profile = profile;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    // Login validation
    public static User login(String userId, String password, Map<String, User> users) {
        User user = users.get(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
