package gps.tracker.backend.services.impl;

import gps.tracker.backend.exceptions.HttpException;
import gps.tracker.backend.mappers.UserMapper;
import gps.tracker.backend.models.User;
import gps.tracker.backend.repositories.IUserRepository;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.requests.UserPasswordResetRequest;
import gps.tracker.backend.requests.UserUpdateRequest;
import gps.tracker.backend.responses.UserResponse;
import gps.tracker.backend.services.IUserService;
import gps.tracker.backend.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse find(String id) {
        return userMapper.toUserResponse(userRepository.load(id).orElseThrow(() -> new HttpException("No user with id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        return userMapper.toUserResponse(userRepository.save(userMapper.toUser(userCreateRequest)));
    }

    @Override
    public UserResponse update(String id, UserUpdateRequest userUpdateRequest) {
        User oldUser = userRepository.load(id).orElseThrow(() -> new HttpException("No user with id: " + id, HttpStatus.BAD_REQUEST));
        User newUser = userMapper.updateUser(oldUser, userUpdateRequest);

        return userMapper.toUserResponse(userRepository.save(newUser));
    }

    @Override
    public UserResponse passwordReset(String id, UserPasswordResetRequest userPasswordResetRequest) {
        User oldUser = userRepository.load(id).orElseThrow(() -> new HttpException("No user with id: " + id, HttpStatus.BAD_REQUEST));
        if (!oldUser.getPassword().equals(PasswordUtils.hashPass(userPasswordResetRequest.getOldPassword(), oldUser.getCreatedAt().toString())))
            throw new HttpException("Wrong password: " + userPasswordResetRequest.getOldPassword(), HttpStatus.BAD_REQUEST);

        User newUser = userMapper.updatePassword(oldUser, userPasswordResetRequest);

        return userMapper.toUserResponse(userRepository.save(newUser));
    }
}
