package com.pe.edu.upc.petcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/")
public class InitialController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getInitial() {

        return "--- Deployment API PetCare Open-Source \n https://petcare-demo.herokuapp.com/ @Carlos Castilla ";
    }
}