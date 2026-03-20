package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.property.type.EntityPropertyTypes;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemType;
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
        var eggItem = getEggItemType().createItemStack(1);
        var particle = new ItemBreakParticle(eggItem);

        var particleCount = nextParticleCount();
        for (int i = 0; i < particleCount; i++) {
            dimension.addParticle(location, particle);
        }
    }

    /**
     * Gets the correct egg item type for break particles based on climate variant.
     */
    protected ItemType<?> getEggItemType() {
        var variant = thisEntity.getPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT);
        return switch (variant) {
            case COLD -> ItemTypes.BLUE_EGG;
            case WARM -> ItemTypes.BROWN_EGG;
            default -> ItemTypes.EGG;
        };
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
            var chicken = EntityTypes.CHICKEN.createEntity(EntityInitInfo.builder()
                    .dimension(dimension)
                    .pos(hitPos.x(), hitPos.y(), hitPos.z())
                    .build());
            chicken.setPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT, thisEntity.getPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT));
            if (chicken instanceof EntityAnimal animalBaby) {
                animalBaby.setBaby(true);
            }
            dimension.getEntityManager().addEntity(chicken);
        }
    }
}
