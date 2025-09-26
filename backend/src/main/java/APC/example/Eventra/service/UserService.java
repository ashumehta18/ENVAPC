package APC.example.Eventra.service;

import APC.example.Eventra.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(System.currentTimeMillis() + "");
        }
        users.put(user.getId(), user);
        return user;
    }
    
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }
    
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> email.equals(user.getEmail()));
    }
    
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }
    
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}