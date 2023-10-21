package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSoulSpeedType extends AbstractEnchantmentType {
  private EnchantmentSoulSpeedType() {
    super(VanillaEnchantmentIds.SOUL_SPEED, 36, 3, Rarity.VERY_RARE);
  }
}
