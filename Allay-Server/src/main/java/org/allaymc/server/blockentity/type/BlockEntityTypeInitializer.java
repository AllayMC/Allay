package org.allaymc.server.blockentity.type;

import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.api.data.VanillaBlockEntityId;
import org.allaymc.server.blockentity.component.BlockEntityBarrelBaseComponentImpl;
import org.allaymc.server.blockentity.component.BlockEntityChestBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityContainerHolderComponentImpl;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
public final class BlockEntityTypeInitializer {
    public static void initBarrel() {
        BlockEntityTypes.BARREL_TYPE = BlockEntityTypeBuilder
                .builder(BlockEntityBarrel.class)
                .name(VanillaBlockEntityId.BARREL)
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initChest() {
        BlockEntityTypes.CHEST_TYPE = BlockEntityTypeBuilder
                .builder(BlockEntityChest.class)
                .name(VanillaBlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(ChestContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }
}
