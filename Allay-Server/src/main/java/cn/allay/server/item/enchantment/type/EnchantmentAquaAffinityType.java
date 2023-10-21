package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentAquaAffinityType extends AbstractEnchantmentType {
  private EnchantmentAquaAffinityType() {
    super(VanillaEnchantmentIds.AQUA_AFFINITY, 8, 1, Rarity.RARE);
  }
}
