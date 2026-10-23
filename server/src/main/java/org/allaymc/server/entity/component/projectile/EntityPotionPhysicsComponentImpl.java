package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.eventbus.event.entity.PotionSplashEvent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.particle.SplashParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.primitives.AABBd;

/**
 * {@code EntityPotionPhysicsComponentImpl} resolves splash and lingering
 * potion impacts using shared target selection and effect application. Each
 * impact queues projectile removal before the splash pass. Glass sound occurs
 * even without configured contents, while configured contents supply color,
 * effects, or water interactions at the current entity location.
 *
 * <p>
 * Effectful splashes combine distance, direct-hit identity, and the subclass
 * duration multiplier. The pass obtains one list of mutable effect instances
 * and reuses it across targets; duration scaling therefore changes values seen
 * by subsequent targets and may also change instances already retained by
 * earlier targets. Per-target cancellation applies only to splash-potion
 * entities, and the subclass completion hook runs after an effectful pass.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public abstract class EntityPotionPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * Injected component supplying optional potion contents.
     */
    @Dependency
    protected EntityPotionComponent potionComponent;

    /**
     * Returns potion downward acceleration in blocks per tick squared.
     */
    @Override
    public double getGravity() {
        return 0.05;
    }

    /**
     * Returns the subclass factor applied to noninstant splash effect
     * durations.
     */
    protected abstract float getDurationMultiplier();

    /**
     * Queues potion removal and splashes with the impacted entity as the
     * direct target. An already queued removal suppresses processing, and
     * direct-target identity can select full effect duration in the splash
     * pass.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        thisEntity.remove();
        splash(null, other);
    }

    /**
     * Queues potion removal and splashes with the impacted block as the direct
     * block target. An already queued removal suppresses processing, while
     * water contents can invoke splash handling on that block.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        thisEntity.remove();
        splash(block, null);
    }

    /**
     * Applies the potion break presentation and any configured effects or
     * water interactions. Missing contents stop after glass sound, while
     * configured contents add a colored particle. Effectful contents query
     * nearby living targets and reject target positions beyond four blocks.
     *
     * <p>
     * Direct target identity supplies full attenuation strength; other targets
     * use linear distance attenuation. Noninstant durations are scaled on
     * shared per-pass effect objects before addition, so later targets can
     * rescale objects already retained by earlier targets. Splash-event
     * cancellation skips one target, and the completion hook still runs after
     * the effectful target loop. Water instead splashes selected blocks and
     * the directly hit entity.
     */
    protected void splash(Block blockBeingHit, Entity entityBeingHit) {
        var dimension = thisEntity.getDimension();
        var location = thisEntity.getLocation();

        dimension.addSound(location, SimpleSound.GLASS_BREAK);

        var potionType = potionComponent.getPotionType();
        if (potionType == null) {
            return;
        }

        dimension.addParticle(location, new SplashParticle(potionType.getColor()));

        var effects = potionType.getEffects();
        // The query grows beyond the radial effect distance so target bounds enter
        // the broad-phase volume before the position-distance test.
        var aabb = MathUtils.grow(new AABBd(thisEntity.getOffsetAABB()), new Vector3d(4.125, 2.125, 4.125));

        if (!effects.isEmpty()) {
            var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);
            for (var entity : entities) {
                if (!(entity instanceof EntityLiving living)) {
                    continue;
                }

                var pos = living.getLocation();
                var distance = pos.distance(location);
                // Position distance caps effect application at four blocks even
                // when a target box overlaps the larger search volume.
                if (distance > 4) {
                    continue;
                }

                // Direct-hit identity receives full strength; other target
                // durations fall linearly to zero at the radial boundary.
                var factor = living != entityBeingHit ? 1.0 - distance / 4.0 : 1.0;

                if (thisEntity instanceof EntitySplashPotion splashPotion) {
                    var event = new PotionSplashEvent(splashPotion, living);
                    if (!event.call()) {
                        continue;
                    }
                }

                for (var effect : effects) {
                    // Leave instant effects unscaled; noninstant scaling mutates
                    // these reused effect instances, including values later targets
                    // will observe.
                    if (effect.getDuration() > 1) {
                        effect.setDuration((int) (effect.getDuration() * getDurationMultiplier() * factor));
                    }
                    living.addEffect(effect);
                }
            }

            afterSplash(potionType);
        } else if (potionType == PotionType.WATER) {
            if (blockBeingHit != null) {
                blockBeingHit.getBehavior().onSplash(blockBeingHit);
            }

            var blockPos = new Vector3i();
            blockPos.set(location.floor(new Vector3d()));
            var block = dimension.getBlockState(blockPos);
            block.getBehavior().onSplash(new Block(block, new Position3i(blockPos, dimension)));

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var offsetPos = face.offsetPos(blockPos);
                block = dimension.getBlockState(offsetPos);
                block.getBehavior().onSplash(new Block(block, new Position3i(offsetPos, dimension)));
            }

            if (entityBeingHit != null) {
                entityBeingHit.onSplash();
            }
        }
    }

    /**
     * Provides a subclass hook after processing an effectful splash pass. The
     * default implementation adds no behavior, and the parent does not call
     * this hook for missing or effectless potion contents.
     */
    protected void afterSplash(PotionType potionType) {
    }
}
