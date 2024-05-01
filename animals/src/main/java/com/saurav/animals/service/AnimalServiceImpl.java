package com.saurav.animals.service;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.lang.Byte.parseByte;

@Service
public class AnimalServiceImpl implements AnimalService{

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository  theAnimalRepository){
        this.animalRepository = theAnimalRepository;
    }

    @Override
    public Animals save(Animals theAnimals, MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains(".."))
//        {
//            System.out.println("not a a valid file");
//        }
//        try {
//            theAnimals.setImage(Arrays.toString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
