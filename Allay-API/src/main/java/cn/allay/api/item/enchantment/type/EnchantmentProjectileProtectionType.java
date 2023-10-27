package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProjectileProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentProjectileProtectionType PROJECTILE_PROTECTION_TYPE = new EnchantmentProjectileProtectionType();
  private EnchantmentProjectileProtectionType() {
    super(new Identifier("minecraft:projectile_protection"), 4, 4, Rarity.UNCOMMON);
  }
}
