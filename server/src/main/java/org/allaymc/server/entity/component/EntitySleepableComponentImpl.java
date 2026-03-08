package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntitySleepableComponent;
import org.allaymc.api.eventbus.event.entity.EntitySleepEvent;
import org.allaymc.api.eventbus.event.entity.EntityWakeEvent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Implementation of EntitySleepableComponent for entities that can sleep in a bed.
 */
public class EntitySleepableComponentImpl implements EntitySleepableComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_sleepable_component");

    @ComponentObject
    protected Entity thisEntity;

    @Getter
    protected Vector3ic sleepingPos;

    @Override
    public void sleep(Vector3ic bedPos) {
        if (isSleeping()) {
            return;
        }

        var event = new EntitySleepEvent(thisEntity, bedPos);
        if (!event.call()) {
            return;
        }

        this.sleepingPos = new Vector3i(bedPos);
        thisEntity.getWorld().setRequiredSleepTicks(100);

        // Mark the bed as occupied
        var dimension = thisEntity.getDimension();
        var blockState = dimension.getBlockState(bedPos);
        if (blockState.getBlockType() == BlockTypes.BED) {
            dimension.setBlockState(
                    bedPos.x(), bedPos.y(), bedPos.z(),
                    blockState.setPropertyValue(BlockPropertyTypes.OCCUPIED_BIT, true)
            );
        }

        thisEntity.broadcastState();
    }

    @Override
    public void wake() {
        if (!isSleeping()) {
            return;
        }

        var prevPos = this.sleepingPos;
        this.sleepingPos = null;

        new EntityWakeEvent(thisEntity, prevPos).call();

        // Mark the bed as unoccupied
        if (prevPos != null) {
            var dimension = thisEntity.getDimension();
            var blockState = dimension.getBlockState(prevPos);
            if (blockState.getBlockType() == BlockTypes.BED) {
                dimension.setBlockState(
                        prevPos.x(), prevPos.y(), prevPos.z(),
                        blockState.setPropertyValue(BlockPropertyTypes.OCCUPIED_BIT, false)
                );
            }
        }

        // Send wake-up animation to all viewers (and the entity itself if player)
        thisEntity.applyAction(SimpleEntityAction.WAKE_UP);
        thisEntity.broadcastState();
    }
}
