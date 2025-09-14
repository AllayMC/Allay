package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.identifier.Identifier;
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
        var pos = event.getCurrentBlock().getPosition();

        createBlockEntity(pos, false);
        var blockEntity = getBlockEntity(pos);
        getBaseComponentImpl(blockEntity).onBlockPlace(event);

        // Send block entity to client after called onPlace() because onPlace() method may make some changes
        // on this block entity
        if (blockEntity.sendToClient()) {
            // Send the block entity in the next chunk tick because we need to wait for the block being sent first
            blockEntity.sendBlockEntityToViewers(false);
        }
    }

    @EventHandler
    protected void onBlockRemove(CBlockOnReplaceEvent event) {
        var pos = event.getCurrentBlock().getPosition();
        var blockEntity = getBlockEntity(pos);
        if (blockEntity == null) {
            log.warn("Block entity not found at pos: {}", pos);
            return;
        }
        getBaseComponentImpl(blockEntity).onBlockReplace(event);
        removeBlockEntity(pos);
    }

    @EventHandler
    protected void onNeighborChanged(CBlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.getCurrent().getPosition());
        var blockEntity = getBlockEntity(pos);
        getBaseComponentImpl(blockEntity).onBlockNeighborUpdate(event);
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var pos = event.getInteractInfo().clickedBlockPos();
        var blockEntity = getBlockEntity(pos.x(), pos.y(), pos.z(), event.getDimension());
        getBaseComponentImpl(blockEntity).onBlockInteract(event);
    }

    @EventHandler
    protected void onPunch(CBlockOnPunchEvent event) {
        var blockEntity = getBlockEntity(event.getCurrentBlock().getPosition());
        getBaseComponentImpl(blockEntity).onBlockPunch(event);
    }

    protected BlockEntityBaseComponentImpl getBaseComponentImpl(BlockEntity blockEntity) {
        return (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
    }
}
