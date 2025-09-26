package APC.example.Eventra.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    
    // Default constructor
    public AuthResponse() {}
    
    // Constructor with success and message only
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    // Constructor with all fields
    public AuthResponse(boolean success, String message, String userId, String email, String firstName, String lastName) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}