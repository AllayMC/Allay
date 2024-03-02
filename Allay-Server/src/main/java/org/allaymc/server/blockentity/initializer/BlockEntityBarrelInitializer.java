package org.allaymc.server.blockentity.initializer;

import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.server.blockentity.component.barrel.BlockEntityBarrelBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityContainerHolderComponentImpl;

/**
 * Allay Project 2024/2/24
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrelInitializer {
    static void init() {
        BlockEntityTypes.BARREL_TYPE = BlockEntityTypeBuilder
                .builder(BlockEntityBarrel.class)
                .identifier(new Identifier("minecraft:barrel"))
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }
}
