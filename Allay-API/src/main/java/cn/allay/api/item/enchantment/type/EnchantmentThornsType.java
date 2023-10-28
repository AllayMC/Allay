package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentThornsType extends AbstractEnchantmentType {
    public static final EnchantmentThornsType THORNS_TYPE = new EnchantmentThornsType();
  private EnchantmentThornsType() {
    super(new Identifier("minecraft:thorns"), 5, 3, Rarity.VERY_RARE);
  }
}
