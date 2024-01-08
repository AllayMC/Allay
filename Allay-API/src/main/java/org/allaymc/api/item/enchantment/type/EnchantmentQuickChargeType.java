package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentQuickChargeType extends AbstractEnchantmentType {
    public static final EnchantmentQuickChargeType QUICK_CHARGE_TYPE = new EnchantmentQuickChargeType();
  private EnchantmentQuickChargeType() {
    super(new Identifier("minecraft:quick_charge"), 35, 3, Rarity.UNCOMMON);
  }
}
