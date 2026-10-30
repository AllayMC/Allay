package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentBlastProtectionType} represents the built-in Blast
 * Protection enchantment's applicability and table-power rules. Its constructor
 * registers the type and configures
 * {@code ApplicableType.ARMOR_WITHOUT_ELYTRA}; the table option generator uses
 * the overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentBlastProtectionType extends EnchantmentType {
    /**
     * Creates and registers the built-in Blast Protection enchantment with
     * numeric ID {@code 3}. The type uses {@code Rarity.RARE}, a normal maximum
     * of {@code 4}, and the {@code ApplicableType.ARMOR_WITHOUT_ELYTRA} item
     * category.
     */
    public EnchantmentBlastProtectionType() {
        super(new Identifier("minecraft:blast_protection"), 3, 4, Rarity.RARE, ApplicableType.ARMOR_WITHOUT_ELYTRA);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentFireProtectionType},
     * {@link EnchantmentProtectionType},
     * {@link EnchantmentProjectileProtectionType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFireProtectionType ||
               other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }

    /**
     * Returns {@code level * 2} protection for
     * {@code DamageType.BLOCK_EXPLOSION}, {@code DamageType.ENTITY_EXPLOSION}.
     * All other damage types, including {@code null}, receive zero protection;
     * the supplied {@code level} is used without normal enchantment-level range
     * validation.
     */
    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (
                damageType != DamageType.BLOCK_EXPLOSION &&
                damageType != DamageType.ENTITY_EXPLOSION
        ) {
            return 0;
        }
        return level * 2;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 8 + 3}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 + 3;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 12}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 12;
    }
}
