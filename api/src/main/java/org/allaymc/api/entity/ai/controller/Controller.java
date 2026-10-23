package org.allaymc.api.entity.ai.controller;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code Controller} applies one step of entity control using the state
 * prepared by behaviors and sensors. Controllers can update motion or rotation
 * from shared memories, and the default behavior group invokes them after route
 * directions have been updated.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface Controller {

    /**
     * Returns whether control work was applied to the supplied entity.
     */
    boolean control(EntityIntelligent entity);
}
