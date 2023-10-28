package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireAspectType extends AbstractEnchantmentType {
    public static final EnchantmentFireAspectType FIRE_ASPECT_TYPE = new EnchantmentFireAspectType();
  private EnchantmentFireAspectType() {
    super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE);
  }
}
