package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentMultishotType extends AbstractEnchantmentType {
    public static final EnchantmentMultishotType MULTISHOT_TYPE = new EnchantmentMultishotType();
  private EnchantmentMultishotType() {
    super(VanillaEnchantmentIds.MULTISHOT, 33, 1, Rarity.RARE);
  }
}
