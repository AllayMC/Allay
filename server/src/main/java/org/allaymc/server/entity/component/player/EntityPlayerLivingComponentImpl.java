package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.I18n;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerLivingComponentImpl extends EntityLivingComponentImpl {
    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Dependency
    protected EntityPlayerClientComponentImpl clientComponent;

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

    @Override
    public void setAbsorption(float absorption) {
        super.setAbsorption(absorption);
        this.clientComponent.sendAbsorption(this.absorption);
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        this.clientComponent.sendHealth(this.health, this.maxHealth);
    }

    @Override
    public void setMaxHealth(float maxHealth) {
        super.setMaxHealth(maxHealth);
        this.clientComponent.sendHealth(this.health, this.maxHealth);
    }

    @Override
    protected void onDie() {
        super.onDie();

        var deathInfo = lastDamage != null ?
                lastDamage.getDamageType().getDeathInfo(thisPlayer, lastDamage.getAttacker()) :
                DamageType.API.getDeathInfo(thisPlayer, null);
        Server.getInstance().getMessageChannel().broadcastTranslatable(deathInfo.left(), (Object[]) deathInfo.right());

        var packet1 = new DeathInfoPacket();
        packet1.setCauseAttackName(I18n.get().tr(thisPlayer.getLoginData().getLangCode(), deathInfo.left(), (Object[]) deathInfo.right()));
        this.clientComponent.sendPacket(packet1);

        var packet2 = new RespawnPacket();
        packet2.setPosition(Vector3f.ZERO);
        packet2.setState(RespawnPacket.State.SERVER_SEARCHING);
        this.clientComponent.sendPacket(packet2);
    }
}
