package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentChannelingType extends AbstractEnchantmentType {
    public static final EnchantmentChannelingType CHANNELING_TYPE = new EnchantmentChannelingType();
  private EnchantmentChannelingType() {
    super(new Identifier("minecraft:channeling"), 32, 1, Rarity.VERY_RARE);
  }
}
