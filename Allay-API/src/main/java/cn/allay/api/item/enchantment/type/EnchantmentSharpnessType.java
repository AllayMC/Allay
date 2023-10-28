package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSharpnessType extends AbstractEnchantmentType {
    public static final EnchantmentSharpnessType SHARPNESS_TYPE = new EnchantmentSharpnessType();
  private EnchantmentSharpnessType() {
    super(new Identifier("minecraft:sharpness"), 9, 5, Rarity.COMMON);
  }
}
