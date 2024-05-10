package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRespirationType extends AbstractEnchantmentType {
    public static final EnchantmentRespirationType RESPIRATION_TYPE = new EnchantmentRespirationType();

    private EnchantmentRespirationType() {
        super(new Identifier("minecraft:respiration"), 6, 3, Rarity.RARE);
    }
}
