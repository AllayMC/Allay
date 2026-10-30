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
 * {@code EntityFallingBlockBaseComponentImpl} represents a falling block state
 * and resolves its transition back into world content. During descent, the
 * entity clears replaceable blocks at its position. Landing consumes the
 * represented block behavior to calculate damage and choose between placement
 * and an item drop. The injected {@code physicsComponent} supplies grounded
 * state and the support block used for that choice.
 *
 * <p>
 * Removal and landing consequences are linked through the entity manager
 * callback. {@code onFall} requests removal first and performs damage, support
 * inspection, and block-specific landing afterward, so the falling entity
 * leaves before the replacement content is introduced.
 *
 * @author IWareQ
 */
@Getter
@Setter
public class EntityFallingBlockBaseComponentImpl extends EntityBaseComponentImpl implements EntityFallingBlockBaseComponent {

    /**
     * Injected motion and supporting-block state component.
     */
    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    /**
     * {@code blockState} retains the represented block state. Landing and
     * serialization require a usable state because both paths access its
     * behavior or state hash.
     */
    protected BlockState blockState;

    /**
     * Creates the falling block base with the supplied entity initialization
     * context.
     */
    public EntityFallingBlockBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Runs inherited entity ticking before clearing replaceable blocks along
     * the descent. Pending removal or grounded state suppresses descent
     * cleanup, leaving landing resolution to the fall event path.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickFalling();
    }

    /**
     * Clears a replaceable block at the current falling entity position. The
     * method leaves air and nonreplaceable blocks unchanged, and it stops
     * processing once landing or queued removal has begun.
     */
    protected void tickFalling() {
        if (this.willBeDespawnedLater() || physicsComponent.isOnGround()) {
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

    /**
     * Resolves a landing after requesting removal of the falling entity. The
     * entity manager callback performs world changes after removal returns
     * normally, rather than introducing replacement content while the falling
     * entity remains registered. A rejected removal transition or failure
     * before callback invocation prevents those landing consequences.
     *
     * <p>
     * A represented state without {@code BlockFallableComponent} ends the
     * callback without placement or drops. The damage pass reuses one mutable
     * damage container across overlapping living components, so target attack
     * modifiers or retained damage references can carry state between victims.
     * Afterward, an incomplete upper support face produces an item drop; a
     * full face delegates to block-specific landing behavior.
     */
    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        var dimension = getDimension();
        // Defer landing consequences until removal finishes so the falling entity
        // leaves before replacement world content appears.
        dimension.getEntityManager().removeEntity(thisEntity, () -> {
            if (!(blockState.getBehavior() instanceof BlockFallableComponent fallableComponent)) {
                return;
            }

            var damage = fallableComponent.calculateDamage(event.getFallDistance());
            if (damage > 0) {
                // Reuse one mutable damage container for every victim; attack
                // mutation or retention can therefore affect later victims in this
                // landing pass.
                var damageContainer = fallableComponent.createDamageContainer(damage);
                dimension.getEntityManager().getPhysicsService().computeCollidingEntities(getOffsetAABB(), true)
                        .stream()
                        .filter(entity -> entity instanceof EntityLivingComponent)
                        .map(EntityLivingComponent.class::cast)
                        .forEach(entity -> entity.attack(damageContainer));
            }

            if (!physicsComponent.getBlockStateStandingOn().getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
                // An incomplete upper collision face cannot support placement, so
                // preserve the represented block as a dropped item instead.
                dimension.dropItem(blockState.toItemStack(), location);
            } else {
                fallableComponent.onLanded(location, event.getFallDistance(), blockState);
            }
        });
    }

    /**
     * Returns inherited entity data with the represented block state hash.
     * {@code blockState} must be initialized before this call because
     * serialization reads the retained state directly without an absence
     * fallback.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt("BlockStateHash", blockState.blockStateHash())
                .build();
    }

    /**
     * Loads inherited entity data and resolves a stored block state hash
     * through the block palette. A missing tag retains the previous state,
     * while palette lookup determines the result for unknown hashes.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt("BlockStateHash", blockStateHash ->
                blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash)
        );
    }

    /**
     * Returns the falling block local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        // The box leaves a small inset within one block to avoid full-cell edge
        // overlap.
        return new AABBd(-0.49, 0, -0.49, 0.49, 0.98, 0.49);
    }

    /**
     * Returns {@code false} so falling blocks do not participate as pushable
     * entities.
     */
    @Override
    public boolean hasEntityCollision() {
        return false;
    }
}
