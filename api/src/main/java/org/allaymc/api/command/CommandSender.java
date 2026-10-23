package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.MessageReceiver;
import org.allaymc.api.permission.Permissible;

/**
 * {@code CommandSender} supplies command identity, execution location,
 * permissions, and feedback delivery for an executor. Entity and player
 * identity are explicit capabilities: proxy senders may represent an entity
 * without implementing its type, so callers use the capability checks and
 * conversion methods together.
 *
 * @author daoge_cmd
 */
public interface CommandSender extends MessageReceiver, Permissible {

    /**
     * Returns the sender name used in command feedback and entity-name
     * matching.
     */
    String getCommandSenderName();

    /**
     * Returns the location and dimension used as the command execution origin.
     */
    Location3dc getCommandExecuteLocation();

    /**
     * Returns whether this sender represents a player for command execution.
     * This default implementation returns {@code false}; implementations
     * reporting {@code true} must provide the represented player through
     * {@code asPlayer}.
     */
    default boolean isPlayer() {
        return false;
    }

    /**
     * Returns whether this sender represents an entity for command execution.
     * Callers use this capability instead of {@code instanceof Entity}, because
     * a proxy can represent an entity without implementing {@code Entity}; the
     * default is {@code false}.
     */
    default boolean isEntity() {
        return false;
    }

    /**
     * Returns the represented entity, or {@code null} in this default
     * implementation. Callers check {@code isEntity} first and rely on an
     * implementing sender to return the entity associated with that capability.
     */
    default Entity asEntity() {
        return null;
    }

    /**
     * Returns the represented player, or {@code null} in this default
     * implementation. Callers check {@code isPlayer} first and rely on an
     * implementing sender to return the player associated with that capability.
     */
    default EntityPlayer asPlayer() {
        return null;
    }
}
