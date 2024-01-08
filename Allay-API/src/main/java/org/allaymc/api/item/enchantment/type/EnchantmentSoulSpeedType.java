package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSoulSpeedType extends AbstractEnchantmentType {
    public static final EnchantmentSoulSpeedType SOUL_SPEED_TYPE = new EnchantmentSoulSpeedType();
  private EnchantmentSoulSpeedType() {
    super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE);
  }
}
