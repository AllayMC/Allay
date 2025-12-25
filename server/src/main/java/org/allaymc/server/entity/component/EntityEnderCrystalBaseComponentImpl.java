package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityEnderCrystalBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author ClexaGod | daoge_cmd
 */
public class EntityEnderCrystalBaseComponentImpl extends EntityBaseComponentImpl implements EntityEnderCrystalBaseComponent {

    protected static final String TAG_SHOW_BOTTOM = "ShowBottom";

    @Dependency
    protected EntityLivingComponent livingComponent;

    @Getter
    protected boolean baseVisible;

    public EntityEnderCrystalBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.5, 0.0, -0.5, 0.5, 1, 0.5);
    }

    @Override
    public boolean hasEntityCollision() {
        return false;
    }

    @Override
    public void setBaseVisibility(boolean value) {
        this.baseVisible = value;
        broadcastState();
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_SHOW_BOTTOM, this.baseVisible)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForBoolean(TAG_SHOW_BOTTOM, value -> this.baseVisible = value);
    }

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        if (!isSpawned()) {
            return;
        }

        var lastDamage = livingComponent.getLastDamage();
        if (lastDamage == null) {
            return;
        }

        var explosion = new Explosion(6);
        explosion.setEntity(thisEntity);
        explosion.setDestroyBlocks(thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING));
        if (new EntityExplodeEvent(thisEntity, explosion).call()) {
            remove();
            explosion.explode(getDimension(), location.x, location.y, location.z);
        }
    }
}
