package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * {@code EnchantmentInstance} pairs an enchantment type with an integer level
 * for item storage and behavior. Instances keep both values fixed, while
 * {@code setLevel} obtains another instance from the type cache. Equality and
 * hashing identify the type alone, so different levels of the same type compare
 * equally.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class EnchantmentInstance {
    /**
     * Enchantment type represented by this instance.
     */
    protected final EnchantmentType type;
    /**
     * Stored enchantment level without range validation.
     */
    protected final int level;

    /**
     * Returns the enchantment type retained by this instance.
     */
    public EnchantmentType getType() {
        return type;
    }

    /**
     * Returns the integer enchantment level retained by this instance.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the type-cached instance for the supplied level. This instance
     * remains unchanged, and {@code level} is accepted without enforcing the
     * enchantment type's usual maximum or a positive minimum.
     */
    public EnchantmentInstance setLevel(int level) {
        return getType().createInstance(level);
    }

    /**
     * Serializes the type identifier and level as NBT shorts. Values outside
     * the signed short range are narrowed by Java casts, so the resulting
     * {@code "id"} and {@code "lvl"} may differ from their integer values.
     */
    public NbtMap saveNBT() {
        // Item enchantment NBT stores both values as shorts. These casts retain that
        // representation even when callers created an instance with an out-of-range integer.
        return NbtMap.builder()
                .putShort("id", (short) getType().getId())
                .putShort("lvl", (short) getLevel())
                .build();
    }

    /**
     * Returns whether another enchantment instance has an equal type. The
     * stored level does not participate in equality, so instances of the same
     * type compare equally even when their levels differ.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EnchantmentInstance that)) {
            return false;
        }

        return Objects.equals(type, that.type);
    }

    /**
     * Returns the type hash used by level-insensitive instance equality.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }
}
