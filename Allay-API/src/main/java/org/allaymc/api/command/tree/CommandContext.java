package org.allaymc.api.command.tree;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.common.utils.TextFormat;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.List;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandContext {

    Command getCommand();

    CommandSender getSender();

    int getCurrentArgIndex();

    int getCurrentResultIndex();

    int getArgCount();

    String[] getArgs();

    default boolean haveUnhandledArg() {
        return getCurrentArgIndex() < getArgCount();
    }

    default int getLeftArgCount() {
        return getArgCount() - getCurrentArgIndex();
    }

    String popArg();

    String queryArg(int index);

    default String queryArg() {
        return queryArg(getCurrentArgIndex());
    }

    void putResult(Object result);

    void addOutput(@MayContainTrKey String output, Object... args);

    default void addOutput(@MayContainTrKey String output) {
        addOutput(output, new Object[0]);
    }

    default void addError(@MayContainTrKey String output) {
        addOutput(TextFormat.RED + output, new Object[0]);
    }

    default void addError(@MayContainTrKey String output, Object... args) {
        addOutput(TextFormat.RED + output, args);
    }

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
        addOutput(TrKeys.M_COMMANDS_GENERIC_SYNTAX, left.toString(), current, right.toString());
    }

    default void addInvalidExecutorError(SenderType<?> correctSenderType) {
        addOutput(correctSenderType.errorMsg());
    }

    default void addNoTargetMatchError() {
        addOutput(TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
    }

    default void addTooManyTargetsError() {
        addOutput(TrKeys.M_COMMANDS_GENERIC_TOOMANYTARGETS);
    }

    default void addPlayerNotFoundError() {
        addOutput(TrKeys.M_COMMANDS_GENERIC_PLAYER_NOTFOUND);
    }

    default boolean isValidArgIndex(int index) {
        return index >= 0 && index < getArgCount();
    }

    default void addSyntaxError() {
        addSyntaxError(getCurrentArgIndex());
    }

    default void sendWhisperTo(EntityPlayer player, @MayContainTrKey String message, Object... args) {
        if (player.getDimension().getWorld().getWorldData().getGameRule(GameRule.SEND_COMMAND_FEEDBACK)) {
            player.sendTr(message, args);
        }
    }

    List<TrContainer> getOutputs();

    default CommandResult success() {
        return CommandResult.success(this);
    }

    default CommandResult success(int status) {
        return new CommandResult(status, this);
    }

    default CommandResult fail() {
        return CommandResult.fail(this);
    }

    <T> T getResult(int index);

    default <T> T getFirstResult() {
        return getResult(0);
    }

    default <T> T getSecondResult() {
        return getResult(1);
    }

    default <T> T getThirdResult() {
        return getResult(2);
    }

    default <T> T getFourthResult() {
        return getResult(3);
    }

    default <T> T getFifthResult() {
        return getResult(4);
    }

    default <T> T getSixthResult() {
        return getResult(5);
    }

    default <T> T getSeventhResult() {
        return getResult(6);
    }

    default <T> T getEighthResult() {
        return getResult(7);
    }

    default <T> T getNinthResult() {
        return getResult(8);
    }

    default <T> T getTenthResult() {
        return getResult(9);
    }

    default <T> T getEleventhResult() {
        return getResult(10);
    }
}
