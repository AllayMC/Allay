package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPiercingType extends AbstractEnchantmentType {
    public static final EnchantmentPiercingType PIERCING_TYPE = new EnchantmentPiercingType();
  private EnchantmentPiercingType() {
    super(new Identifier("minecraft:piercing"), 34, 4, Rarity.COMMON);
  }
}
