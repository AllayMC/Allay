package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMendingType extends AbstractEnchantmentType {
  private EnchantmentMendingType() {
    super(VanillaEnchantmentIds.MENDING, 26, 3, Rarity.RARE);
  }
}
