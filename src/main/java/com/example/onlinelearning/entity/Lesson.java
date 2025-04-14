package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "chapter_id", nullable = false)
    private Integer chapterId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content_url", columnDefinition = "TEXT", nullable = false)
    private String contentUrl;

    @Column(name = "filename", nullable = false)
    private String fileName;

   @Column(name = "sequence_order", nullable = false)
   private Integer sequenceOrder;

    public Lesson() {
    }

    public Lesson(Integer lessonId, Integer chapterId, String title, String contentUrl, String fileName, Integer sequenceOrder) {
        this.lessonId = lessonId;
        this.chapterId = chapterId;
        this.title = title;
        this.contentUrl = contentUrl;
        this.fileName = fileName;
        this.sequenceOrder = sequenceOrder;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }
}
