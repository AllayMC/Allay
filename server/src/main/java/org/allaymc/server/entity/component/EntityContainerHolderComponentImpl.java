package org.allaymc.server.entity.component;

import org.allaymc.api.container.Container;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.container.impl.BaseContainerHolder;
import org.allaymc.server.entity.component.event.CEntityDieEvent;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntityContainerHolderComponentImpl extends BaseContainerHolder implements EntityContainerHolderComponent {

    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_container_holder_component");

    @ComponentObject
    protected static Entity thisEntity;

    public EntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        var pos = thisEntity.getLocation();
        var dimension = pos.dimension();
        var rand = ThreadLocalRandom.current();
        for (var container : getContainers().values()) {
            container.removeAllViewers();
            if (!canDropItemInContainers()) continue;
            for (var itemStack : container.getItemStacks()) {
                if (itemStack == ItemAirStack.AIR_STACK) continue;
                dimension.dropItem(
                        itemStack,
                        new Vector3d(
                                pos.x() + rand.nextDouble(0.5) + 0.25,
                                pos.y() + rand.nextDouble(0.5) + 0.25,
                                pos.z() + rand.nextDouble(0.5) + 0.25
                        ),
                        new Vector3d(
                                rand.nextDouble(1) - 0.5,
                                0.5,
                                rand.nextDouble(1) - 0.5
                        ),
                        40
                );
            }

            container.clearAllSlots();
        }
    }

    protected boolean canDropItemInContainers() {
        return thisEntity.getWorld().getWorldData().getGameRuleValue(GameRule.DO_ENTITY_DROPS);
    }
}
