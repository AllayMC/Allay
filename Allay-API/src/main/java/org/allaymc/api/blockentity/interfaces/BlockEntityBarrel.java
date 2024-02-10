package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.api.identifier.Identifier;

import static org.allaymc.api.blockentity.component.BlockEntityComponentImplFactory.getFactory;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends BlockEntity, BlockEntityContainerHolderComponent {
    BlockEntityType<BlockEntityBarrel> BARREL_TYPE = BlockEntityTypeBuilder
            .builder(BlockEntityBarrel.class)
            .identifier(new Identifier("minecraft:barrel"))
            .addComponent(getFactory().createBlockEntityBarrelBaseComponent())
            .addComponent(getFactory().createBlockEntityContainerHolderComponent(new BarrelContainer()))
            .build();

}
