package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBaneOfArthropodsType extends AbstractEnchantmentType {
    public static final EnchantmentBaneOfArthropodsType BANE_OF_ARTHROPODS_TYPE = new EnchantmentBaneOfArthropodsType();
  private EnchantmentBaneOfArthropodsType() {
    super(new Identifier("minecraft:bane_of_arthropods"), 11, 5, Rarity.UNCOMMON);
  }
}
