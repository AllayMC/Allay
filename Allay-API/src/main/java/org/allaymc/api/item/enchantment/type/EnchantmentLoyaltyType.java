package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

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
