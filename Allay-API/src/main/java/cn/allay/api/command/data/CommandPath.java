package cn.allay.api.command.data;

import lombok.Getter;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Getter
@ToString
public class CommandPath {

    private final String name;
    private final String[] aliases;

    public CommandPath(@NotNull String... nameAndAliases) {
        this.name = nameAndAliases[0];

        var extractedAliases = new String[nameAndAliases.length - 1];
        System.arraycopy(nameAndAliases, 1, extractedAliases, 0, nameAndAliases.length - 1);
        this.aliases = extractedAliases;
    }

    public CommandEnumData getNetworkAliases() {
        Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
        for (var alias : aliases) values.put(alias, Collections.emptySet());
        return new CommandEnumData(name + "Aliases", values, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandPath that)) return false;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
