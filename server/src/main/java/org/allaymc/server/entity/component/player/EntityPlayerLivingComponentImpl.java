package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.message.I18n;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;
import org.allaymc.server.entity.component.event.CEntityDieEvent;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerLivingComponentImpl extends EntityLivingComponentImpl {
    @ComponentObject
    protected EntityPlayerImpl thisPlayer;

    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        var gameMode = thisPlayer.getGameMode();
        if (gameMode == GameMode.SPECTATOR || gameMode == GameMode.CREATIVE) {
            return damage.getDamageType() == DamageType.API;
        }

        return true;
    }

    @Override
    public boolean hasFallDamage() {
        var gameMode = thisPlayer.getGameMode();
        return gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE;
    }

    @Override
    public boolean hasFireDamage() {
        // Player in creative/spectator game mode can't be damaged by fire
        return super.hasFireDamage() && (thisPlayer.getGameMode() == GameMode.SURVIVAL || thisPlayer.getGameMode() == GameMode.ADVENTURE);
    }

    @Override
    protected void sendEffects(EffectInstance newEffect, EffectInstance oldEffect) {
        super.sendEffects(newEffect, oldEffect);
        thisPlayer.viewEntityEffectChange(thisPlayer, newEffect, oldEffect);
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        super.onDie(event);
        var deathInfo = lastDamage != null ?
                lastDamage.getDamageType().getDeathInfo(thisPlayer, lastDamage.getAttacker()) :
                DamageType.API.getDeathInfo(thisPlayer, null);

        Server.getInstance().getMessageChannel().broadcastTranslatable(deathInfo.left(), (Object[]) deathInfo.right());

        var packet = new DeathInfoPacket();
        // Translate it server-side
        packet.setCauseAttackName(I18n.get().tr(thisPlayer.getLoginData().getLangCode(), deathInfo.left(), (Object[]) deathInfo.right()));
        thisPlayer.sendPacket(packet);
    }
}
