package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMendingType extends AbstractEnchantmentType {
    public static final EnchantmentMendingType MENDING_TYPE = new EnchantmentMendingType();
  private EnchantmentMendingType() {
    super(new Identifier("minecraft:mending"), 26, 3, Rarity.RARE);
  }
}
