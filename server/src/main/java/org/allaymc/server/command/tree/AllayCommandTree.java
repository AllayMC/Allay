package org.allaymc.server.command.tree;

import lombok.Getter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.server.command.tree.node.RootNode;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code AllayCommandTree} parses one command's arguments through an ordered
 * tree of parameter nodes and terminal executors. Each invocation gets a fresh
 * context, while the command and root node remain shared across invocations.
 * Child matching consumes tokens and stores results before the tree checks the
 * selected child's permissions.
 *
 * <p> Traversal commits to the first matching child; a later failure returns
 * that context without retrying sibling branches or restoring consumed input. A
 * terminal executor runs only after input is exhausted. A terminal redirect
 * first updates the existing context through its callback, then clears parsed
 * results and restarts at the root while retaining the argument cursor,
 * feedback, and collected permissions.
 *
 * @author daoge_cmd
 */
@Getter
public class AllayCommandTree implements CommandTree {

    /**
     * Command retained by every context created for this tree.
     */
    protected Command command;
    /**
     * {@code root} is the shared structural entry node for parsing and
     * redirects. Tree construction mutates its descendants before command
     * execution begins.
     */
    protected CommandNode root = new RootNode();

    /**
     * Creates a command-associated tree with an initially empty structural
     * root.
     */
    private AllayCommandTree(Command command) {
        this.command = command;
    }

    /**
     * Creates a fresh server command tree retaining the supplied command.
     */
    public static CommandTree create(Command command) {
        return new AllayCommandTree(command);
    }

    /**
     * Collects terminal nodes into a new list in child traversal order. The
     * list retains existing node references and includes the structural root
     * only when that root is itself terminal.
     */
    @Override
    public List<CommandNode> getLeaves() {
        var leaves = new ArrayList<CommandNode>();
        findLeaf(root, leaves);
        return leaves;
    }

    /**
     * Appends all terminal descendants of {@code node} to {@code dest} in child
     * order. Existing destination entries remain intact, and terminal nodes
     * themselves are appended without copying or checking permissions.
     */
    protected void findLeaf(CommandNode node, List<CommandNode> dest) {
        if (node.isLeaf()) dest.add(node);
        else node.getLeaves().forEach(leaf -> findLeaf(leaf, dest));
    }

    /**
     * Creates a fresh context and traverses this tree using the supplied sender
     * and argument array. The context retains {@code args} directly;
     * command-wide registry checks and feedback delivery occur outside this
     * method.
     */
    @Override
    public CommandResult parse(CommandSender sender, String[] args) {
        var context = new AllayCommandContext(command, sender, args);
        return parse0(root, context);
    }

    /**
     * Traverses the current path and returns its executor result or a
     * context-bearing failure. A terminal executor rejects remaining input
     * before running. A redirect callback can change the sender and other
     * context state, after which results are cleared and traversal resumes at
     * the root.
     *
     * <p> The selected child has already matched and consumed its input before
     * its permissions are checked. A missing child or denied permission
     * terminates this invocation; the method does not restore context state or
     * retry another sibling after a downstream failure. Accepted child
     * permissions remain accumulated for later feedback filtering.
     */
    protected CommandResult parse0(CommandNode node, CommandContext context) {
        if (node.isLeaf()) {
            var executor = node.getExecutor();
            var onRedirect = node.getOnRedirect();

            if (executor != null) {
                if (context.haveUnhandledArg()) {
                    context.addSyntaxError(context.getCurrentArgIndex() + 1);
                    return context.fail();
                }

                return executor.apply(context);
            } else if (onRedirect != null) {
                onRedirect.accept(context);
                // Redirect results belong to the completed prefix. Clear only those
                // values so the restarted tree indexes fresh results from zero while
                // consuming the same invocation's remaining arguments.
                context.clearResults();
                node = root;
            }
        }

        var nextNode = node.nextNode(context);
        if (nextNode == null) {
            context.addSyntaxError();
            return context.fail();
        } else if (!nextNode.checkPermissions(context.getSender())) {
            context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_UNKNOWN, context.queryArg(context.getCurrentArgIndex() - 1));
            return context.fail();
        } else {
            context.addPermissions(nextNode.getPermissions());
            return parse0(nextNode, context);
        }
    }
}
