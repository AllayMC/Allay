package org.allaymc.server.command.tree.node;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.exception.CommandParseException;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public abstract class BaseNode implements CommandNode {
    protected int depth;
    protected CommandNode parent;
    protected List<CommandNode> leaves;
    @Getter
    @Setter
    protected CommandNode optionalLeaf;
    protected boolean optional = false;
    protected String name;
    protected Function<CommandContext, CommandResult> executor;
    protected SenderType senderType = SenderType.ANY;

    public BaseNode(String name, CommandNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public CommandNode optional(boolean optional) {
        if (this.optional == optional) {
            return this;
        }
        this.optional = optional;
        if (optional) {
            //一个节点下只能有一个可选参数
            if (parent.getOptionalLeaf() != null) {
                throw new IllegalArgumentException("A node can only have one optional leaf node");
            }
            parent.setOptionalLeaf(this);
        }
        return this;
    }
    @Override
    public int depth() {
        return depth;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public CommandNode parent() {
        return parent;
    }

    @Override
    public CommandNode up(@Range(from = 1, to = Integer.MAX_VALUE) int count) {
        if (count > depth) {
            throw new CommandParseException("ParamNode.up(count): count is bigger than depth");
        }
        CommandNode node = this;
        for (int i = 0; i < count; i++) {
            node = node.parent();
        }
        return node;
    }

    @Nullable
    @Override
    public CommandNode nextNode(CommandContext context) {
        if (isLeaf()) {
            return null;
        }
        if (context.haveUnhandledArg()) {
            for (var leaf : leaves) {
                if (leaf.match(context)) {
                    return leaf;
                }
            }
        } else {
            var optionalLeaf = getOptionalLeaf();
            if (optionalLeaf != null) {
                // 忽略RootNode，所以说索引为optionalLeaf.depth() - 1
                context.putResult(optionalLeaf.depth() - 1, optionalLeaf.getDefaultValue());
                return optionalLeaf;
            }
        }
        return null;
    }

    @Override
    public boolean isLeaf() {
        return leaves == null;
    }

    @Override
    public List<CommandNode> getLeaves() {
        return leaves;
    }

    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        if (leaves == null) {
            leaves = new ArrayList<>();
        }
        leaf.setDepth(depth + 1);
        // 可选参数后不能有非可选参数
        leaf.optional(optional);
        leaves.add(leaf);
        return leaf;
    }

    @Override
    public CommandNode key(String key) {
        return addLeaf(new KeyNode(key, this));
    }

    @Override
    public CommandNode str(String name) {
        return addLeaf(new StringNode(name, this));
    }

    @Override
    public CommandNode intNum(String name) {
        return addLeaf(new IntNode(name, this));
    }

    @Override
    public CommandNode floatNum(String name) {
        return addLeaf(new FloatNode(name, this));
    }

    @Override
    public CommandNode doubleNum(String name) {
        return addLeaf(new DoubleNode(name, this));
    }

    @Override
    public CommandNode bool(String name) {
        return addLeaf(new BooleanNode(name, this));
    }

    @Override
    public CommandNode enums(String name, String... enums) {
        return addLeaf(new EnumNode(name, this, enums));
    }

    @Override
    public CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec(executor, SenderType.ANY);
    }

    @Override
    public CommandNode exec(Function<CommandContext, CommandResult> executor, SenderType senderType) {
        this.executor = executor;
        this.senderType = senderType;
        return this;
    }

    @Override
    public Function<CommandContext, CommandResult> getExecutor() {
        return executor;
    }

    @Override
    public SenderType getSenderType() {
        return senderType;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = new CommandParamData();
        data.setName(name);
        data.setOptional(optional);
        return data;
    }
}
