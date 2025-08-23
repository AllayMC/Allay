package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityTntBaseComponentImpl extends EntityBaseComponentImpl implements EntityTntBaseComponent {

    public static final String TAG_FUSE = "Fuse";

    @Getter
    @Setter
    protected int fuse = 80;

    public EntityTntBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        metadata.set(EntityFlag.IGNITED, true);
        metadata.set(EntityDataTypes.FUSE_TIME, fuse);

        getDimension().addLevelEvent(location, LevelEvent.SOUND_FUSE);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickTnt();
    }

    protected void tickTnt() {
        if (fuse <= 0) {
            if (!getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.TNT_EXPLODES)) {
                this.remove();
                return;
            }

            var explosion = new Explosion();
            explosion.setDestroyBlocks(!isTouchingWater());
            explosion.setEntity(thisEntity);
            var event = new EntityExplodeEvent(thisEntity, explosion);
            if (event.call()) {
                this.remove();
                explosion.explode(getDimension(), location.x, location.y + 0.06125f, location.z);
            }
        } else {
            fuse--;
            if (fuse % 5 == 0) {
                // Reduce the number of packets sent to the client
                setAndSendEntityData(EntityDataTypes.FUSE_TIME, fuse);
            }
        }
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_FUSE, this::setFuse);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putShort(TAG_FUSE, (short) fuse)
                .build();
    }

    @Override
    public float getNetworkOffset() {
        return 0.49f;
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.49, 0.0, -0.49, 0.49, 0.98, 0.49);
    }
}
