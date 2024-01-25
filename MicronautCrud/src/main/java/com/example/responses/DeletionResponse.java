package com.example.responses;

import com.example.dto.CarDto;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record DeletionResponse(CarDto deletedCarDto, String deletionMessage) {
}
