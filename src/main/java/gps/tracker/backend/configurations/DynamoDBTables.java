package gps.tracker.backend.configurations;

import gps.tracker.backend.models.Car;
import gps.tracker.backend.models.User;
import gps.tracker.backend.models.enums.Table;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Configuration
public class DynamoDBTables {

    private final DynamoDbEnhancedClient enhancedClient;

    public DynamoDBTables(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    public <T> DynamoDbTable<T> getTable(Class<T> entityClass) {
        return enhancedClient.table(Table.GPS_TRACKER_V001, TableSchema.fromBean(entityClass));
    }

    @PostConstruct
    public void initTables() {
        getTable(User.class);
        getTable(Car.class);
    }
}
