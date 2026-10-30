package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.joml.Vector3dc;

/**
 * {@code PlayerFishEvent} describes a successful fishing reel involving either loot
 * or a hooked entity. Listeners may cancel the reward or pull, or alter the values
 * consumed by that path; the fishing implementation removes the hook after either
 * outcome even when cancelled.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerFishEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Fishing hook being reeled in.
     */
    protected final EntityFishingHook fishingHook;

    /**
     * {@code caughtItem} supplies item loot when non-{@code null}. The entity-hook
     * constructor initializes it to {@code null}, and listeners may suppress loot by
     * clearing it.
     */
    @Setter
    protected ItemStack caughtItem;

    /**
     * {@code experience} supplies experience points for the loot path. The caller
     * drops orbs only for positive values and ignores this field for hooked
     * entities.
     */
    @Setter
    protected int experience;

    /**
     * {@code hookedEntity} identifies the hooked target for the entity path. The
     * item-loot constructor initializes it to {@code null}.
     */
    protected final Entity hookedEntity;

    /**
     * {@code motion} retains the velocity applied to loot or a physics-capable
     * hooked entity. The caller reads the listener-selected vector after approval.
     */
    @Setter
    protected Vector3dc motion;

    /**
     * Creates a fishing event for item loot with the supplied reward and motion.
     * {@code hookedEntity} is {@code null}; the constructor retains
     * {@code caughtItem} and {@code motion} without copying them.
     */
    public PlayerFishEvent(EntityPlayer player, EntityFishingHook fishingHook, ItemStack caughtItem, int experience, Vector3dc motion) {
        super(player);
        this.fishingHook = fishingHook;
        this.caughtItem = caughtItem;
        this.experience = experience;
        this.hookedEntity = null;
        this.motion = motion;
    }

    /**
     * Creates a fishing event for pulling the supplied hooked entity.
     * {@code caughtItem} is {@code null} and {@code experience} is zero; the
     * constructor retains {@code hookedEntity} and {@code motion} without copying
     * them.
     */
    public PlayerFishEvent(EntityPlayer player, EntityFishingHook fishingHook, Entity hookedEntity, Vector3dc motion) {
        super(player);
        this.fishingHook = fishingHook;
        this.caughtItem = null;
        this.experience = 0;
        this.hookedEntity = hookedEntity;
        this.motion = motion;
    }

    /**
     * Returns whether the current event contains a non-{@code null} caught item.
     */
    public boolean hasCaughtItem() {
        return caughtItem != null;
    }

    /**
     * Returns whether the event contains a non-{@code null} hooked entity.
     */
    public boolean hasHookedEntity() {
        return hookedEntity != null;
    }
}
