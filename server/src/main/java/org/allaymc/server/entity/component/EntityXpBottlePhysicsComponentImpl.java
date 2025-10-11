package org.allaymc.server.entity.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.particle.SplashParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3dc;

import java.awt.*;
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

        thisEntity.remove();
        spawnExp();
    }

    protected void spawnExp() {
        var location = thisEntity.getLocation();
        var dimension = thisEntity.getDimension();
        // Spawn experience orbs with a value of 3-11
        dimension.splitAndDropXpOrb(location, ThreadLocalRandom.current().nextInt(9) + 3);
        dimension.addSound(location, SimpleSound.GLASS_BREAK);
        // Spawn blue potion splash particle
        dimension.addParticle(location, new SplashParticle(new Color(0x00385dc6)));
    }
}
