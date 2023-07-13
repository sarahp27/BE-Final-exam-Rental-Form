package com.final_exam.rental_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Rental> getallCars(){
        return renRepo.findAll();
    }

    @PostMapping(value = "/post")
    public void postBooking(@RequestBody Rental ren){
        renRepo.save(ren);
    }
    
}
