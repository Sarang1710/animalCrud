package com.saurav.animals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.animals.enitity.Animals;

public interface AnimalRepository extends JpaRepository<Animals, Long> {
	List<Animals> findByNameContaining(String query);

	List<Animals> findAllByOrderByNameAsc();

	List<Animals> findAllByOrderByNameDesc();
}
