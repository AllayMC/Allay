package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentInfinityType extends AbstractEnchantmentType {
    public static final EnchantmentInfinityType INFINITY_TYPE = new EnchantmentInfinityType();
  private EnchantmentInfinityType() {
    super(VanillaEnchantmentIds.INFINITY, 22, 1, Rarity.VERY_RARE);
  }
}
