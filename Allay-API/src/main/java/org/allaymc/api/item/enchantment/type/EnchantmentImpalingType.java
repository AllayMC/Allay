package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentImpalingType extends AbstractEnchantmentType {
    public static final EnchantmentImpalingType IMPALING_TYPE = new EnchantmentImpalingType();
  private EnchantmentImpalingType() {
    super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE);
  }
}
