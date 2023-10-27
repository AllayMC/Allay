package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPowerType extends AbstractEnchantmentType {
    public static final EnchantmentPowerType POWER_TYPE = new EnchantmentPowerType();
  private EnchantmentPowerType() {
    super(VanillaEnchantmentIds.POWER, 19, 5, Rarity.COMMON);
  }
}
