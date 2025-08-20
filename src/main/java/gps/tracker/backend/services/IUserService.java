package gps.tracker.backend.services;

import gps.tracker.backend.dto.PageResult;
import gps.tracker.backend.dto.requests.user.UserCreateRequest;
import gps.tracker.backend.dto.requests.user.UserPasswordResetRequest;
import gps.tracker.backend.dto.requests.user.UserUpdateRequest;
import gps.tracker.backend.dto.responses.UserResponse;

public interface IUserService {
    PageResult<UserResponse> findAll();

    UserResponse find(String id);

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    UserResponse passwordReset(String id, UserPasswordResetRequest userPasswordResetRequest);
}

