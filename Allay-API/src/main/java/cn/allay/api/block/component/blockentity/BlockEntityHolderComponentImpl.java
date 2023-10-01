package cn.allay.api.block.component.blockentity;

import cn.allay.api.block.component.event.BlockOnInteractEvent;
import cn.allay.api.block.component.event.BlockOnNeighborUpdateEvent;
import cn.allay.api.block.component.event.BlockOnPlaceEvent;
import cn.allay.api.block.component.event.BlockOnReplaceEvent;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.ComponentEventListener;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.position.Position3i;
import lombok.extern.slf4j.Slf4j;

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
        blockEntity.onReplace(event);
        removeBlockEntityAt(pos);
    }

    @ComponentEventListener
    private void onNeighborChanged(BlockOnNeighborUpdateEvent event) {
        var pos = new Position3i(event.updated(), event.world());
        var blockEntity = getBlockEntity(pos);
        blockEntity.onNeighborUpdate(event);
    }

    @ComponentEventListener
    private void onInteract(BlockOnInteractEvent event) {
        var pos = event.blockPos();
        var blockEntity = getBlockEntity(pos.x(), pos.y(), pos.z(), event.world());
        blockEntity.onInteract(event);
    }

    @Override
    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }
}
