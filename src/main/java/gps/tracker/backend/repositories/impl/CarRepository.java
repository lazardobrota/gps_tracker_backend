package gps.tracker.backend.repositories.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import gps.tracker.backend.dto.query.CarQuery;
import gps.tracker.backend.models.Car;
import gps.tracker.backend.models.User;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.repositories.ICarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository implements ICarRepository {

    private DynamoDBMapper dynamoDBMapper;

    //TODO how to fix this
    @Override
    public List<Car> findAll(CarQuery carQuery) {
        DynamoDBQueryExpression<Car> query = carQuery.getUserId() == null ?
                new DynamoDBQueryExpression<Car>()
                .withIndexName(Index.ENTITY_INDEX.getName())
                .withKeyConditionExpression("entityType = :entityType")
                .withExpressionAttributeValues(Map.of(":entityType", new AttributeValue().withS(EntityType.CAR.toString())))
                .withConsistentRead(false) :

                new DynamoDBQueryExpression<Car>()
                        .withKeyConditionExpression("pk = :pkVal and begins_with(sk, :skPrefix)")
                        .withExpressionAttributeValues(Map.of(
                                ":pkVal", new AttributeValue(carQuery.getUserId()),
                                ":skPrefix", new AttributeValue(Car.skPrefix)
                        ));;

        return dynamoDBMapper.query(Car.class, query);
    }

    @Override
    public Optional<Car> load(String id) {
        return Optional.empty(); //TODO
    }

    @Override
    public Car save(Car car) {
        dynamoDBMapper.save(car);
        return car;
    }
}
