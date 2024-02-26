package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

import java.util.function.Function;

public record SenderType<SENDER_TYPE extends CommandSender>(
        Function<CommandSender, Boolean> validator,
        Function<CommandSender, SENDER_TYPE> caster,
        @MayContainTrKey String errorMsg
) {

    public static SenderType<CommandSender> ANY = new SenderType<>(sender -> true, cs -> cs, ""); // Can't be a wrong executor
    public static SenderType<Server> SERVER = new SenderType<>(Server.class::isInstance, Server.class::cast, TrKeys.A_COMMAND_GENERIC_SENDER_NOTSERVER);
    public static SenderType<EntityPlayer> PLAYER = new SenderType<>(CommandSender::isPlayer, CommandSender::asPlayer, TrKeys.A_COMMAND_GENERIC_SENDER_NOTPLAYER);
    // TODO: entity sender type
     public static SenderType<Entity> ENTITY = new SenderType<>(CommandSender::isEntity, CommandSender::asEntity, TrKeys.A_COMMAND_GENERIC_SENDER_NOTENTITY);

    public boolean validate(CommandSender sender) {
        return validator.apply(sender);
    }
}
