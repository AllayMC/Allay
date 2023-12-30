package org.allaymc.api.command.tree;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;

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

    int getArgCount();

    String[] getArgs();

    default boolean haveUnhandledArg() {
        return getCurrentArgIndex() < getArgCount();
    }

    String popArg();

    String queryArg(int index);

    default String queryArg() {
        return queryArg(getCurrentArgIndex());
    }

    void putResult(int index, Object result);

    default void putResult(Object result) {
        putResult(getCurrentArgIndex(), result);
    }

    default void popAndPutArgToResult() {
        putResult(queryArg());
        popArg();
    }

    <T> T getResult(int index);

    default <T> T getResult() {
        return getResult(getCurrentArgIndex());
    }

    void addOutput(@MayContainTrKey String output, Object... args);

    default void addOutput(@MayContainTrKey String output) {
        addOutput(output, new Object[0]);
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
        addOutput("§c%" + TrKeys.M_COMMANDS_GENERIC_SYNTAX, left.toString(), current, right.toString());
    }

    default boolean isValidArgIndex(int index) {
        return index >= 0 && index < getArgCount();
    }

    default void addSyntaxError() {
        addSyntaxError(getCurrentArgIndex());
    }

    default void addSenderTypeError(CommandNode node) {
        addOutput("§c" + I18n.get().tr(node.getSenderType().i18Key()));
    }

    List<TrContainer> getOutputs();

    default CommandResult success() {
        return CommandResult.success(this);
    }

    default CommandResult failed() {
        return CommandResult.failed(this);
    }
}
