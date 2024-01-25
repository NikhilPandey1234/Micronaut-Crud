package com.example.service.impl;

import com.example.Mapper.ModelMapper;
import com.example.dto.CarDto;
import com.example.entity.BrandEntity;
import com.example.entity.CarEntity;
import com.example.exceptions.NotFoundException;
import com.example.repository.BrandRepository;
import com.example.repository.CarRepository;
import com.example.responses.DeletionResponse;
import com.example.service.CarService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.springframework.boot.Banner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CarServiceImpl implements CarService {

    @Inject
    private CarRepository carRepository;

    @Inject
    private BrandRepository brandRepository;

    @Inject
    private ModelMapper modelMapper;


    @Override
    public CarDto save(CarDto carDto) {
        BrandEntity brandEntity = brandRepository.findByName(carDto.brand())
                .stream()
                .findFirst()
                .orElseGet(() -> brandRepository.save(new BrandEntity(carDto.brand())));

        CarEntity carEntity = modelMapper.fromCarDtoToCarEntity(carDto);
        carEntity.setBrand(brandEntity);

        CarEntity savedCarEntity = carRepository.save(carEntity);

        return modelMapper.fromCarEntityToCarDto(savedCarEntity);
    }

    @Override
    public CarDto update(CarDto carDto, Long id) {
        CarEntity existingCarEntity = carRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Car with ID " + id + " not found"));

        BrandEntity brandEntity = brandRepository.findByName(carDto.brand())
                .stream()
                .findFirst()
                .orElseGet(() -> brandRepository.save(new BrandEntity(carDto.brand())));

        existingCarEntity.setModel(carDto.model());
        existingCarEntity.setYearOfManufacture(carDto.year());
        existingCarEntity.setBrand(brandEntity);

        CarEntity updatedCarEntity = carRepository.update(existingCarEntity);

        return modelMapper.fromCarEntityToCarDto(updatedCarEntity);
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream()
                .map(modelMapper::fromCarEntityToCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto findById(Long id) {
        return carRepository.findById(id)
                .stream()
                .findFirst()
                .map(modelMapper::fromCarEntityToCarDto)
                .orElseThrow(() -> new NotFoundException("Car with ID " + id + " not found"));
    }

    @Override
    public DeletionResponse delete(Long id) {
        CarEntity deletedCarEntity = carRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Car with ID " + id + " not found"));

        CarDto deletedCarDto = modelMapper.fromCarEntityToCarDto(deletedCarEntity);

        carRepository.deleteById(id);
        String deletionMessage = "Car with id " + id + " deleted successfully at " + LocalDateTime.now();
        return new DeletionResponse(deletedCarDto, deletionMessage);
    }
}
