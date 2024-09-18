package org.allaymc.server.block.type;

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
import org.allaymc.api.block.interfaces.BlockCompoundCreatorBehavior;
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
import org.allaymc.api.block.interfaces.BlockDeprecatedPurpurBlock1Behavior;
import org.allaymc.api.block.interfaces.BlockDeprecatedPurpurBlock2Behavior;
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
import org.allaymc.api.block.interfaces.BlockLabTableBehavior;
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
import org.allaymc.api.block.interfaces.BlockMaterialReducerBehavior;
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
import org.allaymc.api.block.interfaces.purpur.BlockPurpurBlockBehavior;
import org.allaymc.api.block.interfaces.purpur.BlockPurpurPillarBehavior;
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
import org.allaymc.api.block.interfaces.sponge.BlockSpongeBehavior;
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
import org.allaymc.api.block.interfaces.tnt.BlockTntBehavior;
import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.block.interfaces.BlockTrialSpawnerBehavior;
import org.allaymc.api.block.interfaces.BlockTripWireBehavior;
import org.allaymc.api.block.interfaces.BlockTripwireHookBehavior;
import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.block.interfaces.BlockTurtleEggBehavior;
import org.allaymc.api.block.interfaces.BlockTwistingVinesBehavior;
import org.allaymc.api.block.interfaces.tnt.BlockUnderwaterTntBehavior;
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
import org.allaymc.api.block.interfaces.sponge.BlockWetSpongeBehavior;
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
import org.allaymc.api.block.interfaces.element.BlockElementConstructorBehavior;
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
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchBlueBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchGreenBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchPurpleBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchRedBehavior;
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
import org.allaymc.api.block.interfaces.wall.BlockAndesiteWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockBlackstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockCobbledDeepslateWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockCobblestoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockDeepslateBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockDeepslateTileWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockDioriteWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockEndStoneBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockGraniteWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockMossyCobblestoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockMossyStoneBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockMudBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockNetherBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedBlackstoneBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedBlackstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedDeepslateWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPolishedTuffWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockPrismarineWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockRedNetherBrickWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockRedSandstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockSandstoneWallBehavior;
import org.allaymc.api.block.interfaces.wall.BlockStoneBrickWallBehavior;
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
import org.allaymc.api.block.type.BlockTypes;

public final class BlockTypeDefaultInitializer {
    public static void initAcaciaButton() {
        if (BlockTypes.ACACIA_BUTTON != null) return;
        BlockTypes.ACACIA_BUTTON = AllayBlockType
                .builder(BlockAcaciaButtonBehavior.class)
                .vanillaBlock(BlockId.ACACIA_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaDoor() {
        if (BlockTypes.ACACIA_DOOR != null) return;
        BlockTypes.ACACIA_DOOR = AllayBlockType
                .builder(BlockAcaciaDoorBehavior.class)
                .vanillaBlock(BlockId.ACACIA_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initAcaciaDoubleSlab() {
        if (BlockTypes.ACACIA_DOUBLE_SLAB != null) return;
        BlockTypes.ACACIA_DOUBLE_SLAB = AllayBlockType
                .builder(BlockAcaciaDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.ACACIA_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaFence() {
        if (BlockTypes.ACACIA_FENCE != null) return;
        BlockTypes.ACACIA_FENCE = AllayBlockType
                .builder(BlockAcaciaFenceBehavior.class)
                .vanillaBlock(BlockId.ACACIA_FENCE)
                .build();
    }

    public static void initAcaciaFenceGate() {
        if (BlockTypes.ACACIA_FENCE_GATE != null) return;
        BlockTypes.ACACIA_FENCE_GATE = AllayBlockType
                .builder(BlockAcaciaFenceGateBehavior.class)
                .vanillaBlock(BlockId.ACACIA_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initAcaciaHangingSign() {
        if (BlockTypes.ACACIA_HANGING_SIGN != null) return;
        BlockTypes.ACACIA_HANGING_SIGN = AllayBlockType
                .builder(BlockAcaciaHangingSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initAcaciaLeaves() {
        if (BlockTypes.ACACIA_LEAVES != null) return;
        BlockTypes.ACACIA_LEAVES = AllayBlockType
                .builder(BlockAcaciaLeavesBehavior.class)
                .vanillaBlock(BlockId.ACACIA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAcaciaLog() {
        if (BlockTypes.ACACIA_LOG != null) return;
        BlockTypes.ACACIA_LOG = AllayBlockType
                .builder(BlockAcaciaLogBehavior.class)
                .vanillaBlock(BlockId.ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initAcaciaPlanks() {
        if (BlockTypes.ACACIA_PLANKS != null) return;
        BlockTypes.ACACIA_PLANKS = AllayBlockType
                .builder(BlockAcaciaPlanksBehavior.class)
                .vanillaBlock(BlockId.ACACIA_PLANKS)
                .build();
    }

    public static void initAcaciaPressurePlate() {
        if (BlockTypes.ACACIA_PRESSURE_PLATE != null) return;
        BlockTypes.ACACIA_PRESSURE_PLATE = AllayBlockType
                .builder(BlockAcaciaPressurePlateBehavior.class)
                .vanillaBlock(BlockId.ACACIA_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initAcaciaSapling() {
        if (BlockTypes.ACACIA_SAPLING != null) return;
        BlockTypes.ACACIA_SAPLING = AllayBlockType
                .builder(BlockAcaciaSaplingBehavior.class)
                .vanillaBlock(BlockId.ACACIA_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initAcaciaSlab() {
        if (BlockTypes.ACACIA_SLAB != null) return;
        BlockTypes.ACACIA_SLAB = AllayBlockType
                .builder(BlockAcaciaSlabBehavior.class)
                .vanillaBlock(BlockId.ACACIA_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaStairs() {
        if (BlockTypes.ACACIA_STAIRS != null) return;
        BlockTypes.ACACIA_STAIRS = AllayBlockType
                .builder(BlockAcaciaStairsBehavior.class)
                .vanillaBlock(BlockId.ACACIA_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAcaciaStandingSign() {
        if (BlockTypes.ACACIA_STANDING_SIGN != null) return;
        BlockTypes.ACACIA_STANDING_SIGN = AllayBlockType
                .builder(BlockAcaciaStandingSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initAcaciaTrapdoor() {
        if (BlockTypes.ACACIA_TRAPDOOR != null) return;
        BlockTypes.ACACIA_TRAPDOOR = AllayBlockType
                .builder(BlockAcaciaTrapdoorBehavior.class)
                .vanillaBlock(BlockId.ACACIA_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initAcaciaWallSign() {
        if (BlockTypes.ACACIA_WALL_SIGN != null) return;
        BlockTypes.ACACIA_WALL_SIGN = AllayBlockType
                .builder(BlockAcaciaWallSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaWood() {
        if (BlockTypes.ACACIA_WOOD != null) return;
        BlockTypes.ACACIA_WOOD = AllayBlockType
                .builder(BlockAcaciaWoodBehavior.class)
                .vanillaBlock(BlockId.ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

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

    public static void initAndesiteDoubleSlab() {
        if (BlockTypes.ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.ANDESITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockAndesiteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteSlab() {
        if (BlockTypes.ANDESITE_SLAB != null) return;
        BlockTypes.ANDESITE_SLAB = AllayBlockType
                .builder(BlockAndesiteSlabBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteStairs() {
        if (BlockTypes.ANDESITE_STAIRS != null) return;
        BlockTypes.ANDESITE_STAIRS = AllayBlockType
                .builder(BlockAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAndesiteWall() {
        if (BlockTypes.ANDESITE_WALL != null) return;
        BlockTypes.ANDESITE_WALL = AllayBlockType
                .builder(BlockAndesiteWallBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initAzaleaLeaves() {
        if (BlockTypes.AZALEA_LEAVES != null) return;
        BlockTypes.AZALEA_LEAVES = AllayBlockType
                .builder(BlockAzaleaLeavesBehavior.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
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

    public static void initBambooButton() {
        if (BlockTypes.BAMBOO_BUTTON != null) return;
        BlockTypes.BAMBOO_BUTTON = AllayBlockType
                .builder(BlockBambooButtonBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBambooDoor() {
        if (BlockTypes.BAMBOO_DOOR != null) return;
        BlockTypes.BAMBOO_DOOR = AllayBlockType
                .builder(BlockBambooDoorBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBambooDoubleSlab() {
        if (BlockTypes.BAMBOO_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBambooDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooFence() {
        if (BlockTypes.BAMBOO_FENCE != null) return;
        BlockTypes.BAMBOO_FENCE = AllayBlockType
                .builder(BlockBambooFenceBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE)
                .build();
    }

    public static void initBambooFenceGate() {
        if (BlockTypes.BAMBOO_FENCE_GATE != null) return;
        BlockTypes.BAMBOO_FENCE_GATE = AllayBlockType
                .builder(BlockBambooFenceGateBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBambooHangingSign() {
        if (BlockTypes.BAMBOO_HANGING_SIGN != null) return;
        BlockTypes.BAMBOO_HANGING_SIGN = AllayBlockType
                .builder(BlockBambooHangingSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBambooMosaic() {
        if (BlockTypes.BAMBOO_MOSAIC != null) return;
        BlockTypes.BAMBOO_MOSAIC = AllayBlockType
                .builder(BlockBambooMosaicBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC)
                .build();
    }

    public static void initBambooMosaicDoubleSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBambooMosaicDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_SLAB = AllayBlockType
                .builder(BlockBambooMosaicSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicStairs() {
        if (BlockTypes.BAMBOO_MOSAIC_STAIRS != null) return;
        BlockTypes.BAMBOO_MOSAIC_STAIRS = AllayBlockType
                .builder(BlockBambooMosaicStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooPlanks() {
        if (BlockTypes.BAMBOO_PLANKS != null) return;
        BlockTypes.BAMBOO_PLANKS = AllayBlockType
                .builder(BlockBambooPlanksBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_PLANKS)
                .build();
    }

    public static void initBambooPressurePlate() {
        if (BlockTypes.BAMBOO_PRESSURE_PLATE != null) return;
        BlockTypes.BAMBOO_PRESSURE_PLATE = AllayBlockType
                .builder(BlockBambooPressurePlateBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBambooSapling() {
        if (BlockTypes.BAMBOO_SAPLING != null) return;
        BlockTypes.BAMBOO_SAPLING = AllayBlockType
                .builder(BlockBambooSaplingBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBambooSlab() {
        if (BlockTypes.BAMBOO_SLAB != null) return;
        BlockTypes.BAMBOO_SLAB = AllayBlockType
                .builder(BlockBambooSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooStairs() {
        if (BlockTypes.BAMBOO_STAIRS != null) return;
        BlockTypes.BAMBOO_STAIRS = AllayBlockType
                .builder(BlockBambooStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooStandingSign() {
        if (BlockTypes.BAMBOO_STANDING_SIGN != null) return;
        BlockTypes.BAMBOO_STANDING_SIGN = AllayBlockType
                .builder(BlockBambooStandingSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBambooTrapdoor() {
        if (BlockTypes.BAMBOO_TRAPDOOR != null) return;
        BlockTypes.BAMBOO_TRAPDOOR = AllayBlockType
                .builder(BlockBambooTrapdoorBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBambooWallSign() {
        if (BlockTypes.BAMBOO_WALL_SIGN != null) return;
        BlockTypes.BAMBOO_WALL_SIGN = AllayBlockType
                .builder(BlockBambooWallSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initBirchButton() {
        if (BlockTypes.BIRCH_BUTTON != null) return;
        BlockTypes.BIRCH_BUTTON = AllayBlockType
                .builder(BlockBirchButtonBehavior.class)
                .vanillaBlock(BlockId.BIRCH_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchDoor() {
        if (BlockTypes.BIRCH_DOOR != null) return;
        BlockTypes.BIRCH_DOOR = AllayBlockType
                .builder(BlockBirchDoorBehavior.class)
                .vanillaBlock(BlockId.BIRCH_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBirchDoubleSlab() {
        if (BlockTypes.BIRCH_DOUBLE_SLAB != null) return;
        BlockTypes.BIRCH_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBirchDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BIRCH_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchFence() {
        if (BlockTypes.BIRCH_FENCE != null) return;
        BlockTypes.BIRCH_FENCE = AllayBlockType
                .builder(BlockBirchFenceBehavior.class)
                .vanillaBlock(BlockId.BIRCH_FENCE)
                .build();
    }

    public static void initBirchFenceGate() {
        if (BlockTypes.BIRCH_FENCE_GATE != null) return;
        BlockTypes.BIRCH_FENCE_GATE = AllayBlockType
                .builder(BlockBirchFenceGateBehavior.class)
                .vanillaBlock(BlockId.BIRCH_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBirchHangingSign() {
        if (BlockTypes.BIRCH_HANGING_SIGN != null) return;
        BlockTypes.BIRCH_HANGING_SIGN = AllayBlockType
                .builder(BlockBirchHangingSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBirchLeaves() {
        if (BlockTypes.BIRCH_LEAVES != null) return;
        BlockTypes.BIRCH_LEAVES = AllayBlockType
                .builder(BlockBirchLeavesBehavior.class)
                .vanillaBlock(BlockId.BIRCH_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initBirchLog() {
        if (BlockTypes.BIRCH_LOG != null) return;
        BlockTypes.BIRCH_LOG = AllayBlockType
                .builder(BlockBirchLogBehavior.class)
                .vanillaBlock(BlockId.BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBirchPlanks() {
        if (BlockTypes.BIRCH_PLANKS != null) return;
        BlockTypes.BIRCH_PLANKS = AllayBlockType
                .builder(BlockBirchPlanksBehavior.class)
                .vanillaBlock(BlockId.BIRCH_PLANKS)
                .build();
    }

    public static void initBirchPressurePlate() {
        if (BlockTypes.BIRCH_PRESSURE_PLATE != null) return;
        BlockTypes.BIRCH_PRESSURE_PLATE = AllayBlockType
                .builder(BlockBirchPressurePlateBehavior.class)
                .vanillaBlock(BlockId.BIRCH_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBirchSapling() {
        if (BlockTypes.BIRCH_SAPLING != null) return;
        BlockTypes.BIRCH_SAPLING = AllayBlockType
                .builder(BlockBirchSaplingBehavior.class)
                .vanillaBlock(BlockId.BIRCH_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBirchSlab() {
        if (BlockTypes.BIRCH_SLAB != null) return;
        BlockTypes.BIRCH_SLAB = AllayBlockType
                .builder(BlockBirchSlabBehavior.class)
                .vanillaBlock(BlockId.BIRCH_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchStairs() {
        if (BlockTypes.BIRCH_STAIRS != null) return;
        BlockTypes.BIRCH_STAIRS = AllayBlockType
                .builder(BlockBirchStairsBehavior.class)
                .vanillaBlock(BlockId.BIRCH_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBirchStandingSign() {
        if (BlockTypes.BIRCH_STANDING_SIGN != null) return;
        BlockTypes.BIRCH_STANDING_SIGN = AllayBlockType
                .builder(BlockBirchStandingSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBirchTrapdoor() {
        if (BlockTypes.BIRCH_TRAPDOOR != null) return;
        BlockTypes.BIRCH_TRAPDOOR = AllayBlockType
                .builder(BlockBirchTrapdoorBehavior.class)
                .vanillaBlock(BlockId.BIRCH_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBirchWallSign() {
        if (BlockTypes.BIRCH_WALL_SIGN != null) return;
        BlockTypes.BIRCH_WALL_SIGN = AllayBlockType
                .builder(BlockBirchWallSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchWood() {
        if (BlockTypes.BIRCH_WOOD != null) return;
        BlockTypes.BIRCH_WOOD = AllayBlockType
                .builder(BlockBirchWoodBehavior.class)
                .vanillaBlock(BlockId.BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBlackCandle() {
        if (BlockTypes.BLACK_CANDLE != null) return;
        BlockTypes.BLACK_CANDLE = AllayBlockType
                .builder(BlockBlackCandleBehavior.class)
                .vanillaBlock(BlockId.BLACK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCandleCake() {
        if (BlockTypes.BLACK_CANDLE_CAKE != null) return;
        BlockTypes.BLACK_CANDLE_CAKE = AllayBlockType
                .builder(BlockBlackCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BLACK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCarpet() {
        if (BlockTypes.BLACK_CARPET != null) return;
        BlockTypes.BLACK_CARPET = AllayBlockType
                .builder(BlockBlackCarpetBehavior.class)
                .vanillaBlock(BlockId.BLACK_CARPET)
                .build();
    }

    public static void initBlackConcrete() {
        if (BlockTypes.BLACK_CONCRETE != null) return;
        BlockTypes.BLACK_CONCRETE = AllayBlockType
                .builder(BlockBlackConcreteBehavior.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE)
                .build();
    }

    public static void initBlackConcretePowder() {
        if (BlockTypes.BLACK_CONCRETE_POWDER != null) return;
        BlockTypes.BLACK_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBlackConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE_POWDER)
                .build();
    }

    public static void initBlackGlazedTerracotta() {
        if (BlockTypes.BLACK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLACK_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBlackGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLACK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBlackShulkerBox() {
        if (BlockTypes.BLACK_SHULKER_BOX != null) return;
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockBlackShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .build();
    }

    public static void initBlackStainedGlass() {
        if (BlockTypes.BLACK_STAINED_GLASS != null) return;
        BlockTypes.BLACK_STAINED_GLASS = AllayBlockType
                .builder(BlockBlackStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS)
                .build();
    }

    public static void initBlackStainedGlassPane() {
        if (BlockTypes.BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLACK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBlackStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlackTerracotta() {
        if (BlockTypes.BLACK_TERRACOTTA != null) return;
        BlockTypes.BLACK_TERRACOTTA = AllayBlockType
                .builder(BlockBlackTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLACK_TERRACOTTA)
                .build();
    }

    public static void initBlackWool() {
        if (BlockTypes.BLACK_WOOL != null) return;
        BlockTypes.BLACK_WOOL = AllayBlockType
                .builder(BlockBlackWoolBehavior.class)
                .vanillaBlock(BlockId.BLACK_WOOL)
                .build();
    }

    public static void initBlackstone() {
        if (BlockTypes.BLACKSTONE != null) return;
        BlockTypes.BLACKSTONE = AllayBlockType
                .builder(BlockBlackstoneBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE)
                .build();
    }

    public static void initBlackstoneDoubleSlab() {
        if (BlockTypes.BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBlackstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneSlab() {
        if (BlockTypes.BLACKSTONE_SLAB != null) return;
        BlockTypes.BLACKSTONE_SLAB = AllayBlockType
                .builder(BlockBlackstoneSlabBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneStairs() {
        if (BlockTypes.BLACKSTONE_STAIRS != null) return;
        BlockTypes.BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBlackstoneWall() {
        if (BlockTypes.BLACKSTONE_WALL != null) return;
        BlockTypes.BLACKSTONE_WALL = AllayBlockType
                .builder(BlockBlackstoneWallBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initBlueCandle() {
        if (BlockTypes.BLUE_CANDLE != null) return;
        BlockTypes.BLUE_CANDLE = AllayBlockType
                .builder(BlockBlueCandleBehavior.class)
                .vanillaBlock(BlockId.BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCandleCake() {
        if (BlockTypes.BLUE_CANDLE_CAKE != null) return;
        BlockTypes.BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockBlueCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCarpet() {
        if (BlockTypes.BLUE_CARPET != null) return;
        BlockTypes.BLUE_CARPET = AllayBlockType
                .builder(BlockBlueCarpetBehavior.class)
                .vanillaBlock(BlockId.BLUE_CARPET)
                .build();
    }

    public static void initBlueConcrete() {
        if (BlockTypes.BLUE_CONCRETE != null) return;
        BlockTypes.BLUE_CONCRETE = AllayBlockType
                .builder(BlockBlueConcreteBehavior.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE)
                .build();
    }

    public static void initBlueConcretePowder() {
        if (BlockTypes.BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.BLUE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBlueConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initBlueGlazedTerracotta() {
        if (BlockTypes.BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLUE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBlueGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initBlueShulkerBox() {
        if (BlockTypes.BLUE_SHULKER_BOX != null) return;
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .build();
    }

    public static void initBlueStainedGlass() {
        if (BlockTypes.BLUE_STAINED_GLASS != null) return;
        BlockTypes.BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS)
                .build();
    }

    public static void initBlueStainedGlassPane() {
        if (BlockTypes.BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlueTerracotta() {
        if (BlockTypes.BLUE_TERRACOTTA != null) return;
        BlockTypes.BLUE_TERRACOTTA = AllayBlockType
                .builder(BlockBlueTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLUE_TERRACOTTA)
                .build();
    }

    public static void initBlueWool() {
        if (BlockTypes.BLUE_WOOL != null) return;
        BlockTypes.BLUE_WOOL = AllayBlockType
                .builder(BlockBlueWoolBehavior.class)
                .vanillaBlock(BlockId.BLUE_WOOL)
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

    public static void initBrickDoubleSlab() {
        if (BlockTypes.BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickSlab() {
        if (BlockTypes.BRICK_SLAB != null) return;
        BlockTypes.BRICK_SLAB = AllayBlockType
                .builder(BlockBrickSlabBehavior.class)
                .vanillaBlock(BlockId.BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickStairs() {
        if (BlockTypes.BRICK_STAIRS != null) return;
        BlockTypes.BRICK_STAIRS = AllayBlockType
                .builder(BlockBrickStairsBehavior.class)
                .vanillaBlock(BlockId.BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBrickWall() {
        if (BlockTypes.BRICK_WALL != null) return;
        BlockTypes.BRICK_WALL = AllayBlockType
                .builder(BlockBrickWallBehavior.class)
                .vanillaBlock(BlockId.BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBrownCandle() {
        if (BlockTypes.BROWN_CANDLE != null) return;
        BlockTypes.BROWN_CANDLE = AllayBlockType
                .builder(BlockBrownCandleBehavior.class)
                .vanillaBlock(BlockId.BROWN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCandleCake() {
        if (BlockTypes.BROWN_CANDLE_CAKE != null) return;
        BlockTypes.BROWN_CANDLE_CAKE = AllayBlockType
                .builder(BlockBrownCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BROWN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCarpet() {
        if (BlockTypes.BROWN_CARPET != null) return;
        BlockTypes.BROWN_CARPET = AllayBlockType
                .builder(BlockBrownCarpetBehavior.class)
                .vanillaBlock(BlockId.BROWN_CARPET)
                .build();
    }

    public static void initBrownConcrete() {
        if (BlockTypes.BROWN_CONCRETE != null) return;
        BlockTypes.BROWN_CONCRETE = AllayBlockType
                .builder(BlockBrownConcreteBehavior.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE)
                .build();
    }

    public static void initBrownConcretePowder() {
        if (BlockTypes.BROWN_CONCRETE_POWDER != null) return;
        BlockTypes.BROWN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockBrownConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE_POWDER)
                .build();
    }

    public static void initBrownGlazedTerracotta() {
        if (BlockTypes.BROWN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BROWN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockBrownGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BROWN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initBrownShulkerBox() {
        if (BlockTypes.BROWN_SHULKER_BOX != null) return;
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockBrownShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .build();
    }

    public static void initBrownStainedGlass() {
        if (BlockTypes.BROWN_STAINED_GLASS != null) return;
        BlockTypes.BROWN_STAINED_GLASS = AllayBlockType
                .builder(BlockBrownStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS)
                .build();
    }

    public static void initBrownStainedGlassPane() {
        if (BlockTypes.BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.BROWN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockBrownStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBrownTerracotta() {
        if (BlockTypes.BROWN_TERRACOTTA != null) return;
        BlockTypes.BROWN_TERRACOTTA = AllayBlockType
                .builder(BlockBrownTerracottaBehavior.class)
                .vanillaBlock(BlockId.BROWN_TERRACOTTA)
                .build();
    }

    public static void initBrownWool() {
        if (BlockTypes.BROWN_WOOL != null) return;
        BlockTypes.BROWN_WOOL = AllayBlockType
                .builder(BlockBrownWoolBehavior.class)
                .vanillaBlock(BlockId.BROWN_WOOL)
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

    public static void initCherryButton() {
        if (BlockTypes.CHERRY_BUTTON != null) return;
        BlockTypes.CHERRY_BUTTON = AllayBlockType
                .builder(BlockCherryButtonBehavior.class)
                .vanillaBlock(BlockId.CHERRY_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryDoor() {
        if (BlockTypes.CHERRY_DOOR != null) return;
        BlockTypes.CHERRY_DOOR = AllayBlockType
                .builder(BlockCherryDoorBehavior.class)
                .vanillaBlock(BlockId.CHERRY_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCherryDoubleSlab() {
        if (BlockTypes.CHERRY_DOUBLE_SLAB != null) return;
        BlockTypes.CHERRY_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCherryDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CHERRY_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryFence() {
        if (BlockTypes.CHERRY_FENCE != null) return;
        BlockTypes.CHERRY_FENCE = AllayBlockType
                .builder(BlockCherryFenceBehavior.class)
                .vanillaBlock(BlockId.CHERRY_FENCE)
                .build();
    }

    public static void initCherryFenceGate() {
        if (BlockTypes.CHERRY_FENCE_GATE != null) return;
        BlockTypes.CHERRY_FENCE_GATE = AllayBlockType
                .builder(BlockCherryFenceGateBehavior.class)
                .vanillaBlock(BlockId.CHERRY_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCherryHangingSign() {
        if (BlockTypes.CHERRY_HANGING_SIGN != null) return;
        BlockTypes.CHERRY_HANGING_SIGN = AllayBlockType
                .builder(BlockCherryHangingSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCherryLeaves() {
        if (BlockTypes.CHERRY_LEAVES != null) return;
        BlockTypes.CHERRY_LEAVES = AllayBlockType
                .builder(BlockCherryLeavesBehavior.class)
                .vanillaBlock(BlockId.CHERRY_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initCherryLog() {
        if (BlockTypes.CHERRY_LOG != null) return;
        BlockTypes.CHERRY_LOG = AllayBlockType
                .builder(BlockCherryLogBehavior.class)
                .vanillaBlock(BlockId.CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCherryPlanks() {
        if (BlockTypes.CHERRY_PLANKS != null) return;
        BlockTypes.CHERRY_PLANKS = AllayBlockType
                .builder(BlockCherryPlanksBehavior.class)
                .vanillaBlock(BlockId.CHERRY_PLANKS)
                .build();
    }

    public static void initCherryPressurePlate() {
        if (BlockTypes.CHERRY_PRESSURE_PLATE != null) return;
        BlockTypes.CHERRY_PRESSURE_PLATE = AllayBlockType
                .builder(BlockCherryPressurePlateBehavior.class)
                .vanillaBlock(BlockId.CHERRY_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCherrySapling() {
        if (BlockTypes.CHERRY_SAPLING != null) return;
        BlockTypes.CHERRY_SAPLING = AllayBlockType
                .builder(BlockCherrySaplingBehavior.class)
                .vanillaBlock(BlockId.CHERRY_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initCherrySlab() {
        if (BlockTypes.CHERRY_SLAB != null) return;
        BlockTypes.CHERRY_SLAB = AllayBlockType
                .builder(BlockCherrySlabBehavior.class)
                .vanillaBlock(BlockId.CHERRY_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryStairs() {
        if (BlockTypes.CHERRY_STAIRS != null) return;
        BlockTypes.CHERRY_STAIRS = AllayBlockType
                .builder(BlockCherryStairsBehavior.class)
                .vanillaBlock(BlockId.CHERRY_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCherryStandingSign() {
        if (BlockTypes.CHERRY_STANDING_SIGN != null) return;
        BlockTypes.CHERRY_STANDING_SIGN = AllayBlockType
                .builder(BlockCherryStandingSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initCherryTrapdoor() {
        if (BlockTypes.CHERRY_TRAPDOOR != null) return;
        BlockTypes.CHERRY_TRAPDOOR = AllayBlockType
                .builder(BlockCherryTrapdoorBehavior.class)
                .vanillaBlock(BlockId.CHERRY_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCherryWallSign() {
        if (BlockTypes.CHERRY_WALL_SIGN != null) return;
        BlockTypes.CHERRY_WALL_SIGN = AllayBlockType
                .builder(BlockCherryWallSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryWood() {
        if (BlockTypes.CHERRY_WOOD != null) return;
        BlockTypes.CHERRY_WOOD = AllayBlockType
                .builder(BlockCherryWoodBehavior.class)
                .vanillaBlock(BlockId.CHERRY_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS, BlockPropertyTypes.STRIPPED_BIT)
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

    public static void initCobbledDeepslateDoubleSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_SLAB = AllayBlockType
                .builder(BlockCobbledDeepslateSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateStairs() {
        if (BlockTypes.COBBLED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockCobbledDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCobbledDeepslateWall() {
        if (BlockTypes.COBBLED_DEEPSLATE_WALL != null) return;
        BlockTypes.COBBLED_DEEPSLATE_WALL = AllayBlockType
                .builder(BlockCobbledDeepslateWallBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initCobblestone() {
        if (BlockTypes.COBBLESTONE != null) return;
        BlockTypes.COBBLESTONE = AllayBlockType
                .builder(BlockCobblestoneBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE)
                .build();
    }

    public static void initCobblestoneDoubleSlab() {
        if (BlockTypes.COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCobblestoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneSlab() {
        if (BlockTypes.COBBLESTONE_SLAB != null) return;
        BlockTypes.COBBLESTONE_SLAB = AllayBlockType
                .builder(BlockCobblestoneSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneWall() {
        if (BlockTypes.COBBLESTONE_WALL != null) return;
        BlockTypes.COBBLESTONE_WALL = AllayBlockType
                .builder(BlockCobblestoneWallBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initColoredTorchBlue() {
        if (BlockTypes.COLORED_TORCH_BLUE != null) return;
        BlockTypes.COLORED_TORCH_BLUE = AllayBlockType
                .builder(BlockColoredTorchBlueBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_BLUE)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchGreen() {
        if (BlockTypes.COLORED_TORCH_GREEN != null) return;
        BlockTypes.COLORED_TORCH_GREEN = AllayBlockType
                .builder(BlockColoredTorchGreenBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_GREEN)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchPurple() {
        if (BlockTypes.COLORED_TORCH_PURPLE != null) return;
        BlockTypes.COLORED_TORCH_PURPLE = AllayBlockType
                .builder(BlockColoredTorchPurpleBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_PURPLE)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchRed() {
        if (BlockTypes.COLORED_TORCH_RED != null) return;
        BlockTypes.COLORED_TORCH_RED = AllayBlockType
                .builder(BlockColoredTorchRedBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_RED)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
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

    public static void initCompoundCreator() {
        if (BlockTypes.COMPOUND_CREATOR != null) return;
        BlockTypes.COMPOUND_CREATOR = AllayBlockType
                .builder(BlockCompoundCreatorBehavior.class)
                .vanillaBlock(BlockId.COMPOUND_CREATOR)
                .setProperties(BlockPropertyTypes.DIRECTION)
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

    public static void initCopperDoor() {
        if (BlockTypes.COPPER_DOOR != null) return;
        BlockTypes.COPPER_DOOR = AllayBlockType
                .builder(BlockCopperDoorBehavior.class)
                .vanillaBlock(BlockId.COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
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

    public static void initCopperTrapdoor() {
        if (BlockTypes.COPPER_TRAPDOOR != null) return;
        BlockTypes.COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
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

    public static void initCrimsonButton() {
        if (BlockTypes.CRIMSON_BUTTON != null) return;
        BlockTypes.CRIMSON_BUTTON = AllayBlockType
                .builder(BlockCrimsonButtonBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCrimsonDoor() {
        if (BlockTypes.CRIMSON_DOOR != null) return;
        BlockTypes.CRIMSON_DOOR = AllayBlockType
                .builder(BlockCrimsonDoorBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCrimsonDoubleSlab() {
        if (BlockTypes.CRIMSON_DOUBLE_SLAB != null) return;
        BlockTypes.CRIMSON_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCrimsonDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonFence() {
        if (BlockTypes.CRIMSON_FENCE != null) return;
        BlockTypes.CRIMSON_FENCE = AllayBlockType
                .builder(BlockCrimsonFenceBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE)
                .build();
    }

    public static void initCrimsonFenceGate() {
        if (BlockTypes.CRIMSON_FENCE_GATE != null) return;
        BlockTypes.CRIMSON_FENCE_GATE = AllayBlockType
                .builder(BlockCrimsonFenceGateBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCrimsonFungus() {
        if (BlockTypes.CRIMSON_FUNGUS != null) return;
        BlockTypes.CRIMSON_FUNGUS = AllayBlockType
                .builder(BlockCrimsonFungusBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                .build();
    }

    public static void initCrimsonHangingSign() {
        if (BlockTypes.CRIMSON_HANGING_SIGN != null) return;
        BlockTypes.CRIMSON_HANGING_SIGN = AllayBlockType
                .builder(BlockCrimsonHangingSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
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

    public static void initCrimsonPlanks() {
        if (BlockTypes.CRIMSON_PLANKS != null) return;
        BlockTypes.CRIMSON_PLANKS = AllayBlockType
                .builder(BlockCrimsonPlanksBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_PLANKS)
                .build();
    }

    public static void initCrimsonPressurePlate() {
        if (BlockTypes.CRIMSON_PRESSURE_PLATE != null) return;
        BlockTypes.CRIMSON_PRESSURE_PLATE = AllayBlockType
                .builder(BlockCrimsonPressurePlateBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCrimsonRoots() {
        if (BlockTypes.CRIMSON_ROOTS != null) return;
        BlockTypes.CRIMSON_ROOTS = AllayBlockType
                .builder(BlockCrimsonRootsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_ROOTS)
                .build();
    }

    public static void initCrimsonSlab() {
        if (BlockTypes.CRIMSON_SLAB != null) return;
        BlockTypes.CRIMSON_SLAB = AllayBlockType
                .builder(BlockCrimsonSlabBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonStairs() {
        if (BlockTypes.CRIMSON_STAIRS != null) return;
        BlockTypes.CRIMSON_STAIRS = AllayBlockType
                .builder(BlockCrimsonStairsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCrimsonStandingSign() {
        if (BlockTypes.CRIMSON_STANDING_SIGN != null) return;
        BlockTypes.CRIMSON_STANDING_SIGN = AllayBlockType
                .builder(BlockCrimsonStandingSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
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

    public static void initCrimsonTrapdoor() {
        if (BlockTypes.CRIMSON_TRAPDOOR != null) return;
        BlockTypes.CRIMSON_TRAPDOOR = AllayBlockType
                .builder(BlockCrimsonTrapdoorBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCrimsonWallSign() {
        if (BlockTypes.CRIMSON_WALL_SIGN != null) return;
        BlockTypes.CRIMSON_WALL_SIGN = AllayBlockType
                .builder(BlockCrimsonWallSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initCutCopperSlab() {
        if (BlockTypes.CUT_COPPER_SLAB != null) return;
        BlockTypes.CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutCopperStairs() {
        if (BlockTypes.CUT_COPPER_STAIRS != null) return;
        BlockTypes.CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCutRedSandstone() {
        if (BlockTypes.CUT_RED_SANDSTONE != null) return;
        BlockTypes.CUT_RED_SANDSTONE = AllayBlockType
                .builder(BlockCutRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE)
                .build();
    }

    public static void initCutRedSandstoneDoubleSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCutRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutRedSandstoneSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockCutRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstone() {
        if (BlockTypes.CUT_SANDSTONE != null) return;
        BlockTypes.CUT_SANDSTONE = AllayBlockType
                .builder(BlockCutSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE)
                .build();
    }

    public static void initCutSandstoneDoubleSlab() {
        if (BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockCutSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstoneSlab() {
        if (BlockTypes.CUT_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockCutSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCyanCandle() {
        if (BlockTypes.CYAN_CANDLE != null) return;
        BlockTypes.CYAN_CANDLE = AllayBlockType
                .builder(BlockCyanCandleBehavior.class)
                .vanillaBlock(BlockId.CYAN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCandleCake() {
        if (BlockTypes.CYAN_CANDLE_CAKE != null) return;
        BlockTypes.CYAN_CANDLE_CAKE = AllayBlockType
                .builder(BlockCyanCandleCakeBehavior.class)
                .vanillaBlock(BlockId.CYAN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCarpet() {
        if (BlockTypes.CYAN_CARPET != null) return;
        BlockTypes.CYAN_CARPET = AllayBlockType
                .builder(BlockCyanCarpetBehavior.class)
                .vanillaBlock(BlockId.CYAN_CARPET)
                .build();
    }

    public static void initCyanConcrete() {
        if (BlockTypes.CYAN_CONCRETE != null) return;
        BlockTypes.CYAN_CONCRETE = AllayBlockType
                .builder(BlockCyanConcreteBehavior.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE)
                .build();
    }

    public static void initCyanConcretePowder() {
        if (BlockTypes.CYAN_CONCRETE_POWDER != null) return;
        BlockTypes.CYAN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockCyanConcretePowderBehavior.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE_POWDER)
                .build();
    }

    public static void initCyanGlazedTerracotta() {
        if (BlockTypes.CYAN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.CYAN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockCyanGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.CYAN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCyanShulkerBox() {
        if (BlockTypes.CYAN_SHULKER_BOX != null) return;
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockCyanShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .build();
    }

    public static void initCyanStainedGlass() {
        if (BlockTypes.CYAN_STAINED_GLASS != null) return;
        BlockTypes.CYAN_STAINED_GLASS = AllayBlockType
                .builder(BlockCyanStainedGlassBehavior.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS)
                .build();
    }

    public static void initCyanStainedGlassPane() {
        if (BlockTypes.CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.CYAN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockCyanStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initCyanTerracotta() {
        if (BlockTypes.CYAN_TERRACOTTA != null) return;
        BlockTypes.CYAN_TERRACOTTA = AllayBlockType
                .builder(BlockCyanTerracottaBehavior.class)
                .vanillaBlock(BlockId.CYAN_TERRACOTTA)
                .build();
    }

    public static void initCyanWool() {
        if (BlockTypes.CYAN_WOOL != null) return;
        BlockTypes.CYAN_WOOL = AllayBlockType
                .builder(BlockCyanWoolBehavior.class)
                .vanillaBlock(BlockId.CYAN_WOOL)
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

    public static void initDarkOakButton() {
        if (BlockTypes.DARK_OAK_BUTTON != null) return;
        BlockTypes.DARK_OAK_BUTTON = AllayBlockType
                .builder(BlockDarkOakButtonBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDarkOakDoor() {
        if (BlockTypes.DARK_OAK_DOOR != null) return;
        BlockTypes.DARK_OAK_DOOR = AllayBlockType
                .builder(BlockDarkOakDoorBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initDarkOakDoubleSlab() {
        if (BlockTypes.DARK_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDarkOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakFence() {
        if (BlockTypes.DARK_OAK_FENCE != null) return;
        BlockTypes.DARK_OAK_FENCE = AllayBlockType
                .builder(BlockDarkOakFenceBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE)
                .build();
    }

    public static void initDarkOakFenceGate() {
        if (BlockTypes.DARK_OAK_FENCE_GATE != null) return;
        BlockTypes.DARK_OAK_FENCE_GATE = AllayBlockType
                .builder(BlockDarkOakFenceGateBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initDarkOakHangingSign() {
        if (BlockTypes.DARK_OAK_HANGING_SIGN != null) return;
        BlockTypes.DARK_OAK_HANGING_SIGN = AllayBlockType
                .builder(BlockDarkOakHangingSignBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initDarkOakLeaves() {
        if (BlockTypes.DARK_OAK_LEAVES != null) return;
        BlockTypes.DARK_OAK_LEAVES = AllayBlockType
                .builder(BlockDarkOakLeavesBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initDarkOakLog() {
        if (BlockTypes.DARK_OAK_LOG != null) return;
        BlockTypes.DARK_OAK_LOG = AllayBlockType
                .builder(BlockDarkOakLogBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkOakPlanks() {
        if (BlockTypes.DARK_OAK_PLANKS != null) return;
        BlockTypes.DARK_OAK_PLANKS = AllayBlockType
                .builder(BlockDarkOakPlanksBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_PLANKS)
                .build();
    }

    public static void initDarkOakPressurePlate() {
        if (BlockTypes.DARK_OAK_PRESSURE_PLATE != null) return;
        BlockTypes.DARK_OAK_PRESSURE_PLATE = AllayBlockType
                .builder(BlockDarkOakPressurePlateBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDarkOakSapling() {
        if (BlockTypes.DARK_OAK_SAPLING != null) return;
        BlockTypes.DARK_OAK_SAPLING = AllayBlockType
                .builder(BlockDarkOakSaplingBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initDarkOakSlab() {
        if (BlockTypes.DARK_OAK_SLAB != null) return;
        BlockTypes.DARK_OAK_SLAB = AllayBlockType
                .builder(BlockDarkOakSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakStairs() {
        if (BlockTypes.DARK_OAK_STAIRS != null) return;
        BlockTypes.DARK_OAK_STAIRS = AllayBlockType
                .builder(BlockDarkOakStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkOakTrapdoor() {
        if (BlockTypes.DARK_OAK_TRAPDOOR != null) return;
        BlockTypes.DARK_OAK_TRAPDOOR = AllayBlockType
                .builder(BlockDarkOakTrapdoorBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initDarkOakWood() {
        if (BlockTypes.DARK_OAK_WOOD != null) return;
        BlockTypes.DARK_OAK_WOOD = AllayBlockType
                .builder(BlockDarkOakWoodBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkPrismarine() {
        if (BlockTypes.DARK_PRISMARINE != null) return;
        BlockTypes.DARK_PRISMARINE = AllayBlockType
                .builder(BlockDarkPrismarineBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE)
                .build();
    }

    public static void initDarkPrismarineDoubleSlab() {
        if (BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDarkPrismarineDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineSlab() {
        if (BlockTypes.DARK_PRISMARINE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_SLAB = AllayBlockType
                .builder(BlockDarkPrismarineSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineStairs() {
        if (BlockTypes.DARK_PRISMARINE_STAIRS != null) return;
        BlockTypes.DARK_PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockDarkPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkoakStandingSign() {
        if (BlockTypes.DARKOAK_STANDING_SIGN != null) return;
        BlockTypes.DARKOAK_STANDING_SIGN = AllayBlockType
                .builder(BlockDarkoakStandingSignBehavior.class)
                .vanillaBlock(BlockId.DARKOAK_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initDarkoakWallSign() {
        if (BlockTypes.DARKOAK_WALL_SIGN != null) return;
        BlockTypes.DARKOAK_WALL_SIGN = AllayBlockType
                .builder(BlockDarkoakWallSignBehavior.class)
                .vanillaBlock(BlockId.DARKOAK_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initDeepslateBrickDoubleSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_SLAB = AllayBlockType
                .builder(BlockDeepslateBrickSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickStairs() {
        if (BlockTypes.DEEPSLATE_BRICK_STAIRS != null) return;
        BlockTypes.DEEPSLATE_BRICK_STAIRS = AllayBlockType
                .builder(BlockDeepslateBrickStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateBrickWall() {
        if (BlockTypes.DEEPSLATE_BRICK_WALL != null) return;
        BlockTypes.DEEPSLATE_BRICK_WALL = AllayBlockType
                .builder(BlockDeepslateBrickWallBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initDeepslateTileDoubleSlab() {
        if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDeepslateTileDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileSlab() {
        if (BlockTypes.DEEPSLATE_TILE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_SLAB = AllayBlockType
                .builder(BlockDeepslateTileSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileStairs() {
        if (BlockTypes.DEEPSLATE_TILE_STAIRS != null) return;
        BlockTypes.DEEPSLATE_TILE_STAIRS = AllayBlockType
                .builder(BlockDeepslateTileStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateTileWall() {
        if (BlockTypes.DEEPSLATE_TILE_WALL != null) return;
        BlockTypes.DEEPSLATE_TILE_WALL = AllayBlockType
                .builder(BlockDeepslateTileWallBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initDeprecatedPurpurBlock1() {
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_1 != null) return;
        BlockTypes.DEPRECATED_PURPUR_BLOCK_1 = AllayBlockType
                .builder(BlockDeprecatedPurpurBlock1Behavior.class)
                .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_1)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDeprecatedPurpurBlock2() {
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_2 != null) return;
        BlockTypes.DEPRECATED_PURPUR_BLOCK_2 = AllayBlockType
                .builder(BlockDeprecatedPurpurBlock2Behavior.class)
                .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_2)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
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

    public static void initDioriteDoubleSlab() {
        if (BlockTypes.DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.DIORITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockDioriteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteSlab() {
        if (BlockTypes.DIORITE_SLAB != null) return;
        BlockTypes.DIORITE_SLAB = AllayBlockType
                .builder(BlockDioriteSlabBehavior.class)
                .vanillaBlock(BlockId.DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteStairs() {
        if (BlockTypes.DIORITE_STAIRS != null) return;
        BlockTypes.DIORITE_STAIRS = AllayBlockType
                .builder(BlockDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDioriteWall() {
        if (BlockTypes.DIORITE_WALL != null) return;
        BlockTypes.DIORITE_WALL = AllayBlockType
                .builder(BlockDioriteWallBehavior.class)
                .vanillaBlock(BlockId.DIORITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initDoubleCutCopperSlab() {
        if (BlockTypes.DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initElement0() {
        if (BlockTypes.ELEMENT_0 != null) return;
        BlockTypes.ELEMENT_0 = AllayBlockType
                .builder(BlockElement0Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_0)
                .build();
    }

    public static void initElement1() {
        if (BlockTypes.ELEMENT_1 != null) return;
        BlockTypes.ELEMENT_1 = AllayBlockType
                .builder(BlockElement1Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_1)
                .build();
    }

    public static void initElement10() {
        if (BlockTypes.ELEMENT_10 != null) return;
        BlockTypes.ELEMENT_10 = AllayBlockType
                .builder(BlockElement10Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_10)
                .build();
    }

    public static void initElement100() {
        if (BlockTypes.ELEMENT_100 != null) return;
        BlockTypes.ELEMENT_100 = AllayBlockType
                .builder(BlockElement100Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_100)
                .build();
    }

    public static void initElement101() {
        if (BlockTypes.ELEMENT_101 != null) return;
        BlockTypes.ELEMENT_101 = AllayBlockType
                .builder(BlockElement101Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_101)
                .build();
    }

    public static void initElement102() {
        if (BlockTypes.ELEMENT_102 != null) return;
        BlockTypes.ELEMENT_102 = AllayBlockType
                .builder(BlockElement102Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_102)
                .build();
    }

    public static void initElement103() {
        if (BlockTypes.ELEMENT_103 != null) return;
        BlockTypes.ELEMENT_103 = AllayBlockType
                .builder(BlockElement103Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_103)
                .build();
    }

    public static void initElement104() {
        if (BlockTypes.ELEMENT_104 != null) return;
        BlockTypes.ELEMENT_104 = AllayBlockType
                .builder(BlockElement104Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_104)
                .build();
    }

    public static void initElement105() {
        if (BlockTypes.ELEMENT_105 != null) return;
        BlockTypes.ELEMENT_105 = AllayBlockType
                .builder(BlockElement105Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_105)
                .build();
    }

    public static void initElement106() {
        if (BlockTypes.ELEMENT_106 != null) return;
        BlockTypes.ELEMENT_106 = AllayBlockType
                .builder(BlockElement106Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_106)
                .build();
    }

    public static void initElement107() {
        if (BlockTypes.ELEMENT_107 != null) return;
        BlockTypes.ELEMENT_107 = AllayBlockType
                .builder(BlockElement107Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_107)
                .build();
    }

    public static void initElement108() {
        if (BlockTypes.ELEMENT_108 != null) return;
        BlockTypes.ELEMENT_108 = AllayBlockType
                .builder(BlockElement108Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_108)
                .build();
    }

    public static void initElement109() {
        if (BlockTypes.ELEMENT_109 != null) return;
        BlockTypes.ELEMENT_109 = AllayBlockType
                .builder(BlockElement109Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_109)
                .build();
    }

    public static void initElement11() {
        if (BlockTypes.ELEMENT_11 != null) return;
        BlockTypes.ELEMENT_11 = AllayBlockType
                .builder(BlockElement11Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_11)
                .build();
    }

    public static void initElement110() {
        if (BlockTypes.ELEMENT_110 != null) return;
        BlockTypes.ELEMENT_110 = AllayBlockType
                .builder(BlockElement110Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_110)
                .build();
    }

    public static void initElement111() {
        if (BlockTypes.ELEMENT_111 != null) return;
        BlockTypes.ELEMENT_111 = AllayBlockType
                .builder(BlockElement111Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_111)
                .build();
    }

    public static void initElement112() {
        if (BlockTypes.ELEMENT_112 != null) return;
        BlockTypes.ELEMENT_112 = AllayBlockType
                .builder(BlockElement112Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_112)
                .build();
    }

    public static void initElement113() {
        if (BlockTypes.ELEMENT_113 != null) return;
        BlockTypes.ELEMENT_113 = AllayBlockType
                .builder(BlockElement113Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_113)
                .build();
    }

    public static void initElement114() {
        if (BlockTypes.ELEMENT_114 != null) return;
        BlockTypes.ELEMENT_114 = AllayBlockType
                .builder(BlockElement114Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_114)
                .build();
    }

    public static void initElement115() {
        if (BlockTypes.ELEMENT_115 != null) return;
        BlockTypes.ELEMENT_115 = AllayBlockType
                .builder(BlockElement115Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_115)
                .build();
    }

    public static void initElement116() {
        if (BlockTypes.ELEMENT_116 != null) return;
        BlockTypes.ELEMENT_116 = AllayBlockType
                .builder(BlockElement116Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_116)
                .build();
    }

    public static void initElement117() {
        if (BlockTypes.ELEMENT_117 != null) return;
        BlockTypes.ELEMENT_117 = AllayBlockType
                .builder(BlockElement117Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_117)
                .build();
    }

    public static void initElement118() {
        if (BlockTypes.ELEMENT_118 != null) return;
        BlockTypes.ELEMENT_118 = AllayBlockType
                .builder(BlockElement118Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_118)
                .build();
    }

    public static void initElement12() {
        if (BlockTypes.ELEMENT_12 != null) return;
        BlockTypes.ELEMENT_12 = AllayBlockType
                .builder(BlockElement12Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_12)
                .build();
    }

    public static void initElement13() {
        if (BlockTypes.ELEMENT_13 != null) return;
        BlockTypes.ELEMENT_13 = AllayBlockType
                .builder(BlockElement13Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_13)
                .build();
    }

    public static void initElement14() {
        if (BlockTypes.ELEMENT_14 != null) return;
        BlockTypes.ELEMENT_14 = AllayBlockType
                .builder(BlockElement14Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_14)
                .build();
    }

    public static void initElement15() {
        if (BlockTypes.ELEMENT_15 != null) return;
        BlockTypes.ELEMENT_15 = AllayBlockType
                .builder(BlockElement15Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_15)
                .build();
    }

    public static void initElement16() {
        if (BlockTypes.ELEMENT_16 != null) return;
        BlockTypes.ELEMENT_16 = AllayBlockType
                .builder(BlockElement16Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_16)
                .build();
    }

    public static void initElement17() {
        if (BlockTypes.ELEMENT_17 != null) return;
        BlockTypes.ELEMENT_17 = AllayBlockType
                .builder(BlockElement17Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_17)
                .build();
    }

    public static void initElement18() {
        if (BlockTypes.ELEMENT_18 != null) return;
        BlockTypes.ELEMENT_18 = AllayBlockType
                .builder(BlockElement18Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_18)
                .build();
    }

    public static void initElement19() {
        if (BlockTypes.ELEMENT_19 != null) return;
        BlockTypes.ELEMENT_19 = AllayBlockType
                .builder(BlockElement19Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_19)
                .build();
    }

    public static void initElement2() {
        if (BlockTypes.ELEMENT_2 != null) return;
        BlockTypes.ELEMENT_2 = AllayBlockType
                .builder(BlockElement2Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_2)
                .build();
    }

    public static void initElement20() {
        if (BlockTypes.ELEMENT_20 != null) return;
        BlockTypes.ELEMENT_20 = AllayBlockType
                .builder(BlockElement20Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_20)
                .build();
    }

    public static void initElement21() {
        if (BlockTypes.ELEMENT_21 != null) return;
        BlockTypes.ELEMENT_21 = AllayBlockType
                .builder(BlockElement21Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_21)
                .build();
    }

    public static void initElement22() {
        if (BlockTypes.ELEMENT_22 != null) return;
        BlockTypes.ELEMENT_22 = AllayBlockType
                .builder(BlockElement22Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_22)
                .build();
    }

    public static void initElement23() {
        if (BlockTypes.ELEMENT_23 != null) return;
        BlockTypes.ELEMENT_23 = AllayBlockType
                .builder(BlockElement23Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_23)
                .build();
    }

    public static void initElement24() {
        if (BlockTypes.ELEMENT_24 != null) return;
        BlockTypes.ELEMENT_24 = AllayBlockType
                .builder(BlockElement24Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_24)
                .build();
    }

    public static void initElement25() {
        if (BlockTypes.ELEMENT_25 != null) return;
        BlockTypes.ELEMENT_25 = AllayBlockType
                .builder(BlockElement25Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_25)
                .build();
    }

    public static void initElement26() {
        if (BlockTypes.ELEMENT_26 != null) return;
        BlockTypes.ELEMENT_26 = AllayBlockType
                .builder(BlockElement26Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_26)
                .build();
    }

    public static void initElement27() {
        if (BlockTypes.ELEMENT_27 != null) return;
        BlockTypes.ELEMENT_27 = AllayBlockType
                .builder(BlockElement27Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_27)
                .build();
    }

    public static void initElement28() {
        if (BlockTypes.ELEMENT_28 != null) return;
        BlockTypes.ELEMENT_28 = AllayBlockType
                .builder(BlockElement28Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_28)
                .build();
    }

    public static void initElement29() {
        if (BlockTypes.ELEMENT_29 != null) return;
        BlockTypes.ELEMENT_29 = AllayBlockType
                .builder(BlockElement29Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_29)
                .build();
    }

    public static void initElement3() {
        if (BlockTypes.ELEMENT_3 != null) return;
        BlockTypes.ELEMENT_3 = AllayBlockType
                .builder(BlockElement3Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_3)
                .build();
    }

    public static void initElement30() {
        if (BlockTypes.ELEMENT_30 != null) return;
        BlockTypes.ELEMENT_30 = AllayBlockType
                .builder(BlockElement30Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_30)
                .build();
    }

    public static void initElement31() {
        if (BlockTypes.ELEMENT_31 != null) return;
        BlockTypes.ELEMENT_31 = AllayBlockType
                .builder(BlockElement31Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_31)
                .build();
    }

    public static void initElement32() {
        if (BlockTypes.ELEMENT_32 != null) return;
        BlockTypes.ELEMENT_32 = AllayBlockType
                .builder(BlockElement32Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_32)
                .build();
    }

    public static void initElement33() {
        if (BlockTypes.ELEMENT_33 != null) return;
        BlockTypes.ELEMENT_33 = AllayBlockType
                .builder(BlockElement33Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_33)
                .build();
    }

    public static void initElement34() {
        if (BlockTypes.ELEMENT_34 != null) return;
        BlockTypes.ELEMENT_34 = AllayBlockType
                .builder(BlockElement34Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_34)
                .build();
    }

    public static void initElement35() {
        if (BlockTypes.ELEMENT_35 != null) return;
        BlockTypes.ELEMENT_35 = AllayBlockType
                .builder(BlockElement35Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_35)
                .build();
    }

    public static void initElement36() {
        if (BlockTypes.ELEMENT_36 != null) return;
        BlockTypes.ELEMENT_36 = AllayBlockType
                .builder(BlockElement36Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_36)
                .build();
    }

    public static void initElement37() {
        if (BlockTypes.ELEMENT_37 != null) return;
        BlockTypes.ELEMENT_37 = AllayBlockType
                .builder(BlockElement37Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_37)
                .build();
    }

    public static void initElement38() {
        if (BlockTypes.ELEMENT_38 != null) return;
        BlockTypes.ELEMENT_38 = AllayBlockType
                .builder(BlockElement38Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_38)
                .build();
    }

    public static void initElement39() {
        if (BlockTypes.ELEMENT_39 != null) return;
        BlockTypes.ELEMENT_39 = AllayBlockType
                .builder(BlockElement39Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_39)
                .build();
    }

    public static void initElement4() {
        if (BlockTypes.ELEMENT_4 != null) return;
        BlockTypes.ELEMENT_4 = AllayBlockType
                .builder(BlockElement4Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_4)
                .build();
    }

    public static void initElement40() {
        if (BlockTypes.ELEMENT_40 != null) return;
        BlockTypes.ELEMENT_40 = AllayBlockType
                .builder(BlockElement40Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_40)
                .build();
    }

    public static void initElement41() {
        if (BlockTypes.ELEMENT_41 != null) return;
        BlockTypes.ELEMENT_41 = AllayBlockType
                .builder(BlockElement41Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_41)
                .build();
    }

    public static void initElement42() {
        if (BlockTypes.ELEMENT_42 != null) return;
        BlockTypes.ELEMENT_42 = AllayBlockType
                .builder(BlockElement42Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_42)
                .build();
    }

    public static void initElement43() {
        if (BlockTypes.ELEMENT_43 != null) return;
        BlockTypes.ELEMENT_43 = AllayBlockType
                .builder(BlockElement43Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_43)
                .build();
    }

    public static void initElement44() {
        if (BlockTypes.ELEMENT_44 != null) return;
        BlockTypes.ELEMENT_44 = AllayBlockType
                .builder(BlockElement44Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_44)
                .build();
    }

    public static void initElement45() {
        if (BlockTypes.ELEMENT_45 != null) return;
        BlockTypes.ELEMENT_45 = AllayBlockType
                .builder(BlockElement45Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_45)
                .build();
    }

    public static void initElement46() {
        if (BlockTypes.ELEMENT_46 != null) return;
        BlockTypes.ELEMENT_46 = AllayBlockType
                .builder(BlockElement46Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_46)
                .build();
    }

    public static void initElement47() {
        if (BlockTypes.ELEMENT_47 != null) return;
        BlockTypes.ELEMENT_47 = AllayBlockType
                .builder(BlockElement47Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_47)
                .build();
    }

    public static void initElement48() {
        if (BlockTypes.ELEMENT_48 != null) return;
        BlockTypes.ELEMENT_48 = AllayBlockType
                .builder(BlockElement48Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_48)
                .build();
    }

    public static void initElement49() {
        if (BlockTypes.ELEMENT_49 != null) return;
        BlockTypes.ELEMENT_49 = AllayBlockType
                .builder(BlockElement49Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_49)
                .build();
    }

    public static void initElement5() {
        if (BlockTypes.ELEMENT_5 != null) return;
        BlockTypes.ELEMENT_5 = AllayBlockType
                .builder(BlockElement5Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_5)
                .build();
    }

    public static void initElement50() {
        if (BlockTypes.ELEMENT_50 != null) return;
        BlockTypes.ELEMENT_50 = AllayBlockType
                .builder(BlockElement50Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_50)
                .build();
    }

    public static void initElement51() {
        if (BlockTypes.ELEMENT_51 != null) return;
        BlockTypes.ELEMENT_51 = AllayBlockType
                .builder(BlockElement51Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_51)
                .build();
    }

    public static void initElement52() {
        if (BlockTypes.ELEMENT_52 != null) return;
        BlockTypes.ELEMENT_52 = AllayBlockType
                .builder(BlockElement52Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_52)
                .build();
    }

    public static void initElement53() {
        if (BlockTypes.ELEMENT_53 != null) return;
        BlockTypes.ELEMENT_53 = AllayBlockType
                .builder(BlockElement53Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_53)
                .build();
    }

    public static void initElement54() {
        if (BlockTypes.ELEMENT_54 != null) return;
        BlockTypes.ELEMENT_54 = AllayBlockType
                .builder(BlockElement54Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_54)
                .build();
    }

    public static void initElement55() {
        if (BlockTypes.ELEMENT_55 != null) return;
        BlockTypes.ELEMENT_55 = AllayBlockType
                .builder(BlockElement55Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_55)
                .build();
    }

    public static void initElement56() {
        if (BlockTypes.ELEMENT_56 != null) return;
        BlockTypes.ELEMENT_56 = AllayBlockType
                .builder(BlockElement56Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_56)
                .build();
    }

    public static void initElement57() {
        if (BlockTypes.ELEMENT_57 != null) return;
        BlockTypes.ELEMENT_57 = AllayBlockType
                .builder(BlockElement57Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_57)
                .build();
    }

    public static void initElement58() {
        if (BlockTypes.ELEMENT_58 != null) return;
        BlockTypes.ELEMENT_58 = AllayBlockType
                .builder(BlockElement58Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_58)
                .build();
    }

    public static void initElement59() {
        if (BlockTypes.ELEMENT_59 != null) return;
        BlockTypes.ELEMENT_59 = AllayBlockType
                .builder(BlockElement59Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_59)
                .build();
    }

    public static void initElement6() {
        if (BlockTypes.ELEMENT_6 != null) return;
        BlockTypes.ELEMENT_6 = AllayBlockType
                .builder(BlockElement6Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_6)
                .build();
    }

    public static void initElement60() {
        if (BlockTypes.ELEMENT_60 != null) return;
        BlockTypes.ELEMENT_60 = AllayBlockType
                .builder(BlockElement60Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_60)
                .build();
    }

    public static void initElement61() {
        if (BlockTypes.ELEMENT_61 != null) return;
        BlockTypes.ELEMENT_61 = AllayBlockType
                .builder(BlockElement61Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_61)
                .build();
    }

    public static void initElement62() {
        if (BlockTypes.ELEMENT_62 != null) return;
        BlockTypes.ELEMENT_62 = AllayBlockType
                .builder(BlockElement62Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_62)
                .build();
    }

    public static void initElement63() {
        if (BlockTypes.ELEMENT_63 != null) return;
        BlockTypes.ELEMENT_63 = AllayBlockType
                .builder(BlockElement63Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_63)
                .build();
    }

    public static void initElement64() {
        if (BlockTypes.ELEMENT_64 != null) return;
        BlockTypes.ELEMENT_64 = AllayBlockType
                .builder(BlockElement64Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_64)
                .build();
    }

    public static void initElement65() {
        if (BlockTypes.ELEMENT_65 != null) return;
        BlockTypes.ELEMENT_65 = AllayBlockType
                .builder(BlockElement65Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_65)
                .build();
    }

    public static void initElement66() {
        if (BlockTypes.ELEMENT_66 != null) return;
        BlockTypes.ELEMENT_66 = AllayBlockType
                .builder(BlockElement66Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_66)
                .build();
    }

    public static void initElement67() {
        if (BlockTypes.ELEMENT_67 != null) return;
        BlockTypes.ELEMENT_67 = AllayBlockType
                .builder(BlockElement67Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_67)
                .build();
    }

    public static void initElement68() {
        if (BlockTypes.ELEMENT_68 != null) return;
        BlockTypes.ELEMENT_68 = AllayBlockType
                .builder(BlockElement68Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_68)
                .build();
    }

    public static void initElement69() {
        if (BlockTypes.ELEMENT_69 != null) return;
        BlockTypes.ELEMENT_69 = AllayBlockType
                .builder(BlockElement69Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_69)
                .build();
    }

    public static void initElement7() {
        if (BlockTypes.ELEMENT_7 != null) return;
        BlockTypes.ELEMENT_7 = AllayBlockType
                .builder(BlockElement7Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_7)
                .build();
    }

    public static void initElement70() {
        if (BlockTypes.ELEMENT_70 != null) return;
        BlockTypes.ELEMENT_70 = AllayBlockType
                .builder(BlockElement70Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_70)
                .build();
    }

    public static void initElement71() {
        if (BlockTypes.ELEMENT_71 != null) return;
        BlockTypes.ELEMENT_71 = AllayBlockType
                .builder(BlockElement71Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_71)
                .build();
    }

    public static void initElement72() {
        if (BlockTypes.ELEMENT_72 != null) return;
        BlockTypes.ELEMENT_72 = AllayBlockType
                .builder(BlockElement72Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_72)
                .build();
    }

    public static void initElement73() {
        if (BlockTypes.ELEMENT_73 != null) return;
        BlockTypes.ELEMENT_73 = AllayBlockType
                .builder(BlockElement73Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_73)
                .build();
    }

    public static void initElement74() {
        if (BlockTypes.ELEMENT_74 != null) return;
        BlockTypes.ELEMENT_74 = AllayBlockType
                .builder(BlockElement74Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_74)
                .build();
    }

    public static void initElement75() {
        if (BlockTypes.ELEMENT_75 != null) return;
        BlockTypes.ELEMENT_75 = AllayBlockType
                .builder(BlockElement75Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_75)
                .build();
    }

    public static void initElement76() {
        if (BlockTypes.ELEMENT_76 != null) return;
        BlockTypes.ELEMENT_76 = AllayBlockType
                .builder(BlockElement76Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_76)
                .build();
    }

    public static void initElement77() {
        if (BlockTypes.ELEMENT_77 != null) return;
        BlockTypes.ELEMENT_77 = AllayBlockType
                .builder(BlockElement77Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_77)
                .build();
    }

    public static void initElement78() {
        if (BlockTypes.ELEMENT_78 != null) return;
        BlockTypes.ELEMENT_78 = AllayBlockType
                .builder(BlockElement78Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_78)
                .build();
    }

    public static void initElement79() {
        if (BlockTypes.ELEMENT_79 != null) return;
        BlockTypes.ELEMENT_79 = AllayBlockType
                .builder(BlockElement79Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_79)
                .build();
    }

    public static void initElement8() {
        if (BlockTypes.ELEMENT_8 != null) return;
        BlockTypes.ELEMENT_8 = AllayBlockType
                .builder(BlockElement8Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_8)
                .build();
    }

    public static void initElement80() {
        if (BlockTypes.ELEMENT_80 != null) return;
        BlockTypes.ELEMENT_80 = AllayBlockType
                .builder(BlockElement80Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_80)
                .build();
    }

    public static void initElement81() {
        if (BlockTypes.ELEMENT_81 != null) return;
        BlockTypes.ELEMENT_81 = AllayBlockType
                .builder(BlockElement81Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_81)
                .build();
    }

    public static void initElement82() {
        if (BlockTypes.ELEMENT_82 != null) return;
        BlockTypes.ELEMENT_82 = AllayBlockType
                .builder(BlockElement82Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_82)
                .build();
    }

    public static void initElement83() {
        if (BlockTypes.ELEMENT_83 != null) return;
        BlockTypes.ELEMENT_83 = AllayBlockType
                .builder(BlockElement83Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_83)
                .build();
    }

    public static void initElement84() {
        if (BlockTypes.ELEMENT_84 != null) return;
        BlockTypes.ELEMENT_84 = AllayBlockType
                .builder(BlockElement84Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_84)
                .build();
    }

    public static void initElement85() {
        if (BlockTypes.ELEMENT_85 != null) return;
        BlockTypes.ELEMENT_85 = AllayBlockType
                .builder(BlockElement85Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_85)
                .build();
    }

    public static void initElement86() {
        if (BlockTypes.ELEMENT_86 != null) return;
        BlockTypes.ELEMENT_86 = AllayBlockType
                .builder(BlockElement86Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_86)
                .build();
    }

    public static void initElement87() {
        if (BlockTypes.ELEMENT_87 != null) return;
        BlockTypes.ELEMENT_87 = AllayBlockType
                .builder(BlockElement87Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_87)
                .build();
    }

    public static void initElement88() {
        if (BlockTypes.ELEMENT_88 != null) return;
        BlockTypes.ELEMENT_88 = AllayBlockType
                .builder(BlockElement88Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_88)
                .build();
    }

    public static void initElement89() {
        if (BlockTypes.ELEMENT_89 != null) return;
        BlockTypes.ELEMENT_89 = AllayBlockType
                .builder(BlockElement89Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_89)
                .build();
    }

    public static void initElement9() {
        if (BlockTypes.ELEMENT_9 != null) return;
        BlockTypes.ELEMENT_9 = AllayBlockType
                .builder(BlockElement9Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_9)
                .build();
    }

    public static void initElement90() {
        if (BlockTypes.ELEMENT_90 != null) return;
        BlockTypes.ELEMENT_90 = AllayBlockType
                .builder(BlockElement90Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_90)
                .build();
    }

    public static void initElement91() {
        if (BlockTypes.ELEMENT_91 != null) return;
        BlockTypes.ELEMENT_91 = AllayBlockType
                .builder(BlockElement91Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_91)
                .build();
    }

    public static void initElement92() {
        if (BlockTypes.ELEMENT_92 != null) return;
        BlockTypes.ELEMENT_92 = AllayBlockType
                .builder(BlockElement92Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_92)
                .build();
    }

    public static void initElement93() {
        if (BlockTypes.ELEMENT_93 != null) return;
        BlockTypes.ELEMENT_93 = AllayBlockType
                .builder(BlockElement93Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_93)
                .build();
    }

    public static void initElement94() {
        if (BlockTypes.ELEMENT_94 != null) return;
        BlockTypes.ELEMENT_94 = AllayBlockType
                .builder(BlockElement94Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_94)
                .build();
    }

    public static void initElement95() {
        if (BlockTypes.ELEMENT_95 != null) return;
        BlockTypes.ELEMENT_95 = AllayBlockType
                .builder(BlockElement95Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_95)
                .build();
    }

    public static void initElement96() {
        if (BlockTypes.ELEMENT_96 != null) return;
        BlockTypes.ELEMENT_96 = AllayBlockType
                .builder(BlockElement96Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_96)
                .build();
    }

    public static void initElement97() {
        if (BlockTypes.ELEMENT_97 != null) return;
        BlockTypes.ELEMENT_97 = AllayBlockType
                .builder(BlockElement97Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_97)
                .build();
    }

    public static void initElement98() {
        if (BlockTypes.ELEMENT_98 != null) return;
        BlockTypes.ELEMENT_98 = AllayBlockType
                .builder(BlockElement98Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_98)
                .build();
    }

    public static void initElement99() {
        if (BlockTypes.ELEMENT_99 != null) return;
        BlockTypes.ELEMENT_99 = AllayBlockType
                .builder(BlockElement99Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_99)
                .build();
    }

    public static void initElementConstructor() {
        if (BlockTypes.ELEMENT_CONSTRUCTOR != null) return;
        BlockTypes.ELEMENT_CONSTRUCTOR = AllayBlockType
                .builder(BlockElementConstructorBehavior.class)
                .vanillaBlock(BlockId.ELEMENT_CONSTRUCTOR)
                .setProperties(BlockPropertyTypes.DIRECTION)
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

    public static void initEndBrickStairs() {
        if (BlockTypes.END_BRICK_STAIRS != null) return;
        BlockTypes.END_BRICK_STAIRS = AllayBlockType
                .builder(BlockEndBrickStairsBehavior.class)
                .vanillaBlock(BlockId.END_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
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

    public static void initEndStoneBrickDoubleSlab() {
        if (BlockTypes.END_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockEndStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEndStoneBrickSlab() {
        if (BlockTypes.END_STONE_BRICK_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockEndStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEndStoneBrickWall() {
        if (BlockTypes.END_STONE_BRICK_WALL != null) return;
        BlockTypes.END_STONE_BRICK_WALL = AllayBlockType
                .builder(BlockEndStoneBrickWallBehavior.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initExposedCopperBulb() {
        if (BlockTypes.EXPOSED_COPPER_BULB != null) return;
        BlockTypes.EXPOSED_COPPER_BULB = AllayBlockType
                .builder(BlockExposedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initExposedCopperDoor() {
        if (BlockTypes.EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.EXPOSED_COPPER_DOOR = AllayBlockType
                .builder(BlockExposedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initExposedCopperGrate() {
        if (BlockTypes.EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.EXPOSED_COPPER_GRATE = AllayBlockType
                .builder(BlockExposedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initExposedCopperTrapdoor() {
        if (BlockTypes.EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockExposedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initExposedCutCopper() {
        if (BlockTypes.EXPOSED_CUT_COPPER != null) return;
        BlockTypes.EXPOSED_CUT_COPPER = AllayBlockType
                .builder(BlockExposedCutCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initExposedCutCopperSlab() {
        if (BlockTypes.EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockExposedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initExposedCutCopperStairs() {
        if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initExposedDoubleCutCopperSlab() {
        if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockExposedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initGraniteDoubleSlab() {
        if (BlockTypes.GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.GRANITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockGraniteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteSlab() {
        if (BlockTypes.GRANITE_SLAB != null) return;
        BlockTypes.GRANITE_SLAB = AllayBlockType
                .builder(BlockGraniteSlabBehavior.class)
                .vanillaBlock(BlockId.GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteStairs() {
        if (BlockTypes.GRANITE_STAIRS != null) return;
        BlockTypes.GRANITE_STAIRS = AllayBlockType
                .builder(BlockGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initGraniteWall() {
        if (BlockTypes.GRANITE_WALL != null) return;
        BlockTypes.GRANITE_WALL = AllayBlockType
                .builder(BlockGraniteWallBehavior.class)
                .vanillaBlock(BlockId.GRANITE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initGrayCandle() {
        if (BlockTypes.GRAY_CANDLE != null) return;
        BlockTypes.GRAY_CANDLE = AllayBlockType
                .builder(BlockGrayCandleBehavior.class)
                .vanillaBlock(BlockId.GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCandleCake() {
        if (BlockTypes.GRAY_CANDLE_CAKE != null) return;
        BlockTypes.GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockGrayCandleCakeBehavior.class)
                .vanillaBlock(BlockId.GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCarpet() {
        if (BlockTypes.GRAY_CARPET != null) return;
        BlockTypes.GRAY_CARPET = AllayBlockType
                .builder(BlockGrayCarpetBehavior.class)
                .vanillaBlock(BlockId.GRAY_CARPET)
                .build();
    }

    public static void initGrayConcrete() {
        if (BlockTypes.GRAY_CONCRETE != null) return;
        BlockTypes.GRAY_CONCRETE = AllayBlockType
                .builder(BlockGrayConcreteBehavior.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE)
                .build();
    }

    public static void initGrayConcretePowder() {
        if (BlockTypes.GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.GRAY_CONCRETE_POWDER = AllayBlockType
                .builder(BlockGrayConcretePowderBehavior.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initGrayGlazedTerracotta() {
        if (BlockTypes.GRAY_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GRAY_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockGrayGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.GRAY_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGrayShulkerBox() {
        if (BlockTypes.GRAY_SHULKER_BOX != null) return;
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .build();
    }

    public static void initGrayStainedGlass() {
        if (BlockTypes.GRAY_STAINED_GLASS != null) return;
        BlockTypes.GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS)
                .build();
    }

    public static void initGrayStainedGlassPane() {
        if (BlockTypes.GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGrayTerracotta() {
        if (BlockTypes.GRAY_TERRACOTTA != null) return;
        BlockTypes.GRAY_TERRACOTTA = AllayBlockType
                .builder(BlockGrayTerracottaBehavior.class)
                .vanillaBlock(BlockId.GRAY_TERRACOTTA)
                .build();
    }

    public static void initGrayWool() {
        if (BlockTypes.GRAY_WOOL != null) return;
        BlockTypes.GRAY_WOOL = AllayBlockType
                .builder(BlockGrayWoolBehavior.class)
                .vanillaBlock(BlockId.GRAY_WOOL)
                .build();
    }

    public static void initGreenCandle() {
        if (BlockTypes.GREEN_CANDLE != null) return;
        BlockTypes.GREEN_CANDLE = AllayBlockType
                .builder(BlockGreenCandleBehavior.class)
                .vanillaBlock(BlockId.GREEN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCandleCake() {
        if (BlockTypes.GREEN_CANDLE_CAKE != null) return;
        BlockTypes.GREEN_CANDLE_CAKE = AllayBlockType
                .builder(BlockGreenCandleCakeBehavior.class)
                .vanillaBlock(BlockId.GREEN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCarpet() {
        if (BlockTypes.GREEN_CARPET != null) return;
        BlockTypes.GREEN_CARPET = AllayBlockType
                .builder(BlockGreenCarpetBehavior.class)
                .vanillaBlock(BlockId.GREEN_CARPET)
                .build();
    }

    public static void initGreenConcrete() {
        if (BlockTypes.GREEN_CONCRETE != null) return;
        BlockTypes.GREEN_CONCRETE = AllayBlockType
                .builder(BlockGreenConcreteBehavior.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE)
                .build();
    }

    public static void initGreenConcretePowder() {
        if (BlockTypes.GREEN_CONCRETE_POWDER != null) return;
        BlockTypes.GREEN_CONCRETE_POWDER = AllayBlockType
                .builder(BlockGreenConcretePowderBehavior.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE_POWDER)
                .build();
    }

    public static void initGreenGlazedTerracotta() {
        if (BlockTypes.GREEN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GREEN_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockGreenGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.GREEN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGreenShulkerBox() {
        if (BlockTypes.GREEN_SHULKER_BOX != null) return;
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockGreenShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .build();
    }

    public static void initGreenStainedGlass() {
        if (BlockTypes.GREEN_STAINED_GLASS != null) return;
        BlockTypes.GREEN_STAINED_GLASS = AllayBlockType
                .builder(BlockGreenStainedGlassBehavior.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS)
                .build();
    }

    public static void initGreenStainedGlassPane() {
        if (BlockTypes.GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.GREEN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockGreenStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGreenTerracotta() {
        if (BlockTypes.GREEN_TERRACOTTA != null) return;
        BlockTypes.GREEN_TERRACOTTA = AllayBlockType
                .builder(BlockGreenTerracottaBehavior.class)
                .vanillaBlock(BlockId.GREEN_TERRACOTTA)
                .build();
    }

    public static void initGreenWool() {
        if (BlockTypes.GREEN_WOOL != null) return;
        BlockTypes.GREEN_WOOL = AllayBlockType
                .builder(BlockGreenWoolBehavior.class)
                .vanillaBlock(BlockId.GREEN_WOOL)
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

    public static void initHardBlackStainedGlass() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBlackStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS)
                .build();
    }

    public static void initHardBlackStainedGlassPane() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBlackStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBlueStainedGlass() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardBlueStainedGlassPane() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBrownStainedGlass() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardBrownStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS)
                .build();
    }

    public static void initHardBrownStainedGlassPane() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardBrownStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardCyanStainedGlass() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardCyanStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS)
                .build();
    }

    public static void initHardCyanStainedGlassPane() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardCyanStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS_PANE)
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

    public static void initHardGrayStainedGlass() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockHardGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardGrayStainedGlassPane() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGreenStainedGlass() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS = AllayBlockType
                .builder(BlockHardGreenStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS)
                .build();
    }

    public static void initHardGreenStainedGlassPane() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardGreenStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightBlueStainedGlass() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLightBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardLightBlueStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLightBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightGrayStainedGlass() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLightGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardLightGrayStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLightGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLimeStainedGlass() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS = AllayBlockType
                .builder(BlockHardLimeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS)
                .build();
    }

    public static void initHardLimeStainedGlassPane() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardLimeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardMagentaStainedGlass() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS = AllayBlockType
                .builder(BlockHardMagentaStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initHardMagentaStainedGlassPane() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardMagentaStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardOrangeStainedGlass() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardOrangeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initHardOrangeStainedGlassPane() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardOrangeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPinkStainedGlass() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS = AllayBlockType
                .builder(BlockHardPinkStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS)
                .build();
    }

    public static void initHardPinkStainedGlassPane() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardPinkStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPurpleStainedGlass() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardPurpleStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initHardPurpleStainedGlassPane() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardPurpleStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardRedStainedGlass() {
        if (BlockTypes.HARD_RED_STAINED_GLASS != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS = AllayBlockType
                .builder(BlockHardRedStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS)
                .build();
    }

    public static void initHardRedStainedGlassPane() {
        if (BlockTypes.HARD_RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardRedStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardWhiteStainedGlass() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS = AllayBlockType
                .builder(BlockHardWhiteStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS)
                .build();
    }

    public static void initHardWhiteStainedGlassPane() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardWhiteStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardYellowStainedGlass() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS = AllayBlockType
                .builder(BlockHardYellowStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initHardYellowStainedGlassPane() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockHardYellowStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS_PANE)
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

    public static void initHeavyWeightedPressurePlate() {
        if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockHeavyWeightedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
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

    public static void initIronDoor() {
        if (BlockTypes.IRON_DOOR != null) return;
        BlockTypes.IRON_DOOR = AllayBlockType
                .builder(BlockIronDoorBehavior.class)
                .vanillaBlock(BlockId.IRON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initIronOre() {
        if (BlockTypes.IRON_ORE != null) return;
        BlockTypes.IRON_ORE = AllayBlockType
                .builder(BlockIronOreBehavior.class)
                .vanillaBlock(BlockId.IRON_ORE)
                .build();
    }

    public static void initIronTrapdoor() {
        if (BlockTypes.IRON_TRAPDOOR != null) return;
        BlockTypes.IRON_TRAPDOOR = AllayBlockType
                .builder(BlockIronTrapdoorBehavior.class)
                .vanillaBlock(BlockId.IRON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
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

    public static void initJungleButton() {
        if (BlockTypes.JUNGLE_BUTTON != null) return;
        BlockTypes.JUNGLE_BUTTON = AllayBlockType
                .builder(BlockJungleButtonBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleDoor() {
        if (BlockTypes.JUNGLE_DOOR != null) return;
        BlockTypes.JUNGLE_DOOR = AllayBlockType
                .builder(BlockJungleDoorBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initJungleDoubleSlab() {
        if (BlockTypes.JUNGLE_DOUBLE_SLAB != null) return;
        BlockTypes.JUNGLE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockJungleDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleFence() {
        if (BlockTypes.JUNGLE_FENCE != null) return;
        BlockTypes.JUNGLE_FENCE = AllayBlockType
                .builder(BlockJungleFenceBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE)
                .build();
    }

    public static void initJungleFenceGate() {
        if (BlockTypes.JUNGLE_FENCE_GATE != null) return;
        BlockTypes.JUNGLE_FENCE_GATE = AllayBlockType
                .builder(BlockJungleFenceGateBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initJungleHangingSign() {
        if (BlockTypes.JUNGLE_HANGING_SIGN != null) return;
        BlockTypes.JUNGLE_HANGING_SIGN = AllayBlockType
                .builder(BlockJungleHangingSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initJungleLeaves() {
        if (BlockTypes.JUNGLE_LEAVES != null) return;
        BlockTypes.JUNGLE_LEAVES = AllayBlockType
                .builder(BlockJungleLeavesBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initJungleLog() {
        if (BlockTypes.JUNGLE_LOG != null) return;
        BlockTypes.JUNGLE_LOG = AllayBlockType
                .builder(BlockJungleLogBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initJunglePlanks() {
        if (BlockTypes.JUNGLE_PLANKS != null) return;
        BlockTypes.JUNGLE_PLANKS = AllayBlockType
                .builder(BlockJunglePlanksBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_PLANKS)
                .build();
    }

    public static void initJunglePressurePlate() {
        if (BlockTypes.JUNGLE_PRESSURE_PLATE != null) return;
        BlockTypes.JUNGLE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockJunglePressurePlateBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initJungleSapling() {
        if (BlockTypes.JUNGLE_SAPLING != null) return;
        BlockTypes.JUNGLE_SAPLING = AllayBlockType
                .builder(BlockJungleSaplingBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initJungleSlab() {
        if (BlockTypes.JUNGLE_SLAB != null) return;
        BlockTypes.JUNGLE_SLAB = AllayBlockType
                .builder(BlockJungleSlabBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleStairs() {
        if (BlockTypes.JUNGLE_STAIRS != null) return;
        BlockTypes.JUNGLE_STAIRS = AllayBlockType
                .builder(BlockJungleStairsBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initJungleStandingSign() {
        if (BlockTypes.JUNGLE_STANDING_SIGN != null) return;
        BlockTypes.JUNGLE_STANDING_SIGN = AllayBlockType
                .builder(BlockJungleStandingSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initJungleTrapdoor() {
        if (BlockTypes.JUNGLE_TRAPDOOR != null) return;
        BlockTypes.JUNGLE_TRAPDOOR = AllayBlockType
                .builder(BlockJungleTrapdoorBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initJungleWallSign() {
        if (BlockTypes.JUNGLE_WALL_SIGN != null) return;
        BlockTypes.JUNGLE_WALL_SIGN = AllayBlockType
                .builder(BlockJungleWallSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleWood() {
        if (BlockTypes.JUNGLE_WOOD != null) return;
        BlockTypes.JUNGLE_WOOD = AllayBlockType
                .builder(BlockJungleWoodBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
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

    public static void initLabTable() {
        if (BlockTypes.LAB_TABLE != null) return;
        BlockTypes.LAB_TABLE = AllayBlockType
                .builder(BlockLabTableBehavior.class)
                .vanillaBlock(BlockId.LAB_TABLE)
                .setProperties(BlockPropertyTypes.DIRECTION)
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

    public static void initLightBlueCandle() {
        if (BlockTypes.LIGHT_BLUE_CANDLE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE = AllayBlockType
                .builder(BlockLightBlueCandleBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCandleCake() {
        if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE_CAKE = AllayBlockType
                .builder(BlockLightBlueCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCarpet() {
        if (BlockTypes.LIGHT_BLUE_CARPET != null) return;
        BlockTypes.LIGHT_BLUE_CARPET = AllayBlockType
                .builder(BlockLightBlueCarpetBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CARPET)
                .build();
    }

    public static void initLightBlueConcrete() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE = AllayBlockType
                .builder(BlockLightBlueConcreteBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE)
                .build();
    }

    public static void initLightBlueConcretePowder() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLightBlueConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initLightBlueGlazedTerracotta() {
        if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockLightBlueGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLightBlueShulkerBox() {
        if (BlockTypes.LIGHT_BLUE_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockLightBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .build();
    }

    public static void initLightBlueStainedGlass() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                .builder(BlockLightBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initLightBlueStainedGlassPane() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLightBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightBlueTerracotta() {
        if (BlockTypes.LIGHT_BLUE_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_TERRACOTTA = AllayBlockType
                .builder(BlockLightBlueTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_TERRACOTTA)
                .build();
    }

    public static void initLightBlueWool() {
        if (BlockTypes.LIGHT_BLUE_WOOL != null) return;
        BlockTypes.LIGHT_BLUE_WOOL = AllayBlockType
                .builder(BlockLightBlueWoolBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_WOOL)
                .build();
    }

    public static void initLightGrayCandle() {
        if (BlockTypes.LIGHT_GRAY_CANDLE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE = AllayBlockType
                .builder(BlockLightGrayCandleBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCandleCake() {
        if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE_CAKE = AllayBlockType
                .builder(BlockLightGrayCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCarpet() {
        if (BlockTypes.LIGHT_GRAY_CARPET != null) return;
        BlockTypes.LIGHT_GRAY_CARPET = AllayBlockType
                .builder(BlockLightGrayCarpetBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CARPET)
                .build();
    }

    public static void initLightGrayConcrete() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE = AllayBlockType
                .builder(BlockLightGrayConcreteBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE)
                .build();
    }

    public static void initLightGrayConcretePowder() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLightGrayConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initLightGrayShulkerBox() {
        if (BlockTypes.LIGHT_GRAY_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockLightGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .build();
    }

    public static void initLightGrayStainedGlass() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                .builder(BlockLightGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initLightGrayStainedGlassPane() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLightGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightGrayTerracotta() {
        if (BlockTypes.LIGHT_GRAY_TERRACOTTA != null) return;
        BlockTypes.LIGHT_GRAY_TERRACOTTA = AllayBlockType
                .builder(BlockLightGrayTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_TERRACOTTA)
                .build();
    }

    public static void initLightGrayWool() {
        if (BlockTypes.LIGHT_GRAY_WOOL != null) return;
        BlockTypes.LIGHT_GRAY_WOOL = AllayBlockType
                .builder(BlockLightGrayWoolBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_WOOL)
                .build();
    }

    public static void initLightWeightedPressurePlate() {
        if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockLightWeightedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
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

    public static void initLimeCandle() {
        if (BlockTypes.LIME_CANDLE != null) return;
        BlockTypes.LIME_CANDLE = AllayBlockType
                .builder(BlockLimeCandleBehavior.class)
                .vanillaBlock(BlockId.LIME_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCandleCake() {
        if (BlockTypes.LIME_CANDLE_CAKE != null) return;
        BlockTypes.LIME_CANDLE_CAKE = AllayBlockType
                .builder(BlockLimeCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIME_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCarpet() {
        if (BlockTypes.LIME_CARPET != null) return;
        BlockTypes.LIME_CARPET = AllayBlockType
                .builder(BlockLimeCarpetBehavior.class)
                .vanillaBlock(BlockId.LIME_CARPET)
                .build();
    }

    public static void initLimeConcrete() {
        if (BlockTypes.LIME_CONCRETE != null) return;
        BlockTypes.LIME_CONCRETE = AllayBlockType
                .builder(BlockLimeConcreteBehavior.class)
                .vanillaBlock(BlockId.LIME_CONCRETE)
                .build();
    }

    public static void initLimeConcretePowder() {
        if (BlockTypes.LIME_CONCRETE_POWDER != null) return;
        BlockTypes.LIME_CONCRETE_POWDER = AllayBlockType
                .builder(BlockLimeConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIME_CONCRETE_POWDER)
                .build();
    }

    public static void initLimeGlazedTerracotta() {
        if (BlockTypes.LIME_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIME_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockLimeGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIME_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLimeShulkerBox() {
        if (BlockTypes.LIME_SHULKER_BOX != null) return;
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockLimeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .build();
    }

    public static void initLimeStainedGlass() {
        if (BlockTypes.LIME_STAINED_GLASS != null) return;
        BlockTypes.LIME_STAINED_GLASS = AllayBlockType
                .builder(BlockLimeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS)
                .build();
    }

    public static void initLimeStainedGlassPane() {
        if (BlockTypes.LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIME_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockLimeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLimeTerracotta() {
        if (BlockTypes.LIME_TERRACOTTA != null) return;
        BlockTypes.LIME_TERRACOTTA = AllayBlockType
                .builder(BlockLimeTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIME_TERRACOTTA)
                .build();
    }

    public static void initLimeWool() {
        if (BlockTypes.LIME_WOOL != null) return;
        BlockTypes.LIME_WOOL = AllayBlockType
                .builder(BlockLimeWoolBehavior.class)
                .vanillaBlock(BlockId.LIME_WOOL)
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

    public static void initMagentaCandle() {
        if (BlockTypes.MAGENTA_CANDLE != null) return;
        BlockTypes.MAGENTA_CANDLE = AllayBlockType
                .builder(BlockMagentaCandleBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCandleCake() {
        if (BlockTypes.MAGENTA_CANDLE_CAKE != null) return;
        BlockTypes.MAGENTA_CANDLE_CAKE = AllayBlockType
                .builder(BlockMagentaCandleCakeBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCarpet() {
        if (BlockTypes.MAGENTA_CARPET != null) return;
        BlockTypes.MAGENTA_CARPET = AllayBlockType
                .builder(BlockMagentaCarpetBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CARPET)
                .build();
    }

    public static void initMagentaConcrete() {
        if (BlockTypes.MAGENTA_CONCRETE != null) return;
        BlockTypes.MAGENTA_CONCRETE = AllayBlockType
                .builder(BlockMagentaConcreteBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE)
                .build();
    }

    public static void initMagentaConcretePowder() {
        if (BlockTypes.MAGENTA_CONCRETE_POWDER != null) return;
        BlockTypes.MAGENTA_CONCRETE_POWDER = AllayBlockType
                .builder(BlockMagentaConcretePowderBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE_POWDER)
                .build();
    }

    public static void initMagentaGlazedTerracotta() {
        if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockMagentaGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMagentaShulkerBox() {
        if (BlockTypes.MAGENTA_SHULKER_BOX != null) return;
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockMagentaShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .build();
    }

    public static void initMagentaStainedGlass() {
        if (BlockTypes.MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS = AllayBlockType
                .builder(BlockMagentaStainedGlassBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initMagentaStainedGlassPane() {
        if (BlockTypes.MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockMagentaStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initMagentaTerracotta() {
        if (BlockTypes.MAGENTA_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_TERRACOTTA = AllayBlockType
                .builder(BlockMagentaTerracottaBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_TERRACOTTA)
                .build();
    }

    public static void initMagentaWool() {
        if (BlockTypes.MAGENTA_WOOL != null) return;
        BlockTypes.MAGENTA_WOOL = AllayBlockType
                .builder(BlockMagentaWoolBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_WOOL)
                .build();
    }

    public static void initMagma() {
        if (BlockTypes.MAGMA != null) return;
        BlockTypes.MAGMA = AllayBlockType
                .builder(BlockMagmaBehavior.class)
                .vanillaBlock(BlockId.MAGMA)
                .build();
    }

    public static void initMangroveButton() {
        if (BlockTypes.MANGROVE_BUTTON != null) return;
        BlockTypes.MANGROVE_BUTTON = AllayBlockType
                .builder(BlockMangroveButtonBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveDoor() {
        if (BlockTypes.MANGROVE_DOOR != null) return;
        BlockTypes.MANGROVE_DOOR = AllayBlockType
                .builder(BlockMangroveDoorBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initMangroveDoubleSlab() {
        if (BlockTypes.MANGROVE_DOUBLE_SLAB != null) return;
        BlockTypes.MANGROVE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMangroveDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveFence() {
        if (BlockTypes.MANGROVE_FENCE != null) return;
        BlockTypes.MANGROVE_FENCE = AllayBlockType
                .builder(BlockMangroveFenceBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE)
                .build();
    }

    public static void initMangroveFenceGate() {
        if (BlockTypes.MANGROVE_FENCE_GATE != null) return;
        BlockTypes.MANGROVE_FENCE_GATE = AllayBlockType
                .builder(BlockMangroveFenceGateBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initMangroveHangingSign() {
        if (BlockTypes.MANGROVE_HANGING_SIGN != null) return;
        BlockTypes.MANGROVE_HANGING_SIGN = AllayBlockType
                .builder(BlockMangroveHangingSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initMangroveLeaves() {
        if (BlockTypes.MANGROVE_LEAVES != null) return;
        BlockTypes.MANGROVE_LEAVES = AllayBlockType
                .builder(BlockMangroveLeavesBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initMangroveLog() {
        if (BlockTypes.MANGROVE_LOG != null) return;
        BlockTypes.MANGROVE_LOG = AllayBlockType
                .builder(BlockMangroveLogBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMangrovePlanks() {
        if (BlockTypes.MANGROVE_PLANKS != null) return;
        BlockTypes.MANGROVE_PLANKS = AllayBlockType
                .builder(BlockMangrovePlanksBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PLANKS)
                .build();
    }

    public static void initMangrovePressurePlate() {
        if (BlockTypes.MANGROVE_PRESSURE_PLATE != null) return;
        BlockTypes.MANGROVE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockMangrovePressurePlateBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
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

    public static void initMangroveSlab() {
        if (BlockTypes.MANGROVE_SLAB != null) return;
        BlockTypes.MANGROVE_SLAB = AllayBlockType
                .builder(BlockMangroveSlabBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveStairs() {
        if (BlockTypes.MANGROVE_STAIRS != null) return;
        BlockTypes.MANGROVE_STAIRS = AllayBlockType
                .builder(BlockMangroveStairsBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMangroveStandingSign() {
        if (BlockTypes.MANGROVE_STANDING_SIGN != null) return;
        BlockTypes.MANGROVE_STANDING_SIGN = AllayBlockType
                .builder(BlockMangroveStandingSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initMangroveTrapdoor() {
        if (BlockTypes.MANGROVE_TRAPDOOR != null) return;
        BlockTypes.MANGROVE_TRAPDOOR = AllayBlockType
                .builder(BlockMangroveTrapdoorBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initMangroveWallSign() {
        if (BlockTypes.MANGROVE_WALL_SIGN != null) return;
        BlockTypes.MANGROVE_WALL_SIGN = AllayBlockType
                .builder(BlockMangroveWallSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveWood() {
        if (BlockTypes.MANGROVE_WOOD != null) return;
        BlockTypes.MANGROVE_WOOD = AllayBlockType
                .builder(BlockMangroveWoodBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS, BlockPropertyTypes.STRIPPED_BIT)
                .build();
    }

    public static void initMaterialReducer() {
        if (BlockTypes.MATERIAL_REDUCER != null) return;
        BlockTypes.MATERIAL_REDUCER = AllayBlockType
                .builder(BlockMaterialReducerBehavior.class)
                .vanillaBlock(BlockId.MATERIAL_REDUCER)
                .setProperties(BlockPropertyTypes.DIRECTION)
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

    public static void initMossCarpet() {
        if (BlockTypes.MOSS_CARPET != null) return;
        BlockTypes.MOSS_CARPET = AllayBlockType
                .builder(BlockMossCarpetBehavior.class)
                .vanillaBlock(BlockId.MOSS_CARPET)
                .build();
    }

    public static void initMossyCobblestone() {
        if (BlockTypes.MOSSY_COBBLESTONE != null) return;
        BlockTypes.MOSSY_COBBLESTONE = AllayBlockType
                .builder(BlockMossyCobblestoneBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE)
                .build();
    }

    public static void initMossyCobblestoneDoubleSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMossyCobblestoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_SLAB = AllayBlockType
                .builder(BlockMossyCobblestoneSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneStairs() {
        if (BlockTypes.MOSSY_COBBLESTONE_STAIRS != null) return;
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = AllayBlockType
                .builder(BlockMossyCobblestoneStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyCobblestoneWall() {
        if (BlockTypes.MOSSY_COBBLESTONE_WALL != null) return;
        BlockTypes.MOSSY_COBBLESTONE_WALL = AllayBlockType
                .builder(BlockMossyCobblestoneWallBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initMossyStoneBrickDoubleSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMossyStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockMossyStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickStairs() {
        if (BlockTypes.MOSSY_STONE_BRICK_STAIRS != null) return;
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockMossyStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyStoneBrickWall() {
        if (BlockTypes.MOSSY_STONE_BRICK_WALL != null) return;
        BlockTypes.MOSSY_STONE_BRICK_WALL = AllayBlockType
                .builder(BlockMossyStoneBrickWallBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initMudBrickDoubleSlab() {
        if (BlockTypes.MUD_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MUD_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockMudBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickSlab() {
        if (BlockTypes.MUD_BRICK_SLAB != null) return;
        BlockTypes.MUD_BRICK_SLAB = AllayBlockType
                .builder(BlockMudBrickSlabBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickStairs() {
        if (BlockTypes.MUD_BRICK_STAIRS != null) return;
        BlockTypes.MUD_BRICK_STAIRS = AllayBlockType
                .builder(BlockMudBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMudBrickWall() {
        if (BlockTypes.MUD_BRICK_WALL != null) return;
        BlockTypes.MUD_BRICK_WALL = AllayBlockType
                .builder(BlockMudBrickWallBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initNetherBrickDoubleSlab() {
        if (BlockTypes.NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockNetherBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickFence() {
        if (BlockTypes.NETHER_BRICK_FENCE != null) return;
        BlockTypes.NETHER_BRICK_FENCE = AllayBlockType
                .builder(BlockNetherBrickFenceBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_FENCE)
                .build();
    }

    public static void initNetherBrickSlab() {
        if (BlockTypes.NETHER_BRICK_SLAB != null) return;
        BlockTypes.NETHER_BRICK_SLAB = AllayBlockType
                .builder(BlockNetherBrickSlabBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickStairs() {
        if (BlockTypes.NETHER_BRICK_STAIRS != null) return;
        BlockTypes.NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNetherBrickWall() {
        if (BlockTypes.NETHER_BRICK_WALL != null) return;
        BlockTypes.NETHER_BRICK_WALL = AllayBlockType
                .builder(BlockNetherBrickWallBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initNormalStoneDoubleSlab() {
        if (BlockTypes.NORMAL_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockNormalStoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneSlab() {
        if (BlockTypes.NORMAL_STONE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_SLAB = AllayBlockType
                .builder(BlockNormalStoneSlabBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneStairs() {
        if (BlockTypes.NORMAL_STONE_STAIRS != null) return;
        BlockTypes.NORMAL_STONE_STAIRS = AllayBlockType
                .builder(BlockNormalStoneStairsBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNoteblock() {
        if (BlockTypes.NOTEBLOCK != null) return;
        BlockTypes.NOTEBLOCK = AllayBlockType
                .builder(BlockNoteblockBehavior.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .build();
    }

    public static void initOakDoubleSlab() {
        if (BlockTypes.OAK_DOUBLE_SLAB != null) return;
        BlockTypes.OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakFence() {
        if (BlockTypes.OAK_FENCE != null) return;
        BlockTypes.OAK_FENCE = AllayBlockType
                .builder(BlockOakFenceBehavior.class)
                .vanillaBlock(BlockId.OAK_FENCE)
                .build();
    }

    public static void initOakHangingSign() {
        if (BlockTypes.OAK_HANGING_SIGN != null) return;
        BlockTypes.OAK_HANGING_SIGN = AllayBlockType
                .builder(BlockOakHangingSignBehavior.class)
                .vanillaBlock(BlockId.OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initOakLeaves() {
        if (BlockTypes.OAK_LEAVES != null) return;
        BlockTypes.OAK_LEAVES = AllayBlockType
                .builder(BlockOakLeavesBehavior.class)
                .vanillaBlock(BlockId.OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initOakLog() {
        if (BlockTypes.OAK_LOG != null) return;
        BlockTypes.OAK_LOG = AllayBlockType
                .builder(BlockOakLogBehavior.class)
                .vanillaBlock(BlockId.OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOakPlanks() {
        if (BlockTypes.OAK_PLANKS != null) return;
        BlockTypes.OAK_PLANKS = AllayBlockType
                .builder(BlockOakPlanksBehavior.class)
                .vanillaBlock(BlockId.OAK_PLANKS)
                .build();
    }

    public static void initOakSapling() {
        if (BlockTypes.OAK_SAPLING != null) return;
        BlockTypes.OAK_SAPLING = AllayBlockType
                .builder(BlockOakSaplingBehavior.class)
                .vanillaBlock(BlockId.OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initOakSlab() {
        if (BlockTypes.OAK_SLAB != null) return;
        BlockTypes.OAK_SLAB = AllayBlockType
                .builder(BlockOakSlabBehavior.class)
                .vanillaBlock(BlockId.OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakStairs() {
        if (BlockTypes.OAK_STAIRS != null) return;
        BlockTypes.OAK_STAIRS = AllayBlockType
                .builder(BlockOakStairsBehavior.class)
                .vanillaBlock(BlockId.OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOakWood() {
        if (BlockTypes.OAK_WOOD != null) return;
        BlockTypes.OAK_WOOD = AllayBlockType
                .builder(BlockOakWoodBehavior.class)
                .vanillaBlock(BlockId.OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
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

    public static void initOrangeCandle() {
        if (BlockTypes.ORANGE_CANDLE != null) return;
        BlockTypes.ORANGE_CANDLE = AllayBlockType
                .builder(BlockOrangeCandleBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCandleCake() {
        if (BlockTypes.ORANGE_CANDLE_CAKE != null) return;
        BlockTypes.ORANGE_CANDLE_CAKE = AllayBlockType
                .builder(BlockOrangeCandleCakeBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCarpet() {
        if (BlockTypes.ORANGE_CARPET != null) return;
        BlockTypes.ORANGE_CARPET = AllayBlockType
                .builder(BlockOrangeCarpetBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CARPET)
                .build();
    }

    public static void initOrangeConcrete() {
        if (BlockTypes.ORANGE_CONCRETE != null) return;
        BlockTypes.ORANGE_CONCRETE = AllayBlockType
                .builder(BlockOrangeConcreteBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE)
                .build();
    }

    public static void initOrangeConcretePowder() {
        if (BlockTypes.ORANGE_CONCRETE_POWDER != null) return;
        BlockTypes.ORANGE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockOrangeConcretePowderBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE_POWDER)
                .build();
    }

    public static void initOrangeGlazedTerracotta() {
        if (BlockTypes.ORANGE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.ORANGE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockOrangeGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.ORANGE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initOrangeShulkerBox() {
        if (BlockTypes.ORANGE_SHULKER_BOX != null) return;
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockOrangeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .build();
    }

    public static void initOrangeStainedGlass() {
        if (BlockTypes.ORANGE_STAINED_GLASS != null) return;
        BlockTypes.ORANGE_STAINED_GLASS = AllayBlockType
                .builder(BlockOrangeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initOrangeStainedGlassPane() {
        if (BlockTypes.ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.ORANGE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockOrangeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initOrangeTerracotta() {
        if (BlockTypes.ORANGE_TERRACOTTA != null) return;
        BlockTypes.ORANGE_TERRACOTTA = AllayBlockType
                .builder(BlockOrangeTerracottaBehavior.class)
                .vanillaBlock(BlockId.ORANGE_TERRACOTTA)
                .build();
    }

    public static void initOrangeTulip() {
        if (BlockTypes.ORANGE_TULIP != null) return;
        BlockTypes.ORANGE_TULIP = AllayBlockType
                .builder(BlockOrangeTulipBehavior.class)
                .vanillaBlock(BlockId.ORANGE_TULIP)
                .build();
    }

    public static void initOrangeWool() {
        if (BlockTypes.ORANGE_WOOL != null) return;
        BlockTypes.ORANGE_WOOL = AllayBlockType
                .builder(BlockOrangeWoolBehavior.class)
                .vanillaBlock(BlockId.ORANGE_WOOL)
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

    public static void initOxidizedCopperBulb() {
        if (BlockTypes.OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.OXIDIZED_COPPER_BULB = AllayBlockType
                .builder(BlockOxidizedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initOxidizedCopperDoor() {
        if (BlockTypes.OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_DOOR = AllayBlockType
                .builder(BlockOxidizedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initOxidizedCopperGrate() {
        if (BlockTypes.OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.OXIDIZED_COPPER_GRATE = AllayBlockType
                .builder(BlockOxidizedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initOxidizedCopperTrapdoor() {
        if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockOxidizedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initOxidizedCutCopper() {
        if (BlockTypes.OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER = AllayBlockType
                .builder(BlockOxidizedCutCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initOxidizedCutCopperSlab() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockOxidizedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOxidizedCutCopperStairs() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockOxidizedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initPetrifiedOakDoubleSlab() {
        if (BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPetrifiedOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPetrifiedOakSlab() {
        if (BlockTypes.PETRIFIED_OAK_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_SLAB = AllayBlockType
                .builder(BlockPetrifiedOakSlabBehavior.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPinkCandle() {
        if (BlockTypes.PINK_CANDLE != null) return;
        BlockTypes.PINK_CANDLE = AllayBlockType
                .builder(BlockPinkCandleBehavior.class)
                .vanillaBlock(BlockId.PINK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCandleCake() {
        if (BlockTypes.PINK_CANDLE_CAKE != null) return;
        BlockTypes.PINK_CANDLE_CAKE = AllayBlockType
                .builder(BlockPinkCandleCakeBehavior.class)
                .vanillaBlock(BlockId.PINK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCarpet() {
        if (BlockTypes.PINK_CARPET != null) return;
        BlockTypes.PINK_CARPET = AllayBlockType
                .builder(BlockPinkCarpetBehavior.class)
                .vanillaBlock(BlockId.PINK_CARPET)
                .build();
    }

    public static void initPinkConcrete() {
        if (BlockTypes.PINK_CONCRETE != null) return;
        BlockTypes.PINK_CONCRETE = AllayBlockType
                .builder(BlockPinkConcreteBehavior.class)
                .vanillaBlock(BlockId.PINK_CONCRETE)
                .build();
    }

    public static void initPinkConcretePowder() {
        if (BlockTypes.PINK_CONCRETE_POWDER != null) return;
        BlockTypes.PINK_CONCRETE_POWDER = AllayBlockType
                .builder(BlockPinkConcretePowderBehavior.class)
                .vanillaBlock(BlockId.PINK_CONCRETE_POWDER)
                .build();
    }

    public static void initPinkGlazedTerracotta() {
        if (BlockTypes.PINK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PINK_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockPinkGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.PINK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initPinkShulkerBox() {
        if (BlockTypes.PINK_SHULKER_BOX != null) return;
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockPinkShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .build();
    }

    public static void initPinkStainedGlass() {
        if (BlockTypes.PINK_STAINED_GLASS != null) return;
        BlockTypes.PINK_STAINED_GLASS = AllayBlockType
                .builder(BlockPinkStainedGlassBehavior.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS)
                .build();
    }

    public static void initPinkStainedGlassPane() {
        if (BlockTypes.PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.PINK_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockPinkStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPinkTerracotta() {
        if (BlockTypes.PINK_TERRACOTTA != null) return;
        BlockTypes.PINK_TERRACOTTA = AllayBlockType
                .builder(BlockPinkTerracottaBehavior.class)
                .vanillaBlock(BlockId.PINK_TERRACOTTA)
                .build();
    }

    public static void initPinkTulip() {
        if (BlockTypes.PINK_TULIP != null) return;
        BlockTypes.PINK_TULIP = AllayBlockType
                .builder(BlockPinkTulipBehavior.class)
                .vanillaBlock(BlockId.PINK_TULIP)
                .build();
    }

    public static void initPinkWool() {
        if (BlockTypes.PINK_WOOL != null) return;
        BlockTypes.PINK_WOOL = AllayBlockType
                .builder(BlockPinkWoolBehavior.class)
                .vanillaBlock(BlockId.PINK_WOOL)
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

    public static void initPolishedAndesiteDoubleSlab() {
        if (BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedAndesiteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteSlab() {
        if (BlockTypes.POLISHED_ANDESITE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_SLAB = AllayBlockType
                .builder(BlockPolishedAndesiteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteStairs() {
        if (BlockTypes.POLISHED_ANDESITE_STAIRS != null) return;
        BlockTypes.POLISHED_ANDESITE_STAIRS = AllayBlockType
                .builder(BlockPolishedAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
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

    public static void initPolishedBlackstoneBrickDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneBrickWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedBlackstoneBricks() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                .builder(BlockPolishedBlackstoneBricksBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initPolishedBlackstoneButton() {
        if (BlockTypes.POLISHED_BLACKSTONE_BUTTON != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BUTTON = AllayBlockType
                .builder(BlockPolishedBlackstoneButtonBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstonePressurePlate() {
        if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE != null) return;
        BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initPolishedBlackstoneSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_SLAB = AllayBlockType
                .builder(BlockPolishedBlackstoneSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockPolishedBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_WALL = AllayBlockType
                .builder(BlockPolishedBlackstoneWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDeepslate() {
        if (BlockTypes.POLISHED_DEEPSLATE != null) return;
        BlockTypes.POLISHED_DEEPSLATE = AllayBlockType
                .builder(BlockPolishedDeepslateBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE)
                .build();
    }

    public static void initPolishedDeepslateDoubleSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_SLAB = AllayBlockType
                .builder(BlockPolishedDeepslateSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateStairs() {
        if (BlockTypes.POLISHED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockPolishedDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedDeepslateWall() {
        if (BlockTypes.POLISHED_DEEPSLATE_WALL != null) return;
        BlockTypes.POLISHED_DEEPSLATE_WALL = AllayBlockType
                .builder(BlockPolishedDeepslateWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDiorite() {
        if (BlockTypes.POLISHED_DIORITE != null) return;
        BlockTypes.POLISHED_DIORITE = AllayBlockType
                .builder(BlockPolishedDioriteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE)
                .build();
    }

    public static void initPolishedDioriteDoubleSlab() {
        if (BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedDioriteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteSlab() {
        if (BlockTypes.POLISHED_DIORITE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_SLAB = AllayBlockType
                .builder(BlockPolishedDioriteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteStairs() {
        if (BlockTypes.POLISHED_DIORITE_STAIRS != null) return;
        BlockTypes.POLISHED_DIORITE_STAIRS = AllayBlockType
                .builder(BlockPolishedDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedGranite() {
        if (BlockTypes.POLISHED_GRANITE != null) return;
        BlockTypes.POLISHED_GRANITE = AllayBlockType
                .builder(BlockPolishedGraniteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE)
                .build();
    }

    public static void initPolishedGraniteDoubleSlab() {
        if (BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedGraniteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteSlab() {
        if (BlockTypes.POLISHED_GRANITE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_SLAB = AllayBlockType
                .builder(BlockPolishedGraniteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteStairs() {
        if (BlockTypes.POLISHED_GRANITE_STAIRS != null) return;
        BlockTypes.POLISHED_GRANITE_STAIRS = AllayBlockType
                .builder(BlockPolishedGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuff() {
        if (BlockTypes.POLISHED_TUFF != null) return;
        BlockTypes.POLISHED_TUFF = AllayBlockType
                .builder(BlockPolishedTuffBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF)
                .build();
    }

    public static void initPolishedTuffDoubleSlab() {
        if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPolishedTuffDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffSlab() {
        if (BlockTypes.POLISHED_TUFF_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_SLAB = AllayBlockType
                .builder(BlockPolishedTuffSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffStairs() {
        if (BlockTypes.POLISHED_TUFF_STAIRS != null) return;
        BlockTypes.POLISHED_TUFF_STAIRS = AllayBlockType
                .builder(BlockPolishedTuffStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuffWall() {
        if (BlockTypes.POLISHED_TUFF_WALL != null) return;
        BlockTypes.POLISHED_TUFF_WALL = AllayBlockType
                .builder(BlockPolishedTuffWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initPrismarineBrickDoubleSlab() {
        if (BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPrismarineBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBrickSlab() {
        if (BlockTypes.PRISMARINE_BRICK_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_SLAB = AllayBlockType
                .builder(BlockPrismarineBrickSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBricks() {
        if (BlockTypes.PRISMARINE_BRICKS != null) return;
        BlockTypes.PRISMARINE_BRICKS = AllayBlockType
                .builder(BlockPrismarineBricksBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS)
                .build();
    }

    public static void initPrismarineBricksStairs() {
        if (BlockTypes.PRISMARINE_BRICKS_STAIRS != null) return;
        BlockTypes.PRISMARINE_BRICKS_STAIRS = AllayBlockType
                .builder(BlockPrismarineBricksStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPrismarineDoubleSlab() {
        if (BlockTypes.PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPrismarineDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineSlab() {
        if (BlockTypes.PRISMARINE_SLAB != null) return;
        BlockTypes.PRISMARINE_SLAB = AllayBlockType
                .builder(BlockPrismarineSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineStairs() {
        if (BlockTypes.PRISMARINE_STAIRS != null) return;
        BlockTypes.PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPrismarineWall() {
        if (BlockTypes.PRISMARINE_WALL != null) return;
        BlockTypes.PRISMARINE_WALL = AllayBlockType
                .builder(BlockPrismarineWallBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initPurpleCandle() {
        if (BlockTypes.PURPLE_CANDLE != null) return;
        BlockTypes.PURPLE_CANDLE = AllayBlockType
                .builder(BlockPurpleCandleBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCandleCake() {
        if (BlockTypes.PURPLE_CANDLE_CAKE != null) return;
        BlockTypes.PURPLE_CANDLE_CAKE = AllayBlockType
                .builder(BlockPurpleCandleCakeBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCarpet() {
        if (BlockTypes.PURPLE_CARPET != null) return;
        BlockTypes.PURPLE_CARPET = AllayBlockType
                .builder(BlockPurpleCarpetBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CARPET)
                .build();
    }

    public static void initPurpleConcrete() {
        if (BlockTypes.PURPLE_CONCRETE != null) return;
        BlockTypes.PURPLE_CONCRETE = AllayBlockType
                .builder(BlockPurpleConcreteBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE)
                .build();
    }

    public static void initPurpleConcretePowder() {
        if (BlockTypes.PURPLE_CONCRETE_POWDER != null) return;
        BlockTypes.PURPLE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockPurpleConcretePowderBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE_POWDER)
                .build();
    }

    public static void initPurpleGlazedTerracotta() {
        if (BlockTypes.PURPLE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockPurpleGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.PURPLE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPurpleShulkerBox() {
        if (BlockTypes.PURPLE_SHULKER_BOX != null) return;
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockPurpleShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .build();
    }

    public static void initPurpleStainedGlass() {
        if (BlockTypes.PURPLE_STAINED_GLASS != null) return;
        BlockTypes.PURPLE_STAINED_GLASS = AllayBlockType
                .builder(BlockPurpleStainedGlassBehavior.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initPurpleStainedGlassPane() {
        if (BlockTypes.PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.PURPLE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockPurpleStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPurpleTerracotta() {
        if (BlockTypes.PURPLE_TERRACOTTA != null) return;
        BlockTypes.PURPLE_TERRACOTTA = AllayBlockType
                .builder(BlockPurpleTerracottaBehavior.class)
                .vanillaBlock(BlockId.PURPLE_TERRACOTTA)
                .build();
    }

    public static void initPurpleWool() {
        if (BlockTypes.PURPLE_WOOL != null) return;
        BlockTypes.PURPLE_WOOL = AllayBlockType
                .builder(BlockPurpleWoolBehavior.class)
                .vanillaBlock(BlockId.PURPLE_WOOL)
                .build();
    }

    public static void initPurpurBlock() {
        if (BlockTypes.PURPUR_BLOCK != null) return;
        BlockTypes.PURPUR_BLOCK = AllayBlockType
                .builder(BlockPurpurBlockBehavior.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPurpurDoubleSlab() {
        if (BlockTypes.PURPUR_DOUBLE_SLAB != null) return;
        BlockTypes.PURPUR_DOUBLE_SLAB = AllayBlockType
                .builder(BlockPurpurDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PURPUR_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurPillar() {
        if (BlockTypes.PURPUR_PILLAR != null) return;
        BlockTypes.PURPUR_PILLAR = AllayBlockType
                .builder(BlockPurpurPillarBehavior.class)
                .vanillaBlock(BlockId.PURPUR_PILLAR)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPurpurSlab() {
        if (BlockTypes.PURPUR_SLAB != null) return;
        BlockTypes.PURPUR_SLAB = AllayBlockType
                .builder(BlockPurpurSlabBehavior.class)
                .vanillaBlock(BlockId.PURPUR_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurStairs() {
        if (BlockTypes.PURPUR_STAIRS != null) return;
        BlockTypes.PURPUR_STAIRS = AllayBlockType
                .builder(BlockPurpurStairsBehavior.class)
                .vanillaBlock(BlockId.PURPUR_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
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

    public static void initQuartzDoubleSlab() {
        if (BlockTypes.QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.QUARTZ_DOUBLE_SLAB = AllayBlockType
                .builder(BlockQuartzDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initQuartzSlab() {
        if (BlockTypes.QUARTZ_SLAB != null) return;
        BlockTypes.QUARTZ_SLAB = AllayBlockType
                .builder(BlockQuartzSlabBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initQuartzStairs() {
        if (BlockTypes.QUARTZ_STAIRS != null) return;
        BlockTypes.QUARTZ_STAIRS = AllayBlockType
                .builder(BlockQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
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

    public static void initRedCandle() {
        if (BlockTypes.RED_CANDLE != null) return;
        BlockTypes.RED_CANDLE = AllayBlockType
                .builder(BlockRedCandleBehavior.class)
                .vanillaBlock(BlockId.RED_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCandleCake() {
        if (BlockTypes.RED_CANDLE_CAKE != null) return;
        BlockTypes.RED_CANDLE_CAKE = AllayBlockType
                .builder(BlockRedCandleCakeBehavior.class)
                .vanillaBlock(BlockId.RED_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCarpet() {
        if (BlockTypes.RED_CARPET != null) return;
        BlockTypes.RED_CARPET = AllayBlockType
                .builder(BlockRedCarpetBehavior.class)
                .vanillaBlock(BlockId.RED_CARPET)
                .build();
    }

    public static void initRedConcrete() {
        if (BlockTypes.RED_CONCRETE != null) return;
        BlockTypes.RED_CONCRETE = AllayBlockType
                .builder(BlockRedConcreteBehavior.class)
                .vanillaBlock(BlockId.RED_CONCRETE)
                .build();
    }

    public static void initRedConcretePowder() {
        if (BlockTypes.RED_CONCRETE_POWDER != null) return;
        BlockTypes.RED_CONCRETE_POWDER = AllayBlockType
                .builder(BlockRedConcretePowderBehavior.class)
                .vanillaBlock(BlockId.RED_CONCRETE_POWDER)
                .build();
    }

    public static void initRedGlazedTerracotta() {
        if (BlockTypes.RED_GLAZED_TERRACOTTA != null) return;
        BlockTypes.RED_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockRedGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.RED_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initRedNetherBrickDoubleSlab() {
        if (BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockRedNetherBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickSlab() {
        if (BlockTypes.RED_NETHER_BRICK_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_SLAB = AllayBlockType
                .builder(BlockRedNetherBrickSlabBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickStairs() {
        if (BlockTypes.RED_NETHER_BRICK_STAIRS != null) return;
        BlockTypes.RED_NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockRedNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedNetherBrickWall() {
        if (BlockTypes.RED_NETHER_BRICK_WALL != null) return;
        BlockTypes.RED_NETHER_BRICK_WALL = AllayBlockType
                .builder(BlockRedNetherBrickWallBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initRedSandstoneDoubleSlab() {
        if (BlockTypes.RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneSlab() {
        if (BlockTypes.RED_SANDSTONE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneStairs() {
        if (BlockTypes.RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedSandstoneWall() {
        if (BlockTypes.RED_SANDSTONE_WALL != null) return;
        BlockTypes.RED_SANDSTONE_WALL = AllayBlockType
                .builder(BlockRedSandstoneWallBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initRedShulkerBox() {
        if (BlockTypes.RED_SHULKER_BOX != null) return;
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockRedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .build();
    }

    public static void initRedStainedGlass() {
        if (BlockTypes.RED_STAINED_GLASS != null) return;
        BlockTypes.RED_STAINED_GLASS = AllayBlockType
                .builder(BlockRedStainedGlassBehavior.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS)
                .build();
    }

    public static void initRedStainedGlassPane() {
        if (BlockTypes.RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.RED_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockRedStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initRedTerracotta() {
        if (BlockTypes.RED_TERRACOTTA != null) return;
        BlockTypes.RED_TERRACOTTA = AllayBlockType
                .builder(BlockRedTerracottaBehavior.class)
                .vanillaBlock(BlockId.RED_TERRACOTTA)
                .build();
    }

    public static void initRedTulip() {
        if (BlockTypes.RED_TULIP != null) return;
        BlockTypes.RED_TULIP = AllayBlockType
                .builder(BlockRedTulipBehavior.class)
                .vanillaBlock(BlockId.RED_TULIP)
                .build();
    }

    public static void initRedWool() {
        if (BlockTypes.RED_WOOL != null) return;
        BlockTypes.RED_WOOL = AllayBlockType
                .builder(BlockRedWoolBehavior.class)
                .vanillaBlock(BlockId.RED_WOOL)
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

    public static void initSandstoneDoubleSlab() {
        if (BlockTypes.SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneSlab() {
        if (BlockTypes.SANDSTONE_SLAB != null) return;
        BlockTypes.SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneStairs() {
        if (BlockTypes.SANDSTONE_STAIRS != null) return;
        BlockTypes.SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSandstoneWall() {
        if (BlockTypes.SANDSTONE_WALL != null) return;
        BlockTypes.SANDSTONE_WALL = AllayBlockType
                .builder(BlockSandstoneWallBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initSilverGlazedTerracotta() {
        if (BlockTypes.SILVER_GLAZED_TERRACOTTA != null) return;
        BlockTypes.SILVER_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockSilverGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.SILVER_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initSmoothQuartzDoubleSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothQuartzDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_SLAB = AllayBlockType
                .builder(BlockSmoothQuartzSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzStairs() {
        if (BlockTypes.SMOOTH_QUARTZ_STAIRS != null) return;
        BlockTypes.SMOOTH_QUARTZ_STAIRS = AllayBlockType
                .builder(BlockSmoothQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothRedSandstone() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE = AllayBlockType
                .builder(BlockSmoothRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE)
                .build();
    }

    public static void initSmoothRedSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSmoothRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneStairs() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSmoothRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothSandstone() {
        if (BlockTypes.SMOOTH_SANDSTONE != null) return;
        BlockTypes.SMOOTH_SANDSTONE = AllayBlockType
                .builder(BlockSmoothSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE)
                .build();
    }

    public static void initSmoothSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_SLAB = AllayBlockType
                .builder(BlockSmoothSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneStairs() {
        if (BlockTypes.SMOOTH_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSmoothSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothStone() {
        if (BlockTypes.SMOOTH_STONE != null) return;
        BlockTypes.SMOOTH_STONE = AllayBlockType
                .builder(BlockSmoothStoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE)
                .build();
    }

    public static void initSmoothStoneDoubleSlab() {
        if (BlockTypes.SMOOTH_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSmoothStoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothStoneSlab() {
        if (BlockTypes.SMOOTH_STONE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_SLAB = AllayBlockType
                .builder(BlockSmoothStoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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
                .build();
    }

    public static void initSporeBlossom() {
        if (BlockTypes.SPORE_BLOSSOM != null) return;
        BlockTypes.SPORE_BLOSSOM = AllayBlockType
                .builder(BlockSporeBlossomBehavior.class)
                .vanillaBlock(BlockId.SPORE_BLOSSOM)
                .build();
    }

    public static void initSpruceButton() {
        if (BlockTypes.SPRUCE_BUTTON != null) return;
        BlockTypes.SPRUCE_BUTTON = AllayBlockType
                .builder(BlockSpruceButtonBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceDoor() {
        if (BlockTypes.SPRUCE_DOOR != null) return;
        BlockTypes.SPRUCE_DOOR = AllayBlockType
                .builder(BlockSpruceDoorBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSpruceDoubleSlab() {
        if (BlockTypes.SPRUCE_DOUBLE_SLAB != null) return;
        BlockTypes.SPRUCE_DOUBLE_SLAB = AllayBlockType
                .builder(BlockSpruceDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceFence() {
        if (BlockTypes.SPRUCE_FENCE != null) return;
        BlockTypes.SPRUCE_FENCE = AllayBlockType
                .builder(BlockSpruceFenceBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE)
                .build();
    }

    public static void initSpruceFenceGate() {
        if (BlockTypes.SPRUCE_FENCE_GATE != null) return;
        BlockTypes.SPRUCE_FENCE_GATE = AllayBlockType
                .builder(BlockSpruceFenceGateBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initSpruceHangingSign() {
        if (BlockTypes.SPRUCE_HANGING_SIGN != null) return;
        BlockTypes.SPRUCE_HANGING_SIGN = AllayBlockType
                .builder(BlockSpruceHangingSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSpruceLeaves() {
        if (BlockTypes.SPRUCE_LEAVES != null) return;
        BlockTypes.SPRUCE_LEAVES = AllayBlockType
                .builder(BlockSpruceLeavesBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initSpruceLog() {
        if (BlockTypes.SPRUCE_LOG != null) return;
        BlockTypes.SPRUCE_LOG = AllayBlockType
                .builder(BlockSpruceLogBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSprucePlanks() {
        if (BlockTypes.SPRUCE_PLANKS != null) return;
        BlockTypes.SPRUCE_PLANKS = AllayBlockType
                .builder(BlockSprucePlanksBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_PLANKS)
                .build();
    }

    public static void initSprucePressurePlate() {
        if (BlockTypes.SPRUCE_PRESSURE_PLATE != null) return;
        BlockTypes.SPRUCE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockSprucePressurePlateBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initSpruceSapling() {
        if (BlockTypes.SPRUCE_SAPLING != null) return;
        BlockTypes.SPRUCE_SAPLING = AllayBlockType
                .builder(BlockSpruceSaplingBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initSpruceSlab() {
        if (BlockTypes.SPRUCE_SLAB != null) return;
        BlockTypes.SPRUCE_SLAB = AllayBlockType
                .builder(BlockSpruceSlabBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceStairs() {
        if (BlockTypes.SPRUCE_STAIRS != null) return;
        BlockTypes.SPRUCE_STAIRS = AllayBlockType
                .builder(BlockSpruceStairsBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSpruceStandingSign() {
        if (BlockTypes.SPRUCE_STANDING_SIGN != null) return;
        BlockTypes.SPRUCE_STANDING_SIGN = AllayBlockType
                .builder(BlockSpruceStandingSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initSpruceTrapdoor() {
        if (BlockTypes.SPRUCE_TRAPDOOR != null) return;
        BlockTypes.SPRUCE_TRAPDOOR = AllayBlockType
                .builder(BlockSpruceTrapdoorBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initSpruceWallSign() {
        if (BlockTypes.SPRUCE_WALL_SIGN != null) return;
        BlockTypes.SPRUCE_WALL_SIGN = AllayBlockType
                .builder(BlockSpruceWallSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceWood() {
        if (BlockTypes.SPRUCE_WOOD != null) return;
        BlockTypes.SPRUCE_WOOD = AllayBlockType
                .builder(BlockSpruceWoodBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
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

    public static void initStandingSign() {
        if (BlockTypes.STANDING_SIGN != null) return;
        BlockTypes.STANDING_SIGN = AllayBlockType
                .builder(BlockStandingSignBehavior.class)
                .vanillaBlock(BlockId.STANDING_SIGN)
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

    public static void initStoneBrickDoubleSlab() {
        if (BlockTypes.STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickSlab() {
        if (BlockTypes.STONE_BRICK_SLAB != null) return;
        BlockTypes.STONE_BRICK_SLAB = AllayBlockType
                .builder(BlockStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickStairs() {
        if (BlockTypes.STONE_BRICK_STAIRS != null) return;
        BlockTypes.STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initStoneBrickWall() {
        if (BlockTypes.STONE_BRICK_WALL != null) return;
        BlockTypes.STONE_BRICK_WALL = AllayBlockType
                .builder(BlockStoneBrickWallBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initStoneBricks() {
        if (BlockTypes.STONE_BRICKS != null) return;
        BlockTypes.STONE_BRICKS = AllayBlockType
                .builder(BlockStoneBricksBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICKS)
                .build();
    }

    public static void initStoneButton() {
        if (BlockTypes.STONE_BUTTON != null) return;
        BlockTypes.STONE_BUTTON = AllayBlockType
                .builder(BlockStoneButtonBehavior.class)
                .vanillaBlock(BlockId.STONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStonePressurePlate() {
        if (BlockTypes.STONE_PRESSURE_PLATE != null) return;
        BlockTypes.STONE_PRESSURE_PLATE = AllayBlockType
                .builder(BlockStonePressurePlateBehavior.class)
                .vanillaBlock(BlockId.STONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initStoneStairs() {
        if (BlockTypes.STONE_STAIRS != null) return;
        BlockTypes.STONE_STAIRS = AllayBlockType
                .builder(BlockStoneStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
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

    public static void initStrippedAcaciaLog() {
        if (BlockTypes.STRIPPED_ACACIA_LOG != null) return;
        BlockTypes.STRIPPED_ACACIA_LOG = AllayBlockType
                .builder(BlockStrippedAcaciaLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedAcaciaWood() {
        if (BlockTypes.STRIPPED_ACACIA_WOOD != null) return;
        BlockTypes.STRIPPED_ACACIA_WOOD = AllayBlockType
                .builder(BlockStrippedAcaciaWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
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

    public static void initStrippedBirchLog() {
        if (BlockTypes.STRIPPED_BIRCH_LOG != null) return;
        BlockTypes.STRIPPED_BIRCH_LOG = AllayBlockType
                .builder(BlockStrippedBirchLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBirchWood() {
        if (BlockTypes.STRIPPED_BIRCH_WOOD != null) return;
        BlockTypes.STRIPPED_BIRCH_WOOD = AllayBlockType
                .builder(BlockStrippedBirchWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryLog() {
        if (BlockTypes.STRIPPED_CHERRY_LOG != null) return;
        BlockTypes.STRIPPED_CHERRY_LOG = AllayBlockType
                .builder(BlockStrippedCherryLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryWood() {
        if (BlockTypes.STRIPPED_CHERRY_WOOD != null) return;
        BlockTypes.STRIPPED_CHERRY_WOOD = AllayBlockType
                .builder(BlockStrippedCherryWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_WOOD)
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

    public static void initStrippedDarkOakLog() {
        if (BlockTypes.STRIPPED_DARK_OAK_LOG != null) return;
        BlockTypes.STRIPPED_DARK_OAK_LOG = AllayBlockType
                .builder(BlockStrippedDarkOakLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedDarkOakWood() {
        if (BlockTypes.STRIPPED_DARK_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_DARK_OAK_WOOD = AllayBlockType
                .builder(BlockStrippedDarkOakWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleLog() {
        if (BlockTypes.STRIPPED_JUNGLE_LOG != null) return;
        BlockTypes.STRIPPED_JUNGLE_LOG = AllayBlockType
                .builder(BlockStrippedJungleLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleWood() {
        if (BlockTypes.STRIPPED_JUNGLE_WOOD != null) return;
        BlockTypes.STRIPPED_JUNGLE_WOOD = AllayBlockType
                .builder(BlockStrippedJungleWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveLog() {
        if (BlockTypes.STRIPPED_MANGROVE_LOG != null) return;
        BlockTypes.STRIPPED_MANGROVE_LOG = AllayBlockType
                .builder(BlockStrippedMangroveLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveWood() {
        if (BlockTypes.STRIPPED_MANGROVE_WOOD != null) return;
        BlockTypes.STRIPPED_MANGROVE_WOOD = AllayBlockType
                .builder(BlockStrippedMangroveWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakLog() {
        if (BlockTypes.STRIPPED_OAK_LOG != null) return;
        BlockTypes.STRIPPED_OAK_LOG = AllayBlockType
                .builder(BlockStrippedOakLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakWood() {
        if (BlockTypes.STRIPPED_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_OAK_WOOD = AllayBlockType
                .builder(BlockStrippedOakWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceLog() {
        if (BlockTypes.STRIPPED_SPRUCE_LOG != null) return;
        BlockTypes.STRIPPED_SPRUCE_LOG = AllayBlockType
                .builder(BlockStrippedSpruceLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceWood() {
        if (BlockTypes.STRIPPED_SPRUCE_WOOD != null) return;
        BlockTypes.STRIPPED_SPRUCE_WOOD = AllayBlockType
                .builder(BlockStrippedSpruceWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_WOOD)
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
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
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

    public static void initTrapdoor() {
        if (BlockTypes.TRAPDOOR != null) return;
        BlockTypes.TRAPDOOR = AllayBlockType
                .builder(BlockTrapdoorBehavior.class)
                .vanillaBlock(BlockId.TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
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

    public static void initTuffBrickDoubleSlab() {
        if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_BRICK_DOUBLE_SLAB = AllayBlockType
                .builder(BlockTuffBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickSlab() {
        if (BlockTypes.TUFF_BRICK_SLAB != null) return;
        BlockTypes.TUFF_BRICK_SLAB = AllayBlockType
                .builder(BlockTuffBrickSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickStairs() {
        if (BlockTypes.TUFF_BRICK_STAIRS != null) return;
        BlockTypes.TUFF_BRICK_STAIRS = AllayBlockType
                .builder(BlockTuffBrickStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffBrickWall() {
        if (BlockTypes.TUFF_BRICK_WALL != null) return;
        BlockTypes.TUFF_BRICK_WALL = AllayBlockType
                .builder(BlockTuffBrickWallBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initTuffBricks() {
        if (BlockTypes.TUFF_BRICKS != null) return;
        BlockTypes.TUFF_BRICKS = AllayBlockType
                .builder(BlockTuffBricksBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICKS)
                .build();
    }

    public static void initTuffDoubleSlab() {
        if (BlockTypes.TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_DOUBLE_SLAB = AllayBlockType
                .builder(BlockTuffDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffSlab() {
        if (BlockTypes.TUFF_SLAB != null) return;
        BlockTypes.TUFF_SLAB = AllayBlockType
                .builder(BlockTuffSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffStairs() {
        if (BlockTypes.TUFF_STAIRS != null) return;
        BlockTypes.TUFF_STAIRS = AllayBlockType
                .builder(BlockTuffStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffWall() {
        if (BlockTypes.TUFF_WALL != null) return;
        BlockTypes.TUFF_WALL = AllayBlockType
                .builder(BlockTuffWallBehavior.class)
                .vanillaBlock(BlockId.TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
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

    public static void initUnderwaterTnt() {
        if (BlockTypes.UNDERWATER_TNT != null) return;
        BlockTypes.UNDERWATER_TNT = AllayBlockType
                .builder(BlockUnderwaterTntBehavior.class)
                .vanillaBlock(BlockId.UNDERWATER_TNT)
                .setProperties(BlockPropertyTypes.EXPLODE_BIT)
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

    public static void initUndyedShulkerBox() {
        if (BlockTypes.UNDYED_SHULKER_BOX != null) return;
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockUndyedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
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

    public static void initWallSign() {
        if (BlockTypes.WALL_SIGN != null) return;
        BlockTypes.WALL_SIGN = AllayBlockType
                .builder(BlockWallSignBehavior.class)
                .vanillaBlock(BlockId.WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedButton() {
        if (BlockTypes.WARPED_BUTTON != null) return;
        BlockTypes.WARPED_BUTTON = AllayBlockType
                .builder(BlockWarpedButtonBehavior.class)
                .vanillaBlock(BlockId.WARPED_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedDoor() {
        if (BlockTypes.WARPED_DOOR != null) return;
        BlockTypes.WARPED_DOOR = AllayBlockType
                .builder(BlockWarpedDoorBehavior.class)
                .vanillaBlock(BlockId.WARPED_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWarpedDoubleSlab() {
        if (BlockTypes.WARPED_DOUBLE_SLAB != null) return;
        BlockTypes.WARPED_DOUBLE_SLAB = AllayBlockType
                .builder(BlockWarpedDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.WARPED_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedFence() {
        if (BlockTypes.WARPED_FENCE != null) return;
        BlockTypes.WARPED_FENCE = AllayBlockType
                .builder(BlockWarpedFenceBehavior.class)
                .vanillaBlock(BlockId.WARPED_FENCE)
                .build();
    }

    public static void initWarpedFenceGate() {
        if (BlockTypes.WARPED_FENCE_GATE != null) return;
        BlockTypes.WARPED_FENCE_GATE = AllayBlockType
                .builder(BlockWarpedFenceGateBehavior.class)
                .vanillaBlock(BlockId.WARPED_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initWarpedFungus() {
        if (BlockTypes.WARPED_FUNGUS != null) return;
        BlockTypes.WARPED_FUNGUS = AllayBlockType
                .builder(BlockWarpedFungusBehavior.class)
                .vanillaBlock(BlockId.WARPED_FUNGUS)
                .build();
    }

    public static void initWarpedHangingSign() {
        if (BlockTypes.WARPED_HANGING_SIGN != null) return;
        BlockTypes.WARPED_HANGING_SIGN = AllayBlockType
                .builder(BlockWarpedHangingSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
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

    public static void initWarpedPlanks() {
        if (BlockTypes.WARPED_PLANKS != null) return;
        BlockTypes.WARPED_PLANKS = AllayBlockType
                .builder(BlockWarpedPlanksBehavior.class)
                .vanillaBlock(BlockId.WARPED_PLANKS)
                .build();
    }

    public static void initWarpedPressurePlate() {
        if (BlockTypes.WARPED_PRESSURE_PLATE != null) return;
        BlockTypes.WARPED_PRESSURE_PLATE = AllayBlockType
                .builder(BlockWarpedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.WARPED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initWarpedRoots() {
        if (BlockTypes.WARPED_ROOTS != null) return;
        BlockTypes.WARPED_ROOTS = AllayBlockType
                .builder(BlockWarpedRootsBehavior.class)
                .vanillaBlock(BlockId.WARPED_ROOTS)
                .build();
    }

    public static void initWarpedSlab() {
        if (BlockTypes.WARPED_SLAB != null) return;
        BlockTypes.WARPED_SLAB = AllayBlockType
                .builder(BlockWarpedSlabBehavior.class)
                .vanillaBlock(BlockId.WARPED_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedStairs() {
        if (BlockTypes.WARPED_STAIRS != null) return;
        BlockTypes.WARPED_STAIRS = AllayBlockType
                .builder(BlockWarpedStairsBehavior.class)
                .vanillaBlock(BlockId.WARPED_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWarpedStandingSign() {
        if (BlockTypes.WARPED_STANDING_SIGN != null) return;
        BlockTypes.WARPED_STANDING_SIGN = AllayBlockType
                .builder(BlockWarpedStandingSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
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

    public static void initWarpedTrapdoor() {
        if (BlockTypes.WARPED_TRAPDOOR != null) return;
        BlockTypes.WARPED_TRAPDOOR = AllayBlockType
                .builder(BlockWarpedTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WARPED_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWarpedWallSign() {
        if (BlockTypes.WARPED_WALL_SIGN != null) return;
        BlockTypes.WARPED_WALL_SIGN = AllayBlockType
                .builder(BlockWarpedWallSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
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

    public static void initWaxedCopperBulb() {
        if (BlockTypes.WAXED_COPPER_BULB != null) return;
        BlockTypes.WAXED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedCopperDoor() {
        if (BlockTypes.WAXED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_COPPER_DOOR = AllayBlockType
                .builder(BlockWaxedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedCopperGrate() {
        if (BlockTypes.WAXED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedCopperTrapdoor() {
        if (BlockTypes.WAXED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedCutCopper() {
        if (BlockTypes.WAXED_CUT_COPPER != null) return;
        BlockTypes.WAXED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER)
                .build();
    }

    public static void initWaxedCutCopperSlab() {
        if (BlockTypes.WAXED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedCutCopperStairs() {
        if (BlockTypes.WAXED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initWaxedExposedCopperBulb() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_BULB != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedExposedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedExposedCopperDoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = AllayBlockType
                .builder(BlockWaxedExposedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedExposedCopperGrate() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedExposedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedExposedCopperTrapdoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedExposedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedExposedCutCopper() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedExposedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initWaxedExposedCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedExposedCutCopperStairs() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedExposedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedExposedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initWaxedOxidizedCopperBulb() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedOxidizedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperDoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = AllayBlockType
                .builder(BlockWaxedOxidizedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperGrate() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedOxidizedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedOxidizedCopperTrapdoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedOxidizedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedOxidizedCutCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedOxidizedCutCopperStairs() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initWaxedWeatheredCopperBulb() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_BULB = AllayBlockType
                .builder(BlockWaxedWeatheredCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperDoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = AllayBlockType
                .builder(BlockWaxedWeatheredCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperGrate() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_GRATE = AllayBlockType
                .builder(BlockWaxedWeatheredCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedWeatheredCopperTrapdoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWaxedWeatheredCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedWeatheredCutCopper() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedWeatheredCutCopperStairs() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initWeatheredCopperBulb() {
        if (BlockTypes.WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WEATHERED_COPPER_BULB = AllayBlockType
                .builder(BlockWeatheredCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWeatheredCopperDoor() {
        if (BlockTypes.WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WEATHERED_COPPER_DOOR = AllayBlockType
                .builder(BlockWeatheredCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWeatheredCopperGrate() {
        if (BlockTypes.WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WEATHERED_COPPER_GRATE = AllayBlockType
                .builder(BlockWeatheredCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWeatheredCopperTrapdoor() {
        if (BlockTypes.WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                .builder(BlockWeatheredCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWeatheredCutCopper() {
        if (BlockTypes.WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WEATHERED_CUT_COPPER = AllayBlockType
                .builder(BlockWeatheredCutCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWeatheredCutCopperSlab() {
        if (BlockTypes.WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWeatheredCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeatheredCutCopperStairs() {
        if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
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

    public static void initWetSponge() {
        if (BlockTypes.WET_SPONGE != null) return;
        BlockTypes.WET_SPONGE = AllayBlockType
                .builder(BlockWetSpongeBehavior.class)
                .vanillaBlock(BlockId.WET_SPONGE)
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

    public static void initWhiteCandle() {
        if (BlockTypes.WHITE_CANDLE != null) return;
        BlockTypes.WHITE_CANDLE = AllayBlockType
                .builder(BlockWhiteCandleBehavior.class)
                .vanillaBlock(BlockId.WHITE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCandleCake() {
        if (BlockTypes.WHITE_CANDLE_CAKE != null) return;
        BlockTypes.WHITE_CANDLE_CAKE = AllayBlockType
                .builder(BlockWhiteCandleCakeBehavior.class)
                .vanillaBlock(BlockId.WHITE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCarpet() {
        if (BlockTypes.WHITE_CARPET != null) return;
        BlockTypes.WHITE_CARPET = AllayBlockType
                .builder(BlockWhiteCarpetBehavior.class)
                .vanillaBlock(BlockId.WHITE_CARPET)
                .build();
    }

    public static void initWhiteConcrete() {
        if (BlockTypes.WHITE_CONCRETE != null) return;
        BlockTypes.WHITE_CONCRETE = AllayBlockType
                .builder(BlockWhiteConcreteBehavior.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE)
                .build();
    }

    public static void initWhiteConcretePowder() {
        if (BlockTypes.WHITE_CONCRETE_POWDER != null) return;
        BlockTypes.WHITE_CONCRETE_POWDER = AllayBlockType
                .builder(BlockWhiteConcretePowderBehavior.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE_POWDER)
                .build();
    }

    public static void initWhiteGlazedTerracotta() {
        if (BlockTypes.WHITE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.WHITE_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockWhiteGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.WHITE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWhiteShulkerBox() {
        if (BlockTypes.WHITE_SHULKER_BOX != null) return;
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockWhiteShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .build();
    }

    public static void initWhiteStainedGlass() {
        if (BlockTypes.WHITE_STAINED_GLASS != null) return;
        BlockTypes.WHITE_STAINED_GLASS = AllayBlockType
                .builder(BlockWhiteStainedGlassBehavior.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS)
                .build();
    }

    public static void initWhiteStainedGlassPane() {
        if (BlockTypes.WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.WHITE_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockWhiteStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initWhiteTerracotta() {
        if (BlockTypes.WHITE_TERRACOTTA != null) return;
        BlockTypes.WHITE_TERRACOTTA = AllayBlockType
                .builder(BlockWhiteTerracottaBehavior.class)
                .vanillaBlock(BlockId.WHITE_TERRACOTTA)
                .build();
    }

    public static void initWhiteTulip() {
        if (BlockTypes.WHITE_TULIP != null) return;
        BlockTypes.WHITE_TULIP = AllayBlockType
                .builder(BlockWhiteTulipBehavior.class)
                .vanillaBlock(BlockId.WHITE_TULIP)
                .build();
    }

    public static void initWhiteWool() {
        if (BlockTypes.WHITE_WOOL != null) return;
        BlockTypes.WHITE_WOOL = AllayBlockType
                .builder(BlockWhiteWoolBehavior.class)
                .vanillaBlock(BlockId.WHITE_WOOL)
                .build();
    }

    public static void initWitherRose() {
        if (BlockTypes.WITHER_ROSE != null) return;
        BlockTypes.WITHER_ROSE = AllayBlockType
                .builder(BlockWitherRoseBehavior.class)
                .vanillaBlock(BlockId.WITHER_ROSE)
                .build();
    }

    public static void initWoodenButton() {
        if (BlockTypes.WOODEN_BUTTON != null) return;
        BlockTypes.WOODEN_BUTTON = AllayBlockType
                .builder(BlockWoodenButtonBehavior.class)
                .vanillaBlock(BlockId.WOODEN_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWoodenDoor() {
        if (BlockTypes.WOODEN_DOOR != null) return;
        BlockTypes.WOODEN_DOOR = AllayBlockType
                .builder(BlockWoodenDoorBehavior.class)
                .vanillaBlock(BlockId.WOODEN_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWoodenPressurePlate() {
        if (BlockTypes.WOODEN_PRESSURE_PLATE != null) return;
        BlockTypes.WOODEN_PRESSURE_PLATE = AllayBlockType
                .builder(BlockWoodenPressurePlateBehavior.class)
                .vanillaBlock(BlockId.WOODEN_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initYellowCandle() {
        if (BlockTypes.YELLOW_CANDLE != null) return;
        BlockTypes.YELLOW_CANDLE = AllayBlockType
                .builder(BlockYellowCandleBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCandleCake() {
        if (BlockTypes.YELLOW_CANDLE_CAKE != null) return;
        BlockTypes.YELLOW_CANDLE_CAKE = AllayBlockType
                .builder(BlockYellowCandleCakeBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCarpet() {
        if (BlockTypes.YELLOW_CARPET != null) return;
        BlockTypes.YELLOW_CARPET = AllayBlockType
                .builder(BlockYellowCarpetBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CARPET)
                .build();
    }

    public static void initYellowConcrete() {
        if (BlockTypes.YELLOW_CONCRETE != null) return;
        BlockTypes.YELLOW_CONCRETE = AllayBlockType
                .builder(BlockYellowConcreteBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE)
                .build();
    }

    public static void initYellowConcretePowder() {
        if (BlockTypes.YELLOW_CONCRETE_POWDER != null) return;
        BlockTypes.YELLOW_CONCRETE_POWDER = AllayBlockType
                .builder(BlockYellowConcretePowderBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE_POWDER)
                .build();
    }

    public static void initYellowGlazedTerracotta() {
        if (BlockTypes.YELLOW_GLAZED_TERRACOTTA != null) return;
        BlockTypes.YELLOW_GLAZED_TERRACOTTA = AllayBlockType
                .builder(BlockYellowGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.YELLOW_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initYellowShulkerBox() {
        if (BlockTypes.YELLOW_SHULKER_BOX != null) return;
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockYellowShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .build();
    }

    public static void initYellowStainedGlass() {
        if (BlockTypes.YELLOW_STAINED_GLASS != null) return;
        BlockTypes.YELLOW_STAINED_GLASS = AllayBlockType
                .builder(BlockYellowStainedGlassBehavior.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initYellowStainedGlassPane() {
        if (BlockTypes.YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.YELLOW_STAINED_GLASS_PANE = AllayBlockType
                .builder(BlockYellowStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initYellowTerracotta() {
        if (BlockTypes.YELLOW_TERRACOTTA != null) return;
        BlockTypes.YELLOW_TERRACOTTA = AllayBlockType
                .builder(BlockYellowTerracottaBehavior.class)
                .vanillaBlock(BlockId.YELLOW_TERRACOTTA)
                .build();
    }

    public static void initYellowWool() {
        if (BlockTypes.YELLOW_WOOL != null) return;
        BlockTypes.YELLOW_WOOL = AllayBlockType
                .builder(BlockYellowWoolBehavior.class)
                .vanillaBlock(BlockId.YELLOW_WOOL)
                .build();
    }
}
