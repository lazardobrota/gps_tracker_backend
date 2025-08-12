package gps.tracker.backend.repositories.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import gps.tracker.backend.models.User;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<User> findAll() {
        DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>()
                .withIndexName(Index.ENTITY_INDEX.getName())
                .withKeyConditionExpression("entityType = :entityType")
                .withExpressionAttributeValues(Map.of(":entityType", new AttributeValue().withS(EntityType.USER.toString())))
                .withConsistentRead(false);

        return dynamoDBMapper.query(User.class, query);
    }

    @Override
    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    @Override
    public Optional<User> load(String id) {
        String validId = User.pkPrefix + id;
        return Optional.ofNullable(dynamoDBMapper.load(User.class, validId, validId));
    }
}
