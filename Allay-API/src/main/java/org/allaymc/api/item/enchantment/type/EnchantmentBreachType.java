package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

public class EnchantmentBreachType extends AbstractEnchantmentType {
    public static final EnchantmentBreachType BREACH_TYPE = new EnchantmentBreachType();

    //todo max level and Rarity
    private EnchantmentBreachType() {
        super(new Identifier("minecraft:density"), 40, 1, Rarity.VERY_RARE);
    }
}
