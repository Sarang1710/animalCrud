package com.saurav.animals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

	private AnimalRepository animalRepository;

	@Autowired
	public AnimalServiceImpl(AnimalRepository theAnimalRepository) {
		this.animalRepository = theAnimalRepository;
	}

	@Override
	public Animals save(Animals theAnimals, MultipartFile file) {
		return animalRepository.save(theAnimals);
	}

	@Override
	public List<Animals> findAll() {
		return animalRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		animalRepository.deleteById(id);
	}

}
