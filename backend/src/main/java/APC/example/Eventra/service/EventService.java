package APC.example.Eventra.service;

import APC.example.Eventra.model.Event;
import APC.example.Eventra.model.Booking;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final Map<String, Event> events = new ConcurrentHashMap<>();
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public EventService() {
        initializeEvents();
    }

    private void initializeEvents() {
        // Create 6 sample events
        events.put("1", new Event("1", "Tech Conference 2025", 
            "Annual technology conference featuring latest innovations", 
            LocalDateTime.of(2025, 12, 15, 9, 0),
            "Convention Center, New York", 299.99, 100,
            "https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=400", "Technology"));

        events.put("2", new Event("2", "Music Festival - Summer Vibes", 
            "3-day music festival with top artists", 
            LocalDateTime.of(2025, 7, 20, 18, 0),
            "Central Park, New York", 199.99, 500,
            "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=400", "Music"));

        events.put("3", new Event("3", "Food & Wine Expo", 
            "Culinary experience with world-class chefs", 
            LocalDateTime.of(2025, 8, 10, 12, 0),
            "Culinary Institute, California", 149.99, 200,
            "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=400", "Food"));

        events.put("4", new Event("4", "Startup Pitch Competition", 
            "Young entrepreneurs showcase innovative ideas", 
            LocalDateTime.of(2025, 9, 5, 10, 0),
            "Innovation Hub, San Francisco", 99.99, 150,
            "https://images.unsplash.com/photo-1559136555-9303baea8ebd?w=400", "Business"));

        events.put("5", new Event("5", "Art & Culture Exhibition", 
            "Contemporary art showcase from emerging artists", 
            LocalDateTime.of(2025, 10, 12, 11, 0),
            "Metropolitan Museum, New York", 79.99, 300,
            "https://images.unsplash.com/photo-1578321272176-b7bbc0679853?w=400", "Art"));

        events.put("6", new Event("6", "Sports Championship Final", 
            "Championship final match with live commentary", 
            LocalDateTime.of(2025, 11, 8, 19, 0),
            "Sports Arena, Chicago", 399.99, 250,
            "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=400", "Sports"));
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    public Optional<Event> getEventById(String id) {
        return Optional.ofNullable(events.get(id));
    }

    public List<Event> getAvailableEvents() {
        return events.values().stream()
                .filter(Event::isAvailable)
                .collect(Collectors.toList());
    }

    public boolean bookTicket(String eventId, String userId, String userEmail, String userName) {
        Event event = events.get(eventId);
        if (event != null && event.isAvailable()) {
            // Check if user already booked this event
            boolean alreadyBooked = bookings.values().stream()
                    .anyMatch(booking -> booking.getUserId().equals(userId) && booking.getEventId().equals(eventId));
            
            if (alreadyBooked) {
                return false; // Already booked
            }

            event.bookTicket();
            Booking booking = new Booking(userId, eventId, event.getName(), event.getPrice(), userEmail, userName);
            bookings.put(booking.getId(), booking);
            return true;
        }
        return false;
    }

    public List<Booking> getUserBookings(String userId) {
        return bookings.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }

    public boolean isEventBookedByUser(String eventId, String userId) {
        return bookings.values().stream()
                .anyMatch(booking -> booking.getUserId().equals(userId) && booking.getEventId().equals(eventId));
    }
}