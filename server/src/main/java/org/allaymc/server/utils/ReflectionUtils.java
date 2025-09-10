package org.allaymc.server.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class ReflectionUtils {
    @SneakyThrows
    public static List<String> getAllClasses(String packageName) {
        var classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
        return classPath.getTopLevelClassesRecursive(packageName).stream()
                .map(ClassPath.ClassInfo::getName)
                .toList();
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

    public static <T, U> BiMap<T, U> mapStaticFields(Class<?> classA, Class<?> classB) {
        Map<String, T> staticInstancesA = new java.util.HashMap<>();

        for (var field : classA.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) &&
                Modifier.isPublic(field.getModifiers()) &&
                classA.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    @SuppressWarnings("unchecked")
                    T instance = (T) field.get(null);
                    if (instance != null) {
                        staticInstancesA.put(field.getName(), instance);
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }

        BiMap<T, U> result = HashBiMap.create();

        for (var field : classB.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) &&
                Modifier.isPublic(field.getModifiers()) &&
                classB.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    @SuppressWarnings("unchecked")
                    U instance = (U) field.get(null);
                    if (instance != null) {
                        T match = staticInstancesA.get(field.getName());
                        if (match != null) {
                            result.put(match, instance);
                        }
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }

        return result;
    }
}

