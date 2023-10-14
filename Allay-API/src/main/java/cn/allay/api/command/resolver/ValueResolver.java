package cn.allay.api.command.resolver;

import cn.allay.api.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface ValueResolver<T> {

    T resolve(@NotNull @Unmodifiable List<String> input, @NotNull CommandSender sender) throws Throwable;

}
