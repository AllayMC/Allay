package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageContainer.DamageType;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireProtectionType extends AbstractEnchantmentProtectionType {
    public EnchantmentFireProtectionType() {
        super(new Identifier("minecraft:fire_protection"), 1, 4, Rarity.UNCOMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
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
}
