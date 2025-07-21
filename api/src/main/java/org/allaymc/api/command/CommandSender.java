package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.Permissible;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author daoge_cmd
 */
public interface CommandSender extends TextReceiver, Permissible {

    /**
     * Get the name of the command sender.
     *
     * @return The name of the command sender.
     */
    String getCommandSenderName();

    /**
     * Get the command origin data of the command sender.
     *
     * @return The command origin data of the command sender.
     */
    CommandOriginData getCommandOriginData();

    /**
     * Get the location where the command was executed.
     *
     * @return The location where the command was executed.
     */
    Location3dc getCommandExecuteLocation();

    /**
     * Handle the result of the command execution.
     *
     * @param result The result of the command execution.
     */
    @ApiStatus.OverrideOnly
    default void handleResult(CommandResult result) {
        if (result.context() == null) return;
        if (!getCommandExecuteLocation().dimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK)) {
            return;
        }

        var status = result.status();
        var outputs = result.context().getOutputs().toArray(TrContainer[]::new);
        if (result.isSuccess()) {
            Server.getInstance().broadcastCommandOutputs(result.context().getSender(), status, outputs);
        } else {
            // If there is an error, only send message to oneself
            sendCommandOutputs(result.context().getSender(), status, outputs);
        }
    }

    /**
     * Checks if the sender is a player.
     *
     * @return Whether the sender is a player.
     */
    default boolean isPlayer() {
        return false;
    }

    /**
     * Checks if the sender is an entity.
     * <p>
     * Please use this method to check whether the sender is an entity instead of using code {@code "xxx instanceof Entity"}.
     * Because the sender may not be an instance of {@code "Entity"} but is executing commands as an entity (e.g., {@code "ExecutorCommandSender"}).
     *
     * @return Whether the sender is an entity.
     */
    default boolean isEntity() {
        return false;
    }

    /**
     * Return the entity executing the command if the sender is an entity.
     *
     * @return Entity instance.
     */
    default Entity asEntity() {
        return null;
    }

    /**
     * Return the player executing the command if the sender is a player.
     *
     * @return Player instance.
     */
    default EntityPlayer asPlayer() {
        return null;
    }
}
