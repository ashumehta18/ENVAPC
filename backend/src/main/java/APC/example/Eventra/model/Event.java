package APC.example.Eventra.model;

import java.time.LocalDateTime;

public class Event {
    private String id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private double price;
    private int totalTickets;
    private int availableTickets;
    private String imageUrl;
    private String category;

    public Event() {}

    public Event(String id, String name, String description, LocalDateTime eventDate, 
                 String location, double price, int totalTickets, String imageUrl, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.price = price;
        this.totalTickets = totalTickets;
        this.availableTickets = totalTickets;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return availableTickets > 0;
    }

    public void bookTicket() {
        if (availableTickets > 0) {
            availableTickets--;
        }
    }
}