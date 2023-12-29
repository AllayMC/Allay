package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandSender;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandContext {

    CommandSender getSender();

    int getCurrentArgIndex();

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
}
