package com.example.demo.dto;

public enum FieldType {
    STRING("string"),
    NUMBER("number");

    private final String name;

    FieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
