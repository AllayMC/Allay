package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.server.Server;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityDamageComponentImpl;
import org.allaymc.server.entity.component.event.CEntityDieEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerDamageComponentImpl extends EntityDamageComponentImpl {
    @ComponentObject
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
    @Override
    protected void applyArmor(DamageContainer damage) {
        if (!damage.canBeReducedByArmor()) return;

        int durabilityIncreased = Math.max(1, (int) (damage.getSourceDamage() / 4f));
        var armorContainer = thisPlayer.getContainer(FullContainerType.ARMOR);
        var itemStackArray = armorContainer.getItemStackArray();
        for (int slot = 0; slot < itemStackArray.length; slot++) {
            var item = itemStackArray[slot];
            if (!item.getItemType().getItemData().isDamageable()) {
                continue;
            }
            item.tryIncreaseDamage(durabilityIncreased);
            armorContainer.notifySlotChange(slot);
        }

        if (damage.getDamageType() == DamageContainer.DamageType.FALL) {
            // Fall damage can't be reduced by armor value,
            // but it can be reduced by feather falling enchantment
            applyArmorWhenFall(damage);
            return;
        }

        var totalArmorValue = 0f;
        var totalToughnessValue = 0f;
        var enchantmentProtectionFactor = 0;

        for (var item : armorContainer.getItemStacks()) {
            if (item == ItemAirStack.AIR_STACK) {
                continue;
            }

            totalArmorValue += item.getItemType().getItemData().armorValue();
            totalToughnessValue += item.getItemType().getItemData().toughnessValue();
            enchantmentProtectionFactor += item.getEnchantmentProtectionFactor(damage.getDamageType());
        }
        enchantmentProtectionFactor = Math.min(20, enchantmentProtectionFactor);

        // See https://minecraft.wiki/w/Armor#Damage_reduction
        final var v = totalArmorValue;
        final var t = totalToughnessValue;
        damage.updateFinalDamage(d -> {
            if (0 <= d && d <= 1.6f * v + 0.2f * v * t) {
                return (1f / (6.25f + 50f)) * d * d +
                       (1f - v / 25f) * d;
            } else {
                return (1f - v / 125f) * d;
            }
        });

        // See https://minecraft.wiki/w/Armor#Enchantments
        final var epf = enchantmentProtectionFactor;
        if (epf != 0) {
            damage.updateFinalDamage(d -> d * (1f - epf / 25f));
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
        damage.updateFinalDamage(d -> d * (1f - epf / 25f));
    }

    @Override
    public boolean hasFireDamage() {
        // Player in creative/spectator game type can't be damaged by fire
        return super.hasFireDamage() && (thisPlayer.getGameType() == GameType.SURVIVAL || thisPlayer.getGameType() == GameType.ADVENTURE);
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        var deathInfo = lastDamage != null ?
                lastDamage.getDamageType().getDeathInfo(thisPlayer, lastDamage.getAttacker()) :
                DamageContainer.DamageType.API.getDeathInfo(thisPlayer, null);

        Server.getInstance().broadcastTr(deathInfo.left(), (Object[]) deathInfo.right());

        var packet = new DeathInfoPacket();
        // Translate it server-side
        packet.setCauseAttackName(I18n.get().tr(thisPlayer.getLoginData().getLangCode(), deathInfo.left(), (Object[]) deathInfo.right()));
        thisPlayer.sendPacket(packet);
    }
}
