package gps.tracker.backend.models.enums;

import lombok.Getter;

@Getter
public enum Index {
    ENTITY_INDEX("EntityIndex");

    private final String name;

    Index(String name) {
        this.name = name;
    }
}
