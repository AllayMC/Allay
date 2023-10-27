package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRiptideType extends AbstractEnchantmentType {
    public static final EnchantmentRiptideType RIPTIDE_TYPE = new EnchantmentRiptideType();
  private EnchantmentRiptideType() {
    super(new Identifier("minecraft:riptide"), 30, 3, Rarity.RARE);
  }
}
