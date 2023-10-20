package cn.allay.api.command.data;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@ToString
@Slf4j
public class CommandPath {

    private final String name;
    private final List<String> aliases;

    public CommandPath(@NotNull String... nameAndAliases) {
        if (nameAndAliases.length == 0) throw new IllegalArgumentException("CommandPath input cannot be empty!");

        this.name = nameAndAliases[0];

        List<String> aliases = new ArrayList<>(List.of(nameAndAliases));
        aliases.remove(0);
        this.aliases = aliases;
    }

    public CommandEnumData getNetworkAliases() {
        Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
        List<String> copy = new ArrayList<>(this.aliases);
        copy.addFirst(this.name);
        for (var alias : copy) values.put(alias, Collections.emptySet());
        return new CommandEnumData(this.name + "Aliases", values, false);
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
