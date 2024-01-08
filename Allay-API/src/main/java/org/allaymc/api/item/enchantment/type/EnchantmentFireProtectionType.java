package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentFireProtectionType FIRE_PROTECTION_TYPE = new EnchantmentFireProtectionType();
  private EnchantmentFireProtectionType() {
    super(new Identifier("minecraft:fire_protection"), 1, 4, Rarity.UNCOMMON);
  }
}
