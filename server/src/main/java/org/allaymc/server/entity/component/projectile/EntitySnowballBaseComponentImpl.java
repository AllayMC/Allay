package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.joml.Vector3dc;

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
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (this.willBeDespawnedNextTick() || other == shooter) {
            return;
        }

        if (other instanceof EntityDamageComponent damageComponent) {
            var damage = DamageContainer.projectile(thisEntity, other.getEntityType() == EntityTypes.BLAZE ? 3 : 0);
            damage.setCustomKnockback(0.3f);
            damageComponent.attack(damage);
        }

        this.despawn();
        this.addHitEffect();
    }

    @Override
    protected void onHitBlock(BlockStateWithPos block, Vector3dc hitPos) {
        if (this.willBeDespawnedNextTick()) {
            return;
        }

        this.despawn();
        this.addHitEffect();
    }

    protected void addHitEffect() {
        var particleCount = nextParticleCount();
        for (var i = 0; i < particleCount; i++) {
            this.location.dimension().addParticle(this.location, ParticleType.SNOWBALL_POOF);
        }
    }
}
