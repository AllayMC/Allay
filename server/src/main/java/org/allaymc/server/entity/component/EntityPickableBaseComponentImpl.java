package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPickableBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityPickableBaseComponentImpl extends EntityBaseComponentImpl implements EntityPickableBaseComponent {

    protected static final int DEFAULT_PICKUP_DELAY = 10;

    protected static final String TAG_PICKUP_DELAY = "PickupDelay";

    protected int pickupDelay = DEFAULT_PICKUP_DELAY;

    public EntityPickableBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (pickupDelay > 0) {
            pickupDelay--;
        }
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_PICKUP_DELAY, pickupDelay -> this.pickupDelay = pickupDelay);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_PICKUP_DELAY, pickupDelay)
                .build();
    }

    @Override
    public boolean hasEntityCollision() {
        return false;
    }
}
