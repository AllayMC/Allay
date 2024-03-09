package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public class EnchantmentFrostWalkerType extends AbstractEnchantmentType {
    public static final EnchantmentFrostWalkerType FROST_WALKER_TYPE = new EnchantmentFrostWalkerType();
    private EnchantmentFrostWalkerType() {
        super(new Identifier("minecraft:frost_walker"), 25, 3, Rarity.RARE);
    }
}
