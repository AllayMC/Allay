package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Chicken base behavior: breeding interaction and loot table.
 */
public class EntityChickenBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    public EntityChickenBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 0.8, 0.3);
    }

    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (!canDrop()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        int chickenCount = 1 + rand.nextInt(event.getLootingLevel() + 1);
        var chickenType = livingComponent.isOnFire() ? ItemTypes.COOKED_CHICKEN : ItemTypes.CHICKEN;
        event.getDrops().add(chickenType.createItemStack(chickenCount));

        int featherCount = rand.nextInt(3) + rand.nextInt(event.getLootingLevel() + 1);
        if (featherCount > 0) {
            event.getDrops().add(ItemTypes.FEATHER.createItemStack(featherCount));
        }
    }
}
