package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSilkTouchType extends AbstractEnchantmentType {
    public static final EnchantmentSilkTouchType SILK_TOUCH_TYPE = new EnchantmentSilkTouchType();
  private EnchantmentSilkTouchType() {
    super(new Identifier("minecraft:silk_touch"), 16, 1, Rarity.VERY_RARE);
  }
}
