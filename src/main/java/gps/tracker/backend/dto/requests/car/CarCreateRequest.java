package gps.tracker.backend.dto.requests.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateRequest {

    private String userId;

    private String licensePlate;

    private String model;

    private LocalDateTime yearOfProduction;

    private LocalDateTime yearOfPurchase;
}
