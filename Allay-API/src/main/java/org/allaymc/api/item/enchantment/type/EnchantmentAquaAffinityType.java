package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentAquaAffinityType extends AbstractEnchantmentType {
    public EnchantmentAquaAffinityType() {
        super(new Identifier("minecraft:aqua_affinity"), 8, 1, Rarity.RARE);
    }
}
