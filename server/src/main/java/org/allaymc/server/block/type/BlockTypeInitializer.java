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
import org.allaymc.server.block.component.amethyst.BlockAmethystClusterBaseComponentImpl;
import org.allaymc.server.block.component.amethyst.BlockBuddingAmethystBaseComponentImpl;
import org.allaymc.server.block.component.bamboo.BlockBambooBaseComponentImpl;
import org.allaymc.server.block.component.bamboo.BlockBambooSaplingBaseComponentImpl;
import org.allaymc.server.block.component.banner.BlockStandingBannerBaseComponentImpl;
import org.allaymc.server.block.component.banner.BlockWallBannerBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockButtonBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockWoodenButtonBaseComponentImpl;
import org.allaymc.server.block.component.campfire.BlockCampfireBaseComponentImpl;
import org.allaymc.server.block.component.campfire.BlockSoulCampfireBaseComponentImpl;
import org.allaymc.server.block.component.candle.BlockCandleBaseComponentImpl;
import org.allaymc.server.block.component.candle.BlockCandleCakeBaseComponentImpl;
import org.allaymc.server.block.component.chorus.BlockChorusFlowerBaseComponentImpl;
import org.allaymc.server.block.component.chorus.BlockChorusPlantBaseComponentImpl;
import org.allaymc.server.block.component.container.*;
import org.allaymc.server.block.component.crops.*;
import org.allaymc.server.block.component.door.BlockCopperDoorBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockDoorBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockIronDoorBaseComponentImpl;
import org.allaymc.server.block.component.dripleaf.BlockBigDripleafBaseComponentImpl;
import org.allaymc.server.block.component.dripleaf.BlockSmallDripleafBaseComponentImpl;
import org.allaymc.server.block.component.fallable.*;
import org.allaymc.server.block.component.fire.BlockFireBaseComponentImpl;
import org.allaymc.server.block.component.fire.BlockSoulFireBaseComponentImpl;
import org.allaymc.server.block.component.flower.BlockBigFlowerBaseComponentImpl;
import org.allaymc.server.block.component.flower.BlockSmallFlowerBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockDryGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockShortGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockTallGrassBaseComponentImpl;
import org.allaymc.server.block.component.ice.BlockHardIceBaseComponentImpl;
import org.allaymc.server.block.component.ice.BlockIceBaseComponentImpl;
import org.allaymc.server.block.component.liquid.BlockLavaBaseComponentImpl;
import org.allaymc.server.block.component.liquid.BlockWaterBaseComponentImpl;
import org.allaymc.server.block.component.multiface.BlockMultiFaceBaseComponentImpl;
import org.allaymc.server.block.component.multiface.BlockResinClumpBaseComponentImpl;
import org.allaymc.server.block.component.ore.BlockOreBaseComponentImpl;
import org.allaymc.server.block.component.ore.BlockRedstoneOreBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockPistonArmCollisionBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockPistonBaseComponentImpl;
import org.allaymc.server.block.component.piston.BlockStickyPistonBaseComponentImpl;
import org.allaymc.server.block.component.plant.*;
import org.allaymc.server.block.component.portal.BlockEndPortalBaseComponentImpl;
import org.allaymc.server.block.component.portal.BlockEndPortalFrameBaseComponentImpl;
import org.allaymc.server.block.component.portal.BlockPortalBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockHeavyWeightedPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockLightWeightedPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.pressureplate.BlockPressurePlateBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockDetectorRailBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockNormalRailBaseComponentImpl;
import org.allaymc.server.block.component.rail.BlockPoweredRailBaseComponentImpl;
import org.allaymc.server.block.component.redstone.*;
import org.allaymc.server.block.component.respawnpoint.BlockBedRespawnPointComponentImpl;
import org.allaymc.server.block.component.respawnpoint.BlockRespawnAnchorRespawnPointComponentImpl;
import org.allaymc.server.block.component.sapling.BlockMangrovePropaguleBaseComponentImpl;
import org.allaymc.server.block.component.sapling.BlockSaplingBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockHangingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockStandingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockWallSignBaseComponentImpl;
import org.allaymc.server.block.component.slab.BlockDoubleSlabBaseComponentImpl;
import org.allaymc.server.block.component.slab.BlockSlabBaseComponentImpl;
import org.allaymc.server.block.component.snow.BlockPowderSnowBaseComponentImpl;
import org.allaymc.server.block.component.snow.BlockSnowBaseComponentImpl;
import org.allaymc.server.block.component.snow.BlockSnowLayerBaseComponentImpl;
import org.allaymc.server.block.component.sponge.BlockSpongeBaseComponentImpl;
import org.allaymc.server.block.component.sponge.BlockWetSpongeBaseComponentImpl;
import org.allaymc.server.block.component.trapdoor.BlockIronTrapdoorBaseComponentImpl;
import org.allaymc.server.block.component.trapdoor.BlockTrapdoorBaseComponentImpl;
import org.allaymc.server.block.component.tripwire.BlockTripWireBaseComponentImpl;
import org.allaymc.server.block.component.tripwire.BlockTripwireHookBaseComponentImpl;
import org.allaymc.server.block.component.vine.*;
import org.allaymc.server.block.component.workstation.*;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.block.impl.*;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.world.feature.fungus.HugeFungusFeature;
import org.allaymc.server.world.feature.mushroom.HugeBrownMushroomFeature;
import org.allaymc.server.world.feature.mushroom.HugeRedMushroomFeature;
import org.allaymc.server.world.feature.tree.*;

import java.time.Duration;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * {@code BlockTypeInitializer} assembles the handwritten vanilla block families and
 * publishes their {@code BlockTypes} references. Family methods configure property
 * domains, base behavior, optional block entities, and extra components through
 * {@code AllayBlockType}. Factories that finish with {@code build} register their
 * result, while builder helpers leave registration to the caller. Vanilla metadata,
 * default-state hashes, and corresponding item types must be available before
 * construction begins.
 *
 * <p>
 * Related block types can refer to counterparts that are assigned later in the same
 * startup sequence. Copper-family resolvers read the public fields when invoked
 * rather than capturing their initial values. Shape overrides are also lazy so
 * state lookup and behavior exist before geometry is computed. Door geometry
 * combines facing, open state, and hinge side into one edge selection; using only
 * facing would give the same box to states whose panels occupy different edges.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    /**
     * Registers the amethyst family and assigns its {@code BlockTypes} references.
     * The budding block receives budding behavior, while buds and the cluster share
     * face-oriented cluster construction.
     */
    public static void initAmethyst() {
        BlockTypes.BUDDING_AMETHYST = AllayBlockType
                .builder(BlockBuddingAmethystBehaviorImpl.class)
                .vanillaBlock(BlockId.BUDDING_AMETHYST)
                .setBaseComponentSupplier(BlockBuddingAmethystBaseComponentImpl::new)
                .build();
        BlockTypes.SMALL_AMETHYST_BUD = buildAmethystCluster(BlockId.SMALL_AMETHYST_BUD);
        BlockTypes.MEDIUM_AMETHYST_BUD = buildAmethystCluster(BlockId.MEDIUM_AMETHYST_BUD);
        BlockTypes.LARGE_AMETHYST_BUD = buildAmethystCluster(BlockId.LARGE_AMETHYST_BUD);
        BlockTypes.AMETHYST_CLUSTER = buildAmethystCluster(BlockId.AMETHYST_CLUSTER);
    }

    /**
     * Builds and registers an amethyst bud or cluster with the block-face property
     * domain. The shared cluster base component receives the newly constructed type
     * before behavior assembly completes.
     */
    private static BlockType<BlockAmethystClusterBehavior> buildAmethystCluster(BlockId blockId) {
        return AllayBlockType
                .builder(BlockAmethystClusterBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .setBaseComponentSupplier(BlockAmethystClusterBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the copper grate family and assigns its {@code BlockTypes}
     * references. Every oxidation and wax variant receives a resolver that selects
     * counterpart fields when oxidation behavior requests them.
     */
    public static void initCopperGrate() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a copper grate with the supplied oxidation level and
     * counterpart resolver. The resolver remains available to oxidation behavior
     * after the family fields have been initialized.
     */
    private static BlockType<BlockCopperGrateBehavior> buildCopperGrate(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperGrateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Registers the copper bulb family and assigns its {@code BlockTypes}
     * references. The variants expose lit and powered properties, with oxidation
     * and wax transitions resolved through their shared counterpart function.
     */
    public static void initCopperBulb() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a copper bulb with lit and powered properties. The
     * supplied oxidation level and counterpart resolver attach to the same type
     * through its oxidation component.
     */
    private static BlockType<BlockCopperBulbBehavior> buildCopperBulb(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperBulbBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCopperBulbBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    /**
     * Registers the copper family and assigns its {@code BlockTypes} references.
     * Ordinary, chiseled, and cut copper each receive a separate resolver so
     * oxidation keeps the original shape family.
     */
    public static void initCopper() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a copper-family block with the supplied oxidation level.
     * The retained resolver determines the corresponding oxidation and wax variant
     * when the oxidation component requests a transition.
     */
    private static BlockType<BlockCopperBehavior> buildCopper(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperBehaviorImpl.class)
                .vanillaBlock(blockId)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Registers the clay family and assigns its {@code BlockTypes} references. The
     * configured clay behavior uses its specialized base component, with state
     * metadata obtained from the vanilla data registry.
     */
    public static void initClay() {
        BlockTypes.CLAY = AllayBlockType
                .builder(BlockClayBehaviorImpl.class)
                .vanillaBlock(BlockId.CLAY)
                .setBaseComponentSupplier(BlockClayBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the carpets family and assigns its {@code BlockTypes} references.
     * Most variants use ordinary carpet construction; pale moss carpet additionally
     * enumerates side-coverage properties and the upper-block flag.
     */
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
        BlockTypes.MOSS_CARPET = buildCarpet(BlockId.MOSS_CARPET);
        BlockTypes.PALE_MOSS_CARPET = AllayBlockType
                .builder(BlockCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.PALE_MOSS_CARPET)
                .setProperties(BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_EAST, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_NORTH, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_SOUTH, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_WEST, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(BlockCarpetBaseComponentImpl::new)
                .build();
    }

    /**
     * Builds and registers an ordinary carpet using its specialized base component.
     * The supplied vanilla identifier selects the metadata, item association, and
     * default state used during construction.
     */
    private static BlockType<BlockCarpetBehavior> buildCarpet(BlockId blockId) {
        return AllayBlockType
                .builder(BlockCarpetBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCarpetBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the jukebox family and assigns its {@code BlockTypes} references.
     * The type binds the jukebox block entity and specialized base component so its
     * behavior shares the configured entity holder.
     */
    public static void initJukebox() {
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehaviorImpl.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .bindBlockEntity(BlockEntityTypes.JUKEBOX)
                .setBaseComponentSupplier(BlockJukeboxBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the banner family and assigns its {@code BlockTypes} references.
     * Standing banners enumerate ground rotation, while wall banners enumerate
     * facing; both forms bind the banner block entity.
     */
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

    /**
     * Registers the leaves family and assigns its {@code BlockTypes} references.
     * Species select their configured sapling drop, with apple eligibility and the
     * {@code dropMoreSaplings} divisor option chosen separately for each variant.
     */
    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = buildLeaves(BlockId.ACACIA_LEAVES, ItemId.ACACIA_SAPLING);
        BlockTypes.AZALEA_LEAVES = buildLeaves(BlockId.AZALEA_LEAVES, ItemId.AZALEA);
        BlockTypes.AZALEA_LEAVES_FLOWERED = buildLeaves(BlockId.AZALEA_LEAVES_FLOWERED, ItemId.FLOWERING_AZALEA);
        BlockTypes.BIRCH_LEAVES = buildLeaves(BlockId.BIRCH_LEAVES, ItemId.BIRCH_SAPLING);
        BlockTypes.CHERRY_LEAVES = buildLeaves(BlockId.CHERRY_LEAVES, ItemId.CHERRY_SAPLING);
        BlockTypes.DARK_OAK_LEAVES = buildLeaves(BlockId.DARK_OAK_LEAVES, ItemId.DARK_OAK_SAPLING);
        BlockTypes.JUNGLE_LEAVES = buildLeaves(BlockId.JUNGLE_LEAVES, ItemId.JUNGLE_SAPLING, false, true);
        BlockTypes.MANGROVE_LEAVES = buildLeaves(BlockId.MANGROVE_LEAVES, null);
        BlockTypes.OAK_LEAVES = buildLeaves(BlockId.OAK_LEAVES, ItemId.OAK_SAPLING, true, false);
        BlockTypes.SPRUCE_LEAVES = buildLeaves(BlockId.SPRUCE_LEAVES, ItemId.SPRUCE_SAPLING);
        BlockTypes.PALE_OAK_LEAVES = buildLeaves(BlockId.PALE_OAK_LEAVES, ItemId.PALE_OAK_SAPLING);
    }

    /**
     * Builds and registers leaves using the supplied sapling-drop identifier and
     * ordinary drop flags. {@code canDropApple} and {@code dropMoreSaplings} are
     * disabled, while a {@code null} sapling identifier leaves that drop absent.
     */
    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemId saplingId) {
        return buildLeaves(id, saplingId, false, false);
    }

    /**
     * Builds and registers leaves with persistence and update properties and the
     * supplied drop options. The sapling identifier may be {@code null}; {@code
     * canDropApple} and {@code dropMoreSaplings} are retained by the specialized
     * leaves base component.
     */
    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemId saplingId, boolean canDropApple, boolean dropMoreSaplings) {
        return AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockLeavesBaseComponentImpl(blockType, saplingId, canDropApple, dropMoreSaplings))
                .build();
    }

    /**
     * Registers the dragon egg family and assigns its {@code BlockTypes}
     * references. The type combines its specialized base component with falling
     * behavior configured to use the stone landing sound.
     */
    public static void initDragonEgg() {
        BlockTypes.DRAGON_EGG = AllayBlockType
                .builder(BlockDragonEggBehaviorImpl.class)
                .vanillaBlock(BlockId.DRAGON_EGG)
                .setBaseComponentSupplier(BlockDragonEggBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_STONE))
                .build();
    }

    /**
     * Registers the fallable family and assigns its {@code BlockTypes} references.
     * Sand and gravel use their landing sounds, while suspicious variants also
     * enumerate brushing progress and hanging state.
     */
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
        BlockTypes.SUSPICIOUS_SAND = AllayBlockType
                .builder(BlockSuspiciousSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SUSPICIOUS_SAND)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockSuspiciousBaseComponentImpl::new)
                .addComponent(new BlockSuspiciousFallableComponentImpl(SoundNames.BREAK_SUSPICIOUS_SAND))
                .build();
        BlockTypes.SUSPICIOUS_GRAVEL = AllayBlockType
                .builder(BlockSuspiciousGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.SUSPICIOUS_GRAVEL)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockSuspiciousBaseComponentImpl::new)
                .addComponent(new BlockSuspiciousFallableComponentImpl(SoundNames.BREAK_SUSPICIOUS_GRAVEL))
                .build();
    }

    /**
     * Registers the shulker box family and assigns its {@code BlockTypes}
     * references. Every color and the undyed variant use the same specialized base
     * component and bind the shulker-box block entity.
     */
    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = buildShulkerBox(BlockId.YELLOW_SHULKER_BOX);
        BlockTypes.WHITE_SHULKER_BOX = buildShulkerBox(BlockId.WHITE_SHULKER_BOX);
        BlockTypes.UNDYED_SHULKER_BOX = buildShulkerBox(BlockId.UNDYED_SHULKER_BOX);
        BlockTypes.RED_SHULKER_BOX = buildShulkerBox(BlockId.RED_SHULKER_BOX);
        BlockTypes.PURPLE_SHULKER_BOX = buildShulkerBox(BlockId.PURPLE_SHULKER_BOX);
        BlockTypes.PINK_SHULKER_BOX = buildShulkerBox(BlockId.PINK_SHULKER_BOX);
        BlockTypes.ORANGE_SHULKER_BOX = buildShulkerBox(BlockId.ORANGE_SHULKER_BOX);
        BlockTypes.MAGENTA_SHULKER_BOX = buildShulkerBox(BlockId.MAGENTA_SHULKER_BOX);
        BlockTypes.LIME_SHULKER_BOX = buildShulkerBox(BlockId.LIME_SHULKER_BOX);
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = buildShulkerBox(BlockId.LIGHT_GRAY_SHULKER_BOX);
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = buildShulkerBox(BlockId.LIGHT_BLUE_SHULKER_BOX);
        BlockTypes.GREEN_SHULKER_BOX = buildShulkerBox(BlockId.GREEN_SHULKER_BOX);
        BlockTypes.GRAY_SHULKER_BOX = buildShulkerBox(BlockId.GRAY_SHULKER_BOX);
        BlockTypes.CYAN_SHULKER_BOX = buildShulkerBox(BlockId.CYAN_SHULKER_BOX);
        BlockTypes.BROWN_SHULKER_BOX = buildShulkerBox(BlockId.BROWN_SHULKER_BOX);
        BlockTypes.BLUE_SHULKER_BOX = buildShulkerBox(BlockId.BLUE_SHULKER_BOX);
        BlockTypes.BLACK_SHULKER_BOX = buildShulkerBox(BlockId.BLACK_SHULKER_BOX);
    }

    /**
     * Builds and registers a shulker-box variant with its specialized base
     * component. The holder binds the common shulker-box entity while the supplied
     * identifier selects that variant's vanilla data.
     */
    private static BlockType<BlockShulkerBoxBehavior> buildShulkerBox(BlockId blockId) {
        return AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(blockId)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the grass block family and assigns its {@code BlockTypes}
     * references. The type combines the grass behavior implementation with its
     * specialized base component while retaining registry-provided vanilla
     * metadata.
     */
    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .setBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the moss block family and assigns its {@code BlockTypes}
     * references. Its base component receives weighted vegetation choices through
     * deferred block suppliers, allowing referenced vegetation fields to be
     * assigned later.
     */
    public static void initMossBlock() {
        BlockTypes.MOSS_BLOCK = AllayBlockType
                .builder(BlockMossBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSS_BLOCK)
                .setBaseComponentSupplier(blockType -> new BlockMossBlockBaseComponentImpl(blockType, List.of(
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(50, () -> BlockTypes.SHORT_GRASS, false),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(25, () -> BlockTypes.MOSS_CARPET, false),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(10, () -> BlockTypes.TALL_GRASS, true),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(7, () -> BlockTypes.AZALEA, false),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(4, () -> BlockTypes.FLOWERING_AZALEA, false)
                )))
                .build();
    }

    /**
     * Registers the pale moss block family and assigns its {@code BlockTypes}
     * references. Its weighted vegetation table uses pale moss carpet alongside
     * short and tall grass, retaining deferred lookups for those choices.
     */
    public static void initPaleMossBlock() {
        BlockTypes.PALE_MOSS_BLOCK = AllayBlockType
                .builder(BlockPaleMossBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.PALE_MOSS_BLOCK)
                .setBaseComponentSupplier(type -> new BlockMossBlockBaseComponentImpl(type, List.of(
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(25, () -> BlockTypes.SHORT_GRASS, false),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(25, () -> BlockTypes.PALE_MOSS_CARPET, false),
                        new BlockMossBlockBaseComponentImpl.VegetationEntry(10, () -> BlockTypes.TALL_GRASS, true)
                )))
                .build();
    }

    /**
     * Registers the short grass family and assigns its {@code BlockTypes}
     * references. Short grass and fern share the grass base component, while short
     * dry grass uses the dry-grass implementation.
     */
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

    /**
     * Registers the tall grass family and assigns its {@code BlockTypes}
     * references. Tall grass and large fern enumerate upper-block state, while tall
     * dry grass uses the single-block dry-grass implementation.
     */
    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockType -> new BlockTallGrassBaseComponentImpl(blockType, ItemId.SHORT_GRASS))
                .build();
        // Tall dry grass uses the single-block dry-grass component; giving it
        // upper-block state would incorrectly create the two-part grass domain.
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

    /**
     * Registers the barrel family and assigns its {@code BlockTypes} references.
     * The type enumerates facing and open state and binds a barrel entity before
     * its specialized base behavior is assembled.
     */
    public static void initBarrel() {
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehaviorImpl.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .bindBlockEntity(BlockEntityTypes.BARREL)
                .setBaseComponentSupplier(BlockBarrelBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the chest family and assigns its {@code BlockTypes} references.
     * Normal and trapped chests share cardinal-direction properties, chest block
     * entities, and the same specialized chest base component.
     */
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

    /**
     * Registers the hopper family and assigns its {@code BlockTypes} references.
     * The type enumerates facing and toggle state and binds the hopper entity
     * alongside its specialized base component.
     */
    public static void initHopper() {
        BlockTypes.HOPPER = AllayBlockType
                .builder(BlockHopperBehaviorImpl.class)
                .vanillaBlock(BlockId.HOPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .bindBlockEntity(BlockEntityTypes.HOPPER)
                .setBaseComponentSupplier(BlockHopperBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the dispenser family and assigns its {@code BlockTypes} references.
     * The type enumerates facing and triggered state and binds the dispenser entity
     * alongside its specialized base component.
     */
    public static void initDispenser() {
        BlockTypes.DISPENSER = AllayBlockType
                .builder(BlockDispenserBehaviorImpl.class)
                .vanillaBlock(BlockId.DISPENSER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .bindBlockEntity(BlockEntityTypes.DISPENSER)
                .setBaseComponentSupplier(BlockDispenserBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the dropper family and assigns its {@code BlockTypes} references.
     * The type enumerates facing and triggered state and binds the dropper entity
     * alongside its specialized base component.
     */
    public static void initDropper() {
        BlockTypes.DROPPER = AllayBlockType
                .builder(BlockDropperBehaviorImpl.class)
                .vanillaBlock(BlockId.DROPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .bindBlockEntity(BlockEntityTypes.DROPPER)
                .setBaseComponentSupplier(BlockDropperBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the crafting table family and assigns its {@code BlockTypes}
     * references. The type installs the crafting-table base component without
     * adding property domains or a block-entity holder in this configuration.
     */
    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .setBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the stairs family and assigns its {@code BlockTypes} references.
     * Ordinary stairs share orientation and upside-down properties, while copper
     * stairs additionally retain oxidation-level and wax counterpart resolution.
     */
    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = buildStairs(BlockId.ACACIA_STAIRS);
        BlockTypes.ANDESITE_STAIRS = buildStairs(BlockId.ANDESITE_STAIRS);
        BlockTypes.BAMBOO_MOSAIC_STAIRS = buildStairs(BlockId.BAMBOO_MOSAIC_STAIRS);
        BlockTypes.BAMBOO_STAIRS = buildStairs(BlockId.BAMBOO_STAIRS);
        BlockTypes.BIRCH_STAIRS = buildStairs(BlockId.BIRCH_STAIRS);
        BlockTypes.BLACKSTONE_STAIRS = buildStairs(BlockId.BLACKSTONE_STAIRS);
        BlockTypes.BRICK_STAIRS = buildStairs(BlockId.BRICK_STAIRS);
        BlockTypes.CHERRY_STAIRS = buildStairs(BlockId.CHERRY_STAIRS);
        BlockTypes.CINNABAR_BRICK_STAIRS = buildStairs(BlockId.CINNABAR_BRICK_STAIRS);
        BlockTypes.CINNABAR_STAIRS = buildStairs(BlockId.CINNABAR_STAIRS);
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
        BlockTypes.POLISHED_CINNABAR_STAIRS = buildStairs(BlockId.POLISHED_CINNABAR_STAIRS);
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = buildStairs(BlockId.POLISHED_DEEPSLATE_STAIRS);
        BlockTypes.POLISHED_DIORITE_STAIRS = buildStairs(BlockId.POLISHED_DIORITE_STAIRS);
        BlockTypes.POLISHED_GRANITE_STAIRS = buildStairs(BlockId.POLISHED_GRANITE_STAIRS);
        BlockTypes.POLISHED_SULFUR_STAIRS = buildStairs(BlockId.POLISHED_SULFUR_STAIRS);
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
        BlockTypes.SULFUR_BRICK_STAIRS = buildStairs(BlockId.SULFUR_BRICK_STAIRS);
        BlockTypes.SULFUR_STAIRS = buildStairs(BlockId.SULFUR_STAIRS);
        BlockTypes.TUFF_BRICK_STAIRS = buildStairs(BlockId.TUFF_BRICK_STAIRS);
        BlockTypes.TUFF_STAIRS = buildStairs(BlockId.TUFF_STAIRS);
        BlockTypes.WARPED_STAIRS = buildStairs(BlockId.WARPED_STAIRS);
        BlockTypes.PALE_OAK_STAIRS = buildStairs(BlockId.PALE_OAK_STAIRS);

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers ordinary stairs with the shared orientation
     * configuration. The returned type is already published by the builder and
     * exposes upside-down and direction properties through stair behavior.
     */
    private static BlockType<BlockStairsBehavior> buildStairs(BlockId id) {
        return stairsBuilder(BlockStairsBehaviorImpl.class, id).build();
    }

    /**
     * Builds and registers copper stairs with shared stair properties and the
     * supplied oxidation component. The retained counterpart resolver keeps
     * oxidation and wax transitions within the configured stair family.
     */
    private static BlockType<BlockCopperStairsBehavior> buildCopperStairs(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return stairsBuilder(BlockCopperStairsBehaviorImpl.class, id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Creates an unbuilt vanilla stair builder with upside-down and direction
     * properties. The caller can add family-specific components before building and
     * registering the type with its shared stair base behavior.
     */
    private static <T extends BlockBehavior> AllayBlockType.Builder stairsBuilder(Class<T> clazz, BlockId id) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .setBaseComponentSupplier(BlockStairsBaseComponentImpl::new);
    }

    /**
     * Registers the slab family and assigns its {@code BlockTypes} references. Each
     * single slab retains its configured double-slab identifier; copper variants
     * additionally receive their oxidation and wax resolver.
     */
    public static void initSlab() {
        BlockTypes.ACACIA_SLAB = buildSlab(BlockId.ACACIA_SLAB, BlockId.ACACIA_DOUBLE_SLAB);
        BlockTypes.ANDESITE_SLAB = buildSlab(BlockId.ANDESITE_SLAB, BlockId.ANDESITE_DOUBLE_SLAB);
        BlockTypes.BAMBOO_MOSAIC_SLAB = buildSlab(BlockId.BAMBOO_MOSAIC_SLAB, BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB);
        BlockTypes.BAMBOO_SLAB = buildSlab(BlockId.BAMBOO_SLAB, BlockId.BAMBOO_DOUBLE_SLAB);
        BlockTypes.BIRCH_SLAB = buildSlab(BlockId.BIRCH_SLAB, BlockId.BIRCH_DOUBLE_SLAB);
        BlockTypes.BLACKSTONE_SLAB = buildSlab(BlockId.BLACKSTONE_SLAB, BlockId.BLACKSTONE_DOUBLE_SLAB);
        BlockTypes.BRICK_SLAB = buildSlab(BlockId.BRICK_SLAB, BlockId.BRICK_DOUBLE_SLAB);
        BlockTypes.CHERRY_SLAB = buildSlab(BlockId.CHERRY_SLAB, BlockId.CHERRY_DOUBLE_SLAB);
        BlockTypes.CINNABAR_BRICK_SLAB = buildSlab(BlockId.CINNABAR_BRICK_SLAB, BlockId.CINNABAR_BRICK_DOUBLE_SLAB);
        BlockTypes.CINNABAR_SLAB = buildSlab(BlockId.CINNABAR_SLAB, BlockId.CINNABAR_DOUBLE_SLAB);
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
        BlockTypes.POLISHED_CINNABAR_SLAB = buildSlab(BlockId.POLISHED_CINNABAR_SLAB, BlockId.POLISHED_CINNABAR_DOUBLE_SLAB);
        BlockTypes.POLISHED_DEEPSLATE_SLAB = buildSlab(BlockId.POLISHED_DEEPSLATE_SLAB, BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB);
        BlockTypes.POLISHED_DIORITE_SLAB = buildSlab(BlockId.POLISHED_DIORITE_SLAB, BlockId.POLISHED_DIORITE_DOUBLE_SLAB);
        BlockTypes.POLISHED_GRANITE_SLAB = buildSlab(BlockId.POLISHED_GRANITE_SLAB, BlockId.POLISHED_GRANITE_DOUBLE_SLAB);
        BlockTypes.POLISHED_SULFUR_SLAB = buildSlab(BlockId.POLISHED_SULFUR_SLAB, BlockId.POLISHED_SULFUR_DOUBLE_SLAB);
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
        BlockTypes.SULFUR_BRICK_SLAB = buildSlab(BlockId.SULFUR_BRICK_SLAB, BlockId.SULFUR_BRICK_DOUBLE_SLAB);
        BlockTypes.SULFUR_SLAB = buildSlab(BlockId.SULFUR_SLAB, BlockId.SULFUR_DOUBLE_SLAB);
        BlockTypes.TUFF_BRICK_SLAB = buildSlab(BlockId.TUFF_BRICK_SLAB, BlockId.TUFF_BRICK_DOUBLE_SLAB);
        BlockTypes.TUFF_SLAB = buildSlab(BlockId.TUFF_SLAB, BlockId.TUFF_DOUBLE_SLAB);
        BlockTypes.WARPED_SLAB = buildSlab(BlockId.WARPED_SLAB, BlockId.WARPED_DOUBLE_SLAB);

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Registers the double slab family and assigns its {@code BlockTypes}
     * references. Each double slab retains its configured single-slab identifier,
     * sharing the vertical-half domain also used by single slabs.
     */
    public static void initDoubleSlab() {
        BlockTypes.ACACIA_DOUBLE_SLAB = buildDoubleSlab(BlockId.ACACIA_DOUBLE_SLAB, BlockId.ACACIA_SLAB);
        BlockTypes.ANDESITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.ANDESITE_DOUBLE_SLAB, BlockId.ANDESITE_SLAB);
        BlockTypes.BAMBOO_DOUBLE_SLAB = buildDoubleSlab(BlockId.BAMBOO_DOUBLE_SLAB, BlockId.BAMBOO_SLAB);
        BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = buildDoubleSlab(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB, BlockId.BAMBOO_MOSAIC_SLAB);
        BlockTypes.BIRCH_DOUBLE_SLAB = buildDoubleSlab(BlockId.BIRCH_DOUBLE_SLAB, BlockId.BIRCH_SLAB);
        BlockTypes.BLACKSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockId.BLACKSTONE_DOUBLE_SLAB, BlockId.BLACKSTONE_SLAB);
        BlockTypes.BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.BRICK_DOUBLE_SLAB, BlockId.BRICK_SLAB);
        BlockTypes.CHERRY_DOUBLE_SLAB = buildDoubleSlab(BlockId.CHERRY_DOUBLE_SLAB, BlockId.CHERRY_SLAB);
        BlockTypes.CINNABAR_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.CINNABAR_BRICK_DOUBLE_SLAB, BlockId.CINNABAR_BRICK_SLAB);
        BlockTypes.CINNABAR_DOUBLE_SLAB = buildDoubleSlab(BlockId.CINNABAR_DOUBLE_SLAB, BlockId.CINNABAR_SLAB);
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
        BlockTypes.POLISHED_CINNABAR_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_CINNABAR_DOUBLE_SLAB, BlockId.POLISHED_CINNABAR_SLAB);
        BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB, BlockId.POLISHED_DEEPSLATE_SLAB);
        BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_DIORITE_DOUBLE_SLAB, BlockId.POLISHED_DIORITE_SLAB);
        BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_GRANITE_DOUBLE_SLAB, BlockId.POLISHED_GRANITE_SLAB);
        BlockTypes.POLISHED_SULFUR_DOUBLE_SLAB = buildDoubleSlab(BlockId.POLISHED_SULFUR_DOUBLE_SLAB, BlockId.POLISHED_SULFUR_SLAB);
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
        BlockTypes.SULFUR_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.SULFUR_BRICK_DOUBLE_SLAB, BlockId.SULFUR_BRICK_SLAB);
        BlockTypes.SULFUR_DOUBLE_SLAB = buildDoubleSlab(BlockId.SULFUR_DOUBLE_SLAB, BlockId.SULFUR_SLAB);
        BlockTypes.TUFF_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockId.TUFF_BRICK_DOUBLE_SLAB, BlockId.TUFF_BRICK_SLAB);
        BlockTypes.TUFF_DOUBLE_SLAB = buildDoubleSlab(BlockId.TUFF_DOUBLE_SLAB, BlockId.TUFF_SLAB);
        BlockTypes.WARPED_DOUBLE_SLAB = buildDoubleSlab(BlockId.WARPED_DOUBLE_SLAB, BlockId.WARPED_SLAB);

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a single slab retaining the corresponding double-slab
     * identifier. The shared slab builder supplies vertical-half state, while the
     * base component receives the counterpart used for slab combination.
     */
    public static BlockType<BlockSlabBehavior> buildSlab(BlockId id, BlockId doubleSlabId) {
        return slabBuilder(BlockSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockSlabBaseComponentImpl(blockType, doubleSlabId))
                .build();
    }

    /**
     * Builds and registers a copper single slab retaining its double-slab target.
     * The supplied oxidation level and resolver add counterpart lookup alongside
     * the shared vertical-half property and slab base behavior.
     */
    public static BlockType<BlockCopperSlabBehavior> buildCopperSlab(BlockId id, BlockId doubleSlabId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return slabBuilder(BlockCopperSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockSlabBaseComponentImpl(blockType, doubleSlabId))
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Builds and registers a double slab retaining the corresponding single-slab
     * identifier. The type shares the vertical-half domain with single slabs and
     * passes the counterpart to its double-slab base component.
     */
    public static BlockType<BlockDoubleSlabBehavior> buildDoubleSlab(BlockId id, BlockId singleSlabId) {
        return slabBuilder(BlockDoubleSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockDoubleSlabBaseComponentImpl(blockType, singleSlabId))
                .build();
    }

    /**
     * Builds and registers a copper double slab retaining its single-slab
     * counterpart. The supplied oxidation resolver adds family transitions
     * alongside the shared vertical-half property and double-slab base behavior.
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> buildCopperDoubleSlab(BlockId id, BlockId singleSlabId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return slabBuilder(BlockDoubleCopperSlabBehaviorImpl.class, id)
                .setBaseComponentSupplier(blockType -> new BlockDoubleSlabBaseComponentImpl(blockType, singleSlabId))
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Creates an unbuilt vanilla slab builder exposing the vertical-half property.
     * Both single and double slab callers use this domain before selecting their
     * base component and registering the finished type.
     */
    public static <T extends BlockBehavior> AllayBlockType.Builder slabBuilder(Class<T> clazz, BlockId id) {
        // Keep vertical-half state on both slab forms so their constructed
        // combinations match the vanilla state metadata used for default-state
        // lookup.
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF);
    }

    /**
     * Builds and registers a torch variant with the torch-facing property domain.
     * The specialized torch base component is shared across these variants, while
     * vanilla data selects each identifier's metadata.
     */
    private static BlockType<BlockTorchBehavior> buildTorch(BlockId blockId) {
        return AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the torch family and assigns its {@code BlockTypes} references.
     * Ordinary variants share torch-facing properties, while lit and unlit redstone
     * torches use base components with their respective lit flag.
     */
    public static void initTorch() {
        BlockTypes.COLORED_TORCH_RED = buildTorch(BlockId.COLORED_TORCH_RED);
        BlockTypes.COLORED_TORCH_BLUE = buildTorch(BlockId.COLORED_TORCH_BLUE);
        BlockTypes.COLORED_TORCH_GREEN = buildTorch(BlockId.COLORED_TORCH_GREEN);
        BlockTypes.COLORED_TORCH_PURPLE = buildTorch(BlockId.COLORED_TORCH_PURPLE);
        BlockTypes.SOUL_TORCH = buildTorch(BlockId.SOUL_TORCH);
        BlockTypes.TORCH = buildTorch(BlockId.TORCH);
        BlockTypes.UNDERWATER_TORCH = buildTorch(BlockId.UNDERWATER_TORCH);
        BlockTypes.COPPER_TORCH = buildTorch(BlockId.COPPER_TORCH);
        BlockTypes.REDSTONE_TORCH = buildRedstoneTorch(BlockId.REDSTONE_TORCH, true);
        BlockTypes.UNLIT_REDSTONE_TORCH = buildRedstoneTorch(BlockId.UNLIT_REDSTONE_TORCH, false);
    }

    /**
     * Registers the furnace family and assigns its {@code BlockTypes} references.
     * Furnace, blast-furnace, and smoker pairs share cardinal orientation but bind
     * the block-entity type matching each furnace family.
     */
    public static void initFurnace() {
        BlockTypes.FURNACE = buildFurnace(BlockId.FURNACE);
        BlockTypes.LIT_FURNACE = buildFurnace(BlockId.LIT_FURNACE);
        BlockTypes.BLAST_FURNACE = buildBlastFurnace(BlockId.BLAST_FURNACE);
        BlockTypes.LIT_BLAST_FURNACE = buildBlastFurnace(BlockId.LIT_BLAST_FURNACE);
        BlockTypes.SMOKER = buildSmoker(BlockId.SMOKER);
        BlockTypes.LIT_SMOKER = buildSmoker(BlockId.LIT_SMOKER);
    }

    /**
     * Builds and registers a furnace variant with cardinal orientation and its
     * furnace entity holder. Lit and unlit identifiers use the same configuration
     * while retaining their separate vanilla state metadata.
     */
    private static BlockType<BlockFurnaceBehavior> buildFurnace(BlockId blockId) {
        return AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
    }

    /**
     * Builds and registers a blast-furnace variant with cardinal orientation and
     * its blast-furnace entity holder. The identifier selects the lit or unlit
     * vanilla metadata used for the constructed state domain.
     */
    private static BlockType<BlockBlastFurnaceBehavior> buildBlastFurnace(BlockId blockId) {
        return AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
    }

    /**
     * Builds and registers a smoker variant with cardinal orientation and its
     * smoker entity holder. The identifier selects the lit or unlit vanilla
     * metadata used for the constructed state domain.
     */
    private static BlockType<BlockSmokerBehavior> buildSmoker(BlockId blockId) {
        return AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
    }

    /**
     * Registers the woods family and assigns its {@code BlockTypes} references.
     * Wood and log variants enumerate pillar axes and retain explicit stripping
     * targets; already stripped variants target their own identifiers.
     */
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

    /**
     * Builds and registers stripped wood using its own identifier as the stripping
     * target. This preserves the shared pillar-axis and strippable-component
     * configuration without selecting another block as its counterpart.
     */
    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(BlockId blockId) {
        return buildWood(blockId, blockId);
    }

    /**
     * Builds and registers wood with pillar-axis state and the supplied stripping
     * target. The target identifier is retained by the strippable component rather
     * than resolved during this factory call.
     */
    private static <T extends BlockBehavior> BlockType<T> buildWood(BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .addComponent(new BlockStrippableComponentImpl(strippedBlockId))
                .build();
    }

    /**
     * Registers the buttons family and assigns its {@code BlockTypes} references.
     * Wooden variants use wooden-button behavior, while stone and
     * polished-blackstone buttons receive an explicit one-second release duration.
     */
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

    /**
     * Registers the doors family and assigns its {@code BlockTypes} references.
     * Door variants share hinge, facing, open, and upper-block properties, and
     * their lazily computed shape reflects the panel state.
     */
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

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers an ordinary door with shared hinge, facing, open, and
     * upper-block state. The door builder also supplies lazy state-derived geometry
     * before the type is published.
     */
    private static <T extends BlockBehavior> BlockType<T> buildDoor(BlockId blockId) {
        return doorBuilder(BlockDoorBehaviorImpl.class, blockId, BlockDoorBaseComponentImpl::new).build();
    }

    /**
     * Builds and registers a copper door with shared door properties and computed
     * geometry. The supplied oxidation level and counterpart resolver attach
     * oxidation behavior without changing the door-specific base component.
     */
    private static <T extends BlockBehavior> BlockType<T> buildCopperDoor(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return doorBuilder(BlockCopperDoorBehaviorImpl.class, blockId, BlockCopperDoorBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Creates an unbuilt door builder with the supplied base-component factory and
     * common door properties. Its lazy metadata override replaces both collision
     * and selection geometry from the same constructed state after lookup becomes
     * available.
     */
    private static AllayBlockType.Builder doorBuilder(Class<? extends BlockBehavior> clazz, BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                // Derive collision and selection from the same door state after
                // palette registration, keeping both boxes aligned with the panel's
                // open and hinge configuration.
                .addComponent(BlockStateDataComponentImpl.ofRedefinedData((builder, blockType, blockStateHash) -> {
                    var shape = buildDoorCollisionShape(blockType.ofState(blockStateHash));
                    return builder.collisionShape(shape).shape(shape).build();
                }));
    }

    /**
     * Computes the unit-block panel box for a door's facing, open state, and hinge
     * side. The state must expose the shared door property domains. A closed panel
     * selects the edge clockwise from its facing; an open panel instead selects
     * either the facing edge or its opposite according to the hinge bit.
     *
     * <p>
     * This combined edge decision keeps open doors from retaining their closed
     * collision and selection box. The resulting panel spans the block's full
     * height and width along its edge, with thickness measured as three sixteenths
     * of a block.
     */
    private static VoxelShape buildDoorCollisionShape(BlockState doorBlockState) {
        var cardinal = BlockFace.from(doorBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION));
        var open = doorBlockState.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
        var hingeBit = doorBlockState.getPropertyValue(BlockPropertyTypes.DOOR_HINGE_BIT);

        // Facing alone cannot locate an open panel; combine open and hinge state
        // before selecting the block edge occupied by both shapes.
        BlockFace edge;
        if (!open) {
            edge = cardinal.rotateY();
        } else if (!hingeBit) {
            edge = cardinal.opposite();
        } else {
            edge = cardinal;
        }

        // Convert the three-pixel panel thickness into unit-block coordinates.
        var thickness = 3.0 / 16.0;
        return switch (edge) {
            case NORTH -> VoxelShape.builder().solid(0, 0, 0, 1, 1, thickness).build();
            case SOUTH -> VoxelShape.builder().solid(0, 0, 1 - thickness, 1, 1, 1).build();
            case WEST -> VoxelShape.builder().solid(0, 0, 0, thickness, 1, 1).build();
            case EAST -> VoxelShape.builder().solid(1 - thickness, 0, 0, 1, 1, 1).build();
            default -> throw new IllegalStateException("Unexpected edge face: " + edge);
        };
    }

    /**
     * Builds and registers a button using the wooden-button base component. The
     * shared button factory supplies pressed and facing properties, while the
     * selected component supplies the wooden behavior variant.
     */
    private static <T extends BlockBehavior> BlockType<T> buildWoodenButton(BlockId blockId) {
        return buildButton(blockId, BlockWoodenButtonBaseComponentImpl::new);
    }

    /**
     * Builds and registers a button exposing pressed and facing properties. The
     * supplied base-component factory receives the constructed type, allowing
     * wooden and stone configurations to retain their distinct release behavior.
     */
    private static <T extends BlockBehavior> BlockType<T> buildButton(BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    /**
     * Registers the wall signs family and assigns its {@code BlockTypes}
     * references. Each wood variant binds the sign entity and retains the
     * configured sign-item identifier used by its wall-sign base component.
     */
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

    /**
     * Builds and registers a wall sign retaining the configured dropped-item
     * identifier. The type exposes facing state and binds the shared sign entity
     * through the specialized wall-sign base component.
     */
    private static <T extends BlockBehavior> BlockType<T> buildWallSign(BlockId blockId, ItemId dropItemId) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemId))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    /**
     * Registers the standing signs family and assigns its {@code BlockTypes}
     * references. Each variant enumerates ground rotation and retains its
     * configured sign-item identifier while binding the shared sign entity type.
     */
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

    /**
     * Builds and registers a standing sign retaining the configured dropped-item
     * identifier. The type exposes ground-rotation state and binds the shared sign
     * entity through the specialized standing-sign base component.
     */
    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(BlockId blockId, ItemId dropItemId) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemId))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    /**
     * Registers the hanging signs family and assigns its {@code BlockTypes}
     * references. Each variant enumerates attached, facing, ground-rotation, and
     * hanging properties, and binds the hanging-sign entity before behavior
     * assembly.
     */
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

    /**
     * Builds and registers a hanging sign with its dedicated entity holder.
     * Attached, facing, ground-rotation, and hanging properties are supplied to the
     * same specialized hanging-sign base component.
     */
    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }

    /**
     * Registers the slime block family and assigns its {@code BlockTypes}
     * references. The type installs the specialized slime base component while its
     * physical state metadata remains supplied by the vanilla registry.
     */
    public static void initSlimeBlock() {
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehaviorImpl.class)
                .vanillaBlock(BlockId.SLIME)
                .setBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the hay block family and assigns its {@code BlockTypes} references.
     * The type enumerates pillar axis and the deprecated property while using the
     * hay-block base component for its behavior.
     */
    public static void initHayBlock() {
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockHayBlockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the water family and assigns its {@code BlockTypes} references.
     * Still and flowing variants share liquid-depth properties and lazily replace
     * their shape from the effective depth after state construction.
     */
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

    /**
     * Registers the bubble column family and assigns its {@code BlockTypes}
     * references. The type enumerates the drag-down property and uses its
     * specialized base component with registry-provided vanilla state metadata.
     */
    public static void initBubbleColumn() {
        BlockTypes.BUBBLE_COLUMN = AllayBlockType
                .builder(BlockBubbleColumnBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_COLUMN)
                .setProperties(BlockPropertyTypes.DRAG_DOWN)
                .setBaseComponentSupplier(BlockBubbleColumnBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the lava family and assigns its {@code BlockTypes} references.
     * Still and flowing variants share liquid-depth properties and lazily replace
     * their shape from effective depth after state and behavior construction.
     */
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

    /**
     * Builds a unit-footprint shape whose height follows the liquid's effective
     * depth. The state must expose liquid behavior and its depth property; sources
     * and falling states occupy a full block, while other depths use eighth-block
     * increments.
     */
    private static VoxelShape buildLiquidShape(BlockState liquidBlockState) {
        if (!(liquidBlockState.getBehavior() instanceof BlockLiquidBaseComponent)) {
            throw new IllegalArgumentException("The liquidBlockState must implement BlockLiquidBaseComponent!");
        }
        // Convert the eight effective depth units into block-height units.
        return VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.125f * BlockLiquidBaseComponent.getDepth(liquidBlockState), 1)
                .build();
    }

    /**
     * Registers the enchanting table family and assigns its {@code BlockTypes}
     * references. The type binds the enchanting-table entity and specialized base
     * component without adding a separate property domain in this configuration.
     */
    public static void initEnchantingTable() {
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .bindBlockEntity(BlockEntityTypes.ENCHANT_TABLE)
                .setBaseComponentSupplier(BlockEnchantingTableBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the stone family and assigns its {@code BlockTypes} references. The
     * type installs the specialized stone base component while preserving the
     * vanilla metadata and default-state selection configured by its builder.
     */
    public static void initStone() {
        BlockTypes.STONE = AllayBlockType
                .builder(BlockStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE)
                .setBaseComponentSupplier(BlockStoneBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the glass family and assigns its {@code BlockTypes} references.
     * Ordinary, stained, hard, and tinted variants use the same glass construction
     * helper and its specialized glass base component.
     */
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

    /**
     * Registers the glass pane family and assigns its {@code BlockTypes}
     * references. Ordinary and hard pane variants use the pane behavior
     * implementation together with the glass base component and vanilla metadata.
     */
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

    /**
     * Builds and registers a glass variant using the shared glass base component.
     * Its supplied vanilla identifier selects the physical metadata and default
     * state without adding a new property domain here.
     */
    public static BlockType<BlockGlassBehavior> buildGlass(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    /**
     * Builds and registers a pane using the pane behavior implementation and glass
     * base component. Its supplied vanilla identifier selects state metadata
     * without adding a separate pane property domain here.
     */
    public static BlockType<BlockGlassPaneBehavior> buildGlassPane(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassPaneBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the fire family and assigns its {@code BlockTypes} references.
     * Normal and soul fire both enumerate age, but their base component classes
     * distinguish the two configured behavior variants.
     */
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

    /**
     * Registers the trapdoor family and assigns its {@code BlockTypes} references.
     * Wood and copper variants share direction, open, and upside-down properties;
     * iron receives its own base component implementation.
     */
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

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers an ordinary trapdoor with the shared trapdoor
     * configuration. Direction, open, and upside-down properties are enumerated
     * before the specialized base component is attached to the constructed type.
     */
    public static BlockType<BlockTrapdoorBehavior> buildTrapdoor(BlockId blockId) {
        return trapdoorBuilder(BlockTrapdoorBehaviorImpl.class, blockId).build();
    }

    /**
     * Builds and registers a copper trapdoor with shared trapdoor properties and
     * base behavior. The supplied oxidation component retains its level and
     * resolver for later wax and oxidation counterpart lookup.
     */
    public static BlockType<BlockCopperTrapdoorBehavior> buildCopperTrapdoor(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return trapdoorBuilder(BlockCopperTrapdoorBehaviorImpl.class, id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Creates an unbuilt vanilla trapdoor builder with direction, open, and
     * upside-down properties. The caller can add variant components before building
     * and publishing the configured type and its states.
     */
    public static <T extends BlockBehavior> AllayBlockType.Builder trapdoorBuilder(Class<T> clazz, BlockId id) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .setBaseComponentSupplier(BlockTrapdoorBaseComponentImpl::new);
    }

    /**
     * Registers the sponge family and assigns its {@code BlockTypes} references.
     * Dry and wet forms receive their respective specialized base components rather
     * than one component with a shared wet-state property.
     */
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

    /**
     * Registers the farm land family and assigns its {@code BlockTypes} references.
     * The type enumerates moisture amount and installs the farmland base component
     * while retaining the vanilla default-state selection.
     */
    public static void initFarmLand() {
        BlockTypes.FARMLAND = AllayBlockType
                .builder(BlockFarmlandBehaviorImpl.class)
                .vanillaBlock(BlockId.FARMLAND)
                .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                .setBaseComponentSupplier(BlockFarmLandBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the crops family and assigns its {@code BlockTypes} references.
     * Crop types receive their growth domains; stems also retain facing and fruit
     * identifiers, while pitcher crops enumerate upper-block state.
     */
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
        BlockTypes.TORCHFLOWER_CROP = AllayBlockType
                .builder(BlockTorchflowerCropBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCHFLOWER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockTorchflowerCropBaseComponentImpl::new)
                .build();
        BlockTypes.PITCHER_CROP = AllayBlockType
                .builder(BlockPitcherCropBehaviorImpl.class)
                .vanillaBlock(BlockId.PITCHER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(BlockPitcherCropBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the melon block family and assigns its {@code BlockTypes}
     * references. Melons use their specialized base component, while pumpkins
     * additionally enumerate cardinal orientation in their configured state domain.
     */
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

    /**
     * Registers the beacon family and assigns its {@code BlockTypes} references.
     * The type binds the beacon entity and specialized base component without
     * adding a separate property domain in this configuration.
     */
    public static void initBeacon() {
        BlockTypes.BEACON = AllayBlockType
                .builder(BlockBeaconBehaviorImpl.class)
                .vanillaBlock(BlockId.BEACON)
                .bindBlockEntity(BlockEntityTypes.BEACON)
                .setBaseComponentSupplier(BlockBeaconBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the brewing stand family and assigns its {@code BlockTypes}
     * references. The type binds its brewing entity and enumerates the three
     * bottle-slot flags, retaining the default base component configuration.
     */
    public static void initBrewingStand() {
        BlockTypes.BREWING_STAND = AllayBlockType
                .builder(BlockBrewingStandBehaviorImpl.class)
                .vanillaBlock(BlockId.BREWING_STAND)
                .bindBlockEntity(BlockEntityTypes.BREWING_STAND)
                .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                .build();
    }

    /**
     * Registers the tnt family and assigns its {@code BlockTypes} references. The
     * type enumerates its explode flag and uses the TNT base component while
     * obtaining state data from vanilla registry metadata.
     */
    public static void initTnt() {
        BlockTypes.TNT = AllayBlockType
                .builder(BlockTntBehaviorImpl.class)
                .vanillaBlock(BlockId.TNT)
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                .setBaseComponentSupplier(BlockTntBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the ender chest family and assigns its {@code BlockTypes}
     * references. The type binds the ender-chest entity and enumerates cardinal
     * orientation before assembling its specialized base component behavior.
     */
    public static void initEnderChest() {
        BlockTypes.ENDER_CHEST = AllayBlockType
                .builder(BlockEnderChestBehaviorImpl.class)
                .vanillaBlock(BlockId.ENDER_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.ENDER_CHEST)
                .setBaseComponentSupplier(BlockEnderChestBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the reeds family and assigns its {@code BlockTypes} references. The
     * type enumerates sixteen age values and installs reed behavior, with the
     * associated item selected through the block-item mapping.
     */
    public static void initReeds() {
        BlockTypes.REEDS = AllayBlockType
                .builder(BlockReedsBehaviorImpl.class)
                .vanillaBlock(BlockId.REEDS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockReedsBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the cactus family and assigns its {@code BlockTypes} references.
     * The type enumerates sixteen age values and installs the cactus base component
     * while keeping vanilla metadata and default-state selection.
     */
    public static void initCactus() {
        BlockTypes.CACTUS = AllayBlockType
                .builder(BlockCactusBehaviorImpl.class)
                .vanillaBlock(BlockId.CACTUS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .setBaseComponentSupplier(BlockCactusBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the flower pot family and assigns its {@code BlockTypes}
     * references. The type binds the flower-pot entity and enumerates its update
     * flag before assembling the specialized pot base component.
     */
    public static void initFlowerPot() {
        BlockTypes.FLOWER_POT = AllayBlockType
                .builder(BlockFlowerPotBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWER_POT)
                .setProperties(BlockPropertyTypes.UPDATE_BIT)
                .bindBlockEntity(BlockEntityTypes.FLOWER_POT)
                .setBaseComponentSupplier(BlockFlowerPotBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the decorated pot family and assigns its {@code BlockTypes}
     * references. The type binds the decorated-pot entity and enumerates its
     * four-way direction before assembling the specialized decorated-pot base
     * component.
     */
    public static void initDecoratedPot() {
        BlockTypes.DECORATED_POT = AllayBlockType
                .builder(BlockDecoratedPotBehaviorImpl.class)
                .vanillaBlock(BlockId.DECORATED_POT)
                .setProperties(BlockPropertyTypes.DIRECTION_4)
                .bindBlockEntity(BlockEntityTypes.DECORATED_POT)
                .setBaseComponentSupplier(BlockDecoratedPotBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the glazed terracotta family and assigns its {@code BlockTypes}
     * references. Every color uses the shared glazed-terracotta helper, exposing
     * facing properties through the same specialized base component and vanilla
     * data.
     */
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

    /**
     * Builds and registers a glazed-terracotta color with facing state and shared
     * base behavior. The supplied identifier selects that color's associated item,
     * metadata, and registry-defined default state.
     */
    public static BlockType<BlockGlazedTerracottaBehavior> buildGlazedTerracotta(BlockId blockId) {
        return AllayBlockType.builder(BlockGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockGlazedTerracottaBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the stonecutter block family and assigns its {@code BlockTypes}
     * references. The type enumerates cardinal orientation and installs the
     * stonecutter base component while keeping registry-defined metadata and
     * default state.
     */
    public static void initStonecutterBlock() {
        BlockTypes.STONECUTTER_BLOCK = AllayBlockType
                .builder(BlockStonecutterBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockStonecutterBlockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the grindstone family and assigns its {@code BlockTypes}
     * references. The type enumerates attachment and four-way direction, allowing
     * the specialized base component to distinguish its configured placement
     * states.
     */
    public static void initGrindstone() {
        BlockTypes.GRINDSTONE = AllayBlockType
                .builder(BlockGrindstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GRINDSTONE)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockGrindstoneBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the loom family and assigns its {@code BlockTypes} references. The
     * type enumerates four-way direction and installs the loom base component while
     * retaining the registry-defined physical state metadata.
     */
    public static void initLoom() {
        BlockTypes.LOOM = AllayBlockType
                .builder(BlockLoomBehaviorImpl.class)
                .vanillaBlock(BlockId.LOOM)
                .setProperties(BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockLoomBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the anvil family and assigns its {@code BlockTypes} references. All
     * damage forms enumerate cardinal orientation and falling behavior, with
     * explicit next-damage targets ending at the air identifier.
     */
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

    /**
     * Registers the smithing table family and assigns its {@code BlockTypes}
     * references. The type installs the smithing-table base component without
     * adding property domains or a block-entity holder in this configuration.
     */
    public static void initSmithingTable() {
        BlockTypes.SMITHING_TABLE = AllayBlockType
                .builder(BlockSmithingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.SMITHING_TABLE)
                .setBaseComponentSupplier(BlockSmithingTableBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the ore family and assigns its {@code BlockTypes} references.
     * Ordinary ores receive explicit item and experience ranges, while redstone
     * ores retain separate lit and unlit counterpart identifiers.
     */
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
        BlockTypes.REDSTONE_ORE = buildRedstoneOre(BlockId.REDSTONE_ORE, BlockId.LIT_REDSTONE_ORE, BlockId.REDSTONE_ORE);
        BlockTypes.LIT_REDSTONE_ORE = buildRedstoneOre(BlockId.LIT_REDSTONE_ORE, BlockId.LIT_REDSTONE_ORE, BlockId.REDSTONE_ORE);

        BlockTypes.DEEPSLATE_COAL_ORE = buildOre(BlockId.DEEPSLATE_COAL_ORE, ItemId.COAL, 1, 1, 0, 2);
        BlockTypes.DEEPSLATE_COPPER_ORE = buildOre(BlockId.DEEPSLATE_COPPER_ORE, ItemId.RAW_COPPER, 2, 5, 0, 0);
        BlockTypes.DEEPSLATE_DIAMOND_ORE = buildOre(BlockId.DEEPSLATE_DIAMOND_ORE, ItemId.DIAMOND, 1, 1, 0, 2);
        BlockTypes.DEEPSLATE_EMERALD_ORE = buildOre(BlockId.DEEPSLATE_EMERALD_ORE, ItemId.EMERALD, 1, 1, 3, 7);
        BlockTypes.DEEPSLATE_GOLD_ORE = buildOre(BlockId.DEEPSLATE_GOLD_ORE, ItemId.RAW_GOLD, 1, 1, 0, 0);
        BlockTypes.DEEPSLATE_IRON_ORE = buildOre(BlockId.DEEPSLATE_IRON_ORE, ItemId.RAW_IRON, 1, 1, 0, 0);
        BlockTypes.DEEPSLATE_LAPIS_ORE = buildOre(BlockId.DEEPSLATE_LAPIS_ORE, ItemId.LAPIS_LAZULI, 4, 9, 2, 5);
        BlockTypes.DEEPSLATE_REDSTONE_ORE = buildRedstoneOre(BlockId.DEEPSLATE_REDSTONE_ORE, BlockId.LIT_DEEPSLATE_REDSTONE_ORE, BlockId.DEEPSLATE_REDSTONE_ORE);
        BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = buildRedstoneOre(BlockId.LIT_DEEPSLATE_REDSTONE_ORE, BlockId.LIT_DEEPSLATE_REDSTONE_ORE, BlockId.DEEPSLATE_REDSTONE_ORE);
    }

    /**
     * Builds and registers ore with its configured drop item and inclusive count
     * and experience ranges. Each maximum must be at least its minimum, as checked
     * when the base component is constructed.
     */
    public static BlockType<BlockOreBehavior> buildOre(BlockId blockId, ItemId dropItem, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        return AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockOreBaseComponentImpl(type, dropItem, minDropItem, maxDropItem, minDropXp, maxDropXp))
                .build();
    }

    /**
     * Builds and registers a redstone-ore variant with explicit lit and unlit
     * counterpart identifiers. The base component retains those identifiers while
     * vanilla data supplies the current variant's physical metadata.
     */
    private static BlockType<BlockOreBehavior> buildRedstoneOre(BlockId blockId, BlockId litBlockId, BlockId unlitBlockId) {
        return AllayBlockType.builder(BlockOreBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneOreBaseComponentImpl(blockType, litBlockId.getIdentifier(), unlitBlockId.getIdentifier()))
                .build();
    }

    /**
     * Registers the glowstone family and assigns its {@code BlockTypes} references.
     * The type installs the glowstone base component while preserving vanilla state
     * metadata and the default-state selection configured by its builder.
     */
    public static void initGlowstone() {
        BlockTypes.GLOWSTONE = AllayBlockType
                .builder(BlockGlowstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOWSTONE)
                .setBaseComponentSupplier(BlockGlowstoneBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the gilded blackstone family and assigns its {@code BlockTypes}
     * references. The type installs the gilded-blackstone base component while
     * retaining vanilla physical metadata and the configured associated item type.
     */
    public static void initGildedBlackstone() {
        BlockTypes.GILDED_BLACKSTONE = AllayBlockType
                .builder(BlockGildedBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                .setBaseComponentSupplier(BlockGildedBlackstoneBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the concrete powder family and assigns its {@code BlockTypes}
     * references. Each color retains its solid-concrete target and receives both
     * concrete-powder base behavior and its specialized falling component.
     */
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

    /**
     * Builds and registers concrete powder retaining the identifier of its solid
     * counterpart. The type combines its powder base component with specialized
     * falling behavior before registration publishes the result.
     */
    public static BlockType<BlockConcretePowderBehavior> buildConcretePowder(BlockId blockId, BlockId solidBlockId) {
        return AllayBlockType.builder(BlockConcretePowderBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockConcretePowderBaseComponentImpl(type, solidBlockId))
                .addComponent(new BlockConcretePowderFallableComponentImpl())
                .build();
    }

    /**
     * Registers the ladder family and assigns its {@code BlockTypes} references.
     * The type enumerates facing direction and installs the ladder base component
     * while retaining registry-defined metadata and default state.
     */
    public static void initLadder() {
        BlockTypes.LADDER = AllayBlockType
                .builder(BlockLadderBehaviorImpl.class)
                .vanillaBlock(BlockId.LADDER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockLadderBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the magma family and assigns its {@code BlockTypes} references. The
     * type installs the magma base component without adding property domains, while
     * vanilla metadata supplies its constructed state data.
     */
    public static void initMagma() {
        BlockTypes.MAGMA = AllayBlockType
                .builder(BlockMagmaBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGMA)
                .setBaseComponentSupplier(BlockMagmaBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the soul sand family and assigns its {@code BlockTypes} references.
     * The type installs the soul-sand base component without adding property
     * domains, while vanilla metadata supplies its constructed state data.
     */
    public static void initSoulSand() {
        BlockTypes.SOUL_SAND = AllayBlockType
                .builder(BlockSoulSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_SAND)
                .setBaseComponentSupplier(BlockSoulSandBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the infested blocks family and assigns its {@code BlockTypes}
     * references. Each variant retains the block it imitates; infested deepslate
     * additionally enumerates pillar axes instead of using the propertyless helper.
     */
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

    /**
     * Builds and registers an infested block retaining the identifier it imitates.
     * This helper adds no property domains, so variants needing pillar-axis state
     * require their separate construction path.
     */
    public static BlockType<BlockInfestedBlockBehavior> buildInfestedBlock(BlockId blockId, BlockId imitateBlockId) {
        return AllayBlockType.builder(BlockInfestedBlockBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(type -> new BlockInfestedBlockBaseComponentImpl(type, imitateBlockId))
                .build();
    }

    /**
     * Registers the end portal frame family and assigns its {@code BlockTypes}
     * references. The type enumerates eye presence and cardinal orientation and
     * installs the end-portal-frame base component for its configured behavior.
     */
    public static void initEndPortalFrame() {
        BlockTypes.END_PORTAL_FRAME = AllayBlockType
                .builder(BlockEndPortalFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.END_PORTAL_FRAME)
                .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockEndPortalFrameBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the portal family and assigns its {@code BlockTypes} references.
     * The type enumerates portal axis and installs portal behavior while retaining
     * physical metadata and default-state selection from vanilla registries.
     */
    public static void initPortal() {
        BlockTypes.PORTAL = AllayBlockType
                .builder(BlockPortalBehaviorImpl.class)
                .vanillaBlock(BlockId.PORTAL)
                .setProperties(BlockPropertyTypes.PORTAL_AXIS)
                .setBaseComponentSupplier(BlockPortalBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the end portal family and assigns its {@code BlockTypes}
     * references. The type installs end-portal behavior without adding property
     * domains or a block-entity holder in this explicit configuration.
     */
    public static void initEndPortal() {
        BlockTypes.END_PORTAL = AllayBlockType
                .builder(BlockEndPortalBehaviorImpl.class)
                .vanillaBlock(BlockId.END_PORTAL)
                .setBaseComponentSupplier(BlockEndPortalBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the ice family and assigns its {@code BlockTypes} references.
     * Ordinary ice uses its own base component, while packed and blue ice share the
     * hard-ice implementation in this family.
     */
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
        // TODO: Add a frosted-ice registration with its age domain and melting behavior when that implementation is available.
    }

    /**
     * Registers the snow family and assigns its {@code BlockTypes} references. The
     * full snow block uses its specialized base component without adding the height
     * or covered properties used by snow layers.
     */
    public static void initSnow() {
        BlockTypes.SNOW = AllayBlockType
                .builder(BlockSnowBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW)
                .setBaseComponentSupplier(BlockSnowBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the powder snow family and assigns its {@code BlockTypes}
     * references. The type installs the powder-snow base component while its
     * physical and rendering metadata remain supplied by the vanilla registry.
     */
    public static void initPowderSnow() {
        BlockTypes.POWDER_SNOW = AllayBlockType
                .builder(BlockPowderSnowBehaviorImpl.class)
                .vanillaBlock(BlockId.POWDER_SNOW)
                .setBaseComponentSupplier(BlockPowderSnowBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the snow layer family and assigns its {@code BlockTypes}
     * references. The type enumerates height and covered state and adds falling
     * behavior configured with the snow landing sound.
     */
    public static void initSnowLayer() {
        BlockTypes.SNOW_LAYER = AllayBlockType
                .builder(BlockSnowLayerBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW_LAYER)
                .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                .setBaseComponentSupplier(BlockSnowLayerBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.LAND_SNOW))
                .build();
    }

    /**
     * Registers the sea lantern family and assigns its {@code BlockTypes}
     * references. The type installs the sea-lantern base component without adding
     * property domains or a block-entity holder in this configuration.
     */
    public static void initSeaLantern() {
        BlockTypes.SEA_LANTERN = AllayBlockType
                .builder(BlockSeaLanternBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_LANTERN)
                .setBaseComponentSupplier(BlockSeaLanternBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the scaffolding family and assigns its {@code BlockTypes}
     * references. The type enumerates stability and stability-check state and adds
     * falling behavior configured with the scaffolding fall sound.
     */
    public static void initScaffolding() {
        BlockTypes.SCAFFOLDING = AllayBlockType
                .builder(BlockScaffoldingBehaviorImpl.class)
                .vanillaBlock(BlockId.SCAFFOLDING)
                .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                .setBaseComponentSupplier(BlockScaffoldingBaseComponentImpl::new)
                .addComponent(new BlockFallableComponentImpl(SoundNames.BLOCK_SCAFFOLDING_FALL))
                .build();
    }

    /**
     * Registers the chorus flower family and assigns its {@code BlockTypes}
     * references. The type enumerates its six-value age domain and installs the
     * chorus-flower base component while retaining vanilla state data.
     */
    public static void initChorusFlower() {
        BlockTypes.CHORUS_FLOWER = AllayBlockType
                .builder(BlockChorusFlowerBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_FLOWER)
                .setProperties(BlockPropertyTypes.AGE_6)
                .setBaseComponentSupplier(BlockChorusFlowerBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the chorus plant family and assigns its {@code BlockTypes}
     * references. The type installs chorus-plant behavior without adding a property
     * domain here, while vanilla registry data supplies its physical metadata.
     */
    public static void initChorusPlant() {
        BlockTypes.CHORUS_PLANT = AllayBlockType
                .builder(BlockChorusPlantBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_PLANT)
                .setBaseComponentSupplier(BlockChorusPlantBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the dead bush family and assigns its {@code BlockTypes} references.
     * The type installs the dead-bush base component while preserving vanilla state
     * metadata and the builder-selected associated item reference.
     */
    public static void initDeadBush() {
        BlockTypes.DEADBUSH = AllayBlockType
                .builder(BlockDeadbushBehaviorImpl.class)
                .vanillaBlock(BlockId.DEADBUSH)
                .setBaseComponentSupplier(BlockDeadbushBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the walls family and assigns its {@code BlockTypes} references.
     * Every material shares four directional connection domains and a post flag,
     * allowing one wall base component to interpret its states.
     */
    public static void initWalls() {
        BlockTypes.ANDESITE_WALL = buildWall(BlockId.ANDESITE_WALL);
        BlockTypes.BLACKSTONE_WALL = buildWall(BlockId.BLACKSTONE_WALL);
        BlockTypes.BRICK_WALL = buildWall(BlockId.BRICK_WALL);
        BlockTypes.CINNABAR_BRICK_WALL = buildWall(BlockId.CINNABAR_BRICK_WALL);
        BlockTypes.CINNABAR_WALL = buildWall(BlockId.CINNABAR_WALL);
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
        BlockTypes.POLISHED_CINNABAR_WALL = buildWall(BlockId.POLISHED_CINNABAR_WALL);
        BlockTypes.POLISHED_DEEPSLATE_WALL = buildWall(BlockId.POLISHED_DEEPSLATE_WALL);
        BlockTypes.POLISHED_SULFUR_WALL = buildWall(BlockId.POLISHED_SULFUR_WALL);
        BlockTypes.POLISHED_TUFF_WALL = buildWall(BlockId.POLISHED_TUFF_WALL);
        BlockTypes.PRISMARINE_WALL = buildWall(BlockId.PRISMARINE_WALL);
        BlockTypes.RED_NETHER_BRICK_WALL = buildWall(BlockId.RED_NETHER_BRICK_WALL);
        BlockTypes.RED_SANDSTONE_WALL = buildWall(BlockId.RED_SANDSTONE_WALL);
        BlockTypes.RESIN_BRICK_WALL = buildWall(BlockId.RESIN_BRICK_WALL);
        BlockTypes.SANDSTONE_WALL = buildWall(BlockId.SANDSTONE_WALL);
        BlockTypes.STONE_BRICK_WALL = buildWall(BlockId.STONE_BRICK_WALL);
        BlockTypes.SULFUR_BRICK_WALL = buildWall(BlockId.SULFUR_BRICK_WALL);
        BlockTypes.SULFUR_WALL = buildWall(BlockId.SULFUR_WALL);
        BlockTypes.TUFF_BRICK_WALL = buildWall(BlockId.TUFF_BRICK_WALL);
        BlockTypes.TUFF_WALL = buildWall(BlockId.TUFF_WALL);
    }

    /**
     * Builds and registers a wall with four directional connection domains and a
     * post flag. The wall base component receives this complete domain to interpret
     * the registered connection combinations.
     */
    public static BlockType<BlockWallBehavior> buildWall(BlockId blockId) {
        return AllayBlockType.builder(BlockWallBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .setBaseComponentSupplier(BlockWallBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the fence gates family and assigns its {@code BlockTypes}
     * references. Every wood variant shares in-wall, cardinal-direction, and open
     * properties, using the same fence-gate base component and vanilla metadata.
     */
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

    /**
     * Builds and registers a fence gate with in-wall, cardinal-direction, and open
     * properties. The shared base component interprets those properties while the
     * supplied identifier chooses the vanilla variant data.
     */
    public static BlockType<BlockFenceGateBehavior> buildFenceGate(BlockId blockId) {
        return AllayBlockType.builder(BlockFenceGateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .setBaseComponentSupplier(BlockFenceGateBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the quartz blocks family and assigns its {@code BlockTypes}
     * references. Ordinary, chiseled, and smooth forms retain the pillar-axis
     * domain so constructed states match the registered vanilla property
     * combinations.
     */
    public static void initQuartzBlocks() {
        // Retain PILLAR_AXIS even when the base behavior does not use it, because
        // vanilla default-state hashes include that property in their serialized
        // combinations.
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

    /**
     * Registers the purpur block family and assigns its {@code BlockTypes}
     * references. The type retains the pillar-axis domain so constructed states
     * match vanilla property combinations, alongside the specialized purpur base
     * component.
     */
    public static void initPurpurBlock() {
        // Retain PILLAR_AXIS even when the base behavior does not use it, because
        // vanilla default-state hashes include that property in their serialized
        // combinations.
        BlockTypes.PURPUR_BLOCK = AllayBlockType
                .builder(BlockPurpurBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockPurpurBlockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the item frame family and assigns its {@code BlockTypes}
     * references. Normal and glowing forms share facing and frame-content flags but
     * bind their corresponding ordinary or glowing item-frame entity type.
     */
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

    /**
     * Registers the bed family and assigns its {@code BlockTypes} references. The
     * type binds a bed entity, enumerates head, occupancy, and direction state, and
     * installs its respawn-point component.
     */
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

    /**
     * Registers the respawn anchor family and assigns its {@code BlockTypes}
     * references. The type enumerates its charge domain and combines the
     * specialized anchor base component with a separate respawn-point component.
     */
    public static void initRespawnAnchor() {
        BlockTypes.RESPAWN_ANCHOR = AllayBlockType
                .builder(BlockRespawnAnchorBehaviorImpl.class)
                .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                .setBaseComponentSupplier(BlockRespawnAnchorBaseComponentImpl::new)
                .addComponent(new BlockRespawnAnchorRespawnPointComponentImpl())
                .build();
    }

    /**
     * Registers the noteblock family and assigns its {@code BlockTypes} references.
     * The type binds the note-block entity and specialized base component without
     * adding a separate property domain in this configuration.
     */
    public static void initNoteblock() {
        BlockTypes.NOTEBLOCK = AllayBlockType
                .builder(BlockNoteblockBehaviorImpl.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .bindBlockEntity(BlockEntityTypes.NOTEBLOCK)
                .setBaseComponentSupplier(BlockNoteblockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the rods family and assigns its {@code BlockTypes} references. End
     * rods use facing behavior, while lightning-rod variants additionally enumerate
     * powered state and retain oxidation and wax counterpart resolution.
     */
    public static void initRods() {
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehaviorImpl.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockEndRodBaseComponentImpl::new)
                .build();

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a lightning rod with facing and powered properties. The
     * supplied oxidation level and resolver add wax and weathering transitions to
     * the specialized lightning-rod base behavior.
     */
    private static BlockType<BlockLightningRodBehavior> buildLightningRod(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockLightningRodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLightningRodBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    /**
     * Registers the lanterns family and assigns its {@code BlockTypes} references.
     * All lanterns enumerate hanging state, and the copper family additionally
     * receives oxidation-level and wax counterpart resolution through one shared
     * function.
     */
    public static void initLanterns() {
        BlockTypes.LANTERN = buildLantern(BlockId.LANTERN);
        BlockTypes.SOUL_LANTERN = buildLantern(BlockId.SOUL_LANTERN);

        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a lantern exposing the hanging property domain. The
     * specialized lantern base component is shared across noncopper variants while
     * their identifiers select different vanilla metadata.
     */
    private static BlockType<BlockLanternBehavior> buildLantern(BlockId blockId) {
        return AllayBlockType
                .builder(BlockLanternBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLanternBaseComponentImpl::new)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    /**
     * Builds and registers a copper lantern with hanging state and shared lantern
     * base behavior. Its oxidation component retains the supplied weathering level
     * and resolver for later counterpart selection.
     */
    private static BlockType<BlockCopperLanternBehavior> buildCopperLantern(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperLanternBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockLanternBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    /**
     * Registers the copper golem statues family and assigns its {@code BlockTypes}
     * references. Every variant enumerates cardinal orientation and retains the
     * shared function resolving oxidation-level and wax counterparts within the
     * statue family.
     */
    public static void initCopperGolemStatues() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a copper golem statue with cardinal orientation and
     * oxidation behavior. The supplied resolver retains lookup of wax and oxidation
     * counterparts within the configured statue family.
     */
    private static BlockType<BlockCopperGolemStatueBehavior> buildCopperGolemStatue(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperGolemStatueBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Registers the copper chains family and assigns its {@code BlockTypes}
     * references. Every variant enumerates pillar-axis orientation and retains the
     * shared function resolving oxidation-level and wax counterparts within the
     * chain family.
     */
    public static void initCopperChains() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers a copper chain with pillar-axis orientation and
     * oxidation behavior. The supplied resolver retains lookup of wax and oxidation
     * counterparts within the configured chain family.
     */
    private static BlockType<BlockCopperChainBehavior> buildCopperChain(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperChainBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Registers the heads family and assigns its {@code BlockTypes} references.
     * Every head variant enumerates facing direction and binds the head entity,
     * sharing the same specialized head base component.
     */
    public static void initHeads() {
        BlockTypes.CREEPER_HEAD = buildHead(BlockId.CREEPER_HEAD);
        BlockTypes.DRAGON_HEAD = buildHead(BlockId.DRAGON_HEAD);
        BlockTypes.PIGLIN_HEAD = buildHead(BlockId.PIGLIN_HEAD);
        BlockTypes.PLAYER_HEAD = buildHead(BlockId.PLAYER_HEAD);
        BlockTypes.ZOMBIE_HEAD = buildHead(BlockId.ZOMBIE_HEAD);
        BlockTypes.SKELETON_SKULL = buildHead(BlockId.SKELETON_SKULL);
        BlockTypes.WITHER_SKELETON_SKULL = buildHead(BlockId.WITHER_SKELETON_SKULL);
    }

    /**
     * Builds and registers a head variant with facing state and a head entity
     * holder. The specialized base component is shared while each identifier
     * selects its corresponding vanilla state metadata.
     */
    private static BlockType<BlockHeadBehavior> buildHead(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHeadBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockHeadBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HEAD)
                .build();
    }

    /**
     * Registers the small flowers family and assigns its {@code BlockTypes}
     * references. All listed single-block flowers share one construction helper and
     * base component while retaining the vanilla metadata for each identifier.
     */
    public static void initSmallFlowers() {
        BlockTypes.DANDELION = buildSmallFlower(BlockId.DANDELION);
        BlockTypes.GOLDEN_DANDELION = buildSmallFlower(BlockId.GOLDEN_DANDELION);
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

    /**
     * Builds and registers a small flower using shared small-flower base behavior.
     * This helper adds no property domains, leaving the supplied vanilla identifier
     * to select metadata and the associated item.
     */
    private static BlockType<BlockSmallFlower> buildSmallFlower(BlockId blockId) {
        return AllayBlockType
                .builder(BlockSmallFlowerImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockSmallFlowerBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the big flowers family and assigns its {@code BlockTypes}
     * references. Every listed large flower enumerates upper-block state and shares
     * the big-flower base component with its own vanilla metadata.
     */
    public static void initBigFlowers() {
        BlockTypes.SUNFLOWER = buildBigFlower(BlockId.SUNFLOWER);
        BlockTypes.LILAC = buildBigFlower(BlockId.LILAC);
        BlockTypes.ROSE_BUSH = buildBigFlower(BlockId.ROSE_BUSH);
        BlockTypes.PEONY = buildBigFlower(BlockId.PEONY);
        BlockTypes.PITCHER_PLANT = buildBigFlower(BlockId.PITCHER_PLANT);
    }

    /**
     * Builds and registers a large flower with upper-block state and shared base
     * behavior. The supplied vanilla identifier selects its metadata, while the
     * property distinguishes the two block halves.
     */
    private static BlockType<BlockBigFlower> buildBigFlower(BlockId blockId) {
        return AllayBlockType
                .builder(BlockBigFlowerImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(BlockBigFlowerBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the plant piles family and assigns its {@code BlockTypes}
     * references. Leaf litter, petals, and wildflowers share growth and
     * cardinal-direction properties, interpreted through the common plant-pile base
     * component.
     */
    public static void initPlantPiles() {
        BlockTypes.LEAF_LITTER = buildPlantPile(BlockId.LEAF_LITTER);
        BlockTypes.PINK_PETALS = buildPlantPile(BlockId.PINK_PETALS);
        BlockTypes.WILDFLOWERS = buildPlantPile(BlockId.WILDFLOWERS);
    }

    /**
     * Builds and registers a plant pile with growth and cardinal-direction
     * properties. The common pile base component interprets those combinations
     * while each identifier retains its own vanilla metadata.
     */
    private static BlockType<BlockPlantPile> buildPlantPile(BlockId blockId) {
        return AllayBlockType
                .builder(BlockPlantPileImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockPlantPileBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the copper bars family and assigns its {@code BlockTypes}
     * references. Every variant retains a resolver for oxidation and wax
     * counterparts while its vanilla metadata supplies the propertyless state
     * configuration.
     */
    public static void initCopperBars() {
        // Read counterpart fields when oxidation requests a transition, after every
        // variant has been assigned, instead of capturing uninitialized startup
        // references.
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

    /**
     * Builds and registers copper bars retaining the supplied oxidation level and
     * counterpart resolver. No additional property domain is configured here, so
     * construction uses the vanilla identifier's propertyless type configuration.
     */
    public static BlockType<BlockCopperBarsBehavior> buildCopperBars(
            BlockId id, OxidationLevel oxidationLevel,
            BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction
    ) {
        return AllayBlockType.builder(BlockCopperBarsBehaviorImpl.class)
                .vanillaBlock(id)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    /**
     * Registers the lever family and assigns its {@code BlockTypes} references. The
     * type enumerates lever direction and open state and installs the specialized
     * lever base component before its registry publication.
     */
    public static void initLever() {
        BlockTypes.LEVER = AllayBlockType
                .builder(BlockLeverBehaviorImpl.class)
                .vanillaBlock(BlockId.LEVER)
                .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .setBaseComponentSupplier(BlockLeverBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the redstone wire family and assigns its {@code BlockTypes}
     * references. The type enumerates signal strength and installs the
     * redstone-wire base component while retaining its vanilla physical state
     * metadata.
     */
    public static void initRedstoneWire() {
        BlockTypes.REDSTONE_WIRE = AllayBlockType
                .builder(BlockRedstoneWireBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_WIRE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(BlockRedstoneWireBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the redstone block family and assigns its {@code BlockTypes}
     * references. The type installs the redstone-block base component without
     * adding a signal-strength property or block-entity holder in this
     * configuration.
     */
    public static void initRedstoneBlock() {
        BlockTypes.REDSTONE_BLOCK = AllayBlockType
                .builder(BlockRedstoneBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_BLOCK)
                .setBaseComponentSupplier(BlockRedstoneBlockBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the pressure plates family and assigns its {@code BlockTypes}
     * references. Binary and weighted variants share the signal-strength domain,
     * while the selected base component determines which plate behavior is
     * assembled.
     */
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

    /**
     * Builds and registers a pressure plate with the redstone-signal property
     * domain. The supplied base-component factory selects binary or weighted plate
     * behavior before the finished type is registered.
     */
    private static <T extends BlockBehavior> BlockType<T> buildPressurePlate(BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(BlockPressurePlateBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    /**
     * Registers the tripwire family and assigns its {@code BlockTypes} references.
     * The hook and wire use different attached, direction, disarmed, powered, and
     * suspended domains appropriate to their separate base components.
     */
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

    /**
     * Registers the observer family and assigns its {@code BlockTypes} references.
     * The type enumerates facing and powered state and installs the observer base
     * component while retaining vanilla physical state metadata.
     */
    public static void initObserver() {
        BlockTypes.OBSERVER = AllayBlockType
                .builder(BlockObserverBehaviorImpl.class)
                .vanillaBlock(BlockId.OBSERVER)
                .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .setBaseComponentSupplier(BlockObserverBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the redstone family and assigns its {@code BlockTypes} references.
     * Lamp, daylight-detector, repeater, and comparator pairs retain explicit
     * variant flags, with comparator types also binding their block entity.
     */
    public static void initRedstone() {
        BlockTypes.REDSTONE_LAMP = buildRedstoneLamp(BlockId.REDSTONE_LAMP, false);
        BlockTypes.LIT_REDSTONE_LAMP = buildRedstoneLamp(BlockId.LIT_REDSTONE_LAMP, true);
        BlockTypes.DAYLIGHT_DETECTOR = buildDaylightDetector(BlockId.DAYLIGHT_DETECTOR, false);
        BlockTypes.DAYLIGHT_DETECTOR_INVERTED = buildDaylightDetector(BlockId.DAYLIGHT_DETECTOR_INVERTED, true);
        BlockTypes.POWERED_REPEATER = buildRedstoneRepeater(BlockId.POWERED_REPEATER, true);
        BlockTypes.UNPOWERED_REPEATER = buildRedstoneRepeater(BlockId.UNPOWERED_REPEATER, false);
        BlockTypes.POWERED_COMPARATOR = buildRedstoneComparator(BlockId.POWERED_COMPARATOR, true);
        BlockTypes.UNPOWERED_COMPARATOR = buildRedstoneComparator(BlockId.UNPOWERED_COMPARATOR, false);
    }

    /**
     * Builds and registers a redstone-lamp variant with the supplied lit flag. Lit
     * and unlit identifiers share the base component class while retaining separate
     * vanilla state metadata and item association.
     */
    private static BlockType<BlockRedstoneLampBehavior> buildRedstoneLamp(BlockId blockId, boolean lit) {
        return AllayBlockType
                .builder(BlockRedstoneLampBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneLampBaseComponentImpl(blockType, lit))
                .build();
    }

    /**
     * Builds and registers a redstone torch with facing state and the supplied lit
     * flag. The flag configures its specialized base component independently of the
     * retained vanilla identifier.
     */
    private static BlockType<BlockTorchBehavior> buildRedstoneTorch(BlockId blockId, boolean lit) {
        return AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneTorchBaseComponentImpl(blockType, lit))
                .build();
    }

    /**
     * Builds and registers a daylight detector with signal-strength state and the
     * supplied inversion flag. Its specialized base component retains that flag for
     * the configured normal or inverted variant.
     */
    private static BlockType<BlockDaylightDetectorBehavior> buildDaylightDetector(BlockId blockId, boolean inverted) {
        return AllayBlockType
                .builder(BlockDaylightDetectorBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .setBaseComponentSupplier(blockType -> new BlockDaylightDetectorBaseComponentImpl(blockType, inverted))
                .build();
    }

    /**
     * Builds and registers a repeater with cardinal orientation, delay state, and
     * the supplied powered flag. Its specialized base component retains that flag
     * for the configured powered or unpowered variant.
     */
    private static BlockType<BlockRepeaterBehavior> buildRedstoneRepeater(BlockId blockId, boolean powered) {
        return AllayBlockType
                .builder(BlockRepeaterBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneRepeaterBaseComponentImpl(blockType, powered))
                .build();
    }

    /**
     * Builds and registers a comparator with orientation and output-mode
     * properties, binding its comparator entity. The supplied powered flag
     * configures the specialized base component for the selected variant.
     */
    private static BlockType<BlockComparatorBehavior> buildRedstoneComparator(BlockId blockId, boolean powered) {
        return AllayBlockType
                .builder(BlockComparatorBehaviorImpl.class)
                .vanillaBlock(blockId)
                .bindBlockEntity(BlockEntityTypes.COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .setBaseComponentSupplier(blockType -> new BlockRedstoneComparatorBaseComponentImpl(blockType, powered))
                .build();
    }

    /**
     * Registers the target family and assigns its {@code BlockTypes} references.
     * The type binds the target entity and specialized target base component
     * without adding a separate signal property in this configuration.
     */
    public static void initTarget() {
        BlockTypes.TARGET = AllayBlockType
                .builder(BlockTargetBehaviorImpl.class)
                .vanillaBlock(BlockId.TARGET)
                .bindBlockEntity(BlockEntityTypes.TARGET)
                .setBaseComponentSupplier(BlockTargetBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the campfire family and assigns its {@code BlockTypes} references.
     * Normal and soul forms share orientation and extinguished properties, but bind
     * their corresponding entity and base component implementations.
     */
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

    /**
     * Registers the saplings family and assigns its {@code BlockTypes} references.
     * Species retain their configured tree feature choices, while mangrove
     * propagules use their own hanging and stage domains and base component.
     */
    public static void initSaplings() {
        BlockTypes.OAK_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, StraightBlobTreeFeature.OAK_IDENTIFIER, FancyOakTreeFeature.IDENTIFIER, null))
                .build();
        BlockTypes.BIRCH_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, StraightBlobTreeFeature.BIRCH_IDENTIFIER, null, null))
                .build();
        BlockTypes.SPRUCE_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(
                        blockType,
                        SpruceTreeFeature.IDENTIFIER,
                        null,
                        MegaConicalTreeFeature.MEGA_SPRUCE_IDENTIFIER,
                        MegaConicalTreeFeature.MEGA_PINE_IDENTIFIER,
                        // Give the two large spruce variants equal selection probability.
                        0.5f
                ))
                .build();
        BlockTypes.JUNGLE_SAPLING = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, StraightBlobTreeFeature.JUNGLE_IDENTIFIER, null, MegaJungleTreeFeature.IDENTIFIER))
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
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, null, null, DoubleTrunkTreeFeature.DARK_OAK_IDENTIFIER))
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
                .setBaseComponentSupplier(blockType -> new BlockSaplingBaseComponentImpl(blockType, DoubleTrunkTreeFeature.PALE_OAK_IDENTIFIER, null, null))
                .build();
        BlockTypes.MANGROVE_PROPAGULE = AllayBlockType
                .builder(BlockSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                .setBaseComponentSupplier(BlockMangrovePropaguleBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the piston family and assigns its {@code BlockTypes} references.
     * Normal and sticky pistons bind arm entities; moving blocks and both
     * arm-collision types receive their separate entity or facing configurations.
     */
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

    /**
     * Registers the cake family and assigns its {@code BlockTypes} references. The
     * type enumerates bite count and installs the cake base component while
     * obtaining physical metadata and default-state selection from vanilla
     * registries.
     */
    public static void initCake() {
        BlockTypes.CAKE = AllayBlockType
                .builder(BlockCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CAKE)
                .setProperties(BlockPropertyTypes.BITE_COUNTER)
                .setBaseComponentSupplier(BlockCakeBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the candles family and assigns its {@code BlockTypes} references.
     * Each candle retains its matching cake-block target, while each candle cake
     * retains the item identifier for its corresponding candle color.
     */
    public static void initCandles() {
        BlockTypes.CANDLE = buildCandle(BlockId.CANDLE, BlockId.CANDLE_CAKE);
        BlockTypes.WHITE_CANDLE = buildCandle(BlockId.WHITE_CANDLE, BlockId.WHITE_CANDLE_CAKE);
        BlockTypes.ORANGE_CANDLE = buildCandle(BlockId.ORANGE_CANDLE, BlockId.ORANGE_CANDLE_CAKE);
        BlockTypes.MAGENTA_CANDLE = buildCandle(BlockId.MAGENTA_CANDLE, BlockId.MAGENTA_CANDLE_CAKE);
        BlockTypes.LIGHT_BLUE_CANDLE = buildCandle(BlockId.LIGHT_BLUE_CANDLE, BlockId.LIGHT_BLUE_CANDLE_CAKE);
        BlockTypes.YELLOW_CANDLE = buildCandle(BlockId.YELLOW_CANDLE, BlockId.YELLOW_CANDLE_CAKE);
        BlockTypes.LIME_CANDLE = buildCandle(BlockId.LIME_CANDLE, BlockId.LIME_CANDLE_CAKE);
        BlockTypes.PINK_CANDLE = buildCandle(BlockId.PINK_CANDLE, BlockId.PINK_CANDLE_CAKE);
        BlockTypes.GRAY_CANDLE = buildCandle(BlockId.GRAY_CANDLE, BlockId.GRAY_CANDLE_CAKE);
        BlockTypes.LIGHT_GRAY_CANDLE = buildCandle(BlockId.LIGHT_GRAY_CANDLE, BlockId.LIGHT_GRAY_CANDLE_CAKE);
        BlockTypes.CYAN_CANDLE = buildCandle(BlockId.CYAN_CANDLE, BlockId.CYAN_CANDLE_CAKE);
        BlockTypes.PURPLE_CANDLE = buildCandle(BlockId.PURPLE_CANDLE, BlockId.PURPLE_CANDLE_CAKE);
        BlockTypes.BLUE_CANDLE = buildCandle(BlockId.BLUE_CANDLE, BlockId.BLUE_CANDLE_CAKE);
        BlockTypes.BROWN_CANDLE = buildCandle(BlockId.BROWN_CANDLE, BlockId.BROWN_CANDLE_CAKE);
        BlockTypes.GREEN_CANDLE = buildCandle(BlockId.GREEN_CANDLE, BlockId.GREEN_CANDLE_CAKE);
        BlockTypes.RED_CANDLE = buildCandle(BlockId.RED_CANDLE, BlockId.RED_CANDLE_CAKE);
        BlockTypes.BLACK_CANDLE = buildCandle(BlockId.BLACK_CANDLE, BlockId.BLACK_CANDLE_CAKE);
        BlockTypes.CANDLE_CAKE = buildCandleCake(BlockId.CANDLE_CAKE, ItemId.CANDLE);
        BlockTypes.WHITE_CANDLE_CAKE = buildCandleCake(BlockId.WHITE_CANDLE_CAKE, ItemId.WHITE_CANDLE);
        BlockTypes.ORANGE_CANDLE_CAKE = buildCandleCake(BlockId.ORANGE_CANDLE_CAKE, ItemId.ORANGE_CANDLE);
        BlockTypes.MAGENTA_CANDLE_CAKE = buildCandleCake(BlockId.MAGENTA_CANDLE_CAKE, ItemId.MAGENTA_CANDLE);
        BlockTypes.LIGHT_BLUE_CANDLE_CAKE = buildCandleCake(BlockId.LIGHT_BLUE_CANDLE_CAKE, ItemId.LIGHT_BLUE_CANDLE);
        BlockTypes.YELLOW_CANDLE_CAKE = buildCandleCake(BlockId.YELLOW_CANDLE_CAKE, ItemId.YELLOW_CANDLE);
        BlockTypes.LIME_CANDLE_CAKE = buildCandleCake(BlockId.LIME_CANDLE_CAKE, ItemId.LIME_CANDLE);
        BlockTypes.PINK_CANDLE_CAKE = buildCandleCake(BlockId.PINK_CANDLE_CAKE, ItemId.PINK_CANDLE);
        BlockTypes.GRAY_CANDLE_CAKE = buildCandleCake(BlockId.GRAY_CANDLE_CAKE, ItemId.GRAY_CANDLE);
        BlockTypes.LIGHT_GRAY_CANDLE_CAKE = buildCandleCake(BlockId.LIGHT_GRAY_CANDLE_CAKE, ItemId.LIGHT_GRAY_CANDLE);
        BlockTypes.CYAN_CANDLE_CAKE = buildCandleCake(BlockId.CYAN_CANDLE_CAKE, ItemId.CYAN_CANDLE);
        BlockTypes.PURPLE_CANDLE_CAKE = buildCandleCake(BlockId.PURPLE_CANDLE_CAKE, ItemId.PURPLE_CANDLE);
        BlockTypes.BLUE_CANDLE_CAKE = buildCandleCake(BlockId.BLUE_CANDLE_CAKE, ItemId.BLUE_CANDLE);
        BlockTypes.BROWN_CANDLE_CAKE = buildCandleCake(BlockId.BROWN_CANDLE_CAKE, ItemId.BROWN_CANDLE);
        BlockTypes.GREEN_CANDLE_CAKE = buildCandleCake(BlockId.GREEN_CANDLE_CAKE, ItemId.GREEN_CANDLE);
        BlockTypes.RED_CANDLE_CAKE = buildCandleCake(BlockId.RED_CANDLE_CAKE, ItemId.RED_CANDLE);
        BlockTypes.BLACK_CANDLE_CAKE = buildCandleCake(BlockId.BLACK_CANDLE_CAKE, ItemId.BLACK_CANDLE);
    }

    /**
     * Builds and registers a candle with count and lit properties and its matching
     * candle-cake identifier. The specialized base component retains that
     * counterpart instead of requiring it to be resolved immediately.
     */
    private static BlockType<BlockCandleBehavior> buildCandle(BlockId blockId, BlockId candleCakeId) {
        return AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleBaseComponentImpl(blockType, candleCakeId))
                .build();
    }

    /**
     * Builds and registers a candle cake with lit state and its corresponding
     * candle-item identifier. The specialized base component retains the item
     * choice while vanilla data supplies the cake variant's metadata.
     */
    private static BlockType<BlockCandleCakeBehavior> buildCandleCake(BlockId blockId, ItemId candleItemId) {
        return AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.LIT)
                .setBaseComponentSupplier(blockType -> new BlockCandleCakeBaseComponentImpl(blockType, candleItemId))
                .build();
    }

    /**
     * Registers the cauldron family and assigns its {@code BlockTypes} references.
     * The type enumerates liquid kind and fill level and binds the cauldron entity
     * before assembling the specialized base component.
     */
    public static void initCauldron() {
        BlockTypes.CAULDRON = AllayBlockType
                .builder(BlockCauldronBehaviorImpl.class)
                .vanillaBlock(BlockId.CAULDRON)
                .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                .bindBlockEntity(BlockEntityTypes.CAULDRON)
                .setBaseComponentSupplier(BlockCauldronBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the composter family and assigns its {@code BlockTypes} references.
     * The type enumerates composter fill level and installs the composter base
     * component while retaining its vanilla physical state metadata.
     */
    public static void initComposter() {
        BlockTypes.COMPOSTER = AllayBlockType
                .builder(BlockComposterBehaviorImpl.class)
                .vanillaBlock(BlockId.COMPOSTER)
                .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                .setBaseComponentSupplier(BlockComposterBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the bell family and assigns its {@code BlockTypes} references. The
     * type enumerates attachment, direction, and toggle state and binds the bell
     * entity alongside its specialized base component.
     */
    public static void initBell() {
        BlockTypes.BELL = AllayBlockType
                .builder(BlockBellBehaviorImpl.class)
                .vanillaBlock(BlockId.BELL)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.TOGGLE_BIT)
                .bindBlockEntity(BlockEntityTypes.BELL)
                .setBaseComponentSupplier(BlockBellBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the lectern family and assigns its {@code BlockTypes} references.
     * The type enumerates cardinal orientation and powered state and binds the
     * lectern entity alongside its specialized base component.
     */
    public static void initLectern() {
        BlockTypes.LECTERN = AllayBlockType
                .builder(BlockLecternBehaviorImpl.class)
                .vanillaBlock(BlockId.LECTERN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .bindBlockEntity(BlockEntityTypes.LECTERN)
                .setBaseComponentSupplier(BlockLecternBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the command block family and assigns its {@code BlockTypes}
     * references. Normal, chain, and repeating forms share conditional and facing
     * properties and bind command-block entities with the same base component.
     */
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

    /**
     * Registers the pointed dripstone family and assigns its {@code BlockTypes}
     * references. The type enumerates thickness and hanging state and adds its
     * specialized falling component with the pointed-dripstone landing sound.
     */
    public static void initPointedDripstone() {
        BlockTypes.POINTED_DRIPSTONE = AllayBlockType
                .builder(BlockPointedDripstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockPointedDripstoneBaseComponentImpl::new)
                .addComponent(new BlockPointedDripstoneFallableComponentImpl(SoundNames.LAND_POINTED_DRIPSTONE))
                .build();
    }

    /**
     * Registers the nether vines family and assigns its {@code BlockTypes}
     * references. Twisting and weeping forms retain opposite growth and support
     * faces, with a separate age property supplied to each base component.
     */
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

    /**
     * Registers the rail family and assigns its {@code BlockTypes} references. The
     * normal rail uses its ten-way direction domain and normal-rail base component,
     * without the powered-rail data flag.
     */
    public static void initRail() {
        BlockTypes.RAIL = AllayBlockType
                .builder(BlockRailBehaviorImpl.class)
                .vanillaBlock(BlockId.RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                .setBaseComponentSupplier(BlockNormalRailBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the golden rail family and assigns its {@code BlockTypes}
     * references. The type combines six-way direction and rail-data properties with
     * powered-rail behavior while retaining the vanilla state metadata.
     */
    public static void initGoldenRail() {
        BlockTypes.GOLDEN_RAIL = AllayBlockType
                .builder(BlockGoldenRailBehaviorImpl.class)
                .vanillaBlock(BlockId.GOLDEN_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockPoweredRailBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the detector rail family and assigns its {@code BlockTypes}
     * references. The type combines six-way direction and rail-data properties with
     * detector-rail behavior while retaining the vanilla state metadata.
     */
    public static void initDetectorRail() {
        BlockTypes.DETECTOR_RAIL = AllayBlockType
                .builder(BlockDetectorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.DETECTOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockDetectorRailBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the activator rail family and assigns its {@code BlockTypes}
     * references. The type combines six-way direction and rail-data properties with
     * the powered-rail base component selected by this configuration.
     */
    public static void initActivatorRail() {
        BlockTypes.ACTIVATOR_RAIL = AllayBlockType
                .builder(BlockActivatorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .setBaseComponentSupplier(BlockPoweredRailBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the nether wart family and assigns its {@code BlockTypes}
     * references. The type enumerates its four-value age domain and installs the
     * nether-wart base component while retaining vanilla metadata.
     */
    public static void initNetherWart() {
        BlockTypes.NETHER_WART = AllayBlockType
                .builder(BlockNetherWartBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_WART)
                .setProperties(BlockPropertyTypes.AGE_4)
                .setBaseComponentSupplier(BlockNetherWartBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the mushroom family and assigns its {@code BlockTypes} references.
     * Brown and red forms retain the identifiers of their corresponding
     * huge-mushroom features and share the mushroom base component.
     */
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

    /**
     * Registers the azalea family and assigns its {@code BlockTypes} references.
     * Ordinary and flowering forms retain the same azalea tree feature identifier
     * while using their corresponding behavior implementation classes.
     */
    public static void initAzalea() {
        BlockTypes.AZALEA = AllayBlockType
                .builder(BlockAzaleaBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA)
                .setBaseComponentSupplier(bt -> new BlockAzaleaBaseComponentImpl(bt, AzaleaTreeFeature.IDENTIFIER))
                .build();
        BlockTypes.FLOWERING_AZALEA = AllayBlockType
                .builder(BlockFloweringAzaleaBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWERING_AZALEA)
                .setBaseComponentSupplier(bt -> new BlockAzaleaBaseComponentImpl(bt, AzaleaTreeFeature.IDENTIFIER))
                .build();
    }

    /**
     * Registers the fungus family and assigns its {@code BlockTypes} references.
     * Crimson and warped forms retain their matching nylium support identifier and
     * huge-fungus feature choice through the shared fungus base component.
     */
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

    /**
     * Registers the sweet berry bush family and assigns its {@code BlockTypes}
     * references. The type enumerates growth and installs the sweet-berry-bush base
     * component while retaining vanilla metadata and default-state selection.
     */
    public static void initSweetBerryBush() {
        BlockTypes.SWEET_BERRY_BUSH = AllayBlockType
                .builder(BlockSweetBerryBushBehaviorImpl.class)
                .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                .setProperties(BlockPropertyTypes.GROWTH)
                .setBaseComponentSupplier(BlockSweetBerryBushBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the firefly bush family and assigns its {@code BlockTypes}
     * references. The type installs the firefly-bush base component without adding
     * property domains or a block-entity holder in this configuration.
     */
    public static void initFireflyBush() {
        BlockTypes.FIREFLY_BUSH = AllayBlockType
                .builder(BlockFireflyBushBehaviorImpl.class)
                .vanillaBlock(BlockId.FIREFLY_BUSH)
                .setBaseComponentSupplier(BlockFireflyBushBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the cocoa family and assigns its {@code BlockTypes} references. The
     * type enumerates age and four-way direction and installs the cocoa base
     * component while retaining vanilla physical metadata.
     */
    public static void initCocoa() {
        BlockTypes.COCOA = AllayBlockType
                .builder(BlockCocoaBehaviorImpl.class)
                .vanillaBlock(BlockId.COCOA)
                .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockCocoaBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the sea pickle family and assigns its {@code BlockTypes}
     * references. The type enumerates cluster count and dead state and installs the
     * sea-pickle base component while retaining vanilla physical metadata.
     */
    public static void initSeaPickle() {
        BlockTypes.SEA_PICKLE = AllayBlockType
                .builder(BlockSeaPickleBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_PICKLE)
                .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                .setBaseComponentSupplier(BlockSeaPickleBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the bamboo family and assigns its {@code BlockTypes} references.
     * The sapling uses its own base component, while mature bamboo additionally
     * enumerates leaf size and stalk thickness beside age.
     */
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

    /**
     * Registers the kelp family and assigns its {@code BlockTypes} references. The
     * type enumerates kelp age and installs the kelp base component while retaining
     * vanilla metadata and the configured associated item.
     */
    public static void initKelp() {
        BlockTypes.KELP = AllayBlockType
                .builder(BlockKelpBehaviorImpl.class)
                .vanillaBlock(BlockId.KELP)
                .setProperties(BlockPropertyTypes.KELP_AGE)
                .setBaseComponentSupplier(BlockKelpBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the vine family and assigns its {@code BlockTypes} references. The
     * type enumerates vine-direction bits and installs the vine base component
     * while retaining registry-defined physical state metadata.
     */
    public static void initVine() {
        BlockTypes.VINE = AllayBlockType
                .builder(BlockVineBehaviorImpl.class)
                .vanillaBlock(BlockId.VINE)
                .setProperties(BlockPropertyTypes.VINE_DIRECTION_BITS)
                .setBaseComponentSupplier(BlockVineBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the glow lichen family and assigns its {@code BlockTypes}
     * references. The type enumerates multi-face direction bits and uses the common
     * multi-face base component with its own vanilla physical metadata.
     */
    public static void initGlowLichen() {
        BlockTypes.GLOW_LICHEN = AllayBlockType
                .builder(BlockGlowLichenBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOW_LICHEN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .setBaseComponentSupplier(BlockMultiFaceBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the sculk vein family and assigns its {@code BlockTypes}
     * references. The type enumerates multi-face direction bits and uses the common
     * multi-face base component with its own vanilla physical metadata.
     */
    public static void initSculkVein() {
        BlockTypes.SCULK_VEIN = AllayBlockType
                .builder(BlockSculkVeinBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK_VEIN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .setBaseComponentSupplier(BlockMultiFaceBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the resin clump family and assigns its {@code BlockTypes}
     * references. The type enumerates multi-face direction bits and uses the
     * resin-clump base component while retaining vanilla physical state metadata.
     */
    public static void initResinClump() {
        BlockTypes.RESIN_CLUMP = AllayBlockType
                .builder(BlockResinClumpBehaviorImpl.class)
                .vanillaBlock(BlockId.RESIN_CLUMP)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .setBaseComponentSupplier(BlockResinClumpBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the waterlily family and assigns its {@code BlockTypes} references.
     * The type installs the waterlily base component without adding property
     * domains or a block-entity holder in this configuration.
     */
    public static void initWaterlily() {
        BlockTypes.WATERLILY = AllayBlockType
                .builder(BlockWaterlilyBehaviorImpl.class)
                .vanillaBlock(BlockId.WATERLILY)
                .setBaseComponentSupplier(BlockWaterlilyBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the spore blossom family and assigns its {@code BlockTypes}
     * references. The type installs the spore-blossom base component without adding
     * property domains or a block-entity holder in this configuration.
     */
    public static void initSporeBlossom() {
        BlockTypes.SPORE_BLOSSOM = AllayBlockType
                .builder(BlockSporeBlossomBehaviorImpl.class)
                .vanillaBlock(BlockId.SPORE_BLOSSOM)
                .setBaseComponentSupplier(BlockSporeBlossomBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the seagrass family and assigns its {@code BlockTypes} references.
     * The type enumerates seagrass kind and installs the seagrass base component
     * while retaining its vanilla physical state metadata.
     */
    public static void initSeagrass() {
        BlockTypes.SEAGRASS = AllayBlockType
                .builder(BlockSeagrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SEAGRASS)
                .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                .setBaseComponentSupplier(BlockSeagrassBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the cave vines family and assigns its {@code BlockTypes}
     * references. The growing, berry-body, and berry-head variants share age state
     * but receive distinct berry and head flags in their base components.
     */
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

    /**
     * Registers the dripleaf family and assigns its {@code BlockTypes} references.
     * Small dripleaf enumerates upper-block state, while large dripleaf enumerates
     * head and tilt state alongside their shared cardinal orientation.
     */
    public static void initDripleaf() {
        BlockTypes.SMALL_DRIPLEAF_BLOCK = AllayBlockType
                .builder(BlockSmallDripleafBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.SMALL_DRIPLEAF_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(BlockSmallDripleafBaseComponentImpl::new)
                .build();

        BlockTypes.BIG_DRIPLEAF = AllayBlockType
                .builder(BlockBigDripleafBehaviorImpl.class)
                .vanillaBlock(BlockId.BIG_DRIPLEAF)
                .setProperties(BlockPropertyTypes.BIG_DRIPLEAF_HEAD, BlockPropertyTypes.BIG_DRIPLEAF_TILT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBaseComponentSupplier(BlockBigDripleafBaseComponentImpl::new)
                .build();
    }

    /**
     * Registers the shelf family and assigns its {@code BlockTypes} references.
     * Every wood variant binds the shelf entity and shares cardinal-direction,
     * powered, and powered-shelf-kind properties through the same base component.
     */
    public static void initShelf() {
        BlockTypes.OAK_SHELF = buildShelf(BlockId.OAK_SHELF);
        BlockTypes.ACACIA_SHELF = buildShelf(BlockId.ACACIA_SHELF);
        BlockTypes.BAMBOO_SHELF = buildShelf(BlockId.BAMBOO_SHELF);
        BlockTypes.BIRCH_SHELF = buildShelf(BlockId.BIRCH_SHELF);
        BlockTypes.CHERRY_SHELF = buildShelf(BlockId.CHERRY_SHELF);
        BlockTypes.CRIMSON_SHELF = buildShelf(BlockId.CRIMSON_SHELF);
        BlockTypes.DARK_OAK_SHELF = buildShelf(BlockId.DARK_OAK_SHELF);
        BlockTypes.JUNGLE_SHELF = buildShelf(BlockId.JUNGLE_SHELF);
        BlockTypes.MANGROVE_SHELF = buildShelf(BlockId.MANGROVE_SHELF);
        BlockTypes.PALE_OAK_SHELF = buildShelf(BlockId.PALE_OAK_SHELF);
        BlockTypes.SPRUCE_SHELF = buildShelf(BlockId.SPRUCE_SHELF);
        BlockTypes.WARPED_SHELF = buildShelf(BlockId.WARPED_SHELF);
    }

    /**
     * Registers the chiseled bookshelf family and assigns its {@code BlockTypes}
     * references. The type binds the chiseled-bookshelf entity and enumerates
     * stored-book and direction state before assembling its specialized base
     * component.
     */
    public static void initChiseledBookshelf() {
        BlockTypes.CHISELED_BOOKSHELF = AllayBlockType
                .builder(BlockChiseledBookshelfBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_BOOKSHELF)
                .bindBlockEntity(BlockEntityTypes.CHISELED_BOOKSHELF)
                .setProperties(BlockPropertyTypes.BOOKS_STORED, BlockPropertyTypes.DIRECTION_4)
                .setBaseComponentSupplier(BlockChiseledBookshelfBaseComponentImpl::new)
                .build();
    }

    /**
     * Builds and registers a shelf with its shelf entity holder and orientation and
     * power-related properties. The shared base component interprets these states
     * while the identifier selects the wood variant.
     */
    private static BlockType<BlockShelfBehavior> buildShelf(BlockId blockId) {
        return AllayBlockType
                .builder(BlockShelfBehaviorImpl.class)
                .vanillaBlock(blockId)
                .bindBlockEntity(BlockEntityTypes.SHELF)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                .setBaseComponentSupplier(BlockShelfBaseComponentImpl::new)
                .build();
    }
}
