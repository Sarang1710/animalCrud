package com.saurav.animals.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.saurav.animals.enitity.Animals;

public interface AnimalService {

	Animals save(Animals animals, MultipartFile multipartFile);

	List<Animals> findAll();

	void deleteById(long id);

}
