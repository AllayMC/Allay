package org.allaymc.server.command.tree;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.command.tree.node.*;
import org.joml.Vector3fc;

import java.util.List;

/**
 * {@code AllayCommandNodeFactory} instantiates the server's concrete
 * parameter-node implementations for fluent command-tree builders. Each result
 * retains its supplied parent and default; this factory performs no child
 * attachment. Enumeration nodes may normalize caller-provided arrays during
 * construction, while block-property nodes require a block-type parent.
 *
 * @author daoge_cmd
 */
public class AllayCommandNodeFactory implements CommandNodeFactory {
    /**
     * Creates the server's literal-key implementation producing {@code String}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * configured key is lowercased at construction and matching is
     * case-sensitive.
     */
    @Override
    public CommandNode key(String key, CommandNode parent, String defaultValue) {
        return new KeyNode(key, parent, defaultValue);
    }

    /**
     * Creates the server's single-token string implementation producing
     * {@code String} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher retains the supplied token without additional quote processing.
     */
    @Override
    public CommandNode str(String name, CommandNode parent, String defaultValue) {
        return new StringNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's short integer implementation producing {@code Short}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * matcher uses the signed decimal short parser.
     */
    @Override
    public CommandNode shortNum(String name, CommandNode parent, short defaultValue) {
        return new ShortNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's integer implementation producing {@code Integer}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * matcher uses the signed decimal integer parser.
     */
    @Override
    public CommandNode intNum(String name, CommandNode parent, int defaultValue) {
        return new IntNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's long integer implementation producing {@code Long}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * matcher uses the signed decimal long parser.
     */
    @Override
    public CommandNode longNum(String name, CommandNode parent, long defaultValue) {
        return new LongNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's floating-point implementation producing
     * {@code Float} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher uses the Java floating-point parser.
     */
    @Override
    public CommandNode floatNum(String name, CommandNode parent, float defaultValue) {
        return new FloatNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's double-precision implementation producing
     * {@code Double} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher uses the Java double-precision parser.
     */
    @Override
    public CommandNode doubleNum(String name, CommandNode parent, double defaultValue) {
        return new DoubleNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's boolean implementation producing {@code Boolean}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * matcher accepts only the exact tokens {@code "true"} and {@code "false"}.
     */
    @Override
    public CommandNode bool(String name, CommandNode parent, boolean defaultValue) {
        return new BooleanNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's case-sensitive enumeration implementation producing
     * {@code String} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * server lowercases the supplied values array in place during construction.
     */
    @Override
    public CommandNode enums(String name, CommandNode parent, String defaultValue, String[] enums) {
        return new EnumNode(name, parent, defaultValue, enums);
    }

    /**
     * Creates the server's case-sensitive enumeration implementation producing
     * {@code String} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * server lowercases the supplied values array in place during construction.
     */
    @Override
    public CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] values) {
        return new EnumNode(name, parent, defaultValue, enumName, values);
    }

    /**
     * Creates the server's case-insensitive enumeration implementation
     * producing {@code String} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The server lowercases the supplied values array in place while
     * preserving matched input in the result.
     */
    @Override
    public CommandNode enumsIgnoreCase(String name, CommandNode parent, String defaultValue, String[] enums) {
        return new EnumIgnoreCaseNode(name, parent, defaultValue, enums);
    }

    /**
     * Creates the server's case-insensitive enumeration implementation
     * producing {@code String} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The server lowercases the supplied values array in place while
     * preserving matched input in the result.
     */
    @Override
    public CommandNode enumsIgnoreCase(String name, CommandNode parent, String enumName, String defaultValue, String[] enums) {
        return new EnumIgnoreCaseNode(name, parent, defaultValue, enumName, enums);
    }

    /**
     * Creates the server's enum-class implementation producing the matching
     * enum constant during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher accepts enum constant names without case sensitivity.
     */
    @Override
    public <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, Class<T> enumClass) {
        return new EnumClassNode(name, parent, defaultValue, enumClass);
    }

    /**
     * Creates the server's enum-class implementation producing the matching
     * enum constant during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher accepts enum constant names without case sensitivity.
     */
    @Override
    public <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, String enumName, Class<T> enumClass) {
        return new EnumClassNode(name, parent, defaultValue, enumName, enumClass);
    }

    /**
     * Creates the server's remaining-message implementation producing
     * {@code String} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher consumes all remaining tokens and joins them with spaces.
     */
    @Override
    public CommandNode msg(String name, CommandNode parent, String defaultValue) {
        return new MessageNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's remaining-token implementation producing
     * {@code List<String>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher consumes all remaining tokens and keeps their
     * individual values in a new list.
     */
    @Override
    public CommandNode remain(String name, CommandNode parent, List<String> defaultValue) {
        return new RemainArgNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's entity-target implementation producing
     * {@code List<Entity>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher accepts a selector or player name and permits an
     * empty selection.
     */
    @Override
    public CommandNode target(String name, CommandNode parent, List<Entity> defaultValue) {
        return new TargetNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's player-target implementation producing
     * {@code List<EntityPlayer>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher accepts a selector or player name and removes
     * non-player entities.
     */
    @Override
    public CommandNode playerTarget(String name, CommandNode parent, List<EntityPlayer> defaultValue) {
        return new PlayerTargetNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's game-mode implementation producing {@code GameMode}
     * during parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * matcher accepts exact lowercase aliases, including {@code "3"},
     * {@code "spectator"}, and {@code "spc"} for spectator mode.
     */
    @Override
    public CommandNode gameMode(String name, CommandNode parent, GameMode defaultValue) {
        return new GameModeNode(name, parent, defaultValue);
    }

    /**
     * Creates the server position node with a {@code Vector3d} matched result.
     * Optional omission returns the original {@code Vector3fc} default without
     * conversion, so executors must handle that type difference. The parent is
     * retained, and the caller attaches the node separately.
     */
    @Override
    public CommandNode pos(String name, CommandNode parent, Vector3fc defaultValue) {
        return new PosNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's wildcard-target implementation producing
     * {@code String} during parsing. The returned node retains {@code parent}
     * and {@code defaultValue}; its caller must attach it before traversal. The
     * matcher accepts one token without resolving it into entities.
     */
    @Override
    public CommandNode wildcardTarget(String name, CommandNode parent, String defaultValue) {
        return new WildcardTargetNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's enchantment-type implementation producing
     * {@code EnchantmentType} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher accepts snapshotted full enchantment identifiers
     * or their bare paths; bare paths use the default namespace for lookup.
     */
    @Override
    public CommandNode enchantment(String name, CommandNode parent, EnchantmentType defaultValue) {
        return new EnchantmentNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's effect-type implementation producing
     * {@code EffectType} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher accepts snapshotted bare effect paths and resolves
     * them in the default namespace.
     */
    @Override
    public CommandNode effect(String name, CommandNode parent, EffectType defaultValue) {
        return new EffectNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's item-type implementation producing
     * {@code ItemType<?>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher resolves a registered item identifier through the
     * item registry.
     */
    @Override
    public CommandNode itemType(String name, CommandNode parent, ItemType<?> defaultValue) {
        return new ItemTypeNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's block-type implementation producing
     * {@code BlockType<?>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher resolves a registered block identifier through the
     * block registry.
     */
    @Override
    public CommandNode blockType(String name, CommandNode parent, BlockType<?> defaultValue) {
        return new BlockTypeNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's entity-type implementation producing
     * {@code EntityType<?>} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher resolves a registered entity identifier through
     * the entity registry.
     */
    @Override
    public CommandNode entityType(String name, CommandNode parent, EntityType<?> defaultValue) {
        return new EntityTypeNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's difficulty implementation producing
     * {@code Difficulty} during parsing. The returned node retains
     * {@code parent} and {@code defaultValue}; its caller must attach it before
     * traversal. The matcher recognizes the difficulty parser's supported
     * numeric and named aliases.
     */
    @Override
    public CommandNode difficulty(String name, CommandNode parent, Difficulty defaultValue) {
        return new DifficultyNode(name, parent, defaultValue);
    }

    /**
     * Creates the server's block-property implementation producing
     * {@code List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>} during
     * parsing. The returned node retains {@code parent} and
     * {@code defaultValue}; its caller must attach it before traversal. The
     * parent must be a block-type node because parsing reads the preceding
     * block result.
     */
    @Override
    public CommandNode blockPropertyValues(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        return new BlockPropertyValuesNode(name, parent, defaultValue);
    }

    /**
     * Creates the server node for reconstructing command text from remaining
     * tokens. Matching re-quotes literal-space tokens, joins and trims the
     * text, and does not escape embedded quotes. Optional omission inserts the
     * retained default; callers attach the node separately.
     */
    @Override
    public CommandNode cmd(String name, CommandNode parent, String defaultValue) {
        return new CmdNode(name, parent, defaultValue);
    }
}
