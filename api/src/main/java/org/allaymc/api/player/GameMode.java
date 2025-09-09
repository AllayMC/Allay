package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;

/**
 * GameMode represents a game mode that may be assigned to a player. Upon joining the world, players will be
 * given the default game mode that the world holds. Game modes specify the way that a player interacts with
 * and plays in the world.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum GameMode {
    /**
     * SURVIVAL is the survival game mode: Players with this game mode have limited supplies and can break
     * blocks after taking some time.
     */
    SURVIVAL(TrKeys.MC_GAMEMODE_SURVIVAL),
    /**
     * CREATIVE represents the creative game mode: Players with this game mode have infinite blocks and
     * items and can break blocks instantly. Players with creative mode can also fly.
     */
    CREATIVE(TrKeys.MC_GAMEMODE_CREATIVE),
    /**
     * ADVENTURE represents the adventure game mode: Players with this game mode cannot edit the world
     * (placing or breaking blocks).
     */
    ADVENTURE(TrKeys.MC_GAMEMODE_ADVENTURE),
    /**
     * SPECTATOR represents the spectator game mode: Players with this game mode cannot interact with the
     * world and cannot be seen by other players. spectator players can fly, like creative mode, and can
     * move through blocks.
     */
    SPECTATOR(TrKeys.MC_GAMEMODE_SPECTATOR);

    /**
     * The translation key for the game mode.
     */
    private final @MayContainTrKey String translationKey;

    /**
     * Looks up a game mode by the str passed.
     *
     * @param str the str to look up
     * @return the game mode, or {@code null} if failed to find a game mode
     */
    public static GameMode from(String str) {
        return switch (str.trim().toLowerCase()) {
            case "0", "survival", "s" -> SURVIVAL;
            case "1", "creative", "c" -> CREATIVE;
            case "2", "adventure", "a" -> ADVENTURE;
            case "3", "spectator", "spc" -> SPECTATOR;
            default -> null;
        };
    }
}
