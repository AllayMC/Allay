package org.allaymc.api.utils.identifier;

import org.allaymc.api.plugin.Plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;
import static org.allaymc.api.utils.identifier.IdentifierUtils.isNamespaceValid;
import static org.allaymc.api.utils.identifier.IdentifierUtils.isPathValid;

/**
 * {@code Identifier} represents a case-sensitive namespace and path used to identify runtime
 * resources and components. Constructors replace an empty namespace with {@code "minecraft"}
 * and validate both components against the current letter, digit, underscore, hyphen, and
 * period rules; empty paths are accepted and slash characters are rejected.
 *
 * @param namespace validated namespace or the default namespace when empty
 * @param path validated resource path
 *
 * @author daoge_cmd
 */
public record Identifier(String namespace, String path) implements Cloneable {

    /**
     * {@code NAMESPACE_SEPARATOR} separates namespace from path in the string representation.
     * Single-string construction splits at its first occurrence before validating the two
     * components.
     */
    public static final String NAMESPACE_SEPARATOR = ":";
    /**
     * {@code DEFAULT_NAMESPACE} supplies the namespace for unqualified identifiers and empty
     * namespace components. Constructors apply it before namespace validation and string
     * representation.
     */
    public static final String DEFAULT_NAMESPACE = "minecraft";

    /**
     * Creates an identifier using the plugin descriptor's name as its namespace. The plugin and
     * its descriptor must be available, and the canonical constructor applies namespace defaults
     * and component validation without changing case.
     */
    public Identifier(Plugin plugin, String path) {
        this(plugin.getPluginContainer().descriptor().getName(), path);
    }

    /**
     * Creates an identifier from the first two array elements as namespace and path. The array
     * must contain at least two elements; additional elements are ignored and canonical validation
     * still applies to the selected components.
     */
    public Identifier(String[] id) {
        this(id[0], id[1]);
    }

    /**
     * Creates an identifier by splitting at the first colon. A missing or leading colon selects
     * {@code DEFAULT_NAMESPACE}; further colons remain in the path and fail canonical component
     * validation rather than creating additional components.
     */
    public Identifier(String id) {
        this(fastTwoPartSplit(id, NAMESPACE_SEPARATOR, DEFAULT_NAMESPACE));
    }

    /**
     * Creates an identifier with a defaulted namespace and validated components. Empty namespaces
     * select {@code DEFAULT_NAMESPACE}, empty paths remain valid, uppercase ASCII letters are
     * accepted, and {@code null} components fail before a valid identifier can be constructed.
     *
     * @throws InvalidIdentifierException if either component contains a character rejected by its validator
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
     * Returns the namespace and path joined by the namespace separator.
     */
    @Override
    public String toString() {
        return this.namespace + NAMESPACE_SEPARATOR + this.path;
    }

    /**
     * Returns whether another identifier has equal case-sensitive namespace and path components.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Identifier(String namespace1, String path1))) {
            return false;
        }

        return Objects.equals(path, path1) && Objects.equals(namespace, namespace1);
    }

    /**
     * Returns the combined hash of this identifier's namespace and path.
     */
    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    /**
     * Creates an equal identifier by revalidating the same immutable string components.
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Identifier clone() {
        return new Identifier(namespace, path);
    }

    /**
     * {@code Component} marks the runtime-visible identifier field used to identify a component
     * implementation during component assembly.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Component {
    }
}
