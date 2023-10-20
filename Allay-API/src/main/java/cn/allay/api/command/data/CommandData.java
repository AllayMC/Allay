package cn.allay.api.command.data;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class CommandData {

    private final CommandPath path;

    private final Map<String[], Method> methods = new HashMap<>();
    private final Map<String[], Method> overloads = new HashMap<>();

    private @NotNull String description = "";
    private @Nullable String usage;
    private @Nullable String permission;

    public CommandData(@NotNull CommandPath path) {
        this.path = path;
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
        // todo
        var data = new CommandParamData();
        data.setName("args");
        data.setOptional(true);
        data.setType(CommandParam.TEXT);
        return new CommandOverloadData[]{
                new CommandOverloadData(false, new CommandParamData[]{data})
        };
    }
}
