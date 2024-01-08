package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLuckOfTheSeaType extends AbstractEnchantmentType {
    public static final EnchantmentLuckOfTheSeaType LUCK_OF_THE_SEA_TYPE = new EnchantmentLuckOfTheSeaType();
  private EnchantmentLuckOfTheSeaType() {
    super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE);
  }
}
