package gps.tracker.backend.repositories;

import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.models.Car;
import gps.tracker.backend.models.User;

import java.util.List;
import java.util.Optional;

public interface ICarRepository {
    List<Car> findAll(CarQuery carQuery);

    Optional<Car> load(String id);

    Car save(Car car);
}
