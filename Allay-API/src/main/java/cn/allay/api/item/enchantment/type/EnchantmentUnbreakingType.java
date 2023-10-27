package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentUnbreakingType extends AbstractEnchantmentType {
    public static final EnchantmentUnbreakingType UNBREAKING_TYPE = new EnchantmentUnbreakingType();
  private EnchantmentUnbreakingType() {
    super(new Identifier("minecraft:unbreaking"), 17, 3, Rarity.UNCOMMON);
  }
}
