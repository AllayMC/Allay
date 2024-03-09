package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFlameType extends AbstractEnchantmentType {
    public static final EnchantmentFlameType FLAME_TYPE = new EnchantmentFlameType();
  private EnchantmentFlameType() {
    super(new Identifier("minecraft:flame"), 21, 1, Rarity.RARE);
  }
}
