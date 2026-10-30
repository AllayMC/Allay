package org.allaymc.dependence;

import static org.allaymc.dependence.IdentifierUtils.isNamespaceValid;
import static org.allaymc.dependence.IdentifierUtils.isPathValid;
import static org.allaymc.dependence.StringUtils.fastTwoPartSplit;

/**
 * {@code Identifier} represents a validated namespace and path used by
 * source-generation inputs. An omitted or empty namespace selects
 * {@code "minecraft"}; path spelling and character case are preserved. Equality
 * compares both stored components, and serialization joins them with a colon
 * for resource-map lookups.
 *
 * @param namespace validated resource namespace
 * @param path validated resource path
 *
 * @author Cool_Loong
 */
public record Identifier(String namespace, String path) {
    /**
     * {@code NAMESPACE_SEPARATOR} separates the namespace from the resource
     * path when parsing or serializing generator-side identifiers.
     */
    public static final String NAMESPACE_SEPARATOR = ":";
    /**
     * {@code DEFAULT_NAMESPACE} supplies the vanilla namespace when parsed
     * identifiers omit a namespace or provide an empty one.
     */
    public static final String DEFAULT_NAMESPACE = "minecraft";

    /**
     * Creates an identifier from the first two entries of the supplied array.
     * {@code id} must contain at least two entries: the first is the namespace,
     * and the second is the path. Additional entries are ignored, and component
     * validation follows the canonical constructor.
     */
    public Identifier(String[] id) {
        this(id[0], id[1]);
    }

    /**
     * Parses a resource name at its first colon and validates both components.
     * A missing colon or empty prefix selects the default namespace; any
     * additional colon remains in the path and fails character validation.
     */
    public Identifier(String id) {
        this(fastTwoPartSplit(id, NAMESPACE_SEPARATOR, DEFAULT_NAMESPACE));
    }

    /**
     * Creates an identifier after substituting the default for an empty
     * namespace. Both components retain their case, and an empty path is valid.
     * Components must be nonnull; invalid characters cause an
     * {@link InvalidIdentifierException} before construction completes.
     */
    public Identifier(String namespace, String path) {
        this.namespace = namespace.isEmpty() ? DEFAULT_NAMESPACE : namespace;
        this.path = path;
        if (!isNamespaceValid(this.namespace))
            throw new InvalidIdentifierException("Non [A-Za-z0-9_.-] character in namespace of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
        if (!isPathValid(this.path))
            throw new InvalidIdentifierException("Non [A-Za-z0-9/._-] character in path of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
    }

    /**
     * Returns the stored namespace and path separated by a colon.
     */
    public String toString() {
        return this.namespace + NAMESPACE_SEPARATOR + this.path;
    }

    /**
     * Returns whether another identifier has the same case-sensitive
     * components. A {@code null} value or an object of another type does not
     * compare equal, even when its string representation matches this
     * identifier.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Identifier lv) {
            return this.namespace.equals(lv.namespace) && this.path.equals(lv.path);
        }
        return false;
    }

    /**
     * Returns a hash combining the stored namespace and path.
     */
    public int hashCode() {
        return 31 * this.namespace.hashCode() + this.path.hashCode();
    }
}
