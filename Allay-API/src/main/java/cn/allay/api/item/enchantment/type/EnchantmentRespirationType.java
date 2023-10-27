package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentRespirationType extends AbstractEnchantmentType {
    public static final EnchantmentRespirationType RESPIRATION_TYPE = new EnchantmentRespirationType();
  private EnchantmentRespirationType() {
    super(new Identifier("minecraft:respiration"), 6, 3, Rarity.RARE);
  }
}
