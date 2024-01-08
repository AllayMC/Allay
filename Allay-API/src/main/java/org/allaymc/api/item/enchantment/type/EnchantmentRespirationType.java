package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRespirationType extends AbstractEnchantmentType {
    public static final EnchantmentRespirationType RESPIRATION_TYPE = new EnchantmentRespirationType();
  private EnchantmentRespirationType() {
    super(new Identifier("minecraft:respiration"), 6, 3, Rarity.RARE);
  }
}
