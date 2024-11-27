package org.allaymc.server.item.type;

import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.impl.ItemAcaciaPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemActivatorRailStackImpl;
import org.allaymc.server.item.impl.ItemAirStackImpl;
import org.allaymc.server.item.impl.ItemAlliumStackImpl;
import org.allaymc.server.item.impl.ItemAllowStackImpl;
import org.allaymc.server.item.impl.ItemAmethystBlockStackImpl;
import org.allaymc.server.item.impl.ItemAmethystClusterStackImpl;
import org.allaymc.server.item.impl.ItemAmethystShardStackImpl;
import org.allaymc.server.item.impl.ItemAncientDebrisStackImpl;
import org.allaymc.server.item.impl.ItemAndesiteStackImpl;
import org.allaymc.server.item.impl.ItemAnglerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemAppleStackImpl;
import org.allaymc.server.item.impl.ItemArcherPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemArmadilloScuteStackImpl;
import org.allaymc.server.item.impl.ItemArmorStandStackImpl;
import org.allaymc.server.item.impl.ItemArmsUpPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemArrowStackImpl;
import org.allaymc.server.item.impl.ItemAzaleaStackImpl;
import org.allaymc.server.item.impl.ItemAzureBluetStackImpl;
import org.allaymc.server.item.impl.ItemBakedPotatoStackImpl;
import org.allaymc.server.item.impl.ItemBalloonStackImpl;
import org.allaymc.server.item.impl.ItemBambooBlockStackImpl;
import org.allaymc.server.item.impl.ItemBambooChestRaftStackImpl;
import org.allaymc.server.item.impl.ItemBambooMosaicStackImpl;
import org.allaymc.server.item.impl.ItemBambooPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemBambooRaftStackImpl;
import org.allaymc.server.item.impl.ItemBambooStackImpl;
import org.allaymc.server.item.impl.ItemBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemBannerStackImpl;
import org.allaymc.server.item.impl.ItemBarrelStackImpl;
import org.allaymc.server.item.impl.ItemBarrierStackImpl;
import org.allaymc.server.item.impl.ItemBasaltStackImpl;
import org.allaymc.server.item.impl.ItemBeaconStackImpl;
import org.allaymc.server.item.impl.ItemBedStackImpl;
import org.allaymc.server.item.impl.ItemBedrockStackImpl;
import org.allaymc.server.item.impl.ItemBeeNestStackImpl;
import org.allaymc.server.item.impl.ItemBeefStackImpl;
import org.allaymc.server.item.impl.ItemBeehiveStackImpl;
import org.allaymc.server.item.impl.ItemBeetrootSeedsStackImpl;
import org.allaymc.server.item.impl.ItemBeetrootSoupStackImpl;
import org.allaymc.server.item.impl.ItemBeetrootStackImpl;
import org.allaymc.server.item.impl.ItemBellStackImpl;
import org.allaymc.server.item.impl.ItemBigDripleafStackImpl;
import org.allaymc.server.item.impl.ItemBirchPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemBlackDyeStackImpl;
import org.allaymc.server.item.impl.ItemBlackstoneStackImpl;
import org.allaymc.server.item.impl.ItemBladePotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemBlazePowderStackImpl;
import org.allaymc.server.item.impl.ItemBlazeRodStackImpl;
import org.allaymc.server.item.impl.ItemBleachStackImpl;
import org.allaymc.server.item.impl.ItemBlueDyeStackImpl;
import org.allaymc.server.item.impl.ItemBlueIceStackImpl;
import org.allaymc.server.item.impl.ItemBlueOrchidStackImpl;
import org.allaymc.server.item.impl.ItemBoltArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemBoneBlockStackImpl;
import org.allaymc.server.item.impl.ItemBoneMealStackImpl;
import org.allaymc.server.item.impl.ItemBoneStackImpl;
import org.allaymc.server.item.impl.ItemBookStackImpl;
import org.allaymc.server.item.impl.ItemBookshelfStackImpl;
import org.allaymc.server.item.impl.ItemBorderBlockStackImpl;
import org.allaymc.server.item.impl.ItemBordureIndentedBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemBowStackImpl;
import org.allaymc.server.item.impl.ItemBowlStackImpl;
import org.allaymc.server.item.impl.ItemBreadStackImpl;
import org.allaymc.server.item.impl.ItemBreezeRodStackImpl;
import org.allaymc.server.item.impl.ItemBrewerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemBrewingStandStackImpl;
import org.allaymc.server.item.impl.ItemBrickBlockStackImpl;
import org.allaymc.server.item.impl.ItemBrickStackImpl;
import org.allaymc.server.item.impl.ItemBrownDyeStackImpl;
import org.allaymc.server.item.impl.ItemBrownMushroomBlockStackImpl;
import org.allaymc.server.item.impl.ItemBrownMushroomStackImpl;
import org.allaymc.server.item.impl.ItemBrushStackImpl;
import org.allaymc.server.item.impl.ItemBubbleColumnStackImpl;
import org.allaymc.server.item.impl.ItemBuddingAmethystStackImpl;
import org.allaymc.server.item.impl.ItemBurnPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemCactusStackImpl;
import org.allaymc.server.item.impl.ItemCakeStackImpl;
import org.allaymc.server.item.impl.ItemCalciteStackImpl;
import org.allaymc.server.item.impl.ItemCalibratedSculkSensorStackImpl;
import org.allaymc.server.item.impl.ItemCameraStackImpl;
import org.allaymc.server.item.impl.ItemCampfireStackImpl;
import org.allaymc.server.item.impl.ItemCarrotOnAStickStackImpl;
import org.allaymc.server.item.impl.ItemCarrotStackImpl;
import org.allaymc.server.item.impl.ItemCarrotsStackImpl;
import org.allaymc.server.item.impl.ItemCartographyTableStackImpl;
import org.allaymc.server.item.impl.ItemCarvedPumpkinStackImpl;
import org.allaymc.server.item.impl.ItemCauldronStackImpl;
import org.allaymc.server.item.impl.ItemCaveVinesBodyWithBerriesStackImpl;
import org.allaymc.server.item.impl.ItemCaveVinesHeadWithBerriesStackImpl;
import org.allaymc.server.item.impl.ItemCaveVinesStackImpl;
import org.allaymc.server.item.impl.ItemChainCommandBlockStackImpl;
import org.allaymc.server.item.impl.ItemChainStackImpl;
import org.allaymc.server.item.impl.ItemCharcoalStackImpl;
import org.allaymc.server.item.impl.ItemChemicalHeatStackImpl;
import org.allaymc.server.item.impl.ItemChemistryTableStackImpl;
import org.allaymc.server.item.impl.ItemCherryPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemChestStackImpl;
import org.allaymc.server.item.impl.ItemChickenStackImpl;
import org.allaymc.server.item.impl.ItemChiseledBookshelfStackImpl;
import org.allaymc.server.item.impl.ItemChiseledDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemChiseledPolishedBlackstoneStackImpl;
import org.allaymc.server.item.impl.ItemChiseledQuartzBlockStackImpl;
import org.allaymc.server.item.impl.ItemChiseledTuffStackImpl;
import org.allaymc.server.item.impl.ItemChorusFlowerStackImpl;
import org.allaymc.server.item.impl.ItemChorusFruitStackImpl;
import org.allaymc.server.item.impl.ItemChorusPlantStackImpl;
import org.allaymc.server.item.impl.ItemClayBallStackImpl;
import org.allaymc.server.item.impl.ItemClayStackImpl;
import org.allaymc.server.item.impl.ItemClientRequestPlaceholderBlockStackImpl;
import org.allaymc.server.item.impl.ItemClockStackImpl;
import org.allaymc.server.item.impl.ItemCoalBlockStackImpl;
import org.allaymc.server.item.impl.ItemCoalOreStackImpl;
import org.allaymc.server.item.impl.ItemCoalStackImpl;
import org.allaymc.server.item.impl.ItemCoastArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemCobbledDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemCobblestoneStackImpl;
import org.allaymc.server.item.impl.ItemCocoaBeansStackImpl;
import org.allaymc.server.item.impl.ItemCocoaStackImpl;
import org.allaymc.server.item.impl.ItemCodStackImpl;
import org.allaymc.server.item.impl.ItemCommandBlockStackImpl;
import org.allaymc.server.item.impl.ItemComparatorStackImpl;
import org.allaymc.server.item.impl.ItemCompassStackImpl;
import org.allaymc.server.item.impl.ItemComposterStackImpl;
import org.allaymc.server.item.impl.ItemCompoundCreatorStackImpl;
import org.allaymc.server.item.impl.ItemCompoundStackImpl;
import org.allaymc.server.item.impl.ItemConduitStackImpl;
import org.allaymc.server.item.impl.ItemCookedBeefStackImpl;
import org.allaymc.server.item.impl.ItemCookedChickenStackImpl;
import org.allaymc.server.item.impl.ItemCookedCodStackImpl;
import org.allaymc.server.item.impl.ItemCookedMuttonStackImpl;
import org.allaymc.server.item.impl.ItemCookedPorkchopStackImpl;
import org.allaymc.server.item.impl.ItemCookedRabbitStackImpl;
import org.allaymc.server.item.impl.ItemCookedSalmonStackImpl;
import org.allaymc.server.item.impl.ItemCookieStackImpl;
import org.allaymc.server.item.impl.ItemCopperBlockStackImpl;
import org.allaymc.server.item.impl.ItemCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemCopperIngotStackImpl;
import org.allaymc.server.item.impl.ItemCopperOreStackImpl;
import org.allaymc.server.item.impl.ItemCornflowerStackImpl;
import org.allaymc.server.item.impl.ItemCrackedDeepslateTilesStackImpl;
import org.allaymc.server.item.impl.ItemCrafterStackImpl;
import org.allaymc.server.item.impl.ItemCraftingTableStackImpl;
import org.allaymc.server.item.impl.ItemCreeperBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonFungusStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonHyphaeStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonNyliumStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonRootsStackImpl;
import org.allaymc.server.item.impl.ItemCrimsonStemStackImpl;
import org.allaymc.server.item.impl.ItemCrossbowStackImpl;
import org.allaymc.server.item.impl.ItemCryingObsidianStackImpl;
import org.allaymc.server.item.impl.ItemCyanDyeStackImpl;
import org.allaymc.server.item.impl.ItemDandelionStackImpl;
import org.allaymc.server.item.impl.ItemDangerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemDarkOakPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemDarkPrismarineStackImpl;
import org.allaymc.server.item.impl.ItemDaylightDetectorInvertedStackImpl;
import org.allaymc.server.item.impl.ItemDaylightDetectorStackImpl;
import org.allaymc.server.item.impl.ItemDeadbushStackImpl;
import org.allaymc.server.item.impl.ItemDecoratedPotStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateCoalOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateCopperOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateDiamondOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateEmeraldOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateGoldOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateIronOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateLapisOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateRedstoneOreStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemDeepslateTilesStackImpl;
import org.allaymc.server.item.impl.ItemDenyStackImpl;
import org.allaymc.server.item.impl.ItemDeprecatedPurpurBlock1StackImpl;
import org.allaymc.server.item.impl.ItemDeprecatedPurpurBlock2StackImpl;
import org.allaymc.server.item.impl.ItemDetectorRailStackImpl;
import org.allaymc.server.item.impl.ItemDiamondBlockStackImpl;
import org.allaymc.server.item.impl.ItemDiamondHorseArmorStackImpl;
import org.allaymc.server.item.impl.ItemDiamondOreStackImpl;
import org.allaymc.server.item.impl.ItemDiamondStackImpl;
import org.allaymc.server.item.impl.ItemDioriteStackImpl;
import org.allaymc.server.item.impl.ItemDirtWithRootsStackImpl;
import org.allaymc.server.item.impl.ItemDiscFragment5StackImpl;
import org.allaymc.server.item.impl.ItemDispenserStackImpl;
import org.allaymc.server.item.impl.ItemDoublePlantStackImpl;
import org.allaymc.server.item.impl.ItemDragonBreathStackImpl;
import org.allaymc.server.item.impl.ItemDragonEggStackImpl;
import org.allaymc.server.item.impl.ItemDriedKelpBlockStackImpl;
import org.allaymc.server.item.impl.ItemDriedKelpStackImpl;
import org.allaymc.server.item.impl.ItemDripstoneBlockStackImpl;
import org.allaymc.server.item.impl.ItemDropperStackImpl;
import org.allaymc.server.item.impl.ItemDuneArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemDyeStackImpl;
import org.allaymc.server.item.impl.ItemEchoShardStackImpl;
import org.allaymc.server.item.impl.ItemEggStackImpl;
import org.allaymc.server.item.impl.ItemElytraStackImpl;
import org.allaymc.server.item.impl.ItemEmeraldBlockStackImpl;
import org.allaymc.server.item.impl.ItemEmeraldOreStackImpl;
import org.allaymc.server.item.impl.ItemEmeraldStackImpl;
import org.allaymc.server.item.impl.ItemEmptyMapStackImpl;
import org.allaymc.server.item.impl.ItemEnchantedBookStackImpl;
import org.allaymc.server.item.impl.ItemEnchantedGoldenAppleStackImpl;
import org.allaymc.server.item.impl.ItemEnchantingTableStackImpl;
import org.allaymc.server.item.impl.ItemEndCrystalStackImpl;
import org.allaymc.server.item.impl.ItemEndGatewayStackImpl;
import org.allaymc.server.item.impl.ItemEndPortalFrameStackImpl;
import org.allaymc.server.item.impl.ItemEndPortalStackImpl;
import org.allaymc.server.item.impl.ItemEndRodStackImpl;
import org.allaymc.server.item.impl.ItemEndStoneStackImpl;
import org.allaymc.server.item.impl.ItemEnderChestStackImpl;
import org.allaymc.server.item.impl.ItemEnderEyeStackImpl;
import org.allaymc.server.item.impl.ItemEnderPearlStackImpl;
import org.allaymc.server.item.impl.ItemExperienceBottleStackImpl;
import org.allaymc.server.item.impl.ItemExplorerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemExposedCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemExposedCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemEyeArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemFarmlandStackImpl;
import org.allaymc.server.item.impl.ItemFeatherStackImpl;
import org.allaymc.server.item.impl.ItemFermentedSpiderEyeStackImpl;
import org.allaymc.server.item.impl.ItemFernStackImpl;
import org.allaymc.server.item.impl.ItemFieldMasonedBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemFilledMapStackImpl;
import org.allaymc.server.item.impl.ItemFireChargeStackImpl;
import org.allaymc.server.item.impl.ItemFireStackImpl;
import org.allaymc.server.item.impl.ItemFireworkRocketStackImpl;
import org.allaymc.server.item.impl.ItemFireworkStarStackImpl;
import org.allaymc.server.item.impl.ItemFishingRodStackImpl;
import org.allaymc.server.item.impl.ItemFletchingTableStackImpl;
import org.allaymc.server.item.impl.ItemFlintAndSteelStackImpl;
import org.allaymc.server.item.impl.ItemFlintStackImpl;
import org.allaymc.server.item.impl.ItemFlowArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemFlowBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemFlowPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemFlowerBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemFlowerPotStackImpl;
import org.allaymc.server.item.impl.ItemFloweringAzaleaStackImpl;
import org.allaymc.server.item.impl.ItemFrameStackImpl;
import org.allaymc.server.item.impl.ItemFriendPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemFrogSpawnStackImpl;
import org.allaymc.server.item.impl.ItemFrostedIceStackImpl;
import org.allaymc.server.item.impl.ItemGhastTearStackImpl;
import org.allaymc.server.item.impl.ItemGildedBlackstoneStackImpl;
import org.allaymc.server.item.impl.ItemGlassBottleStackImpl;
import org.allaymc.server.item.impl.ItemGlisteringMelonSliceStackImpl;
import org.allaymc.server.item.impl.ItemGlobeBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemGlowBerriesStackImpl;
import org.allaymc.server.item.impl.ItemGlowFrameStackImpl;
import org.allaymc.server.item.impl.ItemGlowInkSacStackImpl;
import org.allaymc.server.item.impl.ItemGlowLichenStackImpl;
import org.allaymc.server.item.impl.ItemGlowStickStackImpl;
import org.allaymc.server.item.impl.ItemGlowingobsidianStackImpl;
import org.allaymc.server.item.impl.ItemGlowstoneDustStackImpl;
import org.allaymc.server.item.impl.ItemGlowstoneStackImpl;
import org.allaymc.server.item.impl.ItemGoatHornStackImpl;
import org.allaymc.server.item.impl.ItemGoldBlockStackImpl;
import org.allaymc.server.item.impl.ItemGoldIngotStackImpl;
import org.allaymc.server.item.impl.ItemGoldNuggetStackImpl;
import org.allaymc.server.item.impl.ItemGoldOreStackImpl;
import org.allaymc.server.item.impl.ItemGoldenAppleStackImpl;
import org.allaymc.server.item.impl.ItemGoldenCarrotStackImpl;
import org.allaymc.server.item.impl.ItemGoldenHorseArmorStackImpl;
import org.allaymc.server.item.impl.ItemGoldenRailStackImpl;
import org.allaymc.server.item.impl.ItemGraniteStackImpl;
import org.allaymc.server.item.impl.ItemGrassBlockStackImpl;
import org.allaymc.server.item.impl.ItemGrassPathStackImpl;
import org.allaymc.server.item.impl.ItemGravelStackImpl;
import org.allaymc.server.item.impl.ItemGrayDyeStackImpl;
import org.allaymc.server.item.impl.ItemGreenDyeStackImpl;
import org.allaymc.server.item.impl.ItemGrindstoneStackImpl;
import org.allaymc.server.item.impl.ItemGunpowderStackImpl;
import org.allaymc.server.item.impl.ItemGusterBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemGusterPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemHangingRootsStackImpl;
import org.allaymc.server.item.impl.ItemHardenedClayStackImpl;
import org.allaymc.server.item.impl.ItemHayBlockStackImpl;
import org.allaymc.server.item.impl.ItemHeartOfTheSeaStackImpl;
import org.allaymc.server.item.impl.ItemHeartPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemHeartbreakPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemHeavyCoreStackImpl;
import org.allaymc.server.item.impl.ItemHeavyWeightedPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemHoneyBlockStackImpl;
import org.allaymc.server.item.impl.ItemHoneyBottleStackImpl;
import org.allaymc.server.item.impl.ItemHoneycombBlockStackImpl;
import org.allaymc.server.item.impl.ItemHoneycombStackImpl;
import org.allaymc.server.item.impl.ItemHopperStackImpl;
import org.allaymc.server.item.impl.ItemHostArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemHowlPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemIceBombStackImpl;
import org.allaymc.server.item.impl.ItemIceStackImpl;
import org.allaymc.server.item.impl.ItemInfestedCobblestoneStackImpl;
import org.allaymc.server.item.impl.ItemInfestedDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemInfestedStoneStackImpl;
import org.allaymc.server.item.impl.ItemInfoUpdate2StackImpl;
import org.allaymc.server.item.impl.ItemInfoUpdateStackImpl;
import org.allaymc.server.item.impl.ItemInkSacStackImpl;
import org.allaymc.server.item.impl.ItemInvisibleBedrockStackImpl;
import org.allaymc.server.item.impl.ItemIronBarsStackImpl;
import org.allaymc.server.item.impl.ItemIronBlockStackImpl;
import org.allaymc.server.item.impl.ItemIronHorseArmorStackImpl;
import org.allaymc.server.item.impl.ItemIronIngotStackImpl;
import org.allaymc.server.item.impl.ItemIronNuggetStackImpl;
import org.allaymc.server.item.impl.ItemIronOreStackImpl;
import org.allaymc.server.item.impl.ItemItemBedStackImpl;
import org.allaymc.server.item.impl.ItemItemBeetrootStackImpl;
import org.allaymc.server.item.impl.ItemItemBrewingStandStackImpl;
import org.allaymc.server.item.impl.ItemItemCakeStackImpl;
import org.allaymc.server.item.impl.ItemItemCameraStackImpl;
import org.allaymc.server.item.impl.ItemItemCampfireStackImpl;
import org.allaymc.server.item.impl.ItemItemCauldronStackImpl;
import org.allaymc.server.item.impl.ItemItemChainStackImpl;
import org.allaymc.server.item.impl.ItemItemFlowerPotStackImpl;
import org.allaymc.server.item.impl.ItemItemFrameStackImpl;
import org.allaymc.server.item.impl.ItemItemGlowFrameStackImpl;
import org.allaymc.server.item.impl.ItemItemHopperStackImpl;
import org.allaymc.server.item.impl.ItemItemKelpStackImpl;
import org.allaymc.server.item.impl.ItemItemNetherSproutsStackImpl;
import org.allaymc.server.item.impl.ItemItemNetherWartStackImpl;
import org.allaymc.server.item.impl.ItemItemReedsStackImpl;
import org.allaymc.server.item.impl.ItemItemSoulCampfireStackImpl;
import org.allaymc.server.item.impl.ItemItemWheatStackImpl;
import org.allaymc.server.item.impl.ItemJigsawStackImpl;
import org.allaymc.server.item.impl.ItemJukeboxStackImpl;
import org.allaymc.server.item.impl.ItemJunglePressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemKelpStackImpl;
import org.allaymc.server.item.impl.ItemLabTableStackImpl;
import org.allaymc.server.item.impl.ItemLadderStackImpl;
import org.allaymc.server.item.impl.ItemLanternStackImpl;
import org.allaymc.server.item.impl.ItemLapisBlockStackImpl;
import org.allaymc.server.item.impl.ItemLapisLazuliStackImpl;
import org.allaymc.server.item.impl.ItemLapisOreStackImpl;
import org.allaymc.server.item.impl.ItemLargeAmethystBudStackImpl;
import org.allaymc.server.item.impl.ItemLargeFernStackImpl;
import org.allaymc.server.item.impl.ItemLeadStackImpl;
import org.allaymc.server.item.impl.ItemLeatherHorseArmorStackImpl;
import org.allaymc.server.item.impl.ItemLeatherStackImpl;
import org.allaymc.server.item.impl.ItemLecternStackImpl;
import org.allaymc.server.item.impl.ItemLeverStackImpl;
import org.allaymc.server.item.impl.ItemLightBlueDyeStackImpl;
import org.allaymc.server.item.impl.ItemLightGrayDyeStackImpl;
import org.allaymc.server.item.impl.ItemLightWeightedPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemLightningRodStackImpl;
import org.allaymc.server.item.impl.ItemLilacStackImpl;
import org.allaymc.server.item.impl.ItemLilyOfTheValleyStackImpl;
import org.allaymc.server.item.impl.ItemLimeDyeStackImpl;
import org.allaymc.server.item.impl.ItemLingeringPotionStackImpl;
import org.allaymc.server.item.impl.ItemLitDeepslateRedstoneOreStackImpl;
import org.allaymc.server.item.impl.ItemLitPumpkinStackImpl;
import org.allaymc.server.item.impl.ItemLitRedstoneLampStackImpl;
import org.allaymc.server.item.impl.ItemLitRedstoneOreStackImpl;
import org.allaymc.server.item.impl.ItemLodestoneCompassStackImpl;
import org.allaymc.server.item.impl.ItemLodestoneStackImpl;
import org.allaymc.server.item.impl.ItemLoomStackImpl;
import org.allaymc.server.item.impl.ItemMaceStackImpl;
import org.allaymc.server.item.impl.ItemMagentaDyeStackImpl;
import org.allaymc.server.item.impl.ItemMagmaCreamStackImpl;
import org.allaymc.server.item.impl.ItemMagmaStackImpl;
import org.allaymc.server.item.impl.ItemMangrovePressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemMangrovePropaguleStackImpl;
import org.allaymc.server.item.impl.ItemMangroveRootsStackImpl;
import org.allaymc.server.item.impl.ItemMaterialReducerStackImpl;
import org.allaymc.server.item.impl.ItemMedicineStackImpl;
import org.allaymc.server.item.impl.ItemMediumAmethystBudStackImpl;
import org.allaymc.server.item.impl.ItemMelonBlockStackImpl;
import org.allaymc.server.item.impl.ItemMelonSeedsStackImpl;
import org.allaymc.server.item.impl.ItemMelonSliceStackImpl;
import org.allaymc.server.item.impl.ItemMelonStemStackImpl;
import org.allaymc.server.item.impl.ItemMinerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemMobSpawnerStackImpl;
import org.allaymc.server.item.impl.ItemMojangBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemMonsterEggStackImpl;
import org.allaymc.server.item.impl.ItemMossBlockStackImpl;
import org.allaymc.server.item.impl.ItemMossyCobblestoneStackImpl;
import org.allaymc.server.item.impl.ItemMournerPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemMovingBlockStackImpl;
import org.allaymc.server.item.impl.ItemMudStackImpl;
import org.allaymc.server.item.impl.ItemMuddyMangroveRootsStackImpl;
import org.allaymc.server.item.impl.ItemMushroomStemStackImpl;
import org.allaymc.server.item.impl.ItemMushroomStewStackImpl;
import org.allaymc.server.item.impl.ItemMuttonStackImpl;
import org.allaymc.server.item.impl.ItemMyceliumStackImpl;
import org.allaymc.server.item.impl.ItemNameTagStackImpl;
import org.allaymc.server.item.impl.ItemNautilusShellStackImpl;
import org.allaymc.server.item.impl.ItemNetherBrickStackImpl;
import org.allaymc.server.item.impl.ItemNetherGoldOreStackImpl;
import org.allaymc.server.item.impl.ItemNetherSproutsStackImpl;
import org.allaymc.server.item.impl.ItemNetherStarStackImpl;
import org.allaymc.server.item.impl.ItemNetherWartBlockStackImpl;
import org.allaymc.server.item.impl.ItemNetherWartStackImpl;
import org.allaymc.server.item.impl.ItemNetherbrick0StackImpl;
import org.allaymc.server.item.impl.ItemNetheriteBlockStackImpl;
import org.allaymc.server.item.impl.ItemNetheriteIngotStackImpl;
import org.allaymc.server.item.impl.ItemNetheriteScrapStackImpl;
import org.allaymc.server.item.impl.ItemNetheriteUpgradeSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemNetherrackStackImpl;
import org.allaymc.server.item.impl.ItemNetherreactorStackImpl;
import org.allaymc.server.item.impl.ItemNoteblockStackImpl;
import org.allaymc.server.item.impl.ItemObserverStackImpl;
import org.allaymc.server.item.impl.ItemObsidianStackImpl;
import org.allaymc.server.item.impl.ItemOchreFroglightStackImpl;
import org.allaymc.server.item.impl.ItemOminousBottleStackImpl;
import org.allaymc.server.item.impl.ItemOminousTrialKeyStackImpl;
import org.allaymc.server.item.impl.ItemOrangeDyeStackImpl;
import org.allaymc.server.item.impl.ItemOrangeTulipStackImpl;
import org.allaymc.server.item.impl.ItemOxeyeDaisyStackImpl;
import org.allaymc.server.item.impl.ItemOxidizedCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemOxidizedCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemPackedIceStackImpl;
import org.allaymc.server.item.impl.ItemPackedMudStackImpl;
import org.allaymc.server.item.impl.ItemPaintingStackImpl;
import org.allaymc.server.item.impl.ItemPaperStackImpl;
import org.allaymc.server.item.impl.ItemPearlescentFroglightStackImpl;
import org.allaymc.server.item.impl.ItemPeonyStackImpl;
import org.allaymc.server.item.impl.ItemPhantomMembraneStackImpl;
import org.allaymc.server.item.impl.ItemPiglinBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemPinkDyeStackImpl;
import org.allaymc.server.item.impl.ItemPinkPetalsStackImpl;
import org.allaymc.server.item.impl.ItemPinkTulipStackImpl;
import org.allaymc.server.item.impl.ItemPitcherCropStackImpl;
import org.allaymc.server.item.impl.ItemPitcherPlantStackImpl;
import org.allaymc.server.item.impl.ItemPitcherPodStackImpl;
import org.allaymc.server.item.impl.ItemPlentyPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemPodzolStackImpl;
import org.allaymc.server.item.impl.ItemPointedDripstoneStackImpl;
import org.allaymc.server.item.impl.ItemPoisonousPotatoStackImpl;
import org.allaymc.server.item.impl.ItemPolishedAndesiteStackImpl;
import org.allaymc.server.item.impl.ItemPolishedBasaltStackImpl;
import org.allaymc.server.item.impl.ItemPolishedBlackstonePressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemPolishedBlackstoneStackImpl;
import org.allaymc.server.item.impl.ItemPolishedDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemPolishedDioriteStackImpl;
import org.allaymc.server.item.impl.ItemPolishedGraniteStackImpl;
import org.allaymc.server.item.impl.ItemPolishedTuffStackImpl;
import org.allaymc.server.item.impl.ItemPoppedChorusFruitStackImpl;
import org.allaymc.server.item.impl.ItemPoppyStackImpl;
import org.allaymc.server.item.impl.ItemPorkchopStackImpl;
import org.allaymc.server.item.impl.ItemPortalStackImpl;
import org.allaymc.server.item.impl.ItemPotatoStackImpl;
import org.allaymc.server.item.impl.ItemPotatoesStackImpl;
import org.allaymc.server.item.impl.ItemPotionStackImpl;
import org.allaymc.server.item.impl.ItemPowderSnowStackImpl;
import org.allaymc.server.item.impl.ItemPoweredComparatorStackImpl;
import org.allaymc.server.item.impl.ItemPoweredRepeaterStackImpl;
import org.allaymc.server.item.impl.ItemPrismarineCrystalsStackImpl;
import org.allaymc.server.item.impl.ItemPrismarineShardStackImpl;
import org.allaymc.server.item.impl.ItemPrismarineStackImpl;
import org.allaymc.server.item.impl.ItemPrizePotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemPufferfishStackImpl;
import org.allaymc.server.item.impl.ItemPumpkinPieStackImpl;
import org.allaymc.server.item.impl.ItemPumpkinSeedsStackImpl;
import org.allaymc.server.item.impl.ItemPumpkinStackImpl;
import org.allaymc.server.item.impl.ItemPumpkinStemStackImpl;
import org.allaymc.server.item.impl.ItemPurpleDyeStackImpl;
import org.allaymc.server.item.impl.ItemQuartzBlockStackImpl;
import org.allaymc.server.item.impl.ItemQuartzOreStackImpl;
import org.allaymc.server.item.impl.ItemQuartzPillarStackImpl;
import org.allaymc.server.item.impl.ItemQuartzStackImpl;
import org.allaymc.server.item.impl.ItemRabbitFootStackImpl;
import org.allaymc.server.item.impl.ItemRabbitHideStackImpl;
import org.allaymc.server.item.impl.ItemRabbitStackImpl;
import org.allaymc.server.item.impl.ItemRabbitStewStackImpl;
import org.allaymc.server.item.impl.ItemRailStackImpl;
import org.allaymc.server.item.impl.ItemRaiserArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemRapidFertilizerStackImpl;
import org.allaymc.server.item.impl.ItemRawCopperBlockStackImpl;
import org.allaymc.server.item.impl.ItemRawGoldBlockStackImpl;
import org.allaymc.server.item.impl.ItemRawGoldStackImpl;
import org.allaymc.server.item.impl.ItemRawIronBlockStackImpl;
import org.allaymc.server.item.impl.ItemRawIronStackImpl;
import org.allaymc.server.item.impl.ItemRecoveryCompassStackImpl;
import org.allaymc.server.item.impl.ItemRedDyeStackImpl;
import org.allaymc.server.item.impl.ItemRedFlowerStackImpl;
import org.allaymc.server.item.impl.ItemRedMushroomBlockStackImpl;
import org.allaymc.server.item.impl.ItemRedMushroomStackImpl;
import org.allaymc.server.item.impl.ItemRedNetherBrickStackImpl;
import org.allaymc.server.item.impl.ItemRedTulipStackImpl;
import org.allaymc.server.item.impl.ItemRedstoneBlockStackImpl;
import org.allaymc.server.item.impl.ItemRedstoneLampStackImpl;
import org.allaymc.server.item.impl.ItemRedstoneOreStackImpl;
import org.allaymc.server.item.impl.ItemRedstoneStackImpl;
import org.allaymc.server.item.impl.ItemRedstoneWireStackImpl;
import org.allaymc.server.item.impl.ItemReinforcedDeepslateStackImpl;
import org.allaymc.server.item.impl.ItemRepeaterStackImpl;
import org.allaymc.server.item.impl.ItemRepeatingCommandBlockStackImpl;
import org.allaymc.server.item.impl.ItemReserved6StackImpl;
import org.allaymc.server.item.impl.ItemRespawnAnchorStackImpl;
import org.allaymc.server.item.impl.ItemRibArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemRoseBushStackImpl;
import org.allaymc.server.item.impl.ItemRottenFleshStackImpl;
import org.allaymc.server.item.impl.ItemSaddleStackImpl;
import org.allaymc.server.item.impl.ItemSalmonStackImpl;
import org.allaymc.server.item.impl.ItemScaffoldingStackImpl;
import org.allaymc.server.item.impl.ItemScrapePotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemSculkCatalystStackImpl;
import org.allaymc.server.item.impl.ItemSculkSensorStackImpl;
import org.allaymc.server.item.impl.ItemSculkShriekerStackImpl;
import org.allaymc.server.item.impl.ItemSculkStackImpl;
import org.allaymc.server.item.impl.ItemSculkVeinStackImpl;
import org.allaymc.server.item.impl.ItemSeaLanternStackImpl;
import org.allaymc.server.item.impl.ItemSeaPickleStackImpl;
import org.allaymc.server.item.impl.ItemSeagrassStackImpl;
import org.allaymc.server.item.impl.ItemSentryArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemShaperArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemSheafPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemShearsStackImpl;
import org.allaymc.server.item.impl.ItemShelterPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemShieldStackImpl;
import org.allaymc.server.item.impl.ItemShortGrassStackImpl;
import org.allaymc.server.item.impl.ItemShroomlightStackImpl;
import org.allaymc.server.item.impl.ItemShulkerShellStackImpl;
import org.allaymc.server.item.impl.ItemSilenceArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemSkullBannerPatternStackImpl;
import org.allaymc.server.item.impl.ItemSkullPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemSlimeBallStackImpl;
import org.allaymc.server.item.impl.ItemSlimeStackImpl;
import org.allaymc.server.item.impl.ItemSmallAmethystBudStackImpl;
import org.allaymc.server.item.impl.ItemSmallDripleafBlockStackImpl;
import org.allaymc.server.item.impl.ItemSmithingTableStackImpl;
import org.allaymc.server.item.impl.ItemSmoothBasaltStackImpl;
import org.allaymc.server.item.impl.ItemSmoothQuartzStackImpl;
import org.allaymc.server.item.impl.ItemSmoothStoneStackImpl;
import org.allaymc.server.item.impl.ItemSnifferEggStackImpl;
import org.allaymc.server.item.impl.ItemSnortPotterySherdStackImpl;
import org.allaymc.server.item.impl.ItemSnoutArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemSnowLayerStackImpl;
import org.allaymc.server.item.impl.ItemSnowStackImpl;
import org.allaymc.server.item.impl.ItemSnowballStackImpl;
import org.allaymc.server.item.impl.ItemSoulCampfireStackImpl;
import org.allaymc.server.item.impl.ItemSoulFireStackImpl;
import org.allaymc.server.item.impl.ItemSoulLanternStackImpl;
import org.allaymc.server.item.impl.ItemSoulSoilStackImpl;
import org.allaymc.server.item.impl.ItemSparklerStackImpl;
import org.allaymc.server.item.impl.ItemSpiderEyeStackImpl;
import org.allaymc.server.item.impl.ItemSpireArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemSplashPotionStackImpl;
import org.allaymc.server.item.impl.ItemSporeBlossomStackImpl;
import org.allaymc.server.item.impl.ItemSprucePressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemSpyglassStackImpl;
import org.allaymc.server.item.impl.ItemStainedHardenedClayStackImpl;
import org.allaymc.server.item.impl.ItemStandingBannerStackImpl;
import org.allaymc.server.item.impl.ItemStickStackImpl;
import org.allaymc.server.item.impl.ItemStonePressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemStoneStackImpl;
import org.allaymc.server.item.impl.ItemStonebrickStackImpl;
import org.allaymc.server.item.impl.ItemStonecutterBlockStackImpl;
import org.allaymc.server.item.impl.ItemStonecutterStackImpl;
import org.allaymc.server.item.impl.ItemStringStackImpl;
import org.allaymc.server.item.impl.ItemStrippedBambooBlockStackImpl;
import org.allaymc.server.item.impl.ItemStrippedCrimsonHyphaeStackImpl;
import org.allaymc.server.item.impl.ItemStrippedCrimsonStemStackImpl;
import org.allaymc.server.item.impl.ItemStrippedWarpedHyphaeStackImpl;
import org.allaymc.server.item.impl.ItemStrippedWarpedStemStackImpl;
import org.allaymc.server.item.impl.ItemStructureBlockStackImpl;
import org.allaymc.server.item.impl.ItemStructureVoidStackImpl;
import org.allaymc.server.item.impl.ItemSugarCaneStackImpl;
import org.allaymc.server.item.impl.ItemSugarStackImpl;
import org.allaymc.server.item.impl.ItemSunflowerStackImpl;
import org.allaymc.server.item.impl.ItemSuspiciousGravelStackImpl;
import org.allaymc.server.item.impl.ItemSuspiciousStewStackImpl;
import org.allaymc.server.item.impl.ItemSweetBerriesStackImpl;
import org.allaymc.server.item.impl.ItemSweetBerryBushStackImpl;
import org.allaymc.server.item.impl.ItemTallGrassStackImpl;
import org.allaymc.server.item.impl.ItemTallgrass0StackImpl;
import org.allaymc.server.item.impl.ItemTargetStackImpl;
import org.allaymc.server.item.impl.ItemTideArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemTotemOfUndyingStackImpl;
import org.allaymc.server.item.impl.ItemTrappedChestStackImpl;
import org.allaymc.server.item.impl.ItemTrialKeyStackImpl;
import org.allaymc.server.item.impl.ItemTrialSpawnerStackImpl;
import org.allaymc.server.item.impl.ItemTridentStackImpl;
import org.allaymc.server.item.impl.ItemTripWireStackImpl;
import org.allaymc.server.item.impl.ItemTripwireHookStackImpl;
import org.allaymc.server.item.impl.ItemTropicalFishStackImpl;
import org.allaymc.server.item.impl.ItemTuffStackImpl;
import org.allaymc.server.item.impl.ItemTurtleEggStackImpl;
import org.allaymc.server.item.impl.ItemTurtleScuteStackImpl;
import org.allaymc.server.item.impl.ItemTwistingVinesStackImpl;
import org.allaymc.server.item.impl.ItemUnknownStackImpl;
import org.allaymc.server.item.impl.ItemUnpoweredComparatorStackImpl;
import org.allaymc.server.item.impl.ItemUnpoweredRepeaterStackImpl;
import org.allaymc.server.item.impl.ItemVaultStackImpl;
import org.allaymc.server.item.impl.ItemVerdantFroglightStackImpl;
import org.allaymc.server.item.impl.ItemVexArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemVineStackImpl;
import org.allaymc.server.item.impl.ItemWallBannerStackImpl;
import org.allaymc.server.item.impl.ItemWardArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemWarpedFungusOnAStickStackImpl;
import org.allaymc.server.item.impl.ItemWarpedFungusStackImpl;
import org.allaymc.server.item.impl.ItemWarpedHyphaeStackImpl;
import org.allaymc.server.item.impl.ItemWarpedNyliumStackImpl;
import org.allaymc.server.item.impl.ItemWarpedPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemWarpedRootsStackImpl;
import org.allaymc.server.item.impl.ItemWarpedStemStackImpl;
import org.allaymc.server.item.impl.ItemWarpedWartBlockStackImpl;
import org.allaymc.server.item.impl.ItemWaterlilyStackImpl;
import org.allaymc.server.item.impl.ItemWaxedCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemWaxedCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemWaxedExposedCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemWaxedExposedCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemWaxedOxidizedCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemWaxedOxidizedCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemWaxedWeatheredCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemWaxedWeatheredCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemWayfinderArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemWeatheredCopperBulbStackImpl;
import org.allaymc.server.item.impl.ItemWeatheredCopperGrateStackImpl;
import org.allaymc.server.item.impl.ItemWebStackImpl;
import org.allaymc.server.item.impl.ItemWeepingVinesStackImpl;
import org.allaymc.server.item.impl.ItemWheatSeedsStackImpl;
import org.allaymc.server.item.impl.ItemWheatStackImpl;
import org.allaymc.server.item.impl.ItemWhiteDyeStackImpl;
import org.allaymc.server.item.impl.ItemWhiteTulipStackImpl;
import org.allaymc.server.item.impl.ItemWildArmorTrimSmithingTemplateStackImpl;
import org.allaymc.server.item.impl.ItemWindChargeStackImpl;
import org.allaymc.server.item.impl.ItemWitherRoseStackImpl;
import org.allaymc.server.item.impl.ItemWolfArmorStackImpl;
import org.allaymc.server.item.impl.ItemWoodenPressurePlateStackImpl;
import org.allaymc.server.item.impl.ItemWritableBookStackImpl;
import org.allaymc.server.item.impl.ItemWrittenBookStackImpl;
import org.allaymc.server.item.impl.ItemYellowDyeStackImpl;
import org.allaymc.server.item.impl.anvil.ItemAnvilStackImpl;
import org.allaymc.server.item.impl.anvil.ItemChippedAnvilStackImpl;
import org.allaymc.server.item.impl.anvil.ItemDamagedAnvilStackImpl;
import org.allaymc.server.item.impl.anvil.ItemDeprecatedAnvilStackImpl;
import org.allaymc.server.item.impl.boat.ItemAcaciaBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemAcaciaChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemBirchBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemBirchChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemCherryBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemCherryChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemDarkOakBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemDarkOakChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemJungleBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemJungleChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemMangroveBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemMangroveChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemOakBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemOakChestBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemSpruceBoatStackImpl;
import org.allaymc.server.item.impl.boat.ItemSpruceChestBoatStackImpl;
import org.allaymc.server.item.impl.bricks.ItemChiseledNetherBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemChiseledStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemChiseledTuffBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemCrackedDeepslateBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemCrackedNetherBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemCrackedPolishedBlackstoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemCrackedStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemDeepslateBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemEndBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemInfestedChiseledStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemInfestedCrackedStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemInfestedMossyStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemInfestedStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemMossyStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemMudBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemPolishedBlackstoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemPrismarineBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemQuartzBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemStoneBricksStackImpl;
import org.allaymc.server.item.impl.bricks.ItemTuffBricksStackImpl;
import org.allaymc.server.item.impl.bundle.ItemBlackBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemBlueBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemBrownBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemCyanBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemGrayBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemGreenBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemLightBlueBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemLightGrayBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemLimeBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemMagentaBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemOrangeBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemPinkBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemPurpleBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemRedBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemWhiteBundleStackImpl;
import org.allaymc.server.item.impl.bundle.ItemYellowBundleStackImpl;
import org.allaymc.server.item.impl.button.ItemAcaciaButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemBambooButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemBirchButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemCherryButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemCrimsonButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemDarkOakButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemJungleButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemMangroveButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemPolishedBlackstoneButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemSpruceButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemStoneButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemWarpedButtonStackImpl;
import org.allaymc.server.item.impl.button.ItemWoodenButtonStackImpl;
import org.allaymc.server.item.impl.candle.ItemBlackCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemBlueCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemBrownCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemCyanCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemGrayCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemGreenCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemLightBlueCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemLightGrayCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemLimeCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemMagentaCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemOrangeCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemPinkCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemPurpleCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemRedCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemWhiteCandleStackImpl;
import org.allaymc.server.item.impl.candle.ItemYellowCandleStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemBlackCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemBlueCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemBrownCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemCyanCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemGrayCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemGreenCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemLightBlueCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemLightGrayCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemLimeCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemMagentaCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemOrangeCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemPinkCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemPurpleCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemRedCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemWhiteCandleCakeStackImpl;
import org.allaymc.server.item.impl.candlecake.ItemYellowCandleCakeStackImpl;
import org.allaymc.server.item.impl.carpet.ItemBlackCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemBlueCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemBrownCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemCyanCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemGrayCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemGreenCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemLightBlueCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemLightGrayCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemLimeCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemMagentaCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemMossCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemOrangeCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemPinkCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemPurpleCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemRedCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemWhiteCarpetStackImpl;
import org.allaymc.server.item.impl.carpet.ItemYellowCarpetStackImpl;
import org.allaymc.server.item.impl.concrete.ItemBlackConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemBlueConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemBrownConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemCyanConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemGrayConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemGreenConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemLightBlueConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemLightGrayConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemLimeConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemMagentaConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemOrangeConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemPinkConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemPurpleConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemRedConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemWhiteConcreteStackImpl;
import org.allaymc.server.item.impl.concrete.ItemYellowConcreteStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemBlackConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemBlueConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemBrownConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemCyanConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemGrayConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemGreenConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemLightBlueConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemLightGrayConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemLimeConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemMagentaConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemOrangeConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemPinkConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemPurpleConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemRedConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemWhiteConcretePowderStackImpl;
import org.allaymc.server.item.impl.concretepowder.ItemYellowConcretePowderStackImpl;
import org.allaymc.server.item.impl.copper.ItemChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemExposedChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemExposedCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemExposedCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemOxidizedChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemOxidizedCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemOxidizedCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemRawCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedExposedChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedExposedCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedExposedCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedOxidizedChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedOxidizedCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedOxidizedCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedWeatheredChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedWeatheredCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWaxedWeatheredCutCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWeatheredChiseledCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWeatheredCopperStackImpl;
import org.allaymc.server.item.impl.copper.ItemWeatheredCutCopperStackImpl;
import org.allaymc.server.item.impl.coral.ItemBrainCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemBubbleCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemDeadBrainCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemDeadBubbleCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemDeadFireCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemDeadHornCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemDeadTubeCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemFireCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemHornCoralStackImpl;
import org.allaymc.server.item.impl.coral.ItemTubeCoralStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemBrainCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemBubbleCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemDeadBrainCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemDeadBubbleCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemDeadFireCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemDeadHornCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemDeadTubeCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemFireCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemHornCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralblock.ItemTubeCoralBlockStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemBrainCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemBubbleCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemCoralFanDeadStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemDeadBrainCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemDeadBubbleCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemDeadFireCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemDeadHornCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemDeadTubeCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemFireCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemHornCoralFanStackImpl;
import org.allaymc.server.item.impl.coralfan.ItemTubeCoralFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemBrainCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemBubbleCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemDeadBrainCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemDeadBubbleCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemDeadFireCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemDeadHornCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemDeadTubeCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemFireCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemHornCoralWallFanStackImpl;
import org.allaymc.server.item.impl.coralwallfan.ItemTubeCoralWallFanStackImpl;
import org.allaymc.server.item.impl.dirt.ItemCoarseDirtStackImpl;
import org.allaymc.server.item.impl.dirt.ItemDirtStackImpl;
import org.allaymc.server.item.impl.door.ItemAcaciaDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemBambooDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemBirchDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemCherryDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemCrimsonDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemDarkOakDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemExposedCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemIronDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemAcaciaDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemBirchDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemCrimsonDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemDarkOakDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemIronDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemJungleDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemMangroveDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemSpruceDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemWarpedDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemItemWoodenDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemJungleDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemMangroveDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemOxidizedCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemSpruceDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWarpedDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWaxedCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWaxedExposedCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWaxedOxidizedCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWaxedWeatheredCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWeatheredCopperDoorStackImpl;
import org.allaymc.server.item.impl.door.ItemWoodenDoorStackImpl;
import org.allaymc.server.item.impl.element.ItemElement0StackImpl;
import org.allaymc.server.item.impl.element.ItemElement100StackImpl;
import org.allaymc.server.item.impl.element.ItemElement101StackImpl;
import org.allaymc.server.item.impl.element.ItemElement102StackImpl;
import org.allaymc.server.item.impl.element.ItemElement103StackImpl;
import org.allaymc.server.item.impl.element.ItemElement104StackImpl;
import org.allaymc.server.item.impl.element.ItemElement105StackImpl;
import org.allaymc.server.item.impl.element.ItemElement106StackImpl;
import org.allaymc.server.item.impl.element.ItemElement107StackImpl;
import org.allaymc.server.item.impl.element.ItemElement108StackImpl;
import org.allaymc.server.item.impl.element.ItemElement109StackImpl;
import org.allaymc.server.item.impl.element.ItemElement10StackImpl;
import org.allaymc.server.item.impl.element.ItemElement110StackImpl;
import org.allaymc.server.item.impl.element.ItemElement111StackImpl;
import org.allaymc.server.item.impl.element.ItemElement112StackImpl;
import org.allaymc.server.item.impl.element.ItemElement113StackImpl;
import org.allaymc.server.item.impl.element.ItemElement114StackImpl;
import org.allaymc.server.item.impl.element.ItemElement115StackImpl;
import org.allaymc.server.item.impl.element.ItemElement116StackImpl;
import org.allaymc.server.item.impl.element.ItemElement117StackImpl;
import org.allaymc.server.item.impl.element.ItemElement118StackImpl;
import org.allaymc.server.item.impl.element.ItemElement11StackImpl;
import org.allaymc.server.item.impl.element.ItemElement12StackImpl;
import org.allaymc.server.item.impl.element.ItemElement13StackImpl;
import org.allaymc.server.item.impl.element.ItemElement14StackImpl;
import org.allaymc.server.item.impl.element.ItemElement15StackImpl;
import org.allaymc.server.item.impl.element.ItemElement16StackImpl;
import org.allaymc.server.item.impl.element.ItemElement17StackImpl;
import org.allaymc.server.item.impl.element.ItemElement18StackImpl;
import org.allaymc.server.item.impl.element.ItemElement19StackImpl;
import org.allaymc.server.item.impl.element.ItemElement1StackImpl;
import org.allaymc.server.item.impl.element.ItemElement20StackImpl;
import org.allaymc.server.item.impl.element.ItemElement21StackImpl;
import org.allaymc.server.item.impl.element.ItemElement22StackImpl;
import org.allaymc.server.item.impl.element.ItemElement23StackImpl;
import org.allaymc.server.item.impl.element.ItemElement24StackImpl;
import org.allaymc.server.item.impl.element.ItemElement25StackImpl;
import org.allaymc.server.item.impl.element.ItemElement26StackImpl;
import org.allaymc.server.item.impl.element.ItemElement27StackImpl;
import org.allaymc.server.item.impl.element.ItemElement28StackImpl;
import org.allaymc.server.item.impl.element.ItemElement29StackImpl;
import org.allaymc.server.item.impl.element.ItemElement2StackImpl;
import org.allaymc.server.item.impl.element.ItemElement30StackImpl;
import org.allaymc.server.item.impl.element.ItemElement31StackImpl;
import org.allaymc.server.item.impl.element.ItemElement32StackImpl;
import org.allaymc.server.item.impl.element.ItemElement33StackImpl;
import org.allaymc.server.item.impl.element.ItemElement34StackImpl;
import org.allaymc.server.item.impl.element.ItemElement35StackImpl;
import org.allaymc.server.item.impl.element.ItemElement36StackImpl;
import org.allaymc.server.item.impl.element.ItemElement37StackImpl;
import org.allaymc.server.item.impl.element.ItemElement38StackImpl;
import org.allaymc.server.item.impl.element.ItemElement39StackImpl;
import org.allaymc.server.item.impl.element.ItemElement3StackImpl;
import org.allaymc.server.item.impl.element.ItemElement40StackImpl;
import org.allaymc.server.item.impl.element.ItemElement41StackImpl;
import org.allaymc.server.item.impl.element.ItemElement42StackImpl;
import org.allaymc.server.item.impl.element.ItemElement43StackImpl;
import org.allaymc.server.item.impl.element.ItemElement44StackImpl;
import org.allaymc.server.item.impl.element.ItemElement45StackImpl;
import org.allaymc.server.item.impl.element.ItemElement46StackImpl;
import org.allaymc.server.item.impl.element.ItemElement47StackImpl;
import org.allaymc.server.item.impl.element.ItemElement48StackImpl;
import org.allaymc.server.item.impl.element.ItemElement49StackImpl;
import org.allaymc.server.item.impl.element.ItemElement4StackImpl;
import org.allaymc.server.item.impl.element.ItemElement50StackImpl;
import org.allaymc.server.item.impl.element.ItemElement51StackImpl;
import org.allaymc.server.item.impl.element.ItemElement52StackImpl;
import org.allaymc.server.item.impl.element.ItemElement53StackImpl;
import org.allaymc.server.item.impl.element.ItemElement54StackImpl;
import org.allaymc.server.item.impl.element.ItemElement55StackImpl;
import org.allaymc.server.item.impl.element.ItemElement56StackImpl;
import org.allaymc.server.item.impl.element.ItemElement57StackImpl;
import org.allaymc.server.item.impl.element.ItemElement58StackImpl;
import org.allaymc.server.item.impl.element.ItemElement59StackImpl;
import org.allaymc.server.item.impl.element.ItemElement5StackImpl;
import org.allaymc.server.item.impl.element.ItemElement60StackImpl;
import org.allaymc.server.item.impl.element.ItemElement61StackImpl;
import org.allaymc.server.item.impl.element.ItemElement62StackImpl;
import org.allaymc.server.item.impl.element.ItemElement63StackImpl;
import org.allaymc.server.item.impl.element.ItemElement64StackImpl;
import org.allaymc.server.item.impl.element.ItemElement65StackImpl;
import org.allaymc.server.item.impl.element.ItemElement66StackImpl;
import org.allaymc.server.item.impl.element.ItemElement67StackImpl;
import org.allaymc.server.item.impl.element.ItemElement68StackImpl;
import org.allaymc.server.item.impl.element.ItemElement69StackImpl;
import org.allaymc.server.item.impl.element.ItemElement6StackImpl;
import org.allaymc.server.item.impl.element.ItemElement70StackImpl;
import org.allaymc.server.item.impl.element.ItemElement71StackImpl;
import org.allaymc.server.item.impl.element.ItemElement72StackImpl;
import org.allaymc.server.item.impl.element.ItemElement73StackImpl;
import org.allaymc.server.item.impl.element.ItemElement74StackImpl;
import org.allaymc.server.item.impl.element.ItemElement75StackImpl;
import org.allaymc.server.item.impl.element.ItemElement76StackImpl;
import org.allaymc.server.item.impl.element.ItemElement77StackImpl;
import org.allaymc.server.item.impl.element.ItemElement78StackImpl;
import org.allaymc.server.item.impl.element.ItemElement79StackImpl;
import org.allaymc.server.item.impl.element.ItemElement7StackImpl;
import org.allaymc.server.item.impl.element.ItemElement80StackImpl;
import org.allaymc.server.item.impl.element.ItemElement81StackImpl;
import org.allaymc.server.item.impl.element.ItemElement82StackImpl;
import org.allaymc.server.item.impl.element.ItemElement83StackImpl;
import org.allaymc.server.item.impl.element.ItemElement84StackImpl;
import org.allaymc.server.item.impl.element.ItemElement85StackImpl;
import org.allaymc.server.item.impl.element.ItemElement86StackImpl;
import org.allaymc.server.item.impl.element.ItemElement87StackImpl;
import org.allaymc.server.item.impl.element.ItemElement88StackImpl;
import org.allaymc.server.item.impl.element.ItemElement89StackImpl;
import org.allaymc.server.item.impl.element.ItemElement8StackImpl;
import org.allaymc.server.item.impl.element.ItemElement90StackImpl;
import org.allaymc.server.item.impl.element.ItemElement91StackImpl;
import org.allaymc.server.item.impl.element.ItemElement92StackImpl;
import org.allaymc.server.item.impl.element.ItemElement93StackImpl;
import org.allaymc.server.item.impl.element.ItemElement94StackImpl;
import org.allaymc.server.item.impl.element.ItemElement95StackImpl;
import org.allaymc.server.item.impl.element.ItemElement96StackImpl;
import org.allaymc.server.item.impl.element.ItemElement97StackImpl;
import org.allaymc.server.item.impl.element.ItemElement98StackImpl;
import org.allaymc.server.item.impl.element.ItemElement99StackImpl;
import org.allaymc.server.item.impl.element.ItemElement9StackImpl;
import org.allaymc.server.item.impl.element.ItemElementConstructorStackImpl;
import org.allaymc.server.item.impl.fence.ItemAcaciaFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemBambooFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemBirchFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemCherryFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemCrimsonFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemDarkOakFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemJungleFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemMangroveFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemNetherBrickFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemOakFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemSpruceFenceStackImpl;
import org.allaymc.server.item.impl.fence.ItemWarpedFenceStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemAcaciaFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemBambooFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemBirchFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemCherryFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemCrimsonFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemDarkOakFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemJungleFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemMangroveFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemSpruceFenceGateStackImpl;
import org.allaymc.server.item.impl.fencegate.ItemWarpedFenceGateStackImpl;
import org.allaymc.server.item.impl.furnace.ItemBlastFurnaceStackImpl;
import org.allaymc.server.item.impl.furnace.ItemFurnaceStackImpl;
import org.allaymc.server.item.impl.furnace.ItemLitBlastFurnaceStackImpl;
import org.allaymc.server.item.impl.furnace.ItemLitFurnaceStackImpl;
import org.allaymc.server.item.impl.furnace.ItemLitSmokerStackImpl;
import org.allaymc.server.item.impl.furnace.ItemSmokerStackImpl;
import org.allaymc.server.item.impl.glass.ItemGlassStackImpl;
import org.allaymc.server.item.impl.glass.ItemHardGlassStackImpl;
import org.allaymc.server.item.impl.glass.ItemTintedGlassStackImpl;
import org.allaymc.server.item.impl.glasspane.ItemGlassPaneStackImpl;
import org.allaymc.server.item.impl.glasspane.ItemHardGlassPaneStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemAcaciaHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemBambooHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemBirchHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemCherryHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemCrimsonHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemDarkOakHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemJungleHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemMangroveHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemOakHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemSpruceHangingSignStackImpl;
import org.allaymc.server.item.impl.hangingsign.ItemWarpedHangingSignStackImpl;
import org.allaymc.server.item.impl.head.ItemCreeperHeadStackImpl;
import org.allaymc.server.item.impl.head.ItemDragonHeadStackImpl;
import org.allaymc.server.item.impl.head.ItemPiglinHeadStackImpl;
import org.allaymc.server.item.impl.head.ItemPlayerHeadStackImpl;
import org.allaymc.server.item.impl.head.ItemSkeletonSkullStackImpl;
import org.allaymc.server.item.impl.head.ItemSkullStackImpl;
import org.allaymc.server.item.impl.head.ItemWitherSkeletonSkullStackImpl;
import org.allaymc.server.item.impl.head.ItemZombieHeadStackImpl;
import org.allaymc.server.item.impl.leaves.ItemAcaciaLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemAzaleaLeavesFloweredStackImpl;
import org.allaymc.server.item.impl.leaves.ItemAzaleaLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemBirchLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemCherryLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemDarkOakLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemJungleLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemLeaves2StackImpl;
import org.allaymc.server.item.impl.leaves.ItemLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemMangroveLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemOakLeavesStackImpl;
import org.allaymc.server.item.impl.leaves.ItemSpruceLeavesStackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock0StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock10StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock11StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock12StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock13StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock14StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock15StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock1StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock2StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock3StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock4StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock5StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock6StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock7StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock8StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlock9StackImpl;
import org.allaymc.server.item.impl.lightblock.ItemLightBlockStackImpl;
import org.allaymc.server.item.impl.liquid.ItemFlowingLavaStackImpl;
import org.allaymc.server.item.impl.liquid.ItemFlowingWaterStackImpl;
import org.allaymc.server.item.impl.liquid.ItemLavaStackImpl;
import org.allaymc.server.item.impl.liquid.ItemWaterStackImpl;
import org.allaymc.server.item.impl.log.ItemAcaciaLogStackImpl;
import org.allaymc.server.item.impl.log.ItemBirchLogStackImpl;
import org.allaymc.server.item.impl.log.ItemCherryLogStackImpl;
import org.allaymc.server.item.impl.log.ItemDarkOakLogStackImpl;
import org.allaymc.server.item.impl.log.ItemJungleLogStackImpl;
import org.allaymc.server.item.impl.log.ItemLog2StackImpl;
import org.allaymc.server.item.impl.log.ItemLogStackImpl;
import org.allaymc.server.item.impl.log.ItemMangroveLogStackImpl;
import org.allaymc.server.item.impl.log.ItemOakLogStackImpl;
import org.allaymc.server.item.impl.log.ItemSpruceLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedAcaciaLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedBirchLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedCherryLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedDarkOakLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedJungleLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedMangroveLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedOakLogStackImpl;
import org.allaymc.server.item.impl.log.ItemStrippedSpruceLogStackImpl;
import org.allaymc.server.item.impl.minecart.ItemChestMinecartStackImpl;
import org.allaymc.server.item.impl.minecart.ItemCommandBlockMinecartStackImpl;
import org.allaymc.server.item.impl.minecart.ItemHopperMinecartStackImpl;
import org.allaymc.server.item.impl.minecart.ItemMinecartStackImpl;
import org.allaymc.server.item.impl.minecart.ItemTntMinecartStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDisc11StackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDisc13StackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDisc5StackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscBlocksStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscCatStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscChirpStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscCreatorMusicBoxStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscCreatorStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscFarStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscMallStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscMellohiStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscOthersideStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscPigstepStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscPrecipiceStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscRelicStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscStalStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscStradStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscWaitStackImpl;
import org.allaymc.server.item.impl.musicdisc.ItemMusicDiscWardStackImpl;
import org.allaymc.server.item.impl.piston.ItemPistonArmCollisionStackImpl;
import org.allaymc.server.item.impl.piston.ItemPistonStackImpl;
import org.allaymc.server.item.impl.piston.ItemStickyPistonArmCollisionStackImpl;
import org.allaymc.server.item.impl.piston.ItemStickyPistonStackImpl;
import org.allaymc.server.item.impl.planks.ItemAcaciaPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemBambooPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemBirchPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemCherryPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemCrimsonPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemDarkOakPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemJunglePlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemMangrovePlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemOakPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemPlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemSprucePlanksStackImpl;
import org.allaymc.server.item.impl.planks.ItemWarpedPlanksStackImpl;
import org.allaymc.server.item.impl.purpur.ItemPurpurBlockStackImpl;
import org.allaymc.server.item.impl.purpur.ItemPurpurPillarStackImpl;
import org.allaymc.server.item.impl.sand.ItemRedSandStackImpl;
import org.allaymc.server.item.impl.sand.ItemSandStackImpl;
import org.allaymc.server.item.impl.sand.ItemSoulSandStackImpl;
import org.allaymc.server.item.impl.sand.ItemSuspiciousSandStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemChiseledRedSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemChiseledSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemCutRedSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemCutSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemRedSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemSmoothRedSandstoneStackImpl;
import org.allaymc.server.item.impl.sandstone.ItemSmoothSandstoneStackImpl;
import org.allaymc.server.item.impl.sapling.ItemAcaciaSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemBambooSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemBirchSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemCherrySaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemDarkOakSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemJungleSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemOakSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemSaplingStackImpl;
import org.allaymc.server.item.impl.sapling.ItemSpruceSaplingStackImpl;
import org.allaymc.server.item.impl.slab.ItemAcaciaDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemAcaciaSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemAndesiteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemAndesiteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBambooDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBambooMosaicDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBambooMosaicSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBambooSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBirchDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBirchSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBlackstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBlackstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCherryDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCherrySlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCobbledDeepslateDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCobbledDeepslateSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCobblestoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCobblestoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCrimsonDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCrimsonSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCutRedSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCutRedSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCutSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemCutSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDarkOakDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDarkOakSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDarkPrismarineDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDarkPrismarineSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDeepslateBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDeepslateBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDeepslateTileDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDeepslateTileSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDioriteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDioriteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemDoubleStoneBlockSlab2StackImpl;
import org.allaymc.server.item.impl.slab.ItemDoubleStoneBlockSlab3StackImpl;
import org.allaymc.server.item.impl.slab.ItemDoubleStoneBlockSlab4StackImpl;
import org.allaymc.server.item.impl.slab.ItemDoubleStoneBlockSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemEndStoneBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemEndStoneBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemExposedCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemExposedDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemGraniteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemGraniteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemJungleDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemJungleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMangroveDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMangroveSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMossyCobblestoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMossyCobblestoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMossyStoneBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMossyStoneBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMudBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemMudBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemNetherBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemNetherBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemNormalStoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemNormalStoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemOakDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemOakSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemOxidizedCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemOxidizedDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPetrifiedOakDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPetrifiedOakSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedAndesiteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedAndesiteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedBlackstoneBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedBlackstoneBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedBlackstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedBlackstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedDeepslateDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedDeepslateSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedDioriteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedDioriteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedGraniteDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedGraniteSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedTuffDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPolishedTuffSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPrismarineBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPrismarineBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPrismarineDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPrismarineSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPurpurDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemPurpurSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemQuartzDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemQuartzSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemRedNetherBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemRedNetherBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemRedSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemRedSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothQuartzDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothQuartzSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothRedSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothRedSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothSandstoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothSandstoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothStoneDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSmoothStoneSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSpruceDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemSpruceSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBlockSlab2StackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBlockSlab3StackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBlockSlab4StackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBlockSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemStoneBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemTuffBrickDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemTuffBrickSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemTuffDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemTuffSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWarpedDoubleSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWarpedSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedExposedCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedExposedDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedOxidizedCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedOxidizedDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedWeatheredCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWaxedWeatheredDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWeatheredCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWeatheredDoubleCutCopperSlabStackImpl;
import org.allaymc.server.item.impl.slab.ItemWoodenSlabStackImpl;
import org.allaymc.server.item.impl.sponge.ItemSpongeStackImpl;
import org.allaymc.server.item.impl.sponge.ItemWetSpongeStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemBlackStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemBlueStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemBrownStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemCyanStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemGrayStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemGreenStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardBlackStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardBlueStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardBrownStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardCyanStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardGrayStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardGreenStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardLightBlueStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardLightGrayStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardLimeStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardMagentaStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardOrangeStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardPinkStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardPurpleStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardRedStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardWhiteStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemHardYellowStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemLightBlueStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemLightGrayStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemLimeStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemMagentaStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemOrangeStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemPinkStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemPurpleStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemRedStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemWhiteStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglass.ItemYellowStainedGlassStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemBlackStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemBlueStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemBrownStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemCyanStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemGrayStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemGreenStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardBlackStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardBlueStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardBrownStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardCyanStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardGrayStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardGreenStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardLightBlueStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardLightGrayStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardLimeStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardMagentaStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardOrangeStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardPinkStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardPurpleStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardRedStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardWhiteStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemHardYellowStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemLightBlueStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemLightGrayStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemLimeStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemMagentaStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemOrangeStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemPinkStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemPurpleStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemRedStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemWhiteStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stainedglasspane.ItemYellowStainedGlassPaneStackImpl;
import org.allaymc.server.item.impl.stairs.ItemAcaciaStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemAndesiteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemBambooMosaicStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemBambooStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemBirchStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemBlackstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemCherryStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemCobbledDeepslateStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemCrimsonStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemDarkOakStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemDarkPrismarineStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemDeepslateBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemDeepslateTileStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemDioriteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemEndBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemExposedCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemGraniteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemJungleStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemMangroveStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemMossyCobblestoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemMossyStoneBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemMudBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemNetherBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemNormalStoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemOakStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemOxidizedCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedAndesiteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedBlackstoneBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedBlackstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedDeepslateStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedDioriteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedGraniteStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPolishedTuffStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPrismarineBricksStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPrismarineStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemPurpurStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemQuartzStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemRedNetherBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemRedSandstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemSandstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemSmoothQuartzStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemSmoothRedSandstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemSmoothSandstoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemSpruceStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemStoneBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemStoneStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemTuffBrickStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemTuffStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWarpedStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWaxedCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWaxedExposedCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWaxedOxidizedCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWaxedWeatheredCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.stairs.ItemWeatheredCutCopperStairsStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemAcaciaStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemBambooStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemBirchStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemCherryStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemCrimsonStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemDarkoakStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemJungleStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemMangroveStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemSpruceStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemStandingSignStackImpl;
import org.allaymc.server.item.impl.standingsign.ItemWarpedStandingSignStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBlackGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBlackTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBlueGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBlueTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBrownGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemBrownTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemCyanGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemCyanTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemGrayGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemGrayTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemGreenGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemGreenTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemLightBlueGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemLightBlueTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemLightGrayTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemLimeGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemLimeTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemMagentaGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemMagentaTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemOrangeGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemOrangeTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemPinkGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemPinkTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemPurpleGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemPurpleTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemRedGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemRedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemSilverGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemWhiteGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemWhiteTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemYellowGlazedTerracottaStackImpl;
import org.allaymc.server.item.impl.terracotta.ItemYellowTerracottaStackImpl;
import org.allaymc.server.item.impl.tnt.ItemTntStackImpl;
import org.allaymc.server.item.impl.tnt.ItemUnderwaterTntStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchBlueStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchBpStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchGreenStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchPurpleStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchRedStackImpl;
import org.allaymc.server.item.impl.torch.ItemColoredTorchRgStackImpl;
import org.allaymc.server.item.impl.torch.ItemRedstoneTorchStackImpl;
import org.allaymc.server.item.impl.torch.ItemSoulTorchStackImpl;
import org.allaymc.server.item.impl.torch.ItemTorchStackImpl;
import org.allaymc.server.item.impl.torch.ItemUnderwaterTorchStackImpl;
import org.allaymc.server.item.impl.torch.ItemUnlitRedstoneTorchStackImpl;
import org.allaymc.server.item.impl.torchflower.ItemTorchflowerCropStackImpl;
import org.allaymc.server.item.impl.torchflower.ItemTorchflowerSeedsStackImpl;
import org.allaymc.server.item.impl.torchflower.ItemTorchflowerStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemAcaciaTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemBambooTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemBirchTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemCherryTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemCrimsonTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemDarkOakTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemExposedCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemIronTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemJungleTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemMangroveTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemOxidizedCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemSpruceTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWarpedTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWaxedCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWaxedExposedCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWaxedOxidizedCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWaxedWeatheredCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.trapdoor.ItemWeatheredCopperTrapdoorStackImpl;
import org.allaymc.server.item.impl.wall.ItemAndesiteWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemBlackstoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemCobbledDeepslateWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemCobblestoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemDeepslateBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemDeepslateTileWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemDioriteWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemEndStoneBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemGraniteWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemMossyCobblestoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemMossyStoneBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemMudBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemNetherBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemPolishedBlackstoneBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemPolishedBlackstoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemPolishedDeepslateWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemPolishedTuffWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemPrismarineWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemRedNetherBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemRedSandstoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemSandstoneWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemStoneBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemTuffBrickWallStackImpl;
import org.allaymc.server.item.impl.wall.ItemTuffWallStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemAcaciaWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemBambooWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemBirchWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemCherryWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemCrimsonWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemDarkoakWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemJungleWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemMangroveWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemSpruceWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemWallSignStackImpl;
import org.allaymc.server.item.impl.wallsign.ItemWarpedWallSignStackImpl;
import org.allaymc.server.item.impl.wood.ItemAcaciaWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemBirchWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemCherryWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemDarkOakWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemJungleWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemMangroveWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemOakWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemSpruceWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedAcaciaWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedBirchWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedCherryWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedDarkOakWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedJungleWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedMangroveWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedOakWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemStrippedSpruceWoodStackImpl;
import org.allaymc.server.item.impl.wood.ItemWoodStackImpl;
import org.allaymc.server.item.impl.wool.ItemBlackWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemBlueWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemBrownWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemCyanWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemGrayWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemGreenWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemLightBlueWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemLightGrayWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemLimeWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemMagentaWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemOrangeWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemPinkWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemPurpleWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemRedWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemWhiteWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemWoolStackImpl;
import org.allaymc.server.item.impl.wool.ItemYellowWoolStackImpl;

/**
 * Automatically generated by {@code org.allaymc.codegen.ItemClassGen}
 */
public final class ItemTypeDefaultInitializer {
    public static void init() {
        if (ItemTypes.ACACIA_BOAT == null) {
            ItemTypes.ACACIA_BOAT = AllayItemType
                            .builder(ItemAcaciaBoatStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_BOAT)
                            .build();
        }
        if (ItemTypes.ACACIA_BUTTON == null) {
            ItemTypes.ACACIA_BUTTON = AllayItemType
                            .builder(ItemAcaciaButtonStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_BUTTON)
                            .build();
        }
        if (ItemTypes.ACACIA_CHEST_BOAT == null) {
            ItemTypes.ACACIA_CHEST_BOAT = AllayItemType
                            .builder(ItemAcaciaChestBoatStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.ACACIA_DOOR == null) {
            ItemTypes.ACACIA_DOOR = AllayItemType
                            .builder(ItemAcaciaDoorStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_DOOR)
                            .build();
        }
        if (ItemTypes.ACACIA_DOUBLE_SLAB == null) {
            ItemTypes.ACACIA_DOUBLE_SLAB = AllayItemType
                            .builder(ItemAcaciaDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.ACACIA_FENCE == null) {
            ItemTypes.ACACIA_FENCE = AllayItemType
                            .builder(ItemAcaciaFenceStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_FENCE)
                            .build();
        }
        if (ItemTypes.ACACIA_FENCE_GATE == null) {
            ItemTypes.ACACIA_FENCE_GATE = AllayItemType
                            .builder(ItemAcaciaFenceGateStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.ACACIA_HANGING_SIGN == null) {
            ItemTypes.ACACIA_HANGING_SIGN = AllayItemType
                            .builder(ItemAcaciaHangingSignStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.ACACIA_LEAVES == null) {
            ItemTypes.ACACIA_LEAVES = AllayItemType
                            .builder(ItemAcaciaLeavesStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_LEAVES)
                            .build();
        }
        if (ItemTypes.ACACIA_LOG == null) {
            ItemTypes.ACACIA_LOG = AllayItemType
                            .builder(ItemAcaciaLogStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_LOG)
                            .build();
        }
        if (ItemTypes.ACACIA_PLANKS == null) {
            ItemTypes.ACACIA_PLANKS = AllayItemType
                            .builder(ItemAcaciaPlanksStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_PLANKS)
                            .build();
        }
        if (ItemTypes.ACACIA_PRESSURE_PLATE == null) {
            ItemTypes.ACACIA_PRESSURE_PLATE = AllayItemType
                            .builder(ItemAcaciaPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.ACACIA_SAPLING == null) {
            ItemTypes.ACACIA_SAPLING = AllayItemType
                            .builder(ItemAcaciaSaplingStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_SAPLING)
                            .build();
        }
        if (ItemTypes.ACACIA_SLAB == null) {
            ItemTypes.ACACIA_SLAB = AllayItemType
                            .builder(ItemAcaciaSlabStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_SLAB)
                            .build();
        }
        if (ItemTypes.ACACIA_STAIRS == null) {
            ItemTypes.ACACIA_STAIRS = AllayItemType
                            .builder(ItemAcaciaStairsStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_STAIRS)
                            .build();
        }
        if (ItemTypes.ACACIA_STANDING_SIGN == null) {
            ItemTypes.ACACIA_STANDING_SIGN = AllayItemType
                            .builder(ItemAcaciaStandingSignStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.ACACIA_TRAPDOOR == null) {
            ItemTypes.ACACIA_TRAPDOOR = AllayItemType
                            .builder(ItemAcaciaTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.ACACIA_WALL_SIGN == null) {
            ItemTypes.ACACIA_WALL_SIGN = AllayItemType
                            .builder(ItemAcaciaWallSignStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.ACACIA_WOOD == null) {
            ItemTypes.ACACIA_WOOD = AllayItemType
                            .builder(ItemAcaciaWoodStackImpl.class)
                            .vanillaItem(ItemId.ACACIA_WOOD)
                            .build();
        }
        if (ItemTypes.ACTIVATOR_RAIL == null) {
            ItemTypes.ACTIVATOR_RAIL = AllayItemType
                            .builder(ItemActivatorRailStackImpl.class)
                            .vanillaItem(ItemId.ACTIVATOR_RAIL)
                            .build();
        }
        if (ItemTypes.AIR == null) {
            ItemTypes.AIR = AllayItemType
                            .builder(ItemAirStackImpl.class)
                            .vanillaItem(ItemId.AIR)
                            .build();
        }
        if (ItemTypes.ALLIUM == null) {
            ItemTypes.ALLIUM = AllayItemType
                            .builder(ItemAlliumStackImpl.class)
                            .vanillaItem(ItemId.ALLIUM)
                            .build();
        }
        if (ItemTypes.ALLOW == null) {
            ItemTypes.ALLOW = AllayItemType
                            .builder(ItemAllowStackImpl.class)
                            .vanillaItem(ItemId.ALLOW)
                            .build();
        }
        if (ItemTypes.AMETHYST_BLOCK == null) {
            ItemTypes.AMETHYST_BLOCK = AllayItemType
                            .builder(ItemAmethystBlockStackImpl.class)
                            .vanillaItem(ItemId.AMETHYST_BLOCK)
                            .build();
        }
        if (ItemTypes.AMETHYST_CLUSTER == null) {
            ItemTypes.AMETHYST_CLUSTER = AllayItemType
                            .builder(ItemAmethystClusterStackImpl.class)
                            .vanillaItem(ItemId.AMETHYST_CLUSTER)
                            .build();
        }
        if (ItemTypes.AMETHYST_SHARD == null) {
            ItemTypes.AMETHYST_SHARD = AllayItemType
                            .builder(ItemAmethystShardStackImpl.class)
                            .vanillaItem(ItemId.AMETHYST_SHARD)
                            .build();
        }
        if (ItemTypes.ANCIENT_DEBRIS == null) {
            ItemTypes.ANCIENT_DEBRIS = AllayItemType
                            .builder(ItemAncientDebrisStackImpl.class)
                            .vanillaItem(ItemId.ANCIENT_DEBRIS)
                            .build();
        }
        if (ItemTypes.ANDESITE == null) {
            ItemTypes.ANDESITE = AllayItemType
                            .builder(ItemAndesiteStackImpl.class)
                            .vanillaItem(ItemId.ANDESITE)
                            .build();
        }
        if (ItemTypes.ANDESITE_DOUBLE_SLAB == null) {
            ItemTypes.ANDESITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemAndesiteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.ANDESITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.ANDESITE_SLAB == null) {
            ItemTypes.ANDESITE_SLAB = AllayItemType
                            .builder(ItemAndesiteSlabStackImpl.class)
                            .vanillaItem(ItemId.ANDESITE_SLAB)
                            .build();
        }
        if (ItemTypes.ANDESITE_STAIRS == null) {
            ItemTypes.ANDESITE_STAIRS = AllayItemType
                            .builder(ItemAndesiteStairsStackImpl.class)
                            .vanillaItem(ItemId.ANDESITE_STAIRS)
                            .build();
        }
        if (ItemTypes.ANDESITE_WALL == null) {
            ItemTypes.ANDESITE_WALL = AllayItemType
                            .builder(ItemAndesiteWallStackImpl.class)
                            .vanillaItem(ItemId.ANDESITE_WALL)
                            .build();
        }
        if (ItemTypes.ANGLER_POTTERY_SHERD == null) {
            ItemTypes.ANGLER_POTTERY_SHERD = AllayItemType
                            .builder(ItemAnglerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.ANGLER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.ANVIL == null) {
            ItemTypes.ANVIL = AllayItemType
                            .builder(ItemAnvilStackImpl.class)
                            .vanillaItem(ItemId.ANVIL)
                            .build();
        }
        if (ItemTypes.APPLE == null) {
            ItemTypes.APPLE = AllayItemType
                            .builder(ItemAppleStackImpl.class)
                            .vanillaItem(ItemId.APPLE)
                            .build();
        }
        if (ItemTypes.ARCHER_POTTERY_SHERD == null) {
            ItemTypes.ARCHER_POTTERY_SHERD = AllayItemType
                            .builder(ItemArcherPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.ARCHER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.ARMADILLO_SCUTE == null) {
            ItemTypes.ARMADILLO_SCUTE = AllayItemType
                            .builder(ItemArmadilloScuteStackImpl.class)
                            .vanillaItem(ItemId.ARMADILLO_SCUTE)
                            .build();
        }
        if (ItemTypes.ARMOR_STAND == null) {
            ItemTypes.ARMOR_STAND = AllayItemType
                            .builder(ItemArmorStandStackImpl.class)
                            .vanillaItem(ItemId.ARMOR_STAND)
                            .build();
        }
        if (ItemTypes.ARMS_UP_POTTERY_SHERD == null) {
            ItemTypes.ARMS_UP_POTTERY_SHERD = AllayItemType
                            .builder(ItemArmsUpPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.ARMS_UP_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.ARROW == null) {
            ItemTypes.ARROW = AllayItemType
                            .builder(ItemArrowStackImpl.class)
                            .vanillaItem(ItemId.ARROW)
                            .build();
        }
        if (ItemTypes.AZALEA == null) {
            ItemTypes.AZALEA = AllayItemType
                            .builder(ItemAzaleaStackImpl.class)
                            .vanillaItem(ItemId.AZALEA)
                            .build();
        }
        if (ItemTypes.AZALEA_LEAVES == null) {
            ItemTypes.AZALEA_LEAVES = AllayItemType
                            .builder(ItemAzaleaLeavesStackImpl.class)
                            .vanillaItem(ItemId.AZALEA_LEAVES)
                            .build();
        }
        if (ItemTypes.AZALEA_LEAVES_FLOWERED == null) {
            ItemTypes.AZALEA_LEAVES_FLOWERED = AllayItemType
                            .builder(ItemAzaleaLeavesFloweredStackImpl.class)
                            .vanillaItem(ItemId.AZALEA_LEAVES_FLOWERED)
                            .build();
        }
        if (ItemTypes.AZURE_BLUET == null) {
            ItemTypes.AZURE_BLUET = AllayItemType
                            .builder(ItemAzureBluetStackImpl.class)
                            .vanillaItem(ItemId.AZURE_BLUET)
                            .build();
        }
        if (ItemTypes.BAKED_POTATO == null) {
            ItemTypes.BAKED_POTATO = AllayItemType
                            .builder(ItemBakedPotatoStackImpl.class)
                            .vanillaItem(ItemId.BAKED_POTATO)
                            .build();
        }
        if (ItemTypes.BALLOON == null) {
            ItemTypes.BALLOON = AllayItemType
                            .builder(ItemBalloonStackImpl.class)
                            .vanillaItem(ItemId.BALLOON)
                            .build();
        }
        if (ItemTypes.BAMBOO == null) {
            ItemTypes.BAMBOO = AllayItemType
                            .builder(ItemBambooStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO)
                            .build();
        }
        if (ItemTypes.BAMBOO_BLOCK == null) {
            ItemTypes.BAMBOO_BLOCK = AllayItemType
                            .builder(ItemBambooBlockStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_BLOCK)
                            .build();
        }
        if (ItemTypes.BAMBOO_BUTTON == null) {
            ItemTypes.BAMBOO_BUTTON = AllayItemType
                            .builder(ItemBambooButtonStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_BUTTON)
                            .build();
        }
        if (ItemTypes.BAMBOO_CHEST_RAFT == null) {
            ItemTypes.BAMBOO_CHEST_RAFT = AllayItemType
                            .builder(ItemBambooChestRaftStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_CHEST_RAFT)
                            .build();
        }
        if (ItemTypes.BAMBOO_DOOR == null) {
            ItemTypes.BAMBOO_DOOR = AllayItemType
                            .builder(ItemBambooDoorStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_DOOR)
                            .build();
        }
        if (ItemTypes.BAMBOO_DOUBLE_SLAB == null) {
            ItemTypes.BAMBOO_DOUBLE_SLAB = AllayItemType
                            .builder(ItemBambooDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.BAMBOO_FENCE == null) {
            ItemTypes.BAMBOO_FENCE = AllayItemType
                            .builder(ItemBambooFenceStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_FENCE)
                            .build();
        }
        if (ItemTypes.BAMBOO_FENCE_GATE == null) {
            ItemTypes.BAMBOO_FENCE_GATE = AllayItemType
                            .builder(ItemBambooFenceGateStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.BAMBOO_HANGING_SIGN == null) {
            ItemTypes.BAMBOO_HANGING_SIGN = AllayItemType
                            .builder(ItemBambooHangingSignStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.BAMBOO_MOSAIC == null) {
            ItemTypes.BAMBOO_MOSAIC = AllayItemType
                            .builder(ItemBambooMosaicStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_MOSAIC)
                            .build();
        }
        if (ItemTypes.BAMBOO_MOSAIC_DOUBLE_SLAB == null) {
            ItemTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = AllayItemType
                            .builder(ItemBambooMosaicDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.BAMBOO_MOSAIC_SLAB == null) {
            ItemTypes.BAMBOO_MOSAIC_SLAB = AllayItemType
                            .builder(ItemBambooMosaicSlabStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_MOSAIC_SLAB)
                            .build();
        }
        if (ItemTypes.BAMBOO_MOSAIC_STAIRS == null) {
            ItemTypes.BAMBOO_MOSAIC_STAIRS = AllayItemType
                            .builder(ItemBambooMosaicStairsStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_MOSAIC_STAIRS)
                            .build();
        }
        if (ItemTypes.BAMBOO_PLANKS == null) {
            ItemTypes.BAMBOO_PLANKS = AllayItemType
                            .builder(ItemBambooPlanksStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_PLANKS)
                            .build();
        }
        if (ItemTypes.BAMBOO_PRESSURE_PLATE == null) {
            ItemTypes.BAMBOO_PRESSURE_PLATE = AllayItemType
                            .builder(ItemBambooPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.BAMBOO_RAFT == null) {
            ItemTypes.BAMBOO_RAFT = AllayItemType
                            .builder(ItemBambooRaftStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_RAFT)
                            .build();
        }
        if (ItemTypes.BAMBOO_SAPLING == null) {
            ItemTypes.BAMBOO_SAPLING = AllayItemType
                            .builder(ItemBambooSaplingStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_SAPLING)
                            .build();
        }
        if (ItemTypes.BAMBOO_SLAB == null) {
            ItemTypes.BAMBOO_SLAB = AllayItemType
                            .builder(ItemBambooSlabStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_SLAB)
                            .build();
        }
        if (ItemTypes.BAMBOO_STAIRS == null) {
            ItemTypes.BAMBOO_STAIRS = AllayItemType
                            .builder(ItemBambooStairsStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_STAIRS)
                            .build();
        }
        if (ItemTypes.BAMBOO_STANDING_SIGN == null) {
            ItemTypes.BAMBOO_STANDING_SIGN = AllayItemType
                            .builder(ItemBambooStandingSignStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.BAMBOO_TRAPDOOR == null) {
            ItemTypes.BAMBOO_TRAPDOOR = AllayItemType
                            .builder(ItemBambooTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.BAMBOO_WALL_SIGN == null) {
            ItemTypes.BAMBOO_WALL_SIGN = AllayItemType
                            .builder(ItemBambooWallSignStackImpl.class)
                            .vanillaItem(ItemId.BAMBOO_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.BANNER == null) {
            ItemTypes.BANNER = AllayItemType
                            .builder(ItemBannerStackImpl.class)
                            .vanillaItem(ItemId.BANNER)
                            .build();
        }
        if (ItemTypes.BANNER_PATTERN == null) {
            ItemTypes.BANNER_PATTERN = AllayItemType
                            .builder(ItemBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.BARREL == null) {
            ItemTypes.BARREL = AllayItemType
                            .builder(ItemBarrelStackImpl.class)
                            .vanillaItem(ItemId.BARREL)
                            .build();
        }
        if (ItemTypes.BARRIER == null) {
            ItemTypes.BARRIER = AllayItemType
                            .builder(ItemBarrierStackImpl.class)
                            .vanillaItem(ItemId.BARRIER)
                            .build();
        }
        if (ItemTypes.BASALT == null) {
            ItemTypes.BASALT = AllayItemType
                            .builder(ItemBasaltStackImpl.class)
                            .vanillaItem(ItemId.BASALT)
                            .build();
        }
        if (ItemTypes.BEACON == null) {
            ItemTypes.BEACON = AllayItemType
                            .builder(ItemBeaconStackImpl.class)
                            .vanillaItem(ItemId.BEACON)
                            .build();
        }
        if (ItemTypes.BED == null) {
            ItemTypes.BED = AllayItemType
                            .builder(ItemBedStackImpl.class)
                            .vanillaItem(ItemId.BED)
                            .build();
        }
        if (ItemTypes.BEDROCK == null) {
            ItemTypes.BEDROCK = AllayItemType
                            .builder(ItemBedrockStackImpl.class)
                            .vanillaItem(ItemId.BEDROCK)
                            .build();
        }
        if (ItemTypes.BEE_NEST == null) {
            ItemTypes.BEE_NEST = AllayItemType
                            .builder(ItemBeeNestStackImpl.class)
                            .vanillaItem(ItemId.BEE_NEST)
                            .build();
        }
        if (ItemTypes.BEEF == null) {
            ItemTypes.BEEF = AllayItemType
                            .builder(ItemBeefStackImpl.class)
                            .vanillaItem(ItemId.BEEF)
                            .build();
        }
        if (ItemTypes.BEEHIVE == null) {
            ItemTypes.BEEHIVE = AllayItemType
                            .builder(ItemBeehiveStackImpl.class)
                            .vanillaItem(ItemId.BEEHIVE)
                            .build();
        }
        if (ItemTypes.BEETROOT == null) {
            ItemTypes.BEETROOT = AllayItemType
                            .builder(ItemBeetrootStackImpl.class)
                            .vanillaItem(ItemId.BEETROOT)
                            .build();
        }
        if (ItemTypes.BEETROOT_SEEDS == null) {
            ItemTypes.BEETROOT_SEEDS = AllayItemType
                            .builder(ItemBeetrootSeedsStackImpl.class)
                            .vanillaItem(ItemId.BEETROOT_SEEDS)
                            .build();
        }
        if (ItemTypes.BEETROOT_SOUP == null) {
            ItemTypes.BEETROOT_SOUP = AllayItemType
                            .builder(ItemBeetrootSoupStackImpl.class)
                            .vanillaItem(ItemId.BEETROOT_SOUP)
                            .build();
        }
        if (ItemTypes.BELL == null) {
            ItemTypes.BELL = AllayItemType
                            .builder(ItemBellStackImpl.class)
                            .vanillaItem(ItemId.BELL)
                            .build();
        }
        if (ItemTypes.BIG_DRIPLEAF == null) {
            ItemTypes.BIG_DRIPLEAF = AllayItemType
                            .builder(ItemBigDripleafStackImpl.class)
                            .vanillaItem(ItemId.BIG_DRIPLEAF)
                            .build();
        }
        if (ItemTypes.BIRCH_BOAT == null) {
            ItemTypes.BIRCH_BOAT = AllayItemType
                            .builder(ItemBirchBoatStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_BOAT)
                            .build();
        }
        if (ItemTypes.BIRCH_BUTTON == null) {
            ItemTypes.BIRCH_BUTTON = AllayItemType
                            .builder(ItemBirchButtonStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_BUTTON)
                            .build();
        }
        if (ItemTypes.BIRCH_CHEST_BOAT == null) {
            ItemTypes.BIRCH_CHEST_BOAT = AllayItemType
                            .builder(ItemBirchChestBoatStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.BIRCH_DOOR == null) {
            ItemTypes.BIRCH_DOOR = AllayItemType
                            .builder(ItemBirchDoorStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_DOOR)
                            .build();
        }
        if (ItemTypes.BIRCH_DOUBLE_SLAB == null) {
            ItemTypes.BIRCH_DOUBLE_SLAB = AllayItemType
                            .builder(ItemBirchDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.BIRCH_FENCE == null) {
            ItemTypes.BIRCH_FENCE = AllayItemType
                            .builder(ItemBirchFenceStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_FENCE)
                            .build();
        }
        if (ItemTypes.BIRCH_FENCE_GATE == null) {
            ItemTypes.BIRCH_FENCE_GATE = AllayItemType
                            .builder(ItemBirchFenceGateStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.BIRCH_HANGING_SIGN == null) {
            ItemTypes.BIRCH_HANGING_SIGN = AllayItemType
                            .builder(ItemBirchHangingSignStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.BIRCH_LEAVES == null) {
            ItemTypes.BIRCH_LEAVES = AllayItemType
                            .builder(ItemBirchLeavesStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_LEAVES)
                            .build();
        }
        if (ItemTypes.BIRCH_LOG == null) {
            ItemTypes.BIRCH_LOG = AllayItemType
                            .builder(ItemBirchLogStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_LOG)
                            .build();
        }
        if (ItemTypes.BIRCH_PLANKS == null) {
            ItemTypes.BIRCH_PLANKS = AllayItemType
                            .builder(ItemBirchPlanksStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_PLANKS)
                            .build();
        }
        if (ItemTypes.BIRCH_PRESSURE_PLATE == null) {
            ItemTypes.BIRCH_PRESSURE_PLATE = AllayItemType
                            .builder(ItemBirchPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.BIRCH_SAPLING == null) {
            ItemTypes.BIRCH_SAPLING = AllayItemType
                            .builder(ItemBirchSaplingStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_SAPLING)
                            .build();
        }
        if (ItemTypes.BIRCH_SLAB == null) {
            ItemTypes.BIRCH_SLAB = AllayItemType
                            .builder(ItemBirchSlabStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_SLAB)
                            .build();
        }
        if (ItemTypes.BIRCH_STAIRS == null) {
            ItemTypes.BIRCH_STAIRS = AllayItemType
                            .builder(ItemBirchStairsStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_STAIRS)
                            .build();
        }
        if (ItemTypes.BIRCH_STANDING_SIGN == null) {
            ItemTypes.BIRCH_STANDING_SIGN = AllayItemType
                            .builder(ItemBirchStandingSignStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.BIRCH_TRAPDOOR == null) {
            ItemTypes.BIRCH_TRAPDOOR = AllayItemType
                            .builder(ItemBirchTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.BIRCH_WALL_SIGN == null) {
            ItemTypes.BIRCH_WALL_SIGN = AllayItemType
                            .builder(ItemBirchWallSignStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.BIRCH_WOOD == null) {
            ItemTypes.BIRCH_WOOD = AllayItemType
                            .builder(ItemBirchWoodStackImpl.class)
                            .vanillaItem(ItemId.BIRCH_WOOD)
                            .build();
        }
        if (ItemTypes.BLACK_BUNDLE == null) {
            ItemTypes.BLACK_BUNDLE = AllayItemType
                            .builder(ItemBlackBundleStackImpl.class)
                            .vanillaItem(ItemId.BLACK_BUNDLE)
                            .build();
        }
        if (ItemTypes.BLACK_CANDLE == null) {
            ItemTypes.BLACK_CANDLE = AllayItemType
                            .builder(ItemBlackCandleStackImpl.class)
                            .vanillaItem(ItemId.BLACK_CANDLE)
                            .build();
        }
        if (ItemTypes.BLACK_CANDLE_CAKE == null) {
            ItemTypes.BLACK_CANDLE_CAKE = AllayItemType
                            .builder(ItemBlackCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.BLACK_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.BLACK_CARPET == null) {
            ItemTypes.BLACK_CARPET = AllayItemType
                            .builder(ItemBlackCarpetStackImpl.class)
                            .vanillaItem(ItemId.BLACK_CARPET)
                            .build();
        }
        if (ItemTypes.BLACK_CONCRETE == null) {
            ItemTypes.BLACK_CONCRETE = AllayItemType
                            .builder(ItemBlackConcreteStackImpl.class)
                            .vanillaItem(ItemId.BLACK_CONCRETE)
                            .build();
        }
        if (ItemTypes.BLACK_CONCRETE_POWDER == null) {
            ItemTypes.BLACK_CONCRETE_POWDER = AllayItemType
                            .builder(ItemBlackConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.BLACK_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.BLACK_DYE == null) {
            ItemTypes.BLACK_DYE = AllayItemType
                            .builder(ItemBlackDyeStackImpl.class)
                            .vanillaItem(ItemId.BLACK_DYE)
                            .build();
        }
        if (ItemTypes.BLACK_GLAZED_TERRACOTTA == null) {
            ItemTypes.BLACK_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemBlackGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BLACK_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BLACK_STAINED_GLASS == null) {
            ItemTypes.BLACK_STAINED_GLASS = AllayItemType
                            .builder(ItemBlackStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.BLACK_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.BLACK_STAINED_GLASS_PANE == null) {
            ItemTypes.BLACK_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemBlackStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.BLACK_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.BLACK_TERRACOTTA == null) {
            ItemTypes.BLACK_TERRACOTTA = AllayItemType
                            .builder(ItemBlackTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BLACK_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BLACK_WOOL == null) {
            ItemTypes.BLACK_WOOL = AllayItemType
                            .builder(ItemBlackWoolStackImpl.class)
                            .vanillaItem(ItemId.BLACK_WOOL)
                            .build();
        }
        if (ItemTypes.BLACKSTONE == null) {
            ItemTypes.BLACKSTONE = AllayItemType
                            .builder(ItemBlackstoneStackImpl.class)
                            .vanillaItem(ItemId.BLACKSTONE)
                            .build();
        }
        if (ItemTypes.BLACKSTONE_DOUBLE_SLAB == null) {
            ItemTypes.BLACKSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemBlackstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.BLACKSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.BLACKSTONE_SLAB == null) {
            ItemTypes.BLACKSTONE_SLAB = AllayItemType
                            .builder(ItemBlackstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.BLACKSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.BLACKSTONE_STAIRS == null) {
            ItemTypes.BLACKSTONE_STAIRS = AllayItemType
                            .builder(ItemBlackstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.BLACKSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.BLACKSTONE_WALL == null) {
            ItemTypes.BLACKSTONE_WALL = AllayItemType
                            .builder(ItemBlackstoneWallStackImpl.class)
                            .vanillaItem(ItemId.BLACKSTONE_WALL)
                            .build();
        }
        if (ItemTypes.BLADE_POTTERY_SHERD == null) {
            ItemTypes.BLADE_POTTERY_SHERD = AllayItemType
                            .builder(ItemBladePotterySherdStackImpl.class)
                            .vanillaItem(ItemId.BLADE_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.BLAST_FURNACE == null) {
            ItemTypes.BLAST_FURNACE = AllayItemType
                            .builder(ItemBlastFurnaceStackImpl.class)
                            .vanillaItem(ItemId.BLAST_FURNACE)
                            .build();
        }
        if (ItemTypes.BLAZE_POWDER == null) {
            ItemTypes.BLAZE_POWDER = AllayItemType
                            .builder(ItemBlazePowderStackImpl.class)
                            .vanillaItem(ItemId.BLAZE_POWDER)
                            .build();
        }
        if (ItemTypes.BLAZE_ROD == null) {
            ItemTypes.BLAZE_ROD = AllayItemType
                            .builder(ItemBlazeRodStackImpl.class)
                            .vanillaItem(ItemId.BLAZE_ROD)
                            .build();
        }
        if (ItemTypes.BLEACH == null) {
            ItemTypes.BLEACH = AllayItemType
                            .builder(ItemBleachStackImpl.class)
                            .vanillaItem(ItemId.BLEACH)
                            .build();
        }
        if (ItemTypes.BLUE_BUNDLE == null) {
            ItemTypes.BLUE_BUNDLE = AllayItemType
                            .builder(ItemBlueBundleStackImpl.class)
                            .vanillaItem(ItemId.BLUE_BUNDLE)
                            .build();
        }
        if (ItemTypes.BLUE_CANDLE == null) {
            ItemTypes.BLUE_CANDLE = AllayItemType
                            .builder(ItemBlueCandleStackImpl.class)
                            .vanillaItem(ItemId.BLUE_CANDLE)
                            .build();
        }
        if (ItemTypes.BLUE_CANDLE_CAKE == null) {
            ItemTypes.BLUE_CANDLE_CAKE = AllayItemType
                            .builder(ItemBlueCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.BLUE_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.BLUE_CARPET == null) {
            ItemTypes.BLUE_CARPET = AllayItemType
                            .builder(ItemBlueCarpetStackImpl.class)
                            .vanillaItem(ItemId.BLUE_CARPET)
                            .build();
        }
        if (ItemTypes.BLUE_CONCRETE == null) {
            ItemTypes.BLUE_CONCRETE = AllayItemType
                            .builder(ItemBlueConcreteStackImpl.class)
                            .vanillaItem(ItemId.BLUE_CONCRETE)
                            .build();
        }
        if (ItemTypes.BLUE_CONCRETE_POWDER == null) {
            ItemTypes.BLUE_CONCRETE_POWDER = AllayItemType
                            .builder(ItemBlueConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.BLUE_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.BLUE_DYE == null) {
            ItemTypes.BLUE_DYE = AllayItemType
                            .builder(ItemBlueDyeStackImpl.class)
                            .vanillaItem(ItemId.BLUE_DYE)
                            .build();
        }
        if (ItemTypes.BLUE_GLAZED_TERRACOTTA == null) {
            ItemTypes.BLUE_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemBlueGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BLUE_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BLUE_ICE == null) {
            ItemTypes.BLUE_ICE = AllayItemType
                            .builder(ItemBlueIceStackImpl.class)
                            .vanillaItem(ItemId.BLUE_ICE)
                            .build();
        }
        if (ItemTypes.BLUE_ORCHID == null) {
            ItemTypes.BLUE_ORCHID = AllayItemType
                            .builder(ItemBlueOrchidStackImpl.class)
                            .vanillaItem(ItemId.BLUE_ORCHID)
                            .build();
        }
        if (ItemTypes.BLUE_STAINED_GLASS == null) {
            ItemTypes.BLUE_STAINED_GLASS = AllayItemType
                            .builder(ItemBlueStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.BLUE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.BLUE_STAINED_GLASS_PANE == null) {
            ItemTypes.BLUE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemBlueStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.BLUE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.BLUE_TERRACOTTA == null) {
            ItemTypes.BLUE_TERRACOTTA = AllayItemType
                            .builder(ItemBlueTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BLUE_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BLUE_WOOL == null) {
            ItemTypes.BLUE_WOOL = AllayItemType
                            .builder(ItemBlueWoolStackImpl.class)
                            .vanillaItem(ItemId.BLUE_WOOL)
                            .build();
        }
        if (ItemTypes.BOAT == null) {
            ItemTypes.BOAT = AllayItemType
                            .builder(ItemBoatStackImpl.class)
                            .vanillaItem(ItemId.BOAT)
                            .build();
        }
        if (ItemTypes.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemBoltArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.BONE == null) {
            ItemTypes.BONE = AllayItemType
                            .builder(ItemBoneStackImpl.class)
                            .vanillaItem(ItemId.BONE)
                            .build();
        }
        if (ItemTypes.BONE_BLOCK == null) {
            ItemTypes.BONE_BLOCK = AllayItemType
                            .builder(ItemBoneBlockStackImpl.class)
                            .vanillaItem(ItemId.BONE_BLOCK)
                            .build();
        }
        if (ItemTypes.BONE_MEAL == null) {
            ItemTypes.BONE_MEAL = AllayItemType
                            .builder(ItemBoneMealStackImpl.class)
                            .vanillaItem(ItemId.BONE_MEAL)
                            .build();
        }
        if (ItemTypes.BOOK == null) {
            ItemTypes.BOOK = AllayItemType
                            .builder(ItemBookStackImpl.class)
                            .vanillaItem(ItemId.BOOK)
                            .build();
        }
        if (ItemTypes.BOOKSHELF == null) {
            ItemTypes.BOOKSHELF = AllayItemType
                            .builder(ItemBookshelfStackImpl.class)
                            .vanillaItem(ItemId.BOOKSHELF)
                            .build();
        }
        if (ItemTypes.BORDER_BLOCK == null) {
            ItemTypes.BORDER_BLOCK = AllayItemType
                            .builder(ItemBorderBlockStackImpl.class)
                            .vanillaItem(ItemId.BORDER_BLOCK)
                            .build();
        }
        if (ItemTypes.BORDURE_INDENTED_BANNER_PATTERN == null) {
            ItemTypes.BORDURE_INDENTED_BANNER_PATTERN = AllayItemType
                            .builder(ItemBordureIndentedBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.BORDURE_INDENTED_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.BOW == null) {
            ItemTypes.BOW = AllayItemType
                            .builder(ItemBowStackImpl.class)
                            .vanillaItem(ItemId.BOW)
                            .build();
        }
        if (ItemTypes.BOWL == null) {
            ItemTypes.BOWL = AllayItemType
                            .builder(ItemBowlStackImpl.class)
                            .vanillaItem(ItemId.BOWL)
                            .build();
        }
        if (ItemTypes.BRAIN_CORAL == null) {
            ItemTypes.BRAIN_CORAL = AllayItemType
                            .builder(ItemBrainCoralStackImpl.class)
                            .vanillaItem(ItemId.BRAIN_CORAL)
                            .build();
        }
        if (ItemTypes.BRAIN_CORAL_BLOCK == null) {
            ItemTypes.BRAIN_CORAL_BLOCK = AllayItemType
                            .builder(ItemBrainCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.BRAIN_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.BRAIN_CORAL_FAN == null) {
            ItemTypes.BRAIN_CORAL_FAN = AllayItemType
                            .builder(ItemBrainCoralFanStackImpl.class)
                            .vanillaItem(ItemId.BRAIN_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.BRAIN_CORAL_WALL_FAN == null) {
            ItemTypes.BRAIN_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemBrainCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.BRAIN_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.BREAD == null) {
            ItemTypes.BREAD = AllayItemType
                            .builder(ItemBreadStackImpl.class)
                            .vanillaItem(ItemId.BREAD)
                            .build();
        }
        if (ItemTypes.BREEZE_ROD == null) {
            ItemTypes.BREEZE_ROD = AllayItemType
                            .builder(ItemBreezeRodStackImpl.class)
                            .vanillaItem(ItemId.BREEZE_ROD)
                            .build();
        }
        if (ItemTypes.BREWER_POTTERY_SHERD == null) {
            ItemTypes.BREWER_POTTERY_SHERD = AllayItemType
                            .builder(ItemBrewerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.BREWER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.BREWING_STAND == null) {
            ItemTypes.BREWING_STAND = AllayItemType
                            .builder(ItemBrewingStandStackImpl.class)
                            .vanillaItem(ItemId.BREWING_STAND)
                            .build();
        }
        if (ItemTypes.BRICK == null) {
            ItemTypes.BRICK = AllayItemType
                            .builder(ItemBrickStackImpl.class)
                            .vanillaItem(ItemId.BRICK)
                            .build();
        }
        if (ItemTypes.BRICK_BLOCK == null) {
            ItemTypes.BRICK_BLOCK = AllayItemType
                            .builder(ItemBrickBlockStackImpl.class)
                            .vanillaItem(ItemId.BRICK_BLOCK)
                            .build();
        }
        if (ItemTypes.BRICK_DOUBLE_SLAB == null) {
            ItemTypes.BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.BRICK_SLAB == null) {
            ItemTypes.BRICK_SLAB = AllayItemType
                            .builder(ItemBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.BRICK_STAIRS == null) {
            ItemTypes.BRICK_STAIRS = AllayItemType
                            .builder(ItemBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.BRICK_WALL == null) {
            ItemTypes.BRICK_WALL = AllayItemType
                            .builder(ItemBrickWallStackImpl.class)
                            .vanillaItem(ItemId.BRICK_WALL)
                            .build();
        }
        if (ItemTypes.BROWN_BUNDLE == null) {
            ItemTypes.BROWN_BUNDLE = AllayItemType
                            .builder(ItemBrownBundleStackImpl.class)
                            .vanillaItem(ItemId.BROWN_BUNDLE)
                            .build();
        }
        if (ItemTypes.BROWN_CANDLE == null) {
            ItemTypes.BROWN_CANDLE = AllayItemType
                            .builder(ItemBrownCandleStackImpl.class)
                            .vanillaItem(ItemId.BROWN_CANDLE)
                            .build();
        }
        if (ItemTypes.BROWN_CANDLE_CAKE == null) {
            ItemTypes.BROWN_CANDLE_CAKE = AllayItemType
                            .builder(ItemBrownCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.BROWN_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.BROWN_CARPET == null) {
            ItemTypes.BROWN_CARPET = AllayItemType
                            .builder(ItemBrownCarpetStackImpl.class)
                            .vanillaItem(ItemId.BROWN_CARPET)
                            .build();
        }
        if (ItemTypes.BROWN_CONCRETE == null) {
            ItemTypes.BROWN_CONCRETE = AllayItemType
                            .builder(ItemBrownConcreteStackImpl.class)
                            .vanillaItem(ItemId.BROWN_CONCRETE)
                            .build();
        }
        if (ItemTypes.BROWN_CONCRETE_POWDER == null) {
            ItemTypes.BROWN_CONCRETE_POWDER = AllayItemType
                            .builder(ItemBrownConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.BROWN_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.BROWN_DYE == null) {
            ItemTypes.BROWN_DYE = AllayItemType
                            .builder(ItemBrownDyeStackImpl.class)
                            .vanillaItem(ItemId.BROWN_DYE)
                            .build();
        }
        if (ItemTypes.BROWN_GLAZED_TERRACOTTA == null) {
            ItemTypes.BROWN_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemBrownGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BROWN_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BROWN_MUSHROOM == null) {
            ItemTypes.BROWN_MUSHROOM = AllayItemType
                            .builder(ItemBrownMushroomStackImpl.class)
                            .vanillaItem(ItemId.BROWN_MUSHROOM)
                            .build();
        }
        if (ItemTypes.BROWN_MUSHROOM_BLOCK == null) {
            ItemTypes.BROWN_MUSHROOM_BLOCK = AllayItemType
                            .builder(ItemBrownMushroomBlockStackImpl.class)
                            .vanillaItem(ItemId.BROWN_MUSHROOM_BLOCK)
                            .build();
        }
        if (ItemTypes.BROWN_STAINED_GLASS == null) {
            ItemTypes.BROWN_STAINED_GLASS = AllayItemType
                            .builder(ItemBrownStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.BROWN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.BROWN_STAINED_GLASS_PANE == null) {
            ItemTypes.BROWN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemBrownStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.BROWN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.BROWN_TERRACOTTA == null) {
            ItemTypes.BROWN_TERRACOTTA = AllayItemType
                            .builder(ItemBrownTerracottaStackImpl.class)
                            .vanillaItem(ItemId.BROWN_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.BROWN_WOOL == null) {
            ItemTypes.BROWN_WOOL = AllayItemType
                            .builder(ItemBrownWoolStackImpl.class)
                            .vanillaItem(ItemId.BROWN_WOOL)
                            .build();
        }
        if (ItemTypes.BRUSH == null) {
            ItemTypes.BRUSH = AllayItemType
                            .builder(ItemBrushStackImpl.class)
                            .vanillaItem(ItemId.BRUSH)
                            .build();
        }
        if (ItemTypes.BUBBLE_COLUMN == null) {
            ItemTypes.BUBBLE_COLUMN = AllayItemType
                            .builder(ItemBubbleColumnStackImpl.class)
                            .vanillaItem(ItemId.BUBBLE_COLUMN)
                            .build();
        }
        if (ItemTypes.BUBBLE_CORAL == null) {
            ItemTypes.BUBBLE_CORAL = AllayItemType
                            .builder(ItemBubbleCoralStackImpl.class)
                            .vanillaItem(ItemId.BUBBLE_CORAL)
                            .build();
        }
        if (ItemTypes.BUBBLE_CORAL_BLOCK == null) {
            ItemTypes.BUBBLE_CORAL_BLOCK = AllayItemType
                            .builder(ItemBubbleCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.BUBBLE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.BUBBLE_CORAL_FAN == null) {
            ItemTypes.BUBBLE_CORAL_FAN = AllayItemType
                            .builder(ItemBubbleCoralFanStackImpl.class)
                            .vanillaItem(ItemId.BUBBLE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.BUBBLE_CORAL_WALL_FAN == null) {
            ItemTypes.BUBBLE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemBubbleCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.BUBBLE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.BUDDING_AMETHYST == null) {
            ItemTypes.BUDDING_AMETHYST = AllayItemType
                            .builder(ItemBuddingAmethystStackImpl.class)
                            .vanillaItem(ItemId.BUDDING_AMETHYST)
                            .build();
        }
        if (ItemTypes.BUNDLE == null) {
            ItemTypes.BUNDLE = AllayItemType
                            .builder(ItemBundleStackImpl.class)
                            .vanillaItem(ItemId.BUNDLE)
                            .build();
        }
        if (ItemTypes.BURN_POTTERY_SHERD == null) {
            ItemTypes.BURN_POTTERY_SHERD = AllayItemType
                            .builder(ItemBurnPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.BURN_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.CACTUS == null) {
            ItemTypes.CACTUS = AllayItemType
                            .builder(ItemCactusStackImpl.class)
                            .vanillaItem(ItemId.CACTUS)
                            .build();
        }
        if (ItemTypes.CAKE == null) {
            ItemTypes.CAKE = AllayItemType
                            .builder(ItemCakeStackImpl.class)
                            .vanillaItem(ItemId.CAKE)
                            .build();
        }
        if (ItemTypes.CALCITE == null) {
            ItemTypes.CALCITE = AllayItemType
                            .builder(ItemCalciteStackImpl.class)
                            .vanillaItem(ItemId.CALCITE)
                            .build();
        }
        if (ItemTypes.CALIBRATED_SCULK_SENSOR == null) {
            ItemTypes.CALIBRATED_SCULK_SENSOR = AllayItemType
                            .builder(ItemCalibratedSculkSensorStackImpl.class)
                            .vanillaItem(ItemId.CALIBRATED_SCULK_SENSOR)
                            .build();
        }
        if (ItemTypes.CAMERA == null) {
            ItemTypes.CAMERA = AllayItemType
                            .builder(ItemCameraStackImpl.class)
                            .vanillaItem(ItemId.CAMERA)
                            .build();
        }
        if (ItemTypes.CAMPFIRE == null) {
            ItemTypes.CAMPFIRE = AllayItemType
                            .builder(ItemCampfireStackImpl.class)
                            .vanillaItem(ItemId.CAMPFIRE)
                            .build();
        }
        if (ItemTypes.CANDLE == null) {
            ItemTypes.CANDLE = AllayItemType
                            .builder(ItemCandleStackImpl.class)
                            .vanillaItem(ItemId.CANDLE)
                            .build();
        }
        if (ItemTypes.CANDLE_CAKE == null) {
            ItemTypes.CANDLE_CAKE = AllayItemType
                            .builder(ItemCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.CARPET == null) {
            ItemTypes.CARPET = AllayItemType
                            .builder(ItemCarpetStackImpl.class)
                            .vanillaItem(ItemId.CARPET)
                            .build();
        }
        if (ItemTypes.CARROT == null) {
            ItemTypes.CARROT = AllayItemType
                            .builder(ItemCarrotStackImpl.class)
                            .vanillaItem(ItemId.CARROT)
                            .build();
        }
        if (ItemTypes.CARROT_ON_A_STICK == null) {
            ItemTypes.CARROT_ON_A_STICK = AllayItemType
                            .builder(ItemCarrotOnAStickStackImpl.class)
                            .vanillaItem(ItemId.CARROT_ON_A_STICK)
                            .build();
        }
        if (ItemTypes.CARROTS == null) {
            ItemTypes.CARROTS = AllayItemType
                            .builder(ItemCarrotsStackImpl.class)
                            .vanillaItem(ItemId.CARROTS)
                            .build();
        }
        if (ItemTypes.CARTOGRAPHY_TABLE == null) {
            ItemTypes.CARTOGRAPHY_TABLE = AllayItemType
                            .builder(ItemCartographyTableStackImpl.class)
                            .vanillaItem(ItemId.CARTOGRAPHY_TABLE)
                            .build();
        }
        if (ItemTypes.CARVED_PUMPKIN == null) {
            ItemTypes.CARVED_PUMPKIN = AllayItemType
                            .builder(ItemCarvedPumpkinStackImpl.class)
                            .vanillaItem(ItemId.CARVED_PUMPKIN)
                            .build();
        }
        if (ItemTypes.CAULDRON == null) {
            ItemTypes.CAULDRON = AllayItemType
                            .builder(ItemCauldronStackImpl.class)
                            .vanillaItem(ItemId.CAULDRON)
                            .build();
        }
        if (ItemTypes.CAVE_VINES == null) {
            ItemTypes.CAVE_VINES = AllayItemType
                            .builder(ItemCaveVinesStackImpl.class)
                            .vanillaItem(ItemId.CAVE_VINES)
                            .build();
        }
        if (ItemTypes.CAVE_VINES_BODY_WITH_BERRIES == null) {
            ItemTypes.CAVE_VINES_BODY_WITH_BERRIES = AllayItemType
                            .builder(ItemCaveVinesBodyWithBerriesStackImpl.class)
                            .vanillaItem(ItemId.CAVE_VINES_BODY_WITH_BERRIES)
                            .build();
        }
        if (ItemTypes.CAVE_VINES_HEAD_WITH_BERRIES == null) {
            ItemTypes.CAVE_VINES_HEAD_WITH_BERRIES = AllayItemType
                            .builder(ItemCaveVinesHeadWithBerriesStackImpl.class)
                            .vanillaItem(ItemId.CAVE_VINES_HEAD_WITH_BERRIES)
                            .build();
        }
        if (ItemTypes.CHAIN == null) {
            ItemTypes.CHAIN = AllayItemType
                            .builder(ItemChainStackImpl.class)
                            .vanillaItem(ItemId.CHAIN)
                            .build();
        }
        if (ItemTypes.CHAIN_COMMAND_BLOCK == null) {
            ItemTypes.CHAIN_COMMAND_BLOCK = AllayItemType
                            .builder(ItemChainCommandBlockStackImpl.class)
                            .vanillaItem(ItemId.CHAIN_COMMAND_BLOCK)
                            .build();
        }
        if (ItemTypes.CHARCOAL == null) {
            ItemTypes.CHARCOAL = AllayItemType
                            .builder(ItemCharcoalStackImpl.class)
                            .vanillaItem(ItemId.CHARCOAL)
                            .build();
        }
        if (ItemTypes.CHEMICAL_HEAT == null) {
            ItemTypes.CHEMICAL_HEAT = AllayItemType
                            .builder(ItemChemicalHeatStackImpl.class)
                            .vanillaItem(ItemId.CHEMICAL_HEAT)
                            .build();
        }
        if (ItemTypes.CHEMISTRY_TABLE == null) {
            ItemTypes.CHEMISTRY_TABLE = AllayItemType
                            .builder(ItemChemistryTableStackImpl.class)
                            .vanillaItem(ItemId.CHEMISTRY_TABLE)
                            .build();
        }
        if (ItemTypes.CHERRY_BOAT == null) {
            ItemTypes.CHERRY_BOAT = AllayItemType
                            .builder(ItemCherryBoatStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_BOAT)
                            .build();
        }
        if (ItemTypes.CHERRY_BUTTON == null) {
            ItemTypes.CHERRY_BUTTON = AllayItemType
                            .builder(ItemCherryButtonStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_BUTTON)
                            .build();
        }
        if (ItemTypes.CHERRY_CHEST_BOAT == null) {
            ItemTypes.CHERRY_CHEST_BOAT = AllayItemType
                            .builder(ItemCherryChestBoatStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.CHERRY_DOOR == null) {
            ItemTypes.CHERRY_DOOR = AllayItemType
                            .builder(ItemCherryDoorStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_DOOR)
                            .build();
        }
        if (ItemTypes.CHERRY_DOUBLE_SLAB == null) {
            ItemTypes.CHERRY_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCherryDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.CHERRY_FENCE == null) {
            ItemTypes.CHERRY_FENCE = AllayItemType
                            .builder(ItemCherryFenceStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_FENCE)
                            .build();
        }
        if (ItemTypes.CHERRY_FENCE_GATE == null) {
            ItemTypes.CHERRY_FENCE_GATE = AllayItemType
                            .builder(ItemCherryFenceGateStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.CHERRY_HANGING_SIGN == null) {
            ItemTypes.CHERRY_HANGING_SIGN = AllayItemType
                            .builder(ItemCherryHangingSignStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.CHERRY_LEAVES == null) {
            ItemTypes.CHERRY_LEAVES = AllayItemType
                            .builder(ItemCherryLeavesStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_LEAVES)
                            .build();
        }
        if (ItemTypes.CHERRY_LOG == null) {
            ItemTypes.CHERRY_LOG = AllayItemType
                            .builder(ItemCherryLogStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_LOG)
                            .build();
        }
        if (ItemTypes.CHERRY_PLANKS == null) {
            ItemTypes.CHERRY_PLANKS = AllayItemType
                            .builder(ItemCherryPlanksStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_PLANKS)
                            .build();
        }
        if (ItemTypes.CHERRY_PRESSURE_PLATE == null) {
            ItemTypes.CHERRY_PRESSURE_PLATE = AllayItemType
                            .builder(ItemCherryPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.CHERRY_SAPLING == null) {
            ItemTypes.CHERRY_SAPLING = AllayItemType
                            .builder(ItemCherrySaplingStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_SAPLING)
                            .build();
        }
        if (ItemTypes.CHERRY_SLAB == null) {
            ItemTypes.CHERRY_SLAB = AllayItemType
                            .builder(ItemCherrySlabStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_SLAB)
                            .build();
        }
        if (ItemTypes.CHERRY_STAIRS == null) {
            ItemTypes.CHERRY_STAIRS = AllayItemType
                            .builder(ItemCherryStairsStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_STAIRS)
                            .build();
        }
        if (ItemTypes.CHERRY_STANDING_SIGN == null) {
            ItemTypes.CHERRY_STANDING_SIGN = AllayItemType
                            .builder(ItemCherryStandingSignStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.CHERRY_TRAPDOOR == null) {
            ItemTypes.CHERRY_TRAPDOOR = AllayItemType
                            .builder(ItemCherryTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.CHERRY_WALL_SIGN == null) {
            ItemTypes.CHERRY_WALL_SIGN = AllayItemType
                            .builder(ItemCherryWallSignStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.CHERRY_WOOD == null) {
            ItemTypes.CHERRY_WOOD = AllayItemType
                            .builder(ItemCherryWoodStackImpl.class)
                            .vanillaItem(ItemId.CHERRY_WOOD)
                            .build();
        }
        if (ItemTypes.CHEST == null) {
            ItemTypes.CHEST = AllayItemType
                            .builder(ItemChestStackImpl.class)
                            .vanillaItem(ItemId.CHEST)
                            .build();
        }
        if (ItemTypes.CHEST_BOAT == null) {
            ItemTypes.CHEST_BOAT = AllayItemType
                            .builder(ItemChestBoatStackImpl.class)
                            .vanillaItem(ItemId.CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.CHEST_MINECART == null) {
            ItemTypes.CHEST_MINECART = AllayItemType
                            .builder(ItemChestMinecartStackImpl.class)
                            .vanillaItem(ItemId.CHEST_MINECART)
                            .build();
        }
        if (ItemTypes.CHICKEN == null) {
            ItemTypes.CHICKEN = AllayItemType
                            .builder(ItemChickenStackImpl.class)
                            .vanillaItem(ItemId.CHICKEN)
                            .build();
        }
        if (ItemTypes.CHIPPED_ANVIL == null) {
            ItemTypes.CHIPPED_ANVIL = AllayItemType
                            .builder(ItemChippedAnvilStackImpl.class)
                            .vanillaItem(ItemId.CHIPPED_ANVIL)
                            .build();
        }
        if (ItemTypes.CHISELED_BOOKSHELF == null) {
            ItemTypes.CHISELED_BOOKSHELF = AllayItemType
                            .builder(ItemChiseledBookshelfStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_BOOKSHELF)
                            .build();
        }
        if (ItemTypes.CHISELED_COPPER == null) {
            ItemTypes.CHISELED_COPPER = AllayItemType
                            .builder(ItemChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.CHISELED_DEEPSLATE == null) {
            ItemTypes.CHISELED_DEEPSLATE = AllayItemType
                            .builder(ItemChiseledDeepslateStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_DEEPSLATE)
                            .build();
        }
        if (ItemTypes.CHISELED_NETHER_BRICKS == null) {
            ItemTypes.CHISELED_NETHER_BRICKS = AllayItemType
                            .builder(ItemChiseledNetherBricksStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_NETHER_BRICKS)
                            .build();
        }
        if (ItemTypes.CHISELED_POLISHED_BLACKSTONE == null) {
            ItemTypes.CHISELED_POLISHED_BLACKSTONE = AllayItemType
                            .builder(ItemChiseledPolishedBlackstoneStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_POLISHED_BLACKSTONE)
                            .build();
        }
        if (ItemTypes.CHISELED_QUARTZ_BLOCK == null) {
            ItemTypes.CHISELED_QUARTZ_BLOCK = AllayItemType
                            .builder(ItemChiseledQuartzBlockStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_QUARTZ_BLOCK)
                            .build();
        }
        if (ItemTypes.CHISELED_RED_SANDSTONE == null) {
            ItemTypes.CHISELED_RED_SANDSTONE = AllayItemType
                            .builder(ItemChiseledRedSandstoneStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_RED_SANDSTONE)
                            .build();
        }
        if (ItemTypes.CHISELED_SANDSTONE == null) {
            ItemTypes.CHISELED_SANDSTONE = AllayItemType
                            .builder(ItemChiseledSandstoneStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_SANDSTONE)
                            .build();
        }
        if (ItemTypes.CHISELED_STONE_BRICKS == null) {
            ItemTypes.CHISELED_STONE_BRICKS = AllayItemType
                            .builder(ItemChiseledStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.CHISELED_TUFF == null) {
            ItemTypes.CHISELED_TUFF = AllayItemType
                            .builder(ItemChiseledTuffStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_TUFF)
                            .build();
        }
        if (ItemTypes.CHISELED_TUFF_BRICKS == null) {
            ItemTypes.CHISELED_TUFF_BRICKS = AllayItemType
                            .builder(ItemChiseledTuffBricksStackImpl.class)
                            .vanillaItem(ItemId.CHISELED_TUFF_BRICKS)
                            .build();
        }
        if (ItemTypes.CHORUS_FLOWER == null) {
            ItemTypes.CHORUS_FLOWER = AllayItemType
                            .builder(ItemChorusFlowerStackImpl.class)
                            .vanillaItem(ItemId.CHORUS_FLOWER)
                            .build();
        }
        if (ItemTypes.CHORUS_FRUIT == null) {
            ItemTypes.CHORUS_FRUIT = AllayItemType
                            .builder(ItemChorusFruitStackImpl.class)
                            .vanillaItem(ItemId.CHORUS_FRUIT)
                            .build();
        }
        if (ItemTypes.CHORUS_PLANT == null) {
            ItemTypes.CHORUS_PLANT = AllayItemType
                            .builder(ItemChorusPlantStackImpl.class)
                            .vanillaItem(ItemId.CHORUS_PLANT)
                            .build();
        }
        if (ItemTypes.CLAY == null) {
            ItemTypes.CLAY = AllayItemType
                            .builder(ItemClayStackImpl.class)
                            .vanillaItem(ItemId.CLAY)
                            .build();
        }
        if (ItemTypes.CLAY_BALL == null) {
            ItemTypes.CLAY_BALL = AllayItemType
                            .builder(ItemClayBallStackImpl.class)
                            .vanillaItem(ItemId.CLAY_BALL)
                            .build();
        }
        if (ItemTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK == null) {
            ItemTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = AllayItemType
                            .builder(ItemClientRequestPlaceholderBlockStackImpl.class)
                            .vanillaItem(ItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                            .build();
        }
        if (ItemTypes.CLOCK == null) {
            ItemTypes.CLOCK = AllayItemType
                            .builder(ItemClockStackImpl.class)
                            .vanillaItem(ItemId.CLOCK)
                            .build();
        }
        if (ItemTypes.COAL == null) {
            ItemTypes.COAL = AllayItemType
                            .builder(ItemCoalStackImpl.class)
                            .vanillaItem(ItemId.COAL)
                            .build();
        }
        if (ItemTypes.COAL_BLOCK == null) {
            ItemTypes.COAL_BLOCK = AllayItemType
                            .builder(ItemCoalBlockStackImpl.class)
                            .vanillaItem(ItemId.COAL_BLOCK)
                            .build();
        }
        if (ItemTypes.COAL_ORE == null) {
            ItemTypes.COAL_ORE = AllayItemType
                            .builder(ItemCoalOreStackImpl.class)
                            .vanillaItem(ItemId.COAL_ORE)
                            .build();
        }
        if (ItemTypes.COARSE_DIRT == null) {
            ItemTypes.COARSE_DIRT = AllayItemType
                            .builder(ItemCoarseDirtStackImpl.class)
                            .vanillaItem(ItemId.COARSE_DIRT)
                            .build();
        }
        if (ItemTypes.COAST_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.COAST_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemCoastArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.COBBLED_DEEPSLATE == null) {
            ItemTypes.COBBLED_DEEPSLATE = AllayItemType
                            .builder(ItemCobbledDeepslateStackImpl.class)
                            .vanillaItem(ItemId.COBBLED_DEEPSLATE)
                            .build();
        }
        if (ItemTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB == null) {
            ItemTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCobbledDeepslateDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.COBBLED_DEEPSLATE_SLAB == null) {
            ItemTypes.COBBLED_DEEPSLATE_SLAB = AllayItemType
                            .builder(ItemCobbledDeepslateSlabStackImpl.class)
                            .vanillaItem(ItemId.COBBLED_DEEPSLATE_SLAB)
                            .build();
        }
        if (ItemTypes.COBBLED_DEEPSLATE_STAIRS == null) {
            ItemTypes.COBBLED_DEEPSLATE_STAIRS = AllayItemType
                            .builder(ItemCobbledDeepslateStairsStackImpl.class)
                            .vanillaItem(ItemId.COBBLED_DEEPSLATE_STAIRS)
                            .build();
        }
        if (ItemTypes.COBBLED_DEEPSLATE_WALL == null) {
            ItemTypes.COBBLED_DEEPSLATE_WALL = AllayItemType
                            .builder(ItemCobbledDeepslateWallStackImpl.class)
                            .vanillaItem(ItemId.COBBLED_DEEPSLATE_WALL)
                            .build();
        }
        if (ItemTypes.COBBLESTONE == null) {
            ItemTypes.COBBLESTONE = AllayItemType
                            .builder(ItemCobblestoneStackImpl.class)
                            .vanillaItem(ItemId.COBBLESTONE)
                            .build();
        }
        if (ItemTypes.COBBLESTONE_DOUBLE_SLAB == null) {
            ItemTypes.COBBLESTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCobblestoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.COBBLESTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.COBBLESTONE_SLAB == null) {
            ItemTypes.COBBLESTONE_SLAB = AllayItemType
                            .builder(ItemCobblestoneSlabStackImpl.class)
                            .vanillaItem(ItemId.COBBLESTONE_SLAB)
                            .build();
        }
        if (ItemTypes.COBBLESTONE_WALL == null) {
            ItemTypes.COBBLESTONE_WALL = AllayItemType
                            .builder(ItemCobblestoneWallStackImpl.class)
                            .vanillaItem(ItemId.COBBLESTONE_WALL)
                            .build();
        }
        if (ItemTypes.COCOA == null) {
            ItemTypes.COCOA = AllayItemType
                            .builder(ItemCocoaStackImpl.class)
                            .vanillaItem(ItemId.COCOA)
                            .build();
        }
        if (ItemTypes.COCOA_BEANS == null) {
            ItemTypes.COCOA_BEANS = AllayItemType
                            .builder(ItemCocoaBeansStackImpl.class)
                            .vanillaItem(ItemId.COCOA_BEANS)
                            .build();
        }
        if (ItemTypes.COD == null) {
            ItemTypes.COD = AllayItemType
                            .builder(ItemCodStackImpl.class)
                            .vanillaItem(ItemId.COD)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_BLUE == null) {
            ItemTypes.COLORED_TORCH_BLUE = AllayItemType
                            .builder(ItemColoredTorchBlueStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_BLUE)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_BP == null) {
            ItemTypes.COLORED_TORCH_BP = AllayItemType
                            .builder(ItemColoredTorchBpStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_BP)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_GREEN == null) {
            ItemTypes.COLORED_TORCH_GREEN = AllayItemType
                            .builder(ItemColoredTorchGreenStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_GREEN)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_PURPLE == null) {
            ItemTypes.COLORED_TORCH_PURPLE = AllayItemType
                            .builder(ItemColoredTorchPurpleStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_PURPLE)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_RED == null) {
            ItemTypes.COLORED_TORCH_RED = AllayItemType
                            .builder(ItemColoredTorchRedStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_RED)
                            .build();
        }
        if (ItemTypes.COLORED_TORCH_RG == null) {
            ItemTypes.COLORED_TORCH_RG = AllayItemType
                            .builder(ItemColoredTorchRgStackImpl.class)
                            .vanillaItem(ItemId.COLORED_TORCH_RG)
                            .build();
        }
        if (ItemTypes.COMMAND_BLOCK == null) {
            ItemTypes.COMMAND_BLOCK = AllayItemType
                            .builder(ItemCommandBlockStackImpl.class)
                            .vanillaItem(ItemId.COMMAND_BLOCK)
                            .build();
        }
        if (ItemTypes.COMMAND_BLOCK_MINECART == null) {
            ItemTypes.COMMAND_BLOCK_MINECART = AllayItemType
                            .builder(ItemCommandBlockMinecartStackImpl.class)
                            .vanillaItem(ItemId.COMMAND_BLOCK_MINECART)
                            .build();
        }
        if (ItemTypes.COMPARATOR == null) {
            ItemTypes.COMPARATOR = AllayItemType
                            .builder(ItemComparatorStackImpl.class)
                            .vanillaItem(ItemId.COMPARATOR)
                            .build();
        }
        if (ItemTypes.COMPASS == null) {
            ItemTypes.COMPASS = AllayItemType
                            .builder(ItemCompassStackImpl.class)
                            .vanillaItem(ItemId.COMPASS)
                            .build();
        }
        if (ItemTypes.COMPOSTER == null) {
            ItemTypes.COMPOSTER = AllayItemType
                            .builder(ItemComposterStackImpl.class)
                            .vanillaItem(ItemId.COMPOSTER)
                            .build();
        }
        if (ItemTypes.COMPOUND == null) {
            ItemTypes.COMPOUND = AllayItemType
                            .builder(ItemCompoundStackImpl.class)
                            .vanillaItem(ItemId.COMPOUND)
                            .build();
        }
        if (ItemTypes.COMPOUND_CREATOR == null) {
            ItemTypes.COMPOUND_CREATOR = AllayItemType
                            .builder(ItemCompoundCreatorStackImpl.class)
                            .vanillaItem(ItemId.COMPOUND_CREATOR)
                            .build();
        }
        if (ItemTypes.CONCRETE == null) {
            ItemTypes.CONCRETE = AllayItemType
                            .builder(ItemConcreteStackImpl.class)
                            .vanillaItem(ItemId.CONCRETE)
                            .build();
        }
        if (ItemTypes.CONCRETE_POWDER == null) {
            ItemTypes.CONCRETE_POWDER = AllayItemType
                            .builder(ItemConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.CONDUIT == null) {
            ItemTypes.CONDUIT = AllayItemType
                            .builder(ItemConduitStackImpl.class)
                            .vanillaItem(ItemId.CONDUIT)
                            .build();
        }
        if (ItemTypes.COOKED_BEEF == null) {
            ItemTypes.COOKED_BEEF = AllayItemType
                            .builder(ItemCookedBeefStackImpl.class)
                            .vanillaItem(ItemId.COOKED_BEEF)
                            .build();
        }
        if (ItemTypes.COOKED_CHICKEN == null) {
            ItemTypes.COOKED_CHICKEN = AllayItemType
                            .builder(ItemCookedChickenStackImpl.class)
                            .vanillaItem(ItemId.COOKED_CHICKEN)
                            .build();
        }
        if (ItemTypes.COOKED_COD == null) {
            ItemTypes.COOKED_COD = AllayItemType
                            .builder(ItemCookedCodStackImpl.class)
                            .vanillaItem(ItemId.COOKED_COD)
                            .build();
        }
        if (ItemTypes.COOKED_MUTTON == null) {
            ItemTypes.COOKED_MUTTON = AllayItemType
                            .builder(ItemCookedMuttonStackImpl.class)
                            .vanillaItem(ItemId.COOKED_MUTTON)
                            .build();
        }
        if (ItemTypes.COOKED_PORKCHOP == null) {
            ItemTypes.COOKED_PORKCHOP = AllayItemType
                            .builder(ItemCookedPorkchopStackImpl.class)
                            .vanillaItem(ItemId.COOKED_PORKCHOP)
                            .build();
        }
        if (ItemTypes.COOKED_RABBIT == null) {
            ItemTypes.COOKED_RABBIT = AllayItemType
                            .builder(ItemCookedRabbitStackImpl.class)
                            .vanillaItem(ItemId.COOKED_RABBIT)
                            .build();
        }
        if (ItemTypes.COOKED_SALMON == null) {
            ItemTypes.COOKED_SALMON = AllayItemType
                            .builder(ItemCookedSalmonStackImpl.class)
                            .vanillaItem(ItemId.COOKED_SALMON)
                            .build();
        }
        if (ItemTypes.COOKIE == null) {
            ItemTypes.COOKIE = AllayItemType
                            .builder(ItemCookieStackImpl.class)
                            .vanillaItem(ItemId.COOKIE)
                            .build();
        }
        if (ItemTypes.COPPER_BLOCK == null) {
            ItemTypes.COPPER_BLOCK = AllayItemType
                            .builder(ItemCopperBlockStackImpl.class)
                            .vanillaItem(ItemId.COPPER_BLOCK)
                            .build();
        }
        if (ItemTypes.COPPER_BULB == null) {
            ItemTypes.COPPER_BULB = AllayItemType
                            .builder(ItemCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.COPPER_BULB)
                            .build();
        }
        if (ItemTypes.COPPER_DOOR == null) {
            ItemTypes.COPPER_DOOR = AllayItemType
                            .builder(ItemCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.COPPER_GRATE == null) {
            ItemTypes.COPPER_GRATE = AllayItemType
                            .builder(ItemCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.COPPER_INGOT == null) {
            ItemTypes.COPPER_INGOT = AllayItemType
                            .builder(ItemCopperIngotStackImpl.class)
                            .vanillaItem(ItemId.COPPER_INGOT)
                            .build();
        }
        if (ItemTypes.COPPER_ORE == null) {
            ItemTypes.COPPER_ORE = AllayItemType
                            .builder(ItemCopperOreStackImpl.class)
                            .vanillaItem(ItemId.COPPER_ORE)
                            .build();
        }
        if (ItemTypes.COPPER_TRAPDOOR == null) {
            ItemTypes.COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.CORAL == null) {
            ItemTypes.CORAL = AllayItemType
                            .builder(ItemCoralStackImpl.class)
                            .vanillaItem(ItemId.CORAL)
                            .build();
        }
        if (ItemTypes.CORAL_BLOCK == null) {
            ItemTypes.CORAL_BLOCK = AllayItemType
                            .builder(ItemCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.CORAL_FAN == null) {
            ItemTypes.CORAL_FAN = AllayItemType
                            .builder(ItemCoralFanStackImpl.class)
                            .vanillaItem(ItemId.CORAL_FAN)
                            .build();
        }
        if (ItemTypes.CORAL_FAN_DEAD == null) {
            ItemTypes.CORAL_FAN_DEAD = AllayItemType
                            .builder(ItemCoralFanDeadStackImpl.class)
                            .vanillaItem(ItemId.CORAL_FAN_DEAD)
                            .build();
        }
        if (ItemTypes.CORNFLOWER == null) {
            ItemTypes.CORNFLOWER = AllayItemType
                            .builder(ItemCornflowerStackImpl.class)
                            .vanillaItem(ItemId.CORNFLOWER)
                            .build();
        }
        if (ItemTypes.CRACKED_DEEPSLATE_BRICKS == null) {
            ItemTypes.CRACKED_DEEPSLATE_BRICKS = AllayItemType
                            .builder(ItemCrackedDeepslateBricksStackImpl.class)
                            .vanillaItem(ItemId.CRACKED_DEEPSLATE_BRICKS)
                            .build();
        }
        if (ItemTypes.CRACKED_DEEPSLATE_TILES == null) {
            ItemTypes.CRACKED_DEEPSLATE_TILES = AllayItemType
                            .builder(ItemCrackedDeepslateTilesStackImpl.class)
                            .vanillaItem(ItemId.CRACKED_DEEPSLATE_TILES)
                            .build();
        }
        if (ItemTypes.CRACKED_NETHER_BRICKS == null) {
            ItemTypes.CRACKED_NETHER_BRICKS = AllayItemType
                            .builder(ItemCrackedNetherBricksStackImpl.class)
                            .vanillaItem(ItemId.CRACKED_NETHER_BRICKS)
                            .build();
        }
        if (ItemTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS == null) {
            ItemTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = AllayItemType
                            .builder(ItemCrackedPolishedBlackstoneBricksStackImpl.class)
                            .vanillaItem(ItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                            .build();
        }
        if (ItemTypes.CRACKED_STONE_BRICKS == null) {
            ItemTypes.CRACKED_STONE_BRICKS = AllayItemType
                            .builder(ItemCrackedStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.CRACKED_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.CRAFTER == null) {
            ItemTypes.CRAFTER = AllayItemType
                            .builder(ItemCrafterStackImpl.class)
                            .vanillaItem(ItemId.CRAFTER)
                            .build();
        }
        if (ItemTypes.CRAFTING_TABLE == null) {
            ItemTypes.CRAFTING_TABLE = AllayItemType
                            .builder(ItemCraftingTableStackImpl.class)
                            .vanillaItem(ItemId.CRAFTING_TABLE)
                            .build();
        }
        if (ItemTypes.CREEPER_BANNER_PATTERN == null) {
            ItemTypes.CREEPER_BANNER_PATTERN = AllayItemType
                            .builder(ItemCreeperBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.CREEPER_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.CREEPER_HEAD == null) {
            ItemTypes.CREEPER_HEAD = AllayItemType
                            .builder(ItemCreeperHeadStackImpl.class)
                            .vanillaItem(ItemId.CREEPER_HEAD)
                            .build();
        }
        if (ItemTypes.CRIMSON_BUTTON == null) {
            ItemTypes.CRIMSON_BUTTON = AllayItemType
                            .builder(ItemCrimsonButtonStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_BUTTON)
                            .build();
        }
        if (ItemTypes.CRIMSON_DOOR == null) {
            ItemTypes.CRIMSON_DOOR = AllayItemType
                            .builder(ItemCrimsonDoorStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_DOOR)
                            .build();
        }
        if (ItemTypes.CRIMSON_DOUBLE_SLAB == null) {
            ItemTypes.CRIMSON_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCrimsonDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.CRIMSON_FENCE == null) {
            ItemTypes.CRIMSON_FENCE = AllayItemType
                            .builder(ItemCrimsonFenceStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_FENCE)
                            .build();
        }
        if (ItemTypes.CRIMSON_FENCE_GATE == null) {
            ItemTypes.CRIMSON_FENCE_GATE = AllayItemType
                            .builder(ItemCrimsonFenceGateStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.CRIMSON_FUNGUS == null) {
            ItemTypes.CRIMSON_FUNGUS = AllayItemType
                            .builder(ItemCrimsonFungusStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_FUNGUS)
                            .build();
        }
        if (ItemTypes.CRIMSON_HANGING_SIGN == null) {
            ItemTypes.CRIMSON_HANGING_SIGN = AllayItemType
                            .builder(ItemCrimsonHangingSignStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.CRIMSON_HYPHAE == null) {
            ItemTypes.CRIMSON_HYPHAE = AllayItemType
                            .builder(ItemCrimsonHyphaeStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_HYPHAE)
                            .build();
        }
        if (ItemTypes.CRIMSON_NYLIUM == null) {
            ItemTypes.CRIMSON_NYLIUM = AllayItemType
                            .builder(ItemCrimsonNyliumStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_NYLIUM)
                            .build();
        }
        if (ItemTypes.CRIMSON_PLANKS == null) {
            ItemTypes.CRIMSON_PLANKS = AllayItemType
                            .builder(ItemCrimsonPlanksStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_PLANKS)
                            .build();
        }
        if (ItemTypes.CRIMSON_PRESSURE_PLATE == null) {
            ItemTypes.CRIMSON_PRESSURE_PLATE = AllayItemType
                            .builder(ItemCrimsonPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.CRIMSON_ROOTS == null) {
            ItemTypes.CRIMSON_ROOTS = AllayItemType
                            .builder(ItemCrimsonRootsStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_ROOTS)
                            .build();
        }
        if (ItemTypes.CRIMSON_SLAB == null) {
            ItemTypes.CRIMSON_SLAB = AllayItemType
                            .builder(ItemCrimsonSlabStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_SLAB)
                            .build();
        }
        if (ItemTypes.CRIMSON_STAIRS == null) {
            ItemTypes.CRIMSON_STAIRS = AllayItemType
                            .builder(ItemCrimsonStairsStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_STAIRS)
                            .build();
        }
        if (ItemTypes.CRIMSON_STANDING_SIGN == null) {
            ItemTypes.CRIMSON_STANDING_SIGN = AllayItemType
                            .builder(ItemCrimsonStandingSignStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.CRIMSON_STEM == null) {
            ItemTypes.CRIMSON_STEM = AllayItemType
                            .builder(ItemCrimsonStemStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_STEM)
                            .build();
        }
        if (ItemTypes.CRIMSON_TRAPDOOR == null) {
            ItemTypes.CRIMSON_TRAPDOOR = AllayItemType
                            .builder(ItemCrimsonTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.CRIMSON_WALL_SIGN == null) {
            ItemTypes.CRIMSON_WALL_SIGN = AllayItemType
                            .builder(ItemCrimsonWallSignStackImpl.class)
                            .vanillaItem(ItemId.CRIMSON_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.CROSSBOW == null) {
            ItemTypes.CROSSBOW = AllayItemType
                            .builder(ItemCrossbowStackImpl.class)
                            .vanillaItem(ItemId.CROSSBOW)
                            .build();
        }
        if (ItemTypes.CRYING_OBSIDIAN == null) {
            ItemTypes.CRYING_OBSIDIAN = AllayItemType
                            .builder(ItemCryingObsidianStackImpl.class)
                            .vanillaItem(ItemId.CRYING_OBSIDIAN)
                            .build();
        }
        if (ItemTypes.CUT_COPPER == null) {
            ItemTypes.CUT_COPPER = AllayItemType
                            .builder(ItemCutCopperStackImpl.class)
                            .vanillaItem(ItemId.CUT_COPPER)
                            .build();
        }
        if (ItemTypes.CUT_COPPER_SLAB == null) {
            ItemTypes.CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.CUT_COPPER_STAIRS == null) {
            ItemTypes.CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.CUT_RED_SANDSTONE == null) {
            ItemTypes.CUT_RED_SANDSTONE = AllayItemType
                            .builder(ItemCutRedSandstoneStackImpl.class)
                            .vanillaItem(ItemId.CUT_RED_SANDSTONE)
                            .build();
        }
        if (ItemTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCutRedSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.CUT_RED_SANDSTONE_SLAB == null) {
            ItemTypes.CUT_RED_SANDSTONE_SLAB = AllayItemType
                            .builder(ItemCutRedSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.CUT_RED_SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.CUT_SANDSTONE == null) {
            ItemTypes.CUT_SANDSTONE = AllayItemType
                            .builder(ItemCutSandstoneStackImpl.class)
                            .vanillaItem(ItemId.CUT_SANDSTONE)
                            .build();
        }
        if (ItemTypes.CUT_SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.CUT_SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemCutSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.CUT_SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.CUT_SANDSTONE_SLAB == null) {
            ItemTypes.CUT_SANDSTONE_SLAB = AllayItemType
                            .builder(ItemCutSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.CUT_SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.CYAN_BUNDLE == null) {
            ItemTypes.CYAN_BUNDLE = AllayItemType
                            .builder(ItemCyanBundleStackImpl.class)
                            .vanillaItem(ItemId.CYAN_BUNDLE)
                            .build();
        }
        if (ItemTypes.CYAN_CANDLE == null) {
            ItemTypes.CYAN_CANDLE = AllayItemType
                            .builder(ItemCyanCandleStackImpl.class)
                            .vanillaItem(ItemId.CYAN_CANDLE)
                            .build();
        }
        if (ItemTypes.CYAN_CANDLE_CAKE == null) {
            ItemTypes.CYAN_CANDLE_CAKE = AllayItemType
                            .builder(ItemCyanCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.CYAN_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.CYAN_CARPET == null) {
            ItemTypes.CYAN_CARPET = AllayItemType
                            .builder(ItemCyanCarpetStackImpl.class)
                            .vanillaItem(ItemId.CYAN_CARPET)
                            .build();
        }
        if (ItemTypes.CYAN_CONCRETE == null) {
            ItemTypes.CYAN_CONCRETE = AllayItemType
                            .builder(ItemCyanConcreteStackImpl.class)
                            .vanillaItem(ItemId.CYAN_CONCRETE)
                            .build();
        }
        if (ItemTypes.CYAN_CONCRETE_POWDER == null) {
            ItemTypes.CYAN_CONCRETE_POWDER = AllayItemType
                            .builder(ItemCyanConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.CYAN_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.CYAN_DYE == null) {
            ItemTypes.CYAN_DYE = AllayItemType
                            .builder(ItemCyanDyeStackImpl.class)
                            .vanillaItem(ItemId.CYAN_DYE)
                            .build();
        }
        if (ItemTypes.CYAN_GLAZED_TERRACOTTA == null) {
            ItemTypes.CYAN_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemCyanGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.CYAN_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.CYAN_STAINED_GLASS == null) {
            ItemTypes.CYAN_STAINED_GLASS = AllayItemType
                            .builder(ItemCyanStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.CYAN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.CYAN_STAINED_GLASS_PANE == null) {
            ItemTypes.CYAN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemCyanStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.CYAN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.CYAN_TERRACOTTA == null) {
            ItemTypes.CYAN_TERRACOTTA = AllayItemType
                            .builder(ItemCyanTerracottaStackImpl.class)
                            .vanillaItem(ItemId.CYAN_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.CYAN_WOOL == null) {
            ItemTypes.CYAN_WOOL = AllayItemType
                            .builder(ItemCyanWoolStackImpl.class)
                            .vanillaItem(ItemId.CYAN_WOOL)
                            .build();
        }
        if (ItemTypes.DAMAGED_ANVIL == null) {
            ItemTypes.DAMAGED_ANVIL = AllayItemType
                            .builder(ItemDamagedAnvilStackImpl.class)
                            .vanillaItem(ItemId.DAMAGED_ANVIL)
                            .build();
        }
        if (ItemTypes.DANDELION == null) {
            ItemTypes.DANDELION = AllayItemType
                            .builder(ItemDandelionStackImpl.class)
                            .vanillaItem(ItemId.DANDELION)
                            .build();
        }
        if (ItemTypes.DANGER_POTTERY_SHERD == null) {
            ItemTypes.DANGER_POTTERY_SHERD = AllayItemType
                            .builder(ItemDangerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.DANGER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.DARK_OAK_BOAT == null) {
            ItemTypes.DARK_OAK_BOAT = AllayItemType
                            .builder(ItemDarkOakBoatStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_BOAT)
                            .build();
        }
        if (ItemTypes.DARK_OAK_BUTTON == null) {
            ItemTypes.DARK_OAK_BUTTON = AllayItemType
                            .builder(ItemDarkOakButtonStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_BUTTON)
                            .build();
        }
        if (ItemTypes.DARK_OAK_CHEST_BOAT == null) {
            ItemTypes.DARK_OAK_CHEST_BOAT = AllayItemType
                            .builder(ItemDarkOakChestBoatStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.DARK_OAK_DOOR == null) {
            ItemTypes.DARK_OAK_DOOR = AllayItemType
                            .builder(ItemDarkOakDoorStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_DOOR)
                            .build();
        }
        if (ItemTypes.DARK_OAK_DOUBLE_SLAB == null) {
            ItemTypes.DARK_OAK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemDarkOakDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.DARK_OAK_FENCE == null) {
            ItemTypes.DARK_OAK_FENCE = AllayItemType
                            .builder(ItemDarkOakFenceStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_FENCE)
                            .build();
        }
        if (ItemTypes.DARK_OAK_FENCE_GATE == null) {
            ItemTypes.DARK_OAK_FENCE_GATE = AllayItemType
                            .builder(ItemDarkOakFenceGateStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.DARK_OAK_HANGING_SIGN == null) {
            ItemTypes.DARK_OAK_HANGING_SIGN = AllayItemType
                            .builder(ItemDarkOakHangingSignStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.DARK_OAK_LEAVES == null) {
            ItemTypes.DARK_OAK_LEAVES = AllayItemType
                            .builder(ItemDarkOakLeavesStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_LEAVES)
                            .build();
        }
        if (ItemTypes.DARK_OAK_LOG == null) {
            ItemTypes.DARK_OAK_LOG = AllayItemType
                            .builder(ItemDarkOakLogStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_LOG)
                            .build();
        }
        if (ItemTypes.DARK_OAK_PLANKS == null) {
            ItemTypes.DARK_OAK_PLANKS = AllayItemType
                            .builder(ItemDarkOakPlanksStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_PLANKS)
                            .build();
        }
        if (ItemTypes.DARK_OAK_PRESSURE_PLATE == null) {
            ItemTypes.DARK_OAK_PRESSURE_PLATE = AllayItemType
                            .builder(ItemDarkOakPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.DARK_OAK_SAPLING == null) {
            ItemTypes.DARK_OAK_SAPLING = AllayItemType
                            .builder(ItemDarkOakSaplingStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_SAPLING)
                            .build();
        }
        if (ItemTypes.DARK_OAK_SLAB == null) {
            ItemTypes.DARK_OAK_SLAB = AllayItemType
                            .builder(ItemDarkOakSlabStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_SLAB)
                            .build();
        }
        if (ItemTypes.DARK_OAK_STAIRS == null) {
            ItemTypes.DARK_OAK_STAIRS = AllayItemType
                            .builder(ItemDarkOakStairsStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_STAIRS)
                            .build();
        }
        if (ItemTypes.DARK_OAK_TRAPDOOR == null) {
            ItemTypes.DARK_OAK_TRAPDOOR = AllayItemType
                            .builder(ItemDarkOakTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.DARK_OAK_WOOD == null) {
            ItemTypes.DARK_OAK_WOOD = AllayItemType
                            .builder(ItemDarkOakWoodStackImpl.class)
                            .vanillaItem(ItemId.DARK_OAK_WOOD)
                            .build();
        }
        if (ItemTypes.DARK_PRISMARINE == null) {
            ItemTypes.DARK_PRISMARINE = AllayItemType
                            .builder(ItemDarkPrismarineStackImpl.class)
                            .vanillaItem(ItemId.DARK_PRISMARINE)
                            .build();
        }
        if (ItemTypes.DARK_PRISMARINE_DOUBLE_SLAB == null) {
            ItemTypes.DARK_PRISMARINE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemDarkPrismarineDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.DARK_PRISMARINE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.DARK_PRISMARINE_SLAB == null) {
            ItemTypes.DARK_PRISMARINE_SLAB = AllayItemType
                            .builder(ItemDarkPrismarineSlabStackImpl.class)
                            .vanillaItem(ItemId.DARK_PRISMARINE_SLAB)
                            .build();
        }
        if (ItemTypes.DARK_PRISMARINE_STAIRS == null) {
            ItemTypes.DARK_PRISMARINE_STAIRS = AllayItemType
                            .builder(ItemDarkPrismarineStairsStackImpl.class)
                            .vanillaItem(ItemId.DARK_PRISMARINE_STAIRS)
                            .build();
        }
        if (ItemTypes.DARKOAK_STANDING_SIGN == null) {
            ItemTypes.DARKOAK_STANDING_SIGN = AllayItemType
                            .builder(ItemDarkoakStandingSignStackImpl.class)
                            .vanillaItem(ItemId.DARKOAK_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.DARKOAK_WALL_SIGN == null) {
            ItemTypes.DARKOAK_WALL_SIGN = AllayItemType
                            .builder(ItemDarkoakWallSignStackImpl.class)
                            .vanillaItem(ItemId.DARKOAK_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.DAYLIGHT_DETECTOR == null) {
            ItemTypes.DAYLIGHT_DETECTOR = AllayItemType
                            .builder(ItemDaylightDetectorStackImpl.class)
                            .vanillaItem(ItemId.DAYLIGHT_DETECTOR)
                            .build();
        }
        if (ItemTypes.DAYLIGHT_DETECTOR_INVERTED == null) {
            ItemTypes.DAYLIGHT_DETECTOR_INVERTED = AllayItemType
                            .builder(ItemDaylightDetectorInvertedStackImpl.class)
                            .vanillaItem(ItemId.DAYLIGHT_DETECTOR_INVERTED)
                            .build();
        }
        if (ItemTypes.DEAD_BRAIN_CORAL == null) {
            ItemTypes.DEAD_BRAIN_CORAL = AllayItemType
                            .builder(ItemDeadBrainCoralStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BRAIN_CORAL)
                            .build();
        }
        if (ItemTypes.DEAD_BRAIN_CORAL_BLOCK == null) {
            ItemTypes.DEAD_BRAIN_CORAL_BLOCK = AllayItemType
                            .builder(ItemDeadBrainCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BRAIN_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.DEAD_BRAIN_CORAL_FAN == null) {
            ItemTypes.DEAD_BRAIN_CORAL_FAN = AllayItemType
                            .builder(ItemDeadBrainCoralFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BRAIN_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_BRAIN_CORAL_WALL_FAN == null) {
            ItemTypes.DEAD_BRAIN_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemDeadBrainCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BRAIN_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_BUBBLE_CORAL == null) {
            ItemTypes.DEAD_BUBBLE_CORAL = AllayItemType
                            .builder(ItemDeadBubbleCoralStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BUBBLE_CORAL)
                            .build();
        }
        if (ItemTypes.DEAD_BUBBLE_CORAL_BLOCK == null) {
            ItemTypes.DEAD_BUBBLE_CORAL_BLOCK = AllayItemType
                            .builder(ItemDeadBubbleCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.DEAD_BUBBLE_CORAL_FAN == null) {
            ItemTypes.DEAD_BUBBLE_CORAL_FAN = AllayItemType
                            .builder(ItemDeadBubbleCoralFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_BUBBLE_CORAL_WALL_FAN == null) {
            ItemTypes.DEAD_BUBBLE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemDeadBubbleCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_FIRE_CORAL == null) {
            ItemTypes.DEAD_FIRE_CORAL = AllayItemType
                            .builder(ItemDeadFireCoralStackImpl.class)
                            .vanillaItem(ItemId.DEAD_FIRE_CORAL)
                            .build();
        }
        if (ItemTypes.DEAD_FIRE_CORAL_BLOCK == null) {
            ItemTypes.DEAD_FIRE_CORAL_BLOCK = AllayItemType
                            .builder(ItemDeadFireCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.DEAD_FIRE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.DEAD_FIRE_CORAL_FAN == null) {
            ItemTypes.DEAD_FIRE_CORAL_FAN = AllayItemType
                            .builder(ItemDeadFireCoralFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_FIRE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_FIRE_CORAL_WALL_FAN == null) {
            ItemTypes.DEAD_FIRE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemDeadFireCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_FIRE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_HORN_CORAL == null) {
            ItemTypes.DEAD_HORN_CORAL = AllayItemType
                            .builder(ItemDeadHornCoralStackImpl.class)
                            .vanillaItem(ItemId.DEAD_HORN_CORAL)
                            .build();
        }
        if (ItemTypes.DEAD_HORN_CORAL_BLOCK == null) {
            ItemTypes.DEAD_HORN_CORAL_BLOCK = AllayItemType
                            .builder(ItemDeadHornCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.DEAD_HORN_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.DEAD_HORN_CORAL_FAN == null) {
            ItemTypes.DEAD_HORN_CORAL_FAN = AllayItemType
                            .builder(ItemDeadHornCoralFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_HORN_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_HORN_CORAL_WALL_FAN == null) {
            ItemTypes.DEAD_HORN_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemDeadHornCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_HORN_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_TUBE_CORAL == null) {
            ItemTypes.DEAD_TUBE_CORAL = AllayItemType
                            .builder(ItemDeadTubeCoralStackImpl.class)
                            .vanillaItem(ItemId.DEAD_TUBE_CORAL)
                            .build();
        }
        if (ItemTypes.DEAD_TUBE_CORAL_BLOCK == null) {
            ItemTypes.DEAD_TUBE_CORAL_BLOCK = AllayItemType
                            .builder(ItemDeadTubeCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.DEAD_TUBE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.DEAD_TUBE_CORAL_FAN == null) {
            ItemTypes.DEAD_TUBE_CORAL_FAN = AllayItemType
                            .builder(ItemDeadTubeCoralFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_TUBE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.DEAD_TUBE_CORAL_WALL_FAN == null) {
            ItemTypes.DEAD_TUBE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemDeadTubeCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.DEAD_TUBE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.DEADBUSH == null) {
            ItemTypes.DEADBUSH = AllayItemType
                            .builder(ItemDeadbushStackImpl.class)
                            .vanillaItem(ItemId.DEADBUSH)
                            .build();
        }
        if (ItemTypes.DECORATED_POT == null) {
            ItemTypes.DECORATED_POT = AllayItemType
                            .builder(ItemDecoratedPotStackImpl.class)
                            .vanillaItem(ItemId.DECORATED_POT)
                            .build();
        }
        if (ItemTypes.DEEPSLATE == null) {
            ItemTypes.DEEPSLATE = AllayItemType
                            .builder(ItemDeepslateStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemDeepslateBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_BRICK_SLAB == null) {
            ItemTypes.DEEPSLATE_BRICK_SLAB = AllayItemType
                            .builder(ItemDeepslateBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_BRICK_STAIRS == null) {
            ItemTypes.DEEPSLATE_BRICK_STAIRS = AllayItemType
                            .builder(ItemDeepslateBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_BRICK_WALL == null) {
            ItemTypes.DEEPSLATE_BRICK_WALL = AllayItemType
                            .builder(ItemDeepslateBrickWallStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_BRICKS == null) {
            ItemTypes.DEEPSLATE_BRICKS = AllayItemType
                            .builder(ItemDeepslateBricksStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_BRICKS)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_COAL_ORE == null) {
            ItemTypes.DEEPSLATE_COAL_ORE = AllayItemType
                            .builder(ItemDeepslateCoalOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_COAL_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_COPPER_ORE == null) {
            ItemTypes.DEEPSLATE_COPPER_ORE = AllayItemType
                            .builder(ItemDeepslateCopperOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_COPPER_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_DIAMOND_ORE == null) {
            ItemTypes.DEEPSLATE_DIAMOND_ORE = AllayItemType
                            .builder(ItemDeepslateDiamondOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_DIAMOND_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_EMERALD_ORE == null) {
            ItemTypes.DEEPSLATE_EMERALD_ORE = AllayItemType
                            .builder(ItemDeepslateEmeraldOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_EMERALD_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_GOLD_ORE == null) {
            ItemTypes.DEEPSLATE_GOLD_ORE = AllayItemType
                            .builder(ItemDeepslateGoldOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_GOLD_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_IRON_ORE == null) {
            ItemTypes.DEEPSLATE_IRON_ORE = AllayItemType
                            .builder(ItemDeepslateIronOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_IRON_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_LAPIS_ORE == null) {
            ItemTypes.DEEPSLATE_LAPIS_ORE = AllayItemType
                            .builder(ItemDeepslateLapisOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_LAPIS_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_REDSTONE_ORE == null) {
            ItemTypes.DEEPSLATE_REDSTONE_ORE = AllayItemType
                            .builder(ItemDeepslateRedstoneOreStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_REDSTONE_ORE)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_TILE_DOUBLE_SLAB == null) {
            ItemTypes.DEEPSLATE_TILE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemDeepslateTileDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_TILE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_TILE_SLAB == null) {
            ItemTypes.DEEPSLATE_TILE_SLAB = AllayItemType
                            .builder(ItemDeepslateTileSlabStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_TILE_SLAB)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_TILE_STAIRS == null) {
            ItemTypes.DEEPSLATE_TILE_STAIRS = AllayItemType
                            .builder(ItemDeepslateTileStairsStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_TILE_STAIRS)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_TILE_WALL == null) {
            ItemTypes.DEEPSLATE_TILE_WALL = AllayItemType
                            .builder(ItemDeepslateTileWallStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_TILE_WALL)
                            .build();
        }
        if (ItemTypes.DEEPSLATE_TILES == null) {
            ItemTypes.DEEPSLATE_TILES = AllayItemType
                            .builder(ItemDeepslateTilesStackImpl.class)
                            .vanillaItem(ItemId.DEEPSLATE_TILES)
                            .build();
        }
        if (ItemTypes.DENY == null) {
            ItemTypes.DENY = AllayItemType
                            .builder(ItemDenyStackImpl.class)
                            .vanillaItem(ItemId.DENY)
                            .build();
        }
        if (ItemTypes.DEPRECATED_ANVIL == null) {
            ItemTypes.DEPRECATED_ANVIL = AllayItemType
                            .builder(ItemDeprecatedAnvilStackImpl.class)
                            .vanillaItem(ItemId.DEPRECATED_ANVIL)
                            .build();
        }
        if (ItemTypes.DEPRECATED_PURPUR_BLOCK_1 == null) {
            ItemTypes.DEPRECATED_PURPUR_BLOCK_1 = AllayItemType
                            .builder(ItemDeprecatedPurpurBlock1StackImpl.class)
                            .vanillaItem(ItemId.DEPRECATED_PURPUR_BLOCK_1)
                            .build();
        }
        if (ItemTypes.DEPRECATED_PURPUR_BLOCK_2 == null) {
            ItemTypes.DEPRECATED_PURPUR_BLOCK_2 = AllayItemType
                            .builder(ItemDeprecatedPurpurBlock2StackImpl.class)
                            .vanillaItem(ItemId.DEPRECATED_PURPUR_BLOCK_2)
                            .build();
        }
        if (ItemTypes.DETECTOR_RAIL == null) {
            ItemTypes.DETECTOR_RAIL = AllayItemType
                            .builder(ItemDetectorRailStackImpl.class)
                            .vanillaItem(ItemId.DETECTOR_RAIL)
                            .build();
        }
        if (ItemTypes.DIAMOND == null) {
            ItemTypes.DIAMOND = AllayItemType
                            .builder(ItemDiamondStackImpl.class)
                            .vanillaItem(ItemId.DIAMOND)
                            .build();
        }
        if (ItemTypes.DIAMOND_BLOCK == null) {
            ItemTypes.DIAMOND_BLOCK = AllayItemType
                            .builder(ItemDiamondBlockStackImpl.class)
                            .vanillaItem(ItemId.DIAMOND_BLOCK)
                            .build();
        }
        if (ItemTypes.DIAMOND_HORSE_ARMOR == null) {
            ItemTypes.DIAMOND_HORSE_ARMOR = AllayItemType
                            .builder(ItemDiamondHorseArmorStackImpl.class)
                            .vanillaItem(ItemId.DIAMOND_HORSE_ARMOR)
                            .build();
        }
        if (ItemTypes.DIAMOND_ORE == null) {
            ItemTypes.DIAMOND_ORE = AllayItemType
                            .builder(ItemDiamondOreStackImpl.class)
                            .vanillaItem(ItemId.DIAMOND_ORE)
                            .build();
        }
        if (ItemTypes.DIORITE == null) {
            ItemTypes.DIORITE = AllayItemType
                            .builder(ItemDioriteStackImpl.class)
                            .vanillaItem(ItemId.DIORITE)
                            .build();
        }
        if (ItemTypes.DIORITE_DOUBLE_SLAB == null) {
            ItemTypes.DIORITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemDioriteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.DIORITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.DIORITE_SLAB == null) {
            ItemTypes.DIORITE_SLAB = AllayItemType
                            .builder(ItemDioriteSlabStackImpl.class)
                            .vanillaItem(ItemId.DIORITE_SLAB)
                            .build();
        }
        if (ItemTypes.DIORITE_STAIRS == null) {
            ItemTypes.DIORITE_STAIRS = AllayItemType
                            .builder(ItemDioriteStairsStackImpl.class)
                            .vanillaItem(ItemId.DIORITE_STAIRS)
                            .build();
        }
        if (ItemTypes.DIORITE_WALL == null) {
            ItemTypes.DIORITE_WALL = AllayItemType
                            .builder(ItemDioriteWallStackImpl.class)
                            .vanillaItem(ItemId.DIORITE_WALL)
                            .build();
        }
        if (ItemTypes.DIRT == null) {
            ItemTypes.DIRT = AllayItemType
                            .builder(ItemDirtStackImpl.class)
                            .vanillaItem(ItemId.DIRT)
                            .build();
        }
        if (ItemTypes.DIRT_WITH_ROOTS == null) {
            ItemTypes.DIRT_WITH_ROOTS = AllayItemType
                            .builder(ItemDirtWithRootsStackImpl.class)
                            .vanillaItem(ItemId.DIRT_WITH_ROOTS)
                            .build();
        }
        if (ItemTypes.DISC_FRAGMENT_5 == null) {
            ItemTypes.DISC_FRAGMENT_5 = AllayItemType
                            .builder(ItemDiscFragment5StackImpl.class)
                            .vanillaItem(ItemId.DISC_FRAGMENT_5)
                            .build();
        }
        if (ItemTypes.DISPENSER == null) {
            ItemTypes.DISPENSER = AllayItemType
                            .builder(ItemDispenserStackImpl.class)
                            .vanillaItem(ItemId.DISPENSER)
                            .build();
        }
        if (ItemTypes.DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.DOUBLE_PLANT == null) {
            ItemTypes.DOUBLE_PLANT = AllayItemType
                            .builder(ItemDoublePlantStackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_PLANT)
                            .build();
        }
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB == null) {
            ItemTypes.DOUBLE_STONE_BLOCK_SLAB = AllayItemType
                            .builder(ItemDoubleStoneBlockSlabStackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB)
                            .build();
        }
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB2 == null) {
            ItemTypes.DOUBLE_STONE_BLOCK_SLAB2 = AllayItemType
                            .builder(ItemDoubleStoneBlockSlab2StackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB2)
                            .build();
        }
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB3 == null) {
            ItemTypes.DOUBLE_STONE_BLOCK_SLAB3 = AllayItemType
                            .builder(ItemDoubleStoneBlockSlab3StackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB3)
                            .build();
        }
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB4 == null) {
            ItemTypes.DOUBLE_STONE_BLOCK_SLAB4 = AllayItemType
                            .builder(ItemDoubleStoneBlockSlab4StackImpl.class)
                            .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB4)
                            .build();
        }
        if (ItemTypes.DRAGON_BREATH == null) {
            ItemTypes.DRAGON_BREATH = AllayItemType
                            .builder(ItemDragonBreathStackImpl.class)
                            .vanillaItem(ItemId.DRAGON_BREATH)
                            .build();
        }
        if (ItemTypes.DRAGON_EGG == null) {
            ItemTypes.DRAGON_EGG = AllayItemType
                            .builder(ItemDragonEggStackImpl.class)
                            .vanillaItem(ItemId.DRAGON_EGG)
                            .build();
        }
        if (ItemTypes.DRAGON_HEAD == null) {
            ItemTypes.DRAGON_HEAD = AllayItemType
                            .builder(ItemDragonHeadStackImpl.class)
                            .vanillaItem(ItemId.DRAGON_HEAD)
                            .build();
        }
        if (ItemTypes.DRIED_KELP == null) {
            ItemTypes.DRIED_KELP = AllayItemType
                            .builder(ItemDriedKelpStackImpl.class)
                            .vanillaItem(ItemId.DRIED_KELP)
                            .build();
        }
        if (ItemTypes.DRIED_KELP_BLOCK == null) {
            ItemTypes.DRIED_KELP_BLOCK = AllayItemType
                            .builder(ItemDriedKelpBlockStackImpl.class)
                            .vanillaItem(ItemId.DRIED_KELP_BLOCK)
                            .build();
        }
        if (ItemTypes.DRIPSTONE_BLOCK == null) {
            ItemTypes.DRIPSTONE_BLOCK = AllayItemType
                            .builder(ItemDripstoneBlockStackImpl.class)
                            .vanillaItem(ItemId.DRIPSTONE_BLOCK)
                            .build();
        }
        if (ItemTypes.DROPPER == null) {
            ItemTypes.DROPPER = AllayItemType
                            .builder(ItemDropperStackImpl.class)
                            .vanillaItem(ItemId.DROPPER)
                            .build();
        }
        if (ItemTypes.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemDuneArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.DYE == null) {
            ItemTypes.DYE = AllayItemType
                            .builder(ItemDyeStackImpl.class)
                            .vanillaItem(ItemId.DYE)
                            .build();
        }
        if (ItemTypes.ECHO_SHARD == null) {
            ItemTypes.ECHO_SHARD = AllayItemType
                            .builder(ItemEchoShardStackImpl.class)
                            .vanillaItem(ItemId.ECHO_SHARD)
                            .build();
        }
        if (ItemTypes.EGG == null) {
            ItemTypes.EGG = AllayItemType
                            .builder(ItemEggStackImpl.class)
                            .vanillaItem(ItemId.EGG)
                            .build();
        }
        if (ItemTypes.ELEMENT_0 == null) {
            ItemTypes.ELEMENT_0 = AllayItemType
                            .builder(ItemElement0StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_0)
                            .build();
        }
        if (ItemTypes.ELEMENT_1 == null) {
            ItemTypes.ELEMENT_1 = AllayItemType
                            .builder(ItemElement1StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_1)
                            .build();
        }
        if (ItemTypes.ELEMENT_10 == null) {
            ItemTypes.ELEMENT_10 = AllayItemType
                            .builder(ItemElement10StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_10)
                            .build();
        }
        if (ItemTypes.ELEMENT_100 == null) {
            ItemTypes.ELEMENT_100 = AllayItemType
                            .builder(ItemElement100StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_100)
                            .build();
        }
        if (ItemTypes.ELEMENT_101 == null) {
            ItemTypes.ELEMENT_101 = AllayItemType
                            .builder(ItemElement101StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_101)
                            .build();
        }
        if (ItemTypes.ELEMENT_102 == null) {
            ItemTypes.ELEMENT_102 = AllayItemType
                            .builder(ItemElement102StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_102)
                            .build();
        }
        if (ItemTypes.ELEMENT_103 == null) {
            ItemTypes.ELEMENT_103 = AllayItemType
                            .builder(ItemElement103StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_103)
                            .build();
        }
        if (ItemTypes.ELEMENT_104 == null) {
            ItemTypes.ELEMENT_104 = AllayItemType
                            .builder(ItemElement104StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_104)
                            .build();
        }
        if (ItemTypes.ELEMENT_105 == null) {
            ItemTypes.ELEMENT_105 = AllayItemType
                            .builder(ItemElement105StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_105)
                            .build();
        }
        if (ItemTypes.ELEMENT_106 == null) {
            ItemTypes.ELEMENT_106 = AllayItemType
                            .builder(ItemElement106StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_106)
                            .build();
        }
        if (ItemTypes.ELEMENT_107 == null) {
            ItemTypes.ELEMENT_107 = AllayItemType
                            .builder(ItemElement107StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_107)
                            .build();
        }
        if (ItemTypes.ELEMENT_108 == null) {
            ItemTypes.ELEMENT_108 = AllayItemType
                            .builder(ItemElement108StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_108)
                            .build();
        }
        if (ItemTypes.ELEMENT_109 == null) {
            ItemTypes.ELEMENT_109 = AllayItemType
                            .builder(ItemElement109StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_109)
                            .build();
        }
        if (ItemTypes.ELEMENT_11 == null) {
            ItemTypes.ELEMENT_11 = AllayItemType
                            .builder(ItemElement11StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_11)
                            .build();
        }
        if (ItemTypes.ELEMENT_110 == null) {
            ItemTypes.ELEMENT_110 = AllayItemType
                            .builder(ItemElement110StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_110)
                            .build();
        }
        if (ItemTypes.ELEMENT_111 == null) {
            ItemTypes.ELEMENT_111 = AllayItemType
                            .builder(ItemElement111StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_111)
                            .build();
        }
        if (ItemTypes.ELEMENT_112 == null) {
            ItemTypes.ELEMENT_112 = AllayItemType
                            .builder(ItemElement112StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_112)
                            .build();
        }
        if (ItemTypes.ELEMENT_113 == null) {
            ItemTypes.ELEMENT_113 = AllayItemType
                            .builder(ItemElement113StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_113)
                            .build();
        }
        if (ItemTypes.ELEMENT_114 == null) {
            ItemTypes.ELEMENT_114 = AllayItemType
                            .builder(ItemElement114StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_114)
                            .build();
        }
        if (ItemTypes.ELEMENT_115 == null) {
            ItemTypes.ELEMENT_115 = AllayItemType
                            .builder(ItemElement115StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_115)
                            .build();
        }
        if (ItemTypes.ELEMENT_116 == null) {
            ItemTypes.ELEMENT_116 = AllayItemType
                            .builder(ItemElement116StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_116)
                            .build();
        }
        if (ItemTypes.ELEMENT_117 == null) {
            ItemTypes.ELEMENT_117 = AllayItemType
                            .builder(ItemElement117StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_117)
                            .build();
        }
        if (ItemTypes.ELEMENT_118 == null) {
            ItemTypes.ELEMENT_118 = AllayItemType
                            .builder(ItemElement118StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_118)
                            .build();
        }
        if (ItemTypes.ELEMENT_12 == null) {
            ItemTypes.ELEMENT_12 = AllayItemType
                            .builder(ItemElement12StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_12)
                            .build();
        }
        if (ItemTypes.ELEMENT_13 == null) {
            ItemTypes.ELEMENT_13 = AllayItemType
                            .builder(ItemElement13StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_13)
                            .build();
        }
        if (ItemTypes.ELEMENT_14 == null) {
            ItemTypes.ELEMENT_14 = AllayItemType
                            .builder(ItemElement14StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_14)
                            .build();
        }
        if (ItemTypes.ELEMENT_15 == null) {
            ItemTypes.ELEMENT_15 = AllayItemType
                            .builder(ItemElement15StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_15)
                            .build();
        }
        if (ItemTypes.ELEMENT_16 == null) {
            ItemTypes.ELEMENT_16 = AllayItemType
                            .builder(ItemElement16StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_16)
                            .build();
        }
        if (ItemTypes.ELEMENT_17 == null) {
            ItemTypes.ELEMENT_17 = AllayItemType
                            .builder(ItemElement17StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_17)
                            .build();
        }
        if (ItemTypes.ELEMENT_18 == null) {
            ItemTypes.ELEMENT_18 = AllayItemType
                            .builder(ItemElement18StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_18)
                            .build();
        }
        if (ItemTypes.ELEMENT_19 == null) {
            ItemTypes.ELEMENT_19 = AllayItemType
                            .builder(ItemElement19StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_19)
                            .build();
        }
        if (ItemTypes.ELEMENT_2 == null) {
            ItemTypes.ELEMENT_2 = AllayItemType
                            .builder(ItemElement2StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_2)
                            .build();
        }
        if (ItemTypes.ELEMENT_20 == null) {
            ItemTypes.ELEMENT_20 = AllayItemType
                            .builder(ItemElement20StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_20)
                            .build();
        }
        if (ItemTypes.ELEMENT_21 == null) {
            ItemTypes.ELEMENT_21 = AllayItemType
                            .builder(ItemElement21StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_21)
                            .build();
        }
        if (ItemTypes.ELEMENT_22 == null) {
            ItemTypes.ELEMENT_22 = AllayItemType
                            .builder(ItemElement22StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_22)
                            .build();
        }
        if (ItemTypes.ELEMENT_23 == null) {
            ItemTypes.ELEMENT_23 = AllayItemType
                            .builder(ItemElement23StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_23)
                            .build();
        }
        if (ItemTypes.ELEMENT_24 == null) {
            ItemTypes.ELEMENT_24 = AllayItemType
                            .builder(ItemElement24StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_24)
                            .build();
        }
        if (ItemTypes.ELEMENT_25 == null) {
            ItemTypes.ELEMENT_25 = AllayItemType
                            .builder(ItemElement25StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_25)
                            .build();
        }
        if (ItemTypes.ELEMENT_26 == null) {
            ItemTypes.ELEMENT_26 = AllayItemType
                            .builder(ItemElement26StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_26)
                            .build();
        }
        if (ItemTypes.ELEMENT_27 == null) {
            ItemTypes.ELEMENT_27 = AllayItemType
                            .builder(ItemElement27StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_27)
                            .build();
        }
        if (ItemTypes.ELEMENT_28 == null) {
            ItemTypes.ELEMENT_28 = AllayItemType
                            .builder(ItemElement28StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_28)
                            .build();
        }
        if (ItemTypes.ELEMENT_29 == null) {
            ItemTypes.ELEMENT_29 = AllayItemType
                            .builder(ItemElement29StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_29)
                            .build();
        }
        if (ItemTypes.ELEMENT_3 == null) {
            ItemTypes.ELEMENT_3 = AllayItemType
                            .builder(ItemElement3StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_3)
                            .build();
        }
        if (ItemTypes.ELEMENT_30 == null) {
            ItemTypes.ELEMENT_30 = AllayItemType
                            .builder(ItemElement30StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_30)
                            .build();
        }
        if (ItemTypes.ELEMENT_31 == null) {
            ItemTypes.ELEMENT_31 = AllayItemType
                            .builder(ItemElement31StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_31)
                            .build();
        }
        if (ItemTypes.ELEMENT_32 == null) {
            ItemTypes.ELEMENT_32 = AllayItemType
                            .builder(ItemElement32StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_32)
                            .build();
        }
        if (ItemTypes.ELEMENT_33 == null) {
            ItemTypes.ELEMENT_33 = AllayItemType
                            .builder(ItemElement33StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_33)
                            .build();
        }
        if (ItemTypes.ELEMENT_34 == null) {
            ItemTypes.ELEMENT_34 = AllayItemType
                            .builder(ItemElement34StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_34)
                            .build();
        }
        if (ItemTypes.ELEMENT_35 == null) {
            ItemTypes.ELEMENT_35 = AllayItemType
                            .builder(ItemElement35StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_35)
                            .build();
        }
        if (ItemTypes.ELEMENT_36 == null) {
            ItemTypes.ELEMENT_36 = AllayItemType
                            .builder(ItemElement36StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_36)
                            .build();
        }
        if (ItemTypes.ELEMENT_37 == null) {
            ItemTypes.ELEMENT_37 = AllayItemType
                            .builder(ItemElement37StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_37)
                            .build();
        }
        if (ItemTypes.ELEMENT_38 == null) {
            ItemTypes.ELEMENT_38 = AllayItemType
                            .builder(ItemElement38StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_38)
                            .build();
        }
        if (ItemTypes.ELEMENT_39 == null) {
            ItemTypes.ELEMENT_39 = AllayItemType
                            .builder(ItemElement39StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_39)
                            .build();
        }
        if (ItemTypes.ELEMENT_4 == null) {
            ItemTypes.ELEMENT_4 = AllayItemType
                            .builder(ItemElement4StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_4)
                            .build();
        }
        if (ItemTypes.ELEMENT_40 == null) {
            ItemTypes.ELEMENT_40 = AllayItemType
                            .builder(ItemElement40StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_40)
                            .build();
        }
        if (ItemTypes.ELEMENT_41 == null) {
            ItemTypes.ELEMENT_41 = AllayItemType
                            .builder(ItemElement41StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_41)
                            .build();
        }
        if (ItemTypes.ELEMENT_42 == null) {
            ItemTypes.ELEMENT_42 = AllayItemType
                            .builder(ItemElement42StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_42)
                            .build();
        }
        if (ItemTypes.ELEMENT_43 == null) {
            ItemTypes.ELEMENT_43 = AllayItemType
                            .builder(ItemElement43StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_43)
                            .build();
        }
        if (ItemTypes.ELEMENT_44 == null) {
            ItemTypes.ELEMENT_44 = AllayItemType
                            .builder(ItemElement44StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_44)
                            .build();
        }
        if (ItemTypes.ELEMENT_45 == null) {
            ItemTypes.ELEMENT_45 = AllayItemType
                            .builder(ItemElement45StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_45)
                            .build();
        }
        if (ItemTypes.ELEMENT_46 == null) {
            ItemTypes.ELEMENT_46 = AllayItemType
                            .builder(ItemElement46StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_46)
                            .build();
        }
        if (ItemTypes.ELEMENT_47 == null) {
            ItemTypes.ELEMENT_47 = AllayItemType
                            .builder(ItemElement47StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_47)
                            .build();
        }
        if (ItemTypes.ELEMENT_48 == null) {
            ItemTypes.ELEMENT_48 = AllayItemType
                            .builder(ItemElement48StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_48)
                            .build();
        }
        if (ItemTypes.ELEMENT_49 == null) {
            ItemTypes.ELEMENT_49 = AllayItemType
                            .builder(ItemElement49StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_49)
                            .build();
        }
        if (ItemTypes.ELEMENT_5 == null) {
            ItemTypes.ELEMENT_5 = AllayItemType
                            .builder(ItemElement5StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_5)
                            .build();
        }
        if (ItemTypes.ELEMENT_50 == null) {
            ItemTypes.ELEMENT_50 = AllayItemType
                            .builder(ItemElement50StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_50)
                            .build();
        }
        if (ItemTypes.ELEMENT_51 == null) {
            ItemTypes.ELEMENT_51 = AllayItemType
                            .builder(ItemElement51StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_51)
                            .build();
        }
        if (ItemTypes.ELEMENT_52 == null) {
            ItemTypes.ELEMENT_52 = AllayItemType
                            .builder(ItemElement52StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_52)
                            .build();
        }
        if (ItemTypes.ELEMENT_53 == null) {
            ItemTypes.ELEMENT_53 = AllayItemType
                            .builder(ItemElement53StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_53)
                            .build();
        }
        if (ItemTypes.ELEMENT_54 == null) {
            ItemTypes.ELEMENT_54 = AllayItemType
                            .builder(ItemElement54StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_54)
                            .build();
        }
        if (ItemTypes.ELEMENT_55 == null) {
            ItemTypes.ELEMENT_55 = AllayItemType
                            .builder(ItemElement55StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_55)
                            .build();
        }
        if (ItemTypes.ELEMENT_56 == null) {
            ItemTypes.ELEMENT_56 = AllayItemType
                            .builder(ItemElement56StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_56)
                            .build();
        }
        if (ItemTypes.ELEMENT_57 == null) {
            ItemTypes.ELEMENT_57 = AllayItemType
                            .builder(ItemElement57StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_57)
                            .build();
        }
        if (ItemTypes.ELEMENT_58 == null) {
            ItemTypes.ELEMENT_58 = AllayItemType
                            .builder(ItemElement58StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_58)
                            .build();
        }
        if (ItemTypes.ELEMENT_59 == null) {
            ItemTypes.ELEMENT_59 = AllayItemType
                            .builder(ItemElement59StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_59)
                            .build();
        }
        if (ItemTypes.ELEMENT_6 == null) {
            ItemTypes.ELEMENT_6 = AllayItemType
                            .builder(ItemElement6StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_6)
                            .build();
        }
        if (ItemTypes.ELEMENT_60 == null) {
            ItemTypes.ELEMENT_60 = AllayItemType
                            .builder(ItemElement60StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_60)
                            .build();
        }
        if (ItemTypes.ELEMENT_61 == null) {
            ItemTypes.ELEMENT_61 = AllayItemType
                            .builder(ItemElement61StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_61)
                            .build();
        }
        if (ItemTypes.ELEMENT_62 == null) {
            ItemTypes.ELEMENT_62 = AllayItemType
                            .builder(ItemElement62StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_62)
                            .build();
        }
        if (ItemTypes.ELEMENT_63 == null) {
            ItemTypes.ELEMENT_63 = AllayItemType
                            .builder(ItemElement63StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_63)
                            .build();
        }
        if (ItemTypes.ELEMENT_64 == null) {
            ItemTypes.ELEMENT_64 = AllayItemType
                            .builder(ItemElement64StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_64)
                            .build();
        }
        if (ItemTypes.ELEMENT_65 == null) {
            ItemTypes.ELEMENT_65 = AllayItemType
                            .builder(ItemElement65StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_65)
                            .build();
        }
        if (ItemTypes.ELEMENT_66 == null) {
            ItemTypes.ELEMENT_66 = AllayItemType
                            .builder(ItemElement66StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_66)
                            .build();
        }
        if (ItemTypes.ELEMENT_67 == null) {
            ItemTypes.ELEMENT_67 = AllayItemType
                            .builder(ItemElement67StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_67)
                            .build();
        }
        if (ItemTypes.ELEMENT_68 == null) {
            ItemTypes.ELEMENT_68 = AllayItemType
                            .builder(ItemElement68StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_68)
                            .build();
        }
        if (ItemTypes.ELEMENT_69 == null) {
            ItemTypes.ELEMENT_69 = AllayItemType
                            .builder(ItemElement69StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_69)
                            .build();
        }
        if (ItemTypes.ELEMENT_7 == null) {
            ItemTypes.ELEMENT_7 = AllayItemType
                            .builder(ItemElement7StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_7)
                            .build();
        }
        if (ItemTypes.ELEMENT_70 == null) {
            ItemTypes.ELEMENT_70 = AllayItemType
                            .builder(ItemElement70StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_70)
                            .build();
        }
        if (ItemTypes.ELEMENT_71 == null) {
            ItemTypes.ELEMENT_71 = AllayItemType
                            .builder(ItemElement71StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_71)
                            .build();
        }
        if (ItemTypes.ELEMENT_72 == null) {
            ItemTypes.ELEMENT_72 = AllayItemType
                            .builder(ItemElement72StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_72)
                            .build();
        }
        if (ItemTypes.ELEMENT_73 == null) {
            ItemTypes.ELEMENT_73 = AllayItemType
                            .builder(ItemElement73StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_73)
                            .build();
        }
        if (ItemTypes.ELEMENT_74 == null) {
            ItemTypes.ELEMENT_74 = AllayItemType
                            .builder(ItemElement74StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_74)
                            .build();
        }
        if (ItemTypes.ELEMENT_75 == null) {
            ItemTypes.ELEMENT_75 = AllayItemType
                            .builder(ItemElement75StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_75)
                            .build();
        }
        if (ItemTypes.ELEMENT_76 == null) {
            ItemTypes.ELEMENT_76 = AllayItemType
                            .builder(ItemElement76StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_76)
                            .build();
        }
        if (ItemTypes.ELEMENT_77 == null) {
            ItemTypes.ELEMENT_77 = AllayItemType
                            .builder(ItemElement77StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_77)
                            .build();
        }
        if (ItemTypes.ELEMENT_78 == null) {
            ItemTypes.ELEMENT_78 = AllayItemType
                            .builder(ItemElement78StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_78)
                            .build();
        }
        if (ItemTypes.ELEMENT_79 == null) {
            ItemTypes.ELEMENT_79 = AllayItemType
                            .builder(ItemElement79StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_79)
                            .build();
        }
        if (ItemTypes.ELEMENT_8 == null) {
            ItemTypes.ELEMENT_8 = AllayItemType
                            .builder(ItemElement8StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_8)
                            .build();
        }
        if (ItemTypes.ELEMENT_80 == null) {
            ItemTypes.ELEMENT_80 = AllayItemType
                            .builder(ItemElement80StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_80)
                            .build();
        }
        if (ItemTypes.ELEMENT_81 == null) {
            ItemTypes.ELEMENT_81 = AllayItemType
                            .builder(ItemElement81StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_81)
                            .build();
        }
        if (ItemTypes.ELEMENT_82 == null) {
            ItemTypes.ELEMENT_82 = AllayItemType
                            .builder(ItemElement82StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_82)
                            .build();
        }
        if (ItemTypes.ELEMENT_83 == null) {
            ItemTypes.ELEMENT_83 = AllayItemType
                            .builder(ItemElement83StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_83)
                            .build();
        }
        if (ItemTypes.ELEMENT_84 == null) {
            ItemTypes.ELEMENT_84 = AllayItemType
                            .builder(ItemElement84StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_84)
                            .build();
        }
        if (ItemTypes.ELEMENT_85 == null) {
            ItemTypes.ELEMENT_85 = AllayItemType
                            .builder(ItemElement85StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_85)
                            .build();
        }
        if (ItemTypes.ELEMENT_86 == null) {
            ItemTypes.ELEMENT_86 = AllayItemType
                            .builder(ItemElement86StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_86)
                            .build();
        }
        if (ItemTypes.ELEMENT_87 == null) {
            ItemTypes.ELEMENT_87 = AllayItemType
                            .builder(ItemElement87StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_87)
                            .build();
        }
        if (ItemTypes.ELEMENT_88 == null) {
            ItemTypes.ELEMENT_88 = AllayItemType
                            .builder(ItemElement88StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_88)
                            .build();
        }
        if (ItemTypes.ELEMENT_89 == null) {
            ItemTypes.ELEMENT_89 = AllayItemType
                            .builder(ItemElement89StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_89)
                            .build();
        }
        if (ItemTypes.ELEMENT_9 == null) {
            ItemTypes.ELEMENT_9 = AllayItemType
                            .builder(ItemElement9StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_9)
                            .build();
        }
        if (ItemTypes.ELEMENT_90 == null) {
            ItemTypes.ELEMENT_90 = AllayItemType
                            .builder(ItemElement90StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_90)
                            .build();
        }
        if (ItemTypes.ELEMENT_91 == null) {
            ItemTypes.ELEMENT_91 = AllayItemType
                            .builder(ItemElement91StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_91)
                            .build();
        }
        if (ItemTypes.ELEMENT_92 == null) {
            ItemTypes.ELEMENT_92 = AllayItemType
                            .builder(ItemElement92StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_92)
                            .build();
        }
        if (ItemTypes.ELEMENT_93 == null) {
            ItemTypes.ELEMENT_93 = AllayItemType
                            .builder(ItemElement93StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_93)
                            .build();
        }
        if (ItemTypes.ELEMENT_94 == null) {
            ItemTypes.ELEMENT_94 = AllayItemType
                            .builder(ItemElement94StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_94)
                            .build();
        }
        if (ItemTypes.ELEMENT_95 == null) {
            ItemTypes.ELEMENT_95 = AllayItemType
                            .builder(ItemElement95StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_95)
                            .build();
        }
        if (ItemTypes.ELEMENT_96 == null) {
            ItemTypes.ELEMENT_96 = AllayItemType
                            .builder(ItemElement96StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_96)
                            .build();
        }
        if (ItemTypes.ELEMENT_97 == null) {
            ItemTypes.ELEMENT_97 = AllayItemType
                            .builder(ItemElement97StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_97)
                            .build();
        }
        if (ItemTypes.ELEMENT_98 == null) {
            ItemTypes.ELEMENT_98 = AllayItemType
                            .builder(ItemElement98StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_98)
                            .build();
        }
        if (ItemTypes.ELEMENT_99 == null) {
            ItemTypes.ELEMENT_99 = AllayItemType
                            .builder(ItemElement99StackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_99)
                            .build();
        }
        if (ItemTypes.ELEMENT_CONSTRUCTOR == null) {
            ItemTypes.ELEMENT_CONSTRUCTOR = AllayItemType
                            .builder(ItemElementConstructorStackImpl.class)
                            .vanillaItem(ItemId.ELEMENT_CONSTRUCTOR)
                            .build();
        }
        if (ItemTypes.ELYTRA == null) {
            ItemTypes.ELYTRA = AllayItemType
                            .builder(ItemElytraStackImpl.class)
                            .vanillaItem(ItemId.ELYTRA)
                            .build();
        }
        if (ItemTypes.EMERALD == null) {
            ItemTypes.EMERALD = AllayItemType
                            .builder(ItemEmeraldStackImpl.class)
                            .vanillaItem(ItemId.EMERALD)
                            .build();
        }
        if (ItemTypes.EMERALD_BLOCK == null) {
            ItemTypes.EMERALD_BLOCK = AllayItemType
                            .builder(ItemEmeraldBlockStackImpl.class)
                            .vanillaItem(ItemId.EMERALD_BLOCK)
                            .build();
        }
        if (ItemTypes.EMERALD_ORE == null) {
            ItemTypes.EMERALD_ORE = AllayItemType
                            .builder(ItemEmeraldOreStackImpl.class)
                            .vanillaItem(ItemId.EMERALD_ORE)
                            .build();
        }
        if (ItemTypes.EMPTY_MAP == null) {
            ItemTypes.EMPTY_MAP = AllayItemType
                            .builder(ItemEmptyMapStackImpl.class)
                            .vanillaItem(ItemId.EMPTY_MAP)
                            .build();
        }
        if (ItemTypes.ENCHANTED_BOOK == null) {
            ItemTypes.ENCHANTED_BOOK = AllayItemType
                            .builder(ItemEnchantedBookStackImpl.class)
                            .vanillaItem(ItemId.ENCHANTED_BOOK)
                            .build();
        }
        if (ItemTypes.ENCHANTED_GOLDEN_APPLE == null) {
            ItemTypes.ENCHANTED_GOLDEN_APPLE = AllayItemType
                            .builder(ItemEnchantedGoldenAppleStackImpl.class)
                            .vanillaItem(ItemId.ENCHANTED_GOLDEN_APPLE)
                            .build();
        }
        if (ItemTypes.ENCHANTING_TABLE == null) {
            ItemTypes.ENCHANTING_TABLE = AllayItemType
                            .builder(ItemEnchantingTableStackImpl.class)
                            .vanillaItem(ItemId.ENCHANTING_TABLE)
                            .build();
        }
        if (ItemTypes.END_BRICK_STAIRS == null) {
            ItemTypes.END_BRICK_STAIRS = AllayItemType
                            .builder(ItemEndBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.END_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.END_BRICKS == null) {
            ItemTypes.END_BRICKS = AllayItemType
                            .builder(ItemEndBricksStackImpl.class)
                            .vanillaItem(ItemId.END_BRICKS)
                            .build();
        }
        if (ItemTypes.END_CRYSTAL == null) {
            ItemTypes.END_CRYSTAL = AllayItemType
                            .builder(ItemEndCrystalStackImpl.class)
                            .vanillaItem(ItemId.END_CRYSTAL)
                            .build();
        }
        if (ItemTypes.END_GATEWAY == null) {
            ItemTypes.END_GATEWAY = AllayItemType
                            .builder(ItemEndGatewayStackImpl.class)
                            .vanillaItem(ItemId.END_GATEWAY)
                            .build();
        }
        if (ItemTypes.END_PORTAL == null) {
            ItemTypes.END_PORTAL = AllayItemType
                            .builder(ItemEndPortalStackImpl.class)
                            .vanillaItem(ItemId.END_PORTAL)
                            .build();
        }
        if (ItemTypes.END_PORTAL_FRAME == null) {
            ItemTypes.END_PORTAL_FRAME = AllayItemType
                            .builder(ItemEndPortalFrameStackImpl.class)
                            .vanillaItem(ItemId.END_PORTAL_FRAME)
                            .build();
        }
        if (ItemTypes.END_ROD == null) {
            ItemTypes.END_ROD = AllayItemType
                            .builder(ItemEndRodStackImpl.class)
                            .vanillaItem(ItemId.END_ROD)
                            .build();
        }
        if (ItemTypes.END_STONE == null) {
            ItemTypes.END_STONE = AllayItemType
                            .builder(ItemEndStoneStackImpl.class)
                            .vanillaItem(ItemId.END_STONE)
                            .build();
        }
        if (ItemTypes.END_STONE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.END_STONE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemEndStoneBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.END_STONE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.END_STONE_BRICK_SLAB == null) {
            ItemTypes.END_STONE_BRICK_SLAB = AllayItemType
                            .builder(ItemEndStoneBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.END_STONE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.END_STONE_BRICK_WALL == null) {
            ItemTypes.END_STONE_BRICK_WALL = AllayItemType
                            .builder(ItemEndStoneBrickWallStackImpl.class)
                            .vanillaItem(ItemId.END_STONE_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.ENDER_CHEST == null) {
            ItemTypes.ENDER_CHEST = AllayItemType
                            .builder(ItemEnderChestStackImpl.class)
                            .vanillaItem(ItemId.ENDER_CHEST)
                            .build();
        }
        if (ItemTypes.ENDER_EYE == null) {
            ItemTypes.ENDER_EYE = AllayItemType
                            .builder(ItemEnderEyeStackImpl.class)
                            .vanillaItem(ItemId.ENDER_EYE)
                            .build();
        }
        if (ItemTypes.ENDER_PEARL == null) {
            ItemTypes.ENDER_PEARL = AllayItemType
                            .builder(ItemEnderPearlStackImpl.class)
                            .vanillaItem(ItemId.ENDER_PEARL)
                            .build();
        }
        if (ItemTypes.EXPERIENCE_BOTTLE == null) {
            ItemTypes.EXPERIENCE_BOTTLE = AllayItemType
                            .builder(ItemExperienceBottleStackImpl.class)
                            .vanillaItem(ItemId.EXPERIENCE_BOTTLE)
                            .build();
        }
        if (ItemTypes.EXPLORER_POTTERY_SHERD == null) {
            ItemTypes.EXPLORER_POTTERY_SHERD = AllayItemType
                            .builder(ItemExplorerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.EXPLORER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.EXPOSED_CHISELED_COPPER == null) {
            ItemTypes.EXPOSED_CHISELED_COPPER = AllayItemType
                            .builder(ItemExposedChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.EXPOSED_COPPER == null) {
            ItemTypes.EXPOSED_COPPER = AllayItemType
                            .builder(ItemExposedCopperStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_COPPER)
                            .build();
        }
        if (ItemTypes.EXPOSED_COPPER_BULB == null) {
            ItemTypes.EXPOSED_COPPER_BULB = AllayItemType
                            .builder(ItemExposedCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.EXPOSED_COPPER_DOOR == null) {
            ItemTypes.EXPOSED_COPPER_DOOR = AllayItemType
                            .builder(ItemExposedCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.EXPOSED_COPPER_GRATE == null) {
            ItemTypes.EXPOSED_COPPER_GRATE = AllayItemType
                            .builder(ItemExposedCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.EXPOSED_COPPER_TRAPDOOR == null) {
            ItemTypes.EXPOSED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemExposedCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.EXPOSED_CUT_COPPER == null) {
            ItemTypes.EXPOSED_CUT_COPPER = AllayItemType
                            .builder(ItemExposedCutCopperStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.EXPOSED_CUT_COPPER_SLAB == null) {
            ItemTypes.EXPOSED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemExposedCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.EXPOSED_CUT_COPPER_STAIRS == null) {
            ItemTypes.EXPOSED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemExposedCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemExposedDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.EYE_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.EYE_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemEyeArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.EYE_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.FARMLAND == null) {
            ItemTypes.FARMLAND = AllayItemType
                            .builder(ItemFarmlandStackImpl.class)
                            .vanillaItem(ItemId.FARMLAND)
                            .build();
        }
        if (ItemTypes.FEATHER == null) {
            ItemTypes.FEATHER = AllayItemType
                            .builder(ItemFeatherStackImpl.class)
                            .vanillaItem(ItemId.FEATHER)
                            .build();
        }
        if (ItemTypes.FENCE == null) {
            ItemTypes.FENCE = AllayItemType
                            .builder(ItemFenceStackImpl.class)
                            .vanillaItem(ItemId.FENCE)
                            .build();
        }
        if (ItemTypes.FENCE_GATE == null) {
            ItemTypes.FENCE_GATE = AllayItemType
                            .builder(ItemFenceGateStackImpl.class)
                            .vanillaItem(ItemId.FENCE_GATE)
                            .build();
        }
        if (ItemTypes.FERMENTED_SPIDER_EYE == null) {
            ItemTypes.FERMENTED_SPIDER_EYE = AllayItemType
                            .builder(ItemFermentedSpiderEyeStackImpl.class)
                            .vanillaItem(ItemId.FERMENTED_SPIDER_EYE)
                            .build();
        }
        if (ItemTypes.FERN == null) {
            ItemTypes.FERN = AllayItemType
                            .builder(ItemFernStackImpl.class)
                            .vanillaItem(ItemId.FERN)
                            .build();
        }
        if (ItemTypes.FIELD_MASONED_BANNER_PATTERN == null) {
            ItemTypes.FIELD_MASONED_BANNER_PATTERN = AllayItemType
                            .builder(ItemFieldMasonedBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.FIELD_MASONED_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.FILLED_MAP == null) {
            ItemTypes.FILLED_MAP = AllayItemType
                            .builder(ItemFilledMapStackImpl.class)
                            .vanillaItem(ItemId.FILLED_MAP)
                            .build();
        }
        if (ItemTypes.FIRE == null) {
            ItemTypes.FIRE = AllayItemType
                            .builder(ItemFireStackImpl.class)
                            .vanillaItem(ItemId.FIRE)
                            .build();
        }
        if (ItemTypes.FIRE_CHARGE == null) {
            ItemTypes.FIRE_CHARGE = AllayItemType
                            .builder(ItemFireChargeStackImpl.class)
                            .vanillaItem(ItemId.FIRE_CHARGE)
                            .build();
        }
        if (ItemTypes.FIRE_CORAL == null) {
            ItemTypes.FIRE_CORAL = AllayItemType
                            .builder(ItemFireCoralStackImpl.class)
                            .vanillaItem(ItemId.FIRE_CORAL)
                            .build();
        }
        if (ItemTypes.FIRE_CORAL_BLOCK == null) {
            ItemTypes.FIRE_CORAL_BLOCK = AllayItemType
                            .builder(ItemFireCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.FIRE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.FIRE_CORAL_FAN == null) {
            ItemTypes.FIRE_CORAL_FAN = AllayItemType
                            .builder(ItemFireCoralFanStackImpl.class)
                            .vanillaItem(ItemId.FIRE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.FIRE_CORAL_WALL_FAN == null) {
            ItemTypes.FIRE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemFireCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.FIRE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.FIREWORK_ROCKET == null) {
            ItemTypes.FIREWORK_ROCKET = AllayItemType
                            .builder(ItemFireworkRocketStackImpl.class)
                            .vanillaItem(ItemId.FIREWORK_ROCKET)
                            .build();
        }
        if (ItemTypes.FIREWORK_STAR == null) {
            ItemTypes.FIREWORK_STAR = AllayItemType
                            .builder(ItemFireworkStarStackImpl.class)
                            .vanillaItem(ItemId.FIREWORK_STAR)
                            .build();
        }
        if (ItemTypes.FISHING_ROD == null) {
            ItemTypes.FISHING_ROD = AllayItemType
                            .builder(ItemFishingRodStackImpl.class)
                            .vanillaItem(ItemId.FISHING_ROD)
                            .build();
        }
        if (ItemTypes.FLETCHING_TABLE == null) {
            ItemTypes.FLETCHING_TABLE = AllayItemType
                            .builder(ItemFletchingTableStackImpl.class)
                            .vanillaItem(ItemId.FLETCHING_TABLE)
                            .build();
        }
        if (ItemTypes.FLINT == null) {
            ItemTypes.FLINT = AllayItemType
                            .builder(ItemFlintStackImpl.class)
                            .vanillaItem(ItemId.FLINT)
                            .build();
        }
        if (ItemTypes.FLINT_AND_STEEL == null) {
            ItemTypes.FLINT_AND_STEEL = AllayItemType
                            .builder(ItemFlintAndSteelStackImpl.class)
                            .vanillaItem(ItemId.FLINT_AND_STEEL)
                            .build();
        }
        if (ItemTypes.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemFlowArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.FLOW_BANNER_PATTERN == null) {
            ItemTypes.FLOW_BANNER_PATTERN = AllayItemType
                            .builder(ItemFlowBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.FLOW_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.FLOW_POTTERY_SHERD == null) {
            ItemTypes.FLOW_POTTERY_SHERD = AllayItemType
                            .builder(ItemFlowPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.FLOW_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.FLOWER_BANNER_PATTERN == null) {
            ItemTypes.FLOWER_BANNER_PATTERN = AllayItemType
                            .builder(ItemFlowerBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.FLOWER_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.FLOWER_POT == null) {
            ItemTypes.FLOWER_POT = AllayItemType
                            .builder(ItemFlowerPotStackImpl.class)
                            .vanillaItem(ItemId.FLOWER_POT)
                            .build();
        }
        if (ItemTypes.FLOWERING_AZALEA == null) {
            ItemTypes.FLOWERING_AZALEA = AllayItemType
                            .builder(ItemFloweringAzaleaStackImpl.class)
                            .vanillaItem(ItemId.FLOWERING_AZALEA)
                            .build();
        }
        if (ItemTypes.FLOWING_LAVA == null) {
            ItemTypes.FLOWING_LAVA = AllayItemType
                            .builder(ItemFlowingLavaStackImpl.class)
                            .vanillaItem(ItemId.FLOWING_LAVA)
                            .build();
        }
        if (ItemTypes.FLOWING_WATER == null) {
            ItemTypes.FLOWING_WATER = AllayItemType
                            .builder(ItemFlowingWaterStackImpl.class)
                            .vanillaItem(ItemId.FLOWING_WATER)
                            .build();
        }
        if (ItemTypes.FRAME == null) {
            ItemTypes.FRAME = AllayItemType
                            .builder(ItemFrameStackImpl.class)
                            .vanillaItem(ItemId.FRAME)
                            .build();
        }
        if (ItemTypes.FRIEND_POTTERY_SHERD == null) {
            ItemTypes.FRIEND_POTTERY_SHERD = AllayItemType
                            .builder(ItemFriendPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.FRIEND_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.FROG_SPAWN == null) {
            ItemTypes.FROG_SPAWN = AllayItemType
                            .builder(ItemFrogSpawnStackImpl.class)
                            .vanillaItem(ItemId.FROG_SPAWN)
                            .build();
        }
        if (ItemTypes.FROSTED_ICE == null) {
            ItemTypes.FROSTED_ICE = AllayItemType
                            .builder(ItemFrostedIceStackImpl.class)
                            .vanillaItem(ItemId.FROSTED_ICE)
                            .build();
        }
        if (ItemTypes.FURNACE == null) {
            ItemTypes.FURNACE = AllayItemType
                            .builder(ItemFurnaceStackImpl.class)
                            .vanillaItem(ItemId.FURNACE)
                            .build();
        }
        if (ItemTypes.GHAST_TEAR == null) {
            ItemTypes.GHAST_TEAR = AllayItemType
                            .builder(ItemGhastTearStackImpl.class)
                            .vanillaItem(ItemId.GHAST_TEAR)
                            .build();
        }
        if (ItemTypes.GILDED_BLACKSTONE == null) {
            ItemTypes.GILDED_BLACKSTONE = AllayItemType
                            .builder(ItemGildedBlackstoneStackImpl.class)
                            .vanillaItem(ItemId.GILDED_BLACKSTONE)
                            .build();
        }
        if (ItemTypes.GLASS == null) {
            ItemTypes.GLASS = AllayItemType
                            .builder(ItemGlassStackImpl.class)
                            .vanillaItem(ItemId.GLASS)
                            .build();
        }
        if (ItemTypes.GLASS_BOTTLE == null) {
            ItemTypes.GLASS_BOTTLE = AllayItemType
                            .builder(ItemGlassBottleStackImpl.class)
                            .vanillaItem(ItemId.GLASS_BOTTLE)
                            .build();
        }
        if (ItemTypes.GLASS_PANE == null) {
            ItemTypes.GLASS_PANE = AllayItemType
                            .builder(ItemGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.GLASS_PANE)
                            .build();
        }
        if (ItemTypes.GLISTERING_MELON_SLICE == null) {
            ItemTypes.GLISTERING_MELON_SLICE = AllayItemType
                            .builder(ItemGlisteringMelonSliceStackImpl.class)
                            .vanillaItem(ItemId.GLISTERING_MELON_SLICE)
                            .build();
        }
        if (ItemTypes.GLOBE_BANNER_PATTERN == null) {
            ItemTypes.GLOBE_BANNER_PATTERN = AllayItemType
                            .builder(ItemGlobeBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.GLOBE_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.GLOW_BERRIES == null) {
            ItemTypes.GLOW_BERRIES = AllayItemType
                            .builder(ItemGlowBerriesStackImpl.class)
                            .vanillaItem(ItemId.GLOW_BERRIES)
                            .build();
        }
        if (ItemTypes.GLOW_FRAME == null) {
            ItemTypes.GLOW_FRAME = AllayItemType
                            .builder(ItemGlowFrameStackImpl.class)
                            .vanillaItem(ItemId.GLOW_FRAME)
                            .build();
        }
        if (ItemTypes.GLOW_INK_SAC == null) {
            ItemTypes.GLOW_INK_SAC = AllayItemType
                            .builder(ItemGlowInkSacStackImpl.class)
                            .vanillaItem(ItemId.GLOW_INK_SAC)
                            .build();
        }
        if (ItemTypes.GLOW_LICHEN == null) {
            ItemTypes.GLOW_LICHEN = AllayItemType
                            .builder(ItemGlowLichenStackImpl.class)
                            .vanillaItem(ItemId.GLOW_LICHEN)
                            .build();
        }
        if (ItemTypes.GLOW_STICK == null) {
            ItemTypes.GLOW_STICK = AllayItemType
                            .builder(ItemGlowStickStackImpl.class)
                            .vanillaItem(ItemId.GLOW_STICK)
                            .build();
        }
        if (ItemTypes.GLOWINGOBSIDIAN == null) {
            ItemTypes.GLOWINGOBSIDIAN = AllayItemType
                            .builder(ItemGlowingobsidianStackImpl.class)
                            .vanillaItem(ItemId.GLOWINGOBSIDIAN)
                            .build();
        }
        if (ItemTypes.GLOWSTONE == null) {
            ItemTypes.GLOWSTONE = AllayItemType
                            .builder(ItemGlowstoneStackImpl.class)
                            .vanillaItem(ItemId.GLOWSTONE)
                            .build();
        }
        if (ItemTypes.GLOWSTONE_DUST == null) {
            ItemTypes.GLOWSTONE_DUST = AllayItemType
                            .builder(ItemGlowstoneDustStackImpl.class)
                            .vanillaItem(ItemId.GLOWSTONE_DUST)
                            .build();
        }
        if (ItemTypes.GOAT_HORN == null) {
            ItemTypes.GOAT_HORN = AllayItemType
                            .builder(ItemGoatHornStackImpl.class)
                            .vanillaItem(ItemId.GOAT_HORN)
                            .build();
        }
        if (ItemTypes.GOLD_BLOCK == null) {
            ItemTypes.GOLD_BLOCK = AllayItemType
                            .builder(ItemGoldBlockStackImpl.class)
                            .vanillaItem(ItemId.GOLD_BLOCK)
                            .build();
        }
        if (ItemTypes.GOLD_INGOT == null) {
            ItemTypes.GOLD_INGOT = AllayItemType
                            .builder(ItemGoldIngotStackImpl.class)
                            .vanillaItem(ItemId.GOLD_INGOT)
                            .build();
        }
        if (ItemTypes.GOLD_NUGGET == null) {
            ItemTypes.GOLD_NUGGET = AllayItemType
                            .builder(ItemGoldNuggetStackImpl.class)
                            .vanillaItem(ItemId.GOLD_NUGGET)
                            .build();
        }
        if (ItemTypes.GOLD_ORE == null) {
            ItemTypes.GOLD_ORE = AllayItemType
                            .builder(ItemGoldOreStackImpl.class)
                            .vanillaItem(ItemId.GOLD_ORE)
                            .build();
        }
        if (ItemTypes.GOLDEN_APPLE == null) {
            ItemTypes.GOLDEN_APPLE = AllayItemType
                            .builder(ItemGoldenAppleStackImpl.class)
                            .vanillaItem(ItemId.GOLDEN_APPLE)
                            .build();
        }
        if (ItemTypes.GOLDEN_CARROT == null) {
            ItemTypes.GOLDEN_CARROT = AllayItemType
                            .builder(ItemGoldenCarrotStackImpl.class)
                            .vanillaItem(ItemId.GOLDEN_CARROT)
                            .build();
        }
        if (ItemTypes.GOLDEN_HORSE_ARMOR == null) {
            ItemTypes.GOLDEN_HORSE_ARMOR = AllayItemType
                            .builder(ItemGoldenHorseArmorStackImpl.class)
                            .vanillaItem(ItemId.GOLDEN_HORSE_ARMOR)
                            .build();
        }
        if (ItemTypes.GOLDEN_RAIL == null) {
            ItemTypes.GOLDEN_RAIL = AllayItemType
                            .builder(ItemGoldenRailStackImpl.class)
                            .vanillaItem(ItemId.GOLDEN_RAIL)
                            .build();
        }
        if (ItemTypes.GRANITE == null) {
            ItemTypes.GRANITE = AllayItemType
                            .builder(ItemGraniteStackImpl.class)
                            .vanillaItem(ItemId.GRANITE)
                            .build();
        }
        if (ItemTypes.GRANITE_DOUBLE_SLAB == null) {
            ItemTypes.GRANITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemGraniteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.GRANITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.GRANITE_SLAB == null) {
            ItemTypes.GRANITE_SLAB = AllayItemType
                            .builder(ItemGraniteSlabStackImpl.class)
                            .vanillaItem(ItemId.GRANITE_SLAB)
                            .build();
        }
        if (ItemTypes.GRANITE_STAIRS == null) {
            ItemTypes.GRANITE_STAIRS = AllayItemType
                            .builder(ItemGraniteStairsStackImpl.class)
                            .vanillaItem(ItemId.GRANITE_STAIRS)
                            .build();
        }
        if (ItemTypes.GRANITE_WALL == null) {
            ItemTypes.GRANITE_WALL = AllayItemType
                            .builder(ItemGraniteWallStackImpl.class)
                            .vanillaItem(ItemId.GRANITE_WALL)
                            .build();
        }
        if (ItemTypes.GRASS_BLOCK == null) {
            ItemTypes.GRASS_BLOCK = AllayItemType
                            .builder(ItemGrassBlockStackImpl.class)
                            .vanillaItem(ItemId.GRASS_BLOCK)
                            .build();
        }
        if (ItemTypes.GRASS_PATH == null) {
            ItemTypes.GRASS_PATH = AllayItemType
                            .builder(ItemGrassPathStackImpl.class)
                            .vanillaItem(ItemId.GRASS_PATH)
                            .build();
        }
        if (ItemTypes.GRAVEL == null) {
            ItemTypes.GRAVEL = AllayItemType
                            .builder(ItemGravelStackImpl.class)
                            .vanillaItem(ItemId.GRAVEL)
                            .build();
        }
        if (ItemTypes.GRAY_BUNDLE == null) {
            ItemTypes.GRAY_BUNDLE = AllayItemType
                            .builder(ItemGrayBundleStackImpl.class)
                            .vanillaItem(ItemId.GRAY_BUNDLE)
                            .build();
        }
        if (ItemTypes.GRAY_CANDLE == null) {
            ItemTypes.GRAY_CANDLE = AllayItemType
                            .builder(ItemGrayCandleStackImpl.class)
                            .vanillaItem(ItemId.GRAY_CANDLE)
                            .build();
        }
        if (ItemTypes.GRAY_CANDLE_CAKE == null) {
            ItemTypes.GRAY_CANDLE_CAKE = AllayItemType
                            .builder(ItemGrayCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.GRAY_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.GRAY_CARPET == null) {
            ItemTypes.GRAY_CARPET = AllayItemType
                            .builder(ItemGrayCarpetStackImpl.class)
                            .vanillaItem(ItemId.GRAY_CARPET)
                            .build();
        }
        if (ItemTypes.GRAY_CONCRETE == null) {
            ItemTypes.GRAY_CONCRETE = AllayItemType
                            .builder(ItemGrayConcreteStackImpl.class)
                            .vanillaItem(ItemId.GRAY_CONCRETE)
                            .build();
        }
        if (ItemTypes.GRAY_CONCRETE_POWDER == null) {
            ItemTypes.GRAY_CONCRETE_POWDER = AllayItemType
                            .builder(ItemGrayConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.GRAY_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.GRAY_DYE == null) {
            ItemTypes.GRAY_DYE = AllayItemType
                            .builder(ItemGrayDyeStackImpl.class)
                            .vanillaItem(ItemId.GRAY_DYE)
                            .build();
        }
        if (ItemTypes.GRAY_GLAZED_TERRACOTTA == null) {
            ItemTypes.GRAY_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemGrayGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.GRAY_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.GRAY_STAINED_GLASS == null) {
            ItemTypes.GRAY_STAINED_GLASS = AllayItemType
                            .builder(ItemGrayStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.GRAY_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.GRAY_STAINED_GLASS_PANE == null) {
            ItemTypes.GRAY_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemGrayStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.GRAY_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.GRAY_TERRACOTTA == null) {
            ItemTypes.GRAY_TERRACOTTA = AllayItemType
                            .builder(ItemGrayTerracottaStackImpl.class)
                            .vanillaItem(ItemId.GRAY_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.GRAY_WOOL == null) {
            ItemTypes.GRAY_WOOL = AllayItemType
                            .builder(ItemGrayWoolStackImpl.class)
                            .vanillaItem(ItemId.GRAY_WOOL)
                            .build();
        }
        if (ItemTypes.GREEN_BUNDLE == null) {
            ItemTypes.GREEN_BUNDLE = AllayItemType
                            .builder(ItemGreenBundleStackImpl.class)
                            .vanillaItem(ItemId.GREEN_BUNDLE)
                            .build();
        }
        if (ItemTypes.GREEN_CANDLE == null) {
            ItemTypes.GREEN_CANDLE = AllayItemType
                            .builder(ItemGreenCandleStackImpl.class)
                            .vanillaItem(ItemId.GREEN_CANDLE)
                            .build();
        }
        if (ItemTypes.GREEN_CANDLE_CAKE == null) {
            ItemTypes.GREEN_CANDLE_CAKE = AllayItemType
                            .builder(ItemGreenCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.GREEN_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.GREEN_CARPET == null) {
            ItemTypes.GREEN_CARPET = AllayItemType
                            .builder(ItemGreenCarpetStackImpl.class)
                            .vanillaItem(ItemId.GREEN_CARPET)
                            .build();
        }
        if (ItemTypes.GREEN_CONCRETE == null) {
            ItemTypes.GREEN_CONCRETE = AllayItemType
                            .builder(ItemGreenConcreteStackImpl.class)
                            .vanillaItem(ItemId.GREEN_CONCRETE)
                            .build();
        }
        if (ItemTypes.GREEN_CONCRETE_POWDER == null) {
            ItemTypes.GREEN_CONCRETE_POWDER = AllayItemType
                            .builder(ItemGreenConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.GREEN_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.GREEN_DYE == null) {
            ItemTypes.GREEN_DYE = AllayItemType
                            .builder(ItemGreenDyeStackImpl.class)
                            .vanillaItem(ItemId.GREEN_DYE)
                            .build();
        }
        if (ItemTypes.GREEN_GLAZED_TERRACOTTA == null) {
            ItemTypes.GREEN_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemGreenGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.GREEN_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.GREEN_STAINED_GLASS == null) {
            ItemTypes.GREEN_STAINED_GLASS = AllayItemType
                            .builder(ItemGreenStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.GREEN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.GREEN_STAINED_GLASS_PANE == null) {
            ItemTypes.GREEN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemGreenStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.GREEN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.GREEN_TERRACOTTA == null) {
            ItemTypes.GREEN_TERRACOTTA = AllayItemType
                            .builder(ItemGreenTerracottaStackImpl.class)
                            .vanillaItem(ItemId.GREEN_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.GREEN_WOOL == null) {
            ItemTypes.GREEN_WOOL = AllayItemType
                            .builder(ItemGreenWoolStackImpl.class)
                            .vanillaItem(ItemId.GREEN_WOOL)
                            .build();
        }
        if (ItemTypes.GRINDSTONE == null) {
            ItemTypes.GRINDSTONE = AllayItemType
                            .builder(ItemGrindstoneStackImpl.class)
                            .vanillaItem(ItemId.GRINDSTONE)
                            .build();
        }
        if (ItemTypes.GUNPOWDER == null) {
            ItemTypes.GUNPOWDER = AllayItemType
                            .builder(ItemGunpowderStackImpl.class)
                            .vanillaItem(ItemId.GUNPOWDER)
                            .build();
        }
        if (ItemTypes.GUSTER_BANNER_PATTERN == null) {
            ItemTypes.GUSTER_BANNER_PATTERN = AllayItemType
                            .builder(ItemGusterBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.GUSTER_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.GUSTER_POTTERY_SHERD == null) {
            ItemTypes.GUSTER_POTTERY_SHERD = AllayItemType
                            .builder(ItemGusterPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.GUSTER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.HANGING_ROOTS == null) {
            ItemTypes.HANGING_ROOTS = AllayItemType
                            .builder(ItemHangingRootsStackImpl.class)
                            .vanillaItem(ItemId.HANGING_ROOTS)
                            .build();
        }
        if (ItemTypes.HARD_BLACK_STAINED_GLASS == null) {
            ItemTypes.HARD_BLACK_STAINED_GLASS = AllayItemType
                            .builder(ItemHardBlackStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_BLACK_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_BLACK_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_BLACK_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardBlackStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_BLACK_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_BLUE_STAINED_GLASS == null) {
            ItemTypes.HARD_BLUE_STAINED_GLASS = AllayItemType
                            .builder(ItemHardBlueStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_BLUE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_BLUE_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_BLUE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardBlueStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_BLUE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_BROWN_STAINED_GLASS == null) {
            ItemTypes.HARD_BROWN_STAINED_GLASS = AllayItemType
                            .builder(ItemHardBrownStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_BROWN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_BROWN_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_BROWN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardBrownStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_BROWN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_CYAN_STAINED_GLASS == null) {
            ItemTypes.HARD_CYAN_STAINED_GLASS = AllayItemType
                            .builder(ItemHardCyanStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_CYAN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_CYAN_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_CYAN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardCyanStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_CYAN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_GLASS == null) {
            ItemTypes.HARD_GLASS = AllayItemType
                            .builder(ItemHardGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_GLASS_PANE == null) {
            ItemTypes.HARD_GLASS_PANE = AllayItemType
                            .builder(ItemHardGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_GRAY_STAINED_GLASS == null) {
            ItemTypes.HARD_GRAY_STAINED_GLASS = AllayItemType
                            .builder(ItemHardGrayStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_GRAY_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_GRAY_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_GRAY_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardGrayStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_GRAY_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_GREEN_STAINED_GLASS == null) {
            ItemTypes.HARD_GREEN_STAINED_GLASS = AllayItemType
                            .builder(ItemHardGreenStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_GREEN_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_GREEN_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_GREEN_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardGreenStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_GREEN_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS == null) {
            ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS = AllayItemType
                            .builder(ItemHardLightBlueStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIGHT_BLUE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardLightBlueStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS == null) {
            ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS = AllayItemType
                            .builder(ItemHardLightGrayStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIGHT_GRAY_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardLightGrayStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_LIME_STAINED_GLASS == null) {
            ItemTypes.HARD_LIME_STAINED_GLASS = AllayItemType
                            .builder(ItemHardLimeStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIME_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_LIME_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_LIME_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardLimeStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_LIME_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_MAGENTA_STAINED_GLASS == null) {
            ItemTypes.HARD_MAGENTA_STAINED_GLASS = AllayItemType
                            .builder(ItemHardMagentaStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_MAGENTA_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_MAGENTA_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_MAGENTA_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardMagentaStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_MAGENTA_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_ORANGE_STAINED_GLASS == null) {
            ItemTypes.HARD_ORANGE_STAINED_GLASS = AllayItemType
                            .builder(ItemHardOrangeStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_ORANGE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_ORANGE_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_ORANGE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardOrangeStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_ORANGE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_PINK_STAINED_GLASS == null) {
            ItemTypes.HARD_PINK_STAINED_GLASS = AllayItemType
                            .builder(ItemHardPinkStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_PINK_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_PINK_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_PINK_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardPinkStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_PINK_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_PURPLE_STAINED_GLASS == null) {
            ItemTypes.HARD_PURPLE_STAINED_GLASS = AllayItemType
                            .builder(ItemHardPurpleStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_PURPLE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_PURPLE_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_PURPLE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardPurpleStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_PURPLE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_RED_STAINED_GLASS == null) {
            ItemTypes.HARD_RED_STAINED_GLASS = AllayItemType
                            .builder(ItemHardRedStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_RED_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_RED_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_RED_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardRedStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_RED_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_STAINED_GLASS == null) {
            ItemTypes.HARD_STAINED_GLASS = AllayItemType
                            .builder(ItemHardStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_WHITE_STAINED_GLASS == null) {
            ItemTypes.HARD_WHITE_STAINED_GLASS = AllayItemType
                            .builder(ItemHardWhiteStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_WHITE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_WHITE_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_WHITE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardWhiteStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_WHITE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARD_YELLOW_STAINED_GLASS == null) {
            ItemTypes.HARD_YELLOW_STAINED_GLASS = AllayItemType
                            .builder(ItemHardYellowStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.HARD_YELLOW_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.HARD_YELLOW_STAINED_GLASS_PANE == null) {
            ItemTypes.HARD_YELLOW_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemHardYellowStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.HARD_YELLOW_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.HARDENED_CLAY == null) {
            ItemTypes.HARDENED_CLAY = AllayItemType
                            .builder(ItemHardenedClayStackImpl.class)
                            .vanillaItem(ItemId.HARDENED_CLAY)
                            .build();
        }
        if (ItemTypes.HAY_BLOCK == null) {
            ItemTypes.HAY_BLOCK = AllayItemType
                            .builder(ItemHayBlockStackImpl.class)
                            .vanillaItem(ItemId.HAY_BLOCK)
                            .build();
        }
        if (ItemTypes.HEART_OF_THE_SEA == null) {
            ItemTypes.HEART_OF_THE_SEA = AllayItemType
                            .builder(ItemHeartOfTheSeaStackImpl.class)
                            .vanillaItem(ItemId.HEART_OF_THE_SEA)
                            .build();
        }
        if (ItemTypes.HEART_POTTERY_SHERD == null) {
            ItemTypes.HEART_POTTERY_SHERD = AllayItemType
                            .builder(ItemHeartPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.HEART_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.HEARTBREAK_POTTERY_SHERD == null) {
            ItemTypes.HEARTBREAK_POTTERY_SHERD = AllayItemType
                            .builder(ItemHeartbreakPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.HEARTBREAK_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.HEAVY_CORE == null) {
            ItemTypes.HEAVY_CORE = AllayItemType
                            .builder(ItemHeavyCoreStackImpl.class)
                            .vanillaItem(ItemId.HEAVY_CORE)
                            .build();
        }
        if (ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE == null) {
            ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = AllayItemType
                            .builder(ItemHeavyWeightedPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.HONEY_BLOCK == null) {
            ItemTypes.HONEY_BLOCK = AllayItemType
                            .builder(ItemHoneyBlockStackImpl.class)
                            .vanillaItem(ItemId.HONEY_BLOCK)
                            .build();
        }
        if (ItemTypes.HONEY_BOTTLE == null) {
            ItemTypes.HONEY_BOTTLE = AllayItemType
                            .builder(ItemHoneyBottleStackImpl.class)
                            .vanillaItem(ItemId.HONEY_BOTTLE)
                            .build();
        }
        if (ItemTypes.HONEYCOMB == null) {
            ItemTypes.HONEYCOMB = AllayItemType
                            .builder(ItemHoneycombStackImpl.class)
                            .vanillaItem(ItemId.HONEYCOMB)
                            .build();
        }
        if (ItemTypes.HONEYCOMB_BLOCK == null) {
            ItemTypes.HONEYCOMB_BLOCK = AllayItemType
                            .builder(ItemHoneycombBlockStackImpl.class)
                            .vanillaItem(ItemId.HONEYCOMB_BLOCK)
                            .build();
        }
        if (ItemTypes.HOPPER == null) {
            ItemTypes.HOPPER = AllayItemType
                            .builder(ItemHopperStackImpl.class)
                            .vanillaItem(ItemId.HOPPER)
                            .build();
        }
        if (ItemTypes.HOPPER_MINECART == null) {
            ItemTypes.HOPPER_MINECART = AllayItemType
                            .builder(ItemHopperMinecartStackImpl.class)
                            .vanillaItem(ItemId.HOPPER_MINECART)
                            .build();
        }
        if (ItemTypes.HORN_CORAL == null) {
            ItemTypes.HORN_CORAL = AllayItemType
                            .builder(ItemHornCoralStackImpl.class)
                            .vanillaItem(ItemId.HORN_CORAL)
                            .build();
        }
        if (ItemTypes.HORN_CORAL_BLOCK == null) {
            ItemTypes.HORN_CORAL_BLOCK = AllayItemType
                            .builder(ItemHornCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.HORN_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.HORN_CORAL_FAN == null) {
            ItemTypes.HORN_CORAL_FAN = AllayItemType
                            .builder(ItemHornCoralFanStackImpl.class)
                            .vanillaItem(ItemId.HORN_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.HORN_CORAL_WALL_FAN == null) {
            ItemTypes.HORN_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemHornCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.HORN_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.HOST_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.HOST_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemHostArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.HOWL_POTTERY_SHERD == null) {
            ItemTypes.HOWL_POTTERY_SHERD = AllayItemType
                            .builder(ItemHowlPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.HOWL_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.ICE == null) {
            ItemTypes.ICE = AllayItemType
                            .builder(ItemIceStackImpl.class)
                            .vanillaItem(ItemId.ICE)
                            .build();
        }
        if (ItemTypes.ICE_BOMB == null) {
            ItemTypes.ICE_BOMB = AllayItemType
                            .builder(ItemIceBombStackImpl.class)
                            .vanillaItem(ItemId.ICE_BOMB)
                            .build();
        }
        if (ItemTypes.INFESTED_CHISELED_STONE_BRICKS == null) {
            ItemTypes.INFESTED_CHISELED_STONE_BRICKS = AllayItemType
                            .builder(ItemInfestedChiseledStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_CHISELED_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.INFESTED_COBBLESTONE == null) {
            ItemTypes.INFESTED_COBBLESTONE = AllayItemType
                            .builder(ItemInfestedCobblestoneStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_COBBLESTONE)
                            .build();
        }
        if (ItemTypes.INFESTED_CRACKED_STONE_BRICKS == null) {
            ItemTypes.INFESTED_CRACKED_STONE_BRICKS = AllayItemType
                            .builder(ItemInfestedCrackedStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_CRACKED_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.INFESTED_DEEPSLATE == null) {
            ItemTypes.INFESTED_DEEPSLATE = AllayItemType
                            .builder(ItemInfestedDeepslateStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_DEEPSLATE)
                            .build();
        }
        if (ItemTypes.INFESTED_MOSSY_STONE_BRICKS == null) {
            ItemTypes.INFESTED_MOSSY_STONE_BRICKS = AllayItemType
                            .builder(ItemInfestedMossyStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_MOSSY_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.INFESTED_STONE == null) {
            ItemTypes.INFESTED_STONE = AllayItemType
                            .builder(ItemInfestedStoneStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_STONE)
                            .build();
        }
        if (ItemTypes.INFESTED_STONE_BRICKS == null) {
            ItemTypes.INFESTED_STONE_BRICKS = AllayItemType
                            .builder(ItemInfestedStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.INFESTED_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.INFO_UPDATE == null) {
            ItemTypes.INFO_UPDATE = AllayItemType
                            .builder(ItemInfoUpdateStackImpl.class)
                            .vanillaItem(ItemId.INFO_UPDATE)
                            .build();
        }
        if (ItemTypes.INFO_UPDATE2 == null) {
            ItemTypes.INFO_UPDATE2 = AllayItemType
                            .builder(ItemInfoUpdate2StackImpl.class)
                            .vanillaItem(ItemId.INFO_UPDATE2)
                            .build();
        }
        if (ItemTypes.INK_SAC == null) {
            ItemTypes.INK_SAC = AllayItemType
                            .builder(ItemInkSacStackImpl.class)
                            .vanillaItem(ItemId.INK_SAC)
                            .build();
        }
        if (ItemTypes.INVISIBLE_BEDROCK == null) {
            ItemTypes.INVISIBLE_BEDROCK = AllayItemType
                            .builder(ItemInvisibleBedrockStackImpl.class)
                            .vanillaItem(ItemId.INVISIBLE_BEDROCK)
                            .build();
        }
        if (ItemTypes.IRON_BARS == null) {
            ItemTypes.IRON_BARS = AllayItemType
                            .builder(ItemIronBarsStackImpl.class)
                            .vanillaItem(ItemId.IRON_BARS)
                            .build();
        }
        if (ItemTypes.IRON_BLOCK == null) {
            ItemTypes.IRON_BLOCK = AllayItemType
                            .builder(ItemIronBlockStackImpl.class)
                            .vanillaItem(ItemId.IRON_BLOCK)
                            .build();
        }
        if (ItemTypes.IRON_DOOR == null) {
            ItemTypes.IRON_DOOR = AllayItemType
                            .builder(ItemIronDoorStackImpl.class)
                            .vanillaItem(ItemId.IRON_DOOR)
                            .build();
        }
        if (ItemTypes.IRON_HORSE_ARMOR == null) {
            ItemTypes.IRON_HORSE_ARMOR = AllayItemType
                            .builder(ItemIronHorseArmorStackImpl.class)
                            .vanillaItem(ItemId.IRON_HORSE_ARMOR)
                            .build();
        }
        if (ItemTypes.IRON_INGOT == null) {
            ItemTypes.IRON_INGOT = AllayItemType
                            .builder(ItemIronIngotStackImpl.class)
                            .vanillaItem(ItemId.IRON_INGOT)
                            .build();
        }
        if (ItemTypes.IRON_NUGGET == null) {
            ItemTypes.IRON_NUGGET = AllayItemType
                            .builder(ItemIronNuggetStackImpl.class)
                            .vanillaItem(ItemId.IRON_NUGGET)
                            .build();
        }
        if (ItemTypes.IRON_ORE == null) {
            ItemTypes.IRON_ORE = AllayItemType
                            .builder(ItemIronOreStackImpl.class)
                            .vanillaItem(ItemId.IRON_ORE)
                            .build();
        }
        if (ItemTypes.IRON_TRAPDOOR == null) {
            ItemTypes.IRON_TRAPDOOR = AllayItemType
                            .builder(ItemIronTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.IRON_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.ITEM_ACACIA_DOOR == null) {
            ItemTypes.ITEM_ACACIA_DOOR = AllayItemType
                            .builder(ItemItemAcaciaDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_ACACIA_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_BED == null) {
            ItemTypes.ITEM_BED = AllayItemType
                            .builder(ItemItemBedStackImpl.class)
                            .vanillaItem(ItemId.ITEM_BED)
                            .build();
        }
        if (ItemTypes.ITEM_BEETROOT == null) {
            ItemTypes.ITEM_BEETROOT = AllayItemType
                            .builder(ItemItemBeetrootStackImpl.class)
                            .vanillaItem(ItemId.ITEM_BEETROOT)
                            .build();
        }
        if (ItemTypes.ITEM_BIRCH_DOOR == null) {
            ItemTypes.ITEM_BIRCH_DOOR = AllayItemType
                            .builder(ItemItemBirchDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_BIRCH_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_BREWING_STAND == null) {
            ItemTypes.ITEM_BREWING_STAND = AllayItemType
                            .builder(ItemItemBrewingStandStackImpl.class)
                            .vanillaItem(ItemId.ITEM_BREWING_STAND)
                            .build();
        }
        if (ItemTypes.ITEM_CAKE == null) {
            ItemTypes.ITEM_CAKE = AllayItemType
                            .builder(ItemItemCakeStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CAKE)
                            .build();
        }
        if (ItemTypes.ITEM_CAMERA == null) {
            ItemTypes.ITEM_CAMERA = AllayItemType
                            .builder(ItemItemCameraStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CAMERA)
                            .build();
        }
        if (ItemTypes.ITEM_CAMPFIRE == null) {
            ItemTypes.ITEM_CAMPFIRE = AllayItemType
                            .builder(ItemItemCampfireStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CAMPFIRE)
                            .build();
        }
        if (ItemTypes.ITEM_CAULDRON == null) {
            ItemTypes.ITEM_CAULDRON = AllayItemType
                            .builder(ItemItemCauldronStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CAULDRON)
                            .build();
        }
        if (ItemTypes.ITEM_CHAIN == null) {
            ItemTypes.ITEM_CHAIN = AllayItemType
                            .builder(ItemItemChainStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CHAIN)
                            .build();
        }
        if (ItemTypes.ITEM_CRIMSON_DOOR == null) {
            ItemTypes.ITEM_CRIMSON_DOOR = AllayItemType
                            .builder(ItemItemCrimsonDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_CRIMSON_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_DARK_OAK_DOOR == null) {
            ItemTypes.ITEM_DARK_OAK_DOOR = AllayItemType
                            .builder(ItemItemDarkOakDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_DARK_OAK_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_FLOWER_POT == null) {
            ItemTypes.ITEM_FLOWER_POT = AllayItemType
                            .builder(ItemItemFlowerPotStackImpl.class)
                            .vanillaItem(ItemId.ITEM_FLOWER_POT)
                            .build();
        }
        if (ItemTypes.ITEM_FRAME == null) {
            ItemTypes.ITEM_FRAME = AllayItemType
                            .builder(ItemItemFrameStackImpl.class)
                            .vanillaItem(ItemId.ITEM_FRAME)
                            .build();
        }
        if (ItemTypes.ITEM_GLOW_FRAME == null) {
            ItemTypes.ITEM_GLOW_FRAME = AllayItemType
                            .builder(ItemItemGlowFrameStackImpl.class)
                            .vanillaItem(ItemId.ITEM_GLOW_FRAME)
                            .build();
        }
        if (ItemTypes.ITEM_HOPPER == null) {
            ItemTypes.ITEM_HOPPER = AllayItemType
                            .builder(ItemItemHopperStackImpl.class)
                            .vanillaItem(ItemId.ITEM_HOPPER)
                            .build();
        }
        if (ItemTypes.ITEM_IRON_DOOR == null) {
            ItemTypes.ITEM_IRON_DOOR = AllayItemType
                            .builder(ItemItemIronDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_IRON_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_JUNGLE_DOOR == null) {
            ItemTypes.ITEM_JUNGLE_DOOR = AllayItemType
                            .builder(ItemItemJungleDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_JUNGLE_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_KELP == null) {
            ItemTypes.ITEM_KELP = AllayItemType
                            .builder(ItemItemKelpStackImpl.class)
                            .vanillaItem(ItemId.ITEM_KELP)
                            .build();
        }
        if (ItemTypes.ITEM_MANGROVE_DOOR == null) {
            ItemTypes.ITEM_MANGROVE_DOOR = AllayItemType
                            .builder(ItemItemMangroveDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_MANGROVE_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_NETHER_SPROUTS == null) {
            ItemTypes.ITEM_NETHER_SPROUTS = AllayItemType
                            .builder(ItemItemNetherSproutsStackImpl.class)
                            .vanillaItem(ItemId.ITEM_NETHER_SPROUTS)
                            .build();
        }
        if (ItemTypes.ITEM_NETHER_WART == null) {
            ItemTypes.ITEM_NETHER_WART = AllayItemType
                            .builder(ItemItemNetherWartStackImpl.class)
                            .vanillaItem(ItemId.ITEM_NETHER_WART)
                            .build();
        }
        if (ItemTypes.ITEM_REEDS == null) {
            ItemTypes.ITEM_REEDS = AllayItemType
                            .builder(ItemItemReedsStackImpl.class)
                            .vanillaItem(ItemId.ITEM_REEDS)
                            .build();
        }
        if (ItemTypes.ITEM_SOUL_CAMPFIRE == null) {
            ItemTypes.ITEM_SOUL_CAMPFIRE = AllayItemType
                            .builder(ItemItemSoulCampfireStackImpl.class)
                            .vanillaItem(ItemId.ITEM_SOUL_CAMPFIRE)
                            .build();
        }
        if (ItemTypes.ITEM_SPRUCE_DOOR == null) {
            ItemTypes.ITEM_SPRUCE_DOOR = AllayItemType
                            .builder(ItemItemSpruceDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_SPRUCE_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_WARPED_DOOR == null) {
            ItemTypes.ITEM_WARPED_DOOR = AllayItemType
                            .builder(ItemItemWarpedDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_WARPED_DOOR)
                            .build();
        }
        if (ItemTypes.ITEM_WHEAT == null) {
            ItemTypes.ITEM_WHEAT = AllayItemType
                            .builder(ItemItemWheatStackImpl.class)
                            .vanillaItem(ItemId.ITEM_WHEAT)
                            .build();
        }
        if (ItemTypes.ITEM_WOODEN_DOOR == null) {
            ItemTypes.ITEM_WOODEN_DOOR = AllayItemType
                            .builder(ItemItemWoodenDoorStackImpl.class)
                            .vanillaItem(ItemId.ITEM_WOODEN_DOOR)
                            .build();
        }
        if (ItemTypes.JIGSAW == null) {
            ItemTypes.JIGSAW = AllayItemType
                            .builder(ItemJigsawStackImpl.class)
                            .vanillaItem(ItemId.JIGSAW)
                            .build();
        }
        if (ItemTypes.JUKEBOX == null) {
            ItemTypes.JUKEBOX = AllayItemType
                            .builder(ItemJukeboxStackImpl.class)
                            .vanillaItem(ItemId.JUKEBOX)
                            .build();
        }
        if (ItemTypes.JUNGLE_BOAT == null) {
            ItemTypes.JUNGLE_BOAT = AllayItemType
                            .builder(ItemJungleBoatStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_BOAT)
                            .build();
        }
        if (ItemTypes.JUNGLE_BUTTON == null) {
            ItemTypes.JUNGLE_BUTTON = AllayItemType
                            .builder(ItemJungleButtonStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_BUTTON)
                            .build();
        }
        if (ItemTypes.JUNGLE_CHEST_BOAT == null) {
            ItemTypes.JUNGLE_CHEST_BOAT = AllayItemType
                            .builder(ItemJungleChestBoatStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.JUNGLE_DOOR == null) {
            ItemTypes.JUNGLE_DOOR = AllayItemType
                            .builder(ItemJungleDoorStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_DOOR)
                            .build();
        }
        if (ItemTypes.JUNGLE_DOUBLE_SLAB == null) {
            ItemTypes.JUNGLE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemJungleDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.JUNGLE_FENCE == null) {
            ItemTypes.JUNGLE_FENCE = AllayItemType
                            .builder(ItemJungleFenceStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_FENCE)
                            .build();
        }
        if (ItemTypes.JUNGLE_FENCE_GATE == null) {
            ItemTypes.JUNGLE_FENCE_GATE = AllayItemType
                            .builder(ItemJungleFenceGateStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.JUNGLE_HANGING_SIGN == null) {
            ItemTypes.JUNGLE_HANGING_SIGN = AllayItemType
                            .builder(ItemJungleHangingSignStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.JUNGLE_LEAVES == null) {
            ItemTypes.JUNGLE_LEAVES = AllayItemType
                            .builder(ItemJungleLeavesStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_LEAVES)
                            .build();
        }
        if (ItemTypes.JUNGLE_LOG == null) {
            ItemTypes.JUNGLE_LOG = AllayItemType
                            .builder(ItemJungleLogStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_LOG)
                            .build();
        }
        if (ItemTypes.JUNGLE_PLANKS == null) {
            ItemTypes.JUNGLE_PLANKS = AllayItemType
                            .builder(ItemJunglePlanksStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_PLANKS)
                            .build();
        }
        if (ItemTypes.JUNGLE_PRESSURE_PLATE == null) {
            ItemTypes.JUNGLE_PRESSURE_PLATE = AllayItemType
                            .builder(ItemJunglePressurePlateStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.JUNGLE_SAPLING == null) {
            ItemTypes.JUNGLE_SAPLING = AllayItemType
                            .builder(ItemJungleSaplingStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_SAPLING)
                            .build();
        }
        if (ItemTypes.JUNGLE_SLAB == null) {
            ItemTypes.JUNGLE_SLAB = AllayItemType
                            .builder(ItemJungleSlabStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_SLAB)
                            .build();
        }
        if (ItemTypes.JUNGLE_STAIRS == null) {
            ItemTypes.JUNGLE_STAIRS = AllayItemType
                            .builder(ItemJungleStairsStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_STAIRS)
                            .build();
        }
        if (ItemTypes.JUNGLE_STANDING_SIGN == null) {
            ItemTypes.JUNGLE_STANDING_SIGN = AllayItemType
                            .builder(ItemJungleStandingSignStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.JUNGLE_TRAPDOOR == null) {
            ItemTypes.JUNGLE_TRAPDOOR = AllayItemType
                            .builder(ItemJungleTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.JUNGLE_WALL_SIGN == null) {
            ItemTypes.JUNGLE_WALL_SIGN = AllayItemType
                            .builder(ItemJungleWallSignStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.JUNGLE_WOOD == null) {
            ItemTypes.JUNGLE_WOOD = AllayItemType
                            .builder(ItemJungleWoodStackImpl.class)
                            .vanillaItem(ItemId.JUNGLE_WOOD)
                            .build();
        }
        if (ItemTypes.KELP == null) {
            ItemTypes.KELP = AllayItemType
                            .builder(ItemKelpStackImpl.class)
                            .vanillaItem(ItemId.KELP)
                            .build();
        }
        if (ItemTypes.LAB_TABLE == null) {
            ItemTypes.LAB_TABLE = AllayItemType
                            .builder(ItemLabTableStackImpl.class)
                            .vanillaItem(ItemId.LAB_TABLE)
                            .build();
        }
        if (ItemTypes.LADDER == null) {
            ItemTypes.LADDER = AllayItemType
                            .builder(ItemLadderStackImpl.class)
                            .vanillaItem(ItemId.LADDER)
                            .build();
        }
        if (ItemTypes.LANTERN == null) {
            ItemTypes.LANTERN = AllayItemType
                            .builder(ItemLanternStackImpl.class)
                            .vanillaItem(ItemId.LANTERN)
                            .build();
        }
        if (ItemTypes.LAPIS_BLOCK == null) {
            ItemTypes.LAPIS_BLOCK = AllayItemType
                            .builder(ItemLapisBlockStackImpl.class)
                            .vanillaItem(ItemId.LAPIS_BLOCK)
                            .build();
        }
        if (ItemTypes.LAPIS_LAZULI == null) {
            ItemTypes.LAPIS_LAZULI = AllayItemType
                            .builder(ItemLapisLazuliStackImpl.class)
                            .vanillaItem(ItemId.LAPIS_LAZULI)
                            .build();
        }
        if (ItemTypes.LAPIS_ORE == null) {
            ItemTypes.LAPIS_ORE = AllayItemType
                            .builder(ItemLapisOreStackImpl.class)
                            .vanillaItem(ItemId.LAPIS_ORE)
                            .build();
        }
        if (ItemTypes.LARGE_AMETHYST_BUD == null) {
            ItemTypes.LARGE_AMETHYST_BUD = AllayItemType
                            .builder(ItemLargeAmethystBudStackImpl.class)
                            .vanillaItem(ItemId.LARGE_AMETHYST_BUD)
                            .build();
        }
        if (ItemTypes.LARGE_FERN == null) {
            ItemTypes.LARGE_FERN = AllayItemType
                            .builder(ItemLargeFernStackImpl.class)
                            .vanillaItem(ItemId.LARGE_FERN)
                            .build();
        }
        if (ItemTypes.LAVA == null) {
            ItemTypes.LAVA = AllayItemType
                            .builder(ItemLavaStackImpl.class)
                            .vanillaItem(ItemId.LAVA)
                            .build();
        }
        if (ItemTypes.LEAD == null) {
            ItemTypes.LEAD = AllayItemType
                            .builder(ItemLeadStackImpl.class)
                            .vanillaItem(ItemId.LEAD)
                            .build();
        }
        if (ItemTypes.LEATHER == null) {
            ItemTypes.LEATHER = AllayItemType
                            .builder(ItemLeatherStackImpl.class)
                            .vanillaItem(ItemId.LEATHER)
                            .build();
        }
        if (ItemTypes.LEATHER_HORSE_ARMOR == null) {
            ItemTypes.LEATHER_HORSE_ARMOR = AllayItemType
                            .builder(ItemLeatherHorseArmorStackImpl.class)
                            .vanillaItem(ItemId.LEATHER_HORSE_ARMOR)
                            .build();
        }
        if (ItemTypes.LEAVES == null) {
            ItemTypes.LEAVES = AllayItemType
                            .builder(ItemLeavesStackImpl.class)
                            .vanillaItem(ItemId.LEAVES)
                            .build();
        }
        if (ItemTypes.LEAVES2 == null) {
            ItemTypes.LEAVES2 = AllayItemType
                            .builder(ItemLeaves2StackImpl.class)
                            .vanillaItem(ItemId.LEAVES2)
                            .build();
        }
        if (ItemTypes.LECTERN == null) {
            ItemTypes.LECTERN = AllayItemType
                            .builder(ItemLecternStackImpl.class)
                            .vanillaItem(ItemId.LECTERN)
                            .build();
        }
        if (ItemTypes.LEVER == null) {
            ItemTypes.LEVER = AllayItemType
                            .builder(ItemLeverStackImpl.class)
                            .vanillaItem(ItemId.LEVER)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK == null) {
            ItemTypes.LIGHT_BLOCK = AllayItemType
                            .builder(ItemLightBlockStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_0 == null) {
            ItemTypes.LIGHT_BLOCK_0 = AllayItemType
                            .builder(ItemLightBlock0StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_0)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_1 == null) {
            ItemTypes.LIGHT_BLOCK_1 = AllayItemType
                            .builder(ItemLightBlock1StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_1)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_10 == null) {
            ItemTypes.LIGHT_BLOCK_10 = AllayItemType
                            .builder(ItemLightBlock10StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_10)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_11 == null) {
            ItemTypes.LIGHT_BLOCK_11 = AllayItemType
                            .builder(ItemLightBlock11StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_11)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_12 == null) {
            ItemTypes.LIGHT_BLOCK_12 = AllayItemType
                            .builder(ItemLightBlock12StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_12)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_13 == null) {
            ItemTypes.LIGHT_BLOCK_13 = AllayItemType
                            .builder(ItemLightBlock13StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_13)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_14 == null) {
            ItemTypes.LIGHT_BLOCK_14 = AllayItemType
                            .builder(ItemLightBlock14StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_14)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_15 == null) {
            ItemTypes.LIGHT_BLOCK_15 = AllayItemType
                            .builder(ItemLightBlock15StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_15)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_2 == null) {
            ItemTypes.LIGHT_BLOCK_2 = AllayItemType
                            .builder(ItemLightBlock2StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_2)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_3 == null) {
            ItemTypes.LIGHT_BLOCK_3 = AllayItemType
                            .builder(ItemLightBlock3StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_3)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_4 == null) {
            ItemTypes.LIGHT_BLOCK_4 = AllayItemType
                            .builder(ItemLightBlock4StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_4)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_5 == null) {
            ItemTypes.LIGHT_BLOCK_5 = AllayItemType
                            .builder(ItemLightBlock5StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_5)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_6 == null) {
            ItemTypes.LIGHT_BLOCK_6 = AllayItemType
                            .builder(ItemLightBlock6StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_6)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_7 == null) {
            ItemTypes.LIGHT_BLOCK_7 = AllayItemType
                            .builder(ItemLightBlock7StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_7)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_8 == null) {
            ItemTypes.LIGHT_BLOCK_8 = AllayItemType
                            .builder(ItemLightBlock8StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_8)
                            .build();
        }
        if (ItemTypes.LIGHT_BLOCK_9 == null) {
            ItemTypes.LIGHT_BLOCK_9 = AllayItemType
                            .builder(ItemLightBlock9StackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLOCK_9)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_BUNDLE == null) {
            ItemTypes.LIGHT_BLUE_BUNDLE = AllayItemType
                            .builder(ItemLightBlueBundleStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_BUNDLE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_CANDLE == null) {
            ItemTypes.LIGHT_BLUE_CANDLE = AllayItemType
                            .builder(ItemLightBlueCandleStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_CANDLE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_CANDLE_CAKE == null) {
            ItemTypes.LIGHT_BLUE_CANDLE_CAKE = AllayItemType
                            .builder(ItemLightBlueCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_CARPET == null) {
            ItemTypes.LIGHT_BLUE_CARPET = AllayItemType
                            .builder(ItemLightBlueCarpetStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_CARPET)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_CONCRETE == null) {
            ItemTypes.LIGHT_BLUE_CONCRETE = AllayItemType
                            .builder(ItemLightBlueConcreteStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_CONCRETE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_CONCRETE_POWDER == null) {
            ItemTypes.LIGHT_BLUE_CONCRETE_POWDER = AllayItemType
                            .builder(ItemLightBlueConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_DYE == null) {
            ItemTypes.LIGHT_BLUE_DYE = AllayItemType
                            .builder(ItemLightBlueDyeStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_DYE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_GLAZED_TERRACOTTA == null) {
            ItemTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemLightBlueGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_STAINED_GLASS == null) {
            ItemTypes.LIGHT_BLUE_STAINED_GLASS = AllayItemType
                            .builder(ItemLightBlueStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_STAINED_GLASS_PANE == null) {
            ItemTypes.LIGHT_BLUE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemLightBlueStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_TERRACOTTA == null) {
            ItemTypes.LIGHT_BLUE_TERRACOTTA = AllayItemType
                            .builder(ItemLightBlueTerracottaStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.LIGHT_BLUE_WOOL == null) {
            ItemTypes.LIGHT_BLUE_WOOL = AllayItemType
                            .builder(ItemLightBlueWoolStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_BLUE_WOOL)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_BUNDLE == null) {
            ItemTypes.LIGHT_GRAY_BUNDLE = AllayItemType
                            .builder(ItemLightGrayBundleStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_BUNDLE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_CANDLE == null) {
            ItemTypes.LIGHT_GRAY_CANDLE = AllayItemType
                            .builder(ItemLightGrayCandleStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_CANDLE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_CANDLE_CAKE == null) {
            ItemTypes.LIGHT_GRAY_CANDLE_CAKE = AllayItemType
                            .builder(ItemLightGrayCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_CARPET == null) {
            ItemTypes.LIGHT_GRAY_CARPET = AllayItemType
                            .builder(ItemLightGrayCarpetStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_CARPET)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_CONCRETE == null) {
            ItemTypes.LIGHT_GRAY_CONCRETE = AllayItemType
                            .builder(ItemLightGrayConcreteStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_CONCRETE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_CONCRETE_POWDER == null) {
            ItemTypes.LIGHT_GRAY_CONCRETE_POWDER = AllayItemType
                            .builder(ItemLightGrayConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_DYE == null) {
            ItemTypes.LIGHT_GRAY_DYE = AllayItemType
                            .builder(ItemLightGrayDyeStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_DYE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_STAINED_GLASS == null) {
            ItemTypes.LIGHT_GRAY_STAINED_GLASS = AllayItemType
                            .builder(ItemLightGrayStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_STAINED_GLASS_PANE == null) {
            ItemTypes.LIGHT_GRAY_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemLightGrayStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_TERRACOTTA == null) {
            ItemTypes.LIGHT_GRAY_TERRACOTTA = AllayItemType
                            .builder(ItemLightGrayTerracottaStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.LIGHT_GRAY_WOOL == null) {
            ItemTypes.LIGHT_GRAY_WOOL = AllayItemType
                            .builder(ItemLightGrayWoolStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_GRAY_WOOL)
                            .build();
        }
        if (ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE == null) {
            ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = AllayItemType
                            .builder(ItemLightWeightedPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.LIGHTNING_ROD == null) {
            ItemTypes.LIGHTNING_ROD = AllayItemType
                            .builder(ItemLightningRodStackImpl.class)
                            .vanillaItem(ItemId.LIGHTNING_ROD)
                            .build();
        }
        if (ItemTypes.LILAC == null) {
            ItemTypes.LILAC = AllayItemType
                            .builder(ItemLilacStackImpl.class)
                            .vanillaItem(ItemId.LILAC)
                            .build();
        }
        if (ItemTypes.LILY_OF_THE_VALLEY == null) {
            ItemTypes.LILY_OF_THE_VALLEY = AllayItemType
                            .builder(ItemLilyOfTheValleyStackImpl.class)
                            .vanillaItem(ItemId.LILY_OF_THE_VALLEY)
                            .build();
        }
        if (ItemTypes.LIME_BUNDLE == null) {
            ItemTypes.LIME_BUNDLE = AllayItemType
                            .builder(ItemLimeBundleStackImpl.class)
                            .vanillaItem(ItemId.LIME_BUNDLE)
                            .build();
        }
        if (ItemTypes.LIME_CANDLE == null) {
            ItemTypes.LIME_CANDLE = AllayItemType
                            .builder(ItemLimeCandleStackImpl.class)
                            .vanillaItem(ItemId.LIME_CANDLE)
                            .build();
        }
        if (ItemTypes.LIME_CANDLE_CAKE == null) {
            ItemTypes.LIME_CANDLE_CAKE = AllayItemType
                            .builder(ItemLimeCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.LIME_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.LIME_CARPET == null) {
            ItemTypes.LIME_CARPET = AllayItemType
                            .builder(ItemLimeCarpetStackImpl.class)
                            .vanillaItem(ItemId.LIME_CARPET)
                            .build();
        }
        if (ItemTypes.LIME_CONCRETE == null) {
            ItemTypes.LIME_CONCRETE = AllayItemType
                            .builder(ItemLimeConcreteStackImpl.class)
                            .vanillaItem(ItemId.LIME_CONCRETE)
                            .build();
        }
        if (ItemTypes.LIME_CONCRETE_POWDER == null) {
            ItemTypes.LIME_CONCRETE_POWDER = AllayItemType
                            .builder(ItemLimeConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.LIME_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.LIME_DYE == null) {
            ItemTypes.LIME_DYE = AllayItemType
                            .builder(ItemLimeDyeStackImpl.class)
                            .vanillaItem(ItemId.LIME_DYE)
                            .build();
        }
        if (ItemTypes.LIME_GLAZED_TERRACOTTA == null) {
            ItemTypes.LIME_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemLimeGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.LIME_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.LIME_STAINED_GLASS == null) {
            ItemTypes.LIME_STAINED_GLASS = AllayItemType
                            .builder(ItemLimeStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.LIME_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.LIME_STAINED_GLASS_PANE == null) {
            ItemTypes.LIME_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemLimeStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.LIME_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.LIME_TERRACOTTA == null) {
            ItemTypes.LIME_TERRACOTTA = AllayItemType
                            .builder(ItemLimeTerracottaStackImpl.class)
                            .vanillaItem(ItemId.LIME_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.LIME_WOOL == null) {
            ItemTypes.LIME_WOOL = AllayItemType
                            .builder(ItemLimeWoolStackImpl.class)
                            .vanillaItem(ItemId.LIME_WOOL)
                            .build();
        }
        if (ItemTypes.LINGERING_POTION == null) {
            ItemTypes.LINGERING_POTION = AllayItemType
                            .builder(ItemLingeringPotionStackImpl.class)
                            .vanillaItem(ItemId.LINGERING_POTION)
                            .build();
        }
        if (ItemTypes.LIT_BLAST_FURNACE == null) {
            ItemTypes.LIT_BLAST_FURNACE = AllayItemType
                            .builder(ItemLitBlastFurnaceStackImpl.class)
                            .vanillaItem(ItemId.LIT_BLAST_FURNACE)
                            .build();
        }
        if (ItemTypes.LIT_DEEPSLATE_REDSTONE_ORE == null) {
            ItemTypes.LIT_DEEPSLATE_REDSTONE_ORE = AllayItemType
                            .builder(ItemLitDeepslateRedstoneOreStackImpl.class)
                            .vanillaItem(ItemId.LIT_DEEPSLATE_REDSTONE_ORE)
                            .build();
        }
        if (ItemTypes.LIT_FURNACE == null) {
            ItemTypes.LIT_FURNACE = AllayItemType
                            .builder(ItemLitFurnaceStackImpl.class)
                            .vanillaItem(ItemId.LIT_FURNACE)
                            .build();
        }
        if (ItemTypes.LIT_PUMPKIN == null) {
            ItemTypes.LIT_PUMPKIN = AllayItemType
                            .builder(ItemLitPumpkinStackImpl.class)
                            .vanillaItem(ItemId.LIT_PUMPKIN)
                            .build();
        }
        if (ItemTypes.LIT_REDSTONE_LAMP == null) {
            ItemTypes.LIT_REDSTONE_LAMP = AllayItemType
                            .builder(ItemLitRedstoneLampStackImpl.class)
                            .vanillaItem(ItemId.LIT_REDSTONE_LAMP)
                            .build();
        }
        if (ItemTypes.LIT_REDSTONE_ORE == null) {
            ItemTypes.LIT_REDSTONE_ORE = AllayItemType
                            .builder(ItemLitRedstoneOreStackImpl.class)
                            .vanillaItem(ItemId.LIT_REDSTONE_ORE)
                            .build();
        }
        if (ItemTypes.LIT_SMOKER == null) {
            ItemTypes.LIT_SMOKER = AllayItemType
                            .builder(ItemLitSmokerStackImpl.class)
                            .vanillaItem(ItemId.LIT_SMOKER)
                            .build();
        }
        if (ItemTypes.LODESTONE == null) {
            ItemTypes.LODESTONE = AllayItemType
                            .builder(ItemLodestoneStackImpl.class)
                            .vanillaItem(ItemId.LODESTONE)
                            .build();
        }
        if (ItemTypes.LODESTONE_COMPASS == null) {
            ItemTypes.LODESTONE_COMPASS = AllayItemType
                            .builder(ItemLodestoneCompassStackImpl.class)
                            .vanillaItem(ItemId.LODESTONE_COMPASS)
                            .build();
        }
        if (ItemTypes.LOG == null) {
            ItemTypes.LOG = AllayItemType
                            .builder(ItemLogStackImpl.class)
                            .vanillaItem(ItemId.LOG)
                            .build();
        }
        if (ItemTypes.LOG2 == null) {
            ItemTypes.LOG2 = AllayItemType
                            .builder(ItemLog2StackImpl.class)
                            .vanillaItem(ItemId.LOG2)
                            .build();
        }
        if (ItemTypes.LOOM == null) {
            ItemTypes.LOOM = AllayItemType
                            .builder(ItemLoomStackImpl.class)
                            .vanillaItem(ItemId.LOOM)
                            .build();
        }
        if (ItemTypes.MACE == null) {
            ItemTypes.MACE = AllayItemType
                            .builder(ItemMaceStackImpl.class)
                            .vanillaItem(ItemId.MACE)
                            .build();
        }
        if (ItemTypes.MAGENTA_BUNDLE == null) {
            ItemTypes.MAGENTA_BUNDLE = AllayItemType
                            .builder(ItemMagentaBundleStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_BUNDLE)
                            .build();
        }
        if (ItemTypes.MAGENTA_CANDLE == null) {
            ItemTypes.MAGENTA_CANDLE = AllayItemType
                            .builder(ItemMagentaCandleStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_CANDLE)
                            .build();
        }
        if (ItemTypes.MAGENTA_CANDLE_CAKE == null) {
            ItemTypes.MAGENTA_CANDLE_CAKE = AllayItemType
                            .builder(ItemMagentaCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.MAGENTA_CARPET == null) {
            ItemTypes.MAGENTA_CARPET = AllayItemType
                            .builder(ItemMagentaCarpetStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_CARPET)
                            .build();
        }
        if (ItemTypes.MAGENTA_CONCRETE == null) {
            ItemTypes.MAGENTA_CONCRETE = AllayItemType
                            .builder(ItemMagentaConcreteStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_CONCRETE)
                            .build();
        }
        if (ItemTypes.MAGENTA_CONCRETE_POWDER == null) {
            ItemTypes.MAGENTA_CONCRETE_POWDER = AllayItemType
                            .builder(ItemMagentaConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.MAGENTA_DYE == null) {
            ItemTypes.MAGENTA_DYE = AllayItemType
                            .builder(ItemMagentaDyeStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_DYE)
                            .build();
        }
        if (ItemTypes.MAGENTA_GLAZED_TERRACOTTA == null) {
            ItemTypes.MAGENTA_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemMagentaGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.MAGENTA_STAINED_GLASS == null) {
            ItemTypes.MAGENTA_STAINED_GLASS = AllayItemType
                            .builder(ItemMagentaStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.MAGENTA_STAINED_GLASS_PANE == null) {
            ItemTypes.MAGENTA_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemMagentaStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.MAGENTA_TERRACOTTA == null) {
            ItemTypes.MAGENTA_TERRACOTTA = AllayItemType
                            .builder(ItemMagentaTerracottaStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.MAGENTA_WOOL == null) {
            ItemTypes.MAGENTA_WOOL = AllayItemType
                            .builder(ItemMagentaWoolStackImpl.class)
                            .vanillaItem(ItemId.MAGENTA_WOOL)
                            .build();
        }
        if (ItemTypes.MAGMA == null) {
            ItemTypes.MAGMA = AllayItemType
                            .builder(ItemMagmaStackImpl.class)
                            .vanillaItem(ItemId.MAGMA)
                            .build();
        }
        if (ItemTypes.MAGMA_CREAM == null) {
            ItemTypes.MAGMA_CREAM = AllayItemType
                            .builder(ItemMagmaCreamStackImpl.class)
                            .vanillaItem(ItemId.MAGMA_CREAM)
                            .build();
        }
        if (ItemTypes.MANGROVE_BOAT == null) {
            ItemTypes.MANGROVE_BOAT = AllayItemType
                            .builder(ItemMangroveBoatStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_BOAT)
                            .build();
        }
        if (ItemTypes.MANGROVE_BUTTON == null) {
            ItemTypes.MANGROVE_BUTTON = AllayItemType
                            .builder(ItemMangroveButtonStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_BUTTON)
                            .build();
        }
        if (ItemTypes.MANGROVE_CHEST_BOAT == null) {
            ItemTypes.MANGROVE_CHEST_BOAT = AllayItemType
                            .builder(ItemMangroveChestBoatStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.MANGROVE_DOOR == null) {
            ItemTypes.MANGROVE_DOOR = AllayItemType
                            .builder(ItemMangroveDoorStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_DOOR)
                            .build();
        }
        if (ItemTypes.MANGROVE_DOUBLE_SLAB == null) {
            ItemTypes.MANGROVE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemMangroveDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.MANGROVE_FENCE == null) {
            ItemTypes.MANGROVE_FENCE = AllayItemType
                            .builder(ItemMangroveFenceStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_FENCE)
                            .build();
        }
        if (ItemTypes.MANGROVE_FENCE_GATE == null) {
            ItemTypes.MANGROVE_FENCE_GATE = AllayItemType
                            .builder(ItemMangroveFenceGateStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.MANGROVE_HANGING_SIGN == null) {
            ItemTypes.MANGROVE_HANGING_SIGN = AllayItemType
                            .builder(ItemMangroveHangingSignStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.MANGROVE_LEAVES == null) {
            ItemTypes.MANGROVE_LEAVES = AllayItemType
                            .builder(ItemMangroveLeavesStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_LEAVES)
                            .build();
        }
        if (ItemTypes.MANGROVE_LOG == null) {
            ItemTypes.MANGROVE_LOG = AllayItemType
                            .builder(ItemMangroveLogStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_LOG)
                            .build();
        }
        if (ItemTypes.MANGROVE_PLANKS == null) {
            ItemTypes.MANGROVE_PLANKS = AllayItemType
                            .builder(ItemMangrovePlanksStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_PLANKS)
                            .build();
        }
        if (ItemTypes.MANGROVE_PRESSURE_PLATE == null) {
            ItemTypes.MANGROVE_PRESSURE_PLATE = AllayItemType
                            .builder(ItemMangrovePressurePlateStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.MANGROVE_PROPAGULE == null) {
            ItemTypes.MANGROVE_PROPAGULE = AllayItemType
                            .builder(ItemMangrovePropaguleStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_PROPAGULE)
                            .build();
        }
        if (ItemTypes.MANGROVE_ROOTS == null) {
            ItemTypes.MANGROVE_ROOTS = AllayItemType
                            .builder(ItemMangroveRootsStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_ROOTS)
                            .build();
        }
        if (ItemTypes.MANGROVE_SLAB == null) {
            ItemTypes.MANGROVE_SLAB = AllayItemType
                            .builder(ItemMangroveSlabStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_SLAB)
                            .build();
        }
        if (ItemTypes.MANGROVE_STAIRS == null) {
            ItemTypes.MANGROVE_STAIRS = AllayItemType
                            .builder(ItemMangroveStairsStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_STAIRS)
                            .build();
        }
        if (ItemTypes.MANGROVE_STANDING_SIGN == null) {
            ItemTypes.MANGROVE_STANDING_SIGN = AllayItemType
                            .builder(ItemMangroveStandingSignStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.MANGROVE_TRAPDOOR == null) {
            ItemTypes.MANGROVE_TRAPDOOR = AllayItemType
                            .builder(ItemMangroveTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.MANGROVE_WALL_SIGN == null) {
            ItemTypes.MANGROVE_WALL_SIGN = AllayItemType
                            .builder(ItemMangroveWallSignStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.MANGROVE_WOOD == null) {
            ItemTypes.MANGROVE_WOOD = AllayItemType
                            .builder(ItemMangroveWoodStackImpl.class)
                            .vanillaItem(ItemId.MANGROVE_WOOD)
                            .build();
        }
        if (ItemTypes.MATERIAL_REDUCER == null) {
            ItemTypes.MATERIAL_REDUCER = AllayItemType
                            .builder(ItemMaterialReducerStackImpl.class)
                            .vanillaItem(ItemId.MATERIAL_REDUCER)
                            .build();
        }
        if (ItemTypes.MEDICINE == null) {
            ItemTypes.MEDICINE = AllayItemType
                            .builder(ItemMedicineStackImpl.class)
                            .vanillaItem(ItemId.MEDICINE)
                            .build();
        }
        if (ItemTypes.MEDIUM_AMETHYST_BUD == null) {
            ItemTypes.MEDIUM_AMETHYST_BUD = AllayItemType
                            .builder(ItemMediumAmethystBudStackImpl.class)
                            .vanillaItem(ItemId.MEDIUM_AMETHYST_BUD)
                            .build();
        }
        if (ItemTypes.MELON_BLOCK == null) {
            ItemTypes.MELON_BLOCK = AllayItemType
                            .builder(ItemMelonBlockStackImpl.class)
                            .vanillaItem(ItemId.MELON_BLOCK)
                            .build();
        }
        if (ItemTypes.MELON_SEEDS == null) {
            ItemTypes.MELON_SEEDS = AllayItemType
                            .builder(ItemMelonSeedsStackImpl.class)
                            .vanillaItem(ItemId.MELON_SEEDS)
                            .build();
        }
        if (ItemTypes.MELON_SLICE == null) {
            ItemTypes.MELON_SLICE = AllayItemType
                            .builder(ItemMelonSliceStackImpl.class)
                            .vanillaItem(ItemId.MELON_SLICE)
                            .build();
        }
        if (ItemTypes.MELON_STEM == null) {
            ItemTypes.MELON_STEM = AllayItemType
                            .builder(ItemMelonStemStackImpl.class)
                            .vanillaItem(ItemId.MELON_STEM)
                            .build();
        }
        if (ItemTypes.MINECART == null) {
            ItemTypes.MINECART = AllayItemType
                            .builder(ItemMinecartStackImpl.class)
                            .vanillaItem(ItemId.MINECART)
                            .build();
        }
        if (ItemTypes.MINER_POTTERY_SHERD == null) {
            ItemTypes.MINER_POTTERY_SHERD = AllayItemType
                            .builder(ItemMinerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.MINER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.MOB_SPAWNER == null) {
            ItemTypes.MOB_SPAWNER = AllayItemType
                            .builder(ItemMobSpawnerStackImpl.class)
                            .vanillaItem(ItemId.MOB_SPAWNER)
                            .build();
        }
        if (ItemTypes.MOJANG_BANNER_PATTERN == null) {
            ItemTypes.MOJANG_BANNER_PATTERN = AllayItemType
                            .builder(ItemMojangBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.MOJANG_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.MONSTER_EGG == null) {
            ItemTypes.MONSTER_EGG = AllayItemType
                            .builder(ItemMonsterEggStackImpl.class)
                            .vanillaItem(ItemId.MONSTER_EGG)
                            .build();
        }
        if (ItemTypes.MOSS_BLOCK == null) {
            ItemTypes.MOSS_BLOCK = AllayItemType
                            .builder(ItemMossBlockStackImpl.class)
                            .vanillaItem(ItemId.MOSS_BLOCK)
                            .build();
        }
        if (ItemTypes.MOSS_CARPET == null) {
            ItemTypes.MOSS_CARPET = AllayItemType
                            .builder(ItemMossCarpetStackImpl.class)
                            .vanillaItem(ItemId.MOSS_CARPET)
                            .build();
        }
        if (ItemTypes.MOSSY_COBBLESTONE == null) {
            ItemTypes.MOSSY_COBBLESTONE = AllayItemType
                            .builder(ItemMossyCobblestoneStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_COBBLESTONE)
                            .build();
        }
        if (ItemTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB == null) {
            ItemTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemMossyCobblestoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.MOSSY_COBBLESTONE_SLAB == null) {
            ItemTypes.MOSSY_COBBLESTONE_SLAB = AllayItemType
                            .builder(ItemMossyCobblestoneSlabStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_COBBLESTONE_SLAB)
                            .build();
        }
        if (ItemTypes.MOSSY_COBBLESTONE_STAIRS == null) {
            ItemTypes.MOSSY_COBBLESTONE_STAIRS = AllayItemType
                            .builder(ItemMossyCobblestoneStairsStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_COBBLESTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.MOSSY_COBBLESTONE_WALL == null) {
            ItemTypes.MOSSY_COBBLESTONE_WALL = AllayItemType
                            .builder(ItemMossyCobblestoneWallStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_COBBLESTONE_WALL)
                            .build();
        }
        if (ItemTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemMossyStoneBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.MOSSY_STONE_BRICK_SLAB == null) {
            ItemTypes.MOSSY_STONE_BRICK_SLAB = AllayItemType
                            .builder(ItemMossyStoneBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_STONE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.MOSSY_STONE_BRICK_STAIRS == null) {
            ItemTypes.MOSSY_STONE_BRICK_STAIRS = AllayItemType
                            .builder(ItemMossyStoneBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_STONE_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.MOSSY_STONE_BRICK_WALL == null) {
            ItemTypes.MOSSY_STONE_BRICK_WALL = AllayItemType
                            .builder(ItemMossyStoneBrickWallStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_STONE_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.MOSSY_STONE_BRICKS == null) {
            ItemTypes.MOSSY_STONE_BRICKS = AllayItemType
                            .builder(ItemMossyStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.MOSSY_STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.MOURNER_POTTERY_SHERD == null) {
            ItemTypes.MOURNER_POTTERY_SHERD = AllayItemType
                            .builder(ItemMournerPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.MOURNER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.MOVING_BLOCK == null) {
            ItemTypes.MOVING_BLOCK = AllayItemType
                            .builder(ItemMovingBlockStackImpl.class)
                            .vanillaItem(ItemId.MOVING_BLOCK)
                            .build();
        }
        if (ItemTypes.MUD == null) {
            ItemTypes.MUD = AllayItemType
                            .builder(ItemMudStackImpl.class)
                            .vanillaItem(ItemId.MUD)
                            .build();
        }
        if (ItemTypes.MUD_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.MUD_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemMudBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.MUD_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.MUD_BRICK_SLAB == null) {
            ItemTypes.MUD_BRICK_SLAB = AllayItemType
                            .builder(ItemMudBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.MUD_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.MUD_BRICK_STAIRS == null) {
            ItemTypes.MUD_BRICK_STAIRS = AllayItemType
                            .builder(ItemMudBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.MUD_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.MUD_BRICK_WALL == null) {
            ItemTypes.MUD_BRICK_WALL = AllayItemType
                            .builder(ItemMudBrickWallStackImpl.class)
                            .vanillaItem(ItemId.MUD_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.MUD_BRICKS == null) {
            ItemTypes.MUD_BRICKS = AllayItemType
                            .builder(ItemMudBricksStackImpl.class)
                            .vanillaItem(ItemId.MUD_BRICKS)
                            .build();
        }
        if (ItemTypes.MUDDY_MANGROVE_ROOTS == null) {
            ItemTypes.MUDDY_MANGROVE_ROOTS = AllayItemType
                            .builder(ItemMuddyMangroveRootsStackImpl.class)
                            .vanillaItem(ItemId.MUDDY_MANGROVE_ROOTS)
                            .build();
        }
        if (ItemTypes.MUSHROOM_STEM == null) {
            ItemTypes.MUSHROOM_STEM = AllayItemType
                            .builder(ItemMushroomStemStackImpl.class)
                            .vanillaItem(ItemId.MUSHROOM_STEM)
                            .build();
        }
        if (ItemTypes.MUSHROOM_STEW == null) {
            ItemTypes.MUSHROOM_STEW = AllayItemType
                            .builder(ItemMushroomStewStackImpl.class)
                            .vanillaItem(ItemId.MUSHROOM_STEW)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_11 == null) {
            ItemTypes.MUSIC_DISC_11 = AllayItemType
                            .builder(ItemMusicDisc11StackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_11)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_13 == null) {
            ItemTypes.MUSIC_DISC_13 = AllayItemType
                            .builder(ItemMusicDisc13StackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_13)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_5 == null) {
            ItemTypes.MUSIC_DISC_5 = AllayItemType
                            .builder(ItemMusicDisc5StackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_5)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_BLOCKS == null) {
            ItemTypes.MUSIC_DISC_BLOCKS = AllayItemType
                            .builder(ItemMusicDiscBlocksStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_BLOCKS)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_CAT == null) {
            ItemTypes.MUSIC_DISC_CAT = AllayItemType
                            .builder(ItemMusicDiscCatStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_CAT)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_CHIRP == null) {
            ItemTypes.MUSIC_DISC_CHIRP = AllayItemType
                            .builder(ItemMusicDiscChirpStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_CHIRP)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_CREATOR == null) {
            ItemTypes.MUSIC_DISC_CREATOR = AllayItemType
                            .builder(ItemMusicDiscCreatorStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_CREATOR)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX == null) {
            ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX = AllayItemType
                            .builder(ItemMusicDiscCreatorMusicBoxStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_CREATOR_MUSIC_BOX)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_FAR == null) {
            ItemTypes.MUSIC_DISC_FAR = AllayItemType
                            .builder(ItemMusicDiscFarStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_FAR)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_MALL == null) {
            ItemTypes.MUSIC_DISC_MALL = AllayItemType
                            .builder(ItemMusicDiscMallStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_MALL)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_MELLOHI == null) {
            ItemTypes.MUSIC_DISC_MELLOHI = AllayItemType
                            .builder(ItemMusicDiscMellohiStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_MELLOHI)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_OTHERSIDE == null) {
            ItemTypes.MUSIC_DISC_OTHERSIDE = AllayItemType
                            .builder(ItemMusicDiscOthersideStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_OTHERSIDE)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_PIGSTEP == null) {
            ItemTypes.MUSIC_DISC_PIGSTEP = AllayItemType
                            .builder(ItemMusicDiscPigstepStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_PIGSTEP)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_PRECIPICE == null) {
            ItemTypes.MUSIC_DISC_PRECIPICE = AllayItemType
                            .builder(ItemMusicDiscPrecipiceStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_PRECIPICE)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_RELIC == null) {
            ItemTypes.MUSIC_DISC_RELIC = AllayItemType
                            .builder(ItemMusicDiscRelicStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_RELIC)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_STAL == null) {
            ItemTypes.MUSIC_DISC_STAL = AllayItemType
                            .builder(ItemMusicDiscStalStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_STAL)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_STRAD == null) {
            ItemTypes.MUSIC_DISC_STRAD = AllayItemType
                            .builder(ItemMusicDiscStradStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_STRAD)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_WAIT == null) {
            ItemTypes.MUSIC_DISC_WAIT = AllayItemType
                            .builder(ItemMusicDiscWaitStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_WAIT)
                            .build();
        }
        if (ItemTypes.MUSIC_DISC_WARD == null) {
            ItemTypes.MUSIC_DISC_WARD = AllayItemType
                            .builder(ItemMusicDiscWardStackImpl.class)
                            .vanillaItem(ItemId.MUSIC_DISC_WARD)
                            .build();
        }
        if (ItemTypes.MUTTON == null) {
            ItemTypes.MUTTON = AllayItemType
                            .builder(ItemMuttonStackImpl.class)
                            .vanillaItem(ItemId.MUTTON)
                            .build();
        }
        if (ItemTypes.MYCELIUM == null) {
            ItemTypes.MYCELIUM = AllayItemType
                            .builder(ItemMyceliumStackImpl.class)
                            .vanillaItem(ItemId.MYCELIUM)
                            .build();
        }
        if (ItemTypes.NAME_TAG == null) {
            ItemTypes.NAME_TAG = AllayItemType
                            .builder(ItemNameTagStackImpl.class)
                            .vanillaItem(ItemId.NAME_TAG)
                            .build();
        }
        if (ItemTypes.NAUTILUS_SHELL == null) {
            ItemTypes.NAUTILUS_SHELL = AllayItemType
                            .builder(ItemNautilusShellStackImpl.class)
                            .vanillaItem(ItemId.NAUTILUS_SHELL)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK == null) {
            ItemTypes.NETHER_BRICK = AllayItemType
                            .builder(ItemNetherBrickStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.NETHER_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemNetherBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK_FENCE == null) {
            ItemTypes.NETHER_BRICK_FENCE = AllayItemType
                            .builder(ItemNetherBrickFenceStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK_FENCE)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK_SLAB == null) {
            ItemTypes.NETHER_BRICK_SLAB = AllayItemType
                            .builder(ItemNetherBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK_STAIRS == null) {
            ItemTypes.NETHER_BRICK_STAIRS = AllayItemType
                            .builder(ItemNetherBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.NETHER_BRICK_WALL == null) {
            ItemTypes.NETHER_BRICK_WALL = AllayItemType
                            .builder(ItemNetherBrickWallStackImpl.class)
                            .vanillaItem(ItemId.NETHER_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.NETHER_GOLD_ORE == null) {
            ItemTypes.NETHER_GOLD_ORE = AllayItemType
                            .builder(ItemNetherGoldOreStackImpl.class)
                            .vanillaItem(ItemId.NETHER_GOLD_ORE)
                            .build();
        }
        if (ItemTypes.NETHER_SPROUTS == null) {
            ItemTypes.NETHER_SPROUTS = AllayItemType
                            .builder(ItemNetherSproutsStackImpl.class)
                            .vanillaItem(ItemId.NETHER_SPROUTS)
                            .build();
        }
        if (ItemTypes.NETHER_STAR == null) {
            ItemTypes.NETHER_STAR = AllayItemType
                            .builder(ItemNetherStarStackImpl.class)
                            .vanillaItem(ItemId.NETHER_STAR)
                            .build();
        }
        if (ItemTypes.NETHER_WART == null) {
            ItemTypes.NETHER_WART = AllayItemType
                            .builder(ItemNetherWartStackImpl.class)
                            .vanillaItem(ItemId.NETHER_WART)
                            .build();
        }
        if (ItemTypes.NETHER_WART_BLOCK == null) {
            ItemTypes.NETHER_WART_BLOCK = AllayItemType
                            .builder(ItemNetherWartBlockStackImpl.class)
                            .vanillaItem(ItemId.NETHER_WART_BLOCK)
                            .build();
        }
        if (ItemTypes.NETHERBRICK == null) {
            ItemTypes.NETHERBRICK = AllayItemType
                            .builder(ItemNetherbrick0StackImpl.class)
                            .vanillaItem(ItemId.NETHERBRICK)
                            .build();
        }
        if (ItemTypes.NETHERITE_BLOCK == null) {
            ItemTypes.NETHERITE_BLOCK = AllayItemType
                            .builder(ItemNetheriteBlockStackImpl.class)
                            .vanillaItem(ItemId.NETHERITE_BLOCK)
                            .build();
        }
        if (ItemTypes.NETHERITE_INGOT == null) {
            ItemTypes.NETHERITE_INGOT = AllayItemType
                            .builder(ItemNetheriteIngotStackImpl.class)
                            .vanillaItem(ItemId.NETHERITE_INGOT)
                            .build();
        }
        if (ItemTypes.NETHERITE_SCRAP == null) {
            ItemTypes.NETHERITE_SCRAP = AllayItemType
                            .builder(ItemNetheriteScrapStackImpl.class)
                            .vanillaItem(ItemId.NETHERITE_SCRAP)
                            .build();
        }
        if (ItemTypes.NETHERITE_UPGRADE_SMITHING_TEMPLATE == null) {
            ItemTypes.NETHERITE_UPGRADE_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemNetheriteUpgradeSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.NETHERRACK == null) {
            ItemTypes.NETHERRACK = AllayItemType
                            .builder(ItemNetherrackStackImpl.class)
                            .vanillaItem(ItemId.NETHERRACK)
                            .build();
        }
        if (ItemTypes.NETHERREACTOR == null) {
            ItemTypes.NETHERREACTOR = AllayItemType
                            .builder(ItemNetherreactorStackImpl.class)
                            .vanillaItem(ItemId.NETHERREACTOR)
                            .build();
        }
        if (ItemTypes.NORMAL_STONE_DOUBLE_SLAB == null) {
            ItemTypes.NORMAL_STONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemNormalStoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.NORMAL_STONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.NORMAL_STONE_SLAB == null) {
            ItemTypes.NORMAL_STONE_SLAB = AllayItemType
                            .builder(ItemNormalStoneSlabStackImpl.class)
                            .vanillaItem(ItemId.NORMAL_STONE_SLAB)
                            .build();
        }
        if (ItemTypes.NORMAL_STONE_STAIRS == null) {
            ItemTypes.NORMAL_STONE_STAIRS = AllayItemType
                            .builder(ItemNormalStoneStairsStackImpl.class)
                            .vanillaItem(ItemId.NORMAL_STONE_STAIRS)
                            .build();
        }
        if (ItemTypes.NOTEBLOCK == null) {
            ItemTypes.NOTEBLOCK = AllayItemType
                            .builder(ItemNoteblockStackImpl.class)
                            .vanillaItem(ItemId.NOTEBLOCK)
                            .build();
        }
        if (ItemTypes.OAK_BOAT == null) {
            ItemTypes.OAK_BOAT = AllayItemType
                            .builder(ItemOakBoatStackImpl.class)
                            .vanillaItem(ItemId.OAK_BOAT)
                            .build();
        }
        if (ItemTypes.OAK_CHEST_BOAT == null) {
            ItemTypes.OAK_CHEST_BOAT = AllayItemType
                            .builder(ItemOakChestBoatStackImpl.class)
                            .vanillaItem(ItemId.OAK_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.OAK_DOUBLE_SLAB == null) {
            ItemTypes.OAK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemOakDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.OAK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.OAK_FENCE == null) {
            ItemTypes.OAK_FENCE = AllayItemType
                            .builder(ItemOakFenceStackImpl.class)
                            .vanillaItem(ItemId.OAK_FENCE)
                            .build();
        }
        if (ItemTypes.OAK_HANGING_SIGN == null) {
            ItemTypes.OAK_HANGING_SIGN = AllayItemType
                            .builder(ItemOakHangingSignStackImpl.class)
                            .vanillaItem(ItemId.OAK_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.OAK_LEAVES == null) {
            ItemTypes.OAK_LEAVES = AllayItemType
                            .builder(ItemOakLeavesStackImpl.class)
                            .vanillaItem(ItemId.OAK_LEAVES)
                            .build();
        }
        if (ItemTypes.OAK_LOG == null) {
            ItemTypes.OAK_LOG = AllayItemType
                            .builder(ItemOakLogStackImpl.class)
                            .vanillaItem(ItemId.OAK_LOG)
                            .build();
        }
        if (ItemTypes.OAK_PLANKS == null) {
            ItemTypes.OAK_PLANKS = AllayItemType
                            .builder(ItemOakPlanksStackImpl.class)
                            .vanillaItem(ItemId.OAK_PLANKS)
                            .build();
        }
        if (ItemTypes.OAK_SAPLING == null) {
            ItemTypes.OAK_SAPLING = AllayItemType
                            .builder(ItemOakSaplingStackImpl.class)
                            .vanillaItem(ItemId.OAK_SAPLING)
                            .build();
        }
        if (ItemTypes.OAK_SLAB == null) {
            ItemTypes.OAK_SLAB = AllayItemType
                            .builder(ItemOakSlabStackImpl.class)
                            .vanillaItem(ItemId.OAK_SLAB)
                            .build();
        }
        if (ItemTypes.OAK_STAIRS == null) {
            ItemTypes.OAK_STAIRS = AllayItemType
                            .builder(ItemOakStairsStackImpl.class)
                            .vanillaItem(ItemId.OAK_STAIRS)
                            .build();
        }
        if (ItemTypes.OAK_WOOD == null) {
            ItemTypes.OAK_WOOD = AllayItemType
                            .builder(ItemOakWoodStackImpl.class)
                            .vanillaItem(ItemId.OAK_WOOD)
                            .build();
        }
        if (ItemTypes.OBSERVER == null) {
            ItemTypes.OBSERVER = AllayItemType
                            .builder(ItemObserverStackImpl.class)
                            .vanillaItem(ItemId.OBSERVER)
                            .build();
        }
        if (ItemTypes.OBSIDIAN == null) {
            ItemTypes.OBSIDIAN = AllayItemType
                            .builder(ItemObsidianStackImpl.class)
                            .vanillaItem(ItemId.OBSIDIAN)
                            .build();
        }
        if (ItemTypes.OCHRE_FROGLIGHT == null) {
            ItemTypes.OCHRE_FROGLIGHT = AllayItemType
                            .builder(ItemOchreFroglightStackImpl.class)
                            .vanillaItem(ItemId.OCHRE_FROGLIGHT)
                            .build();
        }
        if (ItemTypes.OMINOUS_BOTTLE == null) {
            ItemTypes.OMINOUS_BOTTLE = AllayItemType
                            .builder(ItemOminousBottleStackImpl.class)
                            .vanillaItem(ItemId.OMINOUS_BOTTLE)
                            .build();
        }
        if (ItemTypes.OMINOUS_TRIAL_KEY == null) {
            ItemTypes.OMINOUS_TRIAL_KEY = AllayItemType
                            .builder(ItemOminousTrialKeyStackImpl.class)
                            .vanillaItem(ItemId.OMINOUS_TRIAL_KEY)
                            .build();
        }
        if (ItemTypes.ORANGE_BUNDLE == null) {
            ItemTypes.ORANGE_BUNDLE = AllayItemType
                            .builder(ItemOrangeBundleStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_BUNDLE)
                            .build();
        }
        if (ItemTypes.ORANGE_CANDLE == null) {
            ItemTypes.ORANGE_CANDLE = AllayItemType
                            .builder(ItemOrangeCandleStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_CANDLE)
                            .build();
        }
        if (ItemTypes.ORANGE_CANDLE_CAKE == null) {
            ItemTypes.ORANGE_CANDLE_CAKE = AllayItemType
                            .builder(ItemOrangeCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.ORANGE_CARPET == null) {
            ItemTypes.ORANGE_CARPET = AllayItemType
                            .builder(ItemOrangeCarpetStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_CARPET)
                            .build();
        }
        if (ItemTypes.ORANGE_CONCRETE == null) {
            ItemTypes.ORANGE_CONCRETE = AllayItemType
                            .builder(ItemOrangeConcreteStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_CONCRETE)
                            .build();
        }
        if (ItemTypes.ORANGE_CONCRETE_POWDER == null) {
            ItemTypes.ORANGE_CONCRETE_POWDER = AllayItemType
                            .builder(ItemOrangeConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.ORANGE_DYE == null) {
            ItemTypes.ORANGE_DYE = AllayItemType
                            .builder(ItemOrangeDyeStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_DYE)
                            .build();
        }
        if (ItemTypes.ORANGE_GLAZED_TERRACOTTA == null) {
            ItemTypes.ORANGE_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemOrangeGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.ORANGE_STAINED_GLASS == null) {
            ItemTypes.ORANGE_STAINED_GLASS = AllayItemType
                            .builder(ItemOrangeStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.ORANGE_STAINED_GLASS_PANE == null) {
            ItemTypes.ORANGE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemOrangeStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.ORANGE_TERRACOTTA == null) {
            ItemTypes.ORANGE_TERRACOTTA = AllayItemType
                            .builder(ItemOrangeTerracottaStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.ORANGE_TULIP == null) {
            ItemTypes.ORANGE_TULIP = AllayItemType
                            .builder(ItemOrangeTulipStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_TULIP)
                            .build();
        }
        if (ItemTypes.ORANGE_WOOL == null) {
            ItemTypes.ORANGE_WOOL = AllayItemType
                            .builder(ItemOrangeWoolStackImpl.class)
                            .vanillaItem(ItemId.ORANGE_WOOL)
                            .build();
        }
        if (ItemTypes.OXEYE_DAISY == null) {
            ItemTypes.OXEYE_DAISY = AllayItemType
                            .builder(ItemOxeyeDaisyStackImpl.class)
                            .vanillaItem(ItemId.OXEYE_DAISY)
                            .build();
        }
        if (ItemTypes.OXIDIZED_CHISELED_COPPER == null) {
            ItemTypes.OXIDIZED_CHISELED_COPPER = AllayItemType
                            .builder(ItemOxidizedChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.OXIDIZED_COPPER == null) {
            ItemTypes.OXIDIZED_COPPER = AllayItemType
                            .builder(ItemOxidizedCopperStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_COPPER)
                            .build();
        }
        if (ItemTypes.OXIDIZED_COPPER_BULB == null) {
            ItemTypes.OXIDIZED_COPPER_BULB = AllayItemType
                            .builder(ItemOxidizedCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.OXIDIZED_COPPER_DOOR == null) {
            ItemTypes.OXIDIZED_COPPER_DOOR = AllayItemType
                            .builder(ItemOxidizedCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.OXIDIZED_COPPER_GRATE == null) {
            ItemTypes.OXIDIZED_COPPER_GRATE = AllayItemType
                            .builder(ItemOxidizedCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.OXIDIZED_COPPER_TRAPDOOR == null) {
            ItemTypes.OXIDIZED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemOxidizedCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.OXIDIZED_CUT_COPPER == null) {
            ItemTypes.OXIDIZED_CUT_COPPER = AllayItemType
                            .builder(ItemOxidizedCutCopperStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.OXIDIZED_CUT_COPPER_SLAB == null) {
            ItemTypes.OXIDIZED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemOxidizedCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.OXIDIZED_CUT_COPPER_STAIRS == null) {
            ItemTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemOxidizedCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemOxidizedDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.PACKED_ICE == null) {
            ItemTypes.PACKED_ICE = AllayItemType
                            .builder(ItemPackedIceStackImpl.class)
                            .vanillaItem(ItemId.PACKED_ICE)
                            .build();
        }
        if (ItemTypes.PACKED_MUD == null) {
            ItemTypes.PACKED_MUD = AllayItemType
                            .builder(ItemPackedMudStackImpl.class)
                            .vanillaItem(ItemId.PACKED_MUD)
                            .build();
        }
        if (ItemTypes.PAINTING == null) {
            ItemTypes.PAINTING = AllayItemType
                            .builder(ItemPaintingStackImpl.class)
                            .vanillaItem(ItemId.PAINTING)
                            .build();
        }
        if (ItemTypes.PAPER == null) {
            ItemTypes.PAPER = AllayItemType
                            .builder(ItemPaperStackImpl.class)
                            .vanillaItem(ItemId.PAPER)
                            .build();
        }
        if (ItemTypes.PEARLESCENT_FROGLIGHT == null) {
            ItemTypes.PEARLESCENT_FROGLIGHT = AllayItemType
                            .builder(ItemPearlescentFroglightStackImpl.class)
                            .vanillaItem(ItemId.PEARLESCENT_FROGLIGHT)
                            .build();
        }
        if (ItemTypes.PEONY == null) {
            ItemTypes.PEONY = AllayItemType
                            .builder(ItemPeonyStackImpl.class)
                            .vanillaItem(ItemId.PEONY)
                            .build();
        }
        if (ItemTypes.PETRIFIED_OAK_DOUBLE_SLAB == null) {
            ItemTypes.PETRIFIED_OAK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPetrifiedOakDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.PETRIFIED_OAK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.PETRIFIED_OAK_SLAB == null) {
            ItemTypes.PETRIFIED_OAK_SLAB = AllayItemType
                            .builder(ItemPetrifiedOakSlabStackImpl.class)
                            .vanillaItem(ItemId.PETRIFIED_OAK_SLAB)
                            .build();
        }
        if (ItemTypes.PHANTOM_MEMBRANE == null) {
            ItemTypes.PHANTOM_MEMBRANE = AllayItemType
                            .builder(ItemPhantomMembraneStackImpl.class)
                            .vanillaItem(ItemId.PHANTOM_MEMBRANE)
                            .build();
        }
        if (ItemTypes.PIGLIN_BANNER_PATTERN == null) {
            ItemTypes.PIGLIN_BANNER_PATTERN = AllayItemType
                            .builder(ItemPiglinBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.PIGLIN_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.PIGLIN_HEAD == null) {
            ItemTypes.PIGLIN_HEAD = AllayItemType
                            .builder(ItemPiglinHeadStackImpl.class)
                            .vanillaItem(ItemId.PIGLIN_HEAD)
                            .build();
        }
        if (ItemTypes.PINK_BUNDLE == null) {
            ItemTypes.PINK_BUNDLE = AllayItemType
                            .builder(ItemPinkBundleStackImpl.class)
                            .vanillaItem(ItemId.PINK_BUNDLE)
                            .build();
        }
        if (ItemTypes.PINK_CANDLE == null) {
            ItemTypes.PINK_CANDLE = AllayItemType
                            .builder(ItemPinkCandleStackImpl.class)
                            .vanillaItem(ItemId.PINK_CANDLE)
                            .build();
        }
        if (ItemTypes.PINK_CANDLE_CAKE == null) {
            ItemTypes.PINK_CANDLE_CAKE = AllayItemType
                            .builder(ItemPinkCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.PINK_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.PINK_CARPET == null) {
            ItemTypes.PINK_CARPET = AllayItemType
                            .builder(ItemPinkCarpetStackImpl.class)
                            .vanillaItem(ItemId.PINK_CARPET)
                            .build();
        }
        if (ItemTypes.PINK_CONCRETE == null) {
            ItemTypes.PINK_CONCRETE = AllayItemType
                            .builder(ItemPinkConcreteStackImpl.class)
                            .vanillaItem(ItemId.PINK_CONCRETE)
                            .build();
        }
        if (ItemTypes.PINK_CONCRETE_POWDER == null) {
            ItemTypes.PINK_CONCRETE_POWDER = AllayItemType
                            .builder(ItemPinkConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.PINK_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.PINK_DYE == null) {
            ItemTypes.PINK_DYE = AllayItemType
                            .builder(ItemPinkDyeStackImpl.class)
                            .vanillaItem(ItemId.PINK_DYE)
                            .build();
        }
        if (ItemTypes.PINK_GLAZED_TERRACOTTA == null) {
            ItemTypes.PINK_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemPinkGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.PINK_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.PINK_PETALS == null) {
            ItemTypes.PINK_PETALS = AllayItemType
                            .builder(ItemPinkPetalsStackImpl.class)
                            .vanillaItem(ItemId.PINK_PETALS)
                            .build();
        }
        if (ItemTypes.PINK_STAINED_GLASS == null) {
            ItemTypes.PINK_STAINED_GLASS = AllayItemType
                            .builder(ItemPinkStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.PINK_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.PINK_STAINED_GLASS_PANE == null) {
            ItemTypes.PINK_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemPinkStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.PINK_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.PINK_TERRACOTTA == null) {
            ItemTypes.PINK_TERRACOTTA = AllayItemType
                            .builder(ItemPinkTerracottaStackImpl.class)
                            .vanillaItem(ItemId.PINK_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.PINK_TULIP == null) {
            ItemTypes.PINK_TULIP = AllayItemType
                            .builder(ItemPinkTulipStackImpl.class)
                            .vanillaItem(ItemId.PINK_TULIP)
                            .build();
        }
        if (ItemTypes.PINK_WOOL == null) {
            ItemTypes.PINK_WOOL = AllayItemType
                            .builder(ItemPinkWoolStackImpl.class)
                            .vanillaItem(ItemId.PINK_WOOL)
                            .build();
        }
        if (ItemTypes.PISTON == null) {
            ItemTypes.PISTON = AllayItemType
                            .builder(ItemPistonStackImpl.class)
                            .vanillaItem(ItemId.PISTON)
                            .build();
        }
        if (ItemTypes.PISTON_ARM_COLLISION == null) {
            ItemTypes.PISTON_ARM_COLLISION = AllayItemType
                            .builder(ItemPistonArmCollisionStackImpl.class)
                            .vanillaItem(ItemId.PISTON_ARM_COLLISION)
                            .build();
        }
        if (ItemTypes.PITCHER_CROP == null) {
            ItemTypes.PITCHER_CROP = AllayItemType
                            .builder(ItemPitcherCropStackImpl.class)
                            .vanillaItem(ItemId.PITCHER_CROP)
                            .build();
        }
        if (ItemTypes.PITCHER_PLANT == null) {
            ItemTypes.PITCHER_PLANT = AllayItemType
                            .builder(ItemPitcherPlantStackImpl.class)
                            .vanillaItem(ItemId.PITCHER_PLANT)
                            .build();
        }
        if (ItemTypes.PITCHER_POD == null) {
            ItemTypes.PITCHER_POD = AllayItemType
                            .builder(ItemPitcherPodStackImpl.class)
                            .vanillaItem(ItemId.PITCHER_POD)
                            .build();
        }
        if (ItemTypes.PLANKS == null) {
            ItemTypes.PLANKS = AllayItemType
                            .builder(ItemPlanksStackImpl.class)
                            .vanillaItem(ItemId.PLANKS)
                            .build();
        }
        if (ItemTypes.PLAYER_HEAD == null) {
            ItemTypes.PLAYER_HEAD = AllayItemType
                            .builder(ItemPlayerHeadStackImpl.class)
                            .vanillaItem(ItemId.PLAYER_HEAD)
                            .build();
        }
        if (ItemTypes.PLENTY_POTTERY_SHERD == null) {
            ItemTypes.PLENTY_POTTERY_SHERD = AllayItemType
                            .builder(ItemPlentyPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.PLENTY_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.PODZOL == null) {
            ItemTypes.PODZOL = AllayItemType
                            .builder(ItemPodzolStackImpl.class)
                            .vanillaItem(ItemId.PODZOL)
                            .build();
        }
        if (ItemTypes.POINTED_DRIPSTONE == null) {
            ItemTypes.POINTED_DRIPSTONE = AllayItemType
                            .builder(ItemPointedDripstoneStackImpl.class)
                            .vanillaItem(ItemId.POINTED_DRIPSTONE)
                            .build();
        }
        if (ItemTypes.POISONOUS_POTATO == null) {
            ItemTypes.POISONOUS_POTATO = AllayItemType
                            .builder(ItemPoisonousPotatoStackImpl.class)
                            .vanillaItem(ItemId.POISONOUS_POTATO)
                            .build();
        }
        if (ItemTypes.POLISHED_ANDESITE == null) {
            ItemTypes.POLISHED_ANDESITE = AllayItemType
                            .builder(ItemPolishedAndesiteStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_ANDESITE)
                            .build();
        }
        if (ItemTypes.POLISHED_ANDESITE_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_ANDESITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedAndesiteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_ANDESITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_ANDESITE_SLAB == null) {
            ItemTypes.POLISHED_ANDESITE_SLAB = AllayItemType
                            .builder(ItemPolishedAndesiteSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_ANDESITE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_ANDESITE_STAIRS == null) {
            ItemTypes.POLISHED_ANDESITE_STAIRS = AllayItemType
                            .builder(ItemPolishedAndesiteStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_ANDESITE_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_BASALT == null) {
            ItemTypes.POLISHED_BASALT = AllayItemType
                            .builder(ItemPolishedBasaltStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BASALT)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE == null) {
            ItemTypes.POLISHED_BLACKSTONE = AllayItemType
                            .builder(ItemPolishedBlackstoneStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedBlackstoneBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_SLAB == null) {
            ItemTypes.POLISHED_BLACKSTONE_BRICK_SLAB = AllayItemType
                            .builder(ItemPolishedBlackstoneBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_STAIRS == null) {
            ItemTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayItemType
                            .builder(ItemPolishedBlackstoneBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_WALL == null) {
            ItemTypes.POLISHED_BLACKSTONE_BRICK_WALL = AllayItemType
                            .builder(ItemPolishedBlackstoneBrickWallStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BRICKS == null) {
            ItemTypes.POLISHED_BLACKSTONE_BRICKS = AllayItemType
                            .builder(ItemPolishedBlackstoneBricksStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICKS)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_BUTTON == null) {
            ItemTypes.POLISHED_BLACKSTONE_BUTTON = AllayItemType
                            .builder(ItemPolishedBlackstoneButtonStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_BUTTON)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedBlackstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE == null) {
            ItemTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = AllayItemType
                            .builder(ItemPolishedBlackstonePressurePlateStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_SLAB == null) {
            ItemTypes.POLISHED_BLACKSTONE_SLAB = AllayItemType
                            .builder(ItemPolishedBlackstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_STAIRS == null) {
            ItemTypes.POLISHED_BLACKSTONE_STAIRS = AllayItemType
                            .builder(ItemPolishedBlackstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_BLACKSTONE_WALL == null) {
            ItemTypes.POLISHED_BLACKSTONE_WALL = AllayItemType
                            .builder(ItemPolishedBlackstoneWallStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_BLACKSTONE_WALL)
                            .build();
        }
        if (ItemTypes.POLISHED_DEEPSLATE == null) {
            ItemTypes.POLISHED_DEEPSLATE = AllayItemType
                            .builder(ItemPolishedDeepslateStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DEEPSLATE)
                            .build();
        }
        if (ItemTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedDeepslateDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_DEEPSLATE_SLAB == null) {
            ItemTypes.POLISHED_DEEPSLATE_SLAB = AllayItemType
                            .builder(ItemPolishedDeepslateSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DEEPSLATE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_DEEPSLATE_STAIRS == null) {
            ItemTypes.POLISHED_DEEPSLATE_STAIRS = AllayItemType
                            .builder(ItemPolishedDeepslateStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DEEPSLATE_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_DEEPSLATE_WALL == null) {
            ItemTypes.POLISHED_DEEPSLATE_WALL = AllayItemType
                            .builder(ItemPolishedDeepslateWallStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DEEPSLATE_WALL)
                            .build();
        }
        if (ItemTypes.POLISHED_DIORITE == null) {
            ItemTypes.POLISHED_DIORITE = AllayItemType
                            .builder(ItemPolishedDioriteStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DIORITE)
                            .build();
        }
        if (ItemTypes.POLISHED_DIORITE_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_DIORITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedDioriteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DIORITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_DIORITE_SLAB == null) {
            ItemTypes.POLISHED_DIORITE_SLAB = AllayItemType
                            .builder(ItemPolishedDioriteSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DIORITE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_DIORITE_STAIRS == null) {
            ItemTypes.POLISHED_DIORITE_STAIRS = AllayItemType
                            .builder(ItemPolishedDioriteStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_DIORITE_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_GRANITE == null) {
            ItemTypes.POLISHED_GRANITE = AllayItemType
                            .builder(ItemPolishedGraniteStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_GRANITE)
                            .build();
        }
        if (ItemTypes.POLISHED_GRANITE_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_GRANITE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedGraniteDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_GRANITE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_GRANITE_SLAB == null) {
            ItemTypes.POLISHED_GRANITE_SLAB = AllayItemType
                            .builder(ItemPolishedGraniteSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_GRANITE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_GRANITE_STAIRS == null) {
            ItemTypes.POLISHED_GRANITE_STAIRS = AllayItemType
                            .builder(ItemPolishedGraniteStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_GRANITE_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_TUFF == null) {
            ItemTypes.POLISHED_TUFF = AllayItemType
                            .builder(ItemPolishedTuffStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_TUFF)
                            .build();
        }
        if (ItemTypes.POLISHED_TUFF_DOUBLE_SLAB == null) {
            ItemTypes.POLISHED_TUFF_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPolishedTuffDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_TUFF_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_TUFF_SLAB == null) {
            ItemTypes.POLISHED_TUFF_SLAB = AllayItemType
                            .builder(ItemPolishedTuffSlabStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_TUFF_SLAB)
                            .build();
        }
        if (ItemTypes.POLISHED_TUFF_STAIRS == null) {
            ItemTypes.POLISHED_TUFF_STAIRS = AllayItemType
                            .builder(ItemPolishedTuffStairsStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_TUFF_STAIRS)
                            .build();
        }
        if (ItemTypes.POLISHED_TUFF_WALL == null) {
            ItemTypes.POLISHED_TUFF_WALL = AllayItemType
                            .builder(ItemPolishedTuffWallStackImpl.class)
                            .vanillaItem(ItemId.POLISHED_TUFF_WALL)
                            .build();
        }
        if (ItemTypes.POPPED_CHORUS_FRUIT == null) {
            ItemTypes.POPPED_CHORUS_FRUIT = AllayItemType
                            .builder(ItemPoppedChorusFruitStackImpl.class)
                            .vanillaItem(ItemId.POPPED_CHORUS_FRUIT)
                            .build();
        }
        if (ItemTypes.POPPY == null) {
            ItemTypes.POPPY = AllayItemType
                            .builder(ItemPoppyStackImpl.class)
                            .vanillaItem(ItemId.POPPY)
                            .build();
        }
        if (ItemTypes.PORKCHOP == null) {
            ItemTypes.PORKCHOP = AllayItemType
                            .builder(ItemPorkchopStackImpl.class)
                            .vanillaItem(ItemId.PORKCHOP)
                            .build();
        }
        if (ItemTypes.PORTAL == null) {
            ItemTypes.PORTAL = AllayItemType
                            .builder(ItemPortalStackImpl.class)
                            .vanillaItem(ItemId.PORTAL)
                            .build();
        }
        if (ItemTypes.POTATO == null) {
            ItemTypes.POTATO = AllayItemType
                            .builder(ItemPotatoStackImpl.class)
                            .vanillaItem(ItemId.POTATO)
                            .build();
        }
        if (ItemTypes.POTATOES == null) {
            ItemTypes.POTATOES = AllayItemType
                            .builder(ItemPotatoesStackImpl.class)
                            .vanillaItem(ItemId.POTATOES)
                            .build();
        }
        if (ItemTypes.POTION == null) {
            ItemTypes.POTION = AllayItemType
                            .builder(ItemPotionStackImpl.class)
                            .vanillaItem(ItemId.POTION)
                            .build();
        }
        if (ItemTypes.POWDER_SNOW == null) {
            ItemTypes.POWDER_SNOW = AllayItemType
                            .builder(ItemPowderSnowStackImpl.class)
                            .vanillaItem(ItemId.POWDER_SNOW)
                            .build();
        }
        if (ItemTypes.POWERED_COMPARATOR == null) {
            ItemTypes.POWERED_COMPARATOR = AllayItemType
                            .builder(ItemPoweredComparatorStackImpl.class)
                            .vanillaItem(ItemId.POWERED_COMPARATOR)
                            .build();
        }
        if (ItemTypes.POWERED_REPEATER == null) {
            ItemTypes.POWERED_REPEATER = AllayItemType
                            .builder(ItemPoweredRepeaterStackImpl.class)
                            .vanillaItem(ItemId.POWERED_REPEATER)
                            .build();
        }
        if (ItemTypes.PRISMARINE == null) {
            ItemTypes.PRISMARINE = AllayItemType
                            .builder(ItemPrismarineStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE)
                            .build();
        }
        if (ItemTypes.PRISMARINE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.PRISMARINE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPrismarineBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.PRISMARINE_BRICK_SLAB == null) {
            ItemTypes.PRISMARINE_BRICK_SLAB = AllayItemType
                            .builder(ItemPrismarineBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.PRISMARINE_BRICKS == null) {
            ItemTypes.PRISMARINE_BRICKS = AllayItemType
                            .builder(ItemPrismarineBricksStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_BRICKS)
                            .build();
        }
        if (ItemTypes.PRISMARINE_BRICKS_STAIRS == null) {
            ItemTypes.PRISMARINE_BRICKS_STAIRS = AllayItemType
                            .builder(ItemPrismarineBricksStairsStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_BRICKS_STAIRS)
                            .build();
        }
        if (ItemTypes.PRISMARINE_CRYSTALS == null) {
            ItemTypes.PRISMARINE_CRYSTALS = AllayItemType
                            .builder(ItemPrismarineCrystalsStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_CRYSTALS)
                            .build();
        }
        if (ItemTypes.PRISMARINE_DOUBLE_SLAB == null) {
            ItemTypes.PRISMARINE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPrismarineDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.PRISMARINE_SHARD == null) {
            ItemTypes.PRISMARINE_SHARD = AllayItemType
                            .builder(ItemPrismarineShardStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_SHARD)
                            .build();
        }
        if (ItemTypes.PRISMARINE_SLAB == null) {
            ItemTypes.PRISMARINE_SLAB = AllayItemType
                            .builder(ItemPrismarineSlabStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_SLAB)
                            .build();
        }
        if (ItemTypes.PRISMARINE_STAIRS == null) {
            ItemTypes.PRISMARINE_STAIRS = AllayItemType
                            .builder(ItemPrismarineStairsStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_STAIRS)
                            .build();
        }
        if (ItemTypes.PRISMARINE_WALL == null) {
            ItemTypes.PRISMARINE_WALL = AllayItemType
                            .builder(ItemPrismarineWallStackImpl.class)
                            .vanillaItem(ItemId.PRISMARINE_WALL)
                            .build();
        }
        if (ItemTypes.PRIZE_POTTERY_SHERD == null) {
            ItemTypes.PRIZE_POTTERY_SHERD = AllayItemType
                            .builder(ItemPrizePotterySherdStackImpl.class)
                            .vanillaItem(ItemId.PRIZE_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.PUFFERFISH == null) {
            ItemTypes.PUFFERFISH = AllayItemType
                            .builder(ItemPufferfishStackImpl.class)
                            .vanillaItem(ItemId.PUFFERFISH)
                            .build();
        }
        if (ItemTypes.PUMPKIN == null) {
            ItemTypes.PUMPKIN = AllayItemType
                            .builder(ItemPumpkinStackImpl.class)
                            .vanillaItem(ItemId.PUMPKIN)
                            .build();
        }
        if (ItemTypes.PUMPKIN_PIE == null) {
            ItemTypes.PUMPKIN_PIE = AllayItemType
                            .builder(ItemPumpkinPieStackImpl.class)
                            .vanillaItem(ItemId.PUMPKIN_PIE)
                            .build();
        }
        if (ItemTypes.PUMPKIN_SEEDS == null) {
            ItemTypes.PUMPKIN_SEEDS = AllayItemType
                            .builder(ItemPumpkinSeedsStackImpl.class)
                            .vanillaItem(ItemId.PUMPKIN_SEEDS)
                            .build();
        }
        if (ItemTypes.PUMPKIN_STEM == null) {
            ItemTypes.PUMPKIN_STEM = AllayItemType
                            .builder(ItemPumpkinStemStackImpl.class)
                            .vanillaItem(ItemId.PUMPKIN_STEM)
                            .build();
        }
        if (ItemTypes.PURPLE_BUNDLE == null) {
            ItemTypes.PURPLE_BUNDLE = AllayItemType
                            .builder(ItemPurpleBundleStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_BUNDLE)
                            .build();
        }
        if (ItemTypes.PURPLE_CANDLE == null) {
            ItemTypes.PURPLE_CANDLE = AllayItemType
                            .builder(ItemPurpleCandleStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_CANDLE)
                            .build();
        }
        if (ItemTypes.PURPLE_CANDLE_CAKE == null) {
            ItemTypes.PURPLE_CANDLE_CAKE = AllayItemType
                            .builder(ItemPurpleCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.PURPLE_CARPET == null) {
            ItemTypes.PURPLE_CARPET = AllayItemType
                            .builder(ItemPurpleCarpetStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_CARPET)
                            .build();
        }
        if (ItemTypes.PURPLE_CONCRETE == null) {
            ItemTypes.PURPLE_CONCRETE = AllayItemType
                            .builder(ItemPurpleConcreteStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_CONCRETE)
                            .build();
        }
        if (ItemTypes.PURPLE_CONCRETE_POWDER == null) {
            ItemTypes.PURPLE_CONCRETE_POWDER = AllayItemType
                            .builder(ItemPurpleConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.PURPLE_DYE == null) {
            ItemTypes.PURPLE_DYE = AllayItemType
                            .builder(ItemPurpleDyeStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_DYE)
                            .build();
        }
        if (ItemTypes.PURPLE_GLAZED_TERRACOTTA == null) {
            ItemTypes.PURPLE_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemPurpleGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.PURPLE_STAINED_GLASS == null) {
            ItemTypes.PURPLE_STAINED_GLASS = AllayItemType
                            .builder(ItemPurpleStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.PURPLE_STAINED_GLASS_PANE == null) {
            ItemTypes.PURPLE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemPurpleStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.PURPLE_TERRACOTTA == null) {
            ItemTypes.PURPLE_TERRACOTTA = AllayItemType
                            .builder(ItemPurpleTerracottaStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.PURPLE_WOOL == null) {
            ItemTypes.PURPLE_WOOL = AllayItemType
                            .builder(ItemPurpleWoolStackImpl.class)
                            .vanillaItem(ItemId.PURPLE_WOOL)
                            .build();
        }
        if (ItemTypes.PURPUR_BLOCK == null) {
            ItemTypes.PURPUR_BLOCK = AllayItemType
                            .builder(ItemPurpurBlockStackImpl.class)
                            .vanillaItem(ItemId.PURPUR_BLOCK)
                            .build();
        }
        if (ItemTypes.PURPUR_DOUBLE_SLAB == null) {
            ItemTypes.PURPUR_DOUBLE_SLAB = AllayItemType
                            .builder(ItemPurpurDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.PURPUR_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.PURPUR_PILLAR == null) {
            ItemTypes.PURPUR_PILLAR = AllayItemType
                            .builder(ItemPurpurPillarStackImpl.class)
                            .vanillaItem(ItemId.PURPUR_PILLAR)
                            .build();
        }
        if (ItemTypes.PURPUR_SLAB == null) {
            ItemTypes.PURPUR_SLAB = AllayItemType
                            .builder(ItemPurpurSlabStackImpl.class)
                            .vanillaItem(ItemId.PURPUR_SLAB)
                            .build();
        }
        if (ItemTypes.PURPUR_STAIRS == null) {
            ItemTypes.PURPUR_STAIRS = AllayItemType
                            .builder(ItemPurpurStairsStackImpl.class)
                            .vanillaItem(ItemId.PURPUR_STAIRS)
                            .build();
        }
        if (ItemTypes.QUARTZ == null) {
            ItemTypes.QUARTZ = AllayItemType
                            .builder(ItemQuartzStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ)
                            .build();
        }
        if (ItemTypes.QUARTZ_BLOCK == null) {
            ItemTypes.QUARTZ_BLOCK = AllayItemType
                            .builder(ItemQuartzBlockStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_BLOCK)
                            .build();
        }
        if (ItemTypes.QUARTZ_BRICKS == null) {
            ItemTypes.QUARTZ_BRICKS = AllayItemType
                            .builder(ItemQuartzBricksStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_BRICKS)
                            .build();
        }
        if (ItemTypes.QUARTZ_DOUBLE_SLAB == null) {
            ItemTypes.QUARTZ_DOUBLE_SLAB = AllayItemType
                            .builder(ItemQuartzDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.QUARTZ_ORE == null) {
            ItemTypes.QUARTZ_ORE = AllayItemType
                            .builder(ItemQuartzOreStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_ORE)
                            .build();
        }
        if (ItemTypes.QUARTZ_PILLAR == null) {
            ItemTypes.QUARTZ_PILLAR = AllayItemType
                            .builder(ItemQuartzPillarStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_PILLAR)
                            .build();
        }
        if (ItemTypes.QUARTZ_SLAB == null) {
            ItemTypes.QUARTZ_SLAB = AllayItemType
                            .builder(ItemQuartzSlabStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_SLAB)
                            .build();
        }
        if (ItemTypes.QUARTZ_STAIRS == null) {
            ItemTypes.QUARTZ_STAIRS = AllayItemType
                            .builder(ItemQuartzStairsStackImpl.class)
                            .vanillaItem(ItemId.QUARTZ_STAIRS)
                            .build();
        }
        if (ItemTypes.RABBIT == null) {
            ItemTypes.RABBIT = AllayItemType
                            .builder(ItemRabbitStackImpl.class)
                            .vanillaItem(ItemId.RABBIT)
                            .build();
        }
        if (ItemTypes.RABBIT_FOOT == null) {
            ItemTypes.RABBIT_FOOT = AllayItemType
                            .builder(ItemRabbitFootStackImpl.class)
                            .vanillaItem(ItemId.RABBIT_FOOT)
                            .build();
        }
        if (ItemTypes.RABBIT_HIDE == null) {
            ItemTypes.RABBIT_HIDE = AllayItemType
                            .builder(ItemRabbitHideStackImpl.class)
                            .vanillaItem(ItemId.RABBIT_HIDE)
                            .build();
        }
        if (ItemTypes.RABBIT_STEW == null) {
            ItemTypes.RABBIT_STEW = AllayItemType
                            .builder(ItemRabbitStewStackImpl.class)
                            .vanillaItem(ItemId.RABBIT_STEW)
                            .build();
        }
        if (ItemTypes.RAIL == null) {
            ItemTypes.RAIL = AllayItemType
                            .builder(ItemRailStackImpl.class)
                            .vanillaItem(ItemId.RAIL)
                            .build();
        }
        if (ItemTypes.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemRaiserArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.RAPID_FERTILIZER == null) {
            ItemTypes.RAPID_FERTILIZER = AllayItemType
                            .builder(ItemRapidFertilizerStackImpl.class)
                            .vanillaItem(ItemId.RAPID_FERTILIZER)
                            .build();
        }
        if (ItemTypes.RAW_COPPER == null) {
            ItemTypes.RAW_COPPER = AllayItemType
                            .builder(ItemRawCopperStackImpl.class)
                            .vanillaItem(ItemId.RAW_COPPER)
                            .build();
        }
        if (ItemTypes.RAW_COPPER_BLOCK == null) {
            ItemTypes.RAW_COPPER_BLOCK = AllayItemType
                            .builder(ItemRawCopperBlockStackImpl.class)
                            .vanillaItem(ItemId.RAW_COPPER_BLOCK)
                            .build();
        }
        if (ItemTypes.RAW_GOLD == null) {
            ItemTypes.RAW_GOLD = AllayItemType
                            .builder(ItemRawGoldStackImpl.class)
                            .vanillaItem(ItemId.RAW_GOLD)
                            .build();
        }
        if (ItemTypes.RAW_GOLD_BLOCK == null) {
            ItemTypes.RAW_GOLD_BLOCK = AllayItemType
                            .builder(ItemRawGoldBlockStackImpl.class)
                            .vanillaItem(ItemId.RAW_GOLD_BLOCK)
                            .build();
        }
        if (ItemTypes.RAW_IRON == null) {
            ItemTypes.RAW_IRON = AllayItemType
                            .builder(ItemRawIronStackImpl.class)
                            .vanillaItem(ItemId.RAW_IRON)
                            .build();
        }
        if (ItemTypes.RAW_IRON_BLOCK == null) {
            ItemTypes.RAW_IRON_BLOCK = AllayItemType
                            .builder(ItemRawIronBlockStackImpl.class)
                            .vanillaItem(ItemId.RAW_IRON_BLOCK)
                            .build();
        }
        if (ItemTypes.RECOVERY_COMPASS == null) {
            ItemTypes.RECOVERY_COMPASS = AllayItemType
                            .builder(ItemRecoveryCompassStackImpl.class)
                            .vanillaItem(ItemId.RECOVERY_COMPASS)
                            .build();
        }
        if (ItemTypes.RED_BUNDLE == null) {
            ItemTypes.RED_BUNDLE = AllayItemType
                            .builder(ItemRedBundleStackImpl.class)
                            .vanillaItem(ItemId.RED_BUNDLE)
                            .build();
        }
        if (ItemTypes.RED_CANDLE == null) {
            ItemTypes.RED_CANDLE = AllayItemType
                            .builder(ItemRedCandleStackImpl.class)
                            .vanillaItem(ItemId.RED_CANDLE)
                            .build();
        }
        if (ItemTypes.RED_CANDLE_CAKE == null) {
            ItemTypes.RED_CANDLE_CAKE = AllayItemType
                            .builder(ItemRedCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.RED_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.RED_CARPET == null) {
            ItemTypes.RED_CARPET = AllayItemType
                            .builder(ItemRedCarpetStackImpl.class)
                            .vanillaItem(ItemId.RED_CARPET)
                            .build();
        }
        if (ItemTypes.RED_CONCRETE == null) {
            ItemTypes.RED_CONCRETE = AllayItemType
                            .builder(ItemRedConcreteStackImpl.class)
                            .vanillaItem(ItemId.RED_CONCRETE)
                            .build();
        }
        if (ItemTypes.RED_CONCRETE_POWDER == null) {
            ItemTypes.RED_CONCRETE_POWDER = AllayItemType
                            .builder(ItemRedConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.RED_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.RED_DYE == null) {
            ItemTypes.RED_DYE = AllayItemType
                            .builder(ItemRedDyeStackImpl.class)
                            .vanillaItem(ItemId.RED_DYE)
                            .build();
        }
        if (ItemTypes.RED_FLOWER == null) {
            ItemTypes.RED_FLOWER = AllayItemType
                            .builder(ItemRedFlowerStackImpl.class)
                            .vanillaItem(ItemId.RED_FLOWER)
                            .build();
        }
        if (ItemTypes.RED_GLAZED_TERRACOTTA == null) {
            ItemTypes.RED_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemRedGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.RED_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.RED_MUSHROOM == null) {
            ItemTypes.RED_MUSHROOM = AllayItemType
                            .builder(ItemRedMushroomStackImpl.class)
                            .vanillaItem(ItemId.RED_MUSHROOM)
                            .build();
        }
        if (ItemTypes.RED_MUSHROOM_BLOCK == null) {
            ItemTypes.RED_MUSHROOM_BLOCK = AllayItemType
                            .builder(ItemRedMushroomBlockStackImpl.class)
                            .vanillaItem(ItemId.RED_MUSHROOM_BLOCK)
                            .build();
        }
        if (ItemTypes.RED_NETHER_BRICK == null) {
            ItemTypes.RED_NETHER_BRICK = AllayItemType
                            .builder(ItemRedNetherBrickStackImpl.class)
                            .vanillaItem(ItemId.RED_NETHER_BRICK)
                            .build();
        }
        if (ItemTypes.RED_NETHER_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.RED_NETHER_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemRedNetherBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.RED_NETHER_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.RED_NETHER_BRICK_SLAB == null) {
            ItemTypes.RED_NETHER_BRICK_SLAB = AllayItemType
                            .builder(ItemRedNetherBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.RED_NETHER_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.RED_NETHER_BRICK_STAIRS == null) {
            ItemTypes.RED_NETHER_BRICK_STAIRS = AllayItemType
                            .builder(ItemRedNetherBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.RED_NETHER_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.RED_NETHER_BRICK_WALL == null) {
            ItemTypes.RED_NETHER_BRICK_WALL = AllayItemType
                            .builder(ItemRedNetherBrickWallStackImpl.class)
                            .vanillaItem(ItemId.RED_NETHER_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.RED_SAND == null) {
            ItemTypes.RED_SAND = AllayItemType
                            .builder(ItemRedSandStackImpl.class)
                            .vanillaItem(ItemId.RED_SAND)
                            .build();
        }
        if (ItemTypes.RED_SANDSTONE == null) {
            ItemTypes.RED_SANDSTONE = AllayItemType
                            .builder(ItemRedSandstoneStackImpl.class)
                            .vanillaItem(ItemId.RED_SANDSTONE)
                            .build();
        }
        if (ItemTypes.RED_SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.RED_SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemRedSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.RED_SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.RED_SANDSTONE_SLAB == null) {
            ItemTypes.RED_SANDSTONE_SLAB = AllayItemType
                            .builder(ItemRedSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.RED_SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.RED_SANDSTONE_STAIRS == null) {
            ItemTypes.RED_SANDSTONE_STAIRS = AllayItemType
                            .builder(ItemRedSandstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.RED_SANDSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.RED_SANDSTONE_WALL == null) {
            ItemTypes.RED_SANDSTONE_WALL = AllayItemType
                            .builder(ItemRedSandstoneWallStackImpl.class)
                            .vanillaItem(ItemId.RED_SANDSTONE_WALL)
                            .build();
        }
        if (ItemTypes.RED_STAINED_GLASS == null) {
            ItemTypes.RED_STAINED_GLASS = AllayItemType
                            .builder(ItemRedStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.RED_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.RED_STAINED_GLASS_PANE == null) {
            ItemTypes.RED_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemRedStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.RED_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.RED_TERRACOTTA == null) {
            ItemTypes.RED_TERRACOTTA = AllayItemType
                            .builder(ItemRedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.RED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.RED_TULIP == null) {
            ItemTypes.RED_TULIP = AllayItemType
                            .builder(ItemRedTulipStackImpl.class)
                            .vanillaItem(ItemId.RED_TULIP)
                            .build();
        }
        if (ItemTypes.RED_WOOL == null) {
            ItemTypes.RED_WOOL = AllayItemType
                            .builder(ItemRedWoolStackImpl.class)
                            .vanillaItem(ItemId.RED_WOOL)
                            .build();
        }
        if (ItemTypes.REDSTONE == null) {
            ItemTypes.REDSTONE = AllayItemType
                            .builder(ItemRedstoneStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE)
                            .build();
        }
        if (ItemTypes.REDSTONE_BLOCK == null) {
            ItemTypes.REDSTONE_BLOCK = AllayItemType
                            .builder(ItemRedstoneBlockStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE_BLOCK)
                            .build();
        }
        if (ItemTypes.REDSTONE_LAMP == null) {
            ItemTypes.REDSTONE_LAMP = AllayItemType
                            .builder(ItemRedstoneLampStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE_LAMP)
                            .build();
        }
        if (ItemTypes.REDSTONE_ORE == null) {
            ItemTypes.REDSTONE_ORE = AllayItemType
                            .builder(ItemRedstoneOreStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE_ORE)
                            .build();
        }
        if (ItemTypes.REDSTONE_TORCH == null) {
            ItemTypes.REDSTONE_TORCH = AllayItemType
                            .builder(ItemRedstoneTorchStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE_TORCH)
                            .build();
        }
        if (ItemTypes.REDSTONE_WIRE == null) {
            ItemTypes.REDSTONE_WIRE = AllayItemType
                            .builder(ItemRedstoneWireStackImpl.class)
                            .vanillaItem(ItemId.REDSTONE_WIRE)
                            .build();
        }
        if (ItemTypes.REINFORCED_DEEPSLATE == null) {
            ItemTypes.REINFORCED_DEEPSLATE = AllayItemType
                            .builder(ItemReinforcedDeepslateStackImpl.class)
                            .vanillaItem(ItemId.REINFORCED_DEEPSLATE)
                            .build();
        }
        if (ItemTypes.REPEATER == null) {
            ItemTypes.REPEATER = AllayItemType
                            .builder(ItemRepeaterStackImpl.class)
                            .vanillaItem(ItemId.REPEATER)
                            .build();
        }
        if (ItemTypes.REPEATING_COMMAND_BLOCK == null) {
            ItemTypes.REPEATING_COMMAND_BLOCK = AllayItemType
                            .builder(ItemRepeatingCommandBlockStackImpl.class)
                            .vanillaItem(ItemId.REPEATING_COMMAND_BLOCK)
                            .build();
        }
        if (ItemTypes.RESERVED6 == null) {
            ItemTypes.RESERVED6 = AllayItemType
                            .builder(ItemReserved6StackImpl.class)
                            .vanillaItem(ItemId.RESERVED6)
                            .build();
        }
        if (ItemTypes.RESPAWN_ANCHOR == null) {
            ItemTypes.RESPAWN_ANCHOR = AllayItemType
                            .builder(ItemRespawnAnchorStackImpl.class)
                            .vanillaItem(ItemId.RESPAWN_ANCHOR)
                            .build();
        }
        if (ItemTypes.RIB_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.RIB_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemRibArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.RIB_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.ROSE_BUSH == null) {
            ItemTypes.ROSE_BUSH = AllayItemType
                            .builder(ItemRoseBushStackImpl.class)
                            .vanillaItem(ItemId.ROSE_BUSH)
                            .build();
        }
        if (ItemTypes.ROTTEN_FLESH == null) {
            ItemTypes.ROTTEN_FLESH = AllayItemType
                            .builder(ItemRottenFleshStackImpl.class)
                            .vanillaItem(ItemId.ROTTEN_FLESH)
                            .build();
        }
        if (ItemTypes.SADDLE == null) {
            ItemTypes.SADDLE = AllayItemType
                            .builder(ItemSaddleStackImpl.class)
                            .vanillaItem(ItemId.SADDLE)
                            .build();
        }
        if (ItemTypes.SALMON == null) {
            ItemTypes.SALMON = AllayItemType
                            .builder(ItemSalmonStackImpl.class)
                            .vanillaItem(ItemId.SALMON)
                            .build();
        }
        if (ItemTypes.SAND == null) {
            ItemTypes.SAND = AllayItemType
                            .builder(ItemSandStackImpl.class)
                            .vanillaItem(ItemId.SAND)
                            .build();
        }
        if (ItemTypes.SANDSTONE == null) {
            ItemTypes.SANDSTONE = AllayItemType
                            .builder(ItemSandstoneStackImpl.class)
                            .vanillaItem(ItemId.SANDSTONE)
                            .build();
        }
        if (ItemTypes.SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SANDSTONE_SLAB == null) {
            ItemTypes.SANDSTONE_SLAB = AllayItemType
                            .builder(ItemSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.SANDSTONE_STAIRS == null) {
            ItemTypes.SANDSTONE_STAIRS = AllayItemType
                            .builder(ItemSandstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.SANDSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.SANDSTONE_WALL == null) {
            ItemTypes.SANDSTONE_WALL = AllayItemType
                            .builder(ItemSandstoneWallStackImpl.class)
                            .vanillaItem(ItemId.SANDSTONE_WALL)
                            .build();
        }
        if (ItemTypes.SAPLING == null) {
            ItemTypes.SAPLING = AllayItemType
                            .builder(ItemSaplingStackImpl.class)
                            .vanillaItem(ItemId.SAPLING)
                            .build();
        }
        if (ItemTypes.SCAFFOLDING == null) {
            ItemTypes.SCAFFOLDING = AllayItemType
                            .builder(ItemScaffoldingStackImpl.class)
                            .vanillaItem(ItemId.SCAFFOLDING)
                            .build();
        }
        if (ItemTypes.SCRAPE_POTTERY_SHERD == null) {
            ItemTypes.SCRAPE_POTTERY_SHERD = AllayItemType
                            .builder(ItemScrapePotterySherdStackImpl.class)
                            .vanillaItem(ItemId.SCRAPE_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.SCULK == null) {
            ItemTypes.SCULK = AllayItemType
                            .builder(ItemSculkStackImpl.class)
                            .vanillaItem(ItemId.SCULK)
                            .build();
        }
        if (ItemTypes.SCULK_CATALYST == null) {
            ItemTypes.SCULK_CATALYST = AllayItemType
                            .builder(ItemSculkCatalystStackImpl.class)
                            .vanillaItem(ItemId.SCULK_CATALYST)
                            .build();
        }
        if (ItemTypes.SCULK_SENSOR == null) {
            ItemTypes.SCULK_SENSOR = AllayItemType
                            .builder(ItemSculkSensorStackImpl.class)
                            .vanillaItem(ItemId.SCULK_SENSOR)
                            .build();
        }
        if (ItemTypes.SCULK_SHRIEKER == null) {
            ItemTypes.SCULK_SHRIEKER = AllayItemType
                            .builder(ItemSculkShriekerStackImpl.class)
                            .vanillaItem(ItemId.SCULK_SHRIEKER)
                            .build();
        }
        if (ItemTypes.SCULK_VEIN == null) {
            ItemTypes.SCULK_VEIN = AllayItemType
                            .builder(ItemSculkVeinStackImpl.class)
                            .vanillaItem(ItemId.SCULK_VEIN)
                            .build();
        }
        if (ItemTypes.SEA_LANTERN == null) {
            ItemTypes.SEA_LANTERN = AllayItemType
                            .builder(ItemSeaLanternStackImpl.class)
                            .vanillaItem(ItemId.SEA_LANTERN)
                            .build();
        }
        if (ItemTypes.SEA_PICKLE == null) {
            ItemTypes.SEA_PICKLE = AllayItemType
                            .builder(ItemSeaPickleStackImpl.class)
                            .vanillaItem(ItemId.SEA_PICKLE)
                            .build();
        }
        if (ItemTypes.SEAGRASS == null) {
            ItemTypes.SEAGRASS = AllayItemType
                            .builder(ItemSeagrassStackImpl.class)
                            .vanillaItem(ItemId.SEAGRASS)
                            .build();
        }
        if (ItemTypes.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemSentryArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemShaperArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.SHEAF_POTTERY_SHERD == null) {
            ItemTypes.SHEAF_POTTERY_SHERD = AllayItemType
                            .builder(ItemSheafPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.SHEAF_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.SHEARS == null) {
            ItemTypes.SHEARS = AllayItemType
                            .builder(ItemShearsStackImpl.class)
                            .vanillaItem(ItemId.SHEARS)
                            .build();
        }
        if (ItemTypes.SHELTER_POTTERY_SHERD == null) {
            ItemTypes.SHELTER_POTTERY_SHERD = AllayItemType
                            .builder(ItemShelterPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.SHELTER_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.SHIELD == null) {
            ItemTypes.SHIELD = AllayItemType
                            .builder(ItemShieldStackImpl.class)
                            .vanillaItem(ItemId.SHIELD)
                            .build();
        }
        if (ItemTypes.SHORT_GRASS == null) {
            ItemTypes.SHORT_GRASS = AllayItemType
                            .builder(ItemShortGrassStackImpl.class)
                            .vanillaItem(ItemId.SHORT_GRASS)
                            .build();
        }
        if (ItemTypes.SHROOMLIGHT == null) {
            ItemTypes.SHROOMLIGHT = AllayItemType
                            .builder(ItemShroomlightStackImpl.class)
                            .vanillaItem(ItemId.SHROOMLIGHT)
                            .build();
        }
        if (ItemTypes.SHULKER_SHELL == null) {
            ItemTypes.SHULKER_SHELL = AllayItemType
                            .builder(ItemShulkerShellStackImpl.class)
                            .vanillaItem(ItemId.SHULKER_SHELL)
                            .build();
        }
        if (ItemTypes.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemSilenceArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.SILVER_GLAZED_TERRACOTTA == null) {
            ItemTypes.SILVER_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemSilverGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.SILVER_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.SKELETON_SKULL == null) {
            ItemTypes.SKELETON_SKULL = AllayItemType
                            .builder(ItemSkeletonSkullStackImpl.class)
                            .vanillaItem(ItemId.SKELETON_SKULL)
                            .build();
        }
        if (ItemTypes.SKULL == null) {
            ItemTypes.SKULL = AllayItemType
                            .builder(ItemSkullStackImpl.class)
                            .vanillaItem(ItemId.SKULL)
                            .build();
        }
        if (ItemTypes.SKULL_BANNER_PATTERN == null) {
            ItemTypes.SKULL_BANNER_PATTERN = AllayItemType
                            .builder(ItemSkullBannerPatternStackImpl.class)
                            .vanillaItem(ItemId.SKULL_BANNER_PATTERN)
                            .build();
        }
        if (ItemTypes.SKULL_POTTERY_SHERD == null) {
            ItemTypes.SKULL_POTTERY_SHERD = AllayItemType
                            .builder(ItemSkullPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.SKULL_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.SLIME == null) {
            ItemTypes.SLIME = AllayItemType
                            .builder(ItemSlimeStackImpl.class)
                            .vanillaItem(ItemId.SLIME)
                            .build();
        }
        if (ItemTypes.SLIME_BALL == null) {
            ItemTypes.SLIME_BALL = AllayItemType
                            .builder(ItemSlimeBallStackImpl.class)
                            .vanillaItem(ItemId.SLIME_BALL)
                            .build();
        }
        if (ItemTypes.SMALL_AMETHYST_BUD == null) {
            ItemTypes.SMALL_AMETHYST_BUD = AllayItemType
                            .builder(ItemSmallAmethystBudStackImpl.class)
                            .vanillaItem(ItemId.SMALL_AMETHYST_BUD)
                            .build();
        }
        if (ItemTypes.SMALL_DRIPLEAF_BLOCK == null) {
            ItemTypes.SMALL_DRIPLEAF_BLOCK = AllayItemType
                            .builder(ItemSmallDripleafBlockStackImpl.class)
                            .vanillaItem(ItemId.SMALL_DRIPLEAF_BLOCK)
                            .build();
        }
        if (ItemTypes.SMITHING_TABLE == null) {
            ItemTypes.SMITHING_TABLE = AllayItemType
                            .builder(ItemSmithingTableStackImpl.class)
                            .vanillaItem(ItemId.SMITHING_TABLE)
                            .build();
        }
        if (ItemTypes.SMOKER == null) {
            ItemTypes.SMOKER = AllayItemType
                            .builder(ItemSmokerStackImpl.class)
                            .vanillaItem(ItemId.SMOKER)
                            .build();
        }
        if (ItemTypes.SMOOTH_BASALT == null) {
            ItemTypes.SMOOTH_BASALT = AllayItemType
                            .builder(ItemSmoothBasaltStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_BASALT)
                            .build();
        }
        if (ItemTypes.SMOOTH_QUARTZ == null) {
            ItemTypes.SMOOTH_QUARTZ = AllayItemType
                            .builder(ItemSmoothQuartzStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_QUARTZ)
                            .build();
        }
        if (ItemTypes.SMOOTH_QUARTZ_DOUBLE_SLAB == null) {
            ItemTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSmoothQuartzDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_QUARTZ_SLAB == null) {
            ItemTypes.SMOOTH_QUARTZ_SLAB = AllayItemType
                            .builder(ItemSmoothQuartzSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_QUARTZ_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_QUARTZ_STAIRS == null) {
            ItemTypes.SMOOTH_QUARTZ_STAIRS = AllayItemType
                            .builder(ItemSmoothQuartzStairsStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_QUARTZ_STAIRS)
                            .build();
        }
        if (ItemTypes.SMOOTH_RED_SANDSTONE == null) {
            ItemTypes.SMOOTH_RED_SANDSTONE = AllayItemType
                            .builder(ItemSmoothRedSandstoneStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE)
                            .build();
        }
        if (ItemTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSmoothRedSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_RED_SANDSTONE_SLAB == null) {
            ItemTypes.SMOOTH_RED_SANDSTONE_SLAB = AllayItemType
                            .builder(ItemSmoothRedSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_RED_SANDSTONE_STAIRS == null) {
            ItemTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayItemType
                            .builder(ItemSmoothRedSandstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.SMOOTH_SANDSTONE == null) {
            ItemTypes.SMOOTH_SANDSTONE = AllayItemType
                            .builder(ItemSmoothSandstoneStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_SANDSTONE)
                            .build();
        }
        if (ItemTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB == null) {
            ItemTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSmoothSandstoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_SANDSTONE_SLAB == null) {
            ItemTypes.SMOOTH_SANDSTONE_SLAB = AllayItemType
                            .builder(ItemSmoothSandstoneSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_SANDSTONE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_SANDSTONE_STAIRS == null) {
            ItemTypes.SMOOTH_SANDSTONE_STAIRS = AllayItemType
                            .builder(ItemSmoothSandstoneStairsStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_SANDSTONE_STAIRS)
                            .build();
        }
        if (ItemTypes.SMOOTH_STONE == null) {
            ItemTypes.SMOOTH_STONE = AllayItemType
                            .builder(ItemSmoothStoneStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_STONE)
                            .build();
        }
        if (ItemTypes.SMOOTH_STONE_DOUBLE_SLAB == null) {
            ItemTypes.SMOOTH_STONE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSmoothStoneDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_STONE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SMOOTH_STONE_SLAB == null) {
            ItemTypes.SMOOTH_STONE_SLAB = AllayItemType
                            .builder(ItemSmoothStoneSlabStackImpl.class)
                            .vanillaItem(ItemId.SMOOTH_STONE_SLAB)
                            .build();
        }
        if (ItemTypes.SNIFFER_EGG == null) {
            ItemTypes.SNIFFER_EGG = AllayItemType
                            .builder(ItemSnifferEggStackImpl.class)
                            .vanillaItem(ItemId.SNIFFER_EGG)
                            .build();
        }
        if (ItemTypes.SNORT_POTTERY_SHERD == null) {
            ItemTypes.SNORT_POTTERY_SHERD = AllayItemType
                            .builder(ItemSnortPotterySherdStackImpl.class)
                            .vanillaItem(ItemId.SNORT_POTTERY_SHERD)
                            .build();
        }
        if (ItemTypes.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemSnoutArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.SNOW == null) {
            ItemTypes.SNOW = AllayItemType
                            .builder(ItemSnowStackImpl.class)
                            .vanillaItem(ItemId.SNOW)
                            .build();
        }
        if (ItemTypes.SNOW_LAYER == null) {
            ItemTypes.SNOW_LAYER = AllayItemType
                            .builder(ItemSnowLayerStackImpl.class)
                            .vanillaItem(ItemId.SNOW_LAYER)
                            .build();
        }
        if (ItemTypes.SNOWBALL == null) {
            ItemTypes.SNOWBALL = AllayItemType
                            .builder(ItemSnowballStackImpl.class)
                            .vanillaItem(ItemId.SNOWBALL)
                            .build();
        }
        if (ItemTypes.SOUL_CAMPFIRE == null) {
            ItemTypes.SOUL_CAMPFIRE = AllayItemType
                            .builder(ItemSoulCampfireStackImpl.class)
                            .vanillaItem(ItemId.SOUL_CAMPFIRE)
                            .build();
        }
        if (ItemTypes.SOUL_FIRE == null) {
            ItemTypes.SOUL_FIRE = AllayItemType
                            .builder(ItemSoulFireStackImpl.class)
                            .vanillaItem(ItemId.SOUL_FIRE)
                            .build();
        }
        if (ItemTypes.SOUL_LANTERN == null) {
            ItemTypes.SOUL_LANTERN = AllayItemType
                            .builder(ItemSoulLanternStackImpl.class)
                            .vanillaItem(ItemId.SOUL_LANTERN)
                            .build();
        }
        if (ItemTypes.SOUL_SAND == null) {
            ItemTypes.SOUL_SAND = AllayItemType
                            .builder(ItemSoulSandStackImpl.class)
                            .vanillaItem(ItemId.SOUL_SAND)
                            .build();
        }
        if (ItemTypes.SOUL_SOIL == null) {
            ItemTypes.SOUL_SOIL = AllayItemType
                            .builder(ItemSoulSoilStackImpl.class)
                            .vanillaItem(ItemId.SOUL_SOIL)
                            .build();
        }
        if (ItemTypes.SOUL_TORCH == null) {
            ItemTypes.SOUL_TORCH = AllayItemType
                            .builder(ItemSoulTorchStackImpl.class)
                            .vanillaItem(ItemId.SOUL_TORCH)
                            .build();
        }
        if (ItemTypes.SPARKLER == null) {
            ItemTypes.SPARKLER = AllayItemType
                            .builder(ItemSparklerStackImpl.class)
                            .vanillaItem(ItemId.SPARKLER)
                            .build();
        }
        if (ItemTypes.SPIDER_EYE == null) {
            ItemTypes.SPIDER_EYE = AllayItemType
                            .builder(ItemSpiderEyeStackImpl.class)
                            .vanillaItem(ItemId.SPIDER_EYE)
                            .build();
        }
        if (ItemTypes.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemSpireArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.SPLASH_POTION == null) {
            ItemTypes.SPLASH_POTION = AllayItemType
                            .builder(ItemSplashPotionStackImpl.class)
                            .vanillaItem(ItemId.SPLASH_POTION)
                            .build();
        }
        if (ItemTypes.SPONGE == null) {
            ItemTypes.SPONGE = AllayItemType
                            .builder(ItemSpongeStackImpl.class)
                            .vanillaItem(ItemId.SPONGE)
                            .build();
        }
        if (ItemTypes.SPORE_BLOSSOM == null) {
            ItemTypes.SPORE_BLOSSOM = AllayItemType
                            .builder(ItemSporeBlossomStackImpl.class)
                            .vanillaItem(ItemId.SPORE_BLOSSOM)
                            .build();
        }
        if (ItemTypes.SPRUCE_BOAT == null) {
            ItemTypes.SPRUCE_BOAT = AllayItemType
                            .builder(ItemSpruceBoatStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_BOAT)
                            .build();
        }
        if (ItemTypes.SPRUCE_BUTTON == null) {
            ItemTypes.SPRUCE_BUTTON = AllayItemType
                            .builder(ItemSpruceButtonStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_BUTTON)
                            .build();
        }
        if (ItemTypes.SPRUCE_CHEST_BOAT == null) {
            ItemTypes.SPRUCE_CHEST_BOAT = AllayItemType
                            .builder(ItemSpruceChestBoatStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_CHEST_BOAT)
                            .build();
        }
        if (ItemTypes.SPRUCE_DOOR == null) {
            ItemTypes.SPRUCE_DOOR = AllayItemType
                            .builder(ItemSpruceDoorStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_DOOR)
                            .build();
        }
        if (ItemTypes.SPRUCE_DOUBLE_SLAB == null) {
            ItemTypes.SPRUCE_DOUBLE_SLAB = AllayItemType
                            .builder(ItemSpruceDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.SPRUCE_FENCE == null) {
            ItemTypes.SPRUCE_FENCE = AllayItemType
                            .builder(ItemSpruceFenceStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_FENCE)
                            .build();
        }
        if (ItemTypes.SPRUCE_FENCE_GATE == null) {
            ItemTypes.SPRUCE_FENCE_GATE = AllayItemType
                            .builder(ItemSpruceFenceGateStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.SPRUCE_HANGING_SIGN == null) {
            ItemTypes.SPRUCE_HANGING_SIGN = AllayItemType
                            .builder(ItemSpruceHangingSignStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.SPRUCE_LEAVES == null) {
            ItemTypes.SPRUCE_LEAVES = AllayItemType
                            .builder(ItemSpruceLeavesStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_LEAVES)
                            .build();
        }
        if (ItemTypes.SPRUCE_LOG == null) {
            ItemTypes.SPRUCE_LOG = AllayItemType
                            .builder(ItemSpruceLogStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_LOG)
                            .build();
        }
        if (ItemTypes.SPRUCE_PLANKS == null) {
            ItemTypes.SPRUCE_PLANKS = AllayItemType
                            .builder(ItemSprucePlanksStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_PLANKS)
                            .build();
        }
        if (ItemTypes.SPRUCE_PRESSURE_PLATE == null) {
            ItemTypes.SPRUCE_PRESSURE_PLATE = AllayItemType
                            .builder(ItemSprucePressurePlateStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.SPRUCE_SAPLING == null) {
            ItemTypes.SPRUCE_SAPLING = AllayItemType
                            .builder(ItemSpruceSaplingStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_SAPLING)
                            .build();
        }
        if (ItemTypes.SPRUCE_SLAB == null) {
            ItemTypes.SPRUCE_SLAB = AllayItemType
                            .builder(ItemSpruceSlabStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_SLAB)
                            .build();
        }
        if (ItemTypes.SPRUCE_STAIRS == null) {
            ItemTypes.SPRUCE_STAIRS = AllayItemType
                            .builder(ItemSpruceStairsStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_STAIRS)
                            .build();
        }
        if (ItemTypes.SPRUCE_STANDING_SIGN == null) {
            ItemTypes.SPRUCE_STANDING_SIGN = AllayItemType
                            .builder(ItemSpruceStandingSignStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.SPRUCE_TRAPDOOR == null) {
            ItemTypes.SPRUCE_TRAPDOOR = AllayItemType
                            .builder(ItemSpruceTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.SPRUCE_WALL_SIGN == null) {
            ItemTypes.SPRUCE_WALL_SIGN = AllayItemType
                            .builder(ItemSpruceWallSignStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.SPRUCE_WOOD == null) {
            ItemTypes.SPRUCE_WOOD = AllayItemType
                            .builder(ItemSpruceWoodStackImpl.class)
                            .vanillaItem(ItemId.SPRUCE_WOOD)
                            .build();
        }
        if (ItemTypes.SPYGLASS == null) {
            ItemTypes.SPYGLASS = AllayItemType
                            .builder(ItemSpyglassStackImpl.class)
                            .vanillaItem(ItemId.SPYGLASS)
                            .build();
        }
        if (ItemTypes.STAINED_GLASS == null) {
            ItemTypes.STAINED_GLASS = AllayItemType
                            .builder(ItemStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.STAINED_GLASS_PANE == null) {
            ItemTypes.STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.STAINED_HARDENED_CLAY == null) {
            ItemTypes.STAINED_HARDENED_CLAY = AllayItemType
                            .builder(ItemStainedHardenedClayStackImpl.class)
                            .vanillaItem(ItemId.STAINED_HARDENED_CLAY)
                            .build();
        }
        if (ItemTypes.STANDING_BANNER == null) {
            ItemTypes.STANDING_BANNER = AllayItemType
                            .builder(ItemStandingBannerStackImpl.class)
                            .vanillaItem(ItemId.STANDING_BANNER)
                            .build();
        }
        if (ItemTypes.STANDING_SIGN == null) {
            ItemTypes.STANDING_SIGN = AllayItemType
                            .builder(ItemStandingSignStackImpl.class)
                            .vanillaItem(ItemId.STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.STICK == null) {
            ItemTypes.STICK = AllayItemType
                            .builder(ItemStickStackImpl.class)
                            .vanillaItem(ItemId.STICK)
                            .build();
        }
        if (ItemTypes.STICKY_PISTON == null) {
            ItemTypes.STICKY_PISTON = AllayItemType
                            .builder(ItemStickyPistonStackImpl.class)
                            .vanillaItem(ItemId.STICKY_PISTON)
                            .build();
        }
        if (ItemTypes.STICKY_PISTON_ARM_COLLISION == null) {
            ItemTypes.STICKY_PISTON_ARM_COLLISION = AllayItemType
                            .builder(ItemStickyPistonArmCollisionStackImpl.class)
                            .vanillaItem(ItemId.STICKY_PISTON_ARM_COLLISION)
                            .build();
        }
        if (ItemTypes.STONE == null) {
            ItemTypes.STONE = AllayItemType
                            .builder(ItemStoneStackImpl.class)
                            .vanillaItem(ItemId.STONE)
                            .build();
        }
        if (ItemTypes.STONE_BLOCK_SLAB == null) {
            ItemTypes.STONE_BLOCK_SLAB = AllayItemType
                            .builder(ItemStoneBlockSlabStackImpl.class)
                            .vanillaItem(ItemId.STONE_BLOCK_SLAB)
                            .build();
        }
        if (ItemTypes.STONE_BLOCK_SLAB2 == null) {
            ItemTypes.STONE_BLOCK_SLAB2 = AllayItemType
                            .builder(ItemStoneBlockSlab2StackImpl.class)
                            .vanillaItem(ItemId.STONE_BLOCK_SLAB2)
                            .build();
        }
        if (ItemTypes.STONE_BLOCK_SLAB3 == null) {
            ItemTypes.STONE_BLOCK_SLAB3 = AllayItemType
                            .builder(ItemStoneBlockSlab3StackImpl.class)
                            .vanillaItem(ItemId.STONE_BLOCK_SLAB3)
                            .build();
        }
        if (ItemTypes.STONE_BLOCK_SLAB4 == null) {
            ItemTypes.STONE_BLOCK_SLAB4 = AllayItemType
                            .builder(ItemStoneBlockSlab4StackImpl.class)
                            .vanillaItem(ItemId.STONE_BLOCK_SLAB4)
                            .build();
        }
        if (ItemTypes.STONE_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.STONE_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemStoneBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.STONE_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.STONE_BRICK_SLAB == null) {
            ItemTypes.STONE_BRICK_SLAB = AllayItemType
                            .builder(ItemStoneBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.STONE_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.STONE_BRICK_STAIRS == null) {
            ItemTypes.STONE_BRICK_STAIRS = AllayItemType
                            .builder(ItemStoneBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.STONE_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.STONE_BRICK_WALL == null) {
            ItemTypes.STONE_BRICK_WALL = AllayItemType
                            .builder(ItemStoneBrickWallStackImpl.class)
                            .vanillaItem(ItemId.STONE_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.STONE_BRICKS == null) {
            ItemTypes.STONE_BRICKS = AllayItemType
                            .builder(ItemStoneBricksStackImpl.class)
                            .vanillaItem(ItemId.STONE_BRICKS)
                            .build();
        }
        if (ItemTypes.STONE_BUTTON == null) {
            ItemTypes.STONE_BUTTON = AllayItemType
                            .builder(ItemStoneButtonStackImpl.class)
                            .vanillaItem(ItemId.STONE_BUTTON)
                            .build();
        }
        if (ItemTypes.STONE_PRESSURE_PLATE == null) {
            ItemTypes.STONE_PRESSURE_PLATE = AllayItemType
                            .builder(ItemStonePressurePlateStackImpl.class)
                            .vanillaItem(ItemId.STONE_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.STONE_STAIRS == null) {
            ItemTypes.STONE_STAIRS = AllayItemType
                            .builder(ItemStoneStairsStackImpl.class)
                            .vanillaItem(ItemId.STONE_STAIRS)
                            .build();
        }
        if (ItemTypes.STONEBRICK == null) {
            ItemTypes.STONEBRICK = AllayItemType
                            .builder(ItemStonebrickStackImpl.class)
                            .vanillaItem(ItemId.STONEBRICK)
                            .build();
        }
        if (ItemTypes.STONECUTTER == null) {
            ItemTypes.STONECUTTER = AllayItemType
                            .builder(ItemStonecutterStackImpl.class)
                            .vanillaItem(ItemId.STONECUTTER)
                            .build();
        }
        if (ItemTypes.STONECUTTER_BLOCK == null) {
            ItemTypes.STONECUTTER_BLOCK = AllayItemType
                            .builder(ItemStonecutterBlockStackImpl.class)
                            .vanillaItem(ItemId.STONECUTTER_BLOCK)
                            .build();
        }
        if (ItemTypes.STRING == null) {
            ItemTypes.STRING = AllayItemType
                            .builder(ItemStringStackImpl.class)
                            .vanillaItem(ItemId.STRING)
                            .build();
        }
        if (ItemTypes.STRIPPED_ACACIA_LOG == null) {
            ItemTypes.STRIPPED_ACACIA_LOG = AllayItemType
                            .builder(ItemStrippedAcaciaLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_ACACIA_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_ACACIA_WOOD == null) {
            ItemTypes.STRIPPED_ACACIA_WOOD = AllayItemType
                            .builder(ItemStrippedAcaciaWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_ACACIA_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_BAMBOO_BLOCK == null) {
            ItemTypes.STRIPPED_BAMBOO_BLOCK = AllayItemType
                            .builder(ItemStrippedBambooBlockStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_BAMBOO_BLOCK)
                            .build();
        }
        if (ItemTypes.STRIPPED_BIRCH_LOG == null) {
            ItemTypes.STRIPPED_BIRCH_LOG = AllayItemType
                            .builder(ItemStrippedBirchLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_BIRCH_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_BIRCH_WOOD == null) {
            ItemTypes.STRIPPED_BIRCH_WOOD = AllayItemType
                            .builder(ItemStrippedBirchWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_BIRCH_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_CHERRY_LOG == null) {
            ItemTypes.STRIPPED_CHERRY_LOG = AllayItemType
                            .builder(ItemStrippedCherryLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_CHERRY_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_CHERRY_WOOD == null) {
            ItemTypes.STRIPPED_CHERRY_WOOD = AllayItemType
                            .builder(ItemStrippedCherryWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_CHERRY_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_CRIMSON_HYPHAE == null) {
            ItemTypes.STRIPPED_CRIMSON_HYPHAE = AllayItemType
                            .builder(ItemStrippedCrimsonHyphaeStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_CRIMSON_HYPHAE)
                            .build();
        }
        if (ItemTypes.STRIPPED_CRIMSON_STEM == null) {
            ItemTypes.STRIPPED_CRIMSON_STEM = AllayItemType
                            .builder(ItemStrippedCrimsonStemStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_CRIMSON_STEM)
                            .build();
        }
        if (ItemTypes.STRIPPED_DARK_OAK_LOG == null) {
            ItemTypes.STRIPPED_DARK_OAK_LOG = AllayItemType
                            .builder(ItemStrippedDarkOakLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_DARK_OAK_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_DARK_OAK_WOOD == null) {
            ItemTypes.STRIPPED_DARK_OAK_WOOD = AllayItemType
                            .builder(ItemStrippedDarkOakWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_DARK_OAK_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_JUNGLE_LOG == null) {
            ItemTypes.STRIPPED_JUNGLE_LOG = AllayItemType
                            .builder(ItemStrippedJungleLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_JUNGLE_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_JUNGLE_WOOD == null) {
            ItemTypes.STRIPPED_JUNGLE_WOOD = AllayItemType
                            .builder(ItemStrippedJungleWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_JUNGLE_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_MANGROVE_LOG == null) {
            ItemTypes.STRIPPED_MANGROVE_LOG = AllayItemType
                            .builder(ItemStrippedMangroveLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_MANGROVE_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_MANGROVE_WOOD == null) {
            ItemTypes.STRIPPED_MANGROVE_WOOD = AllayItemType
                            .builder(ItemStrippedMangroveWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_MANGROVE_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_OAK_LOG == null) {
            ItemTypes.STRIPPED_OAK_LOG = AllayItemType
                            .builder(ItemStrippedOakLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_OAK_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_OAK_WOOD == null) {
            ItemTypes.STRIPPED_OAK_WOOD = AllayItemType
                            .builder(ItemStrippedOakWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_OAK_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_SPRUCE_LOG == null) {
            ItemTypes.STRIPPED_SPRUCE_LOG = AllayItemType
                            .builder(ItemStrippedSpruceLogStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_SPRUCE_LOG)
                            .build();
        }
        if (ItemTypes.STRIPPED_SPRUCE_WOOD == null) {
            ItemTypes.STRIPPED_SPRUCE_WOOD = AllayItemType
                            .builder(ItemStrippedSpruceWoodStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_SPRUCE_WOOD)
                            .build();
        }
        if (ItemTypes.STRIPPED_WARPED_HYPHAE == null) {
            ItemTypes.STRIPPED_WARPED_HYPHAE = AllayItemType
                            .builder(ItemStrippedWarpedHyphaeStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_WARPED_HYPHAE)
                            .build();
        }
        if (ItemTypes.STRIPPED_WARPED_STEM == null) {
            ItemTypes.STRIPPED_WARPED_STEM = AllayItemType
                            .builder(ItemStrippedWarpedStemStackImpl.class)
                            .vanillaItem(ItemId.STRIPPED_WARPED_STEM)
                            .build();
        }
        if (ItemTypes.STRUCTURE_BLOCK == null) {
            ItemTypes.STRUCTURE_BLOCK = AllayItemType
                            .builder(ItemStructureBlockStackImpl.class)
                            .vanillaItem(ItemId.STRUCTURE_BLOCK)
                            .build();
        }
        if (ItemTypes.STRUCTURE_VOID == null) {
            ItemTypes.STRUCTURE_VOID = AllayItemType
                            .builder(ItemStructureVoidStackImpl.class)
                            .vanillaItem(ItemId.STRUCTURE_VOID)
                            .build();
        }
        if (ItemTypes.SUGAR == null) {
            ItemTypes.SUGAR = AllayItemType
                            .builder(ItemSugarStackImpl.class)
                            .vanillaItem(ItemId.SUGAR)
                            .build();
        }
        if (ItemTypes.SUGAR_CANE == null) {
            ItemTypes.SUGAR_CANE = AllayItemType
                            .builder(ItemSugarCaneStackImpl.class)
                            .vanillaItem(ItemId.SUGAR_CANE)
                            .build();
        }
        if (ItemTypes.SUNFLOWER == null) {
            ItemTypes.SUNFLOWER = AllayItemType
                            .builder(ItemSunflowerStackImpl.class)
                            .vanillaItem(ItemId.SUNFLOWER)
                            .build();
        }
        if (ItemTypes.SUSPICIOUS_GRAVEL == null) {
            ItemTypes.SUSPICIOUS_GRAVEL = AllayItemType
                            .builder(ItemSuspiciousGravelStackImpl.class)
                            .vanillaItem(ItemId.SUSPICIOUS_GRAVEL)
                            .build();
        }
        if (ItemTypes.SUSPICIOUS_SAND == null) {
            ItemTypes.SUSPICIOUS_SAND = AllayItemType
                            .builder(ItemSuspiciousSandStackImpl.class)
                            .vanillaItem(ItemId.SUSPICIOUS_SAND)
                            .build();
        }
        if (ItemTypes.SUSPICIOUS_STEW == null) {
            ItemTypes.SUSPICIOUS_STEW = AllayItemType
                            .builder(ItemSuspiciousStewStackImpl.class)
                            .vanillaItem(ItemId.SUSPICIOUS_STEW)
                            .build();
        }
        if (ItemTypes.SWEET_BERRIES == null) {
            ItemTypes.SWEET_BERRIES = AllayItemType
                            .builder(ItemSweetBerriesStackImpl.class)
                            .vanillaItem(ItemId.SWEET_BERRIES)
                            .build();
        }
        if (ItemTypes.SWEET_BERRY_BUSH == null) {
            ItemTypes.SWEET_BERRY_BUSH = AllayItemType
                            .builder(ItemSweetBerryBushStackImpl.class)
                            .vanillaItem(ItemId.SWEET_BERRY_BUSH)
                            .build();
        }
        if (ItemTypes.TALL_GRASS == null) {
            ItemTypes.TALL_GRASS = AllayItemType
                            .builder(ItemTallGrassStackImpl.class)
                            .vanillaItem(ItemId.TALL_GRASS)
                            .build();
        }
        if (ItemTypes.TALLGRASS == null) {
            ItemTypes.TALLGRASS = AllayItemType
                            .builder(ItemTallgrass0StackImpl.class)
                            .vanillaItem(ItemId.TALLGRASS)
                            .build();
        }
        if (ItemTypes.TARGET == null) {
            ItemTypes.TARGET = AllayItemType
                            .builder(ItemTargetStackImpl.class)
                            .vanillaItem(ItemId.TARGET)
                            .build();
        }
        if (ItemTypes.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemTideArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.TINTED_GLASS == null) {
            ItemTypes.TINTED_GLASS = AllayItemType
                            .builder(ItemTintedGlassStackImpl.class)
                            .vanillaItem(ItemId.TINTED_GLASS)
                            .build();
        }
        if (ItemTypes.TNT == null) {
            ItemTypes.TNT = AllayItemType
                            .builder(ItemTntStackImpl.class)
                            .vanillaItem(ItemId.TNT)
                            .build();
        }
        if (ItemTypes.TNT_MINECART == null) {
            ItemTypes.TNT_MINECART = AllayItemType
                            .builder(ItemTntMinecartStackImpl.class)
                            .vanillaItem(ItemId.TNT_MINECART)
                            .build();
        }
        if (ItemTypes.TORCH == null) {
            ItemTypes.TORCH = AllayItemType
                            .builder(ItemTorchStackImpl.class)
                            .vanillaItem(ItemId.TORCH)
                            .build();
        }
        if (ItemTypes.TORCHFLOWER == null) {
            ItemTypes.TORCHFLOWER = AllayItemType
                            .builder(ItemTorchflowerStackImpl.class)
                            .vanillaItem(ItemId.TORCHFLOWER)
                            .build();
        }
        if (ItemTypes.TORCHFLOWER_CROP == null) {
            ItemTypes.TORCHFLOWER_CROP = AllayItemType
                            .builder(ItemTorchflowerCropStackImpl.class)
                            .vanillaItem(ItemId.TORCHFLOWER_CROP)
                            .build();
        }
        if (ItemTypes.TORCHFLOWER_SEEDS == null) {
            ItemTypes.TORCHFLOWER_SEEDS = AllayItemType
                            .builder(ItemTorchflowerSeedsStackImpl.class)
                            .vanillaItem(ItemId.TORCHFLOWER_SEEDS)
                            .build();
        }
        if (ItemTypes.TOTEM_OF_UNDYING == null) {
            ItemTypes.TOTEM_OF_UNDYING = AllayItemType
                            .builder(ItemTotemOfUndyingStackImpl.class)
                            .vanillaItem(ItemId.TOTEM_OF_UNDYING)
                            .build();
        }
        if (ItemTypes.TRAPDOOR == null) {
            ItemTypes.TRAPDOOR = AllayItemType
                            .builder(ItemTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.TRAPDOOR)
                            .build();
        }
        if (ItemTypes.TRAPPED_CHEST == null) {
            ItemTypes.TRAPPED_CHEST = AllayItemType
                            .builder(ItemTrappedChestStackImpl.class)
                            .vanillaItem(ItemId.TRAPPED_CHEST)
                            .build();
        }
        if (ItemTypes.TRIAL_KEY == null) {
            ItemTypes.TRIAL_KEY = AllayItemType
                            .builder(ItemTrialKeyStackImpl.class)
                            .vanillaItem(ItemId.TRIAL_KEY)
                            .build();
        }
        if (ItemTypes.TRIAL_SPAWNER == null) {
            ItemTypes.TRIAL_SPAWNER = AllayItemType
                            .builder(ItemTrialSpawnerStackImpl.class)
                            .vanillaItem(ItemId.TRIAL_SPAWNER)
                            .build();
        }
        if (ItemTypes.TRIDENT == null) {
            ItemTypes.TRIDENT = AllayItemType
                            .builder(ItemTridentStackImpl.class)
                            .vanillaItem(ItemId.TRIDENT)
                            .build();
        }
        if (ItemTypes.TRIP_WIRE == null) {
            ItemTypes.TRIP_WIRE = AllayItemType
                            .builder(ItemTripWireStackImpl.class)
                            .vanillaItem(ItemId.TRIP_WIRE)
                            .build();
        }
        if (ItemTypes.TRIPWIRE_HOOK == null) {
            ItemTypes.TRIPWIRE_HOOK = AllayItemType
                            .builder(ItemTripwireHookStackImpl.class)
                            .vanillaItem(ItemId.TRIPWIRE_HOOK)
                            .build();
        }
        if (ItemTypes.TROPICAL_FISH == null) {
            ItemTypes.TROPICAL_FISH = AllayItemType
                            .builder(ItemTropicalFishStackImpl.class)
                            .vanillaItem(ItemId.TROPICAL_FISH)
                            .build();
        }
        if (ItemTypes.TUBE_CORAL == null) {
            ItemTypes.TUBE_CORAL = AllayItemType
                            .builder(ItemTubeCoralStackImpl.class)
                            .vanillaItem(ItemId.TUBE_CORAL)
                            .build();
        }
        if (ItemTypes.TUBE_CORAL_BLOCK == null) {
            ItemTypes.TUBE_CORAL_BLOCK = AllayItemType
                            .builder(ItemTubeCoralBlockStackImpl.class)
                            .vanillaItem(ItemId.TUBE_CORAL_BLOCK)
                            .build();
        }
        if (ItemTypes.TUBE_CORAL_FAN == null) {
            ItemTypes.TUBE_CORAL_FAN = AllayItemType
                            .builder(ItemTubeCoralFanStackImpl.class)
                            .vanillaItem(ItemId.TUBE_CORAL_FAN)
                            .build();
        }
        if (ItemTypes.TUBE_CORAL_WALL_FAN == null) {
            ItemTypes.TUBE_CORAL_WALL_FAN = AllayItemType
                            .builder(ItemTubeCoralWallFanStackImpl.class)
                            .vanillaItem(ItemId.TUBE_CORAL_WALL_FAN)
                            .build();
        }
        if (ItemTypes.TUFF == null) {
            ItemTypes.TUFF = AllayItemType
                            .builder(ItemTuffStackImpl.class)
                            .vanillaItem(ItemId.TUFF)
                            .build();
        }
        if (ItemTypes.TUFF_BRICK_DOUBLE_SLAB == null) {
            ItemTypes.TUFF_BRICK_DOUBLE_SLAB = AllayItemType
                            .builder(ItemTuffBrickDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.TUFF_BRICK_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.TUFF_BRICK_SLAB == null) {
            ItemTypes.TUFF_BRICK_SLAB = AllayItemType
                            .builder(ItemTuffBrickSlabStackImpl.class)
                            .vanillaItem(ItemId.TUFF_BRICK_SLAB)
                            .build();
        }
        if (ItemTypes.TUFF_BRICK_STAIRS == null) {
            ItemTypes.TUFF_BRICK_STAIRS = AllayItemType
                            .builder(ItemTuffBrickStairsStackImpl.class)
                            .vanillaItem(ItemId.TUFF_BRICK_STAIRS)
                            .build();
        }
        if (ItemTypes.TUFF_BRICK_WALL == null) {
            ItemTypes.TUFF_BRICK_WALL = AllayItemType
                            .builder(ItemTuffBrickWallStackImpl.class)
                            .vanillaItem(ItemId.TUFF_BRICK_WALL)
                            .build();
        }
        if (ItemTypes.TUFF_BRICKS == null) {
            ItemTypes.TUFF_BRICKS = AllayItemType
                            .builder(ItemTuffBricksStackImpl.class)
                            .vanillaItem(ItemId.TUFF_BRICKS)
                            .build();
        }
        if (ItemTypes.TUFF_DOUBLE_SLAB == null) {
            ItemTypes.TUFF_DOUBLE_SLAB = AllayItemType
                            .builder(ItemTuffDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.TUFF_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.TUFF_SLAB == null) {
            ItemTypes.TUFF_SLAB = AllayItemType
                            .builder(ItemTuffSlabStackImpl.class)
                            .vanillaItem(ItemId.TUFF_SLAB)
                            .build();
        }
        if (ItemTypes.TUFF_STAIRS == null) {
            ItemTypes.TUFF_STAIRS = AllayItemType
                            .builder(ItemTuffStairsStackImpl.class)
                            .vanillaItem(ItemId.TUFF_STAIRS)
                            .build();
        }
        if (ItemTypes.TUFF_WALL == null) {
            ItemTypes.TUFF_WALL = AllayItemType
                            .builder(ItemTuffWallStackImpl.class)
                            .vanillaItem(ItemId.TUFF_WALL)
                            .build();
        }
        if (ItemTypes.TURTLE_EGG == null) {
            ItemTypes.TURTLE_EGG = AllayItemType
                            .builder(ItemTurtleEggStackImpl.class)
                            .vanillaItem(ItemId.TURTLE_EGG)
                            .build();
        }
        if (ItemTypes.TURTLE_SCUTE == null) {
            ItemTypes.TURTLE_SCUTE = AllayItemType
                            .builder(ItemTurtleScuteStackImpl.class)
                            .vanillaItem(ItemId.TURTLE_SCUTE)
                            .build();
        }
        if (ItemTypes.TWISTING_VINES == null) {
            ItemTypes.TWISTING_VINES = AllayItemType
                            .builder(ItemTwistingVinesStackImpl.class)
                            .vanillaItem(ItemId.TWISTING_VINES)
                            .build();
        }
        if (ItemTypes.UNDERWATER_TNT == null) {
            ItemTypes.UNDERWATER_TNT = AllayItemType
                            .builder(ItemUnderwaterTntStackImpl.class)
                            .vanillaItem(ItemId.UNDERWATER_TNT)
                            .build();
        }
        if (ItemTypes.UNDERWATER_TORCH == null) {
            ItemTypes.UNDERWATER_TORCH = AllayItemType
                            .builder(ItemUnderwaterTorchStackImpl.class)
                            .vanillaItem(ItemId.UNDERWATER_TORCH)
                            .build();
        }
        if (ItemTypes.UNKNOWN == null) {
            ItemTypes.UNKNOWN = AllayItemType
                            .builder(ItemUnknownStackImpl.class)
                            .vanillaItem(ItemId.UNKNOWN)
                            .build();
        }
        if (ItemTypes.UNLIT_REDSTONE_TORCH == null) {
            ItemTypes.UNLIT_REDSTONE_TORCH = AllayItemType
                            .builder(ItemUnlitRedstoneTorchStackImpl.class)
                            .vanillaItem(ItemId.UNLIT_REDSTONE_TORCH)
                            .build();
        }
        if (ItemTypes.UNPOWERED_COMPARATOR == null) {
            ItemTypes.UNPOWERED_COMPARATOR = AllayItemType
                            .builder(ItemUnpoweredComparatorStackImpl.class)
                            .vanillaItem(ItemId.UNPOWERED_COMPARATOR)
                            .build();
        }
        if (ItemTypes.UNPOWERED_REPEATER == null) {
            ItemTypes.UNPOWERED_REPEATER = AllayItemType
                            .builder(ItemUnpoweredRepeaterStackImpl.class)
                            .vanillaItem(ItemId.UNPOWERED_REPEATER)
                            .build();
        }
        if (ItemTypes.VAULT == null) {
            ItemTypes.VAULT = AllayItemType
                            .builder(ItemVaultStackImpl.class)
                            .vanillaItem(ItemId.VAULT)
                            .build();
        }
        if (ItemTypes.VERDANT_FROGLIGHT == null) {
            ItemTypes.VERDANT_FROGLIGHT = AllayItemType
                            .builder(ItemVerdantFroglightStackImpl.class)
                            .vanillaItem(ItemId.VERDANT_FROGLIGHT)
                            .build();
        }
        if (ItemTypes.VEX_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.VEX_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemVexArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.VEX_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.VINE == null) {
            ItemTypes.VINE = AllayItemType
                            .builder(ItemVineStackImpl.class)
                            .vanillaItem(ItemId.VINE)
                            .build();
        }
        if (ItemTypes.WALL_BANNER == null) {
            ItemTypes.WALL_BANNER = AllayItemType
                            .builder(ItemWallBannerStackImpl.class)
                            .vanillaItem(ItemId.WALL_BANNER)
                            .build();
        }
        if (ItemTypes.WALL_SIGN == null) {
            ItemTypes.WALL_SIGN = AllayItemType
                            .builder(ItemWallSignStackImpl.class)
                            .vanillaItem(ItemId.WALL_SIGN)
                            .build();
        }
        if (ItemTypes.WARD_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.WARD_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemWardArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.WARPED_BUTTON == null) {
            ItemTypes.WARPED_BUTTON = AllayItemType
                            .builder(ItemWarpedButtonStackImpl.class)
                            .vanillaItem(ItemId.WARPED_BUTTON)
                            .build();
        }
        if (ItemTypes.WARPED_DOOR == null) {
            ItemTypes.WARPED_DOOR = AllayItemType
                            .builder(ItemWarpedDoorStackImpl.class)
                            .vanillaItem(ItemId.WARPED_DOOR)
                            .build();
        }
        if (ItemTypes.WARPED_DOUBLE_SLAB == null) {
            ItemTypes.WARPED_DOUBLE_SLAB = AllayItemType
                            .builder(ItemWarpedDoubleSlabStackImpl.class)
                            .vanillaItem(ItemId.WARPED_DOUBLE_SLAB)
                            .build();
        }
        if (ItemTypes.WARPED_FENCE == null) {
            ItemTypes.WARPED_FENCE = AllayItemType
                            .builder(ItemWarpedFenceStackImpl.class)
                            .vanillaItem(ItemId.WARPED_FENCE)
                            .build();
        }
        if (ItemTypes.WARPED_FENCE_GATE == null) {
            ItemTypes.WARPED_FENCE_GATE = AllayItemType
                            .builder(ItemWarpedFenceGateStackImpl.class)
                            .vanillaItem(ItemId.WARPED_FENCE_GATE)
                            .build();
        }
        if (ItemTypes.WARPED_FUNGUS == null) {
            ItemTypes.WARPED_FUNGUS = AllayItemType
                            .builder(ItemWarpedFungusStackImpl.class)
                            .vanillaItem(ItemId.WARPED_FUNGUS)
                            .build();
        }
        if (ItemTypes.WARPED_FUNGUS_ON_A_STICK == null) {
            ItemTypes.WARPED_FUNGUS_ON_A_STICK = AllayItemType
                            .builder(ItemWarpedFungusOnAStickStackImpl.class)
                            .vanillaItem(ItemId.WARPED_FUNGUS_ON_A_STICK)
                            .build();
        }
        if (ItemTypes.WARPED_HANGING_SIGN == null) {
            ItemTypes.WARPED_HANGING_SIGN = AllayItemType
                            .builder(ItemWarpedHangingSignStackImpl.class)
                            .vanillaItem(ItemId.WARPED_HANGING_SIGN)
                            .build();
        }
        if (ItemTypes.WARPED_HYPHAE == null) {
            ItemTypes.WARPED_HYPHAE = AllayItemType
                            .builder(ItemWarpedHyphaeStackImpl.class)
                            .vanillaItem(ItemId.WARPED_HYPHAE)
                            .build();
        }
        if (ItemTypes.WARPED_NYLIUM == null) {
            ItemTypes.WARPED_NYLIUM = AllayItemType
                            .builder(ItemWarpedNyliumStackImpl.class)
                            .vanillaItem(ItemId.WARPED_NYLIUM)
                            .build();
        }
        if (ItemTypes.WARPED_PLANKS == null) {
            ItemTypes.WARPED_PLANKS = AllayItemType
                            .builder(ItemWarpedPlanksStackImpl.class)
                            .vanillaItem(ItemId.WARPED_PLANKS)
                            .build();
        }
        if (ItemTypes.WARPED_PRESSURE_PLATE == null) {
            ItemTypes.WARPED_PRESSURE_PLATE = AllayItemType
                            .builder(ItemWarpedPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.WARPED_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.WARPED_ROOTS == null) {
            ItemTypes.WARPED_ROOTS = AllayItemType
                            .builder(ItemWarpedRootsStackImpl.class)
                            .vanillaItem(ItemId.WARPED_ROOTS)
                            .build();
        }
        if (ItemTypes.WARPED_SLAB == null) {
            ItemTypes.WARPED_SLAB = AllayItemType
                            .builder(ItemWarpedSlabStackImpl.class)
                            .vanillaItem(ItemId.WARPED_SLAB)
                            .build();
        }
        if (ItemTypes.WARPED_STAIRS == null) {
            ItemTypes.WARPED_STAIRS = AllayItemType
                            .builder(ItemWarpedStairsStackImpl.class)
                            .vanillaItem(ItemId.WARPED_STAIRS)
                            .build();
        }
        if (ItemTypes.WARPED_STANDING_SIGN == null) {
            ItemTypes.WARPED_STANDING_SIGN = AllayItemType
                            .builder(ItemWarpedStandingSignStackImpl.class)
                            .vanillaItem(ItemId.WARPED_STANDING_SIGN)
                            .build();
        }
        if (ItemTypes.WARPED_STEM == null) {
            ItemTypes.WARPED_STEM = AllayItemType
                            .builder(ItemWarpedStemStackImpl.class)
                            .vanillaItem(ItemId.WARPED_STEM)
                            .build();
        }
        if (ItemTypes.WARPED_TRAPDOOR == null) {
            ItemTypes.WARPED_TRAPDOOR = AllayItemType
                            .builder(ItemWarpedTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WARPED_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WARPED_WALL_SIGN == null) {
            ItemTypes.WARPED_WALL_SIGN = AllayItemType
                            .builder(ItemWarpedWallSignStackImpl.class)
                            .vanillaItem(ItemId.WARPED_WALL_SIGN)
                            .build();
        }
        if (ItemTypes.WARPED_WART_BLOCK == null) {
            ItemTypes.WARPED_WART_BLOCK = AllayItemType
                            .builder(ItemWarpedWartBlockStackImpl.class)
                            .vanillaItem(ItemId.WARPED_WART_BLOCK)
                            .build();
        }
        if (ItemTypes.WATER == null) {
            ItemTypes.WATER = AllayItemType
                            .builder(ItemWaterStackImpl.class)
                            .vanillaItem(ItemId.WATER)
                            .build();
        }
        if (ItemTypes.WATERLILY == null) {
            ItemTypes.WATERLILY = AllayItemType
                            .builder(ItemWaterlilyStackImpl.class)
                            .vanillaItem(ItemId.WATERLILY)
                            .build();
        }
        if (ItemTypes.WAXED_CHISELED_COPPER == null) {
            ItemTypes.WAXED_CHISELED_COPPER = AllayItemType
                            .builder(ItemWaxedChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_COPPER == null) {
            ItemTypes.WAXED_COPPER = AllayItemType
                            .builder(ItemWaxedCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_COPPER_BULB == null) {
            ItemTypes.WAXED_COPPER_BULB = AllayItemType
                            .builder(ItemWaxedCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.WAXED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.WAXED_COPPER_DOOR == null) {
            ItemTypes.WAXED_COPPER_DOOR = AllayItemType
                            .builder(ItemWaxedCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.WAXED_COPPER_GRATE == null) {
            ItemTypes.WAXED_COPPER_GRATE = AllayItemType
                            .builder(ItemWaxedCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.WAXED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.WAXED_COPPER_TRAPDOOR == null) {
            ItemTypes.WAXED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemWaxedCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WAXED_CUT_COPPER == null) {
            ItemTypes.WAXED_CUT_COPPER = AllayItemType
                            .builder(ItemWaxedCutCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_CUT_COPPER_STAIRS == null) {
            ItemTypes.WAXED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemWaxedCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.WAXED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.WAXED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_CHISELED_COPPER == null) {
            ItemTypes.WAXED_EXPOSED_CHISELED_COPPER = AllayItemType
                            .builder(ItemWaxedExposedChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_COPPER == null) {
            ItemTypes.WAXED_EXPOSED_COPPER = AllayItemType
                            .builder(ItemWaxedExposedCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_COPPER_BULB == null) {
            ItemTypes.WAXED_EXPOSED_COPPER_BULB = AllayItemType
                            .builder(ItemWaxedExposedCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_COPPER_DOOR == null) {
            ItemTypes.WAXED_EXPOSED_COPPER_DOOR = AllayItemType
                            .builder(ItemWaxedExposedCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_COPPER_GRATE == null) {
            ItemTypes.WAXED_EXPOSED_COPPER_GRATE = AllayItemType
                            .builder(ItemWaxedExposedCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_COPPER_TRAPDOOR == null) {
            ItemTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemWaxedExposedCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER == null) {
            ItemTypes.WAXED_EXPOSED_CUT_COPPER = AllayItemType
                            .builder(ItemWaxedExposedCutCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedExposedCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS == null) {
            ItemTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemWaxedExposedCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedExposedDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_CHISELED_COPPER == null) {
            ItemTypes.WAXED_OXIDIZED_CHISELED_COPPER = AllayItemType
                            .builder(ItemWaxedOxidizedChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_COPPER == null) {
            ItemTypes.WAXED_OXIDIZED_COPPER = AllayItemType
                            .builder(ItemWaxedOxidizedCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_COPPER_BULB == null) {
            ItemTypes.WAXED_OXIDIZED_COPPER_BULB = AllayItemType
                            .builder(ItemWaxedOxidizedCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_COPPER_DOOR == null) {
            ItemTypes.WAXED_OXIDIZED_COPPER_DOOR = AllayItemType
                            .builder(ItemWaxedOxidizedCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_COPPER_GRATE == null) {
            ItemTypes.WAXED_OXIDIZED_COPPER_GRATE = AllayItemType
                            .builder(ItemWaxedOxidizedCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR == null) {
            ItemTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemWaxedOxidizedCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER == null) {
            ItemTypes.WAXED_OXIDIZED_CUT_COPPER = AllayItemType
                            .builder(ItemWaxedOxidizedCutCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedOxidizedCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS == null) {
            ItemTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemWaxedOxidizedCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedOxidizedDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_CHISELED_COPPER == null) {
            ItemTypes.WAXED_WEATHERED_CHISELED_COPPER = AllayItemType
                            .builder(ItemWaxedWeatheredChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_COPPER == null) {
            ItemTypes.WAXED_WEATHERED_COPPER = AllayItemType
                            .builder(ItemWaxedWeatheredCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_COPPER_BULB == null) {
            ItemTypes.WAXED_WEATHERED_COPPER_BULB = AllayItemType
                            .builder(ItemWaxedWeatheredCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_COPPER_DOOR == null) {
            ItemTypes.WAXED_WEATHERED_COPPER_DOOR = AllayItemType
                            .builder(ItemWaxedWeatheredCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_COPPER_GRATE == null) {
            ItemTypes.WAXED_WEATHERED_COPPER_GRATE = AllayItemType
                            .builder(ItemWaxedWeatheredCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_COPPER_TRAPDOOR == null) {
            ItemTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemWaxedWeatheredCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER == null) {
            ItemTypes.WAXED_WEATHERED_CUT_COPPER = AllayItemType
                            .builder(ItemWaxedWeatheredCutCopperStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedWeatheredCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS == null) {
            ItemTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemWaxedWeatheredCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWaxedWeatheredDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemWayfinderArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.WEATHERED_CHISELED_COPPER == null) {
            ItemTypes.WEATHERED_CHISELED_COPPER = AllayItemType
                            .builder(ItemWeatheredChiseledCopperStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_CHISELED_COPPER)
                            .build();
        }
        if (ItemTypes.WEATHERED_COPPER == null) {
            ItemTypes.WEATHERED_COPPER = AllayItemType
                            .builder(ItemWeatheredCopperStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_COPPER)
                            .build();
        }
        if (ItemTypes.WEATHERED_COPPER_BULB == null) {
            ItemTypes.WEATHERED_COPPER_BULB = AllayItemType
                            .builder(ItemWeatheredCopperBulbStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_COPPER_BULB)
                            .build();
        }
        if (ItemTypes.WEATHERED_COPPER_DOOR == null) {
            ItemTypes.WEATHERED_COPPER_DOOR = AllayItemType
                            .builder(ItemWeatheredCopperDoorStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_COPPER_DOOR)
                            .build();
        }
        if (ItemTypes.WEATHERED_COPPER_GRATE == null) {
            ItemTypes.WEATHERED_COPPER_GRATE = AllayItemType
                            .builder(ItemWeatheredCopperGrateStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_COPPER_GRATE)
                            .build();
        }
        if (ItemTypes.WEATHERED_COPPER_TRAPDOOR == null) {
            ItemTypes.WEATHERED_COPPER_TRAPDOOR = AllayItemType
                            .builder(ItemWeatheredCopperTrapdoorStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_COPPER_TRAPDOOR)
                            .build();
        }
        if (ItemTypes.WEATHERED_CUT_COPPER == null) {
            ItemTypes.WEATHERED_CUT_COPPER = AllayItemType
                            .builder(ItemWeatheredCutCopperStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_CUT_COPPER)
                            .build();
        }
        if (ItemTypes.WEATHERED_CUT_COPPER_SLAB == null) {
            ItemTypes.WEATHERED_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWeatheredCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WEATHERED_CUT_COPPER_STAIRS == null) {
            ItemTypes.WEATHERED_CUT_COPPER_STAIRS = AllayItemType
                            .builder(ItemWeatheredCutCopperStairsStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_CUT_COPPER_STAIRS)
                            .build();
        }
        if (ItemTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) {
            ItemTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayItemType
                            .builder(ItemWeatheredDoubleCutCopperSlabStackImpl.class)
                            .vanillaItem(ItemId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                            .build();
        }
        if (ItemTypes.WEB == null) {
            ItemTypes.WEB = AllayItemType
                            .builder(ItemWebStackImpl.class)
                            .vanillaItem(ItemId.WEB)
                            .build();
        }
        if (ItemTypes.WEEPING_VINES == null) {
            ItemTypes.WEEPING_VINES = AllayItemType
                            .builder(ItemWeepingVinesStackImpl.class)
                            .vanillaItem(ItemId.WEEPING_VINES)
                            .build();
        }
        if (ItemTypes.WET_SPONGE == null) {
            ItemTypes.WET_SPONGE = AllayItemType
                            .builder(ItemWetSpongeStackImpl.class)
                            .vanillaItem(ItemId.WET_SPONGE)
                            .build();
        }
        if (ItemTypes.WHEAT == null) {
            ItemTypes.WHEAT = AllayItemType
                            .builder(ItemWheatStackImpl.class)
                            .vanillaItem(ItemId.WHEAT)
                            .build();
        }
        if (ItemTypes.WHEAT_SEEDS == null) {
            ItemTypes.WHEAT_SEEDS = AllayItemType
                            .builder(ItemWheatSeedsStackImpl.class)
                            .vanillaItem(ItemId.WHEAT_SEEDS)
                            .build();
        }
        if (ItemTypes.WHITE_BUNDLE == null) {
            ItemTypes.WHITE_BUNDLE = AllayItemType
                            .builder(ItemWhiteBundleStackImpl.class)
                            .vanillaItem(ItemId.WHITE_BUNDLE)
                            .build();
        }
        if (ItemTypes.WHITE_CANDLE == null) {
            ItemTypes.WHITE_CANDLE = AllayItemType
                            .builder(ItemWhiteCandleStackImpl.class)
                            .vanillaItem(ItemId.WHITE_CANDLE)
                            .build();
        }
        if (ItemTypes.WHITE_CANDLE_CAKE == null) {
            ItemTypes.WHITE_CANDLE_CAKE = AllayItemType
                            .builder(ItemWhiteCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.WHITE_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.WHITE_CARPET == null) {
            ItemTypes.WHITE_CARPET = AllayItemType
                            .builder(ItemWhiteCarpetStackImpl.class)
                            .vanillaItem(ItemId.WHITE_CARPET)
                            .build();
        }
        if (ItemTypes.WHITE_CONCRETE == null) {
            ItemTypes.WHITE_CONCRETE = AllayItemType
                            .builder(ItemWhiteConcreteStackImpl.class)
                            .vanillaItem(ItemId.WHITE_CONCRETE)
                            .build();
        }
        if (ItemTypes.WHITE_CONCRETE_POWDER == null) {
            ItemTypes.WHITE_CONCRETE_POWDER = AllayItemType
                            .builder(ItemWhiteConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.WHITE_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.WHITE_DYE == null) {
            ItemTypes.WHITE_DYE = AllayItemType
                            .builder(ItemWhiteDyeStackImpl.class)
                            .vanillaItem(ItemId.WHITE_DYE)
                            .build();
        }
        if (ItemTypes.WHITE_GLAZED_TERRACOTTA == null) {
            ItemTypes.WHITE_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemWhiteGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.WHITE_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.WHITE_STAINED_GLASS == null) {
            ItemTypes.WHITE_STAINED_GLASS = AllayItemType
                            .builder(ItemWhiteStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.WHITE_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.WHITE_STAINED_GLASS_PANE == null) {
            ItemTypes.WHITE_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemWhiteStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.WHITE_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.WHITE_TERRACOTTA == null) {
            ItemTypes.WHITE_TERRACOTTA = AllayItemType
                            .builder(ItemWhiteTerracottaStackImpl.class)
                            .vanillaItem(ItemId.WHITE_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.WHITE_TULIP == null) {
            ItemTypes.WHITE_TULIP = AllayItemType
                            .builder(ItemWhiteTulipStackImpl.class)
                            .vanillaItem(ItemId.WHITE_TULIP)
                            .build();
        }
        if (ItemTypes.WHITE_WOOL == null) {
            ItemTypes.WHITE_WOOL = AllayItemType
                            .builder(ItemWhiteWoolStackImpl.class)
                            .vanillaItem(ItemId.WHITE_WOOL)
                            .build();
        }
        if (ItemTypes.WILD_ARMOR_TRIM_SMITHING_TEMPLATE == null) {
            ItemTypes.WILD_ARMOR_TRIM_SMITHING_TEMPLATE = AllayItemType
                            .builder(ItemWildArmorTrimSmithingTemplateStackImpl.class)
                            .vanillaItem(ItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)
                            .build();
        }
        if (ItemTypes.WIND_CHARGE == null) {
            ItemTypes.WIND_CHARGE = AllayItemType
                            .builder(ItemWindChargeStackImpl.class)
                            .vanillaItem(ItemId.WIND_CHARGE)
                            .build();
        }
        if (ItemTypes.WITHER_ROSE == null) {
            ItemTypes.WITHER_ROSE = AllayItemType
                            .builder(ItemWitherRoseStackImpl.class)
                            .vanillaItem(ItemId.WITHER_ROSE)
                            .build();
        }
        if (ItemTypes.WITHER_SKELETON_SKULL == null) {
            ItemTypes.WITHER_SKELETON_SKULL = AllayItemType
                            .builder(ItemWitherSkeletonSkullStackImpl.class)
                            .vanillaItem(ItemId.WITHER_SKELETON_SKULL)
                            .build();
        }
        if (ItemTypes.WOLF_ARMOR == null) {
            ItemTypes.WOLF_ARMOR = AllayItemType
                            .builder(ItemWolfArmorStackImpl.class)
                            .vanillaItem(ItemId.WOLF_ARMOR)
                            .build();
        }
        if (ItemTypes.WOOD == null) {
            ItemTypes.WOOD = AllayItemType
                            .builder(ItemWoodStackImpl.class)
                            .vanillaItem(ItemId.WOOD)
                            .build();
        }
        if (ItemTypes.WOODEN_BUTTON == null) {
            ItemTypes.WOODEN_BUTTON = AllayItemType
                            .builder(ItemWoodenButtonStackImpl.class)
                            .vanillaItem(ItemId.WOODEN_BUTTON)
                            .build();
        }
        if (ItemTypes.WOODEN_DOOR == null) {
            ItemTypes.WOODEN_DOOR = AllayItemType
                            .builder(ItemWoodenDoorStackImpl.class)
                            .vanillaItem(ItemId.WOODEN_DOOR)
                            .build();
        }
        if (ItemTypes.WOODEN_PRESSURE_PLATE == null) {
            ItemTypes.WOODEN_PRESSURE_PLATE = AllayItemType
                            .builder(ItemWoodenPressurePlateStackImpl.class)
                            .vanillaItem(ItemId.WOODEN_PRESSURE_PLATE)
                            .build();
        }
        if (ItemTypes.WOODEN_SLAB == null) {
            ItemTypes.WOODEN_SLAB = AllayItemType
                            .builder(ItemWoodenSlabStackImpl.class)
                            .vanillaItem(ItemId.WOODEN_SLAB)
                            .build();
        }
        if (ItemTypes.WOOL == null) {
            ItemTypes.WOOL = AllayItemType
                            .builder(ItemWoolStackImpl.class)
                            .vanillaItem(ItemId.WOOL)
                            .build();
        }
        if (ItemTypes.WRITABLE_BOOK == null) {
            ItemTypes.WRITABLE_BOOK = AllayItemType
                            .builder(ItemWritableBookStackImpl.class)
                            .vanillaItem(ItemId.WRITABLE_BOOK)
                            .build();
        }
        if (ItemTypes.WRITTEN_BOOK == null) {
            ItemTypes.WRITTEN_BOOK = AllayItemType
                            .builder(ItemWrittenBookStackImpl.class)
                            .vanillaItem(ItemId.WRITTEN_BOOK)
                            .build();
        }
        if (ItemTypes.YELLOW_BUNDLE == null) {
            ItemTypes.YELLOW_BUNDLE = AllayItemType
                            .builder(ItemYellowBundleStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_BUNDLE)
                            .build();
        }
        if (ItemTypes.YELLOW_CANDLE == null) {
            ItemTypes.YELLOW_CANDLE = AllayItemType
                            .builder(ItemYellowCandleStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_CANDLE)
                            .build();
        }
        if (ItemTypes.YELLOW_CANDLE_CAKE == null) {
            ItemTypes.YELLOW_CANDLE_CAKE = AllayItemType
                            .builder(ItemYellowCandleCakeStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_CANDLE_CAKE)
                            .build();
        }
        if (ItemTypes.YELLOW_CARPET == null) {
            ItemTypes.YELLOW_CARPET = AllayItemType
                            .builder(ItemYellowCarpetStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_CARPET)
                            .build();
        }
        if (ItemTypes.YELLOW_CONCRETE == null) {
            ItemTypes.YELLOW_CONCRETE = AllayItemType
                            .builder(ItemYellowConcreteStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_CONCRETE)
                            .build();
        }
        if (ItemTypes.YELLOW_CONCRETE_POWDER == null) {
            ItemTypes.YELLOW_CONCRETE_POWDER = AllayItemType
                            .builder(ItemYellowConcretePowderStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_CONCRETE_POWDER)
                            .build();
        }
        if (ItemTypes.YELLOW_DYE == null) {
            ItemTypes.YELLOW_DYE = AllayItemType
                            .builder(ItemYellowDyeStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_DYE)
                            .build();
        }
        if (ItemTypes.YELLOW_GLAZED_TERRACOTTA == null) {
            ItemTypes.YELLOW_GLAZED_TERRACOTTA = AllayItemType
                            .builder(ItemYellowGlazedTerracottaStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_GLAZED_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.YELLOW_STAINED_GLASS == null) {
            ItemTypes.YELLOW_STAINED_GLASS = AllayItemType
                            .builder(ItemYellowStainedGlassStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_STAINED_GLASS)
                            .build();
        }
        if (ItemTypes.YELLOW_STAINED_GLASS_PANE == null) {
            ItemTypes.YELLOW_STAINED_GLASS_PANE = AllayItemType
                            .builder(ItemYellowStainedGlassPaneStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_STAINED_GLASS_PANE)
                            .build();
        }
        if (ItemTypes.YELLOW_TERRACOTTA == null) {
            ItemTypes.YELLOW_TERRACOTTA = AllayItemType
                            .builder(ItemYellowTerracottaStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_TERRACOTTA)
                            .build();
        }
        if (ItemTypes.YELLOW_WOOL == null) {
            ItemTypes.YELLOW_WOOL = AllayItemType
                            .builder(ItemYellowWoolStackImpl.class)
                            .vanillaItem(ItemId.YELLOW_WOOL)
                            .build();
        }
        if (ItemTypes.ZOMBIE_HEAD == null) {
            ItemTypes.ZOMBIE_HEAD = AllayItemType
                            .builder(ItemZombieHeadStackImpl.class)
                            .vanillaItem(ItemId.ZOMBIE_HEAD)
                            .build();
        }
    }
}
