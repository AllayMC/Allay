package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public abstract class BaseCommand implements Command {
    @Getter
    protected String name;
    @Getter
    @MayContainTrKey
    protected String description;
    protected Set<CommandData.Flag> flags = new HashSet<>();
    protected List<String> aliases = new ArrayList<>();
    protected List<CommandParamData[]> overloads = new ArrayList<>();

    public BaseCommand(String name, String description) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        this.name = name;
        this.description = description;
    }

    @Override
    public @UnmodifiableView List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    @Override
    public @UnmodifiableView List<CommandParamData[]> getCommandOverloads() {
        return Collections.unmodifiableList(overloads);
    }

    @Override
    public @UnmodifiableView Set<CommandData.Flag> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    @Override
    public CommandData toNetworkData() {
        // TODO: Perm
        var map = new HashMap<String, Set<CommandEnumConstraint>>();
        for (var alias : aliases) {
            map.put(alias, Set.of());
        }
        var networkAliasesData = new CommandEnumData("aliases", map, false);
        CommandOverloadData[] networkOverloadsData = new CommandOverloadData[overloads.size()];
        for (int index = 0; index < overloads.size(); index++) {
            var overload = overloads.get(index);
            networkOverloadsData[index] = new CommandOverloadData(false, overload);
        }
        return new CommandData(name, description, flags, CommandPermission.ANY, networkAliasesData, List.of(), networkOverloadsData);
    }
}
