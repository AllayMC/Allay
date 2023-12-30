package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.perm.Permissible;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandSender extends TextReceiver, Permissible {

    String getName();

    CommandOriginData getCommandOriginData();

    Location3fc getCmdExecuteLocation();

    default void handleResult(CommandResult result) {
        if (result.context() == null) return;
        if (getCmdExecuteLocation().dimension().getWorld().getWorldData().getGameRule(GameRule.SEND_COMMAND_FEEDBACK)) {
            if (result.isSuccess()) {
                Server.getInstance().broadcastCommandOutputs(this, result.context().getOutputs().toArray(TrContainer[]::new));
            } else {
                // 如果报错了就只给自己发消息
                sendCommandOutputs(this, result.context().getOutputs().toArray(TrContainer[]::new));
            }
        }
    }
    
    default boolean isPlayer() {
        return false;
    }

    default EntityPlayer asPlayer() {
        return (EntityPlayer) this;
    }
    
    default boolean isEntity() {
        return false;
    }

    default Entity asEntity() {
        return (Entity) this;
    }
}
