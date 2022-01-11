package com.example.diploma.persistence.enums;

import java.util.HashMap;
import java.util.Map;

public enum  PaymentType {

    BY_BANK_CART("Онлайн на сайте"),
    BY_DELIVERY("При получении");

    private static final Map<String, PaymentType> map;
    static {
        map = new HashMap<>();
        for (PaymentType v : PaymentType.values()) {
            map.put(v.description, v);
        }
    }

    public static PaymentType findByDescription(String description) {
        return map.get(description);
    }

    PaymentType(String description) {
        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }
}
