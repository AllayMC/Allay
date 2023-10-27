package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentFireProtectionType FIRE_PROTECTION_TYPE = new EnchantmentFireProtectionType();
  private EnchantmentFireProtectionType() {
    super(VanillaEnchantmentIds.FIRE_PROTECTION, 1, 4, Rarity.UNCOMMON);
  }
}
