package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFeatherFallingType extends AbstractEnchantmentType {
    public static final EnchantmentFeatherFallingType FEATHER_FALLING_TYPE = new EnchantmentFeatherFallingType();
  private EnchantmentFeatherFallingType() {
    super(new Identifier("minecraft:feather_falling"), 2, 4, Rarity.UNCOMMON);
  }
}
