package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireAspectType extends AbstractEnchantmentType {
    public static final EnchantmentFireAspectType FIRE_ASPECT_TYPE = new EnchantmentFireAspectType();
  private EnchantmentFireAspectType() {
    super(VanillaEnchantmentIds.FIRE_ASPECT, 13, 2, Rarity.RARE);
  }
}
