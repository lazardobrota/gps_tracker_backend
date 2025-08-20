package gps.tracker.backend.repositories;

import gps.tracker.backend.models.User;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.Optional;

public interface IUserRepository {

    Page<User> findAll();

    Optional<User> load(String id);

    User save(User user);

}
