package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnNeighborUpdateEvent;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;

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
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var pos = event.getCurrentBlockState().pos();

        createBlockEntityAt(pos, false);
        var blockEntity = getBlockEntityAt(pos);
        ((BlockEntityBaseComponentImpl)blockEntity).onPlace(event);

        // Send block entity to client after onPlace()
        // because onPlace() method may make some changes on this block entity
        if (blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToViewers();
        }
    }

    @EventHandler
    protected void onBlockRemove(CBlockOnReplaceEvent event) {
        var pos = event.getCurrentBlockState().pos();
        var blockEntity = getBlockEntityAt(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        ((BlockEntityBaseComponentImpl)blockEntity).onReplace(event);
        removeBlockEntityAt(pos);
    }

    @EventHandler
    protected void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().pos());
        var blockEntity = getBlockEntityAt(pos);
        ((BlockEntityBaseComponentImpl)blockEntity).onNeighborUpdate(event);
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickBlockPos();
        var blockEntity = getBlockEntityAt(pos.x(), pos.y(), pos.z(), event.getDimension());
        ((BlockEntityBaseComponentImpl)blockEntity).onInteract(event);
    }
}
