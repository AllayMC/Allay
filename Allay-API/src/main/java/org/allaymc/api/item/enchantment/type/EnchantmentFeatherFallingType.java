package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFeatherFallingType extends AbstractEnchantmentType {
    public EnchantmentFeatherFallingType() {
        super(new Identifier("minecraft:feather_falling"), 2, 4, Rarity.UNCOMMON);
    }
}
