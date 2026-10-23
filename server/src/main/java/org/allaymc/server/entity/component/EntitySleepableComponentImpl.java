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
 * {@code EntitySleepableComponentImpl} stores an entity's sleeping bed
 * position and synchronizes sleep presentation with bed occupancy.
 * {@code sleep} accepts an awake entity only after the cancellable sleep
 * event, copies the requested position, and resets the world's required sleep
 * countdown. A location that currently contains no bed still becomes the
 * entity's sleeping position.
 *
 * <p> {@code wake} clears {@code sleepingPos} before notifying listeners, then
 * releases the previous bed if it still exists and sends the wake animation.
 * Bed updates and event delivery occur in sequence without rollback, so an
 * exception can leave earlier state changes applied. Callers must use the
 * entity's normal ticking context for these world mutations.
 */
public class EntitySleepableComponentImpl implements EntitySleepableComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_sleepable_component");

    /**
     * Injected entity whose sleeping state and bed are updated.
     */
    @ComponentObject
    protected Entity thisEntity;

    /**
     * {@code sleepingPos} holds a copied bed head position while sleeping and
     * {@code null} while awake. The generated getter exposes the retained
     * vector through its read-only interface.
     */
    @Getter
    protected Vector3ic sleepingPos;

    /**
     * Attempts to store a bed head position and publish sleeping state for an
     * awake entity. Already sleeping entities return without another event,
     * and cancellation leaves the existing state unchanged. Callers must use
     * the entity's ticking context because accepted sleep updates the world
     * countdown and may change a bed block.
     *
     * <p> The accepted position is copied before storage, so later caller
     * edits to the input vector do not move the sleeping anchor. Bed occupancy
     * is changed only when that position currently holds a bed; the sleep
     * state is retained even for other blocks.
     */
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

        // Store sleepingPos before changing occupancy and broadcasting metadata so
        // the entity state sent to viewers describes the accepted sleeping anchor.
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

    /**
     * Clears a sleeping entity's anchor and publishes its wake transition.
     * Awake entities return immediately, and callers must use the entity's
     * ticking context for the bed and viewer updates. The wake event observes
     * the entity as awake because {@code sleepingPos} is cleared before
     * notification.
     *
     * <p> The previous bed is released only if a bed still occupies that
     * position. The wake animation and final state broadcast follow the
     * notification and bed update, and an intervening exception leaves earlier
     * changes applied rather than restoring the sleeping anchor.
     */
    @Override
    public void wake() {
        if (!isSleeping()) {
            return;
        }

        var prevPos = this.sleepingPos;
        this.sleepingPos = null;

        new EntityWakeEvent(thisEntity, prevPos).call();

        // Recheck the old position after notification because listeners or world
        // changes may have replaced the bed that originally held this sleeper.
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

        thisEntity.applyAction(SimpleEntityAction.WAKE_UP);
        thisEntity.broadcastState();
    }
}
