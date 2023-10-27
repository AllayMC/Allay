package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentChannelingType extends AbstractEnchantmentType {
    public static final EnchantmentChannelingType CHANNELING_TYPE = new EnchantmentChannelingType();
  private EnchantmentChannelingType() {
    super(VanillaEnchantmentIds.CHANNELING, 32, 1, Rarity.VERY_RARE);
  }
}
