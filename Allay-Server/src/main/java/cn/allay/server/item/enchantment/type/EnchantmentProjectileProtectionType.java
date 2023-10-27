package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProjectileProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentProjectileProtectionType PROJECTILE_PROTECTION_TYPE = new EnchantmentProjectileProtectionType();
  private EnchantmentProjectileProtectionType() {
    super(VanillaEnchantmentIds.PROJECTILE_PROTECTION, 4, 4, Rarity.UNCOMMON);
  }
}
