package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.utils.identifier.Identifier;

import java.util.function.Supplier;

/**
 * Defines a type of memory that entities can store.
 *
 * @param <T> the type of data stored for this memory
 * @author daoge_cmd
 */
public record MemoryType<T>(Identifier identifier, Supplier<T> defaultData) {

    public MemoryType(Identifier identifier) {
        this(identifier, () -> null);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
