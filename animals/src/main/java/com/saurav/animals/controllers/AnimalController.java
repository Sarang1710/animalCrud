package com.saurav.animals.controllers;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;
import com.saurav.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    private AnimalService animalService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalService theAnimalService) {
        this.animalService = theAnimalService;
    }

    @GetMapping("/list")
    public String showAnimals(Model model) {
        List<Animals> animals = animalService.findAll();
        model.addAttribute("animals", animals);
        return "animals/list-animals";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query,Model model){
        List<Animals>searchAnimals=  animalRepository.findByNameContaining(query);
        model.addAttribute("animals",searchAnimals);
        return "animals/list-animals";
    }

    @GetMapping("/sort")
    public String sort(@RequestParam(name = "sort", defaultValue = "asc") String sort, Model model) {
        List<Animals> sortedAnimals;
        if ("desc".equals(sort)) {
            sortedAnimals = animalRepository.findAllByOrderByNameDesc();
        } else {
            sortedAnimals = animalRepository.findAllByOrderByNameAsc();
        }
        model.addAttribute("animals", sortedAnimals);
        return "animals/list-animals";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Animals animals = new Animals();
        model.addAttribute("animals", animals);
        return "animals/animal-form";
    }

    @PostMapping("/save")
    public String saveAnimals(@ModelAttribute("animals") Animals animals, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {

            } else {
                animals.setImage(file.getOriginalFilename());
                File file1 = new ClassPathResource("static/img").getFile();
                Path path =  Paths.get(file1.getAbsolutePath() + File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            }

            animalService.save(animals, file);
            return "redirect:/animals/list";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "redirect:/animals/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("animalId") long id){
        animalService.deleteById(id);
        return "redirect:/animals/list";
    }


}

