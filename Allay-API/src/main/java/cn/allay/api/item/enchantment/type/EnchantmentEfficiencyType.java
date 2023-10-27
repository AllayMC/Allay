package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentEfficiencyType extends AbstractEnchantmentType {
    public static final EnchantmentEfficiencyType EFFICIENCY_TYPE = new EnchantmentEfficiencyType();
  private EnchantmentEfficiencyType() {
    super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON);
  }
}
