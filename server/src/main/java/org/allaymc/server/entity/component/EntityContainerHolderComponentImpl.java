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
 * {@code EntityContainerHolderComponentImpl} exposes entity inventories and
 * contributes their contents to internal drop collection. Drop handling
 * removes container viewers first, then transfers non-air item references and
 * clears slots when the entity-drop game rule permits inventory drops.
 *
 * @author daoge_cmd
 */
public class EntityContainerHolderComponentImpl extends BaseContainerHolder implements EntityContainerHolderComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_container_holder_component");

    /**
     * {@code thisEntity} is the injected entity used for game-rule lookup.
     * This field is static, so later component injection replaces the
     * reference shared by all instances.
     */
    @ComponentObject
    protected static Entity thisEntity;

    /**
     * Creates an inventory holder and registers the supplied container
     * instances by type. Containers remain shared with their callers, and
     * duplicate container types are rejected by the base holder during
     * registration.
     */
    public EntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    /**
     * Removes all container viewers and contributes held non-air item
     * references to the event's drop list. Enabled entity drops also clear the
     * source slots; disabled drops leave the contents intact after viewers are
     * removed.
     */
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
            // Transfer the item references before clearing their source slots so
            // later death processing still owns the collected drops.
            container.clearAllSlots();
        }
    }

    /**
     * Returns the entity-drop game rule from the world reached through
     * {@code thisEntity}. Because that injected reference is static, it may
     * belong to a different holder instance after later entity construction.
     */
    protected boolean canDropItemInContainers() {
        return thisEntity.getWorld().getWorldData().getGameRuleValue(GameRule.DO_ENTITY_DROPS);
    }
}
