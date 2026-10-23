package org.allaymc.api.command.tree;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.*;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.Collection;
import java.util.List;

/**
 * {@code CommandContext} carries a command invocation's sender, argument
 * cursor, parsed results, permission requirements, and accumulated feedback.
 * Nodes consume input and append typed results, while executors read the
 * results by their insertion index. A redirect can replace the sender and clear
 * parsed results while retaining the original argument array and the current
 * argument cursor.
 *
 * <p> Syntax feedback divides that original input into text before, at, and
 * after a selected argument index. {@code addSyntaxError} preserves the command
 * name and token order in separate translation arguments so the message can
 * emphasize the failing token. Output methods accumulate messages; the registry
 * decides whether to deliver them and which permission-filtered receivers may
 * see a successful command's feedback.
 *
 * @author daoge_cmd
 */
public interface CommandContext {

    /**
     * Returns the command whose tree is processing this invocation.
     */
    Command getCommand();

    /**
     * Returns the sender currently supplying execution identity and location.
     */
    CommandSender getSender();

    /**
     * Replaces the sender used by subsequent parsing and executor operations.
     * Redirect callbacks can use this to change execution identity and origin
     * without replacing arguments or already accumulated outputs.
     */
    void setSender(CommandSender sender);

    /**
     * Returns the zero-based index of the next unconsumed argument token.
     */
    int getCurrentArgIndex();

    /**
     * Returns the insertion index that the next parsed result will occupy.
     */
    int getCurrentResultIndex();

    /**
     * Returns the total number of tokens in the original argument array.
     */
    int getArgCount();

    /**
     * Returns the original argument array retained by the execution context.
     * The server implementation exposes the same array, so callers must
     * coordinate mutations with parsing and syntax feedback that read it.
     */
    String[] getArgs();

    /**
     * Returns whether the argument cursor has not reached the argument count.
     */
    default boolean haveUnhandledArg() {
        return getCurrentArgIndex() < getArgCount();
    }

    /**
     * Returns the number of argument tokens remaining after the current cursor.
     */
    default int getLeftArgCount() {
        return getArgCount() - getCurrentArgIndex();
    }

    /**
     * Returns the next argument and advances the argument cursor by one token.
     * Callers must ensure an argument remains; the server parser raises a parse
     * exception when the cursor has reached the end.
     */
    String popArg();

    /**
     * Returns an argument by absolute index without advancing the argument
     * cursor. {@code index} must identify an element of the retained argument
     * array; the server implementation uses the array's ordinary bounds checks.
     */
    String queryArg(int index);

    /**
     * Returns the argument at the current cursor without consuming it. Callers
     * must check {@code haveUnhandledArg} first when end-of-input is possible,
     * because this method does not provide an end sentinel.
     */
    default String queryArg() {
        return queryArg(getCurrentArgIndex());
    }

    /**
     * Appends a parsed result at the current result index and advances that
     * index. A result may be {@code null}, including an optional node's
     * configured default, so executors choose the expected type and fallback
     * explicitly.
     */
    void putResult(Object result);

    /**
     * Clears parsed values and restarts result insertion at index zero. This
     * operation leaves the argument cursor, sender, feedback, and permissions
     * unchanged so redirects can parse the remaining tokens as a fresh result
     * sequence.
     */
    void clearResults();

    /**
     * Appends a translatable feedback message for later registry delivery. This
     * operation does not send immediately; the registry checks the sender's
     * feedback gamerule and routes success or failure messages according to the
     * command result.
     */
    void addOutput(@MayContainTrKey String output, Object... args);

    /**
     * Appends a red-prefixed feedback message with the supplied translation
     * arguments.
     */
    default void addError(@MayContainTrKey String output, Object... args) {
        addOutput(TextFormat.RED + output, args);
    }

    /**
     * Appends syntax feedback identifying the token at the current argument
     * cursor.
     */
    default void addSyntaxError() {
        addSyntaxError(getCurrentArgIndex());
    }

    /**
     * Appends syntax feedback that separates the retained argument sequence
     * around {@code errorIndex}. The method keeps the command's registered name
     * and preceding tokens in the first translation argument, the selected
     * token in the second, and following tokens in the third.
     *
     * <p> An index outside the array selects no token: tokens before that index
     * stay on the left and tokens after it stay on the right. The method only
     * records feedback, leaving both parsing cursors unchanged so callers can
     * return the original failed context. Separating spaces are reconstructed
     * from tokens rather than recovered from the user's original input.
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
        addError("%" + TrKeys.MC_COMMANDS_GENERIC_SYNTAX, left.toString(), current, right.toString());
    }

    /**
     * Appends the configured rejection message for the required executor sender
     * type.
     */
    default void addInvalidExecutorError(SenderType<?> correctSenderType) {
        addError("%" + correctSenderType.errorMsg());
    }

    /**
     * Appends the standard feedback message for an empty target selection.
     */
    default void addNoTargetMatchError() {
        addError("%" + TrKeys.MC_COMMANDS_GENERIC_NOTARGETMATCH);
    }

    /**
     * Appends the standard feedback message for an excessive target selection.
     */
    default void addTooManyTargetsError() {
        addError("%" + TrKeys.MC_COMMANDS_GENERIC_TOOMANYTARGETS);
    }

    /**
     * Appends the standard feedback message for a missing target player.
     */
    default void addPlayerNotFoundError() {
        addError("%" + TrKeys.MC_COMMANDS_GENERIC_PLAYER_NOTFOUND);
    }

    /**
     * Returns whether the index identifies an element of the command argument
     * array.
     */
    default boolean isValidArgIndex(int index) {
        return index >= 0 && index < getArgCount();
    }

    /**
     * Sends a translatable message when the recipient player's world enables
     * command feedback. The gamerule is read from {@code player}, rather than
     * from the current command sender, and a disabled rule suppresses delivery.
     */
    default void sendWhisperTo(EntityPlayer player, @MayContainTrKey String message, Object... args) {
        if (player.getDimension().getWorld().getWorldData().getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK)) {
            player.sendTranslatable(message, args);
        }
    }

    /**
     * Returns the accumulated feedback containers for this execution context.
     * The server exposes its mutable list directly, so adding or removing
     * entries changes the messages later consumed by the registry.
     */
    List<TrContainer> getOutputs();

    /**
     * Appends all supplied feedback containers in collection iteration order.
     * The server retains the containers themselves without copying them; this
     * does not send messages or change the parsing cursors.
     */
    void addOutputs(Collection<TrContainer> outputs);

    /**
     * Returns the permission requirements used to filter successful feedback
     * recipients. The server starts with command-wide permissions and appends
     * those of selected nodes; it exposes the mutable accumulated list
     * directly.
     */
    List<String> getPermissions();

    /**
     * Appends one permission requirement used when filtering command feedback
     * recipients.
     */
    void addPermission(String permission);

    /**
     * Appends the supplied permission requirements used when filtering command
     * feedback recipients.
     */
    void addPermissions(Collection<String> permissions);

    /**
     * Returns a success result retaining this context for later feedback
     * handling.
     */
    default CommandResult success() {
        return CommandResult.success(this);
    }

    /**
     * Returns a result with the supplied status and this execution context. The
     * name does not validate positivity: {@code CommandResult.isSuccess} still
     * treats zero and negative supplied statuses as failures.
     */
    default CommandResult success(int status) {
        return new CommandResult(status, this);
    }

    /**
     * Returns a failure result retaining this context for later feedback
     * handling.
     */
    default CommandResult fail() {
        return CommandResult.fail(this);
    }

    /**
     * Returns the parsed result stored at {@code index}, or {@code null} for an
     * absent value. The caller chooses {@code T} to match the producing node;
     * the context does not provide runtime type metadata.
     */
    <T> T getResult(int index);

    /**
     * Returns a parsed result, substituting {@code defaultValue} when the
     * stored value is absent or {@code null}. The caller must choose a result
     * type compatible with the producing node; this fallback does not convert
     * values of another type.
     */
    default <T> T getResultOr(int index, T defaultValue) {
        T result = getResult(index);
        return result != null ? result : defaultValue;
    }
}
