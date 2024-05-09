package org.allaymc.server.entity.component.player;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Allay Project 2024/1/19
 *
 * @author daoge_cmd
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntityPlayerDamageComponentImpl extends EntityDamageComponentImpl {
    @Dependency
    protected EntityPlayerBaseComponent baseComponent;

    @Override
    public boolean canAttack(DamageContainer damage) {
        var gameType = baseComponent.getGameType();
        if (gameType == GameType.SPECTATOR || gameType == GameType.CREATIVE) {
            return damage.getDamageType() == DamageContainer.DamageType.API;
        }
        return true;
    }

    @Override
    public boolean hasFallDamage() {
        var gameType = baseComponent.getGameType();
        return gameType == GameType.SURVIVAL || gameType == GameType.ADVENTURE;
    }
}
