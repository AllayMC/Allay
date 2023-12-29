package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandResult;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Range;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandNode {

    boolean optional();

    CommandNode setOptional(boolean optional);

    default CommandNode setOptional() {
        return setOptional(true);
    }

    String name();

    int depth();

    void setDepth(int depth);

    CommandNode parent();

    CommandNode up(@Range(from = 1, to = Integer.MAX_VALUE) int count);

    default CommandNode up() {
        return up(1);
    }

    default CommandNode root() {
        return up(depth());
    }

    boolean match(CommandContext helper);

    @Nullable
    CommandNode nextNode(CommandContext helper);

    boolean isLeaf();

    List<CommandNode> getLeaves();

    CommandNode addLeaf(CommandNode leaf);

    CommandNode key(String key);

    CommandNode str(String name);

    CommandNode intNum(String name);

    CommandNode floatNum(String name);

    CommandNode doubleNum(String name);

    CommandNode bool(String name);

    CommandNode enums(String name, String... enums);

    CommandNode exec(Function<CommandContext, CommandResult> executor);

    Function<CommandContext, CommandResult> getExecutor();

    CommandParamData toNetworkData();
}
