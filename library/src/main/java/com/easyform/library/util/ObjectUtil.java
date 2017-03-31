package com.easyform.library.util;

public class ObjectUtil {

    private ObjectUtil() {
    }

    public static <T> String valueOf(T object) {
        return isNull(object) ? "" : String.valueOf(object);
    }

    public static <T> boolean isNull(T object) {
        return object == null;
    }

    public static <T> boolean isNotNull(T object) {
        return object != null;
    }

    public static <T> boolean isNotNullAndEmpty(T object) {
        return object != null && object.toString().trim().length() != 0;
    }

    @SafeVarargs
    public static <T> boolean isNotNull(T... objects) {
        for (T object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isEmpty(T object) {
        return object != null && object.toString().trim().length() == 0;
    }

    public static <T> boolean isNotEmpty(T object) {
        return object != null && object.toString().trim().length() != 0;
    }

    public static <T> boolean isEqual(T val1, T val2) {
        return val1 == null ? val2 == null : val1.equals(val2);
    }

    public static <T> boolean isNullOrEmpty(T object) {
        return object == null || object.toString().trim().length() == 0;
    }
}