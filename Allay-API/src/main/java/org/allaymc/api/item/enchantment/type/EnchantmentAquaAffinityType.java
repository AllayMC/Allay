package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentAquaAffinityType extends AbstractEnchantmentType {
  public static final EnchantmentAquaAffinityType AQUA_AFFINITY_TYPE = new EnchantmentAquaAffinityType();
  private EnchantmentAquaAffinityType() {
    super(new Identifier("minecraft:aqua_affinity"), 8, 1, Rarity.RARE);
  }
}
