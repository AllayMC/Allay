package org.allaymc.server.command.data;

import org.allaymc.api.world.gamerule.GameRule;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author daoge_cmd
 */
public interface CommonEnums {
    String[] BOOL_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.BOOLEAN)
            .map(gameRule -> gameRule.getName().toLowerCase(Locale.ROOT))
            .toArray(String[]::new);
    String[] INT_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.INT)
            .map(gameRule -> gameRule.getName().toLowerCase(Locale.ROOT))
            .toArray(String[]::new);
}
