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
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;

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
    protected void onGetDrop(CEntityGetDropEvent event) {
        for (var container : getContainers().values()) {
            container.removeAllViewers();
            if (!canDropItemInContainers()) {
                continue;
            }

            for (var itemStack : container.getItemStacks()) {
                if (itemStack != ItemAirStack.AIR_STACK) {
                    event.getDrops().add(itemStack);
                }
            }
            container.clearAllSlots();
        }
    }

    protected boolean canDropItemInContainers() {
        return thisEntity.getWorld().getWorldData().getGameRuleValue(GameRule.DO_ENTITY_DROPS);
    }
}
