package cn.allay.api.item.enchantment.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.enchantment.Rarity;
import cn.allay.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBaneOfArthropodsType extends AbstractEnchantmentType {
    public static final EnchantmentBaneOfArthropodsType BANE_OF_ARTHROPODS_TYPE = new EnchantmentBaneOfArthropodsType();
  private EnchantmentBaneOfArthropodsType() {
    super(new Identifier("minecraft:bane_of_arthropods"), 11, 5, Rarity.UNCOMMON);
  }
}
