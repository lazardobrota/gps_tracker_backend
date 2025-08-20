package gps.tracker.backend.models;

import gps.tracker.backend.converters.LocalDateTimeConverter;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.models.enums.Prefix;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@DynamoDbBean
public class Car {

    public static final String pkPrefix = Prefix.U.toLowerStringWithHash();
    public static final String skPrefix = Prefix.C.toLowerStringWithHash();

    private String pk;
    private String sk;
    private String entityType;
    private String licensePlate;
    private String model;
    private LocalDateTime yearOfProduction;
    private LocalDateTime yearOfPurchase;
    private LocalDateTime createdAt;
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

    @DynamoDbPartitionKey
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    @DynamoDbSecondaryPartitionKey(indexNames = Index.SK_INDEX)
    public String getSk() {
        return sk;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = Index.ENTITY_INDEX)
    public String getEntityType() {
        return entityType;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getYearOfProduction() {
        return yearOfProduction;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getYearOfPurchase() {
        return yearOfPurchase;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
