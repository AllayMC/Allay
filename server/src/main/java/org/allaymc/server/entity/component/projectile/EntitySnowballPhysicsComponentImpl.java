package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntitySnowballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    protected static final byte[] PARTICLE_COUNTS = new byte[24];
    protected static int PARTICLE_INDEX = 0;

    static {
        for (int i = 0; i < PARTICLE_COUNTS.length; i++) {
            PARTICLE_COUNTS[i] = (byte) (ThreadLocalRandom.current().nextInt(10) + 5);
        }
    }

    protected static int nextParticleCount() {
        // We don't really care about thread-safe here since it doesn't matter
        int index = PARTICLE_INDEX++;
        if (index >= PARTICLE_COUNTS.length) {
            PARTICLE_INDEX = index = 0;
        }
        return PARTICLE_COUNTS[index];
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            var damage = DamageContainer.projectile(thisEntity, other.getEntityType() == EntityTypes.BLAZE ? 3 : 0);
            damage.setHasKnockback(false);
            if (living.attack(damage) && other instanceof EntityPhysicsComponent physicsComponent) {
                // Use the last location as the knockback source
                physicsComponent.knockback(hitPos.sub(this.motion, new Vector3d()));
            }

        }

        thisEntity.remove();
        this.addHitEffect();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        thisEntity.remove();
        this.addHitEffect();
    }

    protected void addHitEffect() {
        var particleCount = nextParticleCount();
        for (var i = 0; i < particleCount; i++) {
            thisEntity.getLocation().dimension().addParticle(thisEntity.getLocation(), SimpleParticle.SNOWBALL_POOF);
        }
    }
}
