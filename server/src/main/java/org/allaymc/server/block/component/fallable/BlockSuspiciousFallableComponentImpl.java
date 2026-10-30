package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.api.world.sound.CustomSound;

/**
 * {@code BlockSuspiciousFallableComponentImpl} replaces ordinary landing placement
 * with suspicious-block break effects. The falling entity has already been removed
 * before its landing hook runs, and this hook emits sound and block particles without
 * restoring a block or producing an item drop.
 *
 * @author Kanelucky
 */
public class BlockSuspiciousFallableComponentImpl extends BlockFallableComponentImpl {

    /**
     * Creates suspicious-block falling behavior with the supplied landing sound.
     */
    public BlockSuspiciousFallableComponentImpl(String landingSound) {
        super(landingSound);
    }

    /**
     * Emits the configured sound and break particles for the falling state. The block
     * is not placed and no item is dropped, so landing consumes the suspicious block
     * representation.
     */
    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        location.dimension().addSound(location, new CustomSound(landingSound));
        location.dimension().addParticle(location, new BlockBreakParticle(blockState));
    }
}