package com.example.notesproject;

public class Note {
    private String title;
    private String content;
    private String category;

    // Constructor
    public Note(int anInt, String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Optional: Setter methods if needed
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
