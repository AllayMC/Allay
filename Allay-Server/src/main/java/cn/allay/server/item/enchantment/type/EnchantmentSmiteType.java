package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSmiteType extends AbstractEnchantmentType {
  private EnchantmentSmiteType() {
    super(VanillaEnchantmentIds.SMITE, 10, 5, Rarity.UNCOMMON);
  }
}
