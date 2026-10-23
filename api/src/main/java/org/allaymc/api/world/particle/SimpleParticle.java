package org.allaymc.api.world.particle;

/**
 * {@code SimpleParticle} identifies particle effects whose descriptors need no
 * additional parameters. The runtime maps each constant to a client effect,
 * while the broadcasting caller supplies its position; parameterized textures,
 * colors, and directions use separate particle records.
 *
 * @author daoge_cmd
 */
public enum SimpleParticle implements Particle {
    /**
     * Invisible-block force-field particle.
     */
    BLOCK_FORCE_FIELD,
    /**
     * Bone meal growth particle.
     */
    BONE_MEAL,
    /**
     * Particle emitted when a water block evaporates.
     */
    EVAPORATE,
    /**
     * Particle emitted when water drips from a block.
     */
    WATER_DRIP,
    /**
     * Particle emitted when lava drips from a block.
     */
    LAVA_DRIP,
    /**
     * Particle appearing randomly above lava.
     */
    LAVA,
    /**
     * Particle emitted when an item is inserted into a decorated pot.
     */
    DUST_PLUME,
    /**
     * Explosion particle used by sponge absorption and other callers.
     */
    EXPLODE,
    /**
     * Particle emitted when TNT or a creeper explodes.
     */
    HUGE_EXPLOSION,
    /**
     * Particle emitted when an enderman teleports or a player uses ender
     * pearl.
     */
    ENDERMAN_TELEPORT,
    /**
     * Particle emitted when a snowball collides with something.
     */
    SNOWBALL_POOF,
    /**
     * Particle emitted when an entity is set on fire.
     */
    ENTITY_FLAME,
    /**
     * Particle emitted when the flame on an entity is extinguished by water.
     */
    WHITE_SMOKE,
    /**
     * Firework rocket contrail particle.
     */
    FIREWORK_CONTRAIL,
    /**
     * Particle emitted when a mace smash attack hits the ground.
     */
    SMASH_ATTACK_GROUND_DUST,
    /**
     * Particle emitted when a player wind charge explodes.
     */
    WIND_EXPLOSION,
    /**
     * Particle emitted when a breeze wind charge explodes.
     */
    BREEZE_WIND_EXPLOSION,
    /**
     * Particle emitted when a fish is attracted to a fishing hook.
     */
    WATER_WAKE,
    /**
     * Particle emitted when a fish bites the fishing hook.
     */
    BUBBLE
}
