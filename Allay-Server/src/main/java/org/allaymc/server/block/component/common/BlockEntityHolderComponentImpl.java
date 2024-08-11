package org.allaymc.server.block.component.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.component.event.CBlockOnInteractEvent;
import org.allaymc.api.block.component.event.CBlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.api.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class BlockEntityHolderComponentImpl<T extends BlockEntity> implements BlockEntityHolderComponent<T> {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_holder_component");

    @Getter
    protected final BlockEntityType<T> blockEntityType;

    @EventHandler
    private void onBlockPlace(CBlockOnPlaceEvent event) {
        var pos = event.getCurrentBlockState().pos();

        createBlockEntityAt(pos, false);
        var blockEntity = getBlockEntityAt(pos);
        blockEntity.onPlace(event);

        // Send block entity to client after onPlace()
        // because onPlace() method may make some changes on this block entity
        if (blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToAll();
        }
    }

    @EventHandler
    private void onBlockRemove(CBlockOnReplaceEvent event) {
        var pos = event.getCurrentBlockState().pos();
        var blockEntity = getBlockEntityAt(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        blockEntity.onReplace(event);
        removeBlockEntityAt(pos);
    }

    @EventHandler
    private void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().pos());
        var blockEntity = getBlockEntityAt(pos);
        blockEntity.onNeighborUpdate(event);
    }

    @EventHandler
    private void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickBlockPos();
        var blockEntity = getBlockEntityAt(pos.x(), pos.y(), pos.z(), event.getDimension());
        blockEntity.onInteract(event);
    }
}
