package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityTntBaseComponentImpl extends EntityBaseComponentImpl implements EntityTntBaseComponent {

    public static final String TAG_FUSE = "Fuse";

    @Getter
    protected int fuseTime = 80;

    public EntityTntBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(EntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        getDimension().addSound(location, SimpleSound.TNT);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickTnt();
    }

    protected void tickTnt() {
        if (fuseTime <= 0) {
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
            fuseTime--;
            if (fuseTime % 5 == 0 || fuseTime < 20) {
                // Reduce the number of packets sent to the client
                broadcastState();
            }
        }
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_FUSE, this::setFuseTime);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putShort(TAG_FUSE, (short) fuseTime)
                .build();
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.49, 0.0, -0.49, 0.49, 0.98, 0.49);
    }

    @Override
    public void setFuseTime(int fuseTime) {
        this.fuseTime = fuseTime;
        broadcastState();
    }
}
