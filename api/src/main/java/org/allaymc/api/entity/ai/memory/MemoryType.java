package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.utils.identifier.Identifier;

import java.util.function.Supplier;

/**
 * {@code MemoryType} identifies an AI memory entry and supplies its default
 * value when storage has no entry. Record equality includes both {@code
 * identifier} and {@code defaultData}, so callers normally share the same key
 * instance between memory producers and consumers.
 *
 * @param identifier namespaced memory key
 * @param defaultData supplier for absent entries; storage may retain a non-{@code null} result for later reads
 * @param <T> stored memory value type
 *
 * @author daoge_cmd
 */
public record MemoryType<T>(Identifier identifier, Supplier<T> defaultData) {

    /**
     * Creates a memory key whose absent value is {@code null}. The generated
     * canonical constructor also accepts a default supplier; this convenience
     * constructor installs a supplier returning {@code null}.
     */
    public MemoryType(Identifier identifier) {
        this(identifier, () -> null);
    }

    /**
     * Returns the namespaced identifier text associated with this memory key.
     */
    @Override
    public String toString() {
        return identifier.toString();
    }
}
