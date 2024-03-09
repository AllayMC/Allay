package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentVanishingType extends AbstractEnchantmentType {
    public static final EnchantmentVanishingType VANISHING_TYPE = new EnchantmentVanishingType();
  private EnchantmentVanishingType() {
    super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE);
  }
}
