package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

import static org.allaymc.api.perm.DefaultPermissions.*;

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
    @Getter
    protected String permission;
    protected Set<CommandData.Flag> flags = new HashSet<>();
    protected List<String> aliases = new ArrayList<>();
    protected List<CommandParamData[]> overloads = new ArrayList<>();

    public BaseCommand(String name, @MayContainTrKey String description, String permission) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(permission);
        this.name = name;
        this.description = description;
        this.permission = permission;
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
        CommandEnumData networkAliasesData = null;
        if (!aliases.isEmpty()) {
            var map = new HashMap<String, Set<CommandEnumConstraint>>();
            for (var alias : aliases) {
                map.put(alias, Set.of());
            }
            networkAliasesData = new CommandEnumData("aliases", map, false);
        }
        CommandOverloadData[] networkOverloadsData = new CommandOverloadData[overloads.size()];
        for (int index = 0; index < overloads.size(); index++) {
            var overload = overloads.get(index);
            networkOverloadsData[index] = new CommandOverloadData(false, overload);
        }
        var perm = MEMBER.hasPerm(permission) ? CommandPermission.ANY : CommandPermission.ADMIN;
        return new CommandData(name, I18n.get().tr(description), flags, perm, networkAliasesData, List.of(), networkOverloadsData);
    }
}
