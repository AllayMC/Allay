package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentSharpnessType} represents the built-in Sharpness
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.SWORD_OR_AXE}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentSharpnessType extends EnchantmentType {
    /**
     * Creates and registers the built-in Sharpness enchantment with numeric ID
     * {@code 9}. The type uses {@code Rarity.COMMON}, a normal maximum of
     * {@code 5}, and the {@code ApplicableType.SWORD_OR_AXE} item category.
     */
    public EnchantmentSharpnessType() {
        super(new Identifier("minecraft:sharpness"), 9, 5, Rarity.COMMON, ApplicableType.SWORD_OR_AXE);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentBaneOfArthropodsType},
     * {@link EnchantmentSmiteType}; all other values, including {@code null},
     * return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBaneOfArthropodsType || other instanceof EnchantmentSmiteType;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 11 - 10}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 11 - 10;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 20}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
