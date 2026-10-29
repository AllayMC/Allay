package org.allaymc.api.player;

/**
 * Represents a player ability tracked by the server.
 *
 * @author zernix2077
 */
public enum PlayerAbility {
    /**
     * Whether the player can place blocks.
     * <p>
     * Directly affects client-side ability to place blocks.
     */
    PLACE_BLOCK,

    /**
     * Whether the player can break blocks.
     * <p>
     * Directly affects client-side ability to break blocks.
     */
    BREAK_BLOCK,

    /**
     * Whether the player can interact with blocks, like doors and switches.
     * <p>
     * Directly affects client-side ability to perform those interactions.
     */
    INTERACT_BLOCK,

    /**
     * Whether blocks are not consumed when placed down.
     * <p>
     * Generally enabled for creative mode, but may also be enabled for any other gamemode, resulting
     * in the same infinite block behavior client-side.
     */
    INFINITE_BLOCK,

    /**
     * Whether the player can open containers.
     */
    OPEN_CONTAINER,

    /**
     * Whether the player can attack other players.
     */
    ATTACK_PLAYER,

    /**
     * Whether the player can attack mobs.
     */
    ATTACK_MOB,

    /**
     * Whether the player is currently flying.
     */
    FLYING,

    /**
     * Whether the player may start flying.
     */
    MAY_FLY,

    /**
     * Whether the player can move through blocks.
     * <p>
     * Directly affects client-side movement behavior.
     */
    NO_CLIP,
}
