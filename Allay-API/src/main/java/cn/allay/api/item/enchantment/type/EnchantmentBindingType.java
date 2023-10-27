package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBindingType extends AbstractEnchantmentType {
    public static final EnchantmentBindingType BINDING_TYPE = new EnchantmentBindingType();
  private EnchantmentBindingType() {
    super(new Identifier("minecraft:binding"), 27, 1, Rarity.VERY_RARE);
  }
}
