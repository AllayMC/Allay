package org.allaymc.server.command.tree;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.server.command.tree.node.*;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3fc;

import java.util.List;

/**
 * Allay Project 2024/1/5
 *
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
    public CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] enums) {
        return new EnumNode(name, parent, defaultValue, enumName, enums);
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
    public CommandNode msg(String name, CommandNode parent, String defaultValue) {
        return new MessageNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode remain(String name, CommandNode parent, Object defaultValue) {
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
    public CommandNode gameMode(String name, CommandNode parent, GameType defaultValue) {
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
    public CommandNode enchantmentNode(String name, CommandNode parent, EnchantmentType defaultValue) {
        return new EnchantmentNode(name, parent, defaultValue);
    }

    @Override
    public CommandNode effectNode(String name, CommandNode parent, EffectType defaultValue) {
        return new EffectNode(name, parent, defaultValue);
    }
}
