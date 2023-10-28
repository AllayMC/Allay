package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBlastProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentBlastProtectionType BLAST_PROTECTION_TYPE = new EnchantmentBlastProtectionType();
  private EnchantmentBlastProtectionType() {
    super(new Identifier("minecraft:blast_protection"), 3, 4, Rarity.RARE);
  }
}
