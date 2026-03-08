package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Pig base behavior: breeding interaction and loot table.
 */
public class EntityPigBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    public EntityPigBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 0.9, 0.45);
    }

    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (!canDrop()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        int porkchopCount = rand.nextInt(1, 4) + rand.nextInt(event.getLootingLevel() + 1);
        var porkchopType = livingComponent.isOnFire() ? ItemTypes.COOKED_PORKCHOP : ItemTypes.PORKCHOP;
        event.getDrops().add(porkchopType.createItemStack(porkchopCount));
    }
}
