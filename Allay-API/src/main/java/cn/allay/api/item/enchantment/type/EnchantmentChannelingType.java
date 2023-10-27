package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentChannelingType extends AbstractEnchantmentType {
    public static final EnchantmentChannelingType CHANNELING_TYPE = new EnchantmentChannelingType();
  private EnchantmentChannelingType() {
    super(new Identifier("minecraft:channeling"), 32, 1, Rarity.VERY_RARE);
  }
}
