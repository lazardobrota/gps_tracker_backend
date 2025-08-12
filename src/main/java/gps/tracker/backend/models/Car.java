package gps.tracker.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import gps.tracker.backend.converters.LocalDateTimeConverter;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Prefix;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@DynamoDBTable(tableName = "GPSTracker")
@Data
@NoArgsConstructor
public class Car {

    public static final String pkPrefix = Prefix.U.toLowerStringWithHash();
    public static final String skPrefix = Prefix.C.toLowerStringWithHash();

    @DynamoDBHashKey(attributeName = "pk")
    private String pk;

    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;

    @DynamoDBAttribute
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "EntityIndex")
    private String entityType;

    @DynamoDBAttribute
    private String licensePlate;

    @DynamoDBAttribute
    private String model;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime yearOfProduction;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime yearOfPurchase;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedAt;

    public Car(String pk, String sk, String licensePlate, String model, LocalDateTime yearOfProduction, LocalDateTime yearOfPurchase, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pk = pk;
        this.sk = sk;
        this.entityType = EntityType.CAR.toString();
        this.licensePlate = licensePlate;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.yearOfPurchase = yearOfPurchase;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
