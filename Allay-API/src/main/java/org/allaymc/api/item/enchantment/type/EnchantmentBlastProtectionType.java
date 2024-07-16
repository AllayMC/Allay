package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBlastProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentBlastProtectionType BLAST_PROTECTION = new EnchantmentBlastProtectionType();

    private EnchantmentBlastProtectionType() {
        super(new Identifier("minecraft:blast_protection"), 3, 4, Rarity.RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentFireProtectionType ||
               other instanceof EnchantmentProtectionType ||
               other instanceof EnchantmentProjectileProtectionType;
    }
}
