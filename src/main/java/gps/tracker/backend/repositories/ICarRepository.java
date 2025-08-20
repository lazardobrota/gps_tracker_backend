package gps.tracker.backend.repositories;

import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.models.Car;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.Optional;

public interface ICarRepository {
    Page<Car> findAll(CarQuery carQuery);

    Optional<Car> load(String id);

    Car save(Car car);
}
