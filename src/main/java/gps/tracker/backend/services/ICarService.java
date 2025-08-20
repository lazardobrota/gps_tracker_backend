package gps.tracker.backend.services;

import gps.tracker.backend.dto.PageResult;
import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.dto.requests.car.CarCreateRequest;
import gps.tracker.backend.dto.responses.CarResponse;

public interface ICarService {

    PageResult<CarResponse> findAll(CarQuery carQuery);

    CarResponse find(String id);

    CarResponse create(CarCreateRequest carCreateRequest);

    CarResponse update(String id);
}
