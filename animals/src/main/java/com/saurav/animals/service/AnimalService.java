package com.saurav.animals.service;

import com.saurav.animals.enitity.Animals;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalService {

    Animals save(Animals animals, MultipartFile multipartFile);

    List<Animals> findAll();
}
