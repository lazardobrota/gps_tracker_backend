package gps.tracker.backend.mappers;

import gps.tracker.backend.dto.requests.car.CarCreateRequest;
import gps.tracker.backend.dto.responses.CarResponse;
import gps.tracker.backend.models.Car;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class CarMapper {
    
    public Car toCar(CarCreateRequest carCreateRequest) {
        LocalDateTime currTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return new Car(
                Car.pkPrefix + carCreateRequest.getUserId(),
                Car.skPrefix + UUID.randomUUID(),
                carCreateRequest.getLicensePlate(),
                carCreateRequest.getModel(),
                carCreateRequest.getYearOfProduction(),
                carCreateRequest.getYearOfPurchase(),
                currTime,
                currTime
        );
    }

    public CarResponse toCarResponse(Car car) {
        return new CarResponse(
                car.getSk().replace(Car.skPrefix, ""),
                car.getLicensePlate(),
                car.getModel(),
                car.getYearOfProduction(),
                car.getYearOfPurchase(),
                car.getCreatedAt(),
                car.getModifiedAt()
        );
    }
}
