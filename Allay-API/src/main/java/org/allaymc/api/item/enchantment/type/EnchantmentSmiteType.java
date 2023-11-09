package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSmiteType extends AbstractEnchantmentType {
    public static final EnchantmentSmiteType SMITE_TYPE = new EnchantmentSmiteType();
  private EnchantmentSmiteType() {
    super(new Identifier("minecraft:smite"), 10, 5, Rarity.UNCOMMON);
  }
}
