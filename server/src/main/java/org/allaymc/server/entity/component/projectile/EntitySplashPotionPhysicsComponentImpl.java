package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntitySplashPotionProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityLiving;
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
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntitySplashPotionPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @Dependency
    protected EntitySplashPotionProjectileComponent splashPotionProjectileComponent;

    @Override
    public double getGravity() {
        return 0.05;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        thisEntity.remove();
        this.splash(null, other, 1);
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        thisEntity.remove();
        this.splash(block, null, 1);
    }

    protected void splash(Block blockBeingHit, Entity entityBeingHit, float durationMultiplier) {
        var dimension = thisEntity.getDimension();
        dimension.addSound(thisEntity.getLocation(), SimpleSound.GLASS_BREAK);
        var potionType = splashPotionProjectileComponent.getPotionType();
        if (potionType == null) {
            return;
        }

        dimension.addParticle(thisEntity.getLocation(), new SplashParticle(potionType.getColor()));
        var effects = potionType.getEffects();
        var aabb = MathUtils.grow(new AABBd(thisEntity.getOffsetAABB()), new Vector3d(4.125, 2.125, 4.125));
        if (!effects.isEmpty()) {
            var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);
            for (var entity : entities) {
                if (!(entity instanceof EntityLiving living)) {
                    continue;
                }

                var pos = living.getLocation();
                var distance = pos.distance(thisEntity.getLocation());
                if (distance > 4) {
                    continue;
                }

                var factor = living != entityBeingHit ? 1.0 - distance / 4.0 : 1.0;
                for (var effect : effects) {
                    // Zero duration is still meaningful for instantaneous effects
                    effect.setDuration((int) (effect.getDuration() * durationMultiplier * factor));
                    living.addEffect(effect);
                }
            }
        } else if (potionType == PotionType.WATER) {
            // Splash the block being hit
            if (blockBeingHit != null) {
                blockBeingHit.getBehavior().onSplash(blockBeingHit);
            }

            // Splash the block at current pos
            var blockPos = new Vector3i();
            blockPos.set(thisEntity.getLocation().floor(new Vector3d()));
            var block = dimension.getBlockState(blockPos);
            block.getBehavior().onSplash(new Block(block, new Position3i(blockPos, dimension)));

            // Splash horizontal neighbor blocks
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var offsetPos = face.offsetPos(blockPos);
                block = dimension.getBlockState(offsetPos);
                block.getBehavior().onSplash(new Block(block, new Position3i(offsetPos, dimension)));
            }

            // Splash entities
            if (entityBeingHit != null) {
                entityBeingHit.onSplash();
            }
        }
    }
}
