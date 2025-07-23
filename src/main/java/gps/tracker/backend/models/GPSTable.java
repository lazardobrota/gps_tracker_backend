package gps.tracker.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;

@DynamoDBTable(tableName = "GPSTracker")
public class GPSTable {

    @Getter(onMethod_ = @DynamoDBHashKey(attributeName = "pk"))
    private String pk;

    @Getter(onMethod_ = @DynamoDBRangeKey(attributeName = "sk"))
    private String sk;
}
