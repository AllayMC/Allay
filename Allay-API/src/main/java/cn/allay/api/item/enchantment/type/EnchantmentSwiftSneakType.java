package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSwiftSneakType extends AbstractEnchantmentType {
    public static final EnchantmentSwiftSneakType SWIFT_SNEAK_TYPE = new EnchantmentSwiftSneakType();
  private EnchantmentSwiftSneakType() {
    super(new Identifier("minecraft:swift_sneak"), 37, 3, Rarity.VERY_RARE);
  }
}
