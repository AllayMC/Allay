package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityPickableBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

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
    protected void initMetadata() {
        super.initMetadata();
        metadata.set(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (pickupDelay > 0) {
            pickupDelay--;
        }
    }

    @Override
    protected boolean hasDeadTimer() {
        return false;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_PICKUP_DELAY, pickupDelay -> this.pickupDelay = pickupDelay);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putInt(TAG_PICKUP_DELAY, pickupDelay);
        return builder.build();
    }
}
