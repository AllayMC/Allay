package cn.allay.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandSender {

    @NotNull String getName();

    void reply(@NotNull String message);

    default void error(@NotNull String message) {
        this.reply("§4" + message);
    }

    default @NotNull CommandHandler getCommandHandler() {
        return CommandHandler.getInstance();
    }

    default void dispatch(String command) {
        this.getCommandHandler().dispatch(this, command);
    }

    default <T extends CommandSender> T as(@NotNull Class<T> type) {
        return type.cast(this);
    }
}
