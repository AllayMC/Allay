package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

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
