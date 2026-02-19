package org.allaymc.api.entity.ai.memory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.function.Supplier;

/**
 * Defines a type of memory that entities can store.
 *
 * @param <Data> the type of data stored for this memory
 * @author daoge_cmd
 */
@Getter
@EqualsAndHashCode(of = "identifier")
public class MemoryType<Data> {

    protected final Identifier identifier;
    protected final Supplier<Data> defaultData;

    public MemoryType(Identifier identifier, Supplier<Data> defaultData) {
        this.identifier = identifier;
        this.defaultData = defaultData;
    }

    public MemoryType(Identifier identifier) {
        this(identifier, () -> null);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
