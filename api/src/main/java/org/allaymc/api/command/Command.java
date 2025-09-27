package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.permission.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
public abstract class Command {

    /**
     * The default prefix for command permissions.
     */
    public static final String DEFAULT_COMMAND_PERMISSION_PREFIX = "command.";

    /**
     * The name of the command.
     */
    @Getter
    protected final String name;
    /**
     * The description of the command.
     */
    @Getter
    @MayContainTrKey
    protected final String description;
    /**
     * The permissions required to execute this command.
     */
    @Getter
    protected final List<Permission> permissions;
    /**
     * The aliases of this command.
     */
    @Getter
    protected final List<String> aliases;
    /**
     * The command tree of this command.
     */
    @Getter
    protected final CommandTree commandTree;

    /**
     * Create a new command with the given name and description. The permission to execute this
     * command is simply "command.&lt;command name&gt".
     *
     * @param name        The name of the command
     * @param description The description of the command
     */
    public Command(String name, @MayContainTrKey String description) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.permissions = new ArrayList<>();
        this.permissions.add(Permission.createForCommand(name, DEFAULT_COMMAND_PERMISSION_PREFIX + name));
        this.aliases = new ArrayList<>();
        this.commandTree = CommandTree.create(this);
        prepareCommandTree(this.commandTree);
    }

    /**
     * Executes this command with the given sender and arguments.
     *
     * @param sender The sender of the command
     * @param args   The arguments provided to the command
     * @return The result of the command execution
     */
    public CommandResult execute(CommandSender sender, String[] args) {
        return commandTree.parse(sender, args);
    }

    /**
     * Prepare the command tree for this command.
     *
     * @param tree The command tree to prepare
     */
    public abstract void prepareCommandTree(CommandTree tree);

    /**
     * Whether this command is only available on the server side. If a command is server side
     * only, this command won't be sent to the client.
     * <p>
     * Some commands like `/help` are server-side only because in client-side these commands are
     * handled by the client itself and will not request the server to handle them. Sending these
     * commands to the client will cause unknown behavior (e.g., crashing the client).
     *
     * @return {@code true} if this command is server side only, otherwise {@code false}
     */
    public boolean isServerSideOnly() {
        return false;
    }

    /**
     * Checks if this command is a debug command. If return {@code true}, the command name
     * will become blue client-side.
     *
     * @return {@code true} if this command is a debug command, otherwise {@code false}
     */
    public boolean isDebugCommand() {
        return false;
    }
}
