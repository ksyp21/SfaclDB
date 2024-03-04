package com.sfacl.magnus.entity;

public enum ProblemStatus {
    SOLVED(0),
    UNSOLVED(1);

    private final int value;

    ProblemStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}