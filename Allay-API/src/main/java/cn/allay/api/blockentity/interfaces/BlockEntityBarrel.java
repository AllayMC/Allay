package cn.allay.api.blockentity.interfaces;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponent;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponentImpl;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.container.ContainerHolder;
import cn.allay.api.container.impl.BarrelContainer;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends
        BlockEntity, BlockEntityContainerHolderComponent {
    String BLOCK_ENTITY_ID = "Barrel";
    BlockEntityType<BlockEntityBarrel> BARREL_TYPE = BlockEntityTypeBuilder
            .builder(BlockEntityBarrel.class)
            .blockEntityId(BLOCK_ENTITY_ID)
            .addComponent(
                    ComponentProvider.of(
                            initInfo -> new BlockEntityContainerHolderComponentImpl(
                                    new BarrelContainer()
                            ),
                            BlockEntityContainerHolderComponentImpl.class
                    ))
            .build();
}
