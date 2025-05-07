package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.Courses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CoursesRepositoryImp{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Courses> filterCourses(String category,String title){
        StringBuilder select = new StringBuilder("select c from Courses c where 1=1");

        if(category != null){
            select.append(" and c.category like :category");
        }
        if(title != null){
            select.append(" and c.title like :title");
        }

        Query query = entityManager.createQuery(select.toString(),Courses.class);

        if(category != null){
            query.setParameter("category", "%"+category+"%");
        }
        if(title != null){
            query.setParameter("title", "%"+title+"%");
        }

        List<Courses> listCourses = query.getResultList();

        for(int i = 0 ; i < listCourses.size() ; i++){
            Integer courseId = listCourses.get(i).getId();
            listCourses.get(i).setTotalStudents( enrollmentRepository.selectListUserIdByCourseId(courseId).size() );
        }

        return listCourses;
    }
}
