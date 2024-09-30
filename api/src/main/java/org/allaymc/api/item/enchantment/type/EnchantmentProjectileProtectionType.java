package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer.DamageType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentProjectileProtectionType extends AbstractEnchantmentProtectionType {
    public EnchantmentProjectileProtectionType() {
        super(new Identifier("minecraft:projectile_protection"), 4, 4, Rarity.UNCOMMON);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentFireProtectionType;
    }

    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (damageType != DamageType.PROJECTILE && damageType != DamageType.FIREBALL) {
            return 0;
        }
        return level * 2;
    }
}
