package org.allaymc.server.blockentity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.api.data.VanillaBlockEntityId;
import org.allaymc.server.blockentity.component.BlockEntityBarrelBaseComponentImpl;
import org.allaymc.server.blockentity.component.BlockEntityChestBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityContainerHolderComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxContainerHolderComponentImpl;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class BlockEntityTypeInitializer {
    public static void initBarrel() {
        BlockEntityTypes.BARREL = BlockEntityTypeBuilder
                .builder(BlockEntityBarrel.class)
                .name(VanillaBlockEntityId.BARREL)
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initChest() {
        BlockEntityTypes.CHEST = BlockEntityTypeBuilder
                .builder(BlockEntityChest.class)
                .name(VanillaBlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(ChestContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initShulkerBox() {
        BlockEntityTypes.SHULKER_BOX = BlockEntityTypeBuilder
                .builder(BlockEntityShulkerBox.class)
                .name(VanillaBlockEntityId.SHULKER_BOX)
                .addComponent(BlockEntityShulkerBoxBaseComponentImpl::new, BlockEntityShulkerBoxBaseComponentImpl.class)
                .addComponent(BlockEntityShulkerBoxContainerHolderComponentImpl::new, BlockEntityShulkerBoxContainerHolderComponentImpl.class)
                .build();
    }
}
