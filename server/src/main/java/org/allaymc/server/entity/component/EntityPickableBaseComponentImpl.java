package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPickableBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code EntityPickableBaseComponentImpl} adds a persisted pickup delay to the
 * common entity lifecycle. Positive delays decrease once per tick, zero
 * permits pickup through the inherited interface helper, and negative delays
 * remain unchanged; the component also disables ordinary entity pushing while
 * overlap callbacks can still support pickup and merging.
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityPickableBaseComponentImpl extends EntityBaseComponentImpl implements EntityPickableBaseComponent {

    /**
     * {@code DEFAULT_PICKUP_DELAY} initializes new pickable entities to ten
     * ticks before collection; loading NBT can replace this initial delay.
     */
    protected static final int DEFAULT_PICKUP_DELAY = 10;

    /**
     * {@code TAG_PICKUP_DELAY} names the NBT integer used to carry the
     * remaining pickup delay through entity save and load.
     */
    protected static final String TAG_PICKUP_DELAY = "PickupDelay";

    /**
     * {@code pickupDelay} controls collection eligibility in ticks. Positive
     * values count down, zero permits pickup, and negative values remain
     * unchanged until explicitly replaced or loaded.
     */
    protected int pickupDelay = DEFAULT_PICKUP_DELAY;

    /**
     * Creates base entity state with the default ten-tick pickup delay.
     */
    public EntityPickableBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Advances the base entity tick and decreases a positive pickup delay.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (pickupDelay > 0) {
            pickupDelay--;
        }
    }

    /**
     * Loads base entity state and replaces the pickup delay when its NBT
     * integer exists. Missing pickup data preserves the current delay,
     * including the constructor default on a newly created component.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_PICKUP_DELAY, pickupDelay -> this.pickupDelay = pickupDelay);
    }

    /**
     * Returns base entity NBT extended with the current pickup delay.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_PICKUP_DELAY, pickupDelay)
                .build();
    }

    /**
     * Returns {@code false} to disable ordinary entity pushing without
     * suppressing all overlap callbacks.
     */
    @Override
    public boolean hasEntityCollision() {
        return false;
    }
}
