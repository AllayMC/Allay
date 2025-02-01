package org.allaymc.api.command;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface Command {

    /**
     * Prefix for command permissions.
     */
    String COMMAND_PERM_PREFIX = "command.";

    /**
     * Executes this command with the given sender and arguments.
     *
     * @param sender The sender of the command.
     * @param args   The arguments provided to the command.
     *
     * @return The result of the command execution.
     */
    CommandResult execute(CommandSender sender, String[] args);

    /**
     * Builds a network data representation of this command for the given player.
     *
     * @param player The player to build the data for.
     *
     * @return The network data representation of this command.
     */
    CommandData buildNetworkDataFor(EntityPlayer player);

    /**
     * Whether this command is only available on the server side. If a command is server
     * side only, this command won't be encoded and sent to the client.
     * <p>
     * Some commands like `/help` are server-side only because in client-side these commands
     * are handled by the client itself and will not request the server to handle them.
     *
     * @return Whether this command is only available on the server side.
     */
    default boolean isServerSideOnly() {
        return false;
    }

    /**
     * Get the name of this command.
     *
     * @return The name of this command.
     */
    String getName();

    /**
     * Get the aliases of this command.
     *
     * @return The aliases of this command.
     */
    @UnmodifiableView
    List<String> getAliases();

    /**
     * Get the description of this command.
     *
     * @return The description of this command.
     */
    @MayContainTrKey
    String getDescription();

    /**
     * Get the command overloads of this command.
     *
     * @return The command overloads of this command.
     */
    @UnmodifiableView
    List<CommandParamData[]> getCommandOverloads();

    /**
     * Get the flags of this command.
     *
     * @return The flags of this command.
     */
    @UnmodifiableView
    Set<CommandData.Flag> getFlags();

    /**
     * Get the permissions required to execute this command.
     *
     * @return The permissions required to execute this command.
     */
    List<String> getPermissions();

    /**
     * Get this command's overloaded description string
     *
     * @return this command's overloaded description string
     */
    List<String> getCommandFormatTips();
}
