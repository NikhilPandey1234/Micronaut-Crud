package com.example.service;

import com.example.dto.CarDto;
import com.example.responses.DeletionResponse;

import java.util.List;

public interface CarService {

    CarDto save(CarDto carDto);

    CarDto update(CarDto carDto, Long id);

    List<CarDto> findAll();

    CarDto findById(Long id);

    DeletionResponse delete(Long id);
}
