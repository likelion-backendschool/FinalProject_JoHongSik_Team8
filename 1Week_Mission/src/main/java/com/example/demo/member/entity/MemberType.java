package com.example.demo.member.entity;

public enum MemberType {
    GENERAL("일반"), AUTHOR("작가");

    private final String description;

    MemberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
