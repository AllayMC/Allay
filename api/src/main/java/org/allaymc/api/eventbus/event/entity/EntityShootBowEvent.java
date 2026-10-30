package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code EntityShootBowEvent} exposes the bow and prepared arrow before a bow
 * shot is spawned. Cancellation prevents arrow insertion and subsequent shot
 * processing in the bow handler; listeners can inspect the same mutable item
 * and arrow objects used by that handler.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityShootBowEvent extends EntityEvent implements CancellableEvent {
    /**
     * Bow item reference used by the pending shot.
     */
    protected final ItemStack bow;
    /**
     * Prepared arrow entity awaiting the pending bow shot.
     */
    protected final EntityArrow arrow;

    /**
     * Creates a bow-shot proposal retaining the supplied bow and arrow
     * references. The event neither copies the item nor spawns the arrow; the
     * bow handler performs accepted shot processing after dispatch.
     */
    public EntityShootBowEvent(Entity entity, ItemStack bow, EntityArrow arrow) {
        super(entity);
        this.bow = bow;
        this.arrow = arrow;
    }
}
