package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.CommandResult;
import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;
import org.allaymc.server.cmdv2.exception.CommandParseException;
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
    protected boolean optional;
    protected String name;
    protected Function<CommandContext, CommandResult> executor;

    public BaseNode(String name, CommandNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean optional() {
        return optional;
    }

    @Override
    public CommandNode setOptional(boolean optional) {
        this.optional = optional;
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
        for (var leaf : leaves) {
            if (leaf.match(context)) {
                return leaf;
            }
        }
        return null;
    }

    @Override
    public boolean isLeaf() {
        return leaves == null;
    }

    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        if (leaves == null) {
            leaves = new ArrayList<>();
        }
        leaf.setDepth(depth + 1);
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
        this.executor = executor;
        return this;
    }

    @Override
    public Function<CommandContext, CommandResult> getExecutor() {
        return executor;
    }
}
