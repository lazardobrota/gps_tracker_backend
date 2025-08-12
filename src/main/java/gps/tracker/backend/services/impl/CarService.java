package gps.tracker.backend.services.impl;

import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.exceptions.HttpException;
import gps.tracker.backend.mappers.CarMapper;
import gps.tracker.backend.repositories.ICarRepository;
import gps.tracker.backend.dto.requests.car.CarCreateRequest;
import gps.tracker.backend.dto.responses.CarResponse;
import gps.tracker.backend.repositories.IUserRepository;
import gps.tracker.backend.services.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService implements ICarService {

    private final ICarRepository carRepository;
    private final IUserRepository userRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarResponse> findAll(CarQuery carQuery) {
        return carRepository.findAll(carQuery).stream().map(carMapper::toCarResponse).collect(Collectors.toList());
    }

    @Override
    public CarResponse find(String id) {
        return null; //TODO
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
