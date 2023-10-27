package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentImpalingType extends AbstractEnchantmentType {
    public static final EnchantmentImpalingType IMPALING_TYPE = new EnchantmentImpalingType();
  private EnchantmentImpalingType() {
    super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE);
  }
}
