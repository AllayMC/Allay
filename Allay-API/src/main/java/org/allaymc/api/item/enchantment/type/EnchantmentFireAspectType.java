package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireAspectType extends AbstractEnchantmentType {
    public static final EnchantmentFireAspectType FIRE_ASPECT_TYPE = new EnchantmentFireAspectType();
  private EnchantmentFireAspectType() {
    super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE);
  }
}
