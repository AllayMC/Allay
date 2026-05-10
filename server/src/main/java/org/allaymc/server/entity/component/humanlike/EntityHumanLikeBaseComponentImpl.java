package org.allaymc.server.entity.component.humanlike;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;

/**
 * @author daoge_cmd
 */
public class EntityHumanLikeBaseComponentImpl extends EntityBaseComponentImpl {
    public EntityHumanLikeBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void spawnTo(WorldViewer viewer) {
        super.spawnTo(viewer);
        viewer.viewEntityHand((Entity & EntityContainerHolderComponent) thisEntity);
        viewer.viewEntityOffhand((Entity & EntityContainerHolderComponent) thisEntity);
        viewer.viewEntityArmors((Entity & EntityContainerHolderComponent) thisEntity);
    }
}
