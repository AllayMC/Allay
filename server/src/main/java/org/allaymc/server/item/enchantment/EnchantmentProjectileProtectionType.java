package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentProjectileProtectionType} represents the built-in
 * Projectile Protection enchantment's applicability and table-power rules. Its
 * constructor registers the type and configures
 * {@code ApplicableType.ARMOR_WITHOUT_ELYTRA}; the table option generator uses
 * the overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentProjectileProtectionType extends EnchantmentType {
    /**
     * Creates and registers the built-in Projectile Protection enchantment with
     * numeric ID {@code 4}. The type uses {@code Rarity.UNCOMMON}, a normal
     * maximum of {@code 4}, and the {@code ApplicableType.ARMOR_WITHOUT_ELYTRA}
     * item category.
     */
    public EnchantmentProjectileProtectionType() {
        super(new Identifier("minecraft:projectile_protection"), 4, 4, Rarity.UNCOMMON, ApplicableType.ARMOR_WITHOUT_ELYTRA);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentProtectionType},
     * {@link EnchantmentBlastProtectionType},
     * {@link EnchantmentFireProtectionType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentFireProtectionType;
    }

    /**
     * Returns {@code level * 2} protection for {@code DamageType.PROJECTILE},
     * {@code DamageType.FIREBALL}. All other damage types, including
     * {@code null}, receive zero protection; the supplied {@code level} is used
     * without normal enchantment-level range validation.
     */
    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (damageType != DamageType.PROJECTILE && damageType != DamageType.FIREBALL) {
            return 0;
        }
        return level * 2;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 6 + 3}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 6 + 3;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 15}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 15;
    }
}
