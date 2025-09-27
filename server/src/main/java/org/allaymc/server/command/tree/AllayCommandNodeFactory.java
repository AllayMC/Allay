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
 * @author daoge_cmd
 */
public class AllayCommandNodeFactory implements CommandNodeFactory {
    @Override
    public CommandNode key(String key, CommandNode parent, String defaultValue) {
        return new KeyNode(key, parent, defaultValue);
    }

    @Override
    public CommandNode str(String name, CommandNode parent, String defaultValue) {
        return new StringNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode shortNum(String name, CommandNode parent, short defaultValue) {
        return new ShortNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode intNum(String name, CommandNode parent, int defaultValue) {
        return new IntNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode longNum(String name, CommandNode parent, long defaultValue) {
        return new LongNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode floatNum(String name, CommandNode parent, float defaultValue) {
        return new FloatNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode doubleNum(String name, CommandNode parent, double defaultValue) {
        return new DoubleNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode bool(String name, CommandNode parent, boolean defaultValue) {
        return new BooleanNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode enums(String name, CommandNode parent, String defaultValue, String[] enums) {
        return new EnumNode(name, parent, defaultValue, enums);
    }

    @Override
    public CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] values) {
        return new EnumNode(name, parent, defaultValue, enumName, values);
    }

    @Override
    public CommandNode enumsIgnoreCase(String name, CommandNode parent, String defaultValue, String[] enums) {
        return new EnumIgnoreCaseNode(name, parent, defaultValue, enums);
    }

    @Override
    public CommandNode enumsIgnoreCase(String name, CommandNode parent, String enumName, String defaultValue, String[] enums) {
        return new EnumIgnoreCaseNode(name, parent, defaultValue, enumName, enums);
    }

    @Override
    public <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, Class<T> enumClass) {
        return new EnumClassNode(name, parent, defaultValue, enumClass);
    }

    @Override
    public <T extends Enum<?>> CommandNode enumClass(String name, CommandNode parent, T defaultValue, String enumName, Class<T> enumClass) {
        return new EnumClassNode(name, parent, defaultValue, enumName, enumClass);
    }

    @Override
    public CommandNode msg(String name, CommandNode parent, String defaultValue) {
        return new MessageNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode remain(String name, CommandNode parent, List<String> defaultValue) {
        return new RemainArgNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode target(String name, CommandNode parent, List<Entity> defaultValue) {
        return new TargetNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode playerTarget(String name, CommandNode parent, List<EntityPlayer> defaultValue) {
        return new PlayerTargetNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode gameMode(String name, CommandNode parent, GameMode defaultValue) {
        return new GameModeNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode pos(String name, CommandNode parent, Vector3fc defaultValue) {
        return new PosNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode wildcardTarget(String name, CommandNode parent, String defaultValue) {
        return new WildcardTargetNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode enchantment(String name, CommandNode parent, EnchantmentType defaultValue) {
        return new EnchantmentNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode effect(String name, CommandNode parent, EffectType defaultValue) {
        return new EffectNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode itemType(String name, CommandNode parent, ItemType<?> defaultValue) {
        return new ItemTypeNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode blockType(String name, CommandNode parent, BlockType<?> defaultValue) {
        return new BlockTypeNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode entityType(String name, CommandNode parent, EntityType<?> defaultValue) {
        return new EntityTypeNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode difficulty(String name, CommandNode parent, Difficulty defaultValue) {
        return new DifficultyNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode blockPropertyValues(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        return new BlockPropertyValuesNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode cmd(String name, CommandNode parent, String defaultValue) {
        return new CmdNode(name, parent, defaultValue);
    }
}
