package org.allaymc.api.command;

import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.Map;

/**
 * {@code CommandRegistry} maps primary command names to commands and defines
 * registration and execution entry points. {@code findCommand} gives direct
 * mappings precedence over aliases and scans the current commands only when a
 * direct lookup has no result.
 *
 * @author daoge_cmd
 */
public abstract class CommandRegistry extends SimpleMappedRegistry<String, Command> {
    /**
     * Loads the command map through the supplied registry loader immediately.
     * The inherited registry retains the loader's map, so its mutability and
     * later freezing behavior follow {@code SimpleMappedRegistry}.
     */
    protected <I> CommandRegistry(I input, RegistryLoader<I, Map<String, Command>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Registers a command under its primary name for subsequent lookup and
     * execution. Alias resolution uses the command's alias list separately, so
     * aliases do not require individual map entries.
     */
    public abstract void register(Command command);

    /**
     * Removes the mapping for the supplied primary command name and returns its
     * command. A missing name returns {@code null}; removing an alias requires
     * locating and changing its owning command instead.
     */
    public abstract Command unregister(String name);

    /**
     * Executes command text with the supplied sender as its execution context.
     * {@code cmd} excludes the leading {@code /}; the registry resolves the
     * command, checks command-wide permissions, and handles execution feedback.
     */
    public abstract CommandResult execute(CommandSender sender, String cmd);

    /**
     * Returns the command mapped directly to {@code nameOrAlias}, or searches
     * aliases when no direct mapping exists. A direct name always takes
     * precedence over an alias with the same text. Alias comparison is
     * case-sensitive, and the first matching command in the registry's current
     * iteration order wins; a missing match returns {@code null}.
     */
    public Command findCommand(String nameOrAlias) {
        var result = this.get(nameOrAlias);
        if (result != null) {
            return result;
        }

        // Direct names take precedence; scanning aliases only after a miss keeps an
        // alias from replacing an explicitly registered command with the same name.
        return this.getContent().values().stream()
                .filter(command -> command.getAliases().contains(nameOrAlias))
                .findFirst().orElse(null);
    }
}
