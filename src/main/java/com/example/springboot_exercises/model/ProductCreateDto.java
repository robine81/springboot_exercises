package com.example.springboot_exercises.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;


public record ProductCreateDto(
        @NotBlank String name,
        @NotNull @PositiveOrZero double price,
        @NotNull String status)
       // @NotNull LocalDate createdDate
       // @NotBlank String category
    {}