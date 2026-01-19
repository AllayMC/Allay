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
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class BlockBlockEntityHolderComponentImpl<T extends BlockEntity> implements BlockBlockEntityHolderComponent<T> {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_block_entity_holder_component");

    @Getter
    protected final BlockEntityType<T> blockEntityType;

    @SuppressWarnings("unchecked")
    @Override
    public T getBlockEntity(int x, int y, int z, Dimension dimension) {
        var blockEntity = dimension.getBlockEntity(x, y, z);

        if (blockEntity == null) {
            return createBlockEntity(dimension, x, y, z);
        } else if (blockEntity.getBlockEntityType() != this.blockEntityType) {
            log.warn(
                    "Mismatched block entity type at pos {}, {}, {}, {}! Expected: {}, actual: {}",
                    x, y, z, dimension.getWorld().getName() + "#" + dimension.getDimensionInfo().dimensionId(),
                    this.blockEntityType.getName(), blockEntity.getBlockEntityType().getName()
            );
            // Remove the incorrect block entity and create a new one
            removeBlockEntity(dimension, x, y, z);
            return createBlockEntity(dimension, x, y, z);
        }

        // We have checked the type above so we can safely cast
        return (T) blockEntity;
    }

    protected T createBlockEntity(Dimension dimension, int x, int y, int z) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }

        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            // Should never happen since we have checked in getBlockEntity() method
            throw new IllegalStateException("Trying to create a block entity when block entity already exists! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }

        var blockEntity = this.blockEntityType.createBlockEntity(BlockEntityInitInfo.builder().pos(x, y, z).dimension(dimension).build());
        chunk.addBlockEntity(blockEntity);

        return blockEntity;
    }

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

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var pos = event.getCurrentBlock().getPosition();
        var blockEntity = getBlockEntity(pos);

        var placementInfo = event.getPlacementInfo();
        if (placementInfo != null) {
            // Set the block entity's custom name to the item's custom name
            blockEntity.setCustomName(placementInfo.player().getItemInHand().getCustomName());
        }

        forwardEvent(blockEntity, event);
        // Send the block entity to the client after called onPlace() because onPlace() method may make some
        // changes on this block entity. Also, we should send the block entity in the next chunk tick because
        // we need to wait for the block being sent first
        getBaseComponentImpl(blockEntity).sendBlockEntityToViewers(false);
    }

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

    protected void forwardEvent(BlockEntity blockEntity, Event event) {
        ((ComponentClass) blockEntity).getManager().callEvent(event);
    }

    protected BlockEntityBaseComponentImpl getBaseComponentImpl(BlockEntity blockEntity) {
        return (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
    }

    @EventHandler
    protected void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().getPosition());
        forwardEvent(getBlockEntity(pos), event);
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickedBlockPos();
        forwardEvent(getBlockEntity(pos.x(), pos.y(), pos.z(), event.getDimension()), event);
    }

    @EventHandler
    protected void onPunch(CBlockOnPunchEvent event) {
        forwardEvent(getBlockEntity(event.getCurrentBlock().getPosition()), event);
    }
}
