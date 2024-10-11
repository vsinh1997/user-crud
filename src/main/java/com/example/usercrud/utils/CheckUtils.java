package com.example.usercrud.utils;

import java.util.Optional;

public abstract class CheckUtils {

    public static boolean isNotEmpty(Object object) {

        if (object instanceof String) {
            return !((String) object).isEmpty();
        }
        else if (object instanceof Integer) {
            return ((Integer) object) != 0;
        }
        else if (object instanceof Optional<?>) {
            return ((Optional<?>) object).isPresent();
        }
        else {
            return object != null;
        }

    }
}
