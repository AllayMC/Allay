package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.EnumSet;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.jetbrains.annotations.UnmodifiableView;

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
    SURVIVAL(TrKeys.MC_GAMEMODE_SURVIVAL, EnumSet.noneOf(PlayerAbility.class)),
    /**
     * CREATIVE represents the creative game mode: Players with this game mode have infinite blocks and
     * items and can break blocks instantly. Players with creative mode can also fly.
     */
    CREATIVE(TrKeys.MC_GAMEMODE_CREATIVE, EnumSet.of(PlayerAbility.MAY_FLY, PlayerAbility.INSTABUILD)),
    /**
     * ADVENTURE represents the adventure game mode: Players with this game mode cannot edit the world
     * (placing or breaking blocks).
     */
    ADVENTURE(TrKeys.MC_GAMEMODE_ADVENTURE, EnumSet.noneOf(PlayerAbility.class)),
    /**
     * SPECTATOR represents the spectator game mode: Players with this game mode cannot interact with the
     * world and cannot be seen by other players. spectator players can fly, like creative mode, and can
     * move through blocks.
     */
    SPECTATOR(TrKeys.MC_GAMEMODE_SPECTATOR, EnumSet.of(PlayerAbility.MAY_FLY, PlayerAbility.FLYING, PlayerAbility.NO_CLIP));

    private static final GameMode[] VALUES = values();

    /**
     * The translation key for the game mode.
     */
    private final @MayContainTrKey String translationKey;

    /**
     * The default abilities associated with this game mode that should be tracked server-side.
     */
    private final @UnmodifiableView EnumSet<PlayerAbility> abilities;

    /**
     * Looks up a game mode by the id passed.
     *
     * @param id the id to look up
     * @return the game mode, or {@code null} if failed to find a game mode
     */
    public static GameMode from(int id) {
        return VALUES[id];
    }
}
