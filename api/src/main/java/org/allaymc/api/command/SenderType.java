package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;

import java.util.function.Function;

/**
 * @param validator     Validator function to check if the sender is of the correct type
 * @param caster        Caster function to cast the sender to the correct type
 * @param errorMsg      Error message to display if the sender is not of the correct type
 * @param <SENDER_TYPE> The type of command sender
 * @author daoge_cmd
 */
public record SenderType<SENDER_TYPE extends CommandSender>(
        Function<CommandSender, Boolean> validator,
        Function<CommandSender, SENDER_TYPE> caster,
        @MayContainTrKey String errorMsg
) {

    /**
     * Represents any type of command sender.
     */
    public static final SenderType<CommandSender> ANY = new SenderType<>(sender -> true, cs -> cs, ""); // Can't be a wrong executor

    /**
     * Represents a server command sender.
     */
    public static final SenderType<Server> SERVER = new SenderType<>(Server.class::isInstance, Server.class::cast, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTSERVER);

    /**
     * Represents a player command sender.
     */
    public static final SenderType<EntityPlayer> PLAYER = new SenderType<>(CommandSender::isPlayer, CommandSender::asPlayer, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER);

    /**
     * Represents a player command sender, where the player must be an actual player instead of a fake player.
     */
    public static final SenderType<EntityPlayer> ACTUAL_PLAYER = new SenderType<>(
            sender -> sender.isPlayer() && sender.asPlayer().isActualPlayer(), CommandSender::asPlayer, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER);

    /**
     * Represents an entity command sender.
     */
    public static final SenderType<Entity> ENTITY = new SenderType<>(CommandSender::isEntity, CommandSender::asEntity, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTENTITY);

    /**
     * Validates if the sender is of the correct type.
     *
     * @param sender The command sender to validate
     * @return Whether the sender is of the correct type
     */
    public boolean validate(CommandSender sender) {
        return validator.apply(sender);
    }
}
