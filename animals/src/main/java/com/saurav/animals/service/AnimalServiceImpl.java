package com.saurav.animals.service;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService{

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository  theAnimalRepository){
        this.animalRepository = theAnimalRepository;
    }

    @Override
    public Animals save(Animals theAnimals, MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            theAnimals.setImage(Base64.getEncoder().encodeToString(file.getBytes()).getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        return animalRepository.save(theAnimals);
    }

    @Override
    public List<Animals> findAll() {
        return animalRepository.findAll();
    }
}
