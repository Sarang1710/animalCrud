package com.saurav.animals.repository;

import com.saurav.animals.enitity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository  extends JpaRepository<Animals,Long> {

}
