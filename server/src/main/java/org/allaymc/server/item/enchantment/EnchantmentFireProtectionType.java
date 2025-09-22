package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFireProtectionType extends EnchantmentType {
    public EnchantmentFireProtectionType() {
        super(new Identifier("minecraft:fire_protection"), 1, 4, Rarity.UNCOMMON, ApplicableType.ARMOR_WITHOUT_ELYTRA);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }

    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (
                damageType != DamageType.FIRE &&
                damageType != DamageType.LAVA &&
                damageType != DamageType.MAGMA &&
                damageType != DamageType.FIREBALL
        ) {
            return 0;
        }
        return level * 2;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 + 2;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 12;
    }
}
