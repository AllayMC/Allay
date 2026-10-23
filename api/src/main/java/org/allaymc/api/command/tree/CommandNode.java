package org.allaymc.api.command.tree;

import com.google.common.collect.Lists;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3fc;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.allaymc.api.command.tree.CommandNodeFactory.getFactory;

/**
 * {@code CommandNode} represents a parameter or structural position in a
 * command's argument tree. Each child describes accepted input and the result
 * it appends to the invocation context. Fluent parameter methods create and
 * attach children, returning the child so callers can extend a path or install
 * its executor.
 *
 * <p> Child selection is ordered and uses argument-cost hints before invoking
 * {@code match}. A successful match consumes input and appends results; the
 * server does not backtrack to a sibling after a later path fails. An absent
 * optional child contributes its configured default instead of consuming a
 * token. Permissions are checked separately from matching, and redirect
 * callbacks restart at the root with remaining input after parsed results are
 * cleared.
 *
 * @author daoge_cmd
 */
@ApiStatus.NonExtendable
public interface CommandNode {

    /**
     * Returns whether this node may supply a default when input is exhausted.
     */
    boolean isOptional();

    /**
     * Returns the value inserted when this optional node is selected without
     * input. The default may be {@code null} and is retained directly; the
     * structural root does not support this operation.
     */
    Object getDefaultValue();

    /**
     * Returns the stored lower argument-count hint used before matching this
     * branch. The structural root does not support cost queries; parser
     * implementations own initialization and propagation of non-root hints.
     */
    int getMinArgCostBranch();

    /**
     * Stores the lower argument-count hint used when selecting this branch.
     */
    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * Returns the stored upper argument-count hint used before matching this
     * branch. The structural root does not support cost queries; parser
     * implementations update non-root hints as children or redirects are
     * configured.
     */
    int getMaxArgCostBranch();

    /**
     * Stores the upper argument-count hint used when selecting this branch.
     */
    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * Returns this node's maximum token cost for branch selection. Greedy
     * message and redirect implementations use a large finite bound; the
     * structural root does not support a per-node cost query.
     */
    int getMaxArgCost();

    /**
     * Stores the value used when this node is selected without input and
     * returns this node. This operation does not mark the node optional;
     * callers invoke {@code optional} separately to permit omission.
     */
    CommandNode defaultValue(Object defaultValue);

    /**
     * Marks this node optional and returns it for further configuration. The
     * server permits one optional child per parent and rejects permissions on
     * optional nodes; children attached afterward inherit optionality.
     */
    CommandNode optional();

    /**
     * Returns the child selected when this node has no remaining input. A
     * {@code null} result means no optional child is configured; this child
     * need not be a terminal node.
     */
    CommandNode getOptionalLeaf();

    /**
     * Replaces the reference to the child selected at end-of-input. This
     * low-level setter does not configure the child's own optional flag or
     * attach it to the child list.
     */
    void setOptionalLeaf(CommandNode optionalLeaf);

    /**
     * Returns the parameter name used when describing this command node.
     */
    String name();

    /**
     * Returns this node's structural depth, with zero reserved for the root.
     */
    int depth();

    /**
     * Returns whether this node has the zero depth reserved for the root.
     */
    default boolean isRoot() {
        return depth() == 0;
    }

    /**
     * Stores the structural depth assigned when this node joins its parent.
     */
    void setDepth(int depth);

    /**
     * Returns the parent supplied when this parameter node was constructed. The
     * structural root has no parent and rejects this operation instead of
     * returning an end-of-chain sentinel.
     */
    CommandNode parent();

    /**
     * Returns the ancestor {@code count} levels above this node. The server
     * requires a positive count no greater than this node's depth; the
     * structural root rejects upward traversal.
     */
    CommandNode up(int count);

    /**
     * Returns this node's immediate parent through the upward traversal
     * operation. This convenience requires a non-root node because the
     * structural root rejects attempts to move above itself.
     */
    default CommandNode up() {
        return up(1);
    }

    /**
     * Returns the root by moving upward through this node's entire depth.
     * Callers invoke this on a non-root node; the structural root's upward
     * traversal implementation rejects even a zero-level request.
     */
    default CommandNode root() {
        return up(depth());
    }

    /**
     * Tests the current input and, on success, consumes tokens and appends this
     * node's result. This operation does not check permissions, and the parser
     * provides no automatic rollback of a handler's context mutations after
     * failure.
     */
    boolean match(CommandContext context);

    /**
     * Selects the first child whose argument-cost hints and matcher accept the
     * remaining input. With no input, the server inserts the optional child's
     * default; {@code null} signals no selection, and callers check permissions
     * afterward.
     */
    CommandNode nextNode(CommandContext context);

    /**
     * Returns whether this node has no child list and terminates a path.
     */
    boolean isLeaf();

    /**
     * Returns the immediate child list, or {@code null} for a terminal node in
     * the server implementation. The list is shared with tree construction;
     * callers use {@code CommandTree.getLeaves} to collect terminal
     * descendants.
     */
    List<CommandNode> getLeaves();

    /**
     * Attaches the supplied child and returns it for further configuration. The
     * child must already name this node as its parent; the server assigns its
     * depth and propagates branch-cost hints and inherited optionality.
     */
    CommandNode addLeaf(CommandNode leaf);

    /**
     * Creates and attaches a child using this node as the provider's parent
     * argument. The provider must return a node constructed with that parent so
     * ancestry and branch-cost propagation follow the attached path.
     */
    default CommandNode addLeaf(Function<CommandNode, CommandNode> leafProvider) {
        return addLeaf(leafProvider.apply(this));
    }

    /**
     * Installs an executor guarded by the supplied sender capability and
     * returns this node. Validation precedes conversion; a rejected sender
     * produces failure feedback, while a node already configured for
     * redirection rejects executor installation.
     */
    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    /**
     * Installs an executor that accepts any command sender and returns this
     * node. The callback receives the current context; a node already
     * configured for redirection rejects this mutually exclusive executor
     * configuration.
     */
    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    /**
     * Installs a callback for restarting parsing at the root and returns that
     * root. The callback runs at a terminal node before results are cleared;
     * executor configuration and redirection are mutually exclusive.
     */
    CommandNode redirect(Consumer<CommandContext> onRedirect);

    /**
     * Adds a permission required to traverse this node and returns it for
     * configuration. Optional nodes reject permission requirements; the
     * execution parser checks selected-node permissions before descending and
     * accumulates them for feedback filtering.
     */
    CommandNode permission(String permission);

    /**
     * Returns an unmodifiable view of this node's permission requirements.
     * Later configuration remains visible through the view; callers cannot add
     * or remove requirements through the returned list.
     */
    @UnmodifiableView
    List<String> getPermissions();

    /**
     * Returns whether the supplied sender has every permission required by this
     * node.
     */
    default boolean checkPermissions(CommandSender sender) {
        return sender.hasPermissions(getPermissions());
    }

    /**
     * Returns the terminal executor configured for this node, or {@code null}
     * when absent. The server invokes executors only at terminal nodes and
     * rejects leftover input before calling them.
     */
    Function<CommandContext, CommandResult> getExecutor();

    /**
     * Returns the callback configured for terminal redirection, or {@code null}
     * when absent. The server invokes it before clearing parsed results and
     * restarting from the root with remaining input.
     */
    Consumer<CommandContext> getOnRedirect();

    /**
     * Adds a literal-key child producing {@code String} and returns the child
     * for further configuration. The node retains {@code defaultValue} for
     * omission after a separate call to {@code optional}. The configured key is
     * lowercased at construction and matching is case-sensitive.
     */
    default CommandNode key(String key, String defaultValue) {
        return addLeaf(getFactory().key(key, this, defaultValue));
    }

    /**
     * Adds a literal-key child producing {@code String} and returns the child
     * for further configuration. The omission default is {@code ""} and takes
     * effect only after {@code optional} is called. The configured key is
     * lowercased at construction and matching is case-sensitive.
     */
    default CommandNode key(String key) {
        return key(key, "");
    }

    /**
     * Adds a single-token string child producing {@code String} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * retains the supplied token without additional quote processing.
     */
    default CommandNode str(String name, String defaultValue) {
        return addLeaf(getFactory().str(name, this, defaultValue));
    }

    /**
     * Adds a single-token string child producing {@code String} and returns the
     * child for further configuration. The omission default is {@code ""} and
     * takes effect only after {@code optional} is called. The matcher retains
     * the supplied token without additional quote processing.
     */
    default CommandNode str(String name) {
        return str(name, "");
    }

    /**
     * Adds a short integer child producing {@code Short} and returns the child
     * for further configuration. The node retains {@code defaultValue} for
     * omission after a separate call to {@code optional}. The matcher uses the
     * signed decimal short parser.
     */
    default CommandNode shortNum(String name, short defaultValue) {
        return addLeaf(getFactory().shortNum(name, this, defaultValue));
    }

    /**
     * Adds a short integer child producing {@code Short} and returns the child
     * for further configuration. The omission default is zero and takes effect
     * only after {@code optional} is called. The matcher uses the signed
     * decimal short parser.
     */
    default CommandNode shortNum(String name) {
        return shortNum(name, (short) 0);
    }

    /**
     * Adds an integer child producing {@code Integer} and returns the child for
     * further configuration. The node retains {@code defaultValue} for omission
     * after a separate call to {@code optional}. The matcher uses the signed
     * decimal integer parser.
     */
    default CommandNode intNum(String name, int defaultValue) {
        return addLeaf(getFactory().intNum(name, this, defaultValue));
    }

    /**
     * Adds an integer child producing {@code Integer} and returns the child for
     * further configuration. The omission default is zero and takes effect only
     * after {@code optional} is called. The matcher uses the signed decimal
     * integer parser.
     */
    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    /**
     * Adds a long integer child producing {@code Long} and returns the child
     * for further configuration. The node retains {@code defaultValue} for
     * omission after a separate call to {@code optional}. The matcher uses the
     * signed decimal long parser.
     */
    default CommandNode longNum(String name, long defaultValue) {
        return addLeaf(getFactory().longNum(name, this, defaultValue));
    }

    /**
     * Adds a long integer child producing {@code Long} and returns the child
     * for further configuration. The omission default is zero and takes effect
     * only after {@code optional} is called. The matcher uses the signed
     * decimal long parser.
     */
    default CommandNode longNum(String name) {
        return longNum(name, 0);
    }

    /**
     * Adds a floating-point child producing {@code Float} and returns the child
     * for further configuration. The node retains {@code defaultValue} for
     * omission after a separate call to {@code optional}. The matcher uses the
     * Java floating-point parser.
     */
    default CommandNode floatNum(String name, float defaultValue) {
        return addLeaf(getFactory().floatNum(name, this, defaultValue));
    }

    /**
     * Adds a floating-point child producing {@code Float} and returns the child
     * for further configuration. The omission default is zero and takes effect
     * only after {@code optional} is called. The matcher uses the Java
     * floating-point parser.
     */
    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    /**
     * Adds a double-precision child producing {@code Double} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher uses
     * the Java double-precision parser.
     */
    default CommandNode doubleNum(String name, double defaultValue) {
        return addLeaf(getFactory().doubleNum(name, this, defaultValue));
    }

    /**
     * Adds a double-precision child producing {@code Double} and returns the
     * child for further configuration. The omission default is zero and takes
     * effect only after {@code optional} is called. The matcher uses the Java
     * double-precision parser.
     */
    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    /**
     * Adds a boolean child producing {@code Boolean} and returns the child for
     * further configuration. The node retains {@code defaultValue} for omission
     * after a separate call to {@code optional}. The matcher accepts only the
     * exact tokens {@code "true"} and {@code "false"}.
     */
    default CommandNode bool(String name, boolean defaultValue) {
        return addLeaf(getFactory().bool(name, this, defaultValue));
    }

    /**
     * Adds a boolean child producing {@code Boolean} and returns the child for
     * further configuration. The omission default is {@code false} and takes
     * effect only after {@code optional} is called. The matcher accepts only
     * the exact tokens {@code "true"} and {@code "false"}.
     */
    default CommandNode bool(String name) {
        return bool(name, false);
    }

    /**
     * Adds a case-sensitive enumeration child producing {@code String} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The server lowercases the supplied values array in
     * place during construction.
     */
    default CommandNode enums(String name, String defaultValue, String[] values) {
        return addLeaf(getFactory().enums(name, this, defaultValue, values));
    }

    /**
     * Adds a case-sensitive enumeration child producing {@code String} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The server lowercases the supplied values array in
     * place during construction.
     */
    default CommandNode enums(String name, String defaultValue, String enumName, String[] values) {
        return addLeaf(getFactory().enums(name, this, enumName, defaultValue, values));
    }

    /**
     * Adds a case-sensitive enumeration child producing {@code String} and
     * returns the child for further configuration. The omission default is
     * {@code ""} and takes effect only after {@code optional} is called. The
     * server lowercases the supplied values array in place during construction.
     */
    default CommandNode enums(String name, String... values) {
        return enums(name, "", values);
    }

    /**
     * Adds an enum-class child producing the matching enum constant and returns
     * the child for further configuration. The omission default is {@code null}
     * and takes effect only after {@code optional} is called. The matcher
     * accepts enum constant names without case sensitivity.
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, Class<T> enumClass) {
        return enumClass(name, null, enumClass);
    }

    /**
     * Adds an enum-class child producing the matching enum constant and returns
     * the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The matcher accepts enum constant names without case
     * sensitivity.
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, T defaultValue, Class<T> enumClass) {
        return addLeaf(getFactory().enumClass(name, this, defaultValue, enumClass));
    }

    /**
     * Adds an enum-class child producing the matching enum constant and returns
     * the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The matcher accepts enum constant names without case
     * sensitivity.
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, T defaultValue, String enumName, Class<T> enumClass) {
        return addLeaf(getFactory().enumClass(name, this, defaultValue, enumName, enumClass));
    }

    /**
     * Adds a case-insensitive enumeration child producing {@code String} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The server lowercases the supplied values array in
     * place while preserving matched input in the result.
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, defaultValue, enums));
    }

    /**
     * Adds a case-insensitive enumeration child producing {@code String} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The server lowercases the supplied values array in
     * place while preserving matched input in the result.
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, enumName, defaultValue, enums));
    }

    /**
     * Adds a remaining-message child producing {@code String} and returns the
     * child for further configuration. The omission default is {@code ""} and
     * takes effect only after {@code optional} is called. The matcher consumes
     * all remaining tokens and joins them with spaces.
     */
    default CommandNode msg(String name) {
        return msg(name, "");
    }

    /**
     * Adds a remaining-message child producing {@code String} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * consumes all remaining tokens and joins them with spaces.
     */
    default CommandNode msg(String name, String defaultValue) {
        return addLeaf(getFactory().msg(name, this, defaultValue));
    }

    /**
     * Adds a remaining-token child producing {@code List<String>} and returns
     * the child for further configuration. The omission default is a new empty
     * list and takes effect only after {@code optional} is called. The matcher
     * consumes all remaining tokens and keeps their individual values in a new
     * list.
     */
    default CommandNode remain(String name) {
        return remain(name, Lists.newArrayList());
    }

    /**
     * Adds a remaining-token child producing {@code List<String>} and returns
     * the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The matcher consumes all remaining tokens and keeps
     * their individual values in a new list.
     */
    default CommandNode remain(String name, List<String> defaultValue) {
        return addLeaf(getFactory().remain(name, this, defaultValue));
    }

    /**
     * Adds an entity-target child producing {@code List<Entity>} and returns the
     * child for further configuration. The omission default is a new empty list
     * and takes effect only after {@code optional} is called. The matcher
     * accepts a selector or player name and permits an empty selection.
     */
    default CommandNode target(String name) {
        return addLeaf(getFactory().target(name, this, Lists.newArrayList()));
    }

    /**
     * Adds an entity-target child producing {@code List<Entity>} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * accepts a selector or player name and permits an empty selection.
     */
    default CommandNode target(String name, List<Entity> defaultValue) {
        return addLeaf(getFactory().target(name, this, defaultValue));
    }

    /**
     * Adds a player-target child producing {@code List<EntityPlayer>} and
     * returns the child for further configuration. The omission default is a
     * new empty list and takes effect only after {@code optional} is called.
     * The matcher accepts a selector or player name and removes non-player
     * entities.
     */
    default CommandNode playerTarget(String name) {
        return addLeaf(getFactory().playerTarget(name, this, Lists.newArrayList()));
    }

    /**
     * Adds a player-target child producing {@code List<EntityPlayer>} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The matcher accepts a selector or player name and
     * removes non-player entities.
     */
    default CommandNode playerTarget(String name, List<EntityPlayer> defaultValue) {
        return addLeaf(getFactory().playerTarget(name, this, defaultValue));
    }

    /**
     * Adds a game-mode child producing {@code GameMode} and returns the child
     * for further configuration. The omission default is {@code null} and takes
     * effect only after {@code optional} is called. The parameter name is
     * {@code "gameMode"}. The matcher accepts exact lowercase aliases,
     * including {@code "3"}, {@code "spectator"}, and {@code "spc"} for
     * spectator mode.
     */
    default CommandNode gameMode() {
        return gameMode("gameMode");
    }

    /**
     * Adds a game-mode child producing {@code GameMode} and returns the child
     * for further configuration. The omission default is {@code null} and takes
     * effect only after {@code optional} is called. The matcher accepts exact
     * lowercase aliases, including {@code "3"}, {@code "spectator"}, and
     * {@code "spc"} for spectator mode.
     */
    default CommandNode gameMode(String name) {
        return addLeaf(getFactory().gameMode(name, this, null));
    }

    /**
     * Adds a game-mode child producing {@code GameMode} and returns the child
     * for further configuration. The node retains {@code defaultValue} for
     * omission after a separate call to {@code optional}. The matcher accepts
     * exact lowercase aliases, including {@code "3"}, {@code "spectator"}, and
     * {@code "spc"} for spectator mode.
     */
    default CommandNode gameMode(String name, GameMode defaultValue) {
        return addLeaf(getFactory().gameMode(name, this, defaultValue));
    }

    /**
     * Adds a three-coordinate position child producing
     * {@code org.joml.Vector3d} and returns the child for further
     * configuration. The omission default is {@code null} and takes effect only
     * after {@code optional} is called. The matcher supports absolute
     * coordinates and tilde offsets from the current sender location.
     */
    default CommandNode pos(String name) {
        return pos(name, null);
    }

    /**
     * Adds a three-coordinate child whose matched result is
     * {@code org.joml.Vector3d} and returns it for configuration. Omission
     * after {@code optional} inserts the original {@code Vector3fc} default
     * directly, without converting it to the matched result's type; executors
     * must handle that distinction.
     */
    default CommandNode pos(String name, Vector3fc defaultValue) {
        return addLeaf(getFactory().pos(name, this, defaultValue));
    }

    /**
     * Adds a wildcard-target child producing {@code String} and returns the
     * child for further configuration. The omission default is {@code ""} and
     * takes effect only after {@code optional} is called. The matcher accepts
     * one token without resolving it into entities.
     */
    default CommandNode wildcardTarget(String name) {
        return wildcardTarget(name, "");
    }

    /**
     * Adds a wildcard-target child producing {@code String} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * accepts one token without resolving it into entities.
     */
    default CommandNode wildcardTarget(String name, String defaultValue) {
        return addLeaf(getFactory().wildcardTarget(name, this, defaultValue));
    }

    /**
     * Adds an enchantment-type child producing {@code EnchantmentType} and
     * returns the child for further configuration. The omission default is
     * {@code null} and takes effect only after {@code optional} is called. The
     * matcher accepts snapshotted full enchantment identifiers or their bare
     * paths; bare paths use the default namespace for lookup.
     */
    default CommandNode enchantment(String name) {
        return enchantment(name, null);
    }

    /**
     * Adds an enchantment-type child producing {@code EnchantmentType} and
     * returns the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The matcher accepts snapshotted full enchantment
     * identifiers or their bare paths; bare paths use the default namespace for
     * lookup.
     */
    default CommandNode enchantment(String name, EnchantmentType defaultValue) {
        return addLeaf(getFactory().enchantment(name, this, defaultValue));
    }

    /**
     * Adds an effect-type child producing {@code EffectType} and returns the
     * child for further configuration. The omission default is {@code null} and
     * takes effect only after {@code optional} is called. The matcher accepts
     * snapshotted bare effect paths and resolves them in the default namespace.
     */
    default CommandNode effect(String name) {
        return effect(name, null);
    }

    /**
     * Adds an effect-type child producing {@code EffectType} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * accepts snapshotted bare effect paths and resolves them in the default
     * namespace.
     */
    default CommandNode effect(String name, EffectType defaultValue) {
        return addLeaf(getFactory().effect(name, this, defaultValue));
    }

    /**
     * Adds an item-type child producing {@code ItemType<?>} and returns the
     * child for further configuration. The omission default is {@code null} and
     * takes effect only after {@code optional} is called. The matcher resolves
     * a registered item identifier through the item registry.
     */
    default CommandNode itemType(String name) {
        return itemType(name, null);
    }

    /**
     * Adds an item-type child producing {@code ItemType<?>} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * resolves a registered item identifier through the item registry.
     */
    default CommandNode itemType(String name, ItemType<?> defaultValue) {
        return addLeaf(getFactory().itemType(name, this, defaultValue));
    }

    /**
     * Adds a block-type child producing {@code BlockType<?>} and returns the
     * child for further configuration. The omission default is {@code null} and
     * takes effect only after {@code optional} is called. The matcher resolves
     * a registered block identifier through the block registry.
     */
    default CommandNode blockType(String name) {
        return blockType(name, null);
    }

    /**
     * Adds a block-type child producing {@code BlockType<?>} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * resolves a registered block identifier through the block registry.
     */
    default CommandNode blockType(String name, BlockType<?> defaultValue) {
        return addLeaf(getFactory().blockType(name, this, defaultValue));
    }

    /**
     * Adds an entity-type child producing {@code EntityType<?>} and returns the
     * child for further configuration. The omission default is {@code null} and
     * takes effect only after {@code optional} is called. The matcher resolves
     * a registered entity identifier through the entity registry.
     */
    default CommandNode entityType(String name) {
        return entityType(name, null);
    }

    /**
     * Adds an entity-type child producing {@code EntityType<?>} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * resolves a registered entity identifier through the entity registry.
     */
    default CommandNode entityType(String name, EntityType<?> defaultValue) {
        return addLeaf(getFactory().entityType(name, this, defaultValue));
    }

    /**
     * Adds a difficulty child producing {@code Difficulty} and returns the
     * child for further configuration. The omission default is {@code null} and
     * takes effect only after {@code optional} is called. The matcher
     * recognizes the difficulty parser's supported numeric and named aliases.
     */
    default CommandNode difficulty(String name) {
        return difficulty(name, null);
    }

    /**
     * Adds a difficulty child producing {@code Difficulty} and returns the
     * child for further configuration. The node retains {@code defaultValue}
     * for omission after a separate call to {@code optional}. The matcher
     * recognizes the difficulty parser's supported numeric and named aliases.
     */
    default CommandNode difficulty(String name, Difficulty defaultValue) {
        return addLeaf(getFactory().difficulty(name, this, defaultValue));
    }

    /**
     * Adds a block-property child producing
     * {@code List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>} and returns
     * the child for further configuration. The omission default is a new empty
     * list and takes effect only after {@code optional} is called. The parent
     * must be a block-type node because parsing reads the preceding block
     * result.
     */
    default CommandNode blockPropertyValues(String name) {
        return blockPropertyValues(name, Lists.newArrayList());
    }

    /**
     * Adds a block-property child producing
     * {@code List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>} and returns
     * the child for further configuration. The node retains
     * {@code defaultValue} for omission after a separate call to
     * {@code optional}. The parent must be a block-type node because parsing
     * reads the preceding block result.
     */
    default CommandNode blockPropertyValues(String name, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        return addLeaf(getFactory().blockPropertyValues(name, this, defaultValue));
    }

    /**
     * Adds a greedy command-text child and returns it for further
     * configuration. The matcher joins remaining tokens, surrounds
     * literal-space tokens with double quotes, and trims the assembled text;
     * embedded quotes are not escaped. Omission after {@code optional} inserts
     * {@code ""} instead, so redispatch callers must account for the
     * reconstruction limits.
     */
    default CommandNode cmd(String name) {
        return cmd(name, "");
    }

    /**
     * Adds a greedy command-text child and returns it for further
     * configuration. The matcher joins remaining tokens, surrounds
     * literal-space tokens with double quotes, and trims the assembled text;
     * embedded quotes are not escaped. Omission after {@code optional} inserts
     * {@code defaultValue} instead, so redispatch callers must account for the
     * reconstruction limits.
     */
    default CommandNode cmd(String name, String defaultValue) {
        return addLeaf(getFactory().cmd(name, this, defaultValue));
    }
}
