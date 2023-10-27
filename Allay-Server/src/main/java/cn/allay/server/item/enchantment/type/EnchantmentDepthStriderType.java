package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentDepthStriderType extends AbstractEnchantmentType {
    public static final EnchantmentDepthStriderType DEPTH_STRIDER_TYPE = new EnchantmentDepthStriderType();
  private EnchantmentDepthStriderType() {
    super(VanillaEnchantmentIds.DEPTH_STRIDER, 7, 3, Rarity.RARE);
  }
}
