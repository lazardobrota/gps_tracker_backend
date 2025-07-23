package gps.tracker.backend.repositories.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import gps.tracker.backend.models.User;
import gps.tracker.backend.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<User> getAll() {

        return new ArrayList<>();
//        Map<String>
//
//        return dynamoDBMapper.scan(User.class, new DynamoDBScanExpression().withFilterExpression())
    }

    @Override
    public User create(User user) {
        dynamoDBMapper.save(user);
        return user;
    }
}
