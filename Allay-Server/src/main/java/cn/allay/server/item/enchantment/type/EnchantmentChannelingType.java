package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentChannelingType extends AbstractEnchantmentType {
  private EnchantmentChannelingType() {
    super(VanillaEnchantmentIds.CHANNELING, 32, 1, Rarity.VERY_RARE);
  }
}
