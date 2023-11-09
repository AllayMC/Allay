package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLoyaltyType extends AbstractEnchantmentType {
    public static final EnchantmentLoyaltyType LOYALTY_TYPE = new EnchantmentLoyaltyType();
  private EnchantmentLoyaltyType() {
    super(new Identifier("minecraft:loyalty"), 31, 3, Rarity.UNCOMMON);
  }
}
