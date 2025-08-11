package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntityXpBottlePhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        onHit();
    }

    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        onHit();
    }

    protected void onHit() {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        thisEntity.despawn();
        spawnExp();
    }

    protected void spawnExp() {
        var location = thisEntity.getLocation();
        var dimension = thisEntity.getDimension();
        // Spawn experience orbs with a value of 3-11
        dimension.splitAndDropXpOrb(location, ThreadLocalRandom.current().nextInt(9) + 3);
        dimension.addLevelSoundEvent(location, SoundEvent.GLASS);
        // Spawn blue potion splash particle
        dimension.addLevelEvent(location, LevelEvent.PARTICLE_POTION_SPLASH, 0x00385dc6);
    }
}
