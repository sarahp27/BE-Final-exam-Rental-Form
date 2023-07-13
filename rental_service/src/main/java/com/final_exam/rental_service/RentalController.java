package com.final_exam.rental_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
@CrossOrigin("*")
public class RentalController {

    @Autowired
    public RentalRepo renRepo;


    @GetMapping(value = "/getAll")
    public List<Rental> getallRentals(){
        return renRepo.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Rental getCarbyId(@PathVariable Long id){
        return renRepo.findById(id).orElse(null);
    }

    @PostMapping(value = "/post")
    public void postRental(@RequestBody Rental ren){
        renRepo.save(ren);
    }
    
}
