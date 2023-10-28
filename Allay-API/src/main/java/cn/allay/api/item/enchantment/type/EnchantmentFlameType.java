package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFlameType extends AbstractEnchantmentType {
    public static final EnchantmentFlameType FLAME_TYPE = new EnchantmentFlameType();
  private EnchantmentFlameType() {
    super(new Identifier("minecraft:flame"), 21, 1, Rarity.RARE);
  }
}
