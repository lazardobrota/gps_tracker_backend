package gps.tracker.backend.repositories.impl;

import gps.tracker.backend.configurations.DynamoDBTables;
import gps.tracker.backend.models.User;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private final DynamoDBTables tables;

    @Override
    public Page<User> findAll() {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(EntityType.USER.toString()).build()
        );

        return tables.getTable(User.class).index(Index.ENTITY_INDEX)
                .query(request -> request
                .queryConditional(queryConditional)
                .exclusiveStartKey(null) //TODO add startKey
                .limit(20) //TODO add limit
        ).iterator().next();

        //        DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>()
//                .withIndexName(Index.ENTITY_INDEX)
//                .withKeyConditionExpression("entityType = :entityType")
//                .withExpressionAttributeValues(Map.of(":entityType", new AttributeValue().withS(EntityType.USER.toString())))
//                .withConsistentRead(false);
//
//        return dynamoDBMapper.query(User.class, query);
    }

    @Override
    public User save(User user) {
        tables.getTable(User.class).putItem(user);
        return user;
    }

    @Override
    public Optional<User> load(String id) {
        String validId = User.pkPrefix + id;

        Key key = Key.builder().partitionValue(validId).sortValue(validId).build();
        return Optional.ofNullable(tables.getTable(User.class).getItem(key));
    }
}
