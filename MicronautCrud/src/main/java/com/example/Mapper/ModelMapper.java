package com.example.Mapper;


import com.example.dto.CarDto;
import com.example.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    @Mapping(target = "year", source = "yearOfManufacture")
    @Mapping(target = "brand", source = "brand.name")
    CarDto fromCarEntityToCarDto(CarEntity carEntity);

    @Mapping(target = "yearOfManufacture", source = "year")
    @Mapping(target = "brand", ignore = true)
    CarEntity fromCarDtoToCarEntity(CarDto carDto);

}
