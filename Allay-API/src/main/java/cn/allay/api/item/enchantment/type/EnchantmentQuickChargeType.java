package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentQuickChargeType extends AbstractEnchantmentType {
    public static final EnchantmentQuickChargeType QUICK_CHARGE_TYPE = new EnchantmentQuickChargeType();
  private EnchantmentQuickChargeType() {
    super(new Identifier("minecraft:quick_charge"), 35, 3, Rarity.UNCOMMON);
  }
}
