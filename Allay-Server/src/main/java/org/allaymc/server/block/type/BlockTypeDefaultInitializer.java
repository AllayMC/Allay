package org.allaymc.server.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockActivatorRailBehavior;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.interfaces.BlockAlliumBehavior;
import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystBlockBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.block.interfaces.BlockAncientDebrisBehavior;
import org.allaymc.api.block.interfaces.BlockAndesiteBehavior;
import org.allaymc.api.block.interfaces.BlockAzaleaBehavior;
import org.allaymc.api.block.interfaces.BlockAzureBluetBehavior;
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
import org.allaymc.api.block.interfaces.BlockBlueOrchidBehavior;
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
import org.allaymc.api.block.interfaces.BlockChiseledQuartzBlockBehavior;
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
import org.allaymc.api.block.interfaces.BlockCornflowerBehavior;
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
import org.allaymc.api.block.interfaces.BlockDandelionBehavior;
import org.allaymc.api.block.interfaces.BlockDarkOakPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockDarkPrismarineBehavior;
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
import org.allaymc.api.block.interfaces.BlockDirtWithRootsBehavior;
import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
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
import org.allaymc.api.block.interfaces.BlockFernBehavior;
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
import org.allaymc.api.block.interfaces.BlockGrassBlockBehavior;
import org.allaymc.api.block.interfaces.BlockGrassPathBehavior;
import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.block.interfaces.BlockGrindstoneBehavior;
import org.allaymc.api.block.interfaces.BlockHangingRootsBehavior;
import org.allaymc.api.block.interfaces.BlockHardenedClayBehavior;
import org.allaymc.api.block.interfaces.BlockHayBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHeavyCoreBehavior;
import org.allaymc.api.block.interfaces.BlockHeavyWeightedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockHoneyBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHoneycombBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHopperBehavior;
import org.allaymc.api.block.interfaces.BlockIceBehavior;
import org.allaymc.api.block.interfaces.BlockInfestedCobblestoneBehavior;
import org.allaymc.api.block.interfaces.BlockInfestedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockInfestedStoneBehavior;
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
import org.allaymc.api.block.interfaces.BlockLargeFernBehavior;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.interfaces.BlockLeverBehavior;
import org.allaymc.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.block.interfaces.BlockLilacBehavior;
import org.allaymc.api.block.interfaces.BlockLilyOfTheValleyBehavior;
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
import org.allaymc.api.block.interfaces.BlockOrangeTulipBehavior;
import org.allaymc.api.block.interfaces.BlockOxeyeDaisyBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockPackedIceBehavior;
import org.allaymc.api.block.interfaces.BlockPackedMudBehavior;
import org.allaymc.api.block.interfaces.BlockPearlescentFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockPeonyBehavior;
import org.allaymc.api.block.interfaces.BlockPinkPetalsBehavior;
import org.allaymc.api.block.interfaces.BlockPinkTulipBehavior;
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
import org.allaymc.api.block.interfaces.BlockPoppyBehavior;
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
import org.allaymc.api.block.interfaces.BlockQuartzPillarBehavior;
import org.allaymc.api.block.interfaces.BlockRailBehavior;
import org.allaymc.api.block.interfaces.BlockRawCopperBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRawGoldBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRawIronBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.block.interfaces.BlockRedMushroomBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedNetherBrickBehavior;
import org.allaymc.api.block.interfaces.BlockRedTulipBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneWireBehavior;
import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockReserved6Behavior;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.block.interfaces.BlockRoseBushBehavior;
import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.block.interfaces.BlockSculkBehavior;
import org.allaymc.api.block.interfaces.BlockSculkCatalystBehavior;
import org.allaymc.api.block.interfaces.BlockSculkSensorBehavior;
import org.allaymc.api.block.interfaces.BlockSculkShriekerBehavior;
import org.allaymc.api.block.interfaces.BlockSculkVeinBehavior;
import org.allaymc.api.block.interfaces.BlockSeaLanternBehavior;
import org.allaymc.api.block.interfaces.BlockSeaPickleBehavior;
import org.allaymc.api.block.interfaces.BlockSeagrassBehavior;
import org.allaymc.api.block.interfaces.BlockShortGrassBehavior;
import org.allaymc.api.block.interfaces.BlockShroomlightBehavior;
import org.allaymc.api.block.interfaces.BlockSkullBehavior;
import org.allaymc.api.block.interfaces.BlockSlimeBehavior;
import org.allaymc.api.block.interfaces.BlockSmallDripleafBlockBehavior;
import org.allaymc.api.block.interfaces.BlockSmithingTableBehavior;
import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.block.interfaces.BlockSmoothBasaltBehavior;
import org.allaymc.api.block.interfaces.BlockSmoothQuartzBehavior;
import org.allaymc.api.block.interfaces.BlockSmoothStoneBehavior;
import org.allaymc.api.block.interfaces.BlockSnifferEggBehavior;
import org.allaymc.api.block.interfaces.BlockSnowBehavior;
import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.block.interfaces.BlockSoulCampfireBehavior;
import org.allaymc.api.block.interfaces.BlockSoulFireBehavior;
import org.allaymc.api.block.interfaces.BlockSoulLanternBehavior;
import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.block.interfaces.BlockSpongeBehavior;
import org.allaymc.api.block.interfaces.BlockSporeBlossomBehavior;
import org.allaymc.api.block.interfaces.BlockSprucePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.block.interfaces.BlockStoneBehavior;
import org.allaymc.api.block.interfaces.BlockStonePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedBambooBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedCrimsonHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedCrimsonStemBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedWarpedHyphaeBehavior;
import org.allaymc.api.block.interfaces.BlockStrippedWarpedStemBehavior;
import org.allaymc.api.block.interfaces.BlockStructureBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.block.interfaces.BlockSunflowerBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.block.interfaces.BlockTallGrassBehavior;
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
import org.allaymc.api.block.interfaces.BlockVaultBehavior;
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
import org.allaymc.api.block.interfaces.BlockWhiteTulipBehavior;
import org.allaymc.api.block.interfaces.BlockWitherRoseBehavior;
import org.allaymc.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockChippedAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockDamagedAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockDeprecatedAnvilBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledNetherBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockChiseledTuffBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedDeepslateBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedNetherBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedPolishedBlackstoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockCrackedStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockDeepslateBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockEndBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedChiseledStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedCrackedStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedMossyStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockInfestedStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockMossyStoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockMudBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockPolishedBlackstoneBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockPrismarineBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockQuartzBricksBehavior;
import org.allaymc.api.block.interfaces.bricks.BlockStoneBricksBehavior;
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
import org.allaymc.api.block.interfaces.coralblock.BlockBrainCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockBubbleCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadBrainCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadBubbleCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadFireCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadHornCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadTubeCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockFireCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockHornCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralblock.BlockTubeCoralBlockBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockBrainCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockBubbleCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadBrainCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadBubbleCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadFireCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadHornCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadTubeCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockFireCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockHornCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralfan.BlockTubeCoralFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockBrainCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockBubbleCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadBrainCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadBubbleCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadFireCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadHornCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadTubeCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockFireCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockHornCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.coralwallfan.BlockTubeCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.dirt.BlockCoarseDirtBehavior;
import org.allaymc.api.block.interfaces.dirt.BlockDirtBehavior;
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
import org.allaymc.api.block.interfaces.leaves.BlockAcaciaLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesFloweredBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockBirchLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockCherryLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockDarkOakLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockJungleLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockMangroveLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockOakLeavesBehavior;
import org.allaymc.api.block.interfaces.leaves.BlockSpruceLeavesBehavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock0Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock10Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock11Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock12Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock13Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock14Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock15Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock1Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock2Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock3Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock4Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock5Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock6Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock7Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock8Behavior;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock9Behavior;
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
import org.allaymc.api.block.interfaces.sand.BlockRedSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSoulSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockChiseledRedSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockChiseledSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockCutRedSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockCutSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockRedSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockSmoothRedSandstoneBehavior;
import org.allaymc.api.block.interfaces.sandstone.BlockSmoothSandstoneBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockAcaciaSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockBambooSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockBirchSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockCherrySaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockDarkOakSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockJungleSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockOakSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockSpruceSaplingBehavior;
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
import org.allaymc.api.block.interfaces.slab.BlockAcaciaDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockAcaciaSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockAndesiteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockAndesiteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooMosaicDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooMosaicSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBambooSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBirchDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBirchSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBlackstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBlackstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCherryDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCherrySlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobblestoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCobblestoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCrimsonDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCrimsonSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutRedSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutRedSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockCutSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDarkOakDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDarkOakSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDarkPrismarineDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDarkPrismarineSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateTileDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDeepslateTileSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDioriteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDioriteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockEndStoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockEndStoneBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockExposedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockExposedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockGraniteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockGraniteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockJungleDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockJungleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMangroveDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMangroveSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMossyCobblestoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMossyCobblestoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMossyStoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMossyStoneBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMudBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockMudBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockNetherBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockNetherBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockNormalStoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockNormalStoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOakDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOakSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOxidizedCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockOxidizedDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPetrifiedOakDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPetrifiedOakSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedAndesiteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedAndesiteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDioriteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedDioriteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedGraniteDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedGraniteSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedTuffDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPolishedTuffSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPrismarineBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPrismarineBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPrismarineDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPrismarineSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPurpurDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockPurpurSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockQuartzDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockQuartzSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockRedNetherBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockRedNetherBrickSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockRedSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockRedSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothQuartzDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothQuartzSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothRedSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothRedSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothSandstoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothSandstoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothStoneDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSmoothStoneSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSpruceDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockSpruceSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.slab.BlockStoneBrickSlabBehavior;
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
import org.allaymc.api.block.interfaces.wood.BlockAcaciaWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockBirchWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockCherryWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockDarkOakWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockJungleWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockMangroveWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockOakWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockSpruceWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedAcaciaWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedBirchWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedCherryWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedDarkOakWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedJungleWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedMangroveWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedOakWoodBehavior;
import org.allaymc.api.block.interfaces.wood.BlockStrippedSpruceWoodBehavior;
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
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class BlockTypeDefaultInitializer {
    public static void initActivatorRail() {
        if (BlockTypes.ACTIVATOR_RAIL != null) return;
        BlockTypes.ACTIVATOR_RAIL = AllayBlockType
                .builder(BlockActivatorRailBehavior.class)
                .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initAir() {
        if (BlockTypes.AIR != null) return;
        BlockTypes.AIR = AllayBlockType
                .builder(BlockAirBehavior.class)
                .vanillaBlock(BlockId.AIR)
                .build();
    }

    public static void initAllium() {
        if (BlockTypes.ALLIUM != null) return;
        BlockTypes.ALLIUM = AllayBlockType
                .builder(BlockAlliumBehavior.class)
                .vanillaBlock(BlockId.ALLIUM)
                .build();
    }

    public static void initAllow() {
        if (BlockTypes.ALLOW != null) return;
        BlockTypes.ALLOW = AllayBlockType
                .builder(BlockAllowBehavior.class)
                .vanillaBlock(BlockId.ALLOW)
                .build();
    }

    public static void initAmethystBlock() {
        if (BlockTypes.AMETHYST_BLOCK != null) return;
        BlockTypes.AMETHYST_BLOCK = AllayBlockType
                .builder(BlockAmethystBlockBehavior.class)
                .vanillaBlock(BlockId.AMETHYST_BLOCK)
                .build();
    }

    public static void initAmethystCluster() {
        if (BlockTypes.AMETHYST_CLUSTER != null) return;
        BlockTypes.AMETHYST_CLUSTER = AllayBlockType
                .builder(BlockAmethystClusterBehavior.class)
                .vanillaBlock(BlockId.AMETHYST_CLUSTER)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initAncientDebris() {
        if (BlockTypes.ANCIENT_DEBRIS != null) return;
        BlockTypes.ANCIENT_DEBRIS = AllayBlockType
                .builder(BlockAncientDebrisBehavior.class)
                .vanillaBlock(BlockId.ANCIENT_DEBRIS)
                .build();
    }

    public static void initAndesite() {
        if (BlockTypes.ANDESITE != null) return;
        BlockTypes.ANDESITE = AllayBlockType
                .builder(BlockAndesiteBehavior.class)
                .vanillaBlock(BlockId.ANDESITE)
                .build();
    }

    public static void initAnvil() {
        if (BlockTypes.ANVIL != null) return;
        BlockTypes.ANVIL = AllayBlockType
                .builder(BlockAnvilBehavior.class)
                .vanillaBlock(BlockId.ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initAzalea() {
        if (BlockTypes.AZALEA != null) return;
        BlockTypes.AZALEA = AllayBlockType
                .builder(BlockAzaleaBehavior.class)
                .vanillaBlock(BlockId.AZALEA)
                .build();
    }

    public static void initAzaleaLeavesFlowered() {
        if (BlockTypes.AZALEA_LEAVES_FLOWERED != null) return;
        BlockTypes.AZALEA_LEAVES_FLOWERED = AllayBlockType
                .builder(BlockAzaleaLeavesFloweredBehavior.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES_FLOWERED)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAzureBluet() {
        if (BlockTypes.AZURE_BLUET != null) return;
        BlockTypes.AZURE_BLUET = AllayBlockType
                .builder(BlockAzureBluetBehavior.class)
                .vanillaBlock(BlockId.AZURE_BLUET)
                .build();
    }

    public static void initBamboo() {
        if (BlockTypes.BAMBOO != null) return;
        BlockTypes.BAMBOO = AllayBlockType
                .builder(BlockBambooBehavior.class)
                .vanillaBlock(BlockId.BAMBOO)
                .setProperties(BlockPropertyTypes.AGE_BIT, BlockPropertyTypes.BAMBOO_LEAF_SIZE, BlockPropertyTypes.BAMBOO_STALK_THICKNESS)
                .build();
    }

    public static void initBambooBlock() {
        if (BlockTypes.BAMBOO_BLOCK != null) return;
        BlockTypes.BAMBOO_BLOCK = AllayBlockType
                .builder(BlockBambooBlockBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBambooMosaic() {
        if (BlockTypes.BAMBOO_MOSAIC != null) return;
        BlockTypes.BAMBOO_MOSAIC = AllayBlockType
                .builder(BlockBambooMosaicBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC)
                .build();
    }

    public static void initBarrel() {
        if (BlockTypes.BARREL != null) return;
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehavior.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBarrier() {
        if (BlockTypes.BARRIER != null) return;
        BlockTypes.BARRIER = AllayBlockType
                .builder(BlockBarrierBehavior.class)
                .vanillaBlock(BlockId.BARRIER)
                .build();
    }

    public static void initBasalt() {
        if (BlockTypes.BASALT != null) return;
        BlockTypes.BASALT = AllayBlockType
                .builder(BlockBasaltBehavior.class)
                .vanillaBlock(BlockId.BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBeacon() {
        if (BlockTypes.BEACON != null) return;
        BlockTypes.BEACON = AllayBlockType
                .builder(BlockBeaconBehavior.class)
                .vanillaBlock(BlockId.BEACON)
                .build();
    }

    public static void initBed() {
        if (BlockTypes.BED != null) return;
        BlockTypes.BED = AllayBlockType
                .builder(BlockBedBehavior.class)
                .vanillaBlock(BlockId.BED)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HEAD_PIECE_BIT, BlockPropertyTypes.OCCUPIED_BIT)
                .build();
    }

    public static void initBedrock() {
        if (BlockTypes.BEDROCK != null) return;
        BlockTypes.BEDROCK = AllayBlockType
                .builder(BlockBedrockBehavior.class)
                .vanillaBlock(BlockId.BEDROCK)
                .setProperties(BlockPropertyTypes.INFINIBURN_BIT)
                .build();
    }

    public static void initBeeNest() {
        if (BlockTypes.BEE_NEST != null) return;
        BlockTypes.BEE_NEST = AllayBlockType
                .builder(BlockBeeNestBehavior.class)
                .vanillaBlock(BlockId.BEE_NEST)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeehive() {
        if (BlockTypes.BEEHIVE != null) return;
        BlockTypes.BEEHIVE = AllayBlockType
                .builder(BlockBeehiveBehavior.class)
                .vanillaBlock(BlockId.BEEHIVE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeetroot() {
        if (BlockTypes.BEETROOT != null) return;
        BlockTypes.BEETROOT = AllayBlockType
                .builder(BlockBeetrootBehavior.class)
                .vanillaBlock(BlockId.BEETROOT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initBell() {
        if (BlockTypes.BELL != null) return;
        BlockTypes.BELL = AllayBlockType
                .builder(BlockBellBehavior.class)
                .vanillaBlock(BlockId.BELL)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initBigDripleaf() {
        if (BlockTypes.BIG_DRIPLEAF != null) return;
        BlockTypes.BIG_DRIPLEAF = AllayBlockType
                .builder(BlockBigDripleafBehavior.class)
                .vanillaBlock(BlockId.BIG_DRIPLEAF)
                .setProperties(BlockPropertyTypes.BIG_DRIPLEAF_HEAD, BlockPropertyTypes.BIG_DRIPLEAF_TILT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBlackstone() {
        if (BlockTypes.BLACKSTONE != null) return;
        BlockTypes.BLACKSTONE = AllayBlockType
                .builder(BlockBlackstoneBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE)
                .build();
    }

    public static void initBlastFurnace() {
        if (BlockTypes.BLAST_FURNACE != null) return;
        BlockTypes.BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBlueIce() {
        if (BlockTypes.BLUE_ICE != null) return;
        BlockTypes.BLUE_ICE = AllayBlockType
                .builder(BlockBlueIceBehavior.class)
                .vanillaBlock(BlockId.BLUE_ICE)
                .build();
    }

    public static void initBlueOrchid() {
        if (BlockTypes.BLUE_ORCHID != null) return;
        BlockTypes.BLUE_ORCHID = AllayBlockType
                .builder(BlockBlueOrchidBehavior.class)
                .vanillaBlock(BlockId.BLUE_ORCHID)
                .build();
    }

    public static void initBoneBlock() {
        if (BlockTypes.BONE_BLOCK != null) return;
        BlockTypes.BONE_BLOCK = AllayBlockType
                .builder(BlockBoneBlockBehavior.class)
                .vanillaBlock(BlockId.BONE_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBookshelf() {
        if (BlockTypes.BOOKSHELF != null) return;
        BlockTypes.BOOKSHELF = AllayBlockType
                .builder(BlockBookshelfBehavior.class)
                .vanillaBlock(BlockId.BOOKSHELF)
                .build();
    }

    public static void initBorderBlock() {
        if (BlockTypes.BORDER_BLOCK != null) return;
        BlockTypes.BORDER_BLOCK = AllayBlockType
                .builder(BlockBorderBlockBehavior.class)
                .vanillaBlock(BlockId.BORDER_BLOCK)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBrainCoral() {
        if (BlockTypes.BRAIN_CORAL != null) return;
        BlockTypes.BRAIN_CORAL = AllayBlockType
                .builder(BlockBrainCoralBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL)
                .build();
    }

    public static void initBrainCoralBlock() {
        if (BlockTypes.BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.BRAIN_CORAL_BLOCK = AllayBlockType
                .builder(BlockBrainCoralBlockBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initBrainCoralFan() {
        if (BlockTypes.BRAIN_CORAL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_FAN = AllayBlockType
                .builder(BlockBrainCoralFanBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBrainCoralWallFan() {
        if (BlockTypes.BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockBrainCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBrewingStand() {
        if (BlockTypes.BREWING_STAND != null) return;
        BlockTypes.BREWING_STAND = AllayBlockType
                .builder(BlockBrewingStandBehavior.class)
                .vanillaBlock(BlockId.BREWING_STAND)
                .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                .build();
    }

    public static void initBrickBlock() {
        if (BlockTypes.BRICK_BLOCK != null) return;
        BlockTypes.BRICK_BLOCK = AllayBlockType
                .builder(BlockBrickBlockBehavior.class)
                .vanillaBlock(BlockId.BRICK_BLOCK)
                .build();
    }

    public static void initBrownMushroom() {
        if (BlockTypes.BROWN_MUSHROOM != null) return;
        BlockTypes.BROWN_MUSHROOM = AllayBlockType
                .builder(BlockBrownMushroomBehavior.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM)
                .build();
    }

    public static void initBrownMushroomBlock() {
        if (BlockTypes.BROWN_MUSHROOM_BLOCK != null) return;
        BlockTypes.BROWN_MUSHROOM_BLOCK = AllayBlockType
                .builder(BlockBrownMushroomBlockBehavior.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initBubbleColumn() {
        if (BlockTypes.BUBBLE_COLUMN != null) return;
        BlockTypes.BUBBLE_COLUMN = AllayBlockType
                .builder(BlockBubbleColumnBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_COLUMN)
                .setProperties(BlockPropertyTypes.DRAG_DOWN)
                .build();
    }

    public static void initBubbleCoral() {
        if (BlockTypes.BUBBLE_CORAL != null) return;
        BlockTypes.BUBBLE_CORAL = AllayBlockType
                .builder(BlockBubbleCoralBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL)
                .build();
    }

    public static void initBubbleCoralBlock() {
        if (BlockTypes.BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.BUBBLE_CORAL_BLOCK = AllayBlockType
                .builder(BlockBubbleCoralBlockBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initBubbleCoralFan() {
        if (BlockTypes.BUBBLE_CORAL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_FAN = AllayBlockType
                .builder(BlockBubbleCoralFanBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBubbleCoralWallFan() {
        if (BlockTypes.BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockBubbleCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBuddingAmethyst() {
        if (BlockTypes.BUDDING_AMETHYST != null) return;
        BlockTypes.BUDDING_AMETHYST = AllayBlockType
                .builder(BlockBuddingAmethystBehavior.class)
                .vanillaBlock(BlockId.BUDDING_AMETHYST)
                .build();
    }

    public static void initCactus() {
        if (BlockTypes.CACTUS != null) return;
        BlockTypes.CACTUS = AllayBlockType
                .builder(BlockCactusBehavior.class)
                .vanillaBlock(BlockId.CACTUS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initCake() {
        if (BlockTypes.CAKE != null) return;
        BlockTypes.CAKE = AllayBlockType
                .builder(BlockCakeBehavior.class)
                .vanillaBlock(BlockId.CAKE)
                .setProperties(BlockPropertyTypes.BITE_COUNTER)
                .build();
    }

    public static void initCalcite() {
        if (BlockTypes.CALCITE != null) return;
        BlockTypes.CALCITE = AllayBlockType
                .builder(BlockCalciteBehavior.class)
                .vanillaBlock(BlockId.CALCITE)
                .build();
    }

    public static void initCalibratedSculkSensor() {
        if (BlockTypes.CALIBRATED_SCULK_SENSOR != null) return;
        BlockTypes.CALIBRATED_SCULK_SENSOR = AllayBlockType
                .builder(BlockCalibratedSculkSensorBehavior.class)
                .vanillaBlock(BlockId.CALIBRATED_SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initCamera() {
        if (BlockTypes.CAMERA != null) return;
        BlockTypes.CAMERA = AllayBlockType
                .builder(BlockCameraBehavior.class)
                .vanillaBlock(BlockId.CAMERA)
                .build();
    }

    public static void initCampfire() {
        if (BlockTypes.CAMPFIRE != null) return;
        BlockTypes.CAMPFIRE = AllayBlockType
                .builder(BlockCampfireBehavior.class)
                .vanillaBlock(BlockId.CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCandle() {
        if (BlockTypes.CANDLE != null) return;
        BlockTypes.CANDLE = AllayBlockType
                .builder(BlockCandleBehavior.class)
                .vanillaBlock(BlockId.CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCandleCake() {
        if (BlockTypes.CANDLE_CAKE != null) return;
        BlockTypes.CANDLE_CAKE = AllayBlockType
                .builder(BlockCandleCakeBehavior.class)
                .vanillaBlock(BlockId.CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCarrots() {
        if (BlockTypes.CARROTS != null) return;
        BlockTypes.CARROTS = AllayBlockType
                .builder(BlockCarrotsBehavior.class)
                .vanillaBlock(BlockId.CARROTS)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initCartographyTable() {
        if (BlockTypes.CARTOGRAPHY_TABLE != null) return;
        BlockTypes.CARTOGRAPHY_TABLE = AllayBlockType
                .builder(BlockCartographyTableBehavior.class)
                .vanillaBlock(BlockId.CARTOGRAPHY_TABLE)
                .build();
    }

    public static void initCarvedPumpkin() {
        if (BlockTypes.CARVED_PUMPKIN != null) return;
        BlockTypes.CARVED_PUMPKIN = AllayBlockType
                .builder(BlockCarvedPumpkinBehavior.class)
                .vanillaBlock(BlockId.CARVED_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCauldron() {
        if (BlockTypes.CAULDRON != null) return;
        BlockTypes.CAULDRON = AllayBlockType
                .builder(BlockCauldronBehavior.class)
                .vanillaBlock(BlockId.CAULDRON)
                .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                .build();
    }

    public static void initCaveVines() {
        if (BlockTypes.CAVE_VINES != null) return;
        BlockTypes.CAVE_VINES = AllayBlockType
                .builder(BlockCaveVinesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesBodyWithBerries() {
        if (BlockTypes.CAVE_VINES_BODY_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_BODY_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES_BODY_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesHeadWithBerries() {
        if (BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES = AllayBlockType
                .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES_HEAD_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initChain() {
        if (BlockTypes.CHAIN != null) return;
        BlockTypes.CHAIN = AllayBlockType
                .builder(BlockChainBehavior.class)
                .vanillaBlock(BlockId.CHAIN)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChainCommandBlock() {
        if (BlockTypes.CHAIN_COMMAND_BLOCK != null) return;
        BlockTypes.CHAIN_COMMAND_BLOCK = AllayBlockType
                .builder(BlockChainCommandBlockBehavior.class)
                .vanillaBlock(BlockId.CHAIN_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initChemicalHeat() {
        if (BlockTypes.CHEMICAL_HEAT != null) return;
        BlockTypes.CHEMICAL_HEAT = AllayBlockType
                .builder(BlockChemicalHeatBehavior.class)
                .vanillaBlock(BlockId.CHEMICAL_HEAT)
                .build();
    }

    public static void initChemistryTable() {
        if (BlockTypes.CHEMISTRY_TABLE != null) return;
        BlockTypes.CHEMISTRY_TABLE = AllayBlockType
                .builder(BlockChemistryTableBehavior.class)
                .vanillaBlock(BlockId.CHEMISTRY_TABLE)
                .setProperties(BlockPropertyTypes.CHEMISTRY_TABLE_TYPE, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initChest() {
        if (BlockTypes.CHEST != null) return;
        BlockTypes.CHEST = AllayBlockType
                .builder(BlockChestBehavior.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChippedAnvil() {
        if (BlockTypes.CHIPPED_ANVIL != null) return;
        BlockTypes.CHIPPED_ANVIL = AllayBlockType
                .builder(BlockChippedAnvilBehavior.class)
                .vanillaBlock(BlockId.CHIPPED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChiseledBookshelf() {
        if (BlockTypes.CHISELED_BOOKSHELF != null) return;
        BlockTypes.CHISELED_BOOKSHELF = AllayBlockType
                .builder(BlockChiseledBookshelfBehavior.class)
                .vanillaBlock(BlockId.CHISELED_BOOKSHELF)
                .setProperties(BlockPropertyTypes.BOOKS_STORED, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initChiseledCopper() {
        if (BlockTypes.CHISELED_COPPER != null) return;
        BlockTypes.CHISELED_COPPER = AllayBlockType
                .builder(BlockChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.CHISELED_COPPER)
                .build();
    }

    public static void initChiseledDeepslate() {
        if (BlockTypes.CHISELED_DEEPSLATE != null) return;
        BlockTypes.CHISELED_DEEPSLATE = AllayBlockType
                .builder(BlockChiseledDeepslateBehavior.class)
                .vanillaBlock(BlockId.CHISELED_DEEPSLATE)
                .build();
    }

    public static void initChiseledNetherBricks() {
        if (BlockTypes.CHISELED_NETHER_BRICKS != null) return;
        BlockTypes.CHISELED_NETHER_BRICKS = AllayBlockType
                .builder(BlockChiseledNetherBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_NETHER_BRICKS)
                .build();
    }

    public static void initChiseledPolishedBlackstone() {
        if (BlockTypes.CHISELED_POLISHED_BLACKSTONE != null) return;
        BlockTypes.CHISELED_POLISHED_BLACKSTONE = AllayBlockType
                .builder(BlockChiseledPolishedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_POLISHED_BLACKSTONE)
                .build();
    }

    public static void initChiseledQuartzBlock() {
        if (BlockTypes.CHISELED_QUARTZ_BLOCK != null) return;
        BlockTypes.CHISELED_QUARTZ_BLOCK = AllayBlockType
                .builder(BlockChiseledQuartzBlockBehavior.class)
                .vanillaBlock(BlockId.CHISELED_QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChiseledRedSandstone() {
        if (BlockTypes.CHISELED_RED_SANDSTONE != null) return;
        BlockTypes.CHISELED_RED_SANDSTONE = AllayBlockType
                .builder(BlockChiseledRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_RED_SANDSTONE)
                .build();
    }

    public static void initChiseledSandstone() {
        if (BlockTypes.CHISELED_SANDSTONE != null) return;
        BlockTypes.CHISELED_SANDSTONE = AllayBlockType
                .builder(BlockChiseledSandstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_SANDSTONE)
                .build();
    }

    public static void initChiseledStoneBricks() {
        if (BlockTypes.CHISELED_STONE_BRICKS != null) return;
        BlockTypes.CHISELED_STONE_BRICKS = AllayBlockType
                .builder(BlockChiseledStoneBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initChiseledTuff() {
        if (BlockTypes.CHISELED_TUFF != null) return;
        BlockTypes.CHISELED_TUFF = AllayBlockType
                .builder(BlockChiseledTuffBehavior.class)
                .vanillaBlock(BlockId.CHISELED_TUFF)
                .build();
    }

    public static void initChiseledTuffBricks() {
        if (BlockTypes.CHISELED_TUFF_BRICKS != null) return;
        BlockTypes.CHISELED_TUFF_BRICKS = AllayBlockType
                .builder(BlockChiseledTuffBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_TUFF_BRICKS)
                .build();
    }

    public static void initChorusFlower() {
        if (BlockTypes.CHORUS_FLOWER != null) return;
        BlockTypes.CHORUS_FLOWER = AllayBlockType
                .builder(BlockChorusFlowerBehavior.class)
                .vanillaBlock(BlockId.CHORUS_FLOWER)
                .setProperties(BlockPropertyTypes.AGE_6)
                .build();
    }

    public static void initChorusPlant() {
        if (BlockTypes.CHORUS_PLANT != null) return;
        BlockTypes.CHORUS_PLANT = AllayBlockType
                .builder(BlockChorusPlantBehavior.class)
                .vanillaBlock(BlockId.CHORUS_PLANT)
                .build();
    }

    public static void initClay() {
        if (BlockTypes.CLAY != null) return;
        BlockTypes.CLAY = AllayBlockType
                .builder(BlockClayBehavior.class)
                .vanillaBlock(BlockId.CLAY)
                .build();
    }

    public static void initClientRequestPlaceholderBlock() {
        if (BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK != null) return;
        BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = AllayBlockType
                .builder(BlockClientRequestPlaceholderBlockBehavior.class)
                .vanillaBlock(BlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                .build();
    }

    public static void initCoalBlock() {
        if (BlockTypes.COAL_BLOCK != null) return;
        BlockTypes.COAL_BLOCK = AllayBlockType
                .builder(BlockCoalBlockBehavior.class)
                .vanillaBlock(BlockId.COAL_BLOCK)
                .build();
    }

    public static void initCoalOre() {
        if (BlockTypes.COAL_ORE != null) return;
        BlockTypes.COAL_ORE = AllayBlockType
                .builder(BlockCoalOreBehavior.class)
                .vanillaBlock(BlockId.COAL_ORE)
                .build();
    }

    public static void initCoarseDirt() {
        if (BlockTypes.COARSE_DIRT != null) return;
        BlockTypes.COARSE_DIRT = AllayBlockType
                .builder(BlockCoarseDirtBehavior.class)
                .vanillaBlock(BlockId.COARSE_DIRT)
                .build();
    }

    public static void initCobbledDeepslate() {
        if (BlockTypes.COBBLED_DEEPSLATE != null) return;
        BlockTypes.COBBLED_DEEPSLATE = AllayBlockType
                .builder(BlockCobbledDeepslateBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE)
                .build();
    }

    public static void initCobblestone() {
        if (BlockTypes.COBBLESTONE != null) return;
        BlockTypes.COBBLESTONE = AllayBlockType
                .builder(BlockCobblestoneBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE)
                .build();
    }

    public static void initCocoa() {
        if (BlockTypes.COCOA != null) return;
        BlockTypes.COCOA = AllayBlockType
                .builder(BlockCocoaBehavior.class)
                .vanillaBlock(BlockId.COCOA)
                .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initColoredTorchBp() {
        if (BlockTypes.COLORED_TORCH_BP != null) return;
        BlockTypes.COLORED_TORCH_BP = AllayBlockType
                .builder(BlockColoredTorchBpBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_BP)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchRg() {
        if (BlockTypes.COLORED_TORCH_RG != null) return;
        BlockTypes.COLORED_TORCH_RG = AllayBlockType
                .builder(BlockColoredTorchRgBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_RG)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initCommandBlock() {
        if (BlockTypes.COMMAND_BLOCK != null) return;
        BlockTypes.COMMAND_BLOCK = AllayBlockType
                .builder(BlockCommandBlockBehavior.class)
                .vanillaBlock(BlockId.COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initComposter() {
        if (BlockTypes.COMPOSTER != null) return;
        BlockTypes.COMPOSTER = AllayBlockType
                .builder(BlockComposterBehavior.class)
                .vanillaBlock(BlockId.COMPOSTER)
                .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                .build();
    }

    public static void initConduit() {
        if (BlockTypes.CONDUIT != null) return;
        BlockTypes.CONDUIT = AllayBlockType
                .builder(BlockConduitBehavior.class)
                .vanillaBlock(BlockId.CONDUIT)
                .build();
    }

    public static void initCopperBlock() {
        if (BlockTypes.COPPER_BLOCK != null) return;
        BlockTypes.COPPER_BLOCK = AllayBlockType
                .builder(BlockCopperBlockBehavior.class)
                .vanillaBlock(BlockId.COPPER_BLOCK)
                .build();
    }

    public static void initCopperBulb() {
        if (BlockTypes.COPPER_BULB != null) return;
        BlockTypes.COPPER_BULB = AllayBlockType
                .builder(BlockCopperBulbBehavior.class)
                .vanillaBlock(BlockId.COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initCopperGrate() {
        if (BlockTypes.COPPER_GRATE != null) return;
        BlockTypes.COPPER_GRATE = AllayBlockType
                .builder(BlockCopperGrateBehavior.class)
                .vanillaBlock(BlockId.COPPER_GRATE)
                .build();
    }

    public static void initCopperOre() {
        if (BlockTypes.COPPER_ORE != null) return;
        BlockTypes.COPPER_ORE = AllayBlockType
                .builder(BlockCopperOreBehavior.class)
                .vanillaBlock(BlockId.COPPER_ORE)
                .build();
    }

    public static void initCornflower() {
        if (BlockTypes.CORNFLOWER != null) return;
        BlockTypes.CORNFLOWER = AllayBlockType
                .builder(BlockCornflowerBehavior.class)
                .vanillaBlock(BlockId.CORNFLOWER)
                .build();
    }

    public static void initCrackedDeepslateBricks() {
        if (BlockTypes.CRACKED_DEEPSLATE_BRICKS != null) return;
        BlockTypes.CRACKED_DEEPSLATE_BRICKS = AllayBlockType
                .builder(BlockCrackedDeepslateBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_BRICKS)
                .build();
    }

    public static void initCrackedDeepslateTiles() {
        if (BlockTypes.CRACKED_DEEPSLATE_TILES != null) return;
        BlockTypes.CRACKED_DEEPSLATE_TILES = AllayBlockType
                .builder(BlockCrackedDeepslateTilesBehavior.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_TILES)
                .build();
    }

    public static void initCrackedNetherBricks() {
        if (BlockTypes.CRACKED_NETHER_BRICKS != null) return;
        BlockTypes.CRACKED_NETHER_BRICKS = AllayBlockType
                .builder(BlockCrackedNetherBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_NETHER_BRICKS)
                .build();
    }

    public static void initCrackedPolishedBlackstoneBricks() {
        if (BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initCrackedStoneBricks() {
        if (BlockTypes.CRACKED_STONE_BRICKS != null) return;
        BlockTypes.CRACKED_STONE_BRICKS = AllayBlockType
                .builder(BlockCrackedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initCrafter() {
        if (BlockTypes.CRAFTER != null) return;
        BlockTypes.CRAFTER = AllayBlockType
                .builder(BlockCrafterBehavior.class)
                .vanillaBlock(BlockId.CRAFTER)
                .setProperties(BlockPropertyTypes.CRAFTING, BlockPropertyTypes.ORIENTATION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initCraftingTable() {
        if (BlockTypes.CRAFTING_TABLE != null) return;
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehavior.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .build();
    }

    public static void initCrimsonFungus() {
        if (BlockTypes.CRIMSON_FUNGUS != null) return;
        BlockTypes.CRIMSON_FUNGUS = AllayBlockType
                .builder(BlockCrimsonFungusBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                .build();
    }

    public static void initCrimsonHyphae() {
        if (BlockTypes.CRIMSON_HYPHAE != null) return;
        BlockTypes.CRIMSON_HYPHAE = AllayBlockType
                .builder(BlockCrimsonHyphaeBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCrimsonNylium() {
        if (BlockTypes.CRIMSON_NYLIUM != null) return;
        BlockTypes.CRIMSON_NYLIUM = AllayBlockType
                .builder(BlockCrimsonNyliumBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_NYLIUM)
                .build();
    }

    public static void initCrimsonRoots() {
        if (BlockTypes.CRIMSON_ROOTS != null) return;
        BlockTypes.CRIMSON_ROOTS = AllayBlockType
                .builder(BlockCrimsonRootsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_ROOTS)
                .build();
    }

    public static void initCrimsonStem() {
        if (BlockTypes.CRIMSON_STEM != null) return;
        BlockTypes.CRIMSON_STEM = AllayBlockType
                .builder(BlockCrimsonStemBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCryingObsidian() {
        if (BlockTypes.CRYING_OBSIDIAN != null) return;
        BlockTypes.CRYING_OBSIDIAN = AllayBlockType
                .builder(BlockCryingObsidianBehavior.class)
                .vanillaBlock(BlockId.CRYING_OBSIDIAN)
                .build();
    }

    public static void initCutCopper() {
        if (BlockTypes.CUT_COPPER != null) return;
        BlockTypes.CUT_COPPER = AllayBlockType
                .builder(BlockCutCopperBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER)
                .build();
    }

    public static void initCutRedSandstone() {
        if (BlockTypes.CUT_RED_SANDSTONE != null) return;
        BlockTypes.CUT_RED_SANDSTONE = AllayBlockType
                .builder(BlockCutRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE)
                .build();
    }

    public static void initCutSandstone() {
        if (BlockTypes.CUT_SANDSTONE != null) return;
        BlockTypes.CUT_SANDSTONE = AllayBlockType
                .builder(BlockCutSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE)
                .build();
    }

    public static void initDamagedAnvil() {
        if (BlockTypes.DAMAGED_ANVIL != null) return;
        BlockTypes.DAMAGED_ANVIL = AllayBlockType
                .builder(BlockDamagedAnvilBehavior.class)
                .vanillaBlock(BlockId.DAMAGED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDandelion() {
        if (BlockTypes.DANDELION != null) return;
        BlockTypes.DANDELION = AllayBlockType
                .builder(BlockDandelionBehavior.class)
                .vanillaBlock(BlockId.DANDELION)
                .build();
    }

    public static void initDarkPrismarine() {
        if (BlockTypes.DARK_PRISMARINE != null) return;
        BlockTypes.DARK_PRISMARINE = AllayBlockType
                .builder(BlockDarkPrismarineBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE)
                .build();
    }

    public static void initDaylightDetector() {
        if (BlockTypes.DAYLIGHT_DETECTOR != null) return;
        BlockTypes.DAYLIGHT_DETECTOR = AllayBlockType
                .builder(BlockDaylightDetectorBehavior.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDaylightDetectorInverted() {
        if (BlockTypes.DAYLIGHT_DETECTOR_INVERTED != null) return;
        BlockTypes.DAYLIGHT_DETECTOR_INVERTED = AllayBlockType
                .builder(BlockDaylightDetectorInvertedBehavior.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR_INVERTED)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDeadBrainCoral() {
        if (BlockTypes.DEAD_BRAIN_CORAL != null) return;
        BlockTypes.DEAD_BRAIN_CORAL = AllayBlockType
                .builder(BlockDeadBrainCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL)
                .build();
    }

    public static void initDeadBrainCoralBlock() {
        if (BlockTypes.DEAD_BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadBrainCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBrainCoralFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_FAN = AllayBlockType
                .builder(BlockDeadBrainCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBrainCoralWallFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadBrainCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoral() {
        if (BlockTypes.DEAD_BUBBLE_CORAL != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL = AllayBlockType
                .builder(BlockDeadBubbleCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL)
                .build();
    }

    public static void initDeadBubbleCoralBlock() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadBubbleCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBubbleCoralFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadBubbleCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoralWallFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadBubbleCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadFireCoral() {
        if (BlockTypes.DEAD_FIRE_CORAL != null) return;
        BlockTypes.DEAD_FIRE_CORAL = AllayBlockType
                .builder(BlockDeadFireCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL)
                .build();
    }

    public static void initDeadFireCoralBlock() {
        if (BlockTypes.DEAD_FIRE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_FIRE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadFireCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadFireCoralFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadFireCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadFireCoralWallFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadFireCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadHornCoral() {
        if (BlockTypes.DEAD_HORN_CORAL != null) return;
        BlockTypes.DEAD_HORN_CORAL = AllayBlockType
                .builder(BlockDeadHornCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL)
                .build();
    }

    public static void initDeadHornCoralBlock() {
        if (BlockTypes.DEAD_HORN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_HORN_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadHornCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadHornCoralFan() {
        if (BlockTypes.DEAD_HORN_CORAL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_FAN = AllayBlockType
                .builder(BlockDeadHornCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadHornCoralWallFan() {
        if (BlockTypes.DEAD_HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadHornCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoral() {
        if (BlockTypes.DEAD_TUBE_CORAL != null) return;
        BlockTypes.DEAD_TUBE_CORAL = AllayBlockType
                .builder(BlockDeadTubeCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL)
                .build();
    }

    public static void initDeadTubeCoralBlock() {
        if (BlockTypes.DEAD_TUBE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_TUBE_CORAL_BLOCK = AllayBlockType
                .builder(BlockDeadTubeCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadTubeCoralFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_FAN = AllayBlockType
                .builder(BlockDeadTubeCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoralWallFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockDeadTubeCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadbush() {
        if (BlockTypes.DEADBUSH != null) return;
        BlockTypes.DEADBUSH = AllayBlockType
                .builder(BlockDeadbushBehavior.class)
                .vanillaBlock(BlockId.DEADBUSH)
                .build();
    }

    public static void initDecoratedPot() {
        if (BlockTypes.DECORATED_POT != null) return;
        BlockTypes.DECORATED_POT = AllayBlockType
                .builder(BlockDecoratedPotBehavior.class)
                .vanillaBlock(BlockId.DECORATED_POT)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initDeepslate() {
        if (BlockTypes.DEEPSLATE != null) return;
        BlockTypes.DEEPSLATE = AllayBlockType
                .builder(BlockDeepslateBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDeepslateBricks() {
        if (BlockTypes.DEEPSLATE_BRICKS != null) return;
        BlockTypes.DEEPSLATE_BRICKS = AllayBlockType
                .builder(BlockDeepslateBricksBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICKS)
                .build();
    }

    public static void initDeepslateCoalOre() {
        if (BlockTypes.DEEPSLATE_COAL_ORE != null) return;
        BlockTypes.DEEPSLATE_COAL_ORE = AllayBlockType
                .builder(BlockDeepslateCoalOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_COAL_ORE)
                .build();
    }

    public static void initDeepslateCopperOre() {
        if (BlockTypes.DEEPSLATE_COPPER_ORE != null) return;
        BlockTypes.DEEPSLATE_COPPER_ORE = AllayBlockType
                .builder(BlockDeepslateCopperOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_COPPER_ORE)
                .build();
    }

    public static void initDeepslateDiamondOre() {
        if (BlockTypes.DEEPSLATE_DIAMOND_ORE != null) return;
        BlockTypes.DEEPSLATE_DIAMOND_ORE = AllayBlockType
                .builder(BlockDeepslateDiamondOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_DIAMOND_ORE)
                .build();
    }

    public static void initDeepslateEmeraldOre() {
        if (BlockTypes.DEEPSLATE_EMERALD_ORE != null) return;
        BlockTypes.DEEPSLATE_EMERALD_ORE = AllayBlockType
                .builder(BlockDeepslateEmeraldOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_EMERALD_ORE)
                .build();
    }

    public static void initDeepslateGoldOre() {
        if (BlockTypes.DEEPSLATE_GOLD_ORE != null) return;
        BlockTypes.DEEPSLATE_GOLD_ORE = AllayBlockType
                .builder(BlockDeepslateGoldOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_GOLD_ORE)
                .build();
    }

    public static void initDeepslateIronOre() {
        if (BlockTypes.DEEPSLATE_IRON_ORE != null) return;
        BlockTypes.DEEPSLATE_IRON_ORE = AllayBlockType
                .builder(BlockDeepslateIronOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_IRON_ORE)
                .build();
    }

    public static void initDeepslateLapisOre() {
        if (BlockTypes.DEEPSLATE_LAPIS_ORE != null) return;
        BlockTypes.DEEPSLATE_LAPIS_ORE = AllayBlockType
                .builder(BlockDeepslateLapisOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_LAPIS_ORE)
                .build();
    }

    public static void initDeepslateRedstoneOre() {
        if (BlockTypes.DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.DEEPSLATE_REDSTONE_ORE = AllayBlockType
                .builder(BlockDeepslateRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initDeepslateTiles() {
        if (BlockTypes.DEEPSLATE_TILES != null) return;
        BlockTypes.DEEPSLATE_TILES = AllayBlockType
                .builder(BlockDeepslateTilesBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILES)
                .build();
    }

    public static void initDeny() {
        if (BlockTypes.DENY != null) return;
        BlockTypes.DENY = AllayBlockType
                .builder(BlockDenyBehavior.class)
                .vanillaBlock(BlockId.DENY)
                .build();
    }

    public static void initDeprecatedAnvil() {
        if (BlockTypes.DEPRECATED_ANVIL != null) return;
        BlockTypes.DEPRECATED_ANVIL = AllayBlockType
                .builder(BlockDeprecatedAnvilBehavior.class)
                .vanillaBlock(BlockId.DEPRECATED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDetectorRail() {
        if (BlockTypes.DETECTOR_RAIL != null) return;
        BlockTypes.DETECTOR_RAIL = AllayBlockType
                .builder(BlockDetectorRailBehavior.class)
                .vanillaBlock(BlockId.DETECTOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initDiamondBlock() {
        if (BlockTypes.DIAMOND_BLOCK != null) return;
        BlockTypes.DIAMOND_BLOCK = AllayBlockType
                .builder(BlockDiamondBlockBehavior.class)
                .vanillaBlock(BlockId.DIAMOND_BLOCK)
                .build();
    }

    public static void initDiamondOre() {
        if (BlockTypes.DIAMOND_ORE != null) return;
        BlockTypes.DIAMOND_ORE = AllayBlockType
                .builder(BlockDiamondOreBehavior.class)
                .vanillaBlock(BlockId.DIAMOND_ORE)
                .build();
    }

    public static void initDiorite() {
        if (BlockTypes.DIORITE != null) return;
        BlockTypes.DIORITE = AllayBlockType
                .builder(BlockDioriteBehavior.class)
                .vanillaBlock(BlockId.DIORITE)
                .build();
    }

    public static void initDirt() {
        if (BlockTypes.DIRT != null) return;
        BlockTypes.DIRT = AllayBlockType
                .builder(BlockDirtBehavior.class)
                .vanillaBlock(BlockId.DIRT)
                .build();
    }

    public static void initDirtWithRoots() {
        if (BlockTypes.DIRT_WITH_ROOTS != null) return;
        BlockTypes.DIRT_WITH_ROOTS = AllayBlockType
                .builder(BlockDirtWithRootsBehavior.class)
                .vanillaBlock(BlockId.DIRT_WITH_ROOTS)
                .build();
    }

    public static void initDispenser() {
        if (BlockTypes.DISPENSER != null) return;
        BlockTypes.DISPENSER = AllayBlockType
                .builder(BlockDispenserBehavior.class)
                .vanillaBlock(BlockId.DISPENSER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initDragonEgg() {
        if (BlockTypes.DRAGON_EGG != null) return;
        BlockTypes.DRAGON_EGG = AllayBlockType
                .builder(BlockDragonEggBehavior.class)
                .vanillaBlock(BlockId.DRAGON_EGG)
                .build();
    }

    public static void initDriedKelpBlock() {
        if (BlockTypes.DRIED_KELP_BLOCK != null) return;
        BlockTypes.DRIED_KELP_BLOCK = AllayBlockType
                .builder(BlockDriedKelpBlockBehavior.class)
                .vanillaBlock(BlockId.DRIED_KELP_BLOCK)
                .build();
    }

    public static void initDripstoneBlock() {
        if (BlockTypes.DRIPSTONE_BLOCK != null) return;
        BlockTypes.DRIPSTONE_BLOCK = AllayBlockType
                .builder(BlockDripstoneBlockBehavior.class)
                .vanillaBlock(BlockId.DRIPSTONE_BLOCK)
                .build();
    }

    public static void initDropper() {
        if (BlockTypes.DROPPER != null) return;
        BlockTypes.DROPPER = AllayBlockType
                .builder(BlockDropperBehavior.class)
                .vanillaBlock(BlockId.DROPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initEmeraldBlock() {
        if (BlockTypes.EMERALD_BLOCK != null) return;
        BlockTypes.EMERALD_BLOCK = AllayBlockType
                .builder(BlockEmeraldBlockBehavior.class)
                .vanillaBlock(BlockId.EMERALD_BLOCK)
                .build();
    }

    public static void initEmeraldOre() {
        if (BlockTypes.EMERALD_ORE != null) return;
        BlockTypes.EMERALD_ORE = AllayBlockType
                .builder(BlockEmeraldOreBehavior.class)
                .vanillaBlock(BlockId.EMERALD_ORE)
                .build();
    }

    public static void initEnchantingTable() {
        if (BlockTypes.ENCHANTING_TABLE != null) return;
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehavior.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .build();
    }

    public static void initEndBricks() {
        if (BlockTypes.END_BRICKS != null) return;
        BlockTypes.END_BRICKS = AllayBlockType
                .builder(BlockEndBricksBehavior.class)
                .vanillaBlock(BlockId.END_BRICKS)
                .build();
    }

    public static void initEndGateway() {
        if (BlockTypes.END_GATEWAY != null) return;
        BlockTypes.END_GATEWAY = AllayBlockType
                .builder(BlockEndGatewayBehavior.class)
                .vanillaBlock(BlockId.END_GATEWAY)
                .build();
    }

    public static void initEndPortal() {
        if (BlockTypes.END_PORTAL != null) return;
        BlockTypes.END_PORTAL = AllayBlockType
                .builder(BlockEndPortalBehavior.class)
                .vanillaBlock(BlockId.END_PORTAL)
                .build();
    }

    public static void initEndPortalFrame() {
        if (BlockTypes.END_PORTAL_FRAME != null) return;
        BlockTypes.END_PORTAL_FRAME = AllayBlockType
                .builder(BlockEndPortalFrameBehavior.class)
                .vanillaBlock(BlockId.END_PORTAL_FRAME)
                .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initEndRod() {
        if (BlockTypes.END_ROD != null) return;
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehavior.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initEndStone() {
        if (BlockTypes.END_STONE != null) return;
        BlockTypes.END_STONE = AllayBlockType
                .builder(BlockEndStoneBehavior.class)
                .vanillaBlock(BlockId.END_STONE)
                .build();
    }

    public static void initEnderChest() {
        if (BlockTypes.ENDER_CHEST != null) return;
        BlockTypes.ENDER_CHEST = AllayBlockType
                .builder(BlockEnderChestBehavior.class)
                .vanillaBlock(BlockId.ENDER_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initExposedChiseledCopper() {
        if (BlockTypes.EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.EXPOSED_CHISELED_COPPER = AllayBlockType
                .builder(BlockExposedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initExposedCopper() {
        if (BlockTypes.EXPOSED_COPPER != null) return;
        BlockTypes.EXPOSED_COPPER = AllayBlockType
                .builder(BlockExposedCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER)
                .build();
    }

    public static void initFarmland() {
        if (BlockTypes.FARMLAND != null) return;
        BlockTypes.FARMLAND = AllayBlockType
                .builder(BlockFarmlandBehavior.class)
                .vanillaBlock(BlockId.FARMLAND)
                .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                .build();
    }

    public static void initFenceGate() {
        if (BlockTypes.FENCE_GATE != null) return;
        BlockTypes.FENCE_GATE = AllayBlockType
                .builder(BlockFenceGateBehavior.class)
                .vanillaBlock(BlockId.FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initFern() {
        if (BlockTypes.FERN != null) return;
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehavior.class)
                .vanillaBlock(BlockId.FERN)
                .build();
    }

    public static void initFire() {
        if (BlockTypes.FIRE != null) return;
        BlockTypes.FIRE = AllayBlockType
                .builder(BlockFireBehavior.class)
                .vanillaBlock(BlockId.FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initFireCoral() {
        if (BlockTypes.FIRE_CORAL != null) return;
        BlockTypes.FIRE_CORAL = AllayBlockType
                .builder(BlockFireCoralBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL)
                .build();
    }

    public static void initFireCoralBlock() {
        if (BlockTypes.FIRE_CORAL_BLOCK != null) return;
        BlockTypes.FIRE_CORAL_BLOCK = AllayBlockType
                .builder(BlockFireCoralBlockBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initFireCoralFan() {
        if (BlockTypes.FIRE_CORAL_FAN != null) return;
        BlockTypes.FIRE_CORAL_FAN = AllayBlockType
                .builder(BlockFireCoralFanBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initFireCoralWallFan() {
        if (BlockTypes.FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.FIRE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockFireCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initFletchingTable() {
        if (BlockTypes.FLETCHING_TABLE != null) return;
        BlockTypes.FLETCHING_TABLE = AllayBlockType
                .builder(BlockFletchingTableBehavior.class)
                .vanillaBlock(BlockId.FLETCHING_TABLE)
                .build();
    }

    public static void initFlowerPot() {
        if (BlockTypes.FLOWER_POT != null) return;
        BlockTypes.FLOWER_POT = AllayBlockType
                .builder(BlockFlowerPotBehavior.class)
                .vanillaBlock(BlockId.FLOWER_POT)
                .setProperties(BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initFloweringAzalea() {
        if (BlockTypes.FLOWERING_AZALEA != null) return;
        BlockTypes.FLOWERING_AZALEA = AllayBlockType
                .builder(BlockFloweringAzaleaBehavior.class)
                .vanillaBlock(BlockId.FLOWERING_AZALEA)
                .build();
    }

    public static void initFlowingLava() {
        if (BlockTypes.FLOWING_LAVA != null) return;
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockFlowingLavaBehavior.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFlowingWater() {
        if (BlockTypes.FLOWING_WATER != null) return;
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockFlowingWaterBehavior.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFrame() {
        if (BlockTypes.FRAME != null) return;
        BlockTypes.FRAME = AllayBlockType
                .builder(BlockFrameBehavior.class)
                .vanillaBlock(BlockId.FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initFrogSpawn() {
        if (BlockTypes.FROG_SPAWN != null) return;
        BlockTypes.FROG_SPAWN = AllayBlockType
                .builder(BlockFrogSpawnBehavior.class)
                .vanillaBlock(BlockId.FROG_SPAWN)
                .build();
    }

    public static void initFrostedIce() {
        if (BlockTypes.FROSTED_ICE != null) return;
        BlockTypes.FROSTED_ICE = AllayBlockType
                .builder(BlockFrostedIceBehavior.class)
                .vanillaBlock(BlockId.FROSTED_ICE)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initFurnace() {
        if (BlockTypes.FURNACE != null) return;
        BlockTypes.FURNACE = AllayBlockType
                .builder(BlockFurnaceBehavior.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initGildedBlackstone() {
        if (BlockTypes.GILDED_BLACKSTONE != null) return;
        BlockTypes.GILDED_BLACKSTONE = AllayBlockType
                .builder(BlockGildedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                .build();
    }

    public static void initGlass() {
        if (BlockTypes.GLASS != null) return;
        BlockTypes.GLASS = AllayBlockType
                .builder(BlockGlassBehavior.class)
                .vanillaBlock(BlockId.GLASS)
                .build();
    }

    public static void initGlassPane() {
        if (BlockTypes.GLASS_PANE != null) return;
        BlockTypes.GLASS_PANE = AllayBlockType
                .builder(BlockGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GLASS_PANE)
                .build();
    }

    public static void initGlowFrame() {
        if (BlockTypes.GLOW_FRAME != null) return;
        BlockTypes.GLOW_FRAME = AllayBlockType
                .builder(BlockGlowFrameBehavior.class)
                .vanillaBlock(BlockId.GLOW_FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initGlowLichen() {
        if (BlockTypes.GLOW_LICHEN != null) return;
        BlockTypes.GLOW_LICHEN = AllayBlockType
                .builder(BlockGlowLichenBehavior.class)
                .vanillaBlock(BlockId.GLOW_LICHEN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initGlowingobsidian() {
        if (BlockTypes.GLOWINGOBSIDIAN != null) return;
        BlockTypes.GLOWINGOBSIDIAN = AllayBlockType
                .builder(BlockGlowingobsidianBehavior.class)
                .vanillaBlock(BlockId.GLOWINGOBSIDIAN)
                .build();
    }

    public static void initGlowstone() {
        if (BlockTypes.GLOWSTONE != null) return;
        BlockTypes.GLOWSTONE = AllayBlockType
                .builder(BlockGlowstoneBehavior.class)
                .vanillaBlock(BlockId.GLOWSTONE)
                .build();
    }

    public static void initGoldBlock() {
        if (BlockTypes.GOLD_BLOCK != null) return;
        BlockTypes.GOLD_BLOCK = AllayBlockType
                .builder(BlockGoldBlockBehavior.class)
                .vanillaBlock(BlockId.GOLD_BLOCK)
                .build();
    }

    public static void initGoldOre() {
        if (BlockTypes.GOLD_ORE != null) return;
        BlockTypes.GOLD_ORE = AllayBlockType
                .builder(BlockGoldOreBehavior.class)
                .vanillaBlock(BlockId.GOLD_ORE)
                .build();
    }

    public static void initGoldenRail() {
        if (BlockTypes.GOLDEN_RAIL != null) return;
        BlockTypes.GOLDEN_RAIL = AllayBlockType
                .builder(BlockGoldenRailBehavior.class)
                .vanillaBlock(BlockId.GOLDEN_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initGranite() {
        if (BlockTypes.GRANITE != null) return;
        BlockTypes.GRANITE = AllayBlockType
                .builder(BlockGraniteBehavior.class)
                .vanillaBlock(BlockId.GRANITE)
                .build();
    }

    public static void initGrassBlock() {
        if (BlockTypes.GRASS_BLOCK != null) return;
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehavior.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .build();
    }

    public static void initGrassPath() {
        if (BlockTypes.GRASS_PATH != null) return;
        BlockTypes.GRASS_PATH = AllayBlockType
                .builder(BlockGrassPathBehavior.class)
                .vanillaBlock(BlockId.GRASS_PATH)
                .build();
    }

    public static void initGravel() {
        if (BlockTypes.GRAVEL != null) return;
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehavior.class)
                .vanillaBlock(BlockId.GRAVEL)
                .build();
    }

    public static void initGrindstone() {
        if (BlockTypes.GRINDSTONE != null) return;
        BlockTypes.GRINDSTONE = AllayBlockType
                .builder(BlockGrindstoneBehavior.class)
                .vanillaBlock(BlockId.GRINDSTONE)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initHangingRoots() {
        if (BlockTypes.HANGING_ROOTS != null) return;
        BlockTypes.HANGING_ROOTS = AllayBlockType
                .builder(BlockHangingRootsBehavior.class)
                .vanillaBlock(BlockId.HANGING_ROOTS)
                .build();
    }

    public static void initHardGlass() {
        if (BlockTypes.HARD_GLASS != null) return;
        BlockTypes.HARD_GLASS = AllayBlockType
                .builder(BlockHardGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GLASS)
                .build();
    }

    public static void initHardGlassPane() {
        if (BlockTypes.HARD_GLASS_PANE != null) return;
        BlockTypes.HARD_GLASS_PANE = AllayBlockType
                .builder(BlockHardGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GLASS_PANE)
                .build();
    }

    public static void initHardenedClay() {
        if (BlockTypes.HARDENED_CLAY != null) return;
        BlockTypes.HARDENED_CLAY = AllayBlockType
                .builder(BlockHardenedClayBehavior.class)
                .vanillaBlock(BlockId.HARDENED_CLAY)
                .build();
    }

    public static void initHayBlock() {
        if (BlockTypes.HAY_BLOCK != null) return;
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehavior.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initHeavyCore() {
        if (BlockTypes.HEAVY_CORE != null) return;
        BlockTypes.HEAVY_CORE = AllayBlockType
                .builder(BlockHeavyCoreBehavior.class)
                .vanillaBlock(BlockId.HEAVY_CORE)
                .build();
    }

    public static void initHoneyBlock() {
        if (BlockTypes.HONEY_BLOCK != null) return;
        BlockTypes.HONEY_BLOCK = AllayBlockType
                .builder(BlockHoneyBlockBehavior.class)
                .vanillaBlock(BlockId.HONEY_BLOCK)
                .build();
    }

    public static void initHoneycombBlock() {
        if (BlockTypes.HONEYCOMB_BLOCK != null) return;
        BlockTypes.HONEYCOMB_BLOCK = AllayBlockType
                .builder(BlockHoneycombBlockBehavior.class)
                .vanillaBlock(BlockId.HONEYCOMB_BLOCK)
                .build();
    }

    public static void initHopper() {
        if (BlockTypes.HOPPER != null) return;
        BlockTypes.HOPPER = AllayBlockType
                .builder(BlockHopperBehavior.class)
                .vanillaBlock(BlockId.HOPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initHornCoral() {
        if (BlockTypes.HORN_CORAL != null) return;
        BlockTypes.HORN_CORAL = AllayBlockType
                .builder(BlockHornCoralBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL)
                .build();
    }

    public static void initHornCoralBlock() {
        if (BlockTypes.HORN_CORAL_BLOCK != null) return;
        BlockTypes.HORN_CORAL_BLOCK = AllayBlockType
                .builder(BlockHornCoralBlockBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_BLOCK)
                .build();
    }

    public static void initHornCoralFan() {
        if (BlockTypes.HORN_CORAL_FAN != null) return;
        BlockTypes.HORN_CORAL_FAN = AllayBlockType
                .builder(BlockHornCoralFanBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initHornCoralWallFan() {
        if (BlockTypes.HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.HORN_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockHornCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initIce() {
        if (BlockTypes.ICE != null) return;
        BlockTypes.ICE = AllayBlockType
                .builder(BlockIceBehavior.class)
                .vanillaBlock(BlockId.ICE)
                .build();
    }

    public static void initInfestedChiseledStoneBricks() {
        if (BlockTypes.INFESTED_CHISELED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CHISELED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedChiseledStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedCobblestone() {
        if (BlockTypes.INFESTED_COBBLESTONE != null) return;
        BlockTypes.INFESTED_COBBLESTONE = AllayBlockType
                .builder(BlockInfestedCobblestoneBehavior.class)
                .vanillaBlock(BlockId.INFESTED_COBBLESTONE)
                .build();
    }

    public static void initInfestedCrackedStoneBricks() {
        if (BlockTypes.INFESTED_CRACKED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CRACKED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedCrackedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedDeepslate() {
        if (BlockTypes.INFESTED_DEEPSLATE != null) return;
        BlockTypes.INFESTED_DEEPSLATE = AllayBlockType
                .builder(BlockInfestedDeepslateBehavior.class)
                .vanillaBlock(BlockId.INFESTED_DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initInfestedMossyStoneBricks() {
        if (BlockTypes.INFESTED_MOSSY_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_MOSSY_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedMossyStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initInfestedStone() {
        if (BlockTypes.INFESTED_STONE != null) return;
        BlockTypes.INFESTED_STONE = AllayBlockType
                .builder(BlockInfestedStoneBehavior.class)
                .vanillaBlock(BlockId.INFESTED_STONE)
                .build();
    }

    public static void initInfestedStoneBricks() {
        if (BlockTypes.INFESTED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_STONE_BRICKS = AllayBlockType
                .builder(BlockInfestedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_STONE_BRICKS)
                .build();
    }

    public static void initInfoUpdate() {
        if (BlockTypes.INFO_UPDATE != null) return;
        BlockTypes.INFO_UPDATE = AllayBlockType
                .builder(BlockInfoUpdateBehavior.class)
                .vanillaBlock(BlockId.INFO_UPDATE)
                .build();
    }

    public static void initInfoUpdate2() {
        if (BlockTypes.INFO_UPDATE2 != null) return;
        BlockTypes.INFO_UPDATE2 = AllayBlockType
                .builder(BlockInfoUpdate2Behavior.class)
                .vanillaBlock(BlockId.INFO_UPDATE2)
                .build();
    }

    public static void initInvisibleBedrock() {
        if (BlockTypes.INVISIBLE_BEDROCK != null) return;
        BlockTypes.INVISIBLE_BEDROCK = AllayBlockType
                .builder(BlockInvisibleBedrockBehavior.class)
                .vanillaBlock(BlockId.INVISIBLE_BEDROCK)
                .build();
    }

    public static void initIronBars() {
        if (BlockTypes.IRON_BARS != null) return;
        BlockTypes.IRON_BARS = AllayBlockType
                .builder(BlockIronBarsBehavior.class)
                .vanillaBlock(BlockId.IRON_BARS)
                .build();
    }

    public static void initIronBlock() {
        if (BlockTypes.IRON_BLOCK != null) return;
        BlockTypes.IRON_BLOCK = AllayBlockType
                .builder(BlockIronBlockBehavior.class)
                .vanillaBlock(BlockId.IRON_BLOCK)
                .build();
    }

    public static void initIronOre() {
        if (BlockTypes.IRON_ORE != null) return;
        BlockTypes.IRON_ORE = AllayBlockType
                .builder(BlockIronOreBehavior.class)
                .vanillaBlock(BlockId.IRON_ORE)
                .build();
    }

    public static void initJigsaw() {
        if (BlockTypes.JIGSAW != null) return;
        BlockTypes.JIGSAW = AllayBlockType
                .builder(BlockJigsawBehavior.class)
                .vanillaBlock(BlockId.JIGSAW)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ROTATION)
                .build();
    }

    public static void initJukebox() {
        if (BlockTypes.JUKEBOX != null) return;
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehavior.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .build();
    }

    public static void initKelp() {
        if (BlockTypes.KELP != null) return;
        BlockTypes.KELP = AllayBlockType
                .builder(BlockKelpBehavior.class)
                .vanillaBlock(BlockId.KELP)
                .setProperties(BlockPropertyTypes.KELP_AGE)
                .build();
    }

    public static void initLadder() {
        if (BlockTypes.LADDER != null) return;
        BlockTypes.LADDER = AllayBlockType
                .builder(BlockLadderBehavior.class)
                .vanillaBlock(BlockId.LADDER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLantern() {
        if (BlockTypes.LANTERN != null) return;
        BlockTypes.LANTERN = AllayBlockType
                .builder(BlockLanternBehavior.class)
                .vanillaBlock(BlockId.LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initLapisBlock() {
        if (BlockTypes.LAPIS_BLOCK != null) return;
        BlockTypes.LAPIS_BLOCK = AllayBlockType
                .builder(BlockLapisBlockBehavior.class)
                .vanillaBlock(BlockId.LAPIS_BLOCK)
                .build();
    }

    public static void initLapisOre() {
        if (BlockTypes.LAPIS_ORE != null) return;
        BlockTypes.LAPIS_ORE = AllayBlockType
                .builder(BlockLapisOreBehavior.class)
                .vanillaBlock(BlockId.LAPIS_ORE)
                .build();
    }

    public static void initLargeAmethystBud() {
        if (BlockTypes.LARGE_AMETHYST_BUD != null) return;
        BlockTypes.LARGE_AMETHYST_BUD = AllayBlockType
                .builder(BlockLargeAmethystBudBehavior.class)
                .vanillaBlock(BlockId.LARGE_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initLargeFern() {
        if (BlockTypes.LARGE_FERN != null) return;
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehavior.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLava() {
        if (BlockTypes.LAVA != null) return;
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLavaBehavior.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initLectern() {
        if (BlockTypes.LECTERN != null) return;
        BlockTypes.LECTERN = AllayBlockType
                .builder(BlockLecternBehavior.class)
                .vanillaBlock(BlockId.LECTERN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initLever() {
        if (BlockTypes.LEVER != null) return;
        BlockTypes.LEVER = AllayBlockType
                .builder(BlockLeverBehavior.class)
                .vanillaBlock(BlockId.LEVER)
                .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initLightBlock0() {
        if (BlockTypes.LIGHT_BLOCK_0 != null) return;
        BlockTypes.LIGHT_BLOCK_0 = AllayBlockType
                .builder(BlockLightBlock0Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_0)
                .build();
    }

    public static void initLightBlock1() {
        if (BlockTypes.LIGHT_BLOCK_1 != null) return;
        BlockTypes.LIGHT_BLOCK_1 = AllayBlockType
                .builder(BlockLightBlock1Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_1)
                .build();
    }

    public static void initLightBlock10() {
        if (BlockTypes.LIGHT_BLOCK_10 != null) return;
        BlockTypes.LIGHT_BLOCK_10 = AllayBlockType
                .builder(BlockLightBlock10Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_10)
                .build();
    }

    public static void initLightBlock11() {
        if (BlockTypes.LIGHT_BLOCK_11 != null) return;
        BlockTypes.LIGHT_BLOCK_11 = AllayBlockType
                .builder(BlockLightBlock11Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_11)
                .build();
    }

    public static void initLightBlock12() {
        if (BlockTypes.LIGHT_BLOCK_12 != null) return;
        BlockTypes.LIGHT_BLOCK_12 = AllayBlockType
                .builder(BlockLightBlock12Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_12)
                .build();
    }

    public static void initLightBlock13() {
        if (BlockTypes.LIGHT_BLOCK_13 != null) return;
        BlockTypes.LIGHT_BLOCK_13 = AllayBlockType
                .builder(BlockLightBlock13Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_13)
                .build();
    }

    public static void initLightBlock14() {
        if (BlockTypes.LIGHT_BLOCK_14 != null) return;
        BlockTypes.LIGHT_BLOCK_14 = AllayBlockType
                .builder(BlockLightBlock14Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_14)
                .build();
    }

    public static void initLightBlock15() {
        if (BlockTypes.LIGHT_BLOCK_15 != null) return;
        BlockTypes.LIGHT_BLOCK_15 = AllayBlockType
                .builder(BlockLightBlock15Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_15)
                .build();
    }

    public static void initLightBlock2() {
        if (BlockTypes.LIGHT_BLOCK_2 != null) return;
        BlockTypes.LIGHT_BLOCK_2 = AllayBlockType
                .builder(BlockLightBlock2Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_2)
                .build();
    }

    public static void initLightBlock3() {
        if (BlockTypes.LIGHT_BLOCK_3 != null) return;
        BlockTypes.LIGHT_BLOCK_3 = AllayBlockType
                .builder(BlockLightBlock3Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_3)
                .build();
    }

    public static void initLightBlock4() {
        if (BlockTypes.LIGHT_BLOCK_4 != null) return;
        BlockTypes.LIGHT_BLOCK_4 = AllayBlockType
                .builder(BlockLightBlock4Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_4)
                .build();
    }

    public static void initLightBlock5() {
        if (BlockTypes.LIGHT_BLOCK_5 != null) return;
        BlockTypes.LIGHT_BLOCK_5 = AllayBlockType
                .builder(BlockLightBlock5Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_5)
                .build();
    }

    public static void initLightBlock6() {
        if (BlockTypes.LIGHT_BLOCK_6 != null) return;
        BlockTypes.LIGHT_BLOCK_6 = AllayBlockType
                .builder(BlockLightBlock6Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_6)
                .build();
    }

    public static void initLightBlock7() {
        if (BlockTypes.LIGHT_BLOCK_7 != null) return;
        BlockTypes.LIGHT_BLOCK_7 = AllayBlockType
                .builder(BlockLightBlock7Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_7)
                .build();
    }

    public static void initLightBlock8() {
        if (BlockTypes.LIGHT_BLOCK_8 != null) return;
        BlockTypes.LIGHT_BLOCK_8 = AllayBlockType
                .builder(BlockLightBlock8Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_8)
                .build();
    }

    public static void initLightBlock9() {
        if (BlockTypes.LIGHT_BLOCK_9 != null) return;
        BlockTypes.LIGHT_BLOCK_9 = AllayBlockType
                .builder(BlockLightBlock9Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_9)
                .build();
    }

    public static void initLightningRod() {
        if (BlockTypes.LIGHTNING_ROD != null) return;
        BlockTypes.LIGHTNING_ROD = AllayBlockType
                .builder(BlockLightningRodBehavior.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLilac() {
        if (BlockTypes.LILAC != null) return;
        BlockTypes.LILAC = AllayBlockType
                .builder(BlockLilacBehavior.class)
                .vanillaBlock(BlockId.LILAC)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLilyOfTheValley() {
        if (BlockTypes.LILY_OF_THE_VALLEY != null) return;
        BlockTypes.LILY_OF_THE_VALLEY = AllayBlockType
                .builder(BlockLilyOfTheValleyBehavior.class)
                .vanillaBlock(BlockId.LILY_OF_THE_VALLEY)
                .build();
    }

    public static void initLitBlastFurnace() {
        if (BlockTypes.LIT_BLAST_FURNACE != null) return;
        BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                .builder(BlockLitBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitDeepslateRedstoneOre() {
        if (BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = AllayBlockType
                .builder(BlockLitDeepslateRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.LIT_DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initLitFurnace() {
        if (BlockTypes.LIT_FURNACE != null) return;
        BlockTypes.LIT_FURNACE = AllayBlockType
                .builder(BlockLitFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitPumpkin() {
        if (BlockTypes.LIT_PUMPKIN != null) return;
        BlockTypes.LIT_PUMPKIN = AllayBlockType
                .builder(BlockLitPumpkinBehavior.class)
                .vanillaBlock(BlockId.LIT_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitRedstoneLamp() {
        if (BlockTypes.LIT_REDSTONE_LAMP != null) return;
        BlockTypes.LIT_REDSTONE_LAMP = AllayBlockType
                .builder(BlockLitRedstoneLampBehavior.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_LAMP)
                .build();
    }

    public static void initLitRedstoneOre() {
        if (BlockTypes.LIT_REDSTONE_ORE != null) return;
        BlockTypes.LIT_REDSTONE_ORE = AllayBlockType
                .builder(BlockLitRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_ORE)
                .build();
    }

    public static void initLitSmoker() {
        if (BlockTypes.LIT_SMOKER != null) return;
        BlockTypes.LIT_SMOKER = AllayBlockType
                .builder(BlockLitSmokerBehavior.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLodestone() {
        if (BlockTypes.LODESTONE != null) return;
        BlockTypes.LODESTONE = AllayBlockType
                .builder(BlockLodestoneBehavior.class)
                .vanillaBlock(BlockId.LODESTONE)
                .build();
    }

    public static void initLoom() {
        if (BlockTypes.LOOM != null) return;
        BlockTypes.LOOM = AllayBlockType
                .builder(BlockLoomBehavior.class)
                .vanillaBlock(BlockId.LOOM)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initMagma() {
        if (BlockTypes.MAGMA != null) return;
        BlockTypes.MAGMA = AllayBlockType
                .builder(BlockMagmaBehavior.class)
                .vanillaBlock(BlockId.MAGMA)
                .build();
    }

    public static void initMangrovePropagule() {
        if (BlockTypes.MANGROVE_PROPAGULE != null) return;
        BlockTypes.MANGROVE_PROPAGULE = AllayBlockType
                .builder(BlockMangrovePropaguleBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                .build();
    }

    public static void initMangroveRoots() {
        if (BlockTypes.MANGROVE_ROOTS != null) return;
        BlockTypes.MANGROVE_ROOTS = AllayBlockType
                .builder(BlockMangroveRootsBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_ROOTS)
                .build();
    }

    public static void initMediumAmethystBud() {
        if (BlockTypes.MEDIUM_AMETHYST_BUD != null) return;
        BlockTypes.MEDIUM_AMETHYST_BUD = AllayBlockType
                .builder(BlockMediumAmethystBudBehavior.class)
                .vanillaBlock(BlockId.MEDIUM_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initMelonBlock() {
        if (BlockTypes.MELON_BLOCK != null) return;
        BlockTypes.MELON_BLOCK = AllayBlockType
                .builder(BlockMelonBlockBehavior.class)
                .vanillaBlock(BlockId.MELON_BLOCK)
                .build();
    }

    public static void initMelonStem() {
        if (BlockTypes.MELON_STEM != null) return;
        BlockTypes.MELON_STEM = AllayBlockType
                .builder(BlockMelonStemBehavior.class)
                .vanillaBlock(BlockId.MELON_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initMobSpawner() {
        if (BlockTypes.MOB_SPAWNER != null) return;
        BlockTypes.MOB_SPAWNER = AllayBlockType
                .builder(BlockMobSpawnerBehavior.class)
                .vanillaBlock(BlockId.MOB_SPAWNER)
                .build();
    }

    public static void initMossBlock() {
        if (BlockTypes.MOSS_BLOCK != null) return;
        BlockTypes.MOSS_BLOCK = AllayBlockType
                .builder(BlockMossBlockBehavior.class)
                .vanillaBlock(BlockId.MOSS_BLOCK)
                .build();
    }

    public static void initMossyCobblestone() {
        if (BlockTypes.MOSSY_COBBLESTONE != null) return;
        BlockTypes.MOSSY_COBBLESTONE = AllayBlockType
                .builder(BlockMossyCobblestoneBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE)
                .build();
    }

    public static void initMossyStoneBricks() {
        if (BlockTypes.MOSSY_STONE_BRICKS != null) return;
        BlockTypes.MOSSY_STONE_BRICKS = AllayBlockType
                .builder(BlockMossyStoneBricksBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initMovingBlock() {
        if (BlockTypes.MOVING_BLOCK != null) return;
        BlockTypes.MOVING_BLOCK = AllayBlockType
                .builder(BlockMovingBlockBehavior.class)
                .vanillaBlock(BlockId.MOVING_BLOCK)
                .build();
    }

    public static void initMud() {
        if (BlockTypes.MUD != null) return;
        BlockTypes.MUD = AllayBlockType
                .builder(BlockMudBehavior.class)
                .vanillaBlock(BlockId.MUD)
                .build();
    }

    public static void initMudBricks() {
        if (BlockTypes.MUD_BRICKS != null) return;
        BlockTypes.MUD_BRICKS = AllayBlockType
                .builder(BlockMudBricksBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICKS)
                .build();
    }

    public static void initMuddyMangroveRoots() {
        if (BlockTypes.MUDDY_MANGROVE_ROOTS != null) return;
        BlockTypes.MUDDY_MANGROVE_ROOTS = AllayBlockType
                .builder(BlockMuddyMangroveRootsBehavior.class)
                .vanillaBlock(BlockId.MUDDY_MANGROVE_ROOTS)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMycelium() {
        if (BlockTypes.MYCELIUM != null) return;
        BlockTypes.MYCELIUM = AllayBlockType
                .builder(BlockMyceliumBehavior.class)
                .vanillaBlock(BlockId.MYCELIUM)
                .build();
    }

    public static void initNetherBrick() {
        if (BlockTypes.NETHER_BRICK != null) return;
        BlockTypes.NETHER_BRICK = AllayBlockType
                .builder(BlockNetherBrickBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK)
                .build();
    }

    public static void initNetherGoldOre() {
        if (BlockTypes.NETHER_GOLD_ORE != null) return;
        BlockTypes.NETHER_GOLD_ORE = AllayBlockType
                .builder(BlockNetherGoldOreBehavior.class)
                .vanillaBlock(BlockId.NETHER_GOLD_ORE)
                .build();
    }

    public static void initNetherSprouts() {
        if (BlockTypes.NETHER_SPROUTS != null) return;
        BlockTypes.NETHER_SPROUTS = AllayBlockType
                .builder(BlockNetherSproutsBehavior.class)
                .vanillaBlock(BlockId.NETHER_SPROUTS)
                .build();
    }

    public static void initNetherWart() {
        if (BlockTypes.NETHER_WART != null) return;
        BlockTypes.NETHER_WART = AllayBlockType
                .builder(BlockNetherWartBehavior.class)
                .vanillaBlock(BlockId.NETHER_WART)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initNetherWartBlock() {
        if (BlockTypes.NETHER_WART_BLOCK != null) return;
        BlockTypes.NETHER_WART_BLOCK = AllayBlockType
                .builder(BlockNetherWartBlockBehavior.class)
                .vanillaBlock(BlockId.NETHER_WART_BLOCK)
                .build();
    }

    public static void initNetheriteBlock() {
        if (BlockTypes.NETHERITE_BLOCK != null) return;
        BlockTypes.NETHERITE_BLOCK = AllayBlockType
                .builder(BlockNetheriteBlockBehavior.class)
                .vanillaBlock(BlockId.NETHERITE_BLOCK)
                .build();
    }

    public static void initNetherrack() {
        if (BlockTypes.NETHERRACK != null) return;
        BlockTypes.NETHERRACK = AllayBlockType
                .builder(BlockNetherrackBehavior.class)
                .vanillaBlock(BlockId.NETHERRACK)
                .build();
    }

    public static void initNetherreactor() {
        if (BlockTypes.NETHERREACTOR != null) return;
        BlockTypes.NETHERREACTOR = AllayBlockType
                .builder(BlockNetherreactorBehavior.class)
                .vanillaBlock(BlockId.NETHERREACTOR)
                .build();
    }

    public static void initNoteblock() {
        if (BlockTypes.NOTEBLOCK != null) return;
        BlockTypes.NOTEBLOCK = AllayBlockType
                .builder(BlockNoteblockBehavior.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .build();
    }

    public static void initObserver() {
        if (BlockTypes.OBSERVER != null) return;
        BlockTypes.OBSERVER = AllayBlockType
                .builder(BlockObserverBehavior.class)
                .vanillaBlock(BlockId.OBSERVER)
                .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initObsidian() {
        if (BlockTypes.OBSIDIAN != null) return;
        BlockTypes.OBSIDIAN = AllayBlockType
                .builder(BlockObsidianBehavior.class)
                .vanillaBlock(BlockId.OBSIDIAN)
                .build();
    }

    public static void initOchreFroglight() {
        if (BlockTypes.OCHRE_FROGLIGHT != null) return;
        BlockTypes.OCHRE_FROGLIGHT = AllayBlockType
                .builder(BlockOchreFroglightBehavior.class)
                .vanillaBlock(BlockId.OCHRE_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOxeyeDaisy() {
        if (BlockTypes.OXEYE_DAISY != null) return;
        BlockTypes.OXEYE_DAISY = AllayBlockType
                .builder(BlockOxeyeDaisyBehavior.class)
                .vanillaBlock(BlockId.OXEYE_DAISY)
                .build();
    }

    public static void initOxidizedChiseledCopper() {
        if (BlockTypes.OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.OXIDIZED_CHISELED_COPPER = AllayBlockType
                .builder(BlockOxidizedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initOxidizedCopper() {
        if (BlockTypes.OXIDIZED_COPPER != null) return;
        BlockTypes.OXIDIZED_COPPER = AllayBlockType
                .builder(BlockOxidizedCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER)
                .build();
    }

    public static void initPackedIce() {
        if (BlockTypes.PACKED_ICE != null) return;
        BlockTypes.PACKED_ICE = AllayBlockType
                .builder(BlockPackedIceBehavior.class)
                .vanillaBlock(BlockId.PACKED_ICE)
                .build();
    }

    public static void initPackedMud() {
        if (BlockTypes.PACKED_MUD != null) return;
        BlockTypes.PACKED_MUD = AllayBlockType
                .builder(BlockPackedMudBehavior.class)
                .vanillaBlock(BlockId.PACKED_MUD)
                .build();
    }

    public static void initPearlescentFroglight() {
        if (BlockTypes.PEARLESCENT_FROGLIGHT != null) return;
        BlockTypes.PEARLESCENT_FROGLIGHT = AllayBlockType
                .builder(BlockPearlescentFroglightBehavior.class)
                .vanillaBlock(BlockId.PEARLESCENT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPeony() {
        if (BlockTypes.PEONY != null) return;
        BlockTypes.PEONY = AllayBlockType
                .builder(BlockPeonyBehavior.class)
                .vanillaBlock(BlockId.PEONY)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPinkPetals() {
        if (BlockTypes.PINK_PETALS != null) return;
        BlockTypes.PINK_PETALS = AllayBlockType
                .builder(BlockPinkPetalsBehavior.class)
                .vanillaBlock(BlockId.PINK_PETALS)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPiston() {
        if (BlockTypes.PISTON != null) return;
        BlockTypes.PISTON = AllayBlockType
                .builder(BlockPistonBehavior.class)
                .vanillaBlock(BlockId.PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPistonArmCollision() {
        if (BlockTypes.PISTON_ARM_COLLISION != null) return;
        BlockTypes.PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockPistonArmCollisionBehavior.class)
                .vanillaBlock(BlockId.PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPitcherCrop() {
        if (BlockTypes.PITCHER_CROP != null) return;
        BlockTypes.PITCHER_CROP = AllayBlockType
                .builder(BlockPitcherCropBehavior.class)
                .vanillaBlock(BlockId.PITCHER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPitcherPlant() {
        if (BlockTypes.PITCHER_PLANT != null) return;
        BlockTypes.PITCHER_PLANT = AllayBlockType
                .builder(BlockPitcherPlantBehavior.class)
                .vanillaBlock(BlockId.PITCHER_PLANT)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPodzol() {
        if (BlockTypes.PODZOL != null) return;
        BlockTypes.PODZOL = AllayBlockType
                .builder(BlockPodzolBehavior.class)
                .vanillaBlock(BlockId.PODZOL)
                .build();
    }

    public static void initPointedDripstone() {
        if (BlockTypes.POINTED_DRIPSTONE != null) return;
        BlockTypes.POINTED_DRIPSTONE = AllayBlockType
                .builder(BlockPointedDripstoneBehavior.class)
                .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initPolishedAndesite() {
        if (BlockTypes.POLISHED_ANDESITE != null) return;
        BlockTypes.POLISHED_ANDESITE = AllayBlockType
                .builder(BlockPolishedAndesiteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE)
                .build();
    }

    public static void initPolishedBasalt() {
        if (BlockTypes.POLISHED_BASALT != null) return;
        BlockTypes.POLISHED_BASALT = AllayBlockType
                .builder(BlockPolishedBasaltBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPolishedBlackstone() {
        if (BlockTypes.POLISHED_BLACKSTONE != null) return;
        BlockTypes.POLISHED_BLACKSTONE = AllayBlockType
                .builder(BlockPolishedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE)
                .build();
    }

    public static void initPolishedBlackstoneBricks() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                .builder(BlockPolishedBlackstoneBricksBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initPolishedDeepslate() {
        if (BlockTypes.POLISHED_DEEPSLATE != null) return;
        BlockTypes.POLISHED_DEEPSLATE = AllayBlockType
                .builder(BlockPolishedDeepslateBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE)
                .build();
    }

    public static void initPolishedDiorite() {
        if (BlockTypes.POLISHED_DIORITE != null) return;
        BlockTypes.POLISHED_DIORITE = AllayBlockType
                .builder(BlockPolishedDioriteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE)
                .build();
    }

    public static void initPolishedGranite() {
        if (BlockTypes.POLISHED_GRANITE != null) return;
        BlockTypes.POLISHED_GRANITE = AllayBlockType
                .builder(BlockPolishedGraniteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE)
                .build();
    }

    public static void initPolishedTuff() {
        if (BlockTypes.POLISHED_TUFF != null) return;
        BlockTypes.POLISHED_TUFF = AllayBlockType
                .builder(BlockPolishedTuffBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF)
                .build();
    }

    public static void initPoppy() {
        if (BlockTypes.POPPY != null) return;
        BlockTypes.POPPY = AllayBlockType
                .builder(BlockPoppyBehavior.class)
                .vanillaBlock(BlockId.POPPY)
                .build();
    }

    public static void initPortal() {
        if (BlockTypes.PORTAL != null) return;
        BlockTypes.PORTAL = AllayBlockType
                .builder(BlockPortalBehavior.class)
                .vanillaBlock(BlockId.PORTAL)
                .setProperties(BlockPropertyTypes.PORTAL_AXIS)
                .build();
    }

    public static void initPotatoes() {
        if (BlockTypes.POTATOES != null) return;
        BlockTypes.POTATOES = AllayBlockType
                .builder(BlockPotatoesBehavior.class)
                .vanillaBlock(BlockId.POTATOES)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPowderSnow() {
        if (BlockTypes.POWDER_SNOW != null) return;
        BlockTypes.POWDER_SNOW = AllayBlockType
                .builder(BlockPowderSnowBehavior.class)
                .vanillaBlock(BlockId.POWDER_SNOW)
                .build();
    }

    public static void initPoweredComparator() {
        if (BlockTypes.POWERED_COMPARATOR != null) return;
        BlockTypes.POWERED_COMPARATOR = AllayBlockType
                .builder(BlockPoweredComparatorBehavior.class)
                .vanillaBlock(BlockId.POWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initPoweredRepeater() {
        if (BlockTypes.POWERED_REPEATER != null) return;
        BlockTypes.POWERED_REPEATER = AllayBlockType
                .builder(BlockPoweredRepeaterBehavior.class)
                .vanillaBlock(BlockId.POWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initPrismarine() {
        if (BlockTypes.PRISMARINE != null) return;
        BlockTypes.PRISMARINE = AllayBlockType
                .builder(BlockPrismarineBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE)
                .build();
    }

    public static void initPrismarineBricks() {
        if (BlockTypes.PRISMARINE_BRICKS != null) return;
        BlockTypes.PRISMARINE_BRICKS = AllayBlockType
                .builder(BlockPrismarineBricksBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS)
                .build();
    }

    public static void initPumpkin() {
        if (BlockTypes.PUMPKIN != null) return;
        BlockTypes.PUMPKIN = AllayBlockType
                .builder(BlockPumpkinBehavior.class)
                .vanillaBlock(BlockId.PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPumpkinStem() {
        if (BlockTypes.PUMPKIN_STEM != null) return;
        BlockTypes.PUMPKIN_STEM = AllayBlockType
                .builder(BlockPumpkinStemBehavior.class)
                .vanillaBlock(BlockId.PUMPKIN_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPurpurBlock() {
        if (BlockTypes.PURPUR_BLOCK != null) return;
        BlockTypes.PURPUR_BLOCK = AllayBlockType
                .builder(BlockPurpurBlockBehavior.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.CHISEL_TYPE, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzBlock() {
        if (BlockTypes.QUARTZ_BLOCK != null) return;
        BlockTypes.QUARTZ_BLOCK = AllayBlockType
                .builder(BlockQuartzBlockBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzBricks() {
        if (BlockTypes.QUARTZ_BRICKS != null) return;
        BlockTypes.QUARTZ_BRICKS = AllayBlockType
                .builder(BlockQuartzBricksBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_BRICKS)
                .build();
    }

    public static void initQuartzOre() {
        if (BlockTypes.QUARTZ_ORE != null) return;
        BlockTypes.QUARTZ_ORE = AllayBlockType
                .builder(BlockQuartzOreBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_ORE)
                .build();
    }

    public static void initQuartzPillar() {
        if (BlockTypes.QUARTZ_PILLAR != null) return;
        BlockTypes.QUARTZ_PILLAR = AllayBlockType
                .builder(BlockQuartzPillarBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_PILLAR)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initRail() {
        if (BlockTypes.RAIL != null) return;
        BlockTypes.RAIL = AllayBlockType
                .builder(BlockRailBehavior.class)
                .vanillaBlock(BlockId.RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                .build();
    }

    public static void initRawCopperBlock() {
        if (BlockTypes.RAW_COPPER_BLOCK != null) return;
        BlockTypes.RAW_COPPER_BLOCK = AllayBlockType
                .builder(BlockRawCopperBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_COPPER_BLOCK)
                .build();
    }

    public static void initRawGoldBlock() {
        if (BlockTypes.RAW_GOLD_BLOCK != null) return;
        BlockTypes.RAW_GOLD_BLOCK = AllayBlockType
                .builder(BlockRawGoldBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_GOLD_BLOCK)
                .build();
    }

    public static void initRawIronBlock() {
        if (BlockTypes.RAW_IRON_BLOCK != null) return;
        BlockTypes.RAW_IRON_BLOCK = AllayBlockType
                .builder(BlockRawIronBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_IRON_BLOCK)
                .build();
    }

    public static void initRedMushroom() {
        if (BlockTypes.RED_MUSHROOM != null) return;
        BlockTypes.RED_MUSHROOM = AllayBlockType
                .builder(BlockRedMushroomBehavior.class)
                .vanillaBlock(BlockId.RED_MUSHROOM)
                .build();
    }

    public static void initRedMushroomBlock() {
        if (BlockTypes.RED_MUSHROOM_BLOCK != null) return;
        BlockTypes.RED_MUSHROOM_BLOCK = AllayBlockType
                .builder(BlockRedMushroomBlockBehavior.class)
                .vanillaBlock(BlockId.RED_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initRedNetherBrick() {
        if (BlockTypes.RED_NETHER_BRICK != null) return;
        BlockTypes.RED_NETHER_BRICK = AllayBlockType
                .builder(BlockRedNetherBrickBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK)
                .build();
    }

    public static void initRedSand() {
        if (BlockTypes.RED_SAND != null) return;
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehavior.class)
                .vanillaBlock(BlockId.RED_SAND)
                .build();
    }

    public static void initRedSandstone() {
        if (BlockTypes.RED_SANDSTONE != null) return;
        BlockTypes.RED_SANDSTONE = AllayBlockType
                .builder(BlockRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE)
                .build();
    }

    public static void initRedstoneBlock() {
        if (BlockTypes.REDSTONE_BLOCK != null) return;
        BlockTypes.REDSTONE_BLOCK = AllayBlockType
                .builder(BlockRedstoneBlockBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_BLOCK)
                .build();
    }

    public static void initRedstoneLamp() {
        if (BlockTypes.REDSTONE_LAMP != null) return;
        BlockTypes.REDSTONE_LAMP = AllayBlockType
                .builder(BlockRedstoneLampBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_LAMP)
                .build();
    }

    public static void initRedstoneOre() {
        if (BlockTypes.REDSTONE_ORE != null) return;
        BlockTypes.REDSTONE_ORE = AllayBlockType
                .builder(BlockRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_ORE)
                .build();
    }

    public static void initRedstoneTorch() {
        if (BlockTypes.REDSTONE_TORCH != null) return;
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initRedstoneWire() {
        if (BlockTypes.REDSTONE_WIRE != null) return;
        BlockTypes.REDSTONE_WIRE = AllayBlockType
                .builder(BlockRedstoneWireBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_WIRE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initReeds() {
        if (BlockTypes.REEDS != null) return;
        BlockTypes.REEDS = AllayBlockType
                .builder(BlockReedsBehavior.class)
                .vanillaBlock(BlockId.REEDS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initReinforcedDeepslate() {
        if (BlockTypes.REINFORCED_DEEPSLATE != null) return;
        BlockTypes.REINFORCED_DEEPSLATE = AllayBlockType
                .builder(BlockReinforcedDeepslateBehavior.class)
                .vanillaBlock(BlockId.REINFORCED_DEEPSLATE)
                .build();
    }

    public static void initRepeatingCommandBlock() {
        if (BlockTypes.REPEATING_COMMAND_BLOCK != null) return;
        BlockTypes.REPEATING_COMMAND_BLOCK = AllayBlockType
                .builder(BlockRepeatingCommandBlockBehavior.class)
                .vanillaBlock(BlockId.REPEATING_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initReserved6() {
        if (BlockTypes.RESERVED6 != null) return;
        BlockTypes.RESERVED6 = AllayBlockType
                .builder(BlockReserved6Behavior.class)
                .vanillaBlock(BlockId.RESERVED6)
                .build();
    }

    public static void initRespawnAnchor() {
        if (BlockTypes.RESPAWN_ANCHOR != null) return;
        BlockTypes.RESPAWN_ANCHOR = AllayBlockType
                .builder(BlockRespawnAnchorBehavior.class)
                .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                .build();
    }

    public static void initRoseBush() {
        if (BlockTypes.ROSE_BUSH != null) return;
        BlockTypes.ROSE_BUSH = AllayBlockType
                .builder(BlockRoseBushBehavior.class)
                .vanillaBlock(BlockId.ROSE_BUSH)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSand() {
        if (BlockTypes.SAND != null) return;
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehavior.class)
                .vanillaBlock(BlockId.SAND)
                .build();
    }

    public static void initSandstone() {
        if (BlockTypes.SANDSTONE != null) return;
        BlockTypes.SANDSTONE = AllayBlockType
                .builder(BlockSandstoneBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE)
                .build();
    }

    public static void initScaffolding() {
        if (BlockTypes.SCAFFOLDING != null) return;
        BlockTypes.SCAFFOLDING = AllayBlockType
                .builder(BlockScaffoldingBehavior.class)
                .vanillaBlock(BlockId.SCAFFOLDING)
                .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                .build();
    }

    public static void initSculk() {
        if (BlockTypes.SCULK != null) return;
        BlockTypes.SCULK = AllayBlockType
                .builder(BlockSculkBehavior.class)
                .vanillaBlock(BlockId.SCULK)
                .build();
    }

    public static void initSculkCatalyst() {
        if (BlockTypes.SCULK_CATALYST != null) return;
        BlockTypes.SCULK_CATALYST = AllayBlockType
                .builder(BlockSculkCatalystBehavior.class)
                .vanillaBlock(BlockId.SCULK_CATALYST)
                .setProperties(BlockPropertyTypes.BLOOM)
                .build();
    }

    public static void initSculkSensor() {
        if (BlockTypes.SCULK_SENSOR != null) return;
        BlockTypes.SCULK_SENSOR = AllayBlockType
                .builder(BlockSculkSensorBehavior.class)
                .vanillaBlock(BlockId.SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initSculkShrieker() {
        if (BlockTypes.SCULK_SHRIEKER != null) return;
        BlockTypes.SCULK_SHRIEKER = AllayBlockType
                .builder(BlockSculkShriekerBehavior.class)
                .vanillaBlock(BlockId.SCULK_SHRIEKER)
                .setProperties(BlockPropertyTypes.ACTIVE, BlockPropertyTypes.CAN_SUMMON)
                .build();
    }

    public static void initSculkVein() {
        if (BlockTypes.SCULK_VEIN != null) return;
        BlockTypes.SCULK_VEIN = AllayBlockType
                .builder(BlockSculkVeinBehavior.class)
                .vanillaBlock(BlockId.SCULK_VEIN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initSeaLantern() {
        if (BlockTypes.SEA_LANTERN != null) return;
        BlockTypes.SEA_LANTERN = AllayBlockType
                .builder(BlockSeaLanternBehavior.class)
                .vanillaBlock(BlockId.SEA_LANTERN)
                .build();
    }

    public static void initSeaPickle() {
        if (BlockTypes.SEA_PICKLE != null) return;
        BlockTypes.SEA_PICKLE = AllayBlockType
                .builder(BlockSeaPickleBehavior.class)
                .vanillaBlock(BlockId.SEA_PICKLE)
                .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                .build();
    }

    public static void initSeagrass() {
        if (BlockTypes.SEAGRASS != null) return;
        BlockTypes.SEAGRASS = AllayBlockType
                .builder(BlockSeagrassBehavior.class)
                .vanillaBlock(BlockId.SEAGRASS)
                .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                .build();
    }

    public static void initShortGrass() {
        if (BlockTypes.SHORT_GRASS != null) return;
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehavior.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .build();
    }

    public static void initShroomlight() {
        if (BlockTypes.SHROOMLIGHT != null) return;
        BlockTypes.SHROOMLIGHT = AllayBlockType
                .builder(BlockShroomlightBehavior.class)
                .vanillaBlock(BlockId.SHROOMLIGHT)
                .build();
    }

    public static void initSkull() {
        if (BlockTypes.SKULL != null) return;
        BlockTypes.SKULL = AllayBlockType
                .builder(BlockSkullBehavior.class)
                .vanillaBlock(BlockId.SKULL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSlime() {
        if (BlockTypes.SLIME != null) return;
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehavior.class)
                .vanillaBlock(BlockId.SLIME)
                .build();
    }

    public static void initSmallAmethystBud() {
        if (BlockTypes.SMALL_AMETHYST_BUD != null) return;
        BlockTypes.SMALL_AMETHYST_BUD = AllayBlockType
                .builder(BlockSmallAmethystBudBehavior.class)
                .vanillaBlock(BlockId.SMALL_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initSmallDripleafBlock() {
        if (BlockTypes.SMALL_DRIPLEAF_BLOCK != null) return;
        BlockTypes.SMALL_DRIPLEAF_BLOCK = AllayBlockType
                .builder(BlockSmallDripleafBlockBehavior.class)
                .vanillaBlock(BlockId.SMALL_DRIPLEAF_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSmithingTable() {
        if (BlockTypes.SMITHING_TABLE != null) return;
        BlockTypes.SMITHING_TABLE = AllayBlockType
                .builder(BlockSmithingTableBehavior.class)
                .vanillaBlock(BlockId.SMITHING_TABLE)
                .build();
    }

    public static void initSmoker() {
        if (BlockTypes.SMOKER != null) return;
        BlockTypes.SMOKER = AllayBlockType
                .builder(BlockSmokerBehavior.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSmoothBasalt() {
        if (BlockTypes.SMOOTH_BASALT != null) return;
        BlockTypes.SMOOTH_BASALT = AllayBlockType
                .builder(BlockSmoothBasaltBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_BASALT)
                .build();
    }

    public static void initSmoothQuartz() {
        if (BlockTypes.SMOOTH_QUARTZ != null) return;
        BlockTypes.SMOOTH_QUARTZ = AllayBlockType
                .builder(BlockSmoothQuartzBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSmoothRedSandstone() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE = AllayBlockType
                .builder(BlockSmoothRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE)
                .build();
    }

    public static void initSmoothSandstone() {
        if (BlockTypes.SMOOTH_SANDSTONE != null) return;
        BlockTypes.SMOOTH_SANDSTONE = AllayBlockType
                .builder(BlockSmoothSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE)
                .build();
    }

    public static void initSmoothStone() {
        if (BlockTypes.SMOOTH_STONE != null) return;
        BlockTypes.SMOOTH_STONE = AllayBlockType
                .builder(BlockSmoothStoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE)
                .build();
    }

    public static void initSnifferEgg() {
        if (BlockTypes.SNIFFER_EGG != null) return;
        BlockTypes.SNIFFER_EGG = AllayBlockType
                .builder(BlockSnifferEggBehavior.class)
                .vanillaBlock(BlockId.SNIFFER_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE)
                .build();
    }

    public static void initSnow() {
        if (BlockTypes.SNOW != null) return;
        BlockTypes.SNOW = AllayBlockType
                .builder(BlockSnowBehavior.class)
                .vanillaBlock(BlockId.SNOW)
                .build();
    }

    public static void initSnowLayer() {
        if (BlockTypes.SNOW_LAYER != null) return;
        BlockTypes.SNOW_LAYER = AllayBlockType
                .builder(BlockSnowLayerBehavior.class)
                .vanillaBlock(BlockId.SNOW_LAYER)
                .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                .build();
    }

    public static void initSoulCampfire() {
        if (BlockTypes.SOUL_CAMPFIRE != null) return;
        BlockTypes.SOUL_CAMPFIRE = AllayBlockType
                .builder(BlockSoulCampfireBehavior.class)
                .vanillaBlock(BlockId.SOUL_CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSoulFire() {
        if (BlockTypes.SOUL_FIRE != null) return;
        BlockTypes.SOUL_FIRE = AllayBlockType
                .builder(BlockSoulFireBehavior.class)
                .vanillaBlock(BlockId.SOUL_FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initSoulLantern() {
        if (BlockTypes.SOUL_LANTERN != null) return;
        BlockTypes.SOUL_LANTERN = AllayBlockType
                .builder(BlockSoulLanternBehavior.class)
                .vanillaBlock(BlockId.SOUL_LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSoulSand() {
        if (BlockTypes.SOUL_SAND != null) return;
        BlockTypes.SOUL_SAND = AllayBlockType
                .builder(BlockSoulSandBehavior.class)
                .vanillaBlock(BlockId.SOUL_SAND)
                .build();
    }

    public static void initSoulSoil() {
        if (BlockTypes.SOUL_SOIL != null) return;
        BlockTypes.SOUL_SOIL = AllayBlockType
                .builder(BlockSoulSoilBehavior.class)
                .vanillaBlock(BlockId.SOUL_SOIL)
                .build();
    }

    public static void initSoulTorch() {
        if (BlockTypes.SOUL_TORCH != null) return;
        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockSoulTorchBehavior.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initSponge() {
        if (BlockTypes.SPONGE != null) return;
        BlockTypes.SPONGE = AllayBlockType
                .builder(BlockSpongeBehavior.class)
                .vanillaBlock(BlockId.SPONGE)
                .setProperties(BlockPropertyTypes.SPONGE_TYPE)
                .build();
    }

    public static void initSporeBlossom() {
        if (BlockTypes.SPORE_BLOSSOM != null) return;
        BlockTypes.SPORE_BLOSSOM = AllayBlockType
                .builder(BlockSporeBlossomBehavior.class)
                .vanillaBlock(BlockId.SPORE_BLOSSOM)
                .build();
    }

    public static void initStandingBanner() {
        if (BlockTypes.STANDING_BANNER != null) return;
        BlockTypes.STANDING_BANNER = AllayBlockType
                .builder(BlockStandingBannerBehavior.class)
                .vanillaBlock(BlockId.STANDING_BANNER)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initStickyPiston() {
        if (BlockTypes.STICKY_PISTON != null) return;
        BlockTypes.STICKY_PISTON = AllayBlockType
                .builder(BlockStickyPistonBehavior.class)
                .vanillaBlock(BlockId.STICKY_PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStickyPistonArmCollision() {
        if (BlockTypes.STICKY_PISTON_ARM_COLLISION != null) return;
        BlockTypes.STICKY_PISTON_ARM_COLLISION = AllayBlockType
                .builder(BlockStickyPistonArmCollisionBehavior.class)
                .vanillaBlock(BlockId.STICKY_PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStone() {
        if (BlockTypes.STONE != null) return;
        BlockTypes.STONE = AllayBlockType
                .builder(BlockStoneBehavior.class)
                .vanillaBlock(BlockId.STONE)
                .build();
    }

    public static void initStoneBricks() {
        if (BlockTypes.STONE_BRICKS != null) return;
        BlockTypes.STONE_BRICKS = AllayBlockType
                .builder(BlockStoneBricksBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICKS)
                .build();
    }

    public static void initStonecutter() {
        if (BlockTypes.STONECUTTER != null) return;
        BlockTypes.STONECUTTER = AllayBlockType
                .builder(BlockStonecutterBehavior.class)
                .vanillaBlock(BlockId.STONECUTTER)
                .build();
    }

    public static void initStonecutterBlock() {
        if (BlockTypes.STONECUTTER_BLOCK != null) return;
        BlockTypes.STONECUTTER_BLOCK = AllayBlockType
                .builder(BlockStonecutterBlockBehavior.class)
                .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initStrippedBambooBlock() {
        if (BlockTypes.STRIPPED_BAMBOO_BLOCK != null) return;
        BlockTypes.STRIPPED_BAMBOO_BLOCK = AllayBlockType
                .builder(BlockStrippedBambooBlockBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonHyphae() {
        if (BlockTypes.STRIPPED_CRIMSON_HYPHAE != null) return;
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = AllayBlockType
                .builder(BlockStrippedCrimsonHyphaeBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonStem() {
        if (BlockTypes.STRIPPED_CRIMSON_STEM != null) return;
        BlockTypes.STRIPPED_CRIMSON_STEM = AllayBlockType
                .builder(BlockStrippedCrimsonStemBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedHyphae() {
        if (BlockTypes.STRIPPED_WARPED_HYPHAE != null) return;
        BlockTypes.STRIPPED_WARPED_HYPHAE = AllayBlockType
                .builder(BlockStrippedWarpedHyphaeBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedStem() {
        if (BlockTypes.STRIPPED_WARPED_STEM != null) return;
        BlockTypes.STRIPPED_WARPED_STEM = AllayBlockType
                .builder(BlockStrippedWarpedStemBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStructureBlock() {
        if (BlockTypes.STRUCTURE_BLOCK != null) return;
        BlockTypes.STRUCTURE_BLOCK = AllayBlockType
                .builder(BlockStructureBlockBehavior.class)
                .vanillaBlock(BlockId.STRUCTURE_BLOCK)
                .setProperties(BlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
                .build();
    }

    public static void initStructureVoid() {
        if (BlockTypes.STRUCTURE_VOID != null) return;
        BlockTypes.STRUCTURE_VOID = AllayBlockType
                .builder(BlockStructureVoidBehavior.class)
                .vanillaBlock(BlockId.STRUCTURE_VOID)
                .setProperties(BlockPropertyTypes.STRUCTURE_VOID_TYPE)
                .build();
    }

    public static void initSunflower() {
        if (BlockTypes.SUNFLOWER != null) return;
        BlockTypes.SUNFLOWER = AllayBlockType
                .builder(BlockSunflowerBehavior.class)
                .vanillaBlock(BlockId.SUNFLOWER)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSuspiciousGravel() {
        if (BlockTypes.SUSPICIOUS_GRAVEL != null) return;
        BlockTypes.SUSPICIOUS_GRAVEL = AllayBlockType
                .builder(BlockSuspiciousGravelBehavior.class)
                .vanillaBlock(BlockId.SUSPICIOUS_GRAVEL)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSuspiciousSand() {
        if (BlockTypes.SUSPICIOUS_SAND != null) return;
        BlockTypes.SUSPICIOUS_SAND = AllayBlockType
                .builder(BlockSuspiciousSandBehavior.class)
                .vanillaBlock(BlockId.SUSPICIOUS_SAND)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSweetBerryBush() {
        if (BlockTypes.SWEET_BERRY_BUSH != null) return;
        BlockTypes.SWEET_BERRY_BUSH = AllayBlockType
                .builder(BlockSweetBerryBushBehavior.class)
                .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTallGrass() {
        if (BlockTypes.TALL_GRASS != null) return;
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehavior.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initTarget() {
        if (BlockTypes.TARGET != null) return;
        BlockTypes.TARGET = AllayBlockType
                .builder(BlockTargetBehavior.class)
                .vanillaBlock(BlockId.TARGET)
                .build();
    }

    public static void initTintedGlass() {
        if (BlockTypes.TINTED_GLASS != null) return;
        BlockTypes.TINTED_GLASS = AllayBlockType
                .builder(BlockTintedGlassBehavior.class)
                .vanillaBlock(BlockId.TINTED_GLASS)
                .build();
    }

    public static void initTnt() {
        if (BlockTypes.TNT != null) return;
        BlockTypes.TNT = AllayBlockType
                .builder(BlockTntBehavior.class)
                .vanillaBlock(BlockId.TNT)
                .setProperties(BlockPropertyTypes.ALLOW_UNDERWATER_BIT, BlockPropertyTypes.EXPLODE_BIT)
                .build();
    }

    public static void initTorch() {
        if (BlockTypes.TORCH != null) return;
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehavior.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initTorchflower() {
        if (BlockTypes.TORCHFLOWER != null) return;
        BlockTypes.TORCHFLOWER = AllayBlockType
                .builder(BlockTorchflowerBehavior.class)
                .vanillaBlock(BlockId.TORCHFLOWER)
                .build();
    }

    public static void initTorchflowerCrop() {
        if (BlockTypes.TORCHFLOWER_CROP != null) return;
        BlockTypes.TORCHFLOWER_CROP = AllayBlockType
                .builder(BlockTorchflowerCropBehavior.class)
                .vanillaBlock(BlockId.TORCHFLOWER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTrappedChest() {
        if (BlockTypes.TRAPPED_CHEST != null) return;
        BlockTypes.TRAPPED_CHEST = AllayBlockType
                .builder(BlockTrappedChestBehavior.class)
                .vanillaBlock(BlockId.TRAPPED_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initTrialSpawner() {
        if (BlockTypes.TRIAL_SPAWNER != null) return;
        BlockTypes.TRIAL_SPAWNER = AllayBlockType
                .builder(BlockTrialSpawnerBehavior.class)
                .vanillaBlock(BlockId.TRIAL_SPAWNER)
                .setProperties(BlockPropertyTypes.OMINOUS, BlockPropertyTypes.TRIAL_SPAWNER_STATE)
                .build();
    }

    public static void initTripWire() {
        if (BlockTypes.TRIP_WIRE != null) return;
        BlockTypes.TRIP_WIRE = AllayBlockType
                .builder(BlockTripWireBehavior.class)
                .vanillaBlock(BlockId.TRIP_WIRE)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DISARMED_BIT, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.SUSPENDED_BIT)
                .build();
    }

    public static void initTripwireHook() {
        if (BlockTypes.TRIPWIRE_HOOK != null) return;
        BlockTypes.TRIPWIRE_HOOK = AllayBlockType
                .builder(BlockTripwireHookBehavior.class)
                .vanillaBlock(BlockId.TRIPWIRE_HOOK)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initTubeCoral() {
        if (BlockTypes.TUBE_CORAL != null) return;
        BlockTypes.TUBE_CORAL = AllayBlockType
                .builder(BlockTubeCoralBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL)
                .build();
    }

    public static void initTubeCoralBlock() {
        if (BlockTypes.TUBE_CORAL_BLOCK != null) return;
        BlockTypes.TUBE_CORAL_BLOCK = AllayBlockType
                .builder(BlockTubeCoralBlockBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initTubeCoralFan() {
        if (BlockTypes.TUBE_CORAL_FAN != null) return;
        BlockTypes.TUBE_CORAL_FAN = AllayBlockType
                .builder(BlockTubeCoralFanBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initTubeCoralWallFan() {
        if (BlockTypes.TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.TUBE_CORAL_WALL_FAN = AllayBlockType
                .builder(BlockTubeCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initTuff() {
        if (BlockTypes.TUFF != null) return;
        BlockTypes.TUFF = AllayBlockType
                .builder(BlockTuffBehavior.class)
                .vanillaBlock(BlockId.TUFF)
                .build();
    }

    public static void initTuffBricks() {
        if (BlockTypes.TUFF_BRICKS != null) return;
        BlockTypes.TUFF_BRICKS = AllayBlockType
                .builder(BlockTuffBricksBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICKS)
                .build();
    }

    public static void initTurtleEgg() {
        if (BlockTypes.TURTLE_EGG != null) return;
        BlockTypes.TURTLE_EGG = AllayBlockType
                .builder(BlockTurtleEggBehavior.class)
                .vanillaBlock(BlockId.TURTLE_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE, BlockPropertyTypes.TURTLE_EGG_COUNT)
                .build();
    }

    public static void initTwistingVines() {
        if (BlockTypes.TWISTING_VINES != null) return;
        BlockTypes.TWISTING_VINES = AllayBlockType
                .builder(BlockTwistingVinesBehavior.class)
                .vanillaBlock(BlockId.TWISTING_VINES)
                .setProperties(BlockPropertyTypes.TWISTING_VINES_AGE)
                .build();
    }

    public static void initUnderwaterTorch() {
        if (BlockTypes.UNDERWATER_TORCH != null) return;
        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockUnderwaterTorchBehavior.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUnknown() {
        if (BlockTypes.UNKNOWN != null) return;
        BlockTypes.UNKNOWN = AllayBlockType
                .builder(BlockUnknownBehavior.class)
                .vanillaBlock(BlockId.UNKNOWN)
                .build();
    }

    public static void initUnlitRedstoneTorch() {
        if (BlockTypes.UNLIT_REDSTONE_TORCH != null) return;
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockUnlitRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUnpoweredComparator() {
        if (BlockTypes.UNPOWERED_COMPARATOR != null) return;
        BlockTypes.UNPOWERED_COMPARATOR = AllayBlockType
                .builder(BlockUnpoweredComparatorBehavior.class)
                .vanillaBlock(BlockId.UNPOWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initUnpoweredRepeater() {
        if (BlockTypes.UNPOWERED_REPEATER != null) return;
        BlockTypes.UNPOWERED_REPEATER = AllayBlockType
                .builder(BlockUnpoweredRepeaterBehavior.class)
                .vanillaBlock(BlockId.UNPOWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initVault() {
        if (BlockTypes.VAULT != null) return;
        BlockTypes.VAULT = AllayBlockType
                .builder(BlockVaultBehavior.class)
                .vanillaBlock(BlockId.VAULT)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OMINOUS, BlockPropertyTypes.VAULT_STATE)
                .build();
    }

    public static void initVerdantFroglight() {
        if (BlockTypes.VERDANT_FROGLIGHT != null) return;
        BlockTypes.VERDANT_FROGLIGHT = AllayBlockType
                .builder(BlockVerdantFroglightBehavior.class)
                .vanillaBlock(BlockId.VERDANT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initVine() {
        if (BlockTypes.VINE != null) return;
        BlockTypes.VINE = AllayBlockType
                .builder(BlockVineBehavior.class)
                .vanillaBlock(BlockId.VINE)
                .setProperties(BlockPropertyTypes.VINE_DIRECTION_BITS)
                .build();
    }

    public static void initWallBanner() {
        if (BlockTypes.WALL_BANNER != null) return;
        BlockTypes.WALL_BANNER = AllayBlockType
                .builder(BlockWallBannerBehavior.class)
                .vanillaBlock(BlockId.WALL_BANNER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedFungus() {
        if (BlockTypes.WARPED_FUNGUS != null) return;
        BlockTypes.WARPED_FUNGUS = AllayBlockType
                .builder(BlockWarpedFungusBehavior.class)
                .vanillaBlock(BlockId.WARPED_FUNGUS)
                .build();
    }

    public static void initWarpedHyphae() {
        if (BlockTypes.WARPED_HYPHAE != null) return;
        BlockTypes.WARPED_HYPHAE = AllayBlockType
                .builder(BlockWarpedHyphaeBehavior.class)
                .vanillaBlock(BlockId.WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedNylium() {
        if (BlockTypes.WARPED_NYLIUM != null) return;
        BlockTypes.WARPED_NYLIUM = AllayBlockType
                .builder(BlockWarpedNyliumBehavior.class)
                .vanillaBlock(BlockId.WARPED_NYLIUM)
                .build();
    }

    public static void initWarpedRoots() {
        if (BlockTypes.WARPED_ROOTS != null) return;
        BlockTypes.WARPED_ROOTS = AllayBlockType
                .builder(BlockWarpedRootsBehavior.class)
                .vanillaBlock(BlockId.WARPED_ROOTS)
                .build();
    }

    public static void initWarpedStem() {
        if (BlockTypes.WARPED_STEM != null) return;
        BlockTypes.WARPED_STEM = AllayBlockType
                .builder(BlockWarpedStemBehavior.class)
                .vanillaBlock(BlockId.WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedWartBlock() {
        if (BlockTypes.WARPED_WART_BLOCK != null) return;
        BlockTypes.WARPED_WART_BLOCK = AllayBlockType
                .builder(BlockWarpedWartBlockBehavior.class)
                .vanillaBlock(BlockId.WARPED_WART_BLOCK)
                .build();
    }

    public static void initWater() {
        if (BlockTypes.WATER != null) return;
        BlockTypes.WATER = AllayBlockType
                .builder(BlockWaterBehavior.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initWaterlily() {
        if (BlockTypes.WATERLILY != null) return;
        BlockTypes.WATERLILY = AllayBlockType
                .builder(BlockWaterlilyBehavior.class)
                .vanillaBlock(BlockId.WATERLILY)
                .build();
    }

    public static void initWaxedChiseledCopper() {
        if (BlockTypes.WAXED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedCopper() {
        if (BlockTypes.WAXED_COPPER != null) return;
        BlockTypes.WAXED_COPPER = AllayBlockType
                .builder(BlockWaxedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER)
                .build();
    }

    public static void initWaxedExposedChiseledCopper() {
        if (BlockTypes.WAXED_EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedExposedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopper() {
        if (BlockTypes.WAXED_EXPOSED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER = AllayBlockType
                .builder(BlockWaxedExposedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedChiseledCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopper() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredChiseledCopper() {
        if (BlockTypes.WAXED_WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopper() {
        if (BlockTypes.WAXED_WEATHERED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER)
                .build();
    }

    public static void initWeatheredChiseledCopper() {
        if (BlockTypes.WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WEATHERED_CHISELED_COPPER = AllayBlockType
                .builder(BlockWeatheredChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWeatheredCopper() {
        if (BlockTypes.WEATHERED_COPPER != null) return;
        BlockTypes.WEATHERED_COPPER = AllayBlockType
                .builder(BlockWeatheredCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER)
                .build();
    }

    public static void initWeb() {
        if (BlockTypes.WEB != null) return;
        BlockTypes.WEB = AllayBlockType
                .builder(BlockWebBehavior.class)
                .vanillaBlock(BlockId.WEB)
                .build();
    }

    public static void initWeepingVines() {
        if (BlockTypes.WEEPING_VINES != null) return;
        BlockTypes.WEEPING_VINES = AllayBlockType
                .builder(BlockWeepingVinesBehavior.class)
                .vanillaBlock(BlockId.WEEPING_VINES)
                .setProperties(BlockPropertyTypes.WEEPING_VINES_AGE)
                .build();
    }

    public static void initWheat() {
        if (BlockTypes.WHEAT != null) return;
        BlockTypes.WHEAT = AllayBlockType
                .builder(BlockWheatBehavior.class)
                .vanillaBlock(BlockId.WHEAT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initWitherRose() {
        if (BlockTypes.WITHER_ROSE != null) return;
        BlockTypes.WITHER_ROSE = AllayBlockType
                .builder(BlockWitherRoseBehavior.class)
                .vanillaBlock(BlockId.WITHER_ROSE)
                .build();
    }

    public static void initDoubleSlabs() {
        if (BlockTypes.ACACIA_DOUBLE_SLAB == null) BlockTypes.ACACIA_DOUBLE_SLAB = buildDoubleSlab(BlockAcaciaDoubleSlabBehavior.class, BlockId.ACACIA_DOUBLE_SLAB);
        if (BlockTypes.ANDESITE_DOUBLE_SLAB == null) BlockTypes.ANDESITE_DOUBLE_SLAB = buildDoubleSlab(BlockAndesiteDoubleSlabBehavior.class, BlockId.ANDESITE_DOUBLE_SLAB);
        if (BlockTypes.BAMBOO_DOUBLE_SLAB == null) BlockTypes.BAMBOO_DOUBLE_SLAB = buildDoubleSlab(BlockBambooDoubleSlabBehavior.class, BlockId.BAMBOO_DOUBLE_SLAB);
        if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB == null) BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = buildDoubleSlab(BlockBambooMosaicDoubleSlabBehavior.class, BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB);
        if (BlockTypes.BIRCH_DOUBLE_SLAB == null) BlockTypes.BIRCH_DOUBLE_SLAB = buildDoubleSlab(BlockBirchDoubleSlabBehavior.class, BlockId.BIRCH_DOUBLE_SLAB);
        if (BlockTypes.BLACKSTONE_DOUBLE_SLAB == null) BlockTypes.BLACKSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockBlackstoneDoubleSlabBehavior.class, BlockId.BLACKSTONE_DOUBLE_SLAB);
        if (BlockTypes.BRICK_DOUBLE_SLAB == null) BlockTypes.BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockBrickDoubleSlabBehavior.class, BlockId.BRICK_DOUBLE_SLAB);
        if (BlockTypes.CHERRY_DOUBLE_SLAB == null) BlockTypes.CHERRY_DOUBLE_SLAB = buildDoubleSlab(BlockCherryDoubleSlabBehavior.class, BlockId.CHERRY_DOUBLE_SLAB);
        if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB == null) BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = buildDoubleSlab(BlockCobbledDeepslateDoubleSlabBehavior.class, BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB);
        if (BlockTypes.COBBLESTONE_DOUBLE_SLAB == null) BlockTypes.COBBLESTONE_DOUBLE_SLAB = buildDoubleSlab(BlockCobblestoneDoubleSlabBehavior.class, BlockId.COBBLESTONE_DOUBLE_SLAB);
        if (BlockTypes.CRIMSON_DOUBLE_SLAB == null) BlockTypes.CRIMSON_DOUBLE_SLAB = buildDoubleSlab(BlockCrimsonDoubleSlabBehavior.class, BlockId.CRIMSON_DOUBLE_SLAB);
        if (BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB == null) BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockCutRedSandstoneDoubleSlabBehavior.class, BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB == null) BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockCutSandstoneDoubleSlabBehavior.class, BlockId.CUT_SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.DARK_OAK_DOUBLE_SLAB == null) BlockTypes.DARK_OAK_DOUBLE_SLAB = buildDoubleSlab(BlockDarkOakDoubleSlabBehavior.class, BlockId.DARK_OAK_DOUBLE_SLAB);
        if (BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB == null) BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = buildDoubleSlab(BlockDarkPrismarineDoubleSlabBehavior.class, BlockId.DARK_PRISMARINE_DOUBLE_SLAB);
        if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB == null) BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockDeepslateBrickDoubleSlabBehavior.class, BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB == null) BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = buildDoubleSlab(BlockDeepslateTileDoubleSlabBehavior.class, BlockId.DEEPSLATE_TILE_DOUBLE_SLAB);
        if (BlockTypes.DIORITE_DOUBLE_SLAB == null) BlockTypes.DIORITE_DOUBLE_SLAB = buildDoubleSlab(BlockDioriteDoubleSlabBehavior.class, BlockId.DIORITE_DOUBLE_SLAB);
        if (BlockTypes.DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockDoubleCutCopperSlabBehavior.class, BlockId.DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.END_STONE_BRICK_DOUBLE_SLAB == null) BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockEndStoneBrickDoubleSlabBehavior.class, BlockId.END_STONE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockExposedDoubleCutCopperSlabBehavior.class, BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.GRANITE_DOUBLE_SLAB == null) BlockTypes.GRANITE_DOUBLE_SLAB = buildDoubleSlab(BlockGraniteDoubleSlabBehavior.class, BlockId.GRANITE_DOUBLE_SLAB);
        if (BlockTypes.JUNGLE_DOUBLE_SLAB == null) BlockTypes.JUNGLE_DOUBLE_SLAB = buildDoubleSlab(BlockJungleDoubleSlabBehavior.class, BlockId.JUNGLE_DOUBLE_SLAB);
        if (BlockTypes.MANGROVE_DOUBLE_SLAB == null) BlockTypes.MANGROVE_DOUBLE_SLAB = buildDoubleSlab(BlockMangroveDoubleSlabBehavior.class, BlockId.MANGROVE_DOUBLE_SLAB);
        if (BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB == null) BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = buildDoubleSlab(BlockMossyCobblestoneDoubleSlabBehavior.class, BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB);
        if (BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB == null) BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockMossyStoneBrickDoubleSlabBehavior.class, BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.MUD_BRICK_DOUBLE_SLAB == null) BlockTypes.MUD_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockMudBrickDoubleSlabBehavior.class, BlockId.MUD_BRICK_DOUBLE_SLAB);
        if (BlockTypes.NETHER_BRICK_DOUBLE_SLAB == null) BlockTypes.NETHER_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockNetherBrickDoubleSlabBehavior.class, BlockId.NETHER_BRICK_DOUBLE_SLAB);
        if (BlockTypes.NORMAL_STONE_DOUBLE_SLAB == null) BlockTypes.NORMAL_STONE_DOUBLE_SLAB = buildDoubleSlab(BlockNormalStoneDoubleSlabBehavior.class, BlockId.NORMAL_STONE_DOUBLE_SLAB);
        if (BlockTypes.OAK_DOUBLE_SLAB == null) BlockTypes.OAK_DOUBLE_SLAB = buildDoubleSlab(BlockOakDoubleSlabBehavior.class, BlockId.OAK_DOUBLE_SLAB);
        if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockOxidizedDoubleCutCopperSlabBehavior.class, BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB == null) BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = buildDoubleSlab(BlockPetrifiedOakDoubleSlabBehavior.class, BlockId.PETRIFIED_OAK_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB == null) BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedAndesiteDoubleSlabBehavior.class, BlockId.POLISHED_ANDESITE_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB == null) BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class, BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB == null) BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedBlackstoneDoubleSlabBehavior.class, BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB == null) BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedDeepslateDoubleSlabBehavior.class, BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB == null) BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedDioriteDoubleSlabBehavior.class, BlockId.POLISHED_DIORITE_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB == null) BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedGraniteDoubleSlabBehavior.class, BlockId.POLISHED_GRANITE_DOUBLE_SLAB);
        if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB == null) BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = buildDoubleSlab(BlockPolishedTuffDoubleSlabBehavior.class, BlockId.POLISHED_TUFF_DOUBLE_SLAB);
        if (BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB == null) BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockPrismarineBrickDoubleSlabBehavior.class, BlockId.PRISMARINE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.PRISMARINE_DOUBLE_SLAB == null) BlockTypes.PRISMARINE_DOUBLE_SLAB = buildDoubleSlab(BlockPrismarineDoubleSlabBehavior.class, BlockId.PRISMARINE_DOUBLE_SLAB);
        if (BlockTypes.PURPUR_DOUBLE_SLAB == null) BlockTypes.PURPUR_DOUBLE_SLAB = buildDoubleSlab(BlockPurpurDoubleSlabBehavior.class, BlockId.PURPUR_DOUBLE_SLAB);
        if (BlockTypes.QUARTZ_DOUBLE_SLAB == null) BlockTypes.QUARTZ_DOUBLE_SLAB = buildDoubleSlab(BlockQuartzDoubleSlabBehavior.class, BlockId.QUARTZ_DOUBLE_SLAB);
        if (BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB == null) BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockRedNetherBrickDoubleSlabBehavior.class, BlockId.RED_NETHER_BRICK_DOUBLE_SLAB);
        if (BlockTypes.RED_SANDSTONE_DOUBLE_SLAB == null) BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockRedSandstoneDoubleSlabBehavior.class, BlockId.RED_SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.SANDSTONE_DOUBLE_SLAB == null) BlockTypes.SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockSandstoneDoubleSlabBehavior.class, BlockId.SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB == null) BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = buildDoubleSlab(BlockSmoothQuartzDoubleSlabBehavior.class, BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB);
        if (BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB == null) BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockSmoothRedSandstoneDoubleSlabBehavior.class, BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB == null) BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = buildDoubleSlab(BlockSmoothSandstoneDoubleSlabBehavior.class, BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB);
        if (BlockTypes.SMOOTH_STONE_DOUBLE_SLAB == null) BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = buildDoubleSlab(BlockSmoothStoneDoubleSlabBehavior.class, BlockId.SMOOTH_STONE_DOUBLE_SLAB);
        if (BlockTypes.SPRUCE_DOUBLE_SLAB == null) BlockTypes.SPRUCE_DOUBLE_SLAB = buildDoubleSlab(BlockSpruceDoubleSlabBehavior.class, BlockId.SPRUCE_DOUBLE_SLAB);
        if (BlockTypes.STONE_BRICK_DOUBLE_SLAB == null) BlockTypes.STONE_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockStoneBrickDoubleSlabBehavior.class, BlockId.STONE_BRICK_DOUBLE_SLAB);
        if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB == null) BlockTypes.TUFF_BRICK_DOUBLE_SLAB = buildDoubleSlab(BlockTuffBrickDoubleSlabBehavior.class, BlockId.TUFF_BRICK_DOUBLE_SLAB);
        if (BlockTypes.TUFF_DOUBLE_SLAB == null) BlockTypes.TUFF_DOUBLE_SLAB = buildDoubleSlab(BlockTuffDoubleSlabBehavior.class, BlockId.TUFF_DOUBLE_SLAB);
        if (BlockTypes.WARPED_DOUBLE_SLAB == null) BlockTypes.WARPED_DOUBLE_SLAB = buildDoubleSlab(BlockWarpedDoubleSlabBehavior.class, BlockId.WARPED_DOUBLE_SLAB);
        if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockWaxedDoubleCutCopperSlabBehavior.class, BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockWaxedExposedDoubleCutCopperSlabBehavior.class, BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class, BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class, BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB);
        if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = buildDoubleSlab(BlockWeatheredDoubleCutCopperSlabBehavior.class, BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoubleSlab(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
               .build();
    }

    public static void initDoors() {
        if (BlockTypes.ACACIA_DOOR == null) BlockTypes.ACACIA_DOOR = buildDoor(BlockAcaciaDoorBehavior.class, BlockId.ACACIA_DOOR);
        if (BlockTypes.BAMBOO_DOOR == null) BlockTypes.BAMBOO_DOOR = buildDoor(BlockBambooDoorBehavior.class, BlockId.BAMBOO_DOOR);
        if (BlockTypes.BIRCH_DOOR == null) BlockTypes.BIRCH_DOOR = buildDoor(BlockBirchDoorBehavior.class, BlockId.BIRCH_DOOR);
        if (BlockTypes.CHERRY_DOOR == null) BlockTypes.CHERRY_DOOR = buildDoor(BlockCherryDoorBehavior.class, BlockId.CHERRY_DOOR);
        if (BlockTypes.COPPER_DOOR == null) BlockTypes.COPPER_DOOR = buildDoor(BlockCopperDoorBehavior.class, BlockId.COPPER_DOOR);
        if (BlockTypes.CRIMSON_DOOR == null) BlockTypes.CRIMSON_DOOR = buildDoor(BlockCrimsonDoorBehavior.class, BlockId.CRIMSON_DOOR);
        if (BlockTypes.DARK_OAK_DOOR == null) BlockTypes.DARK_OAK_DOOR = buildDoor(BlockDarkOakDoorBehavior.class, BlockId.DARK_OAK_DOOR);
        if (BlockTypes.EXPOSED_COPPER_DOOR == null) BlockTypes.EXPOSED_COPPER_DOOR = buildDoor(BlockExposedCopperDoorBehavior.class, BlockId.EXPOSED_COPPER_DOOR);
        if (BlockTypes.IRON_DOOR == null) BlockTypes.IRON_DOOR = buildDoor(BlockIronDoorBehavior.class, BlockId.IRON_DOOR);
        if (BlockTypes.JUNGLE_DOOR == null) BlockTypes.JUNGLE_DOOR = buildDoor(BlockJungleDoorBehavior.class, BlockId.JUNGLE_DOOR);
        if (BlockTypes.MANGROVE_DOOR == null) BlockTypes.MANGROVE_DOOR = buildDoor(BlockMangroveDoorBehavior.class, BlockId.MANGROVE_DOOR);
        if (BlockTypes.OXIDIZED_COPPER_DOOR == null) BlockTypes.OXIDIZED_COPPER_DOOR = buildDoor(BlockOxidizedCopperDoorBehavior.class, BlockId.OXIDIZED_COPPER_DOOR);
        if (BlockTypes.SPRUCE_DOOR == null) BlockTypes.SPRUCE_DOOR = buildDoor(BlockSpruceDoorBehavior.class, BlockId.SPRUCE_DOOR);
        if (BlockTypes.WARPED_DOOR == null) BlockTypes.WARPED_DOOR = buildDoor(BlockWarpedDoorBehavior.class, BlockId.WARPED_DOOR);
        if (BlockTypes.WAXED_COPPER_DOOR == null) BlockTypes.WAXED_COPPER_DOOR = buildDoor(BlockWaxedCopperDoorBehavior.class, BlockId.WAXED_COPPER_DOOR);
        if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR == null) BlockTypes.WAXED_EXPOSED_COPPER_DOOR = buildDoor(BlockWaxedExposedCopperDoorBehavior.class, BlockId.WAXED_EXPOSED_COPPER_DOOR);
        if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR == null) BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = buildDoor(BlockWaxedOxidizedCopperDoorBehavior.class, BlockId.WAXED_OXIDIZED_COPPER_DOOR);
        if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR == null) BlockTypes.WAXED_WEATHERED_COPPER_DOOR = buildDoor(BlockWaxedWeatheredCopperDoorBehavior.class, BlockId.WAXED_WEATHERED_COPPER_DOOR);
        if (BlockTypes.WEATHERED_COPPER_DOOR == null) BlockTypes.WEATHERED_COPPER_DOOR = buildDoor(BlockWeatheredCopperDoorBehavior.class, BlockId.WEATHERED_COPPER_DOOR);
        if (BlockTypes.WOODEN_DOOR == null) BlockTypes.WOODEN_DOOR = buildDoor(BlockWoodenDoorBehavior.class, BlockId.WOODEN_DOOR);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT)
               .build();
    }

    public static void initCandleCakes() {
        if (BlockTypes.BLACK_CANDLE_CAKE == null) BlockTypes.BLACK_CANDLE_CAKE = buildCandleCake(BlockBlackCandleCakeBehavior.class, BlockId.BLACK_CANDLE_CAKE);
        if (BlockTypes.BLUE_CANDLE_CAKE == null) BlockTypes.BLUE_CANDLE_CAKE = buildCandleCake(BlockBlueCandleCakeBehavior.class, BlockId.BLUE_CANDLE_CAKE);
        if (BlockTypes.BROWN_CANDLE_CAKE == null) BlockTypes.BROWN_CANDLE_CAKE = buildCandleCake(BlockBrownCandleCakeBehavior.class, BlockId.BROWN_CANDLE_CAKE);
        if (BlockTypes.CYAN_CANDLE_CAKE == null) BlockTypes.CYAN_CANDLE_CAKE = buildCandleCake(BlockCyanCandleCakeBehavior.class, BlockId.CYAN_CANDLE_CAKE);
        if (BlockTypes.GRAY_CANDLE_CAKE == null) BlockTypes.GRAY_CANDLE_CAKE = buildCandleCake(BlockGrayCandleCakeBehavior.class, BlockId.GRAY_CANDLE_CAKE);
        if (BlockTypes.GREEN_CANDLE_CAKE == null) BlockTypes.GREEN_CANDLE_CAKE = buildCandleCake(BlockGreenCandleCakeBehavior.class, BlockId.GREEN_CANDLE_CAKE);
        if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE == null) BlockTypes.LIGHT_BLUE_CANDLE_CAKE = buildCandleCake(BlockLightBlueCandleCakeBehavior.class, BlockId.LIGHT_BLUE_CANDLE_CAKE);
        if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE == null) BlockTypes.LIGHT_GRAY_CANDLE_CAKE = buildCandleCake(BlockLightGrayCandleCakeBehavior.class, BlockId.LIGHT_GRAY_CANDLE_CAKE);
        if (BlockTypes.LIME_CANDLE_CAKE == null) BlockTypes.LIME_CANDLE_CAKE = buildCandleCake(BlockLimeCandleCakeBehavior.class, BlockId.LIME_CANDLE_CAKE);
        if (BlockTypes.MAGENTA_CANDLE_CAKE == null) BlockTypes.MAGENTA_CANDLE_CAKE = buildCandleCake(BlockMagentaCandleCakeBehavior.class, BlockId.MAGENTA_CANDLE_CAKE);
        if (BlockTypes.ORANGE_CANDLE_CAKE == null) BlockTypes.ORANGE_CANDLE_CAKE = buildCandleCake(BlockOrangeCandleCakeBehavior.class, BlockId.ORANGE_CANDLE_CAKE);
        if (BlockTypes.PINK_CANDLE_CAKE == null) BlockTypes.PINK_CANDLE_CAKE = buildCandleCake(BlockPinkCandleCakeBehavior.class, BlockId.PINK_CANDLE_CAKE);
        if (BlockTypes.PURPLE_CANDLE_CAKE == null) BlockTypes.PURPLE_CANDLE_CAKE = buildCandleCake(BlockPurpleCandleCakeBehavior.class, BlockId.PURPLE_CANDLE_CAKE);
        if (BlockTypes.RED_CANDLE_CAKE == null) BlockTypes.RED_CANDLE_CAKE = buildCandleCake(BlockRedCandleCakeBehavior.class, BlockId.RED_CANDLE_CAKE);
        if (BlockTypes.WHITE_CANDLE_CAKE == null) BlockTypes.WHITE_CANDLE_CAKE = buildCandleCake(BlockWhiteCandleCakeBehavior.class, BlockId.WHITE_CANDLE_CAKE);
        if (BlockTypes.YELLOW_CANDLE_CAKE == null) BlockTypes.YELLOW_CANDLE_CAKE = buildCandleCake(BlockYellowCandleCakeBehavior.class, BlockId.YELLOW_CANDLE_CAKE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCandleCake(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.LIT)
               .build();
    }

    public static void initTulips() {
        if (BlockTypes.ORANGE_TULIP == null) BlockTypes.ORANGE_TULIP = buildTulip(BlockOrangeTulipBehavior.class, BlockId.ORANGE_TULIP);
        if (BlockTypes.PINK_TULIP == null) BlockTypes.PINK_TULIP = buildTulip(BlockPinkTulipBehavior.class, BlockId.PINK_TULIP);
        if (BlockTypes.RED_TULIP == null) BlockTypes.RED_TULIP = buildTulip(BlockRedTulipBehavior.class, BlockId.RED_TULIP);
        if (BlockTypes.WHITE_TULIP == null) BlockTypes.WHITE_TULIP = buildTulip(BlockWhiteTulipBehavior.class, BlockId.WHITE_TULIP);
    }

    private static <T extends BlockBehavior> BlockType<T> buildTulip(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initCopperBulbs() {
        if (BlockTypes.EXPOSED_COPPER_BULB == null) BlockTypes.EXPOSED_COPPER_BULB = buildCopperBulb(BlockExposedCopperBulbBehavior.class, BlockId.EXPOSED_COPPER_BULB);
        if (BlockTypes.OXIDIZED_COPPER_BULB == null) BlockTypes.OXIDIZED_COPPER_BULB = buildCopperBulb(BlockOxidizedCopperBulbBehavior.class, BlockId.OXIDIZED_COPPER_BULB);
        if (BlockTypes.WAXED_COPPER_BULB == null) BlockTypes.WAXED_COPPER_BULB = buildCopperBulb(BlockWaxedCopperBulbBehavior.class, BlockId.WAXED_COPPER_BULB);
        if (BlockTypes.WAXED_EXPOSED_COPPER_BULB == null) BlockTypes.WAXED_EXPOSED_COPPER_BULB = buildCopperBulb(BlockWaxedExposedCopperBulbBehavior.class, BlockId.WAXED_EXPOSED_COPPER_BULB);
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB == null) BlockTypes.WAXED_OXIDIZED_COPPER_BULB = buildCopperBulb(BlockWaxedOxidizedCopperBulbBehavior.class, BlockId.WAXED_OXIDIZED_COPPER_BULB);
        if (BlockTypes.WAXED_WEATHERED_COPPER_BULB == null) BlockTypes.WAXED_WEATHERED_COPPER_BULB = buildCopperBulb(BlockWaxedWeatheredCopperBulbBehavior.class, BlockId.WAXED_WEATHERED_COPPER_BULB);
        if (BlockTypes.WEATHERED_COPPER_BULB == null) BlockTypes.WEATHERED_COPPER_BULB = buildCopperBulb(BlockWeatheredCopperBulbBehavior.class, BlockId.WEATHERED_COPPER_BULB);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCopperBulb(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
               .build();
    }

    public static void initStrippedLogs() {
        if (BlockTypes.STRIPPED_ACACIA_LOG == null) BlockTypes.STRIPPED_ACACIA_LOG = buildStrippedLog(BlockStrippedAcaciaLogBehavior.class, BlockId.STRIPPED_ACACIA_LOG);
        if (BlockTypes.STRIPPED_BIRCH_LOG == null) BlockTypes.STRIPPED_BIRCH_LOG = buildStrippedLog(BlockStrippedBirchLogBehavior.class, BlockId.STRIPPED_BIRCH_LOG);
        if (BlockTypes.STRIPPED_CHERRY_LOG == null) BlockTypes.STRIPPED_CHERRY_LOG = buildStrippedLog(BlockStrippedCherryLogBehavior.class, BlockId.STRIPPED_CHERRY_LOG);
        if (BlockTypes.STRIPPED_DARK_OAK_LOG == null) BlockTypes.STRIPPED_DARK_OAK_LOG = buildStrippedLog(BlockStrippedDarkOakLogBehavior.class, BlockId.STRIPPED_DARK_OAK_LOG);
        if (BlockTypes.STRIPPED_JUNGLE_LOG == null) BlockTypes.STRIPPED_JUNGLE_LOG = buildStrippedLog(BlockStrippedJungleLogBehavior.class, BlockId.STRIPPED_JUNGLE_LOG);
        if (BlockTypes.STRIPPED_MANGROVE_LOG == null) BlockTypes.STRIPPED_MANGROVE_LOG = buildStrippedLog(BlockStrippedMangroveLogBehavior.class, BlockId.STRIPPED_MANGROVE_LOG);
        if (BlockTypes.STRIPPED_OAK_LOG == null) BlockTypes.STRIPPED_OAK_LOG = buildStrippedLog(BlockStrippedOakLogBehavior.class, BlockId.STRIPPED_OAK_LOG);
        if (BlockTypes.STRIPPED_SPRUCE_LOG == null) BlockTypes.STRIPPED_SPRUCE_LOG = buildStrippedLog(BlockStrippedSpruceLogBehavior.class, BlockId.STRIPPED_SPRUCE_LOG);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedLog(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.PILLAR_AXIS)
               .build();
    }

    public static void initCopperGrates() {
        if (BlockTypes.EXPOSED_COPPER_GRATE == null) BlockTypes.EXPOSED_COPPER_GRATE = buildCopperGrate(BlockExposedCopperGrateBehavior.class, BlockId.EXPOSED_COPPER_GRATE);
        if (BlockTypes.OXIDIZED_COPPER_GRATE == null) BlockTypes.OXIDIZED_COPPER_GRATE = buildCopperGrate(BlockOxidizedCopperGrateBehavior.class, BlockId.OXIDIZED_COPPER_GRATE);
        if (BlockTypes.WAXED_COPPER_GRATE == null) BlockTypes.WAXED_COPPER_GRATE = buildCopperGrate(BlockWaxedCopperGrateBehavior.class, BlockId.WAXED_COPPER_GRATE);
        if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE == null) BlockTypes.WAXED_EXPOSED_COPPER_GRATE = buildCopperGrate(BlockWaxedExposedCopperGrateBehavior.class, BlockId.WAXED_EXPOSED_COPPER_GRATE);
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE == null) BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = buildCopperGrate(BlockWaxedOxidizedCopperGrateBehavior.class, BlockId.WAXED_OXIDIZED_COPPER_GRATE);
        if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE == null) BlockTypes.WAXED_WEATHERED_COPPER_GRATE = buildCopperGrate(BlockWaxedWeatheredCopperGrateBehavior.class, BlockId.WAXED_WEATHERED_COPPER_GRATE);
        if (BlockTypes.WEATHERED_COPPER_GRATE == null) BlockTypes.WEATHERED_COPPER_GRATE = buildCopperGrate(BlockWeatheredCopperGrateBehavior.class, BlockId.WEATHERED_COPPER_GRATE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCopperGrate(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initSaplings() {
        if (BlockTypes.ACACIA_SAPLING == null) BlockTypes.ACACIA_SAPLING = buildSapling(BlockAcaciaSaplingBehavior.class, BlockId.ACACIA_SAPLING);
        if (BlockTypes.BAMBOO_SAPLING == null) BlockTypes.BAMBOO_SAPLING = buildSapling(BlockBambooSaplingBehavior.class, BlockId.BAMBOO_SAPLING);
        if (BlockTypes.BIRCH_SAPLING == null) BlockTypes.BIRCH_SAPLING = buildSapling(BlockBirchSaplingBehavior.class, BlockId.BIRCH_SAPLING);
        if (BlockTypes.CHERRY_SAPLING == null) BlockTypes.CHERRY_SAPLING = buildSapling(BlockCherrySaplingBehavior.class, BlockId.CHERRY_SAPLING);
        if (BlockTypes.DARK_OAK_SAPLING == null) BlockTypes.DARK_OAK_SAPLING = buildSapling(BlockDarkOakSaplingBehavior.class, BlockId.DARK_OAK_SAPLING);
        if (BlockTypes.JUNGLE_SAPLING == null) BlockTypes.JUNGLE_SAPLING = buildSapling(BlockJungleSaplingBehavior.class, BlockId.JUNGLE_SAPLING);
        if (BlockTypes.OAK_SAPLING == null) BlockTypes.OAK_SAPLING = buildSapling(BlockOakSaplingBehavior.class, BlockId.OAK_SAPLING);
        if (BlockTypes.SPRUCE_SAPLING == null) BlockTypes.SPRUCE_SAPLING = buildSapling(BlockSpruceSaplingBehavior.class, BlockId.SPRUCE_SAPLING);
    }

    private static <T extends BlockBehavior> BlockType<T> buildSapling(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.AGE_BIT)
               .build();
    }

    public static void initLogs() {
        if (BlockTypes.ACACIA_LOG == null) BlockTypes.ACACIA_LOG = buildLog(BlockAcaciaLogBehavior.class, BlockId.ACACIA_LOG);
        if (BlockTypes.BIRCH_LOG == null) BlockTypes.BIRCH_LOG = buildLog(BlockBirchLogBehavior.class, BlockId.BIRCH_LOG);
        if (BlockTypes.CHERRY_LOG == null) BlockTypes.CHERRY_LOG = buildLog(BlockCherryLogBehavior.class, BlockId.CHERRY_LOG);
        if (BlockTypes.DARK_OAK_LOG == null) BlockTypes.DARK_OAK_LOG = buildLog(BlockDarkOakLogBehavior.class, BlockId.DARK_OAK_LOG);
        if (BlockTypes.JUNGLE_LOG == null) BlockTypes.JUNGLE_LOG = buildLog(BlockJungleLogBehavior.class, BlockId.JUNGLE_LOG);
        if (BlockTypes.MANGROVE_LOG == null) BlockTypes.MANGROVE_LOG = buildLog(BlockMangroveLogBehavior.class, BlockId.MANGROVE_LOG);
        if (BlockTypes.OAK_LOG == null) BlockTypes.OAK_LOG = buildLog(BlockOakLogBehavior.class, BlockId.OAK_LOG);
        if (BlockTypes.SPRUCE_LOG == null) BlockTypes.SPRUCE_LOG = buildLog(BlockSpruceLogBehavior.class, BlockId.SPRUCE_LOG);
    }

    private static <T extends BlockBehavior> BlockType<T> buildLog(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.PILLAR_AXIS)
               .build();
    }

    public static void initStrippedWoods() {
        if (BlockTypes.STRIPPED_ACACIA_WOOD == null) BlockTypes.STRIPPED_ACACIA_WOOD = buildStrippedWood(BlockStrippedAcaciaWoodBehavior.class, BlockId.STRIPPED_ACACIA_WOOD);
        if (BlockTypes.STRIPPED_BIRCH_WOOD == null) BlockTypes.STRIPPED_BIRCH_WOOD = buildStrippedWood(BlockStrippedBirchWoodBehavior.class, BlockId.STRIPPED_BIRCH_WOOD);
        if (BlockTypes.STRIPPED_CHERRY_WOOD == null) BlockTypes.STRIPPED_CHERRY_WOOD = buildStrippedWood(BlockStrippedCherryWoodBehavior.class, BlockId.STRIPPED_CHERRY_WOOD);
        if (BlockTypes.STRIPPED_DARK_OAK_WOOD == null) BlockTypes.STRIPPED_DARK_OAK_WOOD = buildStrippedWood(BlockStrippedDarkOakWoodBehavior.class, BlockId.STRIPPED_DARK_OAK_WOOD);
        if (BlockTypes.STRIPPED_JUNGLE_WOOD == null) BlockTypes.STRIPPED_JUNGLE_WOOD = buildStrippedWood(BlockStrippedJungleWoodBehavior.class, BlockId.STRIPPED_JUNGLE_WOOD);
        if (BlockTypes.STRIPPED_MANGROVE_WOOD == null) BlockTypes.STRIPPED_MANGROVE_WOOD = buildStrippedWood(BlockStrippedMangroveWoodBehavior.class, BlockId.STRIPPED_MANGROVE_WOOD);
        if (BlockTypes.STRIPPED_OAK_WOOD == null) BlockTypes.STRIPPED_OAK_WOOD = buildStrippedWood(BlockStrippedOakWoodBehavior.class, BlockId.STRIPPED_OAK_WOOD);
        if (BlockTypes.STRIPPED_SPRUCE_WOOD == null) BlockTypes.STRIPPED_SPRUCE_WOOD = buildStrippedWood(BlockStrippedSpruceWoodBehavior.class, BlockId.STRIPPED_SPRUCE_WOOD);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.PILLAR_AXIS)
               .build();
    }

    public static void initStainedGlass() {
        if (BlockTypes.BLACK_STAINED_GLASS == null) BlockTypes.BLACK_STAINED_GLASS = buildStainedGlass(BlockBlackStainedGlassBehavior.class, BlockId.BLACK_STAINED_GLASS);
        if (BlockTypes.BLUE_STAINED_GLASS == null) BlockTypes.BLUE_STAINED_GLASS = buildStainedGlass(BlockBlueStainedGlassBehavior.class, BlockId.BLUE_STAINED_GLASS);
        if (BlockTypes.BROWN_STAINED_GLASS == null) BlockTypes.BROWN_STAINED_GLASS = buildStainedGlass(BlockBrownStainedGlassBehavior.class, BlockId.BROWN_STAINED_GLASS);
        if (BlockTypes.CYAN_STAINED_GLASS == null) BlockTypes.CYAN_STAINED_GLASS = buildStainedGlass(BlockCyanStainedGlassBehavior.class, BlockId.CYAN_STAINED_GLASS);
        if (BlockTypes.GRAY_STAINED_GLASS == null) BlockTypes.GRAY_STAINED_GLASS = buildStainedGlass(BlockGrayStainedGlassBehavior.class, BlockId.GRAY_STAINED_GLASS);
        if (BlockTypes.GREEN_STAINED_GLASS == null) BlockTypes.GREEN_STAINED_GLASS = buildStainedGlass(BlockGreenStainedGlassBehavior.class, BlockId.GREEN_STAINED_GLASS);
        if (BlockTypes.HARD_BLACK_STAINED_GLASS == null) BlockTypes.HARD_BLACK_STAINED_GLASS = buildStainedGlass(BlockHardBlackStainedGlassBehavior.class, BlockId.HARD_BLACK_STAINED_GLASS);
        if (BlockTypes.HARD_BLUE_STAINED_GLASS == null) BlockTypes.HARD_BLUE_STAINED_GLASS = buildStainedGlass(BlockHardBlueStainedGlassBehavior.class, BlockId.HARD_BLUE_STAINED_GLASS);
        if (BlockTypes.HARD_BROWN_STAINED_GLASS == null) BlockTypes.HARD_BROWN_STAINED_GLASS = buildStainedGlass(BlockHardBrownStainedGlassBehavior.class, BlockId.HARD_BROWN_STAINED_GLASS);
        if (BlockTypes.HARD_CYAN_STAINED_GLASS == null) BlockTypes.HARD_CYAN_STAINED_GLASS = buildStainedGlass(BlockHardCyanStainedGlassBehavior.class, BlockId.HARD_CYAN_STAINED_GLASS);
        if (BlockTypes.HARD_GRAY_STAINED_GLASS == null) BlockTypes.HARD_GRAY_STAINED_GLASS = buildStainedGlass(BlockHardGrayStainedGlassBehavior.class, BlockId.HARD_GRAY_STAINED_GLASS);
        if (BlockTypes.HARD_GREEN_STAINED_GLASS == null) BlockTypes.HARD_GREEN_STAINED_GLASS = buildStainedGlass(BlockHardGreenStainedGlassBehavior.class, BlockId.HARD_GREEN_STAINED_GLASS);
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS == null) BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = buildStainedGlass(BlockHardLightBlueStainedGlassBehavior.class, BlockId.HARD_LIGHT_BLUE_STAINED_GLASS);
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS == null) BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = buildStainedGlass(BlockHardLightGrayStainedGlassBehavior.class, BlockId.HARD_LIGHT_GRAY_STAINED_GLASS);
        if (BlockTypes.HARD_LIME_STAINED_GLASS == null) BlockTypes.HARD_LIME_STAINED_GLASS = buildStainedGlass(BlockHardLimeStainedGlassBehavior.class, BlockId.HARD_LIME_STAINED_GLASS);
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS == null) BlockTypes.HARD_MAGENTA_STAINED_GLASS = buildStainedGlass(BlockHardMagentaStainedGlassBehavior.class, BlockId.HARD_MAGENTA_STAINED_GLASS);
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS == null) BlockTypes.HARD_ORANGE_STAINED_GLASS = buildStainedGlass(BlockHardOrangeStainedGlassBehavior.class, BlockId.HARD_ORANGE_STAINED_GLASS);
        if (BlockTypes.HARD_PINK_STAINED_GLASS == null) BlockTypes.HARD_PINK_STAINED_GLASS = buildStainedGlass(BlockHardPinkStainedGlassBehavior.class, BlockId.HARD_PINK_STAINED_GLASS);
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS == null) BlockTypes.HARD_PURPLE_STAINED_GLASS = buildStainedGlass(BlockHardPurpleStainedGlassBehavior.class, BlockId.HARD_PURPLE_STAINED_GLASS);
        if (BlockTypes.HARD_RED_STAINED_GLASS == null) BlockTypes.HARD_RED_STAINED_GLASS = buildStainedGlass(BlockHardRedStainedGlassBehavior.class, BlockId.HARD_RED_STAINED_GLASS);
        if (BlockTypes.HARD_WHITE_STAINED_GLASS == null) BlockTypes.HARD_WHITE_STAINED_GLASS = buildStainedGlass(BlockHardWhiteStainedGlassBehavior.class, BlockId.HARD_WHITE_STAINED_GLASS);
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS == null) BlockTypes.HARD_YELLOW_STAINED_GLASS = buildStainedGlass(BlockHardYellowStainedGlassBehavior.class, BlockId.HARD_YELLOW_STAINED_GLASS);
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS == null) BlockTypes.LIGHT_BLUE_STAINED_GLASS = buildStainedGlass(BlockLightBlueStainedGlassBehavior.class, BlockId.LIGHT_BLUE_STAINED_GLASS);
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS == null) BlockTypes.LIGHT_GRAY_STAINED_GLASS = buildStainedGlass(BlockLightGrayStainedGlassBehavior.class, BlockId.LIGHT_GRAY_STAINED_GLASS);
        if (BlockTypes.LIME_STAINED_GLASS == null) BlockTypes.LIME_STAINED_GLASS = buildStainedGlass(BlockLimeStainedGlassBehavior.class, BlockId.LIME_STAINED_GLASS);
        if (BlockTypes.MAGENTA_STAINED_GLASS == null) BlockTypes.MAGENTA_STAINED_GLASS = buildStainedGlass(BlockMagentaStainedGlassBehavior.class, BlockId.MAGENTA_STAINED_GLASS);
        if (BlockTypes.ORANGE_STAINED_GLASS == null) BlockTypes.ORANGE_STAINED_GLASS = buildStainedGlass(BlockOrangeStainedGlassBehavior.class, BlockId.ORANGE_STAINED_GLASS);
        if (BlockTypes.PINK_STAINED_GLASS == null) BlockTypes.PINK_STAINED_GLASS = buildStainedGlass(BlockPinkStainedGlassBehavior.class, BlockId.PINK_STAINED_GLASS);
        if (BlockTypes.PURPLE_STAINED_GLASS == null) BlockTypes.PURPLE_STAINED_GLASS = buildStainedGlass(BlockPurpleStainedGlassBehavior.class, BlockId.PURPLE_STAINED_GLASS);
        if (BlockTypes.RED_STAINED_GLASS == null) BlockTypes.RED_STAINED_GLASS = buildStainedGlass(BlockRedStainedGlassBehavior.class, BlockId.RED_STAINED_GLASS);
        if (BlockTypes.WHITE_STAINED_GLASS == null) BlockTypes.WHITE_STAINED_GLASS = buildStainedGlass(BlockWhiteStainedGlassBehavior.class, BlockId.WHITE_STAINED_GLASS);
        if (BlockTypes.YELLOW_STAINED_GLASS == null) BlockTypes.YELLOW_STAINED_GLASS = buildStainedGlass(BlockYellowStainedGlassBehavior.class, BlockId.YELLOW_STAINED_GLASS);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStainedGlass(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initStainedGlassPanes() {
        if (BlockTypes.BLACK_STAINED_GLASS_PANE == null) BlockTypes.BLACK_STAINED_GLASS_PANE = buildStainedGlassPane(BlockBlackStainedGlassPaneBehavior.class, BlockId.BLACK_STAINED_GLASS_PANE);
        if (BlockTypes.BLUE_STAINED_GLASS_PANE == null) BlockTypes.BLUE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockBlueStainedGlassPaneBehavior.class, BlockId.BLUE_STAINED_GLASS_PANE);
        if (BlockTypes.BROWN_STAINED_GLASS_PANE == null) BlockTypes.BROWN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockBrownStainedGlassPaneBehavior.class, BlockId.BROWN_STAINED_GLASS_PANE);
        if (BlockTypes.CYAN_STAINED_GLASS_PANE == null) BlockTypes.CYAN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockCyanStainedGlassPaneBehavior.class, BlockId.CYAN_STAINED_GLASS_PANE);
        if (BlockTypes.GRAY_STAINED_GLASS_PANE == null) BlockTypes.GRAY_STAINED_GLASS_PANE = buildStainedGlassPane(BlockGrayStainedGlassPaneBehavior.class, BlockId.GRAY_STAINED_GLASS_PANE);
        if (BlockTypes.GREEN_STAINED_GLASS_PANE == null) BlockTypes.GREEN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockGreenStainedGlassPaneBehavior.class, BlockId.GREEN_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE == null) BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardBlackStainedGlassPaneBehavior.class, BlockId.HARD_BLACK_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE == null) BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardBlueStainedGlassPaneBehavior.class, BlockId.HARD_BLUE_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE == null) BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardBrownStainedGlassPaneBehavior.class, BlockId.HARD_BROWN_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE == null) BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardCyanStainedGlassPaneBehavior.class, BlockId.HARD_CYAN_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE == null) BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardGrayStainedGlassPaneBehavior.class, BlockId.HARD_GRAY_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE == null) BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardGreenStainedGlassPaneBehavior.class, BlockId.HARD_GREEN_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE == null) BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardLightBlueStainedGlassPaneBehavior.class, BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE == null) BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardLightGrayStainedGlassPaneBehavior.class, BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE == null) BlockTypes.HARD_LIME_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardLimeStainedGlassPaneBehavior.class, BlockId.HARD_LIME_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE == null) BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardMagentaStainedGlassPaneBehavior.class, BlockId.HARD_MAGENTA_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE == null) BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardOrangeStainedGlassPaneBehavior.class, BlockId.HARD_ORANGE_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE == null) BlockTypes.HARD_PINK_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardPinkStainedGlassPaneBehavior.class, BlockId.HARD_PINK_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE == null) BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardPurpleStainedGlassPaneBehavior.class, BlockId.HARD_PURPLE_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_RED_STAINED_GLASS_PANE == null) BlockTypes.HARD_RED_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardRedStainedGlassPaneBehavior.class, BlockId.HARD_RED_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE == null) BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardWhiteStainedGlassPaneBehavior.class, BlockId.HARD_WHITE_STAINED_GLASS_PANE);
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE == null) BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = buildStainedGlassPane(BlockHardYellowStainedGlassPaneBehavior.class, BlockId.HARD_YELLOW_STAINED_GLASS_PANE);
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE == null) BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockLightBlueStainedGlassPaneBehavior.class, BlockId.LIGHT_BLUE_STAINED_GLASS_PANE);
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE == null) BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = buildStainedGlassPane(BlockLightGrayStainedGlassPaneBehavior.class, BlockId.LIGHT_GRAY_STAINED_GLASS_PANE);
        if (BlockTypes.LIME_STAINED_GLASS_PANE == null) BlockTypes.LIME_STAINED_GLASS_PANE = buildStainedGlassPane(BlockLimeStainedGlassPaneBehavior.class, BlockId.LIME_STAINED_GLASS_PANE);
        if (BlockTypes.MAGENTA_STAINED_GLASS_PANE == null) BlockTypes.MAGENTA_STAINED_GLASS_PANE = buildStainedGlassPane(BlockMagentaStainedGlassPaneBehavior.class, BlockId.MAGENTA_STAINED_GLASS_PANE);
        if (BlockTypes.ORANGE_STAINED_GLASS_PANE == null) BlockTypes.ORANGE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockOrangeStainedGlassPaneBehavior.class, BlockId.ORANGE_STAINED_GLASS_PANE);
        if (BlockTypes.PINK_STAINED_GLASS_PANE == null) BlockTypes.PINK_STAINED_GLASS_PANE = buildStainedGlassPane(BlockPinkStainedGlassPaneBehavior.class, BlockId.PINK_STAINED_GLASS_PANE);
        if (BlockTypes.PURPLE_STAINED_GLASS_PANE == null) BlockTypes.PURPLE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockPurpleStainedGlassPaneBehavior.class, BlockId.PURPLE_STAINED_GLASS_PANE);
        if (BlockTypes.RED_STAINED_GLASS_PANE == null) BlockTypes.RED_STAINED_GLASS_PANE = buildStainedGlassPane(BlockRedStainedGlassPaneBehavior.class, BlockId.RED_STAINED_GLASS_PANE);
        if (BlockTypes.WHITE_STAINED_GLASS_PANE == null) BlockTypes.WHITE_STAINED_GLASS_PANE = buildStainedGlassPane(BlockWhiteStainedGlassPaneBehavior.class, BlockId.WHITE_STAINED_GLASS_PANE);
        if (BlockTypes.YELLOW_STAINED_GLASS_PANE == null) BlockTypes.YELLOW_STAINED_GLASS_PANE = buildStainedGlassPane(BlockYellowStainedGlassPaneBehavior.class, BlockId.YELLOW_STAINED_GLASS_PANE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStainedGlassPane(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initElements() {
        if (BlockTypes.ELEMENT_0 == null) BlockTypes.ELEMENT_0 = buildElement(BlockElement0Behavior.class, BlockId.ELEMENT_0);
        if (BlockTypes.ELEMENT_1 == null) BlockTypes.ELEMENT_1 = buildElement(BlockElement1Behavior.class, BlockId.ELEMENT_1);
        if (BlockTypes.ELEMENT_10 == null) BlockTypes.ELEMENT_10 = buildElement(BlockElement10Behavior.class, BlockId.ELEMENT_10);
        if (BlockTypes.ELEMENT_100 == null) BlockTypes.ELEMENT_100 = buildElement(BlockElement100Behavior.class, BlockId.ELEMENT_100);
        if (BlockTypes.ELEMENT_101 == null) BlockTypes.ELEMENT_101 = buildElement(BlockElement101Behavior.class, BlockId.ELEMENT_101);
        if (BlockTypes.ELEMENT_102 == null) BlockTypes.ELEMENT_102 = buildElement(BlockElement102Behavior.class, BlockId.ELEMENT_102);
        if (BlockTypes.ELEMENT_103 == null) BlockTypes.ELEMENT_103 = buildElement(BlockElement103Behavior.class, BlockId.ELEMENT_103);
        if (BlockTypes.ELEMENT_104 == null) BlockTypes.ELEMENT_104 = buildElement(BlockElement104Behavior.class, BlockId.ELEMENT_104);
        if (BlockTypes.ELEMENT_105 == null) BlockTypes.ELEMENT_105 = buildElement(BlockElement105Behavior.class, BlockId.ELEMENT_105);
        if (BlockTypes.ELEMENT_106 == null) BlockTypes.ELEMENT_106 = buildElement(BlockElement106Behavior.class, BlockId.ELEMENT_106);
        if (BlockTypes.ELEMENT_107 == null) BlockTypes.ELEMENT_107 = buildElement(BlockElement107Behavior.class, BlockId.ELEMENT_107);
        if (BlockTypes.ELEMENT_108 == null) BlockTypes.ELEMENT_108 = buildElement(BlockElement108Behavior.class, BlockId.ELEMENT_108);
        if (BlockTypes.ELEMENT_109 == null) BlockTypes.ELEMENT_109 = buildElement(BlockElement109Behavior.class, BlockId.ELEMENT_109);
        if (BlockTypes.ELEMENT_11 == null) BlockTypes.ELEMENT_11 = buildElement(BlockElement11Behavior.class, BlockId.ELEMENT_11);
        if (BlockTypes.ELEMENT_110 == null) BlockTypes.ELEMENT_110 = buildElement(BlockElement110Behavior.class, BlockId.ELEMENT_110);
        if (BlockTypes.ELEMENT_111 == null) BlockTypes.ELEMENT_111 = buildElement(BlockElement111Behavior.class, BlockId.ELEMENT_111);
        if (BlockTypes.ELEMENT_112 == null) BlockTypes.ELEMENT_112 = buildElement(BlockElement112Behavior.class, BlockId.ELEMENT_112);
        if (BlockTypes.ELEMENT_113 == null) BlockTypes.ELEMENT_113 = buildElement(BlockElement113Behavior.class, BlockId.ELEMENT_113);
        if (BlockTypes.ELEMENT_114 == null) BlockTypes.ELEMENT_114 = buildElement(BlockElement114Behavior.class, BlockId.ELEMENT_114);
        if (BlockTypes.ELEMENT_115 == null) BlockTypes.ELEMENT_115 = buildElement(BlockElement115Behavior.class, BlockId.ELEMENT_115);
        if (BlockTypes.ELEMENT_116 == null) BlockTypes.ELEMENT_116 = buildElement(BlockElement116Behavior.class, BlockId.ELEMENT_116);
        if (BlockTypes.ELEMENT_117 == null) BlockTypes.ELEMENT_117 = buildElement(BlockElement117Behavior.class, BlockId.ELEMENT_117);
        if (BlockTypes.ELEMENT_118 == null) BlockTypes.ELEMENT_118 = buildElement(BlockElement118Behavior.class, BlockId.ELEMENT_118);
        if (BlockTypes.ELEMENT_12 == null) BlockTypes.ELEMENT_12 = buildElement(BlockElement12Behavior.class, BlockId.ELEMENT_12);
        if (BlockTypes.ELEMENT_13 == null) BlockTypes.ELEMENT_13 = buildElement(BlockElement13Behavior.class, BlockId.ELEMENT_13);
        if (BlockTypes.ELEMENT_14 == null) BlockTypes.ELEMENT_14 = buildElement(BlockElement14Behavior.class, BlockId.ELEMENT_14);
        if (BlockTypes.ELEMENT_15 == null) BlockTypes.ELEMENT_15 = buildElement(BlockElement15Behavior.class, BlockId.ELEMENT_15);
        if (BlockTypes.ELEMENT_16 == null) BlockTypes.ELEMENT_16 = buildElement(BlockElement16Behavior.class, BlockId.ELEMENT_16);
        if (BlockTypes.ELEMENT_17 == null) BlockTypes.ELEMENT_17 = buildElement(BlockElement17Behavior.class, BlockId.ELEMENT_17);
        if (BlockTypes.ELEMENT_18 == null) BlockTypes.ELEMENT_18 = buildElement(BlockElement18Behavior.class, BlockId.ELEMENT_18);
        if (BlockTypes.ELEMENT_19 == null) BlockTypes.ELEMENT_19 = buildElement(BlockElement19Behavior.class, BlockId.ELEMENT_19);
        if (BlockTypes.ELEMENT_2 == null) BlockTypes.ELEMENT_2 = buildElement(BlockElement2Behavior.class, BlockId.ELEMENT_2);
        if (BlockTypes.ELEMENT_20 == null) BlockTypes.ELEMENT_20 = buildElement(BlockElement20Behavior.class, BlockId.ELEMENT_20);
        if (BlockTypes.ELEMENT_21 == null) BlockTypes.ELEMENT_21 = buildElement(BlockElement21Behavior.class, BlockId.ELEMENT_21);
        if (BlockTypes.ELEMENT_22 == null) BlockTypes.ELEMENT_22 = buildElement(BlockElement22Behavior.class, BlockId.ELEMENT_22);
        if (BlockTypes.ELEMENT_23 == null) BlockTypes.ELEMENT_23 = buildElement(BlockElement23Behavior.class, BlockId.ELEMENT_23);
        if (BlockTypes.ELEMENT_24 == null) BlockTypes.ELEMENT_24 = buildElement(BlockElement24Behavior.class, BlockId.ELEMENT_24);
        if (BlockTypes.ELEMENT_25 == null) BlockTypes.ELEMENT_25 = buildElement(BlockElement25Behavior.class, BlockId.ELEMENT_25);
        if (BlockTypes.ELEMENT_26 == null) BlockTypes.ELEMENT_26 = buildElement(BlockElement26Behavior.class, BlockId.ELEMENT_26);
        if (BlockTypes.ELEMENT_27 == null) BlockTypes.ELEMENT_27 = buildElement(BlockElement27Behavior.class, BlockId.ELEMENT_27);
        if (BlockTypes.ELEMENT_28 == null) BlockTypes.ELEMENT_28 = buildElement(BlockElement28Behavior.class, BlockId.ELEMENT_28);
        if (BlockTypes.ELEMENT_29 == null) BlockTypes.ELEMENT_29 = buildElement(BlockElement29Behavior.class, BlockId.ELEMENT_29);
        if (BlockTypes.ELEMENT_3 == null) BlockTypes.ELEMENT_3 = buildElement(BlockElement3Behavior.class, BlockId.ELEMENT_3);
        if (BlockTypes.ELEMENT_30 == null) BlockTypes.ELEMENT_30 = buildElement(BlockElement30Behavior.class, BlockId.ELEMENT_30);
        if (BlockTypes.ELEMENT_31 == null) BlockTypes.ELEMENT_31 = buildElement(BlockElement31Behavior.class, BlockId.ELEMENT_31);
        if (BlockTypes.ELEMENT_32 == null) BlockTypes.ELEMENT_32 = buildElement(BlockElement32Behavior.class, BlockId.ELEMENT_32);
        if (BlockTypes.ELEMENT_33 == null) BlockTypes.ELEMENT_33 = buildElement(BlockElement33Behavior.class, BlockId.ELEMENT_33);
        if (BlockTypes.ELEMENT_34 == null) BlockTypes.ELEMENT_34 = buildElement(BlockElement34Behavior.class, BlockId.ELEMENT_34);
        if (BlockTypes.ELEMENT_35 == null) BlockTypes.ELEMENT_35 = buildElement(BlockElement35Behavior.class, BlockId.ELEMENT_35);
        if (BlockTypes.ELEMENT_36 == null) BlockTypes.ELEMENT_36 = buildElement(BlockElement36Behavior.class, BlockId.ELEMENT_36);
        if (BlockTypes.ELEMENT_37 == null) BlockTypes.ELEMENT_37 = buildElement(BlockElement37Behavior.class, BlockId.ELEMENT_37);
        if (BlockTypes.ELEMENT_38 == null) BlockTypes.ELEMENT_38 = buildElement(BlockElement38Behavior.class, BlockId.ELEMENT_38);
        if (BlockTypes.ELEMENT_39 == null) BlockTypes.ELEMENT_39 = buildElement(BlockElement39Behavior.class, BlockId.ELEMENT_39);
        if (BlockTypes.ELEMENT_4 == null) BlockTypes.ELEMENT_4 = buildElement(BlockElement4Behavior.class, BlockId.ELEMENT_4);
        if (BlockTypes.ELEMENT_40 == null) BlockTypes.ELEMENT_40 = buildElement(BlockElement40Behavior.class, BlockId.ELEMENT_40);
        if (BlockTypes.ELEMENT_41 == null) BlockTypes.ELEMENT_41 = buildElement(BlockElement41Behavior.class, BlockId.ELEMENT_41);
        if (BlockTypes.ELEMENT_42 == null) BlockTypes.ELEMENT_42 = buildElement(BlockElement42Behavior.class, BlockId.ELEMENT_42);
        if (BlockTypes.ELEMENT_43 == null) BlockTypes.ELEMENT_43 = buildElement(BlockElement43Behavior.class, BlockId.ELEMENT_43);
        if (BlockTypes.ELEMENT_44 == null) BlockTypes.ELEMENT_44 = buildElement(BlockElement44Behavior.class, BlockId.ELEMENT_44);
        if (BlockTypes.ELEMENT_45 == null) BlockTypes.ELEMENT_45 = buildElement(BlockElement45Behavior.class, BlockId.ELEMENT_45);
        if (BlockTypes.ELEMENT_46 == null) BlockTypes.ELEMENT_46 = buildElement(BlockElement46Behavior.class, BlockId.ELEMENT_46);
        if (BlockTypes.ELEMENT_47 == null) BlockTypes.ELEMENT_47 = buildElement(BlockElement47Behavior.class, BlockId.ELEMENT_47);
        if (BlockTypes.ELEMENT_48 == null) BlockTypes.ELEMENT_48 = buildElement(BlockElement48Behavior.class, BlockId.ELEMENT_48);
        if (BlockTypes.ELEMENT_49 == null) BlockTypes.ELEMENT_49 = buildElement(BlockElement49Behavior.class, BlockId.ELEMENT_49);
        if (BlockTypes.ELEMENT_5 == null) BlockTypes.ELEMENT_5 = buildElement(BlockElement5Behavior.class, BlockId.ELEMENT_5);
        if (BlockTypes.ELEMENT_50 == null) BlockTypes.ELEMENT_50 = buildElement(BlockElement50Behavior.class, BlockId.ELEMENT_50);
        if (BlockTypes.ELEMENT_51 == null) BlockTypes.ELEMENT_51 = buildElement(BlockElement51Behavior.class, BlockId.ELEMENT_51);
        if (BlockTypes.ELEMENT_52 == null) BlockTypes.ELEMENT_52 = buildElement(BlockElement52Behavior.class, BlockId.ELEMENT_52);
        if (BlockTypes.ELEMENT_53 == null) BlockTypes.ELEMENT_53 = buildElement(BlockElement53Behavior.class, BlockId.ELEMENT_53);
        if (BlockTypes.ELEMENT_54 == null) BlockTypes.ELEMENT_54 = buildElement(BlockElement54Behavior.class, BlockId.ELEMENT_54);
        if (BlockTypes.ELEMENT_55 == null) BlockTypes.ELEMENT_55 = buildElement(BlockElement55Behavior.class, BlockId.ELEMENT_55);
        if (BlockTypes.ELEMENT_56 == null) BlockTypes.ELEMENT_56 = buildElement(BlockElement56Behavior.class, BlockId.ELEMENT_56);
        if (BlockTypes.ELEMENT_57 == null) BlockTypes.ELEMENT_57 = buildElement(BlockElement57Behavior.class, BlockId.ELEMENT_57);
        if (BlockTypes.ELEMENT_58 == null) BlockTypes.ELEMENT_58 = buildElement(BlockElement58Behavior.class, BlockId.ELEMENT_58);
        if (BlockTypes.ELEMENT_59 == null) BlockTypes.ELEMENT_59 = buildElement(BlockElement59Behavior.class, BlockId.ELEMENT_59);
        if (BlockTypes.ELEMENT_6 == null) BlockTypes.ELEMENT_6 = buildElement(BlockElement6Behavior.class, BlockId.ELEMENT_6);
        if (BlockTypes.ELEMENT_60 == null) BlockTypes.ELEMENT_60 = buildElement(BlockElement60Behavior.class, BlockId.ELEMENT_60);
        if (BlockTypes.ELEMENT_61 == null) BlockTypes.ELEMENT_61 = buildElement(BlockElement61Behavior.class, BlockId.ELEMENT_61);
        if (BlockTypes.ELEMENT_62 == null) BlockTypes.ELEMENT_62 = buildElement(BlockElement62Behavior.class, BlockId.ELEMENT_62);
        if (BlockTypes.ELEMENT_63 == null) BlockTypes.ELEMENT_63 = buildElement(BlockElement63Behavior.class, BlockId.ELEMENT_63);
        if (BlockTypes.ELEMENT_64 == null) BlockTypes.ELEMENT_64 = buildElement(BlockElement64Behavior.class, BlockId.ELEMENT_64);
        if (BlockTypes.ELEMENT_65 == null) BlockTypes.ELEMENT_65 = buildElement(BlockElement65Behavior.class, BlockId.ELEMENT_65);
        if (BlockTypes.ELEMENT_66 == null) BlockTypes.ELEMENT_66 = buildElement(BlockElement66Behavior.class, BlockId.ELEMENT_66);
        if (BlockTypes.ELEMENT_67 == null) BlockTypes.ELEMENT_67 = buildElement(BlockElement67Behavior.class, BlockId.ELEMENT_67);
        if (BlockTypes.ELEMENT_68 == null) BlockTypes.ELEMENT_68 = buildElement(BlockElement68Behavior.class, BlockId.ELEMENT_68);
        if (BlockTypes.ELEMENT_69 == null) BlockTypes.ELEMENT_69 = buildElement(BlockElement69Behavior.class, BlockId.ELEMENT_69);
        if (BlockTypes.ELEMENT_7 == null) BlockTypes.ELEMENT_7 = buildElement(BlockElement7Behavior.class, BlockId.ELEMENT_7);
        if (BlockTypes.ELEMENT_70 == null) BlockTypes.ELEMENT_70 = buildElement(BlockElement70Behavior.class, BlockId.ELEMENT_70);
        if (BlockTypes.ELEMENT_71 == null) BlockTypes.ELEMENT_71 = buildElement(BlockElement71Behavior.class, BlockId.ELEMENT_71);
        if (BlockTypes.ELEMENT_72 == null) BlockTypes.ELEMENT_72 = buildElement(BlockElement72Behavior.class, BlockId.ELEMENT_72);
        if (BlockTypes.ELEMENT_73 == null) BlockTypes.ELEMENT_73 = buildElement(BlockElement73Behavior.class, BlockId.ELEMENT_73);
        if (BlockTypes.ELEMENT_74 == null) BlockTypes.ELEMENT_74 = buildElement(BlockElement74Behavior.class, BlockId.ELEMENT_74);
        if (BlockTypes.ELEMENT_75 == null) BlockTypes.ELEMENT_75 = buildElement(BlockElement75Behavior.class, BlockId.ELEMENT_75);
        if (BlockTypes.ELEMENT_76 == null) BlockTypes.ELEMENT_76 = buildElement(BlockElement76Behavior.class, BlockId.ELEMENT_76);
        if (BlockTypes.ELEMENT_77 == null) BlockTypes.ELEMENT_77 = buildElement(BlockElement77Behavior.class, BlockId.ELEMENT_77);
        if (BlockTypes.ELEMENT_78 == null) BlockTypes.ELEMENT_78 = buildElement(BlockElement78Behavior.class, BlockId.ELEMENT_78);
        if (BlockTypes.ELEMENT_79 == null) BlockTypes.ELEMENT_79 = buildElement(BlockElement79Behavior.class, BlockId.ELEMENT_79);
        if (BlockTypes.ELEMENT_8 == null) BlockTypes.ELEMENT_8 = buildElement(BlockElement8Behavior.class, BlockId.ELEMENT_8);
        if (BlockTypes.ELEMENT_80 == null) BlockTypes.ELEMENT_80 = buildElement(BlockElement80Behavior.class, BlockId.ELEMENT_80);
        if (BlockTypes.ELEMENT_81 == null) BlockTypes.ELEMENT_81 = buildElement(BlockElement81Behavior.class, BlockId.ELEMENT_81);
        if (BlockTypes.ELEMENT_82 == null) BlockTypes.ELEMENT_82 = buildElement(BlockElement82Behavior.class, BlockId.ELEMENT_82);
        if (BlockTypes.ELEMENT_83 == null) BlockTypes.ELEMENT_83 = buildElement(BlockElement83Behavior.class, BlockId.ELEMENT_83);
        if (BlockTypes.ELEMENT_84 == null) BlockTypes.ELEMENT_84 = buildElement(BlockElement84Behavior.class, BlockId.ELEMENT_84);
        if (BlockTypes.ELEMENT_85 == null) BlockTypes.ELEMENT_85 = buildElement(BlockElement85Behavior.class, BlockId.ELEMENT_85);
        if (BlockTypes.ELEMENT_86 == null) BlockTypes.ELEMENT_86 = buildElement(BlockElement86Behavior.class, BlockId.ELEMENT_86);
        if (BlockTypes.ELEMENT_87 == null) BlockTypes.ELEMENT_87 = buildElement(BlockElement87Behavior.class, BlockId.ELEMENT_87);
        if (BlockTypes.ELEMENT_88 == null) BlockTypes.ELEMENT_88 = buildElement(BlockElement88Behavior.class, BlockId.ELEMENT_88);
        if (BlockTypes.ELEMENT_89 == null) BlockTypes.ELEMENT_89 = buildElement(BlockElement89Behavior.class, BlockId.ELEMENT_89);
        if (BlockTypes.ELEMENT_9 == null) BlockTypes.ELEMENT_9 = buildElement(BlockElement9Behavior.class, BlockId.ELEMENT_9);
        if (BlockTypes.ELEMENT_90 == null) BlockTypes.ELEMENT_90 = buildElement(BlockElement90Behavior.class, BlockId.ELEMENT_90);
        if (BlockTypes.ELEMENT_91 == null) BlockTypes.ELEMENT_91 = buildElement(BlockElement91Behavior.class, BlockId.ELEMENT_91);
        if (BlockTypes.ELEMENT_92 == null) BlockTypes.ELEMENT_92 = buildElement(BlockElement92Behavior.class, BlockId.ELEMENT_92);
        if (BlockTypes.ELEMENT_93 == null) BlockTypes.ELEMENT_93 = buildElement(BlockElement93Behavior.class, BlockId.ELEMENT_93);
        if (BlockTypes.ELEMENT_94 == null) BlockTypes.ELEMENT_94 = buildElement(BlockElement94Behavior.class, BlockId.ELEMENT_94);
        if (BlockTypes.ELEMENT_95 == null) BlockTypes.ELEMENT_95 = buildElement(BlockElement95Behavior.class, BlockId.ELEMENT_95);
        if (BlockTypes.ELEMENT_96 == null) BlockTypes.ELEMENT_96 = buildElement(BlockElement96Behavior.class, BlockId.ELEMENT_96);
        if (BlockTypes.ELEMENT_97 == null) BlockTypes.ELEMENT_97 = buildElement(BlockElement97Behavior.class, BlockId.ELEMENT_97);
        if (BlockTypes.ELEMENT_98 == null) BlockTypes.ELEMENT_98 = buildElement(BlockElement98Behavior.class, BlockId.ELEMENT_98);
        if (BlockTypes.ELEMENT_99 == null) BlockTypes.ELEMENT_99 = buildElement(BlockElement99Behavior.class, BlockId.ELEMENT_99);
    }

    private static <T extends BlockBehavior> BlockType<T> buildElement(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initPressurePlates() {
        if (BlockTypes.ACACIA_PRESSURE_PLATE == null) BlockTypes.ACACIA_PRESSURE_PLATE = buildPressurePlate(BlockAcaciaPressurePlateBehavior.class, BlockId.ACACIA_PRESSURE_PLATE);
        if (BlockTypes.BAMBOO_PRESSURE_PLATE == null) BlockTypes.BAMBOO_PRESSURE_PLATE = buildPressurePlate(BlockBambooPressurePlateBehavior.class, BlockId.BAMBOO_PRESSURE_PLATE);
        if (BlockTypes.BIRCH_PRESSURE_PLATE == null) BlockTypes.BIRCH_PRESSURE_PLATE = buildPressurePlate(BlockBirchPressurePlateBehavior.class, BlockId.BIRCH_PRESSURE_PLATE);
        if (BlockTypes.CHERRY_PRESSURE_PLATE == null) BlockTypes.CHERRY_PRESSURE_PLATE = buildPressurePlate(BlockCherryPressurePlateBehavior.class, BlockId.CHERRY_PRESSURE_PLATE);
        if (BlockTypes.CRIMSON_PRESSURE_PLATE == null) BlockTypes.CRIMSON_PRESSURE_PLATE = buildPressurePlate(BlockCrimsonPressurePlateBehavior.class, BlockId.CRIMSON_PRESSURE_PLATE);
        if (BlockTypes.DARK_OAK_PRESSURE_PLATE == null) BlockTypes.DARK_OAK_PRESSURE_PLATE = buildPressurePlate(BlockDarkOakPressurePlateBehavior.class, BlockId.DARK_OAK_PRESSURE_PLATE);
        if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE == null) BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = buildPressurePlate(BlockHeavyWeightedPressurePlateBehavior.class, BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE);
        if (BlockTypes.JUNGLE_PRESSURE_PLATE == null) BlockTypes.JUNGLE_PRESSURE_PLATE = buildPressurePlate(BlockJunglePressurePlateBehavior.class, BlockId.JUNGLE_PRESSURE_PLATE);
        if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE == null) BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = buildPressurePlate(BlockLightWeightedPressurePlateBehavior.class, BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE);
        if (BlockTypes.MANGROVE_PRESSURE_PLATE == null) BlockTypes.MANGROVE_PRESSURE_PLATE = buildPressurePlate(BlockMangrovePressurePlateBehavior.class, BlockId.MANGROVE_PRESSURE_PLATE);
        if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE == null) BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = buildPressurePlate(BlockPolishedBlackstonePressurePlateBehavior.class, BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE);
        if (BlockTypes.SPRUCE_PRESSURE_PLATE == null) BlockTypes.SPRUCE_PRESSURE_PLATE = buildPressurePlate(BlockSprucePressurePlateBehavior.class, BlockId.SPRUCE_PRESSURE_PLATE);
        if (BlockTypes.STONE_PRESSURE_PLATE == null) BlockTypes.STONE_PRESSURE_PLATE = buildPressurePlate(BlockStonePressurePlateBehavior.class, BlockId.STONE_PRESSURE_PLATE);
        if (BlockTypes.WARPED_PRESSURE_PLATE == null) BlockTypes.WARPED_PRESSURE_PLATE = buildPressurePlate(BlockWarpedPressurePlateBehavior.class, BlockId.WARPED_PRESSURE_PLATE);
        if (BlockTypes.WOODEN_PRESSURE_PLATE == null) BlockTypes.WOODEN_PRESSURE_PLATE = buildPressurePlate(BlockWoodenPressurePlateBehavior.class, BlockId.WOODEN_PRESSURE_PLATE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildPressurePlate(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
               .build();
    }

    public static void initCarpets() {
        if (BlockTypes.BLACK_CARPET == null) BlockTypes.BLACK_CARPET = buildCarpet(BlockBlackCarpetBehavior.class, BlockId.BLACK_CARPET);
        if (BlockTypes.BLUE_CARPET == null) BlockTypes.BLUE_CARPET = buildCarpet(BlockBlueCarpetBehavior.class, BlockId.BLUE_CARPET);
        if (BlockTypes.BROWN_CARPET == null) BlockTypes.BROWN_CARPET = buildCarpet(BlockBrownCarpetBehavior.class, BlockId.BROWN_CARPET);
        if (BlockTypes.CYAN_CARPET == null) BlockTypes.CYAN_CARPET = buildCarpet(BlockCyanCarpetBehavior.class, BlockId.CYAN_CARPET);
        if (BlockTypes.GRAY_CARPET == null) BlockTypes.GRAY_CARPET = buildCarpet(BlockGrayCarpetBehavior.class, BlockId.GRAY_CARPET);
        if (BlockTypes.GREEN_CARPET == null) BlockTypes.GREEN_CARPET = buildCarpet(BlockGreenCarpetBehavior.class, BlockId.GREEN_CARPET);
        if (BlockTypes.LIGHT_BLUE_CARPET == null) BlockTypes.LIGHT_BLUE_CARPET = buildCarpet(BlockLightBlueCarpetBehavior.class, BlockId.LIGHT_BLUE_CARPET);
        if (BlockTypes.LIGHT_GRAY_CARPET == null) BlockTypes.LIGHT_GRAY_CARPET = buildCarpet(BlockLightGrayCarpetBehavior.class, BlockId.LIGHT_GRAY_CARPET);
        if (BlockTypes.LIME_CARPET == null) BlockTypes.LIME_CARPET = buildCarpet(BlockLimeCarpetBehavior.class, BlockId.LIME_CARPET);
        if (BlockTypes.MAGENTA_CARPET == null) BlockTypes.MAGENTA_CARPET = buildCarpet(BlockMagentaCarpetBehavior.class, BlockId.MAGENTA_CARPET);
        if (BlockTypes.MOSS_CARPET == null) BlockTypes.MOSS_CARPET = buildCarpet(BlockMossCarpetBehavior.class, BlockId.MOSS_CARPET);
        if (BlockTypes.ORANGE_CARPET == null) BlockTypes.ORANGE_CARPET = buildCarpet(BlockOrangeCarpetBehavior.class, BlockId.ORANGE_CARPET);
        if (BlockTypes.PINK_CARPET == null) BlockTypes.PINK_CARPET = buildCarpet(BlockPinkCarpetBehavior.class, BlockId.PINK_CARPET);
        if (BlockTypes.PURPLE_CARPET == null) BlockTypes.PURPLE_CARPET = buildCarpet(BlockPurpleCarpetBehavior.class, BlockId.PURPLE_CARPET);
        if (BlockTypes.RED_CARPET == null) BlockTypes.RED_CARPET = buildCarpet(BlockRedCarpetBehavior.class, BlockId.RED_CARPET);
        if (BlockTypes.WHITE_CARPET == null) BlockTypes.WHITE_CARPET = buildCarpet(BlockWhiteCarpetBehavior.class, BlockId.WHITE_CARPET);
        if (BlockTypes.YELLOW_CARPET == null) BlockTypes.YELLOW_CARPET = buildCarpet(BlockYellowCarpetBehavior.class, BlockId.YELLOW_CARPET);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCarpet(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initCutCoppers() {
        if (BlockTypes.EXPOSED_CUT_COPPER == null) BlockTypes.EXPOSED_CUT_COPPER = buildCutCopper(BlockExposedCutCopperBehavior.class, BlockId.EXPOSED_CUT_COPPER);
        if (BlockTypes.OXIDIZED_CUT_COPPER == null) BlockTypes.OXIDIZED_CUT_COPPER = buildCutCopper(BlockOxidizedCutCopperBehavior.class, BlockId.OXIDIZED_CUT_COPPER);
        if (BlockTypes.WAXED_CUT_COPPER == null) BlockTypes.WAXED_CUT_COPPER = buildCutCopper(BlockWaxedCutCopperBehavior.class, BlockId.WAXED_CUT_COPPER);
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER == null) BlockTypes.WAXED_EXPOSED_CUT_COPPER = buildCutCopper(BlockWaxedExposedCutCopperBehavior.class, BlockId.WAXED_EXPOSED_CUT_COPPER);
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER == null) BlockTypes.WAXED_OXIDIZED_CUT_COPPER = buildCutCopper(BlockWaxedOxidizedCutCopperBehavior.class, BlockId.WAXED_OXIDIZED_CUT_COPPER);
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER == null) BlockTypes.WAXED_WEATHERED_CUT_COPPER = buildCutCopper(BlockWaxedWeatheredCutCopperBehavior.class, BlockId.WAXED_WEATHERED_CUT_COPPER);
        if (BlockTypes.WEATHERED_CUT_COPPER == null) BlockTypes.WEATHERED_CUT_COPPER = buildCutCopper(BlockWeatheredCutCopperBehavior.class, BlockId.WEATHERED_CUT_COPPER);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCutCopper(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initStairs() {
        if (BlockTypes.ACACIA_STAIRS == null) BlockTypes.ACACIA_STAIRS = buildStairs(BlockAcaciaStairsBehavior.class, BlockId.ACACIA_STAIRS);
        if (BlockTypes.ANDESITE_STAIRS == null) BlockTypes.ANDESITE_STAIRS = buildStairs(BlockAndesiteStairsBehavior.class, BlockId.ANDESITE_STAIRS);
        if (BlockTypes.BAMBOO_MOSAIC_STAIRS == null) BlockTypes.BAMBOO_MOSAIC_STAIRS = buildStairs(BlockBambooMosaicStairsBehavior.class, BlockId.BAMBOO_MOSAIC_STAIRS);
        if (BlockTypes.BAMBOO_STAIRS == null) BlockTypes.BAMBOO_STAIRS = buildStairs(BlockBambooStairsBehavior.class, BlockId.BAMBOO_STAIRS);
        if (BlockTypes.BIRCH_STAIRS == null) BlockTypes.BIRCH_STAIRS = buildStairs(BlockBirchStairsBehavior.class, BlockId.BIRCH_STAIRS);
        if (BlockTypes.BLACKSTONE_STAIRS == null) BlockTypes.BLACKSTONE_STAIRS = buildStairs(BlockBlackstoneStairsBehavior.class, BlockId.BLACKSTONE_STAIRS);
        if (BlockTypes.BRICK_STAIRS == null) BlockTypes.BRICK_STAIRS = buildStairs(BlockBrickStairsBehavior.class, BlockId.BRICK_STAIRS);
        if (BlockTypes.CHERRY_STAIRS == null) BlockTypes.CHERRY_STAIRS = buildStairs(BlockCherryStairsBehavior.class, BlockId.CHERRY_STAIRS);
        if (BlockTypes.COBBLED_DEEPSLATE_STAIRS == null) BlockTypes.COBBLED_DEEPSLATE_STAIRS = buildStairs(BlockCobbledDeepslateStairsBehavior.class, BlockId.COBBLED_DEEPSLATE_STAIRS);
        if (BlockTypes.CRIMSON_STAIRS == null) BlockTypes.CRIMSON_STAIRS = buildStairs(BlockCrimsonStairsBehavior.class, BlockId.CRIMSON_STAIRS);
        if (BlockTypes.CUT_COPPER_STAIRS == null) BlockTypes.CUT_COPPER_STAIRS = buildStairs(BlockCutCopperStairsBehavior.class, BlockId.CUT_COPPER_STAIRS);
        if (BlockTypes.DARK_OAK_STAIRS == null) BlockTypes.DARK_OAK_STAIRS = buildStairs(BlockDarkOakStairsBehavior.class, BlockId.DARK_OAK_STAIRS);
        if (BlockTypes.DARK_PRISMARINE_STAIRS == null) BlockTypes.DARK_PRISMARINE_STAIRS = buildStairs(BlockDarkPrismarineStairsBehavior.class, BlockId.DARK_PRISMARINE_STAIRS);
        if (BlockTypes.DEEPSLATE_BRICK_STAIRS == null) BlockTypes.DEEPSLATE_BRICK_STAIRS = buildStairs(BlockDeepslateBrickStairsBehavior.class, BlockId.DEEPSLATE_BRICK_STAIRS);
        if (BlockTypes.DEEPSLATE_TILE_STAIRS == null) BlockTypes.DEEPSLATE_TILE_STAIRS = buildStairs(BlockDeepslateTileStairsBehavior.class, BlockId.DEEPSLATE_TILE_STAIRS);
        if (BlockTypes.DIORITE_STAIRS == null) BlockTypes.DIORITE_STAIRS = buildStairs(BlockDioriteStairsBehavior.class, BlockId.DIORITE_STAIRS);
        if (BlockTypes.END_BRICK_STAIRS == null) BlockTypes.END_BRICK_STAIRS = buildStairs(BlockEndBrickStairsBehavior.class, BlockId.END_BRICK_STAIRS);
        if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS == null) BlockTypes.EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockExposedCutCopperStairsBehavior.class, BlockId.EXPOSED_CUT_COPPER_STAIRS);
        if (BlockTypes.GRANITE_STAIRS == null) BlockTypes.GRANITE_STAIRS = buildStairs(BlockGraniteStairsBehavior.class, BlockId.GRANITE_STAIRS);
        if (BlockTypes.JUNGLE_STAIRS == null) BlockTypes.JUNGLE_STAIRS = buildStairs(BlockJungleStairsBehavior.class, BlockId.JUNGLE_STAIRS);
        if (BlockTypes.MANGROVE_STAIRS == null) BlockTypes.MANGROVE_STAIRS = buildStairs(BlockMangroveStairsBehavior.class, BlockId.MANGROVE_STAIRS);
        if (BlockTypes.MOSSY_COBBLESTONE_STAIRS == null) BlockTypes.MOSSY_COBBLESTONE_STAIRS = buildStairs(BlockMossyCobblestoneStairsBehavior.class, BlockId.MOSSY_COBBLESTONE_STAIRS);
        if (BlockTypes.MOSSY_STONE_BRICK_STAIRS == null) BlockTypes.MOSSY_STONE_BRICK_STAIRS = buildStairs(BlockMossyStoneBrickStairsBehavior.class, BlockId.MOSSY_STONE_BRICK_STAIRS);
        if (BlockTypes.MUD_BRICK_STAIRS == null) BlockTypes.MUD_BRICK_STAIRS = buildStairs(BlockMudBrickStairsBehavior.class, BlockId.MUD_BRICK_STAIRS);
        if (BlockTypes.NETHER_BRICK_STAIRS == null) BlockTypes.NETHER_BRICK_STAIRS = buildStairs(BlockNetherBrickStairsBehavior.class, BlockId.NETHER_BRICK_STAIRS);
        if (BlockTypes.NORMAL_STONE_STAIRS == null) BlockTypes.NORMAL_STONE_STAIRS = buildStairs(BlockNormalStoneStairsBehavior.class, BlockId.NORMAL_STONE_STAIRS);
        if (BlockTypes.OAK_STAIRS == null) BlockTypes.OAK_STAIRS = buildStairs(BlockOakStairsBehavior.class, BlockId.OAK_STAIRS);
        if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS == null) BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockOxidizedCutCopperStairsBehavior.class, BlockId.OXIDIZED_CUT_COPPER_STAIRS);
        if (BlockTypes.POLISHED_ANDESITE_STAIRS == null) BlockTypes.POLISHED_ANDESITE_STAIRS = buildStairs(BlockPolishedAndesiteStairsBehavior.class, BlockId.POLISHED_ANDESITE_STAIRS);
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS == null) BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = buildStairs(BlockPolishedBlackstoneBrickStairsBehavior.class, BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS);
        if (BlockTypes.POLISHED_BLACKSTONE_STAIRS == null) BlockTypes.POLISHED_BLACKSTONE_STAIRS = buildStairs(BlockPolishedBlackstoneStairsBehavior.class, BlockId.POLISHED_BLACKSTONE_STAIRS);
        if (BlockTypes.POLISHED_DEEPSLATE_STAIRS == null) BlockTypes.POLISHED_DEEPSLATE_STAIRS = buildStairs(BlockPolishedDeepslateStairsBehavior.class, BlockId.POLISHED_DEEPSLATE_STAIRS);
        if (BlockTypes.POLISHED_DIORITE_STAIRS == null) BlockTypes.POLISHED_DIORITE_STAIRS = buildStairs(BlockPolishedDioriteStairsBehavior.class, BlockId.POLISHED_DIORITE_STAIRS);
        if (BlockTypes.POLISHED_GRANITE_STAIRS == null) BlockTypes.POLISHED_GRANITE_STAIRS = buildStairs(BlockPolishedGraniteStairsBehavior.class, BlockId.POLISHED_GRANITE_STAIRS);
        if (BlockTypes.POLISHED_TUFF_STAIRS == null) BlockTypes.POLISHED_TUFF_STAIRS = buildStairs(BlockPolishedTuffStairsBehavior.class, BlockId.POLISHED_TUFF_STAIRS);
        if (BlockTypes.PRISMARINE_BRICKS_STAIRS == null) BlockTypes.PRISMARINE_BRICKS_STAIRS = buildStairs(BlockPrismarineBricksStairsBehavior.class, BlockId.PRISMARINE_BRICKS_STAIRS);
        if (BlockTypes.PRISMARINE_STAIRS == null) BlockTypes.PRISMARINE_STAIRS = buildStairs(BlockPrismarineStairsBehavior.class, BlockId.PRISMARINE_STAIRS);
        if (BlockTypes.PURPUR_STAIRS == null) BlockTypes.PURPUR_STAIRS = buildStairs(BlockPurpurStairsBehavior.class, BlockId.PURPUR_STAIRS);
        if (BlockTypes.QUARTZ_STAIRS == null) BlockTypes.QUARTZ_STAIRS = buildStairs(BlockQuartzStairsBehavior.class, BlockId.QUARTZ_STAIRS);
        if (BlockTypes.RED_NETHER_BRICK_STAIRS == null) BlockTypes.RED_NETHER_BRICK_STAIRS = buildStairs(BlockRedNetherBrickStairsBehavior.class, BlockId.RED_NETHER_BRICK_STAIRS);
        if (BlockTypes.RED_SANDSTONE_STAIRS == null) BlockTypes.RED_SANDSTONE_STAIRS = buildStairs(BlockRedSandstoneStairsBehavior.class, BlockId.RED_SANDSTONE_STAIRS);
        if (BlockTypes.SANDSTONE_STAIRS == null) BlockTypes.SANDSTONE_STAIRS = buildStairs(BlockSandstoneStairsBehavior.class, BlockId.SANDSTONE_STAIRS);
        if (BlockTypes.SMOOTH_QUARTZ_STAIRS == null) BlockTypes.SMOOTH_QUARTZ_STAIRS = buildStairs(BlockSmoothQuartzStairsBehavior.class, BlockId.SMOOTH_QUARTZ_STAIRS);
        if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS == null) BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = buildStairs(BlockSmoothRedSandstoneStairsBehavior.class, BlockId.SMOOTH_RED_SANDSTONE_STAIRS);
        if (BlockTypes.SMOOTH_SANDSTONE_STAIRS == null) BlockTypes.SMOOTH_SANDSTONE_STAIRS = buildStairs(BlockSmoothSandstoneStairsBehavior.class, BlockId.SMOOTH_SANDSTONE_STAIRS);
        if (BlockTypes.SPRUCE_STAIRS == null) BlockTypes.SPRUCE_STAIRS = buildStairs(BlockSpruceStairsBehavior.class, BlockId.SPRUCE_STAIRS);
        if (BlockTypes.STONE_BRICK_STAIRS == null) BlockTypes.STONE_BRICK_STAIRS = buildStairs(BlockStoneBrickStairsBehavior.class, BlockId.STONE_BRICK_STAIRS);
        if (BlockTypes.STONE_STAIRS == null) BlockTypes.STONE_STAIRS = buildStairs(BlockStoneStairsBehavior.class, BlockId.STONE_STAIRS);
        if (BlockTypes.TUFF_BRICK_STAIRS == null) BlockTypes.TUFF_BRICK_STAIRS = buildStairs(BlockTuffBrickStairsBehavior.class, BlockId.TUFF_BRICK_STAIRS);
        if (BlockTypes.TUFF_STAIRS == null) BlockTypes.TUFF_STAIRS = buildStairs(BlockTuffStairsBehavior.class, BlockId.TUFF_STAIRS);
        if (BlockTypes.WARPED_STAIRS == null) BlockTypes.WARPED_STAIRS = buildStairs(BlockWarpedStairsBehavior.class, BlockId.WARPED_STAIRS);
        if (BlockTypes.WAXED_CUT_COPPER_STAIRS == null) BlockTypes.WAXED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedCutCopperStairsBehavior.class, BlockId.WAXED_CUT_COPPER_STAIRS);
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS == null) BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedExposedCutCopperStairsBehavior.class, BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS == null) BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedOxidizedCutCopperStairsBehavior.class, BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS == null) BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedWeatheredCutCopperStairsBehavior.class, BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS == null) BlockTypes.WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockWeatheredCutCopperStairsBehavior.class, BlockId.WEATHERED_CUT_COPPER_STAIRS);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStairs(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
               .build();
    }

    public static void initPlanks() {
        if (BlockTypes.ACACIA_PLANKS == null) BlockTypes.ACACIA_PLANKS = buildPlanks(BlockAcaciaPlanksBehavior.class, BlockId.ACACIA_PLANKS);
        if (BlockTypes.BAMBOO_PLANKS == null) BlockTypes.BAMBOO_PLANKS = buildPlanks(BlockBambooPlanksBehavior.class, BlockId.BAMBOO_PLANKS);
        if (BlockTypes.BIRCH_PLANKS == null) BlockTypes.BIRCH_PLANKS = buildPlanks(BlockBirchPlanksBehavior.class, BlockId.BIRCH_PLANKS);
        if (BlockTypes.CHERRY_PLANKS == null) BlockTypes.CHERRY_PLANKS = buildPlanks(BlockCherryPlanksBehavior.class, BlockId.CHERRY_PLANKS);
        if (BlockTypes.CRIMSON_PLANKS == null) BlockTypes.CRIMSON_PLANKS = buildPlanks(BlockCrimsonPlanksBehavior.class, BlockId.CRIMSON_PLANKS);
        if (BlockTypes.DARK_OAK_PLANKS == null) BlockTypes.DARK_OAK_PLANKS = buildPlanks(BlockDarkOakPlanksBehavior.class, BlockId.DARK_OAK_PLANKS);
        if (BlockTypes.JUNGLE_PLANKS == null) BlockTypes.JUNGLE_PLANKS = buildPlanks(BlockJunglePlanksBehavior.class, BlockId.JUNGLE_PLANKS);
        if (BlockTypes.MANGROVE_PLANKS == null) BlockTypes.MANGROVE_PLANKS = buildPlanks(BlockMangrovePlanksBehavior.class, BlockId.MANGROVE_PLANKS);
        if (BlockTypes.OAK_PLANKS == null) BlockTypes.OAK_PLANKS = buildPlanks(BlockOakPlanksBehavior.class, BlockId.OAK_PLANKS);
        if (BlockTypes.SPRUCE_PLANKS == null) BlockTypes.SPRUCE_PLANKS = buildPlanks(BlockSprucePlanksBehavior.class, BlockId.SPRUCE_PLANKS);
        if (BlockTypes.WARPED_PLANKS == null) BlockTypes.WARPED_PLANKS = buildPlanks(BlockWarpedPlanksBehavior.class, BlockId.WARPED_PLANKS);
    }

    private static <T extends BlockBehavior> BlockType<T> buildPlanks(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initGlazedTerracotta() {
        if (BlockTypes.BLACK_GLAZED_TERRACOTTA == null) BlockTypes.BLACK_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockBlackGlazedTerracottaBehavior.class, BlockId.BLACK_GLAZED_TERRACOTTA);
        if (BlockTypes.BLUE_GLAZED_TERRACOTTA == null) BlockTypes.BLUE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockBlueGlazedTerracottaBehavior.class, BlockId.BLUE_GLAZED_TERRACOTTA);
        if (BlockTypes.BROWN_GLAZED_TERRACOTTA == null) BlockTypes.BROWN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockBrownGlazedTerracottaBehavior.class, BlockId.BROWN_GLAZED_TERRACOTTA);
        if (BlockTypes.CYAN_GLAZED_TERRACOTTA == null) BlockTypes.CYAN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockCyanGlazedTerracottaBehavior.class, BlockId.CYAN_GLAZED_TERRACOTTA);
        if (BlockTypes.GRAY_GLAZED_TERRACOTTA == null) BlockTypes.GRAY_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockGrayGlazedTerracottaBehavior.class, BlockId.GRAY_GLAZED_TERRACOTTA);
        if (BlockTypes.GREEN_GLAZED_TERRACOTTA == null) BlockTypes.GREEN_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockGreenGlazedTerracottaBehavior.class, BlockId.GREEN_GLAZED_TERRACOTTA);
        if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA == null) BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockLightBlueGlazedTerracottaBehavior.class, BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA);
        if (BlockTypes.LIME_GLAZED_TERRACOTTA == null) BlockTypes.LIME_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockLimeGlazedTerracottaBehavior.class, BlockId.LIME_GLAZED_TERRACOTTA);
        if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA == null) BlockTypes.MAGENTA_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockMagentaGlazedTerracottaBehavior.class, BlockId.MAGENTA_GLAZED_TERRACOTTA);
        if (BlockTypes.ORANGE_GLAZED_TERRACOTTA == null) BlockTypes.ORANGE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockOrangeGlazedTerracottaBehavior.class, BlockId.ORANGE_GLAZED_TERRACOTTA);
        if (BlockTypes.PINK_GLAZED_TERRACOTTA == null) BlockTypes.PINK_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockPinkGlazedTerracottaBehavior.class, BlockId.PINK_GLAZED_TERRACOTTA);
        if (BlockTypes.PURPLE_GLAZED_TERRACOTTA == null) BlockTypes.PURPLE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockPurpleGlazedTerracottaBehavior.class, BlockId.PURPLE_GLAZED_TERRACOTTA);
        if (BlockTypes.RED_GLAZED_TERRACOTTA == null) BlockTypes.RED_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockRedGlazedTerracottaBehavior.class, BlockId.RED_GLAZED_TERRACOTTA);
        if (BlockTypes.SILVER_GLAZED_TERRACOTTA == null) BlockTypes.SILVER_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockSilverGlazedTerracottaBehavior.class, BlockId.SILVER_GLAZED_TERRACOTTA);
        if (BlockTypes.WHITE_GLAZED_TERRACOTTA == null) BlockTypes.WHITE_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockWhiteGlazedTerracottaBehavior.class, BlockId.WHITE_GLAZED_TERRACOTTA);
        if (BlockTypes.YELLOW_GLAZED_TERRACOTTA == null) BlockTypes.YELLOW_GLAZED_TERRACOTTA = buildGlazedTerracotta(BlockYellowGlazedTerracottaBehavior.class, BlockId.YELLOW_GLAZED_TERRACOTTA);
    }

    private static <T extends BlockBehavior> BlockType<T> buildGlazedTerracotta(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.FACING_DIRECTION)
               .build();
    }

    public static void initWools() {
        if (BlockTypes.BLACK_WOOL == null) BlockTypes.BLACK_WOOL = buildWool(BlockBlackWoolBehavior.class, BlockId.BLACK_WOOL);
        if (BlockTypes.BLUE_WOOL == null) BlockTypes.BLUE_WOOL = buildWool(BlockBlueWoolBehavior.class, BlockId.BLUE_WOOL);
        if (BlockTypes.BROWN_WOOL == null) BlockTypes.BROWN_WOOL = buildWool(BlockBrownWoolBehavior.class, BlockId.BROWN_WOOL);
        if (BlockTypes.CYAN_WOOL == null) BlockTypes.CYAN_WOOL = buildWool(BlockCyanWoolBehavior.class, BlockId.CYAN_WOOL);
        if (BlockTypes.GRAY_WOOL == null) BlockTypes.GRAY_WOOL = buildWool(BlockGrayWoolBehavior.class, BlockId.GRAY_WOOL);
        if (BlockTypes.GREEN_WOOL == null) BlockTypes.GREEN_WOOL = buildWool(BlockGreenWoolBehavior.class, BlockId.GREEN_WOOL);
        if (BlockTypes.LIGHT_BLUE_WOOL == null) BlockTypes.LIGHT_BLUE_WOOL = buildWool(BlockLightBlueWoolBehavior.class, BlockId.LIGHT_BLUE_WOOL);
        if (BlockTypes.LIGHT_GRAY_WOOL == null) BlockTypes.LIGHT_GRAY_WOOL = buildWool(BlockLightGrayWoolBehavior.class, BlockId.LIGHT_GRAY_WOOL);
        if (BlockTypes.LIME_WOOL == null) BlockTypes.LIME_WOOL = buildWool(BlockLimeWoolBehavior.class, BlockId.LIME_WOOL);
        if (BlockTypes.MAGENTA_WOOL == null) BlockTypes.MAGENTA_WOOL = buildWool(BlockMagentaWoolBehavior.class, BlockId.MAGENTA_WOOL);
        if (BlockTypes.ORANGE_WOOL == null) BlockTypes.ORANGE_WOOL = buildWool(BlockOrangeWoolBehavior.class, BlockId.ORANGE_WOOL);
        if (BlockTypes.PINK_WOOL == null) BlockTypes.PINK_WOOL = buildWool(BlockPinkWoolBehavior.class, BlockId.PINK_WOOL);
        if (BlockTypes.PURPLE_WOOL == null) BlockTypes.PURPLE_WOOL = buildWool(BlockPurpleWoolBehavior.class, BlockId.PURPLE_WOOL);
        if (BlockTypes.RED_WOOL == null) BlockTypes.RED_WOOL = buildWool(BlockRedWoolBehavior.class, BlockId.RED_WOOL);
        if (BlockTypes.WHITE_WOOL == null) BlockTypes.WHITE_WOOL = buildWool(BlockWhiteWoolBehavior.class, BlockId.WHITE_WOOL);
        if (BlockTypes.YELLOW_WOOL == null) BlockTypes.YELLOW_WOOL = buildWool(BlockYellowWoolBehavior.class, BlockId.YELLOW_WOOL);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWool(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initButtons() {
        if (BlockTypes.ACACIA_BUTTON == null) BlockTypes.ACACIA_BUTTON = buildButton(BlockAcaciaButtonBehavior.class, BlockId.ACACIA_BUTTON);
        if (BlockTypes.BAMBOO_BUTTON == null) BlockTypes.BAMBOO_BUTTON = buildButton(BlockBambooButtonBehavior.class, BlockId.BAMBOO_BUTTON);
        if (BlockTypes.BIRCH_BUTTON == null) BlockTypes.BIRCH_BUTTON = buildButton(BlockBirchButtonBehavior.class, BlockId.BIRCH_BUTTON);
        if (BlockTypes.CHERRY_BUTTON == null) BlockTypes.CHERRY_BUTTON = buildButton(BlockCherryButtonBehavior.class, BlockId.CHERRY_BUTTON);
        if (BlockTypes.CRIMSON_BUTTON == null) BlockTypes.CRIMSON_BUTTON = buildButton(BlockCrimsonButtonBehavior.class, BlockId.CRIMSON_BUTTON);
        if (BlockTypes.DARK_OAK_BUTTON == null) BlockTypes.DARK_OAK_BUTTON = buildButton(BlockDarkOakButtonBehavior.class, BlockId.DARK_OAK_BUTTON);
        if (BlockTypes.JUNGLE_BUTTON == null) BlockTypes.JUNGLE_BUTTON = buildButton(BlockJungleButtonBehavior.class, BlockId.JUNGLE_BUTTON);
        if (BlockTypes.MANGROVE_BUTTON == null) BlockTypes.MANGROVE_BUTTON = buildButton(BlockMangroveButtonBehavior.class, BlockId.MANGROVE_BUTTON);
        if (BlockTypes.POLISHED_BLACKSTONE_BUTTON == null) BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockPolishedBlackstoneButtonBehavior.class, BlockId.POLISHED_BLACKSTONE_BUTTON);
        if (BlockTypes.SPRUCE_BUTTON == null) BlockTypes.SPRUCE_BUTTON = buildButton(BlockSpruceButtonBehavior.class, BlockId.SPRUCE_BUTTON);
        if (BlockTypes.STONE_BUTTON == null) BlockTypes.STONE_BUTTON = buildButton(BlockStoneButtonBehavior.class, BlockId.STONE_BUTTON);
        if (BlockTypes.WARPED_BUTTON == null) BlockTypes.WARPED_BUTTON = buildButton(BlockWarpedButtonBehavior.class, BlockId.WARPED_BUTTON);
        if (BlockTypes.WOODEN_BUTTON == null) BlockTypes.WOODEN_BUTTON = buildButton(BlockWoodenButtonBehavior.class, BlockId.WOODEN_BUTTON);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.BUTTON_PRESSED_BIT)
               .build();
    }

    public static void initFences() {
        if (BlockTypes.ACACIA_FENCE == null) BlockTypes.ACACIA_FENCE = buildFence(BlockAcaciaFenceBehavior.class, BlockId.ACACIA_FENCE);
        if (BlockTypes.BAMBOO_FENCE == null) BlockTypes.BAMBOO_FENCE = buildFence(BlockBambooFenceBehavior.class, BlockId.BAMBOO_FENCE);
        if (BlockTypes.BIRCH_FENCE == null) BlockTypes.BIRCH_FENCE = buildFence(BlockBirchFenceBehavior.class, BlockId.BIRCH_FENCE);
        if (BlockTypes.CHERRY_FENCE == null) BlockTypes.CHERRY_FENCE = buildFence(BlockCherryFenceBehavior.class, BlockId.CHERRY_FENCE);
        if (BlockTypes.CRIMSON_FENCE == null) BlockTypes.CRIMSON_FENCE = buildFence(BlockCrimsonFenceBehavior.class, BlockId.CRIMSON_FENCE);
        if (BlockTypes.DARK_OAK_FENCE == null) BlockTypes.DARK_OAK_FENCE = buildFence(BlockDarkOakFenceBehavior.class, BlockId.DARK_OAK_FENCE);
        if (BlockTypes.JUNGLE_FENCE == null) BlockTypes.JUNGLE_FENCE = buildFence(BlockJungleFenceBehavior.class, BlockId.JUNGLE_FENCE);
        if (BlockTypes.MANGROVE_FENCE == null) BlockTypes.MANGROVE_FENCE = buildFence(BlockMangroveFenceBehavior.class, BlockId.MANGROVE_FENCE);
        if (BlockTypes.NETHER_BRICK_FENCE == null) BlockTypes.NETHER_BRICK_FENCE = buildFence(BlockNetherBrickFenceBehavior.class, BlockId.NETHER_BRICK_FENCE);
        if (BlockTypes.OAK_FENCE == null) BlockTypes.OAK_FENCE = buildFence(BlockOakFenceBehavior.class, BlockId.OAK_FENCE);
        if (BlockTypes.SPRUCE_FENCE == null) BlockTypes.SPRUCE_FENCE = buildFence(BlockSpruceFenceBehavior.class, BlockId.SPRUCE_FENCE);
        if (BlockTypes.WARPED_FENCE == null) BlockTypes.WARPED_FENCE = buildFence(BlockWarpedFenceBehavior.class, BlockId.WARPED_FENCE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildFence(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initSlabs() {
        if (BlockTypes.ACACIA_SLAB == null) BlockTypes.ACACIA_SLAB = buildSlab(BlockAcaciaSlabBehavior.class, BlockId.ACACIA_SLAB);
        if (BlockTypes.ANDESITE_SLAB == null) BlockTypes.ANDESITE_SLAB = buildSlab(BlockAndesiteSlabBehavior.class, BlockId.ANDESITE_SLAB);
        if (BlockTypes.BAMBOO_MOSAIC_SLAB == null) BlockTypes.BAMBOO_MOSAIC_SLAB = buildSlab(BlockBambooMosaicSlabBehavior.class, BlockId.BAMBOO_MOSAIC_SLAB);
        if (BlockTypes.BAMBOO_SLAB == null) BlockTypes.BAMBOO_SLAB = buildSlab(BlockBambooSlabBehavior.class, BlockId.BAMBOO_SLAB);
        if (BlockTypes.BIRCH_SLAB == null) BlockTypes.BIRCH_SLAB = buildSlab(BlockBirchSlabBehavior.class, BlockId.BIRCH_SLAB);
        if (BlockTypes.BLACKSTONE_SLAB == null) BlockTypes.BLACKSTONE_SLAB = buildSlab(BlockBlackstoneSlabBehavior.class, BlockId.BLACKSTONE_SLAB);
        if (BlockTypes.BRICK_SLAB == null) BlockTypes.BRICK_SLAB = buildSlab(BlockBrickSlabBehavior.class, BlockId.BRICK_SLAB);
        if (BlockTypes.CHERRY_SLAB == null) BlockTypes.CHERRY_SLAB = buildSlab(BlockCherrySlabBehavior.class, BlockId.CHERRY_SLAB);
        if (BlockTypes.COBBLED_DEEPSLATE_SLAB == null) BlockTypes.COBBLED_DEEPSLATE_SLAB = buildSlab(BlockCobbledDeepslateSlabBehavior.class, BlockId.COBBLED_DEEPSLATE_SLAB);
        if (BlockTypes.COBBLESTONE_SLAB == null) BlockTypes.COBBLESTONE_SLAB = buildSlab(BlockCobblestoneSlabBehavior.class, BlockId.COBBLESTONE_SLAB);
        if (BlockTypes.CRIMSON_SLAB == null) BlockTypes.CRIMSON_SLAB = buildSlab(BlockCrimsonSlabBehavior.class, BlockId.CRIMSON_SLAB);
        if (BlockTypes.CUT_COPPER_SLAB == null) BlockTypes.CUT_COPPER_SLAB = buildSlab(BlockCutCopperSlabBehavior.class, BlockId.CUT_COPPER_SLAB);
        if (BlockTypes.CUT_RED_SANDSTONE_SLAB == null) BlockTypes.CUT_RED_SANDSTONE_SLAB = buildSlab(BlockCutRedSandstoneSlabBehavior.class, BlockId.CUT_RED_SANDSTONE_SLAB);
        if (BlockTypes.CUT_SANDSTONE_SLAB == null) BlockTypes.CUT_SANDSTONE_SLAB = buildSlab(BlockCutSandstoneSlabBehavior.class, BlockId.CUT_SANDSTONE_SLAB);
        if (BlockTypes.DARK_OAK_SLAB == null) BlockTypes.DARK_OAK_SLAB = buildSlab(BlockDarkOakSlabBehavior.class, BlockId.DARK_OAK_SLAB);
        if (BlockTypes.DARK_PRISMARINE_SLAB == null) BlockTypes.DARK_PRISMARINE_SLAB = buildSlab(BlockDarkPrismarineSlabBehavior.class, BlockId.DARK_PRISMARINE_SLAB);
        if (BlockTypes.DEEPSLATE_BRICK_SLAB == null) BlockTypes.DEEPSLATE_BRICK_SLAB = buildSlab(BlockDeepslateBrickSlabBehavior.class, BlockId.DEEPSLATE_BRICK_SLAB);
        if (BlockTypes.DEEPSLATE_TILE_SLAB == null) BlockTypes.DEEPSLATE_TILE_SLAB = buildSlab(BlockDeepslateTileSlabBehavior.class, BlockId.DEEPSLATE_TILE_SLAB);
        if (BlockTypes.DIORITE_SLAB == null) BlockTypes.DIORITE_SLAB = buildSlab(BlockDioriteSlabBehavior.class, BlockId.DIORITE_SLAB);
        if (BlockTypes.END_STONE_BRICK_SLAB == null) BlockTypes.END_STONE_BRICK_SLAB = buildSlab(BlockEndStoneBrickSlabBehavior.class, BlockId.END_STONE_BRICK_SLAB);
        if (BlockTypes.EXPOSED_CUT_COPPER_SLAB == null) BlockTypes.EXPOSED_CUT_COPPER_SLAB = buildSlab(BlockExposedCutCopperSlabBehavior.class, BlockId.EXPOSED_CUT_COPPER_SLAB);
        if (BlockTypes.GRANITE_SLAB == null) BlockTypes.GRANITE_SLAB = buildSlab(BlockGraniteSlabBehavior.class, BlockId.GRANITE_SLAB);
        if (BlockTypes.JUNGLE_SLAB == null) BlockTypes.JUNGLE_SLAB = buildSlab(BlockJungleSlabBehavior.class, BlockId.JUNGLE_SLAB);
        if (BlockTypes.MANGROVE_SLAB == null) BlockTypes.MANGROVE_SLAB = buildSlab(BlockMangroveSlabBehavior.class, BlockId.MANGROVE_SLAB);
        if (BlockTypes.MOSSY_COBBLESTONE_SLAB == null) BlockTypes.MOSSY_COBBLESTONE_SLAB = buildSlab(BlockMossyCobblestoneSlabBehavior.class, BlockId.MOSSY_COBBLESTONE_SLAB);
        if (BlockTypes.MOSSY_STONE_BRICK_SLAB == null) BlockTypes.MOSSY_STONE_BRICK_SLAB = buildSlab(BlockMossyStoneBrickSlabBehavior.class, BlockId.MOSSY_STONE_BRICK_SLAB);
        if (BlockTypes.MUD_BRICK_SLAB == null) BlockTypes.MUD_BRICK_SLAB = buildSlab(BlockMudBrickSlabBehavior.class, BlockId.MUD_BRICK_SLAB);
        if (BlockTypes.NETHER_BRICK_SLAB == null) BlockTypes.NETHER_BRICK_SLAB = buildSlab(BlockNetherBrickSlabBehavior.class, BlockId.NETHER_BRICK_SLAB);
        if (BlockTypes.NORMAL_STONE_SLAB == null) BlockTypes.NORMAL_STONE_SLAB = buildSlab(BlockNormalStoneSlabBehavior.class, BlockId.NORMAL_STONE_SLAB);
        if (BlockTypes.OAK_SLAB == null) BlockTypes.OAK_SLAB = buildSlab(BlockOakSlabBehavior.class, BlockId.OAK_SLAB);
        if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB == null) BlockTypes.OXIDIZED_CUT_COPPER_SLAB = buildSlab(BlockOxidizedCutCopperSlabBehavior.class, BlockId.OXIDIZED_CUT_COPPER_SLAB);
        if (BlockTypes.PETRIFIED_OAK_SLAB == null) BlockTypes.PETRIFIED_OAK_SLAB = buildSlab(BlockPetrifiedOakSlabBehavior.class, BlockId.PETRIFIED_OAK_SLAB);
        if (BlockTypes.POLISHED_ANDESITE_SLAB == null) BlockTypes.POLISHED_ANDESITE_SLAB = buildSlab(BlockPolishedAndesiteSlabBehavior.class, BlockId.POLISHED_ANDESITE_SLAB);
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB == null) BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = buildSlab(BlockPolishedBlackstoneBrickSlabBehavior.class, BlockId.POLISHED_BLACKSTONE_BRICK_SLAB);
        if (BlockTypes.POLISHED_BLACKSTONE_SLAB == null) BlockTypes.POLISHED_BLACKSTONE_SLAB = buildSlab(BlockPolishedBlackstoneSlabBehavior.class, BlockId.POLISHED_BLACKSTONE_SLAB);
        if (BlockTypes.POLISHED_DEEPSLATE_SLAB == null) BlockTypes.POLISHED_DEEPSLATE_SLAB = buildSlab(BlockPolishedDeepslateSlabBehavior.class, BlockId.POLISHED_DEEPSLATE_SLAB);
        if (BlockTypes.POLISHED_DIORITE_SLAB == null) BlockTypes.POLISHED_DIORITE_SLAB = buildSlab(BlockPolishedDioriteSlabBehavior.class, BlockId.POLISHED_DIORITE_SLAB);
        if (BlockTypes.POLISHED_GRANITE_SLAB == null) BlockTypes.POLISHED_GRANITE_SLAB = buildSlab(BlockPolishedGraniteSlabBehavior.class, BlockId.POLISHED_GRANITE_SLAB);
        if (BlockTypes.POLISHED_TUFF_SLAB == null) BlockTypes.POLISHED_TUFF_SLAB = buildSlab(BlockPolishedTuffSlabBehavior.class, BlockId.POLISHED_TUFF_SLAB);
        if (BlockTypes.PRISMARINE_BRICK_SLAB == null) BlockTypes.PRISMARINE_BRICK_SLAB = buildSlab(BlockPrismarineBrickSlabBehavior.class, BlockId.PRISMARINE_BRICK_SLAB);
        if (BlockTypes.PRISMARINE_SLAB == null) BlockTypes.PRISMARINE_SLAB = buildSlab(BlockPrismarineSlabBehavior.class, BlockId.PRISMARINE_SLAB);
        if (BlockTypes.PURPUR_SLAB == null) BlockTypes.PURPUR_SLAB = buildSlab(BlockPurpurSlabBehavior.class, BlockId.PURPUR_SLAB);
        if (BlockTypes.QUARTZ_SLAB == null) BlockTypes.QUARTZ_SLAB = buildSlab(BlockQuartzSlabBehavior.class, BlockId.QUARTZ_SLAB);
        if (BlockTypes.RED_NETHER_BRICK_SLAB == null) BlockTypes.RED_NETHER_BRICK_SLAB = buildSlab(BlockRedNetherBrickSlabBehavior.class, BlockId.RED_NETHER_BRICK_SLAB);
        if (BlockTypes.RED_SANDSTONE_SLAB == null) BlockTypes.RED_SANDSTONE_SLAB = buildSlab(BlockRedSandstoneSlabBehavior.class, BlockId.RED_SANDSTONE_SLAB);
        if (BlockTypes.SANDSTONE_SLAB == null) BlockTypes.SANDSTONE_SLAB = buildSlab(BlockSandstoneSlabBehavior.class, BlockId.SANDSTONE_SLAB);
        if (BlockTypes.SMOOTH_QUARTZ_SLAB == null) BlockTypes.SMOOTH_QUARTZ_SLAB = buildSlab(BlockSmoothQuartzSlabBehavior.class, BlockId.SMOOTH_QUARTZ_SLAB);
        if (BlockTypes.SMOOTH_RED_SANDSTONE_SLAB == null) BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = buildSlab(BlockSmoothRedSandstoneSlabBehavior.class, BlockId.SMOOTH_RED_SANDSTONE_SLAB);
        if (BlockTypes.SMOOTH_SANDSTONE_SLAB == null) BlockTypes.SMOOTH_SANDSTONE_SLAB = buildSlab(BlockSmoothSandstoneSlabBehavior.class, BlockId.SMOOTH_SANDSTONE_SLAB);
        if (BlockTypes.SMOOTH_STONE_SLAB == null) BlockTypes.SMOOTH_STONE_SLAB = buildSlab(BlockSmoothStoneSlabBehavior.class, BlockId.SMOOTH_STONE_SLAB);
        if (BlockTypes.SPRUCE_SLAB == null) BlockTypes.SPRUCE_SLAB = buildSlab(BlockSpruceSlabBehavior.class, BlockId.SPRUCE_SLAB);
        if (BlockTypes.STONE_BRICK_SLAB == null) BlockTypes.STONE_BRICK_SLAB = buildSlab(BlockStoneBrickSlabBehavior.class, BlockId.STONE_BRICK_SLAB);
        if (BlockTypes.TUFF_BRICK_SLAB == null) BlockTypes.TUFF_BRICK_SLAB = buildSlab(BlockTuffBrickSlabBehavior.class, BlockId.TUFF_BRICK_SLAB);
        if (BlockTypes.TUFF_SLAB == null) BlockTypes.TUFF_SLAB = buildSlab(BlockTuffSlabBehavior.class, BlockId.TUFF_SLAB);
        if (BlockTypes.WARPED_SLAB == null) BlockTypes.WARPED_SLAB = buildSlab(BlockWarpedSlabBehavior.class, BlockId.WARPED_SLAB);
        if (BlockTypes.WAXED_CUT_COPPER_SLAB == null) BlockTypes.WAXED_CUT_COPPER_SLAB = buildSlab(BlockWaxedCutCopperSlabBehavior.class, BlockId.WAXED_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB == null) BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = buildSlab(BlockWaxedExposedCutCopperSlabBehavior.class, BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB == null) BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = buildSlab(BlockWaxedOxidizedCutCopperSlabBehavior.class, BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB == null) BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = buildSlab(BlockWaxedWeatheredCutCopperSlabBehavior.class, BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB);
        if (BlockTypes.WEATHERED_CUT_COPPER_SLAB == null) BlockTypes.WEATHERED_CUT_COPPER_SLAB = buildSlab(BlockWeatheredCutCopperSlabBehavior.class, BlockId.WEATHERED_CUT_COPPER_SLAB);
    }

    private static <T extends BlockBehavior> BlockType<T> buildSlab(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
               .build();
    }

    public static void initStandingSigns() {
        if (BlockTypes.ACACIA_STANDING_SIGN == null) BlockTypes.ACACIA_STANDING_SIGN = buildStandingSign(BlockAcaciaStandingSignBehavior.class, BlockId.ACACIA_STANDING_SIGN);
        if (BlockTypes.BAMBOO_STANDING_SIGN == null) BlockTypes.BAMBOO_STANDING_SIGN = buildStandingSign(BlockBambooStandingSignBehavior.class, BlockId.BAMBOO_STANDING_SIGN);
        if (BlockTypes.BIRCH_STANDING_SIGN == null) BlockTypes.BIRCH_STANDING_SIGN = buildStandingSign(BlockBirchStandingSignBehavior.class, BlockId.BIRCH_STANDING_SIGN);
        if (BlockTypes.CHERRY_STANDING_SIGN == null) BlockTypes.CHERRY_STANDING_SIGN = buildStandingSign(BlockCherryStandingSignBehavior.class, BlockId.CHERRY_STANDING_SIGN);
        if (BlockTypes.CRIMSON_STANDING_SIGN == null) BlockTypes.CRIMSON_STANDING_SIGN = buildStandingSign(BlockCrimsonStandingSignBehavior.class, BlockId.CRIMSON_STANDING_SIGN);
        if (BlockTypes.DARKOAK_STANDING_SIGN == null) BlockTypes.DARKOAK_STANDING_SIGN = buildStandingSign(BlockDarkoakStandingSignBehavior.class, BlockId.DARKOAK_STANDING_SIGN);
        if (BlockTypes.JUNGLE_STANDING_SIGN == null) BlockTypes.JUNGLE_STANDING_SIGN = buildStandingSign(BlockJungleStandingSignBehavior.class, BlockId.JUNGLE_STANDING_SIGN);
        if (BlockTypes.MANGROVE_STANDING_SIGN == null) BlockTypes.MANGROVE_STANDING_SIGN = buildStandingSign(BlockMangroveStandingSignBehavior.class, BlockId.MANGROVE_STANDING_SIGN);
        if (BlockTypes.SPRUCE_STANDING_SIGN == null) BlockTypes.SPRUCE_STANDING_SIGN = buildStandingSign(BlockSpruceStandingSignBehavior.class, BlockId.SPRUCE_STANDING_SIGN);
        if (BlockTypes.STANDING_SIGN == null) BlockTypes.STANDING_SIGN = buildStandingSign(BlockStandingSignBehavior.class, BlockId.STANDING_SIGN);
        if (BlockTypes.WARPED_STANDING_SIGN == null) BlockTypes.WARPED_STANDING_SIGN = buildStandingSign(BlockWarpedStandingSignBehavior.class, BlockId.WARPED_STANDING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
               .build();
    }

    public static void initConcretePowders() {
        if (BlockTypes.BLACK_CONCRETE_POWDER == null) BlockTypes.BLACK_CONCRETE_POWDER = buildConcretePowder(BlockBlackConcretePowderBehavior.class, BlockId.BLACK_CONCRETE_POWDER);
        if (BlockTypes.BLUE_CONCRETE_POWDER == null) BlockTypes.BLUE_CONCRETE_POWDER = buildConcretePowder(BlockBlueConcretePowderBehavior.class, BlockId.BLUE_CONCRETE_POWDER);
        if (BlockTypes.BROWN_CONCRETE_POWDER == null) BlockTypes.BROWN_CONCRETE_POWDER = buildConcretePowder(BlockBrownConcretePowderBehavior.class, BlockId.BROWN_CONCRETE_POWDER);
        if (BlockTypes.CYAN_CONCRETE_POWDER == null) BlockTypes.CYAN_CONCRETE_POWDER = buildConcretePowder(BlockCyanConcretePowderBehavior.class, BlockId.CYAN_CONCRETE_POWDER);
        if (BlockTypes.GRAY_CONCRETE_POWDER == null) BlockTypes.GRAY_CONCRETE_POWDER = buildConcretePowder(BlockGrayConcretePowderBehavior.class, BlockId.GRAY_CONCRETE_POWDER);
        if (BlockTypes.GREEN_CONCRETE_POWDER == null) BlockTypes.GREEN_CONCRETE_POWDER = buildConcretePowder(BlockGreenConcretePowderBehavior.class, BlockId.GREEN_CONCRETE_POWDER);
        if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER == null) BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = buildConcretePowder(BlockLightBlueConcretePowderBehavior.class, BlockId.LIGHT_BLUE_CONCRETE_POWDER);
        if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER == null) BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = buildConcretePowder(BlockLightGrayConcretePowderBehavior.class, BlockId.LIGHT_GRAY_CONCRETE_POWDER);
        if (BlockTypes.LIME_CONCRETE_POWDER == null) BlockTypes.LIME_CONCRETE_POWDER = buildConcretePowder(BlockLimeConcretePowderBehavior.class, BlockId.LIME_CONCRETE_POWDER);
        if (BlockTypes.MAGENTA_CONCRETE_POWDER == null) BlockTypes.MAGENTA_CONCRETE_POWDER = buildConcretePowder(BlockMagentaConcretePowderBehavior.class, BlockId.MAGENTA_CONCRETE_POWDER);
        if (BlockTypes.ORANGE_CONCRETE_POWDER == null) BlockTypes.ORANGE_CONCRETE_POWDER = buildConcretePowder(BlockOrangeConcretePowderBehavior.class, BlockId.ORANGE_CONCRETE_POWDER);
        if (BlockTypes.PINK_CONCRETE_POWDER == null) BlockTypes.PINK_CONCRETE_POWDER = buildConcretePowder(BlockPinkConcretePowderBehavior.class, BlockId.PINK_CONCRETE_POWDER);
        if (BlockTypes.PURPLE_CONCRETE_POWDER == null) BlockTypes.PURPLE_CONCRETE_POWDER = buildConcretePowder(BlockPurpleConcretePowderBehavior.class, BlockId.PURPLE_CONCRETE_POWDER);
        if (BlockTypes.RED_CONCRETE_POWDER == null) BlockTypes.RED_CONCRETE_POWDER = buildConcretePowder(BlockRedConcretePowderBehavior.class, BlockId.RED_CONCRETE_POWDER);
        if (BlockTypes.WHITE_CONCRETE_POWDER == null) BlockTypes.WHITE_CONCRETE_POWDER = buildConcretePowder(BlockWhiteConcretePowderBehavior.class, BlockId.WHITE_CONCRETE_POWDER);
        if (BlockTypes.YELLOW_CONCRETE_POWDER == null) BlockTypes.YELLOW_CONCRETE_POWDER = buildConcretePowder(BlockYellowConcretePowderBehavior.class, BlockId.YELLOW_CONCRETE_POWDER);
    }

    private static <T extends BlockBehavior> BlockType<T> buildConcretePowder(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initCandles() {
        if (BlockTypes.BLACK_CANDLE == null) BlockTypes.BLACK_CANDLE = buildCandle(BlockBlackCandleBehavior.class, BlockId.BLACK_CANDLE);
        if (BlockTypes.BLUE_CANDLE == null) BlockTypes.BLUE_CANDLE = buildCandle(BlockBlueCandleBehavior.class, BlockId.BLUE_CANDLE);
        if (BlockTypes.BROWN_CANDLE == null) BlockTypes.BROWN_CANDLE = buildCandle(BlockBrownCandleBehavior.class, BlockId.BROWN_CANDLE);
        if (BlockTypes.CYAN_CANDLE == null) BlockTypes.CYAN_CANDLE = buildCandle(BlockCyanCandleBehavior.class, BlockId.CYAN_CANDLE);
        if (BlockTypes.GRAY_CANDLE == null) BlockTypes.GRAY_CANDLE = buildCandle(BlockGrayCandleBehavior.class, BlockId.GRAY_CANDLE);
        if (BlockTypes.GREEN_CANDLE == null) BlockTypes.GREEN_CANDLE = buildCandle(BlockGreenCandleBehavior.class, BlockId.GREEN_CANDLE);
        if (BlockTypes.LIGHT_BLUE_CANDLE == null) BlockTypes.LIGHT_BLUE_CANDLE = buildCandle(BlockLightBlueCandleBehavior.class, BlockId.LIGHT_BLUE_CANDLE);
        if (BlockTypes.LIGHT_GRAY_CANDLE == null) BlockTypes.LIGHT_GRAY_CANDLE = buildCandle(BlockLightGrayCandleBehavior.class, BlockId.LIGHT_GRAY_CANDLE);
        if (BlockTypes.LIME_CANDLE == null) BlockTypes.LIME_CANDLE = buildCandle(BlockLimeCandleBehavior.class, BlockId.LIME_CANDLE);
        if (BlockTypes.MAGENTA_CANDLE == null) BlockTypes.MAGENTA_CANDLE = buildCandle(BlockMagentaCandleBehavior.class, BlockId.MAGENTA_CANDLE);
        if (BlockTypes.ORANGE_CANDLE == null) BlockTypes.ORANGE_CANDLE = buildCandle(BlockOrangeCandleBehavior.class, BlockId.ORANGE_CANDLE);
        if (BlockTypes.PINK_CANDLE == null) BlockTypes.PINK_CANDLE = buildCandle(BlockPinkCandleBehavior.class, BlockId.PINK_CANDLE);
        if (BlockTypes.PURPLE_CANDLE == null) BlockTypes.PURPLE_CANDLE = buildCandle(BlockPurpleCandleBehavior.class, BlockId.PURPLE_CANDLE);
        if (BlockTypes.RED_CANDLE == null) BlockTypes.RED_CANDLE = buildCandle(BlockRedCandleBehavior.class, BlockId.RED_CANDLE);
        if (BlockTypes.WHITE_CANDLE == null) BlockTypes.WHITE_CANDLE = buildCandle(BlockWhiteCandleBehavior.class, BlockId.WHITE_CANDLE);
        if (BlockTypes.YELLOW_CANDLE == null) BlockTypes.YELLOW_CANDLE = buildCandle(BlockYellowCandleBehavior.class, BlockId.YELLOW_CANDLE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCandle(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
               .build();
    }

    public static void initWalls() {
        if (BlockTypes.BLACKSTONE_WALL == null) BlockTypes.BLACKSTONE_WALL = buildWall(BlockBlackstoneWallBehavior.class, BlockId.BLACKSTONE_WALL);
        if (BlockTypes.COBBLED_DEEPSLATE_WALL == null) BlockTypes.COBBLED_DEEPSLATE_WALL = buildWall(BlockCobbledDeepslateWallBehavior.class, BlockId.COBBLED_DEEPSLATE_WALL);
        if (BlockTypes.COBBLESTONE_WALL == null) BlockTypes.COBBLESTONE_WALL = buildWall(BlockCobblestoneWallBehavior.class, BlockId.COBBLESTONE_WALL);
        if (BlockTypes.DEEPSLATE_BRICK_WALL == null) BlockTypes.DEEPSLATE_BRICK_WALL = buildWall(BlockDeepslateBrickWallBehavior.class, BlockId.DEEPSLATE_BRICK_WALL);
        if (BlockTypes.DEEPSLATE_TILE_WALL == null) BlockTypes.DEEPSLATE_TILE_WALL = buildWall(BlockDeepslateTileWallBehavior.class, BlockId.DEEPSLATE_TILE_WALL);
        if (BlockTypes.MUD_BRICK_WALL == null) BlockTypes.MUD_BRICK_WALL = buildWall(BlockMudBrickWallBehavior.class, BlockId.MUD_BRICK_WALL);
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL == null) BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = buildWall(BlockPolishedBlackstoneBrickWallBehavior.class, BlockId.POLISHED_BLACKSTONE_BRICK_WALL);
        if (BlockTypes.POLISHED_BLACKSTONE_WALL == null) BlockTypes.POLISHED_BLACKSTONE_WALL = buildWall(BlockPolishedBlackstoneWallBehavior.class, BlockId.POLISHED_BLACKSTONE_WALL);
        if (BlockTypes.POLISHED_DEEPSLATE_WALL == null) BlockTypes.POLISHED_DEEPSLATE_WALL = buildWall(BlockPolishedDeepslateWallBehavior.class, BlockId.POLISHED_DEEPSLATE_WALL);
        if (BlockTypes.POLISHED_TUFF_WALL == null) BlockTypes.POLISHED_TUFF_WALL = buildWall(BlockPolishedTuffWallBehavior.class, BlockId.POLISHED_TUFF_WALL);
        if (BlockTypes.TUFF_BRICK_WALL == null) BlockTypes.TUFF_BRICK_WALL = buildWall(BlockTuffBrickWallBehavior.class, BlockId.TUFF_BRICK_WALL);
        if (BlockTypes.TUFF_WALL == null) BlockTypes.TUFF_WALL = buildWall(BlockTuffWallBehavior.class, BlockId.TUFF_WALL);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWall(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.WALL_POST_BIT, BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST)
               .build();
    }

    public static void initTerracotta() {
        if (BlockTypes.BLACK_TERRACOTTA == null) BlockTypes.BLACK_TERRACOTTA = buildTerracotta(BlockBlackTerracottaBehavior.class, BlockId.BLACK_TERRACOTTA);
        if (BlockTypes.BLUE_TERRACOTTA == null) BlockTypes.BLUE_TERRACOTTA = buildTerracotta(BlockBlueTerracottaBehavior.class, BlockId.BLUE_TERRACOTTA);
        if (BlockTypes.BROWN_TERRACOTTA == null) BlockTypes.BROWN_TERRACOTTA = buildTerracotta(BlockBrownTerracottaBehavior.class, BlockId.BROWN_TERRACOTTA);
        if (BlockTypes.CYAN_TERRACOTTA == null) BlockTypes.CYAN_TERRACOTTA = buildTerracotta(BlockCyanTerracottaBehavior.class, BlockId.CYAN_TERRACOTTA);
        if (BlockTypes.GRAY_TERRACOTTA == null) BlockTypes.GRAY_TERRACOTTA = buildTerracotta(BlockGrayTerracottaBehavior.class, BlockId.GRAY_TERRACOTTA);
        if (BlockTypes.GREEN_TERRACOTTA == null) BlockTypes.GREEN_TERRACOTTA = buildTerracotta(BlockGreenTerracottaBehavior.class, BlockId.GREEN_TERRACOTTA);
        if (BlockTypes.LIGHT_BLUE_TERRACOTTA == null) BlockTypes.LIGHT_BLUE_TERRACOTTA = buildTerracotta(BlockLightBlueTerracottaBehavior.class, BlockId.LIGHT_BLUE_TERRACOTTA);
        if (BlockTypes.LIGHT_GRAY_TERRACOTTA == null) BlockTypes.LIGHT_GRAY_TERRACOTTA = buildTerracotta(BlockLightGrayTerracottaBehavior.class, BlockId.LIGHT_GRAY_TERRACOTTA);
        if (BlockTypes.LIME_TERRACOTTA == null) BlockTypes.LIME_TERRACOTTA = buildTerracotta(BlockLimeTerracottaBehavior.class, BlockId.LIME_TERRACOTTA);
        if (BlockTypes.MAGENTA_TERRACOTTA == null) BlockTypes.MAGENTA_TERRACOTTA = buildTerracotta(BlockMagentaTerracottaBehavior.class, BlockId.MAGENTA_TERRACOTTA);
        if (BlockTypes.ORANGE_TERRACOTTA == null) BlockTypes.ORANGE_TERRACOTTA = buildTerracotta(BlockOrangeTerracottaBehavior.class, BlockId.ORANGE_TERRACOTTA);
        if (BlockTypes.PINK_TERRACOTTA == null) BlockTypes.PINK_TERRACOTTA = buildTerracotta(BlockPinkTerracottaBehavior.class, BlockId.PINK_TERRACOTTA);
        if (BlockTypes.PURPLE_TERRACOTTA == null) BlockTypes.PURPLE_TERRACOTTA = buildTerracotta(BlockPurpleTerracottaBehavior.class, BlockId.PURPLE_TERRACOTTA);
        if (BlockTypes.RED_TERRACOTTA == null) BlockTypes.RED_TERRACOTTA = buildTerracotta(BlockRedTerracottaBehavior.class, BlockId.RED_TERRACOTTA);
        if (BlockTypes.WHITE_TERRACOTTA == null) BlockTypes.WHITE_TERRACOTTA = buildTerracotta(BlockWhiteTerracottaBehavior.class, BlockId.WHITE_TERRACOTTA);
        if (BlockTypes.YELLOW_TERRACOTTA == null) BlockTypes.YELLOW_TERRACOTTA = buildTerracotta(BlockYellowTerracottaBehavior.class, BlockId.YELLOW_TERRACOTTA);
    }

    private static <T extends BlockBehavior> BlockType<T> buildTerracotta(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initShulkerBoxes() {
        if (BlockTypes.BLACK_SHULKER_BOX == null) BlockTypes.BLACK_SHULKER_BOX = buildShulkerBox(BlockBlackShulkerBoxBehavior.class, BlockId.BLACK_SHULKER_BOX);
        if (BlockTypes.BLUE_SHULKER_BOX == null) BlockTypes.BLUE_SHULKER_BOX = buildShulkerBox(BlockBlueShulkerBoxBehavior.class, BlockId.BLUE_SHULKER_BOX);
        if (BlockTypes.BROWN_SHULKER_BOX == null) BlockTypes.BROWN_SHULKER_BOX = buildShulkerBox(BlockBrownShulkerBoxBehavior.class, BlockId.BROWN_SHULKER_BOX);
        if (BlockTypes.CYAN_SHULKER_BOX == null) BlockTypes.CYAN_SHULKER_BOX = buildShulkerBox(BlockCyanShulkerBoxBehavior.class, BlockId.CYAN_SHULKER_BOX);
        if (BlockTypes.GRAY_SHULKER_BOX == null) BlockTypes.GRAY_SHULKER_BOX = buildShulkerBox(BlockGrayShulkerBoxBehavior.class, BlockId.GRAY_SHULKER_BOX);
        if (BlockTypes.GREEN_SHULKER_BOX == null) BlockTypes.GREEN_SHULKER_BOX = buildShulkerBox(BlockGreenShulkerBoxBehavior.class, BlockId.GREEN_SHULKER_BOX);
        if (BlockTypes.LIGHT_BLUE_SHULKER_BOX == null) BlockTypes.LIGHT_BLUE_SHULKER_BOX = buildShulkerBox(BlockLightBlueShulkerBoxBehavior.class, BlockId.LIGHT_BLUE_SHULKER_BOX);
        if (BlockTypes.LIGHT_GRAY_SHULKER_BOX == null) BlockTypes.LIGHT_GRAY_SHULKER_BOX = buildShulkerBox(BlockLightGrayShulkerBoxBehavior.class, BlockId.LIGHT_GRAY_SHULKER_BOX);
        if (BlockTypes.LIME_SHULKER_BOX == null) BlockTypes.LIME_SHULKER_BOX = buildShulkerBox(BlockLimeShulkerBoxBehavior.class, BlockId.LIME_SHULKER_BOX);
        if (BlockTypes.MAGENTA_SHULKER_BOX == null) BlockTypes.MAGENTA_SHULKER_BOX = buildShulkerBox(BlockMagentaShulkerBoxBehavior.class, BlockId.MAGENTA_SHULKER_BOX);
        if (BlockTypes.ORANGE_SHULKER_BOX == null) BlockTypes.ORANGE_SHULKER_BOX = buildShulkerBox(BlockOrangeShulkerBoxBehavior.class, BlockId.ORANGE_SHULKER_BOX);
        if (BlockTypes.PINK_SHULKER_BOX == null) BlockTypes.PINK_SHULKER_BOX = buildShulkerBox(BlockPinkShulkerBoxBehavior.class, BlockId.PINK_SHULKER_BOX);
        if (BlockTypes.PURPLE_SHULKER_BOX == null) BlockTypes.PURPLE_SHULKER_BOX = buildShulkerBox(BlockPurpleShulkerBoxBehavior.class, BlockId.PURPLE_SHULKER_BOX);
        if (BlockTypes.RED_SHULKER_BOX == null) BlockTypes.RED_SHULKER_BOX = buildShulkerBox(BlockRedShulkerBoxBehavior.class, BlockId.RED_SHULKER_BOX);
        if (BlockTypes.UNDYED_SHULKER_BOX == null) BlockTypes.UNDYED_SHULKER_BOX = buildShulkerBox(BlockUndyedShulkerBoxBehavior.class, BlockId.UNDYED_SHULKER_BOX);
        if (BlockTypes.WHITE_SHULKER_BOX == null) BlockTypes.WHITE_SHULKER_BOX = buildShulkerBox(BlockWhiteShulkerBoxBehavior.class, BlockId.WHITE_SHULKER_BOX);
        if (BlockTypes.YELLOW_SHULKER_BOX == null) BlockTypes.YELLOW_SHULKER_BOX = buildShulkerBox(BlockYellowShulkerBoxBehavior.class, BlockId.YELLOW_SHULKER_BOX);
    }

    private static <T extends BlockBehavior> BlockType<T> buildShulkerBox(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initWallSigns() {
        if (BlockTypes.ACACIA_WALL_SIGN == null) BlockTypes.ACACIA_WALL_SIGN = buildWallSign(BlockAcaciaWallSignBehavior.class, BlockId.ACACIA_WALL_SIGN);
        if (BlockTypes.BAMBOO_WALL_SIGN == null) BlockTypes.BAMBOO_WALL_SIGN = buildWallSign(BlockBambooWallSignBehavior.class, BlockId.BAMBOO_WALL_SIGN);
        if (BlockTypes.BIRCH_WALL_SIGN == null) BlockTypes.BIRCH_WALL_SIGN = buildWallSign(BlockBirchWallSignBehavior.class, BlockId.BIRCH_WALL_SIGN);
        if (BlockTypes.CHERRY_WALL_SIGN == null) BlockTypes.CHERRY_WALL_SIGN = buildWallSign(BlockCherryWallSignBehavior.class, BlockId.CHERRY_WALL_SIGN);
        if (BlockTypes.CRIMSON_WALL_SIGN == null) BlockTypes.CRIMSON_WALL_SIGN = buildWallSign(BlockCrimsonWallSignBehavior.class, BlockId.CRIMSON_WALL_SIGN);
        if (BlockTypes.DARKOAK_WALL_SIGN == null) BlockTypes.DARKOAK_WALL_SIGN = buildWallSign(BlockDarkoakWallSignBehavior.class, BlockId.DARKOAK_WALL_SIGN);
        if (BlockTypes.JUNGLE_WALL_SIGN == null) BlockTypes.JUNGLE_WALL_SIGN = buildWallSign(BlockJungleWallSignBehavior.class, BlockId.JUNGLE_WALL_SIGN);
        if (BlockTypes.MANGROVE_WALL_SIGN == null) BlockTypes.MANGROVE_WALL_SIGN = buildWallSign(BlockMangroveWallSignBehavior.class, BlockId.MANGROVE_WALL_SIGN);
        if (BlockTypes.SPRUCE_WALL_SIGN == null) BlockTypes.SPRUCE_WALL_SIGN = buildWallSign(BlockSpruceWallSignBehavior.class, BlockId.SPRUCE_WALL_SIGN);
        if (BlockTypes.WALL_SIGN == null) BlockTypes.WALL_SIGN = buildWallSign(BlockWallSignBehavior.class, BlockId.WALL_SIGN);
        if (BlockTypes.WARPED_WALL_SIGN == null) BlockTypes.WARPED_WALL_SIGN = buildWallSign(BlockWarpedWallSignBehavior.class, BlockId.WARPED_WALL_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.FACING_DIRECTION)
               .build();
    }

    public static void initFenceGates() {
        if (BlockTypes.ACACIA_FENCE_GATE == null) BlockTypes.ACACIA_FENCE_GATE = buildFenceGate(BlockAcaciaFenceGateBehavior.class, BlockId.ACACIA_FENCE_GATE);
        if (BlockTypes.BAMBOO_FENCE_GATE == null) BlockTypes.BAMBOO_FENCE_GATE = buildFenceGate(BlockBambooFenceGateBehavior.class, BlockId.BAMBOO_FENCE_GATE);
        if (BlockTypes.BIRCH_FENCE_GATE == null) BlockTypes.BIRCH_FENCE_GATE = buildFenceGate(BlockBirchFenceGateBehavior.class, BlockId.BIRCH_FENCE_GATE);
        if (BlockTypes.CHERRY_FENCE_GATE == null) BlockTypes.CHERRY_FENCE_GATE = buildFenceGate(BlockCherryFenceGateBehavior.class, BlockId.CHERRY_FENCE_GATE);
        if (BlockTypes.CRIMSON_FENCE_GATE == null) BlockTypes.CRIMSON_FENCE_GATE = buildFenceGate(BlockCrimsonFenceGateBehavior.class, BlockId.CRIMSON_FENCE_GATE);
        if (BlockTypes.DARK_OAK_FENCE_GATE == null) BlockTypes.DARK_OAK_FENCE_GATE = buildFenceGate(BlockDarkOakFenceGateBehavior.class, BlockId.DARK_OAK_FENCE_GATE);
        if (BlockTypes.JUNGLE_FENCE_GATE == null) BlockTypes.JUNGLE_FENCE_GATE = buildFenceGate(BlockJungleFenceGateBehavior.class, BlockId.JUNGLE_FENCE_GATE);
        if (BlockTypes.MANGROVE_FENCE_GATE == null) BlockTypes.MANGROVE_FENCE_GATE = buildFenceGate(BlockMangroveFenceGateBehavior.class, BlockId.MANGROVE_FENCE_GATE);
        if (BlockTypes.SPRUCE_FENCE_GATE == null) BlockTypes.SPRUCE_FENCE_GATE = buildFenceGate(BlockSpruceFenceGateBehavior.class, BlockId.SPRUCE_FENCE_GATE);
        if (BlockTypes.WARPED_FENCE_GATE == null) BlockTypes.WARPED_FENCE_GATE = buildFenceGate(BlockWarpedFenceGateBehavior.class, BlockId.WARPED_FENCE_GATE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildFenceGate(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT)
               .build();
    }

    public static void initLeaves() {
        if (BlockTypes.ACACIA_LEAVES == null) BlockTypes.ACACIA_LEAVES = buildLeaves(BlockAcaciaLeavesBehavior.class, BlockId.ACACIA_LEAVES);
        if (BlockTypes.AZALEA_LEAVES == null) BlockTypes.AZALEA_LEAVES = buildLeaves(BlockAzaleaLeavesBehavior.class, BlockId.AZALEA_LEAVES);
        if (BlockTypes.BIRCH_LEAVES == null) BlockTypes.BIRCH_LEAVES = buildLeaves(BlockBirchLeavesBehavior.class, BlockId.BIRCH_LEAVES);
        if (BlockTypes.CHERRY_LEAVES == null) BlockTypes.CHERRY_LEAVES = buildLeaves(BlockCherryLeavesBehavior.class, BlockId.CHERRY_LEAVES);
        if (BlockTypes.DARK_OAK_LEAVES == null) BlockTypes.DARK_OAK_LEAVES = buildLeaves(BlockDarkOakLeavesBehavior.class, BlockId.DARK_OAK_LEAVES);
        if (BlockTypes.JUNGLE_LEAVES == null) BlockTypes.JUNGLE_LEAVES = buildLeaves(BlockJungleLeavesBehavior.class, BlockId.JUNGLE_LEAVES);
        if (BlockTypes.MANGROVE_LEAVES == null) BlockTypes.MANGROVE_LEAVES = buildLeaves(BlockMangroveLeavesBehavior.class, BlockId.MANGROVE_LEAVES);
        if (BlockTypes.OAK_LEAVES == null) BlockTypes.OAK_LEAVES = buildLeaves(BlockOakLeavesBehavior.class, BlockId.OAK_LEAVES);
        if (BlockTypes.SPRUCE_LEAVES == null) BlockTypes.SPRUCE_LEAVES = buildLeaves(BlockSpruceLeavesBehavior.class, BlockId.SPRUCE_LEAVES);
    }

    private static <T extends BlockBehavior> BlockType<T> buildLeaves(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
               .build();
    }

    public static void initConcretes() {
        if (BlockTypes.BLACK_CONCRETE == null) BlockTypes.BLACK_CONCRETE = buildConcrete(BlockBlackConcreteBehavior.class, BlockId.BLACK_CONCRETE);
        if (BlockTypes.BLUE_CONCRETE == null) BlockTypes.BLUE_CONCRETE = buildConcrete(BlockBlueConcreteBehavior.class, BlockId.BLUE_CONCRETE);
        if (BlockTypes.BROWN_CONCRETE == null) BlockTypes.BROWN_CONCRETE = buildConcrete(BlockBrownConcreteBehavior.class, BlockId.BROWN_CONCRETE);
        if (BlockTypes.CYAN_CONCRETE == null) BlockTypes.CYAN_CONCRETE = buildConcrete(BlockCyanConcreteBehavior.class, BlockId.CYAN_CONCRETE);
        if (BlockTypes.GRAY_CONCRETE == null) BlockTypes.GRAY_CONCRETE = buildConcrete(BlockGrayConcreteBehavior.class, BlockId.GRAY_CONCRETE);
        if (BlockTypes.GREEN_CONCRETE == null) BlockTypes.GREEN_CONCRETE = buildConcrete(BlockGreenConcreteBehavior.class, BlockId.GREEN_CONCRETE);
        if (BlockTypes.LIGHT_BLUE_CONCRETE == null) BlockTypes.LIGHT_BLUE_CONCRETE = buildConcrete(BlockLightBlueConcreteBehavior.class, BlockId.LIGHT_BLUE_CONCRETE);
        if (BlockTypes.LIGHT_GRAY_CONCRETE == null) BlockTypes.LIGHT_GRAY_CONCRETE = buildConcrete(BlockLightGrayConcreteBehavior.class, BlockId.LIGHT_GRAY_CONCRETE);
        if (BlockTypes.LIME_CONCRETE == null) BlockTypes.LIME_CONCRETE = buildConcrete(BlockLimeConcreteBehavior.class, BlockId.LIME_CONCRETE);
        if (BlockTypes.MAGENTA_CONCRETE == null) BlockTypes.MAGENTA_CONCRETE = buildConcrete(BlockMagentaConcreteBehavior.class, BlockId.MAGENTA_CONCRETE);
        if (BlockTypes.ORANGE_CONCRETE == null) BlockTypes.ORANGE_CONCRETE = buildConcrete(BlockOrangeConcreteBehavior.class, BlockId.ORANGE_CONCRETE);
        if (BlockTypes.PINK_CONCRETE == null) BlockTypes.PINK_CONCRETE = buildConcrete(BlockPinkConcreteBehavior.class, BlockId.PINK_CONCRETE);
        if (BlockTypes.PURPLE_CONCRETE == null) BlockTypes.PURPLE_CONCRETE = buildConcrete(BlockPurpleConcreteBehavior.class, BlockId.PURPLE_CONCRETE);
        if (BlockTypes.RED_CONCRETE == null) BlockTypes.RED_CONCRETE = buildConcrete(BlockRedConcreteBehavior.class, BlockId.RED_CONCRETE);
        if (BlockTypes.WHITE_CONCRETE == null) BlockTypes.WHITE_CONCRETE = buildConcrete(BlockWhiteConcreteBehavior.class, BlockId.WHITE_CONCRETE);
        if (BlockTypes.YELLOW_CONCRETE == null) BlockTypes.YELLOW_CONCRETE = buildConcrete(BlockYellowConcreteBehavior.class, BlockId.YELLOW_CONCRETE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildConcrete(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .build();
    }

    public static void initTrapdoors() {
        if (BlockTypes.ACACIA_TRAPDOOR == null) BlockTypes.ACACIA_TRAPDOOR = buildTrapdoor(BlockAcaciaTrapdoorBehavior.class, BlockId.ACACIA_TRAPDOOR);
        if (BlockTypes.BAMBOO_TRAPDOOR == null) BlockTypes.BAMBOO_TRAPDOOR = buildTrapdoor(BlockBambooTrapdoorBehavior.class, BlockId.BAMBOO_TRAPDOOR);
        if (BlockTypes.BIRCH_TRAPDOOR == null) BlockTypes.BIRCH_TRAPDOOR = buildTrapdoor(BlockBirchTrapdoorBehavior.class, BlockId.BIRCH_TRAPDOOR);
        if (BlockTypes.CHERRY_TRAPDOOR == null) BlockTypes.CHERRY_TRAPDOOR = buildTrapdoor(BlockCherryTrapdoorBehavior.class, BlockId.CHERRY_TRAPDOOR);
        if (BlockTypes.COPPER_TRAPDOOR == null) BlockTypes.COPPER_TRAPDOOR = buildTrapdoor(BlockCopperTrapdoorBehavior.class, BlockId.COPPER_TRAPDOOR);
        if (BlockTypes.CRIMSON_TRAPDOOR == null) BlockTypes.CRIMSON_TRAPDOOR = buildTrapdoor(BlockCrimsonTrapdoorBehavior.class, BlockId.CRIMSON_TRAPDOOR);
        if (BlockTypes.DARK_OAK_TRAPDOOR == null) BlockTypes.DARK_OAK_TRAPDOOR = buildTrapdoor(BlockDarkOakTrapdoorBehavior.class, BlockId.DARK_OAK_TRAPDOOR);
        if (BlockTypes.EXPOSED_COPPER_TRAPDOOR == null) BlockTypes.EXPOSED_COPPER_TRAPDOOR = buildTrapdoor(BlockExposedCopperTrapdoorBehavior.class, BlockId.EXPOSED_COPPER_TRAPDOOR);
        if (BlockTypes.IRON_TRAPDOOR == null) BlockTypes.IRON_TRAPDOOR = buildTrapdoor(BlockIronTrapdoorBehavior.class, BlockId.IRON_TRAPDOOR);
        if (BlockTypes.JUNGLE_TRAPDOOR == null) BlockTypes.JUNGLE_TRAPDOOR = buildTrapdoor(BlockJungleTrapdoorBehavior.class, BlockId.JUNGLE_TRAPDOOR);
        if (BlockTypes.MANGROVE_TRAPDOOR == null) BlockTypes.MANGROVE_TRAPDOOR = buildTrapdoor(BlockMangroveTrapdoorBehavior.class, BlockId.MANGROVE_TRAPDOOR);
        if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR == null) BlockTypes.OXIDIZED_COPPER_TRAPDOOR = buildTrapdoor(BlockOxidizedCopperTrapdoorBehavior.class, BlockId.OXIDIZED_COPPER_TRAPDOOR);
        if (BlockTypes.SPRUCE_TRAPDOOR == null) BlockTypes.SPRUCE_TRAPDOOR = buildTrapdoor(BlockSpruceTrapdoorBehavior.class, BlockId.SPRUCE_TRAPDOOR);
        if (BlockTypes.TRAPDOOR == null) BlockTypes.TRAPDOOR = buildTrapdoor(BlockTrapdoorBehavior.class, BlockId.TRAPDOOR);
        if (BlockTypes.WARPED_TRAPDOOR == null) BlockTypes.WARPED_TRAPDOOR = buildTrapdoor(BlockWarpedTrapdoorBehavior.class, BlockId.WARPED_TRAPDOOR);
        if (BlockTypes.WAXED_COPPER_TRAPDOOR == null) BlockTypes.WAXED_COPPER_TRAPDOOR = buildTrapdoor(BlockWaxedCopperTrapdoorBehavior.class, BlockId.WAXED_COPPER_TRAPDOOR);
        if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR == null) BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = buildTrapdoor(BlockWaxedExposedCopperTrapdoorBehavior.class, BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR);
        if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR == null) BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = buildTrapdoor(BlockWaxedOxidizedCopperTrapdoorBehavior.class, BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR);
        if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR == null) BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = buildTrapdoor(BlockWaxedWeatheredCopperTrapdoorBehavior.class, BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR);
        if (BlockTypes.WEATHERED_COPPER_TRAPDOOR == null) BlockTypes.WEATHERED_COPPER_TRAPDOOR = buildTrapdoor(BlockWeatheredCopperTrapdoorBehavior.class, BlockId.WEATHERED_COPPER_TRAPDOOR);
    }

    private static <T extends BlockBehavior> BlockType<T> buildTrapdoor(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.OPEN_BIT)
               .build();
    }

    public static void initHangingSigns() {
        if (BlockTypes.ACACIA_HANGING_SIGN == null) BlockTypes.ACACIA_HANGING_SIGN = buildHangingSign(BlockAcaciaHangingSignBehavior.class, BlockId.ACACIA_HANGING_SIGN);
        if (BlockTypes.BAMBOO_HANGING_SIGN == null) BlockTypes.BAMBOO_HANGING_SIGN = buildHangingSign(BlockBambooHangingSignBehavior.class, BlockId.BAMBOO_HANGING_SIGN);
        if (BlockTypes.BIRCH_HANGING_SIGN == null) BlockTypes.BIRCH_HANGING_SIGN = buildHangingSign(BlockBirchHangingSignBehavior.class, BlockId.BIRCH_HANGING_SIGN);
        if (BlockTypes.CHERRY_HANGING_SIGN == null) BlockTypes.CHERRY_HANGING_SIGN = buildHangingSign(BlockCherryHangingSignBehavior.class, BlockId.CHERRY_HANGING_SIGN);
        if (BlockTypes.CRIMSON_HANGING_SIGN == null) BlockTypes.CRIMSON_HANGING_SIGN = buildHangingSign(BlockCrimsonHangingSignBehavior.class, BlockId.CRIMSON_HANGING_SIGN);
        if (BlockTypes.DARK_OAK_HANGING_SIGN == null) BlockTypes.DARK_OAK_HANGING_SIGN = buildHangingSign(BlockDarkOakHangingSignBehavior.class, BlockId.DARK_OAK_HANGING_SIGN);
        if (BlockTypes.JUNGLE_HANGING_SIGN == null) BlockTypes.JUNGLE_HANGING_SIGN = buildHangingSign(BlockJungleHangingSignBehavior.class, BlockId.JUNGLE_HANGING_SIGN);
        if (BlockTypes.MANGROVE_HANGING_SIGN == null) BlockTypes.MANGROVE_HANGING_SIGN = buildHangingSign(BlockMangroveHangingSignBehavior.class, BlockId.MANGROVE_HANGING_SIGN);
        if (BlockTypes.OAK_HANGING_SIGN == null) BlockTypes.OAK_HANGING_SIGN = buildHangingSign(BlockOakHangingSignBehavior.class, BlockId.OAK_HANGING_SIGN);
        if (BlockTypes.SPRUCE_HANGING_SIGN == null) BlockTypes.SPRUCE_HANGING_SIGN = buildHangingSign(BlockSpruceHangingSignBehavior.class, BlockId.SPRUCE_HANGING_SIGN);
        if (BlockTypes.WARPED_HANGING_SIGN == null) BlockTypes.WARPED_HANGING_SIGN = buildHangingSign(BlockWarpedHangingSignBehavior.class, BlockId.WARPED_HANGING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.HANGING)
               .build();
    }

    public static void initWoods() {
        if (BlockTypes.ACACIA_WOOD == null) BlockTypes.ACACIA_WOOD = buildWood(BlockAcaciaWoodBehavior.class, BlockId.ACACIA_WOOD);
        if (BlockTypes.BIRCH_WOOD == null) BlockTypes.BIRCH_WOOD = buildWood(BlockBirchWoodBehavior.class, BlockId.BIRCH_WOOD);
        if (BlockTypes.CHERRY_WOOD == null) BlockTypes.CHERRY_WOOD = buildWood(BlockCherryWoodBehavior.class, BlockId.CHERRY_WOOD);
        if (BlockTypes.DARK_OAK_WOOD == null) BlockTypes.DARK_OAK_WOOD = buildWood(BlockDarkOakWoodBehavior.class, BlockId.DARK_OAK_WOOD);
        if (BlockTypes.JUNGLE_WOOD == null) BlockTypes.JUNGLE_WOOD = buildWood(BlockJungleWoodBehavior.class, BlockId.JUNGLE_WOOD);
        if (BlockTypes.MANGROVE_WOOD == null) BlockTypes.MANGROVE_WOOD = buildWood(BlockMangroveWoodBehavior.class, BlockId.MANGROVE_WOOD);
        if (BlockTypes.OAK_WOOD == null) BlockTypes.OAK_WOOD = buildWood(BlockOakWoodBehavior.class, BlockId.OAK_WOOD);
        if (BlockTypes.SPRUCE_WOOD == null) BlockTypes.SPRUCE_WOOD = buildWood(BlockSpruceWoodBehavior.class, BlockId.SPRUCE_WOOD);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(Class<T> clazz,
            BlockId blockId) {
        return AllayBlockType.builder(clazz)
               .vanillaBlock(blockId)
               .setProperties(BlockPropertyTypes.PILLAR_AXIS)
               .build();
    }
}
