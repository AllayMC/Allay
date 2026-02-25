package org.allaymc.api.entity.property.type;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * @author daoge_cmd
 */
@Getter
public final class IntPropertyType extends BaseEntityPropertyType<Integer> {

    private final int min;
    private final int max;

    private IntPropertyType(String name, int min, int max, int defaultValue) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultValue);
        this.min = min;
        this.max = max;
    }

    public static IntPropertyType of(String name, int min, int max, int defaultValue) {
        return new IntPropertyType(name, min, max, defaultValue);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }
}
