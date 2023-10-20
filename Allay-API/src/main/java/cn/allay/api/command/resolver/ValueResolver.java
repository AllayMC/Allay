package cn.allay.api.command.resolver;

import cn.allay.api.command.CommandSender;
import cn.allay.api.command.exception.InvalidValueResolverException;
import org.jetbrains.annotations.NotNull;

public interface ValueResolver<T> {

    T resolve(@NotNull CommandSender sender, @NotNull String input) throws InvalidValueResolverException;
}
