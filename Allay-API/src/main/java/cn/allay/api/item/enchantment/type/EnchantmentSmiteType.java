package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSmiteType extends AbstractEnchantmentType {
    public static final EnchantmentSmiteType SMITE_TYPE = new EnchantmentSmiteType();
  private EnchantmentSmiteType() {
    super(new Identifier("minecraft:smite"), 10, 5, Rarity.UNCOMMON);
  }
}
