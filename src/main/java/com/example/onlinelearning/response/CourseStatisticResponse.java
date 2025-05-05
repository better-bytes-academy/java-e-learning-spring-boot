package com.example.onlinelearning.response;

import java.io.Serializable;

public class CourseStatisticResponse implements Serializable {
    private Integer courseId;
    private String courseTitle;
    private Integer totalStudents;
    private Integer completedStudents;

    private Double completionRate;

    public CourseStatisticResponse() {
    }

    public CourseStatisticResponse(Integer courseId, String courseTitle, Integer totalStudents, Integer completedStudents, Double completionRate) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.totalStudents = totalStudents;
        this.completedStudents = completedStudents;
        this.completionRate = completionRate;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getCompletedStudents() {
        return completedStudents;
    }

    public void setCompletedStudents(Integer completedStudents) {
        this.completedStudents = completedStudents;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }
}
