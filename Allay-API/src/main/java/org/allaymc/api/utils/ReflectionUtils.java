package org.allaymc.api.utils;

import com.google.common.reflect.ClassPath;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ReflectionUtils {
    public List<String> getAllClasses(String packageName) {
        try {
            ClassPath classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
            return new ArrayList<>(classPath.getTopLevelClassesRecursive(packageName).stream().map(ClassPath.ClassInfo::getName).toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public List<Method> getAllStaticVoidParameterlessMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).filter(method -> {
            // return if not a static method
            if (!Modifier.isStatic(method.getModifiers())) return false;
            // return if not a void method
            if (method.getReturnType() != void.class) return false;
            return method.getParameterCount() == 0;
        }).collect(Collectors.toList());
    }
}

