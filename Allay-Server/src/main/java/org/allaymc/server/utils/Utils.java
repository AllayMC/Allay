package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    public static void callInitializer(Method method) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
