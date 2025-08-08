package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.*;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;

/**
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class BlockEntityHolderComponentImpl<T extends BlockEntity> implements BlockEntityHolderComponent<T> {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_holder_component");

    @Getter
    protected final BlockEntityType<T> blockEntityType;

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var pos = event.getCurrentBlock().getPos();

        createBlockEntity(pos, false);
        var blockEntity = getBlockEntity(pos);
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).onPlace(event);

        // Send block entity to client after onPlace()
        // because onPlace() method may make some changes on this block entity
        if (blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToViewers();
        }
    }

    @EventHandler
    protected void onBlockRemove(CBlockOnReplaceEvent event) {
        var pos = event.getCurrentBlock().getPos();
        var blockEntity = getBlockEntity(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).onReplace(event);
        removeBlockEntity(pos);
    }

    @EventHandler
    protected void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().getPos());
        var blockEntity = getBlockEntity(pos);
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).onNeighborUpdate(event);
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickedBlockPos();
        var blockEntity = getBlockEntity(pos.x(), pos.y(), pos.z(), event.getDimension());
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).onInteract(event);
    }

    @EventHandler
    protected void onPunch(CBlockOnPunchEvent event) {
        var blockEntity = getBlockEntity(event.getCurrentBlock().getPos());
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).onPunch(event);
    }
}
