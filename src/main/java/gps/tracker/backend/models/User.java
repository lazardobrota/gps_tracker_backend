package gps.tracker.backend.models;

import gps.tracker.backend.converters.LocalDateTimeConverter;
import gps.tracker.backend.models.enums.EntityType;
import gps.tracker.backend.models.enums.Index;
import gps.tracker.backend.models.enums.Prefix;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@DynamoDbBean
public class User {

    public static final String pkPrefix = Prefix.U.toLowerStringWithHash();
    public static final String skPrefix = Prefix.U.toLowerStringWithHash();

    private String pk;

    private String sk;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String entityType;

    private String password;

    private String salt;

    private LocalDateTime createdAt;

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

    @DynamoDbPartitionKey
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    public String getSk() {
        return sk;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = Index.ENTITY_INDEX)
    public String getEntityType() {
        return entityType;
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
