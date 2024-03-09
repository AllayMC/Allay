package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMultishotType extends AbstractEnchantmentType {
    public static final EnchantmentMultishotType MULTISHOT_TYPE = new EnchantmentMultishotType();
  private EnchantmentMultishotType() {
    super(new Identifier("minecraft:multishot"), 33, 1, Rarity.RARE);
  }
}
