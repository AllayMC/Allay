package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Event fired when an entity shoots an arrow from a bow.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityShootBowEvent extends EntityEvent implements CancellableEvent {
    /**
     * The bow item used to shoot the arrow.
     */
    protected final ItemStack bow;
    /**
     * The arrow that is shot.
     */
    protected final EntityArrow arrow;

    public EntityShootBowEvent(Entity entity, ItemStack bow, EntityArrow arrow) {
        super(entity);
        this.bow = bow;
        this.arrow = arrow;
    }
}
