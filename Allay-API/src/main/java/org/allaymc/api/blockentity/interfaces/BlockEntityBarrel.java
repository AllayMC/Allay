package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.container.impl.BarrelContainer;

import static org.allaymc.api.blockentity.component.BlockEntityComponentImplFactory.getFactory;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends BlockEntity, BlockEntityContainerHolderComponent {
    String BLOCK_ENTITY_ID = "Barrel";
    BlockEntityType<BlockEntityBarrel> BARREL_TYPE = BlockEntityTypeBuilder
            .builder(BlockEntityBarrel.class)
            .blockEntityId(BLOCK_ENTITY_ID)
            .addComponent(getFactory().createBlockEntityBarrelBaseComponent())
            .addComponent(getFactory().createBlockEntityContainerHolderComponent(new BarrelContainer()))
            .build();

}
