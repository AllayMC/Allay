package org.allaymc.server.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CEntityDrownEvent;
import org.allaymc.server.entity.component.event.CEntityInitMetadataEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * @author daoge_cmd
 */
public class EntityBreatheComponentImpl implements EntityComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_breathe_component");

    @ComponentObject
    protected Entity thisEntity;
    @Manager
    protected ComponentManager manager;

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        tickBreathe();
    }

    protected void tickBreathe() {
        if (!thisEntity.hasData(EntityData.AIR_SUPPLY)) {
            return;
        }

        short airSupply = thisEntity.getData(EntityData.AIR_SUPPLY);
        short airSupplyMax = thisEntity.getData(EntityData.AIR_SUPPLY_MAX);
        short newAirSupply = airSupply;
        if (!thisEntity.canBreathe()) {
            thisEntity.setFlag(EntityFlag.BREATHING, false);
            newAirSupply = (short) (airSupply - 1);
            if (newAirSupply <= -20) {
                manager.callEvent(CEntityDrownEvent.INSTANCE);
                newAirSupply = 0;
            }
        } else if (airSupply < airSupplyMax) {
            thisEntity.setFlag(EntityFlag.BREATHING, true);
            newAirSupply = (short) (airSupply + 4);
        }
        if (airSupply != newAirSupply) {
            thisEntity.setData(EntityData.AIR_SUPPLY, newAirSupply);
        }
    }

    @EventHandler
    protected void onInitMetadata(CEntityInitMetadataEvent $) {
        thisEntity.setFlag(EntityFlag.BREATHING, true);
        thisEntity.setData(EntityData.AIR_SUPPLY, (short) 300);
        thisEntity.setData(EntityData.AIR_SUPPLY_MAX, (short) 300);
    }
}
