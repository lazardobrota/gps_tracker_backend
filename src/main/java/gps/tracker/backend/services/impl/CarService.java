package gps.tracker.backend.services.impl;

import gps.tracker.backend.dto.PageResult;
import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.dto.requests.car.CarCreateRequest;
import gps.tracker.backend.dto.responses.CarResponse;
import gps.tracker.backend.exceptions.HttpException;
import gps.tracker.backend.mappers.CarMapper;
import gps.tracker.backend.models.Car;
import gps.tracker.backend.repositories.ICarRepository;
import gps.tracker.backend.repositories.IUserRepository;
import gps.tracker.backend.services.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.stream.Collectors;

@Service
//@Transactional
@RequiredArgsConstructor
public class CarService implements ICarService {

    private final ICarRepository carRepository;
    private final IUserRepository userRepository;
    private final CarMapper carMapper;

    @Override
    public PageResult<CarResponse> findAll(CarQuery carQuery) {
        Page<Car> cars = carRepository.findAll(carQuery);
        return new PageResult<>(cars.items().stream().map(carMapper::toCarResponse).collect(Collectors.toList()), cars.lastEvaluatedKey());
    }

    @Override
    public CarResponse find(String id) {
        return carMapper.toCarResponse(carRepository.load(id).orElseThrow(() -> new HttpException("No car with id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public CarResponse create(CarCreateRequest carCreateRequest) {
        userRepository.load(carCreateRequest.getUserId())
                .orElseThrow(() -> new HttpException("No user with id: " + carCreateRequest.getUserId(), HttpStatus.BAD_REQUEST));

        return carMapper.toCarResponse(carRepository.save(carMapper.toCar(carCreateRequest)));
    }

    @Override
    public CarResponse update(String id) {
        return null; //TODO
    }
}
