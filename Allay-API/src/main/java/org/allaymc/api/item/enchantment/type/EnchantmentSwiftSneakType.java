package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSwiftSneakType extends AbstractEnchantmentType {
    public static final EnchantmentSwiftSneakType SWIFT_SNEAK_TYPE = new EnchantmentSwiftSneakType();
  private EnchantmentSwiftSneakType() {
    super(new Identifier("minecraft:swift_sneak"), 37, 3, Rarity.VERY_RARE);
  }
}
