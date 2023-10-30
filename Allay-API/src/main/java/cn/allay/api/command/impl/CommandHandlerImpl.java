package cn.allay.api.command.impl;

import cn.allay.api.command.CommandHandler;
import cn.allay.api.command.CommandSender;
import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.annotation.DefaultExecuteFor;
import cn.allay.api.command.annotation.Description;
import cn.allay.api.command.annotation.Optional;
import cn.allay.api.command.annotation.Overload;
import cn.allay.api.command.data.CommandData;
import cn.allay.api.command.data.CommandPath;
import cn.allay.api.command.exception.InvalidValueResolverException;
import cn.allay.api.command.resolver.ValueResolver;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;

@Slf4j
public abstract class CommandHandlerImpl implements CommandHandler {

    private final Map<CommandPath, CommandData> commands = new HashMap<>();
    private final Map<Class<?>, ValueResolver<?>> resolvers = new HashMap<>();

    protected CommandHandlerImpl() {
        this.registerValueResolver(GameType.class, (sender, input) -> GameType.valueOf(input.toUpperCase()));
        this.registerValueResolver(String.class, (sender, input) -> input);
        this.registerValueResolver(int.class, (sender, input) -> number(Integer::parseInt, input));
        this.registerValueResolver(long.class, (sender, input) -> number(Long::parseLong, input));
        this.registerValueResolver(double.class, (sender, input) -> number(Double::parseDouble, input));
        this.registerValueResolver(float.class, (sender, input) -> number(Float::parseFloat, input));
        this.registerValueResolver(short.class, (sender, input) -> number(Short::parseShort, input));
        this.registerValueResolver(byte.class, (sender, input) -> number(Byte::parseByte, input));
        this.registerValueResolver(boolean.class, (sender, input) -> Boolean.parseBoolean(input));
        this.registerValueResolver(char.class, (sender, input) -> input.charAt(0));
    }

    private <T> T number(Function<String, T> parser, String input) {
        try {
            return parser.apply(input);
        } catch (NumberFormatException exception) {
            throw new InvalidValueResolverException(exception);
        }
    }

    @Override
    public <T> void registerValueResolver(@NotNull Class<T> type, @NotNull ValueResolver<T> resolver) {
        this.resolvers.put(type, resolver);
    }

    @Override
    public void register(@NotNull Object... commands) {
        for (var command : commands) {
            var clazz = command.getClass();

            if (!clazz.isAnnotationPresent(Command.class))
                throw new IllegalArgumentException("The @Command annotation is required for command class!");

            var path = new CommandPath(clazz.getAnnotation(Command.class).value());
            var data = new CommandData(path, command);

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
        log.info("Command dispatched: " + command);
        String[] args = command.split(" ");
        String name = args[0];
        args = Arrays.copyOfRange(args, 1, args.length);
        CommandData commandData = null;
        for(CommandPath path : this.commands.keySet()) {
            if(name.equals(path.getName())) {
                commandData = this.commands.get(path);
                break;
            }
            for(String alias : path.getAliases()) {
                if(alias.equals(name))
                    // We don't break here since name should take priority over aliases?
                    commandData = this.commands.get(path);
            }
        }

        if(commandData == null) {
            sender.reply("§4The command `" + name + "` does not exist!");
            return;
        }

        // Now we need to figure out what method to run
        LinkedList<Method> possibleMethods = new LinkedList<>();
        for(String[] nameAliasArray : commandData.getMethods().keySet()) {
            boolean found = false;
            for(String value : nameAliasArray) {
                if(value.equals(name)) {
                    found = true;
                    break;
                }
            }
            if(found) {
                possibleMethods.add(commandData.getMethods().get(nameAliasArray));
            }
        }

        if(possibleMethods.isEmpty()) {
            // Empty command class
            sender.reply("§4Found command, could not find an executor for it!");
            return;
        }

        ListIterator<Method> iterator = possibleMethods.listIterator();
        while(iterator.hasNext()) {
            Method method = iterator.next();
            // The -1 is for the first parameter CommandSender
            if(args.length > method.getParameterCount() - 1) {
                sender.reply("You provided too many arguments!");
                return;
            }

            // If a parameter is optional, we should set the extra values passed in to null
            // Reflection only allows the exact number of arguments to invoke a method
            int optionalParams = 0;
            for(int i = 0; i < method.getParameterCount(); i++) {
                optionalParams += (method.getParameters()[i].isAnnotationPresent(Optional.class) ? 1 : 0);
            }

            ArrayList<Object> parametersRun = new ArrayList<>();
            parametersRun.add(sender);
            int argIndex = 0;
            boolean works = true;
            // Start at 1 to skip the first parameter CommandSender
            for(int i = 1; i < method.getParameterCount(); i++) {
                Parameter parameter = method.getParameters()[i];
                // If the parameter is optional and there are not enough arguments provided by the player, stop the loop
                // Values will be populated later on
                if(parameter.isAnnotationPresent(Optional.class) && argIndex >= args.length) {
                    break;
                } else if(args.length <= argIndex) {
                    // TODO: Usage message?
                    sender.reply("§4Not enough parameters for that command!");
                    return;
                }

                ValueResolver<?> resolver = this.resolvers.get(parameter.getType());
                if(resolver == null) {
                    sender.reply("No existing revolver for the " + parameter.getType().getName() + " class!");
                    return;
                }
                try {
                    parametersRun.add(resolver.resolve(sender, args[argIndex]));
                } catch (Exception e) {
                    // I think this can be improved, though it works for now and I don't see a problem with it yet
                    // Resolver wouldn't work due to user input, so this method isn't the targeted one
                    iterator.remove();
                    works = false;
                    break;
                }
                argIndex++;
            }
            if(!works)
                continue;

            int neededParams = method.getParameterCount();
            if(neededParams > parametersRun.size()) {
                for(int i = 0; i < optionalParams; i++) {
                    parametersRun.add(null);
                }
                if(neededParams > parametersRun.size()) {
                    sender.reply("You need to supply more parameters to the command!");
                    return;
                }
            }

            try {
                method.invoke(commandData.getCommandInstance(), parametersRun.toArray());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                sender.reply("§4Something went wrong invoking the command! " + e.getMessage());
            }
        }

        sender.reply("§4Something went wrong executing that command! No methods were found or called based on user input");
    }

    @Override
    public @Unmodifiable @NotNull Map<CommandPath, CommandData> getCommands() {
        return this.commands;
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
