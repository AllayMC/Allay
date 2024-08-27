package org.allaymc.server.blockentity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.blockentity.data.BlockEntityId;
import org.allaymc.api.blockentity.interfaces.*;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.container.impl.*;
import org.allaymc.server.blockentity.component.BlockEntityBarrelBaseComponentImpl;
import org.allaymc.server.blockentity.component.BlockEntityChestBaseComponentImpl;
import org.allaymc.server.blockentity.component.BlockEntityContainerHolderComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntityBlastFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntityFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntitySmokerFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxContainerHolderComponentImpl;
import org.allaymc.server.blockentity.component.sign.BlockEntityHangingSignBaseComponentImpl;
import org.allaymc.server.blockentity.component.sign.BlockEntitySignBaseComponentImpl;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class BlockEntityTypeInitializer {
    public static void initBarrel() {
        BlockEntityTypes.BARREL = AllayBlockEntityType
                .builder(BlockEntityBarrel.class)
                .name(BlockEntityId.BARREL)
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initChest() {
        BlockEntityTypes.CHEST = AllayBlockEntityType
                .builder(BlockEntityChest.class)
                .name(BlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(ChestContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initShulkerBox() {
        BlockEntityTypes.SHULKER_BOX = AllayBlockEntityType
                .builder(BlockEntityShulkerBox.class)
                .name(BlockEntityId.SHULKER_BOX)
                .addComponent(BlockEntityShulkerBoxBaseComponentImpl::new, BlockEntityShulkerBoxBaseComponentImpl.class)
                .addComponent(BlockEntityShulkerBoxContainerHolderComponentImpl::new, BlockEntityShulkerBoxContainerHolderComponentImpl.class)
                .build();
    }

    public static void initFurnace() {
        BlockEntityTypes.FURNACE = AllayBlockEntityType
                .builder(BlockEntityFurnace.class)
                .name(BlockEntityId.FURNACE)
                .addComponent(BlockEntityFurnaceBaseComponentImpl::new, BlockEntityFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(FurnaceContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.BLAST_FURNACE = AllayBlockEntityType
                .builder(BlockEntityBlastFurnace.class)
                .name(BlockEntityId.BLAST_FURNACE)
                .addComponent(BlockEntityBlastFurnaceBaseComponentImpl::new, BlockEntityBlastFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BlastFurnaceContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.SMOKER = AllayBlockEntityType
                .builder(BlockEntitySmoker.class)
                .name(BlockEntityId.SMOKER)
                .addComponent(BlockEntitySmokerFurnaceBaseComponentImpl::new, BlockEntitySmokerFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(SmokerContainer::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initSign() {
        BlockEntityTypes.SIGN = AllayBlockEntityType
                .builder(BlockEntitySign.class)
                .name(BlockEntityId.SIGN)
                .addComponent(BlockEntitySignBaseComponentImpl::new, BlockEntitySignBaseComponentImpl.class)
                .build();
        BlockEntityTypes.HANGING_SIGN = AllayBlockEntityType
                .builder(BlockEntityHangingSign.class)
                .name(BlockEntityId.HANGING_SIGN)
                .addComponent(BlockEntityHangingSignBaseComponentImpl::new, BlockEntityHangingSignBaseComponentImpl.class)
                .build();
    }
}
