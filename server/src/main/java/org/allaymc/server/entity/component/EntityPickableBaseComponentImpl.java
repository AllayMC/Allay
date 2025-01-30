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
    public static final int MAX_AGE = 6000;
    public static final int DEFAULT_PICKUP_DELAY = 10;

    protected int age;
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

        if (age != -1) {
            age++;
            if (age >= MAX_AGE) {
                despawn();
                return;
            }
        }

        if (pickupDelay > 0) pickupDelay--;
    }

    @Override
    protected boolean hasDeadTimer() {
        return false;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort("Age", age -> this.age = age);
        nbt.listenForInt("PickupDelay", pickupDelay -> this.pickupDelay = pickupDelay);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putShort("Age", (short) age);
        builder.putInt("PickupDelay", pickupDelay);

        return builder.build();
    }

    @Override
    public boolean computeBlockCollisionMotion() {
        return true;
    }

    @Override
    public float getGravity() {
        return 0.04f;
    }
}
