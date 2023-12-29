package org.allaymc.api.command.tree;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface ParamNodeFactory {

    CommandNode strNode(String str);

    CommandNode intNode(int number);

    CommandNode floatNode(float number);

    CommandNode enumNode(String[] values);
}
