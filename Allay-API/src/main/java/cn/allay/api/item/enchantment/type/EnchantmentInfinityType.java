package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentInfinityType extends AbstractEnchantmentType {
    public static final EnchantmentInfinityType INFINITY_TYPE = new EnchantmentInfinityType();
  private EnchantmentInfinityType() {
    super(new Identifier("minecraft:infinity"), 22, 1, Rarity.VERY_RARE);
  }
}
