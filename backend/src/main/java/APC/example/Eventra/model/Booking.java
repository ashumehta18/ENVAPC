package APC.example.Eventra.model;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private String userId;
    private String eventId;
    private String eventName;
    private LocalDateTime bookingDate;
    private double price;
    private String userEmail;
    private String userName;

    public Booking() {}

    public Booking(String userId, String eventId, String eventName, double price, String userEmail, String userName) {
        this.id = System.currentTimeMillis() + "_" + userId + "_" + eventId;
        this.userId = userId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.bookingDate = LocalDateTime.now();
        this.price = price;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}