package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBindingType extends AbstractEnchantmentType {
    public static final EnchantmentBindingType BINDING_TYPE = new EnchantmentBindingType();
  private EnchantmentBindingType() {
    super(VanillaEnchantmentIds.BINDING, 27, 1, Rarity.VERY_RARE);
  }
}
