package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.*;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.component.ComponentClass;

import java.util.Objects;

/**
 * {@code BlockBlockEntityHolderComponentImpl} manages the block entity associated
 * with a configured block entity type and forwards block component events to that
 * entity. Position lookups create missing entities in loaded chunks. A stored entity
 * with another type is logged, removed, and replaced, so the returned entity matches
 * the type expected by companion block components.
 *
 * <p>
 * Recovery is an ordered remove-and-create sequence rather than a transaction:
 * creation failures leave the mismatched entity removed. Placement forwards its event
 * before queuing viewer delivery, allowing block entity handlers to update their data
 * before clients read it. Replacement forwards its event before removal, while
 * neighbor, interaction, and punch handlers retrieve or create the entity before
 * forwarding their context.
 *
 * @param <T> block entity interface managed by this holder
 *
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class BlockBlockEntityHolderComponentImpl<T extends BlockEntity> implements BlockBlockEntityHolderComponent<T> {
    /**
     * {@code IDENTIFIER} names the block entity holder implementation when providers
     * and dependency injection identify this component.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_block_entity_holder_component");

    /**
     * Configured block entity creation type.
     */
    @Getter
    protected final BlockEntityType<T> blockEntityType;

    /**
     * Returns the entity at the supplied world block coordinates with this holder's
     * configured type. Missing entities are created in the loaded chunk; an unloaded
     * chunk causes {@code IllegalStateException}.
     *
     * <p>
     * A mismatched stored type is logged and removed before a replacement is created.
     * The two operations do not roll back: replacement failure can leave the position
     * without its previous entity. Matching entities are returned directly, so
     * callers use the existing shared entity instance.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getBlockEntity(int x, int y, int z, Dimension dimension) {
        var blockEntity = dimension.getBlockEntity(x, y, z);

        if (blockEntity == null) {
            return createBlockEntity(dimension, x, y, z);
        } else if (blockEntity.getBlockEntityType() != this.blockEntityType) {
            log.warn(
                    "Mismatched block entity type at pos {}, {}, {}, {}! Expected: {}, actual: {}",
                    x, y, z, dimension.getWorld().getName() + "#" + dimension.getDimensionType().getIdentifier(),
                    this.blockEntityType.getName(), blockEntity.getBlockEntityType().getName()
            );
            // A mismatched entity cannot serve this holder's typed consumers. Remove
            // it before creating the configured replacement, without preserving
            // rollback state.
            removeBlockEntity(dimension, x, y, z);
            return createBlockEntity(dimension, x, y, z);
        }
        return (T) blockEntity;
    }

    /**
     * Creates and adds a new configured entity at the supplied world coordinates. The
     * dimension must be non-{@code null}, its chunk must be loaded, and that position
     * must not already contain a block entity.
     *
     * @throws IllegalStateException if the chunk is unloaded or the position already contains an entity
     */
    protected T createBlockEntity(Dimension dimension, int x, int y, int z) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }

        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            throw new IllegalStateException("Trying to create a block entity when block entity already exists! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }

        var blockEntity = this.blockEntityType.createBlockEntity(BlockEntityInitInfo.builder().pos(x, y, z).dimension(dimension).build());
        chunk.addBlockEntity(blockEntity);

        return blockEntity;
    }

    /**
     * Removes the block entity at the supplied world block coordinates. The dimension
     * must be non-{@code null}, its chunk must be loaded, and a block entity must
     * exist at that position.
     *
     * @throws IllegalStateException if the chunk is unloaded or no entity is removed
     */
    protected void removeBlockEntity(Dimension dimension, int x, int y, int z) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to remove a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        if (chunk.removeBlockEntity(x & 15, y, z & 15) == null) {
            throw new IllegalStateException("Trying to remove a block entity which is not exists in Dimension " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
    }

    /**
     * Retrieves the incoming block entity, applies a held item's custom name, and
     * forwards placement. Player context may be absent; viewer delivery is queued
     * after forwarding so handlers can finish changing the entity.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var pos = event.getCurrentBlock().getPosition();
        var blockEntity = getBlockEntity(pos);

        var placementInfo = event.getPlacementInfo();
        if (placementInfo != null) {
            var itemInHand = placementInfo.player().getItemInHand();
            if (itemInHand.hasCustomName()) {
                blockEntity.setCustomName(itemInHand.getCustomName());
            }
        }

        forwardEvent(blockEntity, event);
        // Queue viewer delivery after entity placement handlers finish, so the live
        // entity data is read by a later chunk task after block placement.
        getBaseComponentImpl(blockEntity).sendBlockEntityToViewers(false);
    }

    /**
     * Forwards replacement to the current block entity before removing it from the
     * chunk. Lookup can create a missing entity first, and a forwarding failure
     * prevents the subsequent removal.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        var pos = event.getCurrentBlock().getPosition();
        var blockEntity = getBlockEntity(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        forwardEvent(blockEntity, event);
        removeBlockEntity(pos.dimension(), pos.x(), pos.y(), pos.z());
    }

    /**
     * Dispatches the supplied block event through the entity's component manager.
     * {@code blockEntity} must be a {@code ComponentClass} implementation, and
     * dispatch uses the same event instance as the block.
     */
    protected void forwardEvent(BlockEntity blockEntity, Event event) {
        ((ComponentClass) blockEntity).getManager().callEvent(event);
    }

    /**
     * Returns the server base component used for immediate and deferred viewer
     * delivery by this entity. The entity must be a {@code BlockEntityImpl} whose
     * base component is a {@code BlockEntityBaseComponentImpl}.
     */
    protected BlockEntityBaseComponentImpl getBaseComponentImpl(BlockEntity blockEntity) {
        return (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
    }

    /**
     * Retrieves the block entity at the updated block position and forwards its
     * neighbor event. A missing entity is created through the normal holder lookup
     * before dispatch.
     */
    @EventHandler
    protected void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().getPosition());
        forwardEvent(getBlockEntity(pos), event);
    }

    /**
     * Retrieves the block entity at the interaction's clicked position and forwards
     * the event. The interaction context must be present; missing entities use the
     * normal holder creation path.
     */
    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickedBlockPos();
        forwardEvent(getBlockEntity(pos.x(), pos.y(), pos.z(), event.getDimension()), event);
    }

    /**
     * Retrieves the punched block's entity and forwards the same punch event. A
     * missing entity is created through the normal holder lookup before its component
     * handlers receive the event.
     */
    @EventHandler
    protected void onPunch(CBlockOnPunchEvent event) {
        forwardEvent(getBlockEntity(event.getCurrentBlock().getPosition()), event);
    }
}
