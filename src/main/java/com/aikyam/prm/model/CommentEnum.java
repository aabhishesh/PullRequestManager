package com.aikyam.prm.model;

public enum CommentEnum {

    TEST,
    TEST_AND_MERGE;

    public static boolean contains(String value) {
        for (CommentEnum status : CommentEnum.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
