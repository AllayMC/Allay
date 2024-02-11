package org.allaymc.server.block.component.common;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.annotation.ComponentEventListener;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.math.position.Position3i;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityHolderComponentImpl<T extends BlockEntity> implements BlockEntityHolderComponent<T> {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_holder_component");

    protected final BlockEntityType<T> blockEntityType;

    public BlockEntityHolderComponentImpl(BlockEntityType<T> blockEntityType) {
        this.blockEntityType = blockEntityType;
    }

    @ComponentEventListener
    private void onBlockPlace(BlockOnPlaceEvent event) {
        var pos = event.currentBlockState().pos();
        createBlockEntityAt(pos);
        var blockEntity = getBlockEntity(pos);
        blockEntity.onPlace(event);
    }

    @ComponentEventListener
    private void onBlockRemove(BlockOnReplaceEvent event) {
        var pos = event.currentBlockState().pos();
        var blockEntity = getBlockEntity(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        blockEntity.onReplace(event);
        removeBlockEntityAt(pos);
    }

    @ComponentEventListener
    private void onNeighborChanged(BlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.updated(), event.dimension());
        var blockEntity = getBlockEntity(pos);
        blockEntity.onNeighborUpdate(event);
    }

    @ComponentEventListener
    private void onInteract(BlockOnInteractEvent event) {
        var pos = event.blockPos();
        var blockEntity = getBlockEntity(pos.x(), pos.y(), pos.z(), event.dimension());
        blockEntity.onInteract(event);
    }

    @Override
    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }
}
