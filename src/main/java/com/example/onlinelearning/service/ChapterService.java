package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Chapter;
import com.example.onlinelearning.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter create(Chapter chapter){
        chapterRepository.save(chapter);
        return chapter;
    }
}
