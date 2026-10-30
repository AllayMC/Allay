package org.allaymc.api.player;

/**
 * {@code PlayerAbility} identifies an ability tracked independently for a player
 * connection. The ability set supplements game-mode defaults and is encoded into
 * client capability flags; checks in server interaction and movement paths still
 * determine which requested actions the server accepts.
 *
 * @author zernix2077
 */
public enum PlayerAbility {
    /**
     * Permission flag for placing blocks.
     */
    PLACE_BLOCK,

    /**
     * Permission flag for breaking blocks.
     */
    BREAK_BLOCK,

    /**
     * Permission flag for block interaction.
     */
    INTERACT_BLOCK,

    /**
     * Client flag for retaining placed blocks.
     */
    INFINITE_BLOCK,

    /**
     * Permission flag for opening containers.
     */
    OPEN_CONTAINER,

    /**
     * Permission flag for attacking players.
     */
    ATTACK_PLAYER,

    /**
     * Permission flag for attacking nonplayer mobs.
     */
    ATTACK_MOB,

    /**
     * Flag indicating current flying state.
     */
    FLYING,

    /**
     * Permission flag for starting flight.
     */
    MAY_FLY,

    /**
     * Client flag for movement through blocks.
     */
    NO_CLIP,
}
