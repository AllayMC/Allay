package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentEfficiencyType extends AbstractEnchantmentType {
    public static final EnchantmentEfficiencyType EFFICIENCY_TYPE = new EnchantmentEfficiencyType();
  private EnchantmentEfficiencyType() {
    super(VanillaEnchantmentIds.EFFICIENCY, 15, 5, Rarity.COMMON);
  }
}
