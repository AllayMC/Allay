package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLuckOfTheSeaType extends AbstractEnchantmentType {
  private EnchantmentLuckOfTheSeaType() {
    super(VanillaEnchantmentIds.LUCK_OF_THE_SEA, 23, 3, Rarity.RARE);
  }
}
