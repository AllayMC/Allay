package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLootingType extends AbstractEnchantmentType {
    public static final EnchantmentLootingType LOOTING_TYPE = new EnchantmentLootingType();
  private EnchantmentLootingType() {
    super(VanillaEnchantmentIds.LOOTING, 14, 3, Rarity.RARE);
  }
}
