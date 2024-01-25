package com.example.controllers;

import com.example.dto.CarDto;
import com.example.responses.DeletionResponse;
import com.example.service.CarService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.List;

@Controller("/car")
@Validated
public class CarController {

    @Inject
    private CarService carServices;

    @Get("/{id}")
    public CarDto getById(@PathVariable Long id){
        return carServices.findById(id);
    }

    @Get
    public List<CarDto> getAll(){return carServices.findAll();}
    @Post
    @Status(HttpStatus.CREATED)
    public CarDto save(@Valid @Body CarDto carDto){
        return carServices.save(carDto);
    }

    @Put("/{id}")
    public CarDto update(@Valid @Body CarDto carDto, @PathVariable Long id){
        return carServices.update(carDto,id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.OK)
    public DeletionResponse delete(@PathVariable Long id) {
        return carServices.delete(id);
    }

}
