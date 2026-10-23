package org.allaymc.api.entity.property.type;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * {@code IntPropertyType} describes a named integer entity property for
 * default lookup, persistence, and client synchronization. The constructor
 * materializes the inclusive range as a list and retains separate endpoints
 * for client encoding; callers must choose a manageable range because
 * construction enumerates every represented integer.
 *
 * @author daoge_cmd
 */
@Getter
public final class IntPropertyType extends BaseEntityPropertyType<Integer> {

    /**
     * Configured lower endpoint for this numeric property.
     */
    private final int min;
    /**
     * Configured upper endpoint for this numeric property.
     */
    private final int max;

    /**
     * Creates an integer property by materializing the supplied inclusive
     * range. The range uses {@code max + 1} as an exclusive end; integer
     * overflow or reversed bounds can produce an empty list, and the default
     * is not range-checked.
     */
    private IntPropertyType(String name, int min, int max, int defaultValue) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultValue);
        this.min = min;
        this.max = max;
    }

    /**
     * Creates an integer property definition by enumerating its configured
     * range. Callers must avoid oversized ranges and upper-end overflow; this
     * factory does not validate endpoint ordering or that the default belongs
     * to the range.
     */
    public static IntPropertyType of(String name, int min, int max, int defaultValue) {
        return new IntPropertyType(name, min, max, defaultValue);
    }

    /**
     * Returns the integer representation category for this property
     * definition.
     */
    @Override
    public Type getType() {
        return Type.INT;
    }
}
