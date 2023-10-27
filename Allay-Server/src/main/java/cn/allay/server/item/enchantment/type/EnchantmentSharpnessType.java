package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSharpnessType extends AbstractEnchantmentType {
    public static final EnchantmentSharpnessType SHARPNESS_TYPE = new EnchantmentSharpnessType();
  private EnchantmentSharpnessType() {
    super(VanillaEnchantmentIds.SHARPNESS, 9, 5, Rarity.COMMON);
  }
}
