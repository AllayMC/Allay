package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentProtectionType PROTECTION_TYPE = new EnchantmentProtectionType();
  private EnchantmentProtectionType() {
    super(new Identifier("minecraft:protection"), 0, 4, Rarity.COMMON);
  }
}
