package gps.tracker.backend.controller;

import gps.tracker.backend.endpoints.Endpoints;
import gps.tracker.backend.dto.requests.user.UserCreateRequest;
import gps.tracker.backend.dto.requests.user.UserPasswordResetRequest;
import gps.tracker.backend.dto.requests.user.UserUpdateRequest;
import gps.tracker.backend.dto.responses.UserResponse;
import gps.tracker.backend.services.IUserService;
import gps.tracker.backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {

    private final IUserService userService;

    //TODO Pagination
    @GetMapping(Endpoints.User.getAll)
    public ResponseEntity<List<UserResponse>> findAll() {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.findAll()));
    }

    //TODO Make it inside jwt token
    @GetMapping(Endpoints.User.getOne)
    public ResponseEntity<UserResponse> findOne(@PathVariable("id") String id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.find(id)));
    }

    @PostMapping(Endpoints.User.create)
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.create(userCreateRequest)));
    }

    @PutMapping(Endpoints.User.update)
    public ResponseEntity<UserResponse> update(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.update(id, userUpdateRequest)));
    }

    @PutMapping(Endpoints.User.passwordReset)
    public ResponseEntity<UserResponse> passwordReset(@PathVariable("id") String id, @RequestBody @Valid UserPasswordResetRequest userPasswordResetRequest) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.passwordReset(id, userPasswordResetRequest)));
    }

}
