package by.edik.car_api.web.dto.request;

import by.edik.car_api.dao.model.Condition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAdRequest {
    private Long adId;
    private Long userId;
    private Integer year;
    private String brand;
    private String model;
    private Integer engineVolume;
    private Condition condition;
    private Long mileage;
    private Integer enginePower;
    private String ownerName;
    private List<String> ownerPhoneNumbers;
    private List<String> pictureReferences;
}
