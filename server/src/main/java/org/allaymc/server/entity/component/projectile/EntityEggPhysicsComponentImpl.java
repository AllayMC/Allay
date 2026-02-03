package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.particle.ItemBreakParticle;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Physics component implementation for egg entities.
 * Handles collision and chicken spawning.
 *
 * @author daoge_cmd
 */
public class EntityEggPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * The chance (1 in N) of spawning a chicken when an egg breaks.
     */
    protected static final int CHICKEN_SPAWN_CHANCE = 8;

    /**
     * The chance (1 in N) of spawning 4 chickens instead of 1 when spawning occurs.
     */
    protected static final int FOUR_CHICKENS_CHANCE = 32;

    /**
     * Minimum particle count for egg break effect.
     */
    protected static final int MIN_PARTICLE_COUNT = 5;

    /**
     * Maximum additional particle count for egg break effect.
     */
    protected static final int PARTICLE_COUNT_RANGE = 10;

    protected static int nextParticleCount() {
        return ThreadLocalRandom.current().nextInt(PARTICLE_COUNT_RANGE) + MIN_PARTICLE_COUNT;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            // Eggs deal 0 damage but still have knockback
            var damage = DamageContainer.projectile(thisEntity, 0);
            // Use the last location as the knockback source
            damage.setKnockbackSource(hitPos.sub(this.motion, new Vector3d()));
            living.attack(damage);
        }

        handleEggBreak(hitPos);
        thisEntity.remove();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        handleEggBreak(hitPos);
        thisEntity.remove();
    }

    /**
     * Handles the egg breaking effect and possible chicken spawning.
     */
    protected void handleEggBreak(Vector3dc hitPos) {
        addBreakEffect();
        trySpawnChickens(hitPos);
    }

    /**
     * Adds the egg break particle effect.
     */
    protected void addBreakEffect() {
        var dimension = thisEntity.getDimension();
        var location = thisEntity.getLocation();
        var eggItem = ItemTypes.EGG.createItemStack(1);
        var particle = new ItemBreakParticle(eggItem);

        var particleCount = nextParticleCount();
        for (int i = 0; i < particleCount; i++) {
            dimension.addParticle(location, particle);
        }
    }

    /**
     * Attempts to spawn chickens at the hit position.
     */
    protected void trySpawnChickens(Vector3dc hitPos) {
        var random = ThreadLocalRandom.current();

        // 1/8 chance to spawn a chicken
        if (random.nextInt(CHICKEN_SPAWN_CHANCE) != 0) {
            return;
        }

        var dimension = thisEntity.getDimension();

        // 1/32 chance to spawn 4 chickens instead of 1
        int chickenCount = random.nextInt(FOUR_CHICKENS_CHANCE) == 0 ? 4 : 1;

        for (int i = 0; i < chickenCount; i++) {
            var chicken = EntityTypes.CHICKEN.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(hitPos.x(), hitPos.y(), hitPos.z())
                            .build()
            );

            if (chicken != null) {
                // Spawn as baby chicken
                // TODO: Set chicken as baby when age component is available for mobs
                dimension.getEntityManager().addEntity(chicken);
            }
        }
    }
}
