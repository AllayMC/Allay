package org.allaymc.server.command.data;

import org.allaymc.api.world.gamerule.GameRule;

import java.util.Arrays;
import java.util.Locale;

/**
 * {@code CommonEnums} supplies lowercased gamerule names for typed command
 * enumeration parameters. Boolean and integer rules are advertised separately
 * so command trees can offer the corresponding value parser. The arrays are
 * computed from {@code GameRule} values during interface initialization and
 * remain publicly shared.
 *
 * @author daoge_cmd
 */
public interface CommonEnums {
    /**
     * {@code BOOL_GAMERULE_ENUM} contains names of boolean gamerules for
     * command suggestions. Consumers share the array and should leave its
     * entries unchanged when constructing enumeration nodes.
     */
    String[] BOOL_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.BOOLEAN)
            .map(gameRule -> gameRule.getName().toLowerCase(Locale.ROOT))
            .toArray(String[]::new);
    /**
     * {@code INT_GAMERULE_ENUM} contains names of integer gamerules for command
     * suggestions. Consumers share the array and should leave its entries
     * unchanged when constructing enumeration nodes.
     */
    String[] INT_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.INT)
            .map(gameRule -> gameRule.getName().toLowerCase(Locale.ROOT))
            .toArray(String[]::new);
}
