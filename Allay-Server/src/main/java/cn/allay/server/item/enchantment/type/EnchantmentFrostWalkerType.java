package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public class EnchantmentFrostWalkerType extends AbstractEnchantmentType {
    private EnchantmentFrostWalkerType() {
        super(VanillaEnchantmentIds.FROST_WALKER, 25, 3, Rarity.RARE);
    }
}
