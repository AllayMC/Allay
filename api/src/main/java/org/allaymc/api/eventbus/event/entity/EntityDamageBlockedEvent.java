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
 * {@code EntityDamageBlockedEvent} represents a proposed shield block after the
 * shield accepts the attack direction. The player damage handler dispatches
 * this event before zeroing final damage, changing shield durability, or
 * applying block feedback. Cancellation resumes ordinary damage handling with
 * the mutable damage container's current values.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntityDamageBlockedEvent extends EntityEvent implements CancellableEvent {

    /**
     * {@code damageContainer} holds the incoming mutable damage data. The block
     * handler continues using this same container after listeners return,
     * including when a listener cancels the proposed block.
     */
    protected DamageContainer damageContainer;

    /**
     * {@code blockingItem} refers to the shield stack selected from the
     * player's hands. The event retains the live stack, so listener mutations
     * affect that item before the block handler applies durability changes.
     */
    protected ItemStack blockingItem;

    /**
     * {@code knockbackAttacker} controls whether an accepted block pushes an
     * attacker that exposes physics. Cancellation prevents this feedback
     * regardless of the flag.
     */
    @Setter
    protected boolean knockbackAttacker;

    /**
     * {@code playAnimation} enables block feedback after an accepted shield
     * block. The current player handler uses this flag to play the shield-block
     * sound when a dimension is available.
     */
    @Setter
    protected boolean playAnimation;

    /**
     * Creates a proposed block event with attacker knockback and feedback
     * enabled. The event retains the supplied entity, mutable damage container,
     * and shield references directly; listeners may change the feedback flags
     * before dispatch returns.
     */
    public EntityDamageBlockedEvent(Entity entity, DamageContainer damageContainer, ItemStack blockingItem) {
        this(entity, damageContainer, blockingItem, true, true);
    }

    /**
     * Creates a proposed block event with the supplied feedback flags. The
     * constructor retains all references without copying or validation;
     * listeners can mutate the shared damage data and shield before the caller
     * decides whether to complete the block.
     */
    public EntityDamageBlockedEvent(Entity entity, DamageContainer damageContainer, ItemStack blockingItem,
                                    boolean knockbackAttacker, boolean playAnimation) {
        super(entity);
        this.damageContainer = damageContainer;
        this.blockingItem = blockingItem;
        this.knockbackAttacker = knockbackAttacker;
        this.playAnimation = playAnimation;
    }
}
