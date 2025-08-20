package gps.tracker.backend.controller;

import gps.tracker.backend.dto.PageResult;
import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.dto.requests.car.CarCreateRequest;
import gps.tracker.backend.dto.responses.CarResponse;
import gps.tracker.backend.endpoints.Endpoints;
import gps.tracker.backend.services.ICarService;
import gps.tracker.backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class CarController {

    private final ICarService carService;

    //TODO Pagination
    @GetMapping(Endpoints.Car.getAll)
    public ResponseEntity<PageResult<CarResponse>> findAll(@RequestParam(required = false) String userId) {
        CarQuery carQuery = new CarQuery(userId);
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(carService.findAll(carQuery)));
    }

    @GetMapping(Endpoints.Car.getOne)
    public ResponseEntity<CarResponse> findOne(@PathVariable("id") String id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(carService.find(id)));
    }

    @PostMapping(Endpoints.Car.create)
    public ResponseEntity<CarResponse> create(@RequestBody @Valid CarCreateRequest carCreateRequest) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(carService.create(carCreateRequest)));
    }

//    @PutMapping(Endpoints.Car.update)
//    public ResponseEntity<CarResponse> update(@PathVariable("id") String id, @RequestBody @Valid CarUpdateRequest carUpdateRequest) {
//        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(carService.update(id, carUpdateRequest)));
//    }
}
