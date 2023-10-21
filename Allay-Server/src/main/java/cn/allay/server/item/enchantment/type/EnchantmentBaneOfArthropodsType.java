package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBaneOfArthropodsType extends AbstractEnchantmentType {
  private EnchantmentBaneOfArthropodsType() {
    super(VanillaEnchantmentIds.BANE_OF_ARTHROPODS, 11, 5, Rarity.UNCOMMON);
  }
}
