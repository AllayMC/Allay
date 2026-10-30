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
 * {@code EntityEggPhysicsComponentImpl} resolves egg impacts into break
 * particles and possible chicken hatching. Living targets receive a
 * zero-damage projectile attack before the egg breaks, and spawned chickens
 * inherit the egg climate variant and start as babies when supported.
 *
 * @author daoge_cmd
 */
public class EntityEggPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * {@code CHICKEN_SPAWN_CHANCE} is the one-in-eight denominator selecting
     * whether an impact attempts chicken creation at all.
     */
    protected static final int CHICKEN_SPAWN_CHANCE = 8;

    /**
     * {@code FOUR_CHICKENS_CHANCE} is the one-in-thirty-two denominator
     * selecting four chickens after the initial hatch test succeeds.
     */
    protected static final int FOUR_CHICKENS_CHANCE = 32;

    /**
     * {@code MIN_PARTICLE_COUNT} sets the smallest egg-break particle count
     * produced by the random presentation helper.
     */
    protected static final int MIN_PARTICLE_COUNT = 5;

    /**
     * {@code PARTICLE_COUNT_RANGE} supplies the exclusive random span added to
     * the minimum egg-break particle count.
     */
    protected static final int PARTICLE_COUNT_RANGE = 10;

    /**
     * Returns a newly sampled egg-break particle count from five through
     * fourteen.
     */
    protected static int nextParticleCount() {
        return ThreadLocalRandom.current().nextInt(PARTICLE_COUNT_RANGE) + MIN_PARTICLE_COUNT;
    }

    /**
     * Offers zero projectile damage to a living target, then breaks and
     * removes the egg. An already queued removal skips all impact effects, and
     * target attack rejection does not prevent breaking.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            // Route zero damage through the target attack pipeline so that target
            // rules decide whether a knockback response is accepted.
            var damage = DamageContainer.projectile(thisEntity, 0);
            // Subtract current motion from the impact point to supply a source
            // behind the egg along its travel direction.
            damage.setKnockbackSource(hitPos.sub(this.motion, new Vector3d()));
            living.attack(damage);
        }

        handleEggBreak(hitPos);
        thisEntity.remove();
    }

    /**
     * Breaks and removes the egg after an accepted block impact. An already
     * queued removal skips both effects and hatching, while the actual hit
     * block is not otherwise modified here.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        handleEggBreak(hitPos);
        thisEntity.remove();
    }

    /**
     * Emits the egg-break presentation before attempting chicken creation at
     * {@code hitPos}. The helper itself does not remove the egg, leaving that
     * lifecycle step to the entity or block impact handler.
     */
    protected void handleEggBreak(Vector3dc hitPos) {
        addBreakEffect();
        trySpawnChickens(hitPos);
    }

    /**
     * Emits a randomly sized burst of item-break particles at the egg
     * location. The particle item follows the current climate variant, and the
     * count is sampled separately for each invocation.
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
     * Returns blue, brown, or ordinary egg items for the climate variant.
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
     * Attempts hatching at the supplied impact coordinates with a one-in-eight
     * initial chance. A successful hatch creates one chicken unless a second
     * one-in-thirty-two test selects four, and each created chicken inherits
     * the climate variant.
     */
    protected void trySpawnChickens(Vector3dc hitPos) {
        var random = ThreadLocalRandom.current();

        // The initial chance gates all hatching; the four-chicken draw happens only
        // after this test succeeds.
        if (random.nextInt(CHICKEN_SPAWN_CHANCE) != 0) {
            return;
        }

        var dimension = thisEntity.getDimension();

        // This conditional draw selects the hatch size, giving four chicks a
        // combined one-in-two-hundred-fifty-six chance per impact.
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
