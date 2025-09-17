package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.Permissible;

/**
 * @author daoge_cmd
 */
public interface CommandSender extends TextReceiver, Permissible {

    /**
     * Get the name of the command sender.
     *
     * @return the name of the command sender
     */
    String getCommandSenderName();

    /**
     * Get the location where the command was executed.
     *
     * @return the location where the command was executed
     */
    Location3dc getCommandExecuteLocation();

    /**
     * Checks if the sender is a player.
     *
     * @return whether the sender is a player
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
     * @return whether the sender is an entity
     */
    default boolean isEntity() {
        return false;
    }

    /**
     * Return the actual entity that executed the command if the sender is an entity.
     *
     * @return the actual entity that executed the command
     */
    default Entity asEntity() {
        return null;
    }

    /**
     * Return the actual player that executed the command if the sender is a player.
     *
     * @return the actual player that executed the command
     */
    default EntityPlayer asPlayer() {
        return null;
    }
}
