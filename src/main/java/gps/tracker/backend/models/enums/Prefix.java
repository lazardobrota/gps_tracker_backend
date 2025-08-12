package gps.tracker.backend.models.enums;

import lombok.Getter;

@Getter
public enum Prefix {
    U,
    C,
    F;

    public String toLowerStringWithHash() {
        return this.toString().toLowerCase() + "#";
    }

    public String toLowerString() {
        return this.toString().toLowerCase();
    }
}
