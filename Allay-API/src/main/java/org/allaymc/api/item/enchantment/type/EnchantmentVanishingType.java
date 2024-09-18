package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentVanishingType extends AbstractEnchantmentType {
    public EnchantmentVanishingType() {
        super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE);
    }
}
