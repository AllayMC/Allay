package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentDepthStriderType extends AbstractEnchantmentType {
    public static final EnchantmentDepthStriderType DEPTH_STRIDER_TYPE = new EnchantmentDepthStriderType();
  private EnchantmentDepthStriderType() {
    super(new Identifier("minecraft:depth_strider"), 7, 3, Rarity.RARE);
  }
}
