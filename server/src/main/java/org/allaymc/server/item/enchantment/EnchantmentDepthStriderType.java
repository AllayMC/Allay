package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentDepthStriderType} represents the built-in Depth Strider
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.BOOTS}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentDepthStriderType extends EnchantmentType {
    /**
     * Creates and registers the built-in Depth Strider enchantment with numeric
     * ID {@code 7}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.BOOTS} item category.
     */
    public EnchantmentDepthStriderType() {
        super(new Identifier("minecraft:depth_strider"), 7, 3, Rarity.RARE, ApplicableType.BOOTS);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentFrostWalkerType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFrostWalkerType;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 10}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code level * 100}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 100;
    }
}
