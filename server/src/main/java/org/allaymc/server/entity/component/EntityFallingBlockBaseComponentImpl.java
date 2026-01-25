package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityFallEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author IWareQ
 */
@Getter
@Setter
public class EntityFallingBlockBaseComponentImpl extends EntityBaseComponentImpl implements EntityFallingBlockBaseComponent {

    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    protected BlockState blockState;

    public EntityFallingBlockBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickFalling();
    }

    protected void tickFalling() {
        if (this.willBeDespawnedNextTick() || physicsComponent.isOnGround()) {
            // The falling block entity already became block
            return;
        }

        var dimension = getDimension();
        var currentBlock = dimension.getBlockState(location);
        if (currentBlock.getBlockType() == BlockTypes.AIR) {
            return;
        }

        if (currentBlock.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            dimension.setBlockState(location.floor(new Vector3d()), BlockTypes.AIR.getDefaultState());
        }
    }

    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        var dimension = getDimension();
        // It is better to place the block when the Entity is removed, so that it looks visually normal.
        dimension.getEntityManager().removeEntity(thisEntity, () -> {
            if (!(blockState.getBehavior() instanceof BlockFallableComponent fallableComponent)) {
                return;
            }

            var damage = fallableComponent.calculateDamage(event.getFallDistance());
            if (damage > 0) {
                var damageContainer = fallableComponent.createDamageContainer(damage);
                dimension.getEntityManager().getPhysicsService().computeCollidingEntities(getOffsetAABB(), true)
                        .stream()
                        .filter(entity -> entity instanceof EntityLivingComponent)
                        .map(EntityLivingComponent.class::cast)
                        .forEach(entity -> entity.attack(damageContainer));
            }

            if (!physicsComponent.getBlockStateStandingOn().getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
                // Falling on a block which is not full in upper face, for example torch.
                // In this case, the falling block should be turned into item instead of block
                dimension.dropItem(blockState.toItemStack(), location);
            } else {
                fallableComponent.onLanded(location, event.getFallDistance(), blockState);
            }
        });
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt("BlockStateHash", blockState.blockStateHash())
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt("BlockStateHash", blockStateHash ->
                blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash)
        );
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.49, 0, -0.49, 0.49, 0.98, 0.49);
    }

    @Override
    public boolean hasEntityCollision() {
        return false;
    }
}
