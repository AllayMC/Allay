package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentEfficiencyType extends AbstractEnchantmentType {
    public static final EnchantmentEfficiencyType EFFICIENCY = new EnchantmentEfficiencyType();

    private EnchantmentEfficiencyType() {
        super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON);
    }
}
