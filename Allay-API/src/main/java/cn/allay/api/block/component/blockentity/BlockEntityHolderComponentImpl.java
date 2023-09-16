package cn.allay.api.block.component.blockentity;

import cn.allay.api.block.component.event.BlockOnPlaceEvent;
import cn.allay.api.block.component.event.BlockOnReplaceEvent;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.ComponentEventListener;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
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
        log.debug("Create block entity at " + event.currentBlockState().pos());
        createBlockEntityAt(event.currentBlockState().pos());
    }

    @ComponentEventListener
    private void onBlockRemove(BlockOnReplaceEvent event) {
        log.debug("Remove block entity at " + event.currentBlockState().pos());
        removeBlockEntityAt(event.currentBlockState().pos());
    }

    @Override
    @Impl
    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }
}
