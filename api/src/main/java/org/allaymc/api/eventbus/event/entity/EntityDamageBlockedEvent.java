package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when an entity blocks damage with a shield.
 * <p>
 * This event is fired after the blocking calculation is complete but before
 * the damage reduction is applied. Cancelling this event will prevent the
 * block from occurring, causing the entity to take full damage.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntityDamageBlockedEvent extends EntityEvent implements CancellableEvent {

    /**
     * The damage container containing information about the incoming damage.
     */
    protected DamageContainer damageContainer;

    /**
     * The shield item that is being used to block.
     */
    protected ItemStack blockingItem;

    /**
     * Whether to knockback the attacker when blocking is successful.
     */
    @Setter
    protected boolean knockbackAttacker;

    /**
     * Whether to play the shield block animation and sound.
     */
    @Setter
    protected boolean playAnimation;

    public EntityDamageBlockedEvent(Entity entity, DamageContainer damageContainer, ItemStack blockingItem) {
        this(entity, damageContainer, blockingItem, true, true);
    }

    public EntityDamageBlockedEvent(Entity entity, DamageContainer damageContainer, ItemStack blockingItem,
                                    boolean knockbackAttacker, boolean playAnimation) {
        super(entity);
        this.damageContainer = damageContainer;
        this.blockingItem = blockingItem;
        this.knockbackAttacker = knockbackAttacker;
        this.playAnimation = playAnimation;
    }
}
