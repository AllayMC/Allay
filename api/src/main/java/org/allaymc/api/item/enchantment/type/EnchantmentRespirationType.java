package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentRespirationType extends EnchantmentType {
    public EnchantmentRespirationType() {
        super(new Identifier("minecraft:respiration"), 6, 3, Rarity.RARE);
    }
}
