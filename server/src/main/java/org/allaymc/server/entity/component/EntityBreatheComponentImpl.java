package org.allaymc.server.entity.component;

import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CEntityDrownEvent;
import org.allaymc.server.entity.component.event.CEntityInitMetadataEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

/**
 * @author daoge_cmd
 */
public class EntityBreatheComponentImpl implements EntityComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_breathe_component");

    @Dependency
    protected EntityBaseComponent baseComponent;
    @ComponentObject
    protected Entity thisEntity;
    @Manager
    protected ComponentManager manager;

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        tickBreathe();
    }

    protected void tickBreathe() {
        var metadata = baseComponent.getMetadata();
        if (!metadata.has(EntityDataTypes.AIR_SUPPLY)) {
            return;
        }

        short airSupply = metadata.get(EntityDataTypes.AIR_SUPPLY);
        short airSupplyMax = metadata.get(EntityDataTypes.AIR_SUPPLY_MAX);
        short newAirSupply = airSupply;
        if (!thisEntity.canBreathe()) {
            baseComponent.setAndSendEntityFlag(EntityFlag.BREATHING, false);
            newAirSupply = (short) (airSupply - 1);
            if (newAirSupply <= -20) {
                manager.callEvent(CEntityDrownEvent.INSTANCE);
                newAirSupply = 0;
            }
        } else if (airSupply < airSupplyMax) {
            baseComponent.setAndSendEntityFlag(EntityFlag.BREATHING, true);
            newAirSupply = (short) (airSupply + 4);
        }
        if (airSupply != newAirSupply) {
            baseComponent.setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, newAirSupply);
        }
    }

    @EventHandler
    protected void onInitMetadata(CEntityInitMetadataEvent $) {
        var metadata = baseComponent.getMetadata();
        metadata.set(EntityFlag.BREATHING, true);
        metadata.set(EntityDataTypes.AIR_SUPPLY, (short) 300);
        metadata.set(EntityDataTypes.AIR_SUPPLY_MAX, (short) 300);
    }
}
