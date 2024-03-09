package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFortuneType extends AbstractEnchantmentType {
    public static final EnchantmentFortuneType FORTUNE_TYPE = new EnchantmentFortuneType();
  private EnchantmentFortuneType() {
    super(new Identifier("minecraft:fortune"), 18, 3, Rarity.RARE);
  }
}
