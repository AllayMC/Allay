package org.allaymc.server.block.type;

import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.server.block.impl.BlockAcaciaPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockActivatorRailBehaviorImpl;
import org.allaymc.server.block.impl.BlockAirBehaviorImpl;
import org.allaymc.server.block.impl.BlockAlliumBehaviorImpl;
import org.allaymc.server.block.impl.BlockAllowBehaviorImpl;
import org.allaymc.server.block.impl.BlockAmethystBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockAmethystClusterBehaviorImpl;
import org.allaymc.server.block.impl.BlockAncientDebrisBehaviorImpl;
import org.allaymc.server.block.impl.BlockAndesiteBehaviorImpl;
import org.allaymc.server.block.impl.BlockAnvilBehaviorImpl;
import org.allaymc.server.block.impl.BlockAzaleaBehaviorImpl;
import org.allaymc.server.block.impl.BlockAzureBluetBehaviorImpl;
import org.allaymc.server.block.impl.BlockBambooBehaviorImpl;
import org.allaymc.server.block.impl.BlockBambooMosaicBehaviorImpl;
import org.allaymc.server.block.impl.BlockBambooPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockBarrelBehaviorImpl;
import org.allaymc.server.block.impl.BlockBarrierBehaviorImpl;
import org.allaymc.server.block.impl.BlockBasaltBehaviorImpl;
import org.allaymc.server.block.impl.BlockBeaconBehaviorImpl;
import org.allaymc.server.block.impl.BlockBedBehaviorImpl;
import org.allaymc.server.block.impl.BlockBedrockBehaviorImpl;
import org.allaymc.server.block.impl.BlockBeeNestBehaviorImpl;
import org.allaymc.server.block.impl.BlockBeehiveBehaviorImpl;
import org.allaymc.server.block.impl.BlockBeetrootBehaviorImpl;
import org.allaymc.server.block.impl.BlockBellBehaviorImpl;
import org.allaymc.server.block.impl.BlockBigDripleafBehaviorImpl;
import org.allaymc.server.block.impl.BlockBirchPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockBlackstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockBlueIceBehaviorImpl;
import org.allaymc.server.block.impl.BlockBlueOrchidBehaviorImpl;
import org.allaymc.server.block.impl.BlockBoneBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockBookshelfBehaviorImpl;
import org.allaymc.server.block.impl.BlockBorderBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockBrewingStandBehaviorImpl;
import org.allaymc.server.block.impl.BlockBrickBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockBrownMushroomBehaviorImpl;
import org.allaymc.server.block.impl.BlockBrownMushroomBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockBubbleColumnBehaviorImpl;
import org.allaymc.server.block.impl.BlockBuddingAmethystBehaviorImpl;
import org.allaymc.server.block.impl.BlockButtonBehaviorImpl;
import org.allaymc.server.block.impl.BlockCactusBehaviorImpl;
import org.allaymc.server.block.impl.BlockCakeBehaviorImpl;
import org.allaymc.server.block.impl.BlockCalciteBehaviorImpl;
import org.allaymc.server.block.impl.BlockCalibratedSculkSensorBehaviorImpl;
import org.allaymc.server.block.impl.BlockCameraBehaviorImpl;
import org.allaymc.server.block.impl.BlockCampfireBehaviorImpl;
import org.allaymc.server.block.impl.BlockCarrotsBehaviorImpl;
import org.allaymc.server.block.impl.BlockCartographyTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockCarvedPumpkinBehaviorImpl;
import org.allaymc.server.block.impl.BlockCauldronBehaviorImpl;
import org.allaymc.server.block.impl.BlockCaveVinesBehaviorImpl;
import org.allaymc.server.block.impl.BlockCaveVinesBodyWithBerriesBehaviorImpl;
import org.allaymc.server.block.impl.BlockCaveVinesHeadWithBerriesBehaviorImpl;
import org.allaymc.server.block.impl.BlockChainBehaviorImpl;
import org.allaymc.server.block.impl.BlockChainCommandBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockChemicalHeatBehaviorImpl;
import org.allaymc.server.block.impl.BlockCherryPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockChestBehaviorImpl;
import org.allaymc.server.block.impl.BlockChiseledBookshelfBehaviorImpl;
import org.allaymc.server.block.impl.BlockChiseledDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockChiseledPolishedBlackstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockChiseledQuartzBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockChiseledTuffBehaviorImpl;
import org.allaymc.server.block.impl.BlockChorusFlowerBehaviorImpl;
import org.allaymc.server.block.impl.BlockChorusPlantBehaviorImpl;
import org.allaymc.server.block.impl.BlockClayBehaviorImpl;
import org.allaymc.server.block.impl.BlockClientRequestPlaceholderBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockCoalBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockCoalOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockCobbledDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockCobblestoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockCocoaBehaviorImpl;
import org.allaymc.server.block.impl.BlockCommandBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockComposterBehaviorImpl;
import org.allaymc.server.block.impl.BlockCompoundCreatorBehaviorImpl;
import org.allaymc.server.block.impl.BlockConduitBehaviorImpl;
import org.allaymc.server.block.impl.BlockCopperBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockCopperOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockCornflowerBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrackedDeepslateTilesBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrafterBehaviorImpl;
import org.allaymc.server.block.impl.BlockCraftingTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrimsonFungusBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrimsonNyliumBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrimsonPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockCrimsonRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockCryingObsidianBehaviorImpl;
import org.allaymc.server.block.impl.BlockDandelionBehaviorImpl;
import org.allaymc.server.block.impl.BlockDarkOakPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockDarkPrismarineBehaviorImpl;
import org.allaymc.server.block.impl.BlockDaylightDetectorBehaviorImpl;
import org.allaymc.server.block.impl.BlockDaylightDetectorInvertedBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeadbushBehaviorImpl;
import org.allaymc.server.block.impl.BlockDecoratedPotBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateCoalOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateCopperOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateDiamondOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateEmeraldOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateGoldOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateIronOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateLapisOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateRedstoneOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeepslateTilesBehaviorImpl;
import org.allaymc.server.block.impl.BlockDenyBehaviorImpl;
import org.allaymc.server.block.impl.BlockDeprecatedPurpurBlock1BehaviorImpl;
import org.allaymc.server.block.impl.BlockDeprecatedPurpurBlock2BehaviorImpl;
import org.allaymc.server.block.impl.BlockDetectorRailBehaviorImpl;
import org.allaymc.server.block.impl.BlockDiamondBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockDiamondOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockDioriteBehaviorImpl;
import org.allaymc.server.block.impl.BlockDirtWithRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockDispenserBehaviorImpl;
import org.allaymc.server.block.impl.BlockDragonEggBehaviorImpl;
import org.allaymc.server.block.impl.BlockDriedKelpBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockDripstoneBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockDropperBehaviorImpl;
import org.allaymc.server.block.impl.BlockEmeraldBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockEmeraldOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockEnchantingTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockEndGatewayBehaviorImpl;
import org.allaymc.server.block.impl.BlockEndPortalBehaviorImpl;
import org.allaymc.server.block.impl.BlockEndPortalFrameBehaviorImpl;
import org.allaymc.server.block.impl.BlockEndRodBehaviorImpl;
import org.allaymc.server.block.impl.BlockEndStoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockEnderChestBehaviorImpl;
import org.allaymc.server.block.impl.BlockExposedCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockExposedCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockFarmlandBehaviorImpl;
import org.allaymc.server.block.impl.BlockFernBehaviorImpl;
import org.allaymc.server.block.impl.BlockFireBehaviorImpl;
import org.allaymc.server.block.impl.BlockFletchingTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockFlowerPotBehaviorImpl;
import org.allaymc.server.block.impl.BlockFloweringAzaleaBehaviorImpl;
import org.allaymc.server.block.impl.BlockFrameBehaviorImpl;
import org.allaymc.server.block.impl.BlockFrogSpawnBehaviorImpl;
import org.allaymc.server.block.impl.BlockFrostedIceBehaviorImpl;
import org.allaymc.server.block.impl.BlockGildedBlackstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockGlowFrameBehaviorImpl;
import org.allaymc.server.block.impl.BlockGlowLichenBehaviorImpl;
import org.allaymc.server.block.impl.BlockGlowingobsidianBehaviorImpl;
import org.allaymc.server.block.impl.BlockGlowstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockGoldBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockGoldOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockGoldenRailBehaviorImpl;
import org.allaymc.server.block.impl.BlockGraniteBehaviorImpl;
import org.allaymc.server.block.impl.BlockGrassBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockGrassPathBehaviorImpl;
import org.allaymc.server.block.impl.BlockGravelBehaviorImpl;
import org.allaymc.server.block.impl.BlockGrindstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockHangingRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockHardenedClayBehaviorImpl;
import org.allaymc.server.block.impl.BlockHayBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockHeavyCoreBehaviorImpl;
import org.allaymc.server.block.impl.BlockHeavyWeightedPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockHoneyBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockHoneycombBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockHopperBehaviorImpl;
import org.allaymc.server.block.impl.BlockIceBehaviorImpl;
import org.allaymc.server.block.impl.BlockInfestedCobblestoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockInfestedDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockInfestedStoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockInfoUpdate2BehaviorImpl;
import org.allaymc.server.block.impl.BlockInfoUpdateBehaviorImpl;
import org.allaymc.server.block.impl.BlockInvisibleBedrockBehaviorImpl;
import org.allaymc.server.block.impl.BlockIronBarsBehaviorImpl;
import org.allaymc.server.block.impl.BlockIronBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockIronOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockJigsawBehaviorImpl;
import org.allaymc.server.block.impl.BlockJukeboxBehaviorImpl;
import org.allaymc.server.block.impl.BlockJunglePressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockKelpBehaviorImpl;
import org.allaymc.server.block.impl.BlockLabTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockLadderBehaviorImpl;
import org.allaymc.server.block.impl.BlockLanternBehaviorImpl;
import org.allaymc.server.block.impl.BlockLapisBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockLapisOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockLargeFernBehaviorImpl;
import org.allaymc.server.block.impl.BlockLeavesBehaviorImpl;
import org.allaymc.server.block.impl.BlockLecternBehaviorImpl;
import org.allaymc.server.block.impl.BlockLeverBehaviorImpl;
import org.allaymc.server.block.impl.BlockLightWeightedPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockLightningRodBehaviorImpl;
import org.allaymc.server.block.impl.BlockLilacBehaviorImpl;
import org.allaymc.server.block.impl.BlockLilyOfTheValleyBehaviorImpl;
import org.allaymc.server.block.impl.BlockLitDeepslateRedstoneOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockLitPumpkinBehaviorImpl;
import org.allaymc.server.block.impl.BlockLitRedstoneLampBehaviorImpl;
import org.allaymc.server.block.impl.BlockLitRedstoneOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockLodestoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockLoomBehaviorImpl;
import org.allaymc.server.block.impl.BlockMagmaBehaviorImpl;
import org.allaymc.server.block.impl.BlockMangrovePressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockMangrovePropaguleBehaviorImpl;
import org.allaymc.server.block.impl.BlockMangroveRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockMaterialReducerBehaviorImpl;
import org.allaymc.server.block.impl.BlockMelonBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockMelonStemBehaviorImpl;
import org.allaymc.server.block.impl.BlockMobSpawnerBehaviorImpl;
import org.allaymc.server.block.impl.BlockMossBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockMossyCobblestoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockMovingBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockMudBehaviorImpl;
import org.allaymc.server.block.impl.BlockMuddyMangroveRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockMushroomStemBehaviorImpl;
import org.allaymc.server.block.impl.BlockMyceliumBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherBrickBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherGoldOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherSproutsBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherWartBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherWartBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetheriteBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherrackBehaviorImpl;
import org.allaymc.server.block.impl.BlockNetherreactorBehaviorImpl;
import org.allaymc.server.block.impl.BlockNoteblockBehaviorImpl;
import org.allaymc.server.block.impl.BlockObserverBehaviorImpl;
import org.allaymc.server.block.impl.BlockObsidianBehaviorImpl;
import org.allaymc.server.block.impl.BlockOchreFroglightBehaviorImpl;
import org.allaymc.server.block.impl.BlockOrangeTulipBehaviorImpl;
import org.allaymc.server.block.impl.BlockOxeyeDaisyBehaviorImpl;
import org.allaymc.server.block.impl.BlockOxidizedCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockOxidizedCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockPackedIceBehaviorImpl;
import org.allaymc.server.block.impl.BlockPackedMudBehaviorImpl;
import org.allaymc.server.block.impl.BlockPearlescentFroglightBehaviorImpl;
import org.allaymc.server.block.impl.BlockPeonyBehaviorImpl;
import org.allaymc.server.block.impl.BlockPinkPetalsBehaviorImpl;
import org.allaymc.server.block.impl.BlockPinkTulipBehaviorImpl;
import org.allaymc.server.block.impl.BlockPitcherCropBehaviorImpl;
import org.allaymc.server.block.impl.BlockPitcherPlantBehaviorImpl;
import org.allaymc.server.block.impl.BlockPodzolBehaviorImpl;
import org.allaymc.server.block.impl.BlockPointedDripstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedAndesiteBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedBasaltBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedBlackstoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedBlackstonePressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedDioriteBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedGraniteBehaviorImpl;
import org.allaymc.server.block.impl.BlockPolishedTuffBehaviorImpl;
import org.allaymc.server.block.impl.BlockPoppyBehaviorImpl;
import org.allaymc.server.block.impl.BlockPortalBehaviorImpl;
import org.allaymc.server.block.impl.BlockPotatoesBehaviorImpl;
import org.allaymc.server.block.impl.BlockPowderSnowBehaviorImpl;
import org.allaymc.server.block.impl.BlockPoweredComparatorBehaviorImpl;
import org.allaymc.server.block.impl.BlockPoweredRepeaterBehaviorImpl;
import org.allaymc.server.block.impl.BlockPrismarineBehaviorImpl;
import org.allaymc.server.block.impl.BlockPumpkinBehaviorImpl;
import org.allaymc.server.block.impl.BlockPumpkinStemBehaviorImpl;
import org.allaymc.server.block.impl.BlockQuartzBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockQuartzOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockQuartzPillarBehaviorImpl;
import org.allaymc.server.block.impl.BlockRailBehaviorImpl;
import org.allaymc.server.block.impl.BlockRawCopperBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockRawGoldBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockRawIronBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedMushroomBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedMushroomBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedNetherBrickBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedTulipBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedstoneBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedstoneLampBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedstoneOreBehaviorImpl;
import org.allaymc.server.block.impl.BlockRedstoneWireBehaviorImpl;
import org.allaymc.server.block.impl.BlockReedsBehaviorImpl;
import org.allaymc.server.block.impl.BlockReinforcedDeepslateBehaviorImpl;
import org.allaymc.server.block.impl.BlockRepeatingCommandBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockReserved6BehaviorImpl;
import org.allaymc.server.block.impl.BlockRespawnAnchorBehaviorImpl;
import org.allaymc.server.block.impl.BlockRoseBushBehaviorImpl;
import org.allaymc.server.block.impl.BlockScaffoldingBehaviorImpl;
import org.allaymc.server.block.impl.BlockSculkBehaviorImpl;
import org.allaymc.server.block.impl.BlockSculkCatalystBehaviorImpl;
import org.allaymc.server.block.impl.BlockSculkSensorBehaviorImpl;
import org.allaymc.server.block.impl.BlockSculkShriekerBehaviorImpl;
import org.allaymc.server.block.impl.BlockSculkVeinBehaviorImpl;
import org.allaymc.server.block.impl.BlockSeaLanternBehaviorImpl;
import org.allaymc.server.block.impl.BlockSeaPickleBehaviorImpl;
import org.allaymc.server.block.impl.BlockSeagrassBehaviorImpl;
import org.allaymc.server.block.impl.BlockShortGrassBehaviorImpl;
import org.allaymc.server.block.impl.BlockShroomlightBehaviorImpl;
import org.allaymc.server.block.impl.BlockShulkerBoxBehaviorImpl;
import org.allaymc.server.block.impl.BlockSlimeBehaviorImpl;
import org.allaymc.server.block.impl.BlockSmallDripleafBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockSmithingTableBehaviorImpl;
import org.allaymc.server.block.impl.BlockSmoothBasaltBehaviorImpl;
import org.allaymc.server.block.impl.BlockSmoothQuartzBehaviorImpl;
import org.allaymc.server.block.impl.BlockSmoothStoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockSnifferEggBehaviorImpl;
import org.allaymc.server.block.impl.BlockSnowBehaviorImpl;
import org.allaymc.server.block.impl.BlockSnowLayerBehaviorImpl;
import org.allaymc.server.block.impl.BlockSoulCampfireBehaviorImpl;
import org.allaymc.server.block.impl.BlockSoulFireBehaviorImpl;
import org.allaymc.server.block.impl.BlockSoulLanternBehaviorImpl;
import org.allaymc.server.block.impl.BlockSoulSoilBehaviorImpl;
import org.allaymc.server.block.impl.BlockSporeBlossomBehaviorImpl;
import org.allaymc.server.block.impl.BlockSprucePressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockStairsBehaviorImpl;
import org.allaymc.server.block.impl.BlockStandingBannerBehaviorImpl;
import org.allaymc.server.block.impl.BlockStoneBehaviorImpl;
import org.allaymc.server.block.impl.BlockStonePressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockStonecutterBehaviorImpl;
import org.allaymc.server.block.impl.BlockStonecutterBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockStructureBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockStructureVoidBehaviorImpl;
import org.allaymc.server.block.impl.BlockSunflowerBehaviorImpl;
import org.allaymc.server.block.impl.BlockSuspiciousGravelBehaviorImpl;
import org.allaymc.server.block.impl.BlockSweetBerryBushBehaviorImpl;
import org.allaymc.server.block.impl.BlockTallGrassBehaviorImpl;
import org.allaymc.server.block.impl.BlockTargetBehaviorImpl;
import org.allaymc.server.block.impl.BlockTrappedChestBehaviorImpl;
import org.allaymc.server.block.impl.BlockTrialSpawnerBehaviorImpl;
import org.allaymc.server.block.impl.BlockTripWireBehaviorImpl;
import org.allaymc.server.block.impl.BlockTripwireHookBehaviorImpl;
import org.allaymc.server.block.impl.BlockTuffBehaviorImpl;
import org.allaymc.server.block.impl.BlockTurtleEggBehaviorImpl;
import org.allaymc.server.block.impl.BlockTwistingVinesBehaviorImpl;
import org.allaymc.server.block.impl.BlockUnknownBehaviorImpl;
import org.allaymc.server.block.impl.BlockUnpoweredComparatorBehaviorImpl;
import org.allaymc.server.block.impl.BlockUnpoweredRepeaterBehaviorImpl;
import org.allaymc.server.block.impl.BlockVaultBehaviorImpl;
import org.allaymc.server.block.impl.BlockVerdantFroglightBehaviorImpl;
import org.allaymc.server.block.impl.BlockVineBehaviorImpl;
import org.allaymc.server.block.impl.BlockWallBannerBehaviorImpl;
import org.allaymc.server.block.impl.BlockWarpedFungusBehaviorImpl;
import org.allaymc.server.block.impl.BlockWarpedNyliumBehaviorImpl;
import org.allaymc.server.block.impl.BlockWarpedPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWarpedRootsBehaviorImpl;
import org.allaymc.server.block.impl.BlockWarpedWartBlockBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaterlilyBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedExposedCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedExposedCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedOxidizedCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedOxidizedCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedWeatheredCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockWaxedWeatheredCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWeatheredCopperBulbBehaviorImpl;
import org.allaymc.server.block.impl.BlockWeatheredCopperGrateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWebBehaviorImpl;
import org.allaymc.server.block.impl.BlockWeepingVinesBehaviorImpl;
import org.allaymc.server.block.impl.BlockWheatBehaviorImpl;
import org.allaymc.server.block.impl.BlockWhiteTulipBehaviorImpl;
import org.allaymc.server.block.impl.BlockWitherRoseBehaviorImpl;
import org.allaymc.server.block.impl.BlockWoodBehaviorImpl;
import org.allaymc.server.block.impl.BlockWoodenPressurePlateBehaviorImpl;
import org.allaymc.server.block.impl.BlockWoolBehaviorImpl;
import org.allaymc.server.block.impl.amethystbud.BlockLargeAmethystBudBehaviorImpl;
import org.allaymc.server.block.impl.amethystbud.BlockMediumAmethystBudBehaviorImpl;
import org.allaymc.server.block.impl.amethystbud.BlockSmallAmethystBudBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockChiseledNetherBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockChiseledStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockChiseledTuffBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockCrackedDeepslateBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockCrackedNetherBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockCrackedPolishedBlackstoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockCrackedStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockDeepslateBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockEndBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockInfestedChiseledStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockInfestedCrackedStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockInfestedMossyStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockInfestedStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockMossyStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockMudBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockPolishedBlackstoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockPrismarineBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockQuartzBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockStoneBricksBehaviorImpl;
import org.allaymc.server.block.impl.bricks.BlockTuffBricksBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockBlackCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockBlueCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockBrownCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockCyanCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockGrayCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockGreenCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockLightBlueCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockLightGrayCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockLimeCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockMagentaCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockOrangeCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockPinkCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockPurpleCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockRedCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockWhiteCandleBehaviorImpl;
import org.allaymc.server.block.impl.candle.BlockYellowCandleBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockBlackCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockBlueCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockBrownCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockCyanCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockGrayCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockGreenCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockLightBlueCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockLightGrayCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockLimeCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockMagentaCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockOrangeCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockPinkCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockPurpleCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockRedCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockWhiteCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.candlecake.BlockYellowCandleCakeBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockBlackCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockBlueCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockBrownCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockCyanCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockGrayCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockGreenCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockLightBlueCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockLightGrayCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockLimeCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockMagentaCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockMossCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockOrangeCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockPinkCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockPurpleCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockRedCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockWhiteCarpetBehaviorImpl;
import org.allaymc.server.block.impl.carpet.BlockYellowCarpetBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockBlackConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockBlueConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockBrownConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockCyanConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockGrayConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockGreenConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockLightBlueConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockLightGrayConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockLimeConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockMagentaConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockOrangeConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockPinkConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockPurpleConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockRedConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockWhiteConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concrete.BlockYellowConcreteBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockBlackConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockBlueConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockBrownConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockCyanConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockGrayConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockGreenConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockLightBlueConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockLightGrayConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockLimeConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockMagentaConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockOrangeConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockPinkConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockPurpleConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockRedConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockWhiteConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.concretepowder.BlockYellowConcretePowderBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockExposedChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockExposedCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockExposedCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockOxidizedChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockOxidizedCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockOxidizedCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedExposedChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedExposedCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedExposedCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedOxidizedChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedOxidizedCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedOxidizedCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedWeatheredChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedWeatheredCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWaxedWeatheredCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWeatheredChiseledCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWeatheredCopperBehaviorImpl;
import org.allaymc.server.block.impl.copper.BlockWeatheredCutCopperBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockBrainCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockBubbleCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockDeadBrainCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockDeadBubbleCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockDeadFireCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockDeadHornCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockDeadTubeCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockFireCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockHornCoralBehaviorImpl;
import org.allaymc.server.block.impl.coral.BlockTubeCoralBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockBrainCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockBubbleCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockDeadBrainCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockDeadBubbleCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockDeadFireCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockDeadHornCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockDeadTubeCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockFireCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockHornCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralblock.BlockTubeCoralBlockBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockBrainCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockBubbleCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockDeadBrainCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockDeadBubbleCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockDeadFireCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockDeadHornCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockDeadTubeCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockFireCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockHornCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralfan.BlockTubeCoralFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockBrainCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockBubbleCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockDeadBrainCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockDeadBubbleCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockDeadFireCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockDeadHornCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockDeadTubeCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockFireCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockHornCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.coralwallfan.BlockTubeCoralWallFanBehaviorImpl;
import org.allaymc.server.block.impl.dirt.BlockCoarseDirtBehaviorImpl;
import org.allaymc.server.block.impl.dirt.BlockDirtBehaviorImpl;
import org.allaymc.server.block.impl.door.BlockCopperDoorBehaviorImpl;
import org.allaymc.server.block.impl.door.BlockDoorBehaviorImpl;
import org.allaymc.server.block.impl.door.BlockIronDoorBehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement0BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement100BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement101BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement102BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement103BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement104BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement105BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement106BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement107BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement108BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement109BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement10BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement110BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement111BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement112BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement113BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement114BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement115BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement116BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement117BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement118BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement11BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement12BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement13BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement14BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement15BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement16BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement17BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement18BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement19BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement1BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement20BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement21BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement22BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement23BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement24BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement25BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement26BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement27BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement28BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement29BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement2BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement30BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement31BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement32BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement33BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement34BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement35BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement36BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement37BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement38BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement39BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement3BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement40BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement41BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement42BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement43BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement44BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement45BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement46BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement47BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement48BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement49BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement4BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement50BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement51BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement52BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement53BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement54BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement55BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement56BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement57BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement58BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement59BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement5BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement60BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement61BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement62BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement63BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement64BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement65BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement66BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement67BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement68BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement69BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement6BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement70BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement71BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement72BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement73BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement74BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement75BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement76BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement77BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement78BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement79BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement7BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement80BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement81BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement82BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement83BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement84BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement85BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement86BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement87BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement88BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement89BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement8BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement90BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement91BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement92BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement93BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement94BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement95BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement96BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement97BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement98BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement99BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElement9BehaviorImpl;
import org.allaymc.server.block.impl.element.BlockElementConstructorBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockAcaciaFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockBambooFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockBirchFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockCherryFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockCrimsonFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockDarkOakFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockJungleFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockMangroveFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockNetherBrickFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockOakFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockSpruceFenceBehaviorImpl;
import org.allaymc.server.block.impl.fence.BlockWarpedFenceBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockAcaciaFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockBambooFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockBirchFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockCherryFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockCrimsonFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockDarkOakFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockJungleFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockMangroveFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockSpruceFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.fencegate.BlockWarpedFenceGateBehaviorImpl;
import org.allaymc.server.block.impl.furnace.BlockBlastFurnaceBehaviorImpl;
import org.allaymc.server.block.impl.furnace.BlockFurnaceBehaviorImpl;
import org.allaymc.server.block.impl.furnace.BlockSmokerBehaviorImpl;
import org.allaymc.server.block.impl.glass.BlockGlassBehaviorImpl;
import org.allaymc.server.block.impl.glass.BlockHardGlassBehaviorImpl;
import org.allaymc.server.block.impl.glass.BlockTintedGlassBehaviorImpl;
import org.allaymc.server.block.impl.glasspane.BlockGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.glasspane.BlockHardGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockCreeperHeadBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockDragonHeadBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockPiglinHeadBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockPlayerHeadBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockSkeletonSkullBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockWitherSkeletonSkullBehaviorImpl;
import org.allaymc.server.block.impl.head.BlockZombieHeadBehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock0BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock10BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock11BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock12BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock13BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock14BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock15BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock1BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock2BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock3BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock4BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock5BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock6BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock7BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock8BehaviorImpl;
import org.allaymc.server.block.impl.lightblock.BlockLightBlock9BehaviorImpl;
import org.allaymc.server.block.impl.liquid.BlockFlowingLavaBehaviorImpl;
import org.allaymc.server.block.impl.liquid.BlockFlowingWaterBehaviorImpl;
import org.allaymc.server.block.impl.liquid.BlockLavaBehaviorImpl;
import org.allaymc.server.block.impl.liquid.BlockWaterBehaviorImpl;
import org.allaymc.server.block.impl.piston.BlockPistonArmCollisionBehaviorImpl;
import org.allaymc.server.block.impl.piston.BlockPistonBehaviorImpl;
import org.allaymc.server.block.impl.piston.BlockStickyPistonArmCollisionBehaviorImpl;
import org.allaymc.server.block.impl.piston.BlockStickyPistonBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockAcaciaPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockBambooPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockBirchPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockCherryPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockCrimsonPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockDarkOakPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockJunglePlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockMangrovePlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockOakPlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockSprucePlanksBehaviorImpl;
import org.allaymc.server.block.impl.planks.BlockWarpedPlanksBehaviorImpl;
import org.allaymc.server.block.impl.purpur.BlockPurpurBlockBehaviorImpl;
import org.allaymc.server.block.impl.purpur.BlockPurpurPillarBehaviorImpl;
import org.allaymc.server.block.impl.sand.BlockRedSandBehaviorImpl;
import org.allaymc.server.block.impl.sand.BlockSandBehaviorImpl;
import org.allaymc.server.block.impl.sand.BlockSoulSandBehaviorImpl;
import org.allaymc.server.block.impl.sand.BlockSuspiciousSandBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockChiseledRedSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockChiseledSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockCutRedSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockCutSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockRedSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockSmoothRedSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sandstone.BlockSmoothSandstoneBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockAcaciaSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockBambooSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockBirchSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockCherrySaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockDarkOakSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockJungleSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockOakSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sapling.BlockSpruceSaplingBehaviorImpl;
import org.allaymc.server.block.impl.sign.BlockHangingSignBehaviorImpl;
import org.allaymc.server.block.impl.sign.BlockSignBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockAcaciaDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockAcaciaSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockAndesiteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockAndesiteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBambooDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBambooMosaicDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBambooMosaicSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBambooSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBirchDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBirchSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBlackstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBlackstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCherryDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCherrySlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCobbledDeepslateDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCobbledDeepslateSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCobblestoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCobblestoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCrimsonDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCrimsonSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCutRedSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCutRedSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCutSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockCutSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDarkOakDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDarkOakSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDarkPrismarineDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDarkPrismarineSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDeepslateBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDeepslateBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDeepslateTileDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDeepslateTileSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDioriteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDioriteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockEndStoneBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockEndStoneBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockExposedCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockExposedDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockGraniteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockGraniteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockJungleDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockJungleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMangroveDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMangroveSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMossyCobblestoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMossyCobblestoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMossyStoneBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMossyStoneBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMudBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockMudBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockNetherBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockNetherBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockNormalStoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockNormalStoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockOakDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockOakSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockOxidizedCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockOxidizedDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPetrifiedOakDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPetrifiedOakSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedAndesiteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedAndesiteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedBlackstoneBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedBlackstoneBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedBlackstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedBlackstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedDeepslateDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedDeepslateSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedDioriteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedDioriteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedGraniteDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedGraniteSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedTuffDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPolishedTuffSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPrismarineBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPrismarineBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPrismarineDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPrismarineSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPurpurDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockPurpurSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockQuartzDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockQuartzSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockRedNetherBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockRedNetherBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockRedSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockRedSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothQuartzDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothQuartzSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothRedSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothRedSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothSandstoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothSandstoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothStoneDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSmoothStoneSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSpruceDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockSpruceSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockStoneBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockStoneBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockTuffBrickDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockTuffBrickSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockTuffDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockTuffSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWarpedDoubleSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWarpedSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedExposedCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedExposedDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedOxidizedCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedOxidizedDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedWeatheredCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWaxedWeatheredDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWeatheredCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.slab.BlockWeatheredDoubleCutCopperSlabBehaviorImpl;
import org.allaymc.server.block.impl.sponge.BlockSpongeBehaviorImpl;
import org.allaymc.server.block.impl.sponge.BlockWetSpongeBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockBlackStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockBlueStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockBrownStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockCyanStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockGrayStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockGreenStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardBlackStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardBlueStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardBrownStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardCyanStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardGrayStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardGreenStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardLightBlueStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardLightGrayStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardLimeStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardMagentaStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardOrangeStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardPinkStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardPurpleStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardRedStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardWhiteStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockHardYellowStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockLightBlueStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockLightGrayStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockLimeStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockMagentaStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockOrangeStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockPinkStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockPurpleStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockRedStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockWhiteStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglass.BlockYellowStainedGlassBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockBlackStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockBlueStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockBrownStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockCyanStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockGrayStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockGreenStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardBlackStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardBlueStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardBrownStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardCyanStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardGrayStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardGreenStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardLightBlueStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardLightGrayStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardLimeStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardMagentaStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardOrangeStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardPinkStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardPurpleStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardRedStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardWhiteStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockHardYellowStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockLightBlueStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockLightGrayStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockLimeStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockMagentaStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockOrangeStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockPinkStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockPurpleStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockRedStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockWhiteStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.stainedglasspane.BlockYellowStainedGlassPaneBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBlackGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBlackTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBlueGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBlueTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBrownGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockBrownTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockCyanGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockCyanTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockGrayGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockGrayTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockGreenGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockGreenTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockLightBlueGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockLightBlueTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockLightGrayTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockLimeGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockLimeTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockMagentaGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockMagentaTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockOrangeGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockOrangeTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockPinkGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockPinkTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockPurpleGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockPurpleTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockRedGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockRedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockSilverGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockWhiteGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockWhiteTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockYellowGlazedTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.terracotta.BlockYellowTerracottaBehaviorImpl;
import org.allaymc.server.block.impl.tnt.BlockTntBehaviorImpl;
import org.allaymc.server.block.impl.tnt.BlockUnderwaterTntBehaviorImpl;
import org.allaymc.server.block.impl.torch.BlockColoredTorchBehaviorImpl;
import org.allaymc.server.block.impl.torch.BlockRedstoneTorchBehaviorImpl;
import org.allaymc.server.block.impl.torch.BlockSoulTorchBehaviorImpl;
import org.allaymc.server.block.impl.torch.BlockTorchBehaviorImpl;
import org.allaymc.server.block.impl.torch.BlockUnderwaterTorchBehaviorImpl;
import org.allaymc.server.block.impl.torchflower.BlockTorchflowerBehaviorImpl;
import org.allaymc.server.block.impl.torchflower.BlockTorchflowerCropBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockAcaciaTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockBambooTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockBirchTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockCherryTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockCrimsonTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockDarkOakTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockExposedCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockIronTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockJungleTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockMangroveTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockOxidizedCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockSpruceTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWarpedTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWaxedCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWaxedExposedCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWaxedOxidizedCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWaxedWeatheredCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.trapdoor.BlockWeatheredCopperTrapdoorBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockAndesiteWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockBlackstoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockCobbledDeepslateWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockCobblestoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockDeepslateBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockDeepslateTileWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockDioriteWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockEndStoneBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockGraniteWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockMossyCobblestoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockMossyStoneBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockMudBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockNetherBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockPolishedBlackstoneBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockPolishedBlackstoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockPolishedDeepslateWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockPolishedTuffWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockPrismarineWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockRedNetherBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockRedSandstoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockSandstoneWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockStoneBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockTuffBrickWallBehaviorImpl;
import org.allaymc.server.block.impl.wall.BlockTuffWallBehaviorImpl;

/**
 * Automatically generated by {@code org.allaymc.codegen.BlockInterfaceGen}
 */
public final class BlockTypeDefaultInitializer {
    public static void initAcaciaButton() {
        if (BlockTypes.ACACIA_BUTTON != null) return;
        BlockTypes.ACACIA_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaDoor() {
        if (BlockTypes.ACACIA_DOOR != null) return;
        BlockTypes.ACACIA_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initAcaciaDoubleSlab() {
        if (BlockTypes.ACACIA_DOUBLE_SLAB != null) return;
        BlockTypes.ACACIA_DOUBLE_SLAB = AllayBlockType
                .builder(BlockAcaciaDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaFence() {
        if (BlockTypes.ACACIA_FENCE != null) return;
        BlockTypes.ACACIA_FENCE = AllayBlockType
                .builder(BlockAcaciaFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_FENCE)
                .build();
    }

    public static void initAcaciaFenceGate() {
        if (BlockTypes.ACACIA_FENCE_GATE != null) return;
        BlockTypes.ACACIA_FENCE_GATE = AllayBlockType
                .builder(BlockAcaciaFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initAcaciaHangingSign() {
        if (BlockTypes.ACACIA_HANGING_SIGN != null) return;
        BlockTypes.ACACIA_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initAcaciaLeaves() {
        if (BlockTypes.ACACIA_LEAVES != null) return;
        BlockTypes.ACACIA_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAcaciaLog() {
        if (BlockTypes.ACACIA_LOG != null) return;
        BlockTypes.ACACIA_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initAcaciaPlanks() {
        if (BlockTypes.ACACIA_PLANKS != null) return;
        BlockTypes.ACACIA_PLANKS = AllayBlockType
                .builder(BlockAcaciaPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_PLANKS)
                .build();
    }

    public static void initAcaciaPressurePlate() {
        if (BlockTypes.ACACIA_PRESSURE_PLATE != null) return;
        BlockTypes.ACACIA_PRESSURE_PLATE = AllayBlockType
                .builder(BlockAcaciaPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initAcaciaSapling() {
        if (BlockTypes.ACACIA_SAPLING != null) return;
        BlockTypes.ACACIA_SAPLING = AllayBlockType
                .builder(BlockAcaciaSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initAcaciaSlab() {
        if (BlockTypes.ACACIA_SLAB != null) return;
        BlockTypes.ACACIA_SLAB = AllayBlockType
                .builder(BlockAcaciaSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaStairs() {
        if (BlockTypes.ACACIA_STAIRS != null) return;
        BlockTypes.ACACIA_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAcaciaStandingSign() {
        if (BlockTypes.ACACIA_STANDING_SIGN != null) return;
        BlockTypes.ACACIA_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initAcaciaTrapdoor() {
        if (BlockTypes.ACACIA_TRAPDOOR != null) return;
        BlockTypes.ACACIA_TRAPDOOR = AllayBlockType
                .builder(BlockAcaciaTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initAcaciaWallSign() {
        if (BlockTypes.ACACIA_WALL_SIGN != null) return;
        BlockTypes.ACACIA_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaWood() {
        if (BlockTypes.ACACIA_WOOD != null) return;
        BlockTypes.ACACIA_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initActivatorRail() {
        if (BlockTypes.ACTIVATOR_RAIL != null) return;
        BlockTypes.ACTIVATOR_RAIL = AllayBlockType
                .builder(BlockActivatorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initAir() {
        if (BlockTypes.AIR != null) return;
        BlockTypes.AIR = AllayBlockType
                .builder(BlockAirBehaviorImpl.class)
                .vanillaBlock(BlockId.AIR)
                .build();
    }

    public static void initAllium() {
        if (BlockTypes.ALLIUM != null) return;
        BlockTypes.ALLIUM = AllayBlockType
                .builder(BlockAlliumBehaviorImpl.class)
                .vanillaBlock(BlockId.ALLIUM)
                .build();
    }

    public static void initAllow() {
        if (BlockTypes.ALLOW != null) return;
        BlockTypes.ALLOW = AllayBlockType
                .builder(BlockAllowBehaviorImpl.class)
                .vanillaBlock(BlockId.ALLOW)
                .build();
    }

    public static void initAmethystBlock() {
        if (BlockTypes.AMETHYST_BLOCK != null) return;
        BlockTypes.AMETHYST_BLOCK = AllayBlockType
                .builder(BlockAmethystBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.AMETHYST_BLOCK)
                .build();
    }

    public static void initAmethystCluster() {
        if (BlockTypes.AMETHYST_CLUSTER != null) return;
        BlockTypes.AMETHYST_CLUSTER = AllayBlockType
                .builder(BlockAmethystClusterBehaviorImpl.class)
                .vanillaBlock(BlockId.AMETHYST_CLUSTER)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initAncientDebris() {
        if (BlockTypes.ANCIENT_DEBRIS != null) return;
        BlockTypes.ANCIENT_DEBRIS = AllayBlockType
                .builder(BlockAncientDebrisBehaviorImpl.class)
                .vanillaBlock(BlockId.ANCIENT_DEBRIS)
                .build();
    }

    public static void initAndesite() {
        if (BlockTypes.ANDESITE != null) return;
        BlockTypes.ANDESITE = AllayBlockType
                .builder(BlockAndesiteBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE)
                .build();
    }

    public static void initAndesiteDoubleSlab() {
        if (BlockTypes.ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.ANDESITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockAndesiteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteSlab() {
        if (BlockTypes.ANDESITE_SLAB != null) return;
        BlockTypes.ANDESITE_SLAB = AllayBlockType
                .builder(BlockAndesiteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteStairs() {
        if (BlockTypes.ANDESITE_STAIRS != null) return;
        BlockTypes.ANDESITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAndesiteWall() {
        if (BlockTypes.ANDESITE_WALL != null) return;
        BlockTypes.ANDESITE_WALL = AllayBlockType
                .builder(BlockAndesiteWallBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initAnvil() {
        if (BlockTypes.ANVIL != null) return;
        BlockTypes.ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initAzalea() {
        if (BlockTypes.AZALEA != null) return;
        BlockTypes.AZALEA = AllayBlockType
                .builder(BlockAzaleaBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA)
                .build();
    }

    public static void initAzaleaLeaves() {
        if (BlockTypes.AZALEA_LEAVES != null) return;
        BlockTypes.AZALEA_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAzaleaLeavesFlowered() {
        if (BlockTypes.AZALEA_LEAVES_FLOWERED != null) return;
        BlockTypes.AZALEA_LEAVES_FLOWERED = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES_FLOWERED)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAzureBluet() {
        if (BlockTypes.AZURE_BLUET != null) return;
        BlockTypes.AZURE_BLUET = AllayBlockType
                .builder(BlockAzureBluetBehaviorImpl.class)
                .vanillaBlock(BlockId.AZURE_BLUET)
                .build();
    }

    public static void initBamboo() {
        if (BlockTypes.BAMBOO != null) return;
        BlockTypes.BAMBOO = AllayBlockType
                .builder(BlockBambooBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO)
                .setProperties(BlockPropertyTypes.AGE_BIT, BlockPropertyTypes.BAMBOO_LEAF_SIZE, BlockPropertyTypes.BAMBOO_STALK_THICKNESS)
                .build();
    }

    public static void initBambooBlock() {
        if (BlockTypes.BAMBOO_BLOCK != null) return;
        BlockTypes.BAMBOO_BLOCK = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBambooButton() {
        if (BlockTypes.BAMBOO_BUTTON != null) return;
        BlockTypes.BAMBOO_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBambooDoor() {
        if (BlockTypes.BAMBOO_DOOR != null) return;
        BlockTypes.BAMBOO_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBambooDoubleSlab() {
        if (BlockTypes.BAMBOO_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBambooDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooFence() {
        if (BlockTypes.BAMBOO_FENCE != null) return;
        BlockTypes.BAMBOO_FENCE = AllayBlockType
                .builder(BlockBambooFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE)
                .build();
    }

    public static void initBambooFenceGate() {
        if (BlockTypes.BAMBOO_FENCE_GATE != null) return;
        BlockTypes.BAMBOO_FENCE_GATE = AllayBlockType
                .builder(BlockBambooFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBambooHangingSign() {
        if (BlockTypes.BAMBOO_HANGING_SIGN != null) return;
        BlockTypes.BAMBOO_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBambooMosaic() {
        if (BlockTypes.BAMBOO_MOSAIC != null) return;
        BlockTypes.BAMBOO_MOSAIC = AllayBlockType
                .builder(BlockBambooMosaicBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC)
                .build();
    }

    public static void initBambooMosaicDoubleSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBambooMosaicDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_SLAB = AllayBlockType
                .builder(BlockBambooMosaicSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicStairs() {
        if (BlockTypes.BAMBOO_MOSAIC_STAIRS != null) return;
        BlockTypes.BAMBOO_MOSAIC_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooPlanks() {
        if (BlockTypes.BAMBOO_PLANKS != null) return;
        BlockTypes.BAMBOO_PLANKS = AllayBlockType
                .builder(BlockBambooPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_PLANKS)
                .build();
    }

    public static void initBambooPressurePlate() {
        if (BlockTypes.BAMBOO_PRESSURE_PLATE != null) return;
        BlockTypes.BAMBOO_PRESSURE_PLATE = AllayBlockType
                .builder(BlockBambooPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBambooSapling() {
        if (BlockTypes.BAMBOO_SAPLING != null) return;
        BlockTypes.BAMBOO_SAPLING = AllayBlockType
                .builder(BlockBambooSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBambooSlab() {
        if (BlockTypes.BAMBOO_SLAB != null) return;
        BlockTypes.BAMBOO_SLAB = AllayBlockType
                .builder(BlockBambooSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooStairs() {
        if (BlockTypes.BAMBOO_STAIRS != null) return;
        BlockTypes.BAMBOO_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooStandingSign() {
        if (BlockTypes.BAMBOO_STANDING_SIGN != null) return;
        BlockTypes.BAMBOO_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBambooTrapdoor() {
        if (BlockTypes.BAMBOO_TRAPDOOR != null) return;
        BlockTypes.BAMBOO_TRAPDOOR = AllayBlockType
                .builder(BlockBambooTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBambooWallSign() {
        if (BlockTypes.BAMBOO_WALL_SIGN != null) return;
        BlockTypes.BAMBOO_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBarrel() {
        if (BlockTypes.BARREL != null) return;
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehaviorImpl.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBarrier() {
        if (BlockTypes.BARRIER != null) return;
        BlockTypes.BARRIER = AllayBlockType
                .builder(BlockBarrierBehaviorImpl.class)
                .vanillaBlock(BlockId.BARRIER)
                .build();
    }

    public static void initBasalt() {
        if (BlockTypes.BASALT != null) return;
        BlockTypes.BASALT = AllayBlockType
                .builder(BlockBasaltBehaviorImpl.class)
                .vanillaBlock(BlockId.BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBeacon() {
        if (BlockTypes.BEACON != null) return;
        BlockTypes.BEACON = AllayBlockType
                .builder(BlockBeaconBehaviorImpl.class)
                .vanillaBlock(BlockId.BEACON)
                .build();
    }

    public static void initBed() {
        if (BlockTypes.BED != null) return;
        BlockTypes.BED = AllayBlockType
                .builder(BlockBedBehaviorImpl.class)
                .vanillaBlock(BlockId.BED)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HEAD_PIECE_BIT, BlockPropertyTypes.OCCUPIED_BIT)
                .build();
    }

    public static void initBedrock() {
        if (BlockTypes.BEDROCK != null) return;
        BlockTypes.BEDROCK = AllayBlockType
                .builder(BlockBedrockBehaviorImpl.class)
                .vanillaBlock(BlockId.BEDROCK)
                .setProperties(BlockPropertyTypes.INFINIBURN_BIT)
                .build();
    }

    public static void initBeeNest() {
        if (BlockTypes.BEE_NEST != null) return;
        BlockTypes.BEE_NEST = AllayBlockType
                .builder(BlockBeeNestBehaviorImpl.class)
                .vanillaBlock(BlockId.BEE_NEST)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeehive() {
        if (BlockTypes.BEEHIVE != null) return;
        BlockTypes.BEEHIVE = AllayBlockType
                .builder(BlockBeehiveBehaviorImpl.class)
                .vanillaBlock(BlockId.BEEHIVE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeetroot() {
        if (BlockTypes.BEETROOT != null) return;
        BlockTypes.BEETROOT = AllayBlockType
                .builder(BlockBeetrootBehaviorImpl.class)
                .vanillaBlock(BlockId.BEETROOT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initBell() {
        if (BlockTypes.BELL != null) return;
        BlockTypes.BELL = AllayBlockType
                .builder(BlockBellBehaviorImpl.class)
                .vanillaBlock(BlockId.BELL)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initBigDripleaf() {
        if (BlockTypes.BIG_DRIPLEAF != null) return;
        BlockTypes.BIG_DRIPLEAF = AllayBlockType
                .builder(BlockBigDripleafBehaviorImpl.class)
                .vanillaBlock(BlockId.BIG_DRIPLEAF)
                .setProperties(BlockPropertyTypes.BIG_DRIPLEAF_HEAD, BlockPropertyTypes.BIG_DRIPLEAF_TILT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBirchButton() {
        if (BlockTypes.BIRCH_BUTTON != null) return;
        BlockTypes.BIRCH_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchDoor() {
        if (BlockTypes.BIRCH_DOOR != null) return;
        BlockTypes.BIRCH_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBirchDoubleSlab() {
        if (BlockTypes.BIRCH_DOUBLE_SLAB != null) return;
        BlockTypes.BIRCH_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBirchDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchFence() {
        if (BlockTypes.BIRCH_FENCE != null) return;
        BlockTypes.BIRCH_FENCE = AllayBlockType
                .builder(BlockBirchFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_FENCE)
                .build();
    }

    public static void initBirchFenceGate() {
        if (BlockTypes.BIRCH_FENCE_GATE != null) return;
        BlockTypes.BIRCH_FENCE_GATE = AllayBlockType
                .builder(BlockBirchFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBirchHangingSign() {
        if (BlockTypes.BIRCH_HANGING_SIGN != null) return;
        BlockTypes.BIRCH_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBirchLeaves() {
        if (BlockTypes.BIRCH_LEAVES != null) return;
        BlockTypes.BIRCH_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initBirchLog() {
        if (BlockTypes.BIRCH_LOG != null) return;
        BlockTypes.BIRCH_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBirchPlanks() {
        if (BlockTypes.BIRCH_PLANKS != null) return;
        BlockTypes.BIRCH_PLANKS = AllayBlockType
                .builder(BlockBirchPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_PLANKS)
                .build();
    }

    public static void initBirchPressurePlate() {
        if (BlockTypes.BIRCH_PRESSURE_PLATE != null) return;
        BlockTypes.BIRCH_PRESSURE_PLATE = AllayBlockType
                .builder(BlockBirchPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBirchSapling() {
        if (BlockTypes.BIRCH_SAPLING != null) return;
        BlockTypes.BIRCH_SAPLING = AllayBlockType
                .builder(BlockBirchSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBirchSlab() {
        if (BlockTypes.BIRCH_SLAB != null) return;
        BlockTypes.BIRCH_SLAB = AllayBlockType
                .builder(BlockBirchSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchStairs() {
        if (BlockTypes.BIRCH_STAIRS != null) return;
        BlockTypes.BIRCH_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBirchStandingSign() {
        if (BlockTypes.BIRCH_STANDING_SIGN != null) return;
        BlockTypes.BIRCH_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBirchTrapdoor() {
        if (BlockTypes.BIRCH_TRAPDOOR != null) return;
        BlockTypes.BIRCH_TRAPDOOR = AllayBlockType
                .builder(BlockBirchTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBirchWallSign() {
        if (BlockTypes.BIRCH_WALL_SIGN != null) return;
        BlockTypes.BIRCH_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchWood() {
        if (BlockTypes.BIRCH_WOOD != null) return;
        BlockTypes.BIRCH_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBlackCandle() {
        if (BlockTypes.BLACK_CANDLE != null) return;
        BlockTypes.BLACK_CANDLE = AllayBlockType
                .builder(BlockBlackCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCandleCake() {
        if (BlockTypes.BLACK_CANDLE_CAKE != null) return;
        BlockTypes.BLACK_CANDLE_CAKE = AllayBlockType
                .builder(BlockBlackCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCarpet() {
        if (BlockTypes.BLACK_CARPET != null) return;
        BlockTypes.BLACK_CARPET = AllayBlockType
                .builder(BlockBlackCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CARPET)
                .build();
    }

    public static void initBlackConcrete() {
        if (BlockTypes.BLACK_CONCRETE != null) return;
        BlockTypes.BLACK_CONCRETE = AllayBlockType
                .builder(BlockBlackConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE)
                .build();
    }

    public static void initBlackConcretePowder() {
        if (BlockTypes.BLACK_CONCRETE_POWDER != null) return;
        BlockTypes.BLACK_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBlackConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE_POWDER)
                .build();
    }

    public static void initBlackGlazedTerracotta() {
        if (BlockTypes.BLACK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLACK_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBlackGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBlackShulkerBox() {
        if (BlockTypes.BLACK_SHULKER_BOX != null) return;
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .build();
    }

    public static void initBlackStainedGlass() {
        if (BlockTypes.BLACK_STAINED_GLASS != null) return;
        BlockTypes.BLACK_STAINED_GLASS = AllayBlockType
                .builder(BlockBlackStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS)
                .build();
    }

    public static void initBlackStainedGlassPane() {
        if (BlockTypes.BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLACK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBlackStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlackTerracotta() {
        if (BlockTypes.BLACK_TERRACOTTA != null) return;
        BlockTypes.BLACK_TERRACOTTA = AllayBlockType
                .builder(BlockBlackTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_TERRACOTTA)
                .build();
    }

    public static void initBlackWool() {
        if (BlockTypes.BLACK_WOOL != null) return;
        BlockTypes.BLACK_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_WOOL)
                .build();
    }

    public static void initBlackstone() {
        if (BlockTypes.BLACKSTONE != null) return;
        BlockTypes.BLACKSTONE = AllayBlockType
                .builder(BlockBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE)
                .build();
    }

    public static void initBlackstoneDoubleSlab() {
        if (BlockTypes.BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBlackstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneSlab() {
        if (BlockTypes.BLACKSTONE_SLAB != null) return;
        BlockTypes.BLACKSTONE_SLAB = AllayBlockType
                .builder(BlockBlackstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneStairs() {
        if (BlockTypes.BLACKSTONE_STAIRS != null) return;
        BlockTypes.BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBlackstoneWall() {
        if (BlockTypes.BLACKSTONE_WALL != null) return;
        BlockTypes.BLACKSTONE_WALL = AllayBlockType
                .builder(BlockBlackstoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBlastFurnace() {
        if (BlockTypes.BLAST_FURNACE != null) return;
        BlockTypes.BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBlueCandle() {
        if (BlockTypes.BLUE_CANDLE != null) return;
        BlockTypes.BLUE_CANDLE = AllayBlockType
                .builder(BlockBlueCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCandleCake() {
        if (BlockTypes.BLUE_CANDLE_CAKE != null) return;
        BlockTypes.BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockBlueCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCarpet() {
        if (BlockTypes.BLUE_CARPET != null) return;
        BlockTypes.BLUE_CARPET = AllayBlockType
                .builder(BlockBlueCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CARPET)
                .build();
    }

    public static void initBlueConcrete() {
        if (BlockTypes.BLUE_CONCRETE != null) return;
        BlockTypes.BLUE_CONCRETE = AllayBlockType
                .builder(BlockBlueConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE)
                .build();
    }

    public static void initBlueConcretePowder() {
        if (BlockTypes.BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.BLUE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBlueConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initBlueGlazedTerracotta() {
        if (BlockTypes.BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLUE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBlueGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBlueIce() {
        if (BlockTypes.BLUE_ICE != null) return;
        BlockTypes.BLUE_ICE = AllayBlockType
                .builder(BlockBlueIceBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_ICE)
                .build();
    }

    public static void initBlueOrchid() {
        if (BlockTypes.BLUE_ORCHID != null) return;
        BlockTypes.BLUE_ORCHID = AllayBlockType
                .builder(BlockBlueOrchidBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_ORCHID)
                .build();
    }

    public static void initBlueShulkerBox() {
        if (BlockTypes.BLUE_SHULKER_BOX != null) return;
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .build();
    }

    public static void initBlueStainedGlass() {
        if (BlockTypes.BLUE_STAINED_GLASS != null) return;
        BlockTypes.BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockBlueStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS)
                .build();
    }

    public static void initBlueStainedGlassPane() {
        if (BlockTypes.BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBlueStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlueTerracotta() {
        if (BlockTypes.BLUE_TERRACOTTA != null) return;
        BlockTypes.BLUE_TERRACOTTA = AllayBlockType
                .builder(BlockBlueTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_TERRACOTTA)
                .build();
    }

    public static void initBlueWool() {
        if (BlockTypes.BLUE_WOOL != null) return;
        BlockTypes.BLUE_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_WOOL)
                .build();
    }

    public static void initBoneBlock() {
        if (BlockTypes.BONE_BLOCK != null) return;
        BlockTypes.BONE_BLOCK = AllayBlockType
                .builder(BlockBoneBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BONE_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBookshelf() {
        if (BlockTypes.BOOKSHELF != null) return;
        BlockTypes.BOOKSHELF = AllayBlockType
                .builder(BlockBookshelfBehaviorImpl.class)
                .vanillaBlock(BlockId.BOOKSHELF)
                .build();
    }

    public static void initBorderBlock() {
        if (BlockTypes.BORDER_BLOCK != null) return;
        BlockTypes.BORDER_BLOCK = AllayBlockType
                .builder(BlockBorderBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BORDER_BLOCK)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBrainCoral() {
        if (BlockTypes.BRAIN_CORAL != null) return;
        BlockTypes.BRAIN_CORAL = AllayBlockType
                .builder(BlockBrainCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.BRAIN_CORAL)
                .build();
    }

    public static void initBrainCoralBlock() {
        if (BlockTypes.BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.BRAIN_CORAL_BLOCK = AllayBlockType
                .builder(BlockBrainCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initBrainCoralFan() {
        if (BlockTypes.BRAIN_CORAL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_FAN = AllayBlockType
                .builder(BlockBrainCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBrainCoralWallFan() {
        if (BlockTypes.BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockBrainCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBrewingStand() {
        if (BlockTypes.BREWING_STAND != null) return;
        BlockTypes.BREWING_STAND = AllayBlockType
                .builder(BlockBrewingStandBehaviorImpl.class)
                .vanillaBlock(BlockId.BREWING_STAND)
                .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                .build();
    }

    public static void initBrickBlock() {
        if (BlockTypes.BRICK_BLOCK != null) return;
        BlockTypes.BRICK_BLOCK = AllayBlockType
                .builder(BlockBrickBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_BLOCK)
                .build();
    }

    public static void initBrickDoubleSlab() {
        if (BlockTypes.BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickSlab() {
        if (BlockTypes.BRICK_SLAB != null) return;
        BlockTypes.BRICK_SLAB = AllayBlockType
                .builder(BlockBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickStairs() {
        if (BlockTypes.BRICK_STAIRS != null) return;
        BlockTypes.BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBrickWall() {
        if (BlockTypes.BRICK_WALL != null) return;
        BlockTypes.BRICK_WALL = AllayBlockType
                .builder(BlockBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBrownCandle() {
        if (BlockTypes.BROWN_CANDLE != null) return;
        BlockTypes.BROWN_CANDLE = AllayBlockType
                .builder(BlockBrownCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCandleCake() {
        if (BlockTypes.BROWN_CANDLE_CAKE != null) return;
        BlockTypes.BROWN_CANDLE_CAKE = AllayBlockType
                .builder(BlockBrownCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCarpet() {
        if (BlockTypes.BROWN_CARPET != null) return;
        BlockTypes.BROWN_CARPET = AllayBlockType
                .builder(BlockBrownCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CARPET)
                .build();
    }

    public static void initBrownConcrete() {
        if (BlockTypes.BROWN_CONCRETE != null) return;
        BlockTypes.BROWN_CONCRETE = AllayBlockType
                .builder(BlockBrownConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE)
                .build();
    }

    public static void initBrownConcretePowder() {
        if (BlockTypes.BROWN_CONCRETE_POWDER != null) return;
        BlockTypes.BROWN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBrownConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE_POWDER)
                .build();
    }

    public static void initBrownGlazedTerracotta() {
        if (BlockTypes.BROWN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BROWN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBrownGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBrownMushroom() {
        if (BlockTypes.BROWN_MUSHROOM != null) return;
        BlockTypes.BROWN_MUSHROOM = AllayBlockType
                .builder(BlockBrownMushroomBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM)
                .build();
    }

    public static void initBrownMushroomBlock() {
        if (BlockTypes.BROWN_MUSHROOM_BLOCK != null) return;
        BlockTypes.BROWN_MUSHROOM_BLOCK = AllayBlockType
                .builder(BlockBrownMushroomBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initBrownShulkerBox() {
        if (BlockTypes.BROWN_SHULKER_BOX != null) return;
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .build();
    }

    public static void initBrownStainedGlass() {
        if (BlockTypes.BROWN_STAINED_GLASS != null) return;
        BlockTypes.BROWN_STAINED_GLASS = AllayBlockType
                .builder(BlockBrownStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS)
                .build();
    }

    public static void initBrownStainedGlassPane() {
        if (BlockTypes.BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.BROWN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBrownStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBrownTerracotta() {
        if (BlockTypes.BROWN_TERRACOTTA != null) return;
        BlockTypes.BROWN_TERRACOTTA = AllayBlockType
                .builder(BlockBrownTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_TERRACOTTA)
                .build();
    }

    public static void initBrownWool() {
        if (BlockTypes.BROWN_WOOL != null) return;
        BlockTypes.BROWN_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_WOOL)
                .build();
    }

    public static void initBubbleColumn() {
        if (BlockTypes.BUBBLE_COLUMN != null) return;
        BlockTypes.BUBBLE_COLUMN = AllayBlockType
                .builder(BlockBubbleColumnBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_COLUMN)
                .setProperties(BlockPropertyTypes.DRAG_DOWN)
                .build();
    }

    public static void initBubbleCoral() {
        if (BlockTypes.BUBBLE_CORAL != null) return;
        BlockTypes.BUBBLE_CORAL = AllayBlockType
                .builder(BlockBubbleCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL)
                .build();
    }

    public static void initBubbleCoralBlock() {
        if (BlockTypes.BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.BUBBLE_CORAL_BLOCK = AllayBlockType
                .builder(BlockBubbleCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initBubbleCoralFan() {
        if (BlockTypes.BUBBLE_CORAL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_FAN = AllayBlockType
                .builder(BlockBubbleCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBubbleCoralWallFan() {
        if (BlockTypes.BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockBubbleCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBuddingAmethyst() {
        if (BlockTypes.BUDDING_AMETHYST != null) return;
        BlockTypes.BUDDING_AMETHYST = AllayBlockType
                .builder(BlockBuddingAmethystBehaviorImpl.class)
                .vanillaBlock(BlockId.BUDDING_AMETHYST)
                .build();
    }

    public static void initCactus() {
        if (BlockTypes.CACTUS != null) return;
        BlockTypes.CACTUS = AllayBlockType
                .builder(BlockCactusBehaviorImpl.class)
                .vanillaBlock(BlockId.CACTUS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initCake() {
        if (BlockTypes.CAKE != null) return;
        BlockTypes.CAKE = AllayBlockType
                .builder(BlockCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CAKE)
                .setProperties(BlockPropertyTypes.BITE_COUNTER)
                .build();
    }

    public static void initCalcite() {
        if (BlockTypes.CALCITE != null) return;
        BlockTypes.CALCITE = AllayBlockType
                .builder(BlockCalciteBehaviorImpl.class)
                .vanillaBlock(BlockId.CALCITE)
                .build();
    }

    public static void initCalibratedSculkSensor() {
        if (BlockTypes.CALIBRATED_SCULK_SENSOR != null) return;
        BlockTypes.CALIBRATED_SCULK_SENSOR = AllayBlockType
                .builder(BlockCalibratedSculkSensorBehaviorImpl.class)
                .vanillaBlock(BlockId.CALIBRATED_SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initCamera() {
        if (BlockTypes.CAMERA != null) return;
        BlockTypes.CAMERA = AllayBlockType
                .builder(BlockCameraBehaviorImpl.class)
                .vanillaBlock(BlockId.CAMERA)
                .build();
    }

    public static void initCampfire() {
        if (BlockTypes.CAMPFIRE != null) return;
        BlockTypes.CAMPFIRE = AllayBlockType
                .builder(BlockCampfireBehaviorImpl.class)
                .vanillaBlock(BlockId.CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCandle() {
        if (BlockTypes.CANDLE != null) return;
        BlockTypes.CANDLE = AllayBlockType
                .builder(BlockCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCandleCake() {
        if (BlockTypes.CANDLE_CAKE != null) return;
        BlockTypes.CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCarrots() {
        if (BlockTypes.CARROTS != null) return;
        BlockTypes.CARROTS = AllayBlockType
                .builder(BlockCarrotsBehaviorImpl.class)
                .vanillaBlock(BlockId.CARROTS)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initCartographyTable() {
        if (BlockTypes.CARTOGRAPHY_TABLE != null) return;
        BlockTypes.CARTOGRAPHY_TABLE = AllayBlockType
                .builder(BlockCartographyTableBehaviorImpl.class)
                .vanillaBlock(BlockId.CARTOGRAPHY_TABLE)
                .build();
    }

    public static void initCarvedPumpkin() {
        if (BlockTypes.CARVED_PUMPKIN != null) return;
        BlockTypes.CARVED_PUMPKIN = AllayBlockType
                .builder(BlockCarvedPumpkinBehaviorImpl.class)
                .vanillaBlock(BlockId.CARVED_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCauldron() {
        if (BlockTypes.CAULDRON != null) return;
        BlockTypes.CAULDRON = AllayBlockType
                .builder(BlockCauldronBehaviorImpl.class)
                .vanillaBlock(BlockId.CAULDRON)
                .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                .build();
    }

    public static void initCaveVines() {
        if (BlockTypes.CAVE_VINES != null) return;
        BlockTypes.CAVE_VINES = AllayBlockType
                .builder(BlockCaveVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesBodyWithBerries() {
        if (BlockTypes.CAVE_VINES_BODY_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_BODY_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesBodyWithBerriesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES_BODY_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesHeadWithBerries() {
        if (BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesHeadWithBerriesBehaviorImpl.class)
                .vanillaBlock(BlockId.CAVE_VINES_HEAD_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initChain() {
        if (BlockTypes.CHAIN != null) return;
        BlockTypes.CHAIN = AllayBlockType
                .builder(BlockChainBehaviorImpl.class)
                .vanillaBlock(BlockId.CHAIN)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChainCommandBlock() {
        if (BlockTypes.CHAIN_COMMAND_BLOCK != null) return;
        BlockTypes.CHAIN_COMMAND_BLOCK = AllayBlockType
                .builder(BlockChainCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.CHAIN_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initChemicalHeat() {
        if (BlockTypes.CHEMICAL_HEAT != null) return;
        BlockTypes.CHEMICAL_HEAT = AllayBlockType
                .builder(BlockChemicalHeatBehaviorImpl.class)
                .vanillaBlock(BlockId.CHEMICAL_HEAT)
                .build();
    }

    public static void initCherryButton() {
        if (BlockTypes.CHERRY_BUTTON != null) return;
        BlockTypes.CHERRY_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryDoor() {
        if (BlockTypes.CHERRY_DOOR != null) return;
        BlockTypes.CHERRY_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCherryDoubleSlab() {
        if (BlockTypes.CHERRY_DOUBLE_SLAB != null) return;
        BlockTypes.CHERRY_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCherryDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryFence() {
        if (BlockTypes.CHERRY_FENCE != null) return;
        BlockTypes.CHERRY_FENCE = AllayBlockType
                .builder(BlockCherryFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_FENCE)
                .build();
    }

    public static void initCherryFenceGate() {
        if (BlockTypes.CHERRY_FENCE_GATE != null) return;
        BlockTypes.CHERRY_FENCE_GATE = AllayBlockType
                .builder(BlockCherryFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCherryHangingSign() {
        if (BlockTypes.CHERRY_HANGING_SIGN != null) return;
        BlockTypes.CHERRY_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCherryLeaves() {
        if (BlockTypes.CHERRY_LEAVES != null) return;
        BlockTypes.CHERRY_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initCherryLog() {
        if (BlockTypes.CHERRY_LOG != null) return;
        BlockTypes.CHERRY_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCherryPlanks() {
        if (BlockTypes.CHERRY_PLANKS != null) return;
        BlockTypes.CHERRY_PLANKS = AllayBlockType
                .builder(BlockCherryPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_PLANKS)
                .build();
    }

    public static void initCherryPressurePlate() {
        if (BlockTypes.CHERRY_PRESSURE_PLATE != null) return;
        BlockTypes.CHERRY_PRESSURE_PLATE = AllayBlockType
                .builder(BlockCherryPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCherrySapling() {
        if (BlockTypes.CHERRY_SAPLING != null) return;
        BlockTypes.CHERRY_SAPLING = AllayBlockType
                .builder(BlockCherrySaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initCherrySlab() {
        if (BlockTypes.CHERRY_SLAB != null) return;
        BlockTypes.CHERRY_SLAB = AllayBlockType
                .builder(BlockCherrySlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryStairs() {
        if (BlockTypes.CHERRY_STAIRS != null) return;
        BlockTypes.CHERRY_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCherryStandingSign() {
        if (BlockTypes.CHERRY_STANDING_SIGN != null) return;
        BlockTypes.CHERRY_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initCherryTrapdoor() {
        if (BlockTypes.CHERRY_TRAPDOOR != null) return;
        BlockTypes.CHERRY_TRAPDOOR = AllayBlockType
                .builder(BlockCherryTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCherryWallSign() {
        if (BlockTypes.CHERRY_WALL_SIGN != null) return;
        BlockTypes.CHERRY_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryWood() {
        if (BlockTypes.CHERRY_WOOD != null) return;
        BlockTypes.CHERRY_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChest() {
        if (BlockTypes.CHEST != null) return;
        BlockTypes.CHEST = AllayBlockType
                .builder(BlockChestBehaviorImpl.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChippedAnvil() {
        if (BlockTypes.CHIPPED_ANVIL != null) return;
        BlockTypes.CHIPPED_ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.CHIPPED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChiseledBookshelf() {
        if (BlockTypes.CHISELED_BOOKSHELF != null) return;
        BlockTypes.CHISELED_BOOKSHELF = AllayBlockType
                .builder(BlockChiseledBookshelfBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_BOOKSHELF)
                .setProperties(BlockPropertyTypes.BOOKS_STORED, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initChiseledCopper() {
        if (BlockTypes.CHISELED_COPPER != null) return;
        BlockTypes.CHISELED_COPPER = AllayBlockType
                .builder(BlockChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_COPPER)
                .build();
    }

    public static void initChiseledDeepslate() {
        if (BlockTypes.CHISELED_DEEPSLATE != null) return;
        BlockTypes.CHISELED_DEEPSLATE = AllayBlockType
                .builder(BlockChiseledDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_DEEPSLATE)
                .build();
    }

    public static void initChiseledNetherBricks() {
        if (BlockTypes.CHISELED_NETHER_BRICKS != null) return;
        BlockTypes.CHISELED_NETHER_BRICKS = AllayBlockType
                .builder(BlockChiseledNetherBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_NETHER_BRICKS)
                .build();
    }

    public static void initChiseledPolishedBlackstone() {
        if (BlockTypes.CHISELED_POLISHED_BLACKSTONE != null) return;
        BlockTypes.CHISELED_POLISHED_BLACKSTONE = AllayBlockType
                .builder(BlockChiseledPolishedBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_POLISHED_BLACKSTONE)
                .build();
    }

    public static void initChiseledQuartzBlock() {
        if (BlockTypes.CHISELED_QUARTZ_BLOCK != null) return;
        BlockTypes.CHISELED_QUARTZ_BLOCK = AllayBlockType
                .builder(BlockChiseledQuartzBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChiseledRedSandstone() {
        if (BlockTypes.CHISELED_RED_SANDSTONE != null) return;
        BlockTypes.CHISELED_RED_SANDSTONE = AllayBlockType
                .builder(BlockChiseledRedSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_RED_SANDSTONE)
                .build();
    }

    public static void initChiseledSandstone() {
        if (BlockTypes.CHISELED_SANDSTONE != null) return;
        BlockTypes.CHISELED_SANDSTONE = AllayBlockType
                .builder(BlockChiseledSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_SANDSTONE)
                .build();
    }

    public static void initChiseledStoneBricks() {
        if (BlockTypes.CHISELED_STONE_BRICKS != null) return;
        BlockTypes.CHISELED_STONE_BRICKS = AllayBlockType
                .builder(BlockChiseledStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initChiseledTuff() {
        if (BlockTypes.CHISELED_TUFF != null) return;
        BlockTypes.CHISELED_TUFF = AllayBlockType
                .builder(BlockChiseledTuffBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_TUFF)
                .build();
    }

    public static void initChiseledTuffBricks() {
        if (BlockTypes.CHISELED_TUFF_BRICKS != null) return;
        BlockTypes.CHISELED_TUFF_BRICKS = AllayBlockType
                .builder(BlockChiseledTuffBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CHISELED_TUFF_BRICKS)
                .build();
    }

    public static void initChorusFlower() {
        if (BlockTypes.CHORUS_FLOWER != null) return;
        BlockTypes.CHORUS_FLOWER = AllayBlockType
                .builder(BlockChorusFlowerBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_FLOWER)
                .setProperties(BlockPropertyTypes.AGE_6)
                .build();
    }

    public static void initChorusPlant() {
        if (BlockTypes.CHORUS_PLANT != null) return;
        BlockTypes.CHORUS_PLANT = AllayBlockType
                .builder(BlockChorusPlantBehaviorImpl.class)
                .vanillaBlock(BlockId.CHORUS_PLANT)
                .build();
    }

    public static void initClay() {
        if (BlockTypes.CLAY != null) return;
        BlockTypes.CLAY = AllayBlockType
                .builder(BlockClayBehaviorImpl.class)
                .vanillaBlock(BlockId.CLAY)
                .build();
    }

    public static void initClientRequestPlaceholderBlock() {
        if (BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK != null) return;
        BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = AllayBlockType
                .builder(BlockClientRequestPlaceholderBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                .build();
    }

    public static void initCoalBlock() {
        if (BlockTypes.COAL_BLOCK != null) return;
        BlockTypes.COAL_BLOCK = AllayBlockType
                .builder(BlockCoalBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.COAL_BLOCK)
                .build();
    }

    public static void initCoalOre() {
        if (BlockTypes.COAL_ORE != null) return;
        BlockTypes.COAL_ORE = AllayBlockType
                .builder(BlockCoalOreBehaviorImpl.class)
                .vanillaBlock(BlockId.COAL_ORE)
                .build();
    }

    public static void initCoarseDirt() {
        if (BlockTypes.COARSE_DIRT != null) return;
        BlockTypes.COARSE_DIRT = AllayBlockType
                .builder(BlockCoarseDirtBehaviorImpl.class)
                .vanillaBlock(BlockId.COARSE_DIRT)
                .build();
    }

    public static void initCobbledDeepslate() {
        if (BlockTypes.COBBLED_DEEPSLATE != null) return;
        BlockTypes.COBBLED_DEEPSLATE = AllayBlockType
                .builder(BlockCobbledDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE)
                .build();
    }

    public static void initCobbledDeepslateDoubleSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCobbledDeepslateDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_SLAB = AllayBlockType
                .builder(BlockCobbledDeepslateSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateStairs() {
        if (BlockTypes.COBBLED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCobbledDeepslateWall() {
        if (BlockTypes.COBBLED_DEEPSLATE_WALL != null) return;
        BlockTypes.COBBLED_DEEPSLATE_WALL = AllayBlockType
                .builder(BlockCobbledDeepslateWallBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initCobblestone() {
        if (BlockTypes.COBBLESTONE != null) return;
        BlockTypes.COBBLESTONE = AllayBlockType
                .builder(BlockCobblestoneBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLESTONE)
                .build();
    }

    public static void initCobblestoneDoubleSlab() {
        if (BlockTypes.COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCobblestoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneSlab() {
        if (BlockTypes.COBBLESTONE_SLAB != null) return;
        BlockTypes.COBBLESTONE_SLAB = AllayBlockType
                .builder(BlockCobblestoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneWall() {
        if (BlockTypes.COBBLESTONE_WALL != null) return;
        BlockTypes.COBBLESTONE_WALL = AllayBlockType
                .builder(BlockCobblestoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLESTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initCocoa() {
        if (BlockTypes.COCOA != null) return;
        BlockTypes.COCOA = AllayBlockType
                .builder(BlockCocoaBehaviorImpl.class)
                .vanillaBlock(BlockId.COCOA)
                .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initColoredTorchBlue() {
        if (BlockTypes.COLORED_TORCH_BLUE != null) return;
        BlockTypes.COLORED_TORCH_BLUE = AllayBlockType
                .builder(BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.COLORED_TORCH_BLUE)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchGreen() {
        if (BlockTypes.COLORED_TORCH_GREEN != null) return;
        BlockTypes.COLORED_TORCH_GREEN = AllayBlockType
                .builder(BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.COLORED_TORCH_GREEN)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchPurple() {
        if (BlockTypes.COLORED_TORCH_PURPLE != null) return;
        BlockTypes.COLORED_TORCH_PURPLE = AllayBlockType
                .builder(BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.COLORED_TORCH_PURPLE)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchRed() {
        if (BlockTypes.COLORED_TORCH_RED != null) return;
        BlockTypes.COLORED_TORCH_RED = AllayBlockType
                .builder(BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.COLORED_TORCH_RED)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initCommandBlock() {
        if (BlockTypes.COMMAND_BLOCK != null) return;
        BlockTypes.COMMAND_BLOCK = AllayBlockType
                .builder(BlockCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initComposter() {
        if (BlockTypes.COMPOSTER != null) return;
        BlockTypes.COMPOSTER = AllayBlockType
                .builder(BlockComposterBehaviorImpl.class)
                .vanillaBlock(BlockId.COMPOSTER)
                .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                .build();
    }

    public static void initCompoundCreator() {
        if (BlockTypes.COMPOUND_CREATOR != null) return;
        BlockTypes.COMPOUND_CREATOR = AllayBlockType
                .builder(BlockCompoundCreatorBehaviorImpl.class)
                .vanillaBlock(BlockId.COMPOUND_CREATOR)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initConduit() {
        if (BlockTypes.CONDUIT != null) return;
        BlockTypes.CONDUIT = AllayBlockType
                .builder(BlockConduitBehaviorImpl.class)
                .vanillaBlock(BlockId.CONDUIT)
                .build();
    }

    public static void initCopperBlock() {
        if (BlockTypes.COPPER_BLOCK != null) return;
        BlockTypes.COPPER_BLOCK = AllayBlockType
                .builder(BlockCopperBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_BLOCK)
                .build();
    }

    public static void initCopperBulb() {
        if (BlockTypes.COPPER_BULB != null) return;
        BlockTypes.COPPER_BULB = AllayBlockType
                .builder(BlockCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initCopperDoor() {
        if (BlockTypes.COPPER_DOOR != null) return;
        BlockTypes.COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCopperGrate() {
        if (BlockTypes.COPPER_GRATE != null) return;
        BlockTypes.COPPER_GRATE = AllayBlockType
                .builder(BlockCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_GRATE)
                .build();
    }

    public static void initCopperOre() {
        if (BlockTypes.COPPER_ORE != null) return;
        BlockTypes.COPPER_ORE = AllayBlockType
                .builder(BlockCopperOreBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_ORE)
                .build();
    }

    public static void initCopperTrapdoor() {
        if (BlockTypes.COPPER_TRAPDOOR != null) return;
        BlockTypes.COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCornflower() {
        if (BlockTypes.CORNFLOWER != null) return;
        BlockTypes.CORNFLOWER = AllayBlockType
                .builder(BlockCornflowerBehaviorImpl.class)
                .vanillaBlock(BlockId.CORNFLOWER)
                .build();
    }

    public static void initCrackedDeepslateBricks() {
        if (BlockTypes.CRACKED_DEEPSLATE_BRICKS != null) return;
        BlockTypes.CRACKED_DEEPSLATE_BRICKS = AllayBlockType
                .builder(BlockCrackedDeepslateBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_BRICKS)
                .build();
    }

    public static void initCrackedDeepslateTiles() {
        if (BlockTypes.CRACKED_DEEPSLATE_TILES != null) return;
        BlockTypes.CRACKED_DEEPSLATE_TILES = AllayBlockType
                .builder(BlockCrackedDeepslateTilesBehaviorImpl.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_TILES)
                .build();
    }

    public static void initCrackedNetherBricks() {
        if (BlockTypes.CRACKED_NETHER_BRICKS != null) return;
        BlockTypes.CRACKED_NETHER_BRICKS = AllayBlockType
                .builder(BlockCrackedNetherBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CRACKED_NETHER_BRICKS)
                .build();
    }

    public static void initCrackedPolishedBlackstoneBricks() {
        if (BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                .builder(BlockCrackedPolishedBlackstoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initCrackedStoneBricks() {
        if (BlockTypes.CRACKED_STONE_BRICKS != null) return;
        BlockTypes.CRACKED_STONE_BRICKS = AllayBlockType
                .builder(BlockCrackedStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initCrafter() {
        if (BlockTypes.CRAFTER != null) return;
        BlockTypes.CRAFTER = AllayBlockType
                .builder(BlockCrafterBehaviorImpl.class)
                .vanillaBlock(BlockId.CRAFTER)
                .setProperties(BlockPropertyTypes.CRAFTING, BlockPropertyTypes.ORIENTATION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initCraftingTable() {
        if (BlockTypes.CRAFTING_TABLE != null) return;
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .build();
    }

    public static void initCreeperHead() {
        if (BlockTypes.CREEPER_HEAD != null) return;
        BlockTypes.CREEPER_HEAD = AllayBlockType
                .builder(BlockCreeperHeadBehaviorImpl.class)
                .vanillaBlock(BlockId.CREEPER_HEAD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCrimsonButton() {
        if (BlockTypes.CRIMSON_BUTTON != null) return;
        BlockTypes.CRIMSON_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCrimsonDoor() {
        if (BlockTypes.CRIMSON_DOOR != null) return;
        BlockTypes.CRIMSON_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCrimsonDoubleSlab() {
        if (BlockTypes.CRIMSON_DOUBLE_SLAB != null) return;
        BlockTypes.CRIMSON_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCrimsonDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonFence() {
        if (BlockTypes.CRIMSON_FENCE != null) return;
        BlockTypes.CRIMSON_FENCE = AllayBlockType
                .builder(BlockCrimsonFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE)
                .build();
    }

    public static void initCrimsonFenceGate() {
        if (BlockTypes.CRIMSON_FENCE_GATE != null) return;
        BlockTypes.CRIMSON_FENCE_GATE = AllayBlockType
                .builder(BlockCrimsonFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCrimsonFungus() {
        if (BlockTypes.CRIMSON_FUNGUS != null) return;
        BlockTypes.CRIMSON_FUNGUS = AllayBlockType
                .builder(BlockCrimsonFungusBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                .build();
    }

    public static void initCrimsonHangingSign() {
        if (BlockTypes.CRIMSON_HANGING_SIGN != null) return;
        BlockTypes.CRIMSON_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCrimsonHyphae() {
        if (BlockTypes.CRIMSON_HYPHAE != null) return;
        BlockTypes.CRIMSON_HYPHAE = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCrimsonNylium() {
        if (BlockTypes.CRIMSON_NYLIUM != null) return;
        BlockTypes.CRIMSON_NYLIUM = AllayBlockType
                .builder(BlockCrimsonNyliumBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_NYLIUM)
                .build();
    }

    public static void initCrimsonPlanks() {
        if (BlockTypes.CRIMSON_PLANKS != null) return;
        BlockTypes.CRIMSON_PLANKS = AllayBlockType
                .builder(BlockCrimsonPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_PLANKS)
                .build();
    }

    public static void initCrimsonPressurePlate() {
        if (BlockTypes.CRIMSON_PRESSURE_PLATE != null) return;
        BlockTypes.CRIMSON_PRESSURE_PLATE = AllayBlockType
                .builder(BlockCrimsonPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCrimsonRoots() {
        if (BlockTypes.CRIMSON_ROOTS != null) return;
        BlockTypes.CRIMSON_ROOTS = AllayBlockType
                .builder(BlockCrimsonRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_ROOTS)
                .build();
    }

    public static void initCrimsonSlab() {
        if (BlockTypes.CRIMSON_SLAB != null) return;
        BlockTypes.CRIMSON_SLAB = AllayBlockType
                .builder(BlockCrimsonSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonStairs() {
        if (BlockTypes.CRIMSON_STAIRS != null) return;
        BlockTypes.CRIMSON_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCrimsonStandingSign() {
        if (BlockTypes.CRIMSON_STANDING_SIGN != null) return;
        BlockTypes.CRIMSON_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initCrimsonStem() {
        if (BlockTypes.CRIMSON_STEM != null) return;
        BlockTypes.CRIMSON_STEM = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCrimsonTrapdoor() {
        if (BlockTypes.CRIMSON_TRAPDOOR != null) return;
        BlockTypes.CRIMSON_TRAPDOOR = AllayBlockType
                .builder(BlockCrimsonTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCrimsonWallSign() {
        if (BlockTypes.CRIMSON_WALL_SIGN != null) return;
        BlockTypes.CRIMSON_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCryingObsidian() {
        if (BlockTypes.CRYING_OBSIDIAN != null) return;
        BlockTypes.CRYING_OBSIDIAN = AllayBlockType
                .builder(BlockCryingObsidianBehaviorImpl.class)
                .vanillaBlock(BlockId.CRYING_OBSIDIAN)
                .build();
    }

    public static void initCutCopper() {
        if (BlockTypes.CUT_COPPER != null) return;
        BlockTypes.CUT_COPPER = AllayBlockType
                .builder(BlockCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_COPPER)
                .build();
    }

    public static void initCutCopperSlab() {
        if (BlockTypes.CUT_COPPER_SLAB != null) return;
        BlockTypes.CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutCopperStairs() {
        if (BlockTypes.CUT_COPPER_STAIRS != null) return;
        BlockTypes.CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCutRedSandstone() {
        if (BlockTypes.CUT_RED_SANDSTONE != null) return;
        BlockTypes.CUT_RED_SANDSTONE = AllayBlockType
                .builder(BlockCutRedSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE)
                .build();
    }

    public static void initCutRedSandstoneDoubleSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCutRedSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutRedSandstoneSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockCutRedSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstone() {
        if (BlockTypes.CUT_SANDSTONE != null) return;
        BlockTypes.CUT_SANDSTONE = AllayBlockType
                .builder(BlockCutSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE)
                .build();
    }

    public static void initCutSandstoneDoubleSlab() {
        if (BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCutSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstoneSlab() {
        if (BlockTypes.CUT_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockCutSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCyanCandle() {
        if (BlockTypes.CYAN_CANDLE != null) return;
        BlockTypes.CYAN_CANDLE = AllayBlockType
                .builder(BlockCyanCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCandleCake() {
        if (BlockTypes.CYAN_CANDLE_CAKE != null) return;
        BlockTypes.CYAN_CANDLE_CAKE = AllayBlockType
                .builder(BlockCyanCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCarpet() {
        if (BlockTypes.CYAN_CARPET != null) return;
        BlockTypes.CYAN_CARPET = AllayBlockType
                .builder(BlockCyanCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CARPET)
                .build();
    }

    public static void initCyanConcrete() {
        if (BlockTypes.CYAN_CONCRETE != null) return;
        BlockTypes.CYAN_CONCRETE = AllayBlockType
                .builder(BlockCyanConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE)
                .build();
    }

    public static void initCyanConcretePowder() {
        if (BlockTypes.CYAN_CONCRETE_POWDER != null) return;
        BlockTypes.CYAN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockCyanConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE_POWDER)
                .build();
    }

    public static void initCyanGlazedTerracotta() {
        if (BlockTypes.CYAN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.CYAN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockCyanGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCyanShulkerBox() {
        if (BlockTypes.CYAN_SHULKER_BOX != null) return;
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .build();
    }

    public static void initCyanStainedGlass() {
        if (BlockTypes.CYAN_STAINED_GLASS != null) return;
        BlockTypes.CYAN_STAINED_GLASS = AllayBlockType
                .builder(BlockCyanStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS)
                .build();
    }

    public static void initCyanStainedGlassPane() {
        if (BlockTypes.CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.CYAN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockCyanStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initCyanTerracotta() {
        if (BlockTypes.CYAN_TERRACOTTA != null) return;
        BlockTypes.CYAN_TERRACOTTA = AllayBlockType
                .builder(BlockCyanTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_TERRACOTTA)
                .build();
    }

    public static void initCyanWool() {
        if (BlockTypes.CYAN_WOOL != null) return;
        BlockTypes.CYAN_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_WOOL)
                .build();
    }

    public static void initDamagedAnvil() {
        if (BlockTypes.DAMAGED_ANVIL != null) return;
        BlockTypes.DAMAGED_ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.DAMAGED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDandelion() {
        if (BlockTypes.DANDELION != null) return;
        BlockTypes.DANDELION = AllayBlockType
                .builder(BlockDandelionBehaviorImpl.class)
                .vanillaBlock(BlockId.DANDELION)
                .build();
    }

    public static void initDarkOakButton() {
        if (BlockTypes.DARK_OAK_BUTTON != null) return;
        BlockTypes.DARK_OAK_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDarkOakDoor() {
        if (BlockTypes.DARK_OAK_DOOR != null) return;
        BlockTypes.DARK_OAK_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initDarkOakDoubleSlab() {
        if (BlockTypes.DARK_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDarkOakDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakFence() {
        if (BlockTypes.DARK_OAK_FENCE != null) return;
        BlockTypes.DARK_OAK_FENCE = AllayBlockType
                .builder(BlockDarkOakFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE)
                .build();
    }

    public static void initDarkOakFenceGate() {
        if (BlockTypes.DARK_OAK_FENCE_GATE != null) return;
        BlockTypes.DARK_OAK_FENCE_GATE = AllayBlockType
                .builder(BlockDarkOakFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initDarkOakHangingSign() {
        if (BlockTypes.DARK_OAK_HANGING_SIGN != null) return;
        BlockTypes.DARK_OAK_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initDarkOakLeaves() {
        if (BlockTypes.DARK_OAK_LEAVES != null) return;
        BlockTypes.DARK_OAK_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initDarkOakLog() {
        if (BlockTypes.DARK_OAK_LOG != null) return;
        BlockTypes.DARK_OAK_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkOakPlanks() {
        if (BlockTypes.DARK_OAK_PLANKS != null) return;
        BlockTypes.DARK_OAK_PLANKS = AllayBlockType
                .builder(BlockDarkOakPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_PLANKS)
                .build();
    }

    public static void initDarkOakPressurePlate() {
        if (BlockTypes.DARK_OAK_PRESSURE_PLATE != null) return;
        BlockTypes.DARK_OAK_PRESSURE_PLATE = AllayBlockType
                .builder(BlockDarkOakPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDarkOakSapling() {
        if (BlockTypes.DARK_OAK_SAPLING != null) return;
        BlockTypes.DARK_OAK_SAPLING = AllayBlockType
                .builder(BlockDarkOakSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initDarkOakSlab() {
        if (BlockTypes.DARK_OAK_SLAB != null) return;
        BlockTypes.DARK_OAK_SLAB = AllayBlockType
                .builder(BlockDarkOakSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakStairs() {
        if (BlockTypes.DARK_OAK_STAIRS != null) return;
        BlockTypes.DARK_OAK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkOakTrapdoor() {
        if (BlockTypes.DARK_OAK_TRAPDOOR != null) return;
        BlockTypes.DARK_OAK_TRAPDOOR = AllayBlockType
                .builder(BlockDarkOakTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initDarkOakWood() {
        if (BlockTypes.DARK_OAK_WOOD != null) return;
        BlockTypes.DARK_OAK_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkPrismarine() {
        if (BlockTypes.DARK_PRISMARINE != null) return;
        BlockTypes.DARK_PRISMARINE = AllayBlockType
                .builder(BlockDarkPrismarineBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE)
                .build();
    }

    public static void initDarkPrismarineDoubleSlab() {
        if (BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDarkPrismarineDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineSlab() {
        if (BlockTypes.DARK_PRISMARINE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_SLAB = AllayBlockType
                .builder(BlockDarkPrismarineSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineStairs() {
        if (BlockTypes.DARK_PRISMARINE_STAIRS != null) return;
        BlockTypes.DARK_PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkoakStandingSign() {
        if (BlockTypes.DARKOAK_STANDING_SIGN != null) return;
        BlockTypes.DARKOAK_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.DARKOAK_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initDarkoakWallSign() {
        if (BlockTypes.DARKOAK_WALL_SIGN != null) return;
        BlockTypes.DARKOAK_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.DARKOAK_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDaylightDetector() {
        if (BlockTypes.DAYLIGHT_DETECTOR != null) return;
        BlockTypes.DAYLIGHT_DETECTOR = AllayBlockType
                .builder(BlockDaylightDetectorBehaviorImpl.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDaylightDetectorInverted() {
        if (BlockTypes.DAYLIGHT_DETECTOR_INVERTED != null) return;
        BlockTypes.DAYLIGHT_DETECTOR_INVERTED = AllayBlockType
                .builder(BlockDaylightDetectorInvertedBehaviorImpl.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR_INVERTED)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDeadBrainCoral() {
        if (BlockTypes.DEAD_BRAIN_CORAL != null) return;
        BlockTypes.DEAD_BRAIN_CORAL = AllayBlockType
                .builder(BlockDeadBrainCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL)
                .build();
    }

    public static void initDeadBrainCoralBlock() {
        if (BlockTypes.DEAD_BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadBrainCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBrainCoralFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_FAN = AllayBlockType
                .builder(BlockDeadBrainCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBrainCoralWallFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadBrainCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoral() {
        if (BlockTypes.DEAD_BUBBLE_CORAL != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL = AllayBlockType
                .builder(BlockDeadBubbleCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL)
                .build();
    }

    public static void initDeadBubbleCoralBlock() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadBubbleCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBubbleCoralFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadBubbleCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoralWallFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadBubbleCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadFireCoral() {
        if (BlockTypes.DEAD_FIRE_CORAL != null) return;
        BlockTypes.DEAD_FIRE_CORAL = AllayBlockType
                .builder(BlockDeadFireCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL)
                .build();
    }

    public static void initDeadFireCoralBlock() {
        if (BlockTypes.DEAD_FIRE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_FIRE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadFireCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadFireCoralFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadFireCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadFireCoralWallFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadFireCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadHornCoral() {
        if (BlockTypes.DEAD_HORN_CORAL != null) return;
        BlockTypes.DEAD_HORN_CORAL = AllayBlockType
                .builder(BlockDeadHornCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL)
                .build();
    }

    public static void initDeadHornCoralBlock() {
        if (BlockTypes.DEAD_HORN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_HORN_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadHornCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadHornCoralFan() {
        if (BlockTypes.DEAD_HORN_CORAL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_FAN = AllayBlockType
                .builder(BlockDeadHornCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadHornCoralWallFan() {
        if (BlockTypes.DEAD_HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadHornCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoral() {
        if (BlockTypes.DEAD_TUBE_CORAL != null) return;
        BlockTypes.DEAD_TUBE_CORAL = AllayBlockType
                .builder(BlockDeadTubeCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL)
                .build();
    }

    public static void initDeadTubeCoralBlock() {
        if (BlockTypes.DEAD_TUBE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_TUBE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadTubeCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadTubeCoralFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadTubeCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoralWallFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadTubeCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadbush() {
        if (BlockTypes.DEADBUSH != null) return;
        BlockTypes.DEADBUSH = AllayBlockType
                .builder(BlockDeadbushBehaviorImpl.class)
                .vanillaBlock(BlockId.DEADBUSH)
                .build();
    }

    public static void initDecoratedPot() {
        if (BlockTypes.DECORATED_POT != null) return;
        BlockTypes.DECORATED_POT = AllayBlockType
                .builder(BlockDecoratedPotBehaviorImpl.class)
                .vanillaBlock(BlockId.DECORATED_POT)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initDeepslate() {
        if (BlockTypes.DEEPSLATE != null) return;
        BlockTypes.DEEPSLATE = AllayBlockType
                .builder(BlockDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDeepslateBrickDoubleSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDeepslateBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_SLAB = AllayBlockType
                .builder(BlockDeepslateBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickStairs() {
        if (BlockTypes.DEEPSLATE_BRICK_STAIRS != null) return;
        BlockTypes.DEEPSLATE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateBrickWall() {
        if (BlockTypes.DEEPSLATE_BRICK_WALL != null) return;
        BlockTypes.DEEPSLATE_BRICK_WALL = AllayBlockType
                .builder(BlockDeepslateBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initDeepslateBricks() {
        if (BlockTypes.DEEPSLATE_BRICKS != null) return;
        BlockTypes.DEEPSLATE_BRICKS = AllayBlockType
                .builder(BlockDeepslateBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICKS)
                .build();
    }

    public static void initDeepslateCoalOre() {
        if (BlockTypes.DEEPSLATE_COAL_ORE != null) return;
        BlockTypes.DEEPSLATE_COAL_ORE = AllayBlockType
                .builder(BlockDeepslateCoalOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_COAL_ORE)
                .build();
    }

    public static void initDeepslateCopperOre() {
        if (BlockTypes.DEEPSLATE_COPPER_ORE != null) return;
        BlockTypes.DEEPSLATE_COPPER_ORE = AllayBlockType
                .builder(BlockDeepslateCopperOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_COPPER_ORE)
                .build();
    }

    public static void initDeepslateDiamondOre() {
        if (BlockTypes.DEEPSLATE_DIAMOND_ORE != null) return;
        BlockTypes.DEEPSLATE_DIAMOND_ORE = AllayBlockType
                .builder(BlockDeepslateDiamondOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_DIAMOND_ORE)
                .build();
    }

    public static void initDeepslateEmeraldOre() {
        if (BlockTypes.DEEPSLATE_EMERALD_ORE != null) return;
        BlockTypes.DEEPSLATE_EMERALD_ORE = AllayBlockType
                .builder(BlockDeepslateEmeraldOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_EMERALD_ORE)
                .build();
    }

    public static void initDeepslateGoldOre() {
        if (BlockTypes.DEEPSLATE_GOLD_ORE != null) return;
        BlockTypes.DEEPSLATE_GOLD_ORE = AllayBlockType
                .builder(BlockDeepslateGoldOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_GOLD_ORE)
                .build();
    }

    public static void initDeepslateIronOre() {
        if (BlockTypes.DEEPSLATE_IRON_ORE != null) return;
        BlockTypes.DEEPSLATE_IRON_ORE = AllayBlockType
                .builder(BlockDeepslateIronOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_IRON_ORE)
                .build();
    }

    public static void initDeepslateLapisOre() {
        if (BlockTypes.DEEPSLATE_LAPIS_ORE != null) return;
        BlockTypes.DEEPSLATE_LAPIS_ORE = AllayBlockType
                .builder(BlockDeepslateLapisOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_LAPIS_ORE)
                .build();
    }

    public static void initDeepslateRedstoneOre() {
        if (BlockTypes.DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.DEEPSLATE_REDSTONE_ORE = AllayBlockType
                .builder(BlockDeepslateRedstoneOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initDeepslateTileDoubleSlab() {
        if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDeepslateTileDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileSlab() {
        if (BlockTypes.DEEPSLATE_TILE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_SLAB = AllayBlockType
                .builder(BlockDeepslateTileSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileStairs() {
        if (BlockTypes.DEEPSLATE_TILE_STAIRS != null) return;
        BlockTypes.DEEPSLATE_TILE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateTileWall() {
        if (BlockTypes.DEEPSLATE_TILE_WALL != null) return;
        BlockTypes.DEEPSLATE_TILE_WALL = AllayBlockType
                .builder(BlockDeepslateTileWallBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initDeepslateTiles() {
        if (BlockTypes.DEEPSLATE_TILES != null) return;
        BlockTypes.DEEPSLATE_TILES = AllayBlockType
                .builder(BlockDeepslateTilesBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILES)
                .build();
    }

    public static void initDeny() {
        if (BlockTypes.DENY != null) return;
        BlockTypes.DENY = AllayBlockType
                .builder(BlockDenyBehaviorImpl.class)
                .vanillaBlock(BlockId.DENY)
                .build();
    }

    public static void initDeprecatedAnvil() {
        if (BlockTypes.DEPRECATED_ANVIL != null) return;
        BlockTypes.DEPRECATED_ANVIL = AllayBlockType
                .builder(BlockAnvilBehaviorImpl.class)
                .vanillaBlock(BlockId.DEPRECATED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDeprecatedPurpurBlock1() {
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_1 != null) return;
        BlockTypes.DEPRECATED_PURPUR_BLOCK_1 = AllayBlockType
                .builder(BlockDeprecatedPurpurBlock1BehaviorImpl.class)
                .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_1)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDeprecatedPurpurBlock2() {
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_2 != null) return;
        BlockTypes.DEPRECATED_PURPUR_BLOCK_2 = AllayBlockType
                .builder(BlockDeprecatedPurpurBlock2BehaviorImpl.class)
                .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_2)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDetectorRail() {
        if (BlockTypes.DETECTOR_RAIL != null) return;
        BlockTypes.DETECTOR_RAIL = AllayBlockType
                .builder(BlockDetectorRailBehaviorImpl.class)
                .vanillaBlock(BlockId.DETECTOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initDiamondBlock() {
        if (BlockTypes.DIAMOND_BLOCK != null) return;
        BlockTypes.DIAMOND_BLOCK = AllayBlockType
                .builder(BlockDiamondBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DIAMOND_BLOCK)
                .build();
    }

    public static void initDiamondOre() {
        if (BlockTypes.DIAMOND_ORE != null) return;
        BlockTypes.DIAMOND_ORE = AllayBlockType
                .builder(BlockDiamondOreBehaviorImpl.class)
                .vanillaBlock(BlockId.DIAMOND_ORE)
                .build();
    }

    public static void initDiorite() {
        if (BlockTypes.DIORITE != null) return;
        BlockTypes.DIORITE = AllayBlockType
                .builder(BlockDioriteBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE)
                .build();
    }

    public static void initDioriteDoubleSlab() {
        if (BlockTypes.DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.DIORITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDioriteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteSlab() {
        if (BlockTypes.DIORITE_SLAB != null) return;
        BlockTypes.DIORITE_SLAB = AllayBlockType
                .builder(BlockDioriteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteStairs() {
        if (BlockTypes.DIORITE_STAIRS != null) return;
        BlockTypes.DIORITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDioriteWall() {
        if (BlockTypes.DIORITE_WALL != null) return;
        BlockTypes.DIORITE_WALL = AllayBlockType
                .builder(BlockDioriteWallBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initDirt() {
        if (BlockTypes.DIRT != null) return;
        BlockTypes.DIRT = AllayBlockType
                .builder(BlockDirtBehaviorImpl.class)
                .vanillaBlock(BlockId.DIRT)
                .build();
    }

    public static void initDirtWithRoots() {
        if (BlockTypes.DIRT_WITH_ROOTS != null) return;
        BlockTypes.DIRT_WITH_ROOTS = AllayBlockType
                .builder(BlockDirtWithRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.DIRT_WITH_ROOTS)
                .build();
    }

    public static void initDispenser() {
        if (BlockTypes.DISPENSER != null) return;
        BlockTypes.DISPENSER = AllayBlockType
                .builder(BlockDispenserBehaviorImpl.class)
                .vanillaBlock(BlockId.DISPENSER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initDoubleCutCopperSlab() {
        if (BlockTypes.DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDragonEgg() {
        if (BlockTypes.DRAGON_EGG != null) return;
        BlockTypes.DRAGON_EGG = AllayBlockType
                .builder(BlockDragonEggBehaviorImpl.class)
                .vanillaBlock(BlockId.DRAGON_EGG)
                .build();
    }

    public static void initDragonHead() {
        if (BlockTypes.DRAGON_HEAD != null) return;
        BlockTypes.DRAGON_HEAD = AllayBlockType
                .builder(BlockDragonHeadBehaviorImpl.class)
                .vanillaBlock(BlockId.DRAGON_HEAD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDriedKelpBlock() {
        if (BlockTypes.DRIED_KELP_BLOCK != null) return;
        BlockTypes.DRIED_KELP_BLOCK = AllayBlockType
                .builder(BlockDriedKelpBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DRIED_KELP_BLOCK)
                .build();
    }

    public static void initDripstoneBlock() {
        if (BlockTypes.DRIPSTONE_BLOCK != null) return;
        BlockTypes.DRIPSTONE_BLOCK = AllayBlockType
                .builder(BlockDripstoneBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.DRIPSTONE_BLOCK)
                .build();
    }

    public static void initDropper() {
        if (BlockTypes.DROPPER != null) return;
        BlockTypes.DROPPER = AllayBlockType
                .builder(BlockDropperBehaviorImpl.class)
                .vanillaBlock(BlockId.DROPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initElement0() {
        if (BlockTypes.ELEMENT_0 != null) return;
        BlockTypes.ELEMENT_0 = AllayBlockType
                .builder(BlockElement0BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_0)
                .build();
    }

    public static void initElement1() {
        if (BlockTypes.ELEMENT_1 != null) return;
        BlockTypes.ELEMENT_1 = AllayBlockType
                .builder(BlockElement1BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_1)
                .build();
    }

    public static void initElement10() {
        if (BlockTypes.ELEMENT_10 != null) return;
        BlockTypes.ELEMENT_10 = AllayBlockType
                .builder(BlockElement10BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_10)
                .build();
    }

    public static void initElement100() {
        if (BlockTypes.ELEMENT_100 != null) return;
        BlockTypes.ELEMENT_100 = AllayBlockType
                .builder(BlockElement100BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_100)
                .build();
    }

    public static void initElement101() {
        if (BlockTypes.ELEMENT_101 != null) return;
        BlockTypes.ELEMENT_101 = AllayBlockType
                .builder(BlockElement101BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_101)
                .build();
    }

    public static void initElement102() {
        if (BlockTypes.ELEMENT_102 != null) return;
        BlockTypes.ELEMENT_102 = AllayBlockType
                .builder(BlockElement102BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_102)
                .build();
    }

    public static void initElement103() {
        if (BlockTypes.ELEMENT_103 != null) return;
        BlockTypes.ELEMENT_103 = AllayBlockType
                .builder(BlockElement103BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_103)
                .build();
    }

    public static void initElement104() {
        if (BlockTypes.ELEMENT_104 != null) return;
        BlockTypes.ELEMENT_104 = AllayBlockType
                .builder(BlockElement104BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_104)
                .build();
    }

    public static void initElement105() {
        if (BlockTypes.ELEMENT_105 != null) return;
        BlockTypes.ELEMENT_105 = AllayBlockType
                .builder(BlockElement105BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_105)
                .build();
    }

    public static void initElement106() {
        if (BlockTypes.ELEMENT_106 != null) return;
        BlockTypes.ELEMENT_106 = AllayBlockType
                .builder(BlockElement106BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_106)
                .build();
    }

    public static void initElement107() {
        if (BlockTypes.ELEMENT_107 != null) return;
        BlockTypes.ELEMENT_107 = AllayBlockType
                .builder(BlockElement107BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_107)
                .build();
    }

    public static void initElement108() {
        if (BlockTypes.ELEMENT_108 != null) return;
        BlockTypes.ELEMENT_108 = AllayBlockType
                .builder(BlockElement108BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_108)
                .build();
    }

    public static void initElement109() {
        if (BlockTypes.ELEMENT_109 != null) return;
        BlockTypes.ELEMENT_109 = AllayBlockType
                .builder(BlockElement109BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_109)
                .build();
    }

    public static void initElement11() {
        if (BlockTypes.ELEMENT_11 != null) return;
        BlockTypes.ELEMENT_11 = AllayBlockType
                .builder(BlockElement11BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_11)
                .build();
    }

    public static void initElement110() {
        if (BlockTypes.ELEMENT_110 != null) return;
        BlockTypes.ELEMENT_110 = AllayBlockType
                .builder(BlockElement110BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_110)
                .build();
    }

    public static void initElement111() {
        if (BlockTypes.ELEMENT_111 != null) return;
        BlockTypes.ELEMENT_111 = AllayBlockType
                .builder(BlockElement111BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_111)
                .build();
    }

    public static void initElement112() {
        if (BlockTypes.ELEMENT_112 != null) return;
        BlockTypes.ELEMENT_112 = AllayBlockType
                .builder(BlockElement112BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_112)
                .build();
    }

    public static void initElement113() {
        if (BlockTypes.ELEMENT_113 != null) return;
        BlockTypes.ELEMENT_113 = AllayBlockType
                .builder(BlockElement113BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_113)
                .build();
    }

    public static void initElement114() {
        if (BlockTypes.ELEMENT_114 != null) return;
        BlockTypes.ELEMENT_114 = AllayBlockType
                .builder(BlockElement114BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_114)
                .build();
    }

    public static void initElement115() {
        if (BlockTypes.ELEMENT_115 != null) return;
        BlockTypes.ELEMENT_115 = AllayBlockType
                .builder(BlockElement115BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_115)
                .build();
    }

    public static void initElement116() {
        if (BlockTypes.ELEMENT_116 != null) return;
        BlockTypes.ELEMENT_116 = AllayBlockType
                .builder(BlockElement116BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_116)
                .build();
    }

    public static void initElement117() {
        if (BlockTypes.ELEMENT_117 != null) return;
        BlockTypes.ELEMENT_117 = AllayBlockType
                .builder(BlockElement117BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_117)
                .build();
    }

    public static void initElement118() {
        if (BlockTypes.ELEMENT_118 != null) return;
        BlockTypes.ELEMENT_118 = AllayBlockType
                .builder(BlockElement118BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_118)
                .build();
    }

    public static void initElement12() {
        if (BlockTypes.ELEMENT_12 != null) return;
        BlockTypes.ELEMENT_12 = AllayBlockType
                .builder(BlockElement12BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_12)
                .build();
    }

    public static void initElement13() {
        if (BlockTypes.ELEMENT_13 != null) return;
        BlockTypes.ELEMENT_13 = AllayBlockType
                .builder(BlockElement13BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_13)
                .build();
    }

    public static void initElement14() {
        if (BlockTypes.ELEMENT_14 != null) return;
        BlockTypes.ELEMENT_14 = AllayBlockType
                .builder(BlockElement14BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_14)
                .build();
    }

    public static void initElement15() {
        if (BlockTypes.ELEMENT_15 != null) return;
        BlockTypes.ELEMENT_15 = AllayBlockType
                .builder(BlockElement15BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_15)
                .build();
    }

    public static void initElement16() {
        if (BlockTypes.ELEMENT_16 != null) return;
        BlockTypes.ELEMENT_16 = AllayBlockType
                .builder(BlockElement16BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_16)
                .build();
    }

    public static void initElement17() {
        if (BlockTypes.ELEMENT_17 != null) return;
        BlockTypes.ELEMENT_17 = AllayBlockType
                .builder(BlockElement17BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_17)
                .build();
    }

    public static void initElement18() {
        if (BlockTypes.ELEMENT_18 != null) return;
        BlockTypes.ELEMENT_18 = AllayBlockType
                .builder(BlockElement18BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_18)
                .build();
    }

    public static void initElement19() {
        if (BlockTypes.ELEMENT_19 != null) return;
        BlockTypes.ELEMENT_19 = AllayBlockType
                .builder(BlockElement19BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_19)
                .build();
    }

    public static void initElement2() {
        if (BlockTypes.ELEMENT_2 != null) return;
        BlockTypes.ELEMENT_2 = AllayBlockType
                .builder(BlockElement2BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_2)
                .build();
    }

    public static void initElement20() {
        if (BlockTypes.ELEMENT_20 != null) return;
        BlockTypes.ELEMENT_20 = AllayBlockType
                .builder(BlockElement20BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_20)
                .build();
    }

    public static void initElement21() {
        if (BlockTypes.ELEMENT_21 != null) return;
        BlockTypes.ELEMENT_21 = AllayBlockType
                .builder(BlockElement21BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_21)
                .build();
    }

    public static void initElement22() {
        if (BlockTypes.ELEMENT_22 != null) return;
        BlockTypes.ELEMENT_22 = AllayBlockType
                .builder(BlockElement22BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_22)
                .build();
    }

    public static void initElement23() {
        if (BlockTypes.ELEMENT_23 != null) return;
        BlockTypes.ELEMENT_23 = AllayBlockType
                .builder(BlockElement23BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_23)
                .build();
    }

    public static void initElement24() {
        if (BlockTypes.ELEMENT_24 != null) return;
        BlockTypes.ELEMENT_24 = AllayBlockType
                .builder(BlockElement24BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_24)
                .build();
    }

    public static void initElement25() {
        if (BlockTypes.ELEMENT_25 != null) return;
        BlockTypes.ELEMENT_25 = AllayBlockType
                .builder(BlockElement25BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_25)
                .build();
    }

    public static void initElement26() {
        if (BlockTypes.ELEMENT_26 != null) return;
        BlockTypes.ELEMENT_26 = AllayBlockType
                .builder(BlockElement26BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_26)
                .build();
    }

    public static void initElement27() {
        if (BlockTypes.ELEMENT_27 != null) return;
        BlockTypes.ELEMENT_27 = AllayBlockType
                .builder(BlockElement27BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_27)
                .build();
    }

    public static void initElement28() {
        if (BlockTypes.ELEMENT_28 != null) return;
        BlockTypes.ELEMENT_28 = AllayBlockType
                .builder(BlockElement28BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_28)
                .build();
    }

    public static void initElement29() {
        if (BlockTypes.ELEMENT_29 != null) return;
        BlockTypes.ELEMENT_29 = AllayBlockType
                .builder(BlockElement29BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_29)
                .build();
    }

    public static void initElement3() {
        if (BlockTypes.ELEMENT_3 != null) return;
        BlockTypes.ELEMENT_3 = AllayBlockType
                .builder(BlockElement3BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_3)
                .build();
    }

    public static void initElement30() {
        if (BlockTypes.ELEMENT_30 != null) return;
        BlockTypes.ELEMENT_30 = AllayBlockType
                .builder(BlockElement30BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_30)
                .build();
    }

    public static void initElement31() {
        if (BlockTypes.ELEMENT_31 != null) return;
        BlockTypes.ELEMENT_31 = AllayBlockType
                .builder(BlockElement31BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_31)
                .build();
    }

    public static void initElement32() {
        if (BlockTypes.ELEMENT_32 != null) return;
        BlockTypes.ELEMENT_32 = AllayBlockType
                .builder(BlockElement32BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_32)
                .build();
    }

    public static void initElement33() {
        if (BlockTypes.ELEMENT_33 != null) return;
        BlockTypes.ELEMENT_33 = AllayBlockType
                .builder(BlockElement33BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_33)
                .build();
    }

    public static void initElement34() {
        if (BlockTypes.ELEMENT_34 != null) return;
        BlockTypes.ELEMENT_34 = AllayBlockType
                .builder(BlockElement34BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_34)
                .build();
    }

    public static void initElement35() {
        if (BlockTypes.ELEMENT_35 != null) return;
        BlockTypes.ELEMENT_35 = AllayBlockType
                .builder(BlockElement35BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_35)
                .build();
    }

    public static void initElement36() {
        if (BlockTypes.ELEMENT_36 != null) return;
        BlockTypes.ELEMENT_36 = AllayBlockType
                .builder(BlockElement36BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_36)
                .build();
    }

    public static void initElement37() {
        if (BlockTypes.ELEMENT_37 != null) return;
        BlockTypes.ELEMENT_37 = AllayBlockType
                .builder(BlockElement37BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_37)
                .build();
    }

    public static void initElement38() {
        if (BlockTypes.ELEMENT_38 != null) return;
        BlockTypes.ELEMENT_38 = AllayBlockType
                .builder(BlockElement38BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_38)
                .build();
    }

    public static void initElement39() {
        if (BlockTypes.ELEMENT_39 != null) return;
        BlockTypes.ELEMENT_39 = AllayBlockType
                .builder(BlockElement39BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_39)
                .build();
    }

    public static void initElement4() {
        if (BlockTypes.ELEMENT_4 != null) return;
        BlockTypes.ELEMENT_4 = AllayBlockType
                .builder(BlockElement4BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_4)
                .build();
    }

    public static void initElement40() {
        if (BlockTypes.ELEMENT_40 != null) return;
        BlockTypes.ELEMENT_40 = AllayBlockType
                .builder(BlockElement40BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_40)
                .build();
    }

    public static void initElement41() {
        if (BlockTypes.ELEMENT_41 != null) return;
        BlockTypes.ELEMENT_41 = AllayBlockType
                .builder(BlockElement41BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_41)
                .build();
    }

    public static void initElement42() {
        if (BlockTypes.ELEMENT_42 != null) return;
        BlockTypes.ELEMENT_42 = AllayBlockType
                .builder(BlockElement42BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_42)
                .build();
    }

    public static void initElement43() {
        if (BlockTypes.ELEMENT_43 != null) return;
        BlockTypes.ELEMENT_43 = AllayBlockType
                .builder(BlockElement43BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_43)
                .build();
    }

    public static void initElement44() {
        if (BlockTypes.ELEMENT_44 != null) return;
        BlockTypes.ELEMENT_44 = AllayBlockType
                .builder(BlockElement44BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_44)
                .build();
    }

    public static void initElement45() {
        if (BlockTypes.ELEMENT_45 != null) return;
        BlockTypes.ELEMENT_45 = AllayBlockType
                .builder(BlockElement45BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_45)
                .build();
    }

    public static void initElement46() {
        if (BlockTypes.ELEMENT_46 != null) return;
        BlockTypes.ELEMENT_46 = AllayBlockType
                .builder(BlockElement46BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_46)
                .build();
    }

    public static void initElement47() {
        if (BlockTypes.ELEMENT_47 != null) return;
        BlockTypes.ELEMENT_47 = AllayBlockType
                .builder(BlockElement47BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_47)
                .build();
    }

    public static void initElement48() {
        if (BlockTypes.ELEMENT_48 != null) return;
        BlockTypes.ELEMENT_48 = AllayBlockType
                .builder(BlockElement48BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_48)
                .build();
    }

    public static void initElement49() {
        if (BlockTypes.ELEMENT_49 != null) return;
        BlockTypes.ELEMENT_49 = AllayBlockType
                .builder(BlockElement49BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_49)
                .build();
    }

    public static void initElement5() {
        if (BlockTypes.ELEMENT_5 != null) return;
        BlockTypes.ELEMENT_5 = AllayBlockType
                .builder(BlockElement5BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_5)
                .build();
    }

    public static void initElement50() {
        if (BlockTypes.ELEMENT_50 != null) return;
        BlockTypes.ELEMENT_50 = AllayBlockType
                .builder(BlockElement50BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_50)
                .build();
    }

    public static void initElement51() {
        if (BlockTypes.ELEMENT_51 != null) return;
        BlockTypes.ELEMENT_51 = AllayBlockType
                .builder(BlockElement51BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_51)
                .build();
    }

    public static void initElement52() {
        if (BlockTypes.ELEMENT_52 != null) return;
        BlockTypes.ELEMENT_52 = AllayBlockType
                .builder(BlockElement52BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_52)
                .build();
    }

    public static void initElement53() {
        if (BlockTypes.ELEMENT_53 != null) return;
        BlockTypes.ELEMENT_53 = AllayBlockType
                .builder(BlockElement53BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_53)
                .build();
    }

    public static void initElement54() {
        if (BlockTypes.ELEMENT_54 != null) return;
        BlockTypes.ELEMENT_54 = AllayBlockType
                .builder(BlockElement54BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_54)
                .build();
    }

    public static void initElement55() {
        if (BlockTypes.ELEMENT_55 != null) return;
        BlockTypes.ELEMENT_55 = AllayBlockType
                .builder(BlockElement55BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_55)
                .build();
    }

    public static void initElement56() {
        if (BlockTypes.ELEMENT_56 != null) return;
        BlockTypes.ELEMENT_56 = AllayBlockType
                .builder(BlockElement56BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_56)
                .build();
    }

    public static void initElement57() {
        if (BlockTypes.ELEMENT_57 != null) return;
        BlockTypes.ELEMENT_57 = AllayBlockType
                .builder(BlockElement57BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_57)
                .build();
    }

    public static void initElement58() {
        if (BlockTypes.ELEMENT_58 != null) return;
        BlockTypes.ELEMENT_58 = AllayBlockType
                .builder(BlockElement58BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_58)
                .build();
    }

    public static void initElement59() {
        if (BlockTypes.ELEMENT_59 != null) return;
        BlockTypes.ELEMENT_59 = AllayBlockType
                .builder(BlockElement59BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_59)
                .build();
    }

    public static void initElement6() {
        if (BlockTypes.ELEMENT_6 != null) return;
        BlockTypes.ELEMENT_6 = AllayBlockType
                .builder(BlockElement6BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_6)
                .build();
    }

    public static void initElement60() {
        if (BlockTypes.ELEMENT_60 != null) return;
        BlockTypes.ELEMENT_60 = AllayBlockType
                .builder(BlockElement60BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_60)
                .build();
    }

    public static void initElement61() {
        if (BlockTypes.ELEMENT_61 != null) return;
        BlockTypes.ELEMENT_61 = AllayBlockType
                .builder(BlockElement61BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_61)
                .build();
    }

    public static void initElement62() {
        if (BlockTypes.ELEMENT_62 != null) return;
        BlockTypes.ELEMENT_62 = AllayBlockType
                .builder(BlockElement62BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_62)
                .build();
    }

    public static void initElement63() {
        if (BlockTypes.ELEMENT_63 != null) return;
        BlockTypes.ELEMENT_63 = AllayBlockType
                .builder(BlockElement63BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_63)
                .build();
    }

    public static void initElement64() {
        if (BlockTypes.ELEMENT_64 != null) return;
        BlockTypes.ELEMENT_64 = AllayBlockType
                .builder(BlockElement64BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_64)
                .build();
    }

    public static void initElement65() {
        if (BlockTypes.ELEMENT_65 != null) return;
        BlockTypes.ELEMENT_65 = AllayBlockType
                .builder(BlockElement65BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_65)
                .build();
    }

    public static void initElement66() {
        if (BlockTypes.ELEMENT_66 != null) return;
        BlockTypes.ELEMENT_66 = AllayBlockType
                .builder(BlockElement66BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_66)
                .build();
    }

    public static void initElement67() {
        if (BlockTypes.ELEMENT_67 != null) return;
        BlockTypes.ELEMENT_67 = AllayBlockType
                .builder(BlockElement67BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_67)
                .build();
    }

    public static void initElement68() {
        if (BlockTypes.ELEMENT_68 != null) return;
        BlockTypes.ELEMENT_68 = AllayBlockType
                .builder(BlockElement68BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_68)
                .build();
    }

    public static void initElement69() {
        if (BlockTypes.ELEMENT_69 != null) return;
        BlockTypes.ELEMENT_69 = AllayBlockType
                .builder(BlockElement69BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_69)
                .build();
    }

    public static void initElement7() {
        if (BlockTypes.ELEMENT_7 != null) return;
        BlockTypes.ELEMENT_7 = AllayBlockType
                .builder(BlockElement7BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_7)
                .build();
    }

    public static void initElement70() {
        if (BlockTypes.ELEMENT_70 != null) return;
        BlockTypes.ELEMENT_70 = AllayBlockType
                .builder(BlockElement70BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_70)
                .build();
    }

    public static void initElement71() {
        if (BlockTypes.ELEMENT_71 != null) return;
        BlockTypes.ELEMENT_71 = AllayBlockType
                .builder(BlockElement71BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_71)
                .build();
    }

    public static void initElement72() {
        if (BlockTypes.ELEMENT_72 != null) return;
        BlockTypes.ELEMENT_72 = AllayBlockType
                .builder(BlockElement72BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_72)
                .build();
    }

    public static void initElement73() {
        if (BlockTypes.ELEMENT_73 != null) return;
        BlockTypes.ELEMENT_73 = AllayBlockType
                .builder(BlockElement73BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_73)
                .build();
    }

    public static void initElement74() {
        if (BlockTypes.ELEMENT_74 != null) return;
        BlockTypes.ELEMENT_74 = AllayBlockType
                .builder(BlockElement74BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_74)
                .build();
    }

    public static void initElement75() {
        if (BlockTypes.ELEMENT_75 != null) return;
        BlockTypes.ELEMENT_75 = AllayBlockType
                .builder(BlockElement75BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_75)
                .build();
    }

    public static void initElement76() {
        if (BlockTypes.ELEMENT_76 != null) return;
        BlockTypes.ELEMENT_76 = AllayBlockType
                .builder(BlockElement76BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_76)
                .build();
    }

    public static void initElement77() {
        if (BlockTypes.ELEMENT_77 != null) return;
        BlockTypes.ELEMENT_77 = AllayBlockType
                .builder(BlockElement77BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_77)
                .build();
    }

    public static void initElement78() {
        if (BlockTypes.ELEMENT_78 != null) return;
        BlockTypes.ELEMENT_78 = AllayBlockType
                .builder(BlockElement78BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_78)
                .build();
    }

    public static void initElement79() {
        if (BlockTypes.ELEMENT_79 != null) return;
        BlockTypes.ELEMENT_79 = AllayBlockType
                .builder(BlockElement79BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_79)
                .build();
    }

    public static void initElement8() {
        if (BlockTypes.ELEMENT_8 != null) return;
        BlockTypes.ELEMENT_8 = AllayBlockType
                .builder(BlockElement8BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_8)
                .build();
    }

    public static void initElement80() {
        if (BlockTypes.ELEMENT_80 != null) return;
        BlockTypes.ELEMENT_80 = AllayBlockType
                .builder(BlockElement80BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_80)
                .build();
    }

    public static void initElement81() {
        if (BlockTypes.ELEMENT_81 != null) return;
        BlockTypes.ELEMENT_81 = AllayBlockType
                .builder(BlockElement81BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_81)
                .build();
    }

    public static void initElement82() {
        if (BlockTypes.ELEMENT_82 != null) return;
        BlockTypes.ELEMENT_82 = AllayBlockType
                .builder(BlockElement82BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_82)
                .build();
    }

    public static void initElement83() {
        if (BlockTypes.ELEMENT_83 != null) return;
        BlockTypes.ELEMENT_83 = AllayBlockType
                .builder(BlockElement83BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_83)
                .build();
    }

    public static void initElement84() {
        if (BlockTypes.ELEMENT_84 != null) return;
        BlockTypes.ELEMENT_84 = AllayBlockType
                .builder(BlockElement84BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_84)
                .build();
    }

    public static void initElement85() {
        if (BlockTypes.ELEMENT_85 != null) return;
        BlockTypes.ELEMENT_85 = AllayBlockType
                .builder(BlockElement85BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_85)
                .build();
    }

    public static void initElement86() {
        if (BlockTypes.ELEMENT_86 != null) return;
        BlockTypes.ELEMENT_86 = AllayBlockType
                .builder(BlockElement86BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_86)
                .build();
    }

    public static void initElement87() {
        if (BlockTypes.ELEMENT_87 != null) return;
        BlockTypes.ELEMENT_87 = AllayBlockType
                .builder(BlockElement87BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_87)
                .build();
    }

    public static void initElement88() {
        if (BlockTypes.ELEMENT_88 != null) return;
        BlockTypes.ELEMENT_88 = AllayBlockType
                .builder(BlockElement88BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_88)
                .build();
    }

    public static void initElement89() {
        if (BlockTypes.ELEMENT_89 != null) return;
        BlockTypes.ELEMENT_89 = AllayBlockType
                .builder(BlockElement89BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_89)
                .build();
    }

    public static void initElement9() {
        if (BlockTypes.ELEMENT_9 != null) return;
        BlockTypes.ELEMENT_9 = AllayBlockType
                .builder(BlockElement9BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_9)
                .build();
    }

    public static void initElement90() {
        if (BlockTypes.ELEMENT_90 != null) return;
        BlockTypes.ELEMENT_90 = AllayBlockType
                .builder(BlockElement90BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_90)
                .build();
    }

    public static void initElement91() {
        if (BlockTypes.ELEMENT_91 != null) return;
        BlockTypes.ELEMENT_91 = AllayBlockType
                .builder(BlockElement91BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_91)
                .build();
    }

    public static void initElement92() {
        if (BlockTypes.ELEMENT_92 != null) return;
        BlockTypes.ELEMENT_92 = AllayBlockType
                .builder(BlockElement92BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_92)
                .build();
    }

    public static void initElement93() {
        if (BlockTypes.ELEMENT_93 != null) return;
        BlockTypes.ELEMENT_93 = AllayBlockType
                .builder(BlockElement93BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_93)
                .build();
    }

    public static void initElement94() {
        if (BlockTypes.ELEMENT_94 != null) return;
        BlockTypes.ELEMENT_94 = AllayBlockType
                .builder(BlockElement94BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_94)
                .build();
    }

    public static void initElement95() {
        if (BlockTypes.ELEMENT_95 != null) return;
        BlockTypes.ELEMENT_95 = AllayBlockType
                .builder(BlockElement95BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_95)
                .build();
    }

    public static void initElement96() {
        if (BlockTypes.ELEMENT_96 != null) return;
        BlockTypes.ELEMENT_96 = AllayBlockType
                .builder(BlockElement96BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_96)
                .build();
    }

    public static void initElement97() {
        if (BlockTypes.ELEMENT_97 != null) return;
        BlockTypes.ELEMENT_97 = AllayBlockType
                .builder(BlockElement97BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_97)
                .build();
    }

    public static void initElement98() {
        if (BlockTypes.ELEMENT_98 != null) return;
        BlockTypes.ELEMENT_98 = AllayBlockType
                .builder(BlockElement98BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_98)
                .build();
    }

    public static void initElement99() {
        if (BlockTypes.ELEMENT_99 != null) return;
        BlockTypes.ELEMENT_99 = AllayBlockType
                .builder(BlockElement99BehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_99)
                .build();
    }

    public static void initElementConstructor() {
        if (BlockTypes.ELEMENT_CONSTRUCTOR != null) return;
        BlockTypes.ELEMENT_CONSTRUCTOR = AllayBlockType
                .builder(BlockElementConstructorBehaviorImpl.class)
                .vanillaBlock(BlockId.ELEMENT_CONSTRUCTOR)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initEmeraldBlock() {
        if (BlockTypes.EMERALD_BLOCK != null) return;
        BlockTypes.EMERALD_BLOCK = AllayBlockType
                .builder(BlockEmeraldBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.EMERALD_BLOCK)
                .build();
    }

    public static void initEmeraldOre() {
        if (BlockTypes.EMERALD_ORE != null) return;
        BlockTypes.EMERALD_ORE = AllayBlockType
                .builder(BlockEmeraldOreBehaviorImpl.class)
                .vanillaBlock(BlockId.EMERALD_ORE)
                .build();
    }

    public static void initEnchantingTable() {
        if (BlockTypes.ENCHANTING_TABLE != null) return;
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .build();
    }

    public static void initEndBrickStairs() {
        if (BlockTypes.END_BRICK_STAIRS != null) return;
        BlockTypes.END_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.END_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initEndBricks() {
        if (BlockTypes.END_BRICKS != null) return;
        BlockTypes.END_BRICKS = AllayBlockType
                .builder(BlockEndBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.END_BRICKS)
                .build();
    }

    public static void initEndGateway() {
        if (BlockTypes.END_GATEWAY != null) return;
        BlockTypes.END_GATEWAY = AllayBlockType
                .builder(BlockEndGatewayBehaviorImpl.class)
                .vanillaBlock(BlockId.END_GATEWAY)
                .build();
    }

    public static void initEndPortal() {
        if (BlockTypes.END_PORTAL != null) return;
        BlockTypes.END_PORTAL = AllayBlockType
                .builder(BlockEndPortalBehaviorImpl.class)
                .vanillaBlock(BlockId.END_PORTAL)
                .build();
    }

    public static void initEndPortalFrame() {
        if (BlockTypes.END_PORTAL_FRAME != null) return;
        BlockTypes.END_PORTAL_FRAME = AllayBlockType
                .builder(BlockEndPortalFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.END_PORTAL_FRAME)
                .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initEndRod() {
        if (BlockTypes.END_ROD != null) return;
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehaviorImpl.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initEndStone() {
        if (BlockTypes.END_STONE != null) return;
        BlockTypes.END_STONE = AllayBlockType
                .builder(BlockEndStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.END_STONE)
                .build();
    }

    public static void initEndStoneBrickDoubleSlab() {
        if (BlockTypes.END_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockEndStoneBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEndStoneBrickSlab() {
        if (BlockTypes.END_STONE_BRICK_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockEndStoneBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEndStoneBrickWall() {
        if (BlockTypes.END_STONE_BRICK_WALL != null) return;
        BlockTypes.END_STONE_BRICK_WALL = AllayBlockType
                .builder(BlockEndStoneBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initEnderChest() {
        if (BlockTypes.ENDER_CHEST != null) return;
        BlockTypes.ENDER_CHEST = AllayBlockType
                .builder(BlockEnderChestBehaviorImpl.class)
                .vanillaBlock(BlockId.ENDER_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initExposedChiseledCopper() {
        if (BlockTypes.EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.EXPOSED_CHISELED_COPPER = AllayBlockType
                .builder(BlockExposedChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initExposedCopper() {
        if (BlockTypes.EXPOSED_COPPER != null) return;
        BlockTypes.EXPOSED_COPPER = AllayBlockType
                .builder(BlockExposedCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER)
                .build();
    }

    public static void initExposedCopperBulb() {
        if (BlockTypes.EXPOSED_COPPER_BULB != null) return;
        BlockTypes.EXPOSED_COPPER_BULB = AllayBlockType
                .builder(BlockExposedCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initExposedCopperDoor() {
        if (BlockTypes.EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.EXPOSED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initExposedCopperGrate() {
        if (BlockTypes.EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.EXPOSED_COPPER_GRATE = AllayBlockType
                .builder(BlockExposedCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initExposedCopperTrapdoor() {
        if (BlockTypes.EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockExposedCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initExposedCutCopper() {
        if (BlockTypes.EXPOSED_CUT_COPPER != null) return;
        BlockTypes.EXPOSED_CUT_COPPER = AllayBlockType
                .builder(BlockExposedCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initExposedCutCopperSlab() {
        if (BlockTypes.EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockExposedCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initExposedCutCopperStairs() {
        if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initExposedDoubleCutCopperSlab() {
        if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockExposedDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initFarmland() {
        if (BlockTypes.FARMLAND != null) return;
        BlockTypes.FARMLAND = AllayBlockType
                .builder(BlockFarmlandBehaviorImpl.class)
                .vanillaBlock(BlockId.FARMLAND)
                .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                .build();
    }

    public static void initFenceGate() {
        if (BlockTypes.FENCE_GATE != null) return;
        BlockTypes.FENCE_GATE = AllayBlockType
                .builder(BlockFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initFern() {
        if (BlockTypes.FERN != null) return;
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehaviorImpl.class)
                .vanillaBlock(BlockId.FERN)
                .build();
    }

    public static void initFire() {
        if (BlockTypes.FIRE != null) return;
        BlockTypes.FIRE = AllayBlockType
                .builder(BlockFireBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initFireCoral() {
        if (BlockTypes.FIRE_CORAL != null) return;
        BlockTypes.FIRE_CORAL = AllayBlockType
                .builder(BlockFireCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE_CORAL)
                .build();
    }

    public static void initFireCoralBlock() {
        if (BlockTypes.FIRE_CORAL_BLOCK != null) return;
        BlockTypes.FIRE_CORAL_BLOCK = AllayBlockType
                .builder(BlockFireCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initFireCoralFan() {
        if (BlockTypes.FIRE_CORAL_FAN != null) return;
        BlockTypes.FIRE_CORAL_FAN = AllayBlockType
                .builder(BlockFireCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initFireCoralWallFan() {
        if (BlockTypes.FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.FIRE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockFireCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initFletchingTable() {
        if (BlockTypes.FLETCHING_TABLE != null) return;
        BlockTypes.FLETCHING_TABLE = AllayBlockType
                .builder(BlockFletchingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.FLETCHING_TABLE)
                .build();
    }

    public static void initFlowerPot() {
        if (BlockTypes.FLOWER_POT != null) return;
        BlockTypes.FLOWER_POT = AllayBlockType
                .builder(BlockFlowerPotBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWER_POT)
                .setProperties(BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initFloweringAzalea() {
        if (BlockTypes.FLOWERING_AZALEA != null) return;
        BlockTypes.FLOWERING_AZALEA = AllayBlockType
                .builder(BlockFloweringAzaleaBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWERING_AZALEA)
                .build();
    }

    public static void initFlowingLava() {
        if (BlockTypes.FLOWING_LAVA != null) return;
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockFlowingLavaBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFlowingWater() {
        if (BlockTypes.FLOWING_WATER != null) return;
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockFlowingWaterBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFrame() {
        if (BlockTypes.FRAME != null) return;
        BlockTypes.FRAME = AllayBlockType
                .builder(BlockFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initFrogSpawn() {
        if (BlockTypes.FROG_SPAWN != null) return;
        BlockTypes.FROG_SPAWN = AllayBlockType
                .builder(BlockFrogSpawnBehaviorImpl.class)
                .vanillaBlock(BlockId.FROG_SPAWN)
                .build();
    }

    public static void initFrostedIce() {
        if (BlockTypes.FROSTED_ICE != null) return;
        BlockTypes.FROSTED_ICE = AllayBlockType
                .builder(BlockFrostedIceBehaviorImpl.class)
                .vanillaBlock(BlockId.FROSTED_ICE)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initFurnace() {
        if (BlockTypes.FURNACE != null) return;
        BlockTypes.FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initGildedBlackstone() {
        if (BlockTypes.GILDED_BLACKSTONE != null) return;
        BlockTypes.GILDED_BLACKSTONE = AllayBlockType
                .builder(BlockGildedBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                .build();
    }

    public static void initGlass() {
        if (BlockTypes.GLASS != null) return;
        BlockTypes.GLASS = AllayBlockType
                .builder(BlockGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.GLASS)
                .build();
    }

    public static void initGlassPane() {
        if (BlockTypes.GLASS_PANE != null) return;
        BlockTypes.GLASS_PANE = AllayBlockType
                .builder(BlockGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.GLASS_PANE)
                .build();
    }

    public static void initGlowFrame() {
        if (BlockTypes.GLOW_FRAME != null) return;
        BlockTypes.GLOW_FRAME = AllayBlockType
                .builder(BlockGlowFrameBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOW_FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initGlowLichen() {
        if (BlockTypes.GLOW_LICHEN != null) return;
        BlockTypes.GLOW_LICHEN = AllayBlockType
                .builder(BlockGlowLichenBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOW_LICHEN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initGlowingobsidian() {
        if (BlockTypes.GLOWINGOBSIDIAN != null) return;
        BlockTypes.GLOWINGOBSIDIAN = AllayBlockType
                .builder(BlockGlowingobsidianBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOWINGOBSIDIAN)
                .build();
    }

    public static void initGlowstone() {
        if (BlockTypes.GLOWSTONE != null) return;
        BlockTypes.GLOWSTONE = AllayBlockType
                .builder(BlockGlowstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GLOWSTONE)
                .build();
    }

    public static void initGoldBlock() {
        if (BlockTypes.GOLD_BLOCK != null) return;
        BlockTypes.GOLD_BLOCK = AllayBlockType
                .builder(BlockGoldBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GOLD_BLOCK)
                .build();
    }

    public static void initGoldOre() {
        if (BlockTypes.GOLD_ORE != null) return;
        BlockTypes.GOLD_ORE = AllayBlockType
                .builder(BlockGoldOreBehaviorImpl.class)
                .vanillaBlock(BlockId.GOLD_ORE)
                .build();
    }

    public static void initGoldenRail() {
        if (BlockTypes.GOLDEN_RAIL != null) return;
        BlockTypes.GOLDEN_RAIL = AllayBlockType
                .builder(BlockGoldenRailBehaviorImpl.class)
                .vanillaBlock(BlockId.GOLDEN_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initGranite() {
        if (BlockTypes.GRANITE != null) return;
        BlockTypes.GRANITE = AllayBlockType
                .builder(BlockGraniteBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE)
                .build();
    }

    public static void initGraniteDoubleSlab() {
        if (BlockTypes.GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.GRANITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockGraniteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteSlab() {
        if (BlockTypes.GRANITE_SLAB != null) return;
        BlockTypes.GRANITE_SLAB = AllayBlockType
                .builder(BlockGraniteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteStairs() {
        if (BlockTypes.GRANITE_STAIRS != null) return;
        BlockTypes.GRANITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initGraniteWall() {
        if (BlockTypes.GRANITE_WALL != null) return;
        BlockTypes.GRANITE_WALL = AllayBlockType
                .builder(BlockGraniteWallBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initGrassBlock() {
        if (BlockTypes.GRASS_BLOCK != null) return;
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .build();
    }

    public static void initGrassPath() {
        if (BlockTypes.GRASS_PATH != null) return;
        BlockTypes.GRASS_PATH = AllayBlockType
                .builder(BlockGrassPathBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_PATH)
                .build();
    }

    public static void initGravel() {
        if (BlockTypes.GRAVEL != null) return;
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAVEL)
                .build();
    }

    public static void initGrayCandle() {
        if (BlockTypes.GRAY_CANDLE != null) return;
        BlockTypes.GRAY_CANDLE = AllayBlockType
                .builder(BlockGrayCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCandleCake() {
        if (BlockTypes.GRAY_CANDLE_CAKE != null) return;
        BlockTypes.GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockGrayCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCarpet() {
        if (BlockTypes.GRAY_CARPET != null) return;
        BlockTypes.GRAY_CARPET = AllayBlockType
                .builder(BlockGrayCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CARPET)
                .build();
    }

    public static void initGrayConcrete() {
        if (BlockTypes.GRAY_CONCRETE != null) return;
        BlockTypes.GRAY_CONCRETE = AllayBlockType
                .builder(BlockGrayConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE)
                .build();
    }

    public static void initGrayConcretePowder() {
        if (BlockTypes.GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.GRAY_CONCRETE_POWDER = AllayBlockType
                .builder(BlockGrayConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initGrayGlazedTerracotta() {
        if (BlockTypes.GRAY_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GRAY_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockGrayGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGrayShulkerBox() {
        if (BlockTypes.GRAY_SHULKER_BOX != null) return;
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .build();
    }

    public static void initGrayStainedGlass() {
        if (BlockTypes.GRAY_STAINED_GLASS != null) return;
        BlockTypes.GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockGrayStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS)
                .build();
    }

    public static void initGrayStainedGlassPane() {
        if (BlockTypes.GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockGrayStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGrayTerracotta() {
        if (BlockTypes.GRAY_TERRACOTTA != null) return;
        BlockTypes.GRAY_TERRACOTTA = AllayBlockType
                .builder(BlockGrayTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_TERRACOTTA)
                .build();
    }

    public static void initGrayWool() {
        if (BlockTypes.GRAY_WOOL != null) return;
        BlockTypes.GRAY_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_WOOL)
                .build();
    }

    public static void initGreenCandle() {
        if (BlockTypes.GREEN_CANDLE != null) return;
        BlockTypes.GREEN_CANDLE = AllayBlockType
                .builder(BlockGreenCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCandleCake() {
        if (BlockTypes.GREEN_CANDLE_CAKE != null) return;
        BlockTypes.GREEN_CANDLE_CAKE = AllayBlockType
                .builder(BlockGreenCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCarpet() {
        if (BlockTypes.GREEN_CARPET != null) return;
        BlockTypes.GREEN_CARPET = AllayBlockType
                .builder(BlockGreenCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CARPET)
                .build();
    }

    public static void initGreenConcrete() {
        if (BlockTypes.GREEN_CONCRETE != null) return;
        BlockTypes.GREEN_CONCRETE = AllayBlockType
                .builder(BlockGreenConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE)
                .build();
    }

    public static void initGreenConcretePowder() {
        if (BlockTypes.GREEN_CONCRETE_POWDER != null) return;
        BlockTypes.GREEN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockGreenConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE_POWDER)
                .build();
    }

    public static void initGreenGlazedTerracotta() {
        if (BlockTypes.GREEN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GREEN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockGreenGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGreenShulkerBox() {
        if (BlockTypes.GREEN_SHULKER_BOX != null) return;
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .build();
    }

    public static void initGreenStainedGlass() {
        if (BlockTypes.GREEN_STAINED_GLASS != null) return;
        BlockTypes.GREEN_STAINED_GLASS = AllayBlockType
                .builder(BlockGreenStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS)
                .build();
    }

    public static void initGreenStainedGlassPane() {
        if (BlockTypes.GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.GREEN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockGreenStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGreenTerracotta() {
        if (BlockTypes.GREEN_TERRACOTTA != null) return;
        BlockTypes.GREEN_TERRACOTTA = AllayBlockType
                .builder(BlockGreenTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_TERRACOTTA)
                .build();
    }

    public static void initGreenWool() {
        if (BlockTypes.GREEN_WOOL != null) return;
        BlockTypes.GREEN_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_WOOL)
                .build();
    }

    public static void initGrindstone() {
        if (BlockTypes.GRINDSTONE != null) return;
        BlockTypes.GRINDSTONE = AllayBlockType
                .builder(BlockGrindstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.GRINDSTONE)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initHangingRoots() {
        if (BlockTypes.HANGING_ROOTS != null) return;
        BlockTypes.HANGING_ROOTS = AllayBlockType
                .builder(BlockHangingRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.HANGING_ROOTS)
                .build();
    }

    public static void initHardBlackStainedGlass() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBlackStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS)
                .build();
    }

    public static void initHardBlackStainedGlassPane() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBlackStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBlueStainedGlass() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBlueStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardBlueStainedGlassPane() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBlueStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBrownStainedGlass() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBrownStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS)
                .build();
    }

    public static void initHardBrownStainedGlassPane() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBrownStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardCyanStainedGlass() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardCyanStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS)
                .build();
    }

    public static void initHardCyanStainedGlassPane() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardCyanStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGlass() {
        if (BlockTypes.HARD_GLASS != null) return;
        BlockTypes.HARD_GLASS = AllayBlockType
                .builder(BlockHardGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GLASS)
                .build();
    }

    public static void initHardGlassPane() {
        if (BlockTypes.HARD_GLASS_PANE != null) return;
        BlockTypes.HARD_GLASS_PANE = AllayBlockType
                .builder(BlockHardGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GLASS_PANE)
                .build();
    }

    public static void initHardGrayStainedGlass() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockHardGrayStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardGrayStainedGlassPane() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardGrayStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGreenStainedGlass() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardGreenStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS)
                .build();
    }

    public static void initHardGreenStainedGlassPane() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardGreenStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightBlueStainedGlass() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLightBlueStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardLightBlueStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLightBlueStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightGrayStainedGlass() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLightGrayStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardLightGrayStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLightGrayStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLimeStainedGlass() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLimeStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS)
                .build();
    }

    public static void initHardLimeStainedGlassPane() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLimeStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardMagentaStainedGlass() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS = AllayBlockType
                .builder(BlockHardMagentaStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initHardMagentaStainedGlassPane() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardMagentaStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardOrangeStainedGlass() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardOrangeStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initHardOrangeStainedGlassPane() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardOrangeStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPinkStainedGlass() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS = AllayBlockType
                .builder(BlockHardPinkStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS)
                .build();
    }

    public static void initHardPinkStainedGlassPane() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardPinkStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPurpleStainedGlass() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardPurpleStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initHardPurpleStainedGlassPane() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardPurpleStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardRedStainedGlass() {
        if (BlockTypes.HARD_RED_STAINED_GLASS != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS = AllayBlockType
                .builder(BlockHardRedStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS)
                .build();
    }

    public static void initHardRedStainedGlassPane() {
        if (BlockTypes.HARD_RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardRedStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardWhiteStainedGlass() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardWhiteStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS)
                .build();
    }

    public static void initHardWhiteStainedGlassPane() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardWhiteStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardYellowStainedGlass() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS = AllayBlockType
                .builder(BlockHardYellowStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initHardYellowStainedGlassPane() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardYellowStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardenedClay() {
        if (BlockTypes.HARDENED_CLAY != null) return;
        BlockTypes.HARDENED_CLAY = AllayBlockType
                .builder(BlockHardenedClayBehaviorImpl.class)
                .vanillaBlock(BlockId.HARDENED_CLAY)
                .build();
    }

    public static void initHayBlock() {
        if (BlockTypes.HAY_BLOCK != null) return;
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initHeavyCore() {
        if (BlockTypes.HEAVY_CORE != null) return;
        BlockTypes.HEAVY_CORE = AllayBlockType
                .builder(BlockHeavyCoreBehaviorImpl.class)
                .vanillaBlock(BlockId.HEAVY_CORE)
                .build();
    }

    public static void initHeavyWeightedPressurePlate() {
        if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockHeavyWeightedPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initHoneyBlock() {
        if (BlockTypes.HONEY_BLOCK != null) return;
        BlockTypes.HONEY_BLOCK = AllayBlockType
                .builder(BlockHoneyBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HONEY_BLOCK)
                .build();
    }

    public static void initHoneycombBlock() {
        if (BlockTypes.HONEYCOMB_BLOCK != null) return;
        BlockTypes.HONEYCOMB_BLOCK = AllayBlockType
                .builder(BlockHoneycombBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HONEYCOMB_BLOCK)
                .build();
    }

    public static void initHopper() {
        if (BlockTypes.HOPPER != null) return;
        BlockTypes.HOPPER = AllayBlockType
                .builder(BlockHopperBehaviorImpl.class)
                .vanillaBlock(BlockId.HOPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initHornCoral() {
        if (BlockTypes.HORN_CORAL != null) return;
        BlockTypes.HORN_CORAL = AllayBlockType
                .builder(BlockHornCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.HORN_CORAL)
                .build();
    }

    public static void initHornCoralBlock() {
        if (BlockTypes.HORN_CORAL_BLOCK != null) return;
        BlockTypes.HORN_CORAL_BLOCK = AllayBlockType
                .builder(BlockHornCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HORN_CORAL_BLOCK)
                .build();
    }

    public static void initHornCoralFan() {
        if (BlockTypes.HORN_CORAL_FAN != null) return;
        BlockTypes.HORN_CORAL_FAN = AllayBlockType
                .builder(BlockHornCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initHornCoralWallFan() {
        if (BlockTypes.HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.HORN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockHornCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initIce() {
        if (BlockTypes.ICE != null) return;
        BlockTypes.ICE = AllayBlockType
                .builder(BlockIceBehaviorImpl.class)
                .vanillaBlock(BlockId.ICE)
                .build();
    }

    public static void initInfestedChiseledStoneBricks() {
        if (BlockTypes.INFESTED_CHISELED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CHISELED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedChiseledStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedCobblestone() {
        if (BlockTypes.INFESTED_COBBLESTONE != null) return;
        BlockTypes.INFESTED_COBBLESTONE = AllayBlockType
                .builder(BlockInfestedCobblestoneBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_COBBLESTONE)
                .build();
    }

    public static void initInfestedCrackedStoneBricks() {
        if (BlockTypes.INFESTED_CRACKED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CRACKED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedCrackedStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedDeepslate() {
        if (BlockTypes.INFESTED_DEEPSLATE != null) return;
        BlockTypes.INFESTED_DEEPSLATE = AllayBlockType
                .builder(BlockInfestedDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initInfestedMossyStoneBricks() {
        if (BlockTypes.INFESTED_MOSSY_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_MOSSY_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedMossyStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initInfestedStone() {
        if (BlockTypes.INFESTED_STONE != null) return;
        BlockTypes.INFESTED_STONE = AllayBlockType
                .builder(BlockInfestedStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_STONE)
                .build();
    }

    public static void initInfestedStoneBricks() {
        if (BlockTypes.INFESTED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.INFESTED_STONE_BRICKS)
                .build();
    }

    public static void initInfoUpdate() {
        if (BlockTypes.INFO_UPDATE != null) return;
        BlockTypes.INFO_UPDATE = AllayBlockType
                .builder(BlockInfoUpdateBehaviorImpl.class)
                .vanillaBlock(BlockId.INFO_UPDATE)
                .build();
    }

    public static void initInfoUpdate2() {
        if (BlockTypes.INFO_UPDATE2 != null) return;
        BlockTypes.INFO_UPDATE2 = AllayBlockType
                .builder(BlockInfoUpdate2BehaviorImpl.class)
                .vanillaBlock(BlockId.INFO_UPDATE2)
                .build();
    }

    public static void initInvisibleBedrock() {
        if (BlockTypes.INVISIBLE_BEDROCK != null) return;
        BlockTypes.INVISIBLE_BEDROCK = AllayBlockType
                .builder(BlockInvisibleBedrockBehaviorImpl.class)
                .vanillaBlock(BlockId.INVISIBLE_BEDROCK)
                .build();
    }

    public static void initIronBars() {
        if (BlockTypes.IRON_BARS != null) return;
        BlockTypes.IRON_BARS = AllayBlockType
                .builder(BlockIronBarsBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_BARS)
                .build();
    }

    public static void initIronBlock() {
        if (BlockTypes.IRON_BLOCK != null) return;
        BlockTypes.IRON_BLOCK = AllayBlockType
                .builder(BlockIronBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_BLOCK)
                .build();
    }

    public static void initIronDoor() {
        if (BlockTypes.IRON_DOOR != null) return;
        BlockTypes.IRON_DOOR = AllayBlockType
                .builder(BlockIronDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initIronOre() {
        if (BlockTypes.IRON_ORE != null) return;
        BlockTypes.IRON_ORE = AllayBlockType
                .builder(BlockIronOreBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_ORE)
                .build();
    }

    public static void initIronTrapdoor() {
        if (BlockTypes.IRON_TRAPDOOR != null) return;
        BlockTypes.IRON_TRAPDOOR = AllayBlockType
                .builder(BlockIronTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.IRON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initJigsaw() {
        if (BlockTypes.JIGSAW != null) return;
        BlockTypes.JIGSAW = AllayBlockType
                .builder(BlockJigsawBehaviorImpl.class)
                .vanillaBlock(BlockId.JIGSAW)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ROTATION)
                .build();
    }

    public static void initJukebox() {
        if (BlockTypes.JUKEBOX != null) return;
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehaviorImpl.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .build();
    }

    public static void initJungleButton() {
        if (BlockTypes.JUNGLE_BUTTON != null) return;
        BlockTypes.JUNGLE_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleDoor() {
        if (BlockTypes.JUNGLE_DOOR != null) return;
        BlockTypes.JUNGLE_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initJungleDoubleSlab() {
        if (BlockTypes.JUNGLE_DOUBLE_SLAB != null) return;
        BlockTypes.JUNGLE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockJungleDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleFence() {
        if (BlockTypes.JUNGLE_FENCE != null) return;
        BlockTypes.JUNGLE_FENCE = AllayBlockType
                .builder(BlockJungleFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE)
                .build();
    }

    public static void initJungleFenceGate() {
        if (BlockTypes.JUNGLE_FENCE_GATE != null) return;
        BlockTypes.JUNGLE_FENCE_GATE = AllayBlockType
                .builder(BlockJungleFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initJungleHangingSign() {
        if (BlockTypes.JUNGLE_HANGING_SIGN != null) return;
        BlockTypes.JUNGLE_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initJungleLeaves() {
        if (BlockTypes.JUNGLE_LEAVES != null) return;
        BlockTypes.JUNGLE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initJungleLog() {
        if (BlockTypes.JUNGLE_LOG != null) return;
        BlockTypes.JUNGLE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initJunglePlanks() {
        if (BlockTypes.JUNGLE_PLANKS != null) return;
        BlockTypes.JUNGLE_PLANKS = AllayBlockType
                .builder(BlockJunglePlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_PLANKS)
                .build();
    }

    public static void initJunglePressurePlate() {
        if (BlockTypes.JUNGLE_PRESSURE_PLATE != null) return;
        BlockTypes.JUNGLE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockJunglePressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initJungleSapling() {
        if (BlockTypes.JUNGLE_SAPLING != null) return;
        BlockTypes.JUNGLE_SAPLING = AllayBlockType
                .builder(BlockJungleSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initJungleSlab() {
        if (BlockTypes.JUNGLE_SLAB != null) return;
        BlockTypes.JUNGLE_SLAB = AllayBlockType
                .builder(BlockJungleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleStairs() {
        if (BlockTypes.JUNGLE_STAIRS != null) return;
        BlockTypes.JUNGLE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initJungleStandingSign() {
        if (BlockTypes.JUNGLE_STANDING_SIGN != null) return;
        BlockTypes.JUNGLE_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initJungleTrapdoor() {
        if (BlockTypes.JUNGLE_TRAPDOOR != null) return;
        BlockTypes.JUNGLE_TRAPDOOR = AllayBlockType
                .builder(BlockJungleTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initJungleWallSign() {
        if (BlockTypes.JUNGLE_WALL_SIGN != null) return;
        BlockTypes.JUNGLE_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleWood() {
        if (BlockTypes.JUNGLE_WOOD != null) return;
        BlockTypes.JUNGLE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initKelp() {
        if (BlockTypes.KELP != null) return;
        BlockTypes.KELP = AllayBlockType
                .builder(BlockKelpBehaviorImpl.class)
                .vanillaBlock(BlockId.KELP)
                .setProperties(BlockPropertyTypes.KELP_AGE)
                .build();
    }

    public static void initLabTable() {
        if (BlockTypes.LAB_TABLE != null) return;
        BlockTypes.LAB_TABLE = AllayBlockType
                .builder(BlockLabTableBehaviorImpl.class)
                .vanillaBlock(BlockId.LAB_TABLE)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initLadder() {
        if (BlockTypes.LADDER != null) return;
        BlockTypes.LADDER = AllayBlockType
                .builder(BlockLadderBehaviorImpl.class)
                .vanillaBlock(BlockId.LADDER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLantern() {
        if (BlockTypes.LANTERN != null) return;
        BlockTypes.LANTERN = AllayBlockType
                .builder(BlockLanternBehaviorImpl.class)
                .vanillaBlock(BlockId.LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initLapisBlock() {
        if (BlockTypes.LAPIS_BLOCK != null) return;
        BlockTypes.LAPIS_BLOCK = AllayBlockType
                .builder(BlockLapisBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.LAPIS_BLOCK)
                .build();
    }

    public static void initLapisOre() {
        if (BlockTypes.LAPIS_ORE != null) return;
        BlockTypes.LAPIS_ORE = AllayBlockType
                .builder(BlockLapisOreBehaviorImpl.class)
                .vanillaBlock(BlockId.LAPIS_ORE)
                .build();
    }

    public static void initLargeAmethystBud() {
        if (BlockTypes.LARGE_AMETHYST_BUD != null) return;
        BlockTypes.LARGE_AMETHYST_BUD = AllayBlockType
                .builder(BlockLargeAmethystBudBehaviorImpl.class)
                .vanillaBlock(BlockId.LARGE_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initLargeFern() {
        if (BlockTypes.LARGE_FERN != null) return;
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehaviorImpl.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLava() {
        if (BlockTypes.LAVA != null) return;
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLavaBehaviorImpl.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initLectern() {
        if (BlockTypes.LECTERN != null) return;
        BlockTypes.LECTERN = AllayBlockType
                .builder(BlockLecternBehaviorImpl.class)
                .vanillaBlock(BlockId.LECTERN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initLever() {
        if (BlockTypes.LEVER != null) return;
        BlockTypes.LEVER = AllayBlockType
                .builder(BlockLeverBehaviorImpl.class)
                .vanillaBlock(BlockId.LEVER)
                .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initLightBlock0() {
        if (BlockTypes.LIGHT_BLOCK_0 != null) return;
        BlockTypes.LIGHT_BLOCK_0 = AllayBlockType
                .builder(BlockLightBlock0BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_0)
                .build();
    }

    public static void initLightBlock1() {
        if (BlockTypes.LIGHT_BLOCK_1 != null) return;
        BlockTypes.LIGHT_BLOCK_1 = AllayBlockType
                .builder(BlockLightBlock1BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_1)
                .build();
    }

    public static void initLightBlock10() {
        if (BlockTypes.LIGHT_BLOCK_10 != null) return;
        BlockTypes.LIGHT_BLOCK_10 = AllayBlockType
                .builder(BlockLightBlock10BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_10)
                .build();
    }

    public static void initLightBlock11() {
        if (BlockTypes.LIGHT_BLOCK_11 != null) return;
        BlockTypes.LIGHT_BLOCK_11 = AllayBlockType
                .builder(BlockLightBlock11BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_11)
                .build();
    }

    public static void initLightBlock12() {
        if (BlockTypes.LIGHT_BLOCK_12 != null) return;
        BlockTypes.LIGHT_BLOCK_12 = AllayBlockType
                .builder(BlockLightBlock12BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_12)
                .build();
    }

    public static void initLightBlock13() {
        if (BlockTypes.LIGHT_BLOCK_13 != null) return;
        BlockTypes.LIGHT_BLOCK_13 = AllayBlockType
                .builder(BlockLightBlock13BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_13)
                .build();
    }

    public static void initLightBlock14() {
        if (BlockTypes.LIGHT_BLOCK_14 != null) return;
        BlockTypes.LIGHT_BLOCK_14 = AllayBlockType
                .builder(BlockLightBlock14BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_14)
                .build();
    }

    public static void initLightBlock15() {
        if (BlockTypes.LIGHT_BLOCK_15 != null) return;
        BlockTypes.LIGHT_BLOCK_15 = AllayBlockType
                .builder(BlockLightBlock15BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_15)
                .build();
    }

    public static void initLightBlock2() {
        if (BlockTypes.LIGHT_BLOCK_2 != null) return;
        BlockTypes.LIGHT_BLOCK_2 = AllayBlockType
                .builder(BlockLightBlock2BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_2)
                .build();
    }

    public static void initLightBlock3() {
        if (BlockTypes.LIGHT_BLOCK_3 != null) return;
        BlockTypes.LIGHT_BLOCK_3 = AllayBlockType
                .builder(BlockLightBlock3BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_3)
                .build();
    }

    public static void initLightBlock4() {
        if (BlockTypes.LIGHT_BLOCK_4 != null) return;
        BlockTypes.LIGHT_BLOCK_4 = AllayBlockType
                .builder(BlockLightBlock4BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_4)
                .build();
    }

    public static void initLightBlock5() {
        if (BlockTypes.LIGHT_BLOCK_5 != null) return;
        BlockTypes.LIGHT_BLOCK_5 = AllayBlockType
                .builder(BlockLightBlock5BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_5)
                .build();
    }

    public static void initLightBlock6() {
        if (BlockTypes.LIGHT_BLOCK_6 != null) return;
        BlockTypes.LIGHT_BLOCK_6 = AllayBlockType
                .builder(BlockLightBlock6BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_6)
                .build();
    }

    public static void initLightBlock7() {
        if (BlockTypes.LIGHT_BLOCK_7 != null) return;
        BlockTypes.LIGHT_BLOCK_7 = AllayBlockType
                .builder(BlockLightBlock7BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_7)
                .build();
    }

    public static void initLightBlock8() {
        if (BlockTypes.LIGHT_BLOCK_8 != null) return;
        BlockTypes.LIGHT_BLOCK_8 = AllayBlockType
                .builder(BlockLightBlock8BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_8)
                .build();
    }

    public static void initLightBlock9() {
        if (BlockTypes.LIGHT_BLOCK_9 != null) return;
        BlockTypes.LIGHT_BLOCK_9 = AllayBlockType
                .builder(BlockLightBlock9BehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_9)
                .build();
    }

    public static void initLightBlueCandle() {
        if (BlockTypes.LIGHT_BLUE_CANDLE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE = AllayBlockType
                .builder(BlockLightBlueCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCandleCake() {
        if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockLightBlueCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCarpet() {
        if (BlockTypes.LIGHT_BLUE_CARPET != null) return;
        BlockTypes.LIGHT_BLUE_CARPET = AllayBlockType
                .builder(BlockLightBlueCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CARPET)
                .build();
    }

    public static void initLightBlueConcrete() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE = AllayBlockType
                .builder(BlockLightBlueConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE)
                .build();
    }

    public static void initLightBlueConcretePowder() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLightBlueConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initLightBlueGlazedTerracotta() {
        if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockLightBlueGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLightBlueShulkerBox() {
        if (BlockTypes.LIGHT_BLUE_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .build();
    }

    public static void initLightBlueStainedGlass() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockLightBlueStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initLightBlueStainedGlassPane() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLightBlueStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightBlueTerracotta() {
        if (BlockTypes.LIGHT_BLUE_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_TERRACOTTA = AllayBlockType
                .builder(BlockLightBlueTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_TERRACOTTA)
                .build();
    }

    public static void initLightBlueWool() {
        if (BlockTypes.LIGHT_BLUE_WOOL != null) return;
        BlockTypes.LIGHT_BLUE_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_WOOL)
                .build();
    }

    public static void initLightGrayCandle() {
        if (BlockTypes.LIGHT_GRAY_CANDLE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE = AllayBlockType
                .builder(BlockLightGrayCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCandleCake() {
        if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockLightGrayCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCarpet() {
        if (BlockTypes.LIGHT_GRAY_CARPET != null) return;
        BlockTypes.LIGHT_GRAY_CARPET = AllayBlockType
                .builder(BlockLightGrayCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CARPET)
                .build();
    }

    public static void initLightGrayConcrete() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE = AllayBlockType
                .builder(BlockLightGrayConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE)
                .build();
    }

    public static void initLightGrayConcretePowder() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLightGrayConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initLightGrayShulkerBox() {
        if (BlockTypes.LIGHT_GRAY_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .build();
    }

    public static void initLightGrayStainedGlass() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockLightGrayStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initLightGrayStainedGlassPane() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLightGrayStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightGrayTerracotta() {
        if (BlockTypes.LIGHT_GRAY_TERRACOTTA != null) return;
        BlockTypes.LIGHT_GRAY_TERRACOTTA = AllayBlockType
                .builder(BlockLightGrayTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_TERRACOTTA)
                .build();
    }

    public static void initLightGrayWool() {
        if (BlockTypes.LIGHT_GRAY_WOOL != null) return;
        BlockTypes.LIGHT_GRAY_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_WOOL)
                .build();
    }

    public static void initLightWeightedPressurePlate() {
        if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockLightWeightedPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initLightningRod() {
        if (BlockTypes.LIGHTNING_ROD != null) return;
        BlockTypes.LIGHTNING_ROD = AllayBlockType
                .builder(BlockLightningRodBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLilac() {
        if (BlockTypes.LILAC != null) return;
        BlockTypes.LILAC = AllayBlockType
                .builder(BlockLilacBehaviorImpl.class)
                .vanillaBlock(BlockId.LILAC)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLilyOfTheValley() {
        if (BlockTypes.LILY_OF_THE_VALLEY != null) return;
        BlockTypes.LILY_OF_THE_VALLEY = AllayBlockType
                .builder(BlockLilyOfTheValleyBehaviorImpl.class)
                .vanillaBlock(BlockId.LILY_OF_THE_VALLEY)
                .build();
    }

    public static void initLimeCandle() {
        if (BlockTypes.LIME_CANDLE != null) return;
        BlockTypes.LIME_CANDLE = AllayBlockType
                .builder(BlockLimeCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCandleCake() {
        if (BlockTypes.LIME_CANDLE_CAKE != null) return;
        BlockTypes.LIME_CANDLE_CAKE = AllayBlockType
                .builder(BlockLimeCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCarpet() {
        if (BlockTypes.LIME_CARPET != null) return;
        BlockTypes.LIME_CARPET = AllayBlockType
                .builder(BlockLimeCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CARPET)
                .build();
    }

    public static void initLimeConcrete() {
        if (BlockTypes.LIME_CONCRETE != null) return;
        BlockTypes.LIME_CONCRETE = AllayBlockType
                .builder(BlockLimeConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CONCRETE)
                .build();
    }

    public static void initLimeConcretePowder() {
        if (BlockTypes.LIME_CONCRETE_POWDER != null) return;
        BlockTypes.LIME_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLimeConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_CONCRETE_POWDER)
                .build();
    }

    public static void initLimeGlazedTerracotta() {
        if (BlockTypes.LIME_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIME_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockLimeGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLimeShulkerBox() {
        if (BlockTypes.LIME_SHULKER_BOX != null) return;
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .build();
    }

    public static void initLimeStainedGlass() {
        if (BlockTypes.LIME_STAINED_GLASS != null) return;
        BlockTypes.LIME_STAINED_GLASS = AllayBlockType
                .builder(BlockLimeStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS)
                .build();
    }

    public static void initLimeStainedGlassPane() {
        if (BlockTypes.LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIME_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLimeStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLimeTerracotta() {
        if (BlockTypes.LIME_TERRACOTTA != null) return;
        BlockTypes.LIME_TERRACOTTA = AllayBlockType
                .builder(BlockLimeTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_TERRACOTTA)
                .build();
    }

    public static void initLimeWool() {
        if (BlockTypes.LIME_WOOL != null) return;
        BlockTypes.LIME_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_WOOL)
                .build();
    }

    public static void initLitBlastFurnace() {
        if (BlockTypes.LIT_BLAST_FURNACE != null) return;
        BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitDeepslateRedstoneOre() {
        if (BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = AllayBlockType
                .builder(BlockLitDeepslateRedstoneOreBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initLitFurnace() {
        if (BlockTypes.LIT_FURNACE != null) return;
        BlockTypes.LIT_FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitPumpkin() {
        if (BlockTypes.LIT_PUMPKIN != null) return;
        BlockTypes.LIT_PUMPKIN = AllayBlockType
                .builder(BlockLitPumpkinBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitRedstoneLamp() {
        if (BlockTypes.LIT_REDSTONE_LAMP != null) return;
        BlockTypes.LIT_REDSTONE_LAMP = AllayBlockType
                .builder(BlockLitRedstoneLampBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_LAMP)
                .build();
    }

    public static void initLitRedstoneOre() {
        if (BlockTypes.LIT_REDSTONE_ORE != null) return;
        BlockTypes.LIT_REDSTONE_ORE = AllayBlockType
                .builder(BlockLitRedstoneOreBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_ORE)
                .build();
    }

    public static void initLitSmoker() {
        if (BlockTypes.LIT_SMOKER != null) return;
        BlockTypes.LIT_SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLodestone() {
        if (BlockTypes.LODESTONE != null) return;
        BlockTypes.LODESTONE = AllayBlockType
                .builder(BlockLodestoneBehaviorImpl.class)
                .vanillaBlock(BlockId.LODESTONE)
                .build();
    }

    public static void initLoom() {
        if (BlockTypes.LOOM != null) return;
        BlockTypes.LOOM = AllayBlockType
                .builder(BlockLoomBehaviorImpl.class)
                .vanillaBlock(BlockId.LOOM)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initMagentaCandle() {
        if (BlockTypes.MAGENTA_CANDLE != null) return;
        BlockTypes.MAGENTA_CANDLE = AllayBlockType
                .builder(BlockMagentaCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCandleCake() {
        if (BlockTypes.MAGENTA_CANDLE_CAKE != null) return;
        BlockTypes.MAGENTA_CANDLE_CAKE = AllayBlockType
                .builder(BlockMagentaCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCarpet() {
        if (BlockTypes.MAGENTA_CARPET != null) return;
        BlockTypes.MAGENTA_CARPET = AllayBlockType
                .builder(BlockMagentaCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CARPET)
                .build();
    }

    public static void initMagentaConcrete() {
        if (BlockTypes.MAGENTA_CONCRETE != null) return;
        BlockTypes.MAGENTA_CONCRETE = AllayBlockType
                .builder(BlockMagentaConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE)
                .build();
    }

    public static void initMagentaConcretePowder() {
        if (BlockTypes.MAGENTA_CONCRETE_POWDER != null) return;
        BlockTypes.MAGENTA_CONCRETE_POWDER = AllayBlockType
                .builder(BlockMagentaConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE_POWDER)
                .build();
    }

    public static void initMagentaGlazedTerracotta() {
        if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockMagentaGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMagentaShulkerBox() {
        if (BlockTypes.MAGENTA_SHULKER_BOX != null) return;
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .build();
    }

    public static void initMagentaStainedGlass() {
        if (BlockTypes.MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS = AllayBlockType
                .builder(BlockMagentaStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initMagentaStainedGlassPane() {
        if (BlockTypes.MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockMagentaStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initMagentaTerracotta() {
        if (BlockTypes.MAGENTA_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_TERRACOTTA = AllayBlockType
                .builder(BlockMagentaTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_TERRACOTTA)
                .build();
    }

    public static void initMagentaWool() {
        if (BlockTypes.MAGENTA_WOOL != null) return;
        BlockTypes.MAGENTA_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_WOOL)
                .build();
    }

    public static void initMagma() {
        if (BlockTypes.MAGMA != null) return;
        BlockTypes.MAGMA = AllayBlockType
                .builder(BlockMagmaBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGMA)
                .build();
    }

    public static void initMangroveButton() {
        if (BlockTypes.MANGROVE_BUTTON != null) return;
        BlockTypes.MANGROVE_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveDoor() {
        if (BlockTypes.MANGROVE_DOOR != null) return;
        BlockTypes.MANGROVE_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initMangroveDoubleSlab() {
        if (BlockTypes.MANGROVE_DOUBLE_SLAB != null) return;
        BlockTypes.MANGROVE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMangroveDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveFence() {
        if (BlockTypes.MANGROVE_FENCE != null) return;
        BlockTypes.MANGROVE_FENCE = AllayBlockType
                .builder(BlockMangroveFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE)
                .build();
    }

    public static void initMangroveFenceGate() {
        if (BlockTypes.MANGROVE_FENCE_GATE != null) return;
        BlockTypes.MANGROVE_FENCE_GATE = AllayBlockType
                .builder(BlockMangroveFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initMangroveHangingSign() {
        if (BlockTypes.MANGROVE_HANGING_SIGN != null) return;
        BlockTypes.MANGROVE_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initMangroveLeaves() {
        if (BlockTypes.MANGROVE_LEAVES != null) return;
        BlockTypes.MANGROVE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initMangroveLog() {
        if (BlockTypes.MANGROVE_LOG != null) return;
        BlockTypes.MANGROVE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMangrovePlanks() {
        if (BlockTypes.MANGROVE_PLANKS != null) return;
        BlockTypes.MANGROVE_PLANKS = AllayBlockType
                .builder(BlockMangrovePlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_PLANKS)
                .build();
    }

    public static void initMangrovePressurePlate() {
        if (BlockTypes.MANGROVE_PRESSURE_PLATE != null) return;
        BlockTypes.MANGROVE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockMangrovePressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initMangrovePropagule() {
        if (BlockTypes.MANGROVE_PROPAGULE != null) return;
        BlockTypes.MANGROVE_PROPAGULE = AllayBlockType
                .builder(BlockMangrovePropaguleBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                .build();
    }

    public static void initMangroveRoots() {
        if (BlockTypes.MANGROVE_ROOTS != null) return;
        BlockTypes.MANGROVE_ROOTS = AllayBlockType
                .builder(BlockMangroveRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_ROOTS)
                .build();
    }

    public static void initMangroveSlab() {
        if (BlockTypes.MANGROVE_SLAB != null) return;
        BlockTypes.MANGROVE_SLAB = AllayBlockType
                .builder(BlockMangroveSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveStairs() {
        if (BlockTypes.MANGROVE_STAIRS != null) return;
        BlockTypes.MANGROVE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMangroveStandingSign() {
        if (BlockTypes.MANGROVE_STANDING_SIGN != null) return;
        BlockTypes.MANGROVE_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initMangroveTrapdoor() {
        if (BlockTypes.MANGROVE_TRAPDOOR != null) return;
        BlockTypes.MANGROVE_TRAPDOOR = AllayBlockType
                .builder(BlockMangroveTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initMangroveWallSign() {
        if (BlockTypes.MANGROVE_WALL_SIGN != null) return;
        BlockTypes.MANGROVE_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveWood() {
        if (BlockTypes.MANGROVE_WOOD != null) return;
        BlockTypes.MANGROVE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMaterialReducer() {
        if (BlockTypes.MATERIAL_REDUCER != null) return;
        BlockTypes.MATERIAL_REDUCER = AllayBlockType
                .builder(BlockMaterialReducerBehaviorImpl.class)
                .vanillaBlock(BlockId.MATERIAL_REDUCER)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initMediumAmethystBud() {
        if (BlockTypes.MEDIUM_AMETHYST_BUD != null) return;
        BlockTypes.MEDIUM_AMETHYST_BUD = AllayBlockType
                .builder(BlockMediumAmethystBudBehaviorImpl.class)
                .vanillaBlock(BlockId.MEDIUM_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initMelonBlock() {
        if (BlockTypes.MELON_BLOCK != null) return;
        BlockTypes.MELON_BLOCK = AllayBlockType
                .builder(BlockMelonBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MELON_BLOCK)
                .build();
    }

    public static void initMelonStem() {
        if (BlockTypes.MELON_STEM != null) return;
        BlockTypes.MELON_STEM = AllayBlockType
                .builder(BlockMelonStemBehaviorImpl.class)
                .vanillaBlock(BlockId.MELON_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initMobSpawner() {
        if (BlockTypes.MOB_SPAWNER != null) return;
        BlockTypes.MOB_SPAWNER = AllayBlockType
                .builder(BlockMobSpawnerBehaviorImpl.class)
                .vanillaBlock(BlockId.MOB_SPAWNER)
                .build();
    }

    public static void initMossBlock() {
        if (BlockTypes.MOSS_BLOCK != null) return;
        BlockTypes.MOSS_BLOCK = AllayBlockType
                .builder(BlockMossBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSS_BLOCK)
                .build();
    }

    public static void initMossCarpet() {
        if (BlockTypes.MOSS_CARPET != null) return;
        BlockTypes.MOSS_CARPET = AllayBlockType
                .builder(BlockMossCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSS_CARPET)
                .build();
    }

    public static void initMossyCobblestone() {
        if (BlockTypes.MOSSY_COBBLESTONE != null) return;
        BlockTypes.MOSSY_COBBLESTONE = AllayBlockType
                .builder(BlockMossyCobblestoneBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE)
                .build();
    }

    public static void initMossyCobblestoneDoubleSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMossyCobblestoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_SLAB = AllayBlockType
                .builder(BlockMossyCobblestoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneStairs() {
        if (BlockTypes.MOSSY_COBBLESTONE_STAIRS != null) return;
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyCobblestoneWall() {
        if (BlockTypes.MOSSY_COBBLESTONE_WALL != null) return;
        BlockTypes.MOSSY_COBBLESTONE_WALL = AllayBlockType
                .builder(BlockMossyCobblestoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initMossyStoneBrickDoubleSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMossyStoneBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockMossyStoneBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickStairs() {
        if (BlockTypes.MOSSY_STONE_BRICK_STAIRS != null) return;
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyStoneBrickWall() {
        if (BlockTypes.MOSSY_STONE_BRICK_WALL != null) return;
        BlockTypes.MOSSY_STONE_BRICK_WALL = AllayBlockType
                .builder(BlockMossyStoneBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initMossyStoneBricks() {
        if (BlockTypes.MOSSY_STONE_BRICKS != null) return;
        BlockTypes.MOSSY_STONE_BRICKS = AllayBlockType
                .builder(BlockMossyStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initMovingBlock() {
        if (BlockTypes.MOVING_BLOCK != null) return;
        BlockTypes.MOVING_BLOCK = AllayBlockType
                .builder(BlockMovingBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.MOVING_BLOCK)
                .build();
    }

    public static void initMud() {
        if (BlockTypes.MUD != null) return;
        BlockTypes.MUD = AllayBlockType
                .builder(BlockMudBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD)
                .build();
    }

    public static void initMudBrickDoubleSlab() {
        if (BlockTypes.MUD_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MUD_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMudBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickSlab() {
        if (BlockTypes.MUD_BRICK_SLAB != null) return;
        BlockTypes.MUD_BRICK_SLAB = AllayBlockType
                .builder(BlockMudBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickStairs() {
        if (BlockTypes.MUD_BRICK_STAIRS != null) return;
        BlockTypes.MUD_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMudBrickWall() {
        if (BlockTypes.MUD_BRICK_WALL != null) return;
        BlockTypes.MUD_BRICK_WALL = AllayBlockType
                .builder(BlockMudBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initMudBricks() {
        if (BlockTypes.MUD_BRICKS != null) return;
        BlockTypes.MUD_BRICKS = AllayBlockType
                .builder(BlockMudBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICKS)
                .build();
    }

    public static void initMuddyMangroveRoots() {
        if (BlockTypes.MUDDY_MANGROVE_ROOTS != null) return;
        BlockTypes.MUDDY_MANGROVE_ROOTS = AllayBlockType
                .builder(BlockMuddyMangroveRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.MUDDY_MANGROVE_ROOTS)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMushroomStem() {
        if (BlockTypes.MUSHROOM_STEM != null) return;
        BlockTypes.MUSHROOM_STEM = AllayBlockType
                .builder(BlockMushroomStemBehaviorImpl.class)
                .vanillaBlock(BlockId.MUSHROOM_STEM)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initMycelium() {
        if (BlockTypes.MYCELIUM != null) return;
        BlockTypes.MYCELIUM = AllayBlockType
                .builder(BlockMyceliumBehaviorImpl.class)
                .vanillaBlock(BlockId.MYCELIUM)
                .build();
    }

    public static void initNetherBrick() {
        if (BlockTypes.NETHER_BRICK != null) return;
        BlockTypes.NETHER_BRICK = AllayBlockType
                .builder(BlockNetherBrickBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK)
                .build();
    }

    public static void initNetherBrickDoubleSlab() {
        if (BlockTypes.NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockNetherBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickFence() {
        if (BlockTypes.NETHER_BRICK_FENCE != null) return;
        BlockTypes.NETHER_BRICK_FENCE = AllayBlockType
                .builder(BlockNetherBrickFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_FENCE)
                .build();
    }

    public static void initNetherBrickSlab() {
        if (BlockTypes.NETHER_BRICK_SLAB != null) return;
        BlockTypes.NETHER_BRICK_SLAB = AllayBlockType
                .builder(BlockNetherBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickStairs() {
        if (BlockTypes.NETHER_BRICK_STAIRS != null) return;
        BlockTypes.NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNetherBrickWall() {
        if (BlockTypes.NETHER_BRICK_WALL != null) return;
        BlockTypes.NETHER_BRICK_WALL = AllayBlockType
                .builder(BlockNetherBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initNetherGoldOre() {
        if (BlockTypes.NETHER_GOLD_ORE != null) return;
        BlockTypes.NETHER_GOLD_ORE = AllayBlockType
                .builder(BlockNetherGoldOreBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_GOLD_ORE)
                .build();
    }

    public static void initNetherSprouts() {
        if (BlockTypes.NETHER_SPROUTS != null) return;
        BlockTypes.NETHER_SPROUTS = AllayBlockType
                .builder(BlockNetherSproutsBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_SPROUTS)
                .build();
    }

    public static void initNetherWart() {
        if (BlockTypes.NETHER_WART != null) return;
        BlockTypes.NETHER_WART = AllayBlockType
                .builder(BlockNetherWartBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_WART)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initNetherWartBlock() {
        if (BlockTypes.NETHER_WART_BLOCK != null) return;
        BlockTypes.NETHER_WART_BLOCK = AllayBlockType
                .builder(BlockNetherWartBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_WART_BLOCK)
                .build();
    }

    public static void initNetheriteBlock() {
        if (BlockTypes.NETHERITE_BLOCK != null) return;
        BlockTypes.NETHERITE_BLOCK = AllayBlockType
                .builder(BlockNetheriteBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHERITE_BLOCK)
                .build();
    }

    public static void initNetherrack() {
        if (BlockTypes.NETHERRACK != null) return;
        BlockTypes.NETHERRACK = AllayBlockType
                .builder(BlockNetherrackBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHERRACK)
                .build();
    }

    public static void initNetherreactor() {
        if (BlockTypes.NETHERREACTOR != null) return;
        BlockTypes.NETHERREACTOR = AllayBlockType
                .builder(BlockNetherreactorBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHERREACTOR)
                .build();
    }

    public static void initNormalStoneDoubleSlab() {
        if (BlockTypes.NORMAL_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockNormalStoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.NORMAL_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneSlab() {
        if (BlockTypes.NORMAL_STONE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_SLAB = AllayBlockType
                .builder(BlockNormalStoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.NORMAL_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneStairs() {
        if (BlockTypes.NORMAL_STONE_STAIRS != null) return;
        BlockTypes.NORMAL_STONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNoteblock() {
        if (BlockTypes.NOTEBLOCK != null) return;
        BlockTypes.NOTEBLOCK = AllayBlockType
                .builder(BlockNoteblockBehaviorImpl.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .build();
    }

    public static void initOakDoubleSlab() {
        if (BlockTypes.OAK_DOUBLE_SLAB != null) return;
        BlockTypes.OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockOakDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakFence() {
        if (BlockTypes.OAK_FENCE != null) return;
        BlockTypes.OAK_FENCE = AllayBlockType
                .builder(BlockOakFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_FENCE)
                .build();
    }

    public static void initOakHangingSign() {
        if (BlockTypes.OAK_HANGING_SIGN != null) return;
        BlockTypes.OAK_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initOakLeaves() {
        if (BlockTypes.OAK_LEAVES != null) return;
        BlockTypes.OAK_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initOakLog() {
        if (BlockTypes.OAK_LOG != null) return;
        BlockTypes.OAK_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOakPlanks() {
        if (BlockTypes.OAK_PLANKS != null) return;
        BlockTypes.OAK_PLANKS = AllayBlockType
                .builder(BlockOakPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_PLANKS)
                .build();
    }

    public static void initOakSapling() {
        if (BlockTypes.OAK_SAPLING != null) return;
        BlockTypes.OAK_SAPLING = AllayBlockType
                .builder(BlockOakSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initOakSlab() {
        if (BlockTypes.OAK_SLAB != null) return;
        BlockTypes.OAK_SLAB = AllayBlockType
                .builder(BlockOakSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakStairs() {
        if (BlockTypes.OAK_STAIRS != null) return;
        BlockTypes.OAK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOakWood() {
        if (BlockTypes.OAK_WOOD != null) return;
        BlockTypes.OAK_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initObserver() {
        if (BlockTypes.OBSERVER != null) return;
        BlockTypes.OBSERVER = AllayBlockType
                .builder(BlockObserverBehaviorImpl.class)
                .vanillaBlock(BlockId.OBSERVER)
                .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initObsidian() {
        if (BlockTypes.OBSIDIAN != null) return;
        BlockTypes.OBSIDIAN = AllayBlockType
                .builder(BlockObsidianBehaviorImpl.class)
                .vanillaBlock(BlockId.OBSIDIAN)
                .build();
    }

    public static void initOchreFroglight() {
        if (BlockTypes.OCHRE_FROGLIGHT != null) return;
        BlockTypes.OCHRE_FROGLIGHT = AllayBlockType
                .builder(BlockOchreFroglightBehaviorImpl.class)
                .vanillaBlock(BlockId.OCHRE_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOrangeCandle() {
        if (BlockTypes.ORANGE_CANDLE != null) return;
        BlockTypes.ORANGE_CANDLE = AllayBlockType
                .builder(BlockOrangeCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCandleCake() {
        if (BlockTypes.ORANGE_CANDLE_CAKE != null) return;
        BlockTypes.ORANGE_CANDLE_CAKE = AllayBlockType
                .builder(BlockOrangeCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCarpet() {
        if (BlockTypes.ORANGE_CARPET != null) return;
        BlockTypes.ORANGE_CARPET = AllayBlockType
                .builder(BlockOrangeCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CARPET)
                .build();
    }

    public static void initOrangeConcrete() {
        if (BlockTypes.ORANGE_CONCRETE != null) return;
        BlockTypes.ORANGE_CONCRETE = AllayBlockType
                .builder(BlockOrangeConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE)
                .build();
    }

    public static void initOrangeConcretePowder() {
        if (BlockTypes.ORANGE_CONCRETE_POWDER != null) return;
        BlockTypes.ORANGE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockOrangeConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE_POWDER)
                .build();
    }

    public static void initOrangeGlazedTerracotta() {
        if (BlockTypes.ORANGE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.ORANGE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockOrangeGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initOrangeShulkerBox() {
        if (BlockTypes.ORANGE_SHULKER_BOX != null) return;
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .build();
    }

    public static void initOrangeStainedGlass() {
        if (BlockTypes.ORANGE_STAINED_GLASS != null) return;
        BlockTypes.ORANGE_STAINED_GLASS = AllayBlockType
                .builder(BlockOrangeStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initOrangeStainedGlassPane() {
        if (BlockTypes.ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.ORANGE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockOrangeStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initOrangeTerracotta() {
        if (BlockTypes.ORANGE_TERRACOTTA != null) return;
        BlockTypes.ORANGE_TERRACOTTA = AllayBlockType
                .builder(BlockOrangeTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_TERRACOTTA)
                .build();
    }

    public static void initOrangeTulip() {
        if (BlockTypes.ORANGE_TULIP != null) return;
        BlockTypes.ORANGE_TULIP = AllayBlockType
                .builder(BlockOrangeTulipBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_TULIP)
                .build();
    }

    public static void initOrangeWool() {
        if (BlockTypes.ORANGE_WOOL != null) return;
        BlockTypes.ORANGE_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_WOOL)
                .build();
    }

    public static void initOxeyeDaisy() {
        if (BlockTypes.OXEYE_DAISY != null) return;
        BlockTypes.OXEYE_DAISY = AllayBlockType
                .builder(BlockOxeyeDaisyBehaviorImpl.class)
                .vanillaBlock(BlockId.OXEYE_DAISY)
                .build();
    }

    public static void initOxidizedChiseledCopper() {
        if (BlockTypes.OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.OXIDIZED_CHISELED_COPPER = AllayBlockType
                .builder(BlockOxidizedChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initOxidizedCopper() {
        if (BlockTypes.OXIDIZED_COPPER != null) return;
        BlockTypes.OXIDIZED_COPPER = AllayBlockType
                .builder(BlockOxidizedCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER)
                .build();
    }

    public static void initOxidizedCopperBulb() {
        if (BlockTypes.OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.OXIDIZED_COPPER_BULB = AllayBlockType
                .builder(BlockOxidizedCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initOxidizedCopperDoor() {
        if (BlockTypes.OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initOxidizedCopperGrate() {
        if (BlockTypes.OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.OXIDIZED_COPPER_GRATE = AllayBlockType
                .builder(BlockOxidizedCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initOxidizedCopperTrapdoor() {
        if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockOxidizedCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initOxidizedCutCopper() {
        if (BlockTypes.OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER = AllayBlockType
                .builder(BlockOxidizedCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initOxidizedCutCopperSlab() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockOxidizedCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOxidizedCutCopperStairs() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockOxidizedDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPackedIce() {
        if (BlockTypes.PACKED_ICE != null) return;
        BlockTypes.PACKED_ICE = AllayBlockType
                .builder(BlockPackedIceBehaviorImpl.class)
                .vanillaBlock(BlockId.PACKED_ICE)
                .build();
    }

    public static void initPackedMud() {
        if (BlockTypes.PACKED_MUD != null) return;
        BlockTypes.PACKED_MUD = AllayBlockType
                .builder(BlockPackedMudBehaviorImpl.class)
                .vanillaBlock(BlockId.PACKED_MUD)
                .build();
    }

    public static void initPearlescentFroglight() {
        if (BlockTypes.PEARLESCENT_FROGLIGHT != null) return;
        BlockTypes.PEARLESCENT_FROGLIGHT = AllayBlockType
                .builder(BlockPearlescentFroglightBehaviorImpl.class)
                .vanillaBlock(BlockId.PEARLESCENT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPeony() {
        if (BlockTypes.PEONY != null) return;
        BlockTypes.PEONY = AllayBlockType
                .builder(BlockPeonyBehaviorImpl.class)
                .vanillaBlock(BlockId.PEONY)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPetrifiedOakDoubleSlab() {
        if (BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPetrifiedOakDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPetrifiedOakSlab() {
        if (BlockTypes.PETRIFIED_OAK_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_SLAB = AllayBlockType
                .builder(BlockPetrifiedOakSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPiglinHead() {
        if (BlockTypes.PIGLIN_HEAD != null) return;
        BlockTypes.PIGLIN_HEAD = AllayBlockType
                .builder(BlockPiglinHeadBehaviorImpl.class)
                .vanillaBlock(BlockId.PIGLIN_HEAD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPinkCandle() {
        if (BlockTypes.PINK_CANDLE != null) return;
        BlockTypes.PINK_CANDLE = AllayBlockType
                .builder(BlockPinkCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCandleCake() {
        if (BlockTypes.PINK_CANDLE_CAKE != null) return;
        BlockTypes.PINK_CANDLE_CAKE = AllayBlockType
                .builder(BlockPinkCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCarpet() {
        if (BlockTypes.PINK_CARPET != null) return;
        BlockTypes.PINK_CARPET = AllayBlockType
                .builder(BlockPinkCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CARPET)
                .build();
    }

    public static void initPinkConcrete() {
        if (BlockTypes.PINK_CONCRETE != null) return;
        BlockTypes.PINK_CONCRETE = AllayBlockType
                .builder(BlockPinkConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CONCRETE)
                .build();
    }

    public static void initPinkConcretePowder() {
        if (BlockTypes.PINK_CONCRETE_POWDER != null) return;
        BlockTypes.PINK_CONCRETE_POWDER = AllayBlockType
                .builder(BlockPinkConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_CONCRETE_POWDER)
                .build();
    }

    public static void initPinkGlazedTerracotta() {
        if (BlockTypes.PINK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PINK_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockPinkGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPinkPetals() {
        if (BlockTypes.PINK_PETALS != null) return;
        BlockTypes.PINK_PETALS = AllayBlockType
                .builder(BlockPinkPetalsBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_PETALS)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPinkShulkerBox() {
        if (BlockTypes.PINK_SHULKER_BOX != null) return;
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .build();
    }

    public static void initPinkStainedGlass() {
        if (BlockTypes.PINK_STAINED_GLASS != null) return;
        BlockTypes.PINK_STAINED_GLASS = AllayBlockType
                .builder(BlockPinkStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS)
                .build();
    }

    public static void initPinkStainedGlassPane() {
        if (BlockTypes.PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.PINK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockPinkStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPinkTerracotta() {
        if (BlockTypes.PINK_TERRACOTTA != null) return;
        BlockTypes.PINK_TERRACOTTA = AllayBlockType
                .builder(BlockPinkTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_TERRACOTTA)
                .build();
    }

    public static void initPinkTulip() {
        if (BlockTypes.PINK_TULIP != null) return;
        BlockTypes.PINK_TULIP = AllayBlockType
                .builder(BlockPinkTulipBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_TULIP)
                .build();
    }

    public static void initPinkWool() {
        if (BlockTypes.PINK_WOOL != null) return;
        BlockTypes.PINK_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_WOOL)
                .build();
    }

    public static void initPiston() {
        if (BlockTypes.PISTON != null) return;
        BlockTypes.PISTON = AllayBlockType
                .builder(BlockPistonBehaviorImpl.class)
                .vanillaBlock(BlockId.PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPistonArmCollision() {
        if (BlockTypes.PISTON_ARM_COLLISION != null) return;
        BlockTypes.PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockPistonArmCollisionBehaviorImpl.class)
                .vanillaBlock(BlockId.PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPitcherCrop() {
        if (BlockTypes.PITCHER_CROP != null) return;
        BlockTypes.PITCHER_CROP = AllayBlockType
                .builder(BlockPitcherCropBehaviorImpl.class)
                .vanillaBlock(BlockId.PITCHER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPitcherPlant() {
        if (BlockTypes.PITCHER_PLANT != null) return;
        BlockTypes.PITCHER_PLANT = AllayBlockType
                .builder(BlockPitcherPlantBehaviorImpl.class)
                .vanillaBlock(BlockId.PITCHER_PLANT)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPlayerHead() {
        if (BlockTypes.PLAYER_HEAD != null) return;
        BlockTypes.PLAYER_HEAD = AllayBlockType
                .builder(BlockPlayerHeadBehaviorImpl.class)
                .vanillaBlock(BlockId.PLAYER_HEAD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPodzol() {
        if (BlockTypes.PODZOL != null) return;
        BlockTypes.PODZOL = AllayBlockType
                .builder(BlockPodzolBehaviorImpl.class)
                .vanillaBlock(BlockId.PODZOL)
                .build();
    }

    public static void initPointedDripstone() {
        if (BlockTypes.POINTED_DRIPSTONE != null) return;
        BlockTypes.POINTED_DRIPSTONE = AllayBlockType
                .builder(BlockPointedDripstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initPolishedAndesite() {
        if (BlockTypes.POLISHED_ANDESITE != null) return;
        BlockTypes.POLISHED_ANDESITE = AllayBlockType
                .builder(BlockPolishedAndesiteBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE)
                .build();
    }

    public static void initPolishedAndesiteDoubleSlab() {
        if (BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedAndesiteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteSlab() {
        if (BlockTypes.POLISHED_ANDESITE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_SLAB = AllayBlockType
                .builder(BlockPolishedAndesiteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteStairs() {
        if (BlockTypes.POLISHED_ANDESITE_STAIRS != null) return;
        BlockTypes.POLISHED_ANDESITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBasalt() {
        if (BlockTypes.POLISHED_BASALT != null) return;
        BlockTypes.POLISHED_BASALT = AllayBlockType
                .builder(BlockPolishedBasaltBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPolishedBlackstone() {
        if (BlockTypes.POLISHED_BLACKSTONE != null) return;
        BlockTypes.POLISHED_BLACKSTONE = AllayBlockType
                .builder(BlockPolishedBlackstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE)
                .build();
    }

    public static void initPolishedBlackstoneBrickDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneBrickWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedBlackstoneBricks() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                .builder(BlockPolishedBlackstoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initPolishedBlackstoneButton() {
        if (BlockTypes.POLISHED_BLACKSTONE_BUTTON != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstonePressurePlate() {
        if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE != null) return;
        BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockPolishedBlackstonePressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initPolishedBlackstoneSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_WALL = AllayBlockType
                .builder(BlockPolishedBlackstoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDeepslate() {
        if (BlockTypes.POLISHED_DEEPSLATE != null) return;
        BlockTypes.POLISHED_DEEPSLATE = AllayBlockType
                .builder(BlockPolishedDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE)
                .build();
    }

    public static void initPolishedDeepslateDoubleSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedDeepslateDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_SLAB = AllayBlockType
                .builder(BlockPolishedDeepslateSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateStairs() {
        if (BlockTypes.POLISHED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedDeepslateWall() {
        if (BlockTypes.POLISHED_DEEPSLATE_WALL != null) return;
        BlockTypes.POLISHED_DEEPSLATE_WALL = AllayBlockType
                .builder(BlockPolishedDeepslateWallBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDiorite() {
        if (BlockTypes.POLISHED_DIORITE != null) return;
        BlockTypes.POLISHED_DIORITE = AllayBlockType
                .builder(BlockPolishedDioriteBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE)
                .build();
    }

    public static void initPolishedDioriteDoubleSlab() {
        if (BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedDioriteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteSlab() {
        if (BlockTypes.POLISHED_DIORITE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_SLAB = AllayBlockType
                .builder(BlockPolishedDioriteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteStairs() {
        if (BlockTypes.POLISHED_DIORITE_STAIRS != null) return;
        BlockTypes.POLISHED_DIORITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedGranite() {
        if (BlockTypes.POLISHED_GRANITE != null) return;
        BlockTypes.POLISHED_GRANITE = AllayBlockType
                .builder(BlockPolishedGraniteBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE)
                .build();
    }

    public static void initPolishedGraniteDoubleSlab() {
        if (BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedGraniteDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteSlab() {
        if (BlockTypes.POLISHED_GRANITE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_SLAB = AllayBlockType
                .builder(BlockPolishedGraniteSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteStairs() {
        if (BlockTypes.POLISHED_GRANITE_STAIRS != null) return;
        BlockTypes.POLISHED_GRANITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuff() {
        if (BlockTypes.POLISHED_TUFF != null) return;
        BlockTypes.POLISHED_TUFF = AllayBlockType
                .builder(BlockPolishedTuffBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF)
                .build();
    }

    public static void initPolishedTuffDoubleSlab() {
        if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedTuffDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffSlab() {
        if (BlockTypes.POLISHED_TUFF_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_SLAB = AllayBlockType
                .builder(BlockPolishedTuffSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffStairs() {
        if (BlockTypes.POLISHED_TUFF_STAIRS != null) return;
        BlockTypes.POLISHED_TUFF_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuffWall() {
        if (BlockTypes.POLISHED_TUFF_WALL != null) return;
        BlockTypes.POLISHED_TUFF_WALL = AllayBlockType
                .builder(BlockPolishedTuffWallBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPoppy() {
        if (BlockTypes.POPPY != null) return;
        BlockTypes.POPPY = AllayBlockType
                .builder(BlockPoppyBehaviorImpl.class)
                .vanillaBlock(BlockId.POPPY)
                .build();
    }

    public static void initPortal() {
        if (BlockTypes.PORTAL != null) return;
        BlockTypes.PORTAL = AllayBlockType
                .builder(BlockPortalBehaviorImpl.class)
                .vanillaBlock(BlockId.PORTAL)
                .setProperties(BlockPropertyTypes.PORTAL_AXIS)
                .build();
    }

    public static void initPotatoes() {
        if (BlockTypes.POTATOES != null) return;
        BlockTypes.POTATOES = AllayBlockType
                .builder(BlockPotatoesBehaviorImpl.class)
                .vanillaBlock(BlockId.POTATOES)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPowderSnow() {
        if (BlockTypes.POWDER_SNOW != null) return;
        BlockTypes.POWDER_SNOW = AllayBlockType
                .builder(BlockPowderSnowBehaviorImpl.class)
                .vanillaBlock(BlockId.POWDER_SNOW)
                .build();
    }

    public static void initPoweredComparator() {
        if (BlockTypes.POWERED_COMPARATOR != null) return;
        BlockTypes.POWERED_COMPARATOR = AllayBlockType
                .builder(BlockPoweredComparatorBehaviorImpl.class)
                .vanillaBlock(BlockId.POWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initPoweredRepeater() {
        if (BlockTypes.POWERED_REPEATER != null) return;
        BlockTypes.POWERED_REPEATER = AllayBlockType
                .builder(BlockPoweredRepeaterBehaviorImpl.class)
                .vanillaBlock(BlockId.POWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initPrismarine() {
        if (BlockTypes.PRISMARINE != null) return;
        BlockTypes.PRISMARINE = AllayBlockType
                .builder(BlockPrismarineBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE)
                .build();
    }

    public static void initPrismarineBrickDoubleSlab() {
        if (BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPrismarineBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBrickSlab() {
        if (BlockTypes.PRISMARINE_BRICK_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_SLAB = AllayBlockType
                .builder(BlockPrismarineBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBricks() {
        if (BlockTypes.PRISMARINE_BRICKS != null) return;
        BlockTypes.PRISMARINE_BRICKS = AllayBlockType
                .builder(BlockPrismarineBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS)
                .build();
    }

    public static void initPrismarineBricksStairs() {
        if (BlockTypes.PRISMARINE_BRICKS_STAIRS != null) return;
        BlockTypes.PRISMARINE_BRICKS_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPrismarineDoubleSlab() {
        if (BlockTypes.PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPrismarineDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineSlab() {
        if (BlockTypes.PRISMARINE_SLAB != null) return;
        BlockTypes.PRISMARINE_SLAB = AllayBlockType
                .builder(BlockPrismarineSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineStairs() {
        if (BlockTypes.PRISMARINE_STAIRS != null) return;
        BlockTypes.PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPrismarineWall() {
        if (BlockTypes.PRISMARINE_WALL != null) return;
        BlockTypes.PRISMARINE_WALL = AllayBlockType
                .builder(BlockPrismarineWallBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPumpkin() {
        if (BlockTypes.PUMPKIN != null) return;
        BlockTypes.PUMPKIN = AllayBlockType
                .builder(BlockPumpkinBehaviorImpl.class)
                .vanillaBlock(BlockId.PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPumpkinStem() {
        if (BlockTypes.PUMPKIN_STEM != null) return;
        BlockTypes.PUMPKIN_STEM = AllayBlockType
                .builder(BlockPumpkinStemBehaviorImpl.class)
                .vanillaBlock(BlockId.PUMPKIN_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPurpleCandle() {
        if (BlockTypes.PURPLE_CANDLE != null) return;
        BlockTypes.PURPLE_CANDLE = AllayBlockType
                .builder(BlockPurpleCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCandleCake() {
        if (BlockTypes.PURPLE_CANDLE_CAKE != null) return;
        BlockTypes.PURPLE_CANDLE_CAKE = AllayBlockType
                .builder(BlockPurpleCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCarpet() {
        if (BlockTypes.PURPLE_CARPET != null) return;
        BlockTypes.PURPLE_CARPET = AllayBlockType
                .builder(BlockPurpleCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CARPET)
                .build();
    }

    public static void initPurpleConcrete() {
        if (BlockTypes.PURPLE_CONCRETE != null) return;
        BlockTypes.PURPLE_CONCRETE = AllayBlockType
                .builder(BlockPurpleConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE)
                .build();
    }

    public static void initPurpleConcretePowder() {
        if (BlockTypes.PURPLE_CONCRETE_POWDER != null) return;
        BlockTypes.PURPLE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockPurpleConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE_POWDER)
                .build();
    }

    public static void initPurpleGlazedTerracotta() {
        if (BlockTypes.PURPLE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockPurpleGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPurpleShulkerBox() {
        if (BlockTypes.PURPLE_SHULKER_BOX != null) return;
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .build();
    }

    public static void initPurpleStainedGlass() {
        if (BlockTypes.PURPLE_STAINED_GLASS != null) return;
        BlockTypes.PURPLE_STAINED_GLASS = AllayBlockType
                .builder(BlockPurpleStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initPurpleStainedGlassPane() {
        if (BlockTypes.PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.PURPLE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockPurpleStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPurpleTerracotta() {
        if (BlockTypes.PURPLE_TERRACOTTA != null) return;
        BlockTypes.PURPLE_TERRACOTTA = AllayBlockType
                .builder(BlockPurpleTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_TERRACOTTA)
                .build();
    }

    public static void initPurpleWool() {
        if (BlockTypes.PURPLE_WOOL != null) return;
        BlockTypes.PURPLE_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_WOOL)
                .build();
    }

    public static void initPurpurBlock() {
        if (BlockTypes.PURPUR_BLOCK != null) return;
        BlockTypes.PURPUR_BLOCK = AllayBlockType
                .builder(BlockPurpurBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPurpurDoubleSlab() {
        if (BlockTypes.PURPUR_DOUBLE_SLAB != null) return;
        BlockTypes.PURPUR_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPurpurDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurPillar() {
        if (BlockTypes.PURPUR_PILLAR != null) return;
        BlockTypes.PURPUR_PILLAR = AllayBlockType
                .builder(BlockPurpurPillarBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_PILLAR)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPurpurSlab() {
        if (BlockTypes.PURPUR_SLAB != null) return;
        BlockTypes.PURPUR_SLAB = AllayBlockType
                .builder(BlockPurpurSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurStairs() {
        if (BlockTypes.PURPUR_STAIRS != null) return;
        BlockTypes.PURPUR_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initQuartzBlock() {
        if (BlockTypes.QUARTZ_BLOCK != null) return;
        BlockTypes.QUARTZ_BLOCK = AllayBlockType
                .builder(BlockQuartzBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzBricks() {
        if (BlockTypes.QUARTZ_BRICKS != null) return;
        BlockTypes.QUARTZ_BRICKS = AllayBlockType
                .builder(BlockQuartzBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_BRICKS)
                .build();
    }

    public static void initQuartzDoubleSlab() {
        if (BlockTypes.QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.QUARTZ_DOUBLE_SLAB = AllayBlockType
                .builder(BlockQuartzDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initQuartzOre() {
        if (BlockTypes.QUARTZ_ORE != null) return;
        BlockTypes.QUARTZ_ORE = AllayBlockType
                .builder(BlockQuartzOreBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_ORE)
                .build();
    }

    public static void initQuartzPillar() {
        if (BlockTypes.QUARTZ_PILLAR != null) return;
        BlockTypes.QUARTZ_PILLAR = AllayBlockType
                .builder(BlockQuartzPillarBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_PILLAR)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzSlab() {
        if (BlockTypes.QUARTZ_SLAB != null) return;
        BlockTypes.QUARTZ_SLAB = AllayBlockType
                .builder(BlockQuartzSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initQuartzStairs() {
        if (BlockTypes.QUARTZ_STAIRS != null) return;
        BlockTypes.QUARTZ_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRail() {
        if (BlockTypes.RAIL != null) return;
        BlockTypes.RAIL = AllayBlockType
                .builder(BlockRailBehaviorImpl.class)
                .vanillaBlock(BlockId.RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                .build();
    }

    public static void initRawCopperBlock() {
        if (BlockTypes.RAW_COPPER_BLOCK != null) return;
        BlockTypes.RAW_COPPER_BLOCK = AllayBlockType
                .builder(BlockRawCopperBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.RAW_COPPER_BLOCK)
                .build();
    }

    public static void initRawGoldBlock() {
        if (BlockTypes.RAW_GOLD_BLOCK != null) return;
        BlockTypes.RAW_GOLD_BLOCK = AllayBlockType
                .builder(BlockRawGoldBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.RAW_GOLD_BLOCK)
                .build();
    }

    public static void initRawIronBlock() {
        if (BlockTypes.RAW_IRON_BLOCK != null) return;
        BlockTypes.RAW_IRON_BLOCK = AllayBlockType
                .builder(BlockRawIronBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.RAW_IRON_BLOCK)
                .build();
    }

    public static void initRedCandle() {
        if (BlockTypes.RED_CANDLE != null) return;
        BlockTypes.RED_CANDLE = AllayBlockType
                .builder(BlockRedCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCandleCake() {
        if (BlockTypes.RED_CANDLE_CAKE != null) return;
        BlockTypes.RED_CANDLE_CAKE = AllayBlockType
                .builder(BlockRedCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCarpet() {
        if (BlockTypes.RED_CARPET != null) return;
        BlockTypes.RED_CARPET = AllayBlockType
                .builder(BlockRedCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CARPET)
                .build();
    }

    public static void initRedConcrete() {
        if (BlockTypes.RED_CONCRETE != null) return;
        BlockTypes.RED_CONCRETE = AllayBlockType
                .builder(BlockRedConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CONCRETE)
                .build();
    }

    public static void initRedConcretePowder() {
        if (BlockTypes.RED_CONCRETE_POWDER != null) return;
        BlockTypes.RED_CONCRETE_POWDER = AllayBlockType
                .builder(BlockRedConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_CONCRETE_POWDER)
                .build();
    }

    public static void initRedGlazedTerracotta() {
        if (BlockTypes.RED_GLAZED_TERRACOTTA != null) return;
        BlockTypes.RED_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockRedGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initRedMushroom() {
        if (BlockTypes.RED_MUSHROOM != null) return;
        BlockTypes.RED_MUSHROOM = AllayBlockType
                .builder(BlockRedMushroomBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_MUSHROOM)
                .build();
    }

    public static void initRedMushroomBlock() {
        if (BlockTypes.RED_MUSHROOM_BLOCK != null) return;
        BlockTypes.RED_MUSHROOM_BLOCK = AllayBlockType
                .builder(BlockRedMushroomBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initRedNetherBrick() {
        if (BlockTypes.RED_NETHER_BRICK != null) return;
        BlockTypes.RED_NETHER_BRICK = AllayBlockType
                .builder(BlockRedNetherBrickBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK)
                .build();
    }

    public static void initRedNetherBrickDoubleSlab() {
        if (BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockRedNetherBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickSlab() {
        if (BlockTypes.RED_NETHER_BRICK_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_SLAB = AllayBlockType
                .builder(BlockRedNetherBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickStairs() {
        if (BlockTypes.RED_NETHER_BRICK_STAIRS != null) return;
        BlockTypes.RED_NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedNetherBrickWall() {
        if (BlockTypes.RED_NETHER_BRICK_WALL != null) return;
        BlockTypes.RED_NETHER_BRICK_WALL = AllayBlockType
                .builder(BlockRedNetherBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initRedSand() {
        if (BlockTypes.RED_SAND != null) return;
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SAND)
                .build();
    }

    public static void initRedSandstone() {
        if (BlockTypes.RED_SANDSTONE != null) return;
        BlockTypes.RED_SANDSTONE = AllayBlockType
                .builder(BlockRedSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE)
                .build();
    }

    public static void initRedSandstoneDoubleSlab() {
        if (BlockTypes.RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockRedSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneSlab() {
        if (BlockTypes.RED_SANDSTONE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockRedSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneStairs() {
        if (BlockTypes.RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedSandstoneWall() {
        if (BlockTypes.RED_SANDSTONE_WALL != null) return;
        BlockTypes.RED_SANDSTONE_WALL = AllayBlockType
                .builder(BlockRedSandstoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initRedShulkerBox() {
        if (BlockTypes.RED_SHULKER_BOX != null) return;
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .build();
    }

    public static void initRedStainedGlass() {
        if (BlockTypes.RED_STAINED_GLASS != null) return;
        BlockTypes.RED_STAINED_GLASS = AllayBlockType
                .builder(BlockRedStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS)
                .build();
    }

    public static void initRedStainedGlassPane() {
        if (BlockTypes.RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.RED_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockRedStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initRedTerracotta() {
        if (BlockTypes.RED_TERRACOTTA != null) return;
        BlockTypes.RED_TERRACOTTA = AllayBlockType
                .builder(BlockRedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_TERRACOTTA)
                .build();
    }

    public static void initRedTulip() {
        if (BlockTypes.RED_TULIP != null) return;
        BlockTypes.RED_TULIP = AllayBlockType
                .builder(BlockRedTulipBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_TULIP)
                .build();
    }

    public static void initRedWool() {
        if (BlockTypes.RED_WOOL != null) return;
        BlockTypes.RED_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_WOOL)
                .build();
    }

    public static void initRedstoneBlock() {
        if (BlockTypes.REDSTONE_BLOCK != null) return;
        BlockTypes.REDSTONE_BLOCK = AllayBlockType
                .builder(BlockRedstoneBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_BLOCK)
                .build();
    }

    public static void initRedstoneLamp() {
        if (BlockTypes.REDSTONE_LAMP != null) return;
        BlockTypes.REDSTONE_LAMP = AllayBlockType
                .builder(BlockRedstoneLampBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_LAMP)
                .build();
    }

    public static void initRedstoneOre() {
        if (BlockTypes.REDSTONE_ORE != null) return;
        BlockTypes.REDSTONE_ORE = AllayBlockType
                .builder(BlockRedstoneOreBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_ORE)
                .build();
    }

    public static void initRedstoneTorch() {
        if (BlockTypes.REDSTONE_TORCH != null) return;
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initRedstoneWire() {
        if (BlockTypes.REDSTONE_WIRE != null) return;
        BlockTypes.REDSTONE_WIRE = AllayBlockType
                .builder(BlockRedstoneWireBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_WIRE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initReeds() {
        if (BlockTypes.REEDS != null) return;
        BlockTypes.REEDS = AllayBlockType
                .builder(BlockReedsBehaviorImpl.class)
                .vanillaBlock(BlockId.REEDS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initReinforcedDeepslate() {
        if (BlockTypes.REINFORCED_DEEPSLATE != null) return;
        BlockTypes.REINFORCED_DEEPSLATE = AllayBlockType
                .builder(BlockReinforcedDeepslateBehaviorImpl.class)
                .vanillaBlock(BlockId.REINFORCED_DEEPSLATE)
                .build();
    }

    public static void initRepeatingCommandBlock() {
        if (BlockTypes.REPEATING_COMMAND_BLOCK != null) return;
        BlockTypes.REPEATING_COMMAND_BLOCK = AllayBlockType
                .builder(BlockRepeatingCommandBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.REPEATING_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initReserved6() {
        if (BlockTypes.RESERVED6 != null) return;
        BlockTypes.RESERVED6 = AllayBlockType
                .builder(BlockReserved6BehaviorImpl.class)
                .vanillaBlock(BlockId.RESERVED6)
                .build();
    }

    public static void initRespawnAnchor() {
        if (BlockTypes.RESPAWN_ANCHOR != null) return;
        BlockTypes.RESPAWN_ANCHOR = AllayBlockType
                .builder(BlockRespawnAnchorBehaviorImpl.class)
                .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                .build();
    }

    public static void initRoseBush() {
        if (BlockTypes.ROSE_BUSH != null) return;
        BlockTypes.ROSE_BUSH = AllayBlockType
                .builder(BlockRoseBushBehaviorImpl.class)
                .vanillaBlock(BlockId.ROSE_BUSH)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSand() {
        if (BlockTypes.SAND != null) return;
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SAND)
                .build();
    }

    public static void initSandstone() {
        if (BlockTypes.SANDSTONE != null) return;
        BlockTypes.SANDSTONE = AllayBlockType
                .builder(BlockSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE)
                .build();
    }

    public static void initSandstoneDoubleSlab() {
        if (BlockTypes.SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneSlab() {
        if (BlockTypes.SANDSTONE_SLAB != null) return;
        BlockTypes.SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneStairs() {
        if (BlockTypes.SANDSTONE_STAIRS != null) return;
        BlockTypes.SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSandstoneWall() {
        if (BlockTypes.SANDSTONE_WALL != null) return;
        BlockTypes.SANDSTONE_WALL = AllayBlockType
                .builder(BlockSandstoneWallBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initScaffolding() {
        if (BlockTypes.SCAFFOLDING != null) return;
        BlockTypes.SCAFFOLDING = AllayBlockType
                .builder(BlockScaffoldingBehaviorImpl.class)
                .vanillaBlock(BlockId.SCAFFOLDING)
                .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                .build();
    }

    public static void initSculk() {
        if (BlockTypes.SCULK != null) return;
        BlockTypes.SCULK = AllayBlockType
                .builder(BlockSculkBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK)
                .build();
    }

    public static void initSculkCatalyst() {
        if (BlockTypes.SCULK_CATALYST != null) return;
        BlockTypes.SCULK_CATALYST = AllayBlockType
                .builder(BlockSculkCatalystBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK_CATALYST)
                .setProperties(BlockPropertyTypes.BLOOM)
                .build();
    }

    public static void initSculkSensor() {
        if (BlockTypes.SCULK_SENSOR != null) return;
        BlockTypes.SCULK_SENSOR = AllayBlockType
                .builder(BlockSculkSensorBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initSculkShrieker() {
        if (BlockTypes.SCULK_SHRIEKER != null) return;
        BlockTypes.SCULK_SHRIEKER = AllayBlockType
                .builder(BlockSculkShriekerBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK_SHRIEKER)
                .setProperties(BlockPropertyTypes.ACTIVE, BlockPropertyTypes.CAN_SUMMON)
                .build();
    }

    public static void initSculkVein() {
        if (BlockTypes.SCULK_VEIN != null) return;
        BlockTypes.SCULK_VEIN = AllayBlockType
                .builder(BlockSculkVeinBehaviorImpl.class)
                .vanillaBlock(BlockId.SCULK_VEIN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initSeaLantern() {
        if (BlockTypes.SEA_LANTERN != null) return;
        BlockTypes.SEA_LANTERN = AllayBlockType
                .builder(BlockSeaLanternBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_LANTERN)
                .build();
    }

    public static void initSeaPickle() {
        if (BlockTypes.SEA_PICKLE != null) return;
        BlockTypes.SEA_PICKLE = AllayBlockType
                .builder(BlockSeaPickleBehaviorImpl.class)
                .vanillaBlock(BlockId.SEA_PICKLE)
                .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                .build();
    }

    public static void initSeagrass() {
        if (BlockTypes.SEAGRASS != null) return;
        BlockTypes.SEAGRASS = AllayBlockType
                .builder(BlockSeagrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SEAGRASS)
                .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                .build();
    }

    public static void initShortGrass() {
        if (BlockTypes.SHORT_GRASS != null) return;
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .build();
    }

    public static void initShroomlight() {
        if (BlockTypes.SHROOMLIGHT != null) return;
        BlockTypes.SHROOMLIGHT = AllayBlockType
                .builder(BlockShroomlightBehaviorImpl.class)
                .vanillaBlock(BlockId.SHROOMLIGHT)
                .build();
    }

    public static void initSilverGlazedTerracotta() {
        if (BlockTypes.SILVER_GLAZED_TERRACOTTA != null) return;
        BlockTypes.SILVER_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockSilverGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.SILVER_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSkeletonSkull() {
        if (BlockTypes.SKELETON_SKULL != null) return;
        BlockTypes.SKELETON_SKULL = AllayBlockType
                .builder(BlockSkeletonSkullBehaviorImpl.class)
                .vanillaBlock(BlockId.SKELETON_SKULL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSlime() {
        if (BlockTypes.SLIME != null) return;
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehaviorImpl.class)
                .vanillaBlock(BlockId.SLIME)
                .build();
    }

    public static void initSmallAmethystBud() {
        if (BlockTypes.SMALL_AMETHYST_BUD != null) return;
        BlockTypes.SMALL_AMETHYST_BUD = AllayBlockType
                .builder(BlockSmallAmethystBudBehaviorImpl.class)
                .vanillaBlock(BlockId.SMALL_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initSmallDripleafBlock() {
        if (BlockTypes.SMALL_DRIPLEAF_BLOCK != null) return;
        BlockTypes.SMALL_DRIPLEAF_BLOCK = AllayBlockType
                .builder(BlockSmallDripleafBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.SMALL_DRIPLEAF_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSmithingTable() {
        if (BlockTypes.SMITHING_TABLE != null) return;
        BlockTypes.SMITHING_TABLE = AllayBlockType
                .builder(BlockSmithingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.SMITHING_TABLE)
                .build();
    }

    public static void initSmoker() {
        if (BlockTypes.SMOKER != null) return;
        BlockTypes.SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSmoothBasalt() {
        if (BlockTypes.SMOOTH_BASALT != null) return;
        BlockTypes.SMOOTH_BASALT = AllayBlockType
                .builder(BlockSmoothBasaltBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_BASALT)
                .build();
    }

    public static void initSmoothQuartz() {
        if (BlockTypes.SMOOTH_QUARTZ != null) return;
        BlockTypes.SMOOTH_QUARTZ = AllayBlockType
                .builder(BlockSmoothQuartzBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSmoothQuartzDoubleSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothQuartzDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_SLAB = AllayBlockType
                .builder(BlockSmoothQuartzSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzStairs() {
        if (BlockTypes.SMOOTH_QUARTZ_STAIRS != null) return;
        BlockTypes.SMOOTH_QUARTZ_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothRedSandstone() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE = AllayBlockType
                .builder(BlockSmoothRedSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE)
                .build();
    }

    public static void initSmoothRedSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothRedSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSmoothRedSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneStairs() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothSandstone() {
        if (BlockTypes.SMOOTH_SANDSTONE != null) return;
        BlockTypes.SMOOTH_SANDSTONE = AllayBlockType
                .builder(BlockSmoothSandstoneBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE)
                .build();
    }

    public static void initSmoothSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothSandstoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSmoothSandstoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneStairs() {
        if (BlockTypes.SMOOTH_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothStone() {
        if (BlockTypes.SMOOTH_STONE != null) return;
        BlockTypes.SMOOTH_STONE = AllayBlockType
                .builder(BlockSmoothStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_STONE)
                .build();
    }

    public static void initSmoothStoneDoubleSlab() {
        if (BlockTypes.SMOOTH_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothStoneDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothStoneSlab() {
        if (BlockTypes.SMOOTH_STONE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_SLAB = AllayBlockType
                .builder(BlockSmoothStoneSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSnifferEgg() {
        if (BlockTypes.SNIFFER_EGG != null) return;
        BlockTypes.SNIFFER_EGG = AllayBlockType
                .builder(BlockSnifferEggBehaviorImpl.class)
                .vanillaBlock(BlockId.SNIFFER_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE)
                .build();
    }

    public static void initSnow() {
        if (BlockTypes.SNOW != null) return;
        BlockTypes.SNOW = AllayBlockType
                .builder(BlockSnowBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW)
                .build();
    }

    public static void initSnowLayer() {
        if (BlockTypes.SNOW_LAYER != null) return;
        BlockTypes.SNOW_LAYER = AllayBlockType
                .builder(BlockSnowLayerBehaviorImpl.class)
                .vanillaBlock(BlockId.SNOW_LAYER)
                .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                .build();
    }

    public static void initSoulCampfire() {
        if (BlockTypes.SOUL_CAMPFIRE != null) return;
        BlockTypes.SOUL_CAMPFIRE = AllayBlockType
                .builder(BlockSoulCampfireBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSoulFire() {
        if (BlockTypes.SOUL_FIRE != null) return;
        BlockTypes.SOUL_FIRE = AllayBlockType
                .builder(BlockSoulFireBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initSoulLantern() {
        if (BlockTypes.SOUL_LANTERN != null) return;
        BlockTypes.SOUL_LANTERN = AllayBlockType
                .builder(BlockSoulLanternBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSoulSand() {
        if (BlockTypes.SOUL_SAND != null) return;
        BlockTypes.SOUL_SAND = AllayBlockType
                .builder(BlockSoulSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_SAND)
                .build();
    }

    public static void initSoulSoil() {
        if (BlockTypes.SOUL_SOIL != null) return;
        BlockTypes.SOUL_SOIL = AllayBlockType
                .builder(BlockSoulSoilBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_SOIL)
                .build();
    }

    public static void initSoulTorch() {
        if (BlockTypes.SOUL_TORCH != null) return;
        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockSoulTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initSponge() {
        if (BlockTypes.SPONGE != null) return;
        BlockTypes.SPONGE = AllayBlockType
                .builder(BlockSpongeBehaviorImpl.class)
                .vanillaBlock(BlockId.SPONGE)
                .build();
    }

    public static void initSporeBlossom() {
        if (BlockTypes.SPORE_BLOSSOM != null) return;
        BlockTypes.SPORE_BLOSSOM = AllayBlockType
                .builder(BlockSporeBlossomBehaviorImpl.class)
                .vanillaBlock(BlockId.SPORE_BLOSSOM)
                .build();
    }

    public static void initSpruceButton() {
        if (BlockTypes.SPRUCE_BUTTON != null) return;
        BlockTypes.SPRUCE_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceDoor() {
        if (BlockTypes.SPRUCE_DOOR != null) return;
        BlockTypes.SPRUCE_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSpruceDoubleSlab() {
        if (BlockTypes.SPRUCE_DOUBLE_SLAB != null) return;
        BlockTypes.SPRUCE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSpruceDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceFence() {
        if (BlockTypes.SPRUCE_FENCE != null) return;
        BlockTypes.SPRUCE_FENCE = AllayBlockType
                .builder(BlockSpruceFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE)
                .build();
    }

    public static void initSpruceFenceGate() {
        if (BlockTypes.SPRUCE_FENCE_GATE != null) return;
        BlockTypes.SPRUCE_FENCE_GATE = AllayBlockType
                .builder(BlockSpruceFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initSpruceHangingSign() {
        if (BlockTypes.SPRUCE_HANGING_SIGN != null) return;
        BlockTypes.SPRUCE_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSpruceLeaves() {
        if (BlockTypes.SPRUCE_LEAVES != null) return;
        BlockTypes.SPRUCE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initSpruceLog() {
        if (BlockTypes.SPRUCE_LOG != null) return;
        BlockTypes.SPRUCE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSprucePlanks() {
        if (BlockTypes.SPRUCE_PLANKS != null) return;
        BlockTypes.SPRUCE_PLANKS = AllayBlockType
                .builder(BlockSprucePlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_PLANKS)
                .build();
    }

    public static void initSprucePressurePlate() {
        if (BlockTypes.SPRUCE_PRESSURE_PLATE != null) return;
        BlockTypes.SPRUCE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockSprucePressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initSpruceSapling() {
        if (BlockTypes.SPRUCE_SAPLING != null) return;
        BlockTypes.SPRUCE_SAPLING = AllayBlockType
                .builder(BlockSpruceSaplingBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initSpruceSlab() {
        if (BlockTypes.SPRUCE_SLAB != null) return;
        BlockTypes.SPRUCE_SLAB = AllayBlockType
                .builder(BlockSpruceSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceStairs() {
        if (BlockTypes.SPRUCE_STAIRS != null) return;
        BlockTypes.SPRUCE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSpruceStandingSign() {
        if (BlockTypes.SPRUCE_STANDING_SIGN != null) return;
        BlockTypes.SPRUCE_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initSpruceTrapdoor() {
        if (BlockTypes.SPRUCE_TRAPDOOR != null) return;
        BlockTypes.SPRUCE_TRAPDOOR = AllayBlockType
                .builder(BlockSpruceTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initSpruceWallSign() {
        if (BlockTypes.SPRUCE_WALL_SIGN != null) return;
        BlockTypes.SPRUCE_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceWood() {
        if (BlockTypes.SPRUCE_WOOD != null) return;
        BlockTypes.SPRUCE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStandingBanner() {
        if (BlockTypes.STANDING_BANNER != null) return;
        BlockTypes.STANDING_BANNER = AllayBlockType
                .builder(BlockStandingBannerBehaviorImpl.class)
                .vanillaBlock(BlockId.STANDING_BANNER)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initStandingSign() {
        if (BlockTypes.STANDING_SIGN != null) return;
        BlockTypes.STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initStickyPiston() {
        if (BlockTypes.STICKY_PISTON != null) return;
        BlockTypes.STICKY_PISTON = AllayBlockType
                .builder(BlockStickyPistonBehaviorImpl.class)
                .vanillaBlock(BlockId.STICKY_PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStickyPistonArmCollision() {
        if (BlockTypes.STICKY_PISTON_ARM_COLLISION != null) return;
        BlockTypes.STICKY_PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockStickyPistonArmCollisionBehaviorImpl.class)
                .vanillaBlock(BlockId.STICKY_PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStone() {
        if (BlockTypes.STONE != null) return;
        BlockTypes.STONE = AllayBlockType
                .builder(BlockStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE)
                .build();
    }

    public static void initStoneBrickDoubleSlab() {
        if (BlockTypes.STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockStoneBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickSlab() {
        if (BlockTypes.STONE_BRICK_SLAB != null) return;
        BlockTypes.STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockStoneBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickStairs() {
        if (BlockTypes.STONE_BRICK_STAIRS != null) return;
        BlockTypes.STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initStoneBrickWall() {
        if (BlockTypes.STONE_BRICK_WALL != null) return;
        BlockTypes.STONE_BRICK_WALL = AllayBlockType
                .builder(BlockStoneBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initStoneBricks() {
        if (BlockTypes.STONE_BRICKS != null) return;
        BlockTypes.STONE_BRICKS = AllayBlockType
                .builder(BlockStoneBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICKS)
                .build();
    }

    public static void initStoneButton() {
        if (BlockTypes.STONE_BUTTON != null) return;
        BlockTypes.STONE_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStonePressurePlate() {
        if (BlockTypes.STONE_PRESSURE_PLATE != null) return;
        BlockTypes.STONE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockStonePressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initStoneStairs() {
        if (BlockTypes.STONE_STAIRS != null) return;
        BlockTypes.STONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initStonecutter() {
        if (BlockTypes.STONECUTTER != null) return;
        BlockTypes.STONECUTTER = AllayBlockType
                .builder(BlockStonecutterBehaviorImpl.class)
                .vanillaBlock(BlockId.STONECUTTER)
                .build();
    }

    public static void initStonecutterBlock() {
        if (BlockTypes.STONECUTTER_BLOCK != null) return;
        BlockTypes.STONECUTTER_BLOCK = AllayBlockType
                .builder(BlockStonecutterBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initStrippedAcaciaLog() {
        if (BlockTypes.STRIPPED_ACACIA_LOG != null) return;
        BlockTypes.STRIPPED_ACACIA_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedAcaciaWood() {
        if (BlockTypes.STRIPPED_ACACIA_WOOD != null) return;
        BlockTypes.STRIPPED_ACACIA_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBambooBlock() {
        if (BlockTypes.STRIPPED_BAMBOO_BLOCK != null) return;
        BlockTypes.STRIPPED_BAMBOO_BLOCK = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBirchLog() {
        if (BlockTypes.STRIPPED_BIRCH_LOG != null) return;
        BlockTypes.STRIPPED_BIRCH_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBirchWood() {
        if (BlockTypes.STRIPPED_BIRCH_WOOD != null) return;
        BlockTypes.STRIPPED_BIRCH_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryLog() {
        if (BlockTypes.STRIPPED_CHERRY_LOG != null) return;
        BlockTypes.STRIPPED_CHERRY_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryWood() {
        if (BlockTypes.STRIPPED_CHERRY_WOOD != null) return;
        BlockTypes.STRIPPED_CHERRY_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonHyphae() {
        if (BlockTypes.STRIPPED_CRIMSON_HYPHAE != null) return;
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonStem() {
        if (BlockTypes.STRIPPED_CRIMSON_STEM != null) return;
        BlockTypes.STRIPPED_CRIMSON_STEM = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedDarkOakLog() {
        if (BlockTypes.STRIPPED_DARK_OAK_LOG != null) return;
        BlockTypes.STRIPPED_DARK_OAK_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedDarkOakWood() {
        if (BlockTypes.STRIPPED_DARK_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_DARK_OAK_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleLog() {
        if (BlockTypes.STRIPPED_JUNGLE_LOG != null) return;
        BlockTypes.STRIPPED_JUNGLE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleWood() {
        if (BlockTypes.STRIPPED_JUNGLE_WOOD != null) return;
        BlockTypes.STRIPPED_JUNGLE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveLog() {
        if (BlockTypes.STRIPPED_MANGROVE_LOG != null) return;
        BlockTypes.STRIPPED_MANGROVE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveWood() {
        if (BlockTypes.STRIPPED_MANGROVE_WOOD != null) return;
        BlockTypes.STRIPPED_MANGROVE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakLog() {
        if (BlockTypes.STRIPPED_OAK_LOG != null) return;
        BlockTypes.STRIPPED_OAK_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakWood() {
        if (BlockTypes.STRIPPED_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_OAK_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceLog() {
        if (BlockTypes.STRIPPED_SPRUCE_LOG != null) return;
        BlockTypes.STRIPPED_SPRUCE_LOG = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceWood() {
        if (BlockTypes.STRIPPED_SPRUCE_WOOD != null) return;
        BlockTypes.STRIPPED_SPRUCE_WOOD = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedHyphae() {
        if (BlockTypes.STRIPPED_WARPED_HYPHAE != null) return;
        BlockTypes.STRIPPED_WARPED_HYPHAE = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedStem() {
        if (BlockTypes.STRIPPED_WARPED_STEM != null) return;
        BlockTypes.STRIPPED_WARPED_STEM = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStructureBlock() {
        if (BlockTypes.STRUCTURE_BLOCK != null) return;
        BlockTypes.STRUCTURE_BLOCK = AllayBlockType
                .builder(BlockStructureBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.STRUCTURE_BLOCK)
                .setProperties(BlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
                .build();
    }

    public static void initStructureVoid() {
        if (BlockTypes.STRUCTURE_VOID != null) return;
        BlockTypes.STRUCTURE_VOID = AllayBlockType
                .builder(BlockStructureVoidBehaviorImpl.class)
                .vanillaBlock(BlockId.STRUCTURE_VOID)
                .build();
    }

    public static void initSunflower() {
        if (BlockTypes.SUNFLOWER != null) return;
        BlockTypes.SUNFLOWER = AllayBlockType
                .builder(BlockSunflowerBehaviorImpl.class)
                .vanillaBlock(BlockId.SUNFLOWER)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSuspiciousGravel() {
        if (BlockTypes.SUSPICIOUS_GRAVEL != null) return;
        BlockTypes.SUSPICIOUS_GRAVEL = AllayBlockType
                .builder(BlockSuspiciousGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.SUSPICIOUS_GRAVEL)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSuspiciousSand() {
        if (BlockTypes.SUSPICIOUS_SAND != null) return;
        BlockTypes.SUSPICIOUS_SAND = AllayBlockType
                .builder(BlockSuspiciousSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SUSPICIOUS_SAND)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSweetBerryBush() {
        if (BlockTypes.SWEET_BERRY_BUSH != null) return;
        BlockTypes.SWEET_BERRY_BUSH = AllayBlockType
                .builder(BlockSweetBerryBushBehaviorImpl.class)
                .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTallGrass() {
        if (BlockTypes.TALL_GRASS != null) return;
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initTarget() {
        if (BlockTypes.TARGET != null) return;
        BlockTypes.TARGET = AllayBlockType
                .builder(BlockTargetBehaviorImpl.class)
                .vanillaBlock(BlockId.TARGET)
                .build();
    }

    public static void initTintedGlass() {
        if (BlockTypes.TINTED_GLASS != null) return;
        BlockTypes.TINTED_GLASS = AllayBlockType
                .builder(BlockTintedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.TINTED_GLASS)
                .build();
    }

    public static void initTnt() {
        if (BlockTypes.TNT != null) return;
        BlockTypes.TNT = AllayBlockType
                .builder(BlockTntBehaviorImpl.class)
                .vanillaBlock(BlockId.TNT)
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                .build();
    }

    public static void initTorch() {
        if (BlockTypes.TORCH != null) return;
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initTorchflower() {
        if (BlockTypes.TORCHFLOWER != null) return;
        BlockTypes.TORCHFLOWER = AllayBlockType
                .builder(BlockTorchflowerBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCHFLOWER)
                .build();
    }

    public static void initTorchflowerCrop() {
        if (BlockTypes.TORCHFLOWER_CROP != null) return;
        BlockTypes.TORCHFLOWER_CROP = AllayBlockType
                .builder(BlockTorchflowerCropBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCHFLOWER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTrapdoor() {
        if (BlockTypes.TRAPDOOR != null) return;
        BlockTypes.TRAPDOOR = AllayBlockType
                .builder(BlockTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initTrappedChest() {
        if (BlockTypes.TRAPPED_CHEST != null) return;
        BlockTypes.TRAPPED_CHEST = AllayBlockType
                .builder(BlockTrappedChestBehaviorImpl.class)
                .vanillaBlock(BlockId.TRAPPED_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initTrialSpawner() {
        if (BlockTypes.TRIAL_SPAWNER != null) return;
        BlockTypes.TRIAL_SPAWNER = AllayBlockType
                .builder(BlockTrialSpawnerBehaviorImpl.class)
                .vanillaBlock(BlockId.TRIAL_SPAWNER)
                .setProperties(BlockPropertyTypes.OMINOUS, BlockPropertyTypes.TRIAL_SPAWNER_STATE)
                .build();
    }

    public static void initTripWire() {
        if (BlockTypes.TRIP_WIRE != null) return;
        BlockTypes.TRIP_WIRE = AllayBlockType
                .builder(BlockTripWireBehaviorImpl.class)
                .vanillaBlock(BlockId.TRIP_WIRE)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DISARMED_BIT, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.SUSPENDED_BIT)
                .build();
    }

    public static void initTripwireHook() {
        if (BlockTypes.TRIPWIRE_HOOK != null) return;
        BlockTypes.TRIPWIRE_HOOK = AllayBlockType
                .builder(BlockTripwireHookBehaviorImpl.class)
                .vanillaBlock(BlockId.TRIPWIRE_HOOK)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initTubeCoral() {
        if (BlockTypes.TUBE_CORAL != null) return;
        BlockTypes.TUBE_CORAL = AllayBlockType
                .builder(BlockTubeCoralBehaviorImpl.class)
                .vanillaBlock(BlockId.TUBE_CORAL)
                .build();
    }

    public static void initTubeCoralBlock() {
        if (BlockTypes.TUBE_CORAL_BLOCK != null) return;
        BlockTypes.TUBE_CORAL_BLOCK = AllayBlockType
                .builder(BlockTubeCoralBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initTubeCoralFan() {
        if (BlockTypes.TUBE_CORAL_FAN != null) return;
        BlockTypes.TUBE_CORAL_FAN = AllayBlockType
                .builder(BlockTubeCoralFanBehaviorImpl.class)
                .vanillaBlock(BlockId.TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initTubeCoralWallFan() {
        if (BlockTypes.TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.TUBE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockTubeCoralWallFanBehaviorImpl.class)
                .vanillaBlock(BlockId.TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initTuff() {
        if (BlockTypes.TUFF != null) return;
        BlockTypes.TUFF = AllayBlockType
                .builder(BlockTuffBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF)
                .build();
    }

    public static void initTuffBrickDoubleSlab() {
        if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockTuffBrickDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickSlab() {
        if (BlockTypes.TUFF_BRICK_SLAB != null) return;
        BlockTypes.TUFF_BRICK_SLAB = AllayBlockType
                .builder(BlockTuffBrickSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickStairs() {
        if (BlockTypes.TUFF_BRICK_STAIRS != null) return;
        BlockTypes.TUFF_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffBrickWall() {
        if (BlockTypes.TUFF_BRICK_WALL != null) return;
        BlockTypes.TUFF_BRICK_WALL = AllayBlockType
                .builder(BlockTuffBrickWallBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initTuffBricks() {
        if (BlockTypes.TUFF_BRICKS != null) return;
        BlockTypes.TUFF_BRICKS = AllayBlockType
                .builder(BlockTuffBricksBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICKS)
                .build();
    }

    public static void initTuffDoubleSlab() {
        if (BlockTypes.TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_DOUBLE_SLAB = AllayBlockType
                .builder(BlockTuffDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffSlab() {
        if (BlockTypes.TUFF_SLAB != null) return;
        BlockTypes.TUFF_SLAB = AllayBlockType
                .builder(BlockTuffSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffStairs() {
        if (BlockTypes.TUFF_STAIRS != null) return;
        BlockTypes.TUFF_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffWall() {
        if (BlockTypes.TUFF_WALL != null) return;
        BlockTypes.TUFF_WALL = AllayBlockType
                .builder(BlockTuffWallBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initTurtleEgg() {
        if (BlockTypes.TURTLE_EGG != null) return;
        BlockTypes.TURTLE_EGG = AllayBlockType
                .builder(BlockTurtleEggBehaviorImpl.class)
                .vanillaBlock(BlockId.TURTLE_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE, BlockPropertyTypes.TURTLE_EGG_COUNT)
                .build();
    }

    public static void initTwistingVines() {
        if (BlockTypes.TWISTING_VINES != null) return;
        BlockTypes.TWISTING_VINES = AllayBlockType
                .builder(BlockTwistingVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.TWISTING_VINES)
                .setProperties(BlockPropertyTypes.TWISTING_VINES_AGE)
                .build();
    }

    public static void initUnderwaterTnt() {
        if (BlockTypes.UNDERWATER_TNT != null) return;
        BlockTypes.UNDERWATER_TNT = AllayBlockType
                .builder(BlockUnderwaterTntBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDERWATER_TNT)
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                .build();
    }

    public static void initUnderwaterTorch() {
        if (BlockTypes.UNDERWATER_TORCH != null) return;
        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockUnderwaterTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUndyedShulkerBox() {
        if (BlockTypes.UNDYED_SHULKER_BOX != null) return;
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .build();
    }

    public static void initUnknown() {
        if (BlockTypes.UNKNOWN != null) return;
        BlockTypes.UNKNOWN = AllayBlockType
                .builder(BlockUnknownBehaviorImpl.class)
                .vanillaBlock(BlockId.UNKNOWN)
                .build();
    }

    public static void initUnlitRedstoneTorch() {
        if (BlockTypes.UNLIT_REDSTONE_TORCH != null) return;
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUnpoweredComparator() {
        if (BlockTypes.UNPOWERED_COMPARATOR != null) return;
        BlockTypes.UNPOWERED_COMPARATOR = AllayBlockType
                .builder(BlockUnpoweredComparatorBehaviorImpl.class)
                .vanillaBlock(BlockId.UNPOWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initUnpoweredRepeater() {
        if (BlockTypes.UNPOWERED_REPEATER != null) return;
        BlockTypes.UNPOWERED_REPEATER = AllayBlockType
                .builder(BlockUnpoweredRepeaterBehaviorImpl.class)
                .vanillaBlock(BlockId.UNPOWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initVault() {
        if (BlockTypes.VAULT != null) return;
        BlockTypes.VAULT = AllayBlockType
                .builder(BlockVaultBehaviorImpl.class)
                .vanillaBlock(BlockId.VAULT)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OMINOUS, BlockPropertyTypes.VAULT_STATE)
                .build();
    }

    public static void initVerdantFroglight() {
        if (BlockTypes.VERDANT_FROGLIGHT != null) return;
        BlockTypes.VERDANT_FROGLIGHT = AllayBlockType
                .builder(BlockVerdantFroglightBehaviorImpl.class)
                .vanillaBlock(BlockId.VERDANT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initVine() {
        if (BlockTypes.VINE != null) return;
        BlockTypes.VINE = AllayBlockType
                .builder(BlockVineBehaviorImpl.class)
                .vanillaBlock(BlockId.VINE)
                .setProperties(BlockPropertyTypes.VINE_DIRECTION_BITS)
                .build();
    }

    public static void initWallBanner() {
        if (BlockTypes.WALL_BANNER != null) return;
        BlockTypes.WALL_BANNER = AllayBlockType
                .builder(BlockWallBannerBehaviorImpl.class)
                .vanillaBlock(BlockId.WALL_BANNER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWallSign() {
        if (BlockTypes.WALL_SIGN != null) return;
        BlockTypes.WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedButton() {
        if (BlockTypes.WARPED_BUTTON != null) return;
        BlockTypes.WARPED_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedDoor() {
        if (BlockTypes.WARPED_DOOR != null) return;
        BlockTypes.WARPED_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWarpedDoubleSlab() {
        if (BlockTypes.WARPED_DOUBLE_SLAB != null) return;
        BlockTypes.WARPED_DOUBLE_SLAB = AllayBlockType
                .builder(BlockWarpedDoubleSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedFence() {
        if (BlockTypes.WARPED_FENCE != null) return;
        BlockTypes.WARPED_FENCE = AllayBlockType
                .builder(BlockWarpedFenceBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_FENCE)
                .build();
    }

    public static void initWarpedFenceGate() {
        if (BlockTypes.WARPED_FENCE_GATE != null) return;
        BlockTypes.WARPED_FENCE_GATE = AllayBlockType
                .builder(BlockWarpedFenceGateBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initWarpedFungus() {
        if (BlockTypes.WARPED_FUNGUS != null) return;
        BlockTypes.WARPED_FUNGUS = AllayBlockType
                .builder(BlockWarpedFungusBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_FUNGUS)
                .build();
    }

    public static void initWarpedHangingSign() {
        if (BlockTypes.WARPED_HANGING_SIGN != null) return;
        BlockTypes.WARPED_HANGING_SIGN = AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initWarpedHyphae() {
        if (BlockTypes.WARPED_HYPHAE != null) return;
        BlockTypes.WARPED_HYPHAE = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedNylium() {
        if (BlockTypes.WARPED_NYLIUM != null) return;
        BlockTypes.WARPED_NYLIUM = AllayBlockType
                .builder(BlockWarpedNyliumBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_NYLIUM)
                .build();
    }

    public static void initWarpedPlanks() {
        if (BlockTypes.WARPED_PLANKS != null) return;
        BlockTypes.WARPED_PLANKS = AllayBlockType
                .builder(BlockWarpedPlanksBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_PLANKS)
                .build();
    }

    public static void initWarpedPressurePlate() {
        if (BlockTypes.WARPED_PRESSURE_PLATE != null) return;
        BlockTypes.WARPED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockWarpedPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initWarpedRoots() {
        if (BlockTypes.WARPED_ROOTS != null) return;
        BlockTypes.WARPED_ROOTS = AllayBlockType
                .builder(BlockWarpedRootsBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_ROOTS)
                .build();
    }

    public static void initWarpedSlab() {
        if (BlockTypes.WARPED_SLAB != null) return;
        BlockTypes.WARPED_SLAB = AllayBlockType
                .builder(BlockWarpedSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedStairs() {
        if (BlockTypes.WARPED_STAIRS != null) return;
        BlockTypes.WARPED_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWarpedStandingSign() {
        if (BlockTypes.WARPED_STANDING_SIGN != null) return;
        BlockTypes.WARPED_STANDING_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initWarpedStem() {
        if (BlockTypes.WARPED_STEM != null) return;
        BlockTypes.WARPED_STEM = AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedTrapdoor() {
        if (BlockTypes.WARPED_TRAPDOOR != null) return;
        BlockTypes.WARPED_TRAPDOOR = AllayBlockType
                .builder(BlockWarpedTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWarpedWallSign() {
        if (BlockTypes.WARPED_WALL_SIGN != null) return;
        BlockTypes.WARPED_WALL_SIGN = AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedWartBlock() {
        if (BlockTypes.WARPED_WART_BLOCK != null) return;
        BlockTypes.WARPED_WART_BLOCK = AllayBlockType
                .builder(BlockWarpedWartBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_WART_BLOCK)
                .build();
    }

    public static void initWater() {
        if (BlockTypes.WATER != null) return;
        BlockTypes.WATER = AllayBlockType
                .builder(BlockWaterBehaviorImpl.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initWaterlily() {
        if (BlockTypes.WATERLILY != null) return;
        BlockTypes.WATERLILY = AllayBlockType
                .builder(BlockWaterlilyBehaviorImpl.class)
                .vanillaBlock(BlockId.WATERLILY)
                .build();
    }

    public static void initWaxedChiseledCopper() {
        if (BlockTypes.WAXED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedCopper() {
        if (BlockTypes.WAXED_COPPER != null) return;
        BlockTypes.WAXED_COPPER = AllayBlockType
                .builder(BlockWaxedCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_COPPER)
                .build();
    }

    public static void initWaxedCopperBulb() {
        if (BlockTypes.WAXED_COPPER_BULB != null) return;
        BlockTypes.WAXED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedCopperDoor() {
        if (BlockTypes.WAXED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedCopperGrate() {
        if (BlockTypes.WAXED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedCopperTrapdoor() {
        if (BlockTypes.WAXED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedCutCopper() {
        if (BlockTypes.WAXED_CUT_COPPER != null) return;
        BlockTypes.WAXED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER)
                .build();
    }

    public static void initWaxedCutCopperSlab() {
        if (BlockTypes.WAXED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedCutCopperStairs() {
        if (BlockTypes.WAXED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedExposedChiseledCopper() {
        if (BlockTypes.WAXED_EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedExposedChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopper() {
        if (BlockTypes.WAXED_EXPOSED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER = AllayBlockType
                .builder(BlockWaxedExposedCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopperBulb() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_BULB != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedExposedCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedExposedCopperDoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedExposedCopperGrate() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedExposedCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedExposedCopperTrapdoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedExposedCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedExposedCutCopper() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedExposedCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initWaxedExposedCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedExposedCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedExposedCutCopperStairs() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedExposedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedExposedDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedOxidizedChiseledCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopper() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopperBulb() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedOxidizedCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperDoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperGrate() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedOxidizedCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedOxidizedCopperTrapdoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedOxidizedCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedOxidizedCutCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedOxidizedCutCopperStairs() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedWeatheredChiseledCopper() {
        if (BlockTypes.WAXED_WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopper() {
        if (BlockTypes.WAXED_WEATHERED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopperBulb() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedWeatheredCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperDoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperGrate() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedWeatheredCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedWeatheredCopperTrapdoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedWeatheredCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedWeatheredCutCopper() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedWeatheredCutCopperStairs() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeatheredChiseledCopper() {
        if (BlockTypes.WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WEATHERED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWeatheredChiseledCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWeatheredCopper() {
        if (BlockTypes.WEATHERED_COPPER != null) return;
        BlockTypes.WEATHERED_COPPER = AllayBlockType
                .builder(BlockWeatheredCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER)
                .build();
    }

    public static void initWeatheredCopperBulb() {
        if (BlockTypes.WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WEATHERED_COPPER_BULB = AllayBlockType
                .builder(BlockWeatheredCopperBulbBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWeatheredCopperDoor() {
        if (BlockTypes.WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WEATHERED_COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWeatheredCopperGrate() {
        if (BlockTypes.WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WEATHERED_COPPER_GRATE = AllayBlockType
                .builder(BlockWeatheredCopperGrateBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWeatheredCopperTrapdoor() {
        if (BlockTypes.WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWeatheredCopperTrapdoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWeatheredCutCopper() {
        if (BlockTypes.WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WEATHERED_CUT_COPPER = AllayBlockType
                .builder(BlockWeatheredCutCopperBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWeatheredCutCopperSlab() {
        if (BlockTypes.WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWeatheredCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeatheredCutCopperStairs() {
        if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWeatheredDoubleCutCopperSlabBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeb() {
        if (BlockTypes.WEB != null) return;
        BlockTypes.WEB = AllayBlockType
                .builder(BlockWebBehaviorImpl.class)
                .vanillaBlock(BlockId.WEB)
                .build();
    }

    public static void initWeepingVines() {
        if (BlockTypes.WEEPING_VINES != null) return;
        BlockTypes.WEEPING_VINES = AllayBlockType
                .builder(BlockWeepingVinesBehaviorImpl.class)
                .vanillaBlock(BlockId.WEEPING_VINES)
                .setProperties(BlockPropertyTypes.WEEPING_VINES_AGE)
                .build();
    }

    public static void initWetSponge() {
        if (BlockTypes.WET_SPONGE != null) return;
        BlockTypes.WET_SPONGE = AllayBlockType
                .builder(BlockWetSpongeBehaviorImpl.class)
                .vanillaBlock(BlockId.WET_SPONGE)
                .build();
    }

    public static void initWheat() {
        if (BlockTypes.WHEAT != null) return;
        BlockTypes.WHEAT = AllayBlockType
                .builder(BlockWheatBehaviorImpl.class)
                .vanillaBlock(BlockId.WHEAT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initWhiteCandle() {
        if (BlockTypes.WHITE_CANDLE != null) return;
        BlockTypes.WHITE_CANDLE = AllayBlockType
                .builder(BlockWhiteCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCandleCake() {
        if (BlockTypes.WHITE_CANDLE_CAKE != null) return;
        BlockTypes.WHITE_CANDLE_CAKE = AllayBlockType
                .builder(BlockWhiteCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCarpet() {
        if (BlockTypes.WHITE_CARPET != null) return;
        BlockTypes.WHITE_CARPET = AllayBlockType
                .builder(BlockWhiteCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CARPET)
                .build();
    }

    public static void initWhiteConcrete() {
        if (BlockTypes.WHITE_CONCRETE != null) return;
        BlockTypes.WHITE_CONCRETE = AllayBlockType
                .builder(BlockWhiteConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE)
                .build();
    }

    public static void initWhiteConcretePowder() {
        if (BlockTypes.WHITE_CONCRETE_POWDER != null) return;
        BlockTypes.WHITE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockWhiteConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE_POWDER)
                .build();
    }

    public static void initWhiteGlazedTerracotta() {
        if (BlockTypes.WHITE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.WHITE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockWhiteGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWhiteShulkerBox() {
        if (BlockTypes.WHITE_SHULKER_BOX != null) return;
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .build();
    }

    public static void initWhiteStainedGlass() {
        if (BlockTypes.WHITE_STAINED_GLASS != null) return;
        BlockTypes.WHITE_STAINED_GLASS = AllayBlockType
                .builder(BlockWhiteStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS)
                .build();
    }

    public static void initWhiteStainedGlassPane() {
        if (BlockTypes.WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.WHITE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockWhiteStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initWhiteTerracotta() {
        if (BlockTypes.WHITE_TERRACOTTA != null) return;
        BlockTypes.WHITE_TERRACOTTA = AllayBlockType
                .builder(BlockWhiteTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_TERRACOTTA)
                .build();
    }

    public static void initWhiteTulip() {
        if (BlockTypes.WHITE_TULIP != null) return;
        BlockTypes.WHITE_TULIP = AllayBlockType
                .builder(BlockWhiteTulipBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_TULIP)
                .build();
    }

    public static void initWhiteWool() {
        if (BlockTypes.WHITE_WOOL != null) return;
        BlockTypes.WHITE_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_WOOL)
                .build();
    }

    public static void initWitherRose() {
        if (BlockTypes.WITHER_ROSE != null) return;
        BlockTypes.WITHER_ROSE = AllayBlockType
                .builder(BlockWitherRoseBehaviorImpl.class)
                .vanillaBlock(BlockId.WITHER_ROSE)
                .build();
    }

    public static void initWitherSkeletonSkull() {
        if (BlockTypes.WITHER_SKELETON_SKULL != null) return;
        BlockTypes.WITHER_SKELETON_SKULL = AllayBlockType
                .builder(BlockWitherSkeletonSkullBehaviorImpl.class)
                .vanillaBlock(BlockId.WITHER_SKELETON_SKULL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWoodenButton() {
        if (BlockTypes.WOODEN_BUTTON != null) return;
        BlockTypes.WOODEN_BUTTON = AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(BlockId.WOODEN_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWoodenDoor() {
        if (BlockTypes.WOODEN_DOOR != null) return;
        BlockTypes.WOODEN_DOOR = AllayBlockType
                .builder(BlockDoorBehaviorImpl.class)
                .vanillaBlock(BlockId.WOODEN_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWoodenPressurePlate() {
        if (BlockTypes.WOODEN_PRESSURE_PLATE != null) return;
        BlockTypes.WOODEN_PRESSURE_PLATE = AllayBlockType
                .builder(BlockWoodenPressurePlateBehaviorImpl.class)
                .vanillaBlock(BlockId.WOODEN_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initYellowCandle() {
        if (BlockTypes.YELLOW_CANDLE != null) return;
        BlockTypes.YELLOW_CANDLE = AllayBlockType
                .builder(BlockYellowCandleBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCandleCake() {
        if (BlockTypes.YELLOW_CANDLE_CAKE != null) return;
        BlockTypes.YELLOW_CANDLE_CAKE = AllayBlockType
                .builder(BlockYellowCandleCakeBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCarpet() {
        if (BlockTypes.YELLOW_CARPET != null) return;
        BlockTypes.YELLOW_CARPET = AllayBlockType
                .builder(BlockYellowCarpetBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CARPET)
                .build();
    }

    public static void initYellowConcrete() {
        if (BlockTypes.YELLOW_CONCRETE != null) return;
        BlockTypes.YELLOW_CONCRETE = AllayBlockType
                .builder(BlockYellowConcreteBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE)
                .build();
    }

    public static void initYellowConcretePowder() {
        if (BlockTypes.YELLOW_CONCRETE_POWDER != null) return;
        BlockTypes.YELLOW_CONCRETE_POWDER = AllayBlockType
                .builder(BlockYellowConcretePowderBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE_POWDER)
                .build();
    }

    public static void initYellowGlazedTerracotta() {
        if (BlockTypes.YELLOW_GLAZED_TERRACOTTA != null) return;
        BlockTypes.YELLOW_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockYellowGlazedTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initYellowShulkerBox() {
        if (BlockTypes.YELLOW_SHULKER_BOX != null) return;
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .build();
    }

    public static void initYellowStainedGlass() {
        if (BlockTypes.YELLOW_STAINED_GLASS != null) return;
        BlockTypes.YELLOW_STAINED_GLASS = AllayBlockType
                .builder(BlockYellowStainedGlassBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initYellowStainedGlassPane() {
        if (BlockTypes.YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.YELLOW_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockYellowStainedGlassPaneBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initYellowTerracotta() {
        if (BlockTypes.YELLOW_TERRACOTTA != null) return;
        BlockTypes.YELLOW_TERRACOTTA = AllayBlockType
                .builder(BlockYellowTerracottaBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_TERRACOTTA)
                .build();
    }

    public static void initYellowWool() {
        if (BlockTypes.YELLOW_WOOL != null) return;
        BlockTypes.YELLOW_WOOL = AllayBlockType
                .builder(BlockWoolBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_WOOL)
                .build();
    }

    public static void initZombieHead() {
        if (BlockTypes.ZOMBIE_HEAD != null) return;
        BlockTypes.ZOMBIE_HEAD = AllayBlockType
                .builder(BlockZombieHeadBehaviorImpl.class)
                .vanillaBlock(BlockId.ZOMBIE_HEAD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }
}
