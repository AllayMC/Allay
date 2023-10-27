package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSilkTouchType extends AbstractEnchantmentType {
    public static final EnchantmentSilkTouchType SILK_TOUCH_TYPE = new EnchantmentSilkTouchType();
  private EnchantmentSilkTouchType() {
    super(VanillaEnchantmentIds.SILK_TOUCH, 16, 1, Rarity.VERY_RARE);
  }
}
