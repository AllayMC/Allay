package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.block.component.*;
import org.allaymc.server.block.component.bamboo.BlockBambooBaseComponentImpl;
import org.allaymc.server.block.component.bamboo.BlockBambooSaplingBaseComponentImpl;
import org.allaymc.server.block.component.banner.BlockStandingBannerBaseComponentImpl;
import org.allaymc.server.block.component.banner.BlockWallBannerBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockButtonBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockWoodenButtonBaseComponentImpl;
import org.allaymc.server.block.component.campfire.BlockCampfireBaseComponentImpl;
import org.allaymc.server.block.component.campfire.BlockSoulCampfireBaseComponentImpl;
import org.allaymc.server.block.component.crops.*;
import org.allaymc.server.block.component.door.BlockCopperDoorBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockDoorBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockIronDoorBaseComponentImpl;
import org.allaymc.server.block.component.fallable.BlockAnvilFallableComponentImpl;
import org.allaymc.server.block.component.fallable.BlockConcretePowderFallableComponentImpl;
import org.allaymc.server.block.component.fallable.BlockFallableComponentImpl;
import org.allaymc.server.block.component.fallable.BlockPointedDripstoneFallableComponentImpl;
import org.allaymc.server.block.component.flower.BlockBigFlowerBaseComponentImpl;
import org.allaymc.server.block.component.flower.BlockSmallFlowerBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockDryGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockShortGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockTallGrassBaseComponentImpl;
import org.allaymc.server.block.component.ice.BlockHardIceBaseComponentImpl;
import org.allaymc.server.block.component.ice.BlockIceBaseComponentImpl;
import org.allaymc.server.block.component.ore.BlockOreBaseComponentImpl;
import org.allaymc.server.block.component.ore.BlockRedstoneOreBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockPistonArmCollisionBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockPistonBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockStickyPistonBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockDetectorRailBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockNormalRailBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockPoweredRailBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockHeavyWeightedPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockLightWeightedPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.respawnpoint.BlockBedRespawnPointComponentImpl;
import org.allaymc.server.block.component.respawnpoint.BlockRespawnAnchorRespawnPointComponentImpl;
import org.allaymc.server.block.component.sapling.BlockMangrovePropaguleBaseComponentImpl;
import org.allaymc.server.block.component.sapling.BlockSaplingBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockHangingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockStandingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockWallSignBaseComponentImpl;
import org.allaymc.server.block.component.slab.BlockDoubleSlabBaseComponentImpl;
import org.allaymc.server.block.component.slab.BlockSlabBaseComponentImpl;
import org.allaymc.server.block.component.trapdoor.BlockIronTrapdoorBaseComponentImpl;
import org.allaymc.server.block.component.trapdoor.BlockTrapdoorBaseComponentImpl;
import org.allaymc.server.block.component.tripwire.BlockTripWireBaseComponentImpl;
import org.allaymc.server.block.component.tripwire.BlockTripwireHookBaseComponentImpl;
import org.allaymc.server.block.component.BlockNetherVinesBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.block.impl.*;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.world.feature.fungus.HugeFungusFeature;
import org.allaymc.server.world.feature.mushroom.HugeBrownMushroomFeature;
import org.allaymc.server.world.feature.mushroom.HugeRedMushroomFeature;
import org.allaymc.server.world.feature.tree.*;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initCopperGrate() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperGrate = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_GRATE : BlockTypes.COPPER_GRATE;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_GRATE : BlockTypes.EXPOSED_COPPER_GRATE;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_GRATE : BlockTypes.WEATHERED_COPPER_GRATE;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_GRATE : BlockTypes.OXIDIZED_COPPER_GRATE;
        };
        BlockTypes.COPPER_GRATE = buildCopperGrate(BlockId.COPPER_GRATE, OxidationLevel.UNAFFECTED, copperGrate);
        BlockTypes.EXPOSED_COPPER_GRATE = buildCopperGrate(BlockId.EXPOSED_COPPER_GRATE, OxidationLevel.EXPOSED, copperGrate);
        BlockTypes.WEATHERED_COPPER_GRATE = buildCopperGrate(BlockId.WEATHERED_COPPER_GRATE, OxidationLevel.WEATHERED, copperGrate);
        BlockTypes.OXIDIZED_COPPER_GRATE = buildCopperGrate(BlockId.OXIDIZED_COPPER_GRATE, OxidationLevel.OXIDIZED, copperGrate);
        BlockTypes.WAXED_COPPER_GRATE = buildCopperGrate(BlockId.WAXED_COPPER_GRATE, OxidationLevel.UNAFFECTED, copperGrate);
        BlockTypes.WAXED_EXPOSED_COPPER_GRATE = buildCopperGrate(BlockId.WAXED_EXPOSED_COPPER_GRATE, OxidationLevel.EXPOSED, copperGrate);
        BlockTypes.WAXED_WEATHERED_COPPER_GRATE = buildCopperGrate(BlockId.WAXED_WEATHERED_COPPER_GRATE, OxidationLevel.WEATHERED, copperGrate);
        BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = buildCopperGrate(BlockId.WAXED_OXIDIZED_COPPER_GRATE, OxidationLevel.OXIDIZED, copperGrate);
    }

    private static BlockType<BlockCopperGrateBehavior> buildCopperGrate(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperGrateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initCopperBulb() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperBulb = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_BULB : BlockTypes.COPPER_BULB;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_BULB : BlockTypes.EXPOSED_COPPER_BULB;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_BULB : BlockTypes.WEATHERED_COPPER_BULB;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_BULB : BlockTypes.OXIDIZED_COPPER_BULB;
        };
        BlockTypes.COPPER_BULB = buildCopperBulb(BlockId.COPPER_BULB, OxidationLevel.UNAFFECTED, copperBulb);
        BlockTypes.EXPOSED_COPPER_BULB = buildCopperBulb(BlockId.EXPOSED_COPPER_BULB, OxidationLevel.EXPOSED, copperBulb);
        BlockTypes.WEATHERED_COPPER_BULB = buildCopperBulb(BlockId.WEATHERED_COPPER_BULB, OxidationLevel.WEATHERED, copperBulb);
        BlockTypes.OXIDIZED_COPPER_BULB = buildCopperBulb(BlockId.OXIDIZED_COPPER_BULB, OxidationLevel.OXIDIZED, copperBulb);
        BlockTypes.WAXED_COPPER_BULB = buildCopperBulb(BlockId.WAXED_COPPER_BULB, OxidationLevel.UNAFFECTED, copperBulb);
        BlockTypes.WAXED_EXPOSED_COPPER_BULB = buildCopperBulb(BlockId.WAXED_EXPOSED_COPPER_BULB, OxidationLevel.EXPOSED, copperBulb);
        BlockTypes.WAXED_WEATHERED_COPPER_BULB = buildCopperBulb(BlockId.WAXED_WEATHERED_COPPER_BULB, OxidationLevel.WEATHERED, copperBulb);
        BlockTypes.WAXED_OXIDIZED_COPPER_BULB = buildCopperBulb(BlockId.WAXED_OXIDIZED_COPPER_BULB, OxidationLevel.OXIDIZED, copperBulb);
    }

    private static BlockType<BlockCopperBulbBehavior> buildCopperBulb(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperBulbBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCopperBulbBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initCopper() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER : BlockTypes.COPPER_BLOCK;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER : BlockTypes.EXPOSED_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER : BlockTypes.WEATHERED_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER : BlockTypes.OXIDIZED_COPPER;
        };
        BlockTypes.COPPER_BLOCK = buildCopper(BlockId.COPPER_BLOCK, OxidationLevel.UNAFFECTED, copper);
        BlockTypes.EXPOSED_COPPER = buildCopper(BlockId.EXPOSED_COPPER, OxidationLevel.EXPOSED, copper);
        BlockTypes.WEATHERED_COPPER = buildCopper(BlockId.WEATHERED_COPPER, OxidationLevel.WEATHERED, copper);
        BlockTypes.OXIDIZED_COPPER = buildCopper(BlockId.OXIDIZED_COPPER, OxidationLevel.OXIDIZED, copper);
        BlockTypes.WAXED_COPPER = buildCopper(BlockId.WAXED_COPPER, OxidationLevel.UNAFFECTED, copper);
        BlockTypes.WAXED_EXPOSED_COPPER = buildCopper(BlockId.WAXED_EXPOSED_COPPER, OxidationLevel.EXPOSED, copper);
        BlockTypes.WAXED_WEATHERED_COPPER = buildCopper(BlockId.WAXED_WEATHERED_COPPER, OxidationLevel.WEATHERED, copper);
        BlockTypes.WAXED_OXIDIZED_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_COPPER, OxidationLevel.OXIDIZED, copper);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> chiseledCopper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CHISELED_COPPER : BlockTypes.CHISELED_COPPER;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CHISELED_COPPER : BlockTypes.EXPOSED_CHISELED_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CHISELED_COPPER : BlockTypes.WEATHERED_CHISELED_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER : BlockTypes.OXIDIZED_CHISELED_COPPER;
        };
        BlockTypes.CHISELED_COPPER = buildCopper(BlockId.CHISELED_COPPER, OxidationLevel.UNAFFECTED, chiseledCopper);
        BlockTypes.EXPOSED_CHISELED_COPPER = buildCopper(BlockId.EXPOSED_CHISELED_COPPER, OxidationLevel.EXPOSED, chiseledCopper);
        BlockTypes.WEATHERED_CHISELED_COPPER = buildCopper(BlockId.WEATHERED_CHISELED_COPPER, OxidationLevel.WEATHERED, chiseledCopper);
        BlockTypes.OXIDIZED_CHISELED_COPPER = buildCopper(BlockId.OXIDIZED_CHISELED_COPPER, OxidationLevel.OXIDIZED, chiseledCopper);
        BlockTypes.WAXED_CHISELED_COPPER = buildCopper(BlockId.WAXED_CHISELED_COPPER, OxidationLevel.UNAFFECTED, chiseledCopper);
        BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = buildCopper(BlockId.WAXED_EXPOSED_CHISELED_COPPER, OxidationLevel.EXPOSED, chiseledCopper);
        BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = buildCopper(BlockId.WAXED_WEATHERED_CHISELED_COPPER, OxidationLevel.WEATHERED, chiseledCopper);
        BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_CHISELED_COPPER, OxidationLevel.OXIDIZED, chiseledCopper);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> cutCopper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CUT_COPPER : BlockTypes.CUT_COPPER;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CUT_COPPER : BlockTypes.EXPOSED_CUT_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CUT_COPPER : BlockTypes.WEATHERED_CUT_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CUT_COPPER : BlockTypes.OXIDIZED_CUT_COPPER;
        };
        BlockTypes.CUT_COPPER = buildCopper(BlockId.CUT_COPPER, OxidationLevel.UNAFFECTED, cutCopper);
        BlockTypes.EXPOSED_CUT_COPPER = buildCopper(BlockId.EXPOSED_CUT_COPPER, OxidationLevel.EXPOSED, cutCopper);
        BlockTypes.WEATHERED_CUT_COPPER = buildCopper(BlockId.WEATHERED_CUT_COPPER, OxidationLevel.WEATHERED, cutCopper);
        BlockTypes.OXIDIZED_CUT_COPPER = buildCopper(BlockId.OXIDIZED_CUT_COPPER, OxidationLevel.OXIDIZED, cutCopper);
        BlockTypes.WAXED_CUT_COPPER = buildCopper(BlockId.WAXED_CUT_COPPER, OxidationLevel.UNAFFECTED, cutCopper);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER = buildCopper(BlockId.WAXED_EXPOSED_CUT_COPPER, OxidationLevel.EXPOSED, cutCopper);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER = buildCopper(BlockId.WAXED_WEATHERED_CUT_COPPER, OxidationLevel.WEATHERED, cutCopper);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_CUT_COPPER, OxidationLevel.OXIDIZED, cutCopper);
    }

    private static BlockType<BlockCopperBehavior> buildCopper(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperBehaviorImpl.class)
                .vanillaBlock(blockId)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initClay() {
        BlockTypes.CLAY = AllayBlockType
                .builder(BlockClayBehaviorImpl.class)
                .vanillaBlock(BlockId.CLAY)
                .setBaseComponentSupplier(BlockClayBaseComponentImpl::new)
                .build();
    }

    public static void initCarpets() {
        BlockTypes.WHITE_CARPET = buildCarpet(BlockId.WHITE_CARPET);
        BlockTypes.ORANGE_CARPET = buildCarpet(BlockId.ORANGE_CARPET);
        BlockTypes.MAGENTA_CARPET = buildCarpet(BlockId.MAGENTA_CARPET);
        BlockTypes.LIGHT_BLUE_CARPET = buildCarpet(BlockId.LIGHT_BLUE_CARPET);
        BlockTypes.YELLOW_CARPET = buildCarpet(BlockId.YELLOW_CARPET);
        BlockTypes.LIME_CARPET = buildCarpet(BlockId.LIME_CARPET);
        BlockTypes.PINK_CARPET = buildCarpet(BlockId.PINK_CARPET);
        BlockTypes.GRAY_CARPET = buildCarpet(BlockId.GRAY_CARPET);
        BlockTypes.LIGHT_GRAY_CARPET = buildCarpet(BlockId.LIGHT_GRAY_CARPET);
        BlockTypes.CYAN_CARPET = buildCarpet(BlockId.CYAN_CARPET);
        BlockTypes.PURPLE_CARPET = buildCarpet(BlockId.PURPLE_CARPET);
        BlockTypes.BLUE_CARPET = buildCarpet(BlockId.BLUE_CARPET);
        BlockTypes.BROWN_CARPET = buildCarpet(BlockId.BROWN_CARPET);
        BlockTypes.GREEN_CARPET = buildCarpet(BlockId.GREEN_CARPET);
        BlockTypes.RED_CARPET = buildCarpet(BlockId.RED_CARPET);
        BlockTypes.BLACK_CARPET = buildCarpet(BlockId.BLACK_CARPET);
    }

    private static BlockType<BlockCarpetBehavior> buildCarpet(BlockId blockId) {
        return AllayBlockType
                .builder(BlockCarpetBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCarpetBaseComponentImpl::new)
                .build();
    }

    public static void initJukebox() {
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehaviorImpl.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .bindBlockEntity(BlockEntityTypes.JUKEBOX)
                .setBaseComponentSupplier(BlockJukeboxBaseComponentImpl::new)
                .build();
    }

    public static void initBanner() {
        BlockTypes.STANDING_BANNER = AllayBlockType
                .builder(BlockStandingBannerBehaviorImpl.class)
                .vanillaBlock(BlockId.STANDING_BANNER)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BANNER)
                .setBaseComponentSupplier(BlockStandingBannerBaseComponentImpl::new)
                .build();
        BlockTypes.WALL_BANNER = AllayBlockType
                .builder(BlockWallBannerBehaviorImpl.class)
                .vanillaBlock(BlockId.WALL_BANNER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BANNER)
                .setBaseComponentSupplier(BlockWallBannerBaseComponentImpl::new)
                .build();
    }

    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = buildLeaves(BlockId.ACACIA_LEAVES, ItemId.ACACIA_SAPLING);
        BlockTypes.AZALEA_LEAVES = buildLeaves(BlockId.AZALEA_LEAVES, null);
        BlockTypes.AZALEA_LEAVES_FLOWERED = buildLeaves(BlockId.AZALEA_LEAVES_FLOWERED, null);
        BlockTypes.BIRCH_LEAVES = buildLeaves(BlockId.BIRCH_LEAVES, ItemId.BIRCH_SAPLING);
        BlockTypes.CHERRY_LEAVES = buildLeaves(BlockId.CHERRY_LEAVES, ItemId.CHERRY_SAPLING);
        BlockTypes.DARK_OAK_LEAVES = buildLeaves(BlockId.DARK_OAK_LEAVES, ItemId.DARK_OAK_SAPLING);
        BlockTypes.JUNGLE_LEAVES = buildLeaves(BlockId.JUNGLE_LEAVES, ItemId.JUNGLE_SAPLING, false, true);
        BlockTypes.MANGROVE_LEAVES = buildLeaves(BlockId.MANGROVE_LEAVES, null);
        BlockTypes.OAK_LEAVES = buildLeaves(BlockId.OAK_LEAVES, ItemId.OAK_SAPLING, true, false);
        BlockTypes.SPRUCE_LEAVES = buildLeaves(BlockId.SPRUCE_LEAVES, ItemId.SPRUCE_SAPLING);
        BlockTypes.PALE_OAK_LEAVES = buildLeaves(BlockId.PALE_OAK_LEAVES, ItemId.PALE_OAK_SAPLING);
    }

    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemId saplingId) {
        return buildLeaves(id, saplingId, false, false);
    }

    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemId saplingId, boolean canDropApple, boolean dropMoreSaplings) {
        return AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockLeavesBaseComponentImpl(blockType, saplingId, canDropApple, dropMoreSaplings))
                .build();
    }

    public static void initFallable() {
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAVEL)
                .setBaseComponentSupplier(BlockGravelBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_GRAVEL))
                .build();
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SAND)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_SAND))
                .build();
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SAND)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_SAND))
                .build();
    }

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .setBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .setBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
        BlockTypes.SHORT_DRY_GRASS = AllayBlockType
                .builder(BlockShortDryGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SHORT_DRY_GRASS)
                .setBaseComponentSupplier(BlockDryGrassBaseComponentImpl::new)
                .build();
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehaviorImpl.class)
                .vanillaBlock(BlockId.FERN)
                .setBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockType -> new BlockTallGrassBaseComponentImpl(blockType, ItemId.SHORT_GRASS))
                .build();
        // Actually, tall dry grass is only one block height
        BlockTypes.TALL_DRY_GRASS = AllayBlockType
                .builder(BlockTallDryGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_DRY_GRASS)
                .setBaseComponentSupplier(BlockDryGrassBaseComponentImpl::new)
                .build();
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehaviorImpl.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockType -> new BlockTallGrassBaseComponentImpl(blockType, ItemId.FERN))
                .build();
    }

    public static void initBarrel() {
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehaviorImpl.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .bindBlockEntity(BlockEntityTypes.BARREL)
                .setBaseComponentSupplier(BlockBarrelBaseComponentImpl::new)
                .build();
    }

    public static void initChest() {
        BlockTypes.CHEST = AllayBlockType
                .builder(BlockChestBehaviorImpl.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.CHEST)
                .setBaseComponentSupplier(BlockChestBaseComponentImpl::new)
                .build();
        BlockTypes.TRAPPED_CHEST = AllayBlockType
                .builder(BlockTrappedChestBehaviorImpl.class)
                .vanillaBlock(BlockId.TRAPPED_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.CHEST)
                .setBaseComponentSupplier(BlockTrappedChestBaseComponentImpl::new)
                .build();
    }

    public static void initHopper() {
        BlockTypes.HOPPER = AllayBlockType
                .builder(BlockHopperBehaviorImpl.class)
                .vanillaBlock(BlockId.HOPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .bindBlockEntity(BlockEntityTypes.HOPPER)
                .setBaseComponentSupplier(BlockHopperBaseComponentImpl::new)
                .build();
    }

    public static void initDispenser() {
        BlockTypes.DISPENSER = AllayBlockType
                .builder(BlockDispenserBehaviorImpl.class)
                .vanillaBlock(BlockId.DISPENSER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .bindBlockEntity(BlockEntityTypes.DISPENSER)
                .setBaseComponentSupplier(BlockDispenserBaseComponentImpl::new)
                .build();
    }

    public static void initDropper() {
        BlockTypes.DROPPER = AllayBlockType
                .builder(BlockDropperBehaviorImpl.class)
                .vanillaBlock(BlockId.DROPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .bindBlockEntity(BlockEntityTypes.DROPPER)
                .setBaseComponentSupplier(BlockDropperBaseComponentImpl::new)
                .build();
    }

    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .setBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = buildStairs(BlockId.ACACIA_STAIRS);
        BlockTypes.ANDESITE_STAIRS = buildStairs(BlockId.ANDESITE_STAIRS);
        BlockTypes.BAMBOO_MOSAIC_STAIRS = buildStairs(BlockId.BAMBOO_MOSAIC_STAIRS);
        BlockTypes.BAMBOO_STAIRS = buildStairs(BlockId.BAMBOO_STAIRS);
        BlockTypes.BIRCH_STAIRS = buildStairs(BlockId.BIRCH_STAIRS);
        BlockTypes.BLACKSTONE_STAIRS = buildStairs(BlockId.BLACKSTONE_STAIRS);
        BlockTypes.BRICK_STAIRS = buildStairs(BlockId.BRICK_STAIRS);
        BlockTypes.CHERRY_STAIRS = buildStairs(BlockId.CHERRY_STAIRS);
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = buildStairs(BlockId.COBBLED_DEEPSLATE_STAIRS);
        BlockTypes.CRIMSON_STAIRS = buildStairs(BlockId.CRIMSON_STAIRS);
        BlockTypes.DARK_OAK_STAIRS = buildStairs(BlockId.DARK_OAK_STAIRS);
        BlockTypes.DARK_PRISMARINE_STAIRS = buildStairs(BlockId.DARK_PRISMARINE_STAIRS);
        BlockTypes.DEEPSLATE_BRICK_STAIRS = buildStairs(BlockId.DEEPSLATE_BRICK_STAIRS);
        BlockTypes.DEEPSLATE_TILE_STAIRS = buildStairs(BlockId.DEEPSLATE_TILE_STAIRS);
        BlockTypes.DIORITE_STAIRS = buildStairs(BlockId.DIORITE_STAIRS);
        BlockTypes.END_BRICK_STAIRS = buildStairs(BlockId.END_BRICK_STAIRS);
        BlockTypes.GRANITE_STAIRS = buildStairs(BlockId.GRANITE_STAIRS);
        BlockTypes.JUNGLE_STAIRS = buildStairs(BlockId.JUNGLE_STAIRS);
        BlockTypes.MANGROVE_STAIRS = buildStairs(BlockId.MANGROVE_STAIRS);
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = buildStairs(BlockId.MOSSY_COBBLESTONE_STAIRS);
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = buildStairs(BlockId.MOSSY_STONE_BRICK_STAIRS);
        BlockTypes.MUD_BRICK_STAIRS = buildStairs(BlockId.MUD_BRICK_STAIRS);
        BlockTypes.NETHER_BRICK_STAIRS = buildStairs(BlockId.NETHER_BRICK_STAIRS);
        BlockTypes.NORMAL_STONE_STAIRS = buildStairs(BlockId.NORMAL_STONE_STAIRS);
        BlockTypes.OAK_STAIRS = buildStairs(BlockId.OAK_STAIRS);
        BlockTypes.POLISHED_ANDESITE_STAIRS = buildStairs(BlockId.POLISHED_ANDESITE_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = buildStairs(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = buildStairs(BlockId.POLISHED_BLACKSTONE_STAIRS);
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = buildStairs(BlockId.POLISHED_DEEPSLATE_STAIRS);
        BlockTypes.POLISHED_DIORITE_STAIRS = buildStairs(BlockId.POLISHED_DIORITE_STAIRS);
        BlockTypes.POLISHED_GRANITE_STAIRS = buildStairs(BlockId.POLISHED_GRANITE_STAIRS);
        BlockTypes.POLISHED_TUFF_STAIRS = buildStairs(BlockId.POLISHED_TUFF_STAIRS);
        BlockTypes.PRISMARINE_BRICKS_STAIRS = buildStairs(BlockId.PRISMARINE_BRICKS_STAIRS);
        BlockTypes.PRISMARINE_STAIRS = buildStairs(BlockId.PRISMARINE_STAIRS);
        BlockTypes.PURPUR_STAIRS = buildStairs(BlockId.PURPUR_STAIRS);
        BlockTypes.QUARTZ_STAIRS = buildStairs(BlockId.QUARTZ_STAIRS);
        BlockTypes.RED_NETHER_BRICK_STAIRS = buildStairs(BlockId.RED_NETHER_BRICK_STAIRS);
        BlockTypes.RED_SANDSTONE_STAIRS = buildStairs(BlockId.RED_SANDSTONE_STAIRS);
        BlockTypes.SANDSTONE_STAIRS = buildStairs(BlockId.SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_QUARTZ_STAIRS = buildStairs(BlockId.SMOOTH_QUARTZ_STAIRS);
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = buildStairs(BlockId.SMOOTH_RED_SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = buildStairs(BlockId.SMOOTH_SANDSTONE_STAIRS);
        BlockTypes.SPRUCE_STAIRS = buildStairs(BlockId.SPRUCE_STAIRS);
        BlockTypes.STONE_BRICK_STAIRS = buildStairs(BlockId.STONE_BRICK_STAIRS);
        BlockTypes.STONE_STAIRS = buildStairs(BlockId.STONE_STAIRS);
        BlockTypes.TUFF_BRICK_STAIRS = buildStairs(BlockId.TUFF_BRICK_STAIRS);
        BlockTypes.TUFF_STAIRS = buildStairs(BlockId.TUFF_STAIRS);
        BlockTypes.WARPED_STAIRS = buildStairs(BlockId.WARPED_STAIRS);
        BlockTypes.PALE_OAK_STAIRS = buildStairs(BlockId.PALE_OAK_STAIRS);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> cutCopperStairs = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CUT_COPPER_STAIRS : BlockTypes.CUT_COPPER_STAIRS;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS : BlockTypes.EXPOSED_CUT_COPPER_STAIRS;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS : BlockTypes.WEATHERED_CUT_COPPER_STAIRS;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS : BlockTypes.OXIDIZED_CUT_COPPER_STAIRS;
        };
        BlockTypes.CUT_COPPER_STAIRS = buildCopperStairs(BlockId.CUT_COPPER_STAIRS, OxidationLevel.UNAFFECTED, cutCopperStairs);
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.EXPOSED_CUT_COPPER_STAIRS, OxidationLevel.EXPOSED, cutCopperStairs);
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.WEATHERED_CUT_COPPER_STAIRS, OxidationLevel.WEATHERED, cutCopperStairs);
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.OXIDIZED_CUT_COPPER_STAIRS, OxidationLevel.OXIDIZED, cutCopperStairs);
        BlockTypes.WAXED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.WAXED_CUT_COPPER_STAIRS, OxidationLevel.UNAFFECTED, cutCopperStairs);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS, OxidationLevel.EXPOSED, cutCopperStairs);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS, OxidationLevel.WEATHERED, cutCopperStairs);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = buildCopperStairs(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, OxidationLevel.OXIDIZED, cutCopperStairs);

        BlockTypes.RESIN_BRICK_STAIRS = buildStairs(BlockId.RESIN_BRICK_STAIRS);
    }

    private static BlockType<BlockStairsBehavior> buildStairs(BlockId id) {
        return stairsBuilder(BlockStairsBehaviorImpl.class, id).build();
    }

    private static BlockType<BlockCopperStairsBehavior> buildCopperStairs(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return stairsBuilder(BlockCopperStairsBehaviorImpl.class, id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    private static <T extends BlockBehavior> AllayBlockType.Builder stairsBuilder(Class<T> clazz, BlockId id) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .setBaseComponentSupplier(BlockStairsBaseComponentImpl::new);
    }

    public static void initSlab() {
        BlockTypes.ACACIA_SLAB = buildSlab(BlockId.ACACIA_SLAB, BlockId.ACACIA_DOUBLE_SLAB);
        BlockTypes.ANDESITE_SLAB = buildSlab(BlockId.ANDESITE_SLAB, BlockId.ANDESITE_DOUBLE_SLAB);
        BlockTypes.BAMBOO_MOSAIC_SLAB = buildSlab(BlockId.BAMBOO_MOSAIC_SLAB, BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB);
        BlockTypes.BAMBOO_SLAB = buildSlab(BlockId.BAMBOO_SLAB, BlockId.BAMBOO_DOUBLE_SLAB);
        BlockTypes.BIRCH_SLAB = buildSlab(BlockId.BIRCH_SLAB, BlockId.BIRCH_DOUBLE_SLAB);
        BlockTypes.BLACKSTONE_SLAB = buildSlab(BlockId.BLACKSTONE_SLAB, BlockId.BLACKSTONE_DOUBLE_SLAB);
        BlockTypes.BRICK_SLAB = buildSlab(BlockId.BRICK_SLAB, BlockId.BRICK_DOUBLE_SLAB);
        BlockTypes.CHERRY_SLAB = buildSlab(BlockId.CHERRY_SLAB, BlockId.CHERRY_DOUBLE_SLAB);
        BlockTypes.COBBLED_DEEPSLATE_SLAB = buildSlab(BlockId.COBBLED_DEEPSLATE_SLAB, BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB);
        BlockTypes.COBBLESTONE_SLAB = buildSlab(BlockId.COBBLESTONE_SLAB, BlockId.COBBLESTONE_DOUBLE_SLAB);
        BlockTypes.CRIMSON_SLAB = buildSlab(BlockId.CRIMSON_SLAB, BlockId.CRIMSON_DOUBLE_SLAB);
        BlockTypes.CUT_RED_SANDSTONE_SLAB = buildSlab(BlockId.CUT_RED_SANDSTONE_SLAB, BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB);
        BlockTypes.CUT_SANDSTONE_SLAB = buildSlab(BlockId.CUT_SANDSTONE_SLAB, BlockId.CUT_SANDSTONE_DOUBLE_SLAB);
        BlockTypes.DARK_OAK_SLAB = buildSlab(BlockId.DARK_OAK_SLAB, BlockId.DARK_OAK_DOUBLE_SLAB);
        BlockTypes.DARK_PRISMARINE_SLAB = buildSlab(BlockId.DARK_PRISMARINE_SLAB, BlockId.DARK_PRISMARINE_DOUBLE_SLAB);
        BlockTypes.DEEPSLATE_BRICK_SLAB = buildSlab(BlockId.DEEPSLATE_BRICK_SLAB, BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB);
        BlockTypes.DEEPSLATE_TILE_SLAB = buildSlab(BlockId.DEEPSLATE_TILE_SLAB, BlockId.DEEPSLATE_TILE_DOUBLE_SLAB);
        BlockTypes.DIORITE_SLAB = buildSlab(BlockId.DIORITE_SLAB, BlockId.DIORITE_DOUBLE_SLAB);
        BlockTypes.END_STONE_BRICK_SLAB = buildSlab(BlockId.END_STONE_BRICK_SLAB, BlockId.END_STONE_BRICK_DOUBLE_SLAB);
        BlockTypes.GRANITE_SLAB = buildSlab(BlockId.GRANITE_SLAB, BlockId.GRANITE_DOUBLE_SLAB);
        BlockTypes.JUNGLE_SLAB = buildSlab(BlockId.JUNGLE_SLAB, BlockId.JUNGLE_DOUBLE_SLAB);
        BlockTypes.MANGROVE_SLAB = buildSlab(BlockId.MANGROVE_SLAB, BlockId.MANGROVE_DOUBLE_SLAB);
        BlockTypes.MOSSY_COBBLESTONE_SLAB = buildSlab(BlockId.MOSSY_COBBLESTONE_SLAB, BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB);
        BlockTypes.MOSSY_STONE_BRICK_SLAB = buildSlab(BlockId.MOSSY_STONE_BRICK_SLAB, BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB);
        BlockTypes.MUD_BRICK_SLAB = buildSlab(BlockId.MUD_BRICK_SLAB, BlockId.MUD_BRICK_DOUBLE_SLAB);
        BlockTypes.NETHER_BRICK_SLAB = buildSlab(BlockId.NETHER_BRICK_SLAB, BlockId.NETHER_BRICK_DOUBLE_SLAB);
        BlockTypes.NORMAL_STONE_SLAB = buildSlab(BlockId.NORMAL_STONE_SLAB, BlockId.NORMAL_STONE_DOUBLE_SLAB);
        BlockTypes.OAK_SLAB = buildSlab(BlockId.OAK_SLAB, BlockId.OAK_DOUBLE_SLAB);
        BlockTypes.PALE_OAK_SLAB = buildSlab(BlockId.PALE_OAK_SLAB, BlockId.PALE_OAK_DOUBLE_SLAB);
        BlockTypes.PETRIFIED_OAK_SLAB = buildSlab(BlockId.PETRIFIED_OAK_SLAB, BlockId.PETRIFIED_OAK_DOUBLE_SLAB);
        BlockTypes.POLISHED_ANDESITE_SLAB = buildSlab(BlockId.POLISHED_ANDESITE_SLAB, BlockId.POLISHED_ANDESITE_DOUBLE_SLAB);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = buildSlab(BlockId.POLISHED_BLACKSTONE_BRICK_SLAB, BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB);
        BlockTypes.POLISHED_BLACKSTONE_SLAB = buildSlab(BlockId.POLISHED_BLACKSTONE_SLAB, BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB);
        BlockTypes.POLISHED_DEEPSLATE_SLAB = buildSlab(BlockId.POLISHED_DEEPSLATE_SLAB, BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB);
        BlockTypes.POLISHED_DIORITE_SLAB = buildSlab(BlockId.POLISHED_DIORITE_SLAB, BlockId.POLISHED_DIORITE_DOUBLE_SLAB);
        BlockTypes.POLISHED_GRANITE_SLAB = buildSlab(BlockId.POLISHED_GRANITE_SLAB, BlockId.POLISHED_GRANITE_DOUBLE_SLAB);
        BlockTypes.POLISHED_TUFF_SLAB = buildSlab(BlockId.POLISHED_TUFF_SLAB, BlockId.POLISHED_TUFF_DOUBLE_SLAB);
        BlockTypes.PRISMARINE_BRICK_SLAB = buildSlab(BlockId.PRISMARINE_BRICK_SLAB, BlockId.PRISMARINE_BRICK_DOUBLE_SLAB);
        BlockTypes.PRISMARINE_SLAB = buildSlab(BlockId.PRISMARINE_SLAB, BlockId.PRISMARINE_DOUBLE_SLAB);
        BlockTypes.PURPUR_SLAB = buildSlab(BlockId.PURPUR_SLAB, BlockId.PURPUR_DOUBLE_SLAB);
        BlockTypes.QUARTZ_SLAB = buildSlab(BlockId.QUARTZ_SLAB, BlockId.QUARTZ_DOUBLE_SLAB);
        BlockTypes.RED_NETHER_BRICK_SLAB = buildSlab(BlockId.RED_NETHER_BRICK_SLAB, BlockId.RED_NETHER_BRICK_DOUBLE_SLAB);
        BlockTypes.RED_SANDSTONE_SLAB = buildSlab(BlockId.RED_SANDSTONE_SLAB, BlockId.RED_SANDSTONE_DOUBLE_SLAB);
        BlockTypes.RESIN_BRICK_SLAB = buildSlab(BlockId.RESIN_BRICK_SLAB, BlockId.RESIN_BRICK_DOUBLE_SLAB);
        BlockTypes.SANDSTONE_SLAB = buildSlab(BlockId.SANDSTONE_SLAB, BlockId.SANDSTONE_DOUBLE_SLAB);
        BlockTypes.SMOOTH_QUARTZ_SLAB = buildSlab(BlockId.SMOOTH_QUARTZ_SLAB, BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB);
        BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = buildSlab(BlockId.SMOOTH_RED_SANDSTONE_SLAB, BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB);
        BlockTypes.SMOOTH_SANDSTONE_SLAB = buildSlab(BlockId.SMOOTH_SANDSTONE_SLAB, BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB);
        BlockTypes.SMOOTH_STONE_SLAB = buildSlab(BlockId.SMOOTH_STONE_SLAB, BlockId.SMOOTH_STONE_DOUBLE_SLAB);
        BlockTypes.SPRUCE_SLAB = buildSlab(BlockId.SPRUCE_SLAB, BlockId.SPRUCE_DOUBLE_SLAB);
        BlockTypes.STONE_BRICK_SLAB = buildSlab(BlockId.STONE_BRICK_SLAB, BlockId.STONE_BRICK_DOUBLE_SLAB);
        BlockTypes.TUFF_BRICK_SLAB = buildSlab(BlockId.TUFF_BRICK_SLAB, BlockId.TUFF_BRICK_DOUBLE_SLAB);
        BlockTypes.TUFF_SLAB = buildSlab(BlockId.TUFF_SLAB, BlockId.TUFF_DOUBLE_SLAB);
        BlockTypes.WARPED_SLAB = buildSlab(BlockId.WARPED_SLAB, BlockId.WARPED_DOUBLE_SLAB);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> cutCopperSlab = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CUT_COPPER_SLAB : BlockTypes.CUT_COPPER_SLAB;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB : BlockTypes.EXPOSED_CUT_COPPER_SLAB;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB : BlockTypes.WEATHERED_CUT_COPPER_SLAB;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB : BlockTypes.OXIDIZED_CUT_COPPER_SLAB;
        };
        BlockTypes.CUT_COPPER_SLAB = buildCopperSlab(BlockId.CUT_COPPER_SLAB, BlockId.DOUBLE_CUT_COPPER_SLAB, OxidationLevel.UNAFFECTED, cutCopperSlab);
        BlockTypes.EXPOSED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.EXPOSED_CUT_COPPER_SLAB, BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.EXPOSED, cutCopperSlab);
        BlockTypes.WEATHERED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.WEATHERED_CUT_COPPER_SLAB, BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.WEATHERED, cutCopperSlab);
        BlockTypes.OXIDIZED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.OXIDIZED_CUT_COPPER_SLAB, BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.OXIDIZED, cutCopperSlab);
        BlockTypes.WAXED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.WAXED_CUT_COPPER_SLAB, BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.UNAFFECTED, cutCopperSlab);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB, BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.EXPOSED, cutCopperSlab);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB, BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.WEATHERED, cutCopperSlab);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = buildCopperSlab(BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB, BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB, OxidationLevel.OXIDIZED, cutCopperSlab);
    }

    public static void initDoubleSlab() {
        BlockTypes.ACACIA_DOUBLE_SLAB = buildDoubleSlab(BlockId.ACACIA_DOUBLE_SLAB, BlockId.ACACIA_SLAB);
        BlockTypes.ANDESITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.ANDESITE_DOUBLE_SLAB, BlockId.ANDESITE_SLAB);
        BlockTypes.BAMBOO_DOUBLE_SLAB = buildDoubleSlab(BlockId.BAMBOO_DOUBLE_SLAB, BlockId.BAMBOO_SLAB);
        BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = buildDoubleSlab(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB, BlockId.BAMBOO_MOSAIC_SLAB);
        BlockTypes.BIRCH_DOUBLE_SLAB = buildDoubleSlab(BlockId.BIRCH_DOUBLE_SLAB, BlockId.BIRCH_SLAB);
        BlockTypes.BLACKSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.BLACKSTONE_DOUBLE_SLAB, BlockId.BLACKSTONE_SLAB);
        BlockTypes.BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.BRICK_DOUBLE_SLAB, BlockId.BRICK_SLAB);
        BlockTypes.CHERRY_DOUBLE_SLAB = buildDoubleSlab(BlockId.CHERRY_DOUBLE_SLAB, BlockId.CHERRY_SLAB);
        BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = buildDoubleSlab(BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB, BlockId.COBBLED_DEEPSLATE_SLAB);
        BlockTypes.COBBLESTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.COBBLESTONE_DOUBLE_SLAB, BlockId.COBBLESTONE_SLAB);
        BlockTypes.CRIMSON_DOUBLE_SLAB = buildDoubleSlab(BlockId.CRIMSON_DOUBLE_SLAB, BlockId.CRIMSON_SLAB);
        BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB, BlockId.CUT_RED_SANDSTONE_SLAB);
        BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.CUT_SANDSTONE_DOUBLE_SLAB, BlockId.CUT_SANDSTONE_SLAB);
        BlockTypes.DARK_OAK_DOUBLE_SLAB = buildDoubleSlab(BlockId.DARK_OAK_DOUBLE_SLAB, BlockId.DARK_OAK_SLAB);
        BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = buildDoubleSlab(BlockId.DARK_PRISMARINE_DOUBLE_SLAB, BlockId.DARK_PRISMARINE_SLAB);
        BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB, BlockId.DEEPSLATE_BRICK_SLAB);
        BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = buildDoubleSlab(BlockId.DEEPSLATE_TILE_DOUBLE_SLAB, BlockId.DEEPSLATE_TILE_SLAB);
        BlockTypes.DIORITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.DIORITE_DOUBLE_SLAB, BlockId.DIORITE_SLAB);
        BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.END_STONE_BRICK_DOUBLE_SLAB, BlockId.END_STONE_BRICK_SLAB);
        BlockTypes.GRANITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.GRANITE_DOUBLE_SLAB, BlockId.GRANITE_SLAB);
        BlockTypes.JUNGLE_DOUBLE_SLAB = buildDoubleSlab(BlockId.JUNGLE_DOUBLE_SLAB, BlockId.JUNGLE_SLAB);
        BlockTypes.MANGROVE_DOUBLE_SLAB = buildDoubleSlab(BlockId.MANGROVE_DOUBLE_SLAB, BlockId.MANGROVE_SLAB);
        BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB, BlockId.MOSSY_COBBLESTONE_SLAB);
        BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB, BlockId.MOSSY_STONE_BRICK_SLAB);
        BlockTypes.MUD_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.MUD_BRICK_DOUBLE_SLAB, BlockId.MUD_BRICK_SLAB);
        BlockTypes.NETHER_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.NETHER_BRICK_DOUBLE_SLAB, BlockId.NETHER_BRICK_SLAB);
        BlockTypes.NORMAL_STONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.NORMAL_STONE_DOUBLE_SLAB, BlockId.NORMAL_STONE_SLAB);
        BlockTypes.OAK_DOUBLE_SLAB = buildDoubleSlab(BlockId.OAK_DOUBLE_SLAB, BlockId.OAK_SLAB);
        BlockTypes.PALE_OAK_DOUBLE_SLAB = buildDoubleSlab(BlockId.PALE_OAK_DOUBLE_SLAB, BlockId.PALE_OAK_SLAB);
        BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = buildDoubleSlab(BlockId.PETRIFIED_OAK_DOUBLE_SLAB, BlockId.PETRIFIED_OAK_SLAB);
        BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_ANDESITE_DOUBLE_SLAB, BlockId.POLISHED_ANDESITE_SLAB);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB, BlockId.POLISHED_BLACKSTONE_BRICK_SLAB);
        BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB, BlockId.POLISHED_BLACKSTONE_SLAB);
        BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB, BlockId.POLISHED_DEEPSLATE_SLAB);
        BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_DIORITE_DOUBLE_SLAB, BlockId.POLISHED_DIORITE_SLAB);
        BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_GRANITE_DOUBLE_SLAB, BlockId.POLISHED_GRANITE_SLAB);
        BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_TUFF_DOUBLE_SLAB, BlockId.POLISHED_TUFF_SLAB);
        BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.PRISMARINE_BRICK_DOUBLE_SLAB, BlockId.PRISMARINE_BRICK_SLAB);
        BlockTypes.PRISMARINE_DOUBLE_SLAB = buildDoubleSlab(BlockId.PRISMARINE_DOUBLE_SLAB, BlockId.PRISMARINE_SLAB);
        BlockTypes.PURPUR_DOUBLE_SLAB = buildDoubleSlab(BlockId.PURPUR_DOUBLE_SLAB, BlockId.PURPUR_SLAB);
        BlockTypes.QUARTZ_DOUBLE_SLAB = buildDoubleSlab(BlockId.QUARTZ_DOUBLE_SLAB, BlockId.QUARTZ_SLAB);
        BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.RED_NETHER_BRICK_DOUBLE_SLAB, BlockId.RED_NETHER_BRICK_SLAB);
        BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.RED_SANDSTONE_DOUBLE_SLAB, BlockId.RED_SANDSTONE_SLAB);
        BlockTypes.RESIN_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.RESIN_BRICK_DOUBLE_SLAB, BlockId.RESIN_BRICK_SLAB);
        BlockTypes.SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.SANDSTONE_DOUBLE_SLAB, BlockId.SANDSTONE_SLAB);
        BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = buildDoubleSlab(BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB, BlockId.SMOOTH_QUARTZ_SLAB);
        BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB, BlockId.SMOOTH_RED_SANDSTONE_SLAB);
        BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB, BlockId.SMOOTH_SANDSTONE_SLAB);
        BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.SMOOTH_STONE_DOUBLE_SLAB, BlockId.SMOOTH_STONE_SLAB);
        BlockTypes.SPRUCE_DOUBLE_SLAB = buildDoubleSlab(BlockId.SPRUCE_DOUBLE_SLAB, BlockId.SPRUCE_SLAB);
        BlockTypes.STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.STONE_BRICK_DOUBLE_SLAB, BlockId.STONE_BRICK_SLAB);
        BlockTypes.TUFF_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.TUFF_BRICK_DOUBLE_SLAB, BlockId.TUFF_BRICK_SLAB);
        BlockTypes.TUFF_DOUBLE_SLAB = buildDoubleSlab(BlockId.TUFF_DOUBLE_SLAB, BlockId.TUFF_SLAB);
        BlockTypes.WARPED_DOUBLE_SLAB = buildDoubleSlab(BlockId.WARPED_DOUBLE_SLAB, BlockId.WARPED_SLAB);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> cutCopperSlab = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB : BlockTypes.DOUBLE_CUT_COPPER_SLAB;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB : BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB : BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB : BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB;
        };
        BlockTypes.DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.DOUBLE_CUT_COPPER_SLAB, BlockId.CUT_COPPER_SLAB, OxidationLevel.UNAFFECTED, cutCopperSlab);
        BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB, BlockId.EXPOSED_CUT_COPPER_SLAB, OxidationLevel.EXPOSED, cutCopperSlab);
        BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB, BlockId.WEATHERED_CUT_COPPER_SLAB, OxidationLevel.WEATHERED, cutCopperSlab);
        BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB, BlockId.OXIDIZED_CUT_COPPER_SLAB, OxidationLevel.OXIDIZED, cutCopperSlab);
        BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB, BlockId.WAXED_CUT_COPPER_SLAB, OxidationLevel.UNAFFECTED, cutCopperSlab);
        BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB, BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB, OxidationLevel.EXPOSED, cutCopperSlab);
        BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB, BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB, OxidationLevel.WEATHERED, cutCopperSlab);
        BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = buildCopperDoubleSlab(BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB, BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB, OxidationLevel.OXIDIZED, cutCopperSlab);
    }

    public static BlockType<BlockSlabBehavior> buildSlab(BlockId id, BlockId doubleSlabId) {
        return slabBuilder(BlockSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockSlabBaseComponentImpl(blockType, doubleSlabId))
                .build();
    }

    public static BlockType<BlockCopperSlabBehavior> buildCopperSlab(BlockId id, BlockId doubleSlabId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return slabBuilder(BlockCopperSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockSlabBaseComponentImpl(blockType, doubleSlabId))
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static BlockType<BlockDoubleSlabBehavior> buildDoubleSlab(BlockId id, BlockId singleSlabId) {
        return slabBuilder(BlockDoubleSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockDoubleSlabBaseComponentImpl(blockType, singleSlabId))
                .build();
    }

    public static BlockType<BlockDoubleCopperSlabBehavior> buildCopperDoubleSlab(BlockId id, BlockId singleSlabId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return slabBuilder(BlockDoubleCopperSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockDoubleSlabBaseComponentImpl(blockType, singleSlabId))
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static <T extends BlockBehavior> AllayBlockType.Builder slabBuilder(Class<T> clazz, BlockId id) {
        // Both slab and double slab have MINECRAFT_VERTICAL_HALF property
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF);
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_RED = buildColoredTorch(BlockId.COLORED_TORCH_RED);
        BlockTypes.COLORED_TORCH_BLUE = buildColoredTorch(BlockId.COLORED_TORCH_BLUE);
        BlockTypes.COLORED_TORCH_GREEN = buildColoredTorch(BlockId.COLORED_TORCH_GREEN);
        BlockTypes.COLORED_TORCH_PURPLE = buildColoredTorch(BlockId.COLORED_TORCH_PURPLE);
    }

    private static BlockType<BlockTorchBehavior> buildColoredTorch(BlockId blockId) {
        return AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        // Note: REDSTONE_TORCH and UNLIT_REDSTONE_TORCH are registered in initRedstoneTorch()

        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.COPPER_TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    public static void initFurnace() {
        BlockTypes.FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.LIT_FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
        BlockTypes.LIT_SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
    }

    public static void initWoods() {
        // Log
        BlockTypes.ACACIA_LOG = buildWood(BlockId.ACACIA_LOG, BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.BAMBOO_BLOCK = buildWood(BlockId.BAMBOO_BLOCK, BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.BIRCH_LOG = buildWood(BlockId.BIRCH_LOG, BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.CHERRY_LOG = buildWood(BlockId.CHERRY_LOG, BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.CRIMSON_STEM = buildWood(BlockId.CRIMSON_STEM, BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.DARK_OAK_LOG = buildWood(BlockId.DARK_OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.JUNGLE_LOG = buildWood(BlockId.JUNGLE_LOG, BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.MANGROVE_LOG = buildWood(BlockId.MANGROVE_LOG, BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.OAK_LOG = buildWood(BlockId.OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.SPRUCE_LOG = buildWood(BlockId.SPRUCE_LOG, BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.WARPED_STEM = buildWood(BlockId.WARPED_STEM, BlockId.STRIPPED_WARPED_STEM);
        BlockTypes.PALE_OAK_LOG = buildWood(BlockId.PALE_OAK_LOG, BlockId.STRIPPED_PALE_OAK_LOG);
        // Stripped Log
        BlockTypes.STRIPPED_ACACIA_LOG = buildStrippedWood(BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.STRIPPED_BAMBOO_BLOCK = buildStrippedWood(BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.STRIPPED_BIRCH_LOG = buildStrippedWood(BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.STRIPPED_CHERRY_LOG = buildStrippedWood(BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.STRIPPED_CRIMSON_STEM = buildStrippedWood(BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.STRIPPED_DARK_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_DARK_OAK_LOG);
        BlockTypes.STRIPPED_JUNGLE_LOG = buildStrippedWood(BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.STRIPPED_MANGROVE_LOG = buildStrippedWood(BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.STRIPPED_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_OAK_LOG);
        BlockTypes.STRIPPED_SPRUCE_LOG = buildStrippedWood(BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.STRIPPED_WARPED_STEM = buildStrippedWood(BlockId.STRIPPED_WARPED_STEM);
        BlockTypes.STRIPPED_PALE_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_PALE_OAK_LOG);

        // Wood
        BlockTypes.ACACIA_WOOD = buildWood(BlockId.ACACIA_WOOD, BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.BIRCH_WOOD = buildWood(BlockId.BIRCH_WOOD, BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.CRIMSON_HYPHAE = buildWood(BlockId.CRIMSON_HYPHAE, BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.DARK_OAK_WOOD = buildWood(BlockId.DARK_OAK_WOOD, BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.JUNGLE_WOOD = buildWood(BlockId.JUNGLE_WOOD, BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.OAK_WOOD = buildWood(BlockId.OAK_WOOD, BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.SPRUCE_WOOD = buildWood(BlockId.SPRUCE_WOOD, BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.WARPED_HYPHAE = buildWood(BlockId.WARPED_HYPHAE, BlockId.STRIPPED_WARPED_HYPHAE);
        BlockTypes.CHERRY_WOOD = buildWood(BlockId.CHERRY_WOOD, BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.MANGROVE_WOOD = buildWood(BlockId.MANGROVE_WOOD, BlockId.STRIPPED_MANGROVE_WOOD);
        BlockTypes.PALE_OAK_WOOD = buildWood(BlockId.PALE_OAK_WOOD, BlockId.STRIPPED_PALE_OAK_WOOD);
        // Stripped Wood
        BlockTypes.STRIPPED_ACACIA_WOOD = buildStrippedWood(BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.STRIPPED_BIRCH_WOOD = buildStrippedWood(BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = buildStrippedWood(BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.STRIPPED_DARK_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.STRIPPED_JUNGLE_WOOD = buildStrippedWood(BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.STRIPPED_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.STRIPPED_SPRUCE_WOOD = buildStrippedWood(BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.STRIPPED_WARPED_HYPHAE = buildStrippedWood(BlockId.STRIPPED_WARPED_HYPHAE);
        BlockTypes.STRIPPED_CHERRY_WOOD = buildStrippedWood(BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.STRIPPED_MANGROVE_WOOD = buildStrippedWood(BlockId.STRIPPED_MANGROVE_WOOD);
        BlockTypes.STRIPPED_PALE_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_PALE_OAK_WOOD);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(BlockId blockId) {
        return buildWood(blockId, blockId);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .addComponent(new BlockStrippableComponentImpl(strippedBlockId))
                .build();
    }

    public static void initButtons() {
        BlockTypes.ACACIA_BUTTON = buildWoodenButton(BlockId.ACACIA_BUTTON);
        BlockTypes.BAMBOO_BUTTON = buildWoodenButton(BlockId.BAMBOO_BUTTON);
        BlockTypes.BIRCH_BUTTON = buildWoodenButton(BlockId.BIRCH_BUTTON);
        BlockTypes.CHERRY_BUTTON = buildWoodenButton(BlockId.CHERRY_BUTTON);
        BlockTypes.CRIMSON_BUTTON = buildWoodenButton(BlockId.CRIMSON_BUTTON);
        BlockTypes.DARK_OAK_BUTTON = buildWoodenButton(BlockId.DARK_OAK_BUTTON);
        BlockTypes.JUNGLE_BUTTON = buildWoodenButton(BlockId.JUNGLE_BUTTON);
        BlockTypes.MANGROVE_BUTTON = buildWoodenButton(BlockId.MANGROVE_BUTTON);
        BlockTypes.SPRUCE_BUTTON = buildWoodenButton(BlockId.SPRUCE_BUTTON);
        BlockTypes.WARPED_BUTTON = buildWoodenButton(BlockId.WARPED_BUTTON);
        BlockTypes.WOODEN_BUTTON = buildWoodenButton(BlockId.WOODEN_BUTTON);
        BlockTypes.PALE_OAK_BUTTON = buildWoodenButton(BlockId.PALE_OAK_BUTTON);

        BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockId.POLISHED_BLACKSTONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
        BlockTypes.STONE_BUTTON = buildButton(BlockId.STONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
    }

    public static void initDoors() {
        BlockTypes.ACACIA_DOOR = buildDoor(BlockId.ACACIA_DOOR);
        BlockTypes.BAMBOO_DOOR = buildDoor(BlockId.BAMBOO_DOOR);
        BlockTypes.BIRCH_DOOR = buildDoor(BlockId.BIRCH_DOOR);
        BlockTypes.CHERRY_DOOR = buildDoor(BlockId.CHERRY_DOOR);
        BlockTypes.CRIMSON_DOOR = buildDoor(BlockId.CRIMSON_DOOR);
        BlockTypes.DARK_OAK_DOOR = buildDoor(BlockId.DARK_OAK_DOOR);
        BlockTypes.JUNGLE_DOOR = buildDoor(BlockId.JUNGLE_DOOR);
        BlockTypes.MANGROVE_DOOR = buildDoor(BlockId.MANGROVE_DOOR);
        BlockTypes.WOODEN_DOOR = buildDoor(BlockId.WOODEN_DOOR);
        BlockTypes.SPRUCE_DOOR = buildDoor(BlockId.SPRUCE_DOOR);
        BlockTypes.WARPED_DOOR = buildDoor(BlockId.WARPED_DOOR);
        BlockTypes.PALE_OAK_DOOR = buildDoor(BlockId.PALE_OAK_DOOR);

        BlockTypes.IRON_DOOR = doorBuilder(BlockIronDoorBehaviorImpl.class, BlockId.IRON_DOOR, BlockIronDoorBaseComponentImpl::new).build();

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperDoor = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_DOOR : BlockTypes.COPPER_DOOR;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_DOOR : BlockTypes.EXPOSED_COPPER_DOOR;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_DOOR : BlockTypes.WEATHERED_COPPER_DOOR;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_DOOR : BlockTypes.OXIDIZED_COPPER_DOOR;
        };
        BlockTypes.COPPER_DOOR = buildCopperDoor(BlockId.COPPER_DOOR, OxidationLevel.UNAFFECTED, copperDoor);
        BlockTypes.EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.EXPOSED_COPPER_DOOR, OxidationLevel.EXPOSED, copperDoor);
        BlockTypes.WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WEATHERED_COPPER_DOOR, OxidationLevel.WEATHERED, copperDoor);
        BlockTypes.OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.OXIDIZED_COPPER_DOOR, OxidationLevel.OXIDIZED, copperDoor);
        BlockTypes.WAXED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_COPPER_DOOR, OxidationLevel.UNAFFECTED, copperDoor);
        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_EXPOSED_COPPER_DOOR, OxidationLevel.EXPOSED, copperDoor);
        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_WEATHERED_COPPER_DOOR, OxidationLevel.WEATHERED, copperDoor);
        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_OXIDIZED_COPPER_DOOR, OxidationLevel.OXIDIZED, copperDoor);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(BlockId blockId) {
        return doorBuilder(BlockDoorBehaviorImpl.class, blockId, BlockDoorBaseComponentImpl::new).build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildCopperDoor(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return doorBuilder(BlockCopperDoorBehaviorImpl.class, blockId, BlockCopperDoorBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    private static AllayBlockType.Builder doorBuilder(Class<? extends BlockBehavior> clazz, BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                // NOTICE: The data in the resource file about the door's collision box is incorrect, so let's calculate the correct hitbox manually
                .addComponent(BlockStateDataComponentImpl.ofRedefinedData((builder, blockType, blockStateHash) -> {
                    var shape = buildDoorCollisionShape(blockType.ofState(blockStateHash));
                    return builder.collisionShape(shape).shape(shape).build();
                }));
    }

    private static VoxelShape buildDoorCollisionShape(BlockState doorBlockState) {
        var cardinal = BlockFace.from(doorBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION));
        var open = doorBlockState.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
        var hingeBit = doorBlockState.getPropertyValue(BlockPropertyTypes.DOOR_HINGE_BIT);

        BlockFace edge;
        if (!open) {
            edge = cardinal.rotateY();
        } else if (!hingeBit) {
            edge = cardinal.opposite();
        } else {
            edge = cardinal;
        }

        var thickness = 3.0 / 16.0;
        return switch (edge) {
            case NORTH -> VoxelShape.builder().solid(0, 0, 0, 1, 1, thickness).build();
            case SOUTH -> VoxelShape.builder().solid(0, 0, 1 - thickness, 1, 1, 1).build();
            case WEST -> VoxelShape.builder().solid(0, 0, 0, thickness, 1, 1).build();
            case EAST -> VoxelShape.builder().solid(1 - thickness, 0, 0, 1, 1, 1).build();
            default -> throw new IllegalStateException("Unexpected edge face: " + edge);
        };
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenButton(BlockId blockId) {
        return buildButton(blockId, BlockWoodenButtonBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initWallSigns() {
        BlockTypes.WALL_SIGN = buildWallSign(BlockId.WALL_SIGN, ItemId.OAK_SIGN);
        BlockTypes.ACACIA_WALL_SIGN = buildWallSign(BlockId.ACACIA_WALL_SIGN, ItemId.ACACIA_SIGN);
        BlockTypes.BAMBOO_WALL_SIGN = buildWallSign(BlockId.BAMBOO_WALL_SIGN, ItemId.BAMBOO_SIGN);
        BlockTypes.BIRCH_WALL_SIGN = buildWallSign(BlockId.BIRCH_WALL_SIGN, ItemId.BIRCH_SIGN);
        BlockTypes.CHERRY_WALL_SIGN = buildWallSign(BlockId.CHERRY_WALL_SIGN, ItemId.CHERRY_SIGN);
        BlockTypes.CRIMSON_WALL_SIGN = buildWallSign(BlockId.CRIMSON_WALL_SIGN, ItemId.CRIMSON_SIGN);
        BlockTypes.DARKOAK_WALL_SIGN = buildWallSign(BlockId.DARKOAK_WALL_SIGN, ItemId.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_WALL_SIGN = buildWallSign(BlockId.JUNGLE_WALL_SIGN, ItemId.JUNGLE_SIGN);
        BlockTypes.MANGROVE_WALL_SIGN = buildWallSign(BlockId.MANGROVE_WALL_SIGN, ItemId.MANGROVE_SIGN);
        BlockTypes.SPRUCE_WALL_SIGN = buildWallSign(BlockId.SPRUCE_WALL_SIGN, ItemId.SPRUCE_SIGN);
        BlockTypes.WARPED_WALL_SIGN = buildWallSign(BlockId.WARPED_WALL_SIGN, ItemId.WARPED_SIGN);
        BlockTypes.PALE_OAK_WALL_SIGN = buildWallSign(BlockId.PALE_OAK_WALL_SIGN, ItemId.PALE_OAK_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(BlockId blockId, ItemId dropItemId) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemId))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initStandingSigns() {
        BlockTypes.STANDING_SIGN = buildStandingSign(BlockId.STANDING_SIGN, ItemId.OAK_SIGN);
        BlockTypes.ACACIA_STANDING_SIGN = buildStandingSign(BlockId.ACACIA_STANDING_SIGN, ItemId.ACACIA_SIGN);
        BlockTypes.BAMBOO_STANDING_SIGN = buildStandingSign(BlockId.BAMBOO_STANDING_SIGN, ItemId.BAMBOO_SIGN);
        BlockTypes.BIRCH_STANDING_SIGN = buildStandingSign(BlockId.BIRCH_STANDING_SIGN, ItemId.BIRCH_SIGN);
        BlockTypes.CHERRY_STANDING_SIGN = buildStandingSign(BlockId.CHERRY_STANDING_SIGN, ItemId.CHERRY_SIGN);
        BlockTypes.CRIMSON_STANDING_SIGN = buildStandingSign(BlockId.CRIMSON_STANDING_SIGN, ItemId.CRIMSON_SIGN);
        BlockTypes.DARKOAK_STANDING_SIGN = buildStandingSign(BlockId.DARKOAK_STANDING_SIGN, ItemId.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_STANDING_SIGN = buildStandingSign(BlockId.JUNGLE_STANDING_SIGN, ItemId.JUNGLE_SIGN);
        BlockTypes.MANGROVE_STANDING_SIGN = buildStandingSign(BlockId.MANGROVE_STANDING_SIGN, ItemId.MANGROVE_SIGN);
        BlockTypes.SPRUCE_STANDING_SIGN = buildStandingSign(BlockId.SPRUCE_STANDING_SIGN, ItemId.SPRUCE_SIGN);
        BlockTypes.WARPED_STANDING_SIGN = buildStandingSign(BlockId.WARPED_STANDING_SIGN, ItemId.WARPED_SIGN);
        BlockTypes.PALE_OAK_STANDING_SIGN = buildStandingSign(BlockId.PALE_OAK_STANDING_SIGN, ItemId.PALE_OAK_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(BlockId blockId, ItemId dropItemId) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemId))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initHangingSigns() {
        BlockTypes.ACACIA_HANGING_SIGN = buildHangingSign(BlockId.ACACIA_HANGING_SIGN);
        BlockTypes.BAMBOO_HANGING_SIGN = buildHangingSign(BlockId.BAMBOO_HANGING_SIGN);
        BlockTypes.BIRCH_HANGING_SIGN = buildHangingSign(BlockId.BIRCH_HANGING_SIGN);
        BlockTypes.CHERRY_HANGING_SIGN = buildHangingSign(BlockId.CHERRY_HANGING_SIGN);
        BlockTypes.CRIMSON_HANGING_SIGN = buildHangingSign(BlockId.CRIMSON_HANGING_SIGN);
        BlockTypes.DARK_OAK_HANGING_SIGN = buildHangingSign(BlockId.DARK_OAK_HANGING_SIGN);
        BlockTypes.JUNGLE_HANGING_SIGN = buildHangingSign(BlockId.JUNGLE_HANGING_SIGN);
        BlockTypes.MANGROVE_HANGING_SIGN = buildHangingSign(BlockId.MANGROVE_HANGING_SIGN);
        BlockTypes.OAK_HANGING_SIGN = buildHangingSign(BlockId.OAK_HANGING_SIGN);
        BlockTypes.SPRUCE_HANGING_SIGN = buildHangingSign(BlockId.SPRUCE_HANGING_SIGN);
        BlockTypes.WARPED_HANGING_SIGN = buildHangingSign(BlockId.WARPED_HANGING_SIGN);
        BlockTypes.PALE_OAK_HANGING_SIGN = buildHangingSign(BlockId.PALE_OAK_HANGING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }

    public static void initSlimeBlock() {
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehaviorImpl.class)
                .vanillaBlock(BlockId.SLIME)
                .setBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .build();
    }

    public static void initHayBlock() {
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockHayBlockBaseComponentImpl::new)
                .build();
    }

    public static void initWater() {
        BlockTypes.WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockWaterBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(BlockTypeInitializer::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockWaterBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(BlockTypeInitializer::buildLiquidShape))
                .build();
    }

    public static void initLava() {
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockLavaBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(BlockTypeInitializer::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockLavaBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(BlockTypeInitializer::buildLiquidShape))
                .build();
    }

    private static VoxelShape buildLiquidShape(BlockState liquidBlockState) {
        if (!(liquidBlockState.getBehavior() instanceof BlockLiquidBaseComponent)) {
            throw new IllegalArgumentException("The liquidBlockState must implement BlockLiquidBaseComponent!");
        }
        return VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.125f * BlockLiquidBaseComponent.getDepth(liquidBlockState), 1)
                .build();
    }

    public static void initEnchantingTable() {
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .bindBlockEntity(BlockEntityTypes.ENCHANT_TABLE)
                .setBaseComponentSupplier(BlockEnchantingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStone() {
        BlockTypes.STONE = AllayBlockType
                .builder(BlockStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE)
                .setBaseComponentSupplier(BlockStoneBaseComponentImpl::new)
                .build();
    }

    public static void initGlass() {
        // Normal
        BlockTypes.BLACK_STAINED_GLASS = buildGlass(BlockId.BLACK_STAINED_GLASS);
        BlockTypes.BLUE_STAINED_GLASS = buildGlass(BlockId.BLUE_STAINED_GLASS);
        BlockTypes.BROWN_STAINED_GLASS = buildGlass(BlockId.BROWN_STAINED_GLASS);
        BlockTypes.CYAN_STAINED_GLASS = buildGlass(BlockId.CYAN_STAINED_GLASS);
        BlockTypes.GLASS = buildGlass(BlockId.GLASS);
        BlockTypes.GRAY_STAINED_GLASS = buildGlass(BlockId.GRAY_STAINED_GLASS);
        BlockTypes.GREEN_STAINED_GLASS = buildGlass(BlockId.GREEN_STAINED_GLASS);
        BlockTypes.LIGHT_BLUE_STAINED_GLASS = buildGlass(BlockId.LIGHT_BLUE_STAINED_GLASS);
        BlockTypes.LIGHT_GRAY_STAINED_GLASS = buildGlass(BlockId.LIGHT_GRAY_STAINED_GLASS);
        BlockTypes.LIME_STAINED_GLASS = buildGlass(BlockId.LIME_STAINED_GLASS);
        BlockTypes.MAGENTA_STAINED_GLASS = buildGlass(BlockId.MAGENTA_STAINED_GLASS);
        BlockTypes.ORANGE_STAINED_GLASS = buildGlass(BlockId.ORANGE_STAINED_GLASS);
        BlockTypes.PINK_STAINED_GLASS = buildGlass(BlockId.PINK_STAINED_GLASS);
        BlockTypes.PURPLE_STAINED_GLASS = buildGlass(BlockId.PURPLE_STAINED_GLASS);
        BlockTypes.RED_STAINED_GLASS = buildGlass(BlockId.RED_STAINED_GLASS);
        BlockTypes.WHITE_STAINED_GLASS = buildGlass(BlockId.WHITE_STAINED_GLASS);
        BlockTypes.YELLOW_STAINED_GLASS = buildGlass(BlockId.YELLOW_STAINED_GLASS);
        // Special
        BlockTypes.TINTED_GLASS = buildGlass(BlockId.TINTED_GLASS);

        // Hard
        BlockTypes.HARD_BLACK_STAINED_GLASS = buildGlass(BlockId.HARD_BLACK_STAINED_GLASS);
        BlockTypes.HARD_BLUE_STAINED_GLASS = buildGlass(BlockId.HARD_BLUE_STAINED_GLASS);
        BlockTypes.HARD_BROWN_STAINED_GLASS = buildGlass(BlockId.HARD_BROWN_STAINED_GLASS);
        BlockTypes.HARD_CYAN_STAINED_GLASS = buildGlass(BlockId.HARD_CYAN_STAINED_GLASS);
        BlockTypes.HARD_GLASS = buildGlass(BlockId.HARD_GLASS);
        BlockTypes.HARD_GRAY_STAINED_GLASS = buildGlass(BlockId.HARD_GRAY_STAINED_GLASS);
        BlockTypes.HARD_GREEN_STAINED_GLASS = buildGlass(BlockId.HARD_GREEN_STAINED_GLASS);
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = buildGlass(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS);
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = buildGlass(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS);
        BlockTypes.HARD_LIME_STAINED_GLASS = buildGlass(BlockId.HARD_LIME_STAINED_GLASS);
        BlockTypes.HARD_MAGENTA_STAINED_GLASS = buildGlass(BlockId.HARD_MAGENTA_STAINED_GLASS);
        BlockTypes.HARD_ORANGE_STAINED_GLASS = buildGlass(BlockId.HARD_ORANGE_STAINED_GLASS);
        BlockTypes.HARD_PINK_STAINED_GLASS = buildGlass(BlockId.HARD_PINK_STAINED_GLASS);
        BlockTypes.HARD_PURPLE_STAINED_GLASS = buildGlass(BlockId.HARD_PURPLE_STAINED_GLASS);
        BlockTypes.HARD_RED_STAINED_GLASS = buildGlass(BlockId.HARD_RED_STAINED_GLASS);
        BlockTypes.HARD_WHITE_STAINED_GLASS = buildGlass(BlockId.HARD_WHITE_STAINED_GLASS);
        BlockTypes.HARD_YELLOW_STAINED_GLASS = buildGlass(BlockId.HARD_YELLOW_STAINED_GLASS);
    }

    public static void initGlassPane() {
        // Normal
        BlockTypes.BLACK_STAINED_GLASS_PANE = buildGlassPane(BlockId.BLACK_STAINED_GLASS_PANE);
        BlockTypes.BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.BLUE_STAINED_GLASS_PANE);
        BlockTypes.BROWN_STAINED_GLASS_PANE = buildGlassPane(BlockId.BROWN_STAINED_GLASS_PANE);
        BlockTypes.CYAN_STAINED_GLASS_PANE = buildGlassPane(BlockId.CYAN_STAINED_GLASS_PANE);
        BlockTypes.GLASS_PANE = buildGlassPane(BlockId.GLASS_PANE);
        BlockTypes.GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.GRAY_STAINED_GLASS_PANE);
        BlockTypes.GREEN_STAINED_GLASS_PANE = buildGlassPane(BlockId.GREEN_STAINED_GLASS_PANE);
        BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE);
        BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE);
        BlockTypes.LIME_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIME_STAINED_GLASS_PANE);
        BlockTypes.MAGENTA_STAINED_GLASS_PANE = buildGlassPane(BlockId.MAGENTA_STAINED_GLASS_PANE);
        BlockTypes.ORANGE_STAINED_GLASS_PANE = buildGlassPane(BlockId.ORANGE_STAINED_GLASS_PANE);
        BlockTypes.PINK_STAINED_GLASS_PANE = buildGlassPane(BlockId.PINK_STAINED_GLASS_PANE);
        BlockTypes.PURPLE_STAINED_GLASS_PANE = buildGlassPane(BlockId.PURPLE_STAINED_GLASS_PANE);
        BlockTypes.RED_STAINED_GLASS_PANE = buildGlassPane(BlockId.RED_STAINED_GLASS_PANE);
        BlockTypes.WHITE_STAINED_GLASS_PANE = buildGlassPane(BlockId.WHITE_STAINED_GLASS_PANE);
        BlockTypes.YELLOW_STAINED_GLASS_PANE = buildGlassPane(BlockId.YELLOW_STAINED_GLASS_PANE);

        // Hard
        BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BLACK_STAINED_GLASS_PANE);
        BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BLUE_STAINED_GLASS_PANE);
        BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BROWN_STAINED_GLASS_PANE);
        BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_CYAN_STAINED_GLASS_PANE);
        BlockTypes.HARD_GLASS_PANE = buildGlassPane(BlockId.HARD_GLASS_PANE);
        BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_GRAY_STAINED_GLASS_PANE);
        BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_GREEN_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIME_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIME_STAINED_GLASS_PANE);
        BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE);
        BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_ORANGE_STAINED_GLASS_PANE);
        BlockTypes.HARD_PINK_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_PINK_STAINED_GLASS_PANE);
        BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_PURPLE_STAINED_GLASS_PANE);
        BlockTypes.HARD_RED_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_RED_STAINED_GLASS_PANE);
        BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_WHITE_STAINED_GLASS_PANE);
        BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_YELLOW_STAINED_GLASS_PANE);
    }

    public static BlockType<BlockGlassBehavior> buildGlass(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    public static BlockType<BlockGlassPaneBehavior> buildGlassPane(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassPaneBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    public static void initFire() {
        BlockTypes.FIRE = AllayBlockType
                .builder(BlockFireBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockFireBaseComponentImpl::new)
                .build();
        BlockTypes.SOUL_FIRE = AllayBlockType
                .builder(BlockFireBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockSoulFireBaseComponentImpl::new)
                .build();
    }

    public static void initTrapdoor() {
        BlockTypes.ACACIA_TRAPDOOR = buildTrapdoor(BlockId.ACACIA_TRAPDOOR);
        BlockTypes.BAMBOO_TRAPDOOR = buildTrapdoor(BlockId.BAMBOO_TRAPDOOR);
        BlockTypes.BIRCH_TRAPDOOR = buildTrapdoor(BlockId.BIRCH_TRAPDOOR);
        BlockTypes.CHERRY_TRAPDOOR = buildTrapdoor(BlockId.CHERRY_TRAPDOOR);
        BlockTypes.CRIMSON_TRAPDOOR = buildTrapdoor(BlockId.CRIMSON_TRAPDOOR);
        BlockTypes.DARK_OAK_TRAPDOOR = buildTrapdoor(BlockId.DARK_OAK_TRAPDOOR);
        BlockTypes.JUNGLE_TRAPDOOR = buildTrapdoor(BlockId.JUNGLE_TRAPDOOR);
        BlockTypes.MANGROVE_TRAPDOOR = buildTrapdoor(BlockId.MANGROVE_TRAPDOOR);
        BlockTypes.TRAPDOOR = buildTrapdoor(BlockId.TRAPDOOR);
        BlockTypes.SPRUCE_TRAPDOOR = buildTrapdoor(BlockId.SPRUCE_TRAPDOOR);
        BlockTypes.WARPED_TRAPDOOR = buildTrapdoor(BlockId.WARPED_TRAPDOOR);
        BlockTypes.PALE_OAK_TRAPDOOR = buildTrapdoor(BlockId.PALE_OAK_TRAPDOOR);

        BlockTypes.IRON_TRAPDOOR = AllayBlockType.builder(BlockTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .setBaseComponentSupplier(BlockIronTrapdoorBaseComponentImpl::new)
                .build();

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperTrapdoor = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_TRAPDOOR : BlockTypes.COPPER_TRAPDOOR;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR : BlockTypes.EXPOSED_COPPER_TRAPDOOR;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR : BlockTypes.WEATHERED_COPPER_TRAPDOOR;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR : BlockTypes.OXIDIZED_COPPER_TRAPDOOR;
        };
        BlockTypes.COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.COPPER_TRAPDOOR, OxidationLevel.UNAFFECTED, copperTrapdoor);
        BlockTypes.EXPOSED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.EXPOSED_COPPER_TRAPDOOR, OxidationLevel.EXPOSED, copperTrapdoor);
        BlockTypes.WEATHERED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.WEATHERED_COPPER_TRAPDOOR, OxidationLevel.WEATHERED, copperTrapdoor);
        BlockTypes.OXIDIZED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.OXIDIZED_COPPER_TRAPDOOR, OxidationLevel.OXIDIZED, copperTrapdoor);
        BlockTypes.WAXED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.WAXED_COPPER_TRAPDOOR, OxidationLevel.UNAFFECTED, copperTrapdoor);
        BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR, OxidationLevel.EXPOSED, copperTrapdoor);
        BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR, OxidationLevel.WEATHERED, copperTrapdoor);
        BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = buildCopperTrapdoor(BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR, OxidationLevel.OXIDIZED, copperTrapdoor);
    }

    public static BlockType<BlockTrapdoorBehavior> buildTrapdoor(BlockId blockId) {
        return trapdoorBuilder(BlockTrapdoorBehaviorImpl.class, blockId).build();
    }

    public static BlockType<BlockCopperTrapdoorBehavior> buildCopperTrapdoor(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return trapdoorBuilder(BlockCopperStairsBehaviorImpl.class, id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static <T extends BlockBehavior> AllayBlockType.Builder trapdoorBuilder(Class<T> clazz, BlockId id) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .setBaseComponentSupplier(BlockTrapdoorBaseComponentImpl::new);
    }

    public static void initSponge() {
        BlockTypes.SPONGE = AllayBlockType
                .builder(BlockSpongeBehaviorImpl.class)
                .vanillaBlock(BlockId.SPONGE)
                .setBaseComponentSupplier(BlockSpongeBaseComponentImpl::new)
                .build();
        BlockTypes.WET_SPONGE = AllayBlockType
                .builder(BlockWetSpongeBehaviorImpl.class)
                .vanillaBlock(BlockId.WET_SPONGE)
                .setBaseComponentSupplier(BlockWetSpongeBaseComponentImpl::new)
                .build();
    }

    public static void initFarmLand() {
        BlockTypes.FARMLAND = AllayBlockType
                .builder(BlockFarmlandBehaviorImpl.class)
                .vanillaBlock(BlockId.FARMLAND)
                .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                .setBaseComponentSupplier(BlockFarmLandBaseComponentImpl::new)
                .build();
    }

    public static void initCrops() {
        BlockTypes.WHEAT = AllayBlockType
                .builder(BlockWheatBehaviorImpl.class)
                .vanillaBlock(BlockId.WHEAT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockWheatBaseComponentImpl::new)
                .build();
        BlockTypes.POTATOES = AllayBlockType
                .builder(BlockPotatoesBehaviorImpl.class)
                .vanillaBlock(BlockId.POTATOES)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockPotatoesBaseComponentImpl::new)
                .build();
        BlockTypes.CARROTS = AllayBlockType
                .builder(BlockCarrotsBehaviorImpl.class)
                .vanillaBlock(BlockId.CARROTS)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockCarrotsBaseComponentImpl::new)
                .build();
        BlockTypes.BEETROOT = AllayBlockType
                .builder(BlockBeetrootBehaviorImpl.class)
                .vanillaBlock(BlockId.BEETROOT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockBeetrootBaseComponentImpl::new)
                .build();
        BlockTypes.MELON_STEM = AllayBlockType
                .builder(BlockMelonStemBehaviorImpl.class)
                .vanillaBlock(BlockId.MELON_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(blockType -> new BlockStemBaseComponentImpl(blockType, BlockId.MELON_BLOCK, ItemId.MELON_SEEDS))
                .build();
        BlockTypes.PUMPKIN_STEM = AllayBlockType
                .builder(BlockPumpkinBehaviorImpl.class)
                .vanillaBlock(BlockId.PUMPKIN_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(blockType -> new BlockStemBaseComponentImpl(blockType, BlockId.PUMPKIN, ItemId.PUMPKIN_SEEDS))
                .build();
    }

    public static void initMelonBlock() {
        BlockTypes.MELON_BLOCK = AllayBlockType
                .builder(BlockMelonBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MELON_BLOCK)
                .setBaseComponentSupplier(BlockMelonBlockBaseComponentImpl::new)
                .build();
        BlockTypes.PUMPKIN = AllayBlockType
                .builder(BlockPumpkinBehaviorImpl.class)
                .vanillaBlock(BlockId.PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockPumpkinBaseComponentImpl::new)
                .build();
    }

    public static void initBeacon() {
        BlockTypes.BEACON = AllayBlockType
                .builder(BlockBeaconBehaviorImpl.class)
                .vanillaBlock(BlockId.BEACON)
                .bindBlockEntity(BlockEntityTypes.BEACON)
                .setBaseComponentSupplier(BlockBeaconBaseComponentImpl::new)
                .build();
    }

    public static void initBrewingStand() {
        BlockTypes.BREWING_STAND = AllayBlockType
                .builder(BlockBrewingStandBehaviorImpl.class)
                .vanillaBlock(BlockId.BREWING_STAND)
                .bindBlockEntity(BlockEntityTypes.BREWING_STAND)
                .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                .build();
    }

    public static void initTnt() {
        BlockTypes.TNT = AllayBlockType
                .builder(BlockTntBehaviorImpl.class)
                .vanillaBlock(BlockId.TNT)
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                .setBaseComponentSupplier(BlockTntBaseComponentImpl::new)
                .build();
    }

    public static void initEnderChest() {
        BlockTypes.ENDER_CHEST = AllayBlockType
                .builder(BlockEnderChestBehaviorImpl.class)
                .vanillaBlock(BlockId.ENDER_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.ENDER_CHEST)
                .setBaseComponentSupplier(BlockEnderChestBaseComponentImpl::new)
                .build();
    }

    public static void initReeds() {
        BlockTypes.REEDS = AllayBlockType
                .builder(BlockReedsBehaviorImpl.class)
                .vanillaBlock(BlockId.REEDS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockReedsBaseComponentImpl::new)
                .build();
    }

    public static void initCactus() {
        BlockTypes.CACTUS = AllayBlockType
                .builder(BlockCactusBehaviorImpl.class)
                .vanillaBlock(BlockId.CACTUS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockCactusBaseComponentImpl::new)
                .build();
    }

    public static void initFlowerPot() {
        BlockTypes.FLOWER_POT = AllayBlockType
                .builder(BlockFlowerPotBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWER_POT)
                .setProperties(BlockPropertyTypes.UPDATE_BIT)
                .bindBlockEntity(BlockEntityTypes.FLOWER_POT)
                .setBaseComponentSupplier(BlockFlowerPotBaseComponentImpl::new)
                .build();
    }

    public static void initDecoratedPot() {
        BlockTypes.DECORATED_POT = AllayBlockType
                .builder(BlockDecoratedPotBehaviorImpl.class)
                .vanillaBlock(BlockId.DECORATED_POT)
                .setProperties(BlockPropertyTypes.DIRECTION_4)
                .bindBlockEntity(BlockEntityTypes.DECORATED_POT)
                .setBaseComponentSupplier(BlockDecoratedPotBaseComponentImpl::new)
                .build();
    }

    public static void initGlazedTerracotta() {
        BlockTypes.BLACK_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.BLACK_GLAZED_TERRACOTTA);
        BlockTypes.BLUE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.BLUE_GLAZED_TERRACOTTA);
        BlockTypes.BROWN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.BROWN_GLAZED_TERRACOTTA);
        BlockTypes.CYAN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.CYAN_GLAZED_TERRACOTTA);
        BlockTypes.GRAY_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.GRAY_GLAZED_TERRACOTTA);
        BlockTypes.GREEN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.GREEN_GLAZED_TERRACOTTA);
        BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA);
        BlockTypes.LIME_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.LIME_GLAZED_TERRACOTTA);
        BlockTypes.MAGENTA_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.MAGENTA_GLAZED_TERRACOTTA);
        BlockTypes.ORANGE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.ORANGE_GLAZED_TERRACOTTA);
        BlockTypes.PINK_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.PINK_GLAZED_TERRACOTTA);
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.PURPLE_GLAZED_TERRACOTTA);
        BlockTypes.RED_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.RED_GLAZED_TERRACOTTA);
        BlockTypes.SILVER_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.SILVER_GLAZED_TERRACOTTA);
        BlockTypes.WHITE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.WHITE_GLAZED_TERRACOTTA);
        BlockTypes.YELLOW_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockId.YELLOW_GLAZED_TERRACOTTA);
    }

    public static BlockType<BlockGlazedTerracottaBehavior> buildGlazedTerracotta(BlockId blockId) {
        return AllayBlockType.builder(BlockGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockGlazedTerracottaBaseComponentImpl::new)
                .build();
    }

    public static void initStonecutterBlock() {
        BlockTypes.STONECUTTER_BLOCK = AllayBlockType
                .builder(BlockStonecutterBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockStonecutterBlockBaseComponentImpl::new)
                .build();
    }

    public static void initGrindstone() {
        BlockTypes.GRINDSTONE = AllayBlockType
                .builder(BlockGrindstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GRINDSTONE)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockGrindstoneBaseComponentImpl::new)
                .build();
    }

    public static void initLoom() {
        BlockTypes.LOOM = AllayBlockType
                .builder(BlockLoomBehaviorImpl.class)
                .vanillaBlock(BlockId.LOOM)
                .setProperties(BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockLoomBaseComponentImpl::new)
                .build();
    }

    public static void initAnvil() {
        BlockTypes.ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(initInfo -> new BlockAnvilBaseComponentImpl(initInfo, BlockId.CHIPPED_ANVIL))
                .addComponent(new BlockAnvilFallableComponentImpl())
                .build();
        BlockTypes.CHIPPED_ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.CHIPPED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(initInfo -> new BlockAnvilBaseComponentImpl(initInfo, BlockId.DAMAGED_ANVIL))
                .addComponent(new BlockAnvilFallableComponentImpl())
                .build();
        BlockTypes.DAMAGED_ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.DAMAGED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(initInfo -> new BlockAnvilBaseComponentImpl(initInfo, BlockId.AIR))
                .addComponent(new BlockAnvilFallableComponentImpl())
                .build();
    }

    public static void initSmithingTable() {
        BlockTypes.SMITHING_TABLE = AllayBlockType
                .builder(BlockSmithingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.SMITHING_TABLE)
                .setBaseComponentSupplier(BlockSmithingTableBaseComponentImpl::new)
                .build();
    }

    public static void initOre() {
        BlockTypes.COAL_ORE = buildOre(BlockId.COAL_ORE, ItemId.COAL, 1, 1, 0, 2);
        BlockTypes.COPPER_ORE = buildOre(BlockId.COPPER_ORE, ItemId.RAW_COPPER, 2, 5, 0, 0);
        BlockTypes.DIAMOND_ORE = buildOre(BlockId.DIAMOND_ORE, ItemId.DIAMOND, 1, 1, 3, 7);
        BlockTypes.EMERALD_ORE = buildOre(BlockId.EMERALD_ORE, ItemId.EMERALD, 1, 1, 3, 7);
        BlockTypes.GOLD_ORE = buildOre(BlockId.GOLD_ORE, ItemId.RAW_GOLD, 1, 1, 0, 0);
        BlockTypes.IRON_ORE = buildOre(BlockId.IRON_ORE, ItemId.RAW_IRON, 1, 1, 0, 0);
        BlockTypes.LAPIS_ORE = buildOre(BlockId.LAPIS_ORE, ItemId.LAPIS_LAZULI, 4, 9, 2, 5);
        BlockTypes.NETHER_GOLD_ORE = buildOre(BlockId.NETHER_GOLD_ORE, ItemId.GOLD_NUGGET, 2, 6, 0, 1);
        BlockTypes.QUARTZ_ORE = buildOre(BlockId.QUARTZ_ORE, ItemId.QUARTZ, 1, 1, 2, 5);
        BlockTypes.REDSTONE_ORE = AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_ORE)
                .setBaseComponentSupplier(BlockRedstoneOreBaseComponentImpl::new)
                .build();
        BlockTypes.LIT_REDSTONE_ORE = AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_ORE)
                .setBaseComponentSupplier(BlockRedstoneOreBaseComponentImpl::new)
                .build();

        BlockTypes.DEEPSLATE_COAL_ORE = buildOre(BlockId.DEEPSLATE_COAL_ORE, ItemId.COAL, 1, 1, 0, 2);
        BlockTypes.DEEPSLATE_COPPER_ORE = buildOre(BlockId.DEEPSLATE_COPPER_ORE, ItemId.RAW_COPPER, 2, 5, 0, 0);
        BlockTypes.DEEPSLATE_DIAMOND_ORE = buildOre(BlockId.DEEPSLATE_DIAMOND_ORE, ItemId.DIAMOND, 1, 1, 0, 2);
        BlockTypes.DEEPSLATE_EMERALD_ORE = buildOre(BlockId.DEEPSLATE_EMERALD_ORE, ItemId.EMERALD, 1, 1, 3, 7);
        BlockTypes.DEEPSLATE_GOLD_ORE = buildOre(BlockId.DEEPSLATE_GOLD_ORE, ItemId.RAW_GOLD, 1, 1, 0, 0);
        BlockTypes.DEEPSLATE_IRON_ORE = buildOre(BlockId.DEEPSLATE_IRON_ORE, ItemId.RAW_IRON, 1, 1, 0, 0);
        BlockTypes.DEEPSLATE_LAPIS_ORE = buildOre(BlockId.DEEPSLATE_LAPIS_ORE, ItemId.LAPIS_LAZULI, 4, 9, 2, 5);
        BlockTypes.DEEPSLATE_REDSTONE_ORE = AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_REDSTONE_ORE)
                .setBaseComponentSupplier(BlockRedstoneOreBaseComponentImpl::new)
                .build();
        BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_DEEPSLATE_REDSTONE_ORE)
                .setBaseComponentSupplier(BlockRedstoneOreBaseComponentImpl::new)
                .build();
    }

    public static BlockType<BlockOreBehavior> buildOre(BlockId blockId, ItemId dropItem, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        return AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockOreBaseComponentImpl(type, dropItem, minDropItem, maxDropItem, minDropXp, maxDropXp))
                .build();
    }

    public static void initGlowstone() {
        BlockTypes.GLOWSTONE = AllayBlockType
                .builder(BlockGlowstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOWSTONE)
                .setBaseComponentSupplier(BlockGlowstoneBaseComponentImpl::new)
                .build();
    }

    public static void initGildedBlackstone() {
        BlockTypes.GILDED_BLACKSTONE = AllayBlockType
                .builder(BlockGildedBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                .setBaseComponentSupplier(BlockGildedBlackstoneBaseComponentImpl::new)
                .build();
    }

    public static void initConcretePowder() {
        BlockTypes.BLACK_CONCRETE_POWDER = buildConcretePowder(BlockId.BLACK_CONCRETE_POWDER, BlockId.BLACK_CONCRETE);
        BlockTypes.BLUE_CONCRETE_POWDER = buildConcretePowder(BlockId.BLUE_CONCRETE_POWDER, BlockId.BLUE_CONCRETE);
        BlockTypes.BROWN_CONCRETE_POWDER = buildConcretePowder(BlockId.BROWN_CONCRETE_POWDER, BlockId.BROWN_CONCRETE);
        BlockTypes.CYAN_CONCRETE_POWDER = buildConcretePowder(BlockId.CYAN_CONCRETE_POWDER, BlockId.CYAN_CONCRETE);
        BlockTypes.GRAY_CONCRETE_POWDER = buildConcretePowder(BlockId.GRAY_CONCRETE_POWDER, BlockId.GRAY_CONCRETE);
        BlockTypes.GREEN_CONCRETE_POWDER = buildConcretePowder(BlockId.GREEN_CONCRETE_POWDER, BlockId.GREEN_CONCRETE);
        BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = buildConcretePowder(BlockId.LIGHT_BLUE_CONCRETE_POWDER, BlockId.LIGHT_BLUE_CONCRETE);
        BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = buildConcretePowder(BlockId.LIGHT_GRAY_CONCRETE_POWDER, BlockId.LIGHT_GRAY_CONCRETE);
        BlockTypes.LIME_CONCRETE_POWDER = buildConcretePowder(BlockId.LIME_CONCRETE_POWDER, BlockId.LIME_CONCRETE);
        BlockTypes.MAGENTA_CONCRETE_POWDER = buildConcretePowder(BlockId.MAGENTA_CONCRETE_POWDER, BlockId.MAGENTA_CONCRETE);
        BlockTypes.ORANGE_CONCRETE_POWDER = buildConcretePowder(BlockId.ORANGE_CONCRETE_POWDER, BlockId.ORANGE_CONCRETE);
        BlockTypes.PINK_CONCRETE_POWDER = buildConcretePowder(BlockId.PINK_CONCRETE_POWDER, BlockId.PINK_CONCRETE);
        BlockTypes.PURPLE_CONCRETE_POWDER = buildConcretePowder(BlockId.PURPLE_CONCRETE_POWDER, BlockId.PURPLE_CONCRETE);
        BlockTypes.RED_CONCRETE_POWDER = buildConcretePowder(BlockId.RED_CONCRETE_POWDER, BlockId.RED_CONCRETE);
        BlockTypes.WHITE_CONCRETE_POWDER = buildConcretePowder(BlockId.WHITE_CONCRETE_POWDER, BlockId.WHITE_CONCRETE);
        BlockTypes.YELLOW_CONCRETE_POWDER = buildConcretePowder(BlockId.YELLOW_CONCRETE_POWDER, BlockId.YELLOW_CONCRETE);
    }

    public static BlockType<BlockConcretePowderBehavior> buildConcretePowder(BlockId blockId, BlockId solidBlockId) {
        return AllayBlockType.builder(BlockConcretePowderBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockConcretePowderBaseComponentImpl(type, solidBlockId))
                .addComponent(new BlockConcretePowderFallableComponentImpl())
                .build();
    }

    public static void initLadder() {
        BlockTypes.LADDER = AllayBlockType
                .builder(BlockLadderBehaviorImpl.class)
                .vanillaBlock(BlockId.LADDER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockLadderBaseComponentImpl::new)
                .build();
    }

    public static void initMagma() {
        BlockTypes.MAGMA = AllayBlockType
                .builder(BlockMagmaBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGMA)
                .setBaseComponentSupplier(BlockMagmaBaseComponentImpl::new)
                .build();
    }

    public static void initInfestedBlocks() {
        BlockTypes.INFESTED_CHISELED_STONE_BRICKS = buildInfestedBlock(BlockId.INFESTED_CHISELED_STONE_BRICKS, BlockId.CHISELED_STONE_BRICKS);
        BlockTypes.INFESTED_COBBLESTONE = buildInfestedBlock(BlockId.INFESTED_COBBLESTONE, BlockId.COBBLESTONE);
        BlockTypes.INFESTED_CRACKED_STONE_BRICKS = buildInfestedBlock(BlockId.INFESTED_CRACKED_STONE_BRICKS, BlockId.CRACKED_STONE_BRICKS);
        BlockTypes.INFESTED_DEEPSLATE = AllayBlockType
                .builder(BlockInfestedBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(type -> new BlockInfestedBlockBaseComponentImpl(type, BlockId.DEEPSLATE))
                .build();
        BlockTypes.INFESTED_MOSSY_STONE_BRICKS = buildInfestedBlock(BlockId.INFESTED_MOSSY_STONE_BRICKS, BlockId.MOSSY_STONE_BRICKS);
        BlockTypes.INFESTED_STONE = buildInfestedBlock(BlockId.INFESTED_STONE, BlockId.STONE);
        BlockTypes.INFESTED_STONE_BRICKS = buildInfestedBlock(BlockId.INFESTED_STONE_BRICKS, BlockId.STONE_BRICKS);
    }

    public static BlockType<BlockInfestedBlockBehavior> buildInfestedBlock(BlockId blockId, BlockId imitateBlockId) {
        return AllayBlockType.builder(BlockInfestedBlockBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockInfestedBlockBaseComponentImpl(type, imitateBlockId))
                .build();
    }

    public static void initEndPortalFrame() {
        BlockTypes.END_PORTAL_FRAME = AllayBlockType
                .builder(BlockEndPortalFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.END_PORTAL_FRAME)
                .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockEndPortalFrameBaseComponentImpl::new)
                .build();
    }

    public static void initIce() {
        BlockTypes.BLUE_ICE = AllayBlockType
                .builder(BlockIceBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_ICE)
                .setBaseComponentSupplier(BlockHardIceBaseComponentImpl::new)
                .build();
        BlockTypes.ICE = AllayBlockType
                .builder(BlockIceBehaviorImpl.class)
                .vanillaBlock(BlockId.ICE)
                .setBaseComponentSupplier(BlockIceBaseComponentImpl::new)
                .build();
        BlockTypes.PACKED_ICE = AllayBlockType
                .builder(BlockIceBehaviorImpl.class)
                .vanillaBlock(BlockId.PACKED_ICE)
                .setBaseComponentSupplier(BlockHardIceBaseComponentImpl::new)
                .build();
        // TODO: Frosted ice
    }

    public static void initSnow() {
        BlockTypes.SNOW = AllayBlockType
                .builder(BlockSnowBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW)
                .setBaseComponentSupplier(BlockSnowBaseComponentImpl::new)
                .build();
    }

    public static void initSnowLayer() {
        BlockTypes.SNOW_LAYER = AllayBlockType
                .builder(BlockSnowLayerBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW_LAYER)
                .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                .setBaseComponentSupplier(BlockSnowLayerBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_SNOW))
                .build();
    }

    public static void initSeaLantern() {
        BlockTypes.SEA_LANTERN = AllayBlockType
                .builder(BlockSeaLanternBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_LANTERN)
                .setBaseComponentSupplier(BlockSeaLanternBaseComponentImpl::new)
                .build();
    }

    public static void initScaffolding() {
        BlockTypes.SCAFFOLDING = AllayBlockType
                .builder(BlockScaffoldingBehaviorImpl.class)
                .vanillaBlock(BlockId.SCAFFOLDING)
                .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                .setBaseComponentSupplier(BlockScaffoldingBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.BLOCK_SCAFFOLDING_FALL))
                .build();
    }

    public static void initChorusFlower() {
        BlockTypes.CHORUS_FLOWER = AllayBlockType
                .builder(BlockChorusFlowerBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_FLOWER)
                .setProperties(BlockPropertyTypes.AGE_6)
                .setBaseComponentSupplier(BlockChorusFlowerBaseComponentImpl::new)
                .build();
    }

    public static void initChorusPlant() {
        BlockTypes.CHORUS_PLANT = AllayBlockType
                .builder(BlockChorusPlantBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_PLANT)
                .setBaseComponentSupplier(BlockChorusPlantBaseComponentImpl::new)
                .build();
    }

    public static void initDeadBush() {
        BlockTypes.DEADBUSH = AllayBlockType
                .builder(BlockDeadbushBehaviorImpl.class)
                .vanillaBlock(BlockId.DEADBUSH)
                .setBaseComponentSupplier(BlockDeadbushBaseComponentImpl::new)
                .build();
    }

    public static void initWalls() {
        BlockTypes.ANDESITE_WALL = buildWall(BlockId.ANDESITE_WALL);
        BlockTypes.BLACKSTONE_WALL = buildWall(BlockId.BLACKSTONE_WALL);
        BlockTypes.BRICK_WALL = buildWall(BlockId.BRICK_WALL);
        BlockTypes.COBBLED_DEEPSLATE_WALL = buildWall(BlockId.COBBLED_DEEPSLATE_WALL);
        BlockTypes.COBBLESTONE_WALL = buildWall(BlockId.COBBLESTONE_WALL);
        BlockTypes.DEEPSLATE_BRICK_WALL = buildWall(BlockId.DEEPSLATE_BRICK_WALL);
        BlockTypes.DEEPSLATE_TILE_WALL = buildWall(BlockId.DEEPSLATE_TILE_WALL);
        BlockTypes.DIORITE_WALL = buildWall(BlockId.DIORITE_WALL);
        BlockTypes.END_STONE_BRICK_WALL = buildWall(BlockId.END_STONE_BRICK_WALL);
        BlockTypes.GRANITE_WALL = buildWall(BlockId.GRANITE_WALL);
        BlockTypes.MOSSY_COBBLESTONE_WALL = buildWall(BlockId.MOSSY_COBBLESTONE_WALL);
        BlockTypes.MOSSY_STONE_BRICK_WALL = buildWall(BlockId.MOSSY_STONE_BRICK_WALL);
        BlockTypes.MUD_BRICK_WALL = buildWall(BlockId.MUD_BRICK_WALL);
        BlockTypes.NETHER_BRICK_WALL = buildWall(BlockId.NETHER_BRICK_WALL);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = buildWall(BlockId.POLISHED_BLACKSTONE_BRICK_WALL);
        BlockTypes.POLISHED_BLACKSTONE_WALL = buildWall(BlockId.POLISHED_BLACKSTONE_WALL);
        BlockTypes.POLISHED_DEEPSLATE_WALL = buildWall(BlockId.POLISHED_DEEPSLATE_WALL);
        BlockTypes.POLISHED_TUFF_WALL = buildWall(BlockId.POLISHED_TUFF_WALL);
        BlockTypes.PRISMARINE_WALL = buildWall(BlockId.PRISMARINE_WALL);
        BlockTypes.RED_NETHER_BRICK_WALL = buildWall(BlockId.RED_NETHER_BRICK_WALL);
        BlockTypes.RED_SANDSTONE_WALL = buildWall(BlockId.RED_SANDSTONE_WALL);
        BlockTypes.RESIN_BRICK_WALL = buildWall(BlockId.RESIN_BRICK_WALL);
        BlockTypes.SANDSTONE_WALL = buildWall(BlockId.SANDSTONE_WALL);
        BlockTypes.STONE_BRICK_WALL = buildWall(BlockId.STONE_BRICK_WALL);
        BlockTypes.TUFF_BRICK_WALL = buildWall(BlockId.TUFF_BRICK_WALL);
        BlockTypes.TUFF_WALL = buildWall(BlockId.TUFF_WALL);
    }

    public static BlockType<BlockWallBehavior> buildWall(BlockId blockId) {
        return AllayBlockType.builder(BlockWallBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .setBaseComponentSupplier(BlockWallBaseComponentImpl::new)
                .build();
    }

    public static void initFenceGates() {
        BlockTypes.ACACIA_FENCE_GATE = buildFenceGate(BlockId.ACACIA_FENCE_GATE);
        BlockTypes.BAMBOO_FENCE_GATE = buildFenceGate(BlockId.BAMBOO_FENCE_GATE);
        BlockTypes.BIRCH_FENCE_GATE = buildFenceGate(BlockId.BIRCH_FENCE_GATE);
        BlockTypes.CHERRY_FENCE_GATE = buildFenceGate(BlockId.CHERRY_FENCE_GATE);
        BlockTypes.CRIMSON_FENCE_GATE = buildFenceGate(BlockId.CRIMSON_FENCE_GATE);
        BlockTypes.DARK_OAK_FENCE_GATE = buildFenceGate(BlockId.DARK_OAK_FENCE_GATE);
        BlockTypes.FENCE_GATE = buildFenceGate(BlockId.FENCE_GATE);
        BlockTypes.JUNGLE_FENCE_GATE = buildFenceGate(BlockId.JUNGLE_FENCE_GATE);
        BlockTypes.MANGROVE_FENCE_GATE = buildFenceGate(BlockId.MANGROVE_FENCE_GATE);
        BlockTypes.PALE_OAK_FENCE_GATE = buildFenceGate(BlockId.PALE_OAK_FENCE_GATE);
        BlockTypes.SPRUCE_FENCE_GATE = buildFenceGate(BlockId.SPRUCE_FENCE_GATE);
        BlockTypes.WARPED_FENCE_GATE = buildFenceGate(BlockId.WARPED_FENCE_GATE);
    }

    public static BlockType<BlockFenceGateBehavior> buildFenceGate(BlockId blockId) {
        return AllayBlockType.builder(BlockFenceGateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .setBaseComponentSupplier(BlockFenceGateBaseComponentImpl::new)
                .build();
    }

    public static void initQuartzBlocks() {
        // IDK why mojang added PILLAR_AXIS to quartz block properties and doesn't use it.
        BlockTypes.QUARTZ_BLOCK = AllayBlockType
                .builder(BlockQuartzBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockQuartzBlockBaseComponentImpl::new)
                .build();
        BlockTypes.CHISELED_QUARTZ_BLOCK = AllayBlockType
                .builder(BlockChiseledQuartzBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockQuartzBlockBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_QUARTZ = AllayBlockType
                .builder(BlockSmoothQuartzBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockQuartzBlockBaseComponentImpl::new)
                .build();
    }

    public static void initPurpurBlock() {
        // IDK why mojang added PILLAR_AXIS to purpur block properties and doesn't use it.
        BlockTypes.PURPUR_BLOCK = AllayBlockType
                .builder(BlockPurpurBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockPurpurBlockBaseComponentImpl::new)
                .build();
    }

    public static void initItemFrame() {
        BlockTypes.FRAME = AllayBlockType
                .builder(BlockFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.FRAME)
                .bindBlockEntity(BlockEntityTypes.ITEM_FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .setBaseComponentSupplier(BlockFrameBaseComponentImpl::new)
                .build();
        BlockTypes.GLOW_FRAME = AllayBlockType
                .builder(BlockGlowFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOW_FRAME)
                .bindBlockEntity(BlockEntityTypes.GLOW_ITEM_FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .setBaseComponentSupplier(BlockFrameBaseComponentImpl::new)
                .build();
    }

    public static void initBed() {
        BlockTypes.BED = AllayBlockType
                .builder(BlockBedBehaviorImpl.class)
                .vanillaBlock(BlockId.BED)
                .bindBlockEntity(BlockEntityTypes.BED)
                .setProperties(BlockPropertyTypes.HEAD_PIECE_BIT, BlockPropertyTypes.OCCUPIED_BIT, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockBedBaseComponentImpl::new)
                .addComponent(new BlockBedRespawnPointComponentImpl())
                .build();
    }

    public static void initRespawnAnchor() {
        BlockTypes.RESPAWN_ANCHOR = AllayBlockType
                .builder(BlockRespawnAnchorBehaviorImpl.class)
                .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                .setBaseComponentSupplier(BlockRespawnAnchorBaseComponentImpl::new)
                .addComponent(new BlockRespawnAnchorRespawnPointComponentImpl())
                .build();
    }

    public static void initNoteblock() {
        BlockTypes.NOTEBLOCK = AllayBlockType
                .builder(BlockNoteblockBehaviorImpl.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .bindBlockEntity(BlockEntityTypes.NOTEBLOCK)
                .setBaseComponentSupplier(BlockNoteblockBaseComponentImpl::new)
                .build();
    }

    public static void initRods() {
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehaviorImpl.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockEndRodBaseComponentImpl::new)
                .build();

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> lightningRod = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_LIGHTNING_ROD : BlockTypes.LIGHTNING_ROD;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_LIGHTNING_ROD : BlockTypes.EXPOSED_LIGHTNING_ROD;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_LIGHTNING_ROD : BlockTypes.WEATHERED_LIGHTNING_ROD;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_LIGHTNING_ROD : BlockTypes.OXIDIZED_LIGHTNING_ROD;
        };

        BlockTypes.LIGHTNING_ROD = buildLightningRod(BlockId.LIGHTNING_ROD, OxidationLevel.UNAFFECTED, lightningRod);
        BlockTypes.EXPOSED_LIGHTNING_ROD = buildLightningRod(BlockId.EXPOSED_LIGHTNING_ROD, OxidationLevel.EXPOSED, lightningRod);
        BlockTypes.WEATHERED_LIGHTNING_ROD = buildLightningRod(BlockId.WEATHERED_LIGHTNING_ROD, OxidationLevel.WEATHERED, lightningRod);
        BlockTypes.OXIDIZED_LIGHTNING_ROD = buildLightningRod(BlockId.OXIDIZED_LIGHTNING_ROD, OxidationLevel.OXIDIZED, lightningRod);
        BlockTypes.WAXED_LIGHTNING_ROD = buildLightningRod(BlockId.WAXED_LIGHTNING_ROD, OxidationLevel.UNAFFECTED, lightningRod);
        BlockTypes.WAXED_EXPOSED_LIGHTNING_ROD = buildLightningRod(BlockId.WAXED_EXPOSED_LIGHTNING_ROD, OxidationLevel.EXPOSED, lightningRod);
        BlockTypes.WAXED_WEATHERED_LIGHTNING_ROD = buildLightningRod(BlockId.WAXED_WEATHERED_LIGHTNING_ROD, OxidationLevel.WEATHERED, lightningRod);
        BlockTypes.WAXED_OXIDIZED_LIGHTNING_ROD = buildLightningRod(BlockId.WAXED_OXIDIZED_LIGHTNING_ROD, OxidationLevel.OXIDIZED, lightningRod);
    }

    private static BlockType<BlockLightningRodBehavior> buildLightningRod(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockLightningRodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLightningRodBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initLanterns() {
        BlockTypes.LANTERN = buildLantern(BlockId.LANTERN);
        BlockTypes.SOUL_LANTERN = buildLantern(BlockId.SOUL_LANTERN);

        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperLantern = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_LANTERN : BlockTypes.COPPER_LANTERN;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_LANTERN : BlockTypes.EXPOSED_COPPER_LANTERN;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_LANTERN : BlockTypes.WEATHERED_COPPER_LANTERN;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_LANTERN : BlockTypes.OXIDIZED_COPPER_LANTERN;
        };

        BlockTypes.COPPER_LANTERN = buildCopperLantern(BlockId.COPPER_LANTERN, OxidationLevel.UNAFFECTED, copperLantern);
        BlockTypes.EXPOSED_COPPER_LANTERN = buildCopperLantern(BlockId.EXPOSED_COPPER_LANTERN, OxidationLevel.EXPOSED, copperLantern);
        BlockTypes.WEATHERED_COPPER_LANTERN = buildCopperLantern(BlockId.WEATHERED_COPPER_LANTERN, OxidationLevel.WEATHERED, copperLantern);
        BlockTypes.OXIDIZED_COPPER_LANTERN = buildCopperLantern(BlockId.OXIDIZED_COPPER_LANTERN, OxidationLevel.OXIDIZED, copperLantern);
        BlockTypes.WAXED_COPPER_LANTERN = buildCopperLantern(BlockId.WAXED_COPPER_LANTERN, OxidationLevel.UNAFFECTED, copperLantern);
        BlockTypes.WAXED_EXPOSED_COPPER_LANTERN = buildCopperLantern(BlockId.WAXED_EXPOSED_COPPER_LANTERN, OxidationLevel.EXPOSED, copperLantern);
        BlockTypes.WAXED_WEATHERED_COPPER_LANTERN = buildCopperLantern(BlockId.WAXED_WEATHERED_COPPER_LANTERN, OxidationLevel.WEATHERED, copperLantern);
        BlockTypes.WAXED_OXIDIZED_COPPER_LANTERN = buildCopperLantern(BlockId.WAXED_OXIDIZED_COPPER_LANTERN, OxidationLevel.OXIDIZED, copperLantern);
    }

    private static BlockType<BlockLanternBehavior> buildLantern(BlockId blockId) {
        return AllayBlockType
                .builder(BlockLanternBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLanternBaseComponentImpl::new)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    private static BlockType<BlockCopperLanternBehavior> buildCopperLantern(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperLanternBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLanternBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCopperGolemStatues() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperGolemStatue = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_GOLEM_STATUE : BlockTypes.COPPER_GOLEM_STATUE;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_GOLEM_STATUE : BlockTypes.EXPOSED_COPPER_GOLEM_STATUE;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_GOLEM_STATUE : BlockTypes.WEATHERED_COPPER_GOLEM_STATUE;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_GOLEM_STATUE : BlockTypes.OXIDIZED_COPPER_GOLEM_STATUE;
        };

        BlockTypes.COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.COPPER_GOLEM_STATUE, OxidationLevel.UNAFFECTED, copperGolemStatue);
        BlockTypes.EXPOSED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.EXPOSED_COPPER_GOLEM_STATUE, OxidationLevel.EXPOSED, copperGolemStatue);
        BlockTypes.WEATHERED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.WEATHERED_COPPER_GOLEM_STATUE, OxidationLevel.WEATHERED, copperGolemStatue);
        BlockTypes.OXIDIZED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.OXIDIZED_COPPER_GOLEM_STATUE, OxidationLevel.OXIDIZED, copperGolemStatue);
        BlockTypes.WAXED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.WAXED_COPPER_GOLEM_STATUE, OxidationLevel.UNAFFECTED, copperGolemStatue);
        BlockTypes.WAXED_EXPOSED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.WAXED_EXPOSED_COPPER_GOLEM_STATUE, OxidationLevel.EXPOSED, copperGolemStatue);
        BlockTypes.WAXED_WEATHERED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.WAXED_WEATHERED_COPPER_GOLEM_STATUE, OxidationLevel.WEATHERED, copperGolemStatue);
        BlockTypes.WAXED_OXIDIZED_COPPER_GOLEM_STATUE = buildCopperGolemStatue(BlockId.WAXED_OXIDIZED_COPPER_GOLEM_STATUE, OxidationLevel.OXIDIZED, copperGolemStatue);
    }

    private static BlockType<BlockCopperGolemStatueBehavior> buildCopperGolemStatue(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperGolemStatueBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initCopperChains() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperChain = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_CHAIN : BlockTypes.COPPER_CHAIN;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_CHAIN : BlockTypes.EXPOSED_COPPER_CHAIN;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_CHAIN : BlockTypes.WEATHERED_COPPER_CHAIN;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_CHAIN : BlockTypes.OXIDIZED_COPPER_CHAIN;
        };

        BlockTypes.COPPER_CHAIN = buildCopperChain(BlockId.COPPER_CHAIN, OxidationLevel.UNAFFECTED, copperChain);
        BlockTypes.EXPOSED_COPPER_CHAIN = buildCopperChain(BlockId.EXPOSED_COPPER_CHAIN, OxidationLevel.EXPOSED, copperChain);
        BlockTypes.WEATHERED_COPPER_CHAIN = buildCopperChain(BlockId.WEATHERED_COPPER_CHAIN, OxidationLevel.WEATHERED, copperChain);
        BlockTypes.OXIDIZED_COPPER_CHAIN = buildCopperChain(BlockId.OXIDIZED_COPPER_CHAIN, OxidationLevel.OXIDIZED, copperChain);
        BlockTypes.WAXED_COPPER_CHAIN = buildCopperChain(BlockId.WAXED_COPPER_CHAIN, OxidationLevel.UNAFFECTED, copperChain);
        BlockTypes.WAXED_EXPOSED_COPPER_CHAIN = buildCopperChain(BlockId.WAXED_EXPOSED_COPPER_CHAIN, OxidationLevel.EXPOSED, copperChain);
        BlockTypes.WAXED_WEATHERED_COPPER_CHAIN = buildCopperChain(BlockId.WAXED_WEATHERED_COPPER_CHAIN, OxidationLevel.WEATHERED, copperChain);
        BlockTypes.WAXED_OXIDIZED_COPPER_CHAIN = buildCopperChain(BlockId.WAXED_OXIDIZED_COPPER_CHAIN, OxidationLevel.OXIDIZED, copperChain);
    }

    private static BlockType<BlockCopperChainBehavior> buildCopperChain(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperChainBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initHeads() {
        BlockTypes.CREEPER_HEAD = buildHead(BlockId.CREEPER_HEAD);
        BlockTypes.DRAGON_HEAD = buildHead(BlockId.DRAGON_HEAD);
        BlockTypes.PIGLIN_HEAD = buildHead(BlockId.PIGLIN_HEAD);
        BlockTypes.PLAYER_HEAD = buildHead(BlockId.PLAYER_HEAD);
        BlockTypes.ZOMBIE_HEAD = buildHead(BlockId.ZOMBIE_HEAD);
        BlockTypes.SKELETON_SKULL = buildHead(BlockId.SKELETON_SKULL);
        BlockTypes.WITHER_SKELETON_SKULL = buildHead(BlockId.WITHER_SKELETON_SKULL);
    }

    private static BlockType<BlockHeadBehavior> buildHead(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHeadBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockHeadBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HEAD)
                .build();
    }

    public static void initSmallFlowers() {
        BlockTypes.DANDELION = buildSmallFlower(BlockId.DANDELION);
        BlockTypes.POPPY = buildSmallFlower(BlockId.POPPY);
        BlockTypes.BLUE_ORCHID = buildSmallFlower(BlockId.BLUE_ORCHID);
        BlockTypes.ALLIUM = buildSmallFlower(BlockId.ALLIUM);
        BlockTypes.AZURE_BLUET = buildSmallFlower(BlockId.AZURE_BLUET);
        BlockTypes.RED_TULIP = buildSmallFlower(BlockId.RED_TULIP);
        BlockTypes.ORANGE_TULIP = buildSmallFlower(BlockId.ORANGE_TULIP);
        BlockTypes.WHITE_TULIP = buildSmallFlower(BlockId.WHITE_TULIP);
        BlockTypes.PINK_TULIP = buildSmallFlower(BlockId.PINK_TULIP);
        BlockTypes.OXEYE_DAISY = buildSmallFlower(BlockId.OXEYE_DAISY);
        BlockTypes.CORNFLOWER = buildSmallFlower(BlockId.CORNFLOWER);
        BlockTypes.LILY_OF_THE_VALLEY = buildSmallFlower(BlockId.LILY_OF_THE_VALLEY);
        BlockTypes.WITHER_ROSE = buildSmallFlower(BlockId.WITHER_ROSE);
        BlockTypes.TORCHFLOWER = buildSmallFlower(BlockId.TORCHFLOWER);
        BlockTypes.CLOSED_EYEBLOSSOM = buildSmallFlower(BlockId.CLOSED_EYEBLOSSOM);
        BlockTypes.OPEN_EYEBLOSSOM = buildSmallFlower(BlockId.OPEN_EYEBLOSSOM);
    }

    private static BlockType<BlockSmallFlower> buildSmallFlower(BlockId blockId) {
        return AllayBlockType
                .builder(BlockSmallFlowerImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockSmallFlowerBaseComponentImpl::new)
                .build();
    }

    public static void initBigFlowers() {
        BlockTypes.SUNFLOWER = buildBigFlower(BlockId.SUNFLOWER);
        BlockTypes.LILAC = buildBigFlower(BlockId.LILAC);
        BlockTypes.ROSE_BUSH = buildBigFlower(BlockId.ROSE_BUSH);
        BlockTypes.PEONY = buildBigFlower(BlockId.PEONY);
        BlockTypes.PITCHER_PLANT = buildBigFlower(BlockId.PITCHER_PLANT);
    }

    private static BlockType<BlockBigFlower> buildBigFlower(BlockId blockId) {
        return AllayBlockType
                .builder(BlockBigFlowerImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(BlockBigFlowerBaseComponentImpl::new)
                .build();
    }

    public static void initPlantPiles() {
        BlockTypes.LEAF_LITTER = buildPlantPile(BlockId.LEAF_LITTER);
        BlockTypes.PINK_PETALS = buildPlantPile(BlockId.PINK_PETALS);
        BlockTypes.WILDFLOWERS = buildPlantPile(BlockId.WILDFLOWERS);
    }

    private static BlockType<BlockPlantPile> buildPlantPile(BlockId blockId) {
        return AllayBlockType
                .builder(BlockPlantPileImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockPlantPileBaseComponentImpl::new)
                .build();
    }

    public static void initCopperBars() {
        BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> copperBars = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER_BARS : BlockTypes.COPPER_BARS;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER_BARS : BlockTypes.EXPOSED_COPPER_BARS;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER_BARS : BlockTypes.WEATHERED_COPPER_BARS;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER_BARS : BlockTypes.OXIDIZED_COPPER_BARS;
        };
        BlockTypes.COPPER_BARS = buildCopperBars(BlockId.COPPER_BARS, OxidationLevel.UNAFFECTED, copperBars);
        BlockTypes.EXPOSED_COPPER_BARS = buildCopperBars(BlockId.EXPOSED_COPPER_BARS, OxidationLevel.EXPOSED, copperBars);
        BlockTypes.WEATHERED_COPPER_BARS = buildCopperBars(BlockId.WEATHERED_COPPER_BARS, OxidationLevel.WEATHERED, copperBars);
        BlockTypes.OXIDIZED_COPPER_BARS = buildCopperBars(BlockId.OXIDIZED_COPPER_BARS, OxidationLevel.OXIDIZED, copperBars);
        BlockTypes.WAXED_COPPER_BARS = buildCopperBars(BlockId.WAXED_COPPER_BARS, OxidationLevel.UNAFFECTED, copperBars);
        BlockTypes.WAXED_EXPOSED_COPPER_BARS = buildCopperBars(BlockId.WAXED_EXPOSED_COPPER_BARS, OxidationLevel.EXPOSED, copperBars);
        BlockTypes.WAXED_WEATHERED_COPPER_BARS = buildCopperBars(BlockId.WAXED_WEATHERED_COPPER_BARS, OxidationLevel.WEATHERED, copperBars);
        BlockTypes.WAXED_OXIDIZED_COPPER_BARS = buildCopperBars(BlockId.WAXED_OXIDIZED_COPPER_BARS, OxidationLevel.OXIDIZED, copperBars);
    }

    public static BlockType<BlockCopperBarsBehavior> buildCopperBars(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return AllayBlockType.builder(BlockCopperBarsBehaviorImpl.class)
                .vanillaBlock(id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initLever() {
        BlockTypes.LEVER = AllayBlockType
                .builder(BlockLeverBehaviorImpl.class)
                .vanillaBlock(BlockId.LEVER)
                .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .setBaseComponentSupplier(BlockLeverBaseComponentImpl::new)
                .build();
    }

    public static void initRedstoneWire() {
        BlockTypes.REDSTONE_WIRE = AllayBlockType
                .builder(BlockRedstoneWireBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_WIRE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(BlockRedstoneWireBaseComponentImpl::new)
                .build();
    }

    public static void initRedstoneBlock() {
        BlockTypes.REDSTONE_BLOCK = AllayBlockType
                .builder(BlockRedstoneBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_BLOCK)
                .setBaseComponentSupplier(BlockRedstoneBlockBaseComponentImpl::new)
                .build();
    }

    public static void initPressurePlates() {
        // Binary pressure plates (stone and wooden variants)
        BlockTypes.STONE_PRESSURE_PLATE = buildPressurePlate(BlockId.STONE_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.WOODEN_PRESSURE_PLATE = buildPressurePlate(BlockId.WOODEN_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.ACACIA_PRESSURE_PLATE = buildPressurePlate(BlockId.ACACIA_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.BAMBOO_PRESSURE_PLATE = buildPressurePlate(BlockId.BAMBOO_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.BIRCH_PRESSURE_PLATE = buildPressurePlate(BlockId.BIRCH_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.CHERRY_PRESSURE_PLATE = buildPressurePlate(BlockId.CHERRY_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.CRIMSON_PRESSURE_PLATE = buildPressurePlate(BlockId.CRIMSON_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.DARK_OAK_PRESSURE_PLATE = buildPressurePlate(BlockId.DARK_OAK_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.JUNGLE_PRESSURE_PLATE = buildPressurePlate(BlockId.JUNGLE_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.MANGROVE_PRESSURE_PLATE = buildPressurePlate(BlockId.MANGROVE_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.SPRUCE_PRESSURE_PLATE = buildPressurePlate(BlockId.SPRUCE_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.WARPED_PRESSURE_PLATE = buildPressurePlate(BlockId.WARPED_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.PALE_OAK_PRESSURE_PLATE = buildPressurePlate(BlockId.PALE_OAK_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);
        BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = buildPressurePlate(BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE, BlockPressurePlateBaseComponentImpl::new);

        // Weighted pressure plates
        BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = buildPressurePlate(BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE, BlockLightWeightedPressurePlateBaseComponentImpl::new);
        BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = buildPressurePlate(BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE, BlockHeavyWeightedPressurePlateBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildPressurePlate(BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(BlockPressurePlateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initTripwire() {
        BlockTypes.TRIPWIRE_HOOK = AllayBlockType
                .builder(BlockTripwireHookBehaviorImpl.class)
                .vanillaBlock(BlockId.TRIPWIRE_HOOK)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.POWERED_BIT)
                .setBaseComponentSupplier(BlockTripwireHookBaseComponentImpl::new)
                .build();
        BlockTypes.TRIP_WIRE = AllayBlockType
                .builder(BlockTripWireBehaviorImpl.class)
                .vanillaBlock(BlockId.TRIP_WIRE)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DISARMED_BIT, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.SUSPENDED_BIT)
                .setBaseComponentSupplier(BlockTripWireBaseComponentImpl::new)
                .build();
    }

    public static void initObserver() {
        BlockTypes.OBSERVER = AllayBlockType
                .builder(BlockObserverBehaviorImpl.class)
                .vanillaBlock(BlockId.OBSERVER)
                .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .setBaseComponentSupplier(BlockObserverBaseComponentImpl::new)
                .build();
    }

    public static void initRedstoneLamp() {
        BlockTypes.REDSTONE_LAMP = AllayBlockType
                .builder(BlockRedstoneLampBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_LAMP)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneLampBaseComponentImpl(blockType, false))
                .build();
        BlockTypes.LIT_REDSTONE_LAMP = AllayBlockType
                .builder(BlockRedstoneLampBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_LAMP)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneLampBaseComponentImpl(blockType, true))
                .build();
    }

    public static void initRedstoneTorch() {
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneTorchBaseComponentImpl(blockType, true))
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneTorchBaseComponentImpl(blockType, false))
                .build();
    }

    public static void initDaylightDetector() {
        BlockTypes.DAYLIGHT_DETECTOR = AllayBlockType
                .builder(BlockDaylightDetectorBehaviorImpl.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(blockType -> new BlockDaylightDetectorBaseComponentImpl(blockType, false))
                .build();
        BlockTypes.DAYLIGHT_DETECTOR_INVERTED = AllayBlockType
                .builder(BlockDaylightDetectorBehaviorImpl.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR_INVERTED)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(blockType -> new BlockDaylightDetectorBaseComponentImpl(blockType, true))
                .build();
    }

    public static void initRedstoneRepeater() {
        BlockTypes.POWERED_REPEATER = AllayBlockType
                .builder(BlockRepeaterBehaviorImpl.class)
                .vanillaBlock(BlockId.POWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneRepeaterBaseComponentImpl(blockType, true))
                .build();
        BlockTypes.UNPOWERED_REPEATER = AllayBlockType
                .builder(BlockRepeaterBehaviorImpl.class)
                .vanillaBlock(BlockId.UNPOWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneRepeaterBaseComponentImpl(blockType, false))
                .build();
    }

    public static void initRedstoneComparator() {
        BlockTypes.POWERED_COMPARATOR = AllayBlockType
                .builder(BlockComparatorBehaviorImpl.class)
                .vanillaBlock(BlockId.POWERED_COMPARATOR)
                .bindBlockEntity(BlockEntityTypes.COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneComparatorBaseComponentImpl(blockType, true))
                .build();
        BlockTypes.UNPOWERED_COMPARATOR = AllayBlockType
                .builder(BlockComparatorBehaviorImpl.class)
                .vanillaBlock(BlockId.UNPOWERED_COMPARATOR)
                .bindBlockEntity(BlockEntityTypes.COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneComparatorBaseComponentImpl(blockType, false))
                .build();
    }

    public static void initTarget() {
        BlockTypes.TARGET = AllayBlockType
                .builder(BlockTargetBehaviorImpl.class)
                .vanillaBlock(BlockId.TARGET)
                .bindBlockEntity(BlockEntityTypes.TARGET)
                .setBaseComponentSupplier(BlockTargetBaseComponentImpl::new)
                .build();
    }

    public static void initCampfire() {
        BlockTypes.CAMPFIRE = AllayBlockType
                .builder(BlockCampfireBehaviorImpl.class)
                .vanillaBlock(BlockId.CAMPFIRE)
                .bindBlockEntity(BlockEntityTypes.CAMPFIRE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.EXTINGUISHED)
                .setBaseComponentSupplier(BlockCampfireBaseComponentImpl::new)
                .build();
        BlockTypes.SOUL_CAMPFIRE = AllayBlockType
                .builder(BlockCampfireBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_CAMPFIRE)
                .bindBlockEntity(BlockEntityTypes.SOUL_CAMPFIRE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.EXTINGUISHED)
                .setBaseComponentSupplier(BlockSoulCampfireBaseComponentImpl::new)
                .build();
    }

    public static void initSaplings() {
        BlockTypes.OAK_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, OakTreeFeature.IDENTIFIER, FancyOakTreeFeature.IDENTIFIER, null))
                .build();
        BlockTypes.BIRCH_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, BirchTreeFeature.IDENTIFIER, null, null))
                .build();
        BlockTypes.SPRUCE_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, SpruceTreeFeature.IDENTIFIER, null, MegaSpruceTreeFeature.IDENTIFIER))
                .build();
        BlockTypes.JUNGLE_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, JungleTreeFeature.IDENTIFIER, null, MegaJungleTreeFeature.IDENTIFIER))
                .build();
        BlockTypes.ACACIA_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, AcaciaTreeFeature.IDENTIFIER, null, null))
                .build();
        BlockTypes.DARK_OAK_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, null, null, DarkOakTreeFeature.IDENTIFIER))
                .build();
        BlockTypes.CHERRY_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, CherryTreeFeature.IDENTIFIER, null, null))
                .build();
        BlockTypes.PALE_OAK_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.PALE_OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, PaleOakTreeFeature.IDENTIFIER, null, null))
                .build();
        BlockTypes.MANGROVE_PROPAGULE = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                .setBaseComponentSupplier(BlockMangrovePropaguleBaseComponentImpl::new)
                .build();
    }

    public static void initPiston() {
        BlockTypes.PISTON = AllayBlockType
                .builder(BlockPistonBehaviorImpl.class)
                .vanillaBlock(BlockId.PISTON)
                .bindBlockEntity(BlockEntityTypes.PISTON_ARM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockPistonBaseComponentImpl::new)
                .build();
        BlockTypes.STICKY_PISTON = AllayBlockType
                .builder(BlockStickyPistonBehaviorImpl.class)
                .vanillaBlock(BlockId.STICKY_PISTON)
                .bindBlockEntity(BlockEntityTypes.PISTON_ARM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockStickyPistonBaseComponentImpl::new)
                .build();
        BlockTypes.MOVING_BLOCK = AllayBlockType
                .builder(BlockMovingBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MOVING_BLOCK)
                .bindBlockEntity(BlockEntityTypes.MOVING_BLOCK)
                .build();
        BlockTypes.PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockPistonArmCollisionBehaviorImpl.class)
                .vanillaBlock(BlockId.PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockPistonArmCollisionBaseComponentImpl::new)
                .build();
        BlockTypes.STICKY_PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockStickyPistonArmCollisionBehaviorImpl.class)
                .vanillaBlock(BlockId.STICKY_PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockPistonArmCollisionBaseComponentImpl::new)
                .build();
    }

    public static void initCake() {
        BlockTypes.CAKE = AllayBlockType
                .builder(BlockCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CAKE)
                .setProperties(BlockPropertyTypes.BITE_COUNTER)
                .setBaseComponentSupplier(BlockCakeBaseComponentImpl::new)
                .build();
    }

    public static void initCandles() {
        BlockTypes.CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.CANDLE_CAKE))
                .build();
        BlockTypes.WHITE_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.WHITE_CANDLE_CAKE))
                .build();
        BlockTypes.ORANGE_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.ORANGE_CANDLE_CAKE))
                .build();
        BlockTypes.MAGENTA_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.MAGENTA_CANDLE_CAKE))
                .build();
        BlockTypes.LIGHT_BLUE_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.LIGHT_BLUE_CANDLE_CAKE))
                .build();
        BlockTypes.YELLOW_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.YELLOW_CANDLE_CAKE))
                .build();
        BlockTypes.LIME_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.LIME_CANDLE_CAKE))
                .build();
        BlockTypes.PINK_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.PINK_CANDLE_CAKE))
                .build();
        BlockTypes.GRAY_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.GRAY_CANDLE_CAKE))
                .build();
        BlockTypes.LIGHT_GRAY_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.LIGHT_GRAY_CANDLE_CAKE))
                .build();
        BlockTypes.CYAN_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.CYAN_CANDLE_CAKE))
                .build();
        BlockTypes.PURPLE_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.PURPLE_CANDLE_CAKE))
                .build();
        BlockTypes.BLUE_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.BLUE_CANDLE_CAKE))
                .build();
        BlockTypes.BROWN_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.BROWN_CANDLE_CAKE))
                .build();
        BlockTypes.GREEN_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.GREEN_CANDLE_CAKE))
                .build();
        BlockTypes.RED_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.RED_CANDLE_CAKE))
                .build();
        BlockTypes.BLACK_CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, BlockId.BLACK_CANDLE_CAKE))
                .build();
    }

    public static void initCandleCakes() {
        BlockTypes.CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.CANDLE))
                .build();
        BlockTypes.WHITE_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.WHITE_CANDLE))
                .build();
        BlockTypes.ORANGE_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.ORANGE_CANDLE))
                .build();
        BlockTypes.MAGENTA_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.MAGENTA_CANDLE))
                .build();
        BlockTypes.LIGHT_BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.LIGHT_BLUE_CANDLE))
                .build();
        BlockTypes.YELLOW_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.YELLOW_CANDLE))
                .build();
        BlockTypes.LIME_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.LIME_CANDLE))
                .build();
        BlockTypes.PINK_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.PINK_CANDLE))
                .build();
        BlockTypes.GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.GRAY_CANDLE))
                .build();
        BlockTypes.LIGHT_GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.LIGHT_GRAY_CANDLE))
                .build();
        BlockTypes.CYAN_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.CYAN_CANDLE))
                .build();
        BlockTypes.PURPLE_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.PURPLE_CANDLE))
                .build();
        BlockTypes.BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.BLUE_CANDLE))
                .build();
        BlockTypes.BROWN_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.BROWN_CANDLE))
                .build();
        BlockTypes.GREEN_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.GREEN_CANDLE))
                .build();
        BlockTypes.RED_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.RED_CANDLE))
                .build();
        BlockTypes.BLACK_CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, ItemId.BLACK_CANDLE))
                .build();
    }

    public static void initCauldron() {
        BlockTypes.CAULDRON = AllayBlockType
                .builder(BlockCauldronBehaviorImpl.class)
                .vanillaBlock(BlockId.CAULDRON)
                .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                .bindBlockEntity(BlockEntityTypes.CAULDRON)
                .setBaseComponentSupplier(BlockCauldronBaseComponentImpl::new)
                .build();
    }

    public static void initComposter() {
        BlockTypes.COMPOSTER = AllayBlockType
                .builder(BlockComposterBehaviorImpl.class)
                .vanillaBlock(BlockId.COMPOSTER)
                .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                .setBaseComponentSupplier(BlockComposterBaseComponentImpl::new)
                .build();
    }

    public static void initBell() {
        BlockTypes.BELL = AllayBlockType
                .builder(BlockBellBehaviorImpl.class)
                .vanillaBlock(BlockId.BELL)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.TOGGLE_BIT)
                .bindBlockEntity(BlockEntityTypes.BELL)
                .setBaseComponentSupplier(BlockBellBaseComponentImpl::new)
                .build();
    }

    public static void initLectern() {
        BlockTypes.LECTERN = AllayBlockType
                .builder(BlockLecternBehaviorImpl.class)
                .vanillaBlock(BlockId.LECTERN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .bindBlockEntity(BlockEntityTypes.LECTERN)
                .setBaseComponentSupplier(BlockLecternBaseComponentImpl::new)
                .build();
    }

    public static void initCommandBlock() {
        BlockTypes.COMMAND_BLOCK = AllayBlockType
                .builder(BlockCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.COMMAND_BLOCK)
                .setBaseComponentSupplier(BlockCommandBlockBaseComponentImpl::new)
                .build();
        BlockTypes.CHAIN_COMMAND_BLOCK = AllayBlockType
                .builder(BlockChainCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.CHAIN_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.COMMAND_BLOCK)
                .setBaseComponentSupplier(BlockCommandBlockBaseComponentImpl::new)
                .build();
        BlockTypes.REPEATING_COMMAND_BLOCK = AllayBlockType
                .builder(BlockRepeatingCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.REPEATING_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.COMMAND_BLOCK)
                .setBaseComponentSupplier(BlockCommandBlockBaseComponentImpl::new)
                .build();
    }

    public static void initPointedDripstone() {
        BlockTypes.POINTED_DRIPSTONE = AllayBlockType
                .builder(BlockPointedDripstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockPointedDripstoneBaseComponentImpl::new)
                .addComponent(new BlockPointedDripstoneFallableComponentImpl(SoundNames.LAND_POINTED_DRIPSTONE))
                .build();
    }

    public static void initNetherVines() {
        BlockTypes.TWISTING_VINES = AllayBlockType
                .builder(BlockTwistingVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.TWISTING_VINES)
                .setProperties(BlockPropertyTypes.TWISTING_VINES_AGE)
                .setBaseComponentSupplier(blockType -> new BlockNetherVinesBaseComponentImpl(blockType, BlockFace.UP, BlockFace.DOWN, BlockPropertyTypes.TWISTING_VINES_AGE))
                .build();
        BlockTypes.WEEPING_VINES = AllayBlockType
                .builder(BlockWeepingVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.WEEPING_VINES)
                .setProperties(BlockPropertyTypes.WEEPING_VINES_AGE)
                .setBaseComponentSupplier(blockType -> new BlockNetherVinesBaseComponentImpl(blockType, BlockFace.DOWN, BlockFace.UP, BlockPropertyTypes.WEEPING_VINES_AGE))
                .build();
    }

    public static void initRail() {
        BlockTypes.RAIL = AllayBlockType
                .builder(BlockRailBehaviorImpl.class)
                .vanillaBlock(BlockId.RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                .setBaseComponentSupplier(BlockNormalRailBaseComponentImpl::new)
                .build();
    }

    public static void initGoldenRail() {
        BlockTypes.GOLDEN_RAIL = AllayBlockType
                .builder(BlockGoldenRailBehaviorImpl.class)
                .vanillaBlock(BlockId.GOLDEN_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockPoweredRailBaseComponentImpl::new)
                .build();
    }

    public static void initDetectorRail() {
        BlockTypes.DETECTOR_RAIL = AllayBlockType
                .builder(BlockDetectorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.DETECTOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockDetectorRailBaseComponentImpl::new)
                .build();
    }

    public static void initActivatorRail() {
        BlockTypes.ACTIVATOR_RAIL = AllayBlockType
                .builder(BlockActivatorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockPoweredRailBaseComponentImpl::new)
                .build();
    }

    public static void initNetherWart() {
        BlockTypes.NETHER_WART = AllayBlockType
                .builder(BlockNetherWartBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_WART)
                .setProperties(BlockPropertyTypes.AGE_4)
                .setBaseComponentSupplier(BlockNetherWartBaseComponentImpl::new)
                .build();
    }

    public static void initMushroom() {
        BlockTypes.BROWN_MUSHROOM = AllayBlockType
                .builder(BlockBrownMushroomBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM)
                .setBaseComponentSupplier(bt -> new BlockMushroomBaseComponentImpl(bt, HugeBrownMushroomFeature.IDENTIFIER))
                .build();
        BlockTypes.RED_MUSHROOM = AllayBlockType
                .builder(BlockRedMushroomBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_MUSHROOM)
                .setBaseComponentSupplier(bt -> new BlockMushroomBaseComponentImpl(bt, HugeRedMushroomFeature.IDENTIFIER))
                .build();
    }

    public static void initFungus() {
        BlockTypes.CRIMSON_FUNGUS = AllayBlockType
                .builder(BlockCrimsonFungusBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                .setBaseComponentSupplier(bt -> new BlockFungusBaseComponentImpl(bt, BlockId.CRIMSON_NYLIUM, HugeFungusFeature.CRIMSON_IDENTIFIER))
                .build();
        BlockTypes.WARPED_FUNGUS = AllayBlockType
                .builder(BlockWarpedFungusBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_FUNGUS)
                .setBaseComponentSupplier(bt -> new BlockFungusBaseComponentImpl(bt, BlockId.WARPED_NYLIUM, HugeFungusFeature.WARPED_IDENTIFIER))
                .build();
    }

    public static void initSweetBerryBush() {
        BlockTypes.SWEET_BERRY_BUSH = AllayBlockType
                .builder(BlockSweetBerryBushBehaviorImpl.class)
                .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockSweetBerryBushBaseComponentImpl::new)
                .build();
    }

    public static void initCocoa() {
        BlockTypes.COCOA = AllayBlockType
                .builder(BlockCocoaBehaviorImpl.class)
                .vanillaBlock(BlockId.COCOA)
                .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockCocoaBaseComponentImpl::new)
                .build();
    }

    public static void initSeaPickle() {
        BlockTypes.SEA_PICKLE = AllayBlockType
                .builder(BlockSeaPickleBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_PICKLE)
                .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                .setBaseComponentSupplier(BlockSeaPickleBaseComponentImpl::new)
                .build();
    }

    public static void initBamboo() {
        BlockTypes.BAMBOO_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(BlockBambooSaplingBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO = AllayBlockType
                .builder(BlockBambooBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO)
                .setProperties(BlockPropertyTypes.AGE_BIT, BlockPropertyTypes.BAMBOO_LEAF_SIZE, BlockPropertyTypes.BAMBOO_STALK_THICKNESS)
                .setBaseComponentSupplier(BlockBambooBaseComponentImpl::new)
                .build();
    }

    public static void initKelp() {
        BlockTypes.KELP = AllayBlockType
                .builder(BlockKelpBehaviorImpl.class)
                .vanillaBlock(BlockId.KELP)
                .setProperties(BlockPropertyTypes.KELP_AGE)
                .setBaseComponentSupplier(BlockKelpBaseComponentImpl::new)
                .build();
    }

    public static void initSeagrass() {
        BlockTypes.SEAGRASS = AllayBlockType
                .builder(BlockSeagrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SEAGRASS)
                .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                .setBaseComponentSupplier(BlockSeagrassBaseComponentImpl::new)
                .build();
    }

    public static void initCaveVines() {
        BlockTypes.CAVE_VINES = AllayBlockType
                .builder(BlockCaveVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .setBaseComponentSupplier(bt -> new BlockCaveVinesBaseComponentImpl(bt, false, true))
                .build();
        BlockTypes.CAVE_VINES_BODY_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesBodyWithBerriesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES_BODY_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .setBaseComponentSupplier(bt -> new BlockCaveVinesBaseComponentImpl(bt, true, false))
                .build();
        BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesHeadWithBerriesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES_HEAD_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .setBaseComponentSupplier(bt -> new BlockCaveVinesBaseComponentImpl(bt, true, true))
                .build();
    }
}
