package org.allaymc.api.command;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

import static org.cloudburstmc.protocol.bedrock.data.command.CommandData.Flag.NOT_CHEAT;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public abstract class BaseCommand implements Command {
    @Getter
    protected final String name;
    @Getter
    @MayContainTrKey
    protected final String description;
    @Getter
    protected final List<String> permissions;
    protected final Set<CommandData.Flag> flags = new HashSet<>();
    protected final List<String> aliases = new ArrayList<>();
    protected final List<CommandParamData[]> overloads = new ArrayList<>();

    public BaseCommand(String name, @MayContainTrKey String description) {
        this(name, description, Lists.newArrayList(COMMAND_PERM_PREFIX + name));
    }

    public BaseCommand(String name, @MayContainTrKey String description, List<String> permissions) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(permissions);
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        // We just add NOT_CHEAT flag to all commands as the available_commands_packet is unique to each player
        flags.add(NOT_CHEAT);
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

    private boolean networkDataPrepared = false;
    private CommandEnumData networkAliasesData = null;
    private CommandOverloadData[] networkOverloadsData = null;

    private void prepareNetworkData() {
        // Aliases
        // TODO: seems that the vanilla aliases implementation causes a lot of problems
        if (!aliases.isEmpty()) {
            var map = new HashMap<String, Set<CommandEnumConstraint>>();
            map.put(name, Collections.emptySet());
            for (var alias : aliases) {
                map.put(alias, Collections.emptySet());
            }
            networkAliasesData = new CommandEnumData(name + "CommandAliases", map, false);
        }

        // Overloads
        if (!overloads.isEmpty()) {
            networkOverloadsData = new CommandOverloadData[overloads.size()];
            for (int index = 0; index < overloads.size(); index++) {
                var overload = overloads.get(index);
                networkOverloadsData[index] = new CommandOverloadData(false, overload);
            }
        }
        networkDataPrepared = true;
    }

    @Override
    public CommandData buildNetworkDataFor(EntityPlayer player) {
        if (!networkDataPrepared) {
            prepareNetworkData();
        }
        return new CommandData(name, I18n.get().tr(player.getLangCode(), description), flags, CommandPermission.ANY, networkAliasesData, List.of(), networkOverloadsData);
    }
}
