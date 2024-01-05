package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Range;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandNode extends ParamNodeFactory {

    boolean isOptional();

    Object getDefaultValue();

    /**
     * @return 选中这个分支后消耗的最小参数数量
     */
    int getMinArgCostBranch();

    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * @return 选中这个分支后后消耗的最大参数数量
     */
    int getMaxArgCostBranch();

    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * @return 这个node将会消耗几个参数，可以返回一个足够大的数来表示无穷
     */
    default int getMaxArgCost() {
        return 1;
    }

    CommandNode defaultValue(Object defaultValue);

    CommandNode optional();

    @Nullable
    CommandNode getOptionalLeaf();

    void setOptionalLeaf(CommandNode optionalLeaf);

    String name();

    int depth();

    default boolean isRoot() {
        return depth() == 0;
    }

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

    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    CommandResult applyExecutor(CommandContext context);

    CommandParamData toNetworkData();
}
