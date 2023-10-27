package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFortuneType extends AbstractEnchantmentType {
    public static final EnchantmentFortuneType FORTUNE_TYPE = new EnchantmentFortuneType();
  private EnchantmentFortuneType() {
    super(new Identifier("minecraft:fortune"), 18, 3, Rarity.RARE);
  }
}
