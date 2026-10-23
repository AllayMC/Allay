package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentFeatherFallingType} represents the built-in Feather Falling
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.BOOTS}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentFeatherFallingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Feather Falling enchantment with
     * numeric ID {@code 2}. The type uses {@code Rarity.UNCOMMON}, a normal
     * maximum of {@code 4}, and the {@code ApplicableType.BOOTS} item category.
     */
    public EnchantmentFeatherFallingType() {
        super(new Identifier("minecraft:feather_falling"), 2, 4, Rarity.UNCOMMON, ApplicableType.BOOTS);
    }

    /**
     * Returns {@code level * 3} protection for {@code DamageType.FALL}. All
     * other damage types, including {@code null}, receive zero protection; the
     * supplied {@code level} is used without normal enchantment-level range
     * validation.
     */
    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (damageType != DamageType.FALL) {
            return 0;
        }
        return level * 3;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 6 + 1}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 6 + 1;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 10}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 10;
    }
}
