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
import org.allaymc.api.world.Difficulty;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamOption;
import org.joml.Vector3fc;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.allaymc.api.command.tree.CommandNodeFactory.getFactory;

/**
 * @author daoge_cmd
 */
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
     * @return The default value.
     */
    Object getDefaultValue();

    /**
     * Return the minimum number of arguments consumed after selecting this branch.
     *
     * @return The minimum number of arguments required.
     */
    int getMinArgCostBranch();

    /**
     * Set the minimum number of arguments consumed after selecting this branch.
     *
     * @param minArgCostBranch The minimum number of arguments.
     */
    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * Return the maximum number of arguments consumed after selecting this branch.
     *
     * @return The maximum number of arguments required.
     */
    int getMaxArgCostBranch();

    /**
     * Set the maximum number of arguments consumed after selecting this branch.
     *
     * @param maxArgCostBranch The maximum number of arguments.
     */
    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * Return the maximum number of arguments this node can consume.
     * A sufficiently large number is returned to indicate an infinite argument count.
     *
     * @return The maximum number of arguments.
     */
    default int getMaxArgCost() {
        return 1;
    }

    /**
     * Set a default value for this node and marks it as optional.
     *
     * @param defaultValue The default value to set.
     *
     * @return This {@code CommandNode}.
     */
    CommandNode defaultValue(Object defaultValue);

    /**
     * Marks this node as optional.
     *
     * @return This {@code CommandNode}.
     */
    CommandNode optional();

    /**
     * Retrieves the optional leaf node for this command.
     *
     * @return The optional leaf node.
     */
    CommandNode getOptionalLeaf();

    /**
     * Set an optional leaf node for this command.
     *
     * @param optionalLeaf The optional leaf to set.
     */
    void setOptionalLeaf(CommandNode optionalLeaf);

    /**
     * Retrieves the name of this command node.
     *
     * @return The name of the command node.
     */
    String name();

    /**
     * Retrieves the depth of this node in the command tree.
     *
     * @return The depth of the node.
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
     * @param depth The depth to set.
     */
    void setDepth(int depth);

    /**
     * Retrieves the parent of this node.
     *
     * @return The parent {@code CommandNode}.
     */
    CommandNode parent();

    /**
     * Moves up a certain number of levels in the command tree.
     *
     * @param count The number of levels to move up.
     *
     * @return The {@code CommandNode} after moving up.
     */
    CommandNode up(int count);

    /**
     * Moves up one level in the command tree.
     *
     * @return The parent {@code CommandNode}.
     */
    default CommandNode up() {
        return up(1);
    }

    /**
     * Return the root node of the command tree by moving up all levels.
     *
     * @return The root {@code CommandNode}.
     */
    default CommandNode root() {
        return up(depth());
    }

    /**
     * Checks if this node matches the current command context.
     *
     * @param context The command context.
     *
     * @return {@code true} if the node matches, {@code false} otherwise.
     */
    boolean match(CommandContext context);

    /**
     * Retrieves the next node in the command tree based on the current context.
     *
     * @param context The command context.
     *
     * @return The next {@code CommandNode}.
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
     * @return A list of {@code CommandNode} leaves.
     */
    List<CommandNode> getLeaves();

    /**
     * Adds a leaf node to this command node.
     *
     * @param leaf The leaf node to add.
     *
     * @return This {@code CommandNode}.
     */
    CommandNode addLeaf(CommandNode leaf);

    /**
     * Set the executor for this node, binding it to a specific sender type.
     *
     * @param <SENDER_TYPE> The type of the command sender.
     * @param executor      The executor function to apply.
     * @param senderType    The sender type for this executor.
     *
     * @return This {@code CommandNode}.
     */
    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    /**
     * Set the executor for this node.
     *
     * @param executor The executor function to apply.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    /**
     * Applies the executor for this node to the given context.
     *
     * @param context The command context.
     *
     * @return The {@code CommandResult} after execution.
     */
    CommandResult applyExecutor(CommandContext context);

    /**
     * Adds a parameter option to this command node.
     *
     * @param option The parameter option to add.
     *
     * @return This {@code CommandNode}.
     */
    CommandNode addParamOption(CommandParamOption option);

    /**
     * Suppresses automatic enum completion for this node.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode suppressEnumAutoCompletion() {
        return addParamOption(CommandParamOption.SUPPRESS_ENUM_AUTOCOMPLETION);
    }

    /**
     * Marks this node as having a semantic constraint.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode hasSemanticConstraint() {
        return addParamOption(CommandParamOption.HAS_SEMANTIC_CONSTRAINT);
    }

    /**
     * Marks this node as an enum that behaves as a chained command.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enumAsChainedCommand() {
        return addParamOption(CommandParamOption.ENUM_AS_CHAINED_COMMAND);
    }

    /**
     * Converts this command node to network data for communication.
     *
     * @return The network data representation of this node.
     */
    CommandParamData toNetworkData();

    /**
     * Adds a key parameter to this command node with a default value.
     *
     * @param key          The key of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode key(String key, String defaultValue) {
        return addLeaf(getFactory().key(key, this, defaultValue));
    }

    /**
     * Adds a key parameter to this command node with an empty default value.
     *
     * @param key The key of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode key(String key) {
        return key(key, "");
    }

    /**
     * Adds a string parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode str(String name, String defaultValue) {
        return addLeaf(getFactory().str(name, this, defaultValue));
    }

    /**
     * Adds a string parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode str(String name) {
        return str(name, "");
    }

    /**
     * Adds a short integer parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode shortNum(String name, short defaultValue) {
        return addLeaf(getFactory().shortNum(name, this, defaultValue));
    }

    /**
     * Adds a short integer parameter to this command node with a default value of zero.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode shortNum(String name) {
        return shortNum(name, (short) 0);
    }

    /**
     * Adds an integer parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode intNum(String name, int defaultValue) {
        return addLeaf(getFactory().intNum(name, this, defaultValue));
    }

    /**
     * Adds an integer parameter to this command node with a default value of zero.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    /**
     * Adds a long integer parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode longNum(String name, long defaultValue) {
        return addLeaf(getFactory().longNum(name, this, defaultValue));
    }

    /**
     * Adds a long integer parameter to this command node with a default value of zero.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode longNum(String name) {
        return longNum(name, 0);
    }

    /**
     * Adds a float parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode floatNum(String name, float defaultValue) {
        return addLeaf(getFactory().floatNum(name, this, defaultValue));
    }

    /**
     * Adds a float parameter to this command node with a default value of zero.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    /**
     * Adds a double parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode doubleNum(String name, double defaultValue) {
        return addLeaf(getFactory().doubleNum(name, this, defaultValue));
    }

    /**
     * Adds a double parameter to this command node with a default value of zero.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    /**
     * Adds a boolean parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode bool(String name, boolean defaultValue) {
        return addLeaf(getFactory().bool(name, this, defaultValue));
    }

    /**
     * Adds a boolean parameter to this command node with a default value of false.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode bool(String name) {
        return bool(name, false);
    }

    /**
     * Adds an enum parameter to this command node with a default value and allowed values.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     * @param enums        The allowed values for the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enums(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enums(name, this, defaultValue, enums));
    }

    /**
     * Adds an enum parameter to this command node with a default value, allowed values, and an enum name.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     * @param enumName     The name of the enum.
     * @param enums        The allowed values for the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enums(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enums(name, this, enumName, defaultValue, enums));
    }

    /**
     * Adds an enum parameter to this command node with allowed values and an empty default value.
     *
     * @param name  The name of the parameter.
     * @param enums The allowed values for the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enums(String name, String... enums) {
        return enums(name, "", enums);
    }

    /**
     * Adds an enum parameter to this command node based on the specified enum class.
     *
     * @param name      The name of the parameter.
     * @param enumClass The class of the enum type.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enums(String name, Class<? extends Enum<?>> enumClass) {
        var enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name().toLowerCase();
        }
        return enums(name, values);
    }

    /**
     * Adds an enum parameter to this command node with a default value and allowed values, ignoring case.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     * @param enums        The allowed values for the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, defaultValue, enums));
    }

    /**
     * Adds an enum parameter to this command node with a default value, allowed values, and an enum name, ignoring case.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     * @param enumName     The name of the enum.
     * @param enums        The allowed values for the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enumsIgnoreCase(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, enumName, defaultValue, enums));
    }

    /**
     * Adds a message parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode msg(String name) {
        return msg(name, "");
    }

    /**
     * Adds a message parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode msg(String name, String defaultValue) {
        return addLeaf(getFactory().msg(name, this, defaultValue));
    }

    /**
     * Adds a remaining parameters node to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode remain(String name) {
        return remain(name, Lists.newArrayList());
    }

    /**
     * Adds a remaining parameters node to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode remain(String name, Object defaultValue) {
        return addLeaf(getFactory().remain(name, this, defaultValue));
    }

    /**
     * Adds a target parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode target(String name) {
        return addLeaf(getFactory().target(name, this, Lists.newArrayList()));
    }

    /**
     * Adds a target parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode target(String name, List<Entity> defaultValue) {
        return addLeaf(getFactory().target(name, this, defaultValue));
    }

    /**
     * Adds a player target parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode playerTarget(String name) {
        return addLeaf(getFactory().playerTarget(name, this, Lists.newArrayList()));
    }

    /**
     * Adds a player target parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode playerTarget(String name, List<EntityPlayer> defaultValue) {
        return addLeaf(getFactory().playerTarget(name, this, defaultValue));
    }

    /**
     * Adds a game mode parameter to this command node with a default name of "gameMode".
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode gameMode() {
        return gameMode("gameMode");
    }

    /**
     * Adds a game mode parameter to this command node with a specified name.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode gameMode(String name) {
        return addLeaf(getFactory().gameMode(name, this, null));
    }

    /**
     * Adds a game mode parameter to this command node with a specified name and default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode gameMode(String name, GameType defaultValue) {
        return addLeaf(getFactory().gameMode(name, this, defaultValue));
    }

    /**
     * Adds a position parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode pos(String name) {
        return pos(name, null);
    }

    /**
     * Adds a position parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode pos(String name, Vector3fc defaultValue) {
        return addLeaf(getFactory().pos(name, this, defaultValue));
    }

    /**
     * Adds a wildcard target parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode wildcardTarget(String name) {
        return wildcardTarget(name, "");
    }

    /**
     * Adds a wildcard target parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode wildcardTarget(String name, String defaultValue) {
        return addLeaf(getFactory().wildcardTarget(name, this, defaultValue));
    }

    /**
     * Adds an enchantment parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enchantment(String name) {
        return enchantment(name, null);
    }

    /**
     * Adds an enchantment parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode enchantment(String name, EnchantmentType defaultValue) {
        return addLeaf(getFactory().enchantment(name, this, defaultValue));
    }

    /**
     * Adds an effect parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode effect(String name) {
        return effect(name, null);
    }

    /**
     * Adds an effect parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode effect(String name, EffectType defaultValue) {
        return addLeaf(getFactory().effect(name, this, defaultValue));
    }

    /**
     * Adds an item type parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode itemType(String name) {
        return itemType(name, null);
    }

    /**
     * Adds an item type parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode itemType(String name, ItemType<?> defaultValue) {
        return addLeaf(getFactory().itemType(name, this, defaultValue));
    }

    /**
     * Adds a block type parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode blockType(String name) {
        return blockType(name, null);
    }

    /**
     * Adds a block type parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode blockType(String name, BlockType<?> defaultValue) {
        return addLeaf(getFactory().blockType(name, this, defaultValue));
    }

    /**
     * Adds an entity type parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode entityType(String name) {
        return entityType(name, null);
    }

    /**
     * Adds an entity type parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode entityType(String name, EntityType<?> defaultValue) {
        return addLeaf(getFactory().entityType(name, this, defaultValue));
    }

    /**
     * Adds a difficulty parameter to this command node with a default value of null.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode difficulty(String name) {
        return difficulty(name, null);
    }

    /**
     * Adds a difficulty parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode difficulty(String name, Difficulty defaultValue) {
        return addLeaf(getFactory().difficulty(name, this, defaultValue));
    }

    /**
     * Adds a block property values parameter to this command node with an empty default value.
     *
     * @param name The name of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode blockPropertyValues(String name) {
        return blockPropertyValues(name, Lists.newArrayList());
    }

    /**
     * Adds a block property values parameter to this command node with a default value.
     *
     * @param name         The name of the parameter.
     * @param defaultValue The default value of the parameter.
     *
     * @return This {@code CommandNode}.
     */
    default CommandNode blockPropertyValues(String name, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        return addLeaf(getFactory().blockPropertyValues(name, this, defaultValue));
    }
}
