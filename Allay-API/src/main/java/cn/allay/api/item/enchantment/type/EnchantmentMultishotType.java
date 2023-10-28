package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMultishotType extends AbstractEnchantmentType {
    public static final EnchantmentMultishotType MULTISHOT_TYPE = new EnchantmentMultishotType();
  private EnchantmentMultishotType() {
    super(new Identifier("minecraft:multishot"), 33, 1, Rarity.RARE);
  }
}
