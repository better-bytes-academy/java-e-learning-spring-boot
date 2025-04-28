package com.example.onlinelearning.request;

import com.example.onlinelearning.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public class UpdateCourses {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    private String category;
    private Level level;

    public UpdateCourses() {
    }

    public UpdateCourses(String title, String description, String category, Level level) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
