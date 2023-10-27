package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentDepthStriderType extends AbstractEnchantmentType {
    public static final EnchantmentDepthStriderType DEPTH_STRIDER_TYPE = new EnchantmentDepthStriderType();
  private EnchantmentDepthStriderType() {
    super(new Identifier("minecraft:depth_strider"), 7, 3, Rarity.RARE);
  }
}
