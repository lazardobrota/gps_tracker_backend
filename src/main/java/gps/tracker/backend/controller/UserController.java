package gps.tracker.backend.controller;

import gps.tracker.backend.endpoints.Endpoints;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.responses.UserResponse;
import gps.tracker.backend.services.IUserService;
import gps.tracker.backend.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(Endpoints.User.base)
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> findAll(@RequestBody UserCreateRequest userCreateRequest) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.create(userCreateRequest)));
    }

}
