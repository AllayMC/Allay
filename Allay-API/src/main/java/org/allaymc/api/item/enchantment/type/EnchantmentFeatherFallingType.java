package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFeatherFallingType extends AbstractEnchantmentProtectionType {
    public EnchantmentFeatherFallingType() {
        super(new Identifier("minecraft:feather_falling"), 2, 4, Rarity.UNCOMMON);
    }

    @Override
    public int getProtectionFactor(DamageContainer.DamageType damageType, int level) {
        if (damageType != DamageContainer.DamageType.FALL) {
            return 0;
        }
        return level * 3;
    }
}
