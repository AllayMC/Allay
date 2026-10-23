package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentFireAspectType} represents the built-in Fire Aspect
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.SWORD}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentFireAspectType extends EnchantmentType {
    /**
     * Creates and registers the built-in Fire Aspect enchantment with numeric
     * ID {@code 13}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 2}, and the {@code ApplicableType.SWORD} item category.
     */
    public EnchantmentFireAspectType() {
        super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE, ApplicableType.SWORD);
    }

    /**
     * Returns the inclusive lower table-power bound for the supported levels.
     * Level {@code 1} uses {@code 10}, while level {@code 2} uses {@code 30};
     * every other integer level returns {@code 0}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return switch (level) {
            case 1 -> 10;
            case 2 -> 30;
            default -> 0;
        };
    }

    /**
     * Returns the inclusive upper table-power bound for the supported levels.
     * Level {@code 1} uses {@code 61}, while level {@code 2} uses {@code 71};
     * every other integer level returns {@code 0}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return switch (level) {
            case 1 -> 61;
            case 2 -> 71;
            default -> 0;
        };
    }
}
