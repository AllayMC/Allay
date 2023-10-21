package cn.allay.api.utils;

import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
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

    @SneakyThrows
    @NotNull
    public static List<String> getParametersName(Method theMethod) {
        var declaringClass = theMethod.getDeclaringClass();
        var declaringClassLoader = declaringClass.getClassLoader();

        var declaringType = Type.getType(declaringClass);
        var constructorDescriptor = Type.getMethodDescriptor(theMethod);
        var url = declaringType.getInternalName() + ".class";

        var classFileInputStream = declaringClassLoader.getResourceAsStream(url);
        if (classFileInputStream == null) {
            throw new IllegalArgumentException("The constructor's class loader cannot find the bytecode that defined the constructor's class (URL: " + url + ")");
        }

        ClassNode classNode;
        try {
            classNode = new ClassNode();
            var classReader = new ClassReader(classFileInputStream);
            classReader.accept(classNode, 0);
        } finally {
            classFileInputStream.close();
        }

        var methods = classNode.methods;
        for (var method : methods) {
            if (method.name.equals(theMethod.getName()) && method.desc.equals(constructorDescriptor)) {
                var argumentTypes = Type.getArgumentTypes(method.desc);
                List<String> parameterNames = new ArrayList<>(argumentTypes.length);

                var localVariables = method.localVariables;
                for (int i = 1; i <= argumentTypes.length; i++) {
                    // The first local variable actually represents the "this"
                    // object if the method is not static!
                    parameterNames.add(localVariables.get(i).name);
                }

                return parameterNames.reversed();
            }
        }

        return Collections.emptyList();
    }
}

