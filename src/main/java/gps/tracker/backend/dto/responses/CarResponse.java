package gps.tracker.backend.dto.responses;

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
