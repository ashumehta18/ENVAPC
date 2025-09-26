package APC.example.Eventra.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private boolean success;
    private String message;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}