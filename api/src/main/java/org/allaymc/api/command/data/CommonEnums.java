package org.allaymc.api.command.data;

import org.allaymc.api.world.gamerule.GameRule;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
public interface CommonEnums {
    String[] BOOL_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.BOOLEAN)
            .map(gameRule -> gameRule.getName().toLowerCase())
            .toArray(String[]::new);
    String[] INT_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.INT)
            .map(gameRule -> gameRule.getName().toLowerCase())
            .toArray(String[]::new);
}
