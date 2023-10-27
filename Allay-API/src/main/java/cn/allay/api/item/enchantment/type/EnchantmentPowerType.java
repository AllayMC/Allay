package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPowerType extends AbstractEnchantmentType {
    public static final EnchantmentPowerType POWER_TYPE = new EnchantmentPowerType();
  private EnchantmentPowerType() {
    super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON);
  }
}
