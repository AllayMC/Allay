package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLureType extends AbstractEnchantmentType {
  private EnchantmentLureType() {
    super(VanillaEnchantmentIds.LURE, 24, 3, Rarity.RARE);
  }
}
