package org.allaymc.api.command.tree;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.Collection;
import java.util.List;

/**
 * @author daoge_cmd
 */
public interface CommandContext {

    /**
     * Get the command object.
     *
     * @return the command object
     */
    Command getCommand();

    /**
     * Get the sender of the command.
     *
     * @return the sender of the command
     */
    CommandSender getSender();

    /**
     * Set the sender of the command.
     *
     * @param sender the sender of the command
     */
    void setSender(CommandSender sender);

    /**
     * Get the current argument index.
     *
     * @return the current argument index
     */
    int getCurrentArgIndex();

    /**
     * Get the current result index.
     *
     * @return the current result index
     */
    int getCurrentResultIndex();

    /**
     * Get the total argument count.
     *
     * @return the total argument count
     */
    int getArgCount();

    /**
     * Get the command arguments.
     *
     * @return the command arguments
     */
    String[] getArgs();

    /**
     * Check if there are unhandled arguments.
     *
     * @return {@code true} if there are unhandled arguments, {@code false} otherwise.
     */
    default boolean haveUnhandledArg() {
        return getCurrentArgIndex() < getArgCount();
    }

    /**
     * Get the left argument count.
     *
     * @return the left argument count
     */
    default int getLeftArgCount() {
        return getArgCount() - getCurrentArgIndex();
    }

    /**
     * Pops and returns the next argument in the list.
     *
     * @return the next argument
     */
    String popArg();

    /**
     * Return the argument at the specified index.
     *
     * @param index the index of the argument to retrieve
     *
     * @return the argument at the specified index
     */
    String queryArg(int index);

    /**
     * Return the current argument being processed.
     *
     * @return the current argument
     */
    default String queryArg() {
        return queryArg(getCurrentArgIndex());
    }

    /**
     * Put the result.
     *
     * @param result the result to put
     */
    void putResult(Object result);

    /**
     * Clear the results.
     */
    void clearResults();

    /**
     * Add an output.
     *
     * @param output the output to add
     * @param args   the arguments for the output
     */
    void addOutput(@MayContainTrKey String output, Object... args);

    /**
     * Add an error.
     *
     * @param output the error to add
     * @param args   the arguments for the error
     */
    default void addError(@MayContainTrKey String output, Object... args) {
        addOutput(TextFormat.RED + output, args);
    }

    /**
     * Adds a syntax error message at the current argument index.
     */
    default void addSyntaxError() {
        addSyntaxError(getCurrentArgIndex());
    }

    /**
     * Adds a syntax error message to the output, indicating the argument position
     * where the error occurred.
     *
     * @param errorIndex the index of the argument where the syntax error occurred
     */
    default void addSyntaxError(int errorIndex) {
        var left = new StringBuilder(getCommand().getName());
        var current = "";
        var right = new StringBuilder();
        String[] args = getArgs();
        for (int index = 0; index < args.length; index++) {
            var arg = args[index];
            if (index < errorIndex) {
                left.append(" ").append(arg);
            } else if (index == errorIndex) {
                left.append(" ");
                current = arg;
            } else {
                right.append(" ").append(arg);
            }
        }
        addOutput(TrKeys.MC_COMMANDS_GENERIC_SYNTAX, left.toString(), current, right.toString());
    }

    /**
     * Adds an error message indicating that the command was executed by an incorrect sender type.
     *
     * @param correctSenderType the correct {@link SenderType} required to execute the command
     */
    default void addInvalidExecutorError(SenderType<?> correctSenderType) {
        addOutput(correctSenderType.errorMsg());
    }

    /**
     * Adds an error message indicating that no targets were found for the command.
     */
    default void addNoTargetMatchError() {
        addOutput(TrKeys.MC_COMMANDS_GENERIC_NOTARGETMATCH);
    }

    /**
     * Adds an error message indicating that too many targets were found for the command.
     */
    default void addTooManyTargetsError() {
        addOutput(TrKeys.MC_COMMANDS_GENERIC_TOOMANYTARGETS);
    }

    /**
     * Adds an error message indicating that a player could not be found.
     */
    default void addPlayerNotFoundError() {
        addOutput(TrKeys.MC_COMMANDS_GENERIC_PLAYER_NOTFOUND);
    }

    /**
     * Checks if the specified argument index is valid.
     *
     * @param index the index to check
     *
     * @return {@code true} if the index is valid, {@code false} otherwise.
     */
    default boolean isValidArgIndex(int index) {
        return index >= 0 && index < getArgCount();
    }

    /**
     * Sends a whisper message to a specified player.
     *
     * @param player  the {@link EntityPlayer} to send the message to
     * @param message the message to send
     * @param args    optional format arguments for the message
     */
    default void sendWhisperTo(EntityPlayer player, @MayContainTrKey String message, Object... args) {
        if (player.getDimension().getWorld().getWorldData().getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK)) {
            player.sendTr(message, args);
        }
    }

    /**
     * Return the list of translation containers that represent the output messages.
     *
     * @return the list of {@link TrContainer} objects
     */
    List<TrContainer> getOutputs();

    /**
     * Add a list of translation containers.
     *
     * @param outputs the list of translation containers to add
     */
    void addOutputs(Collection<TrContainer> outputs);

    /**
     * Return a successful command result.
     *
     * @return a {@link CommandResult} representing success
     */
    default CommandResult success() {
        return CommandResult.success(this);
    }

    /**
     * Return a successful command result with a specified status code.
     *
     * @param status the status code
     *
     * @return a {@link CommandResult} representing success with the given status
     */
    default CommandResult success(int status) {
        return new CommandResult(status, this);
    }

    /**
     * Return a failed command result.
     *
     * @return a {@link CommandResult} representing failure
     */
    default CommandResult fail() {
        return CommandResult.fail(this);
    }

    /**
     * Retrieves a result value by its index.
     *
     * @param index the index of the result
     * @param <T>   the type of the result
     *
     * @return the result at the specified index
     */
    <T> T getResult(int index);

    /**
     * Retrieves a result value by its index, or returns a default value if the result is {@code null}.
     *
     * @param index        the index of the result
     * @param defaultValue the default value to return if the result is {@code null}
     * @param <T>          the type of the result
     *
     * @return the result at the specified index, or the default value if the result is {@code null}
     */
    default <T> T getResultOr(int index, T defaultValue) {
        T result = getResult(index);
        return result != null ? result : defaultValue;
    }
}
