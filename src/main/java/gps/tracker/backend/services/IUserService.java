package gps.tracker.backend.services;

import gps.tracker.backend.dto.requests.user.UserCreateRequest;
import gps.tracker.backend.dto.requests.user.UserPasswordResetRequest;
import gps.tracker.backend.dto.requests.user.UserUpdateRequest;
import gps.tracker.backend.dto.responses.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll();

    UserResponse find(String id);

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    UserResponse passwordReset(String id, UserPasswordResetRequest userPasswordResetRequest);
}

