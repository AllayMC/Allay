package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Water bobbing controller. When an entity is in water, applies
 * upward motion to keep it floating near the surface.
 *
 * @author daoge_cmd
 */
public class FluctuateController implements Controller {

    protected final double floatHeight;

    public FluctuateController() {
        this(0.3);
    }

    public FluctuateController(double floatHeight) {
        this.floatHeight = floatHeight;
    }

    @Override
    public boolean control(EntityIntelligent entity) {
        if (!entity.isTouchingWater()) {
            return false;
        }

        var motion = entity.getMotion();
        if (motion.y() < floatHeight) {
            entity.addMotion(0, floatHeight - motion.y(), 0);
        }

        return true;
    }
}
