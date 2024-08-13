package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2024/8/14
 *
 * @author daoge_cmd
 */
public abstract class AbstractEnchantmentProtectionType extends AbstractEnchantmentType {
    protected AbstractEnchantmentProtectionType(Identifier identifier, int id, int maxLevel, Rarity rarity) {
        super(identifier, id, maxLevel, rarity);
    }

    // See https://minecraft.wiki/w/Armor#Enchantments
    public abstract int getProtectionFactor(DamageContainer.DamageType damageType, int level);
}
