package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentThornsType extends AbstractEnchantmentType {
    public EnchantmentThornsType() {
        super(new Identifier("minecraft:thorns"), 5, 3, Rarity.VERY_RARE);
    }
}
