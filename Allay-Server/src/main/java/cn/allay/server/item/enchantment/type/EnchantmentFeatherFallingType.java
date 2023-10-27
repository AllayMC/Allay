package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFeatherFallingType extends AbstractEnchantmentType {
    public static final EnchantmentFeatherFallingType FEATHER_FALLING_TYPE = new EnchantmentFeatherFallingType();
  private EnchantmentFeatherFallingType() {
    super(VanillaEnchantmentIds.FEATHER_FALLING, 2, 4, Rarity.UNCOMMON);
  }
}
