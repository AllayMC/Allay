package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

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

    public static <T> void mirror(CompletableFuture<T> source, CompletableFuture<T> target) {
        source.whenComplete((result, ex) -> {
            if (ex == null) {
                target.complete(result);
            } else {
                target.completeExceptionally(ex);
            }
        });
    }

    public static boolean isDoneNormally(CompletableFuture<?> future) {
        return future.isDone() && !future.isCompletedExceptionally() && !future.isCancelled();
    }
}
