package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.BlockFace;

/**
 * ShootParticle is a particle shown when a dispenser or dropper dispenses an item.
 * The BlockFace indicates the direction the dispenser is facing.
 *
 * @author daoge_cmd
 */
public record ShootParticle(BlockFace face) implements Particle {
}
