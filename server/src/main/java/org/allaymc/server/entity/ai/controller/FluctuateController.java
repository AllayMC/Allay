package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code FluctuateController} prepares upward motion requests from water
 * samples at an entity's feet and eye height. A dry feet sample prevents a
 * request altogether; otherwise the eye sample selects stronger submerged
 * buoyancy or weaker surface buoyancy. These two points determine the response
 * without measuring how much of the entity's full bounds occupies water.
 *
 * <p>
 * Each requested impulse receives a small random offset and is added to
 * existing motion through {@code addMotion}. The underlying {@code setMotion}
 * event can cancel or replace that combined vector, and the controller does not
 * inspect the event's outcome. {@code control} returns {@code true} for both
 * dry ticks and requested impulses, so callers cannot infer that motion changed
 * from its result. The controller retains no per-entity progress state between
 * calls.
 *
 * @author daoge_cmd
 */
public class FluctuateController implements Controller {

    /**
     * {@code SUBMERGED_BUOYANCY} supplies the upward impulse when both feet and
     * eye-height samples occupy water, increasing the controller's lift below
     * the surface.
     */
    protected static final double SUBMERGED_BUOYANCY = 0.05;

    /**
     * {@code SURFACE_BUOYANCY} supplies the weaker upward impulse when feet
     * occupy water but the eye-height sample is above it, allowing a different
     * surface response.
     */
    protected static final double SURFACE_BUOYANCY = 0.015;

    /**
     * {@code BUOYANCY_JITTER} bounds the random variation added to either
     * buoyancy impulse so repeated applications do not use an identical
     * vertical addition.
     */
    protected static final double BUOYANCY_JITTER = 0.005;

    /**
     * Requests randomized upward motion when the feet sample contains water.
     * The eye-height sample selects the submerged or surface impulse, and
     * jitter varies that impulse before it is added to existing motion. Dry
     * feet skip both the eye sample and the request.
     *
     * <p>
     * The method returns {@code true} regardless of whether the entity is dry
     * or the motion request is accepted. {@code addMotion} delegates to
     * cancellable {@code setMotion} and discards its boolean result, so
     * listeners can prevent or replace the requested vector without changing
     * this controller's return value.
     */
    @Override
    public boolean control(EntityIntelligent entity) {
        if (!hasWaterAt(entity, 0)) {
            return true;
        }

        var eyeHeight = (float) entity.getEyeHeight();
        boolean eyeInWater = hasWaterAt(entity, eyeHeight);

        double buoyancy = eyeInWater ? SUBMERGED_BUOYANCY : SURFACE_BUOYANCY;

        buoyancy += (ThreadLocalRandom.current().nextDouble() - 0.5) * 2 * BUOYANCY_JITTER;

        entity.addMotion(0, buoyancy, 0);
        return true;
    }

    /**
     * Returns whether the block at the supplied vertical offset carries the
     * water tag.
     */
    protected boolean hasWaterAt(EntityIntelligent entity, float height) {
        var loc = entity.getLocation();
        // Floor the sample position to select its containing block, including negative coordinates.
        var blockState = entity.getDimension().getBlockState(
                (int) Math.floor(loc.x()),
                (int) Math.floor(loc.y() + height),
                (int) Math.floor(loc.z())
        );
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
