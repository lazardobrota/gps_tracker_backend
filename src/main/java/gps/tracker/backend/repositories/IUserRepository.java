package gps.tracker.backend.repositories;

import gps.tracker.backend.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> findAll();

    Optional<User> load(String id);

    User save(User user);

}
