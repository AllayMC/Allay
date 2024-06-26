package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentKnockbackType extends AbstractEnchantmentType {
    public static final EnchantmentKnockbackType KNOCKBACK_TYPE = new EnchantmentKnockbackType();

    private EnchantmentKnockbackType() {
        super(new Identifier("minecraft:knockback"), 12, 2, Rarity.UNCOMMON);
    }
}
