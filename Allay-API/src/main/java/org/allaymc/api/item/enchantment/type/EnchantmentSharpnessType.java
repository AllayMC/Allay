package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

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