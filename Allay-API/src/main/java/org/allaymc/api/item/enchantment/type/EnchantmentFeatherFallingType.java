package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

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
