package gps.tracker.backend.configurations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoDBConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials credentials, @Value("${aws.dynamodb.endpoint}") String dynamoDBURL, @Value("${aws.dynamodb.region}") String region) {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBURL, region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials));

        return builder.build();
    }

    @Bean
    public AWSCredentials awsCredentials(@Value("${aws.credentials.access-key}") String accessKey,
                                         @Value("${aws.credentials.secret-key}") String secretKey) {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
