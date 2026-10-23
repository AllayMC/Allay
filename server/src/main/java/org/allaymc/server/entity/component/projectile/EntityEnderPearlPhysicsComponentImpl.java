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
 * {@code EntityEnderPearlPhysicsComponentImpl} resolves pearl impacts by
 * attempting to teleport the retained shooter. The pearl is removed after
 * either impact path, while shooter lifetime and teleport acceptance determine
 * whether teleport particles, a second sound, and fall damage follow.
 *
 * @author daoge_cmd
 */
public class EntityEnderPearlPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * Injected retained shooter reference component.
     */
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    /**
     * Offers zero projectile damage to a living target and attempts shooter
     * teleportation before removing the pearl. A queued pearl removal skips
     * processing, while rejected target damage does not prevent the teleport
     * attempt.
     */
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

    /**
     * Attempts shooter teleportation and then requests pearl removal after a
     * block impact. Teleport rejection does not preserve the pearl, and an
     * already queued removal suppresses this impact path.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        this.teleport();
        thisEntity.remove();
    }

    /**
     * Attempts shooter teleportation using the pearl position and a copy of
     * the shooter's current pitch and yaw. A missing or dead shooter produces no
     * effects. The first sound is emitted at the original target before the
     * teleport request, so cancellation leaves it observable.
     *
     * <p>
     * An accepted request emits another sound and a particle at that same
     * pearl target, even when a listener redirects the teleport. Acceptance
     * can precede completion of a queued cross-dimension transfer. A living
     * shooter then receives a five-point fall-damage attack without waiting
     * for that transfer; impact callers separately request pearl removal.
     */
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
            // Pearl travel charges five points through fall damage so the shooter
            // normal attack rules still apply.
            living.attack(DamageContainer.fall(5));
        }
        // TODO: Add the pearl-impact endermite spawn chance after an accepted
        // shooter teleport request.
    }
}
