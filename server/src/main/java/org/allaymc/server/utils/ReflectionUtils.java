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
 * {@code ReflectionUtils} discovers runtime fields, methods, classes, and named constant
 * correspondences for server initialization and component inspection. Field discovery walks
 * class inheritance, while method discovery traverses declared methods, interfaces, and
 * superclasses using a signature-keyed map.
 *
 * <p>
 * Method collection visits the requested type before its ancestors and retains the first
 * method for a signature. Private interface methods include their declaring interface in the
 * key because unrelated interfaces can declare same-named private helpers without sharing an
 * override. Other keys use only names and parameter types; return types, synthetic status,
 * and visibility do not independently distinguish entries, and the returned method list has
 * no stable order. Static-field mapping instead matches public field names and exposes
 * duplicate-value constraints through a bidirectional map.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ReflectionUtils {
    /**
     * Returns names of top-level classes in the package and its subpackages through the system
     * class loader. The result is unmodifiable, and classpath scanning failures propagate rather
     * than becoming an empty class list.
     */
    @SneakyThrows
    public static List<String> getAllClasses(String packageName) {
        var classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
        return classPath.getTopLevelClassesRecursive(packageName).stream()
                .map(ClassPath.ClassInfo::getName)
                .toList();
    }

    /**
     * Returns declared fields from the class through its superclass chain, with subclasses first.
     * Private and hidden fields are retained independently, access permissions are not changed,
     * and {@code null} produces an empty mutable list.
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();

        var current = clazz;
        while (current != null) {
            Collections.addAll(fields, current.getDeclaredFields());
            current = current.getSuperclass();
        }

        return fields;
    }

    /**
     * Returns a mutable list of methods retained by signature across the class and interface
     * hierarchy. The first discovered declaration wins, the result order is unspecified, and
     * {@code null} produces an empty list.
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap<>();
        collectAllMethods(clazz, methods, new HashSet<>());

        return new ArrayList<>(methods.values());
    }

    /**
     * Collects declared methods before visiting interfaces and then the superclass. The visited
     * set prevents repeated interface traversal, while {@code putIfAbsent} keeps the declaration
     * first encountered for each constructed signature.
     *
     * <p>
     * Requested-type declarations therefore precede inherited candidates, and one interface's
     * private helper cannot erase another interface's same-signature helper because their keys
     * include the declaring type. Other collisions intentionally retain only one declaration;
     * this traversal is a keyed selection and does not claim to model every Java override rule.
     */
    private static void collectAllMethods(Class<?> type, Map<String, Method> methods, Set<Class<?>> visited) {
        if (type == null || !visited.add(type)) {
            return;
        }

        // Visit local declarations before ancestors so first-insertion retention preserves
        // the requested type's method when an inherited declaration has the same lookup key.
        for (var method : type.getDeclaredMethods()) {
            var signature = buildMethodSignature(method);
            methods.putIfAbsent(signature, method);
        }

        for (var iface : type.getInterfaces()) {
            collectAllMethods(iface, methods, visited);
        }

        collectAllMethods(type.getSuperclass(), methods, visited);
    }

    /**
     * Builds a name-and-parameter key for method collection. Return types are omitted, so
     * covariant or bridge variants can share a key and the collector retains whichever declaration
     * its traversal encounters first.
     *
     * <p>
     * Private interface methods are independent helpers rather than inherited overrides. Their
     * declaring interface name prefixes the key to prevent unrelated interfaces' same-named
     * private methods from collapsing into one entry. Class-private methods do not receive this
     * extra qualification, so this key is specific to the collector's current selection rules.
     */
    public static String buildMethodSignature(Method method) {
        var signature = method.getName() + Arrays.toString(method.getParameterTypes());

        // Unrelated interfaces can own identical private helper signatures. Keep the
        // declaring interface in their keys so collection does not mistake them for one method.
        if (method.getDeclaringClass().isInterface() && Modifier.isPrivate(method.getModifiers())) {
            return method.getDeclaringClass().getName() + "#" + signature;
        }

        return signature;
    }

    /**
     * Returns an unmodifiable list of public static parameterless {@code void} methods exposed by
     * {@code clazz.getMethods()}. Inherited public methods can appear, and reflection does not
     * provide a stable ordering for their invocation.
     */
    public static List<Method> getAllStaticVoidParameterlessMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).filter(method -> {
            if (!Modifier.isStatic(method.getModifiers())) return false;
            if (method.getReturnType() != void.class) return false;
            return method.getParameterCount() == 0;
        }).toList();
    }

    /**
     * Matches public static fields declared directly by two classes using their field names.
     * Only non-{@code null} values from both classes enter the returned mutable bidirectional map;
     * unreadable fields are skipped, but access-enablement failures still propagate.
     *
     * <p>
     * Names identify correspondence while field values become map keys and values. Multiple
     * source names with equal values can replace an earlier association, and repeated target
     * values can violate the bidirectional map's uniqueness constraint. Callers therefore need
     * compatible constant sets rather than assuming every public static field creates a distinct
     * pair.
     */
    public static <T, U> BiMap<T, U> mapStaticFields(Class<?> classA, Class<?> classB) {
        Map<String, T> staticInstancesA = new java.util.HashMap<>();

        for (var field : classA.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) &&
                Modifier.isPublic(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    @SuppressWarnings("unchecked")
                    T instance = (T) field.get(null);
                    if (instance != null) {
                        staticInstancesA.put(field.getName(), instance);
                    }
                } catch (IllegalAccessException ignored) {
                    // This field cannot contribute a value to the correspondence map. Leave
                    // it unmatched while still collecting accessible constants from the same class.
                }
            }
        }

        BiMap<T, U> result = HashBiMap.create();

        for (var field : classB.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) &&
                Modifier.isPublic(field.getModifiers())) {
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
                    // This field cannot contribute a value to the correspondence map. Leave
                    // it unmatched while still collecting accessible constants from the same class.
                }
            }
        }

        return result;
    }
}
