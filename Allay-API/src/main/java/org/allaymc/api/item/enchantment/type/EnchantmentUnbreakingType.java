package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentUnbreakingType extends AbstractEnchantmentType {
    public static final EnchantmentUnbreakingType UNBREAKING_TYPE = new EnchantmentUnbreakingType();
  private EnchantmentUnbreakingType() {
    super(new Identifier("minecraft:unbreaking"), 17, 3, Rarity.UNCOMMON);
  }
}
