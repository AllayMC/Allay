package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntitySplashPotionBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntitySplashPotionBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntitySplashPotionBaseComponent {

    protected PotionType potionType;

    public EntitySplashPotionBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public double getGravity() {
        return 0.05;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (this.willBeDespawnedNextTick()) {
            return;
        }

        this.despawn();
        this.splash(null, other, 1);
    }

    @Override
    protected void onHitBlock(BlockStateWithPos block, Vector3dc hitPos) {
        if (this.willBeDespawnedNextTick()) {
            return;
        }

        this.despawn();
        this.splash(block, null, 1);
    }

    protected void splash(BlockStateWithPos blockBeingHit, Entity entityBeingHit, float durationMultiplier) {
        this.location.dimension().addLevelSoundEvent(this.location, SoundEvent.GLASS);
        if (potionType == null) {
            return;
        }

        getDimension().addLevelEvent(this.location, LevelEvent.PARTICLE_POTION_SPLASH, potionType.getColor().getRGB());
        var effects = potionType.getEffects();
        var aabb = MathUtils.grow(getOffsetAABB(), new Vector3d(4.125, 2.125, 4.125));
        if (!effects.isEmpty()) {
            var entities = getDimension().getEntityService().getPhysicsService().computeCollidingEntities(aabb);
            for (var entity : entities) {
                var pos = entity.getLocation();
                var distance = pos.distance(this.location);
                if (distance > 4) {
                    continue;
                }

                var factor = entity != entityBeingHit ? 1.0 - distance / 4.0 : 1.0;
                for (var effect : effects) {
                    // Zero duration is still meaningful for instantaneous effects
                    effect.setDuration((int) (effect.getDuration() * durationMultiplier * factor));
                    entity.addEffect(effect);
                }
            }
        } else if (potionType == PotionType.WATER) {
            // Splash the block being hit
            if (blockBeingHit != null) {
                blockBeingHit.getBehavior().onSplash(blockBeingHit);
            }

            // Splash the block at current pos
            var blockPos = new Vector3i();
            blockPos.set(this.location.floor());
            var block = getDimension().getBlockState(blockPos);
            block.getBehavior().onSplash(new BlockStateWithPos(block, new Position3i(blockPos, getDimension())));

            // Splash horizontal neighbor blocks
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var offsetPos = face.offsetPos(blockPos);
                block = getDimension().getBlockState(offsetPos);
                block.getBehavior().onSplash(new BlockStateWithPos(block, new Position3i(offsetPos, getDimension())));
            }

            // Splash entities
            if (entityBeingHit != null) {
                entityBeingHit.onSplash();
            }
        }
    }
}
