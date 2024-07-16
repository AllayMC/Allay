package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentImpalingType extends AbstractEnchantmentType {
    public static final EnchantmentImpalingType IMPALING = new EnchantmentImpalingType();

    private EnchantmentImpalingType() {
        super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE);
    }
}
