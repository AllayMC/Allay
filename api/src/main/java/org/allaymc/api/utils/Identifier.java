package org.allaymc.api.utils;

import org.allaymc.api.plugin.Plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;
import static org.allaymc.api.utils.IdentifierUtils.isNamespaceValid;
import static org.allaymc.api.utils.IdentifierUtils.isPathValid;

/**
 * Represents an identifier consisting of a namespace and a path.
 * <p>
 * This class is used to uniquely identify resources or components within a specific namespace.
 * It ensures that both the namespace and path conform to specific validation rules.
 * </p>
 * <p>
 * The identifier can be constructed using various constructors, including:
 * <ul>
 *     <li>A namespace and path directly.</li>
 *     <li>A plugin instance and a path, where the namespace is derived from the plugin.</li>
 *     <li>A single string in the format "namespace:path".</li>
 * </ul>
 * </p>
 * <p>
 * Validation rules:
 * <ul>
 *     <li>The namespace must only contain characters matching [A-Za-z0-9_.-].</li>
 *     <li>The path must only contain characters matching [A-Za-z0-9/._-].</li>
 * </ul>
 * </p>
 * <p>
 * This class is immutable and provides methods for equality checks, cloning, and string representation.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 *     Identifier id = new Identifier("minecraft", "cake");
 *     System.out.println(id); // Outputs: minecraft:cake
 * </pre>
 * </p>
 *
 * @author daoge_cmd
 */
public record Identifier(String namespace, String path) implements Cloneable {

    /**
     * The spectator used between namespace and path.
     */
    public static final String NAMESPACE_SEPARATOR = ":";
    /**
     * The default namespace.
     */
    public static final String DEFAULT_NAMESPACE = "minecraft";

    /**
     * Constructs an Identifier using a plugin instance and a path.
     * The namespace is derived from the plugin's name.
     *
     * @param plugin the plugin instance
     * @param path   the path of the identifier
     */
    public Identifier(Plugin plugin, String path) {
        this(plugin.getPluginContainer().descriptor().getName(), path);
    }

    /**
     * Constructs an Identifier using an array of strings.
     * The first element is the namespace, and the second is the path.
     *
     * @param id an array containing the namespace and path
     */
    public Identifier(String[] id) {
        this(id[0], id[1]);
    }

    /**
     * Constructs an Identifier from a single string in the format "namespace:path".
     * If the namespace is missing, the default namespace is used.
     *
     * @param id the identifier string
     */
    public Identifier(String id) {
        this(fastTwoPartSplit(id, NAMESPACE_SEPARATOR, DEFAULT_NAMESPACE));
    }

    /**
     * Constructs an Identifier using a namespace and a path.
     * Validates the namespace and path against specific rules.
     *
     * @param namespace the namespace of the identifier
     * @param path      the path of the identifier
     *
     * @throws InvalidIdentifierException if the namespace or path is invalid
     */
    public Identifier(String namespace, String path) {
        this.namespace = namespace.isEmpty() ? DEFAULT_NAMESPACE : namespace;
        this.path = path;
        if (!isNamespaceValid(this.namespace)) {
            throw new InvalidIdentifierException("Non [A-Za-z0-9_.-] character in namespace of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
        }
        if (!isPathValid(this.path)) {
            throw new InvalidIdentifierException("Non [A-Za-z0-9/._-] character in path of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
        }
    }

    /**
     * Returns the string representation of the identifier in the format "namespace:path".
     *
     * @return the string representation of the identifier
     */
    @Override
    public String toString() {
        return this.namespace + NAMESPACE_SEPARATOR + this.path;
    }

    /**
     * Checks if this identifier is equal to another object.
     * Two identifiers are equal if their namespace and path are equal.
     *
     * @param o the object to compare with
     *
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Identifier(String namespace1, String path1))) {
            return false;
        }

        return Objects.equals(path, path1) && Objects.equals(namespace, namespace1);
    }

    /**
     * Computes the hash code for this identifier based on its namespace and path.
     *
     * @return the hash code of the identifier
     */
    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    /**
     * Creates and returns a copy of this identifier.
     *
     * @return a clone of this identifier
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Identifier clone() {
        return new Identifier(namespace, path);
    }

    /**
     * Annotation used to mark the identifier of a component in the component system
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Component {
    }
}
