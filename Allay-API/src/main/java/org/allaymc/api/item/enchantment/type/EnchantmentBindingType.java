package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentBindingType extends AbstractEnchantmentType {
    public EnchantmentBindingType() {
        super(new Identifier("minecraft:binding"), 27, 1, Rarity.VERY_RARE);
    }
}
