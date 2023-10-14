package cn.allay.api.command.impl;

import cn.allay.api.command.CommandHandler;
import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.data.CommandData;
import cn.allay.api.command.data.CommandPath;
import cn.allay.api.command.resolver.ValueResolver;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandHandleImpl implements CommandHandler {

    private final Map<CommandPath, CommandData> commands = new HashMap<>();

    public CommandHandleImpl() {


    }

    @Override
    public <T> void registerValueResolver(@NotNull Class<T> type, @NotNull ValueResolver<T> resolver) {

    }

    @Override
    public void register(@NotNull Object... commands) {
        for (var command : commands) {
            var cmd = command.getClass().getAnnotation(Command.class);
            var path = new CommandPath(cmd.value());

            // TODO: this.commands.put(path, null);
        }
    }
}
