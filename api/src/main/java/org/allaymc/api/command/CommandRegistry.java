package org.allaymc.api.command;

import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public abstract class CommandRegistry extends SimpleMappedRegistry<String, Command> {
    protected <I> CommandRegistry(I input, RegistryLoader<I, Map<String, Command>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Method to register a command.
     *
     * @param command The command to register
     */
    public abstract void register(Command command);

    /**
     * Method to unregister a command.
     *
     * @param name The name of the command to unregister
     * @return The unregistered command
     */
    public abstract Command unregister(String name);

    /**
     * Method to execute a command.
     *
     * @param sender The command sender
     * @param cmd    The command, without the slash (/)
     * @return The command execution result
     */
    public abstract CommandResult execute(CommandSender sender, String cmd);

    /**
     * Finds a command by its name or alias.
     * <p>
     * This method first searches for a command matching the given name or alias directly in the registry. If
     * no match is found, it then searches through the aliases of all registered commands to find a match.
     *
     * @param nameOrAlias the name or alias of the command to find
     * @return the command matching the given name or alias, or {@code null} if no command is found
     */
    public Command findCommand(String nameOrAlias) {
        var result = this.get(nameOrAlias);
        if (result != null) {
            return result;
        }

        return this.getContent().values().stream()
                .filter(command -> command.getAliases().contains(nameOrAlias))
                .findFirst().orElse(null);
    }
}
