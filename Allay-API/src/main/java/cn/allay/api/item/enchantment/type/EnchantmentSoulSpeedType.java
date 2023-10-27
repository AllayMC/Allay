package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSoulSpeedType extends AbstractEnchantmentType {
    public static final EnchantmentSoulSpeedType SOUL_SPEED_TYPE = new EnchantmentSoulSpeedType();
  private EnchantmentSoulSpeedType() {
    super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE);
  }
}
