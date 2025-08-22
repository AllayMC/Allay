package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public class EntityEnderPearlPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @Dependency
    protected EntityProjectileComponent projectileComponent;

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        if (other instanceof EntityDamageComponent damageComponent) {
            damageComponent.attack(DamageContainer.projectile(thisEntity, 0));
        }

        this.teleport();
        thisEntity.despawn();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        this.teleport();
        thisEntity.despawn();
    }

    protected void teleport() {
        var shooter = projectileComponent.getShooter();
        if (shooter == null) {
            return;
        }

        var location = thisEntity.getLocation();
        var dimension = thisEntity.getDimension();
        dimension.addLevelSoundEvent(location, SoundEvent.TELEPORT);
        if (!shooter.teleport(location, EntityTeleportEvent.Reason.PROJECTILE)) {
            return;
        }

        dimension.addLevelSoundEvent(location, SoundEvent.TELEPORT);
        dimension.addLevelEvent(location, LevelEvent.PARTICLE_TELEPORT);
        if (shooter instanceof EntityDamageComponent damageComponent) {
            damageComponent.attack(DamageContainer.fall(5));
        }
        // TODO: spawn ender mite
    }
}
