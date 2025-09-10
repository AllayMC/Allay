package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer.DamageType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentBlastProtectionType extends AbstractEnchantmentProtectionType {
    public EnchantmentBlastProtectionType() {
        super(new Identifier("minecraft:blast_protection"), 3, 4, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFireProtectionType ||
               other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }

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

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 + 3;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 12;
    }
}
