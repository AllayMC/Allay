package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProtectionType extends AbstractEnchantmentType {
  private EnchantmentProtectionType() {
    super(VanillaEnchantmentIds.PROTECTION, 0, 4, Rarity.COMMON);
  }
}
