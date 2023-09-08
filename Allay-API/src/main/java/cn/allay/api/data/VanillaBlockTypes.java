package cn.allay.api.data;

import cn.allay.api.block.interfaces.BlockAcaciaButtonBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaDoorBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaFenceBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaLogBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaStairsBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockAcaciaWallSignBehavior;
import cn.allay.api.block.interfaces.BlockActivatorRailBehavior;
import cn.allay.api.block.interfaces.BlockAirBehavior;
import cn.allay.api.block.interfaces.BlockAllowBehavior;
import cn.allay.api.block.interfaces.BlockAmethystBlockBehavior;
import cn.allay.api.block.interfaces.BlockAmethystClusterBehavior;
import cn.allay.api.block.interfaces.BlockAncientDebrisBehavior;
import cn.allay.api.block.interfaces.BlockAndesiteStairsBehavior;
import cn.allay.api.block.interfaces.BlockAnvilBehavior;
import cn.allay.api.block.interfaces.BlockAzaleaBehavior;
import cn.allay.api.block.interfaces.BlockAzaleaLeavesBehavior;
import cn.allay.api.block.interfaces.BlockAzaleaLeavesFloweredBehavior;
import cn.allay.api.block.interfaces.BlockBambooBehavior;
import cn.allay.api.block.interfaces.BlockBambooBlockBehavior;
import cn.allay.api.block.interfaces.BlockBambooButtonBehavior;
import cn.allay.api.block.interfaces.BlockBambooDoorBehavior;
import cn.allay.api.block.interfaces.BlockBambooDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockBambooFenceBehavior;
import cn.allay.api.block.interfaces.BlockBambooFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockBambooHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockBambooMosaicBehavior;
import cn.allay.api.block.interfaces.BlockBambooMosaicDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockBambooMosaicSlabBehavior;
import cn.allay.api.block.interfaces.BlockBambooMosaicStairsBehavior;
import cn.allay.api.block.interfaces.BlockBambooPlanksBehavior;
import cn.allay.api.block.interfaces.BlockBambooPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockBambooSaplingBehavior;
import cn.allay.api.block.interfaces.BlockBambooSlabBehavior;
import cn.allay.api.block.interfaces.BlockBambooStairsBehavior;
import cn.allay.api.block.interfaces.BlockBambooStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockBambooTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockBambooWallSignBehavior;
import cn.allay.api.block.interfaces.BlockBarrelBehavior;
import cn.allay.api.block.interfaces.BlockBarrierBehavior;
import cn.allay.api.block.interfaces.BlockBasaltBehavior;
import cn.allay.api.block.interfaces.BlockBeaconBehavior;
import cn.allay.api.block.interfaces.BlockBedBehavior;
import cn.allay.api.block.interfaces.BlockBedrockBehavior;
import cn.allay.api.block.interfaces.BlockBeeNestBehavior;
import cn.allay.api.block.interfaces.BlockBeehiveBehavior;
import cn.allay.api.block.interfaces.BlockBeetrootBehavior;
import cn.allay.api.block.interfaces.BlockBellBehavior;
import cn.allay.api.block.interfaces.BlockBigDripleafBehavior;
import cn.allay.api.block.interfaces.BlockBirchButtonBehavior;
import cn.allay.api.block.interfaces.BlockBirchDoorBehavior;
import cn.allay.api.block.interfaces.BlockBirchFenceBehavior;
import cn.allay.api.block.interfaces.BlockBirchFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockBirchHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockBirchLogBehavior;
import cn.allay.api.block.interfaces.BlockBirchPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockBirchStairsBehavior;
import cn.allay.api.block.interfaces.BlockBirchStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockBirchTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockBirchWallSignBehavior;
import cn.allay.api.block.interfaces.BlockBlackCandleBehavior;
import cn.allay.api.block.interfaces.BlockBlackCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockBlackCarpetBehavior;
import cn.allay.api.block.interfaces.BlockBlackConcreteBehavior;
import cn.allay.api.block.interfaces.BlockBlackGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockBlackShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockBlackWoolBehavior;
import cn.allay.api.block.interfaces.BlockBlackstoneBehavior;
import cn.allay.api.block.interfaces.BlockBlackstoneDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockBlackstoneSlabBehavior;
import cn.allay.api.block.interfaces.BlockBlackstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockBlackstoneWallBehavior;
import cn.allay.api.block.interfaces.BlockBlastFurnaceBehavior;
import cn.allay.api.block.interfaces.BlockBlueCandleBehavior;
import cn.allay.api.block.interfaces.BlockBlueCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockBlueCarpetBehavior;
import cn.allay.api.block.interfaces.BlockBlueConcreteBehavior;
import cn.allay.api.block.interfaces.BlockBlueGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockBlueIceBehavior;
import cn.allay.api.block.interfaces.BlockBlueShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockBlueWoolBehavior;
import cn.allay.api.block.interfaces.BlockBoneBlockBehavior;
import cn.allay.api.block.interfaces.BlockBookshelfBehavior;
import cn.allay.api.block.interfaces.BlockBorderBlockBehavior;
import cn.allay.api.block.interfaces.BlockBrainCoralBehavior;
import cn.allay.api.block.interfaces.BlockBrewingStandBehavior;
import cn.allay.api.block.interfaces.BlockBrickBlockBehavior;
import cn.allay.api.block.interfaces.BlockBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockBrownCandleBehavior;
import cn.allay.api.block.interfaces.BlockBrownCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockBrownCarpetBehavior;
import cn.allay.api.block.interfaces.BlockBrownConcreteBehavior;
import cn.allay.api.block.interfaces.BlockBrownGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockBrownMushroomBehavior;
import cn.allay.api.block.interfaces.BlockBrownMushroomBlockBehavior;
import cn.allay.api.block.interfaces.BlockBrownShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockBrownWoolBehavior;
import cn.allay.api.block.interfaces.BlockBubbleColumnBehavior;
import cn.allay.api.block.interfaces.BlockBubbleCoralBehavior;
import cn.allay.api.block.interfaces.BlockBuddingAmethystBehavior;
import cn.allay.api.block.interfaces.BlockCactusBehavior;
import cn.allay.api.block.interfaces.BlockCakeBehavior;
import cn.allay.api.block.interfaces.BlockCalciteBehavior;
import cn.allay.api.block.interfaces.BlockCalibratedSculkSensorBehavior;
import cn.allay.api.block.interfaces.BlockCameraBehavior;
import cn.allay.api.block.interfaces.BlockCampfireBehavior;
import cn.allay.api.block.interfaces.BlockCandleBehavior;
import cn.allay.api.block.interfaces.BlockCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockCarrotsBehavior;
import cn.allay.api.block.interfaces.BlockCartographyTableBehavior;
import cn.allay.api.block.interfaces.BlockCarvedPumpkinBehavior;
import cn.allay.api.block.interfaces.BlockCauldronBehavior;
import cn.allay.api.block.interfaces.BlockCaveVinesBehavior;
import cn.allay.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import cn.allay.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import cn.allay.api.block.interfaces.BlockChainBehavior;
import cn.allay.api.block.interfaces.BlockChainCommandBlockBehavior;
import cn.allay.api.block.interfaces.BlockChemicalHeatBehavior;
import cn.allay.api.block.interfaces.BlockChemistryTableBehavior;
import cn.allay.api.block.interfaces.BlockCherryButtonBehavior;
import cn.allay.api.block.interfaces.BlockCherryDoorBehavior;
import cn.allay.api.block.interfaces.BlockCherryDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockCherryFenceBehavior;
import cn.allay.api.block.interfaces.BlockCherryFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockCherryHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockCherryLeavesBehavior;
import cn.allay.api.block.interfaces.BlockCherryLogBehavior;
import cn.allay.api.block.interfaces.BlockCherryPlanksBehavior;
import cn.allay.api.block.interfaces.BlockCherryPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockCherrySaplingBehavior;
import cn.allay.api.block.interfaces.BlockCherrySlabBehavior;
import cn.allay.api.block.interfaces.BlockCherryStairsBehavior;
import cn.allay.api.block.interfaces.BlockCherryStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockCherryTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockCherryWallSignBehavior;
import cn.allay.api.block.interfaces.BlockCherryWoodBehavior;
import cn.allay.api.block.interfaces.BlockChestBehavior;
import cn.allay.api.block.interfaces.BlockChiseledBookshelfBehavior;
import cn.allay.api.block.interfaces.BlockChiseledDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockChiseledNetherBricksBehavior;
import cn.allay.api.block.interfaces.BlockChiseledPolishedBlackstoneBehavior;
import cn.allay.api.block.interfaces.BlockChorusFlowerBehavior;
import cn.allay.api.block.interfaces.BlockChorusPlantBehavior;
import cn.allay.api.block.interfaces.BlockClayBehavior;
import cn.allay.api.block.interfaces.BlockClientRequestPlaceholderBlockBehavior;
import cn.allay.api.block.interfaces.BlockCoalBlockBehavior;
import cn.allay.api.block.interfaces.BlockCoalOreBehavior;
import cn.allay.api.block.interfaces.BlockCobbledDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockCobbledDeepslateDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockCobbledDeepslateSlabBehavior;
import cn.allay.api.block.interfaces.BlockCobbledDeepslateStairsBehavior;
import cn.allay.api.block.interfaces.BlockCobbledDeepslateWallBehavior;
import cn.allay.api.block.interfaces.BlockCobblestoneBehavior;
import cn.allay.api.block.interfaces.BlockCobblestoneWallBehavior;
import cn.allay.api.block.interfaces.BlockCocoaBehavior;
import cn.allay.api.block.interfaces.BlockColoredTorchBpBehavior;
import cn.allay.api.block.interfaces.BlockColoredTorchRgBehavior;
import cn.allay.api.block.interfaces.BlockCommandBlockBehavior;
import cn.allay.api.block.interfaces.BlockComposterBehavior;
import cn.allay.api.block.interfaces.BlockConcretePowderBehavior;
import cn.allay.api.block.interfaces.BlockConduitBehavior;
import cn.allay.api.block.interfaces.BlockCopperBlockBehavior;
import cn.allay.api.block.interfaces.BlockCopperOreBehavior;
import cn.allay.api.block.interfaces.BlockCoralBlockBehavior;
import cn.allay.api.block.interfaces.BlockCoralFanBehavior;
import cn.allay.api.block.interfaces.BlockCoralFanDeadBehavior;
import cn.allay.api.block.interfaces.BlockCoralFanHang2Behavior;
import cn.allay.api.block.interfaces.BlockCoralFanHang3Behavior;
import cn.allay.api.block.interfaces.BlockCoralFanHangBehavior;
import cn.allay.api.block.interfaces.BlockCrackedDeepslateBricksBehavior;
import cn.allay.api.block.interfaces.BlockCrackedDeepslateTilesBehavior;
import cn.allay.api.block.interfaces.BlockCrackedNetherBricksBehavior;
import cn.allay.api.block.interfaces.BlockCrackedPolishedBlackstoneBricksBehavior;
import cn.allay.api.block.interfaces.BlockCraftingTableBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonButtonBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonDoorBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonFenceBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonFungusBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonHyphaeBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonNyliumBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonPlanksBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonRootsBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonSlabBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonStairsBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonStemBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockCrimsonWallSignBehavior;
import cn.allay.api.block.interfaces.BlockCryingObsidianBehavior;
import cn.allay.api.block.interfaces.BlockCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockCyanCandleBehavior;
import cn.allay.api.block.interfaces.BlockCyanCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockCyanCarpetBehavior;
import cn.allay.api.block.interfaces.BlockCyanConcreteBehavior;
import cn.allay.api.block.interfaces.BlockCyanGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockCyanShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockCyanWoolBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakButtonBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakDoorBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakFenceBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakLogBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakStairsBehavior;
import cn.allay.api.block.interfaces.BlockDarkOakTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockDarkPrismarineStairsBehavior;
import cn.allay.api.block.interfaces.BlockDarkoakStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockDarkoakWallSignBehavior;
import cn.allay.api.block.interfaces.BlockDaylightDetectorBehavior;
import cn.allay.api.block.interfaces.BlockDaylightDetectorInvertedBehavior;
import cn.allay.api.block.interfaces.BlockDeadBrainCoralBehavior;
import cn.allay.api.block.interfaces.BlockDeadBubbleCoralBehavior;
import cn.allay.api.block.interfaces.BlockDeadFireCoralBehavior;
import cn.allay.api.block.interfaces.BlockDeadHornCoralBehavior;
import cn.allay.api.block.interfaces.BlockDeadTubeCoralBehavior;
import cn.allay.api.block.interfaces.BlockDeadbushBehavior;
import cn.allay.api.block.interfaces.BlockDecoratedPotBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBrickDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBrickSlabBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBrickWallBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateBricksBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateCoalOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateCopperOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateDiamondOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateEmeraldOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateGoldOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateIronOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateLapisOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateRedstoneOreBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateTileDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateTileSlabBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateTileStairsBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateTileWallBehavior;
import cn.allay.api.block.interfaces.BlockDeepslateTilesBehavior;
import cn.allay.api.block.interfaces.BlockDenyBehavior;
import cn.allay.api.block.interfaces.BlockDetectorRailBehavior;
import cn.allay.api.block.interfaces.BlockDiamondBlockBehavior;
import cn.allay.api.block.interfaces.BlockDiamondOreBehavior;
import cn.allay.api.block.interfaces.BlockDioriteStairsBehavior;
import cn.allay.api.block.interfaces.BlockDirtBehavior;
import cn.allay.api.block.interfaces.BlockDirtWithRootsBehavior;
import cn.allay.api.block.interfaces.BlockDispenserBehavior;
import cn.allay.api.block.interfaces.BlockDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockDoublePlantBehavior;
import cn.allay.api.block.interfaces.BlockDoubleStoneBlockSlab2Behavior;
import cn.allay.api.block.interfaces.BlockDoubleStoneBlockSlab3Behavior;
import cn.allay.api.block.interfaces.BlockDoubleStoneBlockSlab4Behavior;
import cn.allay.api.block.interfaces.BlockDoubleStoneBlockSlabBehavior;
import cn.allay.api.block.interfaces.BlockDoubleWoodenSlabBehavior;
import cn.allay.api.block.interfaces.BlockDragonEggBehavior;
import cn.allay.api.block.interfaces.BlockDriedKelpBlockBehavior;
import cn.allay.api.block.interfaces.BlockDripstoneBlockBehavior;
import cn.allay.api.block.interfaces.BlockDropperBehavior;
import cn.allay.api.block.interfaces.BlockElement0Behavior;
import cn.allay.api.block.interfaces.BlockElement100Behavior;
import cn.allay.api.block.interfaces.BlockElement101Behavior;
import cn.allay.api.block.interfaces.BlockElement102Behavior;
import cn.allay.api.block.interfaces.BlockElement103Behavior;
import cn.allay.api.block.interfaces.BlockElement104Behavior;
import cn.allay.api.block.interfaces.BlockElement105Behavior;
import cn.allay.api.block.interfaces.BlockElement106Behavior;
import cn.allay.api.block.interfaces.BlockElement107Behavior;
import cn.allay.api.block.interfaces.BlockElement108Behavior;
import cn.allay.api.block.interfaces.BlockElement109Behavior;
import cn.allay.api.block.interfaces.BlockElement10Behavior;
import cn.allay.api.block.interfaces.BlockElement110Behavior;
import cn.allay.api.block.interfaces.BlockElement111Behavior;
import cn.allay.api.block.interfaces.BlockElement112Behavior;
import cn.allay.api.block.interfaces.BlockElement113Behavior;
import cn.allay.api.block.interfaces.BlockElement114Behavior;
import cn.allay.api.block.interfaces.BlockElement115Behavior;
import cn.allay.api.block.interfaces.BlockElement116Behavior;
import cn.allay.api.block.interfaces.BlockElement117Behavior;
import cn.allay.api.block.interfaces.BlockElement118Behavior;
import cn.allay.api.block.interfaces.BlockElement11Behavior;
import cn.allay.api.block.interfaces.BlockElement12Behavior;
import cn.allay.api.block.interfaces.BlockElement13Behavior;
import cn.allay.api.block.interfaces.BlockElement14Behavior;
import cn.allay.api.block.interfaces.BlockElement15Behavior;
import cn.allay.api.block.interfaces.BlockElement16Behavior;
import cn.allay.api.block.interfaces.BlockElement17Behavior;
import cn.allay.api.block.interfaces.BlockElement18Behavior;
import cn.allay.api.block.interfaces.BlockElement19Behavior;
import cn.allay.api.block.interfaces.BlockElement1Behavior;
import cn.allay.api.block.interfaces.BlockElement20Behavior;
import cn.allay.api.block.interfaces.BlockElement21Behavior;
import cn.allay.api.block.interfaces.BlockElement22Behavior;
import cn.allay.api.block.interfaces.BlockElement23Behavior;
import cn.allay.api.block.interfaces.BlockElement24Behavior;
import cn.allay.api.block.interfaces.BlockElement25Behavior;
import cn.allay.api.block.interfaces.BlockElement26Behavior;
import cn.allay.api.block.interfaces.BlockElement27Behavior;
import cn.allay.api.block.interfaces.BlockElement28Behavior;
import cn.allay.api.block.interfaces.BlockElement29Behavior;
import cn.allay.api.block.interfaces.BlockElement2Behavior;
import cn.allay.api.block.interfaces.BlockElement30Behavior;
import cn.allay.api.block.interfaces.BlockElement31Behavior;
import cn.allay.api.block.interfaces.BlockElement32Behavior;
import cn.allay.api.block.interfaces.BlockElement33Behavior;
import cn.allay.api.block.interfaces.BlockElement34Behavior;
import cn.allay.api.block.interfaces.BlockElement35Behavior;
import cn.allay.api.block.interfaces.BlockElement36Behavior;
import cn.allay.api.block.interfaces.BlockElement37Behavior;
import cn.allay.api.block.interfaces.BlockElement38Behavior;
import cn.allay.api.block.interfaces.BlockElement39Behavior;
import cn.allay.api.block.interfaces.BlockElement3Behavior;
import cn.allay.api.block.interfaces.BlockElement40Behavior;
import cn.allay.api.block.interfaces.BlockElement41Behavior;
import cn.allay.api.block.interfaces.BlockElement42Behavior;
import cn.allay.api.block.interfaces.BlockElement43Behavior;
import cn.allay.api.block.interfaces.BlockElement44Behavior;
import cn.allay.api.block.interfaces.BlockElement45Behavior;
import cn.allay.api.block.interfaces.BlockElement46Behavior;
import cn.allay.api.block.interfaces.BlockElement47Behavior;
import cn.allay.api.block.interfaces.BlockElement48Behavior;
import cn.allay.api.block.interfaces.BlockElement49Behavior;
import cn.allay.api.block.interfaces.BlockElement4Behavior;
import cn.allay.api.block.interfaces.BlockElement50Behavior;
import cn.allay.api.block.interfaces.BlockElement51Behavior;
import cn.allay.api.block.interfaces.BlockElement52Behavior;
import cn.allay.api.block.interfaces.BlockElement53Behavior;
import cn.allay.api.block.interfaces.BlockElement54Behavior;
import cn.allay.api.block.interfaces.BlockElement55Behavior;
import cn.allay.api.block.interfaces.BlockElement56Behavior;
import cn.allay.api.block.interfaces.BlockElement57Behavior;
import cn.allay.api.block.interfaces.BlockElement58Behavior;
import cn.allay.api.block.interfaces.BlockElement59Behavior;
import cn.allay.api.block.interfaces.BlockElement5Behavior;
import cn.allay.api.block.interfaces.BlockElement60Behavior;
import cn.allay.api.block.interfaces.BlockElement61Behavior;
import cn.allay.api.block.interfaces.BlockElement62Behavior;
import cn.allay.api.block.interfaces.BlockElement63Behavior;
import cn.allay.api.block.interfaces.BlockElement64Behavior;
import cn.allay.api.block.interfaces.BlockElement65Behavior;
import cn.allay.api.block.interfaces.BlockElement66Behavior;
import cn.allay.api.block.interfaces.BlockElement67Behavior;
import cn.allay.api.block.interfaces.BlockElement68Behavior;
import cn.allay.api.block.interfaces.BlockElement69Behavior;
import cn.allay.api.block.interfaces.BlockElement6Behavior;
import cn.allay.api.block.interfaces.BlockElement70Behavior;
import cn.allay.api.block.interfaces.BlockElement71Behavior;
import cn.allay.api.block.interfaces.BlockElement72Behavior;
import cn.allay.api.block.interfaces.BlockElement73Behavior;
import cn.allay.api.block.interfaces.BlockElement74Behavior;
import cn.allay.api.block.interfaces.BlockElement75Behavior;
import cn.allay.api.block.interfaces.BlockElement76Behavior;
import cn.allay.api.block.interfaces.BlockElement77Behavior;
import cn.allay.api.block.interfaces.BlockElement78Behavior;
import cn.allay.api.block.interfaces.BlockElement79Behavior;
import cn.allay.api.block.interfaces.BlockElement7Behavior;
import cn.allay.api.block.interfaces.BlockElement80Behavior;
import cn.allay.api.block.interfaces.BlockElement81Behavior;
import cn.allay.api.block.interfaces.BlockElement82Behavior;
import cn.allay.api.block.interfaces.BlockElement83Behavior;
import cn.allay.api.block.interfaces.BlockElement84Behavior;
import cn.allay.api.block.interfaces.BlockElement85Behavior;
import cn.allay.api.block.interfaces.BlockElement86Behavior;
import cn.allay.api.block.interfaces.BlockElement87Behavior;
import cn.allay.api.block.interfaces.BlockElement88Behavior;
import cn.allay.api.block.interfaces.BlockElement89Behavior;
import cn.allay.api.block.interfaces.BlockElement8Behavior;
import cn.allay.api.block.interfaces.BlockElement90Behavior;
import cn.allay.api.block.interfaces.BlockElement91Behavior;
import cn.allay.api.block.interfaces.BlockElement92Behavior;
import cn.allay.api.block.interfaces.BlockElement93Behavior;
import cn.allay.api.block.interfaces.BlockElement94Behavior;
import cn.allay.api.block.interfaces.BlockElement95Behavior;
import cn.allay.api.block.interfaces.BlockElement96Behavior;
import cn.allay.api.block.interfaces.BlockElement97Behavior;
import cn.allay.api.block.interfaces.BlockElement98Behavior;
import cn.allay.api.block.interfaces.BlockElement99Behavior;
import cn.allay.api.block.interfaces.BlockElement9Behavior;
import cn.allay.api.block.interfaces.BlockEmeraldBlockBehavior;
import cn.allay.api.block.interfaces.BlockEmeraldOreBehavior;
import cn.allay.api.block.interfaces.BlockEnchantingTableBehavior;
import cn.allay.api.block.interfaces.BlockEndBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockEndBricksBehavior;
import cn.allay.api.block.interfaces.BlockEndGatewayBehavior;
import cn.allay.api.block.interfaces.BlockEndPortalBehavior;
import cn.allay.api.block.interfaces.BlockEndPortalFrameBehavior;
import cn.allay.api.block.interfaces.BlockEndRodBehavior;
import cn.allay.api.block.interfaces.BlockEndStoneBehavior;
import cn.allay.api.block.interfaces.BlockEnderChestBehavior;
import cn.allay.api.block.interfaces.BlockExposedCopperBehavior;
import cn.allay.api.block.interfaces.BlockExposedCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockExposedCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockExposedCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockExposedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockFarmlandBehavior;
import cn.allay.api.block.interfaces.BlockFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockFireBehavior;
import cn.allay.api.block.interfaces.BlockFireCoralBehavior;
import cn.allay.api.block.interfaces.BlockFletchingTableBehavior;
import cn.allay.api.block.interfaces.BlockFlowerPotBehavior;
import cn.allay.api.block.interfaces.BlockFloweringAzaleaBehavior;
import cn.allay.api.block.interfaces.BlockFlowingLavaBehavior;
import cn.allay.api.block.interfaces.BlockFlowingWaterBehavior;
import cn.allay.api.block.interfaces.BlockFrameBehavior;
import cn.allay.api.block.interfaces.BlockFrogSpawnBehavior;
import cn.allay.api.block.interfaces.BlockFrostedIceBehavior;
import cn.allay.api.block.interfaces.BlockFurnaceBehavior;
import cn.allay.api.block.interfaces.BlockGildedBlackstoneBehavior;
import cn.allay.api.block.interfaces.BlockGlassBehavior;
import cn.allay.api.block.interfaces.BlockGlassPaneBehavior;
import cn.allay.api.block.interfaces.BlockGlowFrameBehavior;
import cn.allay.api.block.interfaces.BlockGlowLichenBehavior;
import cn.allay.api.block.interfaces.BlockGlowingobsidianBehavior;
import cn.allay.api.block.interfaces.BlockGlowstoneBehavior;
import cn.allay.api.block.interfaces.BlockGoldBlockBehavior;
import cn.allay.api.block.interfaces.BlockGoldOreBehavior;
import cn.allay.api.block.interfaces.BlockGoldenRailBehavior;
import cn.allay.api.block.interfaces.BlockGraniteStairsBehavior;
import cn.allay.api.block.interfaces.BlockGrassBehavior;
import cn.allay.api.block.interfaces.BlockGrassPathBehavior;
import cn.allay.api.block.interfaces.BlockGravelBehavior;
import cn.allay.api.block.interfaces.BlockGrayCandleBehavior;
import cn.allay.api.block.interfaces.BlockGrayCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockGrayCarpetBehavior;
import cn.allay.api.block.interfaces.BlockGrayConcreteBehavior;
import cn.allay.api.block.interfaces.BlockGrayGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockGrayShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockGrayWoolBehavior;
import cn.allay.api.block.interfaces.BlockGreenCandleBehavior;
import cn.allay.api.block.interfaces.BlockGreenCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockGreenCarpetBehavior;
import cn.allay.api.block.interfaces.BlockGreenConcreteBehavior;
import cn.allay.api.block.interfaces.BlockGreenGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockGreenShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockGreenWoolBehavior;
import cn.allay.api.block.interfaces.BlockGrindstoneBehavior;
import cn.allay.api.block.interfaces.BlockHangingRootsBehavior;
import cn.allay.api.block.interfaces.BlockHardGlassBehavior;
import cn.allay.api.block.interfaces.BlockHardGlassPaneBehavior;
import cn.allay.api.block.interfaces.BlockHardStainedGlassBehavior;
import cn.allay.api.block.interfaces.BlockHardStainedGlassPaneBehavior;
import cn.allay.api.block.interfaces.BlockHardenedClayBehavior;
import cn.allay.api.block.interfaces.BlockHayBlockBehavior;
import cn.allay.api.block.interfaces.BlockHeavyWeightedPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockHoneyBlockBehavior;
import cn.allay.api.block.interfaces.BlockHoneycombBlockBehavior;
import cn.allay.api.block.interfaces.BlockHopperBehavior;
import cn.allay.api.block.interfaces.BlockHornCoralBehavior;
import cn.allay.api.block.interfaces.BlockIceBehavior;
import cn.allay.api.block.interfaces.BlockInfestedDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockInfoUpdate2Behavior;
import cn.allay.api.block.interfaces.BlockInfoUpdateBehavior;
import cn.allay.api.block.interfaces.BlockInvisibleBedrockBehavior;
import cn.allay.api.block.interfaces.BlockIronBarsBehavior;
import cn.allay.api.block.interfaces.BlockIronBlockBehavior;
import cn.allay.api.block.interfaces.BlockIronDoorBehavior;
import cn.allay.api.block.interfaces.BlockIronOreBehavior;
import cn.allay.api.block.interfaces.BlockIronTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockJigsawBehavior;
import cn.allay.api.block.interfaces.BlockJukeboxBehavior;
import cn.allay.api.block.interfaces.BlockJungleButtonBehavior;
import cn.allay.api.block.interfaces.BlockJungleDoorBehavior;
import cn.allay.api.block.interfaces.BlockJungleFenceBehavior;
import cn.allay.api.block.interfaces.BlockJungleFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockJungleHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockJungleLogBehavior;
import cn.allay.api.block.interfaces.BlockJunglePressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockJungleStairsBehavior;
import cn.allay.api.block.interfaces.BlockJungleStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockJungleTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockJungleWallSignBehavior;
import cn.allay.api.block.interfaces.BlockKelpBehavior;
import cn.allay.api.block.interfaces.BlockLadderBehavior;
import cn.allay.api.block.interfaces.BlockLanternBehavior;
import cn.allay.api.block.interfaces.BlockLapisBlockBehavior;
import cn.allay.api.block.interfaces.BlockLapisOreBehavior;
import cn.allay.api.block.interfaces.BlockLargeAmethystBudBehavior;
import cn.allay.api.block.interfaces.BlockLavaBehavior;
import cn.allay.api.block.interfaces.BlockLeaves2Behavior;
import cn.allay.api.block.interfaces.BlockLeavesBehavior;
import cn.allay.api.block.interfaces.BlockLecternBehavior;
import cn.allay.api.block.interfaces.BlockLeverBehavior;
import cn.allay.api.block.interfaces.BlockLightBlockBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueCandleBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueCarpetBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueConcreteBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockLightBlueWoolBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayCandleBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayCarpetBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayConcreteBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockLightGrayWoolBehavior;
import cn.allay.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockLightningRodBehavior;
import cn.allay.api.block.interfaces.BlockLimeCandleBehavior;
import cn.allay.api.block.interfaces.BlockLimeCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockLimeCarpetBehavior;
import cn.allay.api.block.interfaces.BlockLimeConcreteBehavior;
import cn.allay.api.block.interfaces.BlockLimeGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockLimeShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockLimeWoolBehavior;
import cn.allay.api.block.interfaces.BlockLitBlastFurnaceBehavior;
import cn.allay.api.block.interfaces.BlockLitDeepslateRedstoneOreBehavior;
import cn.allay.api.block.interfaces.BlockLitFurnaceBehavior;
import cn.allay.api.block.interfaces.BlockLitPumpkinBehavior;
import cn.allay.api.block.interfaces.BlockLitRedstoneLampBehavior;
import cn.allay.api.block.interfaces.BlockLitRedstoneOreBehavior;
import cn.allay.api.block.interfaces.BlockLitSmokerBehavior;
import cn.allay.api.block.interfaces.BlockLodestoneBehavior;
import cn.allay.api.block.interfaces.BlockLoomBehavior;
import cn.allay.api.block.interfaces.BlockMagentaCandleBehavior;
import cn.allay.api.block.interfaces.BlockMagentaCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockMagentaCarpetBehavior;
import cn.allay.api.block.interfaces.BlockMagentaConcreteBehavior;
import cn.allay.api.block.interfaces.BlockMagentaGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockMagentaShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockMagentaWoolBehavior;
import cn.allay.api.block.interfaces.BlockMagmaBehavior;
import cn.allay.api.block.interfaces.BlockMangroveButtonBehavior;
import cn.allay.api.block.interfaces.BlockMangroveDoorBehavior;
import cn.allay.api.block.interfaces.BlockMangroveDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockMangroveFenceBehavior;
import cn.allay.api.block.interfaces.BlockMangroveFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockMangroveHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockMangroveLeavesBehavior;
import cn.allay.api.block.interfaces.BlockMangroveLogBehavior;
import cn.allay.api.block.interfaces.BlockMangrovePlanksBehavior;
import cn.allay.api.block.interfaces.BlockMangrovePressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockMangrovePropaguleBehavior;
import cn.allay.api.block.interfaces.BlockMangroveRootsBehavior;
import cn.allay.api.block.interfaces.BlockMangroveSlabBehavior;
import cn.allay.api.block.interfaces.BlockMangroveStairsBehavior;
import cn.allay.api.block.interfaces.BlockMangroveStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockMangroveTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockMangroveWallSignBehavior;
import cn.allay.api.block.interfaces.BlockMangroveWoodBehavior;
import cn.allay.api.block.interfaces.BlockMediumAmethystBudBehavior;
import cn.allay.api.block.interfaces.BlockMelonBlockBehavior;
import cn.allay.api.block.interfaces.BlockMelonStemBehavior;
import cn.allay.api.block.interfaces.BlockMobSpawnerBehavior;
import cn.allay.api.block.interfaces.BlockMonsterEggBehavior;
import cn.allay.api.block.interfaces.BlockMossBlockBehavior;
import cn.allay.api.block.interfaces.BlockMossCarpetBehavior;
import cn.allay.api.block.interfaces.BlockMossyCobblestoneBehavior;
import cn.allay.api.block.interfaces.BlockMossyCobblestoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockMossyStoneBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockMovingBlockBehavior;
import cn.allay.api.block.interfaces.BlockMudBehavior;
import cn.allay.api.block.interfaces.BlockMudBrickDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockMudBrickSlabBehavior;
import cn.allay.api.block.interfaces.BlockMudBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockMudBrickWallBehavior;
import cn.allay.api.block.interfaces.BlockMudBricksBehavior;
import cn.allay.api.block.interfaces.BlockMuddyMangroveRootsBehavior;
import cn.allay.api.block.interfaces.BlockMyceliumBehavior;
import cn.allay.api.block.interfaces.BlockNetherBrickBehavior;
import cn.allay.api.block.interfaces.BlockNetherBrickFenceBehavior;
import cn.allay.api.block.interfaces.BlockNetherBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockNetherGoldOreBehavior;
import cn.allay.api.block.interfaces.BlockNetherSproutsBehavior;
import cn.allay.api.block.interfaces.BlockNetherWartBehavior;
import cn.allay.api.block.interfaces.BlockNetherWartBlockBehavior;
import cn.allay.api.block.interfaces.BlockNetheriteBlockBehavior;
import cn.allay.api.block.interfaces.BlockNetherrackBehavior;
import cn.allay.api.block.interfaces.BlockNetherreactorBehavior;
import cn.allay.api.block.interfaces.BlockNormalStoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockNoteblockBehavior;
import cn.allay.api.block.interfaces.BlockOakFenceBehavior;
import cn.allay.api.block.interfaces.BlockOakHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockOakLogBehavior;
import cn.allay.api.block.interfaces.BlockOakStairsBehavior;
import cn.allay.api.block.interfaces.BlockObserverBehavior;
import cn.allay.api.block.interfaces.BlockObsidianBehavior;
import cn.allay.api.block.interfaces.BlockOchreFroglightBehavior;
import cn.allay.api.block.interfaces.BlockOrangeCandleBehavior;
import cn.allay.api.block.interfaces.BlockOrangeCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockOrangeCarpetBehavior;
import cn.allay.api.block.interfaces.BlockOrangeConcreteBehavior;
import cn.allay.api.block.interfaces.BlockOrangeGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockOrangeShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockOrangeWoolBehavior;
import cn.allay.api.block.interfaces.BlockOxidizedCopperBehavior;
import cn.allay.api.block.interfaces.BlockOxidizedCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockOxidizedCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockOxidizedCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockOxidizedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockPackedIceBehavior;
import cn.allay.api.block.interfaces.BlockPackedMudBehavior;
import cn.allay.api.block.interfaces.BlockPearlescentFroglightBehavior;
import cn.allay.api.block.interfaces.BlockPinkCandleBehavior;
import cn.allay.api.block.interfaces.BlockPinkCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockPinkCarpetBehavior;
import cn.allay.api.block.interfaces.BlockPinkConcreteBehavior;
import cn.allay.api.block.interfaces.BlockPinkGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockPinkPetalsBehavior;
import cn.allay.api.block.interfaces.BlockPinkShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockPinkWoolBehavior;
import cn.allay.api.block.interfaces.BlockPistonArmCollisionBehavior;
import cn.allay.api.block.interfaces.BlockPistonBehavior;
import cn.allay.api.block.interfaces.BlockPitcherCropBehavior;
import cn.allay.api.block.interfaces.BlockPitcherPlantBehavior;
import cn.allay.api.block.interfaces.BlockPlanksBehavior;
import cn.allay.api.block.interfaces.BlockPodzolBehavior;
import cn.allay.api.block.interfaces.BlockPointedDripstoneBehavior;
import cn.allay.api.block.interfaces.BlockPolishedAndesiteStairsBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBasaltBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBrickDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBrickSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBrickWallBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneBricksBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneButtonBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstonePressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockPolishedBlackstoneWallBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDeepslateDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDeepslateSlabBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDeepslateStairsBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDeepslateWallBehavior;
import cn.allay.api.block.interfaces.BlockPolishedDioriteStairsBehavior;
import cn.allay.api.block.interfaces.BlockPolishedGraniteStairsBehavior;
import cn.allay.api.block.interfaces.BlockPortalBehavior;
import cn.allay.api.block.interfaces.BlockPotatoesBehavior;
import cn.allay.api.block.interfaces.BlockPowderSnowBehavior;
import cn.allay.api.block.interfaces.BlockPoweredComparatorBehavior;
import cn.allay.api.block.interfaces.BlockPoweredRepeaterBehavior;
import cn.allay.api.block.interfaces.BlockPrismarineBehavior;
import cn.allay.api.block.interfaces.BlockPrismarineBricksStairsBehavior;
import cn.allay.api.block.interfaces.BlockPrismarineStairsBehavior;
import cn.allay.api.block.interfaces.BlockPumpkinBehavior;
import cn.allay.api.block.interfaces.BlockPumpkinStemBehavior;
import cn.allay.api.block.interfaces.BlockPurpleCandleBehavior;
import cn.allay.api.block.interfaces.BlockPurpleCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockPurpleCarpetBehavior;
import cn.allay.api.block.interfaces.BlockPurpleConcreteBehavior;
import cn.allay.api.block.interfaces.BlockPurpleGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockPurpleShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockPurpleWoolBehavior;
import cn.allay.api.block.interfaces.BlockPurpurBlockBehavior;
import cn.allay.api.block.interfaces.BlockPurpurStairsBehavior;
import cn.allay.api.block.interfaces.BlockQuartzBlockBehavior;
import cn.allay.api.block.interfaces.BlockQuartzBricksBehavior;
import cn.allay.api.block.interfaces.BlockQuartzOreBehavior;
import cn.allay.api.block.interfaces.BlockQuartzStairsBehavior;
import cn.allay.api.block.interfaces.BlockRailBehavior;
import cn.allay.api.block.interfaces.BlockRawCopperBlockBehavior;
import cn.allay.api.block.interfaces.BlockRawGoldBlockBehavior;
import cn.allay.api.block.interfaces.BlockRawIronBlockBehavior;
import cn.allay.api.block.interfaces.BlockRedCandleBehavior;
import cn.allay.api.block.interfaces.BlockRedCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockRedCarpetBehavior;
import cn.allay.api.block.interfaces.BlockRedConcreteBehavior;
import cn.allay.api.block.interfaces.BlockRedFlowerBehavior;
import cn.allay.api.block.interfaces.BlockRedGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockRedMushroomBehavior;
import cn.allay.api.block.interfaces.BlockRedMushroomBlockBehavior;
import cn.allay.api.block.interfaces.BlockRedNetherBrickBehavior;
import cn.allay.api.block.interfaces.BlockRedNetherBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockRedSandstoneBehavior;
import cn.allay.api.block.interfaces.BlockRedSandstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockRedShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockRedWoolBehavior;
import cn.allay.api.block.interfaces.BlockRedstoneBlockBehavior;
import cn.allay.api.block.interfaces.BlockRedstoneLampBehavior;
import cn.allay.api.block.interfaces.BlockRedstoneOreBehavior;
import cn.allay.api.block.interfaces.BlockRedstoneTorchBehavior;
import cn.allay.api.block.interfaces.BlockRedstoneWireBehavior;
import cn.allay.api.block.interfaces.BlockReedsBehavior;
import cn.allay.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import cn.allay.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import cn.allay.api.block.interfaces.BlockReserved6Behavior;
import cn.allay.api.block.interfaces.BlockRespawnAnchorBehavior;
import cn.allay.api.block.interfaces.BlockSandBehavior;
import cn.allay.api.block.interfaces.BlockSandstoneBehavior;
import cn.allay.api.block.interfaces.BlockSandstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockSaplingBehavior;
import cn.allay.api.block.interfaces.BlockScaffoldingBehavior;
import cn.allay.api.block.interfaces.BlockSculkBehavior;
import cn.allay.api.block.interfaces.BlockSculkCatalystBehavior;
import cn.allay.api.block.interfaces.BlockSculkSensorBehavior;
import cn.allay.api.block.interfaces.BlockSculkShriekerBehavior;
import cn.allay.api.block.interfaces.BlockSculkVeinBehavior;
import cn.allay.api.block.interfaces.BlockSeaLanternBehavior;
import cn.allay.api.block.interfaces.BlockSeaPickleBehavior;
import cn.allay.api.block.interfaces.BlockSeagrassBehavior;
import cn.allay.api.block.interfaces.BlockShroomlightBehavior;
import cn.allay.api.block.interfaces.BlockSilverGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockSkullBehavior;
import cn.allay.api.block.interfaces.BlockSlimeBehavior;
import cn.allay.api.block.interfaces.BlockSmallAmethystBudBehavior;
import cn.allay.api.block.interfaces.BlockSmallDripleafBlockBehavior;
import cn.allay.api.block.interfaces.BlockSmithingTableBehavior;
import cn.allay.api.block.interfaces.BlockSmokerBehavior;
import cn.allay.api.block.interfaces.BlockSmoothBasaltBehavior;
import cn.allay.api.block.interfaces.BlockSmoothQuartzStairsBehavior;
import cn.allay.api.block.interfaces.BlockSmoothRedSandstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockSmoothSandstoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockSmoothStoneBehavior;
import cn.allay.api.block.interfaces.BlockSnifferEggBehavior;
import cn.allay.api.block.interfaces.BlockSnowBehavior;
import cn.allay.api.block.interfaces.BlockSnowLayerBehavior;
import cn.allay.api.block.interfaces.BlockSoulCampfireBehavior;
import cn.allay.api.block.interfaces.BlockSoulFireBehavior;
import cn.allay.api.block.interfaces.BlockSoulLanternBehavior;
import cn.allay.api.block.interfaces.BlockSoulSandBehavior;
import cn.allay.api.block.interfaces.BlockSoulSoilBehavior;
import cn.allay.api.block.interfaces.BlockSoulTorchBehavior;
import cn.allay.api.block.interfaces.BlockSpongeBehavior;
import cn.allay.api.block.interfaces.BlockSporeBlossomBehavior;
import cn.allay.api.block.interfaces.BlockSpruceButtonBehavior;
import cn.allay.api.block.interfaces.BlockSpruceDoorBehavior;
import cn.allay.api.block.interfaces.BlockSpruceFenceBehavior;
import cn.allay.api.block.interfaces.BlockSpruceFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockSpruceHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockSpruceLogBehavior;
import cn.allay.api.block.interfaces.BlockSprucePressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockSpruceStairsBehavior;
import cn.allay.api.block.interfaces.BlockSpruceStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockSpruceTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockSpruceWallSignBehavior;
import cn.allay.api.block.interfaces.BlockStainedGlassBehavior;
import cn.allay.api.block.interfaces.BlockStainedGlassPaneBehavior;
import cn.allay.api.block.interfaces.BlockStainedHardenedClayBehavior;
import cn.allay.api.block.interfaces.BlockStandingBannerBehavior;
import cn.allay.api.block.interfaces.BlockStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockStickyPistonArmCollisionBehavior;
import cn.allay.api.block.interfaces.BlockStickyPistonBehavior;
import cn.allay.api.block.interfaces.BlockStoneBehavior;
import cn.allay.api.block.interfaces.BlockStoneBlockSlab2Behavior;
import cn.allay.api.block.interfaces.BlockStoneBlockSlab3Behavior;
import cn.allay.api.block.interfaces.BlockStoneBlockSlab4Behavior;
import cn.allay.api.block.interfaces.BlockStoneBlockSlabBehavior;
import cn.allay.api.block.interfaces.BlockStoneBrickStairsBehavior;
import cn.allay.api.block.interfaces.BlockStoneButtonBehavior;
import cn.allay.api.block.interfaces.BlockStonePressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockStoneStairsBehavior;
import cn.allay.api.block.interfaces.BlockStonebrickBehavior;
import cn.allay.api.block.interfaces.BlockStonecutterBehavior;
import cn.allay.api.block.interfaces.BlockStonecutterBlockBehavior;
import cn.allay.api.block.interfaces.BlockStrippedAcaciaLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedBambooBlockBehavior;
import cn.allay.api.block.interfaces.BlockStrippedBirchLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedCherryLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedCherryWoodBehavior;
import cn.allay.api.block.interfaces.BlockStrippedCrimsonHyphaeBehavior;
import cn.allay.api.block.interfaces.BlockStrippedCrimsonStemBehavior;
import cn.allay.api.block.interfaces.BlockStrippedDarkOakLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedJungleLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedMangroveLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedMangroveWoodBehavior;
import cn.allay.api.block.interfaces.BlockStrippedOakLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedSpruceLogBehavior;
import cn.allay.api.block.interfaces.BlockStrippedWarpedHyphaeBehavior;
import cn.allay.api.block.interfaces.BlockStrippedWarpedStemBehavior;
import cn.allay.api.block.interfaces.BlockStructureBlockBehavior;
import cn.allay.api.block.interfaces.BlockStructureVoidBehavior;
import cn.allay.api.block.interfaces.BlockSuspiciousGravelBehavior;
import cn.allay.api.block.interfaces.BlockSuspiciousSandBehavior;
import cn.allay.api.block.interfaces.BlockSweetBerryBushBehavior;
import cn.allay.api.block.interfaces.BlockTallgrassBehavior;
import cn.allay.api.block.interfaces.BlockTargetBehavior;
import cn.allay.api.block.interfaces.BlockTintedGlassBehavior;
import cn.allay.api.block.interfaces.BlockTntBehavior;
import cn.allay.api.block.interfaces.BlockTorchBehavior;
import cn.allay.api.block.interfaces.BlockTorchflowerBehavior;
import cn.allay.api.block.interfaces.BlockTorchflowerCropBehavior;
import cn.allay.api.block.interfaces.BlockTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockTrappedChestBehavior;
import cn.allay.api.block.interfaces.BlockTripWireBehavior;
import cn.allay.api.block.interfaces.BlockTripwireHookBehavior;
import cn.allay.api.block.interfaces.BlockTubeCoralBehavior;
import cn.allay.api.block.interfaces.BlockTuffBehavior;
import cn.allay.api.block.interfaces.BlockTurtleEggBehavior;
import cn.allay.api.block.interfaces.BlockTwistingVinesBehavior;
import cn.allay.api.block.interfaces.BlockUnderwaterTorchBehavior;
import cn.allay.api.block.interfaces.BlockUndyedShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockUnknownBehavior;
import cn.allay.api.block.interfaces.BlockUnlitRedstoneTorchBehavior;
import cn.allay.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import cn.allay.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import cn.allay.api.block.interfaces.BlockVerdantFroglightBehavior;
import cn.allay.api.block.interfaces.BlockVineBehavior;
import cn.allay.api.block.interfaces.BlockWallBannerBehavior;
import cn.allay.api.block.interfaces.BlockWallSignBehavior;
import cn.allay.api.block.interfaces.BlockWarpedButtonBehavior;
import cn.allay.api.block.interfaces.BlockWarpedDoorBehavior;
import cn.allay.api.block.interfaces.BlockWarpedDoubleSlabBehavior;
import cn.allay.api.block.interfaces.BlockWarpedFenceBehavior;
import cn.allay.api.block.interfaces.BlockWarpedFenceGateBehavior;
import cn.allay.api.block.interfaces.BlockWarpedFungusBehavior;
import cn.allay.api.block.interfaces.BlockWarpedHangingSignBehavior;
import cn.allay.api.block.interfaces.BlockWarpedHyphaeBehavior;
import cn.allay.api.block.interfaces.BlockWarpedNyliumBehavior;
import cn.allay.api.block.interfaces.BlockWarpedPlanksBehavior;
import cn.allay.api.block.interfaces.BlockWarpedPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockWarpedRootsBehavior;
import cn.allay.api.block.interfaces.BlockWarpedSlabBehavior;
import cn.allay.api.block.interfaces.BlockWarpedStairsBehavior;
import cn.allay.api.block.interfaces.BlockWarpedStandingSignBehavior;
import cn.allay.api.block.interfaces.BlockWarpedStemBehavior;
import cn.allay.api.block.interfaces.BlockWarpedTrapdoorBehavior;
import cn.allay.api.block.interfaces.BlockWarpedWallSignBehavior;
import cn.allay.api.block.interfaces.BlockWarpedWartBlockBehavior;
import cn.allay.api.block.interfaces.BlockWaterBehavior;
import cn.allay.api.block.interfaces.BlockWaterlilyBehavior;
import cn.allay.api.block.interfaces.BlockWaxedCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockWaxedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedExposedCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedExposedCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedExposedCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedExposedCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockWaxedExposedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedOxidizedCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedOxidizedCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedOxidizedCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedOxidizedCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockWaxedOxidizedDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedWeatheredCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedWeatheredCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockWaxedWeatheredCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWaxedWeatheredCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockWaxedWeatheredDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWeatheredCopperBehavior;
import cn.allay.api.block.interfaces.BlockWeatheredCutCopperBehavior;
import cn.allay.api.block.interfaces.BlockWeatheredCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWeatheredCutCopperStairsBehavior;
import cn.allay.api.block.interfaces.BlockWeatheredDoubleCutCopperSlabBehavior;
import cn.allay.api.block.interfaces.BlockWebBehavior;
import cn.allay.api.block.interfaces.BlockWeepingVinesBehavior;
import cn.allay.api.block.interfaces.BlockWheatBehavior;
import cn.allay.api.block.interfaces.BlockWhiteCandleBehavior;
import cn.allay.api.block.interfaces.BlockWhiteCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockWhiteCarpetBehavior;
import cn.allay.api.block.interfaces.BlockWhiteConcreteBehavior;
import cn.allay.api.block.interfaces.BlockWhiteGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockWhiteShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockWhiteWoolBehavior;
import cn.allay.api.block.interfaces.BlockWitherRoseBehavior;
import cn.allay.api.block.interfaces.BlockWoodBehavior;
import cn.allay.api.block.interfaces.BlockWoodenButtonBehavior;
import cn.allay.api.block.interfaces.BlockWoodenDoorBehavior;
import cn.allay.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import cn.allay.api.block.interfaces.BlockWoodenSlabBehavior;
import cn.allay.api.block.interfaces.BlockYellowCandleBehavior;
import cn.allay.api.block.interfaces.BlockYellowCandleCakeBehavior;
import cn.allay.api.block.interfaces.BlockYellowCarpetBehavior;
import cn.allay.api.block.interfaces.BlockYellowConcreteBehavior;
import cn.allay.api.block.interfaces.BlockYellowFlowerBehavior;
import cn.allay.api.block.interfaces.BlockYellowGlazedTerracottaBehavior;
import cn.allay.api.block.interfaces.BlockYellowShulkerBoxBehavior;
import cn.allay.api.block.interfaces.BlockYellowWoolBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;

/**
 * Allay Project <p>
 * @author daoge_cmd
 */
public interface VanillaBlockTypes {
  BlockType<BlockAcaciaButtonBehavior> ACACIA_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaDoorBehavior> ACACIA_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFenceBehavior> ACACIA_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFenceGateBehavior> ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaHangingSignBehavior> ACACIA_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaLogBehavior> ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaLogBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaPressurePlateBehavior> ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStairsBehavior> ACACIA_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStandingSignBehavior> ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaTrapdoorBehavior> ACACIA_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaWallSignBehavior> ACACIA_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockActivatorRailBehavior> ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockActivatorRailBehavior.class)
          .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockAirBehavior> AIR_TYPE = BlockTypeBuilder
          .builder(BlockAirBehavior.class)
          .vanillaBlock(VanillaBlockId.AIR)
          .addBasicComponents()
          .build();

  BlockType<BlockAllowBehavior> ALLOW_TYPE = BlockTypeBuilder
          .builder(BlockAllowBehavior.class)
          .vanillaBlock(VanillaBlockId.ALLOW)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystBlockBehavior> AMETHYST_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockAmethystBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
          .builder(BlockAmethystClusterBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAncientDebrisBehavior> ANCIENT_DEBRIS_TYPE = BlockTypeBuilder
          .builder(BlockAncientDebrisBehavior.class)
          .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS)
          .addBasicComponents()
          .build();

  BlockType<BlockAndesiteStairsBehavior> ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAndesiteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAnvilBehavior> ANVIL_TYPE = BlockTypeBuilder
          .builder(BlockAnvilBehavior.class)
          .vanillaBlock(VanillaBlockId.ANVIL)
          .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaBehavior> AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeavesBehavior> AZALEA_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeavesFloweredBehavior> AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesFloweredBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooBehavior> BAMBOO_TYPE = BlockTypeBuilder
          .builder(BlockBambooBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO)
          .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooBlockBehavior> BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBambooBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooButtonBehavior> BAMBOO_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBambooButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoorBehavior> BAMBOO_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoubleSlabBehavior> BAMBOO_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFenceBehavior> BAMBOO_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFenceGateBehavior> BAMBOO_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooHangingSignBehavior> BAMBOO_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicBehavior> BAMBOO_MOSAIC_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicDoubleSlabBehavior> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicSlabBehavior> BAMBOO_MOSAIC_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicStairsBehavior> BAMBOO_MOSAIC_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPlanksBehavior> BAMBOO_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockBambooPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PLANKS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSaplingBehavior> BAMBOO_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockBambooSaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING)
          .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSlabBehavior> BAMBOO_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStairsBehavior> BAMBOO_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStandingSignBehavior> BAMBOO_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooTrapdoorBehavior> BAMBOO_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooWallSignBehavior> BAMBOO_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
          .builder(BlockBarrelBehavior.class)
          .vanillaBlock(VanillaBlockId.BARREL)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrierBehavior> BARRIER_TYPE = BlockTypeBuilder
          .builder(BlockBarrierBehavior.class)
          .vanillaBlock(VanillaBlockId.BARRIER)
          .addBasicComponents()
          .build();

  BlockType<BlockBasaltBehavior> BASALT_TYPE = BlockTypeBuilder
          .builder(BlockBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.BASALT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBeaconBehavior> BEACON_TYPE = BlockTypeBuilder
          .builder(BlockBeaconBehavior.class)
          .vanillaBlock(VanillaBlockId.BEACON)
          .addBasicComponents()
          .build();

  BlockType<BlockBedBehavior> BED_TYPE = BlockTypeBuilder
          .builder(BlockBedBehavior.class)
          .vanillaBlock(VanillaBlockId.BED)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HEAD_PIECE_BIT, VanillaBlockPropertyTypes.OCCUPIED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBedrockBehavior> BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockBedrockBehavior.class)
          .vanillaBlock(VanillaBlockId.BEDROCK)
          .setProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBeeNestBehavior> BEE_NEST_TYPE = BlockTypeBuilder
          .builder(BlockBeeNestBehavior.class)
          .vanillaBlock(VanillaBlockId.BEE_NEST)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeehiveBehavior> BEEHIVE_TYPE = BlockTypeBuilder
          .builder(BlockBeehiveBehavior.class)
          .vanillaBlock(VanillaBlockId.BEEHIVE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeetrootBehavior> BEETROOT_TYPE = BlockTypeBuilder
          .builder(BlockBeetrootBehavior.class)
          .vanillaBlock(VanillaBlockId.BEETROOT)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockBellBehavior> BELL_TYPE = BlockTypeBuilder
          .builder(BlockBellBehavior.class)
          .vanillaBlock(VanillaBlockId.BELL)
          .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBigDripleafBehavior> BIG_DRIPLEAF_TYPE = BlockTypeBuilder
          .builder(BlockBigDripleafBehavior.class)
          .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF)
          .setProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchButtonBehavior> BIRCH_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBirchButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchDoorBehavior> BIRCH_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFenceBehavior> BIRCH_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFenceGateBehavior> BIRCH_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchHangingSignBehavior> BIRCH_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchLogBehavior> BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchPressurePlateBehavior> BIRCH_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStairsBehavior> BIRCH_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBirchStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStandingSignBehavior> BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchTrapdoorBehavior> BIRCH_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchWallSignBehavior> BIRCH_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandleBehavior> BLACK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandleCakeBehavior> BLACK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCarpetBehavior> BLACK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlackCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackConcreteBehavior> BLACK_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBlackConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackGlazedTerracottaBehavior> BLACK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlackGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackShulkerBoxBehavior> BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBlackShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackWoolBehavior> BLACK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlackWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneBehavior> BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneDoubleSlabBehavior> BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneSlabBehavior> BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneStairsBehavior> BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneWallBehavior> BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandleBehavior> BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandleCakeBehavior> BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCarpetBehavior> BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlueCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueConcreteBehavior> BLUE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBlueConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueGlazedTerracottaBehavior> BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlueGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueIceBehavior> BLUE_ICE_TYPE = BlockTypeBuilder
          .builder(BlockBlueIceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_ICE)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueShulkerBoxBehavior> BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBlueShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueWoolBehavior> BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlueWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockBoneBlockBehavior> BONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BONE_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBookshelfBehavior> BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockBookshelfBehavior.class)
          .vanillaBlock(VanillaBlockId.BOOKSHELF)
          .addBasicComponents()
          .build();

  BlockType<BlockBorderBlockBehavior> BORDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBorderBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BORDER_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrainCoralBehavior> BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBrainCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.BRAIN_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBrewingStandBehavior> BREWING_STAND_TYPE = BlockTypeBuilder
          .builder(BlockBrewingStandBehavior.class)
          .vanillaBlock(VanillaBlockId.BREWING_STAND)
          .setProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickBlockBehavior> BRICK_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrickBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BRICK_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickStairsBehavior> BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandleBehavior> BROWN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandleCakeBehavior> BROWN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCarpetBehavior> BROWN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBrownCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownConcreteBehavior> BROWN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockBrownConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownGlazedTerracottaBehavior> BROWN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBrownGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroomBlockBehavior> BROWN_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownShulkerBoxBehavior> BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBrownShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownWoolBehavior> BROWN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBrownWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN_TYPE = BlockTypeBuilder
          .builder(BlockBubbleColumnBehavior.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN)
          .setProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleCoralBehavior> BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBubbleCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBuddingAmethystBehavior> BUDDING_AMETHYST_TYPE = BlockTypeBuilder
          .builder(BlockBuddingAmethystBehavior.class)
          .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST)
          .addBasicComponents()
          .build();

  BlockType<BlockCactusBehavior> CACTUS_TYPE = BlockTypeBuilder
          .builder(BlockCactusBehavior.class)
          .vanillaBlock(VanillaBlockId.CACTUS)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockCakeBehavior> CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CAKE)
          .setProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
          .addBasicComponents()
          .build();

  BlockType<BlockCalciteBehavior> CALCITE_TYPE = BlockTypeBuilder
          .builder(BlockCalciteBehavior.class)
          .vanillaBlock(VanillaBlockId.CALCITE)
          .addBasicComponents()
          .build();

  BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .addBasicComponents()
          .build();

  BlockType<BlockCameraBehavior> CAMERA_TYPE = BlockTypeBuilder
          .builder(BlockCameraBehavior.class)
          .vanillaBlock(VanillaBlockId.CAMERA)
          .addBasicComponents()
          .build();

  BlockType<BlockCampfireBehavior> CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.CAMPFIRE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .addBasicComponents()
          .build();

  BlockType<BlockCandleBehavior> CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCandleCakeBehavior> CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCarrotsBehavior> CARROTS_TYPE = BlockTypeBuilder
          .builder(BlockCarrotsBehavior.class)
          .vanillaBlock(VanillaBlockId.CARROTS)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockCartographyTableBehavior> CARTOGRAPHY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCartographyTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE)
          .addBasicComponents()
          .build();

  BlockType<BlockCarvedPumpkinBehavior> CARVED_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockCarvedPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCauldronBehavior> CAULDRON_TYPE = BlockTypeBuilder
          .builder(BlockCauldronBehavior.class)
          .vanillaBlock(VanillaBlockId.CAULDRON)
          .setProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesBehavior> CAVE_VINES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES)
          .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesBodyWithBerriesBehavior> CAVE_VINES_BODY_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES)
          .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES)
          .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockChainBehavior> CHAIN_TYPE = BlockTypeBuilder
          .builder(BlockChainBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockChainCommandBlockBehavior> CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockChainCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChemicalHeatBehavior> CHEMICAL_HEAT_TYPE = BlockTypeBuilder
          .builder(BlockChemicalHeatBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT)
          .addBasicComponents()
          .build();

  BlockType<BlockChemistryTableBehavior> CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockChemistryTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE)
          .setProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryButtonBehavior> CHERRY_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCherryButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoorBehavior> CHERRY_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoubleSlabBehavior> CHERRY_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFenceBehavior> CHERRY_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFenceGateBehavior> CHERRY_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryHangingSignBehavior> CHERRY_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLeavesBehavior> CHERRY_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockCherryLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLogBehavior> CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockCherryLogBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPlanksBehavior> CHERRY_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCherryPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PLANKS)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPressurePlateBehavior> CHERRY_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySaplingBehavior> CHERRY_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockCherrySaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SAPLING)
          .setProperties(VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySlabBehavior> CHERRY_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherrySlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStairsBehavior> CHERRY_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCherryStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStandingSignBehavior> CHERRY_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryTrapdoorBehavior> CHERRY_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWallSignBehavior> CHERRY_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWoodBehavior> CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockChestBehavior> CHEST_TYPE = BlockTypeBuilder
          .builder(BlockChestBehavior.class)
          .vanillaBlock(VanillaBlockId.CHEST)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledBookshelfBehavior> CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockChiseledBookshelfBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF)
          .setProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledDeepslateBehavior> CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledNetherBricksBehavior> CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockChiseledNetherBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockChorusFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_FLOWER)
          .setProperties(VanillaBlockPropertyTypes.AGE_6)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusPlantBehavior> CHORUS_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockChorusPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_PLANT)
          .addBasicComponents()
          .build();

  BlockType<BlockClayBehavior> CLAY_TYPE = BlockTypeBuilder
          .builder(BlockClayBehavior.class)
          .vanillaBlock(VanillaBlockId.CLAY)
          .addBasicComponents()
          .build();

  BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockClientRequestPlaceholderBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalBlockBehavior> COAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoalBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COAL_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalOreBehavior> COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCoalOreBehavior.class)
          .vanillaBlock(VanillaBlockId.COAL_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateDoubleSlabBehavior> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateSlabBehavior> COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateStairsBehavior> COBBLED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateWallBehavior> COBBLED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateWallBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestoneBehavior> COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestoneWallBehavior> COBBLESTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_BLOCK_TYPE, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCocoaBehavior> COCOA_TYPE = BlockTypeBuilder
          .builder(BlockCocoaBehavior.class)
          .vanillaBlock(VanillaBlockId.COCOA)
          .setProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchBpBehavior> COLORED_TORCH_BP_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchBpBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchRgBehavior> COLORED_TORCH_RG_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchRgBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCommandBlockBehavior> COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COMMAND_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockComposterBehavior> COMPOSTER_TYPE = BlockTypeBuilder
          .builder(BlockComposterBehavior.class)
          .vanillaBlock(VanillaBlockId.COMPOSTER)
          .setProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockConcretePowderBehavior> CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.CONCRETE_POWDER)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockConduitBehavior> CONDUIT_TYPE = BlockTypeBuilder
          .builder(BlockConduitBehavior.class)
          .vanillaBlock(VanillaBlockId.CONDUIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperBlockBehavior> COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperOreBehavior> COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralBlockBehavior> CORAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoralBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanBehavior> CORAL_FAN_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanDeadBehavior> CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanDeadBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHangBehavior> CORAL_FAN_HANG_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHangBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG)
          .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang2Behavior> CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang2Behavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2)
          .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang3Behavior> CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang3Behavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3)
          .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateBricksBehavior> CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedNetherBricksBehavior> CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedNetherBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedPolishedBlackstoneBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCraftingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonButtonBehavior> CRIMSON_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoorBehavior> CRIMSON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoubleSlabBehavior> CRIMSON_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFenceBehavior> CRIMSON_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFenceGateBehavior> CRIMSON_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHangingSignBehavior> CRIMSON_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHyphaeBehavior> CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPlanksBehavior> CRIMSON_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPressurePlateBehavior> CRIMSON_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonRootsBehavior> CRIMSON_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_ROOTS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonSlabBehavior> CRIMSON_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStairsBehavior> CRIMSON_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStandingSignBehavior> CRIMSON_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStemBehavior> CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonTrapdoorBehavior> CRIMSON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonWallSignBehavior> CRIMSON_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCryingObsidianBehavior> CRYING_OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockCryingObsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperBehavior> CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperSlabBehavior> CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperStairsBehavior> CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandleBehavior> CYAN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandleCakeBehavior> CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCarpetBehavior> CYAN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockCyanCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanConcreteBehavior> CYAN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockCyanConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanGlazedTerracottaBehavior> CYAN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockCyanGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanShulkerBoxBehavior> CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockCyanShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanWoolBehavior> CYAN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockCyanWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakButtonBehavior> DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakDoorBehavior> DARK_OAK_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFenceBehavior> DARK_OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFenceGateBehavior> DARK_OAK_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakHangingSignBehavior> DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakLogBehavior> DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakPressurePlateBehavior> DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakStairsBehavior> DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakTrapdoorBehavior> DARK_OAK_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkPrismarineStairsBehavior> DARK_PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkPrismarineStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakStandingSignBehavior> DARKOAK_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakWallSignBehavior> DARKOAK_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetectorInvertedBehavior> DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorInvertedBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBrainCoralBehavior> DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBrainCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBubbleCoralBehavior> DEAD_BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBubbleCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadFireCoralBehavior> DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadFireCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadHornCoralBehavior> DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadTubeCoralBehavior> DEAD_TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadTubeCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_TUBE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadbushBehavior> DEADBUSH_TYPE = BlockTypeBuilder
          .builder(BlockDeadbushBehavior.class)
          .vanillaBlock(VanillaBlockId.DEADBUSH)
          .addBasicComponents()
          .build();

  BlockType<BlockDecoratedPotBehavior> DECORATED_POT_TYPE = BlockTypeBuilder
          .builder(BlockDecoratedPotBehavior.class)
          .vanillaBlock(VanillaBlockId.DECORATED_POT)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBehavior> DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickDoubleSlabBehavior> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickSlabBehavior> DEEPSLATE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickStairsBehavior> DEEPSLATE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickWallBehavior> DEEPSLATE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBricksBehavior> DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCoalOreBehavior> DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCoalOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateDiamondOreBehavior> DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateDiamondOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateEmeraldOreBehavior> DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateEmeraldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateIronOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateLapisOreBehavior> DEEPSLATE_LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateLapisOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_LAPIS_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateRedstoneOreBehavior> DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileDoubleSlabBehavior> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileSlabBehavior> DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileStairsBehavior> DEEPSLATE_TILE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileWallBehavior> DEEPSLATE_TILE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileWallBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES)
          .addBasicComponents()
          .build();

  BlockType<BlockDenyBehavior> DENY_TYPE = BlockTypeBuilder
          .builder(BlockDenyBehavior.class)
          .vanillaBlock(VanillaBlockId.DENY)
          .addBasicComponents()
          .build();

  BlockType<BlockDetectorRailBehavior> DETECTOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockDetectorRailBehavior.class)
          .vanillaBlock(VanillaBlockId.DETECTOR_RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondBlockBehavior> DIAMOND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDiamondBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondOreBehavior> DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDiamondOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockDioriteStairsBehavior> DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDioriteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.DIORITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDirtBehavior> DIRT_TYPE = BlockTypeBuilder
          .builder(BlockDirtBehavior.class)
          .vanillaBlock(VanillaBlockId.DIRT)
          .setProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockDirtWithRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS)
          .addBasicComponents()
          .build();

  BlockType<BlockDispenserBehavior> DISPENSER_TYPE = BlockTypeBuilder
          .builder(BlockDispenserBehavior.class)
          .vanillaBlock(VanillaBlockId.DISPENSER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleCutCopperSlabBehavior> DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoublePlantBehavior> DOUBLE_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockDoublePlantBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
          .setProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlabBehavior> DOUBLE_STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab2Behavior> DOUBLE_STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab2Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab3Behavior> DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab4Behavior> DOUBLE_STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab4Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleWoodenSlabBehavior> DOUBLE_WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleWoodenSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDragonEggBehavior> DRAGON_EGG_TYPE = BlockTypeBuilder
          .builder(BlockDragonEggBehavior.class)
          .vanillaBlock(VanillaBlockId.DRAGON_EGG)
          .addBasicComponents()
          .build();

  BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDriedKelpBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDripstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockDropperBehavior> DROPPER_TYPE = BlockTypeBuilder
          .builder(BlockDropperBehavior.class)
          .vanillaBlock(VanillaBlockId.DROPPER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockElement0Behavior> ELEMENT_0_TYPE = BlockTypeBuilder
          .builder(BlockElement0Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_0)
          .addBasicComponents()
          .build();

  BlockType<BlockElement1Behavior> ELEMENT_1_TYPE = BlockTypeBuilder
          .builder(BlockElement1Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_1)
          .addBasicComponents()
          .build();

  BlockType<BlockElement10Behavior> ELEMENT_10_TYPE = BlockTypeBuilder
          .builder(BlockElement10Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_10)
          .addBasicComponents()
          .build();

  BlockType<BlockElement100Behavior> ELEMENT_100_TYPE = BlockTypeBuilder
          .builder(BlockElement100Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_100)
          .addBasicComponents()
          .build();

  BlockType<BlockElement101Behavior> ELEMENT_101_TYPE = BlockTypeBuilder
          .builder(BlockElement101Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_101)
          .addBasicComponents()
          .build();

  BlockType<BlockElement102Behavior> ELEMENT_102_TYPE = BlockTypeBuilder
          .builder(BlockElement102Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_102)
          .addBasicComponents()
          .build();

  BlockType<BlockElement103Behavior> ELEMENT_103_TYPE = BlockTypeBuilder
          .builder(BlockElement103Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_103)
          .addBasicComponents()
          .build();

  BlockType<BlockElement104Behavior> ELEMENT_104_TYPE = BlockTypeBuilder
          .builder(BlockElement104Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_104)
          .addBasicComponents()
          .build();

  BlockType<BlockElement105Behavior> ELEMENT_105_TYPE = BlockTypeBuilder
          .builder(BlockElement105Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_105)
          .addBasicComponents()
          .build();

  BlockType<BlockElement106Behavior> ELEMENT_106_TYPE = BlockTypeBuilder
          .builder(BlockElement106Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_106)
          .addBasicComponents()
          .build();

  BlockType<BlockElement107Behavior> ELEMENT_107_TYPE = BlockTypeBuilder
          .builder(BlockElement107Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_107)
          .addBasicComponents()
          .build();

  BlockType<BlockElement108Behavior> ELEMENT_108_TYPE = BlockTypeBuilder
          .builder(BlockElement108Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_108)
          .addBasicComponents()
          .build();

  BlockType<BlockElement109Behavior> ELEMENT_109_TYPE = BlockTypeBuilder
          .builder(BlockElement109Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_109)
          .addBasicComponents()
          .build();

  BlockType<BlockElement11Behavior> ELEMENT_11_TYPE = BlockTypeBuilder
          .builder(BlockElement11Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_11)
          .addBasicComponents()
          .build();

  BlockType<BlockElement110Behavior> ELEMENT_110_TYPE = BlockTypeBuilder
          .builder(BlockElement110Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_110)
          .addBasicComponents()
          .build();

  BlockType<BlockElement111Behavior> ELEMENT_111_TYPE = BlockTypeBuilder
          .builder(BlockElement111Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_111)
          .addBasicComponents()
          .build();

  BlockType<BlockElement112Behavior> ELEMENT_112_TYPE = BlockTypeBuilder
          .builder(BlockElement112Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_112)
          .addBasicComponents()
          .build();

  BlockType<BlockElement113Behavior> ELEMENT_113_TYPE = BlockTypeBuilder
          .builder(BlockElement113Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_113)
          .addBasicComponents()
          .build();

  BlockType<BlockElement114Behavior> ELEMENT_114_TYPE = BlockTypeBuilder
          .builder(BlockElement114Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_114)
          .addBasicComponents()
          .build();

  BlockType<BlockElement115Behavior> ELEMENT_115_TYPE = BlockTypeBuilder
          .builder(BlockElement115Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_115)
          .addBasicComponents()
          .build();

  BlockType<BlockElement116Behavior> ELEMENT_116_TYPE = BlockTypeBuilder
          .builder(BlockElement116Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_116)
          .addBasicComponents()
          .build();

  BlockType<BlockElement117Behavior> ELEMENT_117_TYPE = BlockTypeBuilder
          .builder(BlockElement117Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_117)
          .addBasicComponents()
          .build();

  BlockType<BlockElement118Behavior> ELEMENT_118_TYPE = BlockTypeBuilder
          .builder(BlockElement118Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_118)
          .addBasicComponents()
          .build();

  BlockType<BlockElement12Behavior> ELEMENT_12_TYPE = BlockTypeBuilder
          .builder(BlockElement12Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_12)
          .addBasicComponents()
          .build();

  BlockType<BlockElement13Behavior> ELEMENT_13_TYPE = BlockTypeBuilder
          .builder(BlockElement13Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_13)
          .addBasicComponents()
          .build();

  BlockType<BlockElement14Behavior> ELEMENT_14_TYPE = BlockTypeBuilder
          .builder(BlockElement14Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_14)
          .addBasicComponents()
          .build();

  BlockType<BlockElement15Behavior> ELEMENT_15_TYPE = BlockTypeBuilder
          .builder(BlockElement15Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_15)
          .addBasicComponents()
          .build();

  BlockType<BlockElement16Behavior> ELEMENT_16_TYPE = BlockTypeBuilder
          .builder(BlockElement16Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_16)
          .addBasicComponents()
          .build();

  BlockType<BlockElement17Behavior> ELEMENT_17_TYPE = BlockTypeBuilder
          .builder(BlockElement17Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_17)
          .addBasicComponents()
          .build();

  BlockType<BlockElement18Behavior> ELEMENT_18_TYPE = BlockTypeBuilder
          .builder(BlockElement18Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_18)
          .addBasicComponents()
          .build();

  BlockType<BlockElement19Behavior> ELEMENT_19_TYPE = BlockTypeBuilder
          .builder(BlockElement19Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_19)
          .addBasicComponents()
          .build();

  BlockType<BlockElement2Behavior> ELEMENT_2_TYPE = BlockTypeBuilder
          .builder(BlockElement2Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_2)
          .addBasicComponents()
          .build();

  BlockType<BlockElement20Behavior> ELEMENT_20_TYPE = BlockTypeBuilder
          .builder(BlockElement20Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_20)
          .addBasicComponents()
          .build();

  BlockType<BlockElement21Behavior> ELEMENT_21_TYPE = BlockTypeBuilder
          .builder(BlockElement21Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_21)
          .addBasicComponents()
          .build();

  BlockType<BlockElement22Behavior> ELEMENT_22_TYPE = BlockTypeBuilder
          .builder(BlockElement22Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_22)
          .addBasicComponents()
          .build();

  BlockType<BlockElement23Behavior> ELEMENT_23_TYPE = BlockTypeBuilder
          .builder(BlockElement23Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_23)
          .addBasicComponents()
          .build();

  BlockType<BlockElement24Behavior> ELEMENT_24_TYPE = BlockTypeBuilder
          .builder(BlockElement24Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_24)
          .addBasicComponents()
          .build();

  BlockType<BlockElement25Behavior> ELEMENT_25_TYPE = BlockTypeBuilder
          .builder(BlockElement25Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_25)
          .addBasicComponents()
          .build();

  BlockType<BlockElement26Behavior> ELEMENT_26_TYPE = BlockTypeBuilder
          .builder(BlockElement26Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_26)
          .addBasicComponents()
          .build();

  BlockType<BlockElement27Behavior> ELEMENT_27_TYPE = BlockTypeBuilder
          .builder(BlockElement27Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_27)
          .addBasicComponents()
          .build();

  BlockType<BlockElement28Behavior> ELEMENT_28_TYPE = BlockTypeBuilder
          .builder(BlockElement28Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_28)
          .addBasicComponents()
          .build();

  BlockType<BlockElement29Behavior> ELEMENT_29_TYPE = BlockTypeBuilder
          .builder(BlockElement29Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_29)
          .addBasicComponents()
          .build();

  BlockType<BlockElement3Behavior> ELEMENT_3_TYPE = BlockTypeBuilder
          .builder(BlockElement3Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_3)
          .addBasicComponents()
          .build();

  BlockType<BlockElement30Behavior> ELEMENT_30_TYPE = BlockTypeBuilder
          .builder(BlockElement30Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_30)
          .addBasicComponents()
          .build();

  BlockType<BlockElement31Behavior> ELEMENT_31_TYPE = BlockTypeBuilder
          .builder(BlockElement31Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_31)
          .addBasicComponents()
          .build();

  BlockType<BlockElement32Behavior> ELEMENT_32_TYPE = BlockTypeBuilder
          .builder(BlockElement32Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_32)
          .addBasicComponents()
          .build();

  BlockType<BlockElement33Behavior> ELEMENT_33_TYPE = BlockTypeBuilder
          .builder(BlockElement33Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_33)
          .addBasicComponents()
          .build();

  BlockType<BlockElement34Behavior> ELEMENT_34_TYPE = BlockTypeBuilder
          .builder(BlockElement34Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_34)
          .addBasicComponents()
          .build();

  BlockType<BlockElement35Behavior> ELEMENT_35_TYPE = BlockTypeBuilder
          .builder(BlockElement35Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_35)
          .addBasicComponents()
          .build();

  BlockType<BlockElement36Behavior> ELEMENT_36_TYPE = BlockTypeBuilder
          .builder(BlockElement36Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_36)
          .addBasicComponents()
          .build();

  BlockType<BlockElement37Behavior> ELEMENT_37_TYPE = BlockTypeBuilder
          .builder(BlockElement37Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_37)
          .addBasicComponents()
          .build();

  BlockType<BlockElement38Behavior> ELEMENT_38_TYPE = BlockTypeBuilder
          .builder(BlockElement38Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_38)
          .addBasicComponents()
          .build();

  BlockType<BlockElement39Behavior> ELEMENT_39_TYPE = BlockTypeBuilder
          .builder(BlockElement39Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_39)
          .addBasicComponents()
          .build();

  BlockType<BlockElement4Behavior> ELEMENT_4_TYPE = BlockTypeBuilder
          .builder(BlockElement4Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_4)
          .addBasicComponents()
          .build();

  BlockType<BlockElement40Behavior> ELEMENT_40_TYPE = BlockTypeBuilder
          .builder(BlockElement40Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_40)
          .addBasicComponents()
          .build();

  BlockType<BlockElement41Behavior> ELEMENT_41_TYPE = BlockTypeBuilder
          .builder(BlockElement41Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_41)
          .addBasicComponents()
          .build();

  BlockType<BlockElement42Behavior> ELEMENT_42_TYPE = BlockTypeBuilder
          .builder(BlockElement42Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_42)
          .addBasicComponents()
          .build();

  BlockType<BlockElement43Behavior> ELEMENT_43_TYPE = BlockTypeBuilder
          .builder(BlockElement43Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_43)
          .addBasicComponents()
          .build();

  BlockType<BlockElement44Behavior> ELEMENT_44_TYPE = BlockTypeBuilder
          .builder(BlockElement44Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_44)
          .addBasicComponents()
          .build();

  BlockType<BlockElement45Behavior> ELEMENT_45_TYPE = BlockTypeBuilder
          .builder(BlockElement45Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_45)
          .addBasicComponents()
          .build();

  BlockType<BlockElement46Behavior> ELEMENT_46_TYPE = BlockTypeBuilder
          .builder(BlockElement46Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_46)
          .addBasicComponents()
          .build();

  BlockType<BlockElement47Behavior> ELEMENT_47_TYPE = BlockTypeBuilder
          .builder(BlockElement47Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_47)
          .addBasicComponents()
          .build();

  BlockType<BlockElement48Behavior> ELEMENT_48_TYPE = BlockTypeBuilder
          .builder(BlockElement48Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_48)
          .addBasicComponents()
          .build();

  BlockType<BlockElement49Behavior> ELEMENT_49_TYPE = BlockTypeBuilder
          .builder(BlockElement49Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_49)
          .addBasicComponents()
          .build();

  BlockType<BlockElement5Behavior> ELEMENT_5_TYPE = BlockTypeBuilder
          .builder(BlockElement5Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_5)
          .addBasicComponents()
          .build();

  BlockType<BlockElement50Behavior> ELEMENT_50_TYPE = BlockTypeBuilder
          .builder(BlockElement50Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_50)
          .addBasicComponents()
          .build();

  BlockType<BlockElement51Behavior> ELEMENT_51_TYPE = BlockTypeBuilder
          .builder(BlockElement51Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_51)
          .addBasicComponents()
          .build();

  BlockType<BlockElement52Behavior> ELEMENT_52_TYPE = BlockTypeBuilder
          .builder(BlockElement52Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_52)
          .addBasicComponents()
          .build();

  BlockType<BlockElement53Behavior> ELEMENT_53_TYPE = BlockTypeBuilder
          .builder(BlockElement53Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_53)
          .addBasicComponents()
          .build();

  BlockType<BlockElement54Behavior> ELEMENT_54_TYPE = BlockTypeBuilder
          .builder(BlockElement54Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_54)
          .addBasicComponents()
          .build();

  BlockType<BlockElement55Behavior> ELEMENT_55_TYPE = BlockTypeBuilder
          .builder(BlockElement55Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_55)
          .addBasicComponents()
          .build();

  BlockType<BlockElement56Behavior> ELEMENT_56_TYPE = BlockTypeBuilder
          .builder(BlockElement56Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_56)
          .addBasicComponents()
          .build();

  BlockType<BlockElement57Behavior> ELEMENT_57_TYPE = BlockTypeBuilder
          .builder(BlockElement57Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_57)
          .addBasicComponents()
          .build();

  BlockType<BlockElement58Behavior> ELEMENT_58_TYPE = BlockTypeBuilder
          .builder(BlockElement58Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_58)
          .addBasicComponents()
          .build();

  BlockType<BlockElement59Behavior> ELEMENT_59_TYPE = BlockTypeBuilder
          .builder(BlockElement59Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_59)
          .addBasicComponents()
          .build();

  BlockType<BlockElement6Behavior> ELEMENT_6_TYPE = BlockTypeBuilder
          .builder(BlockElement6Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_6)
          .addBasicComponents()
          .build();

  BlockType<BlockElement60Behavior> ELEMENT_60_TYPE = BlockTypeBuilder
          .builder(BlockElement60Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_60)
          .addBasicComponents()
          .build();

  BlockType<BlockElement61Behavior> ELEMENT_61_TYPE = BlockTypeBuilder
          .builder(BlockElement61Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_61)
          .addBasicComponents()
          .build();

  BlockType<BlockElement62Behavior> ELEMENT_62_TYPE = BlockTypeBuilder
          .builder(BlockElement62Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_62)
          .addBasicComponents()
          .build();

  BlockType<BlockElement63Behavior> ELEMENT_63_TYPE = BlockTypeBuilder
          .builder(BlockElement63Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_63)
          .addBasicComponents()
          .build();

  BlockType<BlockElement64Behavior> ELEMENT_64_TYPE = BlockTypeBuilder
          .builder(BlockElement64Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_64)
          .addBasicComponents()
          .build();

  BlockType<BlockElement65Behavior> ELEMENT_65_TYPE = BlockTypeBuilder
          .builder(BlockElement65Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_65)
          .addBasicComponents()
          .build();

  BlockType<BlockElement66Behavior> ELEMENT_66_TYPE = BlockTypeBuilder
          .builder(BlockElement66Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_66)
          .addBasicComponents()
          .build();

  BlockType<BlockElement67Behavior> ELEMENT_67_TYPE = BlockTypeBuilder
          .builder(BlockElement67Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_67)
          .addBasicComponents()
          .build();

  BlockType<BlockElement68Behavior> ELEMENT_68_TYPE = BlockTypeBuilder
          .builder(BlockElement68Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_68)
          .addBasicComponents()
          .build();

  BlockType<BlockElement69Behavior> ELEMENT_69_TYPE = BlockTypeBuilder
          .builder(BlockElement69Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_69)
          .addBasicComponents()
          .build();

  BlockType<BlockElement7Behavior> ELEMENT_7_TYPE = BlockTypeBuilder
          .builder(BlockElement7Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_7)
          .addBasicComponents()
          .build();

  BlockType<BlockElement70Behavior> ELEMENT_70_TYPE = BlockTypeBuilder
          .builder(BlockElement70Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_70)
          .addBasicComponents()
          .build();

  BlockType<BlockElement71Behavior> ELEMENT_71_TYPE = BlockTypeBuilder
          .builder(BlockElement71Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_71)
          .addBasicComponents()
          .build();

  BlockType<BlockElement72Behavior> ELEMENT_72_TYPE = BlockTypeBuilder
          .builder(BlockElement72Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_72)
          .addBasicComponents()
          .build();

  BlockType<BlockElement73Behavior> ELEMENT_73_TYPE = BlockTypeBuilder
          .builder(BlockElement73Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_73)
          .addBasicComponents()
          .build();

  BlockType<BlockElement74Behavior> ELEMENT_74_TYPE = BlockTypeBuilder
          .builder(BlockElement74Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_74)
          .addBasicComponents()
          .build();

  BlockType<BlockElement75Behavior> ELEMENT_75_TYPE = BlockTypeBuilder
          .builder(BlockElement75Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_75)
          .addBasicComponents()
          .build();

  BlockType<BlockElement76Behavior> ELEMENT_76_TYPE = BlockTypeBuilder
          .builder(BlockElement76Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_76)
          .addBasicComponents()
          .build();

  BlockType<BlockElement77Behavior> ELEMENT_77_TYPE = BlockTypeBuilder
          .builder(BlockElement77Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_77)
          .addBasicComponents()
          .build();

  BlockType<BlockElement78Behavior> ELEMENT_78_TYPE = BlockTypeBuilder
          .builder(BlockElement78Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_78)
          .addBasicComponents()
          .build();

  BlockType<BlockElement79Behavior> ELEMENT_79_TYPE = BlockTypeBuilder
          .builder(BlockElement79Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_79)
          .addBasicComponents()
          .build();

  BlockType<BlockElement8Behavior> ELEMENT_8_TYPE = BlockTypeBuilder
          .builder(BlockElement8Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_8)
          .addBasicComponents()
          .build();

  BlockType<BlockElement80Behavior> ELEMENT_80_TYPE = BlockTypeBuilder
          .builder(BlockElement80Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_80)
          .addBasicComponents()
          .build();

  BlockType<BlockElement81Behavior> ELEMENT_81_TYPE = BlockTypeBuilder
          .builder(BlockElement81Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_81)
          .addBasicComponents()
          .build();

  BlockType<BlockElement82Behavior> ELEMENT_82_TYPE = BlockTypeBuilder
          .builder(BlockElement82Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_82)
          .addBasicComponents()
          .build();

  BlockType<BlockElement83Behavior> ELEMENT_83_TYPE = BlockTypeBuilder
          .builder(BlockElement83Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_83)
          .addBasicComponents()
          .build();

  BlockType<BlockElement84Behavior> ELEMENT_84_TYPE = BlockTypeBuilder
          .builder(BlockElement84Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_84)
          .addBasicComponents()
          .build();

  BlockType<BlockElement85Behavior> ELEMENT_85_TYPE = BlockTypeBuilder
          .builder(BlockElement85Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_85)
          .addBasicComponents()
          .build();

  BlockType<BlockElement86Behavior> ELEMENT_86_TYPE = BlockTypeBuilder
          .builder(BlockElement86Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_86)
          .addBasicComponents()
          .build();

  BlockType<BlockElement87Behavior> ELEMENT_87_TYPE = BlockTypeBuilder
          .builder(BlockElement87Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_87)
          .addBasicComponents()
          .build();

  BlockType<BlockElement88Behavior> ELEMENT_88_TYPE = BlockTypeBuilder
          .builder(BlockElement88Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_88)
          .addBasicComponents()
          .build();

  BlockType<BlockElement89Behavior> ELEMENT_89_TYPE = BlockTypeBuilder
          .builder(BlockElement89Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_89)
          .addBasicComponents()
          .build();

  BlockType<BlockElement9Behavior> ELEMENT_9_TYPE = BlockTypeBuilder
          .builder(BlockElement9Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_9)
          .addBasicComponents()
          .build();

  BlockType<BlockElement90Behavior> ELEMENT_90_TYPE = BlockTypeBuilder
          .builder(BlockElement90Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_90)
          .addBasicComponents()
          .build();

  BlockType<BlockElement91Behavior> ELEMENT_91_TYPE = BlockTypeBuilder
          .builder(BlockElement91Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_91)
          .addBasicComponents()
          .build();

  BlockType<BlockElement92Behavior> ELEMENT_92_TYPE = BlockTypeBuilder
          .builder(BlockElement92Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_92)
          .addBasicComponents()
          .build();

  BlockType<BlockElement93Behavior> ELEMENT_93_TYPE = BlockTypeBuilder
          .builder(BlockElement93Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_93)
          .addBasicComponents()
          .build();

  BlockType<BlockElement94Behavior> ELEMENT_94_TYPE = BlockTypeBuilder
          .builder(BlockElement94Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_94)
          .addBasicComponents()
          .build();

  BlockType<BlockElement95Behavior> ELEMENT_95_TYPE = BlockTypeBuilder
          .builder(BlockElement95Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_95)
          .addBasicComponents()
          .build();

  BlockType<BlockElement96Behavior> ELEMENT_96_TYPE = BlockTypeBuilder
          .builder(BlockElement96Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_96)
          .addBasicComponents()
          .build();

  BlockType<BlockElement97Behavior> ELEMENT_97_TYPE = BlockTypeBuilder
          .builder(BlockElement97Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_97)
          .addBasicComponents()
          .build();

  BlockType<BlockElement98Behavior> ELEMENT_98_TYPE = BlockTypeBuilder
          .builder(BlockElement98Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_98)
          .addBasicComponents()
          .build();

  BlockType<BlockElement99Behavior> ELEMENT_99_TYPE = BlockTypeBuilder
          .builder(BlockElement99Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_99)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldBlockBehavior> EMERALD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.EMERALD_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldOreBehavior> EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.EMERALD_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockEnchantingTableBehavior> ENCHANTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockEnchantingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBrickStairsBehavior> END_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockEndBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBricksBehavior> END_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockEndBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.END_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockEndGatewayBehavior> END_GATEWAY_TYPE = BlockTypeBuilder
          .builder(BlockEndGatewayBehavior.class)
          .vanillaBlock(VanillaBlockId.END_GATEWAY)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortalBehavior> END_PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockEndRodBehavior> END_ROD_TYPE = BlockTypeBuilder
          .builder(BlockEndRodBehavior.class)
          .vanillaBlock(VanillaBlockId.END_ROD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndStoneBehavior> END_STONE_TYPE = BlockTypeBuilder
          .builder(BlockEndStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.END_STONE)
          .addBasicComponents()
          .build();

  BlockType<BlockEnderChestBehavior> ENDER_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockEnderChestBehavior.class)
          .vanillaBlock(VanillaBlockId.ENDER_CHEST)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCopperBehavior> EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperBehavior> EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperSlabBehavior> EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperStairsBehavior> EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedDoubleCutCopperSlabBehavior> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFarmlandBehavior> FARMLAND_TYPE = BlockTypeBuilder
          .builder(BlockFarmlandBehavior.class)
          .vanillaBlock(VanillaBlockId.FARMLAND)
          .setProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockFenceGateBehavior> FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFireBehavior> FIRE_TYPE = BlockTypeBuilder
          .builder(BlockFireBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockFireCoralBehavior> FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockFireCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockFletchingTableBehavior> FLETCHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockFletchingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.FLETCHING_TABLE)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowerPotBehavior> FLOWER_POT_TYPE = BlockTypeBuilder
          .builder(BlockFlowerPotBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWER_POT)
          .setProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockFloweringAzaleaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingLavaBehavior> FLOWING_LAVA_TYPE = BlockTypeBuilder
          .builder(BlockFlowingLavaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_LAVA)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingWaterBehavior> FLOWING_WATER_TYPE = BlockTypeBuilder
          .builder(BlockFlowingWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_WATER)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFrameBehavior> FRAME_TYPE = BlockTypeBuilder
          .builder(BlockFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.FRAME)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFrogSpawnBehavior> FROG_SPAWN_TYPE = BlockTypeBuilder
          .builder(BlockFrogSpawnBehavior.class)
          .vanillaBlock(VanillaBlockId.FROG_SPAWN)
          .addBasicComponents()
          .build();

  BlockType<BlockFrostedIceBehavior> FROSTED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockFrostedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.FROSTED_ICE)
          .setProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockFurnaceBehavior> FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.FURNACE)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGildedBlackstoneBehavior> GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGildedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockGlassBehavior> GLASS_TYPE = BlockTypeBuilder
          .builder(BlockGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS)
          .addBasicComponents()
          .build();

  BlockType<BlockGlassPaneBehavior> GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS_PANE)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowFrameBehavior> GLOW_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockGlowFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOW_FRAME)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowLichenBehavior> GLOW_LICHEN_TYPE = BlockTypeBuilder
          .builder(BlockGlowLichenBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOW_LICHEN)
          .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowingobsidianBehavior> GLOWINGOBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockGlowingobsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowstoneBehavior> GLOWSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGlowstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLOWSTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldBlockBehavior> GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockGoldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLD_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldOreBehavior> GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLD_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldenRailBehavior> GOLDEN_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockGoldenRailBehavior.class)
          .vanillaBlock(VanillaBlockId.GOLDEN_RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .addBasicComponents()
          .build();

  BlockType<BlockGraniteStairsBehavior> GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockGraniteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.GRANITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrassBehavior> GRASS_TYPE = BlockTypeBuilder
          .builder(BlockGrassBehavior.class)
          .vanillaBlock(VanillaBlockId.GRASS)
          .addBasicComponents()
          .build();

  BlockType<BlockGrassPathBehavior> GRASS_PATH_TYPE = BlockTypeBuilder
          .builder(BlockGrassPathBehavior.class)
          .vanillaBlock(VanillaBlockId.GRASS_PATH)
          .addBasicComponents()
          .build();

  BlockType<BlockGravelBehavior> GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockGravelBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandleBehavior> GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandleCakeBehavior> GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCarpetBehavior> GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGrayCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayConcreteBehavior> GRAY_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGrayConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayGlazedTerracottaBehavior> GRAY_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGrayGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayShulkerBoxBehavior> GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayWoolBehavior> GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGrayWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandleBehavior> GREEN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandleCakeBehavior> GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCarpetBehavior> GREEN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGreenCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenConcreteBehavior> GREEN_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGreenConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenGlazedTerracottaBehavior> GREEN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGreenGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenShulkerBoxBehavior> GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockGreenShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenWoolBehavior> GREEN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGreenWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockGrindstoneBehavior> GRINDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGrindstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GRINDSTONE)
          .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockHangingRootsBehavior> HANGING_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockHangingRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.HANGING_ROOTS)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlassBehavior> HARD_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlassPaneBehavior> HARD_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlassBehavior> HARD_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlassPaneBehavior> HARD_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardenedClayBehavior> HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockHardenedClayBehavior.class)
          .vanillaBlock(VanillaBlockId.HARDENED_CLAY)
          .addBasicComponents()
          .build();

  BlockType<BlockHayBlockBehavior> HAY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHayBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HAY_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockHeavyWeightedPressurePlateBehavior> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockHeavyWeightedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneyBlockBehavior> HONEY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneyBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HONEY_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneycombBlockBehavior> HONEYCOMB_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneycombBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HONEYCOMB_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockHopperBehavior> HOPPER_TYPE = BlockTypeBuilder
          .builder(BlockHopperBehavior.class)
          .vanillaBlock(VanillaBlockId.HOPPER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockHornCoralBehavior> HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.HORN_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockIceBehavior> ICE_TYPE = BlockTypeBuilder
          .builder(BlockIceBehavior.class)
          .vanillaBlock(VanillaBlockId.ICE)
          .addBasicComponents()
          .build();

  BlockType<BlockInfestedDeepslateBehavior> INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockInfestedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdateBehavior> INFO_UPDATE_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdateBehavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate2Behavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE2)
          .addBasicComponents()
          .build();

  BlockType<BlockInvisibleBedrockBehavior> INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockInvisibleBedrockBehavior.class)
          .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBarsBehavior> IRON_BARS_TYPE = BlockTypeBuilder
          .builder(BlockIronBarsBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BARS)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBlockBehavior> IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockIronDoorBehavior> IRON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockIronOreBehavior> IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockIronOreBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockIronTrapdoorBehavior> IRON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJigsawBehavior> JIGSAW_TYPE = BlockTypeBuilder
          .builder(BlockJigsawBehavior.class)
          .vanillaBlock(VanillaBlockId.JIGSAW)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
          .addBasicComponents()
          .build();

  BlockType<BlockJukeboxBehavior> JUKEBOX_TYPE = BlockTypeBuilder
          .builder(BlockJukeboxBehavior.class)
          .vanillaBlock(VanillaBlockId.JUKEBOX)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleButtonBehavior> JUNGLE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockJungleButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleDoorBehavior> JUNGLE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFenceBehavior> JUNGLE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFenceGateBehavior> JUNGLE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleHangingSignBehavior> JUNGLE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleLogBehavior> JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockJungleLogBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockJunglePressurePlateBehavior> JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockJunglePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStairsBehavior> JUNGLE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockJungleStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStandingSignBehavior> JUNGLE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleTrapdoorBehavior> JUNGLE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleWallSignBehavior> JUNGLE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockKelpBehavior> KELP_TYPE = BlockTypeBuilder
          .builder(BlockKelpBehavior.class)
          .vanillaBlock(VanillaBlockId.KELP)
          .setProperties(VanillaBlockPropertyTypes.KELP_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockLadderBehavior> LADDER_TYPE = BlockTypeBuilder
          .builder(BlockLadderBehavior.class)
          .vanillaBlock(VanillaBlockId.LADDER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLanternBehavior> LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.LANTERN)
          .setProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisBlockBehavior> LAPIS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLapisBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.LAPIS_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisOreBehavior> LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLapisOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LAPIS_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockLargeAmethystBudBehavior> LARGE_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockLargeAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLavaBehavior> LAVA_TYPE = BlockTypeBuilder
          .builder(BlockLavaBehavior.class)
          .vanillaBlock(VanillaBlockId.LAVA)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockLeavesBehavior> LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.LEAVES)
          .setProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLeaves2Behavior> LEAVES2_TYPE = BlockTypeBuilder
          .builder(BlockLeaves2Behavior.class)
          .vanillaBlock(VanillaBlockId.LEAVES2)
          .setProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLecternBehavior> LECTERN_TYPE = BlockTypeBuilder
          .builder(BlockLecternBehavior.class)
          .vanillaBlock(VanillaBlockId.LECTERN)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLeverBehavior> LEVER_TYPE = BlockTypeBuilder
          .builder(BlockLeverBehavior.class)
          .vanillaBlock(VanillaBlockId.LEVER)
          .setProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLightBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandleBehavior> LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCarpetBehavior> LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueConcreteBehavior> LIGHT_BLUE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueGlazedTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueShulkerBoxBehavior> LIGHT_BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueWoolBehavior> LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandleBehavior> LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCarpetBehavior> LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayConcreteBehavior> LIGHT_GRAY_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayWoolBehavior> LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightWeightedPressurePlateBehavior> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockLightWeightedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightningRodBehavior> LIGHTNING_ROD_TYPE = BlockTypeBuilder
          .builder(BlockLightningRodBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandleBehavior> LIME_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandleCakeBehavior> LIME_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCarpetBehavior> LIME_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLimeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeConcreteBehavior> LIME_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLimeConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeGlazedTerracottaBehavior> LIME_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLimeGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeShulkerBoxBehavior> LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLimeShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeWoolBehavior> LIME_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLimeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockLitBlastFurnaceBehavior> LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitDeepslateRedstoneOreBehavior> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitDeepslateRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockLitFurnaceBehavior> LIT_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_FURNACE)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitPumpkinBehavior> LIT_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockLitPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_PUMPKIN)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneOreBehavior> LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockLitSmokerBehavior> LIT_SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockLitSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_SMOKER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLodestoneBehavior> LODESTONE_TYPE = BlockTypeBuilder
          .builder(BlockLodestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.LODESTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockLoomBehavior> LOOM_TYPE = BlockTypeBuilder
          .builder(BlockLoomBehavior.class)
          .vanillaBlock(VanillaBlockId.LOOM)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandleBehavior> MAGENTA_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandleCakeBehavior> MAGENTA_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCarpetBehavior> MAGENTA_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaConcreteBehavior> MAGENTA_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaGlazedTerracottaBehavior> MAGENTA_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockMagentaGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaShulkerBoxBehavior> MAGENTA_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockMagentaShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaWoolBehavior> MAGENTA_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockMagentaWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockMagmaBehavior> MAGMA_TYPE = BlockTypeBuilder
          .builder(BlockMagmaBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGMA)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveButtonBehavior> MANGROVE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockMangroveButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoorBehavior> MANGROVE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoubleSlabBehavior> MANGROVE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFenceBehavior> MANGROVE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFenceGateBehavior> MANGROVE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveHangingSignBehavior> MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLeavesBehavior> MANGROVE_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLogBehavior> MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLogBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePlanksBehavior> MANGROVE_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePressurePlateBehavior> MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePropaguleBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE)
          .setProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveRootsBehavior> MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveSlabBehavior> MANGROVE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStairsBehavior> MANGROVE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStandingSignBehavior> MANGROVE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveTrapdoorBehavior> MANGROVE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWallSignBehavior> MANGROVE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWoodBehavior> MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonBlockBehavior> MELON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMelonBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonStemBehavior> MELON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockMelonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_STEM)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockMobSpawnerBehavior> MOB_SPAWNER_TYPE = BlockTypeBuilder
          .builder(BlockMobSpawnerBehavior.class)
          .vanillaBlock(VanillaBlockId.MOB_SPAWNER)
          .addBasicComponents()
          .build();

  BlockType<BlockMonsterEggBehavior> MONSTER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockMonsterEggBehavior.class)
          .vanillaBlock(VanillaBlockId.MONSTER_EGG)
          .setProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockMossBlockBehavior> MOSS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMossBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockMossCarpetBehavior> MOSS_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMossCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestoneStairsBehavior> MOSSY_COBBLESTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyStoneBrickStairsBehavior> MOSSY_STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyStoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMovingBlockBehavior> MOVING_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMovingBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MOVING_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBehavior> MUD_TYPE = BlockTypeBuilder
          .builder(BlockMudBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickDoubleSlabBehavior> MUD_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickSlabBehavior> MUD_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickStairsBehavior> MUD_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickWallBehavior> MUD_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBricksBehavior> MUD_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockMudBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMuddyMangroveRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMyceliumBehavior> MYCELIUM_TYPE = BlockTypeBuilder
          .builder(BlockMyceliumBehavior.class)
          .vanillaBlock(VanillaBlockId.MYCELIUM)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickBehavior> NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickFenceBehavior> NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickStairsBehavior> NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockNetherGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherSproutsBehavior> NETHER_SPROUTS_TYPE = BlockTypeBuilder
          .builder(BlockNetherSproutsBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_SPROUTS)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWartBehavior> NETHER_WART_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART)
          .setProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockNetheriteBlockBehavior> NETHERITE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetheriteBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherrackBehavior> NETHERRACK_TYPE = BlockTypeBuilder
          .builder(BlockNetherrackBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERRACK)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherreactorBehavior> NETHERREACTOR_TYPE = BlockTypeBuilder
          .builder(BlockNetherreactorBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERREACTOR)
          .addBasicComponents()
          .build();

  BlockType<BlockNormalStoneStairsBehavior> NORMAL_STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNormalStoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNoteblockBehavior> NOTEBLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNoteblockBehavior.class)
          .vanillaBlock(VanillaBlockId.NOTEBLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockOakFenceBehavior> OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockOakHangingSignBehavior> OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockOakHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockOakLogBehavior> OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOakStairsBehavior> OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOakStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockObserverBehavior> OBSERVER_TYPE = BlockTypeBuilder
          .builder(BlockObserverBehavior.class)
          .vanillaBlock(VanillaBlockId.OBSERVER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockObsidianBehavior> OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockObsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.OBSIDIAN)
          .addBasicComponents()
          .build();

  BlockType<BlockOchreFroglightBehavior> OCHRE_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockOchreFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandleBehavior> ORANGE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandleCakeBehavior> ORANGE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCarpetBehavior> ORANGE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeConcreteBehavior> ORANGE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeGlazedTerracottaBehavior> ORANGE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockOrangeGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeShulkerBoxBehavior> ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockOrangeShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeWoolBehavior> ORANGE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockOrangeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCopperBehavior> OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperBehavior> OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperSlabBehavior> OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperStairsBehavior> OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedDoubleCutCopperSlabBehavior> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedIceBehavior> PACKED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockPackedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.PACKED_ICE)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedMudBehavior> PACKED_MUD_TYPE = BlockTypeBuilder
          .builder(BlockPackedMudBehavior.class)
          .vanillaBlock(VanillaBlockId.PACKED_MUD)
          .addBasicComponents()
          .build();

  BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockPearlescentFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandleBehavior> PINK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandleCakeBehavior> PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCarpetBehavior> PINK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPinkCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkConcreteBehavior> PINK_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPinkConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkGlazedTerracottaBehavior> PINK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPinkGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkPetalsBehavior> PINK_PETALS_TYPE = BlockTypeBuilder
          .builder(BlockPinkPetalsBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_PETALS)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkShulkerBoxBehavior> PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockPinkShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkWoolBehavior> PINK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPinkWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockPistonBehavior> PISTON_TYPE = BlockTypeBuilder
          .builder(BlockPistonBehavior.class)
          .vanillaBlock(VanillaBlockId.PISTON)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPistonArmCollisionBehavior> PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockPistonArmCollisionBehavior.class)
          .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherCropBehavior> PITCHER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockPitcherCropBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_CROP)
          .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockPitcherPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_PLANT)
          .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPlanksBehavior> PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.PLANKS)
          .setProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPodzolBehavior> PODZOL_TYPE = BlockTypeBuilder
          .builder(BlockPodzolBehavior.class)
          .vanillaBlock(VanillaBlockId.PODZOL)
          .addBasicComponents()
          .build();

  BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPointedDripstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
          .setProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedAndesiteStairsBehavior> POLISHED_ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedAndesiteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBasaltBehavior> POLISHED_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BASALT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickDoubleSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickSlabBehavior> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickStairsBehavior> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickWallBehavior> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBricksBehavior> POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneButtonBehavior> POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneDoubleSlabBehavior> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstonePressurePlateBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneSlabBehavior> POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneStairsBehavior> POLISHED_BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneWallBehavior> POLISHED_BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateBehavior> POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateDoubleSlabBehavior> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateSlabBehavior> POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateStairsBehavior> POLISHED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateWallBehavior> POLISHED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDioriteStairsBehavior> POLISHED_DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDioriteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedGraniteStairsBehavior> POLISHED_GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedGraniteStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPortalBehavior> PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.PORTAL)
          .setProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPotatoesBehavior> POTATOES_TYPE = BlockTypeBuilder
          .builder(BlockPotatoesBehavior.class)
          .vanillaBlock(VanillaBlockId.POTATOES)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPowderSnowBehavior> POWDER_SNOW_TYPE = BlockTypeBuilder
          .builder(BlockPowderSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.POWDER_SNOW)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredComparatorBehavior> POWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockPoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredRepeaterBehavior> POWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockPoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_REPEATER)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineBehavior> PRISMARINE_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE)
          .setProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineBricksStairsBehavior> PRISMARINE_BRICKS_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineBricksStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineStairsBehavior> PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkinBehavior> PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinStemBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandleBehavior> PURPLE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandleCakeBehavior> PURPLE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCarpetBehavior> PURPLE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleConcreteBehavior> PURPLE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleGlazedTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPurpleGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleShulkerBoxBehavior> PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockPurpleShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleWoolBehavior> PURPLE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPurpleWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockPurpurBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurStairsBehavior> PURPUR_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPurpurStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPUR_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBricksBehavior> QUARTZ_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzOreBehavior> QUARTZ_ORE_TYPE = BlockTypeBuilder
          .builder(BlockQuartzOreBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzStairsBehavior> QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRailBehavior> RAIL_TYPE = BlockTypeBuilder
          .builder(BlockRailBehavior.class)
          .vanillaBlock(VanillaBlockId.RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
          .addBasicComponents()
          .build();

  BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockRawGoldBlockBehavior> RAW_GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawGoldBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockRawIronBlockBehavior> RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandleBehavior> RED_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandleCakeBehavior> RED_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCarpetBehavior> RED_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockRedCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockRedConcreteBehavior> RED_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockRedConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedFlowerBehavior> RED_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockRedFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_FLOWER)
          .setProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedGlazedTerracottaBehavior> RED_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockRedGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroomBehavior> RED_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrickStairsBehavior> RED_NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstoneBehavior> RED_SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE)
          .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstoneStairsBehavior> RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedShulkerBoxBehavior> RED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockRedShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockRedWoolBehavior> RED_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockRedWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_LAMP)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneOreBehavior> REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_ORE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneTorchBehavior> REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneWireBehavior> REDSTONE_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneWireBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_WIRE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockReedsBehavior> REEDS_TYPE = BlockTypeBuilder
          .builder(BlockReedsBehavior.class)
          .vanillaBlock(VanillaBlockId.REEDS)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockReinforcedDeepslateBehavior> REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockReinforcedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE)
          .addBasicComponents()
          .build();

  BlockType<BlockRepeatingCommandBlockBehavior> REPEATING_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRepeatingCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockReserved6Behavior> RESERVED6_TYPE = BlockTypeBuilder
          .builder(BlockReserved6Behavior.class)
          .vanillaBlock(VanillaBlockId.RESERVED6)
          .addBasicComponents()
          .build();

  BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
          .builder(BlockRespawnAnchorBehavior.class)
          .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR)
          .setProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandBehavior> SAND_TYPE = BlockTypeBuilder
          .builder(BlockSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SAND)
          .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstoneBehavior> SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE)
          .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstoneStairsBehavior> SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSaplingBehavior> SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockSaplingBehavior.class)
          .vanillaBlock(VanillaBlockId.SAPLING)
          .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockScaffoldingBehavior> SCAFFOLDING_TYPE = BlockTypeBuilder
          .builder(BlockScaffoldingBehavior.class)
          .vanillaBlock(VanillaBlockId.SCAFFOLDING)
          .setProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkBehavior> SCULK_TYPE = BlockTypeBuilder
          .builder(BlockSculkBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST_TYPE = BlockTypeBuilder
          .builder(BlockSculkCatalystBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
          .setProperties(VanillaBlockPropertyTypes.BLOOM)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkSensorBehavior> SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_SENSOR)
          .setProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkShriekerBehavior> SCULK_SHRIEKER_TYPE = BlockTypeBuilder
          .builder(BlockSculkShriekerBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER)
          .setProperties(VanillaBlockPropertyTypes.ACTIVE, VanillaBlockPropertyTypes.CAN_SUMMON)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkVeinBehavior> SCULK_VEIN_TYPE = BlockTypeBuilder
          .builder(BlockSculkVeinBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_VEIN)
          .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaLanternBehavior> SEA_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSeaLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_LANTERN)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaPickleBehavior> SEA_PICKLE_TYPE = BlockTypeBuilder
          .builder(BlockSeaPickleBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_PICKLE)
          .setProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSeagrassBehavior> SEAGRASS_TYPE = BlockTypeBuilder
          .builder(BlockSeagrassBehavior.class)
          .vanillaBlock(VanillaBlockId.SEAGRASS)
          .setProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockShroomlightBehavior> SHROOMLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockShroomlightBehavior.class)
          .vanillaBlock(VanillaBlockId.SHROOMLIGHT)
          .addBasicComponents()
          .build();

  BlockType<BlockSilverGlazedTerracottaBehavior> SILVER_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockSilverGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSkullBehavior> SKULL_TYPE = BlockTypeBuilder
          .builder(BlockSkullBehavior.class)
          .vanillaBlock(VanillaBlockId.SKULL)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSlimeBehavior> SLIME_TYPE = BlockTypeBuilder
          .builder(BlockSlimeBehavior.class)
          .vanillaBlock(VanillaBlockId.SLIME)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockSmallAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallDripleafBlockBehavior> SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockSmallDripleafBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSmithingTableBehavior> SMITHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockSmithingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.SMITHING_TABLE)
          .addBasicComponents()
          .build();

  BlockType<BlockSmokerBehavior> SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOKER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothBasaltBehavior> SMOOTH_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockSmoothBasaltBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_BASALT)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothQuartzStairsBehavior> SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothQuartzStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothRedSandstoneStairsBehavior> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothRedSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothSandstoneStairsBehavior> SMOOTH_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothSandstoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE_TYPE = BlockTypeBuilder
          .builder(BlockSmoothStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
          .addBasicComponents()
          .build();

  BlockType<BlockSnifferEggBehavior> SNIFFER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockSnifferEggBehavior.class)
          .vanillaBlock(VanillaBlockId.SNIFFER_EGG)
          .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
          .addBasicComponents()
          .build();

  BlockType<BlockSnowBehavior> SNOW_TYPE = BlockTypeBuilder
          .builder(BlockSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW)
          .addBasicComponents()
          .build();

  BlockType<BlockSnowLayerBehavior> SNOW_LAYER_TYPE = BlockTypeBuilder
          .builder(BlockSnowLayerBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW_LAYER)
          .setProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulFireBehavior> SOUL_FIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulFireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_FIRE)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulLanternBehavior> SOUL_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSoulLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_LANTERN)
          .setProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSandBehavior> SOUL_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSoulSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SAND)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSoilBehavior> SOUL_SOIL_TYPE = BlockTypeBuilder
          .builder(BlockSoulSoilBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SOIL)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulTorchBehavior> SOUL_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockSoulTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpongeBehavior> SPONGE_TYPE = BlockTypeBuilder
          .builder(BlockSpongeBehavior.class)
          .vanillaBlock(VanillaBlockId.SPONGE)
          .setProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM_TYPE = BlockTypeBuilder
          .builder(BlockSporeBlossomBehavior.class)
          .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceButtonBehavior> SPRUCE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockSpruceButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceDoorBehavior> SPRUCE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFenceBehavior> SPRUCE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFenceGateBehavior> SPRUCE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceHangingSignBehavior> SPRUCE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceLogBehavior> SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockSpruceLogBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockSprucePressurePlateBehavior> SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockSprucePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStairsBehavior> SPRUCE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStandingSignBehavior> SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceTrapdoorBehavior> SPRUCE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceWallSignBehavior> SPRUCE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlassBehavior> STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlassPaneBehavior> STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS_PANE)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedHardenedClayBehavior> STAINED_HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockStainedHardenedClayBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_HARDENED_CLAY)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingBannerBehavior> STANDING_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockStandingBannerBehavior.class)
          .vanillaBlock(VanillaBlockId.STANDING_BANNER)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingSignBehavior> STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPistonBehavior> STICKY_PISTON_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPistonArmCollisionBehavior> STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonArmCollisionBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBehavior> STONE_TYPE = BlockTypeBuilder
          .builder(BlockStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE)
          .setProperties(VanillaBlockPropertyTypes.STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlabBehavior> STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab2Behavior> STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab2Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab3Behavior> STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab4Behavior> STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab4Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBrickStairsBehavior> STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneButtonBehavior> STONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockStoneButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonePressurePlateBehavior> STONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockStonePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneStairsBehavior> STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonebrickBehavior> STONEBRICK_TYPE = BlockTypeBuilder
          .builder(BlockStonebrickBehavior.class)
          .vanillaBlock(VanillaBlockId.STONEBRICK)
          .setProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutterBehavior> STONECUTTER_TYPE = BlockTypeBuilder
          .builder(BlockStonecutterBehavior.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutterBlockBehavior> STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStonecutterBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedAcaciaLogBehavior> STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedAcaciaLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBambooBlockBehavior> STRIPPED_BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBambooBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBirchLogBehavior> STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryLogBehavior> STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryWoodBehavior> STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonHyphaeBehavior> STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonStemBehavior> STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedDarkOakLogBehavior> STRIPPED_DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedDarkOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedJungleLogBehavior> STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedJungleLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveLogBehavior> STRIPPED_MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveWoodBehavior> STRIPPED_MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedOakLogBehavior> STRIPPED_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_OAK_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedSpruceLogBehavior> STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedSpruceLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedHyphaeBehavior> STRIPPED_WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedStemBehavior> STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStructureBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureVoidBehavior> STRUCTURE_VOID_TYPE = BlockTypeBuilder
          .builder(BlockStructureVoidBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_VOID)
          .setProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousGravelBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL)
          .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND)
          .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSweetBerryBushBehavior> SWEET_BERRY_BUSH_TYPE = BlockTypeBuilder
          .builder(BlockSweetBerryBushBehavior.class)
          .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTallgrassBehavior> TALLGRASS_TYPE = BlockTypeBuilder
          .builder(BlockTallgrassBehavior.class)
          .vanillaBlock(VanillaBlockId.TALLGRASS)
          .setProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockTargetBehavior> TARGET_TYPE = BlockTypeBuilder
          .builder(BlockTargetBehavior.class)
          .vanillaBlock(VanillaBlockId.TARGET)
          .addBasicComponents()
          .build();

  BlockType<BlockTintedGlassBehavior> TINTED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockTintedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.TINTED_GLASS)
          .addBasicComponents()
          .build();

  BlockType<BlockTntBehavior> TNT_TYPE = BlockTypeBuilder
          .builder(BlockTntBehavior.class)
          .vanillaBlock(VanillaBlockId.TNT)
          .setProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchBehavior> TORCH_TYPE = BlockTypeBuilder
          .builder(BlockTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflowerBehavior> TORCHFLOWER_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflowerCropBehavior> TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerCropBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTrapdoorBehavior> TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockTrappedChestBehavior.class)
          .vanillaBlock(VanillaBlockId.TRAPPED_CHEST)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTripWireBehavior> TRIP_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockTripWireBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIP_WIRE)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
          .builder(BlockTripwireHookBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTubeCoralBehavior> TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockTubeCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.TUBE_CORAL)
          .addBasicComponents()
          .build();

  BlockType<BlockTuffBehavior> TUFF_TYPE = BlockTypeBuilder
          .builder(BlockTuffBehavior.class)
          .vanillaBlock(VanillaBlockId.TUFF)
          .addBasicComponents()
          .build();

  BlockType<BlockTurtleEggBehavior> TURTLE_EGG_TYPE = BlockTypeBuilder
          .builder(BlockTurtleEggBehavior.class)
          .vanillaBlock(VanillaBlockId.TURTLE_EGG)
          .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockTwistingVinesBehavior> TWISTING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockTwistingVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.TWISTING_VINES)
          .setProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockUnderwaterTorchBehavior> UNDERWATER_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnderwaterTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUndyedShulkerBoxBehavior> UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockUndyedShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockUnknownBehavior> UNKNOWN_TYPE = BlockTypeBuilder
          .builder(BlockUnknownBehavior.class)
          .vanillaBlock(VanillaBlockId.UNKNOWN)
          .addBasicComponents()
          .build();

  BlockType<BlockUnlitRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnlitRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .addBasicComponents()
          .build();

  BlockType<BlockVerdantFroglightBehavior> VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockVerdantFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockVineBehavior> VINE_TYPE = BlockTypeBuilder
          .builder(BlockVineBehavior.class)
          .vanillaBlock(VanillaBlockId.VINE)
          .setProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockWallBannerBehavior> WALL_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockWallBannerBehavior.class)
          .vanillaBlock(VanillaBlockId.WALL_BANNER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWallSignBehavior> WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedButtonBehavior> WARPED_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWarpedButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoorBehavior> WARPED_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoubleSlabBehavior> WARPED_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFenceBehavior> WARPED_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFenceGateBehavior> WARPED_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FUNGUS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHangingSignBehavior> WARPED_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHyphaeBehavior> WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_NYLIUM)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPlanksBehavior> WARPED_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_PLANKS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPressurePlateBehavior> WARPED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedRootsBehavior> WARPED_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_ROOTS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedSlabBehavior> WARPED_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStairsBehavior> WARPED_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStandingSignBehavior> WARPED_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStemBehavior> WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedTrapdoorBehavior> WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWallSignBehavior> WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK)
          .addBasicComponents()
          .build();

  BlockType<BlockWaterBehavior> WATER_TYPE = BlockTypeBuilder
          .builder(BlockWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.WATER)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWaterlilyBehavior> WATERLILY_TYPE = BlockTypeBuilder
          .builder(BlockWaterlilyBehavior.class)
          .vanillaBlock(VanillaBlockId.WATERLILY)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCopperBehavior> WAXED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperBehavior> WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperSlabBehavior> WAXED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperStairsBehavior> WAXED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedDoubleCutCopperSlabBehavior> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCopperBehavior> WAXED_EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperBehavior> WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperStairsBehavior> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedDoubleCutCopperSlabBehavior> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCopperBehavior> WAXED_OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperBehavior> WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperStairsBehavior> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedDoubleCutCopperSlabBehavior> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCopperBehavior> WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperBehavior> WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperSlabBehavior> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperStairsBehavior> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredDoubleCutCopperSlabBehavior> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCopperBehavior> WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperBehavior> WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperSlabBehavior> WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperStairsBehavior> WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredDoubleCutCopperSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWebBehavior> WEB_TYPE = BlockTypeBuilder
          .builder(BlockWebBehavior.class)
          .vanillaBlock(VanillaBlockId.WEB)
          .addBasicComponents()
          .build();

  BlockType<BlockWeepingVinesBehavior> WEEPING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockWeepingVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.WEEPING_VINES)
          .setProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockWheatBehavior> WHEAT_TYPE = BlockTypeBuilder
          .builder(BlockWheatBehavior.class)
          .vanillaBlock(VanillaBlockId.WHEAT)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandleBehavior> WHITE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandleCakeBehavior> WHITE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCarpetBehavior> WHITE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteConcreteBehavior> WHITE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteGlazedTerracottaBehavior> WHITE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockWhiteGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteShulkerBoxBehavior> WHITE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockWhiteShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteWoolBehavior> WHITE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockWhiteWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_WOOL)
          .addBasicComponents()
          .build();

  BlockType<BlockWitherRoseBehavior> WITHER_ROSE_TYPE = BlockTypeBuilder
          .builder(BlockWitherRoseBehavior.class)
          .vanillaBlock(VanillaBlockId.WITHER_ROSE)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodBehavior> WOOD_TYPE = BlockTypeBuilder
          .builder(BlockWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenButtonBehavior> WOODEN_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWoodenButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenDoorBehavior> WOODEN_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWoodenDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenPressurePlateBehavior> WOODEN_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWoodenPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenSlabBehavior> WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWoodenSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WOODEN_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandleBehavior> YELLOW_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandleCakeBehavior> YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCarpetBehavior> YELLOW_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockYellowCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CARPET)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowConcreteBehavior> YELLOW_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockYellowConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowFlowerBehavior> YELLOW_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockYellowFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_FLOWER)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowGlazedTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockYellowGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowShulkerBoxBehavior> YELLOW_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockYellowShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowWoolBehavior> YELLOW_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockYellowWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_WOOL)
          .addBasicComponents()
          .build();
}
