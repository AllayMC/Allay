package org.allaymc.server.entity.component.player;

import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.data.ArmorTier;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Allay Project 2024/1/19
 *
 * @author daoge_cmd
 */
public class EntityPlayerDamageComponentImpl extends EntityDamageComponentImpl {
    @ComponentedObject
    protected EntityPlayer thisPlayer;

    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        var gameType = thisPlayer.getGameType();
        if (gameType == GameType.SPECTATOR || gameType == GameType.CREATIVE)
            return damage.getDamageType() == DamageContainer.DamageType.API;
        return true;
    }

    @Override
    public boolean hasFallDamage() {
        var gameType = thisPlayer.getGameType();
        return gameType == GameType.SURVIVAL || gameType == GameType.ADVENTURE;
    }

    // TODO: Implement breach enchantment
    // TODO: Enchantment Protection Factor
    @Override
    protected void applyArmor(DamageContainer damage) {
        if (!damage.canBeReducedByArmor()) return;

        int durabilityIncreased = Math.max(1, (int)(damage.getSourceDamage() / 4.0f));
        var armorContainer = thisPlayer.getContainer(FullContainerType.ARMOR);
        for (var item : armorContainer.getItemStacks()) {
            item.increaseDurability(durabilityIncreased);
            armorContainer.onAllSlotsChange();
        }

        if (damage.getDamageType() == DamageContainer.DamageType.FALL) {
            // Fall damage can't be reduced by armor value,
            // but it can be reduced by feather falling enchantment
            applyArmorWhenFall(damage);
            return;
        }

        var totalArmorValue = 0.0f;
        var totalToughnessValue = 0.0f;
        var enchantmentProtectionFactor = 0;
        var knockbackResistance = 0.0f;

        for (var item : armorContainer.getItemStacks()) {
            if (item == ItemAirStack.AIR_STACK) continue;

            totalArmorValue += item.getItemData().armorValue();
            totalToughnessValue += item.getItemData().toughnessValue();
            enchantmentProtectionFactor += item.getEnchantmentProtectionFactor(damage.getDamageType());
            if (ItemHelper.getArmorTier(item.getItemType()) == ArmorTier.NETHERITE) {
                knockbackResistance += 0.1f;
            }
        }
        enchantmentProtectionFactor = Math.min(20, enchantmentProtectionFactor);
        // Update knockback resistance until we use it
        thisPlayer.setAttribute(AttributeType.KNOCKBACK_RESISTANCE, knockbackResistance);

        // See https://minecraft.wiki/w/Armor#Damage_reduction
        final var v = totalArmorValue;
        final var t = totalToughnessValue;
        damage.updateFinalDamage(d -> {
            if (0 <= d && d <= 1.6f * v + 0.2f * v * t) {
                return (1.0f / (6.25f + 50.0f)) * d * d +
                       (1.0f - v / 25.0f) * d;
            } else {
                return (1.0f - v / 125.0f) * d;
            }
        });

        // See https://minecraft.wiki/w/Armor#Enchantments
        final var epf = enchantmentProtectionFactor;
        if (epf != 0) {
            damage.updateFinalDamage(d -> d * (1.0f - epf / 25.0f));
        }
    }

    protected void applyArmorWhenFall(DamageContainer damage) {
        var enchantmentProtectionFactor = 0;

        for (var item : thisPlayer.getContainer(FullContainerType.ARMOR).getItemStacks()) {
            if (item == ItemAirStack.AIR_STACK) continue;
            enchantmentProtectionFactor += item.getEnchantmentProtectionFactor(damage.getDamageType());
        }
        enchantmentProtectionFactor = Math.min(20, enchantmentProtectionFactor);

        if (enchantmentProtectionFactor == 0) return;

        final var epf = enchantmentProtectionFactor;
        damage.updateFinalDamage(d -> d * (1.0f - epf / 25.0f));
    }
}
