package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPunchType extends AbstractEnchantmentType {
    public static final EnchantmentPunchType PUNCH_TYPE = new EnchantmentPunchType();
  private EnchantmentPunchType() {
    super(new Identifier("minecraft:punch"), 20, 2, Rarity.RARE);
  }
}
