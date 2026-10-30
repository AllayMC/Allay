package org.allaymc.server.entity.component.humanlike;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;

/**
 * {@code EntityHumanLikeBaseComponentImpl} adds held-item and armor
 * presentation to the shared entity base. A viewer first receives the ordinary
 * spawn and state messages, then receives equipment from the entity's container
 * component so the visible humanlike entity has its current loadout.
 *
 * @author daoge_cmd
 */
public class EntityHumanLikeBaseComponentImpl extends EntityBaseComponentImpl {
    /**
     * Creates a humanlike base component from the supplied entity
     * initialization data.
     */
    public EntityHumanLikeBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Spawns the entity for a viewer and then sends its equipment. The composed
     * entity must implement {@code EntityContainerHolderComponent}; the viewer
     * receives the main hand, offhand, and armor only after the base spawn and
     * state messages have been sent.
     */
    @Override
    public void spawnTo(WorldViewer viewer) {
        // Publish the entity before its equipment so the viewer can associate
        // hand, offhand, and armor updates with the entity it has just received.
        super.spawnTo(viewer);
        viewer.viewEntityHand((Entity & EntityContainerHolderComponent) thisEntity);
        viewer.viewEntityOffhand((Entity & EntityContainerHolderComponent) thisEntity);
        viewer.viewEntityArmors((Entity & EntityContainerHolderComponent) thisEntity);
    }
}
