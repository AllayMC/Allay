package org.allaymc.server.blockentity.initializer;

import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.api.data.VanillaBlockEntityId;
import org.allaymc.server.blockentity.component.chest.BlockEntityChestBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityContainerHolderComponentImpl;

/**
 * Allay Project 2024/3/2
 *
 * @author daoge_cmd
 */
public interface BlockEntityChestInitializer {
    static void init() {
        BlockEntityTypes.CHEST_TYPE = BlockEntityTypeBuilder
                .builder(BlockEntityChest.class)
                .name(VanillaBlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(ChestContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }
}
