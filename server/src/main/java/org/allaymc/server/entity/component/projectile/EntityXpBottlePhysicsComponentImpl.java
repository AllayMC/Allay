package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.particle.SplashParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3dc;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityXpBottlePhysicsComponentImpl} resolves experience bottle
 * impacts into dropped experience and break effects. Either block or entity
 * impact uses one shared removal guard, then queues bottle removal before
 * creating orbs, a glass sound, and a blue splash particle.
 *
 * @author daoge_cmd
 */
public class EntityXpBottlePhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {
    /**
     * Resolves a block impact through the shared bottle-break handler.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        onHit();
    }

    /**
     * Resolves an entity impact through the shared bottle-break handler.
     */
    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        onHit();
    }

    /**
     * Requests bottle removal and then creates the experience and break
     * presentation. An already queued removal skips the whole path, preventing
     * repeated callbacks from dropping another experience amount.
     */
    protected void onHit() {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        thisEntity.remove();
        spawnExp();
    }

    /**
     * Drops a random total of three through eleven experience points at the
     * bottle location. The dimension splits that amount into orb sizes before
     * the method emits the glass sound and blue splash.
     */
    protected void spawnExp() {
        var location = thisEntity.getLocation();
        var dimension = thisEntity.getDimension();
        // Randomize the total experience before splitting it into canonical orb
        // amounts for world drops.
        dimension.splitAndDropXpOrb(location, ThreadLocalRandom.current().nextInt(9) + 3);
        dimension.addSound(location, SimpleSound.GLASS_BREAK);
        // The fixed blue color identifies bottle breakage independently from a
        // configured potion type.
        dimension.addParticle(location, new SplashParticle(new Color(0x00385dc6)));
    }
}
