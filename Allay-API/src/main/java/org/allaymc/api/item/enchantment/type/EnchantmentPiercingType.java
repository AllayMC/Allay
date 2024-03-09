package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPiercingType extends AbstractEnchantmentType {
    public static final EnchantmentPiercingType PIERCING_TYPE = new EnchantmentPiercingType();
  private EnchantmentPiercingType() {
    super(new Identifier("minecraft:piercing"), 34, 4, Rarity.COMMON);
  }
}
