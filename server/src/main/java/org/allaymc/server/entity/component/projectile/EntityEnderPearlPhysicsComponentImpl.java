package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public class EntityEnderPearlPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @Dependency
    protected EntityProjectileComponent projectileComponent;

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            living.attack(DamageContainer.projectile(thisEntity, 0));
        }

        this.teleport();
        thisEntity.remove();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        this.teleport();
        thisEntity.remove();
    }

    protected void teleport() {
        var shooter = projectileComponent.getShooter();
        if (shooter == null || !shooter.isAlive()) {
            return;
        }

        var pearlLoc = thisEntity.getLocation();
        var shooterLoc = shooter.getLocation();
        var location = new Location3d(
                pearlLoc.x(),
                pearlLoc.y(),
                pearlLoc.z(),
                shooterLoc.pitch(),
                shooterLoc.yaw(),
                pearlLoc.dimension()
        );
        var dimension = thisEntity.getDimension();
        dimension.addSound(location, SimpleSound.TELEPORT);
        if (!shooter.teleport(location, EntityTeleportEvent.Reason.PROJECTILE)) {
            return;
        }

        dimension.addSound(location, SimpleSound.TELEPORT);
        dimension.addParticle(location, SimpleParticle.ENDERMAN_TELEPORT);
        if (shooter instanceof EntityLiving living) {
            living.attack(DamageContainer.fall(5));
        }
        // TODO: spawn ender mite
    }
}
