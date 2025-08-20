package gps.tracker.backend.repositories.impl;

import gps.tracker.backend.configurations.DynamoDBTables;
import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.models.Car;
import gps.tracker.backend.models.User;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.repositories.ICarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository implements ICarRepository {

    private DynamoDBTables tables;

    @Override
    public Page<Car> findAll(CarQuery carQuery) {
//        DynamoDBQueryExpression<Car> query = carQuery.getUserId() == null ?
//                findAllCars() : findAllCarsOfUserQuery(carQuery.getUserId());
//
//        return dynamoDBMapper.query(Car.class, query);

        return carQuery.getUserId() == null ? findAllCars() : findAllCarsOfUserQuery(carQuery.getUserId());
    }

    @Override
    public Optional<Car> load(String id) {
        String validId = Car.pkPrefix + id;

        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(validId).sortValue(User.pkPrefix).build());
        return tables.getTable(Car.class).index(Index.SK_INDEX).query(request -> request.queryConditional(queryConditional))
                .stream().flatMap(page -> page.items().stream()).findFirst();
//        DynamoDBQueryExpression<Car> query = new DynamoDBQueryExpression<Car>()
//                .withIndexName(Index.SK_INDEX)
//                .withKeyConditionExpression("pk = :pkVal and begins_with(sk, :skPrefix)")
//                .withExpressionAttributeValues(Map.of(
//                        ":pkVal", new AttributeValue(validId),
//                        ":skPrefix", new AttributeValue(User.skPrefix)
//                ));
//
//        return Optional.ofNullable(dynamoDBMapper.load(Car.class, query));
    }

    @Override
    public Car save(Car car) {
        tables.getTable(Car.class).putItem(car);
//        dynamoDBMapper.save(car);
        return car;
    }

    private Page<Car> findAllCars() {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(EntityType.CAR.toString()).build());

        return tables.getTable(Car.class).index(Index.ENTITY_INDEX)
                .query(request -> request.queryConditional(queryConditional))
                .iterator().next();
//        return new DynamoDBQueryExpression<Car>()
//                .withIndexName(Index.ENTITY_INDEX)
//                .withKeyConditionExpression("entityType = :entityType")
//                .withExpressionAttributeValues(Map.of(":entityType", new AttributeValue().withS(EntityType.CAR.toString())))
//                .withConsistentRead(false);
    }

    private Page<Car> findAllCarsOfUserQuery(String userId) {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).sortValue(Car.skPrefix).build());
        return tables.getTable(Car.class)
                .query(request -> request.queryConditional(queryConditional))
                .iterator().next();
//        return new DynamoDBQueryExpression<Car>()
//                .withKeyConditionExpression("pk = :pkVal and begins_with(sk, :skPrefix)")
//                .withExpressionAttributeValues(Map.of(
//                        ":pkVal", new AttributeValue(userId),
//                        ":skPrefix", new AttributeValue(Car.skPrefix)
//                ));
    }
}
