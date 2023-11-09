package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBlastProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentBlastProtectionType BLAST_PROTECTION_TYPE = new EnchantmentBlastProtectionType();
  private EnchantmentBlastProtectionType() {
    super(new Identifier("minecraft:blast_protection"), 3, 4, Rarity.RARE);
  }
}
