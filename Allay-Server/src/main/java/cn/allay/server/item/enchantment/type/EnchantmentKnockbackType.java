package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentKnockbackType extends AbstractEnchantmentType {
    public static final EnchantmentKnockbackType KNOCKBACK_TYPE = new EnchantmentKnockbackType();
  private EnchantmentKnockbackType() {
    super(VanillaEnchantmentIds.KNOCKBACK, 12, 2, Rarity.UNCOMMON);
  }
}
