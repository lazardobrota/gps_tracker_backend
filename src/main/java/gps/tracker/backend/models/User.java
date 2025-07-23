package gps.tracker.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import gps.tracker.backend.converters.LocalDateTimeConverter;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "GPSTracker")
public class User {

    private static final String pkPrefix = "";
    private static final String skPrefix = "user#";

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
    private String password;

//    @Getter(onMethod_ = @DynamoDBAttribute)
//    private String salt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedAt;

    public User(String pk, String sk, String firstName, String lastName, String email, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pk = pkPrefix + pk;
        this.sk = skPrefix + sk;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
