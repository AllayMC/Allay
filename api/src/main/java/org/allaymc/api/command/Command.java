package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.MayContainTrKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@code Command} represents a named command with descriptive metadata,
 * permissions, aliases, and a parsing tree. The constructor creates the tree
 * and invokes {@code prepareCommandTree} immediately; subclasses configure
 * argument paths and executors there before registration makes the command
 * available.
 *
 * @author daoge_cmd
 */
public abstract class Command {

    /**
     * Registered command name used for lookup and syntax feedback.
     */
    @Getter
    protected final String name;
    /**
     * Command description, which may contain a translation key.
     */
    @Getter
    @MayContainTrKey
    protected final String description;
    /**
     * {@code permissions} contains command-wide requirements checked by the
     * registry. {@code getPermissions()} exposes the mutable list, and new
     * execution contexts copy its current entries for feedback filtering.
     */
    @Getter
    protected final List<String> permissions;
    /**
     * {@code aliases} contains alternate lookup names. {@code getAliases()}
     * exposes this mutable list, which {@code CommandRegistry.findCommand}
     * searches after direct name lookup fails.
     */
    @Getter
    protected final List<String> aliases;
    /**
     * Argument tree configured during construction and reused for executions.
     */
    @Getter
    protected final CommandTree commandTree;

    /**
     * Creates a command, initializes its mutable metadata lists, and prepares
     * its argument tree. {@code name} and {@code description} must be non-null;
     * {@code permission == null} creates no initial permission requirement. The
     * constructor invokes the overridable {@code prepareCommandTree} before
     * subclass construction finishes, so that callback must not depend on
     * subclass field initialization.
     */
    public Command(String name, @MayContainTrKey String description, String permission) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.permissions = new ArrayList<>();
        if (permission != null) {
            this.permissions.add(permission);
        }
        this.aliases = new ArrayList<>();
        this.commandTree = CommandTree.create(this);
        prepareCommandTree(this.commandTree);
    }

    /**
     * Parses the supplied argument array and executes the selected tree path.
     * {@code args} excludes the command name; the tree owns argument
     * validation, and this entry point does not perform the registry's
     * command-wide permission check or feedback broadcast.
     */
    public CommandResult execute(CommandSender sender, String[] args) {
        return commandTree.parse(sender, args);
    }

    /**
     * Configures argument paths and executors on this command's newly created
     * tree. The base constructor calls this callback before subclass
     * initialization completes, so implementations must build their tree
     * without reading fields initialized later by the subclass.
     */
    public abstract void prepareCommandTree(CommandTree tree);

    /**
     * Returns whether this command is omitted from client command
     * advertisements. The default is {@code false}; implementations returning
     * {@code true} still remain available through server command lookup and
     * execution.
     */
    public boolean isServerSideOnly() {
        return false;
    }

    /**
     * Returns whether client command metadata marks this as a debug command.
     * The default is {@code false}; an override changes the advertised debug
     * flag without changing the command's parsing tree or permissions.
     */
    public boolean isDebugCommand() {
        return false;
    }
}
