package com.bank_example.user_service.domain.models.value_objects;

import lombok.Getter;

@Getter
public enum IdentifierType {

    DNI("DNI"),
    PASSPORT("PASSPORT");

    private final String value;

    IdentifierType(String value) {
        this.value = value;
    }

    public static IdentifierType fromValue(String value) {
        for (IdentifierType type : IdentifierType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown identifier type: " + value);
    }
}
