package cn.allay.api.blockentity.interfaces;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.container.ContainerHolder;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends
        BlockEntity, ContainerHolder {
    String BLOCK_ENTITY_ID = "Barrel";
    BlockEntityType<BlockEntityBarrel> BARREL_TYPE = BlockEntityTypeBuilder
            .builder(BlockEntityBarrel.class)
            .blockEntityId(BLOCK_ENTITY_ID)
            .build();
}
