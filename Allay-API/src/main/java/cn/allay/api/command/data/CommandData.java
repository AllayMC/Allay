package cn.allay.api.command.data;

import cn.allay.api.command.annotation.Optional;
import cn.allay.api.utils.ReflectionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOverloadData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Method;
import java.util.*;

@Getter
@Setter
@ToString
public class CommandData {

    private final CommandPath path;
    private final Object commandInstance; // Needed for reflection, see CommandHandlerImpl#dispatch

    private final Map<String[], Method> methods = new HashMap<>();
    private final Map<String[], Method> overloads = new HashMap<>();

    private @NotNull String description = "";
    private @Nullable String usage;
    private @Nullable String permission;

    public CommandData(@NotNull CommandPath path, Object commandInstance) {
        this.path = path;
        this.commandInstance = commandInstance;
    }

    public void addDefaultExecutor(@NotNull String[] commandNameOrAliases, @NotNull Method method) {
        this.methods.put(commandNameOrAliases, method);
    }

    public void addOverload(@NotNull String[] commandNameOrAliases, @NotNull Method method) {
        this.overloads.put(commandNameOrAliases, method);
    }

    public @Unmodifiable Map<String[], Method> getMethods() {
        return Collections.unmodifiableMap(this.methods);
    }

    public @Unmodifiable Map<String[], Method> getOverloads() {
        return Collections.unmodifiableMap(this.overloads);
    }

    public CommandOverloadData[] getNetworkOverloads() {
        Set<CommandOverloadData> overloads = new HashSet<>();

        overloads.add(getOverloadParams(this.methods));
        overloads.add(getOverloadParams(this.overloads));

        return overloads.toArray(new CommandOverloadData[0]);
    }

    private CommandOverloadData getOverloadParams(Map<String[], Method> methods) {
        Set<CommandParamData> params = new HashSet<>();
        methods.forEach((names, method) -> {
            var paramNames = ReflectionUtils.getParametersName(method);
            for (int i = 0; i < method.getParameterTypes().length; i++) {
                var param = method.getParameterTypes()[i];
                var paramData = new CommandParamData();
                paramData.setName(paramNames.get(i));
                paramData.setOptional(param.isAnnotationPresent(Optional.class));
                paramData.setType(CommandParam.TEXT); // TODO: check type
                params.add(paramData);
            }
        });

        return new CommandOverloadData(false, params.toArray(new CommandParamData[0]));
    }
}
