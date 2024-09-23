package org.allaymc.api.command.data;

import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.Arrays;

import static org.cloudburstmc.protocol.bedrock.data.GameType.*;

/**
 * @author daoge_cmd
 */
public interface CommonEnums {

    String[] GAMEMODE_ENUM = new String[]{
            "survival", "s", "0",
            "c", "creative", "1",
            "adventure", "a", "2",
            "spectator", "spc", "6"
    };
    String[] BOOL_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.BOOLEAN)
            .map(gameRule -> gameRule.getName().toLowerCase())
            .toArray(String[]::new);
    String[] INT_GAMERULE_ENUM = Arrays.stream(GameRule.values())
            .filter(rule -> rule.getType() == GameRule.Type.INT)
            .map(gameRule -> gameRule.getName().toLowerCase())
            .toArray(String[]::new);

    /**
     * Converts a string to a GameType.
     *
     * @param str The string to convert.
     *
     * @return The GameType, or null if the string is not recognized.
     */
    static GameType getGameTypeFromString(String str) {
        return switch (str.trim().toLowerCase()) {
            case "0", "survival", "s" -> SURVIVAL;
            case "1", "creative", "c" -> CREATIVE;
            case "2", "adventure", "a" -> ADVENTURE;
            case "6", "spectator", "spc" -> SPECTATOR;
            default -> null;
        };
    }

    /**
     * Get the translation key for the given GameType.
     *
     * @param gameType The GameType.
     *
     * @return The translation key, or the GameType string if the GameType is not recognized.
     */
    @MayContainTrKey
    static String getGameTypeTrKey(GameType gameType) {
        return switch (gameType) {
            case SURVIVAL -> TrKeys.M_GAMEMODE_SURVIVAL;
            case CREATIVE -> TrKeys.M_GAMEMODE_CREATIVE;
            case ADVENTURE -> TrKeys.M_GAMEMODE_ADVENTURE;
            case SPECTATOR -> TrKeys.M_GAMEMODE_SPECTATOR;
            default -> gameType.toString();
        };
    }

    /**
     * Get the GameRule with the given name.
     *
     * @param name The name of the GameRule.
     *
     * @return The GameRule, or null if the name is not recognized.
     */
    static GameRule getGameRuleFromName(String name) {
        return GameRule.fromName(name);
    }
}
