package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRespirationType extends AbstractEnchantmentType {
    public static final EnchantmentRespirationType RESPIRATION_TYPE = new EnchantmentRespirationType();
  private EnchantmentRespirationType() {
    super(VanillaEnchantmentIds.RESPIRATION, 6, 3, Rarity.RARE);
  }
}
