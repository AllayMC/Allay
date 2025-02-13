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
 * @author daoge_cmd
 */
public record Identifier(String namespace, String path) implements Cloneable {
    public static final String NAMESPACE_SEPARATOR = ":";
    public static final String DEFAULT_NAMESPACE = "minecraft";

    public Identifier(Plugin plugin, String path) {
        this(plugin.getPluginContainer().descriptor().getName(), path);
    }

    public Identifier(String[] id) {
        this(id[0], id[1]);
    }

    public Identifier(String id) {
        this(fastTwoPartSplit(id, NAMESPACE_SEPARATOR, DEFAULT_NAMESPACE));
    }

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

    public String toString() {
        return this.namespace + NAMESPACE_SEPARATOR + this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Identifier(String namespace1, String path1))) {
            return false;
        }

        return Objects.equals(path, path1) && Objects.equals(namespace, namespace1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Identifier clone() {
        return new Identifier(namespace, path);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Component {}
}
