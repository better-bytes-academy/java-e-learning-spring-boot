package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Lesson;
import com.example.onlinelearning.entity.LessonProgress;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.LessonProgressRepository;
import com.example.onlinelearning.repository.LessonRepository;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.response.LessonProgressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonProgressRepository lessonProgressRepository;

    @Autowired
    private UserRepository userRepository;

    public Lesson create(Lesson lesson, MultipartFile file) throws IOException {
        String fileName = null;
        String filePath = null;

        if(file != null && !file.isEmpty()){
            //lấy tên gốc của file VD: chuong1.pdf
            String originalFileName = file.getOriginalFilename();

            // Lấy phần mở rộng của file (ví dụ .mp4, .jpg)
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String uuid = UUID.randomUUID().toString();
            // tạo uuid + tên file gốc
            fileName = uuid + "_" + originalFileName;

            // Lấy thư mục project và trỏ đến uploads
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // Tạo thư mục nếu chưa có (tránh lỗi khi deploy)
            }


            //tạo đường dẫn lưu file
            filePath = uploadDir + File.separator + fileName;

            file.transferTo(new File(filePath));
        }

        lesson.setContentUrl(filePath);
        lesson.setFileName(fileName);
        lessonRepository.save(lesson);

        return lesson;
    }



    public ResponseEntity<Resource> viewFile(Map<String,Object> Map_lessonId) throws FileNotFoundException {
        Integer lessonId = (Integer) Map_lessonId.get("lessonId");
        Lesson lesson = lessonRepository.findByLessonId(lessonId);

        String filePath = lesson.getContentUrl();
        String fileName = lesson.getFileName();

        // Tạo đối tượng file từ filePath
        File file = new File(filePath);
        if (!file.exists()) {
            ResponseEntity.notFound().build(); // Trả về 404 nếu file không tồn tại
        }

// Lấy phần mở rộng
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        if("pdf".equals(fileExtension)) {
            // Tạo InputStreamResource từ file
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // Trả về file với header "Content-Disposition" để trình duyệt mở file
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_PDF) // Bạn có thể điều chỉnh content type tùy theo loại file
                    .body(resource);
        }
        else if("mp4".equals(fileExtension)){
            // Tạo InputStreamResource từ file
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // Trả về file với header "Content-Disposition" để trình duyệt mở file
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.valueOf("video/mp4")) // Bạn có thể điều chỉnh content type tùy theo loại file
                    .body(resource);
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<String> lessonProgressClickComplete(String userEmail, Map<String,Object> map_lessonId){
        Integer lessonId = (Integer) map_lessonId.get("lessonId");
        User user = userRepository.findByEmail(userEmail).get();
        Integer userId = user.getUser_id();

        LessonProgress lessonProgress = new LessonProgress();

        lessonProgress.setUserId(userId);
        lessonProgress.setLessonId(lessonId);
        lessonProgress.setCompleted(true);

        lessonProgressRepository.save(lessonProgress);

        return ResponseEntity.ok("Complete the lesson");
    }

    public ResponseEntity<LessonProgressResponse> lessonProgressSelect(String userEmail, Map<String,Object> map_courseId){
        User user = userRepository.findByEmail(userEmail).get();

        Integer userId = user.getUser_id();
        Integer courseId = (Integer) map_courseId.get("courseId");
        int countLessonCompleted =  lessonProgressRepository.countLessonCompleted(courseId,userId).intValue();
        int countAllLessonOfCourse = lessonProgressRepository.countAllLessonOfCourse(courseId).intValue();
        double progressPercentage = countLessonCompleted * 100 / (double)countAllLessonOfCourse;
        progressPercentage = Math.round(progressPercentage * 100.0) / 100.0;

        LessonProgressResponse lessonProgressResponse = new LessonProgressResponse();

        lessonProgressResponse.setUserId(userId);
        lessonProgressResponse.setCourseId(courseId);
        lessonProgressResponse.setCompletedLessons(countLessonCompleted);
        lessonProgressResponse.setTotalLessons(countAllLessonOfCourse);
        lessonProgressResponse.setProgressPercentage(progressPercentage);

        return ResponseEntity.ok(lessonProgressResponse);
    }
}
