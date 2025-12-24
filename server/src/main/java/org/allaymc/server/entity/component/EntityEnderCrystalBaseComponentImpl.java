package org.allaymc.server.entity.component;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author ClexaGod
 */
public class EntityEnderCrystalBaseComponentImpl extends EntityBaseComponentImpl {
    @Dependency
    protected EntityLivingComponent livingComponent;

    protected boolean detonated;

    public EntityEnderCrystalBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.5, 0.0, -0.5, 0.5, 2.0, 0.5);
    }

    @Override
    public boolean hasEntityCollision() {
        return false;
    }

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        if (detonated || !isSpawned()) {
            return;
        }

        var lastDamage = livingComponent.getLastDamage();
        if (lastDamage == null) {
            return;
        }

        if (lastDamage.getAttacker() instanceof EntityEnderDragon) {
            return;
        }

        explode();
    }

    protected void explode() {
        var explosion = new Explosion(6);
        explosion.setEntity(thisEntity);
        explosion.setDestroyBlocks(thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING));
        var event = new EntityExplodeEvent(thisEntity, explosion);
        if (!event.call()) {
            return;
        }

        detonated = true;
        remove();
        explosion.explode(getDimension(), location.x, location.y, location.z);
    }
}
