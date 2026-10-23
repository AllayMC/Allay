package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.EnumSet;

/**
 * {@code GameMode} identifies how a player interacts with the world and supplies its
 * default tracked abilities. The enum stores a translation key and ability set for
 * each mode; numeric lookup uses declaration order, matching the four standard
 * protocol game-mode values.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum GameMode {
    /**
     * {@code SURVIVAL} selects ordinary resource consumption and timed block
     * breaking, with no additional abilities supplied by this enum.
     */
    SURVIVAL(TrKeys.MC_GAMEMODE_SURVIVAL, EnumSet.noneOf(PlayerAbility.class)),
    /**
     * {@code CREATIVE} supplies {@code MAY_FLY} and {@code INFINITE_BLOCK}. The
     * player component also applies creative interaction and resource-consumption
     * rules.
     */
    CREATIVE(TrKeys.MC_GAMEMODE_CREATIVE, EnumSet.of(PlayerAbility.MAY_FLY, PlayerAbility.INFINITE_BLOCK)),
    /**
     * {@code ADVENTURE} selects restricted world editing and supplies no additional
     * abilities from this enum; interaction checks enforce its restrictions.
     */
    ADVENTURE(TrKeys.MC_GAMEMODE_ADVENTURE, EnumSet.noneOf(PlayerAbility.class)),
    /**
     * {@code SPECTATOR} supplies {@code MAY_FLY}, {@code FLYING}, and
     * {@code NO_CLIP}. The player component applies the corresponding spectator
     * interaction and visibility rules.
     */
    SPECTATOR(TrKeys.MC_GAMEMODE_SPECTATOR, EnumSet.of(PlayerAbility.MAY_FLY, PlayerAbility.FLYING, PlayerAbility.NO_CLIP));

    /**
     * {@code VALUES} caches declaration-order game modes for numeric lookup.
     * {@code from} indexes this array directly and does not validate its argument.
     */
    private static final GameMode[] VALUES = values();

    /**
     * Translation key naming this game mode.
     */
    private final @MayContainTrKey String translationKey;

    /**
     * {@code abilities} holds this mode's default tracked capabilities. The
     * generated getter exposes the stored mutable {@code EnumSet}; callers must
     * leave it unchanged.
     */
    private final @UnmodifiableView EnumSet<PlayerAbility> abilities;

    /**
     * Returns the game mode at the supplied declaration-order index. Supported
     * indexes are {@code 0} through {@code 3}; invalid indexes throw instead of
     * returning {@code null}.
     *
     * @throws ArrayIndexOutOfBoundsException if {@code id} is outside the four game
     * modes
     */
    public static GameMode from(int id) {
        return VALUES[id];
    }
}
