package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLootingType extends AbstractEnchantmentType {
    public static final EnchantmentLootingType LOOTING_TYPE = new EnchantmentLootingType();
  private EnchantmentLootingType() {
    super(new Identifier("minecraft:looting"), 14, 3, Rarity.RARE);
  }
}
