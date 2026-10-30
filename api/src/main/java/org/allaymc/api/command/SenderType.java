package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;

import java.util.function.Function;

/**
 * {@code SenderType} describes an executor capability through validation,
 * conversion, and a rejection message. Command nodes call {@code validator}
 * before applying {@code caster} and use {@code errorMsg} for rejected senders.
 * Custom instances must keep the two functions consistent so accepted senders
 * convert to the executor's expected type.
 *
 * @param validator predicate accepting supported command senders
 * @param caster conversion applied only after successful validation
 * @param errorMsg translation key or message for rejected senders
 * @param <SENDER_TYPE> sender type accepted by the executor
 *
 * @author daoge_cmd
 */
public record SenderType<SENDER_TYPE extends CommandSender>(
        Function<CommandSender, Boolean> validator,
        Function<CommandSender, SENDER_TYPE> caster,
        @MayContainTrKey String errorMsg
) {

    /**
     * {@code ANY} accepts every sender and passes the same object to the
     * executor. Its empty rejection message is unused because validation always
     * succeeds.
     */
    public static final SenderType<CommandSender> ANY = new SenderType<>(sender -> true, cs -> cs, "");

    /**
     * {@code SERVER} accepts senders implementing {@code Server} and casts them
     * directly. Other senders receive the server-only rejection message when a
     * command node validates them.
     */
    public static final SenderType<Server> SERVER = new SenderType<>(Server.class::isInstance, Server.class::cast, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTSERVER);

    /**
     * {@code PLAYER} accepts senders reporting {@code isPlayer} and converts
     * them through {@code asPlayer}. This permits proxy senders to expose their
     * represented player.
     */
    public static final SenderType<EntityPlayer> PLAYER = new SenderType<>(CommandSender::isPlayer, CommandSender::asPlayer, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER);

    /**
     * {@code ACTUAL_PLAYER} additionally requires the represented player's
     * {@code isActualPlayer} check to succeed. A sender reporting player
     * capability must supply a non-null player.
     */
    public static final SenderType<EntityPlayer> ACTUAL_PLAYER = new SenderType<>(
            sender -> sender.isPlayer() && sender.asPlayer().isActualPlayer(), CommandSender::asPlayer, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER);

    /**
     * {@code ENTITY} accepts senders reporting {@code isEntity} and converts
     * them through {@code asEntity}. This permits proxy senders to expose their
     * represented entity.
     */
    public static final SenderType<Entity> ENTITY = new SenderType<>(CommandSender::isEntity, CommandSender::asEntity, TrKeys.ALLAY_COMMAND_GENERIC_SENDER_NOTENTITY);

    /**
     * Returns whether the configured validator accepts the supplied command
     * sender.
     */
    public boolean validate(CommandSender sender) {
        return validator.apply(sender);
    }
}
