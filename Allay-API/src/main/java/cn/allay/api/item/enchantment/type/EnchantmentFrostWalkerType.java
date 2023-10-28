package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

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
