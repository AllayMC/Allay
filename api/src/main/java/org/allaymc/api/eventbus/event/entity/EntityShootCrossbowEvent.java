package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

import java.util.List;

/**
 * Event fired when an entity shoots projectiles from a crossbow.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityShootCrossbowEvent extends EntityEvent implements CancellableEvent {
    /**
     * The crossbow item used to shoot the projectiles.
     */
    protected final ItemStack crossbow;
    /**
     * The projectiles (arrows or fireworks) that will be shot.
     * Can contain multiple entities when Multishot enchantment is used.
     */
    protected final List<Entity> projectiles;

    public EntityShootCrossbowEvent(Entity entity, ItemStack crossbow, List<Entity> projectiles) {
        super(entity);
        this.crossbow = crossbow;
        this.projectiles = projectiles;
    }
}
