package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSwiftSneakType extends AbstractEnchantmentType {
    public static final EnchantmentSwiftSneakType SWIFT_SNEAK_TYPE = new EnchantmentSwiftSneakType();
  private EnchantmentSwiftSneakType() {
    super(VanillaEnchantmentIds.SWIFT_SNEAK, 37, 3, Rarity.VERY_RARE);
  }
}
