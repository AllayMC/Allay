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
 * Called when a player successfully catches something while fishing.
 * <p>
 * This event is only called when the player reels in the line and
 * catches either an item (fish/treasure/junk) or hooks an entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerFishEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The fishing hook entity.
     */
    protected final EntityFishingHook fishingHook;

    /**
     * The caught item, or null if an entity was hooked.
     */
    @Setter
    protected ItemStack caughtItem;

    /**
     * The experience to be given to the player.
     */
    @Setter
    protected int experience;

    /**
     * The hooked entity, or null if an item was caught.
     */
    protected final Entity hookedEntity;

    /**
     * The motion to apply to the caught item/entity.
     */
    @Setter
    protected Vector3dc motion;

    /**
     * Creates a new PlayerFishEvent for catching an item.
     *
     * @param player      the player
     * @param fishingHook the fishing hook
     * @param caughtItem  the caught item
     * @param experience  the experience to give
     * @param motion      the motion to apply to the item
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
     * Creates a new PlayerFishEvent for hooking an entity.
     *
     * @param player       the player
     * @param fishingHook  the fishing hook
     * @param hookedEntity the hooked entity
     * @param motion       the motion to apply to the entity
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
     * Checks if an item was caught.
     *
     * @return {@code true} if an item was caught
     */
    public boolean hasCaughtItem() {
        return caughtItem != null;
    }

    /**
     * Checks if an entity was hooked.
     *
     * @return {@code true} if an entity was hooked
     */
    public boolean hasHookedEntity() {
        return hookedEntity != null;
    }
}
