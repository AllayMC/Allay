package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRiptideType extends AbstractEnchantmentType {
  private EnchantmentRiptideType() {
    super(VanillaEnchantmentIds.RIPTIDE, 30, 3, Rarity.RARE);
  }
}
