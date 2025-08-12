package gps.tracker.backend.dto.responses;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import gps.tracker.backend.converters.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CarResponse {

    private String id;

    private String licensePlate;

    private String model;

    private LocalDateTime yearOfProduction;

    private LocalDateTime yearOfPurchase;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
