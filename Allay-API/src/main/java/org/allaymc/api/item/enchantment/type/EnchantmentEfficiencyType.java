package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentEfficiencyType extends AbstractEnchantmentType {
    public EnchantmentEfficiencyType() {
        super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON);
    }
}
