package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLuckOfTheSeaType extends AbstractEnchantmentType {
    public static final EnchantmentLuckOfTheSeaType LUCK_OF_THE_SEA_TYPE = new EnchantmentLuckOfTheSeaType();
  private EnchantmentLuckOfTheSeaType() {
    super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE);
  }
}
