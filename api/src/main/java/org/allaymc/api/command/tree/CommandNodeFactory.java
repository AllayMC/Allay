package org.allaymc.api.command.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Difficulty;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3fc;

import java.util.List;

/**
 * The {@code CommandNodeFactory} interface defines the structure for creating various types of
 * command nodes. Each command node can hold a specific type of data, such as strings, numbers,
 * game elements, and entities, and it defines how arguments are processed in the command execution tree.
 * <p>
 * This factory is held as an {@link ApiInstanceHolder} and can be accessed using {@link #getFactory()}.
 * The factory allows users to create nodes with different types, and their default values, which are
 * used in command arguments parsing.
 * </p>
 *
 * @author daoge_cmd
 */
public interface CommandNodeFactory {
    ApiInstanceHolder<CommandNodeFactory> FACTORY = ApiInstanceHolder.create();

    static CommandNodeFactory getFactory() {
        return FACTORY.get();
    }

    /**
     * Creates a command node for a key-based argument.
     *
     * @param key          the key name.
     * @param parent       the parent node.
     * @param defaultValue the default value of the key.
     *
     * @return a new {@code CommandNode} for the key.
     */
    CommandNode key(String key, CommandNode parent, String defaultValue);

    /**
     * Creates a command node for a string-based argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default string value.
     *
     * @return a new {@code CommandNode} for the string argument.
     */
    CommandNode str(String name, CommandNode parent, String defaultValue);

    /**
     * Creates a command node for a short number argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default short value.
     *
     * @return a new {@code CommandNode} for the short number argument.
     */
    CommandNode shortNum(String name, CommandNode parent, short defaultValue);

    /**
     * Creates a command node for an integer argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default integer value.
     *
     * @return a new {@code CommandNode} for the integer argument.
     */
    CommandNode intNum(String name, CommandNode parent, int defaultValue);

    /**
     * Creates a command node for a long number argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default long value.
     *
     * @return a new {@code CommandNode} for the long number argument.
     */
    CommandNode longNum(String name, CommandNode parent, long defaultValue);

    /**
     * Creates a command node for a float number argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default float value.
     *
     * @return a new {@code CommandNode} for the float number argument.
     */
    CommandNode floatNum(String name, CommandNode parent, float defaultValue);

    /**
     * Creates a command node for a double number argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default double value.
     *
     * @return a new {@code CommandNode} for the double number argument.
     */
    CommandNode doubleNum(String name, CommandNode parent, double defaultValue);

    /**
     * Creates a command node for a boolean argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default boolean value.
     *
     * @return a new {@code CommandNode} for the boolean argument.
     */
    CommandNode bool(String name, CommandNode parent, boolean defaultValue);

    /**
     * Creates a command node for an enum argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default enum value as a string.
     * @param enums        the available enum options.
     *
     * @return a new {@code CommandNode} for the enum argument.
     */
    CommandNode enums(String name, CommandNode parent, String defaultValue, String[] enums);

    /**
     * Creates a command node for an enum argument, allowing a custom enum name.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param enumName     the name of the enum.
     * @param defaultValue the default enum value as a string.
     * @param enums        the available enum options.
     *
     * @return a new {@code CommandNode} for the enum argument.
     */
    CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] enums);

    /**
     * Creates a command node for an enum argument, ignoring case.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default enum value as a string.
     * @param enums        the available enum options.
     *
     * @return a new {@code CommandNode} for the case-insensitive enum argument.
     */
    CommandNode enumsIgnoreCase(String name, CommandNode parent, String defaultValue, String[] enums);

    /**
     * Creates a command node for an enum argument with a custom name, ignoring case.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param enumName     the custom name of the enum.
     * @param defaultValue the default enum value as a string.
     * @param enums        the available enum options.
     *
     * @return a new {@code CommandNode} for the case-insensitive enum argument.
     */
    CommandNode enumsIgnoreCase(String name, CommandNode parent, String enumName, String defaultValue, String[] enums);

    /**
     * Creates a command node for a message argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default message value.
     *
     * @return a new {@code CommandNode} for the message argument.
     */
    CommandNode msg(String name, CommandNode parent, String defaultValue);

    /**
     * Creates a command node for the remaining arguments.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default value for the remaining arguments.
     *
     * @return a new {@code CommandNode} for the remaining arguments.
     */
    CommandNode remain(String name, CommandNode parent, Object defaultValue);

    /**
     * Creates a command node for a target argument that allows selecting multiple entities.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default target entities.
     *
     * @return a new {@code CommandNode} for the target argument.
     */
    CommandNode target(String name, CommandNode parent, List<Entity> defaultValue);

    /**
     * Creates a command node for a player target argument that allows selecting multiple player entities.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default target player entities.
     *
     * @return a new {@code CommandNode} for the player target argument.
     */
    CommandNode playerTarget(String name, CommandNode parent, List<EntityPlayer> defaultValue);

    /**
     * Creates a command node for a game mode argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default game mode.
     *
     * @return a new {@code CommandNode} for the game mode argument.
     */
    CommandNode gameMode(String name, CommandNode parent, GameType defaultValue);

    /**
     * Creates a command node for a position argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default position (vector).
     *
     * @return a new {@code CommandNode} for the position argument.
     */
    CommandNode pos(String name, CommandNode parent, Vector3fc defaultValue);

    /**
     * Creates a command node for a wildcard target argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default wildcard target.
     *
     * @return a new {@code CommandNode} for the wildcard target argument.
     */
    CommandNode wildcardTarget(String name, CommandNode parent, String defaultValue);

    /**
     * Creates a command node for an enchantment type argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default enchantment type.
     *
     * @return a new {@code CommandNode} for the enchantment type argument.
     */
    CommandNode enchantment(String name, CommandNode parent, EnchantmentType defaultValue);

    /**
     * Creates a command node for an effect type argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default effect type.
     *
     * @return a new {@code CommandNode} for the effect type argument.
     */
    CommandNode effect(String name, CommandNode parent, EffectType defaultValue);

    /**
     * Creates a command node for an item type argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default item type.
     *
     * @return a new {@code CommandNode} for the item type argument.
     */
    CommandNode itemType(String name, CommandNode parent, ItemType<?> defaultValue);

    /**
     * Creates a command node for a block type argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default block type.
     *
     * @return a new {@code CommandNode} for the block type argument.
     */
    CommandNode blockType(String name, CommandNode parent, BlockType<?> defaultValue);

    /**
     * Creates a command node for a difficulty argument.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default difficulty level.
     *
     * @return a new {@code CommandNode} for the difficulty argument.
     */
    CommandNode difficulty(String name, CommandNode parent, Difficulty defaultValue);

    /**
     * Creates a command node for block property values.
     *
     * @param name         the name of the argument.
     * @param parent       the parent node.
     * @param defaultValue the default list of block property values.
     *
     * @return a new {@code CommandNode} for block property values.
     */
    CommandNode blockPropertyValues(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue);
}
