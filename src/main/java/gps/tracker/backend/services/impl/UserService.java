package gps.tracker.backend.services.impl;

import gps.tracker.backend.mappers.UserMapper;
import gps.tracker.backend.models.User;
import gps.tracker.backend.repositories.IUserRepository;
import gps.tracker.backend.requests.UserCreateRequest;
import gps.tracker.backend.responses.UserResponse;
import gps.tracker.backend.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        return userMapper.toUserResponse(userRepository.create(userMapper.toUser(userCreateRequest)));
    }
}
