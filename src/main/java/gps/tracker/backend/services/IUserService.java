package gps.tracker.backend.services;

import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.requests.UserPasswordResetRequest;
import gps.tracker.backend.requests.UserUpdateRequest;
import gps.tracker.backend.responses.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll();

    UserResponse find(String id);

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    UserResponse passwordReset(String id, UserPasswordResetRequest userPasswordResetRequest);
}

