package cn.allay.api.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ReflectionUtils {
    public List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null) {
            Collections.addAll(fields, current.getDeclaredFields());
            current = current.getSuperclass();
        }

        return fields;
    }

    public List<Method> getAllMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap<>();
        Class<?> current = clazz;

        while (current != null) {
            for (Method method : current.getDeclaredMethods()) {
                String signature = buildMethodSignature(method);
                methods.putIfAbsent(signature, method);
            }
            current = current.getSuperclass();
        }

        return new ArrayList<>(methods.values());
    }

    public String buildMethodSignature(Method method) {
        return method.getName() + Arrays.toString(method.getParameterTypes());
    }
}

