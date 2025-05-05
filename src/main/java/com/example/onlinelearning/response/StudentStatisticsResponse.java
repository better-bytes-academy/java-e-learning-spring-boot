package com.example.onlinelearning.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentStatisticsResponse implements Serializable {
    private Integer courseId;
    private String courseTitle;

    private Integer totalStudents;
    private Integer totalLessons;
    private List<Map<String,Object>> students = new ArrayList<>();

    public StudentStatisticsResponse() {
    }

    public StudentStatisticsResponse(Integer courseId, String courseTitle,Integer totalStudents, Integer totalLessons, List<Map<String, Object>> students) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.totalStudents = totalStudents;
        this.totalLessons = totalLessons;
        this.students = students;
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

    public Integer getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(Integer totalLessons) {
        this.totalLessons = totalLessons;
    }

    public List<Map<String, Object>> getStudents() {
        return students;
    }

    public void setStudents(List<Map<String, Object>> students) {
        this.students = students;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }
}
