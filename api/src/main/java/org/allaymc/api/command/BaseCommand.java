package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.permission.Permission;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

import static org.cloudburstmc.protocol.bedrock.data.command.CommandData.Flag.NOT_CHEAT;

/**
 * @author daoge_cmd
 */
public abstract class BaseCommand implements Command {

    @Getter
    protected final String name;
    @Getter
    @MayContainTrKey
    protected final String description;
    @Getter
    protected final List<Permission> permissions;
    protected final Set<CommandData.Flag> flags = new HashSet<>();
    protected final List<String> aliases = new ArrayList<>();
    protected final List<CommandParamData[]> overloads = new ArrayList<>();

    private boolean networkDataPrepared = false;

    private CommandEnumData networkAliasesData = null;
    private CommandOverloadData[] networkOverloadsData = null;

    public BaseCommand(String name, @MayContainTrKey String description) {
        this(name, description, List.of(Command.createPermissionForCommand(name)));
    }

    public BaseCommand(String name, @MayContainTrKey String description, List<Permission> permissions) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.permissions = Objects.requireNonNull(permissions);
        // We just add NOT_CHEAT flag to all commands as the available_commands_packet is unique to each player
        flags.add(NOT_CHEAT);
    }

    @UnmodifiableView
    @Override
    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    @UnmodifiableView
    @Override
    public List<CommandParamData[]> getCommandOverloads() {
        return Collections.unmodifiableList(overloads);
    }

    @Override
    public Set<CommandData.Flag> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    private void prepareNetworkData() {
        // Aliases
        if (!aliases.isEmpty()) {
            Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
            for (var alias : aliases) values.put(alias, Collections.emptySet());
            values.put(name, Collections.emptySet());
            networkAliasesData = new CommandEnumData(name + "CommandAliases", values, false);
        }

        // Overloads
        if (!overloads.isEmpty()) {
            networkOverloadsData = new CommandOverloadData[overloads.size()];
            for (int index = 0; index < overloads.size(); index++) {
                var overload = overloads.get(index);
                networkOverloadsData[index] = new CommandOverloadData(false, overload);
            }
        } else {
            networkOverloadsData = new CommandOverloadData[]{new CommandOverloadData(false, new CommandParamData[0])};
        }
        networkDataPrepared = true;
    }

    @Override
    public CommandData buildNetworkDataFor(EntityPlayer player) {
        if (!networkDataPrepared) {
            prepareNetworkData();
        }
        return new CommandData(name, I18n.get().tr(player.getLoginData().getLangCode(), description), flags, CommandPermission.ANY, networkAliasesData, List.of(), networkOverloadsData);
    }

    public List<String> getCommandFormatTips() {
        return overloads.stream().map(commandParameters -> {
            var builder = new StringBuilder();
            builder.append("- /").append(this.getName());
            for (var commandParameter : commandParameters) {
                if (commandParameter.getEnumData() == null) {
                    builder.append(!commandParameter.isOptional() ? " <" : " [")
                            .append(commandParameter.getName())
                            .append(": ")
                            .append(commandParameter.getType().getParamType().name().toLowerCase(Locale.ENGLISH))
                            .append(!commandParameter.isOptional() ? ">" : "]");
                } else {
                    var enums = commandParameter.getEnumData().getValues().keySet().stream().toList();
                    if (enums.size() == 1 && !commandParameter.isOptional()) {
                        builder.append(" ").append(enums.getFirst());
                    } else {
                        builder.append(!commandParameter.isOptional() ? " <" : " [")
                                .append(
                                        enums.isEmpty() ?
                                                commandParameter.getName() + ": " + commandParameter.getEnumData().getName() :
                                                String.join("|", enums.subList(0, Math.min(commandParameter.getEnumData().getValues().size(), 10)))
                                )
                                .append(commandParameter.getEnumData().getValues().size() > 10 ? "|..." : "")
                                .append(!commandParameter.isOptional() ? ">" : "]");
                    }
                }
            }
            return builder.toString();
        }).toList();
    }
}
