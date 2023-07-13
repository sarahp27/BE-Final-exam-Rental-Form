package com.final_exam.rental_service;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    public long id;
    public long car_id;
    public String name;
    public String address;
    public String phoneNumber;
    public String driverLicense;
    public LocalDateTime pickupTime;
    public LocalDateTime dropoffTime;
    public boolean insurance;
    public int price;
    public int totalPrice;
    
}
