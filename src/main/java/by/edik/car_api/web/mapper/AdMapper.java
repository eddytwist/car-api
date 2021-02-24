package by.edik.car_api.web.mapper;

import by.edik.car_api.model.Ad;
import by.edik.car_api.web.dto.AdDto;
import by.edik.car_api.web.dto.AdFullInformationDto;
import by.edik.car_api.web.dto.AdShortInformationDto;
import by.edik.car_api.web.dto.CreatedAdDto;

public class AdMapper {

    public static AdDto adToAdDto(Ad ad) {
        return AdDto.builder()
                .adId(ad.getUserId())
                .year(ad.getYear())
                .brand(ad.getBrand())
                .model(ad.getModel())
                .engineVolume(ad.getEngineVolume())
                .condition(ad.getCondition())
                .mileage(ad.getMileage())
                .enginePower(ad.getEnginePower())
                .creationTime(ad.getCreationTime())
                .editingTime(ad.getEditingTime())
                .build();
    }

    public static Ad adCreatedDtoToAd(CreatedAdDto createdAdDto) {
        return Ad.builder()
                .year(createdAdDto.getYear())
                .brand(createdAdDto.getBrand())
                .model(createdAdDto.getModel())
                .engineVolume(createdAdDto.getEngineVolume())
                .condition(createdAdDto.getCondition())
                .mileage(createdAdDto.getMileage())
                .enginePower(createdAdDto.getEnginePower())
                .creationTime(createdAdDto.getCreationTime())
                .editingTime(createdAdDto.getEditingTime())
                .build();
    }

    public static AdShortInformationDto adToAdListedDto(Ad ad) {
        return AdShortInformationDto.builder()
                .year(ad.getYear())
                .brand(ad.getBrand())
                .model(ad.getModel())
                .condition(ad.getCondition())
                .mileage(ad.getMileage())
                .creationTime(ad.getCreationTime())
                .build();
    }

    public static AdFullInformationDto adToAdInformationDto(Ad ad) {
        return AdFullInformationDto.builder()
                .year(ad.getYear())
                .brand(ad.getBrand())
                .model(ad.getModel())
                .condition(ad.getCondition())
                .mileage(ad.getMileage())
                .creationTime(ad.getCreationTime())
                .build();
    }
}


