package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLureType extends AbstractEnchantmentType {
    public static final EnchantmentLureType LURE_TYPE = new EnchantmentLureType();
  private EnchantmentLureType() {
    super(new Identifier("minecraft:lure"), 24, 3, Rarity.RARE);
  }
}
