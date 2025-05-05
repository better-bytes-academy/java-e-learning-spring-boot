package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.CoursesRepository;
import com.example.onlinelearning.repository.EnrollmentRepository;
import com.example.onlinelearning.repository.LessonProgressRepository;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.response.CourseStatisticResponse;
import com.example.onlinelearning.response.StudentStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportAndStatisticsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LessonProgressRepository lessonProgressRepository;

    public StudentStatisticsResponse studentProgress(UserDetails userDetails, Integer courseId) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(userId != coursesRepository.findById(courseId).get().getCreator_id()){
            throw new IllegalArgumentException("You are not the course creator.");
        }

        List<Integer> listUserOfCourse = enrollmentRepository.selectListUserIdByCourseId(courseId);

        StudentStatisticsResponse studentStatisticsResponse = new StudentStatisticsResponse();
        studentStatisticsResponse.setCourseId(courseId);
        studentStatisticsResponse.setCourseTitle(coursesRepository.findById(courseId).get().getTitle());
        studentStatisticsResponse.setTotalStudents(listUserOfCourse.size());
        studentStatisticsResponse.setTotalLessons(lessonProgressRepository.countAllLessonOfCourse(courseId).intValue());

        for(int i = 0 ; i < listUserOfCourse.size() ; i++){
            Map<String,Object> student = new LinkedHashMap<>();
            student.put("userId", listUserOfCourse.get(i));
            student.put("email", userRepository.findById(listUserOfCourse.get(i)).get().getEmail());
            student.put("userName", userRepository.findById(listUserOfCourse.get(i)).get().getName());
            student.put("completedLessons", lessonProgressRepository.countLessonCompleted(courseId,listUserOfCourse.get(i)).intValue());

            Double completionPercent =  Double.parseDouble(student.get("completedLessons").toString()) * 100 / studentStatisticsResponse.getTotalLessons();
            completionPercent = Math.round(completionPercent * 100.0) / 100.0;
            student.put("completionPercent", completionPercent);

            studentStatisticsResponse.getStudents().add(student);
        }

        return studentStatisticsResponse;
    }

    public CourseStatisticResponse courseStatistic(UserDetails userDetails, Integer courseId) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(userId != coursesRepository.findById(courseId).get().getCreator_id()){
            throw new IllegalArgumentException("You are not the course creator.");
        }

        List<Integer> listUserOfCourse = enrollmentRepository.selectListUserIdByCourseId(courseId);


        CourseStatisticResponse courseStatisticResponse = new CourseStatisticResponse();
        courseStatisticResponse.setCourseId(courseId);
        courseStatisticResponse.setCourseTitle(coursesRepository.findById(courseId).get().getTitle());
        courseStatisticResponse.setTotalStudents(listUserOfCourse.size());

        Integer completedStudents = 0;
        Integer totalLessonsOfCourse = Integer.parseInt(lessonProgressRepository.countAllLessonOfCourse(courseId).toString());

        for (int i = 0 ; i < listUserOfCourse.size() ; i++){
            if( Integer.parseInt(lessonProgressRepository.countLessonCompleted(courseId,listUserOfCourse.get(i)).toString()) == totalLessonsOfCourse){
                completedStudents++;
            }
        }

        courseStatisticResponse.setCompletedStudents(completedStudents);

        Double completionRate = completedStudents.doubleValue() * 100 / listUserOfCourse.size();
        completionRate = Math.round(completionRate * 100.0) / 100.0;

        courseStatisticResponse.setCompletionRate(completionRate);

        return courseStatisticResponse;
    }
}
