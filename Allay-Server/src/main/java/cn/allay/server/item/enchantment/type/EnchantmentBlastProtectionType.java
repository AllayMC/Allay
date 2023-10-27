package cn.allay.server.item.enchantment.type;

import cn.allay.api.data.VanillaEnchantmentIds;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.server.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBlastProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentBlastProtectionType BLAST_PROTECTION_TYPE = new EnchantmentBlastProtectionType();
  private EnchantmentBlastProtectionType() {
    super(VanillaEnchantmentIds.BLAST_PROTECTION, 3, 4, Rarity.RARE);
  }
}
