package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBindingType extends AbstractEnchantmentType {
    public static final EnchantmentBindingType BINDING_TYPE = new EnchantmentBindingType();
  private EnchantmentBindingType() {
    super(new Identifier("minecraft:binding"), 27, 1, Rarity.VERY_RARE);
  }
}
