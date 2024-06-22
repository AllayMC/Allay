package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentFireProtectionType FIRE_PROTECTION_TYPE = new EnchantmentFireProtectionType();

    private EnchantmentFireProtectionType() {
        super(new Identifier("minecraft:fire_protection"), 1, 4, Rarity.UNCOMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentBlastProtectionType ||
               other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }
}
