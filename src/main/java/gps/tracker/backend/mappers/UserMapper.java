package gps.tracker.backend.mappers;

import gps.tracker.backend.models.User;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.requests.UserPasswordResetRequest;
import gps.tracker.backend.requests.UserUpdateRequest;
import gps.tracker.backend.responses.UserResponse;
import gps.tracker.backend.utils.PasswordUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
//@Extension
public class UserMapper {
    public User toUser(UserCreateRequest userCreateRequest) {
        LocalDateTime currTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String uuid = UUID.randomUUID().toString();
        return new User(
                User.pkPrefix + uuid,
                User.skPrefix + uuid,
                userCreateRequest.getFirstName(),
                userCreateRequest.getLastName(),
                userCreateRequest.getEmail(),
                PasswordUtils.hashPass(userCreateRequest.getPassword(), currTime.toString()),
                currTime.toString(),
                currTime,
                currTime
        );
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getPk().replace(User.pkPrefix, ""),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    public User updateUser(User oldUser, UserUpdateRequest userUpdateRequest) {
        return new User(
                oldUser.getPk(),
                oldUser.getSk(),
                userUpdateRequest.getFirstName(),
                userUpdateRequest.getLastName(),
                userUpdateRequest.getEmail(),
                oldUser.getPassword(),
                oldUser.getSalt(),
                oldUser.getCreatedAt(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
    }

    public User updatePassword(User oldUser, UserPasswordResetRequest userPasswordResetRequest) {
        return new User(
                oldUser.getPk(),
                oldUser.getSk(),
                oldUser.getFirstName(),
                oldUser.getLastName(),
                oldUser.getEmail(),
                PasswordUtils.hashPass(userPasswordResetRequest.getNewPassword(), oldUser.getCreatedAt().toString()),
                oldUser.getSalt(),
                oldUser.getCreatedAt(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
    }
}
