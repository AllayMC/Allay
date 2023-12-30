package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
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

    CommandSender getSender();

    int getCurrentArgIndex();

    int getArgCount();

    String popArg();

    String queryArg(int index);

    default String queryArg() {
        return queryArg(getCurrentArgIndex());
    }

    void putResult(Object result, int index);

    default void putResult(Object result) {
        putResult(result, getCurrentArgIndex());
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

    default void addSyntaxError(int index) {
        var left = index >= 1 ? queryArg(index - 1) : "";
        var current = queryArg(index);
        var right = index < getArgCount() - 1 ? queryArg(index + 1) : "";
        addOutput("§c" + TrKeys.M_COMMANDS_GENERIC_SYNTAX, left, current, right);
    }

    default void addSyntaxError() {
        addSyntaxError(getCurrentArgIndex());
    }

    List<TrContainer> getOutputs();

    default CommandResult success() {
        return CommandResult.success(this);
    }

    default CommandResult failed() {
        return CommandResult.failed(this);
    }
}
