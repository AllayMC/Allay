package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLootingType extends AbstractEnchantmentType {
    public static final EnchantmentLootingType LOOTING_TYPE = new EnchantmentLootingType();

    private EnchantmentLootingType() {
        super(new Identifier("minecraft:looting"), 14, 3, Rarity.RARE);
    }
}
