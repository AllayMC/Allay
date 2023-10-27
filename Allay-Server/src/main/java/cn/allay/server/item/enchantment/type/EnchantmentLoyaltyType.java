package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLoyaltyType extends AbstractEnchantmentType {
    public static final EnchantmentLoyaltyType LOYALTY_TYPE = new EnchantmentLoyaltyType();
  private EnchantmentLoyaltyType() {
    super(VanillaEnchantmentIds.LOYALTY, 31, 3, Rarity.UNCOMMON);
  }
}
