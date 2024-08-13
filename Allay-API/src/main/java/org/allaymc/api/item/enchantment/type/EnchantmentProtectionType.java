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
public class EnchantmentProtectionType extends AbstractEnchantmentProtectionType {
    public EnchantmentProtectionType() {
        super(new Identifier("minecraft:protection"), 0, 4, Rarity.COMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentFireProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }

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
}
