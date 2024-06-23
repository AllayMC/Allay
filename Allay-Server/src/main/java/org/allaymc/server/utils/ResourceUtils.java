package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;
import me.tongfei.progressbar.ProgressBar;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
@UtilityClass
public final class ResourceUtils {
    public static InputStream getResource(String resourceName) {
        return Objects.requireNonNull(ResourceUtils.class.getClassLoader().getResourceAsStream(resourceName));
    }

    public static void callInitializer(Method method, ProgressBar progressBar) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            if (progressBar != null) progressBar.step();
        }
    }
}
