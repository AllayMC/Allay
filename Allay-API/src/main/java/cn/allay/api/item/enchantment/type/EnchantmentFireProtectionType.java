package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentFireProtectionType FIRE_PROTECTION_TYPE = new EnchantmentFireProtectionType();
  private EnchantmentFireProtectionType() {
    super(new Identifier("minecraft:fire_protection"), 1, 4, Rarity.UNCOMMON);
  }
}
