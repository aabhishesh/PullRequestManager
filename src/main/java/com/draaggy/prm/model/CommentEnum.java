package com.draaggy.prm.model;

public enum CommentEnum {

    TEST,
    TEST_AND_MERGE,
    STATUS;

    public static boolean contains(String value) {
        for (CommentEnum status : CommentEnum.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static CommentEnum getEnum(String value) {
        for (CommentEnum status : CommentEnum.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
