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
 * @author daoge_cmd
 */
public interface CommandSender extends TextReceiver, Permissible {

    String getCommandSenderName();

    CommandOriginData getCommandOriginData();

    Location3fc getCmdExecuteLocation();

    default void handleResult(CommandResult result) {
        if (result.context() == null) return;
        if (getCmdExecuteLocation().dimension().getWorld().getWorldData().getGameRule(GameRule.SEND_COMMAND_FEEDBACK)) {
            var status = result.status();
            var outputs = result.context().getOutputs().toArray(TrContainer[]::new);
            if (result.isSuccess()) {
                Server.getInstance().broadcastCommandOutputs(this, status, outputs);
            } else {
                // If there is an error, only send message to oneself
                sendCommandOutputs(this, status, outputs);
            }
        }
    }

    /**
     * @return Whether the sender is a player
     */
    default boolean isPlayer() {
        return false;
    }

    /**
     * Please use this method to check whether the sender is an entity instead of using code {@code "xxx instanceof Entity"}.
     * Because the sender may not be an instance of {@code "Entity"} but is executing commands as an entity (e.g., {@code "ExecutorCommandSender"}).
     *
     * @return Whether the sender is an entity
     */
    default boolean isEntity() {
        return false;
    }

    /**
     * If the sender is an entity, returns the entity executing the command.
     *
     * @return Entity instance
     */
    default Entity asEntity() {
        return null;
    }

    /**
     * If the sender is a player, returns the player executing the command.
     *
     * @return Player instance
     */
    default EntityPlayer asPlayer() {
        return null;
    }
}
