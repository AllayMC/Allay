package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.ProjectileHitEvent;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntitySnowballBaseComponentImpl extends EntityProjectileBaseComponentImpl {

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

    public EntitySnowballBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void onCollideWithEntity(Entity other) {
        if (other == shootingEntity) {
            return;
        }

        if (!this.willBeDespawnedNextTick()) {
            var event = new ProjectileHitEvent((EntityProjectile) thisEntity, other);
            if (!event.call()) {
                return;
            }

            if (other instanceof EntityDamageComponent damageComponent) {
                damageComponent.attack(DamageContainer.projectile(thisEntity, other.getEntityType() == EntityTypes.BLAZE ? 3 : 0));
            }
            this.despawn();
            this.addHitEffect();
        }
    }

    @Override
    public void onCollideWithBlock(BlockStateWithPos block) {
        if (!this.willBeDespawnedNextTick()) {
            var event = new ProjectileHitEvent((EntityProjectile) thisEntity, block);
            if (!event.call()) {
                return;
            }

            this.despawn();
            this.addHitEffect();
        }
    }

    @Override
    public double getGravity() {
        return 0.03;
    }

    @Override
    public double getDragFactorInAir() {
        return 0.01;
    }

    protected void addHitEffect() {
        var particleCount = nextParticleCount();
        for (var i = 0; i < particleCount; i++) {
            this.location.dimension().addParticle(this.location, ParticleType.SNOWBALL_POOF);
        }
    }
}
