package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFlameType extends AbstractEnchantmentType {
    public static final EnchantmentFlameType FLAME_TYPE = new EnchantmentFlameType();
  private EnchantmentFlameType() {
    super(VanillaEnchantmentIds.FLAME, 21, 1, Rarity.RARE);
  }
}
