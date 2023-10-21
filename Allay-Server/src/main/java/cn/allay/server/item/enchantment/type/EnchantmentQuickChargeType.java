package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentQuickChargeType extends AbstractEnchantmentType {
  private EnchantmentQuickChargeType() {
    super(VanillaEnchantmentIds.QUICK_CHARGE, 35, 3, Rarity.UNCOMMON);
  }
}
