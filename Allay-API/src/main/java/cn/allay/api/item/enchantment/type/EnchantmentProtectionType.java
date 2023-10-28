package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentProtectionType PROTECTION_TYPE = new EnchantmentProtectionType();
  private EnchantmentProtectionType() {
    super(new Identifier("minecraft:protection"), 0, 4, Rarity.COMMON);
  }
}
