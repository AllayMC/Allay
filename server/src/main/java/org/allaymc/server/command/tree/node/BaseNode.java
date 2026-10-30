package org.allaymc.server.command.tree.node;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * {@code BaseNode} implements shared structure, optional defaults, branch
 * selection, permissions, and executor configuration for server command nodes.
 * Each node retains its construction-time parent, and {@code addLeaf} sets
 * child depth and updates argument-cost hints. A {@code null} child list marks
 * a terminal path; the optional-child reference independently identifies the
 * path to use when input ends.
 *
 * <p> Selection tests children in attachment order and accepts the first
 * matcher within its stored cost bounds. Matchers share the invocation context,
 * and no snapshot or rollback isolates a failed matcher or a later traversal
 * failure. Optionality permits one default-producing child per parent,
 * disallows node permissions, and propagates to children attached afterward.
 * Executors and redirects are mutually exclusive; terminal traversal decides
 * which callback runs.
 *
 * @author daoge_cmd
 */
public abstract class BaseNode implements CommandNode {
    /**
     * Structural distance from the root node.
     */
    protected int depth;
    /**
     * Parent reference supplied when this node is constructed.
     */
    protected CommandNode parent;
    /**
     * {@code leaves} retains attached children in selection order. A
     * {@code null} value marks a terminal node; {@code getLeaves} exposes the
     * mutable list directly after attachment.
     */
    protected List<CommandNode> leaves;
    /**
     * {@code optionalLeaf} identifies the child whose default is inserted when
     * input ends. {@code optional} registers this reference, while the
     * generated setter only replaces the reference.
     */
    @Getter
    @Setter
    protected CommandNode optionalLeaf;
    /**
     * {@code optional} permits this node to be selected without input. Once
     * set, {@code permission} rejects new requirements and subsequently
     * attached children inherit optionality.
     */
    protected boolean optional = false;
    /**
     * Parameter name included in command network metadata.
     */
    protected String name;
    /**
     * {@code executor} holds the sender-validating terminal callback installed
     * by {@code exec}. A configured redirect prevents installation, and
     * traversal invokes this callback only on terminal nodes.
     */
    @Getter
    protected Function<CommandContext, CommandResult> executor;
    /**
     * {@code onRedirect} holds the terminal callback invoked before parsing
     * restarts at the root. An installed executor prevents configuring this
     * callback on the same node.
     */
    @Getter
    protected Consumer<CommandContext> onRedirect;
    /**
     * {@code defaultValue} is retained without copying for end-of-input
     * optional selection. Changing it does not enable optionality, and mutable
     * defaults can be shared across invocations.
     */
    @Getter
    protected Object defaultValue;
    /**
     * {@code minArgCostBranch} stores the lower bound checked by
     * {@code nextNode}. Its initial value is zero, and optionality updates
     * lower-bound propagation separately from child attachment.
     */
    @Getter
    @Setter
    protected int minArgCostBranch;
    /**
     * {@code maxArgCostBranch} stores the upper bound checked by
     * {@code nextNode}. Child attachment and redirects propagate additive
     * increases through ancestors without recomputing an exact path maximum.
     */
    @Getter
    @Setter
    protected int maxArgCostBranch;
    /**
     * {@code permissions} stores this node's access requirements.
     * {@code getPermissions} returns an unmodifiable live view, and optional
     * nodes reject additions through {@code permission}.
     */
    protected List<String> permissions;

    /**
     * Creates a parameter node with a {@code null} omission default and no
     * permissions. The constructor retains the parent but does not attach this
     * node or mark it optional.
     */
    public BaseNode(String name, CommandNode parent) {
        this(name, parent, null);
    }

    /**
     * Creates a parameter node retaining its parent and default value directly.
     * The caller attaches it separately; a mutable default remains shared with
     * its supplier and with executions that omit this node.
     */
    public BaseNode(String name, CommandNode parent, Object defaultValue) {
        this.name = name;
        this.parent = parent;
        this.defaultValue = defaultValue;
        this.permissions = new ArrayList<>();
    }

    /**
     * Replaces the stored omission default and returns this node for
     * configuration. The value is retained directly, including {@code null};
     * this method does not change the node's optional flag.
     */
    @Override
    public CommandNode defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Returns the parameter name configured when this node was constructed.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Returns whether this node is configured to supply an omission default.
     */
    @Override
    public boolean isOptional() {
        return optional;
    }

    /**
     * Marks this node optional, registers it as its parent's default child, and
     * returns it. Repeated calls on the same optional node are idempotent. A
     * different optional sibling or existing node permissions cause an
     * exception before configuration changes.
     *
     * <p> The method sets this node's minimum argument hint to zero and
     * propagates the lower-bound update through non-root ancestors. Children
     * attached later inherit optionality through {@code addLeaf}; children
     * already attached are not revisited by this operation, so configuration
     * order remains significant.
     */
    @Override
    public CommandNode optional() {
        if (optional) {
            return this;
        }

        if (parent.getOptionalLeaf() != null) {
            throw new IllegalArgumentException("A node can only have one optional leaf node!");
        }

        if (!this.permissions.isEmpty()) {
            throw new IllegalArgumentException("Adding permissions to optional node is not allowed!");
        }

        this.optional = true;
        parent.setOptionalLeaf(this);
        setMinArgCostBranch(optional ? 0 : 1);
        // Omitting this node contributes no input token. Lower-bound propagation
        // follows the retained parent chain so ancestors can admit the default path.
        updateMinArgCostBranch(this);
        return this;
    }

    /**
     * Returns the structural depth assigned when this node was attached.
     */
    @Override
    public int depth() {
        return depth;
    }

    /**
     * Stores this node's structural depth for later ancestry traversal.
     */
    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Returns the parent reference retained when this parameter node was
     * constructed.
     */
    @Override
    public CommandNode parent() {
        return parent;
    }

    /**
     * Returns the ancestor at the requested positive distance above this node.
     * Counts below one or greater than {@code depth} raise
     * {@code IllegalArgumentException}; callers use non-root nodes for upward
     * traversal.
     */
    @Override
    public CommandNode up(int count) {
        Preconditions.checkArgument(count >= 1);
        if (count > depth) {
            throw new IllegalArgumentException("ParamNode.up(count): count is bigger than depth");
        }
        CommandNode node = this;
        for (int i = 0; i < count; i++) {
            node = node.parent();
        }
        return node;
    }


    /**
     * Selects the first matching child whose stored cost bounds contain the
     * remaining token count. Matchers read and mutate the same context, so this
     * method does not restore state between candidates. Permission checks
     * belong to the tree after selection.
     *
     * <p> When no tokens remain, the configured optional child is selected and
     * its stored default is appended without invoking its matcher. A terminal
     * node or an unmatched branch returns {@code null}. The default is inserted
     * by reference, so mutable defaults can be reused by multiple invocation
     * contexts.
     */
    @Override
    public CommandNode nextNode(CommandContext context) {
        if (isLeaf()) {
            return null;
        }

        if (context.haveUnhandledArg()) {
            var leftArgCount = context.getLeftArgCount();
            // Siblings inspect one shared context in attachment order. A matcher
            // that changes it before returning false leaves those changes visible
            // because this loop supplies no snapshot or restoration step.
            for (var leaf : leaves) {
                if (leftArgCount >= leaf.getMinArgCostBranch() &&
                    leftArgCount <= leaf.getMaxArgCostBranch() &&
                    leaf.match(context)) {
                    return leaf;
                }
            }
        } else {
            var optionalLeaf = getOptionalLeaf();
            if (optionalLeaf != null) {
                // End-of-input selection bypasses matching; append the stored default
                // so executors still receive a result slot for this omitted parameter.
                context.putResult(optionalLeaf.getDefaultValue());
                return optionalLeaf;
            }
        }

        return null;
    }

    /**
     * Returns whether no child list has been allocated for this node.
     */
    @Override
    public boolean isLeaf() {
        return leaves == null;
    }

    /**
     * Returns the shared child list, or {@code null} while no child list has
     * been allocated. Direct list mutations bypass depth, optionality, and cost
     * propagation, so callers configure ordinary children through
     * {@code addLeaf}.
     */
    @Override
    public List<CommandNode> getLeaves() {
        return leaves;
    }

    /**
     * Attaches a child, updates its depth and branch costs, and returns that
     * child. The child must already retain this node as its parent; attachment
     * does not rewrite the parent reference. The maximum-cost update occurs
     * before inherited optionality is applied.
     *
     * <p> If this node is optional, attachment also marks the child optional
     * and registers its default path. A failure during that step can occur
     * after depth and ancestor cost changes, because this operation does not
     * roll back those earlier mutations. Successful attachment appends the
     * child in future matching order.
     */
    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        if (leaves == null) {
            leaves = new ArrayList<>();
        }
        leaf.setDepth(depth + 1);
        leaf.setMaxArgCostBranch(leaf.getMaxArgCost());
        // Establish the new upper-cost hint before optionality can alter the lower
        // hint; both updates use the child's construction-time ancestry rather than
        // deriving parents from the child list.
        updateMaxArgCostBranch(leaf);
        if (optional) {
            leaf.optional();
        }
        leaves.add(leaf);
        return leaf;
    }

    /**
     * Propagates candidate lower-cost sums through non-root ancestors when each
     * sum is smaller than the ancestor's stored hint. This is an incremental
     * update using existing hints; it does not recompute all sibling paths.
     */
    protected void updateMinArgCostBranch(CommandNode leaf) {
        var p = leaf.parent();
        while (!p.isRoot()) {
            var min = p.getMinArgCostBranch() + leaf.getMinArgCostBranch();
            if (min < p.getMinArgCostBranch()) {
                p.setMinArgCostBranch(min);
            }
            leaf = p;
            p = leaf.parent();
        }
    }

    /**
     * Propagates additive upper-cost increases through non-root ancestors using
     * their current stored hints. Repeated updates retain earlier increases, so
     * the stored bound can exceed the maximum cost of any individual path.
     */
    protected void updateMaxArgCostBranch(CommandNode leaf) {
        var p = leaf.parent();
        while (!p.isRoot()) {
            var max = p.getMaxArgCostBranch() + leaf.getMaxArgCostBranch();
            if (max > p.getMaxArgCostBranch()) {
                p.setMaxArgCostBranch(max);
            }
            leaf = p;
            p = leaf.parent();
        }
    }

    /**
     * Installs a terminal callback that validates and converts the sender
     * before invoking {@code biExecutor}. A configured redirect rejects
     * installation; a failed capability check records its rejection message and
     * returns a context-bearing failure instead of invoking the executor.
     */
    @Override
    public <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> biExecutor, SenderType<SENDER_TYPE> senderType) {
        if (onRedirect != null) {
            throw new IllegalArgumentException("onRedirect has been set, cannot set redirector");
        }
        // Validate before applying the capability caster: proxy senders can expose
        // entity/player identities without implementing those concrete sender types.
        this.executor = context -> {
            var sender = context.getSender();
            if (senderType.validate(sender)) {
                return biExecutor.apply(context, senderType.caster().apply(context.getSender()));
            } else {
                context.addInvalidExecutorError(senderType);
                return context.fail();
            }
        };
        return this;
    }

    /**
     * Returns one token for an ordinary node, or {@code Short.MAX_VALUE} for a
     * redirect. The finite redirect bound allows trailing subcommand arguments
     * to survive branch-cost filtering before parsing restarts.
     */
    @Override
    public int getMaxArgCost() {
        // The redirect bound admits trailing subcommand tokens for a root restart.
        // Ordinary one-token nodes retain their smaller per-node cost for selection.
        return getOnRedirect() != null ? Short.MAX_VALUE : 1;
    }

    /**
     * Installs the redirect callback, updates branch-cost hints, and returns
     * the structural root. An existing executor rejects installation; parsing
     * invokes the callback only at a terminal node before clearing results and
     * restarting.
     */
    @Override
    public CommandNode redirect(Consumer<CommandContext> onRedirect) {
        if (executor != null) {
            throw new IllegalArgumentException("executor has been set, cannot set onRedirect");
        }
        this.onRedirect = onRedirect;
        setMaxArgCostBranch(getMaxArgCost());
        updateMaxArgCostBranch(this);
        return root();
    }

    /**
     * Appends a node access requirement and returns this node for further
     * configuration. Optional nodes reject additions because an end-of-input
     * default path must not introduce a permission-gated optional parameter.
     */
    @Override
    public CommandNode permission(String permission) {
        if (this.optional) {
            throw new IllegalArgumentException("Adding permissions to optional node is not allowed!");
        }

        this.permissions.add(permission);
        return this;
    }

    /**
     * Returns an unmodifiable view backed by this node's permission list. Later
     * additions through {@code permission} remain visible to callers retaining
     * the view; no permission snapshot is created.
     */
    @Override
    public List<String> getPermissions() {
        return Collections.unmodifiableList(this.permissions);
    }

    /**
     * Creates parameter metadata containing this node's configured name and
     * optional flag.
     */
    public CommandParamData toNetworkData() {
        var data = new CommandParamData();
        data.setName(name);
        data.setOptional(optional);
        return data;
    }
}
