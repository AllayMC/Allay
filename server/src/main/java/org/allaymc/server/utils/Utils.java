package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    public static void callInitializer(Method method) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int assertPositiveOrZero(int n, String name) {
        if (n < 0) {
            throw new IllegalArgumentException(name + ": " + n + " (expected: >= 0)");
        }
        return n;
    }
}
