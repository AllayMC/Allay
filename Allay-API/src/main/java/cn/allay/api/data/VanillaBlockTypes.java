package cn.allay.api.data;

import cn.allay.api.block.behavior.BlockAcaciaButtonBehavior;
import cn.allay.api.block.behavior.BlockAcaciaDoorBehavior;
import cn.allay.api.block.behavior.BlockAcaciaFenceBehavior;
import cn.allay.api.block.behavior.BlockAcaciaFenceGateBehavior;
import cn.allay.api.block.behavior.BlockAcaciaHangingSignBehavior;
import cn.allay.api.block.behavior.BlockAcaciaLogBehavior;
import cn.allay.api.block.behavior.BlockAcaciaPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockAcaciaStairsBehavior;
import cn.allay.api.block.behavior.BlockAcaciaStandingSignBehavior;
import cn.allay.api.block.behavior.BlockAcaciaTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockAcaciaWallSignBehavior;
import cn.allay.api.block.behavior.BlockActivatorRailBehavior;
import cn.allay.api.block.behavior.BlockAirBehavior;
import cn.allay.api.block.behavior.BlockAllowBehavior;
import cn.allay.api.block.behavior.BlockAmethystBlockBehavior;
import cn.allay.api.block.behavior.BlockAmethystClusterBehavior;
import cn.allay.api.block.behavior.BlockAncientDebrisBehavior;
import cn.allay.api.block.behavior.BlockAndesiteStairsBehavior;
import cn.allay.api.block.behavior.BlockAnvilBehavior;
import cn.allay.api.block.behavior.BlockAzaleaBehavior;
import cn.allay.api.block.behavior.BlockAzaleaLeavesBehavior;
import cn.allay.api.block.behavior.BlockAzaleaLeavesFloweredBehavior;
import cn.allay.api.block.behavior.BlockBambooBehavior;
import cn.allay.api.block.behavior.BlockBambooBlockBehavior;
import cn.allay.api.block.behavior.BlockBambooButtonBehavior;
import cn.allay.api.block.behavior.BlockBambooDoorBehavior;
import cn.allay.api.block.behavior.BlockBambooDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockBambooFenceBehavior;
import cn.allay.api.block.behavior.BlockBambooFenceGateBehavior;
import cn.allay.api.block.behavior.BlockBambooHangingSignBehavior;
import cn.allay.api.block.behavior.BlockBambooMosaicBehavior;
import cn.allay.api.block.behavior.BlockBambooMosaicDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockBambooMosaicSlabBehavior;
import cn.allay.api.block.behavior.BlockBambooMosaicStairsBehavior;
import cn.allay.api.block.behavior.BlockBambooPlanksBehavior;
import cn.allay.api.block.behavior.BlockBambooPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockBambooSaplingBehavior;
import cn.allay.api.block.behavior.BlockBambooSlabBehavior;
import cn.allay.api.block.behavior.BlockBambooStairsBehavior;
import cn.allay.api.block.behavior.BlockBambooStandingSignBehavior;
import cn.allay.api.block.behavior.BlockBambooTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockBambooWallSignBehavior;
import cn.allay.api.block.behavior.BlockBarrelBehavior;
import cn.allay.api.block.behavior.BlockBarrierBehavior;
import cn.allay.api.block.behavior.BlockBasaltBehavior;
import cn.allay.api.block.behavior.BlockBeaconBehavior;
import cn.allay.api.block.behavior.BlockBedBehavior;
import cn.allay.api.block.behavior.BlockBedrockBehavior;
import cn.allay.api.block.behavior.BlockBeeNestBehavior;
import cn.allay.api.block.behavior.BlockBeehiveBehavior;
import cn.allay.api.block.behavior.BlockBeetrootBehavior;
import cn.allay.api.block.behavior.BlockBellBehavior;
import cn.allay.api.block.behavior.BlockBigDripleafBehavior;
import cn.allay.api.block.behavior.BlockBirchButtonBehavior;
import cn.allay.api.block.behavior.BlockBirchDoorBehavior;
import cn.allay.api.block.behavior.BlockBirchFenceBehavior;
import cn.allay.api.block.behavior.BlockBirchFenceGateBehavior;
import cn.allay.api.block.behavior.BlockBirchHangingSignBehavior;
import cn.allay.api.block.behavior.BlockBirchLogBehavior;
import cn.allay.api.block.behavior.BlockBirchPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockBirchStairsBehavior;
import cn.allay.api.block.behavior.BlockBirchStandingSignBehavior;
import cn.allay.api.block.behavior.BlockBirchTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockBirchWallSignBehavior;
import cn.allay.api.block.behavior.BlockBlackCandleBehavior;
import cn.allay.api.block.behavior.BlockBlackCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockBlackCarpetBehavior;
import cn.allay.api.block.behavior.BlockBlackConcreteBehavior;
import cn.allay.api.block.behavior.BlockBlackGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockBlackShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockBlackWoolBehavior;
import cn.allay.api.block.behavior.BlockBlackstoneBehavior;
import cn.allay.api.block.behavior.BlockBlackstoneDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockBlackstoneSlabBehavior;
import cn.allay.api.block.behavior.BlockBlackstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockBlackstoneWallBehavior;
import cn.allay.api.block.behavior.BlockBlastFurnaceBehavior;
import cn.allay.api.block.behavior.BlockBlueCandleBehavior;
import cn.allay.api.block.behavior.BlockBlueCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockBlueCarpetBehavior;
import cn.allay.api.block.behavior.BlockBlueConcreteBehavior;
import cn.allay.api.block.behavior.BlockBlueGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockBlueIceBehavior;
import cn.allay.api.block.behavior.BlockBlueShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockBlueWoolBehavior;
import cn.allay.api.block.behavior.BlockBoneBlockBehavior;
import cn.allay.api.block.behavior.BlockBookshelfBehavior;
import cn.allay.api.block.behavior.BlockBorderBlockBehavior;
import cn.allay.api.block.behavior.BlockBrainCoralBehavior;
import cn.allay.api.block.behavior.BlockBrewingStandBehavior;
import cn.allay.api.block.behavior.BlockBrickBlockBehavior;
import cn.allay.api.block.behavior.BlockBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockBrownCandleBehavior;
import cn.allay.api.block.behavior.BlockBrownCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockBrownCarpetBehavior;
import cn.allay.api.block.behavior.BlockBrownConcreteBehavior;
import cn.allay.api.block.behavior.BlockBrownGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockBrownMushroomBehavior;
import cn.allay.api.block.behavior.BlockBrownMushroomBlockBehavior;
import cn.allay.api.block.behavior.BlockBrownShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockBrownWoolBehavior;
import cn.allay.api.block.behavior.BlockBubbleColumnBehavior;
import cn.allay.api.block.behavior.BlockBubbleCoralBehavior;
import cn.allay.api.block.behavior.BlockBuddingAmethystBehavior;
import cn.allay.api.block.behavior.BlockCactusBehavior;
import cn.allay.api.block.behavior.BlockCakeBehavior;
import cn.allay.api.block.behavior.BlockCalciteBehavior;
import cn.allay.api.block.behavior.BlockCalibratedSculkSensorBehavior;
import cn.allay.api.block.behavior.BlockCameraBehavior;
import cn.allay.api.block.behavior.BlockCampfireBehavior;
import cn.allay.api.block.behavior.BlockCandleBehavior;
import cn.allay.api.block.behavior.BlockCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockCarrotsBehavior;
import cn.allay.api.block.behavior.BlockCartographyTableBehavior;
import cn.allay.api.block.behavior.BlockCarvedPumpkinBehavior;
import cn.allay.api.block.behavior.BlockCauldronBehavior;
import cn.allay.api.block.behavior.BlockCaveVinesBehavior;
import cn.allay.api.block.behavior.BlockCaveVinesBodyWithBerriesBehavior;
import cn.allay.api.block.behavior.BlockCaveVinesHeadWithBerriesBehavior;
import cn.allay.api.block.behavior.BlockChainBehavior;
import cn.allay.api.block.behavior.BlockChainCommandBlockBehavior;
import cn.allay.api.block.behavior.BlockChemicalHeatBehavior;
import cn.allay.api.block.behavior.BlockChemistryTableBehavior;
import cn.allay.api.block.behavior.BlockCherryButtonBehavior;
import cn.allay.api.block.behavior.BlockCherryDoorBehavior;
import cn.allay.api.block.behavior.BlockCherryDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockCherryFenceBehavior;
import cn.allay.api.block.behavior.BlockCherryFenceGateBehavior;
import cn.allay.api.block.behavior.BlockCherryHangingSignBehavior;
import cn.allay.api.block.behavior.BlockCherryLeavesBehavior;
import cn.allay.api.block.behavior.BlockCherryLogBehavior;
import cn.allay.api.block.behavior.BlockCherryPlanksBehavior;
import cn.allay.api.block.behavior.BlockCherryPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockCherrySaplingBehavior;
import cn.allay.api.block.behavior.BlockCherrySlabBehavior;
import cn.allay.api.block.behavior.BlockCherryStairsBehavior;
import cn.allay.api.block.behavior.BlockCherryStandingSignBehavior;
import cn.allay.api.block.behavior.BlockCherryTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockCherryWallSignBehavior;
import cn.allay.api.block.behavior.BlockCherryWoodBehavior;
import cn.allay.api.block.behavior.BlockChestBehavior;
import cn.allay.api.block.behavior.BlockChiseledBookshelfBehavior;
import cn.allay.api.block.behavior.BlockChiseledDeepslateBehavior;
import cn.allay.api.block.behavior.BlockChiseledNetherBricksBehavior;
import cn.allay.api.block.behavior.BlockChiseledPolishedBlackstoneBehavior;
import cn.allay.api.block.behavior.BlockChorusFlowerBehavior;
import cn.allay.api.block.behavior.BlockChorusPlantBehavior;
import cn.allay.api.block.behavior.BlockClayBehavior;
import cn.allay.api.block.behavior.BlockClientRequestPlaceholderBlockBehavior;
import cn.allay.api.block.behavior.BlockCoalBlockBehavior;
import cn.allay.api.block.behavior.BlockCoalOreBehavior;
import cn.allay.api.block.behavior.BlockCobbledDeepslateBehavior;
import cn.allay.api.block.behavior.BlockCobbledDeepslateDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockCobbledDeepslateSlabBehavior;
import cn.allay.api.block.behavior.BlockCobbledDeepslateStairsBehavior;
import cn.allay.api.block.behavior.BlockCobbledDeepslateWallBehavior;
import cn.allay.api.block.behavior.BlockCobblestoneBehavior;
import cn.allay.api.block.behavior.BlockCobblestoneWallBehavior;
import cn.allay.api.block.behavior.BlockCocoaBehavior;
import cn.allay.api.block.behavior.BlockColoredTorchBpBehavior;
import cn.allay.api.block.behavior.BlockColoredTorchRgBehavior;
import cn.allay.api.block.behavior.BlockCommandBlockBehavior;
import cn.allay.api.block.behavior.BlockComposterBehavior;
import cn.allay.api.block.behavior.BlockConcretePowderBehavior;
import cn.allay.api.block.behavior.BlockConduitBehavior;
import cn.allay.api.block.behavior.BlockCopperBlockBehavior;
import cn.allay.api.block.behavior.BlockCopperOreBehavior;
import cn.allay.api.block.behavior.BlockCoralBlockBehavior;
import cn.allay.api.block.behavior.BlockCoralFanBehavior;
import cn.allay.api.block.behavior.BlockCoralFanDeadBehavior;
import cn.allay.api.block.behavior.BlockCoralFanHang2Behavior;
import cn.allay.api.block.behavior.BlockCoralFanHang3Behavior;
import cn.allay.api.block.behavior.BlockCoralFanHangBehavior;
import cn.allay.api.block.behavior.BlockCrackedDeepslateBricksBehavior;
import cn.allay.api.block.behavior.BlockCrackedDeepslateTilesBehavior;
import cn.allay.api.block.behavior.BlockCrackedNetherBricksBehavior;
import cn.allay.api.block.behavior.BlockCrackedPolishedBlackstoneBricksBehavior;
import cn.allay.api.block.behavior.BlockCraftingTableBehavior;
import cn.allay.api.block.behavior.BlockCrimsonButtonBehavior;
import cn.allay.api.block.behavior.BlockCrimsonDoorBehavior;
import cn.allay.api.block.behavior.BlockCrimsonDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockCrimsonFenceBehavior;
import cn.allay.api.block.behavior.BlockCrimsonFenceGateBehavior;
import cn.allay.api.block.behavior.BlockCrimsonFungusBehavior;
import cn.allay.api.block.behavior.BlockCrimsonHangingSignBehavior;
import cn.allay.api.block.behavior.BlockCrimsonHyphaeBehavior;
import cn.allay.api.block.behavior.BlockCrimsonNyliumBehavior;
import cn.allay.api.block.behavior.BlockCrimsonPlanksBehavior;
import cn.allay.api.block.behavior.BlockCrimsonPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockCrimsonRootsBehavior;
import cn.allay.api.block.behavior.BlockCrimsonSlabBehavior;
import cn.allay.api.block.behavior.BlockCrimsonStairsBehavior;
import cn.allay.api.block.behavior.BlockCrimsonStandingSignBehavior;
import cn.allay.api.block.behavior.BlockCrimsonStemBehavior;
import cn.allay.api.block.behavior.BlockCrimsonTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockCrimsonWallSignBehavior;
import cn.allay.api.block.behavior.BlockCryingObsidianBehavior;
import cn.allay.api.block.behavior.BlockCutCopperBehavior;
import cn.allay.api.block.behavior.BlockCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockCyanCandleBehavior;
import cn.allay.api.block.behavior.BlockCyanCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockCyanCarpetBehavior;
import cn.allay.api.block.behavior.BlockCyanConcreteBehavior;
import cn.allay.api.block.behavior.BlockCyanGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockCyanShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockCyanWoolBehavior;
import cn.allay.api.block.behavior.BlockDarkOakButtonBehavior;
import cn.allay.api.block.behavior.BlockDarkOakDoorBehavior;
import cn.allay.api.block.behavior.BlockDarkOakFenceBehavior;
import cn.allay.api.block.behavior.BlockDarkOakFenceGateBehavior;
import cn.allay.api.block.behavior.BlockDarkOakHangingSignBehavior;
import cn.allay.api.block.behavior.BlockDarkOakLogBehavior;
import cn.allay.api.block.behavior.BlockDarkOakPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockDarkOakStairsBehavior;
import cn.allay.api.block.behavior.BlockDarkOakTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockDarkPrismarineStairsBehavior;
import cn.allay.api.block.behavior.BlockDarkoakStandingSignBehavior;
import cn.allay.api.block.behavior.BlockDarkoakWallSignBehavior;
import cn.allay.api.block.behavior.BlockDaylightDetectorBehavior;
import cn.allay.api.block.behavior.BlockDaylightDetectorInvertedBehavior;
import cn.allay.api.block.behavior.BlockDeadBrainCoralBehavior;
import cn.allay.api.block.behavior.BlockDeadBubbleCoralBehavior;
import cn.allay.api.block.behavior.BlockDeadFireCoralBehavior;
import cn.allay.api.block.behavior.BlockDeadHornCoralBehavior;
import cn.allay.api.block.behavior.BlockDeadTubeCoralBehavior;
import cn.allay.api.block.behavior.BlockDeadbushBehavior;
import cn.allay.api.block.behavior.BlockDecoratedPotBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBrickDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBrickSlabBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBrickWallBehavior;
import cn.allay.api.block.behavior.BlockDeepslateBricksBehavior;
import cn.allay.api.block.behavior.BlockDeepslateCoalOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateCopperOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateDiamondOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateEmeraldOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateGoldOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateIronOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateLapisOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateRedstoneOreBehavior;
import cn.allay.api.block.behavior.BlockDeepslateTileDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockDeepslateTileSlabBehavior;
import cn.allay.api.block.behavior.BlockDeepslateTileStairsBehavior;
import cn.allay.api.block.behavior.BlockDeepslateTileWallBehavior;
import cn.allay.api.block.behavior.BlockDeepslateTilesBehavior;
import cn.allay.api.block.behavior.BlockDenyBehavior;
import cn.allay.api.block.behavior.BlockDetectorRailBehavior;
import cn.allay.api.block.behavior.BlockDiamondBlockBehavior;
import cn.allay.api.block.behavior.BlockDiamondOreBehavior;
import cn.allay.api.block.behavior.BlockDioriteStairsBehavior;
import cn.allay.api.block.behavior.BlockDirtBehavior;
import cn.allay.api.block.behavior.BlockDirtWithRootsBehavior;
import cn.allay.api.block.behavior.BlockDispenserBehavior;
import cn.allay.api.block.behavior.BlockDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockDoublePlantBehavior;
import cn.allay.api.block.behavior.BlockDoubleStoneBlockSlab2Behavior;
import cn.allay.api.block.behavior.BlockDoubleStoneBlockSlab3Behavior;
import cn.allay.api.block.behavior.BlockDoubleStoneBlockSlab4Behavior;
import cn.allay.api.block.behavior.BlockDoubleStoneBlockSlabBehavior;
import cn.allay.api.block.behavior.BlockDoubleWoodenSlabBehavior;
import cn.allay.api.block.behavior.BlockDragonEggBehavior;
import cn.allay.api.block.behavior.BlockDriedKelpBlockBehavior;
import cn.allay.api.block.behavior.BlockDripstoneBlockBehavior;
import cn.allay.api.block.behavior.BlockDropperBehavior;
import cn.allay.api.block.behavior.BlockElement0Behavior;
import cn.allay.api.block.behavior.BlockElement100Behavior;
import cn.allay.api.block.behavior.BlockElement101Behavior;
import cn.allay.api.block.behavior.BlockElement102Behavior;
import cn.allay.api.block.behavior.BlockElement103Behavior;
import cn.allay.api.block.behavior.BlockElement104Behavior;
import cn.allay.api.block.behavior.BlockElement105Behavior;
import cn.allay.api.block.behavior.BlockElement106Behavior;
import cn.allay.api.block.behavior.BlockElement107Behavior;
import cn.allay.api.block.behavior.BlockElement108Behavior;
import cn.allay.api.block.behavior.BlockElement109Behavior;
import cn.allay.api.block.behavior.BlockElement10Behavior;
import cn.allay.api.block.behavior.BlockElement110Behavior;
import cn.allay.api.block.behavior.BlockElement111Behavior;
import cn.allay.api.block.behavior.BlockElement112Behavior;
import cn.allay.api.block.behavior.BlockElement113Behavior;
import cn.allay.api.block.behavior.BlockElement114Behavior;
import cn.allay.api.block.behavior.BlockElement115Behavior;
import cn.allay.api.block.behavior.BlockElement116Behavior;
import cn.allay.api.block.behavior.BlockElement117Behavior;
import cn.allay.api.block.behavior.BlockElement118Behavior;
import cn.allay.api.block.behavior.BlockElement11Behavior;
import cn.allay.api.block.behavior.BlockElement12Behavior;
import cn.allay.api.block.behavior.BlockElement13Behavior;
import cn.allay.api.block.behavior.BlockElement14Behavior;
import cn.allay.api.block.behavior.BlockElement15Behavior;
import cn.allay.api.block.behavior.BlockElement16Behavior;
import cn.allay.api.block.behavior.BlockElement17Behavior;
import cn.allay.api.block.behavior.BlockElement18Behavior;
import cn.allay.api.block.behavior.BlockElement19Behavior;
import cn.allay.api.block.behavior.BlockElement1Behavior;
import cn.allay.api.block.behavior.BlockElement20Behavior;
import cn.allay.api.block.behavior.BlockElement21Behavior;
import cn.allay.api.block.behavior.BlockElement22Behavior;
import cn.allay.api.block.behavior.BlockElement23Behavior;
import cn.allay.api.block.behavior.BlockElement24Behavior;
import cn.allay.api.block.behavior.BlockElement25Behavior;
import cn.allay.api.block.behavior.BlockElement26Behavior;
import cn.allay.api.block.behavior.BlockElement27Behavior;
import cn.allay.api.block.behavior.BlockElement28Behavior;
import cn.allay.api.block.behavior.BlockElement29Behavior;
import cn.allay.api.block.behavior.BlockElement2Behavior;
import cn.allay.api.block.behavior.BlockElement30Behavior;
import cn.allay.api.block.behavior.BlockElement31Behavior;
import cn.allay.api.block.behavior.BlockElement32Behavior;
import cn.allay.api.block.behavior.BlockElement33Behavior;
import cn.allay.api.block.behavior.BlockElement34Behavior;
import cn.allay.api.block.behavior.BlockElement35Behavior;
import cn.allay.api.block.behavior.BlockElement36Behavior;
import cn.allay.api.block.behavior.BlockElement37Behavior;
import cn.allay.api.block.behavior.BlockElement38Behavior;
import cn.allay.api.block.behavior.BlockElement39Behavior;
import cn.allay.api.block.behavior.BlockElement3Behavior;
import cn.allay.api.block.behavior.BlockElement40Behavior;
import cn.allay.api.block.behavior.BlockElement41Behavior;
import cn.allay.api.block.behavior.BlockElement42Behavior;
import cn.allay.api.block.behavior.BlockElement43Behavior;
import cn.allay.api.block.behavior.BlockElement44Behavior;
import cn.allay.api.block.behavior.BlockElement45Behavior;
import cn.allay.api.block.behavior.BlockElement46Behavior;
import cn.allay.api.block.behavior.BlockElement47Behavior;
import cn.allay.api.block.behavior.BlockElement48Behavior;
import cn.allay.api.block.behavior.BlockElement49Behavior;
import cn.allay.api.block.behavior.BlockElement4Behavior;
import cn.allay.api.block.behavior.BlockElement50Behavior;
import cn.allay.api.block.behavior.BlockElement51Behavior;
import cn.allay.api.block.behavior.BlockElement52Behavior;
import cn.allay.api.block.behavior.BlockElement53Behavior;
import cn.allay.api.block.behavior.BlockElement54Behavior;
import cn.allay.api.block.behavior.BlockElement55Behavior;
import cn.allay.api.block.behavior.BlockElement56Behavior;
import cn.allay.api.block.behavior.BlockElement57Behavior;
import cn.allay.api.block.behavior.BlockElement58Behavior;
import cn.allay.api.block.behavior.BlockElement59Behavior;
import cn.allay.api.block.behavior.BlockElement5Behavior;
import cn.allay.api.block.behavior.BlockElement60Behavior;
import cn.allay.api.block.behavior.BlockElement61Behavior;
import cn.allay.api.block.behavior.BlockElement62Behavior;
import cn.allay.api.block.behavior.BlockElement63Behavior;
import cn.allay.api.block.behavior.BlockElement64Behavior;
import cn.allay.api.block.behavior.BlockElement65Behavior;
import cn.allay.api.block.behavior.BlockElement66Behavior;
import cn.allay.api.block.behavior.BlockElement67Behavior;
import cn.allay.api.block.behavior.BlockElement68Behavior;
import cn.allay.api.block.behavior.BlockElement69Behavior;
import cn.allay.api.block.behavior.BlockElement6Behavior;
import cn.allay.api.block.behavior.BlockElement70Behavior;
import cn.allay.api.block.behavior.BlockElement71Behavior;
import cn.allay.api.block.behavior.BlockElement72Behavior;
import cn.allay.api.block.behavior.BlockElement73Behavior;
import cn.allay.api.block.behavior.BlockElement74Behavior;
import cn.allay.api.block.behavior.BlockElement75Behavior;
import cn.allay.api.block.behavior.BlockElement76Behavior;
import cn.allay.api.block.behavior.BlockElement77Behavior;
import cn.allay.api.block.behavior.BlockElement78Behavior;
import cn.allay.api.block.behavior.BlockElement79Behavior;
import cn.allay.api.block.behavior.BlockElement7Behavior;
import cn.allay.api.block.behavior.BlockElement80Behavior;
import cn.allay.api.block.behavior.BlockElement81Behavior;
import cn.allay.api.block.behavior.BlockElement82Behavior;
import cn.allay.api.block.behavior.BlockElement83Behavior;
import cn.allay.api.block.behavior.BlockElement84Behavior;
import cn.allay.api.block.behavior.BlockElement85Behavior;
import cn.allay.api.block.behavior.BlockElement86Behavior;
import cn.allay.api.block.behavior.BlockElement87Behavior;
import cn.allay.api.block.behavior.BlockElement88Behavior;
import cn.allay.api.block.behavior.BlockElement89Behavior;
import cn.allay.api.block.behavior.BlockElement8Behavior;
import cn.allay.api.block.behavior.BlockElement90Behavior;
import cn.allay.api.block.behavior.BlockElement91Behavior;
import cn.allay.api.block.behavior.BlockElement92Behavior;
import cn.allay.api.block.behavior.BlockElement93Behavior;
import cn.allay.api.block.behavior.BlockElement94Behavior;
import cn.allay.api.block.behavior.BlockElement95Behavior;
import cn.allay.api.block.behavior.BlockElement96Behavior;
import cn.allay.api.block.behavior.BlockElement97Behavior;
import cn.allay.api.block.behavior.BlockElement98Behavior;
import cn.allay.api.block.behavior.BlockElement99Behavior;
import cn.allay.api.block.behavior.BlockElement9Behavior;
import cn.allay.api.block.behavior.BlockEmeraldBlockBehavior;
import cn.allay.api.block.behavior.BlockEmeraldOreBehavior;
import cn.allay.api.block.behavior.BlockEnchantingTableBehavior;
import cn.allay.api.block.behavior.BlockEndBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockEndBricksBehavior;
import cn.allay.api.block.behavior.BlockEndGatewayBehavior;
import cn.allay.api.block.behavior.BlockEndPortalBehavior;
import cn.allay.api.block.behavior.BlockEndPortalFrameBehavior;
import cn.allay.api.block.behavior.BlockEndRodBehavior;
import cn.allay.api.block.behavior.BlockEndStoneBehavior;
import cn.allay.api.block.behavior.BlockEnderChestBehavior;
import cn.allay.api.block.behavior.BlockExposedCopperBehavior;
import cn.allay.api.block.behavior.BlockExposedCutCopperBehavior;
import cn.allay.api.block.behavior.BlockExposedCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockExposedCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockExposedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockFarmlandBehavior;
import cn.allay.api.block.behavior.BlockFenceGateBehavior;
import cn.allay.api.block.behavior.BlockFireBehavior;
import cn.allay.api.block.behavior.BlockFireCoralBehavior;
import cn.allay.api.block.behavior.BlockFletchingTableBehavior;
import cn.allay.api.block.behavior.BlockFlowerPotBehavior;
import cn.allay.api.block.behavior.BlockFloweringAzaleaBehavior;
import cn.allay.api.block.behavior.BlockFlowingLavaBehavior;
import cn.allay.api.block.behavior.BlockFlowingWaterBehavior;
import cn.allay.api.block.behavior.BlockFrameBehavior;
import cn.allay.api.block.behavior.BlockFrogSpawnBehavior;
import cn.allay.api.block.behavior.BlockFrostedIceBehavior;
import cn.allay.api.block.behavior.BlockFurnaceBehavior;
import cn.allay.api.block.behavior.BlockGildedBlackstoneBehavior;
import cn.allay.api.block.behavior.BlockGlassBehavior;
import cn.allay.api.block.behavior.BlockGlassPaneBehavior;
import cn.allay.api.block.behavior.BlockGlowFrameBehavior;
import cn.allay.api.block.behavior.BlockGlowLichenBehavior;
import cn.allay.api.block.behavior.BlockGlowingobsidianBehavior;
import cn.allay.api.block.behavior.BlockGlowstoneBehavior;
import cn.allay.api.block.behavior.BlockGoldBlockBehavior;
import cn.allay.api.block.behavior.BlockGoldOreBehavior;
import cn.allay.api.block.behavior.BlockGoldenRailBehavior;
import cn.allay.api.block.behavior.BlockGraniteStairsBehavior;
import cn.allay.api.block.behavior.BlockGrassBehavior;
import cn.allay.api.block.behavior.BlockGrassPathBehavior;
import cn.allay.api.block.behavior.BlockGravelBehavior;
import cn.allay.api.block.behavior.BlockGrayCandleBehavior;
import cn.allay.api.block.behavior.BlockGrayCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockGrayCarpetBehavior;
import cn.allay.api.block.behavior.BlockGrayConcreteBehavior;
import cn.allay.api.block.behavior.BlockGrayGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockGrayShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockGrayWoolBehavior;
import cn.allay.api.block.behavior.BlockGreenCandleBehavior;
import cn.allay.api.block.behavior.BlockGreenCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockGreenCarpetBehavior;
import cn.allay.api.block.behavior.BlockGreenConcreteBehavior;
import cn.allay.api.block.behavior.BlockGreenGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockGreenShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockGreenWoolBehavior;
import cn.allay.api.block.behavior.BlockGrindstoneBehavior;
import cn.allay.api.block.behavior.BlockHangingRootsBehavior;
import cn.allay.api.block.behavior.BlockHardGlassBehavior;
import cn.allay.api.block.behavior.BlockHardGlassPaneBehavior;
import cn.allay.api.block.behavior.BlockHardStainedGlassBehavior;
import cn.allay.api.block.behavior.BlockHardStainedGlassPaneBehavior;
import cn.allay.api.block.behavior.BlockHardenedClayBehavior;
import cn.allay.api.block.behavior.BlockHayBlockBehavior;
import cn.allay.api.block.behavior.BlockHeavyWeightedPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockHoneyBlockBehavior;
import cn.allay.api.block.behavior.BlockHoneycombBlockBehavior;
import cn.allay.api.block.behavior.BlockHopperBehavior;
import cn.allay.api.block.behavior.BlockHornCoralBehavior;
import cn.allay.api.block.behavior.BlockIceBehavior;
import cn.allay.api.block.behavior.BlockInfestedDeepslateBehavior;
import cn.allay.api.block.behavior.BlockInfoUpdate2Behavior;
import cn.allay.api.block.behavior.BlockInfoUpdateBehavior;
import cn.allay.api.block.behavior.BlockInvisibleBedrockBehavior;
import cn.allay.api.block.behavior.BlockIronBarsBehavior;
import cn.allay.api.block.behavior.BlockIronBlockBehavior;
import cn.allay.api.block.behavior.BlockIronDoorBehavior;
import cn.allay.api.block.behavior.BlockIronOreBehavior;
import cn.allay.api.block.behavior.BlockIronTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockJigsawBehavior;
import cn.allay.api.block.behavior.BlockJukeboxBehavior;
import cn.allay.api.block.behavior.BlockJungleButtonBehavior;
import cn.allay.api.block.behavior.BlockJungleDoorBehavior;
import cn.allay.api.block.behavior.BlockJungleFenceBehavior;
import cn.allay.api.block.behavior.BlockJungleFenceGateBehavior;
import cn.allay.api.block.behavior.BlockJungleHangingSignBehavior;
import cn.allay.api.block.behavior.BlockJungleLogBehavior;
import cn.allay.api.block.behavior.BlockJunglePressurePlateBehavior;
import cn.allay.api.block.behavior.BlockJungleStairsBehavior;
import cn.allay.api.block.behavior.BlockJungleStandingSignBehavior;
import cn.allay.api.block.behavior.BlockJungleTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockJungleWallSignBehavior;
import cn.allay.api.block.behavior.BlockKelpBehavior;
import cn.allay.api.block.behavior.BlockLadderBehavior;
import cn.allay.api.block.behavior.BlockLanternBehavior;
import cn.allay.api.block.behavior.BlockLapisBlockBehavior;
import cn.allay.api.block.behavior.BlockLapisOreBehavior;
import cn.allay.api.block.behavior.BlockLargeAmethystBudBehavior;
import cn.allay.api.block.behavior.BlockLavaBehavior;
import cn.allay.api.block.behavior.BlockLeaves2Behavior;
import cn.allay.api.block.behavior.BlockLeavesBehavior;
import cn.allay.api.block.behavior.BlockLecternBehavior;
import cn.allay.api.block.behavior.BlockLeverBehavior;
import cn.allay.api.block.behavior.BlockLightBlockBehavior;
import cn.allay.api.block.behavior.BlockLightBlueCandleBehavior;
import cn.allay.api.block.behavior.BlockLightBlueCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockLightBlueCarpetBehavior;
import cn.allay.api.block.behavior.BlockLightBlueConcreteBehavior;
import cn.allay.api.block.behavior.BlockLightBlueGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockLightBlueShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockLightBlueWoolBehavior;
import cn.allay.api.block.behavior.BlockLightGrayCandleBehavior;
import cn.allay.api.block.behavior.BlockLightGrayCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockLightGrayCarpetBehavior;
import cn.allay.api.block.behavior.BlockLightGrayConcreteBehavior;
import cn.allay.api.block.behavior.BlockLightGrayShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockLightGrayWoolBehavior;
import cn.allay.api.block.behavior.BlockLightWeightedPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockLightningRodBehavior;
import cn.allay.api.block.behavior.BlockLimeCandleBehavior;
import cn.allay.api.block.behavior.BlockLimeCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockLimeCarpetBehavior;
import cn.allay.api.block.behavior.BlockLimeConcreteBehavior;
import cn.allay.api.block.behavior.BlockLimeGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockLimeShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockLimeWoolBehavior;
import cn.allay.api.block.behavior.BlockLitBlastFurnaceBehavior;
import cn.allay.api.block.behavior.BlockLitDeepslateRedstoneOreBehavior;
import cn.allay.api.block.behavior.BlockLitFurnaceBehavior;
import cn.allay.api.block.behavior.BlockLitPumpkinBehavior;
import cn.allay.api.block.behavior.BlockLitRedstoneLampBehavior;
import cn.allay.api.block.behavior.BlockLitRedstoneOreBehavior;
import cn.allay.api.block.behavior.BlockLitSmokerBehavior;
import cn.allay.api.block.behavior.BlockLodestoneBehavior;
import cn.allay.api.block.behavior.BlockLoomBehavior;
import cn.allay.api.block.behavior.BlockMagentaCandleBehavior;
import cn.allay.api.block.behavior.BlockMagentaCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockMagentaCarpetBehavior;
import cn.allay.api.block.behavior.BlockMagentaConcreteBehavior;
import cn.allay.api.block.behavior.BlockMagentaGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockMagentaShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockMagentaWoolBehavior;
import cn.allay.api.block.behavior.BlockMagmaBehavior;
import cn.allay.api.block.behavior.BlockMangroveButtonBehavior;
import cn.allay.api.block.behavior.BlockMangroveDoorBehavior;
import cn.allay.api.block.behavior.BlockMangroveDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockMangroveFenceBehavior;
import cn.allay.api.block.behavior.BlockMangroveFenceGateBehavior;
import cn.allay.api.block.behavior.BlockMangroveHangingSignBehavior;
import cn.allay.api.block.behavior.BlockMangroveLeavesBehavior;
import cn.allay.api.block.behavior.BlockMangroveLogBehavior;
import cn.allay.api.block.behavior.BlockMangrovePlanksBehavior;
import cn.allay.api.block.behavior.BlockMangrovePressurePlateBehavior;
import cn.allay.api.block.behavior.BlockMangrovePropaguleBehavior;
import cn.allay.api.block.behavior.BlockMangroveRootsBehavior;
import cn.allay.api.block.behavior.BlockMangroveSlabBehavior;
import cn.allay.api.block.behavior.BlockMangroveStairsBehavior;
import cn.allay.api.block.behavior.BlockMangroveStandingSignBehavior;
import cn.allay.api.block.behavior.BlockMangroveTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockMangroveWallSignBehavior;
import cn.allay.api.block.behavior.BlockMangroveWoodBehavior;
import cn.allay.api.block.behavior.BlockMediumAmethystBudBehavior;
import cn.allay.api.block.behavior.BlockMelonBlockBehavior;
import cn.allay.api.block.behavior.BlockMelonStemBehavior;
import cn.allay.api.block.behavior.BlockMobSpawnerBehavior;
import cn.allay.api.block.behavior.BlockMonsterEggBehavior;
import cn.allay.api.block.behavior.BlockMossBlockBehavior;
import cn.allay.api.block.behavior.BlockMossCarpetBehavior;
import cn.allay.api.block.behavior.BlockMossyCobblestoneBehavior;
import cn.allay.api.block.behavior.BlockMossyCobblestoneStairsBehavior;
import cn.allay.api.block.behavior.BlockMossyStoneBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockMovingBlockBehavior;
import cn.allay.api.block.behavior.BlockMudBehavior;
import cn.allay.api.block.behavior.BlockMudBrickDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockMudBrickSlabBehavior;
import cn.allay.api.block.behavior.BlockMudBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockMudBrickWallBehavior;
import cn.allay.api.block.behavior.BlockMudBricksBehavior;
import cn.allay.api.block.behavior.BlockMuddyMangroveRootsBehavior;
import cn.allay.api.block.behavior.BlockMyceliumBehavior;
import cn.allay.api.block.behavior.BlockNetherBrickBehavior;
import cn.allay.api.block.behavior.BlockNetherBrickFenceBehavior;
import cn.allay.api.block.behavior.BlockNetherBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockNetherGoldOreBehavior;
import cn.allay.api.block.behavior.BlockNetherSproutsBehavior;
import cn.allay.api.block.behavior.BlockNetherWartBehavior;
import cn.allay.api.block.behavior.BlockNetherWartBlockBehavior;
import cn.allay.api.block.behavior.BlockNetheriteBlockBehavior;
import cn.allay.api.block.behavior.BlockNetherrackBehavior;
import cn.allay.api.block.behavior.BlockNetherreactorBehavior;
import cn.allay.api.block.behavior.BlockNormalStoneStairsBehavior;
import cn.allay.api.block.behavior.BlockNoteblockBehavior;
import cn.allay.api.block.behavior.BlockOakFenceBehavior;
import cn.allay.api.block.behavior.BlockOakHangingSignBehavior;
import cn.allay.api.block.behavior.BlockOakLogBehavior;
import cn.allay.api.block.behavior.BlockOakStairsBehavior;
import cn.allay.api.block.behavior.BlockObserverBehavior;
import cn.allay.api.block.behavior.BlockObsidianBehavior;
import cn.allay.api.block.behavior.BlockOchreFroglightBehavior;
import cn.allay.api.block.behavior.BlockOrangeCandleBehavior;
import cn.allay.api.block.behavior.BlockOrangeCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockOrangeCarpetBehavior;
import cn.allay.api.block.behavior.BlockOrangeConcreteBehavior;
import cn.allay.api.block.behavior.BlockOrangeGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockOrangeShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockOrangeWoolBehavior;
import cn.allay.api.block.behavior.BlockOxidizedCopperBehavior;
import cn.allay.api.block.behavior.BlockOxidizedCutCopperBehavior;
import cn.allay.api.block.behavior.BlockOxidizedCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockOxidizedCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockOxidizedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockPackedIceBehavior;
import cn.allay.api.block.behavior.BlockPackedMudBehavior;
import cn.allay.api.block.behavior.BlockPearlescentFroglightBehavior;
import cn.allay.api.block.behavior.BlockPinkCandleBehavior;
import cn.allay.api.block.behavior.BlockPinkCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockPinkCarpetBehavior;
import cn.allay.api.block.behavior.BlockPinkConcreteBehavior;
import cn.allay.api.block.behavior.BlockPinkGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockPinkPetalsBehavior;
import cn.allay.api.block.behavior.BlockPinkShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockPinkWoolBehavior;
import cn.allay.api.block.behavior.BlockPistonArmCollisionBehavior;
import cn.allay.api.block.behavior.BlockPistonBehavior;
import cn.allay.api.block.behavior.BlockPitcherCropBehavior;
import cn.allay.api.block.behavior.BlockPitcherPlantBehavior;
import cn.allay.api.block.behavior.BlockPlanksBehavior;
import cn.allay.api.block.behavior.BlockPodzolBehavior;
import cn.allay.api.block.behavior.BlockPointedDripstoneBehavior;
import cn.allay.api.block.behavior.BlockPolishedAndesiteStairsBehavior;
import cn.allay.api.block.behavior.BlockPolishedBasaltBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBrickDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBrickSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBrickWallBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneBricksBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneButtonBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstonePressurePlateBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockPolishedBlackstoneWallBehavior;
import cn.allay.api.block.behavior.BlockPolishedDeepslateBehavior;
import cn.allay.api.block.behavior.BlockPolishedDeepslateDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedDeepslateSlabBehavior;
import cn.allay.api.block.behavior.BlockPolishedDeepslateStairsBehavior;
import cn.allay.api.block.behavior.BlockPolishedDeepslateWallBehavior;
import cn.allay.api.block.behavior.BlockPolishedDioriteStairsBehavior;
import cn.allay.api.block.behavior.BlockPolishedGraniteStairsBehavior;
import cn.allay.api.block.behavior.BlockPortalBehavior;
import cn.allay.api.block.behavior.BlockPotatoesBehavior;
import cn.allay.api.block.behavior.BlockPowderSnowBehavior;
import cn.allay.api.block.behavior.BlockPoweredComparatorBehavior;
import cn.allay.api.block.behavior.BlockPoweredRepeaterBehavior;
import cn.allay.api.block.behavior.BlockPrismarineBehavior;
import cn.allay.api.block.behavior.BlockPrismarineBricksStairsBehavior;
import cn.allay.api.block.behavior.BlockPrismarineStairsBehavior;
import cn.allay.api.block.behavior.BlockPumpkinBehavior;
import cn.allay.api.block.behavior.BlockPumpkinStemBehavior;
import cn.allay.api.block.behavior.BlockPurpleCandleBehavior;
import cn.allay.api.block.behavior.BlockPurpleCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockPurpleCarpetBehavior;
import cn.allay.api.block.behavior.BlockPurpleConcreteBehavior;
import cn.allay.api.block.behavior.BlockPurpleGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockPurpleShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockPurpleWoolBehavior;
import cn.allay.api.block.behavior.BlockPurpurBlockBehavior;
import cn.allay.api.block.behavior.BlockPurpurStairsBehavior;
import cn.allay.api.block.behavior.BlockQuartzBlockBehavior;
import cn.allay.api.block.behavior.BlockQuartzBricksBehavior;
import cn.allay.api.block.behavior.BlockQuartzOreBehavior;
import cn.allay.api.block.behavior.BlockQuartzStairsBehavior;
import cn.allay.api.block.behavior.BlockRailBehavior;
import cn.allay.api.block.behavior.BlockRawCopperBlockBehavior;
import cn.allay.api.block.behavior.BlockRawGoldBlockBehavior;
import cn.allay.api.block.behavior.BlockRawIronBlockBehavior;
import cn.allay.api.block.behavior.BlockRedCandleBehavior;
import cn.allay.api.block.behavior.BlockRedCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockRedCarpetBehavior;
import cn.allay.api.block.behavior.BlockRedConcreteBehavior;
import cn.allay.api.block.behavior.BlockRedFlowerBehavior;
import cn.allay.api.block.behavior.BlockRedGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockRedMushroomBehavior;
import cn.allay.api.block.behavior.BlockRedMushroomBlockBehavior;
import cn.allay.api.block.behavior.BlockRedNetherBrickBehavior;
import cn.allay.api.block.behavior.BlockRedNetherBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockRedSandstoneBehavior;
import cn.allay.api.block.behavior.BlockRedSandstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockRedShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockRedWoolBehavior;
import cn.allay.api.block.behavior.BlockRedstoneBlockBehavior;
import cn.allay.api.block.behavior.BlockRedstoneLampBehavior;
import cn.allay.api.block.behavior.BlockRedstoneOreBehavior;
import cn.allay.api.block.behavior.BlockRedstoneTorchBehavior;
import cn.allay.api.block.behavior.BlockRedstoneWireBehavior;
import cn.allay.api.block.behavior.BlockReedsBehavior;
import cn.allay.api.block.behavior.BlockReinforcedDeepslateBehavior;
import cn.allay.api.block.behavior.BlockRepeatingCommandBlockBehavior;
import cn.allay.api.block.behavior.BlockReserved6Behavior;
import cn.allay.api.block.behavior.BlockRespawnAnchorBehavior;
import cn.allay.api.block.behavior.BlockSandBehavior;
import cn.allay.api.block.behavior.BlockSandstoneBehavior;
import cn.allay.api.block.behavior.BlockSandstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockSaplingBehavior;
import cn.allay.api.block.behavior.BlockScaffoldingBehavior;
import cn.allay.api.block.behavior.BlockSculkBehavior;
import cn.allay.api.block.behavior.BlockSculkCatalystBehavior;
import cn.allay.api.block.behavior.BlockSculkSensorBehavior;
import cn.allay.api.block.behavior.BlockSculkShriekerBehavior;
import cn.allay.api.block.behavior.BlockSculkVeinBehavior;
import cn.allay.api.block.behavior.BlockSeaLanternBehavior;
import cn.allay.api.block.behavior.BlockSeaPickleBehavior;
import cn.allay.api.block.behavior.BlockSeagrassBehavior;
import cn.allay.api.block.behavior.BlockShroomlightBehavior;
import cn.allay.api.block.behavior.BlockSilverGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockSkullBehavior;
import cn.allay.api.block.behavior.BlockSlimeBehavior;
import cn.allay.api.block.behavior.BlockSmallAmethystBudBehavior;
import cn.allay.api.block.behavior.BlockSmallDripleafBlockBehavior;
import cn.allay.api.block.behavior.BlockSmithingTableBehavior;
import cn.allay.api.block.behavior.BlockSmokerBehavior;
import cn.allay.api.block.behavior.BlockSmoothBasaltBehavior;
import cn.allay.api.block.behavior.BlockSmoothQuartzStairsBehavior;
import cn.allay.api.block.behavior.BlockSmoothRedSandstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockSmoothSandstoneStairsBehavior;
import cn.allay.api.block.behavior.BlockSmoothStoneBehavior;
import cn.allay.api.block.behavior.BlockSnifferEggBehavior;
import cn.allay.api.block.behavior.BlockSnowBehavior;
import cn.allay.api.block.behavior.BlockSnowLayerBehavior;
import cn.allay.api.block.behavior.BlockSoulCampfireBehavior;
import cn.allay.api.block.behavior.BlockSoulFireBehavior;
import cn.allay.api.block.behavior.BlockSoulLanternBehavior;
import cn.allay.api.block.behavior.BlockSoulSandBehavior;
import cn.allay.api.block.behavior.BlockSoulSoilBehavior;
import cn.allay.api.block.behavior.BlockSoulTorchBehavior;
import cn.allay.api.block.behavior.BlockSpongeBehavior;
import cn.allay.api.block.behavior.BlockSporeBlossomBehavior;
import cn.allay.api.block.behavior.BlockSpruceButtonBehavior;
import cn.allay.api.block.behavior.BlockSpruceDoorBehavior;
import cn.allay.api.block.behavior.BlockSpruceFenceBehavior;
import cn.allay.api.block.behavior.BlockSpruceFenceGateBehavior;
import cn.allay.api.block.behavior.BlockSpruceHangingSignBehavior;
import cn.allay.api.block.behavior.BlockSpruceLogBehavior;
import cn.allay.api.block.behavior.BlockSprucePressurePlateBehavior;
import cn.allay.api.block.behavior.BlockSpruceStairsBehavior;
import cn.allay.api.block.behavior.BlockSpruceStandingSignBehavior;
import cn.allay.api.block.behavior.BlockSpruceTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockSpruceWallSignBehavior;
import cn.allay.api.block.behavior.BlockStainedGlassBehavior;
import cn.allay.api.block.behavior.BlockStainedGlassPaneBehavior;
import cn.allay.api.block.behavior.BlockStainedHardenedClayBehavior;
import cn.allay.api.block.behavior.BlockStandingBannerBehavior;
import cn.allay.api.block.behavior.BlockStandingSignBehavior;
import cn.allay.api.block.behavior.BlockStickyPistonArmCollisionBehavior;
import cn.allay.api.block.behavior.BlockStickyPistonBehavior;
import cn.allay.api.block.behavior.BlockStoneBehavior;
import cn.allay.api.block.behavior.BlockStoneBlockSlab2Behavior;
import cn.allay.api.block.behavior.BlockStoneBlockSlab3Behavior;
import cn.allay.api.block.behavior.BlockStoneBlockSlab4Behavior;
import cn.allay.api.block.behavior.BlockStoneBlockSlabBehavior;
import cn.allay.api.block.behavior.BlockStoneBrickStairsBehavior;
import cn.allay.api.block.behavior.BlockStoneButtonBehavior;
import cn.allay.api.block.behavior.BlockStonePressurePlateBehavior;
import cn.allay.api.block.behavior.BlockStoneStairsBehavior;
import cn.allay.api.block.behavior.BlockStonebrickBehavior;
import cn.allay.api.block.behavior.BlockStonecutterBehavior;
import cn.allay.api.block.behavior.BlockStonecutterBlockBehavior;
import cn.allay.api.block.behavior.BlockStrippedAcaciaLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedBambooBlockBehavior;
import cn.allay.api.block.behavior.BlockStrippedBirchLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedCherryLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedCherryWoodBehavior;
import cn.allay.api.block.behavior.BlockStrippedCrimsonHyphaeBehavior;
import cn.allay.api.block.behavior.BlockStrippedCrimsonStemBehavior;
import cn.allay.api.block.behavior.BlockStrippedDarkOakLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedJungleLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedMangroveLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedMangroveWoodBehavior;
import cn.allay.api.block.behavior.BlockStrippedOakLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedSpruceLogBehavior;
import cn.allay.api.block.behavior.BlockStrippedWarpedHyphaeBehavior;
import cn.allay.api.block.behavior.BlockStrippedWarpedStemBehavior;
import cn.allay.api.block.behavior.BlockStructureBlockBehavior;
import cn.allay.api.block.behavior.BlockStructureVoidBehavior;
import cn.allay.api.block.behavior.BlockSuspiciousGravelBehavior;
import cn.allay.api.block.behavior.BlockSuspiciousSandBehavior;
import cn.allay.api.block.behavior.BlockSweetBerryBushBehavior;
import cn.allay.api.block.behavior.BlockTallgrassBehavior;
import cn.allay.api.block.behavior.BlockTargetBehavior;
import cn.allay.api.block.behavior.BlockTintedGlassBehavior;
import cn.allay.api.block.behavior.BlockTntBehavior;
import cn.allay.api.block.behavior.BlockTorchBehavior;
import cn.allay.api.block.behavior.BlockTorchflowerBehavior;
import cn.allay.api.block.behavior.BlockTorchflowerCropBehavior;
import cn.allay.api.block.behavior.BlockTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockTrappedChestBehavior;
import cn.allay.api.block.behavior.BlockTripWireBehavior;
import cn.allay.api.block.behavior.BlockTripwireHookBehavior;
import cn.allay.api.block.behavior.BlockTubeCoralBehavior;
import cn.allay.api.block.behavior.BlockTuffBehavior;
import cn.allay.api.block.behavior.BlockTurtleEggBehavior;
import cn.allay.api.block.behavior.BlockTwistingVinesBehavior;
import cn.allay.api.block.behavior.BlockUnderwaterTorchBehavior;
import cn.allay.api.block.behavior.BlockUndyedShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockUnknownBehavior;
import cn.allay.api.block.behavior.BlockUnlitRedstoneTorchBehavior;
import cn.allay.api.block.behavior.BlockUnpoweredComparatorBehavior;
import cn.allay.api.block.behavior.BlockUnpoweredRepeaterBehavior;
import cn.allay.api.block.behavior.BlockVerdantFroglightBehavior;
import cn.allay.api.block.behavior.BlockVineBehavior;
import cn.allay.api.block.behavior.BlockWallBannerBehavior;
import cn.allay.api.block.behavior.BlockWallSignBehavior;
import cn.allay.api.block.behavior.BlockWarpedButtonBehavior;
import cn.allay.api.block.behavior.BlockWarpedDoorBehavior;
import cn.allay.api.block.behavior.BlockWarpedDoubleSlabBehavior;
import cn.allay.api.block.behavior.BlockWarpedFenceBehavior;
import cn.allay.api.block.behavior.BlockWarpedFenceGateBehavior;
import cn.allay.api.block.behavior.BlockWarpedFungusBehavior;
import cn.allay.api.block.behavior.BlockWarpedHangingSignBehavior;
import cn.allay.api.block.behavior.BlockWarpedHyphaeBehavior;
import cn.allay.api.block.behavior.BlockWarpedNyliumBehavior;
import cn.allay.api.block.behavior.BlockWarpedPlanksBehavior;
import cn.allay.api.block.behavior.BlockWarpedPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockWarpedRootsBehavior;
import cn.allay.api.block.behavior.BlockWarpedSlabBehavior;
import cn.allay.api.block.behavior.BlockWarpedStairsBehavior;
import cn.allay.api.block.behavior.BlockWarpedStandingSignBehavior;
import cn.allay.api.block.behavior.BlockWarpedStemBehavior;
import cn.allay.api.block.behavior.BlockWarpedTrapdoorBehavior;
import cn.allay.api.block.behavior.BlockWarpedWallSignBehavior;
import cn.allay.api.block.behavior.BlockWarpedWartBlockBehavior;
import cn.allay.api.block.behavior.BlockWaterBehavior;
import cn.allay.api.block.behavior.BlockWaterlilyBehavior;
import cn.allay.api.block.behavior.BlockWaxedCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedCutCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockWaxedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedExposedCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedExposedCutCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedExposedCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedExposedCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockWaxedExposedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedOxidizedCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedOxidizedCutCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedOxidizedCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedOxidizedCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockWaxedOxidizedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedWeatheredCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedWeatheredCutCopperBehavior;
import cn.allay.api.block.behavior.BlockWaxedWeatheredCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWaxedWeatheredCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockWaxedWeatheredDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWeatheredCopperBehavior;
import cn.allay.api.block.behavior.BlockWeatheredCutCopperBehavior;
import cn.allay.api.block.behavior.BlockWeatheredCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWeatheredCutCopperStairsBehavior;
import cn.allay.api.block.behavior.BlockWeatheredDoubleCutCopperSlabBehavior;
import cn.allay.api.block.behavior.BlockWebBehavior;
import cn.allay.api.block.behavior.BlockWeepingVinesBehavior;
import cn.allay.api.block.behavior.BlockWheatBehavior;
import cn.allay.api.block.behavior.BlockWhiteCandleBehavior;
import cn.allay.api.block.behavior.BlockWhiteCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockWhiteCarpetBehavior;
import cn.allay.api.block.behavior.BlockWhiteConcreteBehavior;
import cn.allay.api.block.behavior.BlockWhiteGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockWhiteShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockWhiteWoolBehavior;
import cn.allay.api.block.behavior.BlockWitherRoseBehavior;
import cn.allay.api.block.behavior.BlockWoodBehavior;
import cn.allay.api.block.behavior.BlockWoodenButtonBehavior;
import cn.allay.api.block.behavior.BlockWoodenDoorBehavior;
import cn.allay.api.block.behavior.BlockWoodenPressurePlateBehavior;
import cn.allay.api.block.behavior.BlockWoodenSlabBehavior;
import cn.allay.api.block.behavior.BlockYellowCandleBehavior;
import cn.allay.api.block.behavior.BlockYellowCandleCakeBehavior;
import cn.allay.api.block.behavior.BlockYellowCarpetBehavior;
import cn.allay.api.block.behavior.BlockYellowConcreteBehavior;
import cn.allay.api.block.behavior.BlockYellowFlowerBehavior;
import cn.allay.api.block.behavior.BlockYellowGlazedTerracottaBehavior;
import cn.allay.api.block.behavior.BlockYellowShulkerBoxBehavior;
import cn.allay.api.block.behavior.BlockYellowWoolBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;

/**
 * Allay Project <p>
 * @author daoge_cmd
 */
public interface VanillaBlockTypes {
  BlockType<BlockAcaciaButtonBehavior> ACACIA_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaDoorBehavior> ACACIA_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFenceBehavior> ACACIA_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFenceGateBehavior> ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaHangingSignBehavior> ACACIA_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaLogBehavior> ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaLogBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaPressurePlateBehavior> ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStairsBehavior> ACACIA_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStandingSignBehavior> ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaTrapdoorBehavior> ACACIA_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaWallSignBehavior> ACACIA_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockActivatorRailBehavior> ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockActivatorRailBehavior.class)
          .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockAirBehavior> AIR_TYPE = BlockTypeBuilder
          .builder(BlockAirBehavior.class)
          .vanillaBlock(VanillaBlockId.AIR, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAllowBehavior> ALLOW_TYPE = BlockTypeBuilder
          .builder(BlockAllowBehavior.class)
          .vanillaBlock(VanillaBlockId.ALLOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystBlockBehavior> AMETHYST_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockAmethystBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
          .builder(BlockAmethystClusterBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAncientDebrisBehavior> ANCIENT_DEBRIS_TYPE = BlockTypeBuilder
          .builder(BlockAncientDebrisBehavior.class)
          .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAndesiteStairsBehavior> ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAndesiteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAnvilBehavior> ANVIL_TYPE = BlockTypeBuilder
          .builder(BlockAnvilBehavior.class)
          .vanillaBlock(VanillaBlockId.ANVIL, true)
          .withProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaBehavior> AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeavesBehavior> AZALEA_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeavesFloweredBehavior> AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesFloweredBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooBehavior> BAMBOO_TYPE = BlockTypeBuilder
          .builder(BlockBambooBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooBlockBehavior> BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBambooBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooButtonBehavior> BAMBOO_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBambooButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoorBehavior> BAMBOO_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoubleSlabBehavior> BAMBOO_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFenceBehavior> BAMBOO_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFenceGateBehavior> BAMBOO_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooHangingSignBehavior> BAMBOO_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicBehavior> BAMBOO_MOSAIC_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicDoubleSlabBehavior> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicSlabBehavior> BAMBOO_MOSAIC_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicStairsBehavior> BAMBOO_MOSAIC_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPlanksBehavior> BAMBOO_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockBambooPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSaplingBehavior> BAMBOO_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockBambooSaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSlabBehavior> BAMBOO_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStairsBehavior> BAMBOO_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStandingSignBehavior> BAMBOO_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooTrapdoorBehavior> BAMBOO_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooWallSignBehavior> BAMBOO_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
          .builder(BlockBarrelBehavior.class)
          .vanillaBlock(VanillaBlockId.BARREL, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrierBehavior> BARRIER_TYPE = BlockTypeBuilder
          .builder(BlockBarrierBehavior.class)
          .vanillaBlock(VanillaBlockId.BARRIER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBasaltBehavior> BASALT_TYPE = BlockTypeBuilder
          .builder(BlockBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.BASALT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBeaconBehavior> BEACON_TYPE = BlockTypeBuilder
          .builder(BlockBeaconBehavior.class)
          .vanillaBlock(VanillaBlockId.BEACON, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBedBehavior> BED_TYPE = BlockTypeBuilder
          .builder(BlockBedBehavior.class)
          .vanillaBlock(VanillaBlockId.BED, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HEAD_PIECE_BIT, VanillaBlockPropertyTypes.OCCUPIED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBedrockBehavior> BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockBedrockBehavior.class)
          .vanillaBlock(VanillaBlockId.BEDROCK, true)
          .withProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBeeNestBehavior> BEE_NEST_TYPE = BlockTypeBuilder
          .builder(BlockBeeNestBehavior.class)
          .vanillaBlock(VanillaBlockId.BEE_NEST, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeehiveBehavior> BEEHIVE_TYPE = BlockTypeBuilder
          .builder(BlockBeehiveBehavior.class)
          .vanillaBlock(VanillaBlockId.BEEHIVE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeetrootBehavior> BEETROOT_TYPE = BlockTypeBuilder
          .builder(BlockBeetrootBehavior.class)
          .vanillaBlock(VanillaBlockId.BEETROOT, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockBellBehavior> BELL_TYPE = BlockTypeBuilder
          .builder(BlockBellBehavior.class)
          .vanillaBlock(VanillaBlockId.BELL, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBigDripleafBehavior> BIG_DRIPLEAF_TYPE = BlockTypeBuilder
          .builder(BlockBigDripleafBehavior.class)
          .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF, true)
          .withProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchButtonBehavior> BIRCH_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBirchButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchDoorBehavior> BIRCH_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFenceBehavior> BIRCH_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFenceGateBehavior> BIRCH_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchHangingSignBehavior> BIRCH_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchLogBehavior> BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchPressurePlateBehavior> BIRCH_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStairsBehavior> BIRCH_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBirchStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStandingSignBehavior> BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchTrapdoorBehavior> BIRCH_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchWallSignBehavior> BIRCH_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandleBehavior> BLACK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandleCakeBehavior> BLACK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCarpetBehavior> BLACK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlackCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackConcreteBehavior> BLACK_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBlackConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackGlazedTerracottaBehavior> BLACK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlackGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackShulkerBoxBehavior> BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBlackShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackWoolBehavior> BLACK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlackWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneBehavior> BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneDoubleSlabBehavior> BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneSlabBehavior> BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneStairsBehavior> BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneWallBehavior> BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLAST_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandleBehavior> BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandleCakeBehavior> BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCarpetBehavior> BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlueCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueConcreteBehavior> BLUE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBlueConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueGlazedTerracottaBehavior> BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlueGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueIceBehavior> BLUE_ICE_TYPE = BlockTypeBuilder
          .builder(BlockBlueIceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueShulkerBoxBehavior> BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBlueShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueWoolBehavior> BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlueWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBoneBlockBehavior> BONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BONE_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBookshelfBehavior> BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockBookshelfBehavior.class)
          .vanillaBlock(VanillaBlockId.BOOKSHELF, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBorderBlockBehavior> BORDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBorderBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BORDER_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrainCoralBehavior> BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBrainCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.BRAIN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrewingStandBehavior> BREWING_STAND_TYPE = BlockTypeBuilder
          .builder(BlockBrewingStandBehavior.class)
          .vanillaBlock(VanillaBlockId.BREWING_STAND, true)
          .withProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickBlockBehavior> BRICK_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrickBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BRICK_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickStairsBehavior> BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandleBehavior> BROWN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandleCakeBehavior> BROWN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCarpetBehavior> BROWN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBrownCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownConcreteBehavior> BROWN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBrownConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownGlazedTerracottaBehavior> BROWN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBrownGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroomBlockBehavior> BROWN_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownShulkerBoxBehavior> BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBrownShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownWoolBehavior> BROWN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBrownWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN_TYPE = BlockTypeBuilder
          .builder(BlockBubbleColumnBehavior.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN, true)
          .withProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleCoralBehavior> BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBubbleCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBuddingAmethystBehavior> BUDDING_AMETHYST_TYPE = BlockTypeBuilder
          .builder(BlockBuddingAmethystBehavior.class)
          .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCactusBehavior> CACTUS_TYPE = BlockTypeBuilder
          .builder(BlockCactusBehavior.class)
          .vanillaBlock(VanillaBlockId.CACTUS, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockCakeBehavior> CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
          .addBasicComponents()
          .build();

  BlockType<BlockCalciteBehavior> CALCITE_TYPE = BlockTypeBuilder
          .builder(BlockCalciteBehavior.class)
          .vanillaBlock(VanillaBlockId.CALCITE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .addBasicComponents()
          .build();

  BlockType<BlockCameraBehavior> CAMERA_TYPE = BlockTypeBuilder
          .builder(BlockCameraBehavior.class)
          .vanillaBlock(VanillaBlockId.CAMERA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCampfireBehavior> CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.CAMPFIRE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .addBasicComponents()
          .build();

  BlockType<BlockCandleBehavior> CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCandleCakeBehavior> CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCarrotsBehavior> CARROTS_TYPE = BlockTypeBuilder
          .builder(BlockCarrotsBehavior.class)
          .vanillaBlock(VanillaBlockId.CARROTS, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockCartographyTableBehavior> CARTOGRAPHY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCartographyTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCarvedPumpkinBehavior> CARVED_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockCarvedPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCauldronBehavior> CAULDRON_TYPE = BlockTypeBuilder
          .builder(BlockCauldronBehavior.class)
          .vanillaBlock(VanillaBlockId.CAULDRON, true)
          .withProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesBehavior> CAVE_VINES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesBodyWithBerriesBehavior> CAVE_VINES_BODY_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockChainBehavior> CHAIN_TYPE = BlockTypeBuilder
          .builder(BlockChainBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockChainCommandBlockBehavior> CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockChainCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChemicalHeatBehavior> CHEMICAL_HEAT_TYPE = BlockTypeBuilder
          .builder(BlockChemicalHeatBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChemistryTableBehavior> CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockChemistryTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE, true)
          .withProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryButtonBehavior> CHERRY_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCherryButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoorBehavior> CHERRY_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoubleSlabBehavior> CHERRY_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFenceBehavior> CHERRY_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFenceGateBehavior> CHERRY_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryHangingSignBehavior> CHERRY_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLeavesBehavior> CHERRY_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockCherryLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLogBehavior> CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockCherryLogBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPlanksBehavior> CHERRY_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCherryPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPressurePlateBehavior> CHERRY_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySaplingBehavior> CHERRY_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockCherrySaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySlabBehavior> CHERRY_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherrySlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStairsBehavior> CHERRY_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCherryStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStandingSignBehavior> CHERRY_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryTrapdoorBehavior> CHERRY_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWallSignBehavior> CHERRY_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWoodBehavior> CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockChestBehavior> CHEST_TYPE = BlockTypeBuilder
          .builder(BlockChestBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledBookshelfBehavior> CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockChiseledBookshelfBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF, true)
          .withProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledDeepslateBehavior> CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledNetherBricksBehavior> CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockChiseledNetherBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockChorusFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_FLOWER, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_6)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusPlantBehavior> CHORUS_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockChorusPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_PLANT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockClayBehavior> CLAY_TYPE = BlockTypeBuilder
          .builder(BlockClayBehavior.class)
          .vanillaBlock(VanillaBlockId.CLAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockClientRequestPlaceholderBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalBlockBehavior> COAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoalBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COAL_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalOreBehavior> COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCoalOreBehavior.class)
          .vanillaBlock(VanillaBlockId.COAL_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateDoubleSlabBehavior> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateSlabBehavior> COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateStairsBehavior> COBBLED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateWallBehavior> COBBLED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateWallBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestoneBehavior> COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestoneWallBehavior> COBBLESTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_BLOCK_TYPE, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCocoaBehavior> COCOA_TYPE = BlockTypeBuilder
          .builder(BlockCocoaBehavior.class)
          .vanillaBlock(VanillaBlockId.COCOA, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchBpBehavior> COLORED_TORCH_BP_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchBpBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchRgBehavior> COLORED_TORCH_RG_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchRgBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCommandBlockBehavior> COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockComposterBehavior> COMPOSTER_TYPE = BlockTypeBuilder
          .builder(BlockComposterBehavior.class)
          .vanillaBlock(VanillaBlockId.COMPOSTER, true)
          .withProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockConcretePowderBehavior> CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.CONCRETE_POWDER, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockConduitBehavior> CONDUIT_TYPE = BlockTypeBuilder
          .builder(BlockConduitBehavior.class)
          .vanillaBlock(VanillaBlockId.CONDUIT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperBlockBehavior> COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperOreBehavior> COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralBlockBehavior> CORAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoralBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanBehavior> CORAL_FAN_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanDeadBehavior> CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanDeadBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHangBehavior> CORAL_FAN_HANG_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHangBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang2Behavior> CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang2Behavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang3Behavior> CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang3Behavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateBricksBehavior> CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedNetherBricksBehavior> CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedNetherBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedPolishedBlackstoneBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCraftingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CRAFTING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonButtonBehavior> CRIMSON_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoorBehavior> CRIMSON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoubleSlabBehavior> CRIMSON_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFenceBehavior> CRIMSON_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFenceGateBehavior> CRIMSON_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHangingSignBehavior> CRIMSON_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHyphaeBehavior> CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPlanksBehavior> CRIMSON_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPressurePlateBehavior> CRIMSON_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonRootsBehavior> CRIMSON_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonSlabBehavior> CRIMSON_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStairsBehavior> CRIMSON_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStandingSignBehavior> CRIMSON_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStemBehavior> CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonTrapdoorBehavior> CRIMSON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonWallSignBehavior> CRIMSON_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCryingObsidianBehavior> CRYING_OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockCryingObsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperBehavior> CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperSlabBehavior> CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperStairsBehavior> CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandleBehavior> CYAN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandleCakeBehavior> CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCarpetBehavior> CYAN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockCyanCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanConcreteBehavior> CYAN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockCyanConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanGlazedTerracottaBehavior> CYAN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockCyanGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanShulkerBoxBehavior> CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockCyanShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanWoolBehavior> CYAN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockCyanWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakButtonBehavior> DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakDoorBehavior> DARK_OAK_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFenceBehavior> DARK_OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFenceGateBehavior> DARK_OAK_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakHangingSignBehavior> DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakLogBehavior> DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakPressurePlateBehavior> DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakStairsBehavior> DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakTrapdoorBehavior> DARK_OAK_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkPrismarineStairsBehavior> DARK_PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkPrismarineStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakStandingSignBehavior> DARKOAK_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakWallSignBehavior> DARKOAK_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetectorInvertedBehavior> DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorInvertedBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBrainCoralBehavior> DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBrainCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBubbleCoralBehavior> DEAD_BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBubbleCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadFireCoralBehavior> DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadFireCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadHornCoralBehavior> DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadTubeCoralBehavior> DEAD_TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadTubeCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_TUBE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadbushBehavior> DEADBUSH_TYPE = BlockTypeBuilder
          .builder(BlockDeadbushBehavior.class)
          .vanillaBlock(VanillaBlockId.DEADBUSH, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDecoratedPotBehavior> DECORATED_POT_TYPE = BlockTypeBuilder
          .builder(BlockDecoratedPotBehavior.class)
          .vanillaBlock(VanillaBlockId.DECORATED_POT, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBehavior> DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickDoubleSlabBehavior> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickSlabBehavior> DEEPSLATE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickStairsBehavior> DEEPSLATE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickWallBehavior> DEEPSLATE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBricksBehavior> DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCoalOreBehavior> DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCoalOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateDiamondOreBehavior> DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateDiamondOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateEmeraldOreBehavior> DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateEmeraldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateIronOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateLapisOreBehavior> DEEPSLATE_LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateLapisOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_LAPIS_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateRedstoneOreBehavior> DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileDoubleSlabBehavior> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileSlabBehavior> DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileStairsBehavior> DEEPSLATE_TILE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileWallBehavior> DEEPSLATE_TILE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileWallBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDenyBehavior> DENY_TYPE = BlockTypeBuilder
          .builder(BlockDenyBehavior.class)
          .vanillaBlock(VanillaBlockId.DENY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDetectorRailBehavior> DETECTOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockDetectorRailBehavior.class)
          .vanillaBlock(VanillaBlockId.DETECTOR_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondBlockBehavior> DIAMOND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDiamondBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondOreBehavior> DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDiamondOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDioriteStairsBehavior> DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDioriteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DIORITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDirtBehavior> DIRT_TYPE = BlockTypeBuilder
          .builder(BlockDirtBehavior.class)
          .vanillaBlock(VanillaBlockId.DIRT, true)
          .withProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockDirtWithRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDispenserBehavior> DISPENSER_TYPE = BlockTypeBuilder
          .builder(BlockDispenserBehavior.class)
          .vanillaBlock(VanillaBlockId.DISPENSER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleCutCopperSlabBehavior> DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoublePlantBehavior> DOUBLE_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockDoublePlantBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlabBehavior> DOUBLE_STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab2Behavior> DOUBLE_STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab2Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab3Behavior> DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab4Behavior> DOUBLE_STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab4Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleWoodenSlabBehavior> DOUBLE_WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleWoodenSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDragonEggBehavior> DRAGON_EGG_TYPE = BlockTypeBuilder
          .builder(BlockDragonEggBehavior.class)
          .vanillaBlock(VanillaBlockId.DRAGON_EGG, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDriedKelpBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDripstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDropperBehavior> DROPPER_TYPE = BlockTypeBuilder
          .builder(BlockDropperBehavior.class)
          .vanillaBlock(VanillaBlockId.DROPPER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockElement0Behavior> ELEMENT_0_TYPE = BlockTypeBuilder
          .builder(BlockElement0Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_0, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement1Behavior> ELEMENT_1_TYPE = BlockTypeBuilder
          .builder(BlockElement1Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_1, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement10Behavior> ELEMENT_10_TYPE = BlockTypeBuilder
          .builder(BlockElement10Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_10, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement100Behavior> ELEMENT_100_TYPE = BlockTypeBuilder
          .builder(BlockElement100Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_100, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement101Behavior> ELEMENT_101_TYPE = BlockTypeBuilder
          .builder(BlockElement101Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_101, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement102Behavior> ELEMENT_102_TYPE = BlockTypeBuilder
          .builder(BlockElement102Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_102, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement103Behavior> ELEMENT_103_TYPE = BlockTypeBuilder
          .builder(BlockElement103Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_103, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement104Behavior> ELEMENT_104_TYPE = BlockTypeBuilder
          .builder(BlockElement104Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_104, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement105Behavior> ELEMENT_105_TYPE = BlockTypeBuilder
          .builder(BlockElement105Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_105, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement106Behavior> ELEMENT_106_TYPE = BlockTypeBuilder
          .builder(BlockElement106Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_106, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement107Behavior> ELEMENT_107_TYPE = BlockTypeBuilder
          .builder(BlockElement107Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_107, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement108Behavior> ELEMENT_108_TYPE = BlockTypeBuilder
          .builder(BlockElement108Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_108, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement109Behavior> ELEMENT_109_TYPE = BlockTypeBuilder
          .builder(BlockElement109Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_109, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement11Behavior> ELEMENT_11_TYPE = BlockTypeBuilder
          .builder(BlockElement11Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_11, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement110Behavior> ELEMENT_110_TYPE = BlockTypeBuilder
          .builder(BlockElement110Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_110, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement111Behavior> ELEMENT_111_TYPE = BlockTypeBuilder
          .builder(BlockElement111Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_111, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement112Behavior> ELEMENT_112_TYPE = BlockTypeBuilder
          .builder(BlockElement112Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_112, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement113Behavior> ELEMENT_113_TYPE = BlockTypeBuilder
          .builder(BlockElement113Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_113, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement114Behavior> ELEMENT_114_TYPE = BlockTypeBuilder
          .builder(BlockElement114Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_114, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement115Behavior> ELEMENT_115_TYPE = BlockTypeBuilder
          .builder(BlockElement115Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_115, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement116Behavior> ELEMENT_116_TYPE = BlockTypeBuilder
          .builder(BlockElement116Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_116, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement117Behavior> ELEMENT_117_TYPE = BlockTypeBuilder
          .builder(BlockElement117Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_117, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement118Behavior> ELEMENT_118_TYPE = BlockTypeBuilder
          .builder(BlockElement118Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_118, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement12Behavior> ELEMENT_12_TYPE = BlockTypeBuilder
          .builder(BlockElement12Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_12, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement13Behavior> ELEMENT_13_TYPE = BlockTypeBuilder
          .builder(BlockElement13Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_13, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement14Behavior> ELEMENT_14_TYPE = BlockTypeBuilder
          .builder(BlockElement14Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_14, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement15Behavior> ELEMENT_15_TYPE = BlockTypeBuilder
          .builder(BlockElement15Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_15, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement16Behavior> ELEMENT_16_TYPE = BlockTypeBuilder
          .builder(BlockElement16Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_16, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement17Behavior> ELEMENT_17_TYPE = BlockTypeBuilder
          .builder(BlockElement17Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_17, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement18Behavior> ELEMENT_18_TYPE = BlockTypeBuilder
          .builder(BlockElement18Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_18, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement19Behavior> ELEMENT_19_TYPE = BlockTypeBuilder
          .builder(BlockElement19Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_19, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement2Behavior> ELEMENT_2_TYPE = BlockTypeBuilder
          .builder(BlockElement2Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_2, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement20Behavior> ELEMENT_20_TYPE = BlockTypeBuilder
          .builder(BlockElement20Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_20, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement21Behavior> ELEMENT_21_TYPE = BlockTypeBuilder
          .builder(BlockElement21Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_21, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement22Behavior> ELEMENT_22_TYPE = BlockTypeBuilder
          .builder(BlockElement22Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_22, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement23Behavior> ELEMENT_23_TYPE = BlockTypeBuilder
          .builder(BlockElement23Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_23, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement24Behavior> ELEMENT_24_TYPE = BlockTypeBuilder
          .builder(BlockElement24Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_24, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement25Behavior> ELEMENT_25_TYPE = BlockTypeBuilder
          .builder(BlockElement25Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_25, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement26Behavior> ELEMENT_26_TYPE = BlockTypeBuilder
          .builder(BlockElement26Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_26, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement27Behavior> ELEMENT_27_TYPE = BlockTypeBuilder
          .builder(BlockElement27Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_27, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement28Behavior> ELEMENT_28_TYPE = BlockTypeBuilder
          .builder(BlockElement28Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_28, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement29Behavior> ELEMENT_29_TYPE = BlockTypeBuilder
          .builder(BlockElement29Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_29, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement3Behavior> ELEMENT_3_TYPE = BlockTypeBuilder
          .builder(BlockElement3Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_3, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement30Behavior> ELEMENT_30_TYPE = BlockTypeBuilder
          .builder(BlockElement30Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_30, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement31Behavior> ELEMENT_31_TYPE = BlockTypeBuilder
          .builder(BlockElement31Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_31, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement32Behavior> ELEMENT_32_TYPE = BlockTypeBuilder
          .builder(BlockElement32Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_32, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement33Behavior> ELEMENT_33_TYPE = BlockTypeBuilder
          .builder(BlockElement33Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_33, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement34Behavior> ELEMENT_34_TYPE = BlockTypeBuilder
          .builder(BlockElement34Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_34, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement35Behavior> ELEMENT_35_TYPE = BlockTypeBuilder
          .builder(BlockElement35Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_35, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement36Behavior> ELEMENT_36_TYPE = BlockTypeBuilder
          .builder(BlockElement36Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_36, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement37Behavior> ELEMENT_37_TYPE = BlockTypeBuilder
          .builder(BlockElement37Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_37, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement38Behavior> ELEMENT_38_TYPE = BlockTypeBuilder
          .builder(BlockElement38Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_38, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement39Behavior> ELEMENT_39_TYPE = BlockTypeBuilder
          .builder(BlockElement39Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_39, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement4Behavior> ELEMENT_4_TYPE = BlockTypeBuilder
          .builder(BlockElement4Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_4, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement40Behavior> ELEMENT_40_TYPE = BlockTypeBuilder
          .builder(BlockElement40Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_40, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement41Behavior> ELEMENT_41_TYPE = BlockTypeBuilder
          .builder(BlockElement41Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_41, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement42Behavior> ELEMENT_42_TYPE = BlockTypeBuilder
          .builder(BlockElement42Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_42, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement43Behavior> ELEMENT_43_TYPE = BlockTypeBuilder
          .builder(BlockElement43Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_43, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement44Behavior> ELEMENT_44_TYPE = BlockTypeBuilder
          .builder(BlockElement44Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_44, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement45Behavior> ELEMENT_45_TYPE = BlockTypeBuilder
          .builder(BlockElement45Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_45, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement46Behavior> ELEMENT_46_TYPE = BlockTypeBuilder
          .builder(BlockElement46Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_46, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement47Behavior> ELEMENT_47_TYPE = BlockTypeBuilder
          .builder(BlockElement47Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_47, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement48Behavior> ELEMENT_48_TYPE = BlockTypeBuilder
          .builder(BlockElement48Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_48, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement49Behavior> ELEMENT_49_TYPE = BlockTypeBuilder
          .builder(BlockElement49Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_49, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement5Behavior> ELEMENT_5_TYPE = BlockTypeBuilder
          .builder(BlockElement5Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_5, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement50Behavior> ELEMENT_50_TYPE = BlockTypeBuilder
          .builder(BlockElement50Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_50, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement51Behavior> ELEMENT_51_TYPE = BlockTypeBuilder
          .builder(BlockElement51Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_51, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement52Behavior> ELEMENT_52_TYPE = BlockTypeBuilder
          .builder(BlockElement52Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_52, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement53Behavior> ELEMENT_53_TYPE = BlockTypeBuilder
          .builder(BlockElement53Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_53, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement54Behavior> ELEMENT_54_TYPE = BlockTypeBuilder
          .builder(BlockElement54Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_54, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement55Behavior> ELEMENT_55_TYPE = BlockTypeBuilder
          .builder(BlockElement55Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_55, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement56Behavior> ELEMENT_56_TYPE = BlockTypeBuilder
          .builder(BlockElement56Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_56, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement57Behavior> ELEMENT_57_TYPE = BlockTypeBuilder
          .builder(BlockElement57Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_57, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement58Behavior> ELEMENT_58_TYPE = BlockTypeBuilder
          .builder(BlockElement58Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_58, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement59Behavior> ELEMENT_59_TYPE = BlockTypeBuilder
          .builder(BlockElement59Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_59, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement6Behavior> ELEMENT_6_TYPE = BlockTypeBuilder
          .builder(BlockElement6Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_6, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement60Behavior> ELEMENT_60_TYPE = BlockTypeBuilder
          .builder(BlockElement60Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_60, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement61Behavior> ELEMENT_61_TYPE = BlockTypeBuilder
          .builder(BlockElement61Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_61, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement62Behavior> ELEMENT_62_TYPE = BlockTypeBuilder
          .builder(BlockElement62Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_62, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement63Behavior> ELEMENT_63_TYPE = BlockTypeBuilder
          .builder(BlockElement63Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_63, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement64Behavior> ELEMENT_64_TYPE = BlockTypeBuilder
          .builder(BlockElement64Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_64, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement65Behavior> ELEMENT_65_TYPE = BlockTypeBuilder
          .builder(BlockElement65Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_65, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement66Behavior> ELEMENT_66_TYPE = BlockTypeBuilder
          .builder(BlockElement66Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_66, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement67Behavior> ELEMENT_67_TYPE = BlockTypeBuilder
          .builder(BlockElement67Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_67, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement68Behavior> ELEMENT_68_TYPE = BlockTypeBuilder
          .builder(BlockElement68Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_68, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement69Behavior> ELEMENT_69_TYPE = BlockTypeBuilder
          .builder(BlockElement69Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_69, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement7Behavior> ELEMENT_7_TYPE = BlockTypeBuilder
          .builder(BlockElement7Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_7, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement70Behavior> ELEMENT_70_TYPE = BlockTypeBuilder
          .builder(BlockElement70Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_70, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement71Behavior> ELEMENT_71_TYPE = BlockTypeBuilder
          .builder(BlockElement71Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_71, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement72Behavior> ELEMENT_72_TYPE = BlockTypeBuilder
          .builder(BlockElement72Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_72, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement73Behavior> ELEMENT_73_TYPE = BlockTypeBuilder
          .builder(BlockElement73Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_73, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement74Behavior> ELEMENT_74_TYPE = BlockTypeBuilder
          .builder(BlockElement74Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_74, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement75Behavior> ELEMENT_75_TYPE = BlockTypeBuilder
          .builder(BlockElement75Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_75, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement76Behavior> ELEMENT_76_TYPE = BlockTypeBuilder
          .builder(BlockElement76Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_76, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement77Behavior> ELEMENT_77_TYPE = BlockTypeBuilder
          .builder(BlockElement77Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_77, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement78Behavior> ELEMENT_78_TYPE = BlockTypeBuilder
          .builder(BlockElement78Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_78, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement79Behavior> ELEMENT_79_TYPE = BlockTypeBuilder
          .builder(BlockElement79Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_79, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement8Behavior> ELEMENT_8_TYPE = BlockTypeBuilder
          .builder(BlockElement8Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_8, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement80Behavior> ELEMENT_80_TYPE = BlockTypeBuilder
          .builder(BlockElement80Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_80, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement81Behavior> ELEMENT_81_TYPE = BlockTypeBuilder
          .builder(BlockElement81Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_81, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement82Behavior> ELEMENT_82_TYPE = BlockTypeBuilder
          .builder(BlockElement82Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_82, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement83Behavior> ELEMENT_83_TYPE = BlockTypeBuilder
          .builder(BlockElement83Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_83, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement84Behavior> ELEMENT_84_TYPE = BlockTypeBuilder
          .builder(BlockElement84Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_84, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement85Behavior> ELEMENT_85_TYPE = BlockTypeBuilder
          .builder(BlockElement85Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_85, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement86Behavior> ELEMENT_86_TYPE = BlockTypeBuilder
          .builder(BlockElement86Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_86, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement87Behavior> ELEMENT_87_TYPE = BlockTypeBuilder
          .builder(BlockElement87Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_87, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement88Behavior> ELEMENT_88_TYPE = BlockTypeBuilder
          .builder(BlockElement88Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_88, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement89Behavior> ELEMENT_89_TYPE = BlockTypeBuilder
          .builder(BlockElement89Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_89, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement9Behavior> ELEMENT_9_TYPE = BlockTypeBuilder
          .builder(BlockElement9Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_9, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement90Behavior> ELEMENT_90_TYPE = BlockTypeBuilder
          .builder(BlockElement90Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_90, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement91Behavior> ELEMENT_91_TYPE = BlockTypeBuilder
          .builder(BlockElement91Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_91, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement92Behavior> ELEMENT_92_TYPE = BlockTypeBuilder
          .builder(BlockElement92Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_92, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement93Behavior> ELEMENT_93_TYPE = BlockTypeBuilder
          .builder(BlockElement93Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_93, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement94Behavior> ELEMENT_94_TYPE = BlockTypeBuilder
          .builder(BlockElement94Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_94, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement95Behavior> ELEMENT_95_TYPE = BlockTypeBuilder
          .builder(BlockElement95Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_95, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement96Behavior> ELEMENT_96_TYPE = BlockTypeBuilder
          .builder(BlockElement96Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_96, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement97Behavior> ELEMENT_97_TYPE = BlockTypeBuilder
          .builder(BlockElement97Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_97, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement98Behavior> ELEMENT_98_TYPE = BlockTypeBuilder
          .builder(BlockElement98Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_98, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement99Behavior> ELEMENT_99_TYPE = BlockTypeBuilder
          .builder(BlockElement99Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_99, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldBlockBehavior> EMERALD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.EMERALD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldOreBehavior> EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.EMERALD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEnchantingTableBehavior> ENCHANTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockEnchantingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBrickStairsBehavior> END_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockEndBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBricksBehavior> END_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockEndBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.END_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndGatewayBehavior> END_GATEWAY_TYPE = BlockTypeBuilder
          .builder(BlockEndGatewayBehavior.class)
          .vanillaBlock(VanillaBlockId.END_GATEWAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortalBehavior> END_PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockEndRodBehavior> END_ROD_TYPE = BlockTypeBuilder
          .builder(BlockEndRodBehavior.class)
          .vanillaBlock(VanillaBlockId.END_ROD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndStoneBehavior> END_STONE_TYPE = BlockTypeBuilder
          .builder(BlockEndStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.END_STONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEnderChestBehavior> ENDER_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockEnderChestBehavior.class)
          .vanillaBlock(VanillaBlockId.ENDER_CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCopperBehavior> EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperBehavior> EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperSlabBehavior> EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperStairsBehavior> EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedDoubleCutCopperSlabBehavior> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFarmlandBehavior> FARMLAND_TYPE = BlockTypeBuilder
          .builder(BlockFarmlandBehavior.class)
          .vanillaBlock(VanillaBlockId.FARMLAND, true)
          .withProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockFenceGateBehavior> FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFireBehavior> FIRE_TYPE = BlockTypeBuilder
          .builder(BlockFireBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockFireCoralBehavior> FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockFireCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFletchingTableBehavior> FLETCHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockFletchingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.FLETCHING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowerPotBehavior> FLOWER_POT_TYPE = BlockTypeBuilder
          .builder(BlockFlowerPotBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWER_POT, true)
          .withProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockFloweringAzaleaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingLavaBehavior> FLOWING_LAVA_TYPE = BlockTypeBuilder
          .builder(BlockFlowingLavaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_LAVA, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingWaterBehavior> FLOWING_WATER_TYPE = BlockTypeBuilder
          .builder(BlockFlowingWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_WATER, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFrameBehavior> FRAME_TYPE = BlockTypeBuilder
          .builder(BlockFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFrogSpawnBehavior> FROG_SPAWN_TYPE = BlockTypeBuilder
          .builder(BlockFrogSpawnBehavior.class)
          .vanillaBlock(VanillaBlockId.FROG_SPAWN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFrostedIceBehavior> FROSTED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockFrostedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.FROSTED_ICE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockFurnaceBehavior> FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGildedBlackstoneBehavior> GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGildedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlassBehavior> GLASS_TYPE = BlockTypeBuilder
          .builder(BlockGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlassPaneBehavior> GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS_PANE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowFrameBehavior> GLOW_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockGlowFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOW_FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowLichenBehavior> GLOW_LICHEN_TYPE = BlockTypeBuilder
          .builder(BlockGlowLichenBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOW_LICHEN, true)
          .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowingobsidianBehavior> GLOWINGOBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockGlowingobsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowstoneBehavior> GLOWSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGlowstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOWSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldBlockBehavior> GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockGoldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldOreBehavior> GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldenRailBehavior> GOLDEN_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockGoldenRailBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLDEN_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockGraniteStairsBehavior> GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockGraniteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.GRANITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrassBehavior> GRASS_TYPE = BlockTypeBuilder
          .builder(BlockGrassBehavior.class)
          .vanillaBlock(VanillaBlockId.GRASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrassPathBehavior> GRASS_PATH_TYPE = BlockTypeBuilder
          .builder(BlockGrassPathBehavior.class)
          .vanillaBlock(VanillaBlockId.GRASS_PATH, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGravelBehavior> GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockGravelBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAVEL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandleBehavior> GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandleCakeBehavior> GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCarpetBehavior> GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGrayCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayConcreteBehavior> GRAY_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGrayConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayGlazedTerracottaBehavior> GRAY_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGrayGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayShulkerBoxBehavior> GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayWoolBehavior> GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGrayWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandleBehavior> GREEN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandleCakeBehavior> GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCarpetBehavior> GREEN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGreenCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenConcreteBehavior> GREEN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGreenConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenGlazedTerracottaBehavior> GREEN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGreenGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenShulkerBoxBehavior> GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockGreenShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenWoolBehavior> GREEN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGreenWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrindstoneBehavior> GRINDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGrindstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GRINDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockHangingRootsBehavior> HANGING_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockHangingRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.HANGING_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlassBehavior> HARD_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlassPaneBehavior> HARD_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlassBehavior> HARD_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlassPaneBehavior> HARD_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardenedClayBehavior> HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockHardenedClayBehavior.class)
          .vanillaBlock(VanillaBlockId.HARDENED_CLAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHayBlockBehavior> HAY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHayBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HAY_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockHeavyWeightedPressurePlateBehavior> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockHeavyWeightedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.HEAVY_WEIGHTED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneyBlockBehavior> HONEY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneyBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HONEY_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneycombBlockBehavior> HONEYCOMB_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneycombBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HONEYCOMB_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHopperBehavior> HOPPER_TYPE = BlockTypeBuilder
          .builder(BlockHopperBehavior.class)
          .vanillaBlock(VanillaBlockId.HOPPER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockHornCoralBehavior> HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.HORN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIceBehavior> ICE_TYPE = BlockTypeBuilder
          .builder(BlockIceBehavior.class)
          .vanillaBlock(VanillaBlockId.ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInfestedDeepslateBehavior> INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockInfestedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdateBehavior> INFO_UPDATE_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdateBehavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate2Behavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE2, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInvisibleBedrockBehavior> INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockInvisibleBedrockBehavior.class)
          .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBarsBehavior> IRON_BARS_TYPE = BlockTypeBuilder
          .builder(BlockIronBarsBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BARS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBlockBehavior> IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronDoorBehavior> IRON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockIronOreBehavior> IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockIronOreBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronTrapdoorBehavior> IRON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJigsawBehavior> JIGSAW_TYPE = BlockTypeBuilder
          .builder(BlockJigsawBehavior.class)
          .vanillaBlock(VanillaBlockId.JIGSAW, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
          .addBasicComponents()
          .build();

  BlockType<BlockJukeboxBehavior> JUKEBOX_TYPE = BlockTypeBuilder
          .builder(BlockJukeboxBehavior.class)
          .vanillaBlock(VanillaBlockId.JUKEBOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleButtonBehavior> JUNGLE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockJungleButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleDoorBehavior> JUNGLE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFenceBehavior> JUNGLE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFenceGateBehavior> JUNGLE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleHangingSignBehavior> JUNGLE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleLogBehavior> JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockJungleLogBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockJunglePressurePlateBehavior> JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockJunglePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStairsBehavior> JUNGLE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockJungleStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStandingSignBehavior> JUNGLE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleTrapdoorBehavior> JUNGLE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleWallSignBehavior> JUNGLE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockKelpBehavior> KELP_TYPE = BlockTypeBuilder
          .builder(BlockKelpBehavior.class)
          .vanillaBlock(VanillaBlockId.KELP, true)
          .withProperties(VanillaBlockPropertyTypes.KELP_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockLadderBehavior> LADDER_TYPE = BlockTypeBuilder
          .builder(BlockLadderBehavior.class)
          .vanillaBlock(VanillaBlockId.LADDER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLanternBehavior> LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.LANTERN, true)
          .withProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisBlockBehavior> LAPIS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLapisBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.LAPIS_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisOreBehavior> LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLapisOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LAPIS_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLargeAmethystBudBehavior> LARGE_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockLargeAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLavaBehavior> LAVA_TYPE = BlockTypeBuilder
          .builder(BlockLavaBehavior.class)
          .vanillaBlock(VanillaBlockId.LAVA, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockLeavesBehavior> LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLeaves2Behavior> LEAVES2_TYPE = BlockTypeBuilder
          .builder(BlockLeaves2Behavior.class)
          .vanillaBlock(VanillaBlockId.LEAVES2, true)
          .withProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLecternBehavior> LECTERN_TYPE = BlockTypeBuilder
          .builder(BlockLecternBehavior.class)
          .vanillaBlock(VanillaBlockId.LECTERN, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLeverBehavior> LEVER_TYPE = BlockTypeBuilder
          .builder(BlockLeverBehavior.class)
          .vanillaBlock(VanillaBlockId.LEVER, true)
          .withProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLightBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandleBehavior> LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCarpetBehavior> LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueConcreteBehavior> LIGHT_BLUE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueGlazedTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueShulkerBoxBehavior> LIGHT_BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueWoolBehavior> LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandleBehavior> LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCarpetBehavior> LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayConcreteBehavior> LIGHT_GRAY_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayWoolBehavior> LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightWeightedPressurePlateBehavior> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockLightWeightedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightningRodBehavior> LIGHTNING_ROD_TYPE = BlockTypeBuilder
          .builder(BlockLightningRodBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHTNING_ROD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandleBehavior> LIME_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandleCakeBehavior> LIME_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCarpetBehavior> LIME_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLimeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeConcreteBehavior> LIME_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLimeConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeGlazedTerracottaBehavior> LIME_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLimeGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeShulkerBoxBehavior> LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLimeShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeWoolBehavior> LIME_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLimeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitBlastFurnaceBehavior> LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitDeepslateRedstoneOreBehavior> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitDeepslateRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitFurnaceBehavior> LIT_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitPumpkinBehavior> LIT_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockLitPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneOreBehavior> LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitSmokerBehavior> LIT_SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockLitSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_SMOKER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLodestoneBehavior> LODESTONE_TYPE = BlockTypeBuilder
          .builder(BlockLodestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.LODESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLoomBehavior> LOOM_TYPE = BlockTypeBuilder
          .builder(BlockLoomBehavior.class)
          .vanillaBlock(VanillaBlockId.LOOM, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandleBehavior> MAGENTA_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandleCakeBehavior> MAGENTA_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCarpetBehavior> MAGENTA_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaConcreteBehavior> MAGENTA_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaGlazedTerracottaBehavior> MAGENTA_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockMagentaGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaShulkerBoxBehavior> MAGENTA_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockMagentaShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaWoolBehavior> MAGENTA_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockMagentaWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagmaBehavior> MAGMA_TYPE = BlockTypeBuilder
          .builder(BlockMagmaBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGMA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveButtonBehavior> MANGROVE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockMangroveButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoorBehavior> MANGROVE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoubleSlabBehavior> MANGROVE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFenceBehavior> MANGROVE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFenceGateBehavior> MANGROVE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveHangingSignBehavior> MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLeavesBehavior> MANGROVE_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLogBehavior> MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLogBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePlanksBehavior> MANGROVE_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePressurePlateBehavior> MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePropaguleBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE, true)
          .withProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveRootsBehavior> MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveSlabBehavior> MANGROVE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStairsBehavior> MANGROVE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStandingSignBehavior> MANGROVE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveTrapdoorBehavior> MANGROVE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWallSignBehavior> MANGROVE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWoodBehavior> MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonBlockBehavior> MELON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMelonBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonStemBehavior> MELON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockMelonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockMobSpawnerBehavior> MOB_SPAWNER_TYPE = BlockTypeBuilder
          .builder(BlockMobSpawnerBehavior.class)
          .vanillaBlock(VanillaBlockId.MOB_SPAWNER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMonsterEggBehavior> MONSTER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockMonsterEggBehavior.class)
          .vanillaBlock(VanillaBlockId.MONSTER_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockMossBlockBehavior> MOSS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMossBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossCarpetBehavior> MOSS_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMossCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestoneStairsBehavior> MOSSY_COBBLESTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyStoneBrickStairsBehavior> MOSSY_STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyStoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMovingBlockBehavior> MOVING_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMovingBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MOVING_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBehavior> MUD_TYPE = BlockTypeBuilder
          .builder(BlockMudBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickDoubleSlabBehavior> MUD_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickSlabBehavior> MUD_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickStairsBehavior> MUD_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickWallBehavior> MUD_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBricksBehavior> MUD_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockMudBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMuddyMangroveRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMyceliumBehavior> MYCELIUM_TYPE = BlockTypeBuilder
          .builder(BlockMyceliumBehavior.class)
          .vanillaBlock(VanillaBlockId.MYCELIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickBehavior> NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickFenceBehavior> NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickStairsBehavior> NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockNetherGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherSproutsBehavior> NETHER_SPROUTS_TYPE = BlockTypeBuilder
          .builder(BlockNetherSproutsBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_SPROUTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWartBehavior> NETHER_WART_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetheriteBlockBehavior> NETHERITE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetheriteBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherrackBehavior> NETHERRACK_TYPE = BlockTypeBuilder
          .builder(BlockNetherrackBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERRACK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherreactorBehavior> NETHERREACTOR_TYPE = BlockTypeBuilder
          .builder(BlockNetherreactorBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERREACTOR, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNormalStoneStairsBehavior> NORMAL_STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNormalStoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNoteblockBehavior> NOTEBLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNoteblockBehavior.class)
          .vanillaBlock(VanillaBlockId.NOTEBLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOakFenceBehavior> OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOakHangingSignBehavior> OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockOakHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockOakLogBehavior> OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOakStairsBehavior> OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOakStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockObserverBehavior> OBSERVER_TYPE = BlockTypeBuilder
          .builder(BlockObserverBehavior.class)
          .vanillaBlock(VanillaBlockId.OBSERVER, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockObsidianBehavior> OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockObsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.OBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOchreFroglightBehavior> OCHRE_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockOchreFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandleBehavior> ORANGE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandleCakeBehavior> ORANGE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCarpetBehavior> ORANGE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeConcreteBehavior> ORANGE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeGlazedTerracottaBehavior> ORANGE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockOrangeGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeShulkerBoxBehavior> ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockOrangeShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeWoolBehavior> ORANGE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockOrangeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCopperBehavior> OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperBehavior> OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperSlabBehavior> OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperStairsBehavior> OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedDoubleCutCopperSlabBehavior> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedIceBehavior> PACKED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockPackedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.PACKED_ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedMudBehavior> PACKED_MUD_TYPE = BlockTypeBuilder
          .builder(BlockPackedMudBehavior.class)
          .vanillaBlock(VanillaBlockId.PACKED_MUD, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockPearlescentFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandleBehavior> PINK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandleCakeBehavior> PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCarpetBehavior> PINK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPinkCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkConcreteBehavior> PINK_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPinkConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkGlazedTerracottaBehavior> PINK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPinkGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkPetalsBehavior> PINK_PETALS_TYPE = BlockTypeBuilder
          .builder(BlockPinkPetalsBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_PETALS, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkShulkerBoxBehavior> PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockPinkShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkWoolBehavior> PINK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPinkWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPistonBehavior> PISTON_TYPE = BlockTypeBuilder
          .builder(BlockPistonBehavior.class)
          .vanillaBlock(VanillaBlockId.PISTON, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPistonArmCollisionBehavior> PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockPistonArmCollisionBehavior.class)
          .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherCropBehavior> PITCHER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockPitcherCropBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_CROP, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockPitcherPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPlanksBehavior> PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.PLANKS, true)
          .withProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPodzolBehavior> PODZOL_TYPE = BlockTypeBuilder
          .builder(BlockPodzolBehavior.class)
          .vanillaBlock(VanillaBlockId.PODZOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPointedDripstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedAndesiteStairsBehavior> POLISHED_ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedAndesiteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBasaltBehavior> POLISHED_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BASALT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickDoubleSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickSlabBehavior> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickStairsBehavior> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickWallBehavior> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBricksBehavior> POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneButtonBehavior> POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneDoubleSlabBehavior> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstonePressurePlateBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneSlabBehavior> POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneStairsBehavior> POLISHED_BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneWallBehavior> POLISHED_BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateBehavior> POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateDoubleSlabBehavior> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateSlabBehavior> POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateStairsBehavior> POLISHED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateWallBehavior> POLISHED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDioriteStairsBehavior> POLISHED_DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDioriteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedGraniteStairsBehavior> POLISHED_GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedGraniteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPortalBehavior> PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.PORTAL, true)
          .withProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPotatoesBehavior> POTATOES_TYPE = BlockTypeBuilder
          .builder(BlockPotatoesBehavior.class)
          .vanillaBlock(VanillaBlockId.POTATOES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPowderSnowBehavior> POWDER_SNOW_TYPE = BlockTypeBuilder
          .builder(BlockPowderSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.POWDER_SNOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredComparatorBehavior> POWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockPoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredRepeaterBehavior> POWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockPoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_REPEATER, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineBehavior> PRISMARINE_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE, true)
          .withProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineBricksStairsBehavior> PRISMARINE_BRICKS_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineBricksStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineStairsBehavior> PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkinBehavior> PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinStemBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandleBehavior> PURPLE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandleCakeBehavior> PURPLE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCarpetBehavior> PURPLE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleConcreteBehavior> PURPLE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleGlazedTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPurpleGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleShulkerBoxBehavior> PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockPurpleShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleWoolBehavior> PURPLE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPurpleWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockPurpurBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPUR_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurStairsBehavior> PURPUR_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPurpurStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPUR_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBricksBehavior> QUARTZ_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzOreBehavior> QUARTZ_ORE_TYPE = BlockTypeBuilder
          .builder(BlockQuartzOreBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzStairsBehavior> QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRailBehavior> RAIL_TYPE = BlockTypeBuilder
          .builder(BlockRailBehavior.class)
          .vanillaBlock(VanillaBlockId.RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
          .addBasicComponents()
          .build();

  BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRawGoldBlockBehavior> RAW_GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawGoldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRawIronBlockBehavior> RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandleBehavior> RED_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandleCakeBehavior> RED_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCarpetBehavior> RED_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockRedCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedConcreteBehavior> RED_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockRedConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedFlowerBehavior> RED_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockRedFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_FLOWER, true)
          .withProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedGlazedTerracottaBehavior> RED_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockRedGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroomBehavior> RED_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrickStairsBehavior> RED_NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstoneBehavior> RED_SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstoneStairsBehavior> RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedShulkerBoxBehavior> RED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockRedShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedWoolBehavior> RED_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockRedWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_LAMP, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneOreBehavior> REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneTorchBehavior> REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneWireBehavior> REDSTONE_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneWireBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_WIRE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockReedsBehavior> REEDS_TYPE = BlockTypeBuilder
          .builder(BlockReedsBehavior.class)
          .vanillaBlock(VanillaBlockId.REEDS, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockReinforcedDeepslateBehavior> REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockReinforcedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRepeatingCommandBlockBehavior> REPEATING_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRepeatingCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockReserved6Behavior> RESERVED6_TYPE = BlockTypeBuilder
          .builder(BlockReserved6Behavior.class)
          .vanillaBlock(VanillaBlockId.RESERVED6, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
          .builder(BlockRespawnAnchorBehavior.class)
          .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR, true)
          .withProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandBehavior> SAND_TYPE = BlockTypeBuilder
          .builder(BlockSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SAND, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstoneBehavior> SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstoneStairsBehavior> SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSaplingBehavior> SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockSaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockScaffoldingBehavior> SCAFFOLDING_TYPE = BlockTypeBuilder
          .builder(BlockScaffoldingBehavior.class)
          .vanillaBlock(VanillaBlockId.SCAFFOLDING, true)
          .withProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkBehavior> SCULK_TYPE = BlockTypeBuilder
          .builder(BlockSculkBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST_TYPE = BlockTypeBuilder
          .builder(BlockSculkCatalystBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_CATALYST, true)
          .withProperties(VanillaBlockPropertyTypes.BLOOM)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkSensorBehavior> SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_SENSOR, true)
          .withProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkShriekerBehavior> SCULK_SHRIEKER_TYPE = BlockTypeBuilder
          .builder(BlockSculkShriekerBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER, true)
          .withProperties(VanillaBlockPropertyTypes.ACTIVE, VanillaBlockPropertyTypes.CAN_SUMMON)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkVeinBehavior> SCULK_VEIN_TYPE = BlockTypeBuilder
          .builder(BlockSculkVeinBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_VEIN, true)
          .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaLanternBehavior> SEA_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSeaLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_LANTERN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaPickleBehavior> SEA_PICKLE_TYPE = BlockTypeBuilder
          .builder(BlockSeaPickleBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_PICKLE, true)
          .withProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSeagrassBehavior> SEAGRASS_TYPE = BlockTypeBuilder
          .builder(BlockSeagrassBehavior.class)
          .vanillaBlock(VanillaBlockId.SEAGRASS, true)
          .withProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockShroomlightBehavior> SHROOMLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockShroomlightBehavior.class)
          .vanillaBlock(VanillaBlockId.SHROOMLIGHT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSilverGlazedTerracottaBehavior> SILVER_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockSilverGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSkullBehavior> SKULL_TYPE = BlockTypeBuilder
          .builder(BlockSkullBehavior.class)
          .vanillaBlock(VanillaBlockId.SKULL, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSlimeBehavior> SLIME_TYPE = BlockTypeBuilder
          .builder(BlockSlimeBehavior.class)
          .vanillaBlock(VanillaBlockId.SLIME, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockSmallAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallDripleafBlockBehavior> SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockSmallDripleafBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSmithingTableBehavior> SMITHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockSmithingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.SMITHING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmokerBehavior> SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOKER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothBasaltBehavior> SMOOTH_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockSmoothBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_BASALT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothQuartzStairsBehavior> SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothQuartzStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothRedSandstoneStairsBehavior> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothRedSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothSandstoneStairsBehavior> SMOOTH_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE_TYPE = BlockTypeBuilder
          .builder(BlockSmoothStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_STONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSnifferEggBehavior> SNIFFER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockSnifferEggBehavior.class)
          .vanillaBlock(VanillaBlockId.SNIFFER_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
          .addBasicComponents()
          .build();

  BlockType<BlockSnowBehavior> SNOW_TYPE = BlockTypeBuilder
          .builder(BlockSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSnowLayerBehavior> SNOW_LAYER_TYPE = BlockTypeBuilder
          .builder(BlockSnowLayerBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW_LAYER, true)
          .withProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulFireBehavior> SOUL_FIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulFireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_FIRE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulLanternBehavior> SOUL_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSoulLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_LANTERN, true)
          .withProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSandBehavior> SOUL_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSoulSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SAND, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSoilBehavior> SOUL_SOIL_TYPE = BlockTypeBuilder
          .builder(BlockSoulSoilBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SOIL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulTorchBehavior> SOUL_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockSoulTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpongeBehavior> SPONGE_TYPE = BlockTypeBuilder
          .builder(BlockSpongeBehavior.class)
          .vanillaBlock(VanillaBlockId.SPONGE, true)
          .withProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM_TYPE = BlockTypeBuilder
          .builder(BlockSporeBlossomBehavior.class)
          .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceButtonBehavior> SPRUCE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockSpruceButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceDoorBehavior> SPRUCE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFenceBehavior> SPRUCE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFenceGateBehavior> SPRUCE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceHangingSignBehavior> SPRUCE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceLogBehavior> SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockSpruceLogBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockSprucePressurePlateBehavior> SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockSprucePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStairsBehavior> SPRUCE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStandingSignBehavior> SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceTrapdoorBehavior> SPRUCE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceWallSignBehavior> SPRUCE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlassBehavior> STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlassPaneBehavior> STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS_PANE, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedHardenedClayBehavior> STAINED_HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockStainedHardenedClayBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_HARDENED_CLAY, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingBannerBehavior> STANDING_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockStandingBannerBehavior.class)
          .vanillaBlock(VanillaBlockId.STANDING_BANNER, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingSignBehavior> STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPistonBehavior> STICKY_PISTON_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPistonArmCollisionBehavior> STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonArmCollisionBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBehavior> STONE_TYPE = BlockTypeBuilder
          .builder(BlockStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlabBehavior> STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab2Behavior> STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab2Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab3Behavior> STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab4Behavior> STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab4Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBrickStairsBehavior> STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneButtonBehavior> STONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockStoneButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonePressurePlateBehavior> STONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockStonePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneStairsBehavior> STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonebrickBehavior> STONEBRICK_TYPE = BlockTypeBuilder
          .builder(BlockStonebrickBehavior.class)
          .vanillaBlock(VanillaBlockId.STONEBRICK, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutterBehavior> STONECUTTER_TYPE = BlockTypeBuilder
          .builder(BlockStonecutterBehavior.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutterBlockBehavior> STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStonecutterBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedAcaciaLogBehavior> STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedAcaciaLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBambooBlockBehavior> STRIPPED_BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBambooBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBirchLogBehavior> STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryLogBehavior> STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryWoodBehavior> STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonHyphaeBehavior> STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonStemBehavior> STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedDarkOakLogBehavior> STRIPPED_DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedDarkOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedJungleLogBehavior> STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedJungleLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveLogBehavior> STRIPPED_MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveWoodBehavior> STRIPPED_MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedOakLogBehavior> STRIPPED_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedSpruceLogBehavior> STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedSpruceLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedHyphaeBehavior> STRIPPED_WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedStemBehavior> STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStructureBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureVoidBehavior> STRUCTURE_VOID_TYPE = BlockTypeBuilder
          .builder(BlockStructureVoidBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_VOID, true)
          .withProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousGravelBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL, true)
          .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND, true)
          .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSweetBerryBushBehavior> SWEET_BERRY_BUSH_TYPE = BlockTypeBuilder
          .builder(BlockSweetBerryBushBehavior.class)
          .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTallgrassBehavior> TALLGRASS_TYPE = BlockTypeBuilder
          .builder(BlockTallgrassBehavior.class)
          .vanillaBlock(VanillaBlockId.TALLGRASS, true)
          .withProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockTargetBehavior> TARGET_TYPE = BlockTypeBuilder
          .builder(BlockTargetBehavior.class)
          .vanillaBlock(VanillaBlockId.TARGET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTintedGlassBehavior> TINTED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockTintedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.TINTED_GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTntBehavior> TNT_TYPE = BlockTypeBuilder
          .builder(BlockTntBehavior.class)
          .vanillaBlock(VanillaBlockId.TNT, true)
          .withProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchBehavior> TORCH_TYPE = BlockTypeBuilder
          .builder(BlockTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflowerBehavior> TORCHFLOWER_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflowerCropBehavior> TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerCropBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTrapdoorBehavior> TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockTrappedChestBehavior.class)
          .vanillaBlock(VanillaBlockId.TRAPPED_CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTripWireBehavior> TRIP_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockTripWireBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIP_WIRE, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
          .builder(BlockTripwireHookBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTubeCoralBehavior> TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockTubeCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.TUBE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTuffBehavior> TUFF_TYPE = BlockTypeBuilder
          .builder(BlockTuffBehavior.class)
          .vanillaBlock(VanillaBlockId.TUFF, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTurtleEggBehavior> TURTLE_EGG_TYPE = BlockTypeBuilder
          .builder(BlockTurtleEggBehavior.class)
          .vanillaBlock(VanillaBlockId.TURTLE_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockTwistingVinesBehavior> TWISTING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockTwistingVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.TWISTING_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockUnderwaterTorchBehavior> UNDERWATER_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnderwaterTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUndyedShulkerBoxBehavior> UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockUndyedShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockUnknownBehavior> UNKNOWN_TYPE = BlockTypeBuilder
          .builder(BlockUnknownBehavior.class)
          .vanillaBlock(VanillaBlockId.UNKNOWN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockUnlitRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnlitRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .addBasicComponents()
          .build();

  BlockType<BlockVerdantFroglightBehavior> VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockVerdantFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockVineBehavior> VINE_TYPE = BlockTypeBuilder
          .builder(BlockVineBehavior.class)
          .vanillaBlock(VanillaBlockId.VINE, true)
          .withProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockWallBannerBehavior> WALL_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockWallBannerBehavior.class)
          .vanillaBlock(VanillaBlockId.WALL_BANNER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWallSignBehavior> WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedButtonBehavior> WARPED_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWarpedButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoorBehavior> WARPED_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoubleSlabBehavior> WARPED_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFenceBehavior> WARPED_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFenceGateBehavior> WARPED_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FUNGUS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHangingSignBehavior> WARPED_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHyphaeBehavior> WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_NYLIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPlanksBehavior> WARPED_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPressurePlateBehavior> WARPED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedRootsBehavior> WARPED_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedSlabBehavior> WARPED_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStairsBehavior> WARPED_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStandingSignBehavior> WARPED_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStemBehavior> WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedTrapdoorBehavior> WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWallSignBehavior> WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaterBehavior> WATER_TYPE = BlockTypeBuilder
          .builder(BlockWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.WATER, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWaterlilyBehavior> WATERLILY_TYPE = BlockTypeBuilder
          .builder(BlockWaterlilyBehavior.class)
          .vanillaBlock(VanillaBlockId.WATERLILY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCopperBehavior> WAXED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperBehavior> WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperSlabBehavior> WAXED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperStairsBehavior> WAXED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedDoubleCutCopperSlabBehavior> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCopperBehavior> WAXED_EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperBehavior> WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperStairsBehavior> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedDoubleCutCopperSlabBehavior> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCopperBehavior> WAXED_OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperBehavior> WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperStairsBehavior> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedDoubleCutCopperSlabBehavior> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCopperBehavior> WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperBehavior> WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperSlabBehavior> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperStairsBehavior> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredDoubleCutCopperSlabBehavior> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCopperBehavior> WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperBehavior> WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperSlabBehavior> WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperStairsBehavior> WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredDoubleCutCopperSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWebBehavior> WEB_TYPE = BlockTypeBuilder
          .builder(BlockWebBehavior.class)
          .vanillaBlock(VanillaBlockId.WEB, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeepingVinesBehavior> WEEPING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockWeepingVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.WEEPING_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockWheatBehavior> WHEAT_TYPE = BlockTypeBuilder
          .builder(BlockWheatBehavior.class)
          .vanillaBlock(VanillaBlockId.WHEAT, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandleBehavior> WHITE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandleCakeBehavior> WHITE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCarpetBehavior> WHITE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteConcreteBehavior> WHITE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteGlazedTerracottaBehavior> WHITE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockWhiteGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteShulkerBoxBehavior> WHITE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockWhiteShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteWoolBehavior> WHITE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockWhiteWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWitherRoseBehavior> WITHER_ROSE_TYPE = BlockTypeBuilder
          .builder(BlockWitherRoseBehavior.class)
          .vanillaBlock(VanillaBlockId.WITHER_ROSE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodBehavior> WOOD_TYPE = BlockTypeBuilder
          .builder(BlockWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenButtonBehavior> WOODEN_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWoodenButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenDoorBehavior> WOODEN_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWoodenDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenPressurePlateBehavior> WOODEN_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWoodenPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenSlabBehavior> WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWoodenSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandleBehavior> YELLOW_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandleCakeBehavior> YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCarpetBehavior> YELLOW_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockYellowCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowConcreteBehavior> YELLOW_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockYellowConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowFlowerBehavior> YELLOW_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockYellowFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_FLOWER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowGlazedTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockYellowGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowShulkerBoxBehavior> YELLOW_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockYellowShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowWoolBehavior> YELLOW_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockYellowWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_WOOL, true)
          .addBasicComponents()
          .build();
}
