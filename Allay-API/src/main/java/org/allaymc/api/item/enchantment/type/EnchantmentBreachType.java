package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2024/6/18
 *
 * @author daoge_cmd
 */
public class EnchantmentBreachType extends AbstractEnchantmentType {
    public static final EnchantmentBreachType BREACH_TYPE = new EnchantmentBreachType();
    private EnchantmentBreachType() {
        // TODO: Check rarity
        super(new Identifier("minecraft:breach"), 40, 4, Rarity.VERY_RARE);
    }
}
