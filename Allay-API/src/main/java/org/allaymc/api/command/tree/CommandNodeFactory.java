package org.allaymc.api.command.tree;

import org.allaymc.api.ApiInstanceHolder;
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
 * Allay Project 2024/1/5
 *
 * @author daoge_cmd
 */
public interface CommandNodeFactory {

    ApiInstanceHolder<CommandNodeFactory> FACTORY = ApiInstanceHolder.create();

    static CommandNodeFactory getFactory() {
        return FACTORY.get();
    }

    CommandNode key(String key, CommandNode parent, String defaultValue);

    CommandNode str(String name, CommandNode parent, String defaultValue);

    CommandNode shortNum(String name, CommandNode parent, short defaultValue);

    CommandNode intNum(String name, CommandNode parent, int defaultValue);

    CommandNode longNum(String name, CommandNode parent, long defaultValue);

    CommandNode floatNum(String name, CommandNode parent, float defaultValue);

    CommandNode doubleNum(String name, CommandNode parent, double defaultValue);

    CommandNode bool(String name, CommandNode parent, boolean defaultValue);

    CommandNode enums(String name, CommandNode parent, String defaultValue, String[] enums);

    CommandNode enums(String name, CommandNode parent, String enumName, String defaultValue, String[] enums);

    CommandNode enumsIgnoreCase(String name, CommandNode parent, String defaultValue, String[] enums);

    CommandNode enumsIgnoreCase(String name, CommandNode parent, String enumName, String defaultValue, String[] enums);

    CommandNode msg(String name, CommandNode parent, String defaultValue);

    CommandNode remain(String name, CommandNode parent, Object defaultValue);

    CommandNode target(String name, CommandNode parent, List<Entity> defaultValue);

    CommandNode playerTarget(String name, CommandNode parent, List<EntityPlayer> defaultValue);

    CommandNode gameMode(String name, CommandNode parent, GameType defaultValue);

    CommandNode pos(String name, CommandNode parent, Vector3fc defaultValue);

    CommandNode wildcardTarget(String name, CommandNode parent, String defaultValue);

    CommandNode enchantmentNode(String name, CommandNode parent, EnchantmentType defaultValue);

    CommandNode effectNode(String name, CommandNode parent, EffectType defaultValue);

    CommandNode itemTypeNode(String name, CommandNode parent, ItemType<?> defaultValue);

    CommandNode difficultyNode(String name, CommandNode parent, Difficulty defaultValue);
}
