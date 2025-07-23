package gps.tracker.backend.test;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.net.URI;

public class CheckConnection {

    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder()
                .region(Region.EU_WEST_1) // Dummy region for local
                .endpointOverride(URI.create("http://localhost:8000"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("EMPTY", "EMPTY")))
                .build();

        ListTablesResponse response = client.listTables(ListTablesRequest.builder().build());
        System.out.println("Tables: " + response.tableNames());
    }
}
