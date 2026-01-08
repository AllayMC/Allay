package org.allaymc.server.blockentity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.server.blockentity.component.*;
import org.allaymc.server.blockentity.component.furnace.BlockEntityBlastFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntityFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntitySmokerFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxContainerHolderComponentImpl;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.allaymc.server.blockentity.impl.*;
import org.allaymc.server.container.impl.*;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockEntityTypeInitializer {
    public static void initJukebox() {
        BlockEntityTypes.JUKEBOX = AllayBlockEntityType
                .builder(BlockEntityJukeboxImpl.class)
                .name(BlockEntityId.JUKEBOX)
                .addComponent(BlockEntityJukeboxBaseComponentImpl::new, BlockEntityJukeboxBaseComponentImpl.class)
                .build();
    }

    public static void initBarrel() {
        BlockEntityTypes.BARREL = AllayBlockEntityType
                .builder(BlockEntityBarrelImpl.class)
                .name(BlockEntityId.BARREL)
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initHopper() {
        BlockEntityTypes.HOPPER = AllayBlockEntityType
                .builder(BlockEntityHopperImpl.class)
                .name(BlockEntityId.HOPPER)
                .addComponent(BlockEntityHopperBaseComponentImpl::new, BlockEntityHopperBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(HopperContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initChest() {
        BlockEntityTypes.CHEST = AllayBlockEntityType
                .builder(BlockEntityChestImpl.class)
                .name(BlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(BlockEntityChestContainerHolderComponentImpl::new, BlockEntityChestContainerHolderComponentImpl.class)
                .addComponent(BlockEntityPairableComponentImpl::new, BlockEntityPairableComponentImpl.class)
                .build();
    }

    public static void initShulkerBox() {
        BlockEntityTypes.SHULKER_BOX = AllayBlockEntityType
                .builder(BlockEntityShulkerBoxImpl.class)
                .name(BlockEntityId.SHULKER_BOX)
                .addComponent(BlockEntityShulkerBoxBaseComponentImpl::new, BlockEntityShulkerBoxBaseComponentImpl.class)
                .addComponent(BlockEntityShulkerBoxContainerHolderComponentImpl::new, BlockEntityShulkerBoxContainerHolderComponentImpl.class)
                .build();
    }

    public static void initFurnace() {
        BlockEntityTypes.FURNACE = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.FURNACE)
                .addComponent(BlockEntityFurnaceBaseComponentImpl::new, BlockEntityFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(FurnaceContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.BLAST_FURNACE = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.BLAST_FURNACE)
                .addComponent(BlockEntityBlastFurnaceBaseComponentImpl::new, BlockEntityBlastFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BlastFurnaceContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.SMOKER = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.SMOKER)
                .addComponent(BlockEntitySmokerFurnaceBaseComponentImpl::new, BlockEntitySmokerFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(SmokerContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initSign() {
        BlockEntityTypes.SIGN = AllayBlockEntityType
                .builder(BlockEntitySignImpl.class)
                .name(BlockEntityId.SIGN)
                .addComponent(BlockEntitySignBaseComponentImpl::new, BlockEntitySignBaseComponentImpl.class)
                .build();
        BlockEntityTypes.HANGING_SIGN = AllayBlockEntityType
                .builder(BlockEntitySignImpl.class)
                .name(BlockEntityId.HANGING_SIGN)
                .addComponent(BlockEntitySignBaseComponentImpl::new, BlockEntitySignBaseComponentImpl.class)
                .build();
    }

    public static void initEnchantTable() {
        BlockEntityTypes.ENCHANT_TABLE = AllayBlockEntityType
                .builder(BlockEntityEnchantTableImpl.class)
                .name(BlockEntityId.ENCHANT_TABLE)
                .addComponent(BlockEntityEnchantTableBaseComponentImpl::new, BlockEntityEnchantTableBaseComponentImpl.class)
                .build();
    }

    public static void initBeacon() {
        BlockEntityTypes.BEACON = AllayBlockEntityType
                .builder(BlockEntityBeaconImpl.class)
                .name(BlockEntityId.BEACON)
                .addComponent(BlockEntityBeaconBaseComponentImpl::new, BlockEntityBeaconBaseComponentImpl.class)
                .build();
    }

    public static void initBrewingStand() {
        BlockEntityTypes.BREWING_STAND = AllayBlockEntityType
                .builder(BlockEntityBrewingStandImpl.class)
                .name(BlockEntityId.BREWING_STAND)
                .addComponent(BlockEntityBrewingStandBaseComponentImpl::new, BlockEntityBrewingStandBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BrewingStandContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    public static void initEnderChest() {
        BlockEntityTypes.ENDER_CHEST = AllayBlockEntityType
                .builder(BlockEntityEnderChestImpl.class)
                .name(BlockEntityId.ENDER_CHEST)
                .addComponent(BlockEntityEnderChestBaseComponentImpl::new, BlockEntityEnderChestBaseComponentImpl.class)
                .build();
    }

    public static void initFlowerPot() {
        BlockEntityTypes.FLOWER_POT = AllayBlockEntityType
                .builder(BlockEntityFlowerPotImpl.class)
                .name(BlockEntityId.FLOWER_POT)
                .addComponent(BlockEntityFlowerPotBaseComponentImpl::new, BlockEntityFlowerPotBaseComponentImpl.class)
                .build();
    }

    public static void initItemFrame() {
        BlockEntityTypes.ITEM_FRAME = AllayBlockEntityType
                .builder(BlockEntityItemFrameImpl.class)
                .name(BlockEntityId.ITEM_FRAME)
                .addComponent(BlockEntityItemFrameBaseComponentImpl::new, BlockEntityItemFrameBaseComponentImpl.class)
                .build();
        BlockEntityTypes.GLOW_ITEM_FRAME = AllayBlockEntityType
                .builder(BlockEntityItemFrameImpl.class)
                .name(BlockEntityId.GLOW_ITEM_FRAME)
                .addComponent(BlockEntityItemFrameBaseComponentImpl::new, BlockEntityItemFrameBaseComponentImpl.class)
                .build();
    }

    public static void initBed() {
        BlockEntityTypes.BED = AllayBlockEntityType
                .builder(BlockEntityBedImpl.class)
                .name(BlockEntityId.BED)
                .addComponent(BlockEntityBedBaseComponentImpl::new, BlockEntityBedBaseComponentImpl.class)
                .build();
    }

    public static void initNoteblock() {
        BlockEntityTypes.NOTEBLOCK = AllayBlockEntityType
                .builder(BlockEntityNoteblockImpl.class)
                .name(BlockEntityId.MUSIC)
                .addComponent(BlockEntityNoteblockBaseComponentImpl::new, BlockEntityNoteblockBaseComponentImpl.class)
                .build();
    }

    public static void initHead() {
        BlockEntityTypes.HEAD = AllayBlockEntityType
                .builder(BlockEntityHeadImpl.class)
                .name(BlockEntityId.SKULL)
                .addComponent(BlockEntityHeadBaseComponentImpl::new, BlockEntityHeadBaseComponentImpl.class)
                .build();
    }

    public static void initComparator() {
        BlockEntityTypes.COMPARATOR = AllayBlockEntityType
                .builder(BlockEntityComparatorImpl.class)
                .name(BlockEntityId.COMPARATOR)
                .addComponent(BlockEntityComparatorBaseComponentImpl::new, BlockEntityComparatorBaseComponentImpl.class)
                .build();
    }
}
