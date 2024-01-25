package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Introspected
@Serdeable
public record CarDto(Long id,
                     @NotEmpty(message = "Car should have a brand") String brand,
                     @NotEmpty(message = "Car should have a model") String model,
                     @NotNull(message = "Car should have a year") Integer year) {
}
