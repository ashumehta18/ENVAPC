package APC.example.Eventra.controller;

import APC.example.Eventra.model.Event;
import APC.example.Eventra.model.Booking;
import APC.example.Eventra.service.EventService;
import APC.example.Eventra.service.UserService;
import APC.example.Eventra.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Events retrieved successfully");
        response.put("totalEvents", events.size());
        response.put("events", events);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEventById(@PathVariable String id) {
        Optional<Event> event = eventService.getEventById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (event.isPresent()) {
            response.put("success", true);
            response.put("event", event.get());
        } else {
            response.put("success", false);
            response.put("message", "Event not found");
        }
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/book")
    public ResponseEntity<Map<String, Object>> bookTicket(
            @PathVariable String eventId, 
            @RequestBody Map<String, String> request) {
        
        Map<String, Object> response = new HashMap<>();
        String userId = request.get("userId");
        
        if (userId == null) {
            response.put("success", false);
            response.put("message", "User ID is required");
            return ResponseEntity.badRequest().body(response);
        }

        // Get user details
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            response.put("success", false);
            response.put("message", "User not found");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userOptional.get();
        
        // Check if event exists
        Optional<Event> eventOptional = eventService.getEventById(eventId);
        if (eventOptional.isEmpty()) {
            response.put("success", false);
            response.put("message", "Event not found");
            return ResponseEntity.badRequest().body(response);
        }

        Event event = eventOptional.get();
        
        // Check if user already booked this event
        if (eventService.isEventBookedByUser(eventId, userId)) {
            response.put("success", false);
            response.put("message", "You have already booked this event");
            return ResponseEntity.badRequest().body(response);
        }

        // Attempt to book the ticket
        boolean bookingSuccess = eventService.bookTicket(eventId, userId, user.getEmail(), 
                user.getFirstName() + " " + user.getLastName());

        if (bookingSuccess) {
            response.put("success", true);
            response.put("message", "Ticket booked successfully!");
            response.put("eventName", event.getName());
            response.put("price", event.getPrice());
        } else {
            response.put("success", false);
            response.put("message", "Unable to book ticket. Event may be sold out.");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/bookings")
    public ResponseEntity<Map<String, Object>> getUserBookings(@PathVariable String userId) {
        List<Booking> bookings = eventService.getUserBookings(userId);
        Map<String, Object> response = new HashMap<>();
        
        response.put("success", true);
        response.put("totalBookings", bookings.size());
        response.put("bookings", bookings);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookings")
    public ResponseEntity<Map<String, Object>> getAllBookings() {
        List<Booking> bookings = eventService.getAllBookings();
        Map<String, Object> response = new HashMap<>();
        
        response.put("success", true);
        response.put("totalBookings", bookings.size());
        response.put("bookings", bookings);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/check/{eventId}")
    public ResponseEntity<Map<String, Object>> checkBookingStatus(
            @PathVariable String userId, 
            @PathVariable String eventId) {
        
        Map<String, Object> response = new HashMap<>();
        boolean isBooked = eventService.isEventBookedByUser(eventId, userId);
        
        response.put("success", true);
        response.put("isBooked", isBooked);
        
        return ResponseEntity.ok(response);
    }
}