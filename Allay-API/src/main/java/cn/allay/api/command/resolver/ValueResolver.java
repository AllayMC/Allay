package cn.allay.api.command.resolver;

import cn.allay.api.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface ValueResolver<T> {

    T resolve(@NotNull CommandSender sender, @NotNull String input) throws Throwable;
}
