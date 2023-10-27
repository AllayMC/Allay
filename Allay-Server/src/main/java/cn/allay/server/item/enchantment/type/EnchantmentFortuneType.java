package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFortuneType extends AbstractEnchantmentType {
    public static final EnchantmentFortuneType FORTUNE_TYPE = new EnchantmentFortuneType();
  private EnchantmentFortuneType() {
    super(VanillaEnchantmentIds.FORTUNE, 18, 3, Rarity.RARE);
  }
}
