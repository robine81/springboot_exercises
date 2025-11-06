package com.example.springboot_exercises.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record ProductCreateDto(
        @NotBlank String name,
        @NotNull @PositiveOrZero double price,
        @NotNull String status,
       // @NotNull LocalDate createdDate
        @NotBlank String category)
    {}