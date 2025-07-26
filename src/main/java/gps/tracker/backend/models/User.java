package gps.tracker.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import gps.tracker.backend.converters.LocalDateTimeConverter;
import gps.tracker.backend.models.enums.EntityType;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "GPSTracker")
public class User {

    public static final String pkPrefix = "u#";
    public static final String skPrefix = "u#";

    @DynamoDBHashKey(attributeName = "pk")
    private String pk;

    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    @Email
    private String email;

    @DynamoDBAttribute
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "UserIndex")
    private String entityType;

    @DynamoDBAttribute
    private String password;

    @DynamoDBAttribute
    private String salt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedAt;

    public User(String pk, String sk, String firstName, String lastName, String email, String password, String salt, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pk = pk;
        this.sk = sk;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.entityType = EntityType.USER.toString();
    }
}
