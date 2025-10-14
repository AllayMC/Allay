package org.allaymc.api.world.particle;

/**
 * SimpleParticle contains all particle effects that do not require additional parameters.
 *
 * @author daoge_cmd
 */
public enum SimpleParticle implements Particle {
    // BLOCK_FORCE_FIELD is a particle that shows up as a block that turns invisible.
    BLOCK_FORCE_FIELD,
    // BONE_MEAL is a particle that shows up on bone meal usage.
    BONE_MEAL,
    // EVAPORATE is a particle that shows up when a water block evaporates.
    EVAPORATE,
    // WATER_DRIP is a particle that shows up when water drips from a block.
    WATER_DRIP,
    // LAVA_DRIP is a particle that shows up when lava drips from a block.
    LAVA_DRIP,
    // LAVA is a particle that shows up randomly above lava.
    LAVA,
    // DUST_PLUME is a particle that shows up when an item is inserted into a decorated pot.
    DUST_PLUME,
    // EXPLODE is a particle shown when sponge absorb water.
    EXPLODE,
    // HUGE_EXPLOSION is a particle shown when TNT or a creeper explodes.
    HUGE_EXPLOSION,
    // ENDERMAN_TELEPORT is a particle that shows up when an enderman teleports or a player uses ender pearl.
    ENDERMAN_TELEPORT,
    // SNOWBALL_POOF is a particle shown when a snowball collides with something.
    SNOWBALL_POOF,
    // ENTITY_FLAME is a particle shown when an entity is set on fire.
    ENTITY_FLAME,
    // WHITE_SMOKE is a particle shown when the flame on an entity is extinguished by water.
    WHITE_SMOKE,
    // FIREWORK_CONTRAIL is a particle shown at the location where the firework rocket flew across.
    FIREWORK_CONTRAIL
}
