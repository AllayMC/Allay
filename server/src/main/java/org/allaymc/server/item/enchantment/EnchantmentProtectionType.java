package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentProtectionType} represents the built-in Protection
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.ARMOR_WITHOUT_ELYTRA}; the
 * table option generator uses the overridden power bounds when considering each
 * normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentProtectionType extends EnchantmentType {
    /**
     * Creates and registers the built-in Protection enchantment with numeric ID
     * {@code 0}. The type uses {@code Rarity.COMMON}, a normal maximum of
     * {@code 4}, and the {@code ApplicableType.ARMOR_WITHOUT_ELYTRA} item
     * category.
     */
    public EnchantmentProtectionType() {
        super(new Identifier("minecraft:protection"), 0, 4, Rarity.COMMON, ApplicableType.ARMOR_WITHOUT_ELYTRA);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentBlastProtectionType},
     * {@link EnchantmentFireProtectionType},
     * {@link EnchantmentProjectileProtectionType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentFireProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }

    /**
     * Returns zero protection for {@code DamageType.STARVE},
     * {@code DamageType.VOID}, {@code DamageType.COMMAND},
     * {@code DamageType.SONIC_BOOM}. Every other value, including {@code null},
     * receives {@code level} protection. This method does not check whether
     * {@code level} lies within the configured normal enchantment level bounds.
     */
    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (
                damageType == DamageType.STARVE ||
                damageType == DamageType.VOID ||
                damageType == DamageType.COMMAND ||
                damageType == DamageType.SONIC_BOOM
        ) {
            return 0;
        }
        return level;
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
