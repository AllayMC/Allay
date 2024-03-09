package org.allaymc.server.block.type;

import org.allaymc.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockActivatorRailBehavior;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystBlockBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.block.interfaces.BlockAncientDebrisBehavior;
import org.allaymc.api.block.interfaces.BlockAndesiteBehavior;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.block.interfaces.BlockAzaleaBehavior;
import org.allaymc.api.block.interfaces.BlockBambooBehavior;
import org.allaymc.api.block.interfaces.BlockBambooBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBambooMosaicBehavior;
import org.allaymc.api.block.interfaces.BlockBambooPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockBarrelBehavior;
import org.allaymc.api.block.interfaces.BlockBarrierBehavior;
import org.allaymc.api.block.interfaces.BlockBasaltBehavior;
import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.block.interfaces.BlockBedrockBehavior;
import org.allaymc.api.block.interfaces.BlockBeeNestBehavior;
import org.allaymc.api.block.interfaces.BlockBeehiveBehavior;
import org.allaymc.api.block.interfaces.BlockBeetrootBehavior;
import org.allaymc.api.block.interfaces.BlockBellBehavior;
import org.allaymc.api.block.interfaces.BlockBigDripleafBehavior;
import org.allaymc.api.block.interfaces.BlockBirchPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockBlackstoneBehavior;
import org.allaymc.api.block.interfaces.BlockBlastFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockBlueIceBehavior;
import org.allaymc.api.block.interfaces.BlockBoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBookshelfBehavior;
import org.allaymc.api.block.interfaces.BlockBorderBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.block.interfaces.BlockBrickBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBrownMushroomBehavior;
import org.allaymc.api.block.interfaces.BlockBrownMushroomBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBubbleColumnBehavior;
import org.allaymc.api.block.interfaces.BlockBuddingAmethystBehavior;
import org.allaymc.api.block.interfaces.BlockCactusBehavior;
import org.allaymc.api.block.interfaces.BlockCakeBehavior;
import org.allaymc.api.block.interfaces.BlockCalciteBehavior;
import org.allaymc.api.block.interfaces.BlockCalibratedSculkSensorBehavior;
import org.allaymc.api.block.interfaces.BlockCameraBehavior;
import org.allaymc.api.block.interfaces.BlockCampfireBehavior;
import org.allaymc.api.block.interfaces.BlockCarrotsBehavior;
import org.allaymc.api.block.interfaces.BlockCartographyTableBehavior;
import org.allaymc.api.block.interfaces.BlockCarvedPumpkinBehavior;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.block.interfaces.BlockChainBehavior;
import org.allaymc.api.block.interfaces.BlockChainCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockChemicalHeatBehavior;
import org.allaymc.api.block.interfaces.BlockChemistryTableBehavior;
import org.allaymc.api.block.interfaces.BlockCherryPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockChestBehavior;
import org.allaymc.api.block.interfaces.BlockChiseledBookshelfBehavior;
import org.allaymc.api.block.interfaces.BlockChiseledDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockChiseledPolishedBlackstoneBehavior;
import org.allaymc.api.block.interfaces.BlockChiseledTuffBehavior;
import org.allaymc.api.block.interfaces.BlockChorusFlowerBehavior;
import org.allaymc.api.block.interfaces.BlockChorusPlantBehavior;
import org.allaymc.api.block.interfaces.BlockClayBehavior;
import org.allaymc.api.block.interfaces.BlockClientRequestPlaceholderBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCoalBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCoalOreBehavior;
import org.allaymc.api.block.interfaces.BlockCobbledDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockCobblestoneBehavior;
import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.block.interfaces.BlockCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockComposterBehavior;
import org.allaymc.api.block.interfaces.BlockConduitBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockCopperOreBehavior;
import org.allaymc.api.block.interfaces.BlockCoralBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCrackedDeepslateTilesBehavior;
import org.allaymc.api.block.interfaces.BlockCrafterBehavior;
import org.allaymc.api.block.interfaces.BlockCraftingTableBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonFungusBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonNyliumBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonRootsBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonStemBehavior;
import org.allaymc.api.block.interfaces.BlockCryingObsidianBehavior;
import org.allaymc.api.block.interfaces.BlockDarkOakPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockDaylightDetectorBehavior;
import org.allaymc.api.block.interfaces.BlockDaylightDetectorInvertedBehavior;
import org.allaymc.api.block.interfaces.BlockDeadbushBehavior;
import org.allaymc.api.block.interfaces.BlockDecoratedPotBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateCoalOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateCopperOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateDiamondOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateEmeraldOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateGoldOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateIronOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateLapisOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockDeepslateTilesBehavior;
import org.allaymc.api.block.interfaces.BlockDenyBehavior;
import org.allaymc.api.block.interfaces.BlockDetectorRailBehavior;
import org.allaymc.api.block.interfaces.BlockDiamondBlockBehavior;
import org.allaymc.api.block.interfaces.BlockDiamondOreBehavior;
import org.allaymc.api.block.interfaces.BlockDioriteBehavior;
import org.allaymc.api.block.interfaces.BlockDirtBehavior;
import org.allaymc.api.block.interfaces.BlockDirtWithRootsBehavior;
import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
import org.allaymc.api.block.interfaces.BlockDoublePlantBehavior;
import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.block.interfaces.BlockDriedKelpBlockBehavior;
import org.allaymc.api.block.interfaces.BlockDripstoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockDropperBehavior;
import org.allaymc.api.block.interfaces.BlockEmeraldBlockBehavior;
import org.allaymc.api.block.interfaces.BlockEmeraldOreBehavior;
import org.allaymc.api.block.interfaces.BlockEnchantingTableBehavior;
import org.allaymc.api.block.interfaces.BlockEndGatewayBehavior;
import org.allaymc.api.block.interfaces.BlockEndPortalBehavior;
import org.allaymc.api.block.interfaces.BlockEndPortalFrameBehavior;
import org.allaymc.api.block.interfaces.BlockEndRodBehavior;
import org.allaymc.api.block.interfaces.BlockEndStoneBehavior;
import org.allaymc.api.block.interfaces.BlockEnderChestBehavior;
import org.allaymc.api.block.interfaces.BlockExposedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockExposedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockFarmlandBehavior;
import org.allaymc.api.block.interfaces.BlockFireBehavior;
import org.allaymc.api.block.interfaces.BlockFletchingTableBehavior;
import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.block.interfaces.BlockFloweringAzaleaBehavior;
import org.allaymc.api.block.interfaces.BlockFrameBehavior;
import org.allaymc.api.block.interfaces.BlockFrogSpawnBehavior;
import org.allaymc.api.block.interfaces.BlockFrostedIceBehavior;
import org.allaymc.api.block.interfaces.BlockFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockGildedBlackstoneBehavior;
import org.allaymc.api.block.interfaces.BlockGlowFrameBehavior;
import org.allaymc.api.block.interfaces.BlockGlowLichenBehavior;
import org.allaymc.api.block.interfaces.BlockGlowingobsidianBehavior;
import org.allaymc.api.block.interfaces.BlockGlowstoneBehavior;
import org.allaymc.api.block.interfaces.BlockGoldBlockBehavior;
import org.allaymc.api.block.interfaces.BlockGoldOreBehavior;
import org.allaymc.api.block.interfaces.BlockGoldenRailBehavior;
import org.allaymc.api.block.interfaces.BlockGraniteBehavior;
import org.allaymc.api.block.interfaces.BlockGrassBehavior;
import org.allaymc.api.block.interfaces.BlockGrassPathBehavior;
import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.block.interfaces.BlockGrindstoneBehavior;
import org.allaymc.api.block.interfaces.BlockHangingRootsBehavior;
import org.allaymc.api.block.interfaces.BlockHardenedClayBehavior;
import org.allaymc.api.block.interfaces.BlockHayBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHeavyWeightedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockHoneyBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHoneycombBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHopperBehavior;
import org.allaymc.api.block.interfaces.BlockIceBehavior;
import org.allaymc.api.block.interfaces.BlockInfestedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockInfoUpdate2Behavior;
import org.allaymc.api.block.interfaces.BlockInfoUpdateBehavior;
import org.allaymc.api.block.interfaces.BlockInvisibleBedrockBehavior;
import org.allaymc.api.block.interfaces.BlockIronBarsBehavior;
import org.allaymc.api.block.interfaces.BlockIronBlockBehavior;
import org.allaymc.api.block.interfaces.BlockIronOreBehavior;
import org.allaymc.api.block.interfaces.BlockJigsawBehavior;
import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.block.interfaces.BlockJunglePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockKelpBehavior;
import org.allaymc.api.block.interfaces.BlockLadderBehavior;
import org.allaymc.api.block.interfaces.BlockLanternBehavior;
import org.allaymc.api.block.interfaces.BlockLapisBlockBehavior;
import org.allaymc.api.block.interfaces.BlockLapisOreBehavior;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.interfaces.BlockLeverBehavior;
import org.allaymc.api.block.interfaces.BlockLightBlockBehavior;
import org.allaymc.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.block.interfaces.BlockLitBlastFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockLitDeepslateRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockLitFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockLitPumpkinBehavior;
import org.allaymc.api.block.interfaces.BlockLitRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockLitRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockLitSmokerBehavior;
import org.allaymc.api.block.interfaces.BlockLodestoneBehavior;
import org.allaymc.api.block.interfaces.BlockLoomBehavior;
import org.allaymc.api.block.interfaces.BlockMagmaBehavior;
import org.allaymc.api.block.interfaces.BlockMangrovePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockMangrovePropaguleBehavior;
import org.allaymc.api.block.interfaces.BlockMangroveRootsBehavior;
import org.allaymc.api.block.interfaces.BlockMelonBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMelonStemBehavior;
import org.allaymc.api.block.interfaces.BlockMobSpawnerBehavior;
import org.allaymc.api.block.interfaces.BlockMonsterEggBehavior;
import org.allaymc.api.block.interfaces.BlockMossBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMossyCobblestoneBehavior;
import org.allaymc.api.block.interfaces.BlockMovingBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMudBehavior;
import org.allaymc.api.block.interfaces.BlockMuddyMangroveRootsBehavior;
import org.allaymc.api.block.interfaces.BlockMyceliumBehavior;
import org.allaymc.api.block.interfaces.BlockNetherBrickBehavior;
import org.allaymc.api.block.interfaces.BlockNetherGoldOreBehavior;
import org.allaymc.api.block.interfaces.BlockNetherSproutsBehavior;
import org.allaymc.api.block.interfaces.BlockNetherWartBehavior;
import org.allaymc.api.block.interfaces.BlockNetherWartBlockBehavior;
import org.allaymc.api.block.interfaces.BlockNetheriteBlockBehavior;
import org.allaymc.api.block.interfaces.BlockNetherrackBehavior;
import org.allaymc.api.block.interfaces.BlockNetherreactorBehavior;
import org.allaymc.api.block.interfaces.BlockNoteblockBehavior;
import org.allaymc.api.block.interfaces.BlockObserverBehavior;
import org.allaymc.api.block.interfaces.BlockObsidianBehavior;
import org.allaymc.api.block.interfaces.BlockOchreFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockPackedIceBehavior;
import org.allaymc.api.block.interfaces.BlockPackedMudBehavior;
import org.allaymc.api.block.interfaces.BlockPearlescentFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockPinkPetalsBehavior;
import org.allaymc.api.block.interfaces.BlockPitcherCropBehavior;
import org.allaymc.api.block.interfaces.BlockPitcherPlantBehavior;
import org.allaymc.api.block.interfaces.BlockPodzolBehavior;
import org.allaymc.api.block.interfaces.BlockPointedDripstoneBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedAndesiteBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedBasaltBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedBlackstoneBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedBlackstonePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedDioriteBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedGraniteBehavior;
import org.allaymc.api.block.interfaces.BlockPolishedTuffBehavior;
import org.allaymc.api.block.interfaces.BlockPortalBehavior;
import org.allaymc.api.block.interfaces.BlockPotatoesBehavior;
import org.allaymc.api.block.interfaces.BlockPowderSnowBehavior;
import org.allaymc.api.block.interfaces.BlockPoweredComparatorBehavior;
import org.allaymc.api.block.interfaces.BlockPoweredRepeaterBehavior;
import org.allaymc.api.block.interfaces.BlockPrismarineBehavior;
import org.allaymc.api.block.interfaces.BlockPumpkinBehavior;
import org.allaymc.api.block.interfaces.BlockPumpkinStemBehavior;
import org.allaymc.api.block.interfaces.BlockPurpurBlockBehavior;
import org.allaymc.api.block.interfaces.BlockQuartzBlockBehavior;
import org.allaymc.api.block.interfaces.BlockQuartzOreBehavior;
import org.allaymc.api.block.interfaces.BlockRailBehavior;
import org.allaymc.api.block.interfaces.BlockRawCopperBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRawGoldBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRawIronBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedFlowerBehavior;
import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.block.interfaces.BlockRedMushroomBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedNetherBrickBehavior;
import org.allaymc.api.block.interfaces.BlockRedSandstoneBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneWireBehavior;
import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockReserved6Behavior;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.block.interfaces.BlockSandstoneBehavior;
import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.block.interfaces.BlockSculkBehavior;
import org.allaymc.api.block.interfaces.BlockSculkCatalystBehavior;
import org.allaymc.api.block.interfaces.BlockSculkSensorBehavior;
import org.allaymc.api.block.interfaces.BlockSculkShriekerBehavior;
import org.allaymc.api.block.interfaces.BlockSculkVeinBehavior;
import org.allaymc.api.block.interfaces.BlockSeaLanternBehavior;
import org.allaymc.api.block.interfaces.BlockSeaPickleBehavior;
import org.allaymc.api.block.interfaces.BlockSeagrassBehavior;
import org.allaymc.api.block.interfaces.BlockShroomlightBehavior;
import org.allaymc.api.block.interfaces.BlockSkullBehavior;
import org.allaymc.api.block.interfaces.BlockSlimeBehavior;
import org.allaymc.api.block.interfaces.BlockSmallDripleafBlockBehavior;
import org.allaymc.api.block.interfaces.BlockSmithingTableBehavior;
import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.block.interfaces.BlockSmoothBasaltBehavior;
import org.allaymc.api.block.interfaces.BlockSmoothStoneBehavior;
import org.allaymc.api.block.interfaces.BlockSnifferEggBehavior;
import org.allaymc.api.block.interfaces.BlockSnowBehavior;
import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.block.interfaces.BlockSoulCampfireBehavior;
import org.allaymc.api.block.interfaces.BlockSoulFireBehavior;
import org.allaymc.api.block.interfaces.BlockSoulLanternBehavior;
import org.allaymc.api.block.interfaces.BlockSoulSandBehavior;
import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.block.interfaces.BlockSpongeBehavior;
import org.allaymc.api.block.interfaces.BlockSporeBlossomBehavior;
import org.allaymc.api.block.interfaces.BlockSprucePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.block.interfaces.BlockStoneBehavior;
import org.allaymc.api.block.interfaces.BlockStonePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStonebrickBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedBambooBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedCrimsonHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedCrimsonStemBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedWarpedHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedWarpedStemBehavior;
import org.allaymc.api.block.interfaces.BlockStructureBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.block.interfaces.BlockTallgrassBehavior;
import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.block.interfaces.BlockTrialSpawnerBehavior;
import org.allaymc.api.block.interfaces.BlockTripWireBehavior;
import org.allaymc.api.block.interfaces.BlockTripwireHookBehavior;
import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.block.interfaces.BlockTurtleEggBehavior;
import org.allaymc.api.block.interfaces.BlockTwistingVinesBehavior;
import org.allaymc.api.block.interfaces.BlockUnknownBehavior;
import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import org.allaymc.api.block.interfaces.BlockVerdantFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockVineBehavior;
import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedFungusBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedNyliumBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedRootsBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedStemBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedWartBlockBehavior;
import org.allaymc.api.block.interfaces.BlockWaterlilyBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockWeatheredCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockWeatheredCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockWebBehavior;
import org.allaymc.api.block.interfaces.BlockWeepingVinesBehavior;
import org.allaymc.api.block.interfaces.BlockWheatBehavior;
import org.allaymc.api.block.interfaces.BlockWitherRoseBehavior;
import org.allaymc.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockYellowFlowerBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledNetherBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledTuffBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedDeepslateBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedNetherBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedPolishedBlackstoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockDeepslateBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockEndBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockMudBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockPolishedBlackstoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockQuartzBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockTuffBricksBehavior;
import org.allaymc.api.block.interfaces.button.BlockAcaciaButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockBambooButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockBirchButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockCherryButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockCrimsonButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockDarkOakButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockJungleButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockMangroveButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockPolishedBlackstoneButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockSpruceButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockStoneButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockWarpedButtonBehavior;
import org.allaymc.api.block.interfaces.button.BlockWoodenButtonBehavior;
import org.allaymc.api.block.interfaces.candle.BlockBlackCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockBlueCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockBrownCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockCyanCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockGrayCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockGreenCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockLightBlueCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockLightGrayCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockLimeCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockMagentaCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockOrangeCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockPinkCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockPurpleCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockRedCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockWhiteCandleBehavior;
import org.allaymc.api.block.interfaces.candle.BlockYellowCandleBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockBlackCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockBlueCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockBrownCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockCyanCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockGrayCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockGreenCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockLightBlueCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockLightGrayCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockLimeCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockMagentaCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockOrangeCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockPinkCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockPurpleCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockRedCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockWhiteCandleCakeBehavior;
import org.allaymc.api.block.interfaces.candlecake.BlockYellowCandleCakeBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockBlackCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockBlueCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockBrownCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockCyanCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockGrayCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockGreenCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockLightBlueCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockLightGrayCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockLimeCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockMagentaCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockMossCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockOrangeCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockPinkCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockPurpleCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockRedCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockWhiteCarpetBehavior;
import org.allaymc.api.block.interfaces.carpet.BlockYellowCarpetBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockBlackConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockBlueConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockBrownConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockCyanConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockGrayConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockGreenConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockLightBlueConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockLightGrayConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockLimeConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockMagentaConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockOrangeConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockPinkConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockPurpleConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockRedConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockWhiteConcreteBehavior;
import org.allaymc.api.block.interfaces.concrete.BlockYellowConcreteBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockBlackConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockBlueConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockBrownConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockCyanConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockGrayConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockGreenConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockLightBlueConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockLightGrayConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockLimeConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockMagentaConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockOrangeConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockPinkConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockPurpleConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockRedConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockWhiteConcretePowderBehavior;
import org.allaymc.api.block.interfaces.concretepowder.BlockYellowConcretePowderBehavior;
import org.allaymc.api.block.interfaces.copper.BlockChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockExposedChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockExposedCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockExposedCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockOxidizedChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockOxidizedCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockOxidizedCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedExposedChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedExposedCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedExposedCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredCutCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWeatheredChiseledCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWeatheredCopperBehavior;
import org.allaymc.api.block.interfaces.copper.BlockWeatheredCutCopperBehavior;
import org.allaymc.api.block.interfaces.coral.BlockBrainCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockBubbleCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockDeadBrainCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockDeadBubbleCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockDeadFireCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockDeadHornCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockDeadTubeCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockFireCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockHornCoralBehavior;
import org.allaymc.api.block.interfaces.coral.BlockTubeCoralBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanDeadBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanHang2Behavior;
import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanHang3Behavior;
import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanHangBehavior;
import org.allaymc.api.block.interfaces.door.BlockAcaciaDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockBambooDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockBirchDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockCherryDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockCrimsonDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockDarkOakDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockExposedCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockIronDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockJungleDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockMangroveDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockOxidizedCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockSpruceDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWarpedDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWaxedCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWaxedExposedCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWaxedOxidizedCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWaxedWeatheredCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWeatheredCopperDoorBehavior;
import org.allaymc.api.block.interfaces.door.BlockWoodenDoorBehavior;
import org.allaymc.api.block.interfaces.element.BlockElement0Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement100Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement101Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement102Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement103Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement104Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement105Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement106Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement107Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement108Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement109Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement10Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement110Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement111Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement112Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement113Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement114Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement115Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement116Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement117Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement118Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement11Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement12Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement13Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement14Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement15Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement16Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement17Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement18Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement19Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement1Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement20Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement21Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement22Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement23Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement24Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement25Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement26Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement27Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement28Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement29Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement2Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement30Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement31Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement32Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement33Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement34Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement35Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement36Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement37Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement38Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement39Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement3Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement40Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement41Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement42Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement43Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement44Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement45Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement46Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement47Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement48Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement49Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement4Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement50Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement51Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement52Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement53Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement54Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement55Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement56Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement57Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement58Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement59Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement5Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement60Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement61Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement62Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement63Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement64Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement65Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement66Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement67Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement68Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement69Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement6Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement70Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement71Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement72Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement73Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement74Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement75Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement76Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement77Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement78Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement79Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement7Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement80Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement81Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement82Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement83Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement84Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement85Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement86Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement87Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement88Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement89Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement8Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement90Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement91Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement92Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement93Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement94Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement95Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement96Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement97Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement98Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement99Behavior;
import org.allaymc.api.block.interfaces.element.BlockElement9Behavior;
import org.allaymc.api.block.interfaces.fence.BlockAcaciaFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockBambooFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockBirchFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockCherryFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockCrimsonFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockDarkOakFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockJungleFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockMangroveFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockNetherBrickFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockOakFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockSpruceFenceBehavior;
import org.allaymc.api.block.interfaces.fence.BlockWarpedFenceBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockAcaciaFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockBambooFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockBirchFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockCherryFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockCrimsonFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockDarkOakFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockJungleFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockMangroveFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockSpruceFenceGateBehavior;
import org.allaymc.api.block.interfaces.fencegate.BlockWarpedFenceGateBehavior;
import org.allaymc.api.block.interfaces.glass.BlockGlassBehavior;
import org.allaymc.api.block.interfaces.glass.BlockHardGlassBehavior;
import org.allaymc.api.block.interfaces.glass.BlockTintedGlassBehavior;
import org.allaymc.api.block.interfaces.glasspane.BlockGlassPaneBehavior;
import org.allaymc.api.block.interfaces.glasspane.BlockHardGlassPaneBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockAcaciaHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockBambooHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockBirchHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockCherryHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockCrimsonHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockDarkOakHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockJungleHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockMangroveHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockOakHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockSpruceHangingSignBehavior;
import org.allaymc.api.block.interfaces.hangingsign.BlockWarpedHangingSignBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesFloweredBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockCherryLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockLeaves2Behavior;
import org.allaymc.api.block.interfaces.leaves.BlockLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockMangroveLeavesBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingWaterBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockWaterBehavior;
import org.allaymc.api.block.interfaces.log.BlockAcaciaLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockBirchLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockCherryLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockDarkOakLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockJungleLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockMangroveLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockOakLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockSpruceLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedAcaciaLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedBirchLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedCherryLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedDarkOakLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedJungleLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedMangroveLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedOakLogBehavior;
import org.allaymc.api.block.interfaces.log.BlockStrippedSpruceLogBehavior;
import org.allaymc.api.block.interfaces.piston.BlockPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.piston.BlockPistonBehavior;
import org.allaymc.api.block.interfaces.piston.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.piston.BlockStickyPistonBehavior;
import org.allaymc.api.block.interfaces.planks.BlockAcaciaPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockBambooPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockBirchPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockCherryPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockCrimsonPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockDarkOakPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockJunglePlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockMangrovePlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockOakPlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockSprucePlanksBehavior;
import org.allaymc.api.block.interfaces.planks.BlockWarpedPlanksBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockBambooSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockCherrySaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockSaplingBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockBlackShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockBlueShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockBrownShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockCyanShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockGrayShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockGreenShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockLightBlueShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockLightGrayShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockLimeShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockMagentaShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockOrangeShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockPinkShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockPurpleShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockRedShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockUndyedShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockWhiteShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.BlockYellowShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooMosaicDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooMosaicSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBlackstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBlackstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCherryDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCherrySlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCrimsonDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCrimsonSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateTileDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateTileSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlab2Behavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlab3Behavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlab4Behavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleWoodenSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockExposedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockExposedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMangroveDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMangroveSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMudBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMudBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOxidizedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOxidizedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedTuffDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedTuffSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlab2Behavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlab3Behavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlab4Behavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockTuffBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockTuffBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockTuffDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockTuffSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWarpedDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWarpedSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedExposedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedExposedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedOxidizedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedOxidizedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedWeatheredCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWaxedWeatheredDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWeatheredCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWeatheredDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockWoodenSlabBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockBlackStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockBlueStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockBrownStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockCyanStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockGrayStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockGreenStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardBlackStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardBlueStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardBrownStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardCyanStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardGrayStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardGreenStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardLightBlueStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardLightGrayStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardLimeStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardMagentaStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardOrangeStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardPinkStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardPurpleStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardRedStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardWhiteStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardYellowStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockLightBlueStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockLightGrayStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockLimeStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockMagentaStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockOrangeStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockPinkStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockPurpleStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockRedStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockWhiteStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglass.BlockYellowStainedGlassBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockBlackStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockBrownStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockCyanStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockGreenStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBlackStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBrownStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardCyanStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardGreenStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardLightBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardLightGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardLimeStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardMagentaStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardOrangeStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardPinkStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardPurpleStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardRedStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardWhiteStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardYellowStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockLightBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockLightGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockLimeStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockMagentaStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockOrangeStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockPinkStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockPurpleStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockRedStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockWhiteStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockYellowStainedGlassPaneBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockAcaciaStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockAndesiteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockBambooMosaicStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockBambooStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockBirchStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockBlackstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockCherryStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockCobbledDeepslateStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockCrimsonStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockDarkOakStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockDarkPrismarineStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockDeepslateBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockDeepslateTileStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockDioriteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockEndBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockExposedCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockGraniteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockJungleStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockMangroveStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockMossyCobblestoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockMossyStoneBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockMudBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockNetherBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockNormalStoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockOakStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockOxidizedCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedAndesiteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedBlackstoneBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedBlackstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedDeepslateStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedDioriteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedGraniteStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPolishedTuffStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPrismarineBricksStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPrismarineStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockPurpurStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockQuartzStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockRedNetherBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockRedSandstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockSandstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockSmoothQuartzStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockSmoothRedSandstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockSmoothSandstoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockSpruceStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockStoneBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockStoneStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockTuffBrickStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockTuffStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWarpedStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedExposedCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedOxidizedCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedWeatheredCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.stairs.BlockWeatheredCutCopperStairsBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockAcaciaStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockBambooStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockBirchStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockCherryStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockCrimsonStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockDarkoakStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockJungleStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockMangroveStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockSpruceStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockStandingSignBehavior;
import org.allaymc.api.block.interfaces.standingsign.BlockWarpedStandingSignBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBlackGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBlackTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBlueGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBlueTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBrownGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockBrownTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockCyanGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockCyanTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockGrayGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockGrayTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockGreenGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockGreenTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockLightBlueGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockLightBlueTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockLightGrayTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockLimeGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockLimeTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockMagentaGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockMagentaTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockOrangeGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockOrangeTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockPinkGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockPinkTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockPurpleGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockPurpleTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockRedGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockRedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockSilverGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockWhiteGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockWhiteTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockYellowGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.terracotta.BlockYellowTerracottaBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchBpBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchRgBehavior;
import org.allaymc.api.block.interfaces.torch.BlockRedstoneTorchBehavior;
import org.allaymc.api.block.interfaces.torch.BlockSoulTorchBehavior;
import org.allaymc.api.block.interfaces.torch.BlockTorchBehavior;
import org.allaymc.api.block.interfaces.torch.BlockTorchflowerBehavior;
import org.allaymc.api.block.interfaces.torch.BlockTorchflowerCropBehavior;
import org.allaymc.api.block.interfaces.torch.BlockUnderwaterTorchBehavior;
import org.allaymc.api.block.interfaces.torch.BlockUnlitRedstoneTorchBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockAcaciaTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockBambooTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockBirchTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockCherryTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockCrimsonTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockDarkOakTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockExposedCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockIronTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockJungleTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockMangroveTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockOxidizedCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockSpruceTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWarpedTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedExposedCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedOxidizedCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedWeatheredCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.trapdoor.BlockWeatheredCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.wall.BlockBlackstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockCobbledDeepslateWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockCobblestoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockDeepslateBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockDeepslateTileWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockMudBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedBlackstoneBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedBlackstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedDeepslateWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedTuffWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockTuffBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockTuffWallBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockAcaciaWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockBambooWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockBirchWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockCherryWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockCrimsonWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockDarkoakWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockJungleWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockMangroveWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockSpruceWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockWallSignBehavior;
import org.allaymc.api.block.interfaces.wallsign.BlockWarpedWallSignBehavior;
import org.allaymc.api.block.interfaces.wood.BlockCherryWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockMangroveWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedCherryWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedMangroveWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockWoodBehavior;
import org.allaymc.api.block.interfaces.wool.BlockBlackWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockBlueWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockBrownWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockCyanWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockGrayWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockGreenWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockLightBlueWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockLightGrayWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockLimeWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockMagentaWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockOrangeWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockPinkWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockPurpleWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockRedWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockWhiteWoolBehavior;
import org.allaymc.api.block.interfaces.wool.BlockYellowWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class BlockTypeDefaultInitializer {
  public static void initAcaciaButton() {
    if (BlockTypes.ACACIA_BUTTON_TYPE != null) return;
    BlockTypes.ACACIA_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initAcaciaDoor() {
    if (BlockTypes.ACACIA_DOOR_TYPE != null) return;
    BlockTypes.ACACIA_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initAcaciaFence() {
    if (BlockTypes.ACACIA_FENCE_TYPE != null) return;
    BlockTypes.ACACIA_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE)
            .build();
  }

  public static void initAcaciaFenceGate() {
    if (BlockTypes.ACACIA_FENCE_GATE_TYPE != null) return;
    BlockTypes.ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initAcaciaHangingSign() {
    if (BlockTypes.ACACIA_HANGING_SIGN_TYPE != null) return;
    BlockTypes.ACACIA_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initAcaciaLog() {
    if (BlockTypes.ACACIA_LOG_TYPE != null) return;
    BlockTypes.ACACIA_LOG_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaLogBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initAcaciaPlanks() {
    if (BlockTypes.ACACIA_PLANKS_TYPE != null) return;
    BlockTypes.ACACIA_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_PLANKS)
            .build();
  }

  public static void initAcaciaPressurePlate() {
    if (BlockTypes.ACACIA_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initAcaciaStairs() {
    if (BlockTypes.ACACIA_STAIRS_TYPE != null) return;
    BlockTypes.ACACIA_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initAcaciaStandingSign() {
    if (BlockTypes.ACACIA_STANDING_SIGN_TYPE != null) return;
    BlockTypes.ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initAcaciaTrapdoor() {
    if (BlockTypes.ACACIA_TRAPDOOR_TYPE != null) return;
    BlockTypes.ACACIA_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initAcaciaWallSign() {
    if (BlockTypes.ACACIA_WALL_SIGN_TYPE != null) return;
    BlockTypes.ACACIA_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initActivatorRail() {
    if (BlockTypes.ACTIVATOR_RAIL_TYPE != null) return;
    BlockTypes.ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockActivatorRailBehavior.class)
            .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }

  public static void initAir() {
    if (BlockTypes.AIR_TYPE != null) return;
    BlockTypes.AIR_TYPE = BlockTypeBuilder
            .builder(BlockAirBehavior.class)
            .vanillaBlock(VanillaBlockId.AIR)
            .build();
  }

  public static void initAllow() {
    if (BlockTypes.ALLOW_TYPE != null) return;
    BlockTypes.ALLOW_TYPE = BlockTypeBuilder
            .builder(BlockAllowBehavior.class)
            .vanillaBlock(VanillaBlockId.ALLOW)
            .build();
  }

  public static void initAmethystBlock() {
    if (BlockTypes.AMETHYST_BLOCK_TYPE != null) return;
    BlockTypes.AMETHYST_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockAmethystBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.AMETHYST_BLOCK)
            .build();
  }

  public static void initAmethystCluster() {
    if (BlockTypes.AMETHYST_CLUSTER_TYPE != null) return;
    BlockTypes.AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
            .builder(BlockAmethystClusterBehavior.class)
            .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }

  public static void initAncientDebris() {
    if (BlockTypes.ANCIENT_DEBRIS_TYPE != null) return;
    BlockTypes.ANCIENT_DEBRIS_TYPE = BlockTypeBuilder
            .builder(BlockAncientDebrisBehavior.class)
            .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS)
            .build();
  }

  public static void initAndesite() {
    if (BlockTypes.ANDESITE_TYPE != null) return;
    BlockTypes.ANDESITE_TYPE = BlockTypeBuilder
            .builder(BlockAndesiteBehavior.class)
            .vanillaBlock(VanillaBlockId.ANDESITE)
            .build();
  }

  public static void initAndesiteStairs() {
    if (BlockTypes.ANDESITE_STAIRS_TYPE != null) return;
    BlockTypes.ANDESITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockAndesiteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initAnvil() {
    if (BlockTypes.ANVIL_TYPE != null) return;
    BlockTypes.ANVIL_TYPE = BlockTypeBuilder
            .builder(BlockAnvilBehavior.class)
            .vanillaBlock(VanillaBlockId.ANVIL)
            .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initAzalea() {
    if (BlockTypes.AZALEA_TYPE != null) return;
    BlockTypes.AZALEA_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA)
            .build();
  }

  public static void initAzaleaLeaves() {
    if (BlockTypes.AZALEA_LEAVES_TYPE != null) return;
    BlockTypes.AZALEA_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initAzaleaLeavesFlowered() {
    if (BlockTypes.AZALEA_LEAVES_FLOWERED_TYPE != null) return;
    BlockTypes.AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesFloweredBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initBamboo() {
    if (BlockTypes.BAMBOO_TYPE != null) return;
    BlockTypes.BAMBOO_TYPE = BlockTypeBuilder
            .builder(BlockBambooBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
            .build();
  }

  public static void initBambooBlock() {
    if (BlockTypes.BAMBOO_BLOCK_TYPE != null) return;
    BlockTypes.BAMBOO_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBambooBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initBambooButton() {
    if (BlockTypes.BAMBOO_BUTTON_TYPE != null) return;
    BlockTypes.BAMBOO_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockBambooButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBambooDoor() {
    if (BlockTypes.BAMBOO_DOOR_TYPE != null) return;
    BlockTypes.BAMBOO_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockBambooDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initBambooDoubleSlab() {
    if (BlockTypes.BAMBOO_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.BAMBOO_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBambooFence() {
    if (BlockTypes.BAMBOO_FENCE_TYPE != null) return;
    BlockTypes.BAMBOO_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockBambooFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_FENCE)
            .build();
  }

  public static void initBambooFenceGate() {
    if (BlockTypes.BAMBOO_FENCE_GATE_TYPE != null) return;
    BlockTypes.BAMBOO_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockBambooFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initBambooHangingSign() {
    if (BlockTypes.BAMBOO_HANGING_SIGN_TYPE != null) return;
    BlockTypes.BAMBOO_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBambooHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initBambooMosaic() {
    if (BlockTypes.BAMBOO_MOSAIC_TYPE != null) return;
    BlockTypes.BAMBOO_MOSAIC_TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC)
            .build();
  }

  public static void initBambooMosaicDoubleSlab() {
    if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBambooMosaicSlab() {
    if (BlockTypes.BAMBOO_MOSAIC_SLAB_TYPE != null) return;
    BlockTypes.BAMBOO_MOSAIC_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBambooMosaicStairs() {
    if (BlockTypes.BAMBOO_MOSAIC_STAIRS_TYPE != null) return;
    BlockTypes.BAMBOO_MOSAIC_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initBambooPlanks() {
    if (BlockTypes.BAMBOO_PLANKS_TYPE != null) return;
    BlockTypes.BAMBOO_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockBambooPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PLANKS)
            .build();
  }

  public static void initBambooPressurePlate() {
    if (BlockTypes.BAMBOO_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockBambooPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initBambooSapling() {
    if (BlockTypes.BAMBOO_SAPLING_TYPE != null) return;
    BlockTypes.BAMBOO_SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockBambooSaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
            .build();
  }

  public static void initBambooSlab() {
    if (BlockTypes.BAMBOO_SLAB_TYPE != null) return;
    BlockTypes.BAMBOO_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBambooStairs() {
    if (BlockTypes.BAMBOO_STAIRS_TYPE != null) return;
    BlockTypes.BAMBOO_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBambooStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initBambooStandingSign() {
    if (BlockTypes.BAMBOO_STANDING_SIGN_TYPE != null) return;
    BlockTypes.BAMBOO_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBambooStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initBambooTrapdoor() {
    if (BlockTypes.BAMBOO_TRAPDOOR_TYPE != null) return;
    BlockTypes.BAMBOO_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockBambooTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initBambooWallSign() {
    if (BlockTypes.BAMBOO_WALL_SIGN_TYPE != null) return;
    BlockTypes.BAMBOO_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBambooWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBarrel() {
    if (BlockTypes.BARREL_TYPE != null) return;
    BlockTypes.BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initBarrier() {
    if (BlockTypes.BARRIER_TYPE != null) return;
    BlockTypes.BARRIER_TYPE = BlockTypeBuilder
            .builder(BlockBarrierBehavior.class)
            .vanillaBlock(VanillaBlockId.BARRIER)
            .build();
  }

  public static void initBasalt() {
    if (BlockTypes.BASALT_TYPE != null) return;
    BlockTypes.BASALT_TYPE = BlockTypeBuilder
            .builder(BlockBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.BASALT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initBeacon() {
    if (BlockTypes.BEACON_TYPE != null) return;
    BlockTypes.BEACON_TYPE = BlockTypeBuilder
            .builder(BlockBeaconBehavior.class)
            .vanillaBlock(VanillaBlockId.BEACON)
            .build();
  }

  public static void initBed() {
    if (BlockTypes.BED_TYPE != null) return;
    BlockTypes.BED_TYPE = BlockTypeBuilder
            .builder(BlockBedBehavior.class)
            .vanillaBlock(VanillaBlockId.BED)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HEAD_PIECE_BIT, VanillaBlockPropertyTypes.OCCUPIED_BIT)
            .build();
  }

  public static void initBedrock() {
    if (BlockTypes.BEDROCK_TYPE != null) return;
    BlockTypes.BEDROCK_TYPE = BlockTypeBuilder
            .builder(BlockBedrockBehavior.class)
            .vanillaBlock(VanillaBlockId.BEDROCK)
            .setProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
            .build();
  }

  public static void initBeeNest() {
    if (BlockTypes.BEE_NEST_TYPE != null) return;
    BlockTypes.BEE_NEST_TYPE = BlockTypeBuilder
            .builder(BlockBeeNestBehavior.class)
            .vanillaBlock(VanillaBlockId.BEE_NEST)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
            .build();
  }

  public static void initBeehive() {
    if (BlockTypes.BEEHIVE_TYPE != null) return;
    BlockTypes.BEEHIVE_TYPE = BlockTypeBuilder
            .builder(BlockBeehiveBehavior.class)
            .vanillaBlock(VanillaBlockId.BEEHIVE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
            .build();
  }

  public static void initBeetroot() {
    if (BlockTypes.BEETROOT_TYPE != null) return;
    BlockTypes.BEETROOT_TYPE = BlockTypeBuilder
            .builder(BlockBeetrootBehavior.class)
            .vanillaBlock(VanillaBlockId.BEETROOT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initBell() {
    if (BlockTypes.BELL_TYPE != null) return;
    BlockTypes.BELL_TYPE = BlockTypeBuilder
            .builder(BlockBellBehavior.class)
            .vanillaBlock(VanillaBlockId.BELL)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
  }

  public static void initBigDripleaf() {
    if (BlockTypes.BIG_DRIPLEAF_TYPE != null) return;
    BlockTypes.BIG_DRIPLEAF_TYPE = BlockTypeBuilder
            .builder(BlockBigDripleafBehavior.class)
            .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF)
            .setProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initBirchButton() {
    if (BlockTypes.BIRCH_BUTTON_TYPE != null) return;
    BlockTypes.BIRCH_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockBirchButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBirchDoor() {
    if (BlockTypes.BIRCH_DOOR_TYPE != null) return;
    BlockTypes.BIRCH_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockBirchDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initBirchFence() {
    if (BlockTypes.BIRCH_FENCE_TYPE != null) return;
    BlockTypes.BIRCH_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockBirchFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE)
            .build();
  }

  public static void initBirchFenceGate() {
    if (BlockTypes.BIRCH_FENCE_GATE_TYPE != null) return;
    BlockTypes.BIRCH_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockBirchFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initBirchHangingSign() {
    if (BlockTypes.BIRCH_HANGING_SIGN_TYPE != null) return;
    BlockTypes.BIRCH_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initBirchLog() {
    if (BlockTypes.BIRCH_LOG_TYPE != null) return;
    BlockTypes.BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initBirchPlanks() {
    if (BlockTypes.BIRCH_PLANKS_TYPE != null) return;
    BlockTypes.BIRCH_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockBirchPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_PLANKS)
            .build();
  }

  public static void initBirchPressurePlate() {
    if (BlockTypes.BIRCH_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.BIRCH_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockBirchPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initBirchStairs() {
    if (BlockTypes.BIRCH_STAIRS_TYPE != null) return;
    BlockTypes.BIRCH_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBirchStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initBirchStandingSign() {
    if (BlockTypes.BIRCH_STANDING_SIGN_TYPE != null) return;
    BlockTypes.BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initBirchTrapdoor() {
    if (BlockTypes.BIRCH_TRAPDOOR_TYPE != null) return;
    BlockTypes.BIRCH_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockBirchTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initBirchWallSign() {
    if (BlockTypes.BIRCH_WALL_SIGN_TYPE != null) return;
    BlockTypes.BIRCH_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBlackCandle() {
    if (BlockTypes.BLACK_CANDLE_TYPE != null) return;
    BlockTypes.BLACK_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBlackCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBlackCandleCake() {
    if (BlockTypes.BLACK_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.BLACK_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBlackCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBlackCarpet() {
    if (BlockTypes.BLACK_CARPET_TYPE != null) return;
    BlockTypes.BLACK_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBlackCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CARPET)
            .build();
  }

  public static void initBlackConcrete() {
    if (BlockTypes.BLACK_CONCRETE_TYPE != null) return;
    BlockTypes.BLACK_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlackConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CONCRETE)
            .build();
  }

  public static void initBlackConcretePowder() {
    if (BlockTypes.BLACK_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.BLACK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBlackConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CONCRETE_POWDER)
            .build();
  }

  public static void initBlackGlazedTerracotta() {
    if (BlockTypes.BLACK_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.BLACK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlackGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBlackShulkerBox() {
    if (BlockTypes.BLACK_SHULKER_BOX_TYPE != null) return;
    BlockTypes.BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBlackShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
            .build();
  }

  public static void initBlackStainedGlass() {
    if (BlockTypes.BLACK_STAINED_GLASS_TYPE != null) return;
    BlockTypes.BLACK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBlackStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_STAINED_GLASS)
            .build();
  }

  public static void initBlackStainedGlassPane() {
    if (BlockTypes.BLACK_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.BLACK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockBlackStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_STAINED_GLASS_PANE)
            .build();
  }

  public static void initBlackTerracotta() {
    if (BlockTypes.BLACK_TERRACOTTA_TYPE != null) return;
    BlockTypes.BLACK_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlackTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_TERRACOTTA)
            .build();
  }

  public static void initBlackWool() {
    if (BlockTypes.BLACK_WOOL_TYPE != null) return;
    BlockTypes.BLACK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlackWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_WOOL)
            .build();
  }

  public static void initBlackstone() {
    if (BlockTypes.BLACKSTONE_TYPE != null) return;
    BlockTypes.BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE)
            .build();
  }

  public static void initBlackstoneDoubleSlab() {
    if (BlockTypes.BLACKSTONE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBlackstoneSlab() {
    if (BlockTypes.BLACKSTONE_SLAB_TYPE != null) return;
    BlockTypes.BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initBlackstoneStairs() {
    if (BlockTypes.BLACKSTONE_STAIRS_TYPE != null) return;
    BlockTypes.BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initBlackstoneWall() {
    if (BlockTypes.BLACKSTONE_WALL_TYPE != null) return;
    BlockTypes.BLACKSTONE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneWallBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initBlastFurnace() {
    if (BlockTypes.BLAST_FURNACE_TYPE != null) return;
    BlockTypes.BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initBlueCandle() {
    if (BlockTypes.BLUE_CANDLE_TYPE != null) return;
    BlockTypes.BLUE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBlueCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBlueCandleCake() {
    if (BlockTypes.BLUE_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBlueCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBlueCarpet() {
    if (BlockTypes.BLUE_CARPET_TYPE != null) return;
    BlockTypes.BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CARPET)
            .build();
  }

  public static void initBlueConcrete() {
    if (BlockTypes.BLUE_CONCRETE_TYPE != null) return;
    BlockTypes.BLUE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE)
            .build();
  }

  public static void initBlueConcretePowder() {
    if (BlockTypes.BLUE_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE_POWDER)
            .build();
  }

  public static void initBlueGlazedTerracotta() {
    if (BlockTypes.BLUE_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlueGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBlueIce() {
    if (BlockTypes.BLUE_ICE_TYPE != null) return;
    BlockTypes.BLUE_ICE_TYPE = BlockTypeBuilder
            .builder(BlockBlueIceBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_ICE)
            .build();
  }

  public static void initBlueShulkerBox() {
    if (BlockTypes.BLUE_SHULKER_BOX_TYPE != null) return;
    BlockTypes.BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBlueShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
            .build();
  }

  public static void initBlueStainedGlass() {
    if (BlockTypes.BLUE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_STAINED_GLASS)
            .build();
  }

  public static void initBlueStainedGlassPane() {
    if (BlockTypes.BLUE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initBlueTerracotta() {
    if (BlockTypes.BLUE_TERRACOTTA_TYPE != null) return;
    BlockTypes.BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlueTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_TERRACOTTA)
            .build();
  }

  public static void initBlueWool() {
    if (BlockTypes.BLUE_WOOL_TYPE != null) return;
    BlockTypes.BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_WOOL)
            .build();
  }

  public static void initBoneBlock() {
    if (BlockTypes.BONE_BLOCK_TYPE != null) return;
    BlockTypes.BONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BONE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initBookshelf() {
    if (BlockTypes.BOOKSHELF_TYPE != null) return;
    BlockTypes.BOOKSHELF_TYPE = BlockTypeBuilder
            .builder(BlockBookshelfBehavior.class)
            .vanillaBlock(VanillaBlockId.BOOKSHELF)
            .build();
  }

  public static void initBorderBlock() {
    if (BlockTypes.BORDER_BLOCK_TYPE != null) return;
    BlockTypes.BORDER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBorderBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BORDER_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initBrainCoral() {
    if (BlockTypes.BRAIN_CORAL_TYPE != null) return;
    BlockTypes.BRAIN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockBrainCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.BRAIN_CORAL)
            .build();
  }

  public static void initBrewingStand() {
    if (BlockTypes.BREWING_STAND_TYPE != null) return;
    BlockTypes.BREWING_STAND_TYPE = BlockTypeBuilder
            .builder(BlockBrewingStandBehavior.class)
            .vanillaBlock(VanillaBlockId.BREWING_STAND)
            .setProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
            .build();
  }

  public static void initBrickBlock() {
    if (BlockTypes.BRICK_BLOCK_TYPE != null) return;
    BlockTypes.BRICK_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBrickBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BRICK_BLOCK)
            .build();
  }

  public static void initBrickStairs() {
    if (BlockTypes.BRICK_STAIRS_TYPE != null) return;
    BlockTypes.BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initBrownCandle() {
    if (BlockTypes.BROWN_CANDLE_TYPE != null) return;
    BlockTypes.BROWN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBrownCandleCake() {
    if (BlockTypes.BROWN_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.BROWN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initBrownCarpet() {
    if (BlockTypes.BROWN_CARPET_TYPE != null) return;
    BlockTypes.BROWN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBrownCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CARPET)
            .build();
  }

  public static void initBrownConcrete() {
    if (BlockTypes.BROWN_CONCRETE_TYPE != null) return;
    BlockTypes.BROWN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBrownConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CONCRETE)
            .build();
  }

  public static void initBrownConcretePowder() {
    if (BlockTypes.BROWN_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.BROWN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBrownConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CONCRETE_POWDER)
            .build();
  }

  public static void initBrownGlazedTerracotta() {
    if (BlockTypes.BROWN_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.BROWN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBrownGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initBrownMushroom() {
    if (BlockTypes.BROWN_MUSHROOM_TYPE != null) return;
    BlockTypes.BROWN_MUSHROOM_TYPE = BlockTypeBuilder
            .builder(BlockBrownMushroomBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM)
            .build();
  }

  public static void initBrownMushroomBlock() {
    if (BlockTypes.BROWN_MUSHROOM_BLOCK_TYPE != null) return;
    BlockTypes.BROWN_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBrownMushroomBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .build();
  }

  public static void initBrownShulkerBox() {
    if (BlockTypes.BROWN_SHULKER_BOX_TYPE != null) return;
    BlockTypes.BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBrownShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
            .build();
  }

  public static void initBrownStainedGlass() {
    if (BlockTypes.BROWN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.BROWN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBrownStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_STAINED_GLASS)
            .build();
  }

  public static void initBrownStainedGlassPane() {
    if (BlockTypes.BROWN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.BROWN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockBrownStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initBrownTerracotta() {
    if (BlockTypes.BROWN_TERRACOTTA_TYPE != null) return;
    BlockTypes.BROWN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBrownTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_TERRACOTTA)
            .build();
  }

  public static void initBrownWool() {
    if (BlockTypes.BROWN_WOOL_TYPE != null) return;
    BlockTypes.BROWN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBrownWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_WOOL)
            .build();
  }

  public static void initBubbleColumn() {
    if (BlockTypes.BUBBLE_COLUMN_TYPE != null) return;
    BlockTypes.BUBBLE_COLUMN_TYPE = BlockTypeBuilder
            .builder(BlockBubbleColumnBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN)
            .setProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
            .build();
  }

  public static void initBubbleCoral() {
    if (BlockTypes.BUBBLE_CORAL_TYPE != null) return;
    BlockTypes.BUBBLE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockBubbleCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_CORAL)
            .build();
  }

  public static void initBuddingAmethyst() {
    if (BlockTypes.BUDDING_AMETHYST_TYPE != null) return;
    BlockTypes.BUDDING_AMETHYST_TYPE = BlockTypeBuilder
            .builder(BlockBuddingAmethystBehavior.class)
            .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST)
            .build();
  }

  public static void initCactus() {
    if (BlockTypes.CACTUS_TYPE != null) return;
    BlockTypes.CACTUS_TYPE = BlockTypeBuilder
            .builder(BlockCactusBehavior.class)
            .vanillaBlock(VanillaBlockId.CACTUS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }

  public static void initCake() {
    if (BlockTypes.CAKE_TYPE != null) return;
    BlockTypes.CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CAKE)
            .setProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
            .build();
  }

  public static void initCalcite() {
    if (BlockTypes.CALCITE_TYPE != null) return;
    BlockTypes.CALCITE_TYPE = BlockTypeBuilder
            .builder(BlockCalciteBehavior.class)
            .vanillaBlock(VanillaBlockId.CALCITE)
            .build();
  }

  public static void initCalibratedSculkSensor() {
    if (BlockTypes.CALIBRATED_SCULK_SENSOR_TYPE != null) return;
    BlockTypes.CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
            .builder(BlockCalibratedSculkSensorBehavior.class)
            .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
            .build();
  }

  public static void initCamera() {
    if (BlockTypes.CAMERA_TYPE != null) return;
    BlockTypes.CAMERA_TYPE = BlockTypeBuilder
            .builder(BlockCameraBehavior.class)
            .vanillaBlock(VanillaBlockId.CAMERA)
            .build();
  }

  public static void initCampfire() {
    if (BlockTypes.CAMPFIRE_TYPE != null) return;
    BlockTypes.CAMPFIRE_TYPE = BlockTypeBuilder
            .builder(BlockCampfireBehavior.class)
            .vanillaBlock(VanillaBlockId.CAMPFIRE)
            .setProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initCandle() {
    if (BlockTypes.CANDLE_TYPE != null) return;
    BlockTypes.CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initCandleCake() {
    if (BlockTypes.CANDLE_CAKE_TYPE != null) return;
    BlockTypes.CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initCarrots() {
    if (BlockTypes.CARROTS_TYPE != null) return;
    BlockTypes.CARROTS_TYPE = BlockTypeBuilder
            .builder(BlockCarrotsBehavior.class)
            .vanillaBlock(VanillaBlockId.CARROTS)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initCartographyTable() {
    if (BlockTypes.CARTOGRAPHY_TABLE_TYPE != null) return;
    BlockTypes.CARTOGRAPHY_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockCartographyTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE)
            .build();
  }

  public static void initCarvedPumpkin() {
    if (BlockTypes.CARVED_PUMPKIN_TYPE != null) return;
    BlockTypes.CARVED_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockCarvedPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initCauldron() {
    if (BlockTypes.CAULDRON_TYPE != null) return;
    BlockTypes.CAULDRON_TYPE = BlockTypeBuilder
            .builder(BlockCauldronBehavior.class)
            .vanillaBlock(VanillaBlockId.CAULDRON)
            .setProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
            .build();
  }

  public static void initCaveVines() {
    if (BlockTypes.CAVE_VINES_TYPE != null) return;
    BlockTypes.CAVE_VINES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }

  public static void initCaveVinesBodyWithBerries() {
    if (BlockTypes.CAVE_VINES_BODY_WITH_BERRIES_TYPE != null) return;
    BlockTypes.CAVE_VINES_BODY_WITH_BERRIES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }

  public static void initCaveVinesHeadWithBerries() {
    if (BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES_TYPE != null) return;
    BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }

  public static void initChain() {
    if (BlockTypes.CHAIN_TYPE != null) return;
    BlockTypes.CHAIN_TYPE = BlockTypeBuilder
            .builder(BlockChainBehavior.class)
            .vanillaBlock(VanillaBlockId.CHAIN)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initChainCommandBlock() {
    if (BlockTypes.CHAIN_COMMAND_BLOCK_TYPE != null) return;
    BlockTypes.CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockChainCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initChemicalHeat() {
    if (BlockTypes.CHEMICAL_HEAT_TYPE != null) return;
    BlockTypes.CHEMICAL_HEAT_TYPE = BlockTypeBuilder
            .builder(BlockChemicalHeatBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT)
            .build();
  }

  public static void initChemistryTable() {
    if (BlockTypes.CHEMISTRY_TABLE_TYPE != null) return;
    BlockTypes.CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockChemistryTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE)
            .setProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initCherryButton() {
    if (BlockTypes.CHERRY_BUTTON_TYPE != null) return;
    BlockTypes.CHERRY_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockCherryButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initCherryDoor() {
    if (BlockTypes.CHERRY_DOOR_TYPE != null) return;
    BlockTypes.CHERRY_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockCherryDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initCherryDoubleSlab() {
    if (BlockTypes.CHERRY_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.CHERRY_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCherryDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCherryFence() {
    if (BlockTypes.CHERRY_FENCE_TYPE != null) return;
    BlockTypes.CHERRY_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockCherryFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_FENCE)
            .build();
  }

  public static void initCherryFenceGate() {
    if (BlockTypes.CHERRY_FENCE_GATE_TYPE != null) return;
    BlockTypes.CHERRY_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockCherryFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initCherryHangingSign() {
    if (BlockTypes.CHERRY_HANGING_SIGN_TYPE != null) return;
    BlockTypes.CHERRY_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCherryHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initCherryLeaves() {
    if (BlockTypes.CHERRY_LEAVES_TYPE != null) return;
    BlockTypes.CHERRY_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockCherryLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initCherryLog() {
    if (BlockTypes.CHERRY_LOG_TYPE != null) return;
    BlockTypes.CHERRY_LOG_TYPE = BlockTypeBuilder
            .builder(BlockCherryLogBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initCherryPlanks() {
    if (BlockTypes.CHERRY_PLANKS_TYPE != null) return;
    BlockTypes.CHERRY_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockCherryPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PLANKS)
            .build();
  }

  public static void initCherryPressurePlate() {
    if (BlockTypes.CHERRY_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.CHERRY_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockCherryPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initCherrySapling() {
    if (BlockTypes.CHERRY_SAPLING_TYPE != null) return;
    BlockTypes.CHERRY_SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockCherrySaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT)
            .build();
  }

  public static void initCherrySlab() {
    if (BlockTypes.CHERRY_SLAB_TYPE != null) return;
    BlockTypes.CHERRY_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCherrySlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCherryStairs() {
    if (BlockTypes.CHERRY_STAIRS_TYPE != null) return;
    BlockTypes.CHERRY_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockCherryStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initCherryStandingSign() {
    if (BlockTypes.CHERRY_STANDING_SIGN_TYPE != null) return;
    BlockTypes.CHERRY_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCherryStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initCherryTrapdoor() {
    if (BlockTypes.CHERRY_TRAPDOOR_TYPE != null) return;
    BlockTypes.CHERRY_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockCherryTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initCherryWallSign() {
    if (BlockTypes.CHERRY_WALL_SIGN_TYPE != null) return;
    BlockTypes.CHERRY_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCherryWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initCherryWood() {
    if (BlockTypes.CHERRY_WOOD_TYPE != null) return;
    BlockTypes.CHERRY_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockCherryWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
  }

  public static void initChest() {
    if (BlockTypes.CHEST_TYPE != null) return;
    BlockTypes.CHEST_TYPE = BlockTypeBuilder
            .builder(BlockChestBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initChiseledBookshelf() {
    if (BlockTypes.CHISELED_BOOKSHELF_TYPE != null) return;
    BlockTypes.CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
            .builder(BlockChiseledBookshelfBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF)
            .setProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initChiseledCopper() {
    if (BlockTypes.CHISELED_COPPER_TYPE != null) return;
    BlockTypes.CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_COPPER)
            .build();
  }

  public static void initChiseledDeepslate() {
    if (BlockTypes.CHISELED_DEEPSLATE_TYPE != null) return;
    BlockTypes.CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockChiseledDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE)
            .build();
  }

  public static void initChiseledNetherBricks() {
    if (BlockTypes.CHISELED_NETHER_BRICKS_TYPE != null) return;
    BlockTypes.CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockChiseledNetherBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS)
            .build();
  }

  public static void initChiseledPolishedBlackstone() {
    if (BlockTypes.CHISELED_POLISHED_BLACKSTONE_TYPE != null) return;
    BlockTypes.CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockChiseledPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE)
            .build();
  }

  public static void initChiseledTuff() {
    if (BlockTypes.CHISELED_TUFF_TYPE != null) return;
    BlockTypes.CHISELED_TUFF_TYPE = BlockTypeBuilder
            .builder(BlockChiseledTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_TUFF)
            .build();
  }

  public static void initChiseledTuffBricks() {
    if (BlockTypes.CHISELED_TUFF_BRICKS_TYPE != null) return;
    BlockTypes.CHISELED_TUFF_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockChiseledTuffBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_TUFF_BRICKS)
            .build();
  }

  public static void initChorusFlower() {
    if (BlockTypes.CHORUS_FLOWER_TYPE != null) return;
    BlockTypes.CHORUS_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockChorusFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.CHORUS_FLOWER)
            .setProperties(VanillaBlockPropertyTypes.AGE_6)
            .build();
  }

  public static void initChorusPlant() {
    if (BlockTypes.CHORUS_PLANT_TYPE != null) return;
    BlockTypes.CHORUS_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockChorusPlantBehavior.class)
            .vanillaBlock(VanillaBlockId.CHORUS_PLANT)
            .build();
  }

  public static void initClay() {
    if (BlockTypes.CLAY_TYPE != null) return;
    BlockTypes.CLAY_TYPE = BlockTypeBuilder
            .builder(BlockClayBehavior.class)
            .vanillaBlock(VanillaBlockId.CLAY)
            .build();
  }

  public static void initClientRequestPlaceholderBlock() {
    if (BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE != null) return;
    BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockClientRequestPlaceholderBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
            .build();
  }

  public static void initCoalBlock() {
    if (BlockTypes.COAL_BLOCK_TYPE != null) return;
    BlockTypes.COAL_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCoalBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COAL_BLOCK)
            .build();
  }

  public static void initCoalOre() {
    if (BlockTypes.COAL_ORE_TYPE != null) return;
    BlockTypes.COAL_ORE_TYPE = BlockTypeBuilder
            .builder(BlockCoalOreBehavior.class)
            .vanillaBlock(VanillaBlockId.COAL_ORE)
            .build();
  }

  public static void initCobbledDeepslate() {
    if (BlockTypes.COBBLED_DEEPSLATE_TYPE != null) return;
    BlockTypes.COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE)
            .build();
  }

  public static void initCobbledDeepslateDoubleSlab() {
    if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCobbledDeepslateSlab() {
    if (BlockTypes.COBBLED_DEEPSLATE_SLAB_TYPE != null) return;
    BlockTypes.COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCobbledDeepslateStairs() {
    if (BlockTypes.COBBLED_DEEPSLATE_STAIRS_TYPE != null) return;
    BlockTypes.COBBLED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initCobbledDeepslateWall() {
    if (BlockTypes.COBBLED_DEEPSLATE_WALL_TYPE != null) return;
    BlockTypes.COBBLED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateWallBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initCobblestone() {
    if (BlockTypes.COBBLESTONE_TYPE != null) return;
    BlockTypes.COBBLESTONE_TYPE = BlockTypeBuilder
            .builder(BlockCobblestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLESTONE)
            .build();
  }

  public static void initCobblestoneWall() {
    if (BlockTypes.COBBLESTONE_WALL_TYPE != null) return;
    BlockTypes.COBBLESTONE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockCobblestoneWallBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_BLOCK_TYPE, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initCocoa() {
    if (BlockTypes.COCOA_TYPE != null) return;
    BlockTypes.COCOA_TYPE = BlockTypeBuilder
            .builder(BlockCocoaBehavior.class)
            .vanillaBlock(VanillaBlockId.COCOA)
            .setProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initColoredTorchBp() {
    if (BlockTypes.COLORED_TORCH_BP_TYPE != null) return;
    BlockTypes.COLORED_TORCH_BP_TYPE = BlockTypeBuilder
            .builder(BlockColoredTorchBpBehavior.class)
            .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
            .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initColoredTorchRg() {
    if (BlockTypes.COLORED_TORCH_RG_TYPE != null) return;
    BlockTypes.COLORED_TORCH_RG_TYPE = BlockTypeBuilder
            .builder(BlockColoredTorchRgBehavior.class)
            .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
            .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initCommandBlock() {
    if (BlockTypes.COMMAND_BLOCK_TYPE != null) return;
    BlockTypes.COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initComposter() {
    if (BlockTypes.COMPOSTER_TYPE != null) return;
    BlockTypes.COMPOSTER_TYPE = BlockTypeBuilder
            .builder(BlockComposterBehavior.class)
            .vanillaBlock(VanillaBlockId.COMPOSTER)
            .setProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
            .build();
  }

  public static void initConduit() {
    if (BlockTypes.CONDUIT_TYPE != null) return;
    BlockTypes.CONDUIT_TYPE = BlockTypeBuilder
            .builder(BlockConduitBehavior.class)
            .vanillaBlock(VanillaBlockId.CONDUIT)
            .build();
  }

  public static void initCopperBlock() {
    if (BlockTypes.COPPER_BLOCK_TYPE != null) return;
    BlockTypes.COPPER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCopperBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_BLOCK)
            .build();
  }

  public static void initCopperBulb() {
    if (BlockTypes.COPPER_BULB_TYPE != null) return;
    BlockTypes.COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initCopperDoor() {
    if (BlockTypes.COPPER_DOOR_TYPE != null) return;
    BlockTypes.COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initCopperGrate() {
    if (BlockTypes.COPPER_GRATE_TYPE != null) return;
    BlockTypes.COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_GRATE)
            .build();
  }

  public static void initCopperOre() {
    if (BlockTypes.COPPER_ORE_TYPE != null) return;
    BlockTypes.COPPER_ORE_TYPE = BlockTypeBuilder
            .builder(BlockCopperOreBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_ORE)
            .build();
  }

  public static void initCopperTrapdoor() {
    if (BlockTypes.COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initCoralBlock() {
    if (BlockTypes.CORAL_BLOCK_TYPE != null) return;
    BlockTypes.CORAL_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCoralBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }

  public static void initCoralFan() {
    if (BlockTypes.CORAL_FAN_TYPE != null) return;
    BlockTypes.CORAL_FAN_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .build();
  }

  public static void initCoralFanDead() {
    if (BlockTypes.CORAL_FAN_DEAD_TYPE != null) return;
    BlockTypes.CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanDeadBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .build();
  }

  public static void initCoralFanHang() {
    if (BlockTypes.CORAL_FAN_HANG_TYPE != null) return;
    BlockTypes.CORAL_FAN_HANG_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHangBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }

  public static void initCoralFanHang2() {
    if (BlockTypes.CORAL_FAN_HANG2_TYPE != null) return;
    BlockTypes.CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang2Behavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }

  public static void initCoralFanHang3() {
    if (BlockTypes.CORAL_FAN_HANG3_TYPE != null) return;
    BlockTypes.CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang3Behavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }

  public static void initCrackedDeepslateBricks() {
    if (BlockTypes.CRACKED_DEEPSLATE_BRICKS_TYPE != null) return;
    BlockTypes.CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS)
            .build();
  }

  public static void initCrackedDeepslateTiles() {
    if (BlockTypes.CRACKED_DEEPSLATE_TILES_TYPE != null) return;
    BlockTypes.CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES)
            .build();
  }

  public static void initCrackedNetherBricks() {
    if (BlockTypes.CRACKED_NETHER_BRICKS_TYPE != null) return;
    BlockTypes.CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedNetherBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS)
            .build();
  }

  public static void initCrackedPolishedBlackstoneBricks() {
    if (BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE != null) return;
    BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .build();
  }

  public static void initCrafter() {
    if (BlockTypes.CRAFTER_TYPE != null) return;
    BlockTypes.CRAFTER_TYPE = BlockTypeBuilder
            .builder(BlockCrafterBehavior.class)
            .vanillaBlock(VanillaBlockId.CRAFTER)
            .setProperties(VanillaBlockPropertyTypes.CRAFTING, VanillaBlockPropertyTypes.ORIENTATION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }

  public static void initCraftingTable() {
    if (BlockTypes.CRAFTING_TABLE_TYPE != null) return;
    BlockTypes.CRAFTING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockCraftingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
            .build();
  }

  public static void initCrimsonButton() {
    if (BlockTypes.CRIMSON_BUTTON_TYPE != null) return;
    BlockTypes.CRIMSON_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initCrimsonDoor() {
    if (BlockTypes.CRIMSON_DOOR_TYPE != null) return;
    BlockTypes.CRIMSON_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initCrimsonDoubleSlab() {
    if (BlockTypes.CRIMSON_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.CRIMSON_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCrimsonFence() {
    if (BlockTypes.CRIMSON_FENCE_TYPE != null) return;
    BlockTypes.CRIMSON_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE)
            .build();
  }

  public static void initCrimsonFenceGate() {
    if (BlockTypes.CRIMSON_FENCE_GATE_TYPE != null) return;
    BlockTypes.CRIMSON_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initCrimsonFungus() {
    if (BlockTypes.CRIMSON_FUNGUS_TYPE != null) return;
    BlockTypes.CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFungusBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS)
            .build();
  }

  public static void initCrimsonHangingSign() {
    if (BlockTypes.CRIMSON_HANGING_SIGN_TYPE != null) return;
    BlockTypes.CRIMSON_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initCrimsonHyphae() {
    if (BlockTypes.CRIMSON_HYPHAE_TYPE != null) return;
    BlockTypes.CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initCrimsonNylium() {
    if (BlockTypes.CRIMSON_NYLIUM_TYPE != null) return;
    BlockTypes.CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonNyliumBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM)
            .build();
  }

  public static void initCrimsonPlanks() {
    if (BlockTypes.CRIMSON_PLANKS_TYPE != null) return;
    BlockTypes.CRIMSON_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS)
            .build();
  }

  public static void initCrimsonPressurePlate() {
    if (BlockTypes.CRIMSON_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.CRIMSON_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initCrimsonRoots() {
    if (BlockTypes.CRIMSON_ROOTS_TYPE != null) return;
    BlockTypes.CRIMSON_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_ROOTS)
            .build();
  }

  public static void initCrimsonSlab() {
    if (BlockTypes.CRIMSON_SLAB_TYPE != null) return;
    BlockTypes.CRIMSON_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCrimsonStairs() {
    if (BlockTypes.CRIMSON_STAIRS_TYPE != null) return;
    BlockTypes.CRIMSON_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initCrimsonStandingSign() {
    if (BlockTypes.CRIMSON_STANDING_SIGN_TYPE != null) return;
    BlockTypes.CRIMSON_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initCrimsonStem() {
    if (BlockTypes.CRIMSON_STEM_TYPE != null) return;
    BlockTypes.CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initCrimsonTrapdoor() {
    if (BlockTypes.CRIMSON_TRAPDOOR_TYPE != null) return;
    BlockTypes.CRIMSON_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initCrimsonWallSign() {
    if (BlockTypes.CRIMSON_WALL_SIGN_TYPE != null) return;
    BlockTypes.CRIMSON_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initCryingObsidian() {
    if (BlockTypes.CRYING_OBSIDIAN_TYPE != null) return;
    BlockTypes.CRYING_OBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockCryingObsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN)
            .build();
  }

  public static void initCutCopper() {
    if (BlockTypes.CUT_COPPER_TYPE != null) return;
    BlockTypes.CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER)
            .build();
  }

  public static void initCutCopperSlab() {
    if (BlockTypes.CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initCutCopperStairs() {
    if (BlockTypes.CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initCyanCandle() {
    if (BlockTypes.CYAN_CANDLE_TYPE != null) return;
    BlockTypes.CYAN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockCyanCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initCyanCandleCake() {
    if (BlockTypes.CYAN_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCyanCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initCyanCarpet() {
    if (BlockTypes.CYAN_CARPET_TYPE != null) return;
    BlockTypes.CYAN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockCyanCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CARPET)
            .build();
  }

  public static void initCyanConcrete() {
    if (BlockTypes.CYAN_CONCRETE_TYPE != null) return;
    BlockTypes.CYAN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockCyanConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CONCRETE)
            .build();
  }

  public static void initCyanConcretePowder() {
    if (BlockTypes.CYAN_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.CYAN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockCyanConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CONCRETE_POWDER)
            .build();
  }

  public static void initCyanGlazedTerracotta() {
    if (BlockTypes.CYAN_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.CYAN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockCyanGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initCyanShulkerBox() {
    if (BlockTypes.CYAN_SHULKER_BOX_TYPE != null) return;
    BlockTypes.CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockCyanShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
            .build();
  }

  public static void initCyanStainedGlass() {
    if (BlockTypes.CYAN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.CYAN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockCyanStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_STAINED_GLASS)
            .build();
  }

  public static void initCyanStainedGlassPane() {
    if (BlockTypes.CYAN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.CYAN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockCyanStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initCyanTerracotta() {
    if (BlockTypes.CYAN_TERRACOTTA_TYPE != null) return;
    BlockTypes.CYAN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockCyanTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_TERRACOTTA)
            .build();
  }

  public static void initCyanWool() {
    if (BlockTypes.CYAN_WOOL_TYPE != null) return;
    BlockTypes.CYAN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockCyanWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_WOOL)
            .build();
  }

  public static void initDarkOakButton() {
    if (BlockTypes.DARK_OAK_BUTTON_TYPE != null) return;
    BlockTypes.DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initDarkOakDoor() {
    if (BlockTypes.DARK_OAK_DOOR_TYPE != null) return;
    BlockTypes.DARK_OAK_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initDarkOakFence() {
    if (BlockTypes.DARK_OAK_FENCE_TYPE != null) return;
    BlockTypes.DARK_OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE)
            .build();
  }

  public static void initDarkOakFenceGate() {
    if (BlockTypes.DARK_OAK_FENCE_GATE_TYPE != null) return;
    BlockTypes.DARK_OAK_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initDarkOakHangingSign() {
    if (BlockTypes.DARK_OAK_HANGING_SIGN_TYPE != null) return;
    BlockTypes.DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initDarkOakLog() {
    if (BlockTypes.DARK_OAK_LOG_TYPE != null) return;
    BlockTypes.DARK_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initDarkOakPlanks() {
    if (BlockTypes.DARK_OAK_PLANKS_TYPE != null) return;
    BlockTypes.DARK_OAK_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_PLANKS)
            .build();
  }

  public static void initDarkOakPressurePlate() {
    if (BlockTypes.DARK_OAK_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initDarkOakStairs() {
    if (BlockTypes.DARK_OAK_STAIRS_TYPE != null) return;
    BlockTypes.DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initDarkOakTrapdoor() {
    if (BlockTypes.DARK_OAK_TRAPDOOR_TYPE != null) return;
    BlockTypes.DARK_OAK_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initDarkPrismarineStairs() {
    if (BlockTypes.DARK_PRISMARINE_STAIRS_TYPE != null) return;
    BlockTypes.DARK_PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDarkPrismarineStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initDarkoakStandingSign() {
    if (BlockTypes.DARKOAK_STANDING_SIGN_TYPE != null) return;
    BlockTypes.DARKOAK_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockDarkoakStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initDarkoakWallSign() {
    if (BlockTypes.DARKOAK_WALL_SIGN_TYPE != null) return;
    BlockTypes.DARKOAK_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockDarkoakWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.DARKOAK_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initDaylightDetector() {
    if (BlockTypes.DAYLIGHT_DETECTOR_TYPE != null) return;
    BlockTypes.DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorBehavior.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initDaylightDetectorInverted() {
    if (BlockTypes.DAYLIGHT_DETECTOR_INVERTED_TYPE != null) return;
    BlockTypes.DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorInvertedBehavior.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initDeadBrainCoral() {
    if (BlockTypes.DEAD_BRAIN_CORAL_TYPE != null) return;
    BlockTypes.DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadBrainCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL)
            .build();
  }

  public static void initDeadBubbleCoral() {
    if (BlockTypes.DEAD_BUBBLE_CORAL_TYPE != null) return;
    BlockTypes.DEAD_BUBBLE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadBubbleCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL)
            .build();
  }

  public static void initDeadFireCoral() {
    if (BlockTypes.DEAD_FIRE_CORAL_TYPE != null) return;
    BlockTypes.DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadFireCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL)
            .build();
  }

  public static void initDeadHornCoral() {
    if (BlockTypes.DEAD_HORN_CORAL_TYPE != null) return;
    BlockTypes.DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadHornCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL)
            .build();
  }

  public static void initDeadTubeCoral() {
    if (BlockTypes.DEAD_TUBE_CORAL_TYPE != null) return;
    BlockTypes.DEAD_TUBE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadTubeCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_TUBE_CORAL)
            .build();
  }

  public static void initDeadbush() {
    if (BlockTypes.DEADBUSH_TYPE != null) return;
    BlockTypes.DEADBUSH_TYPE = BlockTypeBuilder
            .builder(BlockDeadbushBehavior.class)
            .vanillaBlock(VanillaBlockId.DEADBUSH)
            .build();
  }

  public static void initDecoratedPot() {
    if (BlockTypes.DECORATED_POT_TYPE != null) return;
    BlockTypes.DECORATED_POT_TYPE = BlockTypeBuilder
            .builder(BlockDecoratedPotBehavior.class)
            .vanillaBlock(VanillaBlockId.DECORATED_POT)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initDeepslate() {
    if (BlockTypes.DEEPSLATE_TYPE != null) return;
    BlockTypes.DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initDeepslateBrickDoubleSlab() {
    if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initDeepslateBrickSlab() {
    if (BlockTypes.DEEPSLATE_BRICK_SLAB_TYPE != null) return;
    BlockTypes.DEEPSLATE_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initDeepslateBrickStairs() {
    if (BlockTypes.DEEPSLATE_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.DEEPSLATE_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initDeepslateBrickWall() {
    if (BlockTypes.DEEPSLATE_BRICK_WALL_TYPE != null) return;
    BlockTypes.DEEPSLATE_BRICK_WALL_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickWallBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initDeepslateBricks() {
    if (BlockTypes.DEEPSLATE_BRICKS_TYPE != null) return;
    BlockTypes.DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS)
            .build();
  }

  public static void initDeepslateCoalOre() {
    if (BlockTypes.DEEPSLATE_COAL_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCoalOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE)
            .build();
  }

  public static void initDeepslateCopperOre() {
    if (BlockTypes.DEEPSLATE_COPPER_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCopperOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE)
            .build();
  }

  public static void initDeepslateDiamondOre() {
    if (BlockTypes.DEEPSLATE_DIAMOND_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateDiamondOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE)
            .build();
  }

  public static void initDeepslateEmeraldOre() {
    if (BlockTypes.DEEPSLATE_EMERALD_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateEmeraldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE)
            .build();
  }

  public static void initDeepslateGoldOre() {
    if (BlockTypes.DEEPSLATE_GOLD_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
            .build();
  }

  public static void initDeepslateIronOre() {
    if (BlockTypes.DEEPSLATE_IRON_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateIronOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE)
            .build();
  }

  public static void initDeepslateLapisOre() {
    if (BlockTypes.DEEPSLATE_LAPIS_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_LAPIS_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateLapisOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_LAPIS_ORE)
            .build();
  }

  public static void initDeepslateRedstoneOre() {
    if (BlockTypes.DEEPSLATE_REDSTONE_ORE_TYPE != null) return;
    BlockTypes.DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE)
            .build();
  }

  public static void initDeepslateTileDoubleSlab() {
    if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTileDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initDeepslateTileSlab() {
    if (BlockTypes.DEEPSLATE_TILE_SLAB_TYPE != null) return;
    BlockTypes.DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTileSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initDeepslateTileStairs() {
    if (BlockTypes.DEEPSLATE_TILE_STAIRS_TYPE != null) return;
    BlockTypes.DEEPSLATE_TILE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTileStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initDeepslateTileWall() {
    if (BlockTypes.DEEPSLATE_TILE_WALL_TYPE != null) return;
    BlockTypes.DEEPSLATE_TILE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTileWallBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initDeepslateTiles() {
    if (BlockTypes.DEEPSLATE_TILES_TYPE != null) return;
    BlockTypes.DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES)
            .build();
  }

  public static void initDeny() {
    if (BlockTypes.DENY_TYPE != null) return;
    BlockTypes.DENY_TYPE = BlockTypeBuilder
            .builder(BlockDenyBehavior.class)
            .vanillaBlock(VanillaBlockId.DENY)
            .build();
  }

  public static void initDetectorRail() {
    if (BlockTypes.DETECTOR_RAIL_TYPE != null) return;
    BlockTypes.DETECTOR_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockDetectorRailBehavior.class)
            .vanillaBlock(VanillaBlockId.DETECTOR_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }

  public static void initDiamondBlock() {
    if (BlockTypes.DIAMOND_BLOCK_TYPE != null) return;
    BlockTypes.DIAMOND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDiamondBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK)
            .build();
  }

  public static void initDiamondOre() {
    if (BlockTypes.DIAMOND_ORE_TYPE != null) return;
    BlockTypes.DIAMOND_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDiamondOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_ORE)
            .build();
  }

  public static void initDiorite() {
    if (BlockTypes.DIORITE_TYPE != null) return;
    BlockTypes.DIORITE_TYPE = BlockTypeBuilder
            .builder(BlockDioriteBehavior.class)
            .vanillaBlock(VanillaBlockId.DIORITE)
            .build();
  }

  public static void initDioriteStairs() {
    if (BlockTypes.DIORITE_STAIRS_TYPE != null) return;
    BlockTypes.DIORITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDioriteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DIORITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initDirt() {
    if (BlockTypes.DIRT_TYPE != null) return;
    BlockTypes.DIRT_TYPE = BlockTypeBuilder
            .builder(BlockDirtBehavior.class)
            .vanillaBlock(VanillaBlockId.DIRT)
            .setProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
            .build();
  }

  public static void initDirtWithRoots() {
    if (BlockTypes.DIRT_WITH_ROOTS_TYPE != null) return;
    BlockTypes.DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockDirtWithRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS)
            .build();
  }

  public static void initDispenser() {
    if (BlockTypes.DISPENSER_TYPE != null) return;
    BlockTypes.DISPENSER_TYPE = BlockTypeBuilder
            .builder(BlockDispenserBehavior.class)
            .vanillaBlock(VanillaBlockId.DISPENSER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }

  public static void initDoubleCutCopperSlab() {
    if (BlockTypes.DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initDoublePlant() {
    if (BlockTypes.DOUBLE_PLANT_TYPE != null) return;
    BlockTypes.DOUBLE_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockDoublePlantBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
            .setProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initDoubleStoneBlockSlab() {
    if (BlockTypes.DOUBLE_STONE_BLOCK_SLAB_TYPE != null) return;
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE)
            .build();
  }

  public static void initDoubleStoneBlockSlab2() {
    if (BlockTypes.DOUBLE_STONE_BLOCK_SLAB2_TYPE != null) return;
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab2Behavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2)
            .build();
  }

  public static void initDoubleStoneBlockSlab3() {
    if (BlockTypes.DOUBLE_STONE_BLOCK_SLAB3_TYPE != null) return;
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab3Behavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3)
            .build();
  }

  public static void initDoubleStoneBlockSlab4() {
    if (BlockTypes.DOUBLE_STONE_BLOCK_SLAB4_TYPE != null) return;
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab4Behavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
            .build();
  }

  public static void initDoubleWoodenSlab() {
    if (BlockTypes.DOUBLE_WOODEN_SLAB_TYPE != null) return;
    BlockTypes.DOUBLE_WOODEN_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleWoodenSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }

  public static void initDragonEgg() {
    if (BlockTypes.DRAGON_EGG_TYPE != null) return;
    BlockTypes.DRAGON_EGG_TYPE = BlockTypeBuilder
            .builder(BlockDragonEggBehavior.class)
            .vanillaBlock(VanillaBlockId.DRAGON_EGG)
            .build();
  }

  public static void initDriedKelpBlock() {
    if (BlockTypes.DRIED_KELP_BLOCK_TYPE != null) return;
    BlockTypes.DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDriedKelpBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK)
            .build();
  }

  public static void initDripstoneBlock() {
    if (BlockTypes.DRIPSTONE_BLOCK_TYPE != null) return;
    BlockTypes.DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDripstoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK)
            .build();
  }

  public static void initDropper() {
    if (BlockTypes.DROPPER_TYPE != null) return;
    BlockTypes.DROPPER_TYPE = BlockTypeBuilder
            .builder(BlockDropperBehavior.class)
            .vanillaBlock(VanillaBlockId.DROPPER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }

  public static void initElement0() {
    if (BlockTypes.ELEMENT_0_TYPE != null) return;
    BlockTypes.ELEMENT_0_TYPE = BlockTypeBuilder
            .builder(BlockElement0Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_0)
            .build();
  }

  public static void initElement1() {
    if (BlockTypes.ELEMENT_1_TYPE != null) return;
    BlockTypes.ELEMENT_1_TYPE = BlockTypeBuilder
            .builder(BlockElement1Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_1)
            .build();
  }

  public static void initElement10() {
    if (BlockTypes.ELEMENT_10_TYPE != null) return;
    BlockTypes.ELEMENT_10_TYPE = BlockTypeBuilder
            .builder(BlockElement10Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_10)
            .build();
  }

  public static void initElement100() {
    if (BlockTypes.ELEMENT_100_TYPE != null) return;
    BlockTypes.ELEMENT_100_TYPE = BlockTypeBuilder
            .builder(BlockElement100Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_100)
            .build();
  }

  public static void initElement101() {
    if (BlockTypes.ELEMENT_101_TYPE != null) return;
    BlockTypes.ELEMENT_101_TYPE = BlockTypeBuilder
            .builder(BlockElement101Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_101)
            .build();
  }

  public static void initElement102() {
    if (BlockTypes.ELEMENT_102_TYPE != null) return;
    BlockTypes.ELEMENT_102_TYPE = BlockTypeBuilder
            .builder(BlockElement102Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_102)
            .build();
  }

  public static void initElement103() {
    if (BlockTypes.ELEMENT_103_TYPE != null) return;
    BlockTypes.ELEMENT_103_TYPE = BlockTypeBuilder
            .builder(BlockElement103Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_103)
            .build();
  }

  public static void initElement104() {
    if (BlockTypes.ELEMENT_104_TYPE != null) return;
    BlockTypes.ELEMENT_104_TYPE = BlockTypeBuilder
            .builder(BlockElement104Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_104)
            .build();
  }

  public static void initElement105() {
    if (BlockTypes.ELEMENT_105_TYPE != null) return;
    BlockTypes.ELEMENT_105_TYPE = BlockTypeBuilder
            .builder(BlockElement105Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_105)
            .build();
  }

  public static void initElement106() {
    if (BlockTypes.ELEMENT_106_TYPE != null) return;
    BlockTypes.ELEMENT_106_TYPE = BlockTypeBuilder
            .builder(BlockElement106Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_106)
            .build();
  }

  public static void initElement107() {
    if (BlockTypes.ELEMENT_107_TYPE != null) return;
    BlockTypes.ELEMENT_107_TYPE = BlockTypeBuilder
            .builder(BlockElement107Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_107)
            .build();
  }

  public static void initElement108() {
    if (BlockTypes.ELEMENT_108_TYPE != null) return;
    BlockTypes.ELEMENT_108_TYPE = BlockTypeBuilder
            .builder(BlockElement108Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_108)
            .build();
  }

  public static void initElement109() {
    if (BlockTypes.ELEMENT_109_TYPE != null) return;
    BlockTypes.ELEMENT_109_TYPE = BlockTypeBuilder
            .builder(BlockElement109Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_109)
            .build();
  }

  public static void initElement11() {
    if (BlockTypes.ELEMENT_11_TYPE != null) return;
    BlockTypes.ELEMENT_11_TYPE = BlockTypeBuilder
            .builder(BlockElement11Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_11)
            .build();
  }

  public static void initElement110() {
    if (BlockTypes.ELEMENT_110_TYPE != null) return;
    BlockTypes.ELEMENT_110_TYPE = BlockTypeBuilder
            .builder(BlockElement110Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_110)
            .build();
  }

  public static void initElement111() {
    if (BlockTypes.ELEMENT_111_TYPE != null) return;
    BlockTypes.ELEMENT_111_TYPE = BlockTypeBuilder
            .builder(BlockElement111Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_111)
            .build();
  }

  public static void initElement112() {
    if (BlockTypes.ELEMENT_112_TYPE != null) return;
    BlockTypes.ELEMENT_112_TYPE = BlockTypeBuilder
            .builder(BlockElement112Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_112)
            .build();
  }

  public static void initElement113() {
    if (BlockTypes.ELEMENT_113_TYPE != null) return;
    BlockTypes.ELEMENT_113_TYPE = BlockTypeBuilder
            .builder(BlockElement113Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_113)
            .build();
  }

  public static void initElement114() {
    if (BlockTypes.ELEMENT_114_TYPE != null) return;
    BlockTypes.ELEMENT_114_TYPE = BlockTypeBuilder
            .builder(BlockElement114Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_114)
            .build();
  }

  public static void initElement115() {
    if (BlockTypes.ELEMENT_115_TYPE != null) return;
    BlockTypes.ELEMENT_115_TYPE = BlockTypeBuilder
            .builder(BlockElement115Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_115)
            .build();
  }

  public static void initElement116() {
    if (BlockTypes.ELEMENT_116_TYPE != null) return;
    BlockTypes.ELEMENT_116_TYPE = BlockTypeBuilder
            .builder(BlockElement116Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_116)
            .build();
  }

  public static void initElement117() {
    if (BlockTypes.ELEMENT_117_TYPE != null) return;
    BlockTypes.ELEMENT_117_TYPE = BlockTypeBuilder
            .builder(BlockElement117Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_117)
            .build();
  }

  public static void initElement118() {
    if (BlockTypes.ELEMENT_118_TYPE != null) return;
    BlockTypes.ELEMENT_118_TYPE = BlockTypeBuilder
            .builder(BlockElement118Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_118)
            .build();
  }

  public static void initElement12() {
    if (BlockTypes.ELEMENT_12_TYPE != null) return;
    BlockTypes.ELEMENT_12_TYPE = BlockTypeBuilder
            .builder(BlockElement12Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_12)
            .build();
  }

  public static void initElement13() {
    if (BlockTypes.ELEMENT_13_TYPE != null) return;
    BlockTypes.ELEMENT_13_TYPE = BlockTypeBuilder
            .builder(BlockElement13Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_13)
            .build();
  }

  public static void initElement14() {
    if (BlockTypes.ELEMENT_14_TYPE != null) return;
    BlockTypes.ELEMENT_14_TYPE = BlockTypeBuilder
            .builder(BlockElement14Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_14)
            .build();
  }

  public static void initElement15() {
    if (BlockTypes.ELEMENT_15_TYPE != null) return;
    BlockTypes.ELEMENT_15_TYPE = BlockTypeBuilder
            .builder(BlockElement15Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_15)
            .build();
  }

  public static void initElement16() {
    if (BlockTypes.ELEMENT_16_TYPE != null) return;
    BlockTypes.ELEMENT_16_TYPE = BlockTypeBuilder
            .builder(BlockElement16Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_16)
            .build();
  }

  public static void initElement17() {
    if (BlockTypes.ELEMENT_17_TYPE != null) return;
    BlockTypes.ELEMENT_17_TYPE = BlockTypeBuilder
            .builder(BlockElement17Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_17)
            .build();
  }

  public static void initElement18() {
    if (BlockTypes.ELEMENT_18_TYPE != null) return;
    BlockTypes.ELEMENT_18_TYPE = BlockTypeBuilder
            .builder(BlockElement18Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_18)
            .build();
  }

  public static void initElement19() {
    if (BlockTypes.ELEMENT_19_TYPE != null) return;
    BlockTypes.ELEMENT_19_TYPE = BlockTypeBuilder
            .builder(BlockElement19Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_19)
            .build();
  }

  public static void initElement2() {
    if (BlockTypes.ELEMENT_2_TYPE != null) return;
    BlockTypes.ELEMENT_2_TYPE = BlockTypeBuilder
            .builder(BlockElement2Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_2)
            .build();
  }

  public static void initElement20() {
    if (BlockTypes.ELEMENT_20_TYPE != null) return;
    BlockTypes.ELEMENT_20_TYPE = BlockTypeBuilder
            .builder(BlockElement20Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_20)
            .build();
  }

  public static void initElement21() {
    if (BlockTypes.ELEMENT_21_TYPE != null) return;
    BlockTypes.ELEMENT_21_TYPE = BlockTypeBuilder
            .builder(BlockElement21Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_21)
            .build();
  }

  public static void initElement22() {
    if (BlockTypes.ELEMENT_22_TYPE != null) return;
    BlockTypes.ELEMENT_22_TYPE = BlockTypeBuilder
            .builder(BlockElement22Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_22)
            .build();
  }

  public static void initElement23() {
    if (BlockTypes.ELEMENT_23_TYPE != null) return;
    BlockTypes.ELEMENT_23_TYPE = BlockTypeBuilder
            .builder(BlockElement23Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_23)
            .build();
  }

  public static void initElement24() {
    if (BlockTypes.ELEMENT_24_TYPE != null) return;
    BlockTypes.ELEMENT_24_TYPE = BlockTypeBuilder
            .builder(BlockElement24Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_24)
            .build();
  }

  public static void initElement25() {
    if (BlockTypes.ELEMENT_25_TYPE != null) return;
    BlockTypes.ELEMENT_25_TYPE = BlockTypeBuilder
            .builder(BlockElement25Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_25)
            .build();
  }

  public static void initElement26() {
    if (BlockTypes.ELEMENT_26_TYPE != null) return;
    BlockTypes.ELEMENT_26_TYPE = BlockTypeBuilder
            .builder(BlockElement26Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_26)
            .build();
  }

  public static void initElement27() {
    if (BlockTypes.ELEMENT_27_TYPE != null) return;
    BlockTypes.ELEMENT_27_TYPE = BlockTypeBuilder
            .builder(BlockElement27Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_27)
            .build();
  }

  public static void initElement28() {
    if (BlockTypes.ELEMENT_28_TYPE != null) return;
    BlockTypes.ELEMENT_28_TYPE = BlockTypeBuilder
            .builder(BlockElement28Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_28)
            .build();
  }

  public static void initElement29() {
    if (BlockTypes.ELEMENT_29_TYPE != null) return;
    BlockTypes.ELEMENT_29_TYPE = BlockTypeBuilder
            .builder(BlockElement29Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_29)
            .build();
  }

  public static void initElement3() {
    if (BlockTypes.ELEMENT_3_TYPE != null) return;
    BlockTypes.ELEMENT_3_TYPE = BlockTypeBuilder
            .builder(BlockElement3Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_3)
            .build();
  }

  public static void initElement30() {
    if (BlockTypes.ELEMENT_30_TYPE != null) return;
    BlockTypes.ELEMENT_30_TYPE = BlockTypeBuilder
            .builder(BlockElement30Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_30)
            .build();
  }

  public static void initElement31() {
    if (BlockTypes.ELEMENT_31_TYPE != null) return;
    BlockTypes.ELEMENT_31_TYPE = BlockTypeBuilder
            .builder(BlockElement31Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_31)
            .build();
  }

  public static void initElement32() {
    if (BlockTypes.ELEMENT_32_TYPE != null) return;
    BlockTypes.ELEMENT_32_TYPE = BlockTypeBuilder
            .builder(BlockElement32Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_32)
            .build();
  }

  public static void initElement33() {
    if (BlockTypes.ELEMENT_33_TYPE != null) return;
    BlockTypes.ELEMENT_33_TYPE = BlockTypeBuilder
            .builder(BlockElement33Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_33)
            .build();
  }

  public static void initElement34() {
    if (BlockTypes.ELEMENT_34_TYPE != null) return;
    BlockTypes.ELEMENT_34_TYPE = BlockTypeBuilder
            .builder(BlockElement34Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_34)
            .build();
  }

  public static void initElement35() {
    if (BlockTypes.ELEMENT_35_TYPE != null) return;
    BlockTypes.ELEMENT_35_TYPE = BlockTypeBuilder
            .builder(BlockElement35Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_35)
            .build();
  }

  public static void initElement36() {
    if (BlockTypes.ELEMENT_36_TYPE != null) return;
    BlockTypes.ELEMENT_36_TYPE = BlockTypeBuilder
            .builder(BlockElement36Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_36)
            .build();
  }

  public static void initElement37() {
    if (BlockTypes.ELEMENT_37_TYPE != null) return;
    BlockTypes.ELEMENT_37_TYPE = BlockTypeBuilder
            .builder(BlockElement37Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_37)
            .build();
  }

  public static void initElement38() {
    if (BlockTypes.ELEMENT_38_TYPE != null) return;
    BlockTypes.ELEMENT_38_TYPE = BlockTypeBuilder
            .builder(BlockElement38Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_38)
            .build();
  }

  public static void initElement39() {
    if (BlockTypes.ELEMENT_39_TYPE != null) return;
    BlockTypes.ELEMENT_39_TYPE = BlockTypeBuilder
            .builder(BlockElement39Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_39)
            .build();
  }

  public static void initElement4() {
    if (BlockTypes.ELEMENT_4_TYPE != null) return;
    BlockTypes.ELEMENT_4_TYPE = BlockTypeBuilder
            .builder(BlockElement4Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_4)
            .build();
  }

  public static void initElement40() {
    if (BlockTypes.ELEMENT_40_TYPE != null) return;
    BlockTypes.ELEMENT_40_TYPE = BlockTypeBuilder
            .builder(BlockElement40Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_40)
            .build();
  }

  public static void initElement41() {
    if (BlockTypes.ELEMENT_41_TYPE != null) return;
    BlockTypes.ELEMENT_41_TYPE = BlockTypeBuilder
            .builder(BlockElement41Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_41)
            .build();
  }

  public static void initElement42() {
    if (BlockTypes.ELEMENT_42_TYPE != null) return;
    BlockTypes.ELEMENT_42_TYPE = BlockTypeBuilder
            .builder(BlockElement42Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_42)
            .build();
  }

  public static void initElement43() {
    if (BlockTypes.ELEMENT_43_TYPE != null) return;
    BlockTypes.ELEMENT_43_TYPE = BlockTypeBuilder
            .builder(BlockElement43Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_43)
            .build();
  }

  public static void initElement44() {
    if (BlockTypes.ELEMENT_44_TYPE != null) return;
    BlockTypes.ELEMENT_44_TYPE = BlockTypeBuilder
            .builder(BlockElement44Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_44)
            .build();
  }

  public static void initElement45() {
    if (BlockTypes.ELEMENT_45_TYPE != null) return;
    BlockTypes.ELEMENT_45_TYPE = BlockTypeBuilder
            .builder(BlockElement45Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_45)
            .build();
  }

  public static void initElement46() {
    if (BlockTypes.ELEMENT_46_TYPE != null) return;
    BlockTypes.ELEMENT_46_TYPE = BlockTypeBuilder
            .builder(BlockElement46Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_46)
            .build();
  }

  public static void initElement47() {
    if (BlockTypes.ELEMENT_47_TYPE != null) return;
    BlockTypes.ELEMENT_47_TYPE = BlockTypeBuilder
            .builder(BlockElement47Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_47)
            .build();
  }

  public static void initElement48() {
    if (BlockTypes.ELEMENT_48_TYPE != null) return;
    BlockTypes.ELEMENT_48_TYPE = BlockTypeBuilder
            .builder(BlockElement48Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_48)
            .build();
  }

  public static void initElement49() {
    if (BlockTypes.ELEMENT_49_TYPE != null) return;
    BlockTypes.ELEMENT_49_TYPE = BlockTypeBuilder
            .builder(BlockElement49Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_49)
            .build();
  }

  public static void initElement5() {
    if (BlockTypes.ELEMENT_5_TYPE != null) return;
    BlockTypes.ELEMENT_5_TYPE = BlockTypeBuilder
            .builder(BlockElement5Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_5)
            .build();
  }

  public static void initElement50() {
    if (BlockTypes.ELEMENT_50_TYPE != null) return;
    BlockTypes.ELEMENT_50_TYPE = BlockTypeBuilder
            .builder(BlockElement50Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_50)
            .build();
  }

  public static void initElement51() {
    if (BlockTypes.ELEMENT_51_TYPE != null) return;
    BlockTypes.ELEMENT_51_TYPE = BlockTypeBuilder
            .builder(BlockElement51Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_51)
            .build();
  }

  public static void initElement52() {
    if (BlockTypes.ELEMENT_52_TYPE != null) return;
    BlockTypes.ELEMENT_52_TYPE = BlockTypeBuilder
            .builder(BlockElement52Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_52)
            .build();
  }

  public static void initElement53() {
    if (BlockTypes.ELEMENT_53_TYPE != null) return;
    BlockTypes.ELEMENT_53_TYPE = BlockTypeBuilder
            .builder(BlockElement53Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_53)
            .build();
  }

  public static void initElement54() {
    if (BlockTypes.ELEMENT_54_TYPE != null) return;
    BlockTypes.ELEMENT_54_TYPE = BlockTypeBuilder
            .builder(BlockElement54Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_54)
            .build();
  }

  public static void initElement55() {
    if (BlockTypes.ELEMENT_55_TYPE != null) return;
    BlockTypes.ELEMENT_55_TYPE = BlockTypeBuilder
            .builder(BlockElement55Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_55)
            .build();
  }

  public static void initElement56() {
    if (BlockTypes.ELEMENT_56_TYPE != null) return;
    BlockTypes.ELEMENT_56_TYPE = BlockTypeBuilder
            .builder(BlockElement56Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_56)
            .build();
  }

  public static void initElement57() {
    if (BlockTypes.ELEMENT_57_TYPE != null) return;
    BlockTypes.ELEMENT_57_TYPE = BlockTypeBuilder
            .builder(BlockElement57Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_57)
            .build();
  }

  public static void initElement58() {
    if (BlockTypes.ELEMENT_58_TYPE != null) return;
    BlockTypes.ELEMENT_58_TYPE = BlockTypeBuilder
            .builder(BlockElement58Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_58)
            .build();
  }

  public static void initElement59() {
    if (BlockTypes.ELEMENT_59_TYPE != null) return;
    BlockTypes.ELEMENT_59_TYPE = BlockTypeBuilder
            .builder(BlockElement59Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_59)
            .build();
  }

  public static void initElement6() {
    if (BlockTypes.ELEMENT_6_TYPE != null) return;
    BlockTypes.ELEMENT_6_TYPE = BlockTypeBuilder
            .builder(BlockElement6Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_6)
            .build();
  }

  public static void initElement60() {
    if (BlockTypes.ELEMENT_60_TYPE != null) return;
    BlockTypes.ELEMENT_60_TYPE = BlockTypeBuilder
            .builder(BlockElement60Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_60)
            .build();
  }

  public static void initElement61() {
    if (BlockTypes.ELEMENT_61_TYPE != null) return;
    BlockTypes.ELEMENT_61_TYPE = BlockTypeBuilder
            .builder(BlockElement61Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_61)
            .build();
  }

  public static void initElement62() {
    if (BlockTypes.ELEMENT_62_TYPE != null) return;
    BlockTypes.ELEMENT_62_TYPE = BlockTypeBuilder
            .builder(BlockElement62Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_62)
            .build();
  }

  public static void initElement63() {
    if (BlockTypes.ELEMENT_63_TYPE != null) return;
    BlockTypes.ELEMENT_63_TYPE = BlockTypeBuilder
            .builder(BlockElement63Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_63)
            .build();
  }

  public static void initElement64() {
    if (BlockTypes.ELEMENT_64_TYPE != null) return;
    BlockTypes.ELEMENT_64_TYPE = BlockTypeBuilder
            .builder(BlockElement64Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_64)
            .build();
  }

  public static void initElement65() {
    if (BlockTypes.ELEMENT_65_TYPE != null) return;
    BlockTypes.ELEMENT_65_TYPE = BlockTypeBuilder
            .builder(BlockElement65Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_65)
            .build();
  }

  public static void initElement66() {
    if (BlockTypes.ELEMENT_66_TYPE != null) return;
    BlockTypes.ELEMENT_66_TYPE = BlockTypeBuilder
            .builder(BlockElement66Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_66)
            .build();
  }

  public static void initElement67() {
    if (BlockTypes.ELEMENT_67_TYPE != null) return;
    BlockTypes.ELEMENT_67_TYPE = BlockTypeBuilder
            .builder(BlockElement67Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_67)
            .build();
  }

  public static void initElement68() {
    if (BlockTypes.ELEMENT_68_TYPE != null) return;
    BlockTypes.ELEMENT_68_TYPE = BlockTypeBuilder
            .builder(BlockElement68Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_68)
            .build();
  }

  public static void initElement69() {
    if (BlockTypes.ELEMENT_69_TYPE != null) return;
    BlockTypes.ELEMENT_69_TYPE = BlockTypeBuilder
            .builder(BlockElement69Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_69)
            .build();
  }

  public static void initElement7() {
    if (BlockTypes.ELEMENT_7_TYPE != null) return;
    BlockTypes.ELEMENT_7_TYPE = BlockTypeBuilder
            .builder(BlockElement7Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_7)
            .build();
  }

  public static void initElement70() {
    if (BlockTypes.ELEMENT_70_TYPE != null) return;
    BlockTypes.ELEMENT_70_TYPE = BlockTypeBuilder
            .builder(BlockElement70Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_70)
            .build();
  }

  public static void initElement71() {
    if (BlockTypes.ELEMENT_71_TYPE != null) return;
    BlockTypes.ELEMENT_71_TYPE = BlockTypeBuilder
            .builder(BlockElement71Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_71)
            .build();
  }

  public static void initElement72() {
    if (BlockTypes.ELEMENT_72_TYPE != null) return;
    BlockTypes.ELEMENT_72_TYPE = BlockTypeBuilder
            .builder(BlockElement72Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_72)
            .build();
  }

  public static void initElement73() {
    if (BlockTypes.ELEMENT_73_TYPE != null) return;
    BlockTypes.ELEMENT_73_TYPE = BlockTypeBuilder
            .builder(BlockElement73Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_73)
            .build();
  }

  public static void initElement74() {
    if (BlockTypes.ELEMENT_74_TYPE != null) return;
    BlockTypes.ELEMENT_74_TYPE = BlockTypeBuilder
            .builder(BlockElement74Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_74)
            .build();
  }

  public static void initElement75() {
    if (BlockTypes.ELEMENT_75_TYPE != null) return;
    BlockTypes.ELEMENT_75_TYPE = BlockTypeBuilder
            .builder(BlockElement75Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_75)
            .build();
  }

  public static void initElement76() {
    if (BlockTypes.ELEMENT_76_TYPE != null) return;
    BlockTypes.ELEMENT_76_TYPE = BlockTypeBuilder
            .builder(BlockElement76Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_76)
            .build();
  }

  public static void initElement77() {
    if (BlockTypes.ELEMENT_77_TYPE != null) return;
    BlockTypes.ELEMENT_77_TYPE = BlockTypeBuilder
            .builder(BlockElement77Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_77)
            .build();
  }

  public static void initElement78() {
    if (BlockTypes.ELEMENT_78_TYPE != null) return;
    BlockTypes.ELEMENT_78_TYPE = BlockTypeBuilder
            .builder(BlockElement78Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_78)
            .build();
  }

  public static void initElement79() {
    if (BlockTypes.ELEMENT_79_TYPE != null) return;
    BlockTypes.ELEMENT_79_TYPE = BlockTypeBuilder
            .builder(BlockElement79Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_79)
            .build();
  }

  public static void initElement8() {
    if (BlockTypes.ELEMENT_8_TYPE != null) return;
    BlockTypes.ELEMENT_8_TYPE = BlockTypeBuilder
            .builder(BlockElement8Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_8)
            .build();
  }

  public static void initElement80() {
    if (BlockTypes.ELEMENT_80_TYPE != null) return;
    BlockTypes.ELEMENT_80_TYPE = BlockTypeBuilder
            .builder(BlockElement80Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_80)
            .build();
  }

  public static void initElement81() {
    if (BlockTypes.ELEMENT_81_TYPE != null) return;
    BlockTypes.ELEMENT_81_TYPE = BlockTypeBuilder
            .builder(BlockElement81Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_81)
            .build();
  }

  public static void initElement82() {
    if (BlockTypes.ELEMENT_82_TYPE != null) return;
    BlockTypes.ELEMENT_82_TYPE = BlockTypeBuilder
            .builder(BlockElement82Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_82)
            .build();
  }

  public static void initElement83() {
    if (BlockTypes.ELEMENT_83_TYPE != null) return;
    BlockTypes.ELEMENT_83_TYPE = BlockTypeBuilder
            .builder(BlockElement83Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_83)
            .build();
  }

  public static void initElement84() {
    if (BlockTypes.ELEMENT_84_TYPE != null) return;
    BlockTypes.ELEMENT_84_TYPE = BlockTypeBuilder
            .builder(BlockElement84Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_84)
            .build();
  }

  public static void initElement85() {
    if (BlockTypes.ELEMENT_85_TYPE != null) return;
    BlockTypes.ELEMENT_85_TYPE = BlockTypeBuilder
            .builder(BlockElement85Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_85)
            .build();
  }

  public static void initElement86() {
    if (BlockTypes.ELEMENT_86_TYPE != null) return;
    BlockTypes.ELEMENT_86_TYPE = BlockTypeBuilder
            .builder(BlockElement86Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_86)
            .build();
  }

  public static void initElement87() {
    if (BlockTypes.ELEMENT_87_TYPE != null) return;
    BlockTypes.ELEMENT_87_TYPE = BlockTypeBuilder
            .builder(BlockElement87Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_87)
            .build();
  }

  public static void initElement88() {
    if (BlockTypes.ELEMENT_88_TYPE != null) return;
    BlockTypes.ELEMENT_88_TYPE = BlockTypeBuilder
            .builder(BlockElement88Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_88)
            .build();
  }

  public static void initElement89() {
    if (BlockTypes.ELEMENT_89_TYPE != null) return;
    BlockTypes.ELEMENT_89_TYPE = BlockTypeBuilder
            .builder(BlockElement89Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_89)
            .build();
  }

  public static void initElement9() {
    if (BlockTypes.ELEMENT_9_TYPE != null) return;
    BlockTypes.ELEMENT_9_TYPE = BlockTypeBuilder
            .builder(BlockElement9Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_9)
            .build();
  }

  public static void initElement90() {
    if (BlockTypes.ELEMENT_90_TYPE != null) return;
    BlockTypes.ELEMENT_90_TYPE = BlockTypeBuilder
            .builder(BlockElement90Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_90)
            .build();
  }

  public static void initElement91() {
    if (BlockTypes.ELEMENT_91_TYPE != null) return;
    BlockTypes.ELEMENT_91_TYPE = BlockTypeBuilder
            .builder(BlockElement91Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_91)
            .build();
  }

  public static void initElement92() {
    if (BlockTypes.ELEMENT_92_TYPE != null) return;
    BlockTypes.ELEMENT_92_TYPE = BlockTypeBuilder
            .builder(BlockElement92Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_92)
            .build();
  }

  public static void initElement93() {
    if (BlockTypes.ELEMENT_93_TYPE != null) return;
    BlockTypes.ELEMENT_93_TYPE = BlockTypeBuilder
            .builder(BlockElement93Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_93)
            .build();
  }

  public static void initElement94() {
    if (BlockTypes.ELEMENT_94_TYPE != null) return;
    BlockTypes.ELEMENT_94_TYPE = BlockTypeBuilder
            .builder(BlockElement94Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_94)
            .build();
  }

  public static void initElement95() {
    if (BlockTypes.ELEMENT_95_TYPE != null) return;
    BlockTypes.ELEMENT_95_TYPE = BlockTypeBuilder
            .builder(BlockElement95Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_95)
            .build();
  }

  public static void initElement96() {
    if (BlockTypes.ELEMENT_96_TYPE != null) return;
    BlockTypes.ELEMENT_96_TYPE = BlockTypeBuilder
            .builder(BlockElement96Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_96)
            .build();
  }

  public static void initElement97() {
    if (BlockTypes.ELEMENT_97_TYPE != null) return;
    BlockTypes.ELEMENT_97_TYPE = BlockTypeBuilder
            .builder(BlockElement97Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_97)
            .build();
  }

  public static void initElement98() {
    if (BlockTypes.ELEMENT_98_TYPE != null) return;
    BlockTypes.ELEMENT_98_TYPE = BlockTypeBuilder
            .builder(BlockElement98Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_98)
            .build();
  }

  public static void initElement99() {
    if (BlockTypes.ELEMENT_99_TYPE != null) return;
    BlockTypes.ELEMENT_99_TYPE = BlockTypeBuilder
            .builder(BlockElement99Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_99)
            .build();
  }

  public static void initEmeraldBlock() {
    if (BlockTypes.EMERALD_BLOCK_TYPE != null) return;
    BlockTypes.EMERALD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockEmeraldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.EMERALD_BLOCK)
            .build();
  }

  public static void initEmeraldOre() {
    if (BlockTypes.EMERALD_ORE_TYPE != null) return;
    BlockTypes.EMERALD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockEmeraldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.EMERALD_ORE)
            .build();
  }

  public static void initEnchantingTable() {
    if (BlockTypes.ENCHANTING_TABLE_TYPE != null) return;
    BlockTypes.ENCHANTING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockEnchantingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE)
            .build();
  }

  public static void initEndBrickStairs() {
    if (BlockTypes.END_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.END_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockEndBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initEndBricks() {
    if (BlockTypes.END_BRICKS_TYPE != null) return;
    BlockTypes.END_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockEndBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.END_BRICKS)
            .build();
  }

  public static void initEndGateway() {
    if (BlockTypes.END_GATEWAY_TYPE != null) return;
    BlockTypes.END_GATEWAY_TYPE = BlockTypeBuilder
            .builder(BlockEndGatewayBehavior.class)
            .vanillaBlock(VanillaBlockId.END_GATEWAY)
            .build();
  }

  public static void initEndPortal() {
    if (BlockTypes.END_PORTAL_TYPE != null) return;
    BlockTypes.END_PORTAL_TYPE = BlockTypeBuilder
            .builder(BlockEndPortalBehavior.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL)
            .build();
  }

  public static void initEndPortalFrame() {
    if (BlockTypes.END_PORTAL_FRAME_TYPE != null) return;
    BlockTypes.END_PORTAL_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockEndPortalFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME)
            .setProperties(VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initEndRod() {
    if (BlockTypes.END_ROD_TYPE != null) return;
    BlockTypes.END_ROD_TYPE = BlockTypeBuilder
            .builder(BlockEndRodBehavior.class)
            .vanillaBlock(VanillaBlockId.END_ROD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initEndStone() {
    if (BlockTypes.END_STONE_TYPE != null) return;
    BlockTypes.END_STONE_TYPE = BlockTypeBuilder
            .builder(BlockEndStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.END_STONE)
            .build();
  }

  public static void initEnderChest() {
    if (BlockTypes.ENDER_CHEST_TYPE != null) return;
    BlockTypes.ENDER_CHEST_TYPE = BlockTypeBuilder
            .builder(BlockEnderChestBehavior.class)
            .vanillaBlock(VanillaBlockId.ENDER_CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initExposedChiseledCopper() {
    if (BlockTypes.EXPOSED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.EXPOSED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CHISELED_COPPER)
            .build();
  }

  public static void initExposedCopper() {
    if (BlockTypes.EXPOSED_COPPER_TYPE != null) return;
    BlockTypes.EXPOSED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER)
            .build();
  }

  public static void initExposedCopperBulb() {
    if (BlockTypes.EXPOSED_COPPER_BULB_TYPE != null) return;
    BlockTypes.EXPOSED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initExposedCopperDoor() {
    if (BlockTypes.EXPOSED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.EXPOSED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initExposedCopperGrate() {
    if (BlockTypes.EXPOSED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.EXPOSED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_GRATE)
            .build();
  }

  public static void initExposedCopperTrapdoor() {
    if (BlockTypes.EXPOSED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.EXPOSED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initExposedCutCopper() {
    if (BlockTypes.EXPOSED_CUT_COPPER_TYPE != null) return;
    BlockTypes.EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER)
            .build();
  }

  public static void initExposedCutCopperSlab() {
    if (BlockTypes.EXPOSED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initExposedCutCopperStairs() {
    if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initExposedDoubleCutCopperSlab() {
    if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockExposedDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initFarmland() {
    if (BlockTypes.FARMLAND_TYPE != null) return;
    BlockTypes.FARMLAND_TYPE = BlockTypeBuilder
            .builder(BlockFarmlandBehavior.class)
            .vanillaBlock(VanillaBlockId.FARMLAND)
            .setProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
            .build();
  }

  public static void initFenceGate() {
    if (BlockTypes.FENCE_GATE_TYPE != null) return;
    BlockTypes.FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initFire() {
    if (BlockTypes.FIRE_TYPE != null) return;
    BlockTypes.FIRE_TYPE = BlockTypeBuilder
            .builder(BlockFireBehavior.class)
            .vanillaBlock(VanillaBlockId.FIRE)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }

  public static void initFireCoral() {
    if (BlockTypes.FIRE_CORAL_TYPE != null) return;
    BlockTypes.FIRE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockFireCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.FIRE_CORAL)
            .build();
  }

  public static void initFletchingTable() {
    if (BlockTypes.FLETCHING_TABLE_TYPE != null) return;
    BlockTypes.FLETCHING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockFletchingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.FLETCHING_TABLE)
            .build();
  }

  public static void initFlowerPot() {
    if (BlockTypes.FLOWER_POT_TYPE != null) return;
    BlockTypes.FLOWER_POT_TYPE = BlockTypeBuilder
            .builder(BlockFlowerPotBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWER_POT)
            .setProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initFloweringAzalea() {
    if (BlockTypes.FLOWERING_AZALEA_TYPE != null) return;
    BlockTypes.FLOWERING_AZALEA_TYPE = BlockTypeBuilder
            .builder(BlockFloweringAzaleaBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA)
            .build();
  }

  public static void initFlowingLava() {
    if (BlockTypes.FLOWING_LAVA_TYPE != null) return;
    BlockTypes.FLOWING_LAVA_TYPE = BlockTypeBuilder
            .builder(BlockFlowingLavaBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWING_LAVA)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }

  public static void initFlowingWater() {
    if (BlockTypes.FLOWING_WATER_TYPE != null) return;
    BlockTypes.FLOWING_WATER_TYPE = BlockTypeBuilder
            .builder(BlockFlowingWaterBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWING_WATER)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }

  public static void initFrame() {
    if (BlockTypes.FRAME_TYPE != null) return;
    BlockTypes.FRAME_TYPE = BlockTypeBuilder
            .builder(BlockFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.FRAME)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .build();
  }

  public static void initFrogSpawn() {
    if (BlockTypes.FROG_SPAWN_TYPE != null) return;
    BlockTypes.FROG_SPAWN_TYPE = BlockTypeBuilder
            .builder(BlockFrogSpawnBehavior.class)
            .vanillaBlock(VanillaBlockId.FROG_SPAWN)
            .build();
  }

  public static void initFrostedIce() {
    if (BlockTypes.FROSTED_ICE_TYPE != null) return;
    BlockTypes.FROSTED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockFrostedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.FROSTED_ICE)
            .setProperties(VanillaBlockPropertyTypes.AGE_4)
            .build();
  }

  public static void initFurnace() {
    if (BlockTypes.FURNACE_TYPE != null) return;
    BlockTypes.FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initGildedBlackstone() {
    if (BlockTypes.GILDED_BLACKSTONE_TYPE != null) return;
    BlockTypes.GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGildedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE)
            .build();
  }

  public static void initGlass() {
    if (BlockTypes.GLASS_TYPE != null) return;
    BlockTypes.GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GLASS)
            .build();
  }

  public static void initGlassPane() {
    if (BlockTypes.GLASS_PANE_TYPE != null) return;
    BlockTypes.GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GLASS_PANE)
            .build();
  }

  public static void initGlowFrame() {
    if (BlockTypes.GLOW_FRAME_TYPE != null) return;
    BlockTypes.GLOW_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockGlowFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_FRAME)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .build();
  }

  public static void initGlowLichen() {
    if (BlockTypes.GLOW_LICHEN_TYPE != null) return;
    BlockTypes.GLOW_LICHEN_TYPE = BlockTypeBuilder
            .builder(BlockGlowLichenBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_LICHEN)
            .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .build();
  }

  public static void initGlowingobsidian() {
    if (BlockTypes.GLOWINGOBSIDIAN_TYPE != null) return;
    BlockTypes.GLOWINGOBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockGlowingobsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN)
            .build();
  }

  public static void initGlowstone() {
    if (BlockTypes.GLOWSTONE_TYPE != null) return;
    BlockTypes.GLOWSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGlowstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOWSTONE)
            .build();
  }

  public static void initGoldBlock() {
    if (BlockTypes.GOLD_BLOCK_TYPE != null) return;
    BlockTypes.GOLD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockGoldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_BLOCK)
            .build();
  }

  public static void initGoldOre() {
    if (BlockTypes.GOLD_ORE_TYPE != null) return;
    BlockTypes.GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_ORE)
            .build();
  }

  public static void initGoldenRail() {
    if (BlockTypes.GOLDEN_RAIL_TYPE != null) return;
    BlockTypes.GOLDEN_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockGoldenRailBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLDEN_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }

  public static void initGranite() {
    if (BlockTypes.GRANITE_TYPE != null) return;
    BlockTypes.GRANITE_TYPE = BlockTypeBuilder
            .builder(BlockGraniteBehavior.class)
            .vanillaBlock(VanillaBlockId.GRANITE)
            .build();
  }

  public static void initGraniteStairs() {
    if (BlockTypes.GRANITE_STAIRS_TYPE != null) return;
    BlockTypes.GRANITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockGraniteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.GRANITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initGrass() {
    if (BlockTypes.GRASS_TYPE != null) return;
    BlockTypes.GRASS_TYPE = BlockTypeBuilder
            .builder(BlockGrassBehavior.class)
            .vanillaBlock(VanillaBlockId.GRASS)
            .build();
  }

  public static void initGrassPath() {
    if (BlockTypes.GRASS_PATH_TYPE != null) return;
    BlockTypes.GRASS_PATH_TYPE = BlockTypeBuilder
            .builder(BlockGrassPathBehavior.class)
            .vanillaBlock(VanillaBlockId.GRASS_PATH)
            .build();
  }

  public static void initGravel() {
    if (BlockTypes.GRAVEL_TYPE != null) return;
    BlockTypes.GRAVEL_TYPE = BlockTypeBuilder
            .builder(BlockGravelBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAVEL)
            .build();
  }

  public static void initGrayCandle() {
    if (BlockTypes.GRAY_CANDLE_TYPE != null) return;
    BlockTypes.GRAY_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockGrayCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initGrayCandleCake() {
    if (BlockTypes.GRAY_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockGrayCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initGrayCarpet() {
    if (BlockTypes.GRAY_CARPET_TYPE != null) return;
    BlockTypes.GRAY_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockGrayCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CARPET)
            .build();
  }

  public static void initGrayConcrete() {
    if (BlockTypes.GRAY_CONCRETE_TYPE != null) return;
    BlockTypes.GRAY_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockGrayConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CONCRETE)
            .build();
  }

  public static void initGrayConcretePowder() {
    if (BlockTypes.GRAY_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockGrayConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CONCRETE_POWDER)
            .build();
  }

  public static void initGrayGlazedTerracotta() {
    if (BlockTypes.GRAY_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.GRAY_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGrayGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initGrayShulkerBox() {
    if (BlockTypes.GRAY_SHULKER_BOX_TYPE != null) return;
    BlockTypes.GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGrayShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
            .build();
  }

  public static void initGrayStainedGlass() {
    if (BlockTypes.GRAY_STAINED_GLASS_TYPE != null) return;
    BlockTypes.GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS)
            .build();
  }

  public static void initGrayStainedGlassPane() {
    if (BlockTypes.GRAY_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS_PANE)
            .build();
  }

  public static void initGrayTerracotta() {
    if (BlockTypes.GRAY_TERRACOTTA_TYPE != null) return;
    BlockTypes.GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGrayTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_TERRACOTTA)
            .build();
  }

  public static void initGrayWool() {
    if (BlockTypes.GRAY_WOOL_TYPE != null) return;
    BlockTypes.GRAY_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockGrayWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_WOOL)
            .build();
  }

  public static void initGreenCandle() {
    if (BlockTypes.GREEN_CANDLE_TYPE != null) return;
    BlockTypes.GREEN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockGreenCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initGreenCandleCake() {
    if (BlockTypes.GREEN_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockGreenCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initGreenCarpet() {
    if (BlockTypes.GREEN_CARPET_TYPE != null) return;
    BlockTypes.GREEN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockGreenCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CARPET)
            .build();
  }

  public static void initGreenConcrete() {
    if (BlockTypes.GREEN_CONCRETE_TYPE != null) return;
    BlockTypes.GREEN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE)
            .build();
  }

  public static void initGreenConcretePowder() {
    if (BlockTypes.GREEN_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.GREEN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE_POWDER)
            .build();
  }

  public static void initGreenGlazedTerracotta() {
    if (BlockTypes.GREEN_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.GREEN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGreenGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initGreenShulkerBox() {
    if (BlockTypes.GREEN_SHULKER_BOX_TYPE != null) return;
    BlockTypes.GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGreenShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
            .build();
  }

  public static void initGreenStainedGlass() {
    if (BlockTypes.GREEN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.GREEN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGreenStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_STAINED_GLASS)
            .build();
  }

  public static void initGreenStainedGlassPane() {
    if (BlockTypes.GREEN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.GREEN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGreenStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initGreenTerracotta() {
    if (BlockTypes.GREEN_TERRACOTTA_TYPE != null) return;
    BlockTypes.GREEN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGreenTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_TERRACOTTA)
            .build();
  }

  public static void initGreenWool() {
    if (BlockTypes.GREEN_WOOL_TYPE != null) return;
    BlockTypes.GREEN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockGreenWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_WOOL)
            .build();
  }

  public static void initGrindstone() {
    if (BlockTypes.GRINDSTONE_TYPE != null) return;
    BlockTypes.GRINDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGrindstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GRINDSTONE)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initHangingRoots() {
    if (BlockTypes.HANGING_ROOTS_TYPE != null) return;
    BlockTypes.HANGING_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockHangingRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.HANGING_ROOTS)
            .build();
  }

  public static void initHardBlackStainedGlass() {
    if (BlockTypes.HARD_BLACK_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_BLACK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBlackStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLACK_STAINED_GLASS)
            .build();
  }

  public static void initHardBlackStainedGlassPane() {
    if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_BLACK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBlackStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLACK_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardBlueStainedGlass() {
    if (BlockTypes.HARD_BLUE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLUE_STAINED_GLASS)
            .build();
  }

  public static void initHardBlueStainedGlassPane() {
    if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLUE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardBrownStainedGlass() {
    if (BlockTypes.HARD_BROWN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_BROWN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBrownStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BROWN_STAINED_GLASS)
            .build();
  }

  public static void initHardBrownStainedGlassPane() {
    if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_BROWN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBrownStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BROWN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardCyanStainedGlass() {
    if (BlockTypes.HARD_CYAN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_CYAN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardCyanStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_CYAN_STAINED_GLASS)
            .build();
  }

  public static void initHardCyanStainedGlassPane() {
    if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_CYAN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardCyanStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_CYAN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardGlass() {
    if (BlockTypes.HARD_GLASS_TYPE != null) return;
    BlockTypes.HARD_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS)
            .build();
  }

  public static void initHardGlassPane() {
    if (BlockTypes.HARD_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE)
            .build();
  }

  public static void initHardGrayStainedGlass() {
    if (BlockTypes.HARD_GRAY_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GRAY_STAINED_GLASS)
            .build();
  }

  public static void initHardGrayStainedGlassPane() {
    if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GRAY_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardGreenStainedGlass() {
    if (BlockTypes.HARD_GREEN_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_GREEN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGreenStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GREEN_STAINED_GLASS)
            .build();
  }

  public static void initHardGreenStainedGlassPane() {
    if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_GREEN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGreenStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GREEN_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardLightBlueStainedGlass() {
    if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLightBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
            .build();
  }

  public static void initHardLightBlueStainedGlassPane() {
    if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardLightBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardLightGrayStainedGlass() {
    if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLightGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
            .build();
  }

  public static void initHardLightGrayStainedGlassPane() {
    if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardLightGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardLimeStainedGlass() {
    if (BlockTypes.HARD_LIME_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_LIME_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLimeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIME_STAINED_GLASS)
            .build();
  }

  public static void initHardLimeStainedGlassPane() {
    if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_LIME_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardLimeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIME_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardMagentaStainedGlass() {
    if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_MAGENTA_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardMagentaStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_MAGENTA_STAINED_GLASS)
            .build();
  }

  public static void initHardMagentaStainedGlassPane() {
    if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardMagentaStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardOrangeStainedGlass() {
    if (BlockTypes.HARD_ORANGE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_ORANGE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardOrangeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_ORANGE_STAINED_GLASS)
            .build();
  }

  public static void initHardOrangeStainedGlassPane() {
    if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardOrangeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_ORANGE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardPinkStainedGlass() {
    if (BlockTypes.HARD_PINK_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardPinkStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PINK_STAINED_GLASS)
            .build();
  }

  public static void initHardPinkStainedGlassPane() {
    if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_PINK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardPinkStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PINK_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardPurpleStainedGlass() {
    if (BlockTypes.HARD_PURPLE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_PURPLE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardPurpleStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PURPLE_STAINED_GLASS)
            .build();
  }

  public static void initHardPurpleStainedGlassPane() {
    if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardPurpleStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PURPLE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardRedStainedGlass() {
    if (BlockTypes.HARD_RED_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_RED_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardRedStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_RED_STAINED_GLASS)
            .build();
  }

  public static void initHardRedStainedGlassPane() {
    if (BlockTypes.HARD_RED_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_RED_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardRedStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_RED_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardWhiteStainedGlass() {
    if (BlockTypes.HARD_WHITE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_WHITE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardWhiteStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_WHITE_STAINED_GLASS)
            .build();
  }

  public static void initHardWhiteStainedGlassPane() {
    if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_WHITE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardWhiteStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_WHITE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardYellowStainedGlass() {
    if (BlockTypes.HARD_YELLOW_STAINED_GLASS_TYPE != null) return;
    BlockTypes.HARD_YELLOW_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardYellowStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_YELLOW_STAINED_GLASS)
            .build();
  }

  public static void initHardYellowStainedGlassPane() {
    if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardYellowStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_YELLOW_STAINED_GLASS_PANE)
            .build();
  }

  public static void initHardenedClay() {
    if (BlockTypes.HARDENED_CLAY_TYPE != null) return;
    BlockTypes.HARDENED_CLAY_TYPE = BlockTypeBuilder
            .builder(BlockHardenedClayBehavior.class)
            .vanillaBlock(VanillaBlockId.HARDENED_CLAY)
            .build();
  }

  public static void initHayBlock() {
    if (BlockTypes.HAY_BLOCK_TYPE != null) return;
    BlockTypes.HAY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHayBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HAY_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initHeavyWeightedPressurePlate() {
    if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockHeavyWeightedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initHoneyBlock() {
    if (BlockTypes.HONEY_BLOCK_TYPE != null) return;
    BlockTypes.HONEY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHoneyBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HONEY_BLOCK)
            .build();
  }

  public static void initHoneycombBlock() {
    if (BlockTypes.HONEYCOMB_BLOCK_TYPE != null) return;
    BlockTypes.HONEYCOMB_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHoneycombBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HONEYCOMB_BLOCK)
            .build();
  }

  public static void initHopper() {
    if (BlockTypes.HOPPER_TYPE != null) return;
    BlockTypes.HOPPER_TYPE = BlockTypeBuilder
            .builder(BlockHopperBehavior.class)
            .vanillaBlock(VanillaBlockId.HOPPER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
  }

  public static void initHornCoral() {
    if (BlockTypes.HORN_CORAL_TYPE != null) return;
    BlockTypes.HORN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockHornCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.HORN_CORAL)
            .build();
  }

  public static void initIce() {
    if (BlockTypes.ICE_TYPE != null) return;
    BlockTypes.ICE_TYPE = BlockTypeBuilder
            .builder(BlockIceBehavior.class)
            .vanillaBlock(VanillaBlockId.ICE)
            .build();
  }

  public static void initInfestedDeepslate() {
    if (BlockTypes.INFESTED_DEEPSLATE_TYPE != null) return;
    BlockTypes.INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockInfestedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initInfoUpdate() {
    if (BlockTypes.INFO_UPDATE_TYPE != null) return;
    BlockTypes.INFO_UPDATE_TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE)
            .build();
  }

  public static void initInfoUpdate2() {
    if (BlockTypes.INFO_UPDATE2_TYPE != null) return;
    BlockTypes.INFO_UPDATE2_TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdate2Behavior.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE2)
            .build();
  }

  public static void initInvisibleBedrock() {
    if (BlockTypes.INVISIBLE_BEDROCK_TYPE != null) return;
    BlockTypes.INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
            .builder(BlockInvisibleBedrockBehavior.class)
            .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK)
            .build();
  }

  public static void initIronBars() {
    if (BlockTypes.IRON_BARS_TYPE != null) return;
    BlockTypes.IRON_BARS_TYPE = BlockTypeBuilder
            .builder(BlockIronBarsBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_BARS)
            .build();
  }

  public static void initIronBlock() {
    if (BlockTypes.IRON_BLOCK_TYPE != null) return;
    BlockTypes.IRON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockIronBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_BLOCK)
            .build();
  }

  public static void initIronDoor() {
    if (BlockTypes.IRON_DOOR_TYPE != null) return;
    BlockTypes.IRON_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockIronDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initIronOre() {
    if (BlockTypes.IRON_ORE_TYPE != null) return;
    BlockTypes.IRON_ORE_TYPE = BlockTypeBuilder
            .builder(BlockIronOreBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_ORE)
            .build();
  }

  public static void initIronTrapdoor() {
    if (BlockTypes.IRON_TRAPDOOR_TYPE != null) return;
    BlockTypes.IRON_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockIronTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initJigsaw() {
    if (BlockTypes.JIGSAW_TYPE != null) return;
    BlockTypes.JIGSAW_TYPE = BlockTypeBuilder
            .builder(BlockJigsawBehavior.class)
            .vanillaBlock(VanillaBlockId.JIGSAW)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
            .build();
  }

  public static void initJukebox() {
    if (BlockTypes.JUKEBOX_TYPE != null) return;
    BlockTypes.JUKEBOX_TYPE = BlockTypeBuilder
            .builder(BlockJukeboxBehavior.class)
            .vanillaBlock(VanillaBlockId.JUKEBOX)
            .build();
  }

  public static void initJungleButton() {
    if (BlockTypes.JUNGLE_BUTTON_TYPE != null) return;
    BlockTypes.JUNGLE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockJungleButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initJungleDoor() {
    if (BlockTypes.JUNGLE_DOOR_TYPE != null) return;
    BlockTypes.JUNGLE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockJungleDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initJungleFence() {
    if (BlockTypes.JUNGLE_FENCE_TYPE != null) return;
    BlockTypes.JUNGLE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockJungleFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_FENCE)
            .build();
  }

  public static void initJungleFenceGate() {
    if (BlockTypes.JUNGLE_FENCE_GATE_TYPE != null) return;
    BlockTypes.JUNGLE_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockJungleFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initJungleHangingSign() {
    if (BlockTypes.JUNGLE_HANGING_SIGN_TYPE != null) return;
    BlockTypes.JUNGLE_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockJungleHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initJungleLog() {
    if (BlockTypes.JUNGLE_LOG_TYPE != null) return;
    BlockTypes.JUNGLE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockJungleLogBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initJunglePlanks() {
    if (BlockTypes.JUNGLE_PLANKS_TYPE != null) return;
    BlockTypes.JUNGLE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockJunglePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_PLANKS)
            .build();
  }

  public static void initJunglePressurePlate() {
    if (BlockTypes.JUNGLE_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockJunglePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initJungleStairs() {
    if (BlockTypes.JUNGLE_STAIRS_TYPE != null) return;
    BlockTypes.JUNGLE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockJungleStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initJungleStandingSign() {
    if (BlockTypes.JUNGLE_STANDING_SIGN_TYPE != null) return;
    BlockTypes.JUNGLE_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockJungleStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initJungleTrapdoor() {
    if (BlockTypes.JUNGLE_TRAPDOOR_TYPE != null) return;
    BlockTypes.JUNGLE_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockJungleTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initJungleWallSign() {
    if (BlockTypes.JUNGLE_WALL_SIGN_TYPE != null) return;
    BlockTypes.JUNGLE_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockJungleWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initKelp() {
    if (BlockTypes.KELP_TYPE != null) return;
    BlockTypes.KELP_TYPE = BlockTypeBuilder
            .builder(BlockKelpBehavior.class)
            .vanillaBlock(VanillaBlockId.KELP)
            .setProperties(VanillaBlockPropertyTypes.KELP_AGE)
            .build();
  }

  public static void initLadder() {
    if (BlockTypes.LADDER_TYPE != null) return;
    BlockTypes.LADDER_TYPE = BlockTypeBuilder
            .builder(BlockLadderBehavior.class)
            .vanillaBlock(VanillaBlockId.LADDER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initLantern() {
    if (BlockTypes.LANTERN_TYPE != null) return;
    BlockTypes.LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.LANTERN)
            .setProperties(VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initLapisBlock() {
    if (BlockTypes.LAPIS_BLOCK_TYPE != null) return;
    BlockTypes.LAPIS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLapisBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_BLOCK)
            .build();
  }

  public static void initLapisOre() {
    if (BlockTypes.LAPIS_ORE_TYPE != null) return;
    BlockTypes.LAPIS_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLapisOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_ORE)
            .build();
  }

  public static void initLargeAmethystBud() {
    if (BlockTypes.LARGE_AMETHYST_BUD_TYPE != null) return;
    BlockTypes.LARGE_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockLargeAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }

  public static void initLava() {
    if (BlockTypes.LAVA_TYPE != null) return;
    BlockTypes.LAVA_TYPE = BlockTypeBuilder
            .builder(BlockLavaBehavior.class)
            .vanillaBlock(VanillaBlockId.LAVA)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }

  public static void initLeaves() {
    if (BlockTypes.LEAVES_TYPE != null) return;
    BlockTypes.LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.LEAVES)
            .setProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initLeaves2() {
    if (BlockTypes.LEAVES2_TYPE != null) return;
    BlockTypes.LEAVES2_TYPE = BlockTypeBuilder
            .builder(BlockLeaves2Behavior.class)
            .vanillaBlock(VanillaBlockId.LEAVES2)
            .setProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initLectern() {
    if (BlockTypes.LECTERN_TYPE != null) return;
    BlockTypes.LECTERN_TYPE = BlockTypeBuilder
            .builder(BlockLecternBehavior.class)
            .vanillaBlock(VanillaBlockId.LECTERN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initLever() {
    if (BlockTypes.LEVER_TYPE != null) return;
    BlockTypes.LEVER_TYPE = BlockTypeBuilder
            .builder(BlockLeverBehavior.class)
            .vanillaBlock(VanillaBlockId.LEVER)
            .setProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initLightBlock() {
    if (BlockTypes.LIGHT_BLOCK_TYPE != null) return;
    BlockTypes.LIGHT_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLightBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
            .build();
  }

  public static void initLightBlueCandle() {
    if (BlockTypes.LIGHT_BLUE_CANDLE_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLightBlueCandleCake() {
    if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLightBlueCarpet() {
    if (BlockTypes.LIGHT_BLUE_CARPET_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET)
            .build();
  }

  public static void initLightBlueConcrete() {
    if (BlockTypes.LIGHT_BLUE_CONCRETE_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE)
            .build();
  }

  public static void initLightBlueConcretePowder() {
    if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE_POWDER)
            .build();
  }

  public static void initLightBlueGlazedTerracotta() {
    if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initLightBlueShulkerBox() {
    if (BlockTypes.LIGHT_BLUE_SHULKER_BOX_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX)
            .build();
  }

  public static void initLightBlueStainedGlass() {
    if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_STAINED_GLASS)
            .build();
  }

  public static void initLightBlueStainedGlassPane() {
    if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initLightBlueTerracotta() {
    if (BlockTypes.LIGHT_BLUE_TERRACOTTA_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_TERRACOTTA)
            .build();
  }

  public static void initLightBlueWool() {
    if (BlockTypes.LIGHT_BLUE_WOOL_TYPE != null) return;
    BlockTypes.LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL)
            .build();
  }

  public static void initLightGrayCandle() {
    if (BlockTypes.LIGHT_GRAY_CANDLE_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLightGrayCandleCake() {
    if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLightGrayCarpet() {
    if (BlockTypes.LIGHT_GRAY_CARPET_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET)
            .build();
  }

  public static void initLightGrayConcrete() {
    if (BlockTypes.LIGHT_GRAY_CONCRETE_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE)
            .build();
  }

  public static void initLightGrayConcretePowder() {
    if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE_POWDER)
            .build();
  }

  public static void initLightGrayShulkerBox() {
    if (BlockTypes.LIGHT_GRAY_SHULKER_BOX_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
            .build();
  }

  public static void initLightGrayStainedGlass() {
    if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_STAINED_GLASS)
            .build();
  }

  public static void initLightGrayStainedGlassPane() {
    if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }

  public static void initLightGrayTerracotta() {
    if (BlockTypes.LIGHT_GRAY_TERRACOTTA_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_TERRACOTTA)
            .build();
  }

  public static void initLightGrayWool() {
    if (BlockTypes.LIGHT_GRAY_WOOL_TYPE != null) return;
    BlockTypes.LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL)
            .build();
  }

  public static void initLightWeightedPressurePlate() {
    if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockLightWeightedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initLightningRod() {
    if (BlockTypes.LIGHTNING_ROD_TYPE != null) return;
    BlockTypes.LIGHTNING_ROD_TYPE = BlockTypeBuilder
            .builder(BlockLightningRodBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initLimeCandle() {
    if (BlockTypes.LIME_CANDLE_TYPE != null) return;
    BlockTypes.LIME_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLimeCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLimeCandleCake() {
    if (BlockTypes.LIME_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.LIME_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLimeCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initLimeCarpet() {
    if (BlockTypes.LIME_CARPET_TYPE != null) return;
    BlockTypes.LIME_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLimeCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CARPET)
            .build();
  }

  public static void initLimeConcrete() {
    if (BlockTypes.LIME_CONCRETE_TYPE != null) return;
    BlockTypes.LIME_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLimeConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CONCRETE)
            .build();
  }

  public static void initLimeConcretePowder() {
    if (BlockTypes.LIME_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.LIME_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLimeConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CONCRETE_POWDER)
            .build();
  }

  public static void initLimeGlazedTerracotta() {
    if (BlockTypes.LIME_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.LIME_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLimeGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initLimeShulkerBox() {
    if (BlockTypes.LIME_SHULKER_BOX_TYPE != null) return;
    BlockTypes.LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLimeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
            .build();
  }

  public static void initLimeStainedGlass() {
    if (BlockTypes.LIME_STAINED_GLASS_TYPE != null) return;
    BlockTypes.LIME_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLimeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_STAINED_GLASS)
            .build();
  }

  public static void initLimeStainedGlassPane() {
    if (BlockTypes.LIME_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.LIME_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLimeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_STAINED_GLASS_PANE)
            .build();
  }

  public static void initLimeTerracotta() {
    if (BlockTypes.LIME_TERRACOTTA_TYPE != null) return;
    BlockTypes.LIME_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLimeTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_TERRACOTTA)
            .build();
  }

  public static void initLimeWool() {
    if (BlockTypes.LIME_WOOL_TYPE != null) return;
    BlockTypes.LIME_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLimeWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_WOOL)
            .build();
  }

  public static void initLitBlastFurnace() {
    if (BlockTypes.LIT_BLAST_FURNACE_TYPE != null) return;
    BlockTypes.LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockLitBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initLitDeepslateRedstoneOre() {
    if (BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE_TYPE != null) return;
    BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLitDeepslateRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE)
            .build();
  }

  public static void initLitFurnace() {
    if (BlockTypes.LIT_FURNACE_TYPE != null) return;
    BlockTypes.LIT_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockLitFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initLitPumpkin() {
    if (BlockTypes.LIT_PUMPKIN_TYPE != null) return;
    BlockTypes.LIT_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockLitPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initLitRedstoneLamp() {
    if (BlockTypes.LIT_REDSTONE_LAMP_TYPE != null) return;
    BlockTypes.LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneLampBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP)
            .build();
  }

  public static void initLitRedstoneOre() {
    if (BlockTypes.LIT_REDSTONE_ORE_TYPE != null) return;
    BlockTypes.LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE)
            .build();
  }

  public static void initLitSmoker() {
    if (BlockTypes.LIT_SMOKER_TYPE != null) return;
    BlockTypes.LIT_SMOKER_TYPE = BlockTypeBuilder
            .builder(BlockLitSmokerBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_SMOKER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initLodestone() {
    if (BlockTypes.LODESTONE_TYPE != null) return;
    BlockTypes.LODESTONE_TYPE = BlockTypeBuilder
            .builder(BlockLodestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.LODESTONE)
            .build();
  }

  public static void initLoom() {
    if (BlockTypes.LOOM_TYPE != null) return;
    BlockTypes.LOOM_TYPE = BlockTypeBuilder
            .builder(BlockLoomBehavior.class)
            .vanillaBlock(VanillaBlockId.LOOM)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }

  public static void initMagentaCandle() {
    if (BlockTypes.MAGENTA_CANDLE_TYPE != null) return;
    BlockTypes.MAGENTA_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initMagentaCandleCake() {
    if (BlockTypes.MAGENTA_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.MAGENTA_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initMagentaCarpet() {
    if (BlockTypes.MAGENTA_CARPET_TYPE != null) return;
    BlockTypes.MAGENTA_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CARPET)
            .build();
  }

  public static void initMagentaConcrete() {
    if (BlockTypes.MAGENTA_CONCRETE_TYPE != null) return;
    BlockTypes.MAGENTA_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE)
            .build();
  }

  public static void initMagentaConcretePowder() {
    if (BlockTypes.MAGENTA_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.MAGENTA_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockMagentaConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE_POWDER)
            .build();
  }

  public static void initMagentaGlazedTerracotta() {
    if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.MAGENTA_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockMagentaGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initMagentaShulkerBox() {
    if (BlockTypes.MAGENTA_SHULKER_BOX_TYPE != null) return;
    BlockTypes.MAGENTA_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockMagentaShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX)
            .build();
  }

  public static void initMagentaStainedGlass() {
    if (BlockTypes.MAGENTA_STAINED_GLASS_TYPE != null) return;
    BlockTypes.MAGENTA_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockMagentaStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_STAINED_GLASS)
            .build();
  }

  public static void initMagentaStainedGlassPane() {
    if (BlockTypes.MAGENTA_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.MAGENTA_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_STAINED_GLASS_PANE)
            .build();
  }

  public static void initMagentaTerracotta() {
    if (BlockTypes.MAGENTA_TERRACOTTA_TYPE != null) return;
    BlockTypes.MAGENTA_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockMagentaTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_TERRACOTTA)
            .build();
  }

  public static void initMagentaWool() {
    if (BlockTypes.MAGENTA_WOOL_TYPE != null) return;
    BlockTypes.MAGENTA_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockMagentaWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_WOOL)
            .build();
  }

  public static void initMagma() {
    if (BlockTypes.MAGMA_TYPE != null) return;
    BlockTypes.MAGMA_TYPE = BlockTypeBuilder
            .builder(BlockMagmaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGMA)
            .build();
  }

  public static void initMangroveButton() {
    if (BlockTypes.MANGROVE_BUTTON_TYPE != null) return;
    BlockTypes.MANGROVE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockMangroveButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initMangroveDoor() {
    if (BlockTypes.MANGROVE_DOOR_TYPE != null) return;
    BlockTypes.MANGROVE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockMangroveDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initMangroveDoubleSlab() {
    if (BlockTypes.MANGROVE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.MANGROVE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMangroveDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initMangroveFence() {
    if (BlockTypes.MANGROVE_FENCE_TYPE != null) return;
    BlockTypes.MANGROVE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockMangroveFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_FENCE)
            .build();
  }

  public static void initMangroveFenceGate() {
    if (BlockTypes.MANGROVE_FENCE_GATE_TYPE != null) return;
    BlockTypes.MANGROVE_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockMangroveFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initMangroveHangingSign() {
    if (BlockTypes.MANGROVE_HANGING_SIGN_TYPE != null) return;
    BlockTypes.MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockMangroveHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initMangroveLeaves() {
    if (BlockTypes.MANGROVE_LEAVES_TYPE != null) return;
    BlockTypes.MANGROVE_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockMangroveLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }

  public static void initMangroveLog() {
    if (BlockTypes.MANGROVE_LOG_TYPE != null) return;
    BlockTypes.MANGROVE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockMangroveLogBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initMangrovePlanks() {
    if (BlockTypes.MANGROVE_PLANKS_TYPE != null) return;
    BlockTypes.MANGROVE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS)
            .build();
  }

  public static void initMangrovePressurePlate() {
    if (BlockTypes.MANGROVE_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initMangrovePropagule() {
    if (BlockTypes.MANGROVE_PROPAGULE_TYPE != null) return;
    BlockTypes.MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePropaguleBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE)
            .setProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
            .build();
  }

  public static void initMangroveRoots() {
    if (BlockTypes.MANGROVE_ROOTS_TYPE != null) return;
    BlockTypes.MANGROVE_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockMangroveRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS)
            .build();
  }

  public static void initMangroveSlab() {
    if (BlockTypes.MANGROVE_SLAB_TYPE != null) return;
    BlockTypes.MANGROVE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMangroveSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initMangroveStairs() {
    if (BlockTypes.MANGROVE_STAIRS_TYPE != null) return;
    BlockTypes.MANGROVE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockMangroveStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initMangroveStandingSign() {
    if (BlockTypes.MANGROVE_STANDING_SIGN_TYPE != null) return;
    BlockTypes.MANGROVE_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockMangroveStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initMangroveTrapdoor() {
    if (BlockTypes.MANGROVE_TRAPDOOR_TYPE != null) return;
    BlockTypes.MANGROVE_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockMangroveTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initMangroveWallSign() {
    if (BlockTypes.MANGROVE_WALL_SIGN_TYPE != null) return;
    BlockTypes.MANGROVE_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockMangroveWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initMangroveWood() {
    if (BlockTypes.MANGROVE_WOOD_TYPE != null) return;
    BlockTypes.MANGROVE_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockMangroveWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
  }

  public static void initMediumAmethystBud() {
    if (BlockTypes.MEDIUM_AMETHYST_BUD_TYPE != null) return;
    BlockTypes.MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockMediumAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }

  public static void initMelonBlock() {
    if (BlockTypes.MELON_BLOCK_TYPE != null) return;
    BlockTypes.MELON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMelonBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MELON_BLOCK)
            .build();
  }

  public static void initMelonStem() {
    if (BlockTypes.MELON_STEM_TYPE != null) return;
    BlockTypes.MELON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockMelonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.MELON_STEM)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initMobSpawner() {
    if (BlockTypes.MOB_SPAWNER_TYPE != null) return;
    BlockTypes.MOB_SPAWNER_TYPE = BlockTypeBuilder
            .builder(BlockMobSpawnerBehavior.class)
            .vanillaBlock(VanillaBlockId.MOB_SPAWNER)
            .build();
  }

  public static void initMonsterEgg() {
    if (BlockTypes.MONSTER_EGG_TYPE != null) return;
    BlockTypes.MONSTER_EGG_TYPE = BlockTypeBuilder
            .builder(BlockMonsterEggBehavior.class)
            .vanillaBlock(VanillaBlockId.MONSTER_EGG)
            .setProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
            .build();
  }

  public static void initMossBlock() {
    if (BlockTypes.MOSS_BLOCK_TYPE != null) return;
    BlockTypes.MOSS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMossBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSS_BLOCK)
            .build();
  }

  public static void initMossCarpet() {
    if (BlockTypes.MOSS_CARPET_TYPE != null) return;
    BlockTypes.MOSS_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockMossCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSS_CARPET)
            .build();
  }

  public static void initMossyCobblestone() {
    if (BlockTypes.MOSSY_COBBLESTONE_TYPE != null) return;
    BlockTypes.MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
            .builder(BlockMossyCobblestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE)
            .build();
  }

  public static void initMossyCobblestoneStairs() {
    if (BlockTypes.MOSSY_COBBLESTONE_STAIRS_TYPE != null) return;
    BlockTypes.MOSSY_COBBLESTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockMossyCobblestoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initMossyStoneBrickStairs() {
    if (BlockTypes.MOSSY_STONE_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.MOSSY_STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockMossyStoneBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initMovingBlock() {
    if (BlockTypes.MOVING_BLOCK_TYPE != null) return;
    BlockTypes.MOVING_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMovingBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOVING_BLOCK)
            .build();
  }

  public static void initMud() {
    if (BlockTypes.MUD_TYPE != null) return;
    BlockTypes.MUD_TYPE = BlockTypeBuilder
            .builder(BlockMudBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD)
            .build();
  }

  public static void initMudBrickDoubleSlab() {
    if (BlockTypes.MUD_BRICK_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.MUD_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMudBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initMudBrickSlab() {
    if (BlockTypes.MUD_BRICK_SLAB_TYPE != null) return;
    BlockTypes.MUD_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMudBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initMudBrickStairs() {
    if (BlockTypes.MUD_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.MUD_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockMudBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initMudBrickWall() {
    if (BlockTypes.MUD_BRICK_WALL_TYPE != null) return;
    BlockTypes.MUD_BRICK_WALL_TYPE = BlockTypeBuilder
            .builder(BlockMudBrickWallBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICK_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initMudBricks() {
    if (BlockTypes.MUD_BRICKS_TYPE != null) return;
    BlockTypes.MUD_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockMudBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICKS)
            .build();
  }

  public static void initMuddyMangroveRoots() {
    if (BlockTypes.MUDDY_MANGROVE_ROOTS_TYPE != null) return;
    BlockTypes.MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockMuddyMangroveRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initMycelium() {
    if (BlockTypes.MYCELIUM_TYPE != null) return;
    BlockTypes.MYCELIUM_TYPE = BlockTypeBuilder
            .builder(BlockMyceliumBehavior.class)
            .vanillaBlock(VanillaBlockId.MYCELIUM)
            .build();
  }

  public static void initNetherBrick() {
    if (BlockTypes.NETHER_BRICK_TYPE != null) return;
    BlockTypes.NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK)
            .build();
  }

  public static void initNetherBrickFence() {
    if (BlockTypes.NETHER_BRICK_FENCE_TYPE != null) return;
    BlockTypes.NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE)
            .build();
  }

  public static void initNetherBrickStairs() {
    if (BlockTypes.NETHER_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initNetherGoldOre() {
    if (BlockTypes.NETHER_GOLD_ORE_TYPE != null) return;
    BlockTypes.NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockNetherGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE)
            .build();
  }

  public static void initNetherSprouts() {
    if (BlockTypes.NETHER_SPROUTS_TYPE != null) return;
    BlockTypes.NETHER_SPROUTS_TYPE = BlockTypeBuilder
            .builder(BlockNetherSproutsBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_SPROUTS)
            .build();
  }

  public static void initNetherWart() {
    if (BlockTypes.NETHER_WART_TYPE != null) return;
    BlockTypes.NETHER_WART_TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART)
            .setProperties(VanillaBlockPropertyTypes.AGE_4)
            .build();
  }

  public static void initNetherWartBlock() {
    if (BlockTypes.NETHER_WART_BLOCK_TYPE != null) return;
    BlockTypes.NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
            .build();
  }

  public static void initNetheriteBlock() {
    if (BlockTypes.NETHERITE_BLOCK_TYPE != null) return;
    BlockTypes.NETHERITE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNetheriteBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK)
            .build();
  }

  public static void initNetherrack() {
    if (BlockTypes.NETHERRACK_TYPE != null) return;
    BlockTypes.NETHERRACK_TYPE = BlockTypeBuilder
            .builder(BlockNetherrackBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERRACK)
            .build();
  }

  public static void initNetherreactor() {
    if (BlockTypes.NETHERREACTOR_TYPE != null) return;
    BlockTypes.NETHERREACTOR_TYPE = BlockTypeBuilder
            .builder(BlockNetherreactorBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERREACTOR)
            .build();
  }

  public static void initNormalStoneStairs() {
    if (BlockTypes.NORMAL_STONE_STAIRS_TYPE != null) return;
    BlockTypes.NORMAL_STONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockNormalStoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initNoteblock() {
    if (BlockTypes.NOTEBLOCK_TYPE != null) return;
    BlockTypes.NOTEBLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNoteblockBehavior.class)
            .vanillaBlock(VanillaBlockId.NOTEBLOCK)
            .build();
  }

  public static void initOakFence() {
    if (BlockTypes.OAK_FENCE_TYPE != null) return;
    BlockTypes.OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_FENCE)
            .build();
  }

  public static void initOakHangingSign() {
    if (BlockTypes.OAK_HANGING_SIGN_TYPE != null) return;
    BlockTypes.OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockOakHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initOakLog() {
    if (BlockTypes.OAK_LOG_TYPE != null) return;
    BlockTypes.OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initOakPlanks() {
    if (BlockTypes.OAK_PLANKS_TYPE != null) return;
    BlockTypes.OAK_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockOakPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_PLANKS)
            .build();
  }

  public static void initOakStairs() {
    if (BlockTypes.OAK_STAIRS_TYPE != null) return;
    BlockTypes.OAK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockOakStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initObserver() {
    if (BlockTypes.OBSERVER_TYPE != null) return;
    BlockTypes.OBSERVER_TYPE = BlockTypeBuilder
            .builder(BlockObserverBehavior.class)
            .vanillaBlock(VanillaBlockId.OBSERVER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initObsidian() {
    if (BlockTypes.OBSIDIAN_TYPE != null) return;
    BlockTypes.OBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockObsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.OBSIDIAN)
            .build();
  }

  public static void initOchreFroglight() {
    if (BlockTypes.OCHRE_FROGLIGHT_TYPE != null) return;
    BlockTypes.OCHRE_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockOchreFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initOrangeCandle() {
    if (BlockTypes.ORANGE_CANDLE_TYPE != null) return;
    BlockTypes.ORANGE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initOrangeCandleCake() {
    if (BlockTypes.ORANGE_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.ORANGE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initOrangeCarpet() {
    if (BlockTypes.ORANGE_CARPET_TYPE != null) return;
    BlockTypes.ORANGE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CARPET)
            .build();
  }

  public static void initOrangeConcrete() {
    if (BlockTypes.ORANGE_CONCRETE_TYPE != null) return;
    BlockTypes.ORANGE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE)
            .build();
  }

  public static void initOrangeConcretePowder() {
    if (BlockTypes.ORANGE_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.ORANGE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockOrangeConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE_POWDER)
            .build();
  }

  public static void initOrangeGlazedTerracotta() {
    if (BlockTypes.ORANGE_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.ORANGE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockOrangeGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initOrangeShulkerBox() {
    if (BlockTypes.ORANGE_SHULKER_BOX_TYPE != null) return;
    BlockTypes.ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockOrangeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
            .build();
  }

  public static void initOrangeStainedGlass() {
    if (BlockTypes.ORANGE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.ORANGE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockOrangeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_STAINED_GLASS)
            .build();
  }

  public static void initOrangeStainedGlassPane() {
    if (BlockTypes.ORANGE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.ORANGE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initOrangeTerracotta() {
    if (BlockTypes.ORANGE_TERRACOTTA_TYPE != null) return;
    BlockTypes.ORANGE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockOrangeTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_TERRACOTTA)
            .build();
  }

  public static void initOrangeWool() {
    if (BlockTypes.ORANGE_WOOL_TYPE != null) return;
    BlockTypes.ORANGE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockOrangeWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_WOOL)
            .build();
  }

  public static void initOxidizedChiseledCopper() {
    if (BlockTypes.OXIDIZED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.OXIDIZED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CHISELED_COPPER)
            .build();
  }

  public static void initOxidizedCopper() {
    if (BlockTypes.OXIDIZED_COPPER_TYPE != null) return;
    BlockTypes.OXIDIZED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER)
            .build();
  }

  public static void initOxidizedCopperBulb() {
    if (BlockTypes.OXIDIZED_COPPER_BULB_TYPE != null) return;
    BlockTypes.OXIDIZED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initOxidizedCopperDoor() {
    if (BlockTypes.OXIDIZED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.OXIDIZED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initOxidizedCopperGrate() {
    if (BlockTypes.OXIDIZED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_GRATE)
            .build();
  }

  public static void initOxidizedCopperTrapdoor() {
    if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.OXIDIZED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initOxidizedCutCopper() {
    if (BlockTypes.OXIDIZED_CUT_COPPER_TYPE != null) return;
    BlockTypes.OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER)
            .build();
  }

  public static void initOxidizedCutCopperSlab() {
    if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initOxidizedCutCopperStairs() {
    if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initOxidizedDoubleCutCopperSlab() {
    if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPackedIce() {
    if (BlockTypes.PACKED_ICE_TYPE != null) return;
    BlockTypes.PACKED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockPackedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.PACKED_ICE)
            .build();
  }

  public static void initPackedMud() {
    if (BlockTypes.PACKED_MUD_TYPE != null) return;
    BlockTypes.PACKED_MUD_TYPE = BlockTypeBuilder
            .builder(BlockPackedMudBehavior.class)
            .vanillaBlock(VanillaBlockId.PACKED_MUD)
            .build();
  }

  public static void initPearlescentFroglight() {
    if (BlockTypes.PEARLESCENT_FROGLIGHT_TYPE != null) return;
    BlockTypes.PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockPearlescentFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initPinkCandle() {
    if (BlockTypes.PINK_CANDLE_TYPE != null) return;
    BlockTypes.PINK_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initPinkCandleCake() {
    if (BlockTypes.PINK_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initPinkCarpet() {
    if (BlockTypes.PINK_CARPET_TYPE != null) return;
    BlockTypes.PINK_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockPinkCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CARPET)
            .build();
  }

  public static void initPinkConcrete() {
    if (BlockTypes.PINK_CONCRETE_TYPE != null) return;
    BlockTypes.PINK_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockPinkConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CONCRETE)
            .build();
  }

  public static void initPinkConcretePowder() {
    if (BlockTypes.PINK_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.PINK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockPinkConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CONCRETE_POWDER)
            .build();
  }

  public static void initPinkGlazedTerracotta() {
    if (BlockTypes.PINK_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.PINK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPinkGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initPinkPetals() {
    if (BlockTypes.PINK_PETALS_TYPE != null) return;
    BlockTypes.PINK_PETALS_TYPE = BlockTypeBuilder
            .builder(BlockPinkPetalsBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_PETALS)
            .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initPinkShulkerBox() {
    if (BlockTypes.PINK_SHULKER_BOX_TYPE != null) return;
    BlockTypes.PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPinkShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
            .build();
  }

  public static void initPinkStainedGlass() {
    if (BlockTypes.PINK_STAINED_GLASS_TYPE != null) return;
    BlockTypes.PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockPinkStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS)
            .build();
  }

  public static void initPinkStainedGlassPane() {
    if (BlockTypes.PINK_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.PINK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockPinkStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS_PANE)
            .build();
  }

  public static void initPinkTerracotta() {
    if (BlockTypes.PINK_TERRACOTTA_TYPE != null) return;
    BlockTypes.PINK_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPinkTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_TERRACOTTA)
            .build();
  }

  public static void initPinkWool() {
    if (BlockTypes.PINK_WOOL_TYPE != null) return;
    BlockTypes.PINK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockPinkWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_WOOL)
            .build();
  }

  public static void initPiston() {
    if (BlockTypes.PISTON_TYPE != null) return;
    BlockTypes.PISTON_TYPE = BlockTypeBuilder
            .builder(BlockPistonBehavior.class)
            .vanillaBlock(VanillaBlockId.PISTON)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initPistonArmCollision() {
    if (BlockTypes.PISTON_ARM_COLLISION_TYPE != null) return;
    BlockTypes.PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
            .builder(BlockPistonArmCollisionBehavior.class)
            .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initPitcherCrop() {
    if (BlockTypes.PITCHER_CROP_TYPE != null) return;
    BlockTypes.PITCHER_CROP_TYPE = BlockTypeBuilder
            .builder(BlockPitcherCropBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_CROP)
            .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initPitcherPlant() {
    if (BlockTypes.PITCHER_PLANT_TYPE != null) return;
    BlockTypes.PITCHER_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockPitcherPlantBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_PLANT)
            .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initPodzol() {
    if (BlockTypes.PODZOL_TYPE != null) return;
    BlockTypes.PODZOL_TYPE = BlockTypeBuilder
            .builder(BlockPodzolBehavior.class)
            .vanillaBlock(VanillaBlockId.PODZOL)
            .build();
  }

  public static void initPointedDripstone() {
    if (BlockTypes.POINTED_DRIPSTONE_TYPE != null) return;
    BlockTypes.POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPointedDripstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
            .setProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initPolishedAndesite() {
    if (BlockTypes.POLISHED_ANDESITE_TYPE != null) return;
    BlockTypes.POLISHED_ANDESITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedAndesiteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE)
            .build();
  }

  public static void initPolishedAndesiteStairs() {
    if (BlockTypes.POLISHED_ANDESITE_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_ANDESITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedAndesiteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedBasalt() {
    if (BlockTypes.POLISHED_BASALT_TYPE != null) return;
    BlockTypes.POLISHED_BASALT_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BASALT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initPolishedBlackstone() {
    if (BlockTypes.POLISHED_BLACKSTONE_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE)
            .build();
  }

  public static void initPolishedBlackstoneBrickDoubleSlab() {
    if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedBlackstoneBrickSlab() {
    if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedBlackstoneBrickStairs() {
    if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedBlackstoneBrickWall() {
    if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initPolishedBlackstoneBricks() {
    if (BlockTypes.POLISHED_BLACKSTONE_BRICKS_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICKS)
            .build();
  }

  public static void initPolishedBlackstoneButton() {
    if (BlockTypes.POLISHED_BLACKSTONE_BUTTON_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initPolishedBlackstoneDoubleSlab() {
    if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedBlackstonePressurePlate() {
    if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initPolishedBlackstoneSlab() {
    if (BlockTypes.POLISHED_BLACKSTONE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedBlackstoneStairs() {
    if (BlockTypes.POLISHED_BLACKSTONE_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedBlackstoneWall() {
    if (BlockTypes.POLISHED_BLACKSTONE_WALL_TYPE != null) return;
    BlockTypes.POLISHED_BLACKSTONE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initPolishedDeepslate() {
    if (BlockTypes.POLISHED_DEEPSLATE_TYPE != null) return;
    BlockTypes.POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE)
            .build();
  }

  public static void initPolishedDeepslateDoubleSlab() {
    if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedDeepslateSlab() {
    if (BlockTypes.POLISHED_DEEPSLATE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedDeepslateStairs() {
    if (BlockTypes.POLISHED_DEEPSLATE_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedDeepslateWall() {
    if (BlockTypes.POLISHED_DEEPSLATE_WALL_TYPE != null) return;
    BlockTypes.POLISHED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initPolishedDiorite() {
    if (BlockTypes.POLISHED_DIORITE_TYPE != null) return;
    BlockTypes.POLISHED_DIORITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDioriteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DIORITE)
            .build();
  }

  public static void initPolishedDioriteStairs() {
    if (BlockTypes.POLISHED_DIORITE_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_DIORITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDioriteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedGranite() {
    if (BlockTypes.POLISHED_GRANITE_TYPE != null) return;
    BlockTypes.POLISHED_GRANITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedGraniteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_GRANITE)
            .build();
  }

  public static void initPolishedGraniteStairs() {
    if (BlockTypes.POLISHED_GRANITE_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_GRANITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedGraniteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedTuff() {
    if (BlockTypes.POLISHED_TUFF_TYPE != null) return;
    BlockTypes.POLISHED_TUFF_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF)
            .build();
  }

  public static void initPolishedTuffDoubleSlab() {
    if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_TUFF_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedTuffSlab() {
    if (BlockTypes.POLISHED_TUFF_SLAB_TYPE != null) return;
    BlockTypes.POLISHED_TUFF_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initPolishedTuffStairs() {
    if (BlockTypes.POLISHED_TUFF_STAIRS_TYPE != null) return;
    BlockTypes.POLISHED_TUFF_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPolishedTuffWall() {
    if (BlockTypes.POLISHED_TUFF_WALL_TYPE != null) return;
    BlockTypes.POLISHED_TUFF_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initPortal() {
    if (BlockTypes.PORTAL_TYPE != null) return;
    BlockTypes.PORTAL_TYPE = BlockTypeBuilder
            .builder(BlockPortalBehavior.class)
            .vanillaBlock(VanillaBlockId.PORTAL)
            .setProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
            .build();
  }

  public static void initPotatoes() {
    if (BlockTypes.POTATOES_TYPE != null) return;
    BlockTypes.POTATOES_TYPE = BlockTypeBuilder
            .builder(BlockPotatoesBehavior.class)
            .vanillaBlock(VanillaBlockId.POTATOES)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initPowderSnow() {
    if (BlockTypes.POWDER_SNOW_TYPE != null) return;
    BlockTypes.POWDER_SNOW_TYPE = BlockTypeBuilder
            .builder(BlockPowderSnowBehavior.class)
            .vanillaBlock(VanillaBlockId.POWDER_SNOW)
            .build();
  }

  public static void initPoweredComparator() {
    if (BlockTypes.POWERED_COMPARATOR_TYPE != null) return;
    BlockTypes.POWERED_COMPARATOR_TYPE = BlockTypeBuilder
            .builder(BlockPoweredComparatorBehavior.class)
            .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
            .build();
  }

  public static void initPoweredRepeater() {
    if (BlockTypes.POWERED_REPEATER_TYPE != null) return;
    BlockTypes.POWERED_REPEATER_TYPE = BlockTypeBuilder
            .builder(BlockPoweredRepeaterBehavior.class)
            .vanillaBlock(VanillaBlockId.POWERED_REPEATER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
  }

  public static void initPrismarine() {
    if (BlockTypes.PRISMARINE_TYPE != null) return;
    BlockTypes.PRISMARINE_TYPE = BlockTypeBuilder
            .builder(BlockPrismarineBehavior.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE)
            .setProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
            .build();
  }

  public static void initPrismarineBricksStairs() {
    if (BlockTypes.PRISMARINE_BRICKS_STAIRS_TYPE != null) return;
    BlockTypes.PRISMARINE_BRICKS_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPrismarineBricksStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPrismarineStairs() {
    if (BlockTypes.PRISMARINE_STAIRS_TYPE != null) return;
    BlockTypes.PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPrismarineStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initPumpkin() {
    if (BlockTypes.PUMPKIN_TYPE != null) return;
    BlockTypes.PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initPumpkinStem() {
    if (BlockTypes.PUMPKIN_STEM_TYPE != null) return;
    BlockTypes.PUMPKIN_STEM_TYPE = BlockTypeBuilder
            .builder(BlockPumpkinStemBehavior.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initPurpleCandle() {
    if (BlockTypes.PURPLE_CANDLE_TYPE != null) return;
    BlockTypes.PURPLE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initPurpleCandleCake() {
    if (BlockTypes.PURPLE_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.PURPLE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initPurpleCarpet() {
    if (BlockTypes.PURPLE_CARPET_TYPE != null) return;
    BlockTypes.PURPLE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CARPET)
            .build();
  }

  public static void initPurpleConcrete() {
    if (BlockTypes.PURPLE_CONCRETE_TYPE != null) return;
    BlockTypes.PURPLE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE)
            .build();
  }

  public static void initPurpleConcretePowder() {
    if (BlockTypes.PURPLE_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.PURPLE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockPurpleConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE_POWDER)
            .build();
  }

  public static void initPurpleGlazedTerracotta() {
    if (BlockTypes.PURPLE_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.PURPLE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPurpleGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initPurpleShulkerBox() {
    if (BlockTypes.PURPLE_SHULKER_BOX_TYPE != null) return;
    BlockTypes.PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPurpleShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
            .build();
  }

  public static void initPurpleStainedGlass() {
    if (BlockTypes.PURPLE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.PURPLE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockPurpleStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_STAINED_GLASS)
            .build();
  }

  public static void initPurpleStainedGlassPane() {
    if (BlockTypes.PURPLE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.PURPLE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initPurpleTerracotta() {
    if (BlockTypes.PURPLE_TERRACOTTA_TYPE != null) return;
    BlockTypes.PURPLE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPurpleTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_TERRACOTTA)
            .build();
  }

  public static void initPurpleWool() {
    if (BlockTypes.PURPLE_WOOL_TYPE != null) return;
    BlockTypes.PURPLE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockPurpleWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_WOOL)
            .build();
  }

  public static void initPurpurBlock() {
    if (BlockTypes.PURPUR_BLOCK_TYPE != null) return;
    BlockTypes.PURPUR_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockPurpurBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initPurpurStairs() {
    if (BlockTypes.PURPUR_STAIRS_TYPE != null) return;
    BlockTypes.PURPUR_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockPurpurStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPUR_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initQuartzBlock() {
    if (BlockTypes.QUARTZ_BLOCK_TYPE != null) return;
    BlockTypes.QUARTZ_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockQuartzBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initQuartzBricks() {
    if (BlockTypes.QUARTZ_BRICKS_TYPE != null) return;
    BlockTypes.QUARTZ_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockQuartzBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS)
            .build();
  }

  public static void initQuartzOre() {
    if (BlockTypes.QUARTZ_ORE_TYPE != null) return;
    BlockTypes.QUARTZ_ORE_TYPE = BlockTypeBuilder
            .builder(BlockQuartzOreBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_ORE)
            .build();
  }

  public static void initQuartzStairs() {
    if (BlockTypes.QUARTZ_STAIRS_TYPE != null) return;
    BlockTypes.QUARTZ_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockQuartzStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initRail() {
    if (BlockTypes.RAIL_TYPE != null) return;
    BlockTypes.RAIL_TYPE = BlockTypeBuilder
            .builder(BlockRailBehavior.class)
            .vanillaBlock(VanillaBlockId.RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
            .build();
  }

  public static void initRawCopperBlock() {
    if (BlockTypes.RAW_COPPER_BLOCK_TYPE != null) return;
    BlockTypes.RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawCopperBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK)
            .build();
  }

  public static void initRawGoldBlock() {
    if (BlockTypes.RAW_GOLD_BLOCK_TYPE != null) return;
    BlockTypes.RAW_GOLD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawGoldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK)
            .build();
  }

  public static void initRawIronBlock() {
    if (BlockTypes.RAW_IRON_BLOCK_TYPE != null) return;
    BlockTypes.RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawIronBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK)
            .build();
  }

  public static void initRedCandle() {
    if (BlockTypes.RED_CANDLE_TYPE != null) return;
    BlockTypes.RED_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockRedCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initRedCandleCake() {
    if (BlockTypes.RED_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.RED_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockRedCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initRedCarpet() {
    if (BlockTypes.RED_CARPET_TYPE != null) return;
    BlockTypes.RED_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockRedCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CARPET)
            .build();
  }

  public static void initRedConcrete() {
    if (BlockTypes.RED_CONCRETE_TYPE != null) return;
    BlockTypes.RED_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockRedConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CONCRETE)
            .build();
  }

  public static void initRedConcretePowder() {
    if (BlockTypes.RED_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.RED_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockRedConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CONCRETE_POWDER)
            .build();
  }

  public static void initRedFlower() {
    if (BlockTypes.RED_FLOWER_TYPE != null) return;
    BlockTypes.RED_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockRedFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_FLOWER)
            .setProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
            .build();
  }

  public static void initRedGlazedTerracotta() {
    if (BlockTypes.RED_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.RED_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockRedGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initRedMushroom() {
    if (BlockTypes.RED_MUSHROOM_TYPE != null) return;
    BlockTypes.RED_MUSHROOM_TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM)
            .build();
  }

  public static void initRedMushroomBlock() {
    if (BlockTypes.RED_MUSHROOM_BLOCK_TYPE != null) return;
    BlockTypes.RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .build();
  }

  public static void initRedNetherBrick() {
    if (BlockTypes.RED_NETHER_BRICK_TYPE != null) return;
    BlockTypes.RED_NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockRedNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK)
            .build();
  }

  public static void initRedNetherBrickStairs() {
    if (BlockTypes.RED_NETHER_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.RED_NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockRedNetherBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initRedSandstone() {
    if (BlockTypes.RED_SANDSTONE_TYPE != null) return;
    BlockTypes.RED_SANDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockRedSandstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_SANDSTONE)
            .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .build();
  }

  public static void initRedSandstoneStairs() {
    if (BlockTypes.RED_SANDSTONE_STAIRS_TYPE != null) return;
    BlockTypes.RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockRedSandstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initRedShulkerBox() {
    if (BlockTypes.RED_SHULKER_BOX_TYPE != null) return;
    BlockTypes.RED_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockRedShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
            .build();
  }

  public static void initRedStainedGlass() {
    if (BlockTypes.RED_STAINED_GLASS_TYPE != null) return;
    BlockTypes.RED_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS)
            .build();
  }

  public static void initRedStainedGlassPane() {
    if (BlockTypes.RED_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.RED_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS_PANE)
            .build();
  }

  public static void initRedTerracotta() {
    if (BlockTypes.RED_TERRACOTTA_TYPE != null) return;
    BlockTypes.RED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockRedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_TERRACOTTA)
            .build();
  }

  public static void initRedWool() {
    if (BlockTypes.RED_WOOL_TYPE != null) return;
    BlockTypes.RED_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockRedWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_WOOL)
            .build();
  }

  public static void initRedstoneBlock() {
    if (BlockTypes.REDSTONE_BLOCK_TYPE != null) return;
    BlockTypes.REDSTONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK)
            .build();
  }

  public static void initRedstoneLamp() {
    if (BlockTypes.REDSTONE_LAMP_TYPE != null) return;
    BlockTypes.REDSTONE_LAMP_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneLampBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_LAMP)
            .build();
  }

  public static void initRedstoneOre() {
    if (BlockTypes.REDSTONE_ORE_TYPE != null) return;
    BlockTypes.REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_ORE)
            .build();
  }

  public static void initRedstoneTorch() {
    if (BlockTypes.REDSTONE_TORCH_TYPE != null) return;
    BlockTypes.REDSTONE_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initRedstoneWire() {
    if (BlockTypes.REDSTONE_WIRE_TYPE != null) return;
    BlockTypes.REDSTONE_WIRE_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneWireBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_WIRE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initReeds() {
    if (BlockTypes.REEDS_TYPE != null) return;
    BlockTypes.REEDS_TYPE = BlockTypeBuilder
            .builder(BlockReedsBehavior.class)
            .vanillaBlock(VanillaBlockId.REEDS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }

  public static void initReinforcedDeepslate() {
    if (BlockTypes.REINFORCED_DEEPSLATE_TYPE != null) return;
    BlockTypes.REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockReinforcedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE)
            .build();
  }

  public static void initRepeatingCommandBlock() {
    if (BlockTypes.REPEATING_COMMAND_BLOCK_TYPE != null) return;
    BlockTypes.REPEATING_COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRepeatingCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initReserved6() {
    if (BlockTypes.RESERVED6_TYPE != null) return;
    BlockTypes.RESERVED6_TYPE = BlockTypeBuilder
            .builder(BlockReserved6Behavior.class)
            .vanillaBlock(VanillaBlockId.RESERVED6)
            .build();
  }

  public static void initRespawnAnchor() {
    if (BlockTypes.RESPAWN_ANCHOR_TYPE != null) return;
    BlockTypes.RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
            .builder(BlockRespawnAnchorBehavior.class)
            .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR)
            .setProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
            .build();
  }

  public static void initSand() {
    if (BlockTypes.SAND_TYPE != null) return;
    BlockTypes.SAND_TYPE = BlockTypeBuilder
            .builder(BlockSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SAND)
            .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
            .build();
  }

  public static void initSandstone() {
    if (BlockTypes.SANDSTONE_TYPE != null) return;
    BlockTypes.SANDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockSandstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE)
            .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .build();
  }

  public static void initSandstoneStairs() {
    if (BlockTypes.SANDSTONE_STAIRS_TYPE != null) return;
    BlockTypes.SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSandstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initSapling() {
    if (BlockTypes.SAPLING_TYPE != null) return;
    BlockTypes.SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockSaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
            .build();
  }

  public static void initScaffolding() {
    if (BlockTypes.SCAFFOLDING_TYPE != null) return;
    BlockTypes.SCAFFOLDING_TYPE = BlockTypeBuilder
            .builder(BlockScaffoldingBehavior.class)
            .vanillaBlock(VanillaBlockId.SCAFFOLDING)
            .setProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
            .build();
  }

  public static void initSculk() {
    if (BlockTypes.SCULK_TYPE != null) return;
    BlockTypes.SCULK_TYPE = BlockTypeBuilder
            .builder(BlockSculkBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK)
            .build();
  }

  public static void initSculkCatalyst() {
    if (BlockTypes.SCULK_CATALYST_TYPE != null) return;
    BlockTypes.SCULK_CATALYST_TYPE = BlockTypeBuilder
            .builder(BlockSculkCatalystBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
            .setProperties(VanillaBlockPropertyTypes.BLOOM)
            .build();
  }

  public static void initSculkSensor() {
    if (BlockTypes.SCULK_SENSOR_TYPE != null) return;
    BlockTypes.SCULK_SENSOR_TYPE = BlockTypeBuilder
            .builder(BlockSculkSensorBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_SENSOR)
            .setProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
            .build();
  }

  public static void initSculkShrieker() {
    if (BlockTypes.SCULK_SHRIEKER_TYPE != null) return;
    BlockTypes.SCULK_SHRIEKER_TYPE = BlockTypeBuilder
            .builder(BlockSculkShriekerBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER)
            .setProperties(VanillaBlockPropertyTypes.ACTIVE, VanillaBlockPropertyTypes.CAN_SUMMON)
            .build();
  }

  public static void initSculkVein() {
    if (BlockTypes.SCULK_VEIN_TYPE != null) return;
    BlockTypes.SCULK_VEIN_TYPE = BlockTypeBuilder
            .builder(BlockSculkVeinBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_VEIN)
            .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .build();
  }

  public static void initSeaLantern() {
    if (BlockTypes.SEA_LANTERN_TYPE != null) return;
    BlockTypes.SEA_LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockSeaLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.SEA_LANTERN)
            .build();
  }

  public static void initSeaPickle() {
    if (BlockTypes.SEA_PICKLE_TYPE != null) return;
    BlockTypes.SEA_PICKLE_TYPE = BlockTypeBuilder
            .builder(BlockSeaPickleBehavior.class)
            .vanillaBlock(VanillaBlockId.SEA_PICKLE)
            .setProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }

  public static void initSeagrass() {
    if (BlockTypes.SEAGRASS_TYPE != null) return;
    BlockTypes.SEAGRASS_TYPE = BlockTypeBuilder
            .builder(BlockSeagrassBehavior.class)
            .vanillaBlock(VanillaBlockId.SEAGRASS)
            .setProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
            .build();
  }

  public static void initShroomlight() {
    if (BlockTypes.SHROOMLIGHT_TYPE != null) return;
    BlockTypes.SHROOMLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockShroomlightBehavior.class)
            .vanillaBlock(VanillaBlockId.SHROOMLIGHT)
            .build();
  }

  public static void initSilverGlazedTerracotta() {
    if (BlockTypes.SILVER_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.SILVER_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockSilverGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initSkull() {
    if (BlockTypes.SKULL_TYPE != null) return;
    BlockTypes.SKULL_TYPE = BlockTypeBuilder
            .builder(BlockSkullBehavior.class)
            .vanillaBlock(VanillaBlockId.SKULL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initSlime() {
    if (BlockTypes.SLIME_TYPE != null) return;
    BlockTypes.SLIME_TYPE = BlockTypeBuilder
            .builder(BlockSlimeBehavior.class)
            .vanillaBlock(VanillaBlockId.SLIME)
            .build();
  }

  public static void initSmallAmethystBud() {
    if (BlockTypes.SMALL_AMETHYST_BUD_TYPE != null) return;
    BlockTypes.SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockSmallAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }

  public static void initSmallDripleafBlock() {
    if (BlockTypes.SMALL_DRIPLEAF_BLOCK_TYPE != null) return;
    BlockTypes.SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockSmallDripleafBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initSmithingTable() {
    if (BlockTypes.SMITHING_TABLE_TYPE != null) return;
    BlockTypes.SMITHING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockSmithingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.SMITHING_TABLE)
            .build();
  }

  public static void initSmoker() {
    if (BlockTypes.SMOKER_TYPE != null) return;
    BlockTypes.SMOKER_TYPE = BlockTypeBuilder
            .builder(BlockSmokerBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOKER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initSmoothBasalt() {
    if (BlockTypes.SMOOTH_BASALT_TYPE != null) return;
    BlockTypes.SMOOTH_BASALT_TYPE = BlockTypeBuilder
            .builder(BlockSmoothBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_BASALT)
            .build();
  }

  public static void initSmoothQuartzStairs() {
    if (BlockTypes.SMOOTH_QUARTZ_STAIRS_TYPE != null) return;
    BlockTypes.SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSmoothQuartzStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initSmoothRedSandstoneStairs() {
    if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS_TYPE != null) return;
    BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSmoothRedSandstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initSmoothSandstoneStairs() {
    if (BlockTypes.SMOOTH_SANDSTONE_STAIRS_TYPE != null) return;
    BlockTypes.SMOOTH_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSmoothSandstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initSmoothStone() {
    if (BlockTypes.SMOOTH_STONE_TYPE != null) return;
    BlockTypes.SMOOTH_STONE_TYPE = BlockTypeBuilder
            .builder(BlockSmoothStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
            .build();
  }

  public static void initSnifferEgg() {
    if (BlockTypes.SNIFFER_EGG_TYPE != null) return;
    BlockTypes.SNIFFER_EGG_TYPE = BlockTypeBuilder
            .builder(BlockSnifferEggBehavior.class)
            .vanillaBlock(VanillaBlockId.SNIFFER_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
            .build();
  }

  public static void initSnow() {
    if (BlockTypes.SNOW_TYPE != null) return;
    BlockTypes.SNOW_TYPE = BlockTypeBuilder
            .builder(BlockSnowBehavior.class)
            .vanillaBlock(VanillaBlockId.SNOW)
            .build();
  }

  public static void initSnowLayer() {
    if (BlockTypes.SNOW_LAYER_TYPE != null) return;
    BlockTypes.SNOW_LAYER_TYPE = BlockTypeBuilder
            .builder(BlockSnowLayerBehavior.class)
            .vanillaBlock(VanillaBlockId.SNOW_LAYER)
            .setProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
            .build();
  }

  public static void initSoulCampfire() {
    if (BlockTypes.SOUL_CAMPFIRE_TYPE != null) return;
    BlockTypes.SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
            .builder(BlockSoulCampfireBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE)
            .setProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initSoulFire() {
    if (BlockTypes.SOUL_FIRE_TYPE != null) return;
    BlockTypes.SOUL_FIRE_TYPE = BlockTypeBuilder
            .builder(BlockSoulFireBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_FIRE)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }

  public static void initSoulLantern() {
    if (BlockTypes.SOUL_LANTERN_TYPE != null) return;
    BlockTypes.SOUL_LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockSoulLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_LANTERN)
            .setProperties(VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initSoulSand() {
    if (BlockTypes.SOUL_SAND_TYPE != null) return;
    BlockTypes.SOUL_SAND_TYPE = BlockTypeBuilder
            .builder(BlockSoulSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_SAND)
            .build();
  }

  public static void initSoulSoil() {
    if (BlockTypes.SOUL_SOIL_TYPE != null) return;
    BlockTypes.SOUL_SOIL_TYPE = BlockTypeBuilder
            .builder(BlockSoulSoilBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_SOIL)
            .build();
  }

  public static void initSoulTorch() {
    if (BlockTypes.SOUL_TORCH_TYPE != null) return;
    BlockTypes.SOUL_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockSoulTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initSponge() {
    if (BlockTypes.SPONGE_TYPE != null) return;
    BlockTypes.SPONGE_TYPE = BlockTypeBuilder
            .builder(BlockSpongeBehavior.class)
            .vanillaBlock(VanillaBlockId.SPONGE)
            .setProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
            .build();
  }

  public static void initSporeBlossom() {
    if (BlockTypes.SPORE_BLOSSOM_TYPE != null) return;
    BlockTypes.SPORE_BLOSSOM_TYPE = BlockTypeBuilder
            .builder(BlockSporeBlossomBehavior.class)
            .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
            .build();
  }

  public static void initSpruceButton() {
    if (BlockTypes.SPRUCE_BUTTON_TYPE != null) return;
    BlockTypes.SPRUCE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockSpruceButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initSpruceDoor() {
    if (BlockTypes.SPRUCE_DOOR_TYPE != null) return;
    BlockTypes.SPRUCE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockSpruceDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initSpruceFence() {
    if (BlockTypes.SPRUCE_FENCE_TYPE != null) return;
    BlockTypes.SPRUCE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockSpruceFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_FENCE)
            .build();
  }

  public static void initSpruceFenceGate() {
    if (BlockTypes.SPRUCE_FENCE_GATE_TYPE != null) return;
    BlockTypes.SPRUCE_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockSpruceFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initSpruceHangingSign() {
    if (BlockTypes.SPRUCE_HANGING_SIGN_TYPE != null) return;
    BlockTypes.SPRUCE_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockSpruceHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initSpruceLog() {
    if (BlockTypes.SPRUCE_LOG_TYPE != null) return;
    BlockTypes.SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initSprucePlanks() {
    if (BlockTypes.SPRUCE_PLANKS_TYPE != null) return;
    BlockTypes.SPRUCE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockSprucePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_PLANKS)
            .build();
  }

  public static void initSprucePressurePlate() {
    if (BlockTypes.SPRUCE_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockSprucePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initSpruceStairs() {
    if (BlockTypes.SPRUCE_STAIRS_TYPE != null) return;
    BlockTypes.SPRUCE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSpruceStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initSpruceStandingSign() {
    if (BlockTypes.SPRUCE_STANDING_SIGN_TYPE != null) return;
    BlockTypes.SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockSpruceStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initSpruceTrapdoor() {
    if (BlockTypes.SPRUCE_TRAPDOOR_TYPE != null) return;
    BlockTypes.SPRUCE_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockSpruceTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initSpruceWallSign() {
    if (BlockTypes.SPRUCE_WALL_SIGN_TYPE != null) return;
    BlockTypes.SPRUCE_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockSpruceWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initStandingBanner() {
    if (BlockTypes.STANDING_BANNER_TYPE != null) return;
    BlockTypes.STANDING_BANNER_TYPE = BlockTypeBuilder
            .builder(BlockStandingBannerBehavior.class)
            .vanillaBlock(VanillaBlockId.STANDING_BANNER)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initStandingSign() {
    if (BlockTypes.STANDING_SIGN_TYPE != null) return;
    BlockTypes.STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initStickyPiston() {
    if (BlockTypes.STICKY_PISTON_TYPE != null) return;
    BlockTypes.STICKY_PISTON_TYPE = BlockTypeBuilder
            .builder(BlockStickyPistonBehavior.class)
            .vanillaBlock(VanillaBlockId.STICKY_PISTON)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initStickyPistonArmCollision() {
    if (BlockTypes.STICKY_PISTON_ARM_COLLISION_TYPE != null) return;
    BlockTypes.STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
            .builder(BlockStickyPistonArmCollisionBehavior.class)
            .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initStone() {
    if (BlockTypes.STONE_TYPE != null) return;
    BlockTypes.STONE_TYPE = BlockTypeBuilder
            .builder(BlockStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE)
            .build();
  }

  public static void initStoneBlockSlab() {
    if (BlockTypes.STONE_BLOCK_SLAB_TYPE != null) return;
    BlockTypes.STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE)
            .build();
  }

  public static void initStoneBlockSlab2() {
    if (BlockTypes.STONE_BLOCK_SLAB2_TYPE != null) return;
    BlockTypes.STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab2Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2)
            .build();
  }

  public static void initStoneBlockSlab3() {
    if (BlockTypes.STONE_BLOCK_SLAB3_TYPE != null) return;
    BlockTypes.STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab3Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3)
            .build();
  }

  public static void initStoneBlockSlab4() {
    if (BlockTypes.STONE_BLOCK_SLAB4_TYPE != null) return;
    BlockTypes.STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab4Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
            .build();
  }

  public static void initStoneBrickStairs() {
    if (BlockTypes.STONE_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockStoneBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initStoneButton() {
    if (BlockTypes.STONE_BUTTON_TYPE != null) return;
    BlockTypes.STONE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockStoneButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initStonePressurePlate() {
    if (BlockTypes.STONE_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.STONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockStonePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initStoneStairs() {
    if (BlockTypes.STONE_STAIRS_TYPE != null) return;
    BlockTypes.STONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockStoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initStonebrick() {
    if (BlockTypes.STONEBRICK_TYPE != null) return;
    BlockTypes.STONEBRICK_TYPE = BlockTypeBuilder
            .builder(BlockStonebrickBehavior.class)
            .vanillaBlock(VanillaBlockId.STONEBRICK)
            .setProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
            .build();
  }

  public static void initStonecutter() {
    if (BlockTypes.STONECUTTER_TYPE != null) return;
    BlockTypes.STONECUTTER_TYPE = BlockTypeBuilder
            .builder(BlockStonecutterBehavior.class)
            .vanillaBlock(VanillaBlockId.STONECUTTER)
            .build();
  }

  public static void initStonecutterBlock() {
    if (BlockTypes.STONECUTTER_BLOCK_TYPE != null) return;
    BlockTypes.STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStonecutterBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initStrippedAcaciaLog() {
    if (BlockTypes.STRIPPED_ACACIA_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedAcaciaLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedBambooBlock() {
    if (BlockTypes.STRIPPED_BAMBOO_BLOCK_TYPE != null) return;
    BlockTypes.STRIPPED_BAMBOO_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStrippedBambooBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedBirchLog() {
    if (BlockTypes.STRIPPED_BIRCH_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedCherryLog() {
    if (BlockTypes.STRIPPED_CHERRY_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCherryLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedCherryWood() {
    if (BlockTypes.STRIPPED_CHERRY_WOOD_TYPE != null) return;
    BlockTypes.STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCherryWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedCrimsonHyphae() {
    if (BlockTypes.STRIPPED_CRIMSON_HYPHAE_TYPE != null) return;
    BlockTypes.STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedCrimsonStem() {
    if (BlockTypes.STRIPPED_CRIMSON_STEM_TYPE != null) return;
    BlockTypes.STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedDarkOakLog() {
    if (BlockTypes.STRIPPED_DARK_OAK_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_DARK_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedDarkOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedJungleLog() {
    if (BlockTypes.STRIPPED_JUNGLE_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedJungleLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedMangroveLog() {
    if (BlockTypes.STRIPPED_MANGROVE_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_MANGROVE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedMangroveLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedMangroveWood() {
    if (BlockTypes.STRIPPED_MANGROVE_WOOD_TYPE != null) return;
    BlockTypes.STRIPPED_MANGROVE_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockStrippedMangroveWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedOakLog() {
    if (BlockTypes.STRIPPED_OAK_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedSpruceLog() {
    if (BlockTypes.STRIPPED_SPRUCE_LOG_TYPE != null) return;
    BlockTypes.STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedWarpedHyphae() {
    if (BlockTypes.STRIPPED_WARPED_HYPHAE_TYPE != null) return;
    BlockTypes.STRIPPED_WARPED_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStrippedWarpedStem() {
    if (BlockTypes.STRIPPED_WARPED_STEM_TYPE != null) return;
    BlockTypes.STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initStructureBlock() {
    if (BlockTypes.STRUCTURE_BLOCK_TYPE != null) return;
    BlockTypes.STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStructureBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
            .build();
  }

  public static void initStructureVoid() {
    if (BlockTypes.STRUCTURE_VOID_TYPE != null) return;
    BlockTypes.STRUCTURE_VOID_TYPE = BlockTypeBuilder
            .builder(BlockStructureVoidBehavior.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_VOID)
            .setProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
            .build();
  }

  public static void initSuspiciousGravel() {
    if (BlockTypes.SUSPICIOUS_GRAVEL_TYPE != null) return;
    BlockTypes.SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousGravelBehavior.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL)
            .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initSuspiciousSand() {
    if (BlockTypes.SUSPICIOUS_SAND_TYPE != null) return;
    BlockTypes.SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND)
            .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initSweetBerryBush() {
    if (BlockTypes.SWEET_BERRY_BUSH_TYPE != null) return;
    BlockTypes.SWEET_BERRY_BUSH_TYPE = BlockTypeBuilder
            .builder(BlockSweetBerryBushBehavior.class)
            .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initTallgrass() {
    if (BlockTypes.TALLGRASS_TYPE != null) return;
    BlockTypes.TALLGRASS_TYPE = BlockTypeBuilder
            .builder(BlockTallgrassBehavior.class)
            .vanillaBlock(VanillaBlockId.TALLGRASS)
            .setProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
            .build();
  }

  public static void initTarget() {
    if (BlockTypes.TARGET_TYPE != null) return;
    BlockTypes.TARGET_TYPE = BlockTypeBuilder
            .builder(BlockTargetBehavior.class)
            .vanillaBlock(VanillaBlockId.TARGET)
            .build();
  }

  public static void initTintedGlass() {
    if (BlockTypes.TINTED_GLASS_TYPE != null) return;
    BlockTypes.TINTED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockTintedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.TINTED_GLASS)
            .build();
  }

  public static void initTnt() {
    if (BlockTypes.TNT_TYPE != null) return;
    BlockTypes.TNT_TYPE = BlockTypeBuilder
            .builder(BlockTntBehavior.class)
            .vanillaBlock(VanillaBlockId.TNT)
            .setProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
            .build();
  }

  public static void initTorch() {
    if (BlockTypes.TORCH_TYPE != null) return;
    BlockTypes.TORCH_TYPE = BlockTypeBuilder
            .builder(BlockTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initTorchflower() {
    if (BlockTypes.TORCHFLOWER_TYPE != null) return;
    BlockTypes.TORCHFLOWER_TYPE = BlockTypeBuilder
            .builder(BlockTorchflowerBehavior.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER)
            .build();
  }

  public static void initTorchflowerCrop() {
    if (BlockTypes.TORCHFLOWER_CROP_TYPE != null) return;
    BlockTypes.TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
            .builder(BlockTorchflowerCropBehavior.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initTrapdoor() {
    if (BlockTypes.TRAPDOOR_TYPE != null) return;
    BlockTypes.TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initTrappedChest() {
    if (BlockTypes.TRAPPED_CHEST_TYPE != null) return;
    BlockTypes.TRAPPED_CHEST_TYPE = BlockTypeBuilder
            .builder(BlockTrappedChestBehavior.class)
            .vanillaBlock(VanillaBlockId.TRAPPED_CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }

  public static void initTrialSpawner() {
    if (BlockTypes.TRIAL_SPAWNER_TYPE != null) return;
    BlockTypes.TRIAL_SPAWNER_TYPE = BlockTypeBuilder
            .builder(BlockTrialSpawnerBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIAL_SPAWNER)
            .setProperties(VanillaBlockPropertyTypes.TRIAL_SPAWNER_STATE)
            .build();
  }

  public static void initTripWire() {
    if (BlockTypes.TRIP_WIRE_TYPE != null) return;
    BlockTypes.TRIP_WIRE_TYPE = BlockTypeBuilder
            .builder(BlockTripWireBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIP_WIRE)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT)
            .build();
  }

  public static void initTripwireHook() {
    if (BlockTypes.TRIPWIRE_HOOK_TYPE != null) return;
    BlockTypes.TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
            .builder(BlockTripwireHookBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initTubeCoral() {
    if (BlockTypes.TUBE_CORAL_TYPE != null) return;
    BlockTypes.TUBE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockTubeCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.TUBE_CORAL)
            .build();
  }

  public static void initTuff() {
    if (BlockTypes.TUFF_TYPE != null) return;
    BlockTypes.TUFF_TYPE = BlockTypeBuilder
            .builder(BlockTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF)
            .build();
  }

  public static void initTuffBrickDoubleSlab() {
    if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.TUFF_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initTuffBrickSlab() {
    if (BlockTypes.TUFF_BRICK_SLAB_TYPE != null) return;
    BlockTypes.TUFF_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initTuffBrickStairs() {
    if (BlockTypes.TUFF_BRICK_STAIRS_TYPE != null) return;
    BlockTypes.TUFF_BRICK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initTuffBrickWall() {
    if (BlockTypes.TUFF_BRICK_WALL_TYPE != null) return;
    BlockTypes.TUFF_BRICK_WALL_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickWallBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initTuffBricks() {
    if (BlockTypes.TUFF_BRICKS_TYPE != null) return;
    BlockTypes.TUFF_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockTuffBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICKS)
            .build();
  }

  public static void initTuffDoubleSlab() {
    if (BlockTypes.TUFF_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.TUFF_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initTuffSlab() {
    if (BlockTypes.TUFF_SLAB_TYPE != null) return;
    BlockTypes.TUFF_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initTuffStairs() {
    if (BlockTypes.TUFF_STAIRS_TYPE != null) return;
    BlockTypes.TUFF_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockTuffStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initTuffWall() {
    if (BlockTypes.TUFF_WALL_TYPE != null) return;
    BlockTypes.TUFF_WALL_TYPE = BlockTypeBuilder
            .builder(BlockTuffWallBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }

  public static void initTurtleEgg() {
    if (BlockTypes.TURTLE_EGG_TYPE != null) return;
    BlockTypes.TURTLE_EGG_TYPE = BlockTypeBuilder
            .builder(BlockTurtleEggBehavior.class)
            .vanillaBlock(VanillaBlockId.TURTLE_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
            .build();
  }

  public static void initTwistingVines() {
    if (BlockTypes.TWISTING_VINES_TYPE != null) return;
    BlockTypes.TWISTING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockTwistingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES)
            .setProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .build();
  }

  public static void initUnderwaterTorch() {
    if (BlockTypes.UNDERWATER_TORCH_TYPE != null) return;
    BlockTypes.UNDERWATER_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockUnderwaterTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initUndyedShulkerBox() {
    if (BlockTypes.UNDYED_SHULKER_BOX_TYPE != null) return;
    BlockTypes.UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockUndyedShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
            .build();
  }

  public static void initUnknown() {
    if (BlockTypes.UNKNOWN_TYPE != null) return;
    BlockTypes.UNKNOWN_TYPE = BlockTypeBuilder
            .builder(BlockUnknownBehavior.class)
            .vanillaBlock(VanillaBlockId.UNKNOWN)
            .build();
  }

  public static void initUnlitRedstoneTorch() {
    if (BlockTypes.UNLIT_REDSTONE_TORCH_TYPE != null) return;
    BlockTypes.UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockUnlitRedstoneTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
  }

  public static void initUnpoweredComparator() {
    if (BlockTypes.UNPOWERED_COMPARATOR_TYPE != null) return;
    BlockTypes.UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredComparatorBehavior.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
            .build();
  }

  public static void initUnpoweredRepeater() {
    if (BlockTypes.UNPOWERED_REPEATER_TYPE != null) return;
    BlockTypes.UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredRepeaterBehavior.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
  }

  public static void initVerdantFroglight() {
    if (BlockTypes.VERDANT_FROGLIGHT_TYPE != null) return;
    BlockTypes.VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockVerdantFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initVine() {
    if (BlockTypes.VINE_TYPE != null) return;
    BlockTypes.VINE_TYPE = BlockTypeBuilder
            .builder(BlockVineBehavior.class)
            .vanillaBlock(VanillaBlockId.VINE)
            .setProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
            .build();
  }

  public static void initWallBanner() {
    if (BlockTypes.WALL_BANNER_TYPE != null) return;
    BlockTypes.WALL_BANNER_TYPE = BlockTypeBuilder
            .builder(BlockWallBannerBehavior.class)
            .vanillaBlock(VanillaBlockId.WALL_BANNER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWallSign() {
    if (BlockTypes.WALL_SIGN_TYPE != null) return;
    BlockTypes.WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWarpedButton() {
    if (BlockTypes.WARPED_BUTTON_TYPE != null) return;
    BlockTypes.WARPED_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockWarpedButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWarpedDoor() {
    if (BlockTypes.WARPED_DOOR_TYPE != null) return;
    BlockTypes.WARPED_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWarpedDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWarpedDoubleSlab() {
    if (BlockTypes.WARPED_DOUBLE_SLAB_TYPE != null) return;
    BlockTypes.WARPED_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWarpedDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWarpedFence() {
    if (BlockTypes.WARPED_FENCE_TYPE != null) return;
    BlockTypes.WARPED_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_FENCE)
            .build();
  }

  public static void initWarpedFenceGate() {
    if (BlockTypes.WARPED_FENCE_GATE_TYPE != null) return;
    BlockTypes.WARPED_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }

  public static void initWarpedFungus() {
    if (BlockTypes.WARPED_FUNGUS_TYPE != null) return;
    BlockTypes.WARPED_FUNGUS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedFungusBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_FUNGUS)
            .build();
  }

  public static void initWarpedHangingSign() {
    if (BlockTypes.WARPED_HANGING_SIGN_TYPE != null) return;
    BlockTypes.WARPED_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWarpedHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }

  public static void initWarpedHyphae() {
    if (BlockTypes.WARPED_HYPHAE_TYPE != null) return;
    BlockTypes.WARPED_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initWarpedNylium() {
    if (BlockTypes.WARPED_NYLIUM_TYPE != null) return;
    BlockTypes.WARPED_NYLIUM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedNyliumBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_NYLIUM)
            .build();
  }

  public static void initWarpedPlanks() {
    if (BlockTypes.WARPED_PLANKS_TYPE != null) return;
    BlockTypes.WARPED_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_PLANKS)
            .build();
  }

  public static void initWarpedPressurePlate() {
    if (BlockTypes.WARPED_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.WARPED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initWarpedRoots() {
    if (BlockTypes.WARPED_ROOTS_TYPE != null) return;
    BlockTypes.WARPED_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_ROOTS)
            .build();
  }

  public static void initWarpedSlab() {
    if (BlockTypes.WARPED_SLAB_TYPE != null) return;
    BlockTypes.WARPED_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWarpedSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWarpedStairs() {
    if (BlockTypes.WARPED_STAIRS_TYPE != null) return;
    BlockTypes.WARPED_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWarpedStandingSign() {
    if (BlockTypes.WARPED_STANDING_SIGN_TYPE != null) return;
    BlockTypes.WARPED_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWarpedStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }

  public static void initWarpedStem() {
    if (BlockTypes.WARPED_STEM_TYPE != null) return;
    BlockTypes.WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }

  public static void initWarpedTrapdoor() {
    if (BlockTypes.WARPED_TRAPDOOR_TYPE != null) return;
    BlockTypes.WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWarpedTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWarpedWallSign() {
    if (BlockTypes.WARPED_WALL_SIGN_TYPE != null) return;
    BlockTypes.WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWarpedWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWarpedWartBlock() {
    if (BlockTypes.WARPED_WART_BLOCK_TYPE != null) return;
    BlockTypes.WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockWarpedWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK)
            .build();
  }

  public static void initWater() {
    if (BlockTypes.WATER_TYPE != null) return;
    BlockTypes.WATER_TYPE = BlockTypeBuilder
            .builder(BlockWaterBehavior.class)
            .vanillaBlock(VanillaBlockId.WATER)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }

  public static void initWaterlily() {
    if (BlockTypes.WATERLILY_TYPE != null) return;
    BlockTypes.WATERLILY_TYPE = BlockTypeBuilder
            .builder(BlockWaterlilyBehavior.class)
            .vanillaBlock(VanillaBlockId.WATERLILY)
            .build();
  }

  public static void initWaxedChiseledCopper() {
    if (BlockTypes.WAXED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CHISELED_COPPER)
            .build();
  }

  public static void initWaxedCopper() {
    if (BlockTypes.WAXED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER)
            .build();
  }

  public static void initWaxedCopperBulb() {
    if (BlockTypes.WAXED_COPPER_BULB_TYPE != null) return;
    BlockTypes.WAXED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initWaxedCopperDoor() {
    if (BlockTypes.WAXED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.WAXED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWaxedCopperGrate() {
    if (BlockTypes.WAXED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.WAXED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER_GRATE)
            .build();
  }

  public static void initWaxedCopperTrapdoor() {
    if (BlockTypes.WAXED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.WAXED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWaxedCutCopper() {
    if (BlockTypes.WAXED_CUT_COPPER_TYPE != null) return;
    BlockTypes.WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER)
            .build();
  }

  public static void initWaxedCutCopperSlab() {
    if (BlockTypes.WAXED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedCutCopperStairs() {
    if (BlockTypes.WAXED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.WAXED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWaxedDoubleCutCopperSlab() {
    if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedExposedChiseledCopper() {
    if (BlockTypes.WAXED_EXPOSED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CHISELED_COPPER)
            .build();
  }

  public static void initWaxedExposedCopper() {
    if (BlockTypes.WAXED_EXPOSED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER)
            .build();
  }

  public static void initWaxedExposedCopperBulb() {
    if (BlockTypes.WAXED_EXPOSED_COPPER_BULB_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initWaxedExposedCopperDoor() {
    if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWaxedExposedCopperGrate() {
    if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_GRATE)
            .build();
  }

  public static void initWaxedExposedCopperTrapdoor() {
    if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWaxedExposedCutCopper() {
    if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER)
            .build();
  }

  public static void initWaxedExposedCutCopperSlab() {
    if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedExposedCutCopperStairs() {
    if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWaxedExposedDoubleCutCopperSlab() {
    if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedOxidizedChiseledCopper() {
    if (BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CHISELED_COPPER)
            .build();
  }

  public static void initWaxedOxidizedCopper() {
    if (BlockTypes.WAXED_OXIDIZED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER)
            .build();
  }

  public static void initWaxedOxidizedCopperBulb() {
    if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initWaxedOxidizedCopperDoor() {
    if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWaxedOxidizedCopperGrate() {
    if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_GRATE)
            .build();
  }

  public static void initWaxedOxidizedCopperTrapdoor() {
    if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWaxedOxidizedCutCopper() {
    if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
  }

  public static void initWaxedOxidizedCutCopperSlab() {
    if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedOxidizedCutCopperStairs() {
    if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWaxedOxidizedDoubleCutCopperSlab() {
    if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedWeatheredChiseledCopper() {
    if (BlockTypes.WAXED_WEATHERED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CHISELED_COPPER)
            .build();
  }

  public static void initWaxedWeatheredCopper() {
    if (BlockTypes.WAXED_WEATHERED_COPPER_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER)
            .build();
  }

  public static void initWaxedWeatheredCopperBulb() {
    if (BlockTypes.WAXED_WEATHERED_COPPER_BULB_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initWaxedWeatheredCopperDoor() {
    if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWaxedWeatheredCopperGrate() {
    if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_GRATE)
            .build();
  }

  public static void initWaxedWeatheredCopperTrapdoor() {
    if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWaxedWeatheredCutCopper() {
    if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER)
            .build();
  }

  public static void initWaxedWeatheredCutCopperSlab() {
    if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWaxedWeatheredCutCopperStairs() {
    if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWaxedWeatheredDoubleCutCopperSlab() {
    if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWeatheredChiseledCopper() {
    if (BlockTypes.WEATHERED_CHISELED_COPPER_TYPE != null) return;
    BlockTypes.WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CHISELED_COPPER)
            .build();
  }

  public static void initWeatheredCopper() {
    if (BlockTypes.WEATHERED_COPPER_TYPE != null) return;
    BlockTypes.WEATHERED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER)
            .build();
  }

  public static void initWeatheredCopperBulb() {
    if (BlockTypes.WEATHERED_COPPER_BULB_TYPE != null) return;
    BlockTypes.WEATHERED_COPPER_BULB_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperBulbBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_BULB)
            .setProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }

  public static void initWeatheredCopperDoor() {
    if (BlockTypes.WEATHERED_COPPER_DOOR_TYPE != null) return;
    BlockTypes.WEATHERED_COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWeatheredCopperGrate() {
    if (BlockTypes.WEATHERED_COPPER_GRATE_TYPE != null) return;
    BlockTypes.WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_GRATE)
            .build();
  }

  public static void initWeatheredCopperTrapdoor() {
    if (BlockTypes.WEATHERED_COPPER_TRAPDOOR_TYPE != null) return;
    BlockTypes.WEATHERED_COPPER_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }

  public static void initWeatheredCutCopper() {
    if (BlockTypes.WEATHERED_CUT_COPPER_TYPE != null) return;
    BlockTypes.WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER)
            .build();
  }

  public static void initWeatheredCutCopperSlab() {
    if (BlockTypes.WEATHERED_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWeatheredCutCopperStairs() {
    if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS_TYPE != null) return;
    BlockTypes.WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
  }

  public static void initWeatheredDoubleCutCopperSlab() {
    if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE != null) return;
    BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }

  public static void initWeb() {
    if (BlockTypes.WEB_TYPE != null) return;
    BlockTypes.WEB_TYPE = BlockTypeBuilder
            .builder(BlockWebBehavior.class)
            .vanillaBlock(VanillaBlockId.WEB)
            .build();
  }

  public static void initWeepingVines() {
    if (BlockTypes.WEEPING_VINES_TYPE != null) return;
    BlockTypes.WEEPING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockWeepingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.WEEPING_VINES)
            .setProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
            .build();
  }

  public static void initWheat() {
    if (BlockTypes.WHEAT_TYPE != null) return;
    BlockTypes.WHEAT_TYPE = BlockTypeBuilder
            .builder(BlockWheatBehavior.class)
            .vanillaBlock(VanillaBlockId.WHEAT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }

  public static void initWhiteCandle() {
    if (BlockTypes.WHITE_CANDLE_TYPE != null) return;
    BlockTypes.WHITE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initWhiteCandleCake() {
    if (BlockTypes.WHITE_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.WHITE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initWhiteCarpet() {
    if (BlockTypes.WHITE_CARPET_TYPE != null) return;
    BlockTypes.WHITE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CARPET)
            .build();
  }

  public static void initWhiteConcrete() {
    if (BlockTypes.WHITE_CONCRETE_TYPE != null) return;
    BlockTypes.WHITE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CONCRETE)
            .build();
  }

  public static void initWhiteConcretePowder() {
    if (BlockTypes.WHITE_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.WHITE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockWhiteConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CONCRETE_POWDER)
            .build();
  }

  public static void initWhiteGlazedTerracotta() {
    if (BlockTypes.WHITE_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.WHITE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockWhiteGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWhiteShulkerBox() {
    if (BlockTypes.WHITE_SHULKER_BOX_TYPE != null) return;
    BlockTypes.WHITE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockWhiteShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX)
            .build();
  }

  public static void initWhiteStainedGlass() {
    if (BlockTypes.WHITE_STAINED_GLASS_TYPE != null) return;
    BlockTypes.WHITE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockWhiteStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_STAINED_GLASS)
            .build();
  }

  public static void initWhiteStainedGlassPane() {
    if (BlockTypes.WHITE_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.WHITE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_STAINED_GLASS_PANE)
            .build();
  }

  public static void initWhiteTerracotta() {
    if (BlockTypes.WHITE_TERRACOTTA_TYPE != null) return;
    BlockTypes.WHITE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockWhiteTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_TERRACOTTA)
            .build();
  }

  public static void initWhiteWool() {
    if (BlockTypes.WHITE_WOOL_TYPE != null) return;
    BlockTypes.WHITE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockWhiteWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_WOOL)
            .build();
  }

  public static void initWitherRose() {
    if (BlockTypes.WITHER_ROSE_TYPE != null) return;
    BlockTypes.WITHER_ROSE_TYPE = BlockTypeBuilder
            .builder(BlockWitherRoseBehavior.class)
            .vanillaBlock(VanillaBlockId.WITHER_ROSE)
            .build();
  }

  public static void initWood() {
    if (BlockTypes.WOOD_TYPE != null) return;
    BlockTypes.WOOD_TYPE = BlockTypeBuilder
            .builder(BlockWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }

  public static void initWoodenButton() {
    if (BlockTypes.WOODEN_BUTTON_TYPE != null) return;
    BlockTypes.WOODEN_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockWoodenButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initWoodenDoor() {
    if (BlockTypes.WOODEN_DOOR_TYPE != null) return;
    BlockTypes.WOODEN_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWoodenDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }

  public static void initWoodenPressurePlate() {
    if (BlockTypes.WOODEN_PRESSURE_PLATE_TYPE != null) return;
    BlockTypes.WOODEN_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockWoodenPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }

  public static void initWoodenSlab() {
    if (BlockTypes.WOODEN_SLAB_TYPE != null) return;
    BlockTypes.WOODEN_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWoodenSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }

  public static void initYellowCandle() {
    if (BlockTypes.YELLOW_CANDLE_TYPE != null) return;
    BlockTypes.YELLOW_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initYellowCandleCake() {
    if (BlockTypes.YELLOW_CANDLE_CAKE_TYPE != null) return;
    BlockTypes.YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }

  public static void initYellowCarpet() {
    if (BlockTypes.YELLOW_CARPET_TYPE != null) return;
    BlockTypes.YELLOW_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockYellowCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CARPET)
            .build();
  }

  public static void initYellowConcrete() {
    if (BlockTypes.YELLOW_CONCRETE_TYPE != null) return;
    BlockTypes.YELLOW_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockYellowConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE)
            .build();
  }

  public static void initYellowConcretePowder() {
    if (BlockTypes.YELLOW_CONCRETE_POWDER_TYPE != null) return;
    BlockTypes.YELLOW_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockYellowConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE_POWDER)
            .build();
  }

  public static void initYellowFlower() {
    if (BlockTypes.YELLOW_FLOWER_TYPE != null) return;
    BlockTypes.YELLOW_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockYellowFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_FLOWER)
            .build();
  }

  public static void initYellowGlazedTerracotta() {
    if (BlockTypes.YELLOW_GLAZED_TERRACOTTA_TYPE != null) return;
    BlockTypes.YELLOW_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockYellowGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }

  public static void initYellowShulkerBox() {
    if (BlockTypes.YELLOW_SHULKER_BOX_TYPE != null) return;
    BlockTypes.YELLOW_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockYellowShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX)
            .build();
  }

  public static void initYellowStainedGlass() {
    if (BlockTypes.YELLOW_STAINED_GLASS_TYPE != null) return;
    BlockTypes.YELLOW_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockYellowStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_STAINED_GLASS)
            .build();
  }

  public static void initYellowStainedGlassPane() {
    if (BlockTypes.YELLOW_STAINED_GLASS_PANE_TYPE != null) return;
    BlockTypes.YELLOW_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockYellowStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_STAINED_GLASS_PANE)
            .build();
  }

  public static void initYellowTerracotta() {
    if (BlockTypes.YELLOW_TERRACOTTA_TYPE != null) return;
    BlockTypes.YELLOW_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockYellowTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_TERRACOTTA)
            .build();
  }

  public static void initYellowWool() {
    if (BlockTypes.YELLOW_WOOL_TYPE != null) return;
    BlockTypes.YELLOW_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockYellowWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_WOOL)
            .build();
  }
}
