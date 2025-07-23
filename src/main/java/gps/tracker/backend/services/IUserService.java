package gps.tracker.backend.services;

import gps.tracker.backend.models.User;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.responses.UserResponse;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    UserResponse create(UserCreateRequest userCreateRequest);
}

