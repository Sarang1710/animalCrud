package com.saurav.animals.controllers;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;
import com.saurav.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    private AnimalService animalService;

    @Autowired

    public AnimalController(AnimalService theAnimalService){
        this.animalService = theAnimalService;
    }

    @GetMapping("/list")
    public String showAnimals(Model model){
        List<Animals> animals = animalService.findAll();
        model.addAttribute("animals",animals);
        return "animals/list-animals";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd( Model model){
//        System.out.println(img.getOriginalFilename());
        Animals animals = new Animals();
        model.addAttribute("animals",animals);
        return "animals/animal-form";
    }

    @PostMapping("/save")
    public String saveAnimals(@ModelAttribute("animals")Animals animals,@RequestParam("file") MultipartFile file){
        animalService.save(animals,file);
        return "redirect:/animals/list";
    }
}
