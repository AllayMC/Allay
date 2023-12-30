package org.allaymc.api.command;

import org.allaymc.api.i18n.TrKeys;

import java.util.function.Function;

public record SenderType(Function<CommandSender, Boolean> validator, String i18Key) {
    public static SenderType ANY = new SenderType(context -> true, ""); // Can't be a wrong executor
    public static SenderType SERVER = new SenderType(CommandSender::isServer, TrKeys.A_COMMAND_EXECUTOR_NOT_SERVER);
    public static SenderType PLAYER = new SenderType(CommandSender::isPlayer, TrKeys.A_COMMAND_EXECUTOR_NOT_PLAYER);
    public static SenderType ENTITY = new SenderType(CommandSender::isEntity, TrKeys.A_COMMAND_EXECUTOR_NOT_ENTITY);

    public boolean validate(CommandSender sender) {
        return validator.apply(sender);
    }
}
