package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.api.world.sound.CustomSound;

/**
 * @author Kanelucky
 */
public class BlockSuspiciousFallableComponentImpl extends BlockFallableComponentImpl {

    public BlockSuspiciousFallableComponentImpl(String landingSound) {
        super(landingSound);
    }

    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        location.dimension().addSound(location, new CustomSound(landingSound));
        location.dimension().addParticle(location, new BlockBreakParticle(blockState));
    }
}