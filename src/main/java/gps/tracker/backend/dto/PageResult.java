package gps.tracker.backend.dto;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

public record PageResult<T>(List<T> items, Map<String, AttributeValue> nextKey) {

}
