package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPunchType extends AbstractEnchantmentType {
    public static final EnchantmentPunchType PUNCH_TYPE = new EnchantmentPunchType();
  private EnchantmentPunchType() {
    super(VanillaEnchantmentIds.PUNCH, 20, 2, Rarity.RARE);
  }
}
