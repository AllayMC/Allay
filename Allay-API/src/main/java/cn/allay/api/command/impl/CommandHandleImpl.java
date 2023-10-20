package cn.allay.api.command.impl;

import cn.allay.api.command.CommandHandler;
import cn.allay.api.command.CommandSender;
import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.annotation.DefaultExecuteFor;
import cn.allay.api.command.annotation.Description;
import cn.allay.api.command.annotation.Overload;
import cn.allay.api.command.data.CommandData;
import cn.allay.api.command.data.CommandPath;
import cn.allay.api.command.resolver.ValueResolver;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class CommandHandleImpl implements CommandHandler {

    private final Map<CommandPath, CommandData> commands = new HashMap<>();

    protected CommandHandleImpl() {
        this.registerValueResolver(int.class, (sender, input) -> Integer.parseInt(input));
        this.registerValueResolver(double.class, (sender, input) -> Double.parseDouble(input));
        this.registerValueResolver(float.class, (sender, input) -> Float.parseFloat(input));
        this.registerValueResolver(long.class, (sender, input) -> Long.parseLong(input));
        this.registerValueResolver(short.class, (sender, input) -> Short.parseShort(input));
        this.registerValueResolver(byte.class, (sender, input) -> Byte.parseByte(input));
        this.registerValueResolver(boolean.class, (sender, input) -> Boolean.parseBoolean(input));
        this.registerValueResolver(char.class, (sender, input) -> input.charAt(0));
        this.registerValueResolver(String.class, (sender, input) -> input);
    }

    @Override
    public <T> void registerValueResolver(@NotNull Class<T> type, @NotNull ValueResolver<T> resolver) {
        // TODO
    }

    @Override
    public void register(@NotNull Object... commands) {
        for (var command : commands) {
            var clazz = command.getClass();

            if (!clazz.isAnnotationPresent(Command.class))
                throw new IllegalArgumentException("The @Command annotation is required for command class!");

            var path = new CommandPath(clazz.getAnnotation(Command.class).value());
            var data = new CommandData(path);

            if (clazz.isAnnotationPresent(Description.class)) {
                data.setDescription(clazz.getAnnotation(Description.class).value());
            }

            // it doesn't look very good, so it's worth refactoring
            for (var method : clazz.getMethods()) {
                if (method.isAnnotationPresent(DefaultExecuteFor.class)) {
                    var value = method.getAnnotation(DefaultExecuteFor.class).value();
                    if (value.length == 0) {
                        var nameAndAliases = path.getAliases();
                        nameAndAliases.addFirst(path.getName());
                        value = nameAndAliases.toArray(new String[0]);
                    }

                    data.addDefaultExecutor(value, method);
                } else if (method.isAnnotationPresent(Overload.class)) {
                    var value = method.getAnnotation(Overload.class).value();
                    data.addOverload(value, method);
                }
            }

            this.commands.put(path, data);
        }
    }

    @Override
    public void dispatch(@NotNull CommandSender sender, @NotNull String command) {
        log.info(String.valueOf(commands));
    }

    @Override
    public @NotNull AvailableCommandsPacket createPacketFor(@NotNull EntityPlayer player) {
        var packet = new AvailableCommandsPacket();

        this.commands.forEach((path, data) ->
                packet.getCommands().add(new org.cloudburstmc.protocol.bedrock.data.command.CommandData(
                        data.getPath().getName(),
                        data.getDescription(),
                        Collections.emptySet(),
                        CommandPermission.ANY,
                        path.getNetworkAliases(),
                        Collections.emptyList(),
                        data.getNetworkOverloads()
                )));

        return packet;
    }
}
