package gps.tracker.backend.repositories;

import gps.tracker.backend.models.User;

import java.util.List;

public interface IUserRepository {

    List<User> getAll();
    User create(User user);
}
