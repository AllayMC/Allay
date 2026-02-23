package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Water bobbing controller. Simulates buoyancy by applying graduated
 * upward forces based on submersion depth, producing a natural
 * bobbing motion at the water surface.
 *
 * @author daoge_cmd
 */
public class FluctuateController implements Controller {

    /**
     * Upward force applied each tick when the entity is fully
     * submerged (eyes underwater). Strong enough to push the
     * entity toward the surface.
     */
    protected static final double SUBMERGED_BUOYANCY = 0.05;

    /**
     * Upward force applied each tick when the entity is at the
     * surface (feet in water, eyes above). Weaker than gravity
     * so the entity slowly sinks back, creating the bob cycle.
     */
    protected static final double SURFACE_BUOYANCY = 0.015;

    /**
     * Maximum random offset (±) added to buoyancy each tick to
     * prevent perfectly periodic, mechanical-looking motion.
     */
    protected static final double BUOYANCY_JITTER = 0.005;

    @Override
    public boolean control(EntityIntelligent entity) {
        if (!hasWaterAt(entity, 0)) {
            return true;
        }

        var eyeHeight = (float) entity.getEyeHeight();
        boolean eyeInWater = hasWaterAt(entity, eyeHeight);

        // Graduated buoyancy: stronger force when deeper
        double buoyancy = eyeInWater ? SUBMERGED_BUOYANCY : SURFACE_BUOYANCY;

        // Random jitter for natural, non-mechanical motion
        buoyancy += (ThreadLocalRandom.current().nextDouble() - 0.5) * 2 * BUOYANCY_JITTER;

        entity.addMotion(0, buoyancy, 0);
        return true;
    }

    protected boolean hasWaterAt(EntityIntelligent entity, float height) {
        var loc = entity.getLocation();
        var blockState = entity.getDimension().getBlockState(
                (int) Math.floor(loc.x()),
                (int) Math.floor(loc.y() + height),
                (int) Math.floor(loc.z())
        );
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
