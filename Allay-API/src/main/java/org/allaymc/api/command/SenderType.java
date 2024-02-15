package org.allaymc.api.command;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

import java.util.function.Function;

public record SenderType(
        Function<CommandSender, Boolean> validator,
        @MayContainTrKey String errorMsg
) {

    public static SenderType ANY = new SenderType(sender -> true, ""); // Can't be a wrong executor
    public static SenderType SERVER = new SenderType(Server.class::isInstance, TrKeys.A_COMMAND_GENERIC_SENDER_NOTSERVER);
    public static SenderType PLAYER = new SenderType(EntityPlayer.class::isInstance, TrKeys.A_COMMAND_GENERIC_SENDER_NOTPLAYER);
    // TODO: entity sender type
    // public static SenderType<Entity> ENTITY = new SenderType(CommandSender::isEntity, TrKeys.A_COMMAND_GENERIC_SENDER_NOTENTITY);

    public boolean validate(CommandSender sender) {
        return validator.apply(sender);
    }
}
