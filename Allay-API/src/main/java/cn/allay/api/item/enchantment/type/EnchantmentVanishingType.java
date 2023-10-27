package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentVanishingType extends AbstractEnchantmentType {
    public static final EnchantmentVanishingType VANISHING_TYPE = new EnchantmentVanishingType();
  private EnchantmentVanishingType() {
    super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE);
  }
}
