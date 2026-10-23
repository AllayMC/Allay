package org.allaymc.api.command.tree;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.joml.Vector3fc;

import java.util.List;

/**
 * {@code CommandNodeFactory} creates parameter nodes for the installed
 * command-tree implementation. Factory methods retain a parent reference but do
 * not attach the new node; callers use {@code CommandNode.addLeaf} to complete
 * attachment. Supplied defaults are stored directly and become omission results
 * only after callers mark a node optional.
 *
 * @author daoge_cmd
 */
public interface CommandNodeFactory {
    /**
     * {@code FACTORY} holds the installed parameter-node factory. Server
     * initialization must populate it before fluent tree builders request nodes
     * through {@code getFactory}.
     */
    AllayAPI.APIInstanceHolder<CommandNodeFactory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Returns the installed node factory from its shared API instance holder.
     */
    static CommandNodeFactory getFactory() {
        return FACTORY.get();
    }

    /**
     * Creates an unattached literal-key node producing {@code String} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The configured key
     * is lowercased at construction and matching is case-sensitive.
     */
    CommandNode key(String key, CommandNode parent, String defaultValue);

    /**
     * Creates an unattached single-token string node producing {@code String}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher retains
     * the supplied token without additional quote processing.
     */
    CommandNode str(String name, CommandNode parent, String defaultValue);

    /**
     * Creates an unattached short integer node producing {@code Short} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher uses the
     * signed decimal short parser.
     */
    CommandNode shortNum(String name, CommandNode parent, short defaultValue);

    /**
     * Creates an unattached integer node producing {@code Integer} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher uses the
     * signed decimal integer parser.
     */
    CommandNode intNum(String name, CommandNode parent, int defaultValue);

    /**
     * Creates an unattached long integer node producing {@code Long} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher uses the
     * signed decimal long parser.
     */
    CommandNode longNum(String name, CommandNode parent, long defaultValue);

    /**
     * Creates an unattached floating-point node producing {@code Float} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher uses the
     * Java floating-point parser.
     */
    CommandNode floatNum(String name, CommandNode parent, float defaultValue);

    /**
     * Creates an unattached double-precision node producing {@code Double}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher uses the
     * Java double-precision parser.
     */
    CommandNode doubleNum(String name, CommandNode parent, double defaultValue);

    /**
     * Creates an unattached boolean node producing {@code Boolean} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher accepts
     * only the exact tokens {@code "true"} and {@code "false"}.
     */
    CommandNode bool(String name, CommandNode parent, boolean defaultValue);

    /**
     * Creates an unattached case-sensitive enumeration node producing
     * {@code String} during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The server lowercases the supplied values array in place
     * during construction.
     */
    CommandNode enums(String name, CommandNode parent, String defaultValue, String[] enums);

    /**
     * Creates an unattached case-sensitive enumeration node producing
     * {@code String} during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The server lowercases the supplied values array in place
     * during construction.
     */
    CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] values);

    /**
     * Creates an unattached case-insensitive enumeration node producing
     * {@code String} during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The server lowercases the supplied values array in place
     * while preserving matched input in the result.
     */
    CommandNode enumsIgnoreCase(String name, CommandNode parent, String defaultValue, String[] enums);

    /**
     * Creates an unattached case-insensitive enumeration node producing
     * {@code String} during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The server lowercases the supplied values array in place
     * while preserving matched input in the result.
     */
    CommandNode enumsIgnoreCase(String name, CommandNode parent, String enumName, String defaultValue, String[] enums);

    /**
     * Creates an unattached enum-class node producing the matching enum
     * constant during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The matcher accepts enum constant names without case
     * sensitivity.
     */
    <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, Class<T> enumClass);


    /**
     * Creates an unattached enum-class node producing the matching enum
     * constant during parsing. The node retains {@code parent} and
     * {@code defaultValue}; callers attach it and enable optionality
     * separately. The matcher accepts enum constant names without case
     * sensitivity.
     */
    <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, String enumName, Class<T> enumClass);

    /**
     * Creates an unattached remaining-message node producing {@code String}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher consumes
     * all remaining tokens and joins them with spaces.
     */
    CommandNode msg(String name, CommandNode parent, String defaultValue);

    /**
     * Creates an unattached remaining-token node producing {@code List<String>}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher consumes
     * all remaining tokens and keeps their individual values in a new list.
     */
    CommandNode remain(String name, CommandNode parent, List<String> defaultValue);

    /**
     * Creates an unattached entity-target node producing {@code List<Entity>}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher accepts
     * a selector or player name and permits an empty selection.
     */
    CommandNode target(String name, CommandNode parent, List<Entity> defaultValue);

    /**
     * Creates an unattached player-target node producing
     * {@code List<EntityPlayer>} during parsing. The node retains
     * {@code parent} and {@code defaultValue}; callers attach it and enable
     * optionality separately. The matcher accepts a selector or player name and
     * removes non-player entities.
     */
    CommandNode playerTarget(String name, CommandNode parent, List<EntityPlayer> defaultValue);

    /**
     * Creates an unattached game-mode node producing {@code GameMode} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher accepts
     * exact lowercase aliases, including {@code "3"}, {@code "spectator"}, and
     * {@code "spc"} for spectator mode.
     */
    CommandNode gameMode(String name, CommandNode parent, GameMode defaultValue);

    /**
     * Creates an unattached position node whose matched result is
     * {@code org.joml.Vector3d}. Optional omission instead returns the retained
     * {@code Vector3fc} default without conversion, so executors must
     * distinguish those result types. The caller attaches the node and
     * configures optionality separately.
     */
    CommandNode pos(String name, CommandNode parent, Vector3fc defaultValue);

    /**
     * Creates an unattached wildcard-target node producing {@code String}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher accepts
     * one token without resolving it into entities.
     */
    CommandNode wildcardTarget(String name, CommandNode parent, String defaultValue);

    /**
     * Creates an unattached enchantment-type node producing
     * {@code EnchantmentType} during parsing. The node retains {@code parent}
     * and {@code defaultValue}; callers attach it and enable optionality
     * separately. The matcher accepts snapshotted full enchantment identifiers
     * or their bare paths; bare paths use the default namespace for lookup.
     */
    CommandNode enchantment(String name, CommandNode parent, EnchantmentType defaultValue);

    /**
     * Creates an unattached effect-type node producing {@code EffectType}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher accepts
     * snapshotted bare effect paths and resolves them in the default namespace.
     */
    CommandNode effect(String name, CommandNode parent, EffectType defaultValue);

    /**
     * Creates an unattached item-type node producing {@code ItemType<?>} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher resolves
     * a registered item identifier through the item registry.
     */
    CommandNode itemType(String name, CommandNode parent, ItemType<?> defaultValue);

    /**
     * Creates an unattached block-type node producing {@code BlockType<?>}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher resolves
     * a registered block identifier through the block registry.
     */
    CommandNode blockType(String name, CommandNode parent, BlockType<?> defaultValue);

    /**
     * Creates an unattached entity-type node producing {@code EntityType<?>}
     * during parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher resolves
     * a registered entity identifier through the entity registry.
     */
    CommandNode entityType(String name, CommandNode parent, EntityType<?> defaultValue);

    /**
     * Creates an unattached difficulty node producing {@code Difficulty} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The matcher
     * recognizes the difficulty parser's supported numeric and named aliases.
     */
    CommandNode difficulty(String name, CommandNode parent, Difficulty defaultValue);

    /**
     * Creates an unattached block-property node producing
     * {@code List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>} during
     * parsing. The node retains {@code parent} and {@code defaultValue};
     * callers attach it and enable optionality separately. The parent must be a
     * block-type node because parsing reads the preceding block result.
     */
    CommandNode blockPropertyValues(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue);

    /**
     * Creates an unattached node for reconstructing remaining tokens as command
     * text. Matching joins and trims tokens, re-quotes literal-space tokens,
     * and leaves embedded quotes unescaped; redispatch callers must account for
     * those limits. Optional omission inserts the retained
     * {@code defaultValue}.
     */
    CommandNode cmd(String name, CommandNode parent, String defaultValue);
}
