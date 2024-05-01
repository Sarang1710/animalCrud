package com.saurav.animals.controllers;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.saurav.animals.enitity.Animals;
import com.saurav.animals.repository.AnimalRepository;
import com.saurav.animals.service.AnimalService;

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
	public String search(@RequestParam("query") String query, Model model) {
		List<Animals> searchAnimals = animalRepository.findByNameContaining(query);
		model.addAttribute("animals", searchAnimals);
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
			if (!file.isEmpty()) {
				animals.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
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
	public String delete(@RequestParam("animalId") long id) {
		animalService.deleteById(id);
		return "redirect:/animals/list";
	}

}
