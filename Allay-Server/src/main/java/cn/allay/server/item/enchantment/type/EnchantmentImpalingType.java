package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentImpalingType extends AbstractEnchantmentType {
    public static final EnchantmentImpalingType IMPALING_TYPE = new EnchantmentImpalingType();
  private EnchantmentImpalingType() {
    super(VanillaEnchantmentIds.IMPALING, 29, 5, Rarity.RARE);
  }
}
