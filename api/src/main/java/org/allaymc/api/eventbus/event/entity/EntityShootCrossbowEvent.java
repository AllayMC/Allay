package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

import java.util.List;

/**
 * {@code EntityShootCrossbowEvent} exposes the crossbow and prepared
 * projectile list before the shot is spawned. The crossbow handler has already
 * charged durability when it dispatches this event; cancellation prevents
 * projectile insertion and clearing the loaded shot, while accepted events use
 * the retained projectile list.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityShootCrossbowEvent extends EntityEvent implements CancellableEvent {
    /**
     * Crossbow item reference used by the pending shot.
     */
    protected final ItemStack crossbow;
    /**
     * {@code projectiles} retains the mutable list prepared by the crossbow
     * handler. The generated getter exposes it directly, and accepted shot
     * processing iterates that same list, including listener changes.
     */
    protected final List<Entity> projectiles;

    /**
     * Creates a crossbow-shot proposal retaining the item and projectile list
     * references. The list and its entity objects remain shared with the
     * handler, so listener edits influence the projectiles spawned after
     * acceptance.
     */
    public EntityShootCrossbowEvent(Entity entity, ItemStack crossbow, List<Entity> projectiles) {
        super(entity);
        this.crossbow = crossbow;
        this.projectiles = projectiles;
    }
}
