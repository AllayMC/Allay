package org.allaymc.api.command;

import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Set;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface Command {

    String COMMAND_PERM_PREFIX = "command.";

    String getName();

    @UnmodifiableView
    List<String> getAliases();

    @MayContainTrKey
    String getDescription();

    @UnmodifiableView
    List<CommandParamData[]> getCommandOverloads();

    @UnmodifiableView
    Set<CommandData.Flag> getFlags();

    String getPermission();

    CommandResult execute(CommandSender sender, String[] args);

    CommandData toNetworkData();
}
