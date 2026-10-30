package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntitySnowballPhysicsComponentImpl} resolves snowball impacts into
 * target-specific damage and poof particles. Living blazes receive three
 * damage points while other living targets receive a zero-damage attack, and a
 * shared precomputed particle sequence varies the break presentation.
 *
 * @author daoge_cmd
 */
public class EntitySnowballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * {@code PARTICLE_COUNTS} holds shared precomputed impact particle counts.
     * Class initialization fills each entry once, and {@code
     * nextParticleCount} cycles through them for later snowball impacts.
     */
    protected static final byte[] PARTICLE_COUNTS = new byte[24];
    /**
     * {@code PARTICLE_INDEX} selects the next shared particle count. Access is
     * unsynchronized, so concurrent impacts can reuse or reorder entries
     * rather than claim a unique sequence position.
     */
    protected static int PARTICLE_INDEX = 0;

    // Precompute five-through-fourteen particle counts once so impacts reuse
    // presentation variation without another random draw.
    static {
        for (int i = 0; i < PARTICLE_COUNTS.length; i++) {
            PARTICLE_COUNTS[i] = (byte) (ThreadLocalRandom.current().nextInt(10) + 5);
        }
    }

    /**
     * Returns the next count from the shared cyclic presentation array. The
     * unsynchronized cursor does not promise a distinct index for concurrent
     * calls, which affects particle variation rather than projectile damage.
     */
    protected static int nextParticleCount() {
        // The shared cursor affects presentation only; unsynchronized calls may
        // repeat or reorder counts without coordinating impact damage processing.
        int index = PARTICLE_INDEX++;
        if (index >= PARTICLE_COUNTS.length) {
            PARTICLE_INDEX = index = 0;
        }
        return PARTICLE_COUNTS[index];
    }

    /**
     * Offers blaze-specific or zero projectile damage, then removes the
     * snowball and emits break particles. An already queued removal suppresses
     * the callback, and target attack rejection does not preserve the
     * snowball.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            var damage = DamageContainer.projectile(thisEntity, other.getEntityType() == EntityTypes.BLAZE ? 3 : 0);
            // Subtract current motion from the impact position to place the
            // knockback source behind the snowball along its travel direction.
            damage.setKnockbackSource(hitPos.sub(this.motion, new Vector3d()));
            living.attack(damage);
        }

        thisEntity.remove();
        this.addHitEffect();
    }

    /**
     * Removes the snowball and emits its break particles after a block hit. An
     * already queued removal skips both effects, and this handler does not
     * otherwise modify the hit block.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        thisEntity.remove();
        this.addHitEffect();
    }

    /**
     * Emits the next sampled count of snowball poof particles at the current
     * location. The count comes from the shared cyclic sequence rather than a
     * new random draw.
     */
    protected void addHitEffect() {
        var particleCount = nextParticleCount();
        for (var i = 0; i < particleCount; i++) {
            thisEntity.getLocation().dimension().addParticle(thisEntity.getLocation(), SimpleParticle.SNOWBALL_POOF);
        }
    }
}
