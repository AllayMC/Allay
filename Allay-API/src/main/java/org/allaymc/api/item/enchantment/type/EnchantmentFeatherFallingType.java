package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

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
