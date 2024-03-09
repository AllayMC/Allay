package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentEfficiencyType extends AbstractEnchantmentType {
    public static final EnchantmentEfficiencyType EFFICIENCY_TYPE = new EnchantmentEfficiencyType();
  private EnchantmentEfficiencyType() {
    super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON);
  }
}
