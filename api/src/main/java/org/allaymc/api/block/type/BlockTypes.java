package org.allaymc.api.block.type;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockActivatorRailBehavior;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.interfaces.BlockAlliumBehavior;
import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystBlockBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.block.interfaces.BlockAncientDebrisBehavior;
import org.allaymc.api.block.interfaces.BlockAndesiteBehavior;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.block.interfaces.BlockAzaleaBehavior;
import org.allaymc.api.block.interfaces.BlockAzureBluetBehavior;
import org.allaymc.api.block.interfaces.BlockBambooBehavior;
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
import org.allaymc.api.block.interfaces.BlockBricksBehavior;
import org.allaymc.api.block.interfaces.BlockBrownMushroomBehavior;
import org.allaymc.api.block.interfaces.BlockBrownMushroomBlockBehavior;
import org.allaymc.api.block.interfaces.BlockBubbleColumnBehavior;
import org.allaymc.api.block.interfaces.BlockBuddingAmethystBehavior;
import org.allaymc.api.block.interfaces.BlockButtonBehavior;
import org.allaymc.api.block.interfaces.BlockCactusBehavior;
import org.allaymc.api.block.interfaces.BlockCakeBehavior;
import org.allaymc.api.block.interfaces.BlockCalciteBehavior;
import org.allaymc.api.block.interfaces.BlockCalibratedSculkSensorBehavior;
import org.allaymc.api.block.interfaces.BlockCameraBehavior;
import org.allaymc.api.block.interfaces.BlockCampfireBehavior;
import org.allaymc.api.block.interfaces.BlockCandleBehavior;
import org.allaymc.api.block.interfaces.BlockCandleCakeBehavior;
import org.allaymc.api.block.interfaces.BlockCarpetBehavior;
import org.allaymc.api.block.interfaces.BlockCarrotsBehavior;
import org.allaymc.api.block.interfaces.BlockCartographyTableBehavior;
import org.allaymc.api.block.interfaces.BlockCarvedPumpkinBehavior;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.block.interfaces.BlockChainBehavior;
import org.allaymc.api.block.interfaces.BlockChainCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockChalkboardBehavior;
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
import org.allaymc.api.block.interfaces.BlockClosedEyeblossomBehavior;
import org.allaymc.api.block.interfaces.BlockCoalBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCoalOreBehavior;
import org.allaymc.api.block.interfaces.BlockCoarseDirtBehavior;
import org.allaymc.api.block.interfaces.BlockCobbledDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockCobblestoneBehavior;
import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.block.interfaces.BlockColoredTorchBehavior;
import org.allaymc.api.block.interfaces.BlockCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockComposterBehavior;
import org.allaymc.api.block.interfaces.BlockCompoundCreatorBehavior;
import org.allaymc.api.block.interfaces.BlockConcreteBehavior;
import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.block.interfaces.BlockConduitBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockCopperDoorBehavior;
import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockCopperOreBehavior;
import org.allaymc.api.block.interfaces.BlockCopperStairsBehavior;
import org.allaymc.api.block.interfaces.BlockCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.BlockCoralBehavior;
import org.allaymc.api.block.interfaces.BlockCoralBlockBehavior;
import org.allaymc.api.block.interfaces.BlockCoralFanBehavior;
import org.allaymc.api.block.interfaces.BlockCoralWallFanBehavior;
import org.allaymc.api.block.interfaces.BlockCornflowerBehavior;
import org.allaymc.api.block.interfaces.BlockCrackedDeepslateTilesBehavior;
import org.allaymc.api.block.interfaces.BlockCrafterBehavior;
import org.allaymc.api.block.interfaces.BlockCraftingTableBehavior;
import org.allaymc.api.block.interfaces.BlockCreakingHeartBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonFungusBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonNyliumBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockCrimsonRootsBehavior;
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
import org.allaymc.api.block.interfaces.BlockDirtBehavior;
import org.allaymc.api.block.interfaces.BlockDirtWithRootsBehavior;
import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
import org.allaymc.api.block.interfaces.BlockDoorBehavior;
import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.block.interfaces.BlockDriedKelpBlockBehavior;
import org.allaymc.api.block.interfaces.BlockDripstoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockDropperBehavior;
import org.allaymc.api.block.interfaces.BlockElementBehavior;
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
import org.allaymc.api.block.interfaces.BlockFenceBehavior;
import org.allaymc.api.block.interfaces.BlockFenceGateBehavior;
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
import org.allaymc.api.block.interfaces.BlockGlassBehavior;
import org.allaymc.api.block.interfaces.BlockGlassPaneBehavior;
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
import org.allaymc.api.block.interfaces.BlockHangingSignBehavior;
import org.allaymc.api.block.interfaces.BlockHardenedClayBehavior;
import org.allaymc.api.block.interfaces.BlockHayBlockBehavior;
import org.allaymc.api.block.interfaces.BlockHeadBehavior;
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
import org.allaymc.api.block.interfaces.BlockIronDoorBehavior;
import org.allaymc.api.block.interfaces.BlockIronOreBehavior;
import org.allaymc.api.block.interfaces.BlockIronTrapdoorBehavior;
import org.allaymc.api.block.interfaces.BlockJigsawBehavior;
import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.block.interfaces.BlockJunglePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockKelpBehavior;
import org.allaymc.api.block.interfaces.BlockLabTableBehavior;
import org.allaymc.api.block.interfaces.BlockLadderBehavior;
import org.allaymc.api.block.interfaces.BlockLanternBehavior;
import org.allaymc.api.block.interfaces.BlockLapisBlockBehavior;
import org.allaymc.api.block.interfaces.BlockLapisOreBehavior;
import org.allaymc.api.block.interfaces.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.interfaces.BlockLargeFernBehavior;
import org.allaymc.api.block.interfaces.BlockLeavesBehavior;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.interfaces.BlockLeverBehavior;
import org.allaymc.api.block.interfaces.BlockLightBlockBehavior;
import org.allaymc.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.block.interfaces.BlockLilacBehavior;
import org.allaymc.api.block.interfaces.BlockLilyOfTheValleyBehavior;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.interfaces.BlockLitDeepslateRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockLitPumpkinBehavior;
import org.allaymc.api.block.interfaces.BlockLitRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockLitRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockLodestoneBehavior;
import org.allaymc.api.block.interfaces.BlockLoomBehavior;
import org.allaymc.api.block.interfaces.BlockMagmaBehavior;
import org.allaymc.api.block.interfaces.BlockMangrovePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockMangrovePropaguleBehavior;
import org.allaymc.api.block.interfaces.BlockMangroveRootsBehavior;
import org.allaymc.api.block.interfaces.BlockMaterialReducerBehavior;
import org.allaymc.api.block.interfaces.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.interfaces.BlockMelonBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMelonStemBehavior;
import org.allaymc.api.block.interfaces.BlockMobSpawnerBehavior;
import org.allaymc.api.block.interfaces.BlockMossBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMossyCobblestoneBehavior;
import org.allaymc.api.block.interfaces.BlockMovingBlockBehavior;
import org.allaymc.api.block.interfaces.BlockMudBehavior;
import org.allaymc.api.block.interfaces.BlockMuddyMangroveRootsBehavior;
import org.allaymc.api.block.interfaces.BlockMushroomStemBehavior;
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
import org.allaymc.api.block.interfaces.BlockOpenEyeblossomBehavior;
import org.allaymc.api.block.interfaces.BlockOrangeTulipBehavior;
import org.allaymc.api.block.interfaces.BlockOxeyeDaisyBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockOxidizedCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockPackedIceBehavior;
import org.allaymc.api.block.interfaces.BlockPackedMudBehavior;
import org.allaymc.api.block.interfaces.BlockPaleHangingMossBehavior;
import org.allaymc.api.block.interfaces.BlockPaleMossBlockBehavior;
import org.allaymc.api.block.interfaces.BlockPaleOakPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockPearlescentFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockPeonyBehavior;
import org.allaymc.api.block.interfaces.BlockPinkPetalsBehavior;
import org.allaymc.api.block.interfaces.BlockPinkTulipBehavior;
import org.allaymc.api.block.interfaces.BlockPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.BlockPistonBehavior;
import org.allaymc.api.block.interfaces.BlockPitcherCropBehavior;
import org.allaymc.api.block.interfaces.BlockPitcherPlantBehavior;
import org.allaymc.api.block.interfaces.BlockPlanksBehavior;
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
import org.allaymc.api.block.interfaces.BlockPurpurPillarBehavior;
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
import org.allaymc.api.block.interfaces.BlockRedSandBehavior;
import org.allaymc.api.block.interfaces.BlockRedTulipBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneBlockBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneOreBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneTorchBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneWireBehavior;
import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import org.allaymc.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import org.allaymc.api.block.interfaces.BlockReserved6Behavior;
import org.allaymc.api.block.interfaces.BlockResinBlockBehavior;
import org.allaymc.api.block.interfaces.BlockResinClumpBehavior;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.block.interfaces.BlockRoseBushBehavior;
import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.block.interfaces.BlockSandstoneBehavior;
import org.allaymc.api.block.interfaces.BlockSaplingBehavior;
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
import org.allaymc.api.block.interfaces.BlockShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.BlockSignBehavior;
import org.allaymc.api.block.interfaces.BlockSlabBehavior;
import org.allaymc.api.block.interfaces.BlockSlimeBehavior;
import org.allaymc.api.block.interfaces.BlockSmallAmethystBudBehavior;
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
import org.allaymc.api.block.interfaces.BlockSoulSandBehavior;
import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.block.interfaces.BlockSoulTorchBehavior;
import org.allaymc.api.block.interfaces.BlockSpongeBehavior;
import org.allaymc.api.block.interfaces.BlockSporeBlossomBehavior;
import org.allaymc.api.block.interfaces.BlockSprucePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStairsBehavior;
import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.block.interfaces.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.BlockStickyPistonBehavior;
import org.allaymc.api.block.interfaces.BlockStoneBehavior;
import org.allaymc.api.block.interfaces.BlockStonePressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.block.interfaces.BlockStonecutterBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStructureBlockBehavior;
import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.block.interfaces.BlockSunflowerBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.block.interfaces.BlockTallGrassBehavior;
import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.block.interfaces.BlockTorchBehavior;
import org.allaymc.api.block.interfaces.BlockTorchflowerBehavior;
import org.allaymc.api.block.interfaces.BlockTorchflowerCropBehavior;
import org.allaymc.api.block.interfaces.BlockTrapdoorBehavior;
import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.block.interfaces.BlockTrialSpawnerBehavior;
import org.allaymc.api.block.interfaces.BlockTripWireBehavior;
import org.allaymc.api.block.interfaces.BlockTripwireHookBehavior;
import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.block.interfaces.BlockTurtleEggBehavior;
import org.allaymc.api.block.interfaces.BlockTwistingVinesBehavior;
import org.allaymc.api.block.interfaces.BlockUnderwaterTntBehavior;
import org.allaymc.api.block.interfaces.BlockUnderwaterTorchBehavior;
import org.allaymc.api.block.interfaces.BlockUnknownBehavior;
import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import org.allaymc.api.block.interfaces.BlockVaultBehavior;
import org.allaymc.api.block.interfaces.BlockVerdantFroglightBehavior;
import org.allaymc.api.block.interfaces.BlockVineBehavior;
import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedFungusBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedNyliumBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockWarpedRootsBehavior;
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
import org.allaymc.api.block.interfaces.BlockWetSpongeBehavior;
import org.allaymc.api.block.interfaces.BlockWheatBehavior;
import org.allaymc.api.block.interfaces.BlockWhiteTulipBehavior;
import org.allaymc.api.block.interfaces.BlockWitherRoseBehavior;
import org.allaymc.api.block.interfaces.BlockWoodBehavior;
import org.allaymc.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import org.allaymc.api.block.interfaces.BlockWoolBehavior;

/**
 * Automatically generated by {@code org.allaymc.codegen.BlockClassGen}
 */
@MinecraftVersionSensitive
public final class BlockTypes {
    public static BlockType<BlockButtonBehavior> ACACIA_BUTTON;

    public static BlockType<BlockDoorBehavior> ACACIA_DOOR;

    public static BlockType<BlockSlabBehavior> ACACIA_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> ACACIA_FENCE;

    public static BlockType<BlockFenceGateBehavior> ACACIA_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> ACACIA_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> ACACIA_LEAVES;

    public static BlockType<BlockWoodBehavior> ACACIA_LOG;

    public static BlockType<BlockPlanksBehavior> ACACIA_PLANKS;

    public static BlockType<BlockAcaciaPressurePlateBehavior> ACACIA_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> ACACIA_SAPLING;

    public static BlockType<BlockSlabBehavior> ACACIA_SLAB;

    public static BlockType<BlockStairsBehavior> ACACIA_STAIRS;

    public static BlockType<BlockSignBehavior> ACACIA_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> ACACIA_TRAPDOOR;

    public static BlockType<BlockSignBehavior> ACACIA_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> ACACIA_WOOD;

    public static BlockType<BlockActivatorRailBehavior> ACTIVATOR_RAIL;

    public static BlockType<BlockAirBehavior> AIR;

    public static BlockType<BlockAlliumBehavior> ALLIUM;

    public static BlockType<BlockAllowBehavior> ALLOW;

    public static BlockType<BlockAmethystBlockBehavior> AMETHYST_BLOCK;

    public static BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER;

    public static BlockType<BlockAncientDebrisBehavior> ANCIENT_DEBRIS;

    public static BlockType<BlockAndesiteBehavior> ANDESITE;

    public static BlockType<BlockSlabBehavior> ANDESITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> ANDESITE_SLAB;

    public static BlockType<BlockStairsBehavior> ANDESITE_STAIRS;

    public static BlockType<BlockWallBehavior> ANDESITE_WALL;

    public static BlockType<BlockAnvilBehavior> ANVIL;

    public static BlockType<BlockAzaleaBehavior> AZALEA;

    public static BlockType<BlockLeavesBehavior> AZALEA_LEAVES;

    public static BlockType<BlockLeavesBehavior> AZALEA_LEAVES_FLOWERED;

    public static BlockType<BlockAzureBluetBehavior> AZURE_BLUET;

    public static BlockType<BlockBambooBehavior> BAMBOO;

    public static BlockType<BlockWoodBehavior> BAMBOO_BLOCK;

    public static BlockType<BlockButtonBehavior> BAMBOO_BUTTON;

    public static BlockType<BlockDoorBehavior> BAMBOO_DOOR;

    public static BlockType<BlockSlabBehavior> BAMBOO_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> BAMBOO_FENCE;

    public static BlockType<BlockFenceGateBehavior> BAMBOO_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> BAMBOO_HANGING_SIGN;

    public static BlockType<BlockBambooMosaicBehavior> BAMBOO_MOSAIC;

    public static BlockType<BlockSlabBehavior> BAMBOO_MOSAIC_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> BAMBOO_MOSAIC_SLAB;

    public static BlockType<BlockStairsBehavior> BAMBOO_MOSAIC_STAIRS;

    public static BlockType<BlockPlanksBehavior> BAMBOO_PLANKS;

    public static BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> BAMBOO_SAPLING;

    public static BlockType<BlockSlabBehavior> BAMBOO_SLAB;

    public static BlockType<BlockStairsBehavior> BAMBOO_STAIRS;

    public static BlockType<BlockSignBehavior> BAMBOO_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> BAMBOO_TRAPDOOR;

    public static BlockType<BlockSignBehavior> BAMBOO_WALL_SIGN;

    public static BlockType<BlockBarrelBehavior> BARREL;

    public static BlockType<BlockBarrierBehavior> BARRIER;

    public static BlockType<BlockBasaltBehavior> BASALT;

    public static BlockType<BlockBeaconBehavior> BEACON;

    public static BlockType<BlockBedBehavior> BED;

    public static BlockType<BlockBedrockBehavior> BEDROCK;

    public static BlockType<BlockBeeNestBehavior> BEE_NEST;

    public static BlockType<BlockBeehiveBehavior> BEEHIVE;

    public static BlockType<BlockBeetrootBehavior> BEETROOT;

    public static BlockType<BlockBellBehavior> BELL;

    public static BlockType<BlockBigDripleafBehavior> BIG_DRIPLEAF;

    public static BlockType<BlockButtonBehavior> BIRCH_BUTTON;

    public static BlockType<BlockDoorBehavior> BIRCH_DOOR;

    public static BlockType<BlockSlabBehavior> BIRCH_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> BIRCH_FENCE;

    public static BlockType<BlockFenceGateBehavior> BIRCH_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> BIRCH_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> BIRCH_LEAVES;

    public static BlockType<BlockWoodBehavior> BIRCH_LOG;

    public static BlockType<BlockPlanksBehavior> BIRCH_PLANKS;

    public static BlockType<BlockBirchPressurePlateBehavior> BIRCH_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> BIRCH_SAPLING;

    public static BlockType<BlockSlabBehavior> BIRCH_SLAB;

    public static BlockType<BlockStairsBehavior> BIRCH_STAIRS;

    public static BlockType<BlockSignBehavior> BIRCH_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> BIRCH_TRAPDOOR;

    public static BlockType<BlockSignBehavior> BIRCH_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> BIRCH_WOOD;

    public static BlockType<BlockCandleBehavior> BLACK_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> BLACK_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BLACK_CARPET;

    public static BlockType<BlockConcreteBehavior> BLACK_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> BLACK_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> BLACK_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> BLACK_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BLACK_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> BLACK_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BLACK_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> BLACK_WOOL;

    public static BlockType<BlockBlackstoneBehavior> BLACKSTONE;

    public static BlockType<BlockSlabBehavior> BLACKSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> BLACKSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> BLACKSTONE_STAIRS;

    public static BlockType<BlockWallBehavior> BLACKSTONE_WALL;

    public static BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE;

    public static BlockType<BlockCandleBehavior> BLUE_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> BLUE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BLUE_CARPET;

    public static BlockType<BlockConcreteBehavior> BLUE_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> BLUE_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> BLUE_GLAZED_TERRACOTTA;

    public static BlockType<BlockBlueIceBehavior> BLUE_ICE;

    public static BlockType<BlockBlueOrchidBehavior> BLUE_ORCHID;

    public static BlockType<BlockShulkerBoxBehavior> BLUE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BLUE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BLUE_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> BLUE_WOOL;

    public static BlockType<BlockBoneBlockBehavior> BONE_BLOCK;

    public static BlockType<BlockBookshelfBehavior> BOOKSHELF;

    public static BlockType<BlockBorderBlockBehavior> BORDER_BLOCK;

    public static BlockType<BlockCoralBehavior> BRAIN_CORAL;

    public static BlockType<BlockCoralBlockBehavior> BRAIN_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> BRAIN_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> BRAIN_CORAL_WALL_FAN;

    public static BlockType<BlockBrewingStandBehavior> BREWING_STAND;

    public static BlockType<BlockBrickBlockBehavior> BRICK_BLOCK;

    public static BlockType<BlockSlabBehavior> BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> BRICK_WALL;

    public static BlockType<BlockCandleBehavior> BROWN_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> BROWN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BROWN_CARPET;

    public static BlockType<BlockConcreteBehavior> BROWN_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> BROWN_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> BROWN_GLAZED_TERRACOTTA;

    public static BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM;

    public static BlockType<BlockBrownMushroomBlockBehavior> BROWN_MUSHROOM_BLOCK;

    public static BlockType<BlockShulkerBoxBehavior> BROWN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BROWN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> BROWN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BROWN_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> BROWN_WOOL;

    public static BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN;

    public static BlockType<BlockCoralBehavior> BUBBLE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> BUBBLE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> BUBBLE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> BUBBLE_CORAL_WALL_FAN;

    public static BlockType<BlockBuddingAmethystBehavior> BUDDING_AMETHYST;

    public static BlockType<BlockCactusBehavior> CACTUS;

    public static BlockType<BlockCakeBehavior> CAKE;

    public static BlockType<BlockCalciteBehavior> CALCITE;

    public static BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR;

    public static BlockType<BlockCameraBehavior> CAMERA;

    public static BlockType<BlockCampfireBehavior> CAMPFIRE;

    public static BlockType<BlockCandleBehavior> CANDLE;

    public static BlockType<BlockCandleCakeBehavior> CANDLE_CAKE;

    public static BlockType<BlockCarrotsBehavior> CARROTS;

    public static BlockType<BlockCartographyTableBehavior> CARTOGRAPHY_TABLE;

    public static BlockType<BlockCarvedPumpkinBehavior> CARVED_PUMPKIN;

    public static BlockType<BlockCauldronBehavior> CAULDRON;

    public static BlockType<BlockCaveVinesBehavior> CAVE_VINES;

    public static BlockType<BlockCaveVinesBodyWithBerriesBehavior> CAVE_VINES_BODY_WITH_BERRIES;

    public static BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES;

    public static BlockType<BlockChainBehavior> CHAIN;

    public static BlockType<BlockChainCommandBlockBehavior> CHAIN_COMMAND_BLOCK;

    public static BlockType<BlockChalkboardBehavior> CHALKBOARD;

    public static BlockType<BlockChemicalHeatBehavior> CHEMICAL_HEAT;

    public static BlockType<BlockButtonBehavior> CHERRY_BUTTON;

    public static BlockType<BlockDoorBehavior> CHERRY_DOOR;

    public static BlockType<BlockSlabBehavior> CHERRY_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> CHERRY_FENCE;

    public static BlockType<BlockFenceGateBehavior> CHERRY_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> CHERRY_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> CHERRY_LEAVES;

    public static BlockType<BlockWoodBehavior> CHERRY_LOG;

    public static BlockType<BlockPlanksBehavior> CHERRY_PLANKS;

    public static BlockType<BlockCherryPressurePlateBehavior> CHERRY_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> CHERRY_SAPLING;

    public static BlockType<BlockSlabBehavior> CHERRY_SLAB;

    public static BlockType<BlockStairsBehavior> CHERRY_STAIRS;

    public static BlockType<BlockSignBehavior> CHERRY_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> CHERRY_TRAPDOOR;

    public static BlockType<BlockSignBehavior> CHERRY_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> CHERRY_WOOD;

    public static BlockType<BlockChestBehavior> CHEST;

    public static BlockType<BlockAnvilBehavior> CHIPPED_ANVIL;

    public static BlockType<BlockChiseledBookshelfBehavior> CHISELED_BOOKSHELF;

    public static BlockType<BlockCopperBehavior> CHISELED_COPPER;

    public static BlockType<BlockChiseledDeepslateBehavior> CHISELED_DEEPSLATE;

    public static BlockType<BlockBricksBehavior> CHISELED_NETHER_BRICKS;

    public static BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE;

    public static BlockType<BlockChiseledQuartzBlockBehavior> CHISELED_QUARTZ_BLOCK;

    public static BlockType<BlockSandstoneBehavior> CHISELED_RED_SANDSTONE;

    public static BlockType<BlockBricksBehavior> CHISELED_RESIN_BRICKS;

    public static BlockType<BlockSandstoneBehavior> CHISELED_SANDSTONE;

    public static BlockType<BlockBricksBehavior> CHISELED_STONE_BRICKS;

    public static BlockType<BlockChiseledTuffBehavior> CHISELED_TUFF;

    public static BlockType<BlockBricksBehavior> CHISELED_TUFF_BRICKS;

    public static BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER;

    public static BlockType<BlockChorusPlantBehavior> CHORUS_PLANT;

    public static BlockType<BlockClayBehavior> CLAY;

    public static BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK;

    public static BlockType<BlockClosedEyeblossomBehavior> CLOSED_EYEBLOSSOM;

    public static BlockType<BlockCoalBlockBehavior> COAL_BLOCK;

    public static BlockType<BlockCoalOreBehavior> COAL_ORE;

    public static BlockType<BlockCoarseDirtBehavior> COARSE_DIRT;

    public static BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE;

    public static BlockType<BlockSlabBehavior> COBBLED_DEEPSLATE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> COBBLED_DEEPSLATE_SLAB;

    public static BlockType<BlockStairsBehavior> COBBLED_DEEPSLATE_STAIRS;

    public static BlockType<BlockWallBehavior> COBBLED_DEEPSLATE_WALL;

    public static BlockType<BlockCobblestoneBehavior> COBBLESTONE;

    public static BlockType<BlockSlabBehavior> COBBLESTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> COBBLESTONE_SLAB;

    public static BlockType<BlockWallBehavior> COBBLESTONE_WALL;

    public static BlockType<BlockCocoaBehavior> COCOA;

    public static BlockType<BlockColoredTorchBehavior> COLORED_TORCH_BLUE;

    public static BlockType<BlockColoredTorchBehavior> COLORED_TORCH_GREEN;

    public static BlockType<BlockColoredTorchBehavior> COLORED_TORCH_PURPLE;

    public static BlockType<BlockColoredTorchBehavior> COLORED_TORCH_RED;

    public static BlockType<BlockCommandBlockBehavior> COMMAND_BLOCK;

    public static BlockType<BlockComposterBehavior> COMPOSTER;

    public static BlockType<BlockCompoundCreatorBehavior> COMPOUND_CREATOR;

    public static BlockType<BlockConduitBehavior> CONDUIT;

    public static BlockType<BlockCopperBehavior> COPPER_BLOCK;

    public static BlockType<BlockCopperBulbBehavior> COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> COPPER_DOOR;

    public static BlockType<BlockCopperGrateBehavior> COPPER_GRATE;

    public static BlockType<BlockCopperOreBehavior> COPPER_ORE;

    public static BlockType<BlockCopperTrapdoorBehavior> COPPER_TRAPDOOR;

    public static BlockType<BlockCornflowerBehavior> CORNFLOWER;

    public static BlockType<BlockBricksBehavior> CRACKED_DEEPSLATE_BRICKS;

    public static BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES;

    public static BlockType<BlockBricksBehavior> CRACKED_NETHER_BRICKS;

    public static BlockType<BlockBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS;

    public static BlockType<BlockBricksBehavior> CRACKED_STONE_BRICKS;

    public static BlockType<BlockCrafterBehavior> CRAFTER;

    public static BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE;

    public static BlockType<BlockCreakingHeartBehavior> CREAKING_HEART;

    public static BlockType<BlockHeadBehavior> CREEPER_HEAD;

    public static BlockType<BlockButtonBehavior> CRIMSON_BUTTON;

    public static BlockType<BlockDoorBehavior> CRIMSON_DOOR;

    public static BlockType<BlockSlabBehavior> CRIMSON_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> CRIMSON_FENCE;

    public static BlockType<BlockFenceGateBehavior> CRIMSON_FENCE_GATE;

    public static BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS;

    public static BlockType<BlockHangingSignBehavior> CRIMSON_HANGING_SIGN;

    public static BlockType<BlockWoodBehavior> CRIMSON_HYPHAE;

    public static BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM;

    public static BlockType<BlockPlanksBehavior> CRIMSON_PLANKS;

    public static BlockType<BlockCrimsonPressurePlateBehavior> CRIMSON_PRESSURE_PLATE;

    public static BlockType<BlockCrimsonRootsBehavior> CRIMSON_ROOTS;

    public static BlockType<BlockSlabBehavior> CRIMSON_SLAB;

    public static BlockType<BlockStairsBehavior> CRIMSON_STAIRS;

    public static BlockType<BlockSignBehavior> CRIMSON_STANDING_SIGN;

    public static BlockType<BlockWoodBehavior> CRIMSON_STEM;

    public static BlockType<BlockTrapdoorBehavior> CRIMSON_TRAPDOOR;

    public static BlockType<BlockSignBehavior> CRIMSON_WALL_SIGN;

    public static BlockType<BlockCryingObsidianBehavior> CRYING_OBSIDIAN;

    public static BlockType<BlockCopperBehavior> CUT_COPPER;

    public static BlockType<BlockSlabBehavior> CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> CUT_COPPER_STAIRS;

    public static BlockType<BlockSandstoneBehavior> CUT_RED_SANDSTONE;

    public static BlockType<BlockSlabBehavior> CUT_RED_SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> CUT_RED_SANDSTONE_SLAB;

    public static BlockType<BlockSandstoneBehavior> CUT_SANDSTONE;

    public static BlockType<BlockSlabBehavior> CUT_SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> CUT_SANDSTONE_SLAB;

    public static BlockType<BlockCandleBehavior> CYAN_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> CYAN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> CYAN_CARPET;

    public static BlockType<BlockConcreteBehavior> CYAN_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> CYAN_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> CYAN_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> CYAN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> CYAN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> CYAN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> CYAN_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> CYAN_WOOL;

    public static BlockType<BlockAnvilBehavior> DAMAGED_ANVIL;

    public static BlockType<BlockDandelionBehavior> DANDELION;

    public static BlockType<BlockButtonBehavior> DARK_OAK_BUTTON;

    public static BlockType<BlockDoorBehavior> DARK_OAK_DOOR;

    public static BlockType<BlockSlabBehavior> DARK_OAK_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> DARK_OAK_FENCE;

    public static BlockType<BlockFenceGateBehavior> DARK_OAK_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> DARK_OAK_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> DARK_OAK_LEAVES;

    public static BlockType<BlockWoodBehavior> DARK_OAK_LOG;

    public static BlockType<BlockPlanksBehavior> DARK_OAK_PLANKS;

    public static BlockType<BlockDarkOakPressurePlateBehavior> DARK_OAK_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> DARK_OAK_SAPLING;

    public static BlockType<BlockSlabBehavior> DARK_OAK_SLAB;

    public static BlockType<BlockStairsBehavior> DARK_OAK_STAIRS;

    public static BlockType<BlockTrapdoorBehavior> DARK_OAK_TRAPDOOR;

    public static BlockType<BlockWoodBehavior> DARK_OAK_WOOD;

    public static BlockType<BlockDarkPrismarineBehavior> DARK_PRISMARINE;

    public static BlockType<BlockSlabBehavior> DARK_PRISMARINE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> DARK_PRISMARINE_SLAB;

    public static BlockType<BlockStairsBehavior> DARK_PRISMARINE_STAIRS;

    public static BlockType<BlockSignBehavior> DARKOAK_STANDING_SIGN;

    public static BlockType<BlockSignBehavior> DARKOAK_WALL_SIGN;

    public static BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR;

    public static BlockType<BlockDaylightDetectorInvertedBehavior> DAYLIGHT_DETECTOR_INVERTED;

    public static BlockType<BlockCoralBehavior> DEAD_BRAIN_CORAL;

    public static BlockType<BlockCoralBlockBehavior> DEAD_BRAIN_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> DEAD_BRAIN_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> DEAD_BRAIN_CORAL_WALL_FAN;

    public static BlockType<BlockCoralBehavior> DEAD_BUBBLE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> DEAD_BUBBLE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> DEAD_BUBBLE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> DEAD_BUBBLE_CORAL_WALL_FAN;

    public static BlockType<BlockCoralBehavior> DEAD_FIRE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> DEAD_FIRE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> DEAD_FIRE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> DEAD_FIRE_CORAL_WALL_FAN;

    public static BlockType<BlockCoralBehavior> DEAD_HORN_CORAL;

    public static BlockType<BlockCoralBlockBehavior> DEAD_HORN_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> DEAD_HORN_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> DEAD_HORN_CORAL_WALL_FAN;

    public static BlockType<BlockCoralBehavior> DEAD_TUBE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> DEAD_TUBE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> DEAD_TUBE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> DEAD_TUBE_CORAL_WALL_FAN;

    public static BlockType<BlockDeadbushBehavior> DEADBUSH;

    public static BlockType<BlockDecoratedPotBehavior> DECORATED_POT;

    public static BlockType<BlockDeepslateBehavior> DEEPSLATE;

    public static BlockType<BlockSlabBehavior> DEEPSLATE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> DEEPSLATE_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> DEEPSLATE_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> DEEPSLATE_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> DEEPSLATE_BRICKS;

    public static BlockType<BlockDeepslateCoalOreBehavior> DEEPSLATE_COAL_ORE;

    public static BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE;

    public static BlockType<BlockDeepslateDiamondOreBehavior> DEEPSLATE_DIAMOND_ORE;

    public static BlockType<BlockDeepslateEmeraldOreBehavior> DEEPSLATE_EMERALD_ORE;

    public static BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE;

    public static BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE;

    public static BlockType<BlockDeepslateLapisOreBehavior> DEEPSLATE_LAPIS_ORE;

    public static BlockType<BlockDeepslateRedstoneOreBehavior> DEEPSLATE_REDSTONE_ORE;

    public static BlockType<BlockSlabBehavior> DEEPSLATE_TILE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> DEEPSLATE_TILE_SLAB;

    public static BlockType<BlockStairsBehavior> DEEPSLATE_TILE_STAIRS;

    public static BlockType<BlockWallBehavior> DEEPSLATE_TILE_WALL;

    public static BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES;

    public static BlockType<BlockDenyBehavior> DENY;

    public static BlockType<BlockAnvilBehavior> DEPRECATED_ANVIL;

    public static BlockType<BlockDeprecatedPurpurBlock1Behavior> DEPRECATED_PURPUR_BLOCK_1;

    public static BlockType<BlockDeprecatedPurpurBlock2Behavior> DEPRECATED_PURPUR_BLOCK_2;

    public static BlockType<BlockDetectorRailBehavior> DETECTOR_RAIL;

    public static BlockType<BlockDiamondBlockBehavior> DIAMOND_BLOCK;

    public static BlockType<BlockDiamondOreBehavior> DIAMOND_ORE;

    public static BlockType<BlockDioriteBehavior> DIORITE;

    public static BlockType<BlockSlabBehavior> DIORITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> DIORITE_SLAB;

    public static BlockType<BlockStairsBehavior> DIORITE_STAIRS;

    public static BlockType<BlockWallBehavior> DIORITE_WALL;

    public static BlockType<BlockDirtBehavior> DIRT;

    public static BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS;

    public static BlockType<BlockDispenserBehavior> DISPENSER;

    public static BlockType<BlockSlabBehavior> DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockDragonEggBehavior> DRAGON_EGG;

    public static BlockType<BlockHeadBehavior> DRAGON_HEAD;

    public static BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK;

    public static BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK;

    public static BlockType<BlockDropperBehavior> DROPPER;

    public static BlockType<BlockElementBehavior> ELEMENT_0;

    public static BlockType<BlockElementBehavior> ELEMENT_1;

    public static BlockType<BlockElementBehavior> ELEMENT_10;

    public static BlockType<BlockElementBehavior> ELEMENT_100;

    public static BlockType<BlockElementBehavior> ELEMENT_101;

    public static BlockType<BlockElementBehavior> ELEMENT_102;

    public static BlockType<BlockElementBehavior> ELEMENT_103;

    public static BlockType<BlockElementBehavior> ELEMENT_104;

    public static BlockType<BlockElementBehavior> ELEMENT_105;

    public static BlockType<BlockElementBehavior> ELEMENT_106;

    public static BlockType<BlockElementBehavior> ELEMENT_107;

    public static BlockType<BlockElementBehavior> ELEMENT_108;

    public static BlockType<BlockElementBehavior> ELEMENT_109;

    public static BlockType<BlockElementBehavior> ELEMENT_11;

    public static BlockType<BlockElementBehavior> ELEMENT_110;

    public static BlockType<BlockElementBehavior> ELEMENT_111;

    public static BlockType<BlockElementBehavior> ELEMENT_112;

    public static BlockType<BlockElementBehavior> ELEMENT_113;

    public static BlockType<BlockElementBehavior> ELEMENT_114;

    public static BlockType<BlockElementBehavior> ELEMENT_115;

    public static BlockType<BlockElementBehavior> ELEMENT_116;

    public static BlockType<BlockElementBehavior> ELEMENT_117;

    public static BlockType<BlockElementBehavior> ELEMENT_118;

    public static BlockType<BlockElementBehavior> ELEMENT_12;

    public static BlockType<BlockElementBehavior> ELEMENT_13;

    public static BlockType<BlockElementBehavior> ELEMENT_14;

    public static BlockType<BlockElementBehavior> ELEMENT_15;

    public static BlockType<BlockElementBehavior> ELEMENT_16;

    public static BlockType<BlockElementBehavior> ELEMENT_17;

    public static BlockType<BlockElementBehavior> ELEMENT_18;

    public static BlockType<BlockElementBehavior> ELEMENT_19;

    public static BlockType<BlockElementBehavior> ELEMENT_2;

    public static BlockType<BlockElementBehavior> ELEMENT_20;

    public static BlockType<BlockElementBehavior> ELEMENT_21;

    public static BlockType<BlockElementBehavior> ELEMENT_22;

    public static BlockType<BlockElementBehavior> ELEMENT_23;

    public static BlockType<BlockElementBehavior> ELEMENT_24;

    public static BlockType<BlockElementBehavior> ELEMENT_25;

    public static BlockType<BlockElementBehavior> ELEMENT_26;

    public static BlockType<BlockElementBehavior> ELEMENT_27;

    public static BlockType<BlockElementBehavior> ELEMENT_28;

    public static BlockType<BlockElementBehavior> ELEMENT_29;

    public static BlockType<BlockElementBehavior> ELEMENT_3;

    public static BlockType<BlockElementBehavior> ELEMENT_30;

    public static BlockType<BlockElementBehavior> ELEMENT_31;

    public static BlockType<BlockElementBehavior> ELEMENT_32;

    public static BlockType<BlockElementBehavior> ELEMENT_33;

    public static BlockType<BlockElementBehavior> ELEMENT_34;

    public static BlockType<BlockElementBehavior> ELEMENT_35;

    public static BlockType<BlockElementBehavior> ELEMENT_36;

    public static BlockType<BlockElementBehavior> ELEMENT_37;

    public static BlockType<BlockElementBehavior> ELEMENT_38;

    public static BlockType<BlockElementBehavior> ELEMENT_39;

    public static BlockType<BlockElementBehavior> ELEMENT_4;

    public static BlockType<BlockElementBehavior> ELEMENT_40;

    public static BlockType<BlockElementBehavior> ELEMENT_41;

    public static BlockType<BlockElementBehavior> ELEMENT_42;

    public static BlockType<BlockElementBehavior> ELEMENT_43;

    public static BlockType<BlockElementBehavior> ELEMENT_44;

    public static BlockType<BlockElementBehavior> ELEMENT_45;

    public static BlockType<BlockElementBehavior> ELEMENT_46;

    public static BlockType<BlockElementBehavior> ELEMENT_47;

    public static BlockType<BlockElementBehavior> ELEMENT_48;

    public static BlockType<BlockElementBehavior> ELEMENT_49;

    public static BlockType<BlockElementBehavior> ELEMENT_5;

    public static BlockType<BlockElementBehavior> ELEMENT_50;

    public static BlockType<BlockElementBehavior> ELEMENT_51;

    public static BlockType<BlockElementBehavior> ELEMENT_52;

    public static BlockType<BlockElementBehavior> ELEMENT_53;

    public static BlockType<BlockElementBehavior> ELEMENT_54;

    public static BlockType<BlockElementBehavior> ELEMENT_55;

    public static BlockType<BlockElementBehavior> ELEMENT_56;

    public static BlockType<BlockElementBehavior> ELEMENT_57;

    public static BlockType<BlockElementBehavior> ELEMENT_58;

    public static BlockType<BlockElementBehavior> ELEMENT_59;

    public static BlockType<BlockElementBehavior> ELEMENT_6;

    public static BlockType<BlockElementBehavior> ELEMENT_60;

    public static BlockType<BlockElementBehavior> ELEMENT_61;

    public static BlockType<BlockElementBehavior> ELEMENT_62;

    public static BlockType<BlockElementBehavior> ELEMENT_63;

    public static BlockType<BlockElementBehavior> ELEMENT_64;

    public static BlockType<BlockElementBehavior> ELEMENT_65;

    public static BlockType<BlockElementBehavior> ELEMENT_66;

    public static BlockType<BlockElementBehavior> ELEMENT_67;

    public static BlockType<BlockElementBehavior> ELEMENT_68;

    public static BlockType<BlockElementBehavior> ELEMENT_69;

    public static BlockType<BlockElementBehavior> ELEMENT_7;

    public static BlockType<BlockElementBehavior> ELEMENT_70;

    public static BlockType<BlockElementBehavior> ELEMENT_71;

    public static BlockType<BlockElementBehavior> ELEMENT_72;

    public static BlockType<BlockElementBehavior> ELEMENT_73;

    public static BlockType<BlockElementBehavior> ELEMENT_74;

    public static BlockType<BlockElementBehavior> ELEMENT_75;

    public static BlockType<BlockElementBehavior> ELEMENT_76;

    public static BlockType<BlockElementBehavior> ELEMENT_77;

    public static BlockType<BlockElementBehavior> ELEMENT_78;

    public static BlockType<BlockElementBehavior> ELEMENT_79;

    public static BlockType<BlockElementBehavior> ELEMENT_8;

    public static BlockType<BlockElementBehavior> ELEMENT_80;

    public static BlockType<BlockElementBehavior> ELEMENT_81;

    public static BlockType<BlockElementBehavior> ELEMENT_82;

    public static BlockType<BlockElementBehavior> ELEMENT_83;

    public static BlockType<BlockElementBehavior> ELEMENT_84;

    public static BlockType<BlockElementBehavior> ELEMENT_85;

    public static BlockType<BlockElementBehavior> ELEMENT_86;

    public static BlockType<BlockElementBehavior> ELEMENT_87;

    public static BlockType<BlockElementBehavior> ELEMENT_88;

    public static BlockType<BlockElementBehavior> ELEMENT_89;

    public static BlockType<BlockElementBehavior> ELEMENT_9;

    public static BlockType<BlockElementBehavior> ELEMENT_90;

    public static BlockType<BlockElementBehavior> ELEMENT_91;

    public static BlockType<BlockElementBehavior> ELEMENT_92;

    public static BlockType<BlockElementBehavior> ELEMENT_93;

    public static BlockType<BlockElementBehavior> ELEMENT_94;

    public static BlockType<BlockElementBehavior> ELEMENT_95;

    public static BlockType<BlockElementBehavior> ELEMENT_96;

    public static BlockType<BlockElementBehavior> ELEMENT_97;

    public static BlockType<BlockElementBehavior> ELEMENT_98;

    public static BlockType<BlockElementBehavior> ELEMENT_99;

    public static BlockType<BlockElementBehavior> ELEMENT_CONSTRUCTOR;

    public static BlockType<BlockEmeraldBlockBehavior> EMERALD_BLOCK;

    public static BlockType<BlockEmeraldOreBehavior> EMERALD_ORE;

    public static BlockType<BlockEnchantingTableBehavior> ENCHANTING_TABLE;

    public static BlockType<BlockStairsBehavior> END_BRICK_STAIRS;

    public static BlockType<BlockBricksBehavior> END_BRICKS;

    public static BlockType<BlockEndGatewayBehavior> END_GATEWAY;

    public static BlockType<BlockEndPortalBehavior> END_PORTAL;

    public static BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME;

    public static BlockType<BlockEndRodBehavior> END_ROD;

    public static BlockType<BlockEndStoneBehavior> END_STONE;

    public static BlockType<BlockSlabBehavior> END_STONE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> END_STONE_BRICK_SLAB;

    public static BlockType<BlockWallBehavior> END_STONE_BRICK_WALL;

    public static BlockType<BlockEnderChestBehavior> ENDER_CHEST;

    public static BlockType<BlockCopperBehavior> EXPOSED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> EXPOSED_COPPER;

    public static BlockType<BlockExposedCopperBulbBehavior> EXPOSED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> EXPOSED_COPPER_DOOR;

    public static BlockType<BlockExposedCopperGrateBehavior> EXPOSED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> EXPOSED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> EXPOSED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> EXPOSED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> EXPOSED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockFarmlandBehavior> FARMLAND;

    public static BlockType<BlockFenceGateBehavior> FENCE_GATE;

    public static BlockType<BlockFernBehavior> FERN;

    public static BlockType<BlockFireBehavior> FIRE;

    public static BlockType<BlockCoralBehavior> FIRE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> FIRE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> FIRE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> FIRE_CORAL_WALL_FAN;

    public static BlockType<BlockFletchingTableBehavior> FLETCHING_TABLE;

    public static BlockType<BlockFlowerPotBehavior> FLOWER_POT;

    public static BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA;

    public static BlockType<BlockLiquidBehavior> FLOWING_LAVA;

    public static BlockType<BlockLiquidBehavior> FLOWING_WATER;

    public static BlockType<BlockFrameBehavior> FRAME;

    public static BlockType<BlockFrogSpawnBehavior> FROG_SPAWN;

    public static BlockType<BlockFrostedIceBehavior> FROSTED_ICE;

    public static BlockType<BlockFurnaceBehavior> FURNACE;

    public static BlockType<BlockGildedBlackstoneBehavior> GILDED_BLACKSTONE;

    public static BlockType<BlockGlassBehavior> GLASS;

    public static BlockType<BlockGlassPaneBehavior> GLASS_PANE;

    public static BlockType<BlockGlowFrameBehavior> GLOW_FRAME;

    public static BlockType<BlockGlowLichenBehavior> GLOW_LICHEN;

    public static BlockType<BlockGlowingobsidianBehavior> GLOWINGOBSIDIAN;

    public static BlockType<BlockGlowstoneBehavior> GLOWSTONE;

    public static BlockType<BlockGoldBlockBehavior> GOLD_BLOCK;

    public static BlockType<BlockGoldOreBehavior> GOLD_ORE;

    public static BlockType<BlockGoldenRailBehavior> GOLDEN_RAIL;

    public static BlockType<BlockGraniteBehavior> GRANITE;

    public static BlockType<BlockSlabBehavior> GRANITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> GRANITE_SLAB;

    public static BlockType<BlockStairsBehavior> GRANITE_STAIRS;

    public static BlockType<BlockWallBehavior> GRANITE_WALL;

    public static BlockType<BlockGrassBlockBehavior> GRASS_BLOCK;

    public static BlockType<BlockGrassPathBehavior> GRASS_PATH;

    public static BlockType<BlockGravelBehavior> GRAVEL;

    public static BlockType<BlockCandleBehavior> GRAY_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> GRAY_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> GRAY_CARPET;

    public static BlockType<BlockConcreteBehavior> GRAY_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> GRAY_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> GRAY_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> GRAY_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> GRAY_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> GRAY_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> GRAY_WOOL;

    public static BlockType<BlockCandleBehavior> GREEN_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> GREEN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> GREEN_CARPET;

    public static BlockType<BlockConcreteBehavior> GREEN_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> GREEN_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> GREEN_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> GREEN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> GREEN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> GREEN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> GREEN_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> GREEN_WOOL;

    public static BlockType<BlockGrindstoneBehavior> GRINDSTONE;

    public static BlockType<BlockHangingRootsBehavior> HANGING_ROOTS;

    public static BlockType<BlockGlassBehavior> HARD_BLACK_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_BLACK_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_BLUE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_BROWN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_BROWN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_CYAN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_CYAN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GRAY_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GREEN_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_GREEN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIGHT_BLUE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_LIGHT_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIGHT_GRAY_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_LIGHT_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIME_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_LIME_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_MAGENTA_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_MAGENTA_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_ORANGE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_ORANGE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_PINK_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_PINK_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_PURPLE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_PURPLE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_RED_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_RED_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_WHITE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_WHITE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_YELLOW_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> HARD_YELLOW_STAINED_GLASS_PANE;

    public static BlockType<BlockHardenedClayBehavior> HARDENED_CLAY;

    public static BlockType<BlockHayBlockBehavior> HAY_BLOCK;

    public static BlockType<BlockHeavyCoreBehavior> HEAVY_CORE;

    public static BlockType<BlockHeavyWeightedPressurePlateBehavior> HEAVY_WEIGHTED_PRESSURE_PLATE;

    public static BlockType<BlockHoneyBlockBehavior> HONEY_BLOCK;

    public static BlockType<BlockHoneycombBlockBehavior> HONEYCOMB_BLOCK;

    public static BlockType<BlockHopperBehavior> HOPPER;

    public static BlockType<BlockCoralBehavior> HORN_CORAL;

    public static BlockType<BlockCoralBlockBehavior> HORN_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> HORN_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> HORN_CORAL_WALL_FAN;

    public static BlockType<BlockIceBehavior> ICE;

    public static BlockType<BlockBricksBehavior> INFESTED_CHISELED_STONE_BRICKS;

    public static BlockType<BlockInfestedCobblestoneBehavior> INFESTED_COBBLESTONE;

    public static BlockType<BlockBricksBehavior> INFESTED_CRACKED_STONE_BRICKS;

    public static BlockType<BlockInfestedDeepslateBehavior> INFESTED_DEEPSLATE;

    public static BlockType<BlockBricksBehavior> INFESTED_MOSSY_STONE_BRICKS;

    public static BlockType<BlockInfestedStoneBehavior> INFESTED_STONE;

    public static BlockType<BlockBricksBehavior> INFESTED_STONE_BRICKS;

    public static BlockType<BlockInfoUpdateBehavior> INFO_UPDATE;

    public static BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2;

    public static BlockType<BlockInvisibleBedrockBehavior> INVISIBLE_BEDROCK;

    public static BlockType<BlockIronBarsBehavior> IRON_BARS;

    public static BlockType<BlockIronBlockBehavior> IRON_BLOCK;

    public static BlockType<BlockIronDoorBehavior> IRON_DOOR;

    public static BlockType<BlockIronOreBehavior> IRON_ORE;

    public static BlockType<BlockIronTrapdoorBehavior> IRON_TRAPDOOR;

    public static BlockType<BlockJigsawBehavior> JIGSAW;

    public static BlockType<BlockJukeboxBehavior> JUKEBOX;

    public static BlockType<BlockButtonBehavior> JUNGLE_BUTTON;

    public static BlockType<BlockDoorBehavior> JUNGLE_DOOR;

    public static BlockType<BlockSlabBehavior> JUNGLE_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> JUNGLE_FENCE;

    public static BlockType<BlockFenceGateBehavior> JUNGLE_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> JUNGLE_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> JUNGLE_LEAVES;

    public static BlockType<BlockWoodBehavior> JUNGLE_LOG;

    public static BlockType<BlockPlanksBehavior> JUNGLE_PLANKS;

    public static BlockType<BlockJunglePressurePlateBehavior> JUNGLE_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> JUNGLE_SAPLING;

    public static BlockType<BlockSlabBehavior> JUNGLE_SLAB;

    public static BlockType<BlockStairsBehavior> JUNGLE_STAIRS;

    public static BlockType<BlockSignBehavior> JUNGLE_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> JUNGLE_TRAPDOOR;

    public static BlockType<BlockSignBehavior> JUNGLE_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> JUNGLE_WOOD;

    public static BlockType<BlockKelpBehavior> KELP;

    public static BlockType<BlockLabTableBehavior> LAB_TABLE;

    public static BlockType<BlockLadderBehavior> LADDER;

    public static BlockType<BlockLanternBehavior> LANTERN;

    public static BlockType<BlockLapisBlockBehavior> LAPIS_BLOCK;

    public static BlockType<BlockLapisOreBehavior> LAPIS_ORE;

    public static BlockType<BlockLargeAmethystBudBehavior> LARGE_AMETHYST_BUD;

    public static BlockType<BlockLargeFernBehavior> LARGE_FERN;

    public static BlockType<BlockLiquidBehavior> LAVA;

    public static BlockType<BlockLecternBehavior> LECTERN;

    public static BlockType<BlockLeverBehavior> LEVER;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_0;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_1;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_10;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_11;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_12;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_13;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_14;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_15;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_2;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_3;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_4;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_5;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_6;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_7;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_8;

    public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_9;

    public static BlockType<BlockCandleBehavior> LIGHT_BLUE_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIGHT_BLUE_CARPET;

    public static BlockType<BlockConcreteBehavior> LIGHT_BLUE_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> LIGHT_BLUE_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> LIGHT_BLUE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIGHT_BLUE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> LIGHT_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIGHT_BLUE_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> LIGHT_BLUE_WOOL;

    public static BlockType<BlockCandleBehavior> LIGHT_GRAY_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIGHT_GRAY_CARPET;

    public static BlockType<BlockConcreteBehavior> LIGHT_GRAY_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> LIGHT_GRAY_CONCRETE_POWDER;

    public static BlockType<BlockShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIGHT_GRAY_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> LIGHT_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIGHT_GRAY_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> LIGHT_GRAY_WOOL;

    public static BlockType<BlockLightWeightedPressurePlateBehavior> LIGHT_WEIGHTED_PRESSURE_PLATE;

    public static BlockType<BlockLightningRodBehavior> LIGHTNING_ROD;

    public static BlockType<BlockLilacBehavior> LILAC;

    public static BlockType<BlockLilyOfTheValleyBehavior> LILY_OF_THE_VALLEY;

    public static BlockType<BlockCandleBehavior> LIME_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> LIME_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIME_CARPET;

    public static BlockType<BlockConcreteBehavior> LIME_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> LIME_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> LIME_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> LIME_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIME_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> LIME_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIME_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> LIME_WOOL;

    public static BlockType<BlockBlastFurnaceBehavior> LIT_BLAST_FURNACE;

    public static BlockType<BlockLitDeepslateRedstoneOreBehavior> LIT_DEEPSLATE_REDSTONE_ORE;

    public static BlockType<BlockFurnaceBehavior> LIT_FURNACE;

    public static BlockType<BlockLitPumpkinBehavior> LIT_PUMPKIN;

    public static BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP;

    public static BlockType<BlockLitRedstoneOreBehavior> LIT_REDSTONE_ORE;

    public static BlockType<BlockSmokerBehavior> LIT_SMOKER;

    public static BlockType<BlockLodestoneBehavior> LODESTONE;

    public static BlockType<BlockLoomBehavior> LOOM;

    public static BlockType<BlockCandleBehavior> MAGENTA_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> MAGENTA_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> MAGENTA_CARPET;

    public static BlockType<BlockConcreteBehavior> MAGENTA_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> MAGENTA_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> MAGENTA_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> MAGENTA_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> MAGENTA_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> MAGENTA_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> MAGENTA_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> MAGENTA_WOOL;

    public static BlockType<BlockMagmaBehavior> MAGMA;

    public static BlockType<BlockButtonBehavior> MANGROVE_BUTTON;

    public static BlockType<BlockDoorBehavior> MANGROVE_DOOR;

    public static BlockType<BlockSlabBehavior> MANGROVE_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> MANGROVE_FENCE;

    public static BlockType<BlockFenceGateBehavior> MANGROVE_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> MANGROVE_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> MANGROVE_LEAVES;

    public static BlockType<BlockWoodBehavior> MANGROVE_LOG;

    public static BlockType<BlockPlanksBehavior> MANGROVE_PLANKS;

    public static BlockType<BlockMangrovePressurePlateBehavior> MANGROVE_PRESSURE_PLATE;

    public static BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE;

    public static BlockType<BlockMangroveRootsBehavior> MANGROVE_ROOTS;

    public static BlockType<BlockSlabBehavior> MANGROVE_SLAB;

    public static BlockType<BlockStairsBehavior> MANGROVE_STAIRS;

    public static BlockType<BlockSignBehavior> MANGROVE_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> MANGROVE_TRAPDOOR;

    public static BlockType<BlockSignBehavior> MANGROVE_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> MANGROVE_WOOD;

    public static BlockType<BlockMaterialReducerBehavior> MATERIAL_REDUCER;

    public static BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD;

    public static BlockType<BlockMelonBlockBehavior> MELON_BLOCK;

    public static BlockType<BlockMelonStemBehavior> MELON_STEM;

    public static BlockType<BlockMobSpawnerBehavior> MOB_SPAWNER;

    public static BlockType<BlockMossBlockBehavior> MOSS_BLOCK;

    public static BlockType<BlockCarpetBehavior> MOSS_CARPET;

    public static BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE;

    public static BlockType<BlockSlabBehavior> MOSSY_COBBLESTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> MOSSY_COBBLESTONE_SLAB;

    public static BlockType<BlockStairsBehavior> MOSSY_COBBLESTONE_STAIRS;

    public static BlockType<BlockWallBehavior> MOSSY_COBBLESTONE_WALL;

    public static BlockType<BlockSlabBehavior> MOSSY_STONE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> MOSSY_STONE_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> MOSSY_STONE_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> MOSSY_STONE_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> MOSSY_STONE_BRICKS;

    public static BlockType<BlockMovingBlockBehavior> MOVING_BLOCK;

    public static BlockType<BlockMudBehavior> MUD;

    public static BlockType<BlockSlabBehavior> MUD_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> MUD_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> MUD_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> MUD_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> MUD_BRICKS;

    public static BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS;

    public static BlockType<BlockMushroomStemBehavior> MUSHROOM_STEM;

    public static BlockType<BlockMyceliumBehavior> MYCELIUM;

    public static BlockType<BlockNetherBrickBehavior> NETHER_BRICK;

    public static BlockType<BlockSlabBehavior> NETHER_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> NETHER_BRICK_FENCE;

    public static BlockType<BlockSlabBehavior> NETHER_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> NETHER_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> NETHER_BRICK_WALL;

    public static BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE;

    public static BlockType<BlockNetherSproutsBehavior> NETHER_SPROUTS;

    public static BlockType<BlockNetherWartBehavior> NETHER_WART;

    public static BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK;

    public static BlockType<BlockNetheriteBlockBehavior> NETHERITE_BLOCK;

    public static BlockType<BlockNetherrackBehavior> NETHERRACK;

    public static BlockType<BlockNetherreactorBehavior> NETHERREACTOR;

    public static BlockType<BlockSlabBehavior> NORMAL_STONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> NORMAL_STONE_SLAB;

    public static BlockType<BlockStairsBehavior> NORMAL_STONE_STAIRS;

    public static BlockType<BlockNoteblockBehavior> NOTEBLOCK;

    public static BlockType<BlockSlabBehavior> OAK_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> OAK_FENCE;

    public static BlockType<BlockHangingSignBehavior> OAK_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> OAK_LEAVES;

    public static BlockType<BlockWoodBehavior> OAK_LOG;

    public static BlockType<BlockPlanksBehavior> OAK_PLANKS;

    public static BlockType<BlockSaplingBehavior> OAK_SAPLING;

    public static BlockType<BlockSlabBehavior> OAK_SLAB;

    public static BlockType<BlockStairsBehavior> OAK_STAIRS;

    public static BlockType<BlockWoodBehavior> OAK_WOOD;

    public static BlockType<BlockObserverBehavior> OBSERVER;

    public static BlockType<BlockObsidianBehavior> OBSIDIAN;

    public static BlockType<BlockOchreFroglightBehavior> OCHRE_FROGLIGHT;

    public static BlockType<BlockOpenEyeblossomBehavior> OPEN_EYEBLOSSOM;

    public static BlockType<BlockCandleBehavior> ORANGE_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> ORANGE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> ORANGE_CARPET;

    public static BlockType<BlockConcreteBehavior> ORANGE_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> ORANGE_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> ORANGE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> ORANGE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> ORANGE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> ORANGE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> ORANGE_TERRACOTTA;

    public static BlockType<BlockOrangeTulipBehavior> ORANGE_TULIP;

    public static BlockType<BlockWoolBehavior> ORANGE_WOOL;

    public static BlockType<BlockOxeyeDaisyBehavior> OXEYE_DAISY;

    public static BlockType<BlockCopperBehavior> OXIDIZED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> OXIDIZED_COPPER;

    public static BlockType<BlockOxidizedCopperBulbBehavior> OXIDIZED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> OXIDIZED_COPPER_DOOR;

    public static BlockType<BlockOxidizedCopperGrateBehavior> OXIDIZED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> OXIDIZED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> OXIDIZED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> OXIDIZED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> OXIDIZED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockPackedIceBehavior> PACKED_ICE;

    public static BlockType<BlockPackedMudBehavior> PACKED_MUD;

    public static BlockType<BlockPaleHangingMossBehavior> PALE_HANGING_MOSS;

    public static BlockType<BlockPaleMossBlockBehavior> PALE_MOSS_BLOCK;

    public static BlockType<BlockCarpetBehavior> PALE_MOSS_CARPET;

    public static BlockType<BlockButtonBehavior> PALE_OAK_BUTTON;

    public static BlockType<BlockDoorBehavior> PALE_OAK_DOOR;

    public static BlockType<BlockSlabBehavior> PALE_OAK_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> PALE_OAK_FENCE;

    public static BlockType<BlockFenceGateBehavior> PALE_OAK_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> PALE_OAK_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> PALE_OAK_LEAVES;

    public static BlockType<BlockWoodBehavior> PALE_OAK_LOG;

    public static BlockType<BlockPlanksBehavior> PALE_OAK_PLANKS;

    public static BlockType<BlockPaleOakPressurePlateBehavior> PALE_OAK_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> PALE_OAK_SAPLING;

    public static BlockType<BlockSlabBehavior> PALE_OAK_SLAB;

    public static BlockType<BlockStairsBehavior> PALE_OAK_STAIRS;

    public static BlockType<BlockSignBehavior> PALE_OAK_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> PALE_OAK_TRAPDOOR;

    public static BlockType<BlockSignBehavior> PALE_OAK_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> PALE_OAK_WOOD;

    public static BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT;

    public static BlockType<BlockPeonyBehavior> PEONY;

    public static BlockType<BlockSlabBehavior> PETRIFIED_OAK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> PETRIFIED_OAK_SLAB;

    public static BlockType<BlockHeadBehavior> PIGLIN_HEAD;

    public static BlockType<BlockCandleBehavior> PINK_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> PINK_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> PINK_CARPET;

    public static BlockType<BlockConcreteBehavior> PINK_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> PINK_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> PINK_GLAZED_TERRACOTTA;

    public static BlockType<BlockPinkPetalsBehavior> PINK_PETALS;

    public static BlockType<BlockShulkerBoxBehavior> PINK_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> PINK_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> PINK_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> PINK_TERRACOTTA;

    public static BlockType<BlockPinkTulipBehavior> PINK_TULIP;

    public static BlockType<BlockWoolBehavior> PINK_WOOL;

    public static BlockType<BlockPistonBehavior> PISTON;

    public static BlockType<BlockPistonArmCollisionBehavior> PISTON_ARM_COLLISION;

    public static BlockType<BlockPitcherCropBehavior> PITCHER_CROP;

    public static BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT;

    public static BlockType<BlockHeadBehavior> PLAYER_HEAD;

    public static BlockType<BlockPodzolBehavior> PODZOL;

    public static BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE;

    public static BlockType<BlockPolishedAndesiteBehavior> POLISHED_ANDESITE;

    public static BlockType<BlockSlabBehavior> POLISHED_ANDESITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_ANDESITE_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_ANDESITE_STAIRS;

    public static BlockType<BlockPolishedBasaltBehavior> POLISHED_BASALT;

    public static BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE;

    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_BLACKSTONE_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> POLISHED_BLACKSTONE_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> POLISHED_BLACKSTONE_BRICKS;

    public static BlockType<BlockButtonBehavior> POLISHED_BLACKSTONE_BUTTON;

    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_DOUBLE_SLAB;

    public static BlockType<BlockPolishedBlackstonePressurePlateBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE;

    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_BLACKSTONE_STAIRS;

    public static BlockType<BlockWallBehavior> POLISHED_BLACKSTONE_WALL;

    public static BlockType<BlockPolishedDeepslateBehavior> POLISHED_DEEPSLATE;

    public static BlockType<BlockSlabBehavior> POLISHED_DEEPSLATE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_DEEPSLATE_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_DEEPSLATE_STAIRS;

    public static BlockType<BlockWallBehavior> POLISHED_DEEPSLATE_WALL;

    public static BlockType<BlockPolishedDioriteBehavior> POLISHED_DIORITE;

    public static BlockType<BlockSlabBehavior> POLISHED_DIORITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_DIORITE_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_DIORITE_STAIRS;

    public static BlockType<BlockPolishedGraniteBehavior> POLISHED_GRANITE;

    public static BlockType<BlockSlabBehavior> POLISHED_GRANITE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_GRANITE_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_GRANITE_STAIRS;

    public static BlockType<BlockPolishedTuffBehavior> POLISHED_TUFF;

    public static BlockType<BlockSlabBehavior> POLISHED_TUFF_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> POLISHED_TUFF_SLAB;

    public static BlockType<BlockStairsBehavior> POLISHED_TUFF_STAIRS;

    public static BlockType<BlockWallBehavior> POLISHED_TUFF_WALL;

    public static BlockType<BlockPoppyBehavior> POPPY;

    public static BlockType<BlockPortalBehavior> PORTAL;

    public static BlockType<BlockPotatoesBehavior> POTATOES;

    public static BlockType<BlockPowderSnowBehavior> POWDER_SNOW;

    public static BlockType<BlockPoweredComparatorBehavior> POWERED_COMPARATOR;

    public static BlockType<BlockPoweredRepeaterBehavior> POWERED_REPEATER;

    public static BlockType<BlockPrismarineBehavior> PRISMARINE;

    public static BlockType<BlockSlabBehavior> PRISMARINE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> PRISMARINE_BRICK_SLAB;

    public static BlockType<BlockBricksBehavior> PRISMARINE_BRICKS;

    public static BlockType<BlockStairsBehavior> PRISMARINE_BRICKS_STAIRS;

    public static BlockType<BlockSlabBehavior> PRISMARINE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> PRISMARINE_SLAB;

    public static BlockType<BlockStairsBehavior> PRISMARINE_STAIRS;

    public static BlockType<BlockWallBehavior> PRISMARINE_WALL;

    public static BlockType<BlockPumpkinBehavior> PUMPKIN;

    public static BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM;

    public static BlockType<BlockCandleBehavior> PURPLE_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> PURPLE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> PURPLE_CARPET;

    public static BlockType<BlockConcreteBehavior> PURPLE_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> PURPLE_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> PURPLE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> PURPLE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> PURPLE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> PURPLE_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> PURPLE_WOOL;

    public static BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK;

    public static BlockType<BlockSlabBehavior> PURPUR_DOUBLE_SLAB;

    public static BlockType<BlockPurpurPillarBehavior> PURPUR_PILLAR;

    public static BlockType<BlockSlabBehavior> PURPUR_SLAB;

    public static BlockType<BlockStairsBehavior> PURPUR_STAIRS;

    public static BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK;

    public static BlockType<BlockBricksBehavior> QUARTZ_BRICKS;

    public static BlockType<BlockSlabBehavior> QUARTZ_DOUBLE_SLAB;

    public static BlockType<BlockQuartzOreBehavior> QUARTZ_ORE;

    public static BlockType<BlockQuartzPillarBehavior> QUARTZ_PILLAR;

    public static BlockType<BlockSlabBehavior> QUARTZ_SLAB;

    public static BlockType<BlockStairsBehavior> QUARTZ_STAIRS;

    public static BlockType<BlockRailBehavior> RAIL;

    public static BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK;

    public static BlockType<BlockRawGoldBlockBehavior> RAW_GOLD_BLOCK;

    public static BlockType<BlockRawIronBlockBehavior> RAW_IRON_BLOCK;

    public static BlockType<BlockCandleBehavior> RED_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> RED_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> RED_CARPET;

    public static BlockType<BlockConcreteBehavior> RED_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> RED_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> RED_GLAZED_TERRACOTTA;

    public static BlockType<BlockRedMushroomBehavior> RED_MUSHROOM;

    public static BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK;

    public static BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK;

    public static BlockType<BlockSlabBehavior> RED_NETHER_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> RED_NETHER_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> RED_NETHER_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> RED_NETHER_BRICK_WALL;

    public static BlockType<BlockRedSandBehavior> RED_SAND;

    public static BlockType<BlockSandstoneBehavior> RED_SANDSTONE;

    public static BlockType<BlockSlabBehavior> RED_SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> RED_SANDSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> RED_SANDSTONE_STAIRS;

    public static BlockType<BlockWallBehavior> RED_SANDSTONE_WALL;

    public static BlockType<BlockShulkerBoxBehavior> RED_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> RED_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> RED_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> RED_TERRACOTTA;

    public static BlockType<BlockRedTulipBehavior> RED_TULIP;

    public static BlockType<BlockWoolBehavior> RED_WOOL;

    public static BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK;

    public static BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP;

    public static BlockType<BlockRedstoneOreBehavior> REDSTONE_ORE;

    public static BlockType<BlockRedstoneTorchBehavior> REDSTONE_TORCH;

    public static BlockType<BlockRedstoneWireBehavior> REDSTONE_WIRE;

    public static BlockType<BlockReedsBehavior> REEDS;

    public static BlockType<BlockReinforcedDeepslateBehavior> REINFORCED_DEEPSLATE;

    public static BlockType<BlockRepeatingCommandBlockBehavior> REPEATING_COMMAND_BLOCK;

    public static BlockType<BlockReserved6Behavior> RESERVED6;

    public static BlockType<BlockResinBlockBehavior> RESIN_BLOCK;

    public static BlockType<BlockSlabBehavior> RESIN_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> RESIN_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> RESIN_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> RESIN_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> RESIN_BRICKS;

    public static BlockType<BlockResinClumpBehavior> RESIN_CLUMP;

    public static BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR;

    public static BlockType<BlockRoseBushBehavior> ROSE_BUSH;

    public static BlockType<BlockSandBehavior> SAND;

    public static BlockType<BlockSandstoneBehavior> SANDSTONE;

    public static BlockType<BlockSlabBehavior> SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> SANDSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> SANDSTONE_STAIRS;

    public static BlockType<BlockWallBehavior> SANDSTONE_WALL;

    public static BlockType<BlockScaffoldingBehavior> SCAFFOLDING;

    public static BlockType<BlockSculkBehavior> SCULK;

    public static BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST;

    public static BlockType<BlockSculkSensorBehavior> SCULK_SENSOR;

    public static BlockType<BlockSculkShriekerBehavior> SCULK_SHRIEKER;

    public static BlockType<BlockSculkVeinBehavior> SCULK_VEIN;

    public static BlockType<BlockSeaLanternBehavior> SEA_LANTERN;

    public static BlockType<BlockSeaPickleBehavior> SEA_PICKLE;

    public static BlockType<BlockSeagrassBehavior> SEAGRASS;

    public static BlockType<BlockShortGrassBehavior> SHORT_GRASS;

    public static BlockType<BlockShroomlightBehavior> SHROOMLIGHT;

    public static BlockType<BlockTerracottaBehavior> SILVER_GLAZED_TERRACOTTA;

    public static BlockType<BlockHeadBehavior> SKELETON_SKULL;

    public static BlockType<BlockSlimeBehavior> SLIME;

    public static BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD;

    public static BlockType<BlockSmallDripleafBlockBehavior> SMALL_DRIPLEAF_BLOCK;

    public static BlockType<BlockSmithingTableBehavior> SMITHING_TABLE;

    public static BlockType<BlockSmokerBehavior> SMOKER;

    public static BlockType<BlockSmoothBasaltBehavior> SMOOTH_BASALT;

    public static BlockType<BlockSmoothQuartzBehavior> SMOOTH_QUARTZ;

    public static BlockType<BlockSlabBehavior> SMOOTH_QUARTZ_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> SMOOTH_QUARTZ_SLAB;

    public static BlockType<BlockStairsBehavior> SMOOTH_QUARTZ_STAIRS;

    public static BlockType<BlockSandstoneBehavior> SMOOTH_RED_SANDSTONE;

    public static BlockType<BlockSlabBehavior> SMOOTH_RED_SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> SMOOTH_RED_SANDSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> SMOOTH_RED_SANDSTONE_STAIRS;

    public static BlockType<BlockSandstoneBehavior> SMOOTH_SANDSTONE;

    public static BlockType<BlockSlabBehavior> SMOOTH_SANDSTONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> SMOOTH_SANDSTONE_SLAB;

    public static BlockType<BlockStairsBehavior> SMOOTH_SANDSTONE_STAIRS;

    public static BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE;

    public static BlockType<BlockSlabBehavior> SMOOTH_STONE_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> SMOOTH_STONE_SLAB;

    public static BlockType<BlockSnifferEggBehavior> SNIFFER_EGG;

    public static BlockType<BlockSnowBehavior> SNOW;

    public static BlockType<BlockSnowLayerBehavior> SNOW_LAYER;

    public static BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE;

    public static BlockType<BlockFireBehavior> SOUL_FIRE;

    public static BlockType<BlockSoulLanternBehavior> SOUL_LANTERN;

    public static BlockType<BlockSoulSandBehavior> SOUL_SAND;

    public static BlockType<BlockSoulSoilBehavior> SOUL_SOIL;

    public static BlockType<BlockSoulTorchBehavior> SOUL_TORCH;

    public static BlockType<BlockSpongeBehavior> SPONGE;

    public static BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM;

    public static BlockType<BlockButtonBehavior> SPRUCE_BUTTON;

    public static BlockType<BlockDoorBehavior> SPRUCE_DOOR;

    public static BlockType<BlockSlabBehavior> SPRUCE_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> SPRUCE_FENCE;

    public static BlockType<BlockFenceGateBehavior> SPRUCE_FENCE_GATE;

    public static BlockType<BlockHangingSignBehavior> SPRUCE_HANGING_SIGN;

    public static BlockType<BlockLeavesBehavior> SPRUCE_LEAVES;

    public static BlockType<BlockWoodBehavior> SPRUCE_LOG;

    public static BlockType<BlockPlanksBehavior> SPRUCE_PLANKS;

    public static BlockType<BlockSprucePressurePlateBehavior> SPRUCE_PRESSURE_PLATE;

    public static BlockType<BlockSaplingBehavior> SPRUCE_SAPLING;

    public static BlockType<BlockSlabBehavior> SPRUCE_SLAB;

    public static BlockType<BlockStairsBehavior> SPRUCE_STAIRS;

    public static BlockType<BlockSignBehavior> SPRUCE_STANDING_SIGN;

    public static BlockType<BlockTrapdoorBehavior> SPRUCE_TRAPDOOR;

    public static BlockType<BlockSignBehavior> SPRUCE_WALL_SIGN;

    public static BlockType<BlockWoodBehavior> SPRUCE_WOOD;

    public static BlockType<BlockStandingBannerBehavior> STANDING_BANNER;

    public static BlockType<BlockSignBehavior> STANDING_SIGN;

    public static BlockType<BlockStickyPistonBehavior> STICKY_PISTON;

    public static BlockType<BlockStickyPistonArmCollisionBehavior> STICKY_PISTON_ARM_COLLISION;

    public static BlockType<BlockStoneBehavior> STONE;

    public static BlockType<BlockSlabBehavior> STONE_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> STONE_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> STONE_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> STONE_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> STONE_BRICKS;

    public static BlockType<BlockButtonBehavior> STONE_BUTTON;

    public static BlockType<BlockStonePressurePlateBehavior> STONE_PRESSURE_PLATE;

    public static BlockType<BlockStairsBehavior> STONE_STAIRS;

    public static BlockType<BlockStonecutterBehavior> STONECUTTER;

    public static BlockType<BlockStonecutterBlockBehavior> STONECUTTER_BLOCK;

    public static BlockType<BlockWoodBehavior> STRIPPED_ACACIA_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_ACACIA_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_BAMBOO_BLOCK;

    public static BlockType<BlockWoodBehavior> STRIPPED_BIRCH_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_BIRCH_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_CHERRY_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_CHERRY_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_CRIMSON_HYPHAE;

    public static BlockType<BlockWoodBehavior> STRIPPED_CRIMSON_STEM;

    public static BlockType<BlockWoodBehavior> STRIPPED_DARK_OAK_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_DARK_OAK_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_JUNGLE_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_JUNGLE_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_MANGROVE_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_MANGROVE_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_OAK_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_OAK_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_PALE_OAK_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_PALE_OAK_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_SPRUCE_LOG;

    public static BlockType<BlockWoodBehavior> STRIPPED_SPRUCE_WOOD;

    public static BlockType<BlockWoodBehavior> STRIPPED_WARPED_HYPHAE;

    public static BlockType<BlockWoodBehavior> STRIPPED_WARPED_STEM;

    public static BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK;

    public static BlockType<BlockStructureVoidBehavior> STRUCTURE_VOID;

    public static BlockType<BlockSunflowerBehavior> SUNFLOWER;

    public static BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL;

    public static BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND;

    public static BlockType<BlockSweetBerryBushBehavior> SWEET_BERRY_BUSH;

    public static BlockType<BlockTallGrassBehavior> TALL_GRASS;

    public static BlockType<BlockTargetBehavior> TARGET;

    public static BlockType<BlockGlassBehavior> TINTED_GLASS;

    public static BlockType<BlockTntBehavior> TNT;

    public static BlockType<BlockTorchBehavior> TORCH;

    public static BlockType<BlockTorchflowerBehavior> TORCHFLOWER;

    public static BlockType<BlockTorchflowerCropBehavior> TORCHFLOWER_CROP;

    public static BlockType<BlockTrapdoorBehavior> TRAPDOOR;

    public static BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST;

    public static BlockType<BlockTrialSpawnerBehavior> TRIAL_SPAWNER;

    public static BlockType<BlockTripWireBehavior> TRIP_WIRE;

    public static BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK;

    public static BlockType<BlockCoralBehavior> TUBE_CORAL;

    public static BlockType<BlockCoralBlockBehavior> TUBE_CORAL_BLOCK;

    public static BlockType<BlockCoralFanBehavior> TUBE_CORAL_FAN;

    public static BlockType<BlockCoralWallFanBehavior> TUBE_CORAL_WALL_FAN;

    public static BlockType<BlockTuffBehavior> TUFF;

    public static BlockType<BlockSlabBehavior> TUFF_BRICK_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> TUFF_BRICK_SLAB;

    public static BlockType<BlockStairsBehavior> TUFF_BRICK_STAIRS;

    public static BlockType<BlockWallBehavior> TUFF_BRICK_WALL;

    public static BlockType<BlockBricksBehavior> TUFF_BRICKS;

    public static BlockType<BlockSlabBehavior> TUFF_DOUBLE_SLAB;

    public static BlockType<BlockSlabBehavior> TUFF_SLAB;

    public static BlockType<BlockStairsBehavior> TUFF_STAIRS;

    public static BlockType<BlockWallBehavior> TUFF_WALL;

    public static BlockType<BlockTurtleEggBehavior> TURTLE_EGG;

    public static BlockType<BlockTwistingVinesBehavior> TWISTING_VINES;

    public static BlockType<BlockUnderwaterTntBehavior> UNDERWATER_TNT;

    public static BlockType<BlockUnderwaterTorchBehavior> UNDERWATER_TORCH;

    public static BlockType<BlockShulkerBoxBehavior> UNDYED_SHULKER_BOX;

    public static BlockType<BlockUnknownBehavior> UNKNOWN;

    public static BlockType<BlockRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH;

    public static BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR;

    public static BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER;

    public static BlockType<BlockVaultBehavior> VAULT;

    public static BlockType<BlockVerdantFroglightBehavior> VERDANT_FROGLIGHT;

    public static BlockType<BlockVineBehavior> VINE;

    public static BlockType<BlockWallBannerBehavior> WALL_BANNER;

    public static BlockType<BlockSignBehavior> WALL_SIGN;

    public static BlockType<BlockButtonBehavior> WARPED_BUTTON;

    public static BlockType<BlockDoorBehavior> WARPED_DOOR;

    public static BlockType<BlockSlabBehavior> WARPED_DOUBLE_SLAB;

    public static BlockType<BlockFenceBehavior> WARPED_FENCE;

    public static BlockType<BlockFenceGateBehavior> WARPED_FENCE_GATE;

    public static BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS;

    public static BlockType<BlockHangingSignBehavior> WARPED_HANGING_SIGN;

    public static BlockType<BlockWoodBehavior> WARPED_HYPHAE;

    public static BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM;

    public static BlockType<BlockPlanksBehavior> WARPED_PLANKS;

    public static BlockType<BlockWarpedPressurePlateBehavior> WARPED_PRESSURE_PLATE;

    public static BlockType<BlockWarpedRootsBehavior> WARPED_ROOTS;

    public static BlockType<BlockSlabBehavior> WARPED_SLAB;

    public static BlockType<BlockStairsBehavior> WARPED_STAIRS;

    public static BlockType<BlockSignBehavior> WARPED_STANDING_SIGN;

    public static BlockType<BlockWoodBehavior> WARPED_STEM;

    public static BlockType<BlockTrapdoorBehavior> WARPED_TRAPDOOR;

    public static BlockType<BlockSignBehavior> WARPED_WALL_SIGN;

    public static BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK;

    public static BlockType<BlockLiquidBehavior> WATER;

    public static BlockType<BlockWaterlilyBehavior> WATERLILY;

    public static BlockType<BlockCopperBehavior> WAXED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_COPPER;

    public static BlockType<BlockWaxedCopperBulbBehavior> WAXED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> WAXED_COPPER_DOOR;

    public static BlockType<BlockWaxedCopperGrateBehavior> WAXED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> WAXED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> WAXED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> WAXED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_COPPER;

    public static BlockType<BlockWaxedExposedCopperBulbBehavior> WAXED_EXPOSED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> WAXED_EXPOSED_COPPER_DOOR;

    public static BlockType<BlockWaxedExposedCopperGrateBehavior> WAXED_EXPOSED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_EXPOSED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> WAXED_EXPOSED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_COPPER;

    public static BlockType<BlockWaxedOxidizedCopperBulbBehavior> WAXED_OXIDIZED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> WAXED_OXIDIZED_COPPER_DOOR;

    public static BlockType<BlockWaxedOxidizedCopperGrateBehavior> WAXED_OXIDIZED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_OXIDIZED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> WAXED_OXIDIZED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_COPPER;

    public static BlockType<BlockWaxedWeatheredCopperBulbBehavior> WAXED_WEATHERED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> WAXED_WEATHERED_COPPER_DOOR;

    public static BlockType<BlockWaxedWeatheredCopperGrateBehavior> WAXED_WEATHERED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_WEATHERED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> WAXED_WEATHERED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> WAXED_WEATHERED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperBehavior> WEATHERED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WEATHERED_COPPER;

    public static BlockType<BlockWeatheredCopperBulbBehavior> WEATHERED_COPPER_BULB;

    public static BlockType<BlockCopperDoorBehavior> WEATHERED_COPPER_DOOR;

    public static BlockType<BlockWeatheredCopperGrateBehavior> WEATHERED_COPPER_GRATE;

    public static BlockType<BlockCopperTrapdoorBehavior> WEATHERED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WEATHERED_CUT_COPPER;

    public static BlockType<BlockSlabBehavior> WEATHERED_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperStairsBehavior> WEATHERED_CUT_COPPER_STAIRS;

    public static BlockType<BlockSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockWebBehavior> WEB;

    public static BlockType<BlockWeepingVinesBehavior> WEEPING_VINES;

    public static BlockType<BlockWetSpongeBehavior> WET_SPONGE;

    public static BlockType<BlockWheatBehavior> WHEAT;

    public static BlockType<BlockCandleBehavior> WHITE_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> WHITE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> WHITE_CARPET;

    public static BlockType<BlockConcreteBehavior> WHITE_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> WHITE_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> WHITE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> WHITE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> WHITE_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> WHITE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> WHITE_TERRACOTTA;

    public static BlockType<BlockWhiteTulipBehavior> WHITE_TULIP;

    public static BlockType<BlockWoolBehavior> WHITE_WOOL;

    public static BlockType<BlockWitherRoseBehavior> WITHER_ROSE;

    public static BlockType<BlockHeadBehavior> WITHER_SKELETON_SKULL;

    public static BlockType<BlockButtonBehavior> WOODEN_BUTTON;

    public static BlockType<BlockDoorBehavior> WOODEN_DOOR;

    public static BlockType<BlockWoodenPressurePlateBehavior> WOODEN_PRESSURE_PLATE;

    public static BlockType<BlockCandleBehavior> YELLOW_CANDLE;

    public static BlockType<BlockCandleCakeBehavior> YELLOW_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> YELLOW_CARPET;

    public static BlockType<BlockConcreteBehavior> YELLOW_CONCRETE;

    public static BlockType<BlockConcretePowderBehavior> YELLOW_CONCRETE_POWDER;

    public static BlockType<BlockTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> YELLOW_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> YELLOW_STAINED_GLASS;

    public static BlockType<BlockGlassPaneBehavior> YELLOW_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> YELLOW_TERRACOTTA;

    public static BlockType<BlockWoolBehavior> YELLOW_WOOL;

    public static BlockType<BlockHeadBehavior> ZOMBIE_HEAD;
}
