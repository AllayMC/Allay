package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPowerType extends AbstractEnchantmentType {
    public static final EnchantmentPowerType POWER_TYPE = new EnchantmentPowerType();
  private EnchantmentPowerType() {
    super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON);
  }
}
