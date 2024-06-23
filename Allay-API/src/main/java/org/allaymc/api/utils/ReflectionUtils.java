package org.allaymc.api.utils;

import com.google.common.reflect.ClassPath;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ReflectionUtils {
    public static List<String> getAllClasses(String packageName) {
        try {
            var classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
            return classPath.getTopLevelClassesRecursive(packageName).stream()
                    .map(ClassPath.ClassInfo::getName)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();

        var current = clazz;
        while (current != null) {
            Collections.addAll(fields, current.getDeclaredFields());
            current = current.getSuperclass();
        }

        return fields;
    }

    public static List<Method> getAllMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap<>();

        var current = clazz;
        while (current != null) {
            for (var method : current.getDeclaredMethods()) {
                var signature = buildMethodSignature(method);
                methods.putIfAbsent(signature, method);
            }

            current = current.getSuperclass();
        }

        return new ArrayList<>(methods.values());
    }

    public static String buildMethodSignature(Method method) {
        return method.getName() + Arrays.toString(method.getParameterTypes());
    }

    public static List<Method> getAllStaticVoidParameterlessMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).filter(method -> {
            // return if not a static method
            if (!Modifier.isStatic(method.getModifiers())) return false;
            // return if not a void method
            if (method.getReturnType() != void.class) return false;
            return method.getParameterCount() == 0;
        }).toList();
    }
}

