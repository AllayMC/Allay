package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMendingType extends AbstractEnchantmentType {
    public static final EnchantmentMendingType MENDING_TYPE = new EnchantmentMendingType();
  private EnchantmentMendingType() {
    super(new Identifier("minecraft:mending"), 26, 3, Rarity.RARE);
  }
}
