package org.allaymc.api.command.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.List;

/**
 * Allay Project 2024/1/5
 *
 * @author daoge_cmd
 */
public interface CommandNodeFactory {

    ApiInstanceHolder<CommandNodeFactory> FACTORY = ApiInstanceHolder.of();

    static CommandNodeFactory getFactory() {
        return FACTORY.get();
    }

    CommandNode key(String key, CommandNode parent, String defaultValue);

    CommandNode str(String name, CommandNode parent, String defaultValue);

    CommandNode intNum(String name, CommandNode parent, int defaultValue);

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

    CommandNode gameMode(String name, CommandNode parent, GameType defaultValue);
}
