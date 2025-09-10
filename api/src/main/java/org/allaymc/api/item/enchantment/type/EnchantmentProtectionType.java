package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer.DamageType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentProtectionType extends AbstractEnchantmentProtectionType {
    public EnchantmentProtectionType() {
        super(new Identifier("minecraft:protection"), 0, 4, Rarity.COMMON);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
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

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 11 - 10;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
