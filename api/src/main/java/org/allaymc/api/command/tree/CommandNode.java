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
 * @author daoge_cmd
 */
@ApiStatus.NonExtendable
public interface CommandNode {

    /**
     * Determines if this node is optional in the command tree.
     *
     * @return {@code true} if the node is optional, {@code false} otherwise.
     */
    boolean isOptional();

    /**
     * Retrieves the default value for this node, if specified.
     *
     * @return the default value
     */
    Object getDefaultValue();

    /**
     * Return the minimum number of arguments consumed after selecting this branch.
     *
     * @return the minimum number of arguments required
     */
    int getMinArgCostBranch();

    /**
     * Set the minimum number of arguments consumed after selecting this branch.
     *
     * @param minArgCostBranch the minimum number of arguments
     */
    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * Return the maximum number of arguments consumed after selecting this branch.
     *
     * @return the maximum number of arguments required
     */
    int getMaxArgCostBranch();

    /**
     * Set the maximum number of arguments consumed after selecting this branch.
     *
     * @param maxArgCostBranch the maximum number of arguments
     */
    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * Return the maximum number of arguments this node can consume.
     * A sufficiently large number is returned to indicate an infinite argument count.
     *
     * @return the maximum number of arguments
     */
    int getMaxArgCost();

    /**
     * Set a default value for this node and marks it as optional.
     *
     * @param defaultValue the default value to set
     * @return the current {@code CommandNode}
     */
    CommandNode defaultValue(Object defaultValue);

    /**
     * Marks this node as optional.
     *
     * @return the current {@code CommandNode}
     */
    CommandNode optional();

    /**
     * Retrieves the optional leaf node for this command.
     *
     * @return the optional leaf node
     */
    CommandNode getOptionalLeaf();

    /**
     * Set an optional leaf node for this command.
     *
     * @param optionalLeaf the optional leaf to set
     */
    void setOptionalLeaf(CommandNode optionalLeaf);

    /**
     * Retrieves the name of this command node.
     *
     * @return the name of the command node
     */
    String name();

    /**
     * Retrieves the depth of this node in the command tree.
     *
     * @return the depth of the node
     */
    int depth();

    /**
     * Checks if this node is the root node (depth 0).
     *
     * @return {@code true} if this is the root node, {@code false} otherwise.
     */
    default boolean isRoot() {
        return depth() == 0;
    }

    /**
     * Set the depth of this node in the command tree.
     *
     * @param depth the depth to set
     */
    void setDepth(int depth);

    /**
     * Retrieves the parent of this node.
     *
     * @return the parent {@code CommandNode}
     */
    CommandNode parent();

    /**
     * Moves up a certain number of levels in the command tree.
     *
     * @param count the number of levels to move up
     * @return the {@code CommandNode} after moving up
     */
    CommandNode up(int count);

    /**
     * Moves up one level in the command tree.
     *
     * @return the parent {@code CommandNode}
     */
    default CommandNode up() {
        return up(1);
    }

    /**
     * Return the root node of the command tree by moving up all levels.
     *
     * @return the root {@code CommandNode}
     */
    default CommandNode root() {
        return up(depth());
    }

    /**
     * Checks if this node matches the current command context. Notes that permission
     * will not be checked in this method.
     *
     * @param context the command context
     * @return {@code true} if the node matches, {@code false} otherwise.
     */
    boolean match(CommandContext context);

    /**
     * Retrieves the next accessible node in the command tree based on the current context.
     * Notes that permission will not be checked in this method.
     *
     * @param context the command context
     * @return the next {@code CommandNode}
     */
    CommandNode nextNode(CommandContext context);

    /**
     * Checks if this node is a leaf node (i.e., it has no further branches).
     *
     * @return {@code true} if the node is a leaf, {@code false} otherwise.
     */
    boolean isLeaf();

    /**
     * Retrieves the list of leaf nodes connected to this node.
     *
     * @return A list of {@code CommandNode} leaves
     */
    List<CommandNode> getLeaves();

    /**
     * Add a leaf node to this command node.
     *
     * @param leaf the leaf node to add
     * @return the passed {@code CommandNode}
     */
    CommandNode addLeaf(CommandNode leaf);

    /**
     * Add a leaf node to this command node.
     *
     * @param leafProvider the provider that will provide the leaf node. The current command
     *                     node will be passed to the provider, and the provider should return
     *                     the leaf node. This is helpful for keeping chain calls when using
     *                     custom command node.
     * @return the passed {@code CommandNode}
     */
    default CommandNode addLeaf(Function<CommandNode, CommandNode> leafProvider) {
        return addLeaf(leafProvider.apply(this));
    }

    /**
     * Set the executor for this node, binding it to a specific sender type.
     *
     * @param <SENDER_TYPE> the type of the command sender
     * @param executor      the executor function to apply
     * @param senderType    the sender type for this executor
     * @return the current {@code CommandNode}
     */
    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    /**
     * Set the executor for this node.
     *
     * @param executor the executor function to apply
     * @return the current {@code CommandNode}
     */
    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    /**
     * Redirect to the root node, which is useful for commands like /execute, which has multiple subcommands.
     *
     * @param onRedirect Called when redirecting
     * @return the root node
     */
    CommandNode redirect(Consumer<CommandContext> onRedirect);

    /**
     * Add a permission requirement for this node. If the command sender does not have the required
     * permission, it will not be able to access this command node. In other words, the sub commands
     * under this node will not be executable for the command sender.
     * <p>
     * When the command is sent to a player, command nodes that the player does not have access to
     * are not sent. This is useful for hiding command nodes from players that do not have access to
     * them.
     *
     * @param permission the permissions to check
     * @return the current {@code CommandNode}
     */
    CommandNode permission(String permission);

    /**
     * Get the permissions required to access this node.
     *
     * @return the permissions
     */
    @UnmodifiableView
    List<String> getPermissions();

    /**
     * Check if the command sender has all the required permissions for accessing this node.
     *
     * @param sender the command sender
     * @return {@code true} if the sender has all permissions, {@code false} otherwise.
     */
    default boolean checkPermissions(CommandSender sender) {
        return sender.hasPermissions(getPermissions());
    }

    /**
     * Get the executor of this node.
     *
     * @return the executor function, or {@code null} if not set
     */
    Function<CommandContext, CommandResult> getExecutor();

    /**
     * Get the onRedirect function of this node.
     *
     * @return the onRedirect function, or {@code null} if not set
     */
    Consumer<CommandContext> getOnRedirect();

    /**
     * Add a key parameter to this command node with a default value.
     *
     * @param key          the key of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode key(String key, String defaultValue) {
        return addLeaf(getFactory().key(key, this, defaultValue));
    }

    /**
     * Add a key parameter to this command node with an empty default value.
     *
     * @param key the key of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode key(String key) {
        return key(key, "");
    }

    /**
     * Add a string parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode str(String name, String defaultValue) {
        return addLeaf(getFactory().str(name, this, defaultValue));
    }

    /**
     * Add a string parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode str(String name) {
        return str(name, "");
    }

    /**
     * Add a short integer parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode shortNum(String name, short defaultValue) {
        return addLeaf(getFactory().shortNum(name, this, defaultValue));
    }

    /**
     * Add a short integer parameter to this command node with a default value of zero.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode shortNum(String name) {
        return shortNum(name, (short) 0);
    }

    /**
     * Add an integer parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode intNum(String name, int defaultValue) {
        return addLeaf(getFactory().intNum(name, this, defaultValue));
    }

    /**
     * Add an integer parameter to this command node with a default value of zero.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    /**
     * Add a long integer parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode longNum(String name, long defaultValue) {
        return addLeaf(getFactory().longNum(name, this, defaultValue));
    }

    /**
     * Add a long integer parameter to this command node with a default value of zero.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode longNum(String name) {
        return longNum(name, 0);
    }

    /**
     * Add a float parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode floatNum(String name, float defaultValue) {
        return addLeaf(getFactory().floatNum(name, this, defaultValue));
    }

    /**
     * Add a float parameter to this command node with a default value of zero.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    /**
     * Add a double parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode doubleNum(String name, double defaultValue) {
        return addLeaf(getFactory().doubleNum(name, this, defaultValue));
    }

    /**
     * Add a double parameter to this command node with a default value of zero.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    /**
     * Add a boolean parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode bool(String name, boolean defaultValue) {
        return addLeaf(getFactory().bool(name, this, defaultValue));
    }

    /**
     * Add a boolean parameter to this command node with a default value of false.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode bool(String name) {
        return bool(name, false);
    }

    /**
     * Add an enum parameter to this command node with a default value and allowed values.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param values       the allowed values for the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enums(String name, String defaultValue, String[] values) {
        return addLeaf(getFactory().enums(name, this, defaultValue, values));
    }

    /**
     * Add an enum parameter to this command node with a default value, allowed values, and an enum name.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param enumName     the name of the enum
     * @param values       the allowed values for the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enums(String name, String defaultValue, String enumName, String[] values) {
        return addLeaf(getFactory().enums(name, this, enumName, defaultValue, values));
    }

    /**
     * Add an enum parameter to this command node with allowed values and an empty default value.
     *
     * @param name   the name of the parameter
     * @param values the allowed values for the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enums(String name, String... values) {
        return enums(name, "", values);
    }

    /**
     * Add an enum parameter to this command node based on the specified enum class, and
     * the default value is set to {@code null}.
     * <p>
     * Unlike {@link #enums}, the string result will then be mapped back to the type of
     * the provided enum class, which means that you don't need to map string back to
     * enum manually.
     *
     * @param name      the name of the command node
     * @param enumClass the class of the enumeration to be used for this command node
     * @return a CommandNode instance configured to accept values from the specified enumeration class
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, Class<T> enumClass) {
        return enumClass(name, null, enumClass);
    }

    /**
     * Add an enum parameter to this command node based on the specified enum class.
     * <p>
     * Unlike {@link #enums}, the string result will then be mapped back to the type of
     * the provided enum class, which means that you don't need to map string back to
     * enum manually.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param enumClass    the class of the enum type
     * @return the {@code CommandNode} that created by this method
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, T defaultValue, Class<T> enumClass) {
        return addLeaf(getFactory().enumClass(name, this, defaultValue, enumClass));
    }

    /**
     * Add an enum parameter to this command node based on the specified enum class.
     * <p>
     * Unlike {@link #enums}, the string result will then be mapped back to the type of
     * the provided enum class, which means that you don't need to map string back to
     * enum manually.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param enumName     the name of the enum
     * @param enumClass    the class of the enum type
     * @return the {@code CommandNode} that created by this method
     */
    default <T extends Enum<?>> CommandNode enumClass(String name, T defaultValue, String enumName, Class<T> enumClass) {
        return addLeaf(getFactory().enumClass(name, this, defaultValue, enumName, enumClass));
    }

    /**
     * Add an enum parameter to this command node with a default value and allowed values, ignoring case.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param enums        the allowed values for the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, defaultValue, enums));
    }

    /**
     * Add an enum parameter to this command node with a default value, allowed values, and an enum name, ignoring case.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param enumName     the name of the enum
     * @param enums        the allowed values for the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, enumName, defaultValue, enums));
    }

    /**
     * Add a message parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode msg(String name) {
        return msg(name, "");
    }

    /**
     * Add a message parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode msg(String name, String defaultValue) {
        return addLeaf(getFactory().msg(name, this, defaultValue));
    }

    /**
     * Add a remaining parameters node to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode remain(String name) {
        return remain(name, Lists.newArrayList());
    }

    /**
     * Add a remaining parameters node to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode remain(String name, List<String> defaultValue) {
        return addLeaf(getFactory().remain(name, this, defaultValue));
    }

    /**
     * Add a target parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode target(String name) {
        return addLeaf(getFactory().target(name, this, Lists.newArrayList()));
    }

    /**
     * Add a target parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode target(String name, List<Entity> defaultValue) {
        return addLeaf(getFactory().target(name, this, defaultValue));
    }

    /**
     * Add a player target parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode playerTarget(String name) {
        return addLeaf(getFactory().playerTarget(name, this, Lists.newArrayList()));
    }

    /**
     * Add a player target parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode playerTarget(String name, List<EntityPlayer> defaultValue) {
        return addLeaf(getFactory().playerTarget(name, this, defaultValue));
    }

    /**
     * Add a game mode parameter to this command node with a default name of "gameMode".
     *
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode gameMode() {
        return gameMode("gameMode");
    }

    /**
     * Add a game mode parameter to this command node with a specified name.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode gameMode(String name) {
        return addLeaf(getFactory().gameMode(name, this, null));
    }

    /**
     * Add a game mode parameter to this command node with a specified name and default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode gameMode(String name, GameMode defaultValue) {
        return addLeaf(getFactory().gameMode(name, this, defaultValue));
    }

    /**
     * Add a position parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode pos(String name) {
        return pos(name, null);
    }

    /**
     * Add a position parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode pos(String name, Vector3fc defaultValue) {
        return addLeaf(getFactory().pos(name, this, defaultValue));
    }

    /**
     * Add a wildcard target parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode wildcardTarget(String name) {
        return wildcardTarget(name, "");
    }

    /**
     * Add a wildcard target parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode wildcardTarget(String name, String defaultValue) {
        return addLeaf(getFactory().wildcardTarget(name, this, defaultValue));
    }

    /**
     * Add an enchantment parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enchantment(String name) {
        return enchantment(name, null);
    }

    /**
     * Add an enchantment parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode enchantment(String name, EnchantmentType defaultValue) {
        return addLeaf(getFactory().enchantment(name, this, defaultValue));
    }

    /**
     * Add an effect parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode effect(String name) {
        return effect(name, null);
    }

    /**
     * Add an effect parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode effect(String name, EffectType defaultValue) {
        return addLeaf(getFactory().effect(name, this, defaultValue));
    }

    /**
     * Add an item type parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode itemType(String name) {
        return itemType(name, null);
    }

    /**
     * Add an item type parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode itemType(String name, ItemType<?> defaultValue) {
        return addLeaf(getFactory().itemType(name, this, defaultValue));
    }

    /**
     * Add a block type parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode blockType(String name) {
        return blockType(name, null);
    }

    /**
     * Add a block type parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode blockType(String name, BlockType<?> defaultValue) {
        return addLeaf(getFactory().blockType(name, this, defaultValue));
    }

    /**
     * Add an entity type parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode entityType(String name) {
        return entityType(name, null);
    }

    /**
     * Add an entity type parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode entityType(String name, EntityType<?> defaultValue) {
        return addLeaf(getFactory().entityType(name, this, defaultValue));
    }

    /**
     * Add a difficulty parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode difficulty(String name) {
        return difficulty(name, null);
    }

    /**
     * Add a difficulty parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode difficulty(String name, Difficulty defaultValue) {
        return addLeaf(getFactory().difficulty(name, this, defaultValue));
    }

    /**
     * Add a block property values parameter to this command node with an empty default value.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode blockPropertyValues(String name) {
        return blockPropertyValues(name, Lists.newArrayList());
    }

    /**
     * Add a block property values parameter to this command node with a default value.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode blockPropertyValues(String name, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        return addLeaf(getFactory().blockPropertyValues(name, this, defaultValue));
    }

    /**
     * Add a command parameter to this command node with a default value of {@code null}.
     *
     * @param name the name of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode cmd(String name) {
        return cmd(name, "");
    }

    /**
     * Add a command parameter to this command node with a default value of {@code null}.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     * @return the {@code CommandNode} that created by this method
     */
    default CommandNode cmd(String name, String defaultValue) {
        return addLeaf(getFactory().cmd(name, this, defaultValue));
    }
}
