package org.allaymc.api.command.data;

import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import static org.cloudburstmc.protocol.bedrock.data.GameType.*;

/**
 * Allay Project 2024/1/6
 *
 * @author daoge_cmd
 */
public interface CommonEnum {

    String[] GAMEMODE_ENUM = new String[] {
            "survival", "s", "0",
            "c", "creative", "1",
            "adventure", "a", "2",
            "spectator", "spc", "6"
    };

    static GameType getGameTypeFromString(String str) {
        return switch (str.trim().toLowerCase()) {
            case "0", "survival", "s" -> SURVIVAL;
            case "1", "creative", "c" -> CREATIVE;
            case "2", "adventure", "a" -> ADVENTURE;
            case "6", "spectator", "spc" -> SPECTATOR;
            default -> null;
        };
    }

    @MayContainTrKey
    static String getGameTypeTrKey(GameType gameType) {
        return switch (gameType) {
            case SURVIVAL -> TrKeys.M_GAMEMODE_SURVIVAL;
            case CREATIVE -> TrKeys.M_GAMEMODE_CREATIVE;
            case ADVENTURE -> TrKeys.M_GAMEMODE_ADVENTURE;
            case SPECTATOR -> TrKeys.M_GAMEMODE_SPECTATOR;
            // 不应该其他情况
            default -> gameType.toString();
        };
    }
}
