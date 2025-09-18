package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MayContainTrKey;
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
    private CommandEnumData networkAliasesData;
    private CommandOverloadData[] networkOverloadsData;

    public BaseCommand(String name, @MayContainTrKey String description, List<Permission> permissions) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.permissions = Objects.requireNonNull(permissions);
        // We just add NOT_CHEAT flag to all commands as the available_commands_packet is unique to each player
        flags.add(NOT_CHEAT);
    }

    @Override
    public CommandData buildNetworkDataFor(EntityPlayer player) {
        if (!networkDataPrepared) {
            prepareNetworkData();
        }

        return new CommandData(
                name,
                I18n.get().tr(player.getLoginData().getLangCode(), description),
                flags, CommandPermission.ANY,
                networkAliasesData,
                List.of(),
                networkOverloadsData
        );
    }

    private void prepareNetworkData() {
        // Aliases
        if (!aliases.isEmpty()) {
            Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
            aliases.forEach(alias -> values.put(alias, Collections.emptySet()));
            values.put(name, Collections.emptySet());
            networkAliasesData = new CommandEnumData(name + "CommandAliases", values, false);
        }

        // Overloads
        if (!overloads.isEmpty()) {
            networkOverloadsData = overloads.stream()
                    .map(overload -> new CommandOverloadData(false, overload))
                    .toArray(CommandOverloadData[]::new);
        } else {
            networkOverloadsData = new CommandOverloadData[]{new CommandOverloadData(false, new CommandParamData[0])};
        }

        networkDataPrepared = true;
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

    @UnmodifiableView
    @Override
    public Set<CommandData.Flag> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    @Override
    public List<String> getCommandFormatTips() {
        return overloads.stream().map(params -> {
            var builder = new StringBuilder("- /").append(name);
            for (var param : params) {
                if (param.getEnumData() == null) {
                    builder.append(param.isOptional() ? " [" : " <")
                            .append(param.getName())
                            .append(": ")
                            .append(param.getType().getParamType().name().toLowerCase(Locale.ENGLISH))
                            .append(param.isOptional() ? "]" : ">");
                } else {
                    var enums = new ArrayList<>(param.getEnumData().getValues().keySet());
                    if (enums.size() == 1 && !param.isOptional()) {
                        builder.append(" ").append(enums.getFirst());
                    } else {
                        builder.append(param.isOptional() ? " [" : " <")
                                .append(
                                        enums.isEmpty()
                                                ? param.getName() + ": " + param.getEnumData().getName()
                                                : String.join("|", enums.subList(0, Math.min(enums.size(), 10)))
                                )
                                .append(enums.size() > 10 ? "|..." : "")
                                .append(param.isOptional() ? "]" : ">");
                    }
                }
            }

            return builder.toString();
        }).toList();
    }
}
