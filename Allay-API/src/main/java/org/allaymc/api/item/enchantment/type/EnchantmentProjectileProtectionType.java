package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentProjectileProtectionType extends AbstractEnchantmentType {
    public static final EnchantmentProjectileProtectionType PROJECTILE_PROTECTION_TYPE = new EnchantmentProjectileProtectionType();

    private EnchantmentProjectileProtectionType() {
        super(new Identifier("minecraft:projectile_protection"), 4, 4, Rarity.UNCOMMON);
    }
}
