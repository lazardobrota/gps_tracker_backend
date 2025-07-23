package gps.tracker.backend.mappers;

import gps.tracker.backend.models.User;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.responses.UserResponse;
import gps.tracker.backend.utils.PasswordUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
//@Extension
public class UserMapper {
    public User toUser(UserCreateRequest userCreateRequest) {
        String uuid = UUID.randomUUID().toString();
        return new User(
                uuid,
                uuid,
                userCreateRequest.getFirstName(),
                userCreateRequest.getLastName(),
                userCreateRequest.getEmail(),
                PasswordUtils.hashPass(userCreateRequest.getPassword()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getPk(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
