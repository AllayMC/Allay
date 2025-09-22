package org.allaymc.server.item.enchantment;

import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFeatherFallingType extends EnchantmentType {
    public EnchantmentFeatherFallingType() {
        super(new Identifier("minecraft:feather_falling"), 2, 4, Rarity.UNCOMMON, ApplicableType.BOOTS);
    }

    @Override
    public int getProtectionFactor(DamageType damageType, int level) {
        if (damageType != DamageType.FALL) {
            return 0;
        }
        return level * 3;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 6 + 1;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 10;
    }
}
