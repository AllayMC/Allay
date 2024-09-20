package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentKnockbackType extends EnchantmentType {
    public EnchantmentKnockbackType() {
        super(new Identifier("minecraft:knockback"), 12, 2, Rarity.UNCOMMON);
    }
}
