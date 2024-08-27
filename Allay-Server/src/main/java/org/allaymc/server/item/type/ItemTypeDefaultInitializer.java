package org.allaymc.server.item.type;

import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.interfaces.anvil.ItemAnvilStack;
import org.allaymc.api.item.interfaces.anvil.ItemChippedAnvilStack;
import org.allaymc.api.item.interfaces.anvil.ItemDamagedAnvilStack;
import org.allaymc.api.item.interfaces.anvil.ItemDeprecatedAnvilStack;
import org.allaymc.api.item.interfaces.axe.*;
import org.allaymc.api.item.interfaces.boat.*;
import org.allaymc.api.item.interfaces.boots.*;
import org.allaymc.api.item.interfaces.bricks.*;
import org.allaymc.api.item.interfaces.bucket.*;
import org.allaymc.api.item.interfaces.button.*;
import org.allaymc.api.item.interfaces.candle.*;
import org.allaymc.api.item.interfaces.candlecake.*;
import org.allaymc.api.item.interfaces.carpet.*;
import org.allaymc.api.item.interfaces.chestplate.*;
import org.allaymc.api.item.interfaces.concrete.*;
import org.allaymc.api.item.interfaces.concretepowder.*;
import org.allaymc.api.item.interfaces.copper.*;
import org.allaymc.api.item.interfaces.coral.*;
import org.allaymc.api.item.interfaces.coralblock.*;
import org.allaymc.api.item.interfaces.coralfan.*;
import org.allaymc.api.item.interfaces.coralwallfan.*;
import org.allaymc.api.item.interfaces.dirt.ItemCoarseDirtStack;
import org.allaymc.api.item.interfaces.dirt.ItemDirtStack;
import org.allaymc.api.item.interfaces.door.*;
import org.allaymc.api.item.interfaces.egg.*;
import org.allaymc.api.item.interfaces.element.*;
import org.allaymc.api.item.interfaces.fence.*;
import org.allaymc.api.item.interfaces.fencegate.*;
import org.allaymc.api.item.interfaces.glass.ItemGlassStack;
import org.allaymc.api.item.interfaces.glass.ItemHardGlassStack;
import org.allaymc.api.item.interfaces.glass.ItemTintedGlassStack;
import org.allaymc.api.item.interfaces.glasspane.ItemGlassPaneStack;
import org.allaymc.api.item.interfaces.glasspane.ItemHardGlassPaneStack;
import org.allaymc.api.item.interfaces.hangingsign.*;
import org.allaymc.api.item.interfaces.helmet.*;
import org.allaymc.api.item.interfaces.hoe.*;
import org.allaymc.api.item.interfaces.leaves.*;
import org.allaymc.api.item.interfaces.leggings.*;
import org.allaymc.api.item.interfaces.lightblock.*;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingLavaStack;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingWaterStack;
import org.allaymc.api.item.interfaces.liquid.ItemLavaStack;
import org.allaymc.api.item.interfaces.liquid.ItemWaterStack;
import org.allaymc.api.item.interfaces.log.*;
import org.allaymc.api.item.interfaces.minecart.*;
import org.allaymc.api.item.interfaces.musicdisc.*;
import org.allaymc.api.item.interfaces.pickaxe.*;
import org.allaymc.api.item.interfaces.piston.ItemPistonArmCollisionStack;
import org.allaymc.api.item.interfaces.piston.ItemPistonStack;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonArmCollisionStack;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonStack;
import org.allaymc.api.item.interfaces.planks.*;
import org.allaymc.api.item.interfaces.sand.ItemRedSandStack;
import org.allaymc.api.item.interfaces.sand.ItemSandStack;
import org.allaymc.api.item.interfaces.sand.ItemSoulSandStack;
import org.allaymc.api.item.interfaces.sand.ItemSuspiciousSandStack;
import org.allaymc.api.item.interfaces.sandstone.*;
import org.allaymc.api.item.interfaces.sapling.*;
import org.allaymc.api.item.interfaces.shovel.*;
import org.allaymc.api.item.interfaces.shulkerbox.*;
import org.allaymc.api.item.interfaces.sign.*;
import org.allaymc.api.item.interfaces.slab.*;
import org.allaymc.api.item.interfaces.stainedglass.*;
import org.allaymc.api.item.interfaces.stainedglasspane.*;
import org.allaymc.api.item.interfaces.stairs.*;
import org.allaymc.api.item.interfaces.standingsign.*;
import org.allaymc.api.item.interfaces.sword.*;
import org.allaymc.api.item.interfaces.terracotta.*;
import org.allaymc.api.item.interfaces.trapdoor.*;
import org.allaymc.api.item.interfaces.wall.*;
import org.allaymc.api.item.interfaces.wallsign.*;
import org.allaymc.api.item.interfaces.wood.*;
import org.allaymc.api.item.interfaces.wool.*;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class ItemTypeDefaultInitializer {
    public static void initAcaciaBoat() {
        if (ItemTypes.ACACIA_BOAT != null) return;
        ItemTypes.ACACIA_BOAT = ItemTypeBuilder
                .builder(ItemAcaciaBoatStack.class)
                .vanillaItem(ItemId.ACACIA_BOAT)
                .build();
    }

    public static void initAcaciaButton() {
        if (ItemTypes.ACACIA_BUTTON != null) return;
        ItemTypes.ACACIA_BUTTON = ItemTypeBuilder
                .builder(ItemAcaciaButtonStack.class)
                .vanillaItem(ItemId.ACACIA_BUTTON)
                .build();
    }

    public static void initAcaciaChestBoat() {
        if (ItemTypes.ACACIA_CHEST_BOAT != null) return;
        ItemTypes.ACACIA_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemAcaciaChestBoatStack.class)
                .vanillaItem(ItemId.ACACIA_CHEST_BOAT)
                .build();
    }

    public static void initAcaciaDoor() {
        if (ItemTypes.ACACIA_DOOR != null) return;
        ItemTypes.ACACIA_DOOR = ItemTypeBuilder
                .builder(ItemAcaciaDoorStack.class)
                .vanillaItem(ItemId.ACACIA_DOOR)
                .build();
    }

    public static void initAcaciaDoubleSlab() {
        if (ItemTypes.ACACIA_DOUBLE_SLAB != null) return;
        ItemTypes.ACACIA_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemAcaciaDoubleSlabStack.class)
                .vanillaItem(ItemId.ACACIA_DOUBLE_SLAB)
                .build();
    }

    public static void initAcaciaFence() {
        if (ItemTypes.ACACIA_FENCE != null) return;
        ItemTypes.ACACIA_FENCE = ItemTypeBuilder
                .builder(ItemAcaciaFenceStack.class)
                .vanillaItem(ItemId.ACACIA_FENCE)
                .build();
    }

    public static void initAcaciaFenceGate() {
        if (ItemTypes.ACACIA_FENCE_GATE != null) return;
        ItemTypes.ACACIA_FENCE_GATE = ItemTypeBuilder
                .builder(ItemAcaciaFenceGateStack.class)
                .vanillaItem(ItemId.ACACIA_FENCE_GATE)
                .build();
    }

    public static void initAcaciaHangingSign() {
        if (ItemTypes.ACACIA_HANGING_SIGN != null) return;
        ItemTypes.ACACIA_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemAcaciaHangingSignStack.class)
                .vanillaItem(ItemId.ACACIA_HANGING_SIGN)
                .build();
    }

    public static void initAcaciaLeaves() {
        if (ItemTypes.ACACIA_LEAVES != null) return;
        ItemTypes.ACACIA_LEAVES = ItemTypeBuilder
                .builder(ItemAcaciaLeavesStack.class)
                .vanillaItem(ItemId.ACACIA_LEAVES)
                .build();
    }

    public static void initAcaciaLog() {
        if (ItemTypes.ACACIA_LOG != null) return;
        ItemTypes.ACACIA_LOG = ItemTypeBuilder
                .builder(ItemAcaciaLogStack.class)
                .vanillaItem(ItemId.ACACIA_LOG)
                .build();
    }

    public static void initAcaciaPlanks() {
        if (ItemTypes.ACACIA_PLANKS != null) return;
        ItemTypes.ACACIA_PLANKS = ItemTypeBuilder
                .builder(ItemAcaciaPlanksStack.class)
                .vanillaItem(ItemId.ACACIA_PLANKS)
                .build();
    }

    public static void initAcaciaPressurePlate() {
        if (ItemTypes.ACACIA_PRESSURE_PLATE != null) return;
        ItemTypes.ACACIA_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemAcaciaPressurePlateStack.class)
                .vanillaItem(ItemId.ACACIA_PRESSURE_PLATE)
                .build();
    }

    public static void initAcaciaSapling() {
        if (ItemTypes.ACACIA_SAPLING != null) return;
        ItemTypes.ACACIA_SAPLING = ItemTypeBuilder
                .builder(ItemAcaciaSaplingStack.class)
                .vanillaItem(ItemId.ACACIA_SAPLING)
                .build();
    }

    public static void initAcaciaSign() {
        if (ItemTypes.ACACIA_SIGN != null) return;
        ItemTypes.ACACIA_SIGN = ItemTypeBuilder
                .builder(ItemAcaciaSignStack.class)
                .vanillaItem(ItemId.ACACIA_SIGN)
                .build();
    }

    public static void initAcaciaSlab() {
        if (ItemTypes.ACACIA_SLAB != null) return;
        ItemTypes.ACACIA_SLAB = ItemTypeBuilder
                .builder(ItemAcaciaSlabStack.class)
                .vanillaItem(ItemId.ACACIA_SLAB)
                .build();
    }

    public static void initAcaciaStairs() {
        if (ItemTypes.ACACIA_STAIRS != null) return;
        ItemTypes.ACACIA_STAIRS = ItemTypeBuilder
                .builder(ItemAcaciaStairsStack.class)
                .vanillaItem(ItemId.ACACIA_STAIRS)
                .build();
    }

    public static void initAcaciaStandingSign() {
        if (ItemTypes.ACACIA_STANDING_SIGN != null) return;
        ItemTypes.ACACIA_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemAcaciaStandingSignStack.class)
                .vanillaItem(ItemId.ACACIA_STANDING_SIGN)
                .build();
    }

    public static void initAcaciaTrapdoor() {
        if (ItemTypes.ACACIA_TRAPDOOR != null) return;
        ItemTypes.ACACIA_TRAPDOOR = ItemTypeBuilder
                .builder(ItemAcaciaTrapdoorStack.class)
                .vanillaItem(ItemId.ACACIA_TRAPDOOR)
                .build();
    }

    public static void initAcaciaWallSign() {
        if (ItemTypes.ACACIA_WALL_SIGN != null) return;
        ItemTypes.ACACIA_WALL_SIGN = ItemTypeBuilder
                .builder(ItemAcaciaWallSignStack.class)
                .vanillaItem(ItemId.ACACIA_WALL_SIGN)
                .build();
    }

    public static void initAcaciaWood() {
        if (ItemTypes.ACACIA_WOOD != null) return;
        ItemTypes.ACACIA_WOOD = ItemTypeBuilder
                .builder(ItemAcaciaWoodStack.class)
                .vanillaItem(ItemId.ACACIA_WOOD)
                .build();
    }

    public static void initActivatorRail() {
        if (ItemTypes.ACTIVATOR_RAIL != null) return;
        ItemTypes.ACTIVATOR_RAIL = ItemTypeBuilder
                .builder(ItemActivatorRailStack.class)
                .vanillaItem(ItemId.ACTIVATOR_RAIL)
                .build();
    }

    public static void initAgentSpawnEgg() {
        if (ItemTypes.AGENT_SPAWN_EGG != null) return;
        ItemTypes.AGENT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAgentSpawnEggStack.class)
                .vanillaItem(ItemId.AGENT_SPAWN_EGG)
                .build();
    }

    public static void initAir() {
        if (ItemTypes.AIR != null) return;
        ItemTypes.AIR = ItemTypeBuilder
                .builder(ItemAirStack.class)
                .vanillaItem(ItemId.AIR)
                .build();
    }

    public static void initAllaySpawnEgg() {
        if (ItemTypes.ALLAY_SPAWN_EGG != null) return;
        ItemTypes.ALLAY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAllaySpawnEggStack.class)
                .vanillaItem(ItemId.ALLAY_SPAWN_EGG)
                .build();
    }

    public static void initAllium() {
        if (ItemTypes.ALLIUM != null) return;
        ItemTypes.ALLIUM = ItemTypeBuilder
                .builder(ItemAlliumStack.class)
                .vanillaItem(ItemId.ALLIUM)
                .build();
    }

    public static void initAllow() {
        if (ItemTypes.ALLOW != null) return;
        ItemTypes.ALLOW = ItemTypeBuilder
                .builder(ItemAllowStack.class)
                .vanillaItem(ItemId.ALLOW)
                .build();
    }

    public static void initAmethystBlock() {
        if (ItemTypes.AMETHYST_BLOCK != null) return;
        ItemTypes.AMETHYST_BLOCK = ItemTypeBuilder
                .builder(ItemAmethystBlockStack.class)
                .vanillaItem(ItemId.AMETHYST_BLOCK)
                .build();
    }

    public static void initAmethystCluster() {
        if (ItemTypes.AMETHYST_CLUSTER != null) return;
        ItemTypes.AMETHYST_CLUSTER = ItemTypeBuilder
                .builder(ItemAmethystClusterStack.class)
                .vanillaItem(ItemId.AMETHYST_CLUSTER)
                .build();
    }

    public static void initAmethystShard() {
        if (ItemTypes.AMETHYST_SHARD != null) return;
        ItemTypes.AMETHYST_SHARD = ItemTypeBuilder
                .builder(ItemAmethystShardStack.class)
                .vanillaItem(ItemId.AMETHYST_SHARD)
                .build();
    }

    public static void initAncientDebris() {
        if (ItemTypes.ANCIENT_DEBRIS != null) return;
        ItemTypes.ANCIENT_DEBRIS = ItemTypeBuilder
                .builder(ItemAncientDebrisStack.class)
                .vanillaItem(ItemId.ANCIENT_DEBRIS)
                .build();
    }

    public static void initAndesite() {
        if (ItemTypes.ANDESITE != null) return;
        ItemTypes.ANDESITE = ItemTypeBuilder
                .builder(ItemAndesiteStack.class)
                .vanillaItem(ItemId.ANDESITE)
                .build();
    }

    public static void initAndesiteDoubleSlab() {
        if (ItemTypes.ANDESITE_DOUBLE_SLAB != null) return;
        ItemTypes.ANDESITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemAndesiteDoubleSlabStack.class)
                .vanillaItem(ItemId.ANDESITE_DOUBLE_SLAB)
                .build();
    }

    public static void initAndesiteSlab() {
        if (ItemTypes.ANDESITE_SLAB != null) return;
        ItemTypes.ANDESITE_SLAB = ItemTypeBuilder
                .builder(ItemAndesiteSlabStack.class)
                .vanillaItem(ItemId.ANDESITE_SLAB)
                .build();
    }

    public static void initAndesiteStairs() {
        if (ItemTypes.ANDESITE_STAIRS != null) return;
        ItemTypes.ANDESITE_STAIRS = ItemTypeBuilder
                .builder(ItemAndesiteStairsStack.class)
                .vanillaItem(ItemId.ANDESITE_STAIRS)
                .build();
    }

    public static void initAnglerPotterySherd() {
        if (ItemTypes.ANGLER_POTTERY_SHERD != null) return;
        ItemTypes.ANGLER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemAnglerPotterySherdStack.class)
                .vanillaItem(ItemId.ANGLER_POTTERY_SHERD)
                .build();
    }

    public static void initAnvil() {
        if (ItemTypes.ANVIL != null) return;
        ItemTypes.ANVIL = ItemTypeBuilder
                .builder(ItemAnvilStack.class)
                .vanillaItem(ItemId.ANVIL)
                .build();
    }

    public static void initApple() {
        if (ItemTypes.APPLE != null) return;
        ItemTypes.APPLE = ItemTypeBuilder
                .builder(ItemAppleStack.class)
                .vanillaItem(ItemId.APPLE)
                .build();
    }

    public static void initArcherPotterySherd() {
        if (ItemTypes.ARCHER_POTTERY_SHERD != null) return;
        ItemTypes.ARCHER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemArcherPotterySherdStack.class)
                .vanillaItem(ItemId.ARCHER_POTTERY_SHERD)
                .build();
    }

    public static void initArmadilloScute() {
        if (ItemTypes.ARMADILLO_SCUTE != null) return;
        ItemTypes.ARMADILLO_SCUTE = ItemTypeBuilder
                .builder(ItemArmadilloScuteStack.class)
                .vanillaItem(ItemId.ARMADILLO_SCUTE)
                .build();
    }

    public static void initArmadilloSpawnEgg() {
        if (ItemTypes.ARMADILLO_SPAWN_EGG != null) return;
        ItemTypes.ARMADILLO_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemArmadilloSpawnEggStack.class)
                .vanillaItem(ItemId.ARMADILLO_SPAWN_EGG)
                .build();
    }

    public static void initArmorStand() {
        if (ItemTypes.ARMOR_STAND != null) return;
        ItemTypes.ARMOR_STAND = ItemTypeBuilder
                .builder(ItemArmorStandStack.class)
                .vanillaItem(ItemId.ARMOR_STAND)
                .build();
    }

    public static void initArmsUpPotterySherd() {
        if (ItemTypes.ARMS_UP_POTTERY_SHERD != null) return;
        ItemTypes.ARMS_UP_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemArmsUpPotterySherdStack.class)
                .vanillaItem(ItemId.ARMS_UP_POTTERY_SHERD)
                .build();
    }

    public static void initArrow() {
        if (ItemTypes.ARROW != null) return;
        ItemTypes.ARROW = ItemTypeBuilder
                .builder(ItemArrowStack.class)
                .vanillaItem(ItemId.ARROW)
                .build();
    }

    public static void initAxolotlBucket() {
        if (ItemTypes.AXOLOTL_BUCKET != null) return;
        ItemTypes.AXOLOTL_BUCKET = ItemTypeBuilder
                .builder(ItemAxolotlBucketStack.class)
                .vanillaItem(ItemId.AXOLOTL_BUCKET)
                .build();
    }

    public static void initAxolotlSpawnEgg() {
        if (ItemTypes.AXOLOTL_SPAWN_EGG != null) return;
        ItemTypes.AXOLOTL_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAxolotlSpawnEggStack.class)
                .vanillaItem(ItemId.AXOLOTL_SPAWN_EGG)
                .build();
    }

    public static void initAzalea() {
        if (ItemTypes.AZALEA != null) return;
        ItemTypes.AZALEA = ItemTypeBuilder
                .builder(ItemAzaleaStack.class)
                .vanillaItem(ItemId.AZALEA)
                .build();
    }

    public static void initAzaleaLeaves() {
        if (ItemTypes.AZALEA_LEAVES != null) return;
        ItemTypes.AZALEA_LEAVES = ItemTypeBuilder
                .builder(ItemAzaleaLeavesStack.class)
                .vanillaItem(ItemId.AZALEA_LEAVES)
                .build();
    }

    public static void initAzaleaLeavesFlowered() {
        if (ItemTypes.AZALEA_LEAVES_FLOWERED != null) return;
        ItemTypes.AZALEA_LEAVES_FLOWERED = ItemTypeBuilder
                .builder(ItemAzaleaLeavesFloweredStack.class)
                .vanillaItem(ItemId.AZALEA_LEAVES_FLOWERED)
                .build();
    }

    public static void initAzureBluet() {
        if (ItemTypes.AZURE_BLUET != null) return;
        ItemTypes.AZURE_BLUET = ItemTypeBuilder
                .builder(ItemAzureBluetStack.class)
                .vanillaItem(ItemId.AZURE_BLUET)
                .build();
    }

    public static void initBakedPotato() {
        if (ItemTypes.BAKED_POTATO != null) return;
        ItemTypes.BAKED_POTATO = ItemTypeBuilder
                .builder(ItemBakedPotatoStack.class)
                .vanillaItem(ItemId.BAKED_POTATO)
                .build();
    }

    public static void initBalloon() {
        if (ItemTypes.BALLOON != null) return;
        ItemTypes.BALLOON = ItemTypeBuilder
                .builder(ItemBalloonStack.class)
                .vanillaItem(ItemId.BALLOON)
                .build();
    }

    public static void initBamboo() {
        if (ItemTypes.BAMBOO != null) return;
        ItemTypes.BAMBOO = ItemTypeBuilder
                .builder(ItemBambooStack.class)
                .vanillaItem(ItemId.BAMBOO)
                .build();
    }

    public static void initBambooBlock() {
        if (ItemTypes.BAMBOO_BLOCK != null) return;
        ItemTypes.BAMBOO_BLOCK = ItemTypeBuilder
                .builder(ItemBambooBlockStack.class)
                .vanillaItem(ItemId.BAMBOO_BLOCK)
                .build();
    }

    public static void initBambooButton() {
        if (ItemTypes.BAMBOO_BUTTON != null) return;
        ItemTypes.BAMBOO_BUTTON = ItemTypeBuilder
                .builder(ItemBambooButtonStack.class)
                .vanillaItem(ItemId.BAMBOO_BUTTON)
                .build();
    }

    public static void initBambooChestRaft() {
        if (ItemTypes.BAMBOO_CHEST_RAFT != null) return;
        ItemTypes.BAMBOO_CHEST_RAFT = ItemTypeBuilder
                .builder(ItemBambooChestRaftStack.class)
                .vanillaItem(ItemId.BAMBOO_CHEST_RAFT)
                .build();
    }

    public static void initBambooDoor() {
        if (ItemTypes.BAMBOO_DOOR != null) return;
        ItemTypes.BAMBOO_DOOR = ItemTypeBuilder
                .builder(ItemBambooDoorStack.class)
                .vanillaItem(ItemId.BAMBOO_DOOR)
                .build();
    }

    public static void initBambooDoubleSlab() {
        if (ItemTypes.BAMBOO_DOUBLE_SLAB != null) return;
        ItemTypes.BAMBOO_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemBambooDoubleSlabStack.class)
                .vanillaItem(ItemId.BAMBOO_DOUBLE_SLAB)
                .build();
    }

    public static void initBambooFence() {
        if (ItemTypes.BAMBOO_FENCE != null) return;
        ItemTypes.BAMBOO_FENCE = ItemTypeBuilder
                .builder(ItemBambooFenceStack.class)
                .vanillaItem(ItemId.BAMBOO_FENCE)
                .build();
    }

    public static void initBambooFenceGate() {
        if (ItemTypes.BAMBOO_FENCE_GATE != null) return;
        ItemTypes.BAMBOO_FENCE_GATE = ItemTypeBuilder
                .builder(ItemBambooFenceGateStack.class)
                .vanillaItem(ItemId.BAMBOO_FENCE_GATE)
                .build();
    }

    public static void initBambooHangingSign() {
        if (ItemTypes.BAMBOO_HANGING_SIGN != null) return;
        ItemTypes.BAMBOO_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemBambooHangingSignStack.class)
                .vanillaItem(ItemId.BAMBOO_HANGING_SIGN)
                .build();
    }

    public static void initBambooMosaic() {
        if (ItemTypes.BAMBOO_MOSAIC != null) return;
        ItemTypes.BAMBOO_MOSAIC = ItemTypeBuilder
                .builder(ItemBambooMosaicStack.class)
                .vanillaItem(ItemId.BAMBOO_MOSAIC)
                .build();
    }

    public static void initBambooMosaicDoubleSlab() {
        if (ItemTypes.BAMBOO_MOSAIC_DOUBLE_SLAB != null) return;
        ItemTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemBambooMosaicDoubleSlabStack.class)
                .vanillaItem(ItemId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                .build();
    }

    public static void initBambooMosaicSlab() {
        if (ItemTypes.BAMBOO_MOSAIC_SLAB != null) return;
        ItemTypes.BAMBOO_MOSAIC_SLAB = ItemTypeBuilder
                .builder(ItemBambooMosaicSlabStack.class)
                .vanillaItem(ItemId.BAMBOO_MOSAIC_SLAB)
                .build();
    }

    public static void initBambooMosaicStairs() {
        if (ItemTypes.BAMBOO_MOSAIC_STAIRS != null) return;
        ItemTypes.BAMBOO_MOSAIC_STAIRS = ItemTypeBuilder
                .builder(ItemBambooMosaicStairsStack.class)
                .vanillaItem(ItemId.BAMBOO_MOSAIC_STAIRS)
                .build();
    }

    public static void initBambooPlanks() {
        if (ItemTypes.BAMBOO_PLANKS != null) return;
        ItemTypes.BAMBOO_PLANKS = ItemTypeBuilder
                .builder(ItemBambooPlanksStack.class)
                .vanillaItem(ItemId.BAMBOO_PLANKS)
                .build();
    }

    public static void initBambooPressurePlate() {
        if (ItemTypes.BAMBOO_PRESSURE_PLATE != null) return;
        ItemTypes.BAMBOO_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemBambooPressurePlateStack.class)
                .vanillaItem(ItemId.BAMBOO_PRESSURE_PLATE)
                .build();
    }

    public static void initBambooRaft() {
        if (ItemTypes.BAMBOO_RAFT != null) return;
        ItemTypes.BAMBOO_RAFT = ItemTypeBuilder
                .builder(ItemBambooRaftStack.class)
                .vanillaItem(ItemId.BAMBOO_RAFT)
                .build();
    }

    public static void initBambooSapling() {
        if (ItemTypes.BAMBOO_SAPLING != null) return;
        ItemTypes.BAMBOO_SAPLING = ItemTypeBuilder
                .builder(ItemBambooSaplingStack.class)
                .vanillaItem(ItemId.BAMBOO_SAPLING)
                .build();
    }

    public static void initBambooSign() {
        if (ItemTypes.BAMBOO_SIGN != null) return;
        ItemTypes.BAMBOO_SIGN = ItemTypeBuilder
                .builder(ItemBambooSignStack.class)
                .vanillaItem(ItemId.BAMBOO_SIGN)
                .build();
    }

    public static void initBambooSlab() {
        if (ItemTypes.BAMBOO_SLAB != null) return;
        ItemTypes.BAMBOO_SLAB = ItemTypeBuilder
                .builder(ItemBambooSlabStack.class)
                .vanillaItem(ItemId.BAMBOO_SLAB)
                .build();
    }

    public static void initBambooStairs() {
        if (ItemTypes.BAMBOO_STAIRS != null) return;
        ItemTypes.BAMBOO_STAIRS = ItemTypeBuilder
                .builder(ItemBambooStairsStack.class)
                .vanillaItem(ItemId.BAMBOO_STAIRS)
                .build();
    }

    public static void initBambooStandingSign() {
        if (ItemTypes.BAMBOO_STANDING_SIGN != null) return;
        ItemTypes.BAMBOO_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemBambooStandingSignStack.class)
                .vanillaItem(ItemId.BAMBOO_STANDING_SIGN)
                .build();
    }

    public static void initBambooTrapdoor() {
        if (ItemTypes.BAMBOO_TRAPDOOR != null) return;
        ItemTypes.BAMBOO_TRAPDOOR = ItemTypeBuilder
                .builder(ItemBambooTrapdoorStack.class)
                .vanillaItem(ItemId.BAMBOO_TRAPDOOR)
                .build();
    }

    public static void initBambooWallSign() {
        if (ItemTypes.BAMBOO_WALL_SIGN != null) return;
        ItemTypes.BAMBOO_WALL_SIGN = ItemTypeBuilder
                .builder(ItemBambooWallSignStack.class)
                .vanillaItem(ItemId.BAMBOO_WALL_SIGN)
                .build();
    }

    public static void initBanner() {
        if (ItemTypes.BANNER != null) return;
        ItemTypes.BANNER = ItemTypeBuilder
                .builder(ItemBannerStack.class)
                .vanillaItem(ItemId.BANNER)
                .build();
    }

    public static void initBannerPattern() {
        if (ItemTypes.BANNER_PATTERN != null) return;
        ItemTypes.BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemBannerPatternStack.class)
                .vanillaItem(ItemId.BANNER_PATTERN)
                .build();
    }

    public static void initBarrel() {
        if (ItemTypes.BARREL != null) return;
        ItemTypes.BARREL = ItemTypeBuilder
                .builder(ItemBarrelStack.class)
                .vanillaItem(ItemId.BARREL)
                .build();
    }

    public static void initBarrier() {
        if (ItemTypes.BARRIER != null) return;
        ItemTypes.BARRIER = ItemTypeBuilder
                .builder(ItemBarrierStack.class)
                .vanillaItem(ItemId.BARRIER)
                .build();
    }

    public static void initBasalt() {
        if (ItemTypes.BASALT != null) return;
        ItemTypes.BASALT = ItemTypeBuilder
                .builder(ItemBasaltStack.class)
                .vanillaItem(ItemId.BASALT)
                .build();
    }

    public static void initBatSpawnEgg() {
        if (ItemTypes.BAT_SPAWN_EGG != null) return;
        ItemTypes.BAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBatSpawnEggStack.class)
                .vanillaItem(ItemId.BAT_SPAWN_EGG)
                .build();
    }

    public static void initBeacon() {
        if (ItemTypes.BEACON != null) return;
        ItemTypes.BEACON = ItemTypeBuilder
                .builder(ItemBeaconStack.class)
                .vanillaItem(ItemId.BEACON)
                .build();
    }

    public static void initBed() {
        if (ItemTypes.BED != null) return;
        ItemTypes.BED = ItemTypeBuilder
                .builder(ItemBedStack.class)
                .vanillaItem(ItemId.BED)
                .build();
    }

    public static void initBedrock() {
        if (ItemTypes.BEDROCK != null) return;
        ItemTypes.BEDROCK = ItemTypeBuilder
                .builder(ItemBedrockStack.class)
                .vanillaItem(ItemId.BEDROCK)
                .build();
    }

    public static void initBeeNest() {
        if (ItemTypes.BEE_NEST != null) return;
        ItemTypes.BEE_NEST = ItemTypeBuilder
                .builder(ItemBeeNestStack.class)
                .vanillaItem(ItemId.BEE_NEST)
                .build();
    }

    public static void initBeeSpawnEgg() {
        if (ItemTypes.BEE_SPAWN_EGG != null) return;
        ItemTypes.BEE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBeeSpawnEggStack.class)
                .vanillaItem(ItemId.BEE_SPAWN_EGG)
                .build();
    }

    public static void initBeef() {
        if (ItemTypes.BEEF != null) return;
        ItemTypes.BEEF = ItemTypeBuilder
                .builder(ItemBeefStack.class)
                .vanillaItem(ItemId.BEEF)
                .build();
    }

    public static void initBeehive() {
        if (ItemTypes.BEEHIVE != null) return;
        ItemTypes.BEEHIVE = ItemTypeBuilder
                .builder(ItemBeehiveStack.class)
                .vanillaItem(ItemId.BEEHIVE)
                .build();
    }

    public static void initBeetroot() {
        if (ItemTypes.BEETROOT != null) return;
        ItemTypes.BEETROOT = ItemTypeBuilder
                .builder(ItemBeetrootStack.class)
                .vanillaItem(ItemId.BEETROOT)
                .build();
    }

    public static void initBeetrootSeeds() {
        if (ItemTypes.BEETROOT_SEEDS != null) return;
        ItemTypes.BEETROOT_SEEDS = ItemTypeBuilder
                .builder(ItemBeetrootSeedsStack.class)
                .vanillaItem(ItemId.BEETROOT_SEEDS)
                .build();
    }

    public static void initBeetrootSoup() {
        if (ItemTypes.BEETROOT_SOUP != null) return;
        ItemTypes.BEETROOT_SOUP = ItemTypeBuilder
                .builder(ItemBeetrootSoupStack.class)
                .vanillaItem(ItemId.BEETROOT_SOUP)
                .build();
    }

    public static void initBell() {
        if (ItemTypes.BELL != null) return;
        ItemTypes.BELL = ItemTypeBuilder
                .builder(ItemBellStack.class)
                .vanillaItem(ItemId.BELL)
                .build();
    }

    public static void initBigDripleaf() {
        if (ItemTypes.BIG_DRIPLEAF != null) return;
        ItemTypes.BIG_DRIPLEAF = ItemTypeBuilder
                .builder(ItemBigDripleafStack.class)
                .vanillaItem(ItemId.BIG_DRIPLEAF)
                .build();
    }

    public static void initBirchBoat() {
        if (ItemTypes.BIRCH_BOAT != null) return;
        ItemTypes.BIRCH_BOAT = ItemTypeBuilder
                .builder(ItemBirchBoatStack.class)
                .vanillaItem(ItemId.BIRCH_BOAT)
                .build();
    }

    public static void initBirchButton() {
        if (ItemTypes.BIRCH_BUTTON != null) return;
        ItemTypes.BIRCH_BUTTON = ItemTypeBuilder
                .builder(ItemBirchButtonStack.class)
                .vanillaItem(ItemId.BIRCH_BUTTON)
                .build();
    }

    public static void initBirchChestBoat() {
        if (ItemTypes.BIRCH_CHEST_BOAT != null) return;
        ItemTypes.BIRCH_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemBirchChestBoatStack.class)
                .vanillaItem(ItemId.BIRCH_CHEST_BOAT)
                .build();
    }

    public static void initBirchDoor() {
        if (ItemTypes.BIRCH_DOOR != null) return;
        ItemTypes.BIRCH_DOOR = ItemTypeBuilder
                .builder(ItemBirchDoorStack.class)
                .vanillaItem(ItemId.BIRCH_DOOR)
                .build();
    }

    public static void initBirchDoubleSlab() {
        if (ItemTypes.BIRCH_DOUBLE_SLAB != null) return;
        ItemTypes.BIRCH_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemBirchDoubleSlabStack.class)
                .vanillaItem(ItemId.BIRCH_DOUBLE_SLAB)
                .build();
    }

    public static void initBirchFence() {
        if (ItemTypes.BIRCH_FENCE != null) return;
        ItemTypes.BIRCH_FENCE = ItemTypeBuilder
                .builder(ItemBirchFenceStack.class)
                .vanillaItem(ItemId.BIRCH_FENCE)
                .build();
    }

    public static void initBirchFenceGate() {
        if (ItemTypes.BIRCH_FENCE_GATE != null) return;
        ItemTypes.BIRCH_FENCE_GATE = ItemTypeBuilder
                .builder(ItemBirchFenceGateStack.class)
                .vanillaItem(ItemId.BIRCH_FENCE_GATE)
                .build();
    }

    public static void initBirchHangingSign() {
        if (ItemTypes.BIRCH_HANGING_SIGN != null) return;
        ItemTypes.BIRCH_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemBirchHangingSignStack.class)
                .vanillaItem(ItemId.BIRCH_HANGING_SIGN)
                .build();
    }

    public static void initBirchLeaves() {
        if (ItemTypes.BIRCH_LEAVES != null) return;
        ItemTypes.BIRCH_LEAVES = ItemTypeBuilder
                .builder(ItemBirchLeavesStack.class)
                .vanillaItem(ItemId.BIRCH_LEAVES)
                .build();
    }

    public static void initBirchLog() {
        if (ItemTypes.BIRCH_LOG != null) return;
        ItemTypes.BIRCH_LOG = ItemTypeBuilder
                .builder(ItemBirchLogStack.class)
                .vanillaItem(ItemId.BIRCH_LOG)
                .build();
    }

    public static void initBirchPlanks() {
        if (ItemTypes.BIRCH_PLANKS != null) return;
        ItemTypes.BIRCH_PLANKS = ItemTypeBuilder
                .builder(ItemBirchPlanksStack.class)
                .vanillaItem(ItemId.BIRCH_PLANKS)
                .build();
    }

    public static void initBirchPressurePlate() {
        if (ItemTypes.BIRCH_PRESSURE_PLATE != null) return;
        ItemTypes.BIRCH_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemBirchPressurePlateStack.class)
                .vanillaItem(ItemId.BIRCH_PRESSURE_PLATE)
                .build();
    }

    public static void initBirchSapling() {
        if (ItemTypes.BIRCH_SAPLING != null) return;
        ItemTypes.BIRCH_SAPLING = ItemTypeBuilder
                .builder(ItemBirchSaplingStack.class)
                .vanillaItem(ItemId.BIRCH_SAPLING)
                .build();
    }

    public static void initBirchSign() {
        if (ItemTypes.BIRCH_SIGN != null) return;
        ItemTypes.BIRCH_SIGN = ItemTypeBuilder
                .builder(ItemBirchSignStack.class)
                .vanillaItem(ItemId.BIRCH_SIGN)
                .build();
    }

    public static void initBirchSlab() {
        if (ItemTypes.BIRCH_SLAB != null) return;
        ItemTypes.BIRCH_SLAB = ItemTypeBuilder
                .builder(ItemBirchSlabStack.class)
                .vanillaItem(ItemId.BIRCH_SLAB)
                .build();
    }

    public static void initBirchStairs() {
        if (ItemTypes.BIRCH_STAIRS != null) return;
        ItemTypes.BIRCH_STAIRS = ItemTypeBuilder
                .builder(ItemBirchStairsStack.class)
                .vanillaItem(ItemId.BIRCH_STAIRS)
                .build();
    }

    public static void initBirchStandingSign() {
        if (ItemTypes.BIRCH_STANDING_SIGN != null) return;
        ItemTypes.BIRCH_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemBirchStandingSignStack.class)
                .vanillaItem(ItemId.BIRCH_STANDING_SIGN)
                .build();
    }

    public static void initBirchTrapdoor() {
        if (ItemTypes.BIRCH_TRAPDOOR != null) return;
        ItemTypes.BIRCH_TRAPDOOR = ItemTypeBuilder
                .builder(ItemBirchTrapdoorStack.class)
                .vanillaItem(ItemId.BIRCH_TRAPDOOR)
                .build();
    }

    public static void initBirchWallSign() {
        if (ItemTypes.BIRCH_WALL_SIGN != null) return;
        ItemTypes.BIRCH_WALL_SIGN = ItemTypeBuilder
                .builder(ItemBirchWallSignStack.class)
                .vanillaItem(ItemId.BIRCH_WALL_SIGN)
                .build();
    }

    public static void initBirchWood() {
        if (ItemTypes.BIRCH_WOOD != null) return;
        ItemTypes.BIRCH_WOOD = ItemTypeBuilder
                .builder(ItemBirchWoodStack.class)
                .vanillaItem(ItemId.BIRCH_WOOD)
                .build();
    }

    public static void initBlackCandle() {
        if (ItemTypes.BLACK_CANDLE != null) return;
        ItemTypes.BLACK_CANDLE = ItemTypeBuilder
                .builder(ItemBlackCandleStack.class)
                .vanillaItem(ItemId.BLACK_CANDLE)
                .build();
    }

    public static void initBlackCandleCake() {
        if (ItemTypes.BLACK_CANDLE_CAKE != null) return;
        ItemTypes.BLACK_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemBlackCandleCakeStack.class)
                .vanillaItem(ItemId.BLACK_CANDLE_CAKE)
                .build();
    }

    public static void initBlackCarpet() {
        if (ItemTypes.BLACK_CARPET != null) return;
        ItemTypes.BLACK_CARPET = ItemTypeBuilder
                .builder(ItemBlackCarpetStack.class)
                .vanillaItem(ItemId.BLACK_CARPET)
                .build();
    }

    public static void initBlackConcrete() {
        if (ItemTypes.BLACK_CONCRETE != null) return;
        ItemTypes.BLACK_CONCRETE = ItemTypeBuilder
                .builder(ItemBlackConcreteStack.class)
                .vanillaItem(ItemId.BLACK_CONCRETE)
                .build();
    }

    public static void initBlackConcretePowder() {
        if (ItemTypes.BLACK_CONCRETE_POWDER != null) return;
        ItemTypes.BLACK_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemBlackConcretePowderStack.class)
                .vanillaItem(ItemId.BLACK_CONCRETE_POWDER)
                .build();
    }

    public static void initBlackDye() {
        if (ItemTypes.BLACK_DYE != null) return;
        ItemTypes.BLACK_DYE = ItemTypeBuilder
                .builder(ItemBlackDyeStack.class)
                .vanillaItem(ItemId.BLACK_DYE)
                .build();
    }

    public static void initBlackGlazedTerracotta() {
        if (ItemTypes.BLACK_GLAZED_TERRACOTTA != null) return;
        ItemTypes.BLACK_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBlackGlazedTerracottaStack.class)
                .vanillaItem(ItemId.BLACK_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initBlackShulkerBox() {
        if (ItemTypes.BLACK_SHULKER_BOX != null) return;
        ItemTypes.BLACK_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBlackShulkerBoxStack.class)
                .vanillaItem(ItemId.BLACK_SHULKER_BOX)
                .build();
    }

    public static void initBlackStainedGlass() {
        if (ItemTypes.BLACK_STAINED_GLASS != null) return;
        ItemTypes.BLACK_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemBlackStainedGlassStack.class)
                .vanillaItem(ItemId.BLACK_STAINED_GLASS)
                .build();
    }

    public static void initBlackStainedGlassPane() {
        if (ItemTypes.BLACK_STAINED_GLASS_PANE != null) return;
        ItemTypes.BLACK_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemBlackStainedGlassPaneStack.class)
                .vanillaItem(ItemId.BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlackTerracotta() {
        if (ItemTypes.BLACK_TERRACOTTA != null) return;
        ItemTypes.BLACK_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBlackTerracottaStack.class)
                .vanillaItem(ItemId.BLACK_TERRACOTTA)
                .build();
    }

    public static void initBlackWool() {
        if (ItemTypes.BLACK_WOOL != null) return;
        ItemTypes.BLACK_WOOL = ItemTypeBuilder
                .builder(ItemBlackWoolStack.class)
                .vanillaItem(ItemId.BLACK_WOOL)
                .build();
    }

    public static void initBlackstone() {
        if (ItemTypes.BLACKSTONE != null) return;
        ItemTypes.BLACKSTONE = ItemTypeBuilder
                .builder(ItemBlackstoneStack.class)
                .vanillaItem(ItemId.BLACKSTONE)
                .build();
    }

    public static void initBlackstoneDoubleSlab() {
        if (ItemTypes.BLACKSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.BLACKSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemBlackstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.BLACKSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initBlackstoneSlab() {
        if (ItemTypes.BLACKSTONE_SLAB != null) return;
        ItemTypes.BLACKSTONE_SLAB = ItemTypeBuilder
                .builder(ItemBlackstoneSlabStack.class)
                .vanillaItem(ItemId.BLACKSTONE_SLAB)
                .build();
    }

    public static void initBlackstoneStairs() {
        if (ItemTypes.BLACKSTONE_STAIRS != null) return;
        ItemTypes.BLACKSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemBlackstoneStairsStack.class)
                .vanillaItem(ItemId.BLACKSTONE_STAIRS)
                .build();
    }

    public static void initBlackstoneWall() {
        if (ItemTypes.BLACKSTONE_WALL != null) return;
        ItemTypes.BLACKSTONE_WALL = ItemTypeBuilder
                .builder(ItemBlackstoneWallStack.class)
                .vanillaItem(ItemId.BLACKSTONE_WALL)
                .build();
    }

    public static void initBladePotterySherd() {
        if (ItemTypes.BLADE_POTTERY_SHERD != null) return;
        ItemTypes.BLADE_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemBladePotterySherdStack.class)
                .vanillaItem(ItemId.BLADE_POTTERY_SHERD)
                .build();
    }

    public static void initBlastFurnace() {
        if (ItemTypes.BLAST_FURNACE != null) return;
        ItemTypes.BLAST_FURNACE = ItemTypeBuilder
                .builder(ItemBlastFurnaceStack.class)
                .vanillaItem(ItemId.BLAST_FURNACE)
                .build();
    }

    public static void initBlazePowder() {
        if (ItemTypes.BLAZE_POWDER != null) return;
        ItemTypes.BLAZE_POWDER = ItemTypeBuilder
                .builder(ItemBlazePowderStack.class)
                .vanillaItem(ItemId.BLAZE_POWDER)
                .build();
    }

    public static void initBlazeRod() {
        if (ItemTypes.BLAZE_ROD != null) return;
        ItemTypes.BLAZE_ROD = ItemTypeBuilder
                .builder(ItemBlazeRodStack.class)
                .vanillaItem(ItemId.BLAZE_ROD)
                .build();
    }

    public static void initBlazeSpawnEgg() {
        if (ItemTypes.BLAZE_SPAWN_EGG != null) return;
        ItemTypes.BLAZE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBlazeSpawnEggStack.class)
                .vanillaItem(ItemId.BLAZE_SPAWN_EGG)
                .build();
    }

    public static void initBleach() {
        if (ItemTypes.BLEACH != null) return;
        ItemTypes.BLEACH = ItemTypeBuilder
                .builder(ItemBleachStack.class)
                .vanillaItem(ItemId.BLEACH)
                .build();
    }

    public static void initBlueCandle() {
        if (ItemTypes.BLUE_CANDLE != null) return;
        ItemTypes.BLUE_CANDLE = ItemTypeBuilder
                .builder(ItemBlueCandleStack.class)
                .vanillaItem(ItemId.BLUE_CANDLE)
                .build();
    }

    public static void initBlueCandleCake() {
        if (ItemTypes.BLUE_CANDLE_CAKE != null) return;
        ItemTypes.BLUE_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemBlueCandleCakeStack.class)
                .vanillaItem(ItemId.BLUE_CANDLE_CAKE)
                .build();
    }

    public static void initBlueCarpet() {
        if (ItemTypes.BLUE_CARPET != null) return;
        ItemTypes.BLUE_CARPET = ItemTypeBuilder
                .builder(ItemBlueCarpetStack.class)
                .vanillaItem(ItemId.BLUE_CARPET)
                .build();
    }

    public static void initBlueConcrete() {
        if (ItemTypes.BLUE_CONCRETE != null) return;
        ItemTypes.BLUE_CONCRETE = ItemTypeBuilder
                .builder(ItemBlueConcreteStack.class)
                .vanillaItem(ItemId.BLUE_CONCRETE)
                .build();
    }

    public static void initBlueConcretePowder() {
        if (ItemTypes.BLUE_CONCRETE_POWDER != null) return;
        ItemTypes.BLUE_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemBlueConcretePowderStack.class)
                .vanillaItem(ItemId.BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initBlueDye() {
        if (ItemTypes.BLUE_DYE != null) return;
        ItemTypes.BLUE_DYE = ItemTypeBuilder
                .builder(ItemBlueDyeStack.class)
                .vanillaItem(ItemId.BLUE_DYE)
                .build();
    }

    public static void initBlueGlazedTerracotta() {
        if (ItemTypes.BLUE_GLAZED_TERRACOTTA != null) return;
        ItemTypes.BLUE_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBlueGlazedTerracottaStack.class)
                .vanillaItem(ItemId.BLUE_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initBlueIce() {
        if (ItemTypes.BLUE_ICE != null) return;
        ItemTypes.BLUE_ICE = ItemTypeBuilder
                .builder(ItemBlueIceStack.class)
                .vanillaItem(ItemId.BLUE_ICE)
                .build();
    }

    public static void initBlueOrchid() {
        if (ItemTypes.BLUE_ORCHID != null) return;
        ItemTypes.BLUE_ORCHID = ItemTypeBuilder
                .builder(ItemBlueOrchidStack.class)
                .vanillaItem(ItemId.BLUE_ORCHID)
                .build();
    }

    public static void initBlueShulkerBox() {
        if (ItemTypes.BLUE_SHULKER_BOX != null) return;
        ItemTypes.BLUE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBlueShulkerBoxStack.class)
                .vanillaItem(ItemId.BLUE_SHULKER_BOX)
                .build();
    }

    public static void initBlueStainedGlass() {
        if (ItemTypes.BLUE_STAINED_GLASS != null) return;
        ItemTypes.BLUE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemBlueStainedGlassStack.class)
                .vanillaItem(ItemId.BLUE_STAINED_GLASS)
                .build();
    }

    public static void initBlueStainedGlassPane() {
        if (ItemTypes.BLUE_STAINED_GLASS_PANE != null) return;
        ItemTypes.BLUE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemBlueStainedGlassPaneStack.class)
                .vanillaItem(ItemId.BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlueTerracotta() {
        if (ItemTypes.BLUE_TERRACOTTA != null) return;
        ItemTypes.BLUE_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBlueTerracottaStack.class)
                .vanillaItem(ItemId.BLUE_TERRACOTTA)
                .build();
    }

    public static void initBlueWool() {
        if (ItemTypes.BLUE_WOOL != null) return;
        ItemTypes.BLUE_WOOL = ItemTypeBuilder
                .builder(ItemBlueWoolStack.class)
                .vanillaItem(ItemId.BLUE_WOOL)
                .build();
    }

    public static void initBoat() {
        if (ItemTypes.BOAT != null) return;
        ItemTypes.BOAT = ItemTypeBuilder
                .builder(ItemBoatStack.class)
                .vanillaItem(ItemId.BOAT)
                .build();
    }

    public static void initBoggedSpawnEgg() {
        if (ItemTypes.BOGGED_SPAWN_EGG != null) return;
        ItemTypes.BOGGED_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBoggedSpawnEggStack.class)
                .vanillaItem(ItemId.BOGGED_SPAWN_EGG)
                .build();
    }

    public static void initBoltArmorTrimSmithingTemplate() {
        if (ItemTypes.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemBoltArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initBone() {
        if (ItemTypes.BONE != null) return;
        ItemTypes.BONE = ItemTypeBuilder
                .builder(ItemBoneStack.class)
                .vanillaItem(ItemId.BONE)
                .build();
    }

    public static void initBoneBlock() {
        if (ItemTypes.BONE_BLOCK != null) return;
        ItemTypes.BONE_BLOCK = ItemTypeBuilder
                .builder(ItemBoneBlockStack.class)
                .vanillaItem(ItemId.BONE_BLOCK)
                .build();
    }

    public static void initBoneMeal() {
        if (ItemTypes.BONE_MEAL != null) return;
        ItemTypes.BONE_MEAL = ItemTypeBuilder
                .builder(ItemBoneMealStack.class)
                .vanillaItem(ItemId.BONE_MEAL)
                .build();
    }

    public static void initBook() {
        if (ItemTypes.BOOK != null) return;
        ItemTypes.BOOK = ItemTypeBuilder
                .builder(ItemBookStack.class)
                .vanillaItem(ItemId.BOOK)
                .build();
    }

    public static void initBookshelf() {
        if (ItemTypes.BOOKSHELF != null) return;
        ItemTypes.BOOKSHELF = ItemTypeBuilder
                .builder(ItemBookshelfStack.class)
                .vanillaItem(ItemId.BOOKSHELF)
                .build();
    }

    public static void initBorderBlock() {
        if (ItemTypes.BORDER_BLOCK != null) return;
        ItemTypes.BORDER_BLOCK = ItemTypeBuilder
                .builder(ItemBorderBlockStack.class)
                .vanillaItem(ItemId.BORDER_BLOCK)
                .build();
    }

    public static void initBordureIndentedBannerPattern() {
        if (ItemTypes.BORDURE_INDENTED_BANNER_PATTERN != null) return;
        ItemTypes.BORDURE_INDENTED_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemBordureIndentedBannerPatternStack.class)
                .vanillaItem(ItemId.BORDURE_INDENTED_BANNER_PATTERN)
                .build();
    }

    public static void initBow() {
        if (ItemTypes.BOW != null) return;
        ItemTypes.BOW = ItemTypeBuilder
                .builder(ItemBowStack.class)
                .vanillaItem(ItemId.BOW)
                .build();
    }

    public static void initBowl() {
        if (ItemTypes.BOWL != null) return;
        ItemTypes.BOWL = ItemTypeBuilder
                .builder(ItemBowlStack.class)
                .vanillaItem(ItemId.BOWL)
                .build();
    }

    public static void initBrainCoral() {
        if (ItemTypes.BRAIN_CORAL != null) return;
        ItemTypes.BRAIN_CORAL = ItemTypeBuilder
                .builder(ItemBrainCoralStack.class)
                .vanillaItem(ItemId.BRAIN_CORAL)
                .build();
    }

    public static void initBrainCoralBlock() {
        if (ItemTypes.BRAIN_CORAL_BLOCK != null) return;
        ItemTypes.BRAIN_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemBrainCoralBlockStack.class)
                .vanillaItem(ItemId.BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initBrainCoralFan() {
        if (ItemTypes.BRAIN_CORAL_FAN != null) return;
        ItemTypes.BRAIN_CORAL_FAN = ItemTypeBuilder
                .builder(ItemBrainCoralFanStack.class)
                .vanillaItem(ItemId.BRAIN_CORAL_FAN)
                .build();
    }

    public static void initBrainCoralWallFan() {
        if (ItemTypes.BRAIN_CORAL_WALL_FAN != null) return;
        ItemTypes.BRAIN_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemBrainCoralWallFanStack.class)
                .vanillaItem(ItemId.BRAIN_CORAL_WALL_FAN)
                .build();
    }

    public static void initBread() {
        if (ItemTypes.BREAD != null) return;
        ItemTypes.BREAD = ItemTypeBuilder
                .builder(ItemBreadStack.class)
                .vanillaItem(ItemId.BREAD)
                .build();
    }

    public static void initBreezeRod() {
        if (ItemTypes.BREEZE_ROD != null) return;
        ItemTypes.BREEZE_ROD = ItemTypeBuilder
                .builder(ItemBreezeRodStack.class)
                .vanillaItem(ItemId.BREEZE_ROD)
                .build();
    }

    public static void initBreezeSpawnEgg() {
        if (ItemTypes.BREEZE_SPAWN_EGG != null) return;
        ItemTypes.BREEZE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBreezeSpawnEggStack.class)
                .vanillaItem(ItemId.BREEZE_SPAWN_EGG)
                .build();
    }

    public static void initBrewerPotterySherd() {
        if (ItemTypes.BREWER_POTTERY_SHERD != null) return;
        ItemTypes.BREWER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemBrewerPotterySherdStack.class)
                .vanillaItem(ItemId.BREWER_POTTERY_SHERD)
                .build();
    }

    public static void initBrewingStand() {
        if (ItemTypes.BREWING_STAND != null) return;
        ItemTypes.BREWING_STAND = ItemTypeBuilder
                .builder(ItemBrewingStandStack.class)
                .vanillaItem(ItemId.BREWING_STAND)
                .build();
    }

    public static void initBrick() {
        if (ItemTypes.BRICK != null) return;
        ItemTypes.BRICK = ItemTypeBuilder
                .builder(ItemBrickStack.class)
                .vanillaItem(ItemId.BRICK)
                .build();
    }

    public static void initBrickBlock() {
        if (ItemTypes.BRICK_BLOCK != null) return;
        ItemTypes.BRICK_BLOCK = ItemTypeBuilder
                .builder(ItemBrickBlockStack.class)
                .vanillaItem(ItemId.BRICK_BLOCK)
                .build();
    }

    public static void initBrickDoubleSlab() {
        if (ItemTypes.BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initBrickSlab() {
        if (ItemTypes.BRICK_SLAB != null) return;
        ItemTypes.BRICK_SLAB = ItemTypeBuilder
                .builder(ItemBrickSlabStack.class)
                .vanillaItem(ItemId.BRICK_SLAB)
                .build();
    }

    public static void initBrickStairs() {
        if (ItemTypes.BRICK_STAIRS != null) return;
        ItemTypes.BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemBrickStairsStack.class)
                .vanillaItem(ItemId.BRICK_STAIRS)
                .build();
    }

    public static void initBrownCandle() {
        if (ItemTypes.BROWN_CANDLE != null) return;
        ItemTypes.BROWN_CANDLE = ItemTypeBuilder
                .builder(ItemBrownCandleStack.class)
                .vanillaItem(ItemId.BROWN_CANDLE)
                .build();
    }

    public static void initBrownCandleCake() {
        if (ItemTypes.BROWN_CANDLE_CAKE != null) return;
        ItemTypes.BROWN_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemBrownCandleCakeStack.class)
                .vanillaItem(ItemId.BROWN_CANDLE_CAKE)
                .build();
    }

    public static void initBrownCarpet() {
        if (ItemTypes.BROWN_CARPET != null) return;
        ItemTypes.BROWN_CARPET = ItemTypeBuilder
                .builder(ItemBrownCarpetStack.class)
                .vanillaItem(ItemId.BROWN_CARPET)
                .build();
    }

    public static void initBrownConcrete() {
        if (ItemTypes.BROWN_CONCRETE != null) return;
        ItemTypes.BROWN_CONCRETE = ItemTypeBuilder
                .builder(ItemBrownConcreteStack.class)
                .vanillaItem(ItemId.BROWN_CONCRETE)
                .build();
    }

    public static void initBrownConcretePowder() {
        if (ItemTypes.BROWN_CONCRETE_POWDER != null) return;
        ItemTypes.BROWN_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemBrownConcretePowderStack.class)
                .vanillaItem(ItemId.BROWN_CONCRETE_POWDER)
                .build();
    }

    public static void initBrownDye() {
        if (ItemTypes.BROWN_DYE != null) return;
        ItemTypes.BROWN_DYE = ItemTypeBuilder
                .builder(ItemBrownDyeStack.class)
                .vanillaItem(ItemId.BROWN_DYE)
                .build();
    }

    public static void initBrownGlazedTerracotta() {
        if (ItemTypes.BROWN_GLAZED_TERRACOTTA != null) return;
        ItemTypes.BROWN_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBrownGlazedTerracottaStack.class)
                .vanillaItem(ItemId.BROWN_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initBrownMushroom() {
        if (ItemTypes.BROWN_MUSHROOM != null) return;
        ItemTypes.BROWN_MUSHROOM = ItemTypeBuilder
                .builder(ItemBrownMushroomStack.class)
                .vanillaItem(ItemId.BROWN_MUSHROOM)
                .build();
    }

    public static void initBrownMushroomBlock() {
        if (ItemTypes.BROWN_MUSHROOM_BLOCK != null) return;
        ItemTypes.BROWN_MUSHROOM_BLOCK = ItemTypeBuilder
                .builder(ItemBrownMushroomBlockStack.class)
                .vanillaItem(ItemId.BROWN_MUSHROOM_BLOCK)
                .build();
    }

    public static void initBrownShulkerBox() {
        if (ItemTypes.BROWN_SHULKER_BOX != null) return;
        ItemTypes.BROWN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBrownShulkerBoxStack.class)
                .vanillaItem(ItemId.BROWN_SHULKER_BOX)
                .build();
    }

    public static void initBrownStainedGlass() {
        if (ItemTypes.BROWN_STAINED_GLASS != null) return;
        ItemTypes.BROWN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemBrownStainedGlassStack.class)
                .vanillaItem(ItemId.BROWN_STAINED_GLASS)
                .build();
    }

    public static void initBrownStainedGlassPane() {
        if (ItemTypes.BROWN_STAINED_GLASS_PANE != null) return;
        ItemTypes.BROWN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemBrownStainedGlassPaneStack.class)
                .vanillaItem(ItemId.BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBrownTerracotta() {
        if (ItemTypes.BROWN_TERRACOTTA != null) return;
        ItemTypes.BROWN_TERRACOTTA = ItemTypeBuilder
                .builder(ItemBrownTerracottaStack.class)
                .vanillaItem(ItemId.BROWN_TERRACOTTA)
                .build();
    }

    public static void initBrownWool() {
        if (ItemTypes.BROWN_WOOL != null) return;
        ItemTypes.BROWN_WOOL = ItemTypeBuilder
                .builder(ItemBrownWoolStack.class)
                .vanillaItem(ItemId.BROWN_WOOL)
                .build();
    }

    public static void initBrush() {
        if (ItemTypes.BRUSH != null) return;
        ItemTypes.BRUSH = ItemTypeBuilder
                .builder(ItemBrushStack.class)
                .vanillaItem(ItemId.BRUSH)
                .build();
    }

    public static void initBubbleColumn() {
        if (ItemTypes.BUBBLE_COLUMN != null) return;
        ItemTypes.BUBBLE_COLUMN = ItemTypeBuilder
                .builder(ItemBubbleColumnStack.class)
                .vanillaItem(ItemId.BUBBLE_COLUMN)
                .build();
    }

    public static void initBubbleCoral() {
        if (ItemTypes.BUBBLE_CORAL != null) return;
        ItemTypes.BUBBLE_CORAL = ItemTypeBuilder
                .builder(ItemBubbleCoralStack.class)
                .vanillaItem(ItemId.BUBBLE_CORAL)
                .build();
    }

    public static void initBubbleCoralBlock() {
        if (ItemTypes.BUBBLE_CORAL_BLOCK != null) return;
        ItemTypes.BUBBLE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemBubbleCoralBlockStack.class)
                .vanillaItem(ItemId.BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initBubbleCoralFan() {
        if (ItemTypes.BUBBLE_CORAL_FAN != null) return;
        ItemTypes.BUBBLE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemBubbleCoralFanStack.class)
                .vanillaItem(ItemId.BUBBLE_CORAL_FAN)
                .build();
    }

    public static void initBubbleCoralWallFan() {
        if (ItemTypes.BUBBLE_CORAL_WALL_FAN != null) return;
        ItemTypes.BUBBLE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemBubbleCoralWallFanStack.class)
                .vanillaItem(ItemId.BUBBLE_CORAL_WALL_FAN)
                .build();
    }

    public static void initBucket() {
        if (ItemTypes.BUCKET != null) return;
        ItemTypes.BUCKET = ItemTypeBuilder
                .builder(ItemBucketStack.class)
                .vanillaItem(ItemId.BUCKET)
                .build();
    }

    public static void initBuddingAmethyst() {
        if (ItemTypes.BUDDING_AMETHYST != null) return;
        ItemTypes.BUDDING_AMETHYST = ItemTypeBuilder
                .builder(ItemBuddingAmethystStack.class)
                .vanillaItem(ItemId.BUDDING_AMETHYST)
                .build();
    }

    public static void initBurnPotterySherd() {
        if (ItemTypes.BURN_POTTERY_SHERD != null) return;
        ItemTypes.BURN_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemBurnPotterySherdStack.class)
                .vanillaItem(ItemId.BURN_POTTERY_SHERD)
                .build();
    }

    public static void initCactus() {
        if (ItemTypes.CACTUS != null) return;
        ItemTypes.CACTUS = ItemTypeBuilder
                .builder(ItemCactusStack.class)
                .vanillaItem(ItemId.CACTUS)
                .build();
    }

    public static void initCake() {
        if (ItemTypes.CAKE != null) return;
        ItemTypes.CAKE = ItemTypeBuilder
                .builder(ItemCakeStack.class)
                .vanillaItem(ItemId.CAKE)
                .build();
    }

    public static void initCalcite() {
        if (ItemTypes.CALCITE != null) return;
        ItemTypes.CALCITE = ItemTypeBuilder
                .builder(ItemCalciteStack.class)
                .vanillaItem(ItemId.CALCITE)
                .build();
    }

    public static void initCalibratedSculkSensor() {
        if (ItemTypes.CALIBRATED_SCULK_SENSOR != null) return;
        ItemTypes.CALIBRATED_SCULK_SENSOR = ItemTypeBuilder
                .builder(ItemCalibratedSculkSensorStack.class)
                .vanillaItem(ItemId.CALIBRATED_SCULK_SENSOR)
                .build();
    }

    public static void initCamelSpawnEgg() {
        if (ItemTypes.CAMEL_SPAWN_EGG != null) return;
        ItemTypes.CAMEL_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCamelSpawnEggStack.class)
                .vanillaItem(ItemId.CAMEL_SPAWN_EGG)
                .build();
    }

    public static void initCamera() {
        if (ItemTypes.CAMERA != null) return;
        ItemTypes.CAMERA = ItemTypeBuilder
                .builder(ItemCameraStack.class)
                .vanillaItem(ItemId.CAMERA)
                .build();
    }

    public static void initCampfire() {
        if (ItemTypes.CAMPFIRE != null) return;
        ItemTypes.CAMPFIRE = ItemTypeBuilder
                .builder(ItemCampfireStack.class)
                .vanillaItem(ItemId.CAMPFIRE)
                .build();
    }

    public static void initCandle() {
        if (ItemTypes.CANDLE != null) return;
        ItemTypes.CANDLE = ItemTypeBuilder
                .builder(ItemCandleStack.class)
                .vanillaItem(ItemId.CANDLE)
                .build();
    }

    public static void initCandleCake() {
        if (ItemTypes.CANDLE_CAKE != null) return;
        ItemTypes.CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemCandleCakeStack.class)
                .vanillaItem(ItemId.CANDLE_CAKE)
                .build();
    }

    public static void initCarpet() {
        if (ItemTypes.CARPET != null) return;
        ItemTypes.CARPET = ItemTypeBuilder
                .builder(ItemCarpetStack.class)
                .vanillaItem(ItemId.CARPET)
                .build();
    }

    public static void initCarrot() {
        if (ItemTypes.CARROT != null) return;
        ItemTypes.CARROT = ItemTypeBuilder
                .builder(ItemCarrotStack.class)
                .vanillaItem(ItemId.CARROT)
                .build();
    }

    public static void initCarrotOnAStick() {
        if (ItemTypes.CARROT_ON_A_STICK != null) return;
        ItemTypes.CARROT_ON_A_STICK = ItemTypeBuilder
                .builder(ItemCarrotOnAStickStack.class)
                .vanillaItem(ItemId.CARROT_ON_A_STICK)
                .build();
    }

    public static void initCarrots() {
        if (ItemTypes.CARROTS != null) return;
        ItemTypes.CARROTS = ItemTypeBuilder
                .builder(ItemCarrotsStack.class)
                .vanillaItem(ItemId.CARROTS)
                .build();
    }

    public static void initCartographyTable() {
        if (ItemTypes.CARTOGRAPHY_TABLE != null) return;
        ItemTypes.CARTOGRAPHY_TABLE = ItemTypeBuilder
                .builder(ItemCartographyTableStack.class)
                .vanillaItem(ItemId.CARTOGRAPHY_TABLE)
                .build();
    }

    public static void initCarvedPumpkin() {
        if (ItemTypes.CARVED_PUMPKIN != null) return;
        ItemTypes.CARVED_PUMPKIN = ItemTypeBuilder
                .builder(ItemCarvedPumpkinStack.class)
                .vanillaItem(ItemId.CARVED_PUMPKIN)
                .build();
    }

    public static void initCatSpawnEgg() {
        if (ItemTypes.CAT_SPAWN_EGG != null) return;
        ItemTypes.CAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCatSpawnEggStack.class)
                .vanillaItem(ItemId.CAT_SPAWN_EGG)
                .build();
    }

    public static void initCauldron() {
        if (ItemTypes.CAULDRON != null) return;
        ItemTypes.CAULDRON = ItemTypeBuilder
                .builder(ItemCauldronStack.class)
                .vanillaItem(ItemId.CAULDRON)
                .build();
    }

    public static void initCaveSpiderSpawnEgg() {
        if (ItemTypes.CAVE_SPIDER_SPAWN_EGG != null) return;
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCaveSpiderSpawnEggStack.class)
                .vanillaItem(ItemId.CAVE_SPIDER_SPAWN_EGG)
                .build();
    }

    public static void initCaveVines() {
        if (ItemTypes.CAVE_VINES != null) return;
        ItemTypes.CAVE_VINES = ItemTypeBuilder
                .builder(ItemCaveVinesStack.class)
                .vanillaItem(ItemId.CAVE_VINES)
                .build();
    }

    public static void initCaveVinesBodyWithBerries() {
        if (ItemTypes.CAVE_VINES_BODY_WITH_BERRIES != null) return;
        ItemTypes.CAVE_VINES_BODY_WITH_BERRIES = ItemTypeBuilder
                .builder(ItemCaveVinesBodyWithBerriesStack.class)
                .vanillaItem(ItemId.CAVE_VINES_BODY_WITH_BERRIES)
                .build();
    }

    public static void initCaveVinesHeadWithBerries() {
        if (ItemTypes.CAVE_VINES_HEAD_WITH_BERRIES != null) return;
        ItemTypes.CAVE_VINES_HEAD_WITH_BERRIES = ItemTypeBuilder
                .builder(ItemCaveVinesHeadWithBerriesStack.class)
                .vanillaItem(ItemId.CAVE_VINES_HEAD_WITH_BERRIES)
                .build();
    }

    public static void initChain() {
        if (ItemTypes.CHAIN != null) return;
        ItemTypes.CHAIN = ItemTypeBuilder
                .builder(ItemChainStack.class)
                .vanillaItem(ItemId.CHAIN)
                .build();
    }

    public static void initChainCommandBlock() {
        if (ItemTypes.CHAIN_COMMAND_BLOCK != null) return;
        ItemTypes.CHAIN_COMMAND_BLOCK = ItemTypeBuilder
                .builder(ItemChainCommandBlockStack.class)
                .vanillaItem(ItemId.CHAIN_COMMAND_BLOCK)
                .build();
    }

    public static void initChainmailBoots() {
        if (ItemTypes.CHAINMAIL_BOOTS != null) return;
        ItemTypes.CHAINMAIL_BOOTS = ItemTypeBuilder
                .builder(ItemChainmailBootsStack.class)
                .vanillaItem(ItemId.CHAINMAIL_BOOTS)
                .build();
    }

    public static void initChainmailChestplate() {
        if (ItemTypes.CHAINMAIL_CHESTPLATE != null) return;
        ItemTypes.CHAINMAIL_CHESTPLATE = ItemTypeBuilder
                .builder(ItemChainmailChestplateStack.class)
                .vanillaItem(ItemId.CHAINMAIL_CHESTPLATE)
                .build();
    }

    public static void initChainmailHelmet() {
        if (ItemTypes.CHAINMAIL_HELMET != null) return;
        ItemTypes.CHAINMAIL_HELMET = ItemTypeBuilder
                .builder(ItemChainmailHelmetStack.class)
                .vanillaItem(ItemId.CHAINMAIL_HELMET)
                .build();
    }

    public static void initChainmailLeggings() {
        if (ItemTypes.CHAINMAIL_LEGGINGS != null) return;
        ItemTypes.CHAINMAIL_LEGGINGS = ItemTypeBuilder
                .builder(ItemChainmailLeggingsStack.class)
                .vanillaItem(ItemId.CHAINMAIL_LEGGINGS)
                .build();
    }

    public static void initCharcoal() {
        if (ItemTypes.CHARCOAL != null) return;
        ItemTypes.CHARCOAL = ItemTypeBuilder
                .builder(ItemCharcoalStack.class)
                .vanillaItem(ItemId.CHARCOAL)
                .build();
    }

    public static void initChemicalHeat() {
        if (ItemTypes.CHEMICAL_HEAT != null) return;
        ItemTypes.CHEMICAL_HEAT = ItemTypeBuilder
                .builder(ItemChemicalHeatStack.class)
                .vanillaItem(ItemId.CHEMICAL_HEAT)
                .build();
    }

    public static void initChemistryTable() {
        if (ItemTypes.CHEMISTRY_TABLE != null) return;
        ItemTypes.CHEMISTRY_TABLE = ItemTypeBuilder
                .builder(ItemChemistryTableStack.class)
                .vanillaItem(ItemId.CHEMISTRY_TABLE)
                .build();
    }

    public static void initCherryBoat() {
        if (ItemTypes.CHERRY_BOAT != null) return;
        ItemTypes.CHERRY_BOAT = ItemTypeBuilder
                .builder(ItemCherryBoatStack.class)
                .vanillaItem(ItemId.CHERRY_BOAT)
                .build();
    }

    public static void initCherryButton() {
        if (ItemTypes.CHERRY_BUTTON != null) return;
        ItemTypes.CHERRY_BUTTON = ItemTypeBuilder
                .builder(ItemCherryButtonStack.class)
                .vanillaItem(ItemId.CHERRY_BUTTON)
                .build();
    }

    public static void initCherryChestBoat() {
        if (ItemTypes.CHERRY_CHEST_BOAT != null) return;
        ItemTypes.CHERRY_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemCherryChestBoatStack.class)
                .vanillaItem(ItemId.CHERRY_CHEST_BOAT)
                .build();
    }

    public static void initCherryDoor() {
        if (ItemTypes.CHERRY_DOOR != null) return;
        ItemTypes.CHERRY_DOOR = ItemTypeBuilder
                .builder(ItemCherryDoorStack.class)
                .vanillaItem(ItemId.CHERRY_DOOR)
                .build();
    }

    public static void initCherryDoubleSlab() {
        if (ItemTypes.CHERRY_DOUBLE_SLAB != null) return;
        ItemTypes.CHERRY_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCherryDoubleSlabStack.class)
                .vanillaItem(ItemId.CHERRY_DOUBLE_SLAB)
                .build();
    }

    public static void initCherryFence() {
        if (ItemTypes.CHERRY_FENCE != null) return;
        ItemTypes.CHERRY_FENCE = ItemTypeBuilder
                .builder(ItemCherryFenceStack.class)
                .vanillaItem(ItemId.CHERRY_FENCE)
                .build();
    }

    public static void initCherryFenceGate() {
        if (ItemTypes.CHERRY_FENCE_GATE != null) return;
        ItemTypes.CHERRY_FENCE_GATE = ItemTypeBuilder
                .builder(ItemCherryFenceGateStack.class)
                .vanillaItem(ItemId.CHERRY_FENCE_GATE)
                .build();
    }

    public static void initCherryHangingSign() {
        if (ItemTypes.CHERRY_HANGING_SIGN != null) return;
        ItemTypes.CHERRY_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemCherryHangingSignStack.class)
                .vanillaItem(ItemId.CHERRY_HANGING_SIGN)
                .build();
    }

    public static void initCherryLeaves() {
        if (ItemTypes.CHERRY_LEAVES != null) return;
        ItemTypes.CHERRY_LEAVES = ItemTypeBuilder
                .builder(ItemCherryLeavesStack.class)
                .vanillaItem(ItemId.CHERRY_LEAVES)
                .build();
    }

    public static void initCherryLog() {
        if (ItemTypes.CHERRY_LOG != null) return;
        ItemTypes.CHERRY_LOG = ItemTypeBuilder
                .builder(ItemCherryLogStack.class)
                .vanillaItem(ItemId.CHERRY_LOG)
                .build();
    }

    public static void initCherryPlanks() {
        if (ItemTypes.CHERRY_PLANKS != null) return;
        ItemTypes.CHERRY_PLANKS = ItemTypeBuilder
                .builder(ItemCherryPlanksStack.class)
                .vanillaItem(ItemId.CHERRY_PLANKS)
                .build();
    }

    public static void initCherryPressurePlate() {
        if (ItemTypes.CHERRY_PRESSURE_PLATE != null) return;
        ItemTypes.CHERRY_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemCherryPressurePlateStack.class)
                .vanillaItem(ItemId.CHERRY_PRESSURE_PLATE)
                .build();
    }

    public static void initCherrySapling() {
        if (ItemTypes.CHERRY_SAPLING != null) return;
        ItemTypes.CHERRY_SAPLING = ItemTypeBuilder
                .builder(ItemCherrySaplingStack.class)
                .vanillaItem(ItemId.CHERRY_SAPLING)
                .build();
    }

    public static void initCherrySign() {
        if (ItemTypes.CHERRY_SIGN != null) return;
        ItemTypes.CHERRY_SIGN = ItemTypeBuilder
                .builder(ItemCherrySignStack.class)
                .vanillaItem(ItemId.CHERRY_SIGN)
                .build();
    }

    public static void initCherrySlab() {
        if (ItemTypes.CHERRY_SLAB != null) return;
        ItemTypes.CHERRY_SLAB = ItemTypeBuilder
                .builder(ItemCherrySlabStack.class)
                .vanillaItem(ItemId.CHERRY_SLAB)
                .build();
    }

    public static void initCherryStairs() {
        if (ItemTypes.CHERRY_STAIRS != null) return;
        ItemTypes.CHERRY_STAIRS = ItemTypeBuilder
                .builder(ItemCherryStairsStack.class)
                .vanillaItem(ItemId.CHERRY_STAIRS)
                .build();
    }

    public static void initCherryStandingSign() {
        if (ItemTypes.CHERRY_STANDING_SIGN != null) return;
        ItemTypes.CHERRY_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemCherryStandingSignStack.class)
                .vanillaItem(ItemId.CHERRY_STANDING_SIGN)
                .build();
    }

    public static void initCherryTrapdoor() {
        if (ItemTypes.CHERRY_TRAPDOOR != null) return;
        ItemTypes.CHERRY_TRAPDOOR = ItemTypeBuilder
                .builder(ItemCherryTrapdoorStack.class)
                .vanillaItem(ItemId.CHERRY_TRAPDOOR)
                .build();
    }

    public static void initCherryWallSign() {
        if (ItemTypes.CHERRY_WALL_SIGN != null) return;
        ItemTypes.CHERRY_WALL_SIGN = ItemTypeBuilder
                .builder(ItemCherryWallSignStack.class)
                .vanillaItem(ItemId.CHERRY_WALL_SIGN)
                .build();
    }

    public static void initCherryWood() {
        if (ItemTypes.CHERRY_WOOD != null) return;
        ItemTypes.CHERRY_WOOD = ItemTypeBuilder
                .builder(ItemCherryWoodStack.class)
                .vanillaItem(ItemId.CHERRY_WOOD)
                .build();
    }

    public static void initChest() {
        if (ItemTypes.CHEST != null) return;
        ItemTypes.CHEST = ItemTypeBuilder
                .builder(ItemChestStack.class)
                .vanillaItem(ItemId.CHEST)
                .build();
    }

    public static void initChestBoat() {
        if (ItemTypes.CHEST_BOAT != null) return;
        ItemTypes.CHEST_BOAT = ItemTypeBuilder
                .builder(ItemChestBoatStack.class)
                .vanillaItem(ItemId.CHEST_BOAT)
                .build();
    }

    public static void initChestMinecart() {
        if (ItemTypes.CHEST_MINECART != null) return;
        ItemTypes.CHEST_MINECART = ItemTypeBuilder
                .builder(ItemChestMinecartStack.class)
                .vanillaItem(ItemId.CHEST_MINECART)
                .build();
    }

    public static void initChicken() {
        if (ItemTypes.CHICKEN != null) return;
        ItemTypes.CHICKEN = ItemTypeBuilder
                .builder(ItemChickenStack.class)
                .vanillaItem(ItemId.CHICKEN)
                .build();
    }

    public static void initChickenSpawnEgg() {
        if (ItemTypes.CHICKEN_SPAWN_EGG != null) return;
        ItemTypes.CHICKEN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemChickenSpawnEggStack.class)
                .vanillaItem(ItemId.CHICKEN_SPAWN_EGG)
                .build();
    }

    public static void initChippedAnvil() {
        if (ItemTypes.CHIPPED_ANVIL != null) return;
        ItemTypes.CHIPPED_ANVIL = ItemTypeBuilder
                .builder(ItemChippedAnvilStack.class)
                .vanillaItem(ItemId.CHIPPED_ANVIL)
                .build();
    }

    public static void initChiseledBookshelf() {
        if (ItemTypes.CHISELED_BOOKSHELF != null) return;
        ItemTypes.CHISELED_BOOKSHELF = ItemTypeBuilder
                .builder(ItemChiseledBookshelfStack.class)
                .vanillaItem(ItemId.CHISELED_BOOKSHELF)
                .build();
    }

    public static void initChiseledCopper() {
        if (ItemTypes.CHISELED_COPPER != null) return;
        ItemTypes.CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemChiseledCopperStack.class)
                .vanillaItem(ItemId.CHISELED_COPPER)
                .build();
    }

    public static void initChiseledDeepslate() {
        if (ItemTypes.CHISELED_DEEPSLATE != null) return;
        ItemTypes.CHISELED_DEEPSLATE = ItemTypeBuilder
                .builder(ItemChiseledDeepslateStack.class)
                .vanillaItem(ItemId.CHISELED_DEEPSLATE)
                .build();
    }

    public static void initChiseledNetherBricks() {
        if (ItemTypes.CHISELED_NETHER_BRICKS != null) return;
        ItemTypes.CHISELED_NETHER_BRICKS = ItemTypeBuilder
                .builder(ItemChiseledNetherBricksStack.class)
                .vanillaItem(ItemId.CHISELED_NETHER_BRICKS)
                .build();
    }

    public static void initChiseledPolishedBlackstone() {
        if (ItemTypes.CHISELED_POLISHED_BLACKSTONE != null) return;
        ItemTypes.CHISELED_POLISHED_BLACKSTONE = ItemTypeBuilder
                .builder(ItemChiseledPolishedBlackstoneStack.class)
                .vanillaItem(ItemId.CHISELED_POLISHED_BLACKSTONE)
                .build();
    }

    public static void initChiseledQuartzBlock() {
        if (ItemTypes.CHISELED_QUARTZ_BLOCK != null) return;
        ItemTypes.CHISELED_QUARTZ_BLOCK = ItemTypeBuilder
                .builder(ItemChiseledQuartzBlockStack.class)
                .vanillaItem(ItemId.CHISELED_QUARTZ_BLOCK)
                .build();
    }

    public static void initChiseledRedSandstone() {
        if (ItemTypes.CHISELED_RED_SANDSTONE != null) return;
        ItemTypes.CHISELED_RED_SANDSTONE = ItemTypeBuilder
                .builder(ItemChiseledRedSandstoneStack.class)
                .vanillaItem(ItemId.CHISELED_RED_SANDSTONE)
                .build();
    }

    public static void initChiseledSandstone() {
        if (ItemTypes.CHISELED_SANDSTONE != null) return;
        ItemTypes.CHISELED_SANDSTONE = ItemTypeBuilder
                .builder(ItemChiseledSandstoneStack.class)
                .vanillaItem(ItemId.CHISELED_SANDSTONE)
                .build();
    }

    public static void initChiseledStoneBricks() {
        if (ItemTypes.CHISELED_STONE_BRICKS != null) return;
        ItemTypes.CHISELED_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemChiseledStoneBricksStack.class)
                .vanillaItem(ItemId.CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initChiseledTuff() {
        if (ItemTypes.CHISELED_TUFF != null) return;
        ItemTypes.CHISELED_TUFF = ItemTypeBuilder
                .builder(ItemChiseledTuffStack.class)
                .vanillaItem(ItemId.CHISELED_TUFF)
                .build();
    }

    public static void initChiseledTuffBricks() {
        if (ItemTypes.CHISELED_TUFF_BRICKS != null) return;
        ItemTypes.CHISELED_TUFF_BRICKS = ItemTypeBuilder
                .builder(ItemChiseledTuffBricksStack.class)
                .vanillaItem(ItemId.CHISELED_TUFF_BRICKS)
                .build();
    }

    public static void initChorusFlower() {
        if (ItemTypes.CHORUS_FLOWER != null) return;
        ItemTypes.CHORUS_FLOWER = ItemTypeBuilder
                .builder(ItemChorusFlowerStack.class)
                .vanillaItem(ItemId.CHORUS_FLOWER)
                .build();
    }

    public static void initChorusFruit() {
        if (ItemTypes.CHORUS_FRUIT != null) return;
        ItemTypes.CHORUS_FRUIT = ItemTypeBuilder
                .builder(ItemChorusFruitStack.class)
                .vanillaItem(ItemId.CHORUS_FRUIT)
                .build();
    }

    public static void initChorusPlant() {
        if (ItemTypes.CHORUS_PLANT != null) return;
        ItemTypes.CHORUS_PLANT = ItemTypeBuilder
                .builder(ItemChorusPlantStack.class)
                .vanillaItem(ItemId.CHORUS_PLANT)
                .build();
    }

    public static void initClay() {
        if (ItemTypes.CLAY != null) return;
        ItemTypes.CLAY = ItemTypeBuilder
                .builder(ItemClayStack.class)
                .vanillaItem(ItemId.CLAY)
                .build();
    }

    public static void initClayBall() {
        if (ItemTypes.CLAY_BALL != null) return;
        ItemTypes.CLAY_BALL = ItemTypeBuilder
                .builder(ItemClayBallStack.class)
                .vanillaItem(ItemId.CLAY_BALL)
                .build();
    }

    public static void initClientRequestPlaceholderBlock() {
        if (ItemTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK != null) return;
        ItemTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = ItemTypeBuilder
                .builder(ItemClientRequestPlaceholderBlockStack.class)
                .vanillaItem(ItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                .build();
    }

    public static void initClock() {
        if (ItemTypes.CLOCK != null) return;
        ItemTypes.CLOCK = ItemTypeBuilder
                .builder(ItemClockStack.class)
                .vanillaItem(ItemId.CLOCK)
                .build();
    }

    public static void initCoal() {
        if (ItemTypes.COAL != null) return;
        ItemTypes.COAL = ItemTypeBuilder
                .builder(ItemCoalStack.class)
                .vanillaItem(ItemId.COAL)
                .build();
    }

    public static void initCoalBlock() {
        if (ItemTypes.COAL_BLOCK != null) return;
        ItemTypes.COAL_BLOCK = ItemTypeBuilder
                .builder(ItemCoalBlockStack.class)
                .vanillaItem(ItemId.COAL_BLOCK)
                .build();
    }

    public static void initCoalOre() {
        if (ItemTypes.COAL_ORE != null) return;
        ItemTypes.COAL_ORE = ItemTypeBuilder
                .builder(ItemCoalOreStack.class)
                .vanillaItem(ItemId.COAL_ORE)
                .build();
    }

    public static void initCoarseDirt() {
        if (ItemTypes.COARSE_DIRT != null) return;
        ItemTypes.COARSE_DIRT = ItemTypeBuilder
                .builder(ItemCoarseDirtStack.class)
                .vanillaItem(ItemId.COARSE_DIRT)
                .build();
    }

    public static void initCoastArmorTrimSmithingTemplate() {
        if (ItemTypes.COAST_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.COAST_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemCoastArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initCobbledDeepslate() {
        if (ItemTypes.COBBLED_DEEPSLATE != null) return;
        ItemTypes.COBBLED_DEEPSLATE = ItemTypeBuilder
                .builder(ItemCobbledDeepslateStack.class)
                .vanillaItem(ItemId.COBBLED_DEEPSLATE)
                .build();
    }

    public static void initCobbledDeepslateDoubleSlab() {
        if (ItemTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB != null) return;
        ItemTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCobbledDeepslateDoubleSlabStack.class)
                .vanillaItem(ItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                .build();
    }

    public static void initCobbledDeepslateSlab() {
        if (ItemTypes.COBBLED_DEEPSLATE_SLAB != null) return;
        ItemTypes.COBBLED_DEEPSLATE_SLAB = ItemTypeBuilder
                .builder(ItemCobbledDeepslateSlabStack.class)
                .vanillaItem(ItemId.COBBLED_DEEPSLATE_SLAB)
                .build();
    }

    public static void initCobbledDeepslateStairs() {
        if (ItemTypes.COBBLED_DEEPSLATE_STAIRS != null) return;
        ItemTypes.COBBLED_DEEPSLATE_STAIRS = ItemTypeBuilder
                .builder(ItemCobbledDeepslateStairsStack.class)
                .vanillaItem(ItemId.COBBLED_DEEPSLATE_STAIRS)
                .build();
    }

    public static void initCobbledDeepslateWall() {
        if (ItemTypes.COBBLED_DEEPSLATE_WALL != null) return;
        ItemTypes.COBBLED_DEEPSLATE_WALL = ItemTypeBuilder
                .builder(ItemCobbledDeepslateWallStack.class)
                .vanillaItem(ItemId.COBBLED_DEEPSLATE_WALL)
                .build();
    }

    public static void initCobblestone() {
        if (ItemTypes.COBBLESTONE != null) return;
        ItemTypes.COBBLESTONE = ItemTypeBuilder
                .builder(ItemCobblestoneStack.class)
                .vanillaItem(ItemId.COBBLESTONE)
                .build();
    }

    public static void initCobblestoneDoubleSlab() {
        if (ItemTypes.COBBLESTONE_DOUBLE_SLAB != null) return;
        ItemTypes.COBBLESTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCobblestoneDoubleSlabStack.class)
                .vanillaItem(ItemId.COBBLESTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initCobblestoneSlab() {
        if (ItemTypes.COBBLESTONE_SLAB != null) return;
        ItemTypes.COBBLESTONE_SLAB = ItemTypeBuilder
                .builder(ItemCobblestoneSlabStack.class)
                .vanillaItem(ItemId.COBBLESTONE_SLAB)
                .build();
    }

    public static void initCobblestoneWall() {
        if (ItemTypes.COBBLESTONE_WALL != null) return;
        ItemTypes.COBBLESTONE_WALL = ItemTypeBuilder
                .builder(ItemCobblestoneWallStack.class)
                .vanillaItem(ItemId.COBBLESTONE_WALL)
                .build();
    }

    public static void initCocoa() {
        if (ItemTypes.COCOA != null) return;
        ItemTypes.COCOA = ItemTypeBuilder
                .builder(ItemCocoaStack.class)
                .vanillaItem(ItemId.COCOA)
                .build();
    }

    public static void initCocoaBeans() {
        if (ItemTypes.COCOA_BEANS != null) return;
        ItemTypes.COCOA_BEANS = ItemTypeBuilder
                .builder(ItemCocoaBeansStack.class)
                .vanillaItem(ItemId.COCOA_BEANS)
                .build();
    }

    public static void initCod() {
        if (ItemTypes.COD != null) return;
        ItemTypes.COD = ItemTypeBuilder
                .builder(ItemCodStack.class)
                .vanillaItem(ItemId.COD)
                .build();
    }

    public static void initCodBucket() {
        if (ItemTypes.COD_BUCKET != null) return;
        ItemTypes.COD_BUCKET = ItemTypeBuilder
                .builder(ItemCodBucketStack.class)
                .vanillaItem(ItemId.COD_BUCKET)
                .build();
    }

    public static void initCodSpawnEgg() {
        if (ItemTypes.COD_SPAWN_EGG != null) return;
        ItemTypes.COD_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCodSpawnEggStack.class)
                .vanillaItem(ItemId.COD_SPAWN_EGG)
                .build();
    }

    public static void initColoredTorchBp() {
        if (ItemTypes.COLORED_TORCH_BP != null) return;
        ItemTypes.COLORED_TORCH_BP = ItemTypeBuilder
                .builder(ItemColoredTorchBpStack.class)
                .vanillaItem(ItemId.COLORED_TORCH_BP)
                .build();
    }

    public static void initColoredTorchRg() {
        if (ItemTypes.COLORED_TORCH_RG != null) return;
        ItemTypes.COLORED_TORCH_RG = ItemTypeBuilder
                .builder(ItemColoredTorchRgStack.class)
                .vanillaItem(ItemId.COLORED_TORCH_RG)
                .build();
    }

    public static void initCommandBlock() {
        if (ItemTypes.COMMAND_BLOCK != null) return;
        ItemTypes.COMMAND_BLOCK = ItemTypeBuilder
                .builder(ItemCommandBlockStack.class)
                .vanillaItem(ItemId.COMMAND_BLOCK)
                .build();
    }

    public static void initCommandBlockMinecart() {
        if (ItemTypes.COMMAND_BLOCK_MINECART != null) return;
        ItemTypes.COMMAND_BLOCK_MINECART = ItemTypeBuilder
                .builder(ItemCommandBlockMinecartStack.class)
                .vanillaItem(ItemId.COMMAND_BLOCK_MINECART)
                .build();
    }

    public static void initComparator() {
        if (ItemTypes.COMPARATOR != null) return;
        ItemTypes.COMPARATOR = ItemTypeBuilder
                .builder(ItemComparatorStack.class)
                .vanillaItem(ItemId.COMPARATOR)
                .build();
    }

    public static void initCompass() {
        if (ItemTypes.COMPASS != null) return;
        ItemTypes.COMPASS = ItemTypeBuilder
                .builder(ItemCompassStack.class)
                .vanillaItem(ItemId.COMPASS)
                .build();
    }

    public static void initComposter() {
        if (ItemTypes.COMPOSTER != null) return;
        ItemTypes.COMPOSTER = ItemTypeBuilder
                .builder(ItemComposterStack.class)
                .vanillaItem(ItemId.COMPOSTER)
                .build();
    }

    public static void initCompound() {
        if (ItemTypes.COMPOUND != null) return;
        ItemTypes.COMPOUND = ItemTypeBuilder
                .builder(ItemCompoundStack.class)
                .vanillaItem(ItemId.COMPOUND)
                .build();
    }

    public static void initConcrete() {
        if (ItemTypes.CONCRETE != null) return;
        ItemTypes.CONCRETE = ItemTypeBuilder
                .builder(ItemConcreteStack.class)
                .vanillaItem(ItemId.CONCRETE)
                .build();
    }

    public static void initConcretePowder() {
        if (ItemTypes.CONCRETE_POWDER != null) return;
        ItemTypes.CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemConcretePowderStack.class)
                .vanillaItem(ItemId.CONCRETE_POWDER)
                .build();
    }

    public static void initConduit() {
        if (ItemTypes.CONDUIT != null) return;
        ItemTypes.CONDUIT = ItemTypeBuilder
                .builder(ItemConduitStack.class)
                .vanillaItem(ItemId.CONDUIT)
                .build();
    }

    public static void initCookedBeef() {
        if (ItemTypes.COOKED_BEEF != null) return;
        ItemTypes.COOKED_BEEF = ItemTypeBuilder
                .builder(ItemCookedBeefStack.class)
                .vanillaItem(ItemId.COOKED_BEEF)
                .build();
    }

    public static void initCookedChicken() {
        if (ItemTypes.COOKED_CHICKEN != null) return;
        ItemTypes.COOKED_CHICKEN = ItemTypeBuilder
                .builder(ItemCookedChickenStack.class)
                .vanillaItem(ItemId.COOKED_CHICKEN)
                .build();
    }

    public static void initCookedCod() {
        if (ItemTypes.COOKED_COD != null) return;
        ItemTypes.COOKED_COD = ItemTypeBuilder
                .builder(ItemCookedCodStack.class)
                .vanillaItem(ItemId.COOKED_COD)
                .build();
    }

    public static void initCookedMutton() {
        if (ItemTypes.COOKED_MUTTON != null) return;
        ItemTypes.COOKED_MUTTON = ItemTypeBuilder
                .builder(ItemCookedMuttonStack.class)
                .vanillaItem(ItemId.COOKED_MUTTON)
                .build();
    }

    public static void initCookedPorkchop() {
        if (ItemTypes.COOKED_PORKCHOP != null) return;
        ItemTypes.COOKED_PORKCHOP = ItemTypeBuilder
                .builder(ItemCookedPorkchopStack.class)
                .vanillaItem(ItemId.COOKED_PORKCHOP)
                .build();
    }

    public static void initCookedRabbit() {
        if (ItemTypes.COOKED_RABBIT != null) return;
        ItemTypes.COOKED_RABBIT = ItemTypeBuilder
                .builder(ItemCookedRabbitStack.class)
                .vanillaItem(ItemId.COOKED_RABBIT)
                .build();
    }

    public static void initCookedSalmon() {
        if (ItemTypes.COOKED_SALMON != null) return;
        ItemTypes.COOKED_SALMON = ItemTypeBuilder
                .builder(ItemCookedSalmonStack.class)
                .vanillaItem(ItemId.COOKED_SALMON)
                .build();
    }

    public static void initCookie() {
        if (ItemTypes.COOKIE != null) return;
        ItemTypes.COOKIE = ItemTypeBuilder
                .builder(ItemCookieStack.class)
                .vanillaItem(ItemId.COOKIE)
                .build();
    }

    public static void initCopperBlock() {
        if (ItemTypes.COPPER_BLOCK != null) return;
        ItemTypes.COPPER_BLOCK = ItemTypeBuilder
                .builder(ItemCopperBlockStack.class)
                .vanillaItem(ItemId.COPPER_BLOCK)
                .build();
    }

    public static void initCopperBulb() {
        if (ItemTypes.COPPER_BULB != null) return;
        ItemTypes.COPPER_BULB = ItemTypeBuilder
                .builder(ItemCopperBulbStack.class)
                .vanillaItem(ItemId.COPPER_BULB)
                .build();
    }

    public static void initCopperDoor() {
        if (ItemTypes.COPPER_DOOR != null) return;
        ItemTypes.COPPER_DOOR = ItemTypeBuilder
                .builder(ItemCopperDoorStack.class)
                .vanillaItem(ItemId.COPPER_DOOR)
                .build();
    }

    public static void initCopperGrate() {
        if (ItemTypes.COPPER_GRATE != null) return;
        ItemTypes.COPPER_GRATE = ItemTypeBuilder
                .builder(ItemCopperGrateStack.class)
                .vanillaItem(ItemId.COPPER_GRATE)
                .build();
    }

    public static void initCopperIngot() {
        if (ItemTypes.COPPER_INGOT != null) return;
        ItemTypes.COPPER_INGOT = ItemTypeBuilder
                .builder(ItemCopperIngotStack.class)
                .vanillaItem(ItemId.COPPER_INGOT)
                .build();
    }

    public static void initCopperOre() {
        if (ItemTypes.COPPER_ORE != null) return;
        ItemTypes.COPPER_ORE = ItemTypeBuilder
                .builder(ItemCopperOreStack.class)
                .vanillaItem(ItemId.COPPER_ORE)
                .build();
    }

    public static void initCopperTrapdoor() {
        if (ItemTypes.COPPER_TRAPDOOR != null) return;
        ItemTypes.COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemCopperTrapdoorStack.class)
                .vanillaItem(ItemId.COPPER_TRAPDOOR)
                .build();
    }

    public static void initCoral() {
        if (ItemTypes.CORAL != null) return;
        ItemTypes.CORAL = ItemTypeBuilder
                .builder(ItemCoralStack.class)
                .vanillaItem(ItemId.CORAL)
                .build();
    }

    public static void initCoralBlock() {
        if (ItemTypes.CORAL_BLOCK != null) return;
        ItemTypes.CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemCoralBlockStack.class)
                .vanillaItem(ItemId.CORAL_BLOCK)
                .build();
    }

    public static void initCoralFan() {
        if (ItemTypes.CORAL_FAN != null) return;
        ItemTypes.CORAL_FAN = ItemTypeBuilder
                .builder(ItemCoralFanStack.class)
                .vanillaItem(ItemId.CORAL_FAN)
                .build();
    }

    public static void initCoralFanDead() {
        if (ItemTypes.CORAL_FAN_DEAD != null) return;
        ItemTypes.CORAL_FAN_DEAD = ItemTypeBuilder
                .builder(ItemCoralFanDeadStack.class)
                .vanillaItem(ItemId.CORAL_FAN_DEAD)
                .build();
    }

    public static void initCornflower() {
        if (ItemTypes.CORNFLOWER != null) return;
        ItemTypes.CORNFLOWER = ItemTypeBuilder
                .builder(ItemCornflowerStack.class)
                .vanillaItem(ItemId.CORNFLOWER)
                .build();
    }

    public static void initCowSpawnEgg() {
        if (ItemTypes.COW_SPAWN_EGG != null) return;
        ItemTypes.COW_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCowSpawnEggStack.class)
                .vanillaItem(ItemId.COW_SPAWN_EGG)
                .build();
    }

    public static void initCrackedDeepslateBricks() {
        if (ItemTypes.CRACKED_DEEPSLATE_BRICKS != null) return;
        ItemTypes.CRACKED_DEEPSLATE_BRICKS = ItemTypeBuilder
                .builder(ItemCrackedDeepslateBricksStack.class)
                .vanillaItem(ItemId.CRACKED_DEEPSLATE_BRICKS)
                .build();
    }

    public static void initCrackedDeepslateTiles() {
        if (ItemTypes.CRACKED_DEEPSLATE_TILES != null) return;
        ItemTypes.CRACKED_DEEPSLATE_TILES = ItemTypeBuilder
                .builder(ItemCrackedDeepslateTilesStack.class)
                .vanillaItem(ItemId.CRACKED_DEEPSLATE_TILES)
                .build();
    }

    public static void initCrackedNetherBricks() {
        if (ItemTypes.CRACKED_NETHER_BRICKS != null) return;
        ItemTypes.CRACKED_NETHER_BRICKS = ItemTypeBuilder
                .builder(ItemCrackedNetherBricksStack.class)
                .vanillaItem(ItemId.CRACKED_NETHER_BRICKS)
                .build();
    }

    public static void initCrackedPolishedBlackstoneBricks() {
        if (ItemTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS != null) return;
        ItemTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = ItemTypeBuilder
                .builder(ItemCrackedPolishedBlackstoneBricksStack.class)
                .vanillaItem(ItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initCrackedStoneBricks() {
        if (ItemTypes.CRACKED_STONE_BRICKS != null) return;
        ItemTypes.CRACKED_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemCrackedStoneBricksStack.class)
                .vanillaItem(ItemId.CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initCrafter() {
        if (ItemTypes.CRAFTER != null) return;
        ItemTypes.CRAFTER = ItemTypeBuilder
                .builder(ItemCrafterStack.class)
                .vanillaItem(ItemId.CRAFTER)
                .build();
    }

    public static void initCraftingTable() {
        if (ItemTypes.CRAFTING_TABLE != null) return;
        ItemTypes.CRAFTING_TABLE = ItemTypeBuilder
                .builder(ItemCraftingTableStack.class)
                .vanillaItem(ItemId.CRAFTING_TABLE)
                .build();
    }

    public static void initCreeperBannerPattern() {
        if (ItemTypes.CREEPER_BANNER_PATTERN != null) return;
        ItemTypes.CREEPER_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemCreeperBannerPatternStack.class)
                .vanillaItem(ItemId.CREEPER_BANNER_PATTERN)
                .build();
    }

    public static void initCreeperSpawnEgg() {
        if (ItemTypes.CREEPER_SPAWN_EGG != null) return;
        ItemTypes.CREEPER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCreeperSpawnEggStack.class)
                .vanillaItem(ItemId.CREEPER_SPAWN_EGG)
                .build();
    }

    public static void initCrimsonButton() {
        if (ItemTypes.CRIMSON_BUTTON != null) return;
        ItemTypes.CRIMSON_BUTTON = ItemTypeBuilder
                .builder(ItemCrimsonButtonStack.class)
                .vanillaItem(ItemId.CRIMSON_BUTTON)
                .build();
    }

    public static void initCrimsonDoor() {
        if (ItemTypes.CRIMSON_DOOR != null) return;
        ItemTypes.CRIMSON_DOOR = ItemTypeBuilder
                .builder(ItemCrimsonDoorStack.class)
                .vanillaItem(ItemId.CRIMSON_DOOR)
                .build();
    }

    public static void initCrimsonDoubleSlab() {
        if (ItemTypes.CRIMSON_DOUBLE_SLAB != null) return;
        ItemTypes.CRIMSON_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCrimsonDoubleSlabStack.class)
                .vanillaItem(ItemId.CRIMSON_DOUBLE_SLAB)
                .build();
    }

    public static void initCrimsonFence() {
        if (ItemTypes.CRIMSON_FENCE != null) return;
        ItemTypes.CRIMSON_FENCE = ItemTypeBuilder
                .builder(ItemCrimsonFenceStack.class)
                .vanillaItem(ItemId.CRIMSON_FENCE)
                .build();
    }

    public static void initCrimsonFenceGate() {
        if (ItemTypes.CRIMSON_FENCE_GATE != null) return;
        ItemTypes.CRIMSON_FENCE_GATE = ItemTypeBuilder
                .builder(ItemCrimsonFenceGateStack.class)
                .vanillaItem(ItemId.CRIMSON_FENCE_GATE)
                .build();
    }

    public static void initCrimsonFungus() {
        if (ItemTypes.CRIMSON_FUNGUS != null) return;
        ItemTypes.CRIMSON_FUNGUS = ItemTypeBuilder
                .builder(ItemCrimsonFungusStack.class)
                .vanillaItem(ItemId.CRIMSON_FUNGUS)
                .build();
    }

    public static void initCrimsonHangingSign() {
        if (ItemTypes.CRIMSON_HANGING_SIGN != null) return;
        ItemTypes.CRIMSON_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemCrimsonHangingSignStack.class)
                .vanillaItem(ItemId.CRIMSON_HANGING_SIGN)
                .build();
    }

    public static void initCrimsonHyphae() {
        if (ItemTypes.CRIMSON_HYPHAE != null) return;
        ItemTypes.CRIMSON_HYPHAE = ItemTypeBuilder
                .builder(ItemCrimsonHyphaeStack.class)
                .vanillaItem(ItemId.CRIMSON_HYPHAE)
                .build();
    }

    public static void initCrimsonNylium() {
        if (ItemTypes.CRIMSON_NYLIUM != null) return;
        ItemTypes.CRIMSON_NYLIUM = ItemTypeBuilder
                .builder(ItemCrimsonNyliumStack.class)
                .vanillaItem(ItemId.CRIMSON_NYLIUM)
                .build();
    }

    public static void initCrimsonPlanks() {
        if (ItemTypes.CRIMSON_PLANKS != null) return;
        ItemTypes.CRIMSON_PLANKS = ItemTypeBuilder
                .builder(ItemCrimsonPlanksStack.class)
                .vanillaItem(ItemId.CRIMSON_PLANKS)
                .build();
    }

    public static void initCrimsonPressurePlate() {
        if (ItemTypes.CRIMSON_PRESSURE_PLATE != null) return;
        ItemTypes.CRIMSON_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemCrimsonPressurePlateStack.class)
                .vanillaItem(ItemId.CRIMSON_PRESSURE_PLATE)
                .build();
    }

    public static void initCrimsonRoots() {
        if (ItemTypes.CRIMSON_ROOTS != null) return;
        ItemTypes.CRIMSON_ROOTS = ItemTypeBuilder
                .builder(ItemCrimsonRootsStack.class)
                .vanillaItem(ItemId.CRIMSON_ROOTS)
                .build();
    }

    public static void initCrimsonSign() {
        if (ItemTypes.CRIMSON_SIGN != null) return;
        ItemTypes.CRIMSON_SIGN = ItemTypeBuilder
                .builder(ItemCrimsonSignStack.class)
                .vanillaItem(ItemId.CRIMSON_SIGN)
                .build();
    }

    public static void initCrimsonSlab() {
        if (ItemTypes.CRIMSON_SLAB != null) return;
        ItemTypes.CRIMSON_SLAB = ItemTypeBuilder
                .builder(ItemCrimsonSlabStack.class)
                .vanillaItem(ItemId.CRIMSON_SLAB)
                .build();
    }

    public static void initCrimsonStairs() {
        if (ItemTypes.CRIMSON_STAIRS != null) return;
        ItemTypes.CRIMSON_STAIRS = ItemTypeBuilder
                .builder(ItemCrimsonStairsStack.class)
                .vanillaItem(ItemId.CRIMSON_STAIRS)
                .build();
    }

    public static void initCrimsonStandingSign() {
        if (ItemTypes.CRIMSON_STANDING_SIGN != null) return;
        ItemTypes.CRIMSON_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemCrimsonStandingSignStack.class)
                .vanillaItem(ItemId.CRIMSON_STANDING_SIGN)
                .build();
    }

    public static void initCrimsonStem() {
        if (ItemTypes.CRIMSON_STEM != null) return;
        ItemTypes.CRIMSON_STEM = ItemTypeBuilder
                .builder(ItemCrimsonStemStack.class)
                .vanillaItem(ItemId.CRIMSON_STEM)
                .build();
    }

    public static void initCrimsonTrapdoor() {
        if (ItemTypes.CRIMSON_TRAPDOOR != null) return;
        ItemTypes.CRIMSON_TRAPDOOR = ItemTypeBuilder
                .builder(ItemCrimsonTrapdoorStack.class)
                .vanillaItem(ItemId.CRIMSON_TRAPDOOR)
                .build();
    }

    public static void initCrimsonWallSign() {
        if (ItemTypes.CRIMSON_WALL_SIGN != null) return;
        ItemTypes.CRIMSON_WALL_SIGN = ItemTypeBuilder
                .builder(ItemCrimsonWallSignStack.class)
                .vanillaItem(ItemId.CRIMSON_WALL_SIGN)
                .build();
    }

    public static void initCrossbow() {
        if (ItemTypes.CROSSBOW != null) return;
        ItemTypes.CROSSBOW = ItemTypeBuilder
                .builder(ItemCrossbowStack.class)
                .vanillaItem(ItemId.CROSSBOW)
                .build();
    }

    public static void initCryingObsidian() {
        if (ItemTypes.CRYING_OBSIDIAN != null) return;
        ItemTypes.CRYING_OBSIDIAN = ItemTypeBuilder
                .builder(ItemCryingObsidianStack.class)
                .vanillaItem(ItemId.CRYING_OBSIDIAN)
                .build();
    }

    public static void initCutCopper() {
        if (ItemTypes.CUT_COPPER != null) return;
        ItemTypes.CUT_COPPER = ItemTypeBuilder
                .builder(ItemCutCopperStack.class)
                .vanillaItem(ItemId.CUT_COPPER)
                .build();
    }

    public static void initCutCopperSlab() {
        if (ItemTypes.CUT_COPPER_SLAB != null) return;
        ItemTypes.CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemCutCopperSlabStack.class)
                .vanillaItem(ItemId.CUT_COPPER_SLAB)
                .build();
    }

    public static void initCutCopperStairs() {
        if (ItemTypes.CUT_COPPER_STAIRS != null) return;
        ItemTypes.CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemCutCopperStairsStack.class)
                .vanillaItem(ItemId.CUT_COPPER_STAIRS)
                .build();
    }

    public static void initCutRedSandstone() {
        if (ItemTypes.CUT_RED_SANDSTONE != null) return;
        ItemTypes.CUT_RED_SANDSTONE = ItemTypeBuilder
                .builder(ItemCutRedSandstoneStack.class)
                .vanillaItem(ItemId.CUT_RED_SANDSTONE)
                .build();
    }

    public static void initCutRedSandstoneDoubleSlab() {
        if (ItemTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCutRedSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initCutRedSandstoneSlab() {
        if (ItemTypes.CUT_RED_SANDSTONE_SLAB != null) return;
        ItemTypes.CUT_RED_SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemCutRedSandstoneSlabStack.class)
                .vanillaItem(ItemId.CUT_RED_SANDSTONE_SLAB)
                .build();
    }

    public static void initCutSandstone() {
        if (ItemTypes.CUT_SANDSTONE != null) return;
        ItemTypes.CUT_SANDSTONE = ItemTypeBuilder
                .builder(ItemCutSandstoneStack.class)
                .vanillaItem(ItemId.CUT_SANDSTONE)
                .build();
    }

    public static void initCutSandstoneDoubleSlab() {
        if (ItemTypes.CUT_SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.CUT_SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemCutSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.CUT_SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initCutSandstoneSlab() {
        if (ItemTypes.CUT_SANDSTONE_SLAB != null) return;
        ItemTypes.CUT_SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemCutSandstoneSlabStack.class)
                .vanillaItem(ItemId.CUT_SANDSTONE_SLAB)
                .build();
    }

    public static void initCyanCandle() {
        if (ItemTypes.CYAN_CANDLE != null) return;
        ItemTypes.CYAN_CANDLE = ItemTypeBuilder
                .builder(ItemCyanCandleStack.class)
                .vanillaItem(ItemId.CYAN_CANDLE)
                .build();
    }

    public static void initCyanCandleCake() {
        if (ItemTypes.CYAN_CANDLE_CAKE != null) return;
        ItemTypes.CYAN_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemCyanCandleCakeStack.class)
                .vanillaItem(ItemId.CYAN_CANDLE_CAKE)
                .build();
    }

    public static void initCyanCarpet() {
        if (ItemTypes.CYAN_CARPET != null) return;
        ItemTypes.CYAN_CARPET = ItemTypeBuilder
                .builder(ItemCyanCarpetStack.class)
                .vanillaItem(ItemId.CYAN_CARPET)
                .build();
    }

    public static void initCyanConcrete() {
        if (ItemTypes.CYAN_CONCRETE != null) return;
        ItemTypes.CYAN_CONCRETE = ItemTypeBuilder
                .builder(ItemCyanConcreteStack.class)
                .vanillaItem(ItemId.CYAN_CONCRETE)
                .build();
    }

    public static void initCyanConcretePowder() {
        if (ItemTypes.CYAN_CONCRETE_POWDER != null) return;
        ItemTypes.CYAN_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemCyanConcretePowderStack.class)
                .vanillaItem(ItemId.CYAN_CONCRETE_POWDER)
                .build();
    }

    public static void initCyanDye() {
        if (ItemTypes.CYAN_DYE != null) return;
        ItemTypes.CYAN_DYE = ItemTypeBuilder
                .builder(ItemCyanDyeStack.class)
                .vanillaItem(ItemId.CYAN_DYE)
                .build();
    }

    public static void initCyanGlazedTerracotta() {
        if (ItemTypes.CYAN_GLAZED_TERRACOTTA != null) return;
        ItemTypes.CYAN_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemCyanGlazedTerracottaStack.class)
                .vanillaItem(ItemId.CYAN_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initCyanShulkerBox() {
        if (ItemTypes.CYAN_SHULKER_BOX != null) return;
        ItemTypes.CYAN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemCyanShulkerBoxStack.class)
                .vanillaItem(ItemId.CYAN_SHULKER_BOX)
                .build();
    }

    public static void initCyanStainedGlass() {
        if (ItemTypes.CYAN_STAINED_GLASS != null) return;
        ItemTypes.CYAN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemCyanStainedGlassStack.class)
                .vanillaItem(ItemId.CYAN_STAINED_GLASS)
                .build();
    }

    public static void initCyanStainedGlassPane() {
        if (ItemTypes.CYAN_STAINED_GLASS_PANE != null) return;
        ItemTypes.CYAN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemCyanStainedGlassPaneStack.class)
                .vanillaItem(ItemId.CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initCyanTerracotta() {
        if (ItemTypes.CYAN_TERRACOTTA != null) return;
        ItemTypes.CYAN_TERRACOTTA = ItemTypeBuilder
                .builder(ItemCyanTerracottaStack.class)
                .vanillaItem(ItemId.CYAN_TERRACOTTA)
                .build();
    }

    public static void initCyanWool() {
        if (ItemTypes.CYAN_WOOL != null) return;
        ItemTypes.CYAN_WOOL = ItemTypeBuilder
                .builder(ItemCyanWoolStack.class)
                .vanillaItem(ItemId.CYAN_WOOL)
                .build();
    }

    public static void initDamagedAnvil() {
        if (ItemTypes.DAMAGED_ANVIL != null) return;
        ItemTypes.DAMAGED_ANVIL = ItemTypeBuilder
                .builder(ItemDamagedAnvilStack.class)
                .vanillaItem(ItemId.DAMAGED_ANVIL)
                .build();
    }

    public static void initDandelion() {
        if (ItemTypes.DANDELION != null) return;
        ItemTypes.DANDELION = ItemTypeBuilder
                .builder(ItemDandelionStack.class)
                .vanillaItem(ItemId.DANDELION)
                .build();
    }

    public static void initDangerPotterySherd() {
        if (ItemTypes.DANGER_POTTERY_SHERD != null) return;
        ItemTypes.DANGER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemDangerPotterySherdStack.class)
                .vanillaItem(ItemId.DANGER_POTTERY_SHERD)
                .build();
    }

    public static void initDarkOakBoat() {
        if (ItemTypes.DARK_OAK_BOAT != null) return;
        ItemTypes.DARK_OAK_BOAT = ItemTypeBuilder
                .builder(ItemDarkOakBoatStack.class)
                .vanillaItem(ItemId.DARK_OAK_BOAT)
                .build();
    }

    public static void initDarkOakButton() {
        if (ItemTypes.DARK_OAK_BUTTON != null) return;
        ItemTypes.DARK_OAK_BUTTON = ItemTypeBuilder
                .builder(ItemDarkOakButtonStack.class)
                .vanillaItem(ItemId.DARK_OAK_BUTTON)
                .build();
    }

    public static void initDarkOakChestBoat() {
        if (ItemTypes.DARK_OAK_CHEST_BOAT != null) return;
        ItemTypes.DARK_OAK_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemDarkOakChestBoatStack.class)
                .vanillaItem(ItemId.DARK_OAK_CHEST_BOAT)
                .build();
    }

    public static void initDarkOakDoor() {
        if (ItemTypes.DARK_OAK_DOOR != null) return;
        ItemTypes.DARK_OAK_DOOR = ItemTypeBuilder
                .builder(ItemDarkOakDoorStack.class)
                .vanillaItem(ItemId.DARK_OAK_DOOR)
                .build();
    }

    public static void initDarkOakDoubleSlab() {
        if (ItemTypes.DARK_OAK_DOUBLE_SLAB != null) return;
        ItemTypes.DARK_OAK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemDarkOakDoubleSlabStack.class)
                .vanillaItem(ItemId.DARK_OAK_DOUBLE_SLAB)
                .build();
    }

    public static void initDarkOakFence() {
        if (ItemTypes.DARK_OAK_FENCE != null) return;
        ItemTypes.DARK_OAK_FENCE = ItemTypeBuilder
                .builder(ItemDarkOakFenceStack.class)
                .vanillaItem(ItemId.DARK_OAK_FENCE)
                .build();
    }

    public static void initDarkOakFenceGate() {
        if (ItemTypes.DARK_OAK_FENCE_GATE != null) return;
        ItemTypes.DARK_OAK_FENCE_GATE = ItemTypeBuilder
                .builder(ItemDarkOakFenceGateStack.class)
                .vanillaItem(ItemId.DARK_OAK_FENCE_GATE)
                .build();
    }

    public static void initDarkOakHangingSign() {
        if (ItemTypes.DARK_OAK_HANGING_SIGN != null) return;
        ItemTypes.DARK_OAK_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemDarkOakHangingSignStack.class)
                .vanillaItem(ItemId.DARK_OAK_HANGING_SIGN)
                .build();
    }

    public static void initDarkOakLeaves() {
        if (ItemTypes.DARK_OAK_LEAVES != null) return;
        ItemTypes.DARK_OAK_LEAVES = ItemTypeBuilder
                .builder(ItemDarkOakLeavesStack.class)
                .vanillaItem(ItemId.DARK_OAK_LEAVES)
                .build();
    }

    public static void initDarkOakLog() {
        if (ItemTypes.DARK_OAK_LOG != null) return;
        ItemTypes.DARK_OAK_LOG = ItemTypeBuilder
                .builder(ItemDarkOakLogStack.class)
                .vanillaItem(ItemId.DARK_OAK_LOG)
                .build();
    }

    public static void initDarkOakPlanks() {
        if (ItemTypes.DARK_OAK_PLANKS != null) return;
        ItemTypes.DARK_OAK_PLANKS = ItemTypeBuilder
                .builder(ItemDarkOakPlanksStack.class)
                .vanillaItem(ItemId.DARK_OAK_PLANKS)
                .build();
    }

    public static void initDarkOakPressurePlate() {
        if (ItemTypes.DARK_OAK_PRESSURE_PLATE != null) return;
        ItemTypes.DARK_OAK_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemDarkOakPressurePlateStack.class)
                .vanillaItem(ItemId.DARK_OAK_PRESSURE_PLATE)
                .build();
    }

    public static void initDarkOakSapling() {
        if (ItemTypes.DARK_OAK_SAPLING != null) return;
        ItemTypes.DARK_OAK_SAPLING = ItemTypeBuilder
                .builder(ItemDarkOakSaplingStack.class)
                .vanillaItem(ItemId.DARK_OAK_SAPLING)
                .build();
    }

    public static void initDarkOakSign() {
        if (ItemTypes.DARK_OAK_SIGN != null) return;
        ItemTypes.DARK_OAK_SIGN = ItemTypeBuilder
                .builder(ItemDarkOakSignStack.class)
                .vanillaItem(ItemId.DARK_OAK_SIGN)
                .build();
    }

    public static void initDarkOakSlab() {
        if (ItemTypes.DARK_OAK_SLAB != null) return;
        ItemTypes.DARK_OAK_SLAB = ItemTypeBuilder
                .builder(ItemDarkOakSlabStack.class)
                .vanillaItem(ItemId.DARK_OAK_SLAB)
                .build();
    }

    public static void initDarkOakStairs() {
        if (ItemTypes.DARK_OAK_STAIRS != null) return;
        ItemTypes.DARK_OAK_STAIRS = ItemTypeBuilder
                .builder(ItemDarkOakStairsStack.class)
                .vanillaItem(ItemId.DARK_OAK_STAIRS)
                .build();
    }

    public static void initDarkOakTrapdoor() {
        if (ItemTypes.DARK_OAK_TRAPDOOR != null) return;
        ItemTypes.DARK_OAK_TRAPDOOR = ItemTypeBuilder
                .builder(ItemDarkOakTrapdoorStack.class)
                .vanillaItem(ItemId.DARK_OAK_TRAPDOOR)
                .build();
    }

    public static void initDarkOakWood() {
        if (ItemTypes.DARK_OAK_WOOD != null) return;
        ItemTypes.DARK_OAK_WOOD = ItemTypeBuilder
                .builder(ItemDarkOakWoodStack.class)
                .vanillaItem(ItemId.DARK_OAK_WOOD)
                .build();
    }

    public static void initDarkPrismarine() {
        if (ItemTypes.DARK_PRISMARINE != null) return;
        ItemTypes.DARK_PRISMARINE = ItemTypeBuilder
                .builder(ItemDarkPrismarineStack.class)
                .vanillaItem(ItemId.DARK_PRISMARINE)
                .build();
    }

    public static void initDarkPrismarineDoubleSlab() {
        if (ItemTypes.DARK_PRISMARINE_DOUBLE_SLAB != null) return;
        ItemTypes.DARK_PRISMARINE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemDarkPrismarineDoubleSlabStack.class)
                .vanillaItem(ItemId.DARK_PRISMARINE_DOUBLE_SLAB)
                .build();
    }

    public static void initDarkPrismarineSlab() {
        if (ItemTypes.DARK_PRISMARINE_SLAB != null) return;
        ItemTypes.DARK_PRISMARINE_SLAB = ItemTypeBuilder
                .builder(ItemDarkPrismarineSlabStack.class)
                .vanillaItem(ItemId.DARK_PRISMARINE_SLAB)
                .build();
    }

    public static void initDarkPrismarineStairs() {
        if (ItemTypes.DARK_PRISMARINE_STAIRS != null) return;
        ItemTypes.DARK_PRISMARINE_STAIRS = ItemTypeBuilder
                .builder(ItemDarkPrismarineStairsStack.class)
                .vanillaItem(ItemId.DARK_PRISMARINE_STAIRS)
                .build();
    }

    public static void initDarkoakStandingSign() {
        if (ItemTypes.DARKOAK_STANDING_SIGN != null) return;
        ItemTypes.DARKOAK_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemDarkoakStandingSignStack.class)
                .vanillaItem(ItemId.DARKOAK_STANDING_SIGN)
                .build();
    }

    public static void initDarkoakWallSign() {
        if (ItemTypes.DARKOAK_WALL_SIGN != null) return;
        ItemTypes.DARKOAK_WALL_SIGN = ItemTypeBuilder
                .builder(ItemDarkoakWallSignStack.class)
                .vanillaItem(ItemId.DARKOAK_WALL_SIGN)
                .build();
    }

    public static void initDaylightDetector() {
        if (ItemTypes.DAYLIGHT_DETECTOR != null) return;
        ItemTypes.DAYLIGHT_DETECTOR = ItemTypeBuilder
                .builder(ItemDaylightDetectorStack.class)
                .vanillaItem(ItemId.DAYLIGHT_DETECTOR)
                .build();
    }

    public static void initDaylightDetectorInverted() {
        if (ItemTypes.DAYLIGHT_DETECTOR_INVERTED != null) return;
        ItemTypes.DAYLIGHT_DETECTOR_INVERTED = ItemTypeBuilder
                .builder(ItemDaylightDetectorInvertedStack.class)
                .vanillaItem(ItemId.DAYLIGHT_DETECTOR_INVERTED)
                .build();
    }

    public static void initDeadBrainCoral() {
        if (ItemTypes.DEAD_BRAIN_CORAL != null) return;
        ItemTypes.DEAD_BRAIN_CORAL = ItemTypeBuilder
                .builder(ItemDeadBrainCoralStack.class)
                .vanillaItem(ItemId.DEAD_BRAIN_CORAL)
                .build();
    }

    public static void initDeadBrainCoralBlock() {
        if (ItemTypes.DEAD_BRAIN_CORAL_BLOCK != null) return;
        ItemTypes.DEAD_BRAIN_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemDeadBrainCoralBlockStack.class)
                .vanillaItem(ItemId.DEAD_BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBrainCoralFan() {
        if (ItemTypes.DEAD_BRAIN_CORAL_FAN != null) return;
        ItemTypes.DEAD_BRAIN_CORAL_FAN = ItemTypeBuilder
                .builder(ItemDeadBrainCoralFanStack.class)
                .vanillaItem(ItemId.DEAD_BRAIN_CORAL_FAN)
                .build();
    }

    public static void initDeadBrainCoralWallFan() {
        if (ItemTypes.DEAD_BRAIN_CORAL_WALL_FAN != null) return;
        ItemTypes.DEAD_BRAIN_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemDeadBrainCoralWallFanStack.class)
                .vanillaItem(ItemId.DEAD_BRAIN_CORAL_WALL_FAN)
                .build();
    }

    public static void initDeadBubbleCoral() {
        if (ItemTypes.DEAD_BUBBLE_CORAL != null) return;
        ItemTypes.DEAD_BUBBLE_CORAL = ItemTypeBuilder
                .builder(ItemDeadBubbleCoralStack.class)
                .vanillaItem(ItemId.DEAD_BUBBLE_CORAL)
                .build();
    }

    public static void initDeadBubbleCoralBlock() {
        if (ItemTypes.DEAD_BUBBLE_CORAL_BLOCK != null) return;
        ItemTypes.DEAD_BUBBLE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemDeadBubbleCoralBlockStack.class)
                .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBubbleCoralFan() {
        if (ItemTypes.DEAD_BUBBLE_CORAL_FAN != null) return;
        ItemTypes.DEAD_BUBBLE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemDeadBubbleCoralFanStack.class)
                .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_FAN)
                .build();
    }

    public static void initDeadBubbleCoralWallFan() {
        if (ItemTypes.DEAD_BUBBLE_CORAL_WALL_FAN != null) return;
        ItemTypes.DEAD_BUBBLE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemDeadBubbleCoralWallFanStack.class)
                .vanillaItem(ItemId.DEAD_BUBBLE_CORAL_WALL_FAN)
                .build();
    }

    public static void initDeadFireCoral() {
        if (ItemTypes.DEAD_FIRE_CORAL != null) return;
        ItemTypes.DEAD_FIRE_CORAL = ItemTypeBuilder
                .builder(ItemDeadFireCoralStack.class)
                .vanillaItem(ItemId.DEAD_FIRE_CORAL)
                .build();
    }

    public static void initDeadFireCoralBlock() {
        if (ItemTypes.DEAD_FIRE_CORAL_BLOCK != null) return;
        ItemTypes.DEAD_FIRE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemDeadFireCoralBlockStack.class)
                .vanillaItem(ItemId.DEAD_FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadFireCoralFan() {
        if (ItemTypes.DEAD_FIRE_CORAL_FAN != null) return;
        ItemTypes.DEAD_FIRE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemDeadFireCoralFanStack.class)
                .vanillaItem(ItemId.DEAD_FIRE_CORAL_FAN)
                .build();
    }

    public static void initDeadFireCoralWallFan() {
        if (ItemTypes.DEAD_FIRE_CORAL_WALL_FAN != null) return;
        ItemTypes.DEAD_FIRE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemDeadFireCoralWallFanStack.class)
                .vanillaItem(ItemId.DEAD_FIRE_CORAL_WALL_FAN)
                .build();
    }

    public static void initDeadHornCoral() {
        if (ItemTypes.DEAD_HORN_CORAL != null) return;
        ItemTypes.DEAD_HORN_CORAL = ItemTypeBuilder
                .builder(ItemDeadHornCoralStack.class)
                .vanillaItem(ItemId.DEAD_HORN_CORAL)
                .build();
    }

    public static void initDeadHornCoralBlock() {
        if (ItemTypes.DEAD_HORN_CORAL_BLOCK != null) return;
        ItemTypes.DEAD_HORN_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemDeadHornCoralBlockStack.class)
                .vanillaItem(ItemId.DEAD_HORN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadHornCoralFan() {
        if (ItemTypes.DEAD_HORN_CORAL_FAN != null) return;
        ItemTypes.DEAD_HORN_CORAL_FAN = ItemTypeBuilder
                .builder(ItemDeadHornCoralFanStack.class)
                .vanillaItem(ItemId.DEAD_HORN_CORAL_FAN)
                .build();
    }

    public static void initDeadHornCoralWallFan() {
        if (ItemTypes.DEAD_HORN_CORAL_WALL_FAN != null) return;
        ItemTypes.DEAD_HORN_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemDeadHornCoralWallFanStack.class)
                .vanillaItem(ItemId.DEAD_HORN_CORAL_WALL_FAN)
                .build();
    }

    public static void initDeadTubeCoral() {
        if (ItemTypes.DEAD_TUBE_CORAL != null) return;
        ItemTypes.DEAD_TUBE_CORAL = ItemTypeBuilder
                .builder(ItemDeadTubeCoralStack.class)
                .vanillaItem(ItemId.DEAD_TUBE_CORAL)
                .build();
    }

    public static void initDeadTubeCoralBlock() {
        if (ItemTypes.DEAD_TUBE_CORAL_BLOCK != null) return;
        ItemTypes.DEAD_TUBE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemDeadTubeCoralBlockStack.class)
                .vanillaItem(ItemId.DEAD_TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadTubeCoralFan() {
        if (ItemTypes.DEAD_TUBE_CORAL_FAN != null) return;
        ItemTypes.DEAD_TUBE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemDeadTubeCoralFanStack.class)
                .vanillaItem(ItemId.DEAD_TUBE_CORAL_FAN)
                .build();
    }

    public static void initDeadTubeCoralWallFan() {
        if (ItemTypes.DEAD_TUBE_CORAL_WALL_FAN != null) return;
        ItemTypes.DEAD_TUBE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemDeadTubeCoralWallFanStack.class)
                .vanillaItem(ItemId.DEAD_TUBE_CORAL_WALL_FAN)
                .build();
    }

    public static void initDeadbush() {
        if (ItemTypes.DEADBUSH != null) return;
        ItemTypes.DEADBUSH = ItemTypeBuilder
                .builder(ItemDeadbushStack.class)
                .vanillaItem(ItemId.DEADBUSH)
                .build();
    }

    public static void initDecoratedPot() {
        if (ItemTypes.DECORATED_POT != null) return;
        ItemTypes.DECORATED_POT = ItemTypeBuilder
                .builder(ItemDecoratedPotStack.class)
                .vanillaItem(ItemId.DECORATED_POT)
                .build();
    }

    public static void initDeepslate() {
        if (ItemTypes.DEEPSLATE != null) return;
        ItemTypes.DEEPSLATE = ItemTypeBuilder
                .builder(ItemDeepslateStack.class)
                .vanillaItem(ItemId.DEEPSLATE)
                .build();
    }

    public static void initDeepslateBrickDoubleSlab() {
        if (ItemTypes.DEEPSLATE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemDeepslateBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initDeepslateBrickSlab() {
        if (ItemTypes.DEEPSLATE_BRICK_SLAB != null) return;
        ItemTypes.DEEPSLATE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemDeepslateBrickSlabStack.class)
                .vanillaItem(ItemId.DEEPSLATE_BRICK_SLAB)
                .build();
    }

    public static void initDeepslateBrickStairs() {
        if (ItemTypes.DEEPSLATE_BRICK_STAIRS != null) return;
        ItemTypes.DEEPSLATE_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemDeepslateBrickStairsStack.class)
                .vanillaItem(ItemId.DEEPSLATE_BRICK_STAIRS)
                .build();
    }

    public static void initDeepslateBrickWall() {
        if (ItemTypes.DEEPSLATE_BRICK_WALL != null) return;
        ItemTypes.DEEPSLATE_BRICK_WALL = ItemTypeBuilder
                .builder(ItemDeepslateBrickWallStack.class)
                .vanillaItem(ItemId.DEEPSLATE_BRICK_WALL)
                .build();
    }

    public static void initDeepslateBricks() {
        if (ItemTypes.DEEPSLATE_BRICKS != null) return;
        ItemTypes.DEEPSLATE_BRICKS = ItemTypeBuilder
                .builder(ItemDeepslateBricksStack.class)
                .vanillaItem(ItemId.DEEPSLATE_BRICKS)
                .build();
    }

    public static void initDeepslateCoalOre() {
        if (ItemTypes.DEEPSLATE_COAL_ORE != null) return;
        ItemTypes.DEEPSLATE_COAL_ORE = ItemTypeBuilder
                .builder(ItemDeepslateCoalOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_COAL_ORE)
                .build();
    }

    public static void initDeepslateCopperOre() {
        if (ItemTypes.DEEPSLATE_COPPER_ORE != null) return;
        ItemTypes.DEEPSLATE_COPPER_ORE = ItemTypeBuilder
                .builder(ItemDeepslateCopperOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_COPPER_ORE)
                .build();
    }

    public static void initDeepslateDiamondOre() {
        if (ItemTypes.DEEPSLATE_DIAMOND_ORE != null) return;
        ItemTypes.DEEPSLATE_DIAMOND_ORE = ItemTypeBuilder
                .builder(ItemDeepslateDiamondOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_DIAMOND_ORE)
                .build();
    }

    public static void initDeepslateEmeraldOre() {
        if (ItemTypes.DEEPSLATE_EMERALD_ORE != null) return;
        ItemTypes.DEEPSLATE_EMERALD_ORE = ItemTypeBuilder
                .builder(ItemDeepslateEmeraldOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_EMERALD_ORE)
                .build();
    }

    public static void initDeepslateGoldOre() {
        if (ItemTypes.DEEPSLATE_GOLD_ORE != null) return;
        ItemTypes.DEEPSLATE_GOLD_ORE = ItemTypeBuilder
                .builder(ItemDeepslateGoldOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_GOLD_ORE)
                .build();
    }

    public static void initDeepslateIronOre() {
        if (ItemTypes.DEEPSLATE_IRON_ORE != null) return;
        ItemTypes.DEEPSLATE_IRON_ORE = ItemTypeBuilder
                .builder(ItemDeepslateIronOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_IRON_ORE)
                .build();
    }

    public static void initDeepslateLapisOre() {
        if (ItemTypes.DEEPSLATE_LAPIS_ORE != null) return;
        ItemTypes.DEEPSLATE_LAPIS_ORE = ItemTypeBuilder
                .builder(ItemDeepslateLapisOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_LAPIS_ORE)
                .build();
    }

    public static void initDeepslateRedstoneOre() {
        if (ItemTypes.DEEPSLATE_REDSTONE_ORE != null) return;
        ItemTypes.DEEPSLATE_REDSTONE_ORE = ItemTypeBuilder
                .builder(ItemDeepslateRedstoneOreStack.class)
                .vanillaItem(ItemId.DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initDeepslateTileDoubleSlab() {
        if (ItemTypes.DEEPSLATE_TILE_DOUBLE_SLAB != null) return;
        ItemTypes.DEEPSLATE_TILE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemDeepslateTileDoubleSlabStack.class)
                .vanillaItem(ItemId.DEEPSLATE_TILE_DOUBLE_SLAB)
                .build();
    }

    public static void initDeepslateTileSlab() {
        if (ItemTypes.DEEPSLATE_TILE_SLAB != null) return;
        ItemTypes.DEEPSLATE_TILE_SLAB = ItemTypeBuilder
                .builder(ItemDeepslateTileSlabStack.class)
                .vanillaItem(ItemId.DEEPSLATE_TILE_SLAB)
                .build();
    }

    public static void initDeepslateTileStairs() {
        if (ItemTypes.DEEPSLATE_TILE_STAIRS != null) return;
        ItemTypes.DEEPSLATE_TILE_STAIRS = ItemTypeBuilder
                .builder(ItemDeepslateTileStairsStack.class)
                .vanillaItem(ItemId.DEEPSLATE_TILE_STAIRS)
                .build();
    }

    public static void initDeepslateTileWall() {
        if (ItemTypes.DEEPSLATE_TILE_WALL != null) return;
        ItemTypes.DEEPSLATE_TILE_WALL = ItemTypeBuilder
                .builder(ItemDeepslateTileWallStack.class)
                .vanillaItem(ItemId.DEEPSLATE_TILE_WALL)
                .build();
    }

    public static void initDeepslateTiles() {
        if (ItemTypes.DEEPSLATE_TILES != null) return;
        ItemTypes.DEEPSLATE_TILES = ItemTypeBuilder
                .builder(ItemDeepslateTilesStack.class)
                .vanillaItem(ItemId.DEEPSLATE_TILES)
                .build();
    }

    public static void initDeny() {
        if (ItemTypes.DENY != null) return;
        ItemTypes.DENY = ItemTypeBuilder
                .builder(ItemDenyStack.class)
                .vanillaItem(ItemId.DENY)
                .build();
    }

    public static void initDeprecatedAnvil() {
        if (ItemTypes.DEPRECATED_ANVIL != null) return;
        ItemTypes.DEPRECATED_ANVIL = ItemTypeBuilder
                .builder(ItemDeprecatedAnvilStack.class)
                .vanillaItem(ItemId.DEPRECATED_ANVIL)
                .build();
    }

    public static void initDetectorRail() {
        if (ItemTypes.DETECTOR_RAIL != null) return;
        ItemTypes.DETECTOR_RAIL = ItemTypeBuilder
                .builder(ItemDetectorRailStack.class)
                .vanillaItem(ItemId.DETECTOR_RAIL)
                .build();
    }

    public static void initDiamond() {
        if (ItemTypes.DIAMOND != null) return;
        ItemTypes.DIAMOND = ItemTypeBuilder
                .builder(ItemDiamondStack.class)
                .vanillaItem(ItemId.DIAMOND)
                .build();
    }

    public static void initDiamondAxe() {
        if (ItemTypes.DIAMOND_AXE != null) return;
        ItemTypes.DIAMOND_AXE = ItemTypeBuilder
                .builder(ItemDiamondAxeStack.class)
                .vanillaItem(ItemId.DIAMOND_AXE)
                .build();
    }

    public static void initDiamondBlock() {
        if (ItemTypes.DIAMOND_BLOCK != null) return;
        ItemTypes.DIAMOND_BLOCK = ItemTypeBuilder
                .builder(ItemDiamondBlockStack.class)
                .vanillaItem(ItemId.DIAMOND_BLOCK)
                .build();
    }

    public static void initDiamondBoots() {
        if (ItemTypes.DIAMOND_BOOTS != null) return;
        ItemTypes.DIAMOND_BOOTS = ItemTypeBuilder
                .builder(ItemDiamondBootsStack.class)
                .vanillaItem(ItemId.DIAMOND_BOOTS)
                .build();
    }

    public static void initDiamondChestplate() {
        if (ItemTypes.DIAMOND_CHESTPLATE != null) return;
        ItemTypes.DIAMOND_CHESTPLATE = ItemTypeBuilder
                .builder(ItemDiamondChestplateStack.class)
                .vanillaItem(ItemId.DIAMOND_CHESTPLATE)
                .build();
    }

    public static void initDiamondHelmet() {
        if (ItemTypes.DIAMOND_HELMET != null) return;
        ItemTypes.DIAMOND_HELMET = ItemTypeBuilder
                .builder(ItemDiamondHelmetStack.class)
                .vanillaItem(ItemId.DIAMOND_HELMET)
                .build();
    }

    public static void initDiamondHoe() {
        if (ItemTypes.DIAMOND_HOE != null) return;
        ItemTypes.DIAMOND_HOE = ItemTypeBuilder
                .builder(ItemDiamondHoeStack.class)
                .vanillaItem(ItemId.DIAMOND_HOE)
                .build();
    }

    public static void initDiamondHorseArmor() {
        if (ItemTypes.DIAMOND_HORSE_ARMOR != null) return;
        ItemTypes.DIAMOND_HORSE_ARMOR = ItemTypeBuilder
                .builder(ItemDiamondHorseArmorStack.class)
                .vanillaItem(ItemId.DIAMOND_HORSE_ARMOR)
                .build();
    }

    public static void initDiamondLeggings() {
        if (ItemTypes.DIAMOND_LEGGINGS != null) return;
        ItemTypes.DIAMOND_LEGGINGS = ItemTypeBuilder
                .builder(ItemDiamondLeggingsStack.class)
                .vanillaItem(ItemId.DIAMOND_LEGGINGS)
                .build();
    }

    public static void initDiamondOre() {
        if (ItemTypes.DIAMOND_ORE != null) return;
        ItemTypes.DIAMOND_ORE = ItemTypeBuilder
                .builder(ItemDiamondOreStack.class)
                .vanillaItem(ItemId.DIAMOND_ORE)
                .build();
    }

    public static void initDiamondPickaxe() {
        if (ItemTypes.DIAMOND_PICKAXE != null) return;
        ItemTypes.DIAMOND_PICKAXE = ItemTypeBuilder
                .builder(ItemDiamondPickaxeStack.class)
                .vanillaItem(ItemId.DIAMOND_PICKAXE)
                .build();
    }

    public static void initDiamondShovel() {
        if (ItemTypes.DIAMOND_SHOVEL != null) return;
        ItemTypes.DIAMOND_SHOVEL = ItemTypeBuilder
                .builder(ItemDiamondShovelStack.class)
                .vanillaItem(ItemId.DIAMOND_SHOVEL)
                .build();
    }

    public static void initDiamondSword() {
        if (ItemTypes.DIAMOND_SWORD != null) return;
        ItemTypes.DIAMOND_SWORD = ItemTypeBuilder
                .builder(ItemDiamondSwordStack.class)
                .vanillaItem(ItemId.DIAMOND_SWORD)
                .build();
    }

    public static void initDiorite() {
        if (ItemTypes.DIORITE != null) return;
        ItemTypes.DIORITE = ItemTypeBuilder
                .builder(ItemDioriteStack.class)
                .vanillaItem(ItemId.DIORITE)
                .build();
    }

    public static void initDioriteDoubleSlab() {
        if (ItemTypes.DIORITE_DOUBLE_SLAB != null) return;
        ItemTypes.DIORITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemDioriteDoubleSlabStack.class)
                .vanillaItem(ItemId.DIORITE_DOUBLE_SLAB)
                .build();
    }

    public static void initDioriteSlab() {
        if (ItemTypes.DIORITE_SLAB != null) return;
        ItemTypes.DIORITE_SLAB = ItemTypeBuilder
                .builder(ItemDioriteSlabStack.class)
                .vanillaItem(ItemId.DIORITE_SLAB)
                .build();
    }

    public static void initDioriteStairs() {
        if (ItemTypes.DIORITE_STAIRS != null) return;
        ItemTypes.DIORITE_STAIRS = ItemTypeBuilder
                .builder(ItemDioriteStairsStack.class)
                .vanillaItem(ItemId.DIORITE_STAIRS)
                .build();
    }

    public static void initDirt() {
        if (ItemTypes.DIRT != null) return;
        ItemTypes.DIRT = ItemTypeBuilder
                .builder(ItemDirtStack.class)
                .vanillaItem(ItemId.DIRT)
                .build();
    }

    public static void initDirtWithRoots() {
        if (ItemTypes.DIRT_WITH_ROOTS != null) return;
        ItemTypes.DIRT_WITH_ROOTS = ItemTypeBuilder
                .builder(ItemDirtWithRootsStack.class)
                .vanillaItem(ItemId.DIRT_WITH_ROOTS)
                .build();
    }

    public static void initDiscFragment5() {
        if (ItemTypes.DISC_FRAGMENT_5 != null) return;
        ItemTypes.DISC_FRAGMENT_5 = ItemTypeBuilder
                .builder(ItemDiscFragment5Stack.class)
                .vanillaItem(ItemId.DISC_FRAGMENT_5)
                .build();
    }

    public static void initDispenser() {
        if (ItemTypes.DISPENSER != null) return;
        ItemTypes.DISPENSER = ItemTypeBuilder
                .builder(ItemDispenserStack.class)
                .vanillaItem(ItemId.DISPENSER)
                .build();
    }

    public static void initDolphinSpawnEgg() {
        if (ItemTypes.DOLPHIN_SPAWN_EGG != null) return;
        ItemTypes.DOLPHIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDolphinSpawnEggStack.class)
                .vanillaItem(ItemId.DOLPHIN_SPAWN_EGG)
                .build();
    }

    public static void initDonkeySpawnEgg() {
        if (ItemTypes.DONKEY_SPAWN_EGG != null) return;
        ItemTypes.DONKEY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDonkeySpawnEggStack.class)
                .vanillaItem(ItemId.DONKEY_SPAWN_EGG)
                .build();
    }

    public static void initDoubleCutCopperSlab() {
        if (ItemTypes.DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initDoublePlant() {
        if (ItemTypes.DOUBLE_PLANT != null) return;
        ItemTypes.DOUBLE_PLANT = ItemTypeBuilder
                .builder(ItemDoublePlantStack.class)
                .vanillaItem(ItemId.DOUBLE_PLANT)
                .build();
    }

    public static void initDoubleStoneBlockSlab() {
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB != null) return;
        ItemTypes.DOUBLE_STONE_BLOCK_SLAB = ItemTypeBuilder
                .builder(ItemDoubleStoneBlockSlabStack.class)
                .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB)
                .build();
    }

    public static void initDoubleStoneBlockSlab2() {
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB2 != null) return;
        ItemTypes.DOUBLE_STONE_BLOCK_SLAB2 = ItemTypeBuilder
                .builder(ItemDoubleStoneBlockSlab2Stack.class)
                .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB2)
                .build();
    }

    public static void initDoubleStoneBlockSlab3() {
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB3 != null) return;
        ItemTypes.DOUBLE_STONE_BLOCK_SLAB3 = ItemTypeBuilder
                .builder(ItemDoubleStoneBlockSlab3Stack.class)
                .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB3)
                .build();
    }

    public static void initDoubleStoneBlockSlab4() {
        if (ItemTypes.DOUBLE_STONE_BLOCK_SLAB4 != null) return;
        ItemTypes.DOUBLE_STONE_BLOCK_SLAB4 = ItemTypeBuilder
                .builder(ItemDoubleStoneBlockSlab4Stack.class)
                .vanillaItem(ItemId.DOUBLE_STONE_BLOCK_SLAB4)
                .build();
    }

    public static void initDragonBreath() {
        if (ItemTypes.DRAGON_BREATH != null) return;
        ItemTypes.DRAGON_BREATH = ItemTypeBuilder
                .builder(ItemDragonBreathStack.class)
                .vanillaItem(ItemId.DRAGON_BREATH)
                .build();
    }

    public static void initDragonEgg() {
        if (ItemTypes.DRAGON_EGG != null) return;
        ItemTypes.DRAGON_EGG = ItemTypeBuilder
                .builder(ItemDragonEggStack.class)
                .vanillaItem(ItemId.DRAGON_EGG)
                .build();
    }

    public static void initDriedKelp() {
        if (ItemTypes.DRIED_KELP != null) return;
        ItemTypes.DRIED_KELP = ItemTypeBuilder
                .builder(ItemDriedKelpStack.class)
                .vanillaItem(ItemId.DRIED_KELP)
                .build();
    }

    public static void initDriedKelpBlock() {
        if (ItemTypes.DRIED_KELP_BLOCK != null) return;
        ItemTypes.DRIED_KELP_BLOCK = ItemTypeBuilder
                .builder(ItemDriedKelpBlockStack.class)
                .vanillaItem(ItemId.DRIED_KELP_BLOCK)
                .build();
    }

    public static void initDripstoneBlock() {
        if (ItemTypes.DRIPSTONE_BLOCK != null) return;
        ItemTypes.DRIPSTONE_BLOCK = ItemTypeBuilder
                .builder(ItemDripstoneBlockStack.class)
                .vanillaItem(ItemId.DRIPSTONE_BLOCK)
                .build();
    }

    public static void initDropper() {
        if (ItemTypes.DROPPER != null) return;
        ItemTypes.DROPPER = ItemTypeBuilder
                .builder(ItemDropperStack.class)
                .vanillaItem(ItemId.DROPPER)
                .build();
    }

    public static void initDrownedSpawnEgg() {
        if (ItemTypes.DROWNED_SPAWN_EGG != null) return;
        ItemTypes.DROWNED_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDrownedSpawnEggStack.class)
                .vanillaItem(ItemId.DROWNED_SPAWN_EGG)
                .build();
    }

    public static void initDuneArmorTrimSmithingTemplate() {
        if (ItemTypes.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemDuneArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initDye() {
        if (ItemTypes.DYE != null) return;
        ItemTypes.DYE = ItemTypeBuilder
                .builder(ItemDyeStack.class)
                .vanillaItem(ItemId.DYE)
                .build();
    }

    public static void initEchoShard() {
        if (ItemTypes.ECHO_SHARD != null) return;
        ItemTypes.ECHO_SHARD = ItemTypeBuilder
                .builder(ItemEchoShardStack.class)
                .vanillaItem(ItemId.ECHO_SHARD)
                .build();
    }

    public static void initEgg() {
        if (ItemTypes.EGG != null) return;
        ItemTypes.EGG = ItemTypeBuilder
                .builder(ItemEggStack.class)
                .vanillaItem(ItemId.EGG)
                .build();
    }

    public static void initElderGuardianSpawnEgg() {
        if (ItemTypes.ELDER_GUARDIAN_SPAWN_EGG != null) return;
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemElderGuardianSpawnEggStack.class)
                .vanillaItem(ItemId.ELDER_GUARDIAN_SPAWN_EGG)
                .build();
    }

    public static void initElement0() {
        if (ItemTypes.ELEMENT_0 != null) return;
        ItemTypes.ELEMENT_0 = ItemTypeBuilder
                .builder(ItemElement0Stack.class)
                .vanillaItem(ItemId.ELEMENT_0)
                .build();
    }

    public static void initElement1() {
        if (ItemTypes.ELEMENT_1 != null) return;
        ItemTypes.ELEMENT_1 = ItemTypeBuilder
                .builder(ItemElement1Stack.class)
                .vanillaItem(ItemId.ELEMENT_1)
                .build();
    }

    public static void initElement10() {
        if (ItemTypes.ELEMENT_10 != null) return;
        ItemTypes.ELEMENT_10 = ItemTypeBuilder
                .builder(ItemElement10Stack.class)
                .vanillaItem(ItemId.ELEMENT_10)
                .build();
    }

    public static void initElement100() {
        if (ItemTypes.ELEMENT_100 != null) return;
        ItemTypes.ELEMENT_100 = ItemTypeBuilder
                .builder(ItemElement100Stack.class)
                .vanillaItem(ItemId.ELEMENT_100)
                .build();
    }

    public static void initElement101() {
        if (ItemTypes.ELEMENT_101 != null) return;
        ItemTypes.ELEMENT_101 = ItemTypeBuilder
                .builder(ItemElement101Stack.class)
                .vanillaItem(ItemId.ELEMENT_101)
                .build();
    }

    public static void initElement102() {
        if (ItemTypes.ELEMENT_102 != null) return;
        ItemTypes.ELEMENT_102 = ItemTypeBuilder
                .builder(ItemElement102Stack.class)
                .vanillaItem(ItemId.ELEMENT_102)
                .build();
    }

    public static void initElement103() {
        if (ItemTypes.ELEMENT_103 != null) return;
        ItemTypes.ELEMENT_103 = ItemTypeBuilder
                .builder(ItemElement103Stack.class)
                .vanillaItem(ItemId.ELEMENT_103)
                .build();
    }

    public static void initElement104() {
        if (ItemTypes.ELEMENT_104 != null) return;
        ItemTypes.ELEMENT_104 = ItemTypeBuilder
                .builder(ItemElement104Stack.class)
                .vanillaItem(ItemId.ELEMENT_104)
                .build();
    }

    public static void initElement105() {
        if (ItemTypes.ELEMENT_105 != null) return;
        ItemTypes.ELEMENT_105 = ItemTypeBuilder
                .builder(ItemElement105Stack.class)
                .vanillaItem(ItemId.ELEMENT_105)
                .build();
    }

    public static void initElement106() {
        if (ItemTypes.ELEMENT_106 != null) return;
        ItemTypes.ELEMENT_106 = ItemTypeBuilder
                .builder(ItemElement106Stack.class)
                .vanillaItem(ItemId.ELEMENT_106)
                .build();
    }

    public static void initElement107() {
        if (ItemTypes.ELEMENT_107 != null) return;
        ItemTypes.ELEMENT_107 = ItemTypeBuilder
                .builder(ItemElement107Stack.class)
                .vanillaItem(ItemId.ELEMENT_107)
                .build();
    }

    public static void initElement108() {
        if (ItemTypes.ELEMENT_108 != null) return;
        ItemTypes.ELEMENT_108 = ItemTypeBuilder
                .builder(ItemElement108Stack.class)
                .vanillaItem(ItemId.ELEMENT_108)
                .build();
    }

    public static void initElement109() {
        if (ItemTypes.ELEMENT_109 != null) return;
        ItemTypes.ELEMENT_109 = ItemTypeBuilder
                .builder(ItemElement109Stack.class)
                .vanillaItem(ItemId.ELEMENT_109)
                .build();
    }

    public static void initElement11() {
        if (ItemTypes.ELEMENT_11 != null) return;
        ItemTypes.ELEMENT_11 = ItemTypeBuilder
                .builder(ItemElement11Stack.class)
                .vanillaItem(ItemId.ELEMENT_11)
                .build();
    }

    public static void initElement110() {
        if (ItemTypes.ELEMENT_110 != null) return;
        ItemTypes.ELEMENT_110 = ItemTypeBuilder
                .builder(ItemElement110Stack.class)
                .vanillaItem(ItemId.ELEMENT_110)
                .build();
    }

    public static void initElement111() {
        if (ItemTypes.ELEMENT_111 != null) return;
        ItemTypes.ELEMENT_111 = ItemTypeBuilder
                .builder(ItemElement111Stack.class)
                .vanillaItem(ItemId.ELEMENT_111)
                .build();
    }

    public static void initElement112() {
        if (ItemTypes.ELEMENT_112 != null) return;
        ItemTypes.ELEMENT_112 = ItemTypeBuilder
                .builder(ItemElement112Stack.class)
                .vanillaItem(ItemId.ELEMENT_112)
                .build();
    }

    public static void initElement113() {
        if (ItemTypes.ELEMENT_113 != null) return;
        ItemTypes.ELEMENT_113 = ItemTypeBuilder
                .builder(ItemElement113Stack.class)
                .vanillaItem(ItemId.ELEMENT_113)
                .build();
    }

    public static void initElement114() {
        if (ItemTypes.ELEMENT_114 != null) return;
        ItemTypes.ELEMENT_114 = ItemTypeBuilder
                .builder(ItemElement114Stack.class)
                .vanillaItem(ItemId.ELEMENT_114)
                .build();
    }

    public static void initElement115() {
        if (ItemTypes.ELEMENT_115 != null) return;
        ItemTypes.ELEMENT_115 = ItemTypeBuilder
                .builder(ItemElement115Stack.class)
                .vanillaItem(ItemId.ELEMENT_115)
                .build();
    }

    public static void initElement116() {
        if (ItemTypes.ELEMENT_116 != null) return;
        ItemTypes.ELEMENT_116 = ItemTypeBuilder
                .builder(ItemElement116Stack.class)
                .vanillaItem(ItemId.ELEMENT_116)
                .build();
    }

    public static void initElement117() {
        if (ItemTypes.ELEMENT_117 != null) return;
        ItemTypes.ELEMENT_117 = ItemTypeBuilder
                .builder(ItemElement117Stack.class)
                .vanillaItem(ItemId.ELEMENT_117)
                .build();
    }

    public static void initElement118() {
        if (ItemTypes.ELEMENT_118 != null) return;
        ItemTypes.ELEMENT_118 = ItemTypeBuilder
                .builder(ItemElement118Stack.class)
                .vanillaItem(ItemId.ELEMENT_118)
                .build();
    }

    public static void initElement12() {
        if (ItemTypes.ELEMENT_12 != null) return;
        ItemTypes.ELEMENT_12 = ItemTypeBuilder
                .builder(ItemElement12Stack.class)
                .vanillaItem(ItemId.ELEMENT_12)
                .build();
    }

    public static void initElement13() {
        if (ItemTypes.ELEMENT_13 != null) return;
        ItemTypes.ELEMENT_13 = ItemTypeBuilder
                .builder(ItemElement13Stack.class)
                .vanillaItem(ItemId.ELEMENT_13)
                .build();
    }

    public static void initElement14() {
        if (ItemTypes.ELEMENT_14 != null) return;
        ItemTypes.ELEMENT_14 = ItemTypeBuilder
                .builder(ItemElement14Stack.class)
                .vanillaItem(ItemId.ELEMENT_14)
                .build();
    }

    public static void initElement15() {
        if (ItemTypes.ELEMENT_15 != null) return;
        ItemTypes.ELEMENT_15 = ItemTypeBuilder
                .builder(ItemElement15Stack.class)
                .vanillaItem(ItemId.ELEMENT_15)
                .build();
    }

    public static void initElement16() {
        if (ItemTypes.ELEMENT_16 != null) return;
        ItemTypes.ELEMENT_16 = ItemTypeBuilder
                .builder(ItemElement16Stack.class)
                .vanillaItem(ItemId.ELEMENT_16)
                .build();
    }

    public static void initElement17() {
        if (ItemTypes.ELEMENT_17 != null) return;
        ItemTypes.ELEMENT_17 = ItemTypeBuilder
                .builder(ItemElement17Stack.class)
                .vanillaItem(ItemId.ELEMENT_17)
                .build();
    }

    public static void initElement18() {
        if (ItemTypes.ELEMENT_18 != null) return;
        ItemTypes.ELEMENT_18 = ItemTypeBuilder
                .builder(ItemElement18Stack.class)
                .vanillaItem(ItemId.ELEMENT_18)
                .build();
    }

    public static void initElement19() {
        if (ItemTypes.ELEMENT_19 != null) return;
        ItemTypes.ELEMENT_19 = ItemTypeBuilder
                .builder(ItemElement19Stack.class)
                .vanillaItem(ItemId.ELEMENT_19)
                .build();
    }

    public static void initElement2() {
        if (ItemTypes.ELEMENT_2 != null) return;
        ItemTypes.ELEMENT_2 = ItemTypeBuilder
                .builder(ItemElement2Stack.class)
                .vanillaItem(ItemId.ELEMENT_2)
                .build();
    }

    public static void initElement20() {
        if (ItemTypes.ELEMENT_20 != null) return;
        ItemTypes.ELEMENT_20 = ItemTypeBuilder
                .builder(ItemElement20Stack.class)
                .vanillaItem(ItemId.ELEMENT_20)
                .build();
    }

    public static void initElement21() {
        if (ItemTypes.ELEMENT_21 != null) return;
        ItemTypes.ELEMENT_21 = ItemTypeBuilder
                .builder(ItemElement21Stack.class)
                .vanillaItem(ItemId.ELEMENT_21)
                .build();
    }

    public static void initElement22() {
        if (ItemTypes.ELEMENT_22 != null) return;
        ItemTypes.ELEMENT_22 = ItemTypeBuilder
                .builder(ItemElement22Stack.class)
                .vanillaItem(ItemId.ELEMENT_22)
                .build();
    }

    public static void initElement23() {
        if (ItemTypes.ELEMENT_23 != null) return;
        ItemTypes.ELEMENT_23 = ItemTypeBuilder
                .builder(ItemElement23Stack.class)
                .vanillaItem(ItemId.ELEMENT_23)
                .build();
    }

    public static void initElement24() {
        if (ItemTypes.ELEMENT_24 != null) return;
        ItemTypes.ELEMENT_24 = ItemTypeBuilder
                .builder(ItemElement24Stack.class)
                .vanillaItem(ItemId.ELEMENT_24)
                .build();
    }

    public static void initElement25() {
        if (ItemTypes.ELEMENT_25 != null) return;
        ItemTypes.ELEMENT_25 = ItemTypeBuilder
                .builder(ItemElement25Stack.class)
                .vanillaItem(ItemId.ELEMENT_25)
                .build();
    }

    public static void initElement26() {
        if (ItemTypes.ELEMENT_26 != null) return;
        ItemTypes.ELEMENT_26 = ItemTypeBuilder
                .builder(ItemElement26Stack.class)
                .vanillaItem(ItemId.ELEMENT_26)
                .build();
    }

    public static void initElement27() {
        if (ItemTypes.ELEMENT_27 != null) return;
        ItemTypes.ELEMENT_27 = ItemTypeBuilder
                .builder(ItemElement27Stack.class)
                .vanillaItem(ItemId.ELEMENT_27)
                .build();
    }

    public static void initElement28() {
        if (ItemTypes.ELEMENT_28 != null) return;
        ItemTypes.ELEMENT_28 = ItemTypeBuilder
                .builder(ItemElement28Stack.class)
                .vanillaItem(ItemId.ELEMENT_28)
                .build();
    }

    public static void initElement29() {
        if (ItemTypes.ELEMENT_29 != null) return;
        ItemTypes.ELEMENT_29 = ItemTypeBuilder
                .builder(ItemElement29Stack.class)
                .vanillaItem(ItemId.ELEMENT_29)
                .build();
    }

    public static void initElement3() {
        if (ItemTypes.ELEMENT_3 != null) return;
        ItemTypes.ELEMENT_3 = ItemTypeBuilder
                .builder(ItemElement3Stack.class)
                .vanillaItem(ItemId.ELEMENT_3)
                .build();
    }

    public static void initElement30() {
        if (ItemTypes.ELEMENT_30 != null) return;
        ItemTypes.ELEMENT_30 = ItemTypeBuilder
                .builder(ItemElement30Stack.class)
                .vanillaItem(ItemId.ELEMENT_30)
                .build();
    }

    public static void initElement31() {
        if (ItemTypes.ELEMENT_31 != null) return;
        ItemTypes.ELEMENT_31 = ItemTypeBuilder
                .builder(ItemElement31Stack.class)
                .vanillaItem(ItemId.ELEMENT_31)
                .build();
    }

    public static void initElement32() {
        if (ItemTypes.ELEMENT_32 != null) return;
        ItemTypes.ELEMENT_32 = ItemTypeBuilder
                .builder(ItemElement32Stack.class)
                .vanillaItem(ItemId.ELEMENT_32)
                .build();
    }

    public static void initElement33() {
        if (ItemTypes.ELEMENT_33 != null) return;
        ItemTypes.ELEMENT_33 = ItemTypeBuilder
                .builder(ItemElement33Stack.class)
                .vanillaItem(ItemId.ELEMENT_33)
                .build();
    }

    public static void initElement34() {
        if (ItemTypes.ELEMENT_34 != null) return;
        ItemTypes.ELEMENT_34 = ItemTypeBuilder
                .builder(ItemElement34Stack.class)
                .vanillaItem(ItemId.ELEMENT_34)
                .build();
    }

    public static void initElement35() {
        if (ItemTypes.ELEMENT_35 != null) return;
        ItemTypes.ELEMENT_35 = ItemTypeBuilder
                .builder(ItemElement35Stack.class)
                .vanillaItem(ItemId.ELEMENT_35)
                .build();
    }

    public static void initElement36() {
        if (ItemTypes.ELEMENT_36 != null) return;
        ItemTypes.ELEMENT_36 = ItemTypeBuilder
                .builder(ItemElement36Stack.class)
                .vanillaItem(ItemId.ELEMENT_36)
                .build();
    }

    public static void initElement37() {
        if (ItemTypes.ELEMENT_37 != null) return;
        ItemTypes.ELEMENT_37 = ItemTypeBuilder
                .builder(ItemElement37Stack.class)
                .vanillaItem(ItemId.ELEMENT_37)
                .build();
    }

    public static void initElement38() {
        if (ItemTypes.ELEMENT_38 != null) return;
        ItemTypes.ELEMENT_38 = ItemTypeBuilder
                .builder(ItemElement38Stack.class)
                .vanillaItem(ItemId.ELEMENT_38)
                .build();
    }

    public static void initElement39() {
        if (ItemTypes.ELEMENT_39 != null) return;
        ItemTypes.ELEMENT_39 = ItemTypeBuilder
                .builder(ItemElement39Stack.class)
                .vanillaItem(ItemId.ELEMENT_39)
                .build();
    }

    public static void initElement4() {
        if (ItemTypes.ELEMENT_4 != null) return;
        ItemTypes.ELEMENT_4 = ItemTypeBuilder
                .builder(ItemElement4Stack.class)
                .vanillaItem(ItemId.ELEMENT_4)
                .build();
    }

    public static void initElement40() {
        if (ItemTypes.ELEMENT_40 != null) return;
        ItemTypes.ELEMENT_40 = ItemTypeBuilder
                .builder(ItemElement40Stack.class)
                .vanillaItem(ItemId.ELEMENT_40)
                .build();
    }

    public static void initElement41() {
        if (ItemTypes.ELEMENT_41 != null) return;
        ItemTypes.ELEMENT_41 = ItemTypeBuilder
                .builder(ItemElement41Stack.class)
                .vanillaItem(ItemId.ELEMENT_41)
                .build();
    }

    public static void initElement42() {
        if (ItemTypes.ELEMENT_42 != null) return;
        ItemTypes.ELEMENT_42 = ItemTypeBuilder
                .builder(ItemElement42Stack.class)
                .vanillaItem(ItemId.ELEMENT_42)
                .build();
    }

    public static void initElement43() {
        if (ItemTypes.ELEMENT_43 != null) return;
        ItemTypes.ELEMENT_43 = ItemTypeBuilder
                .builder(ItemElement43Stack.class)
                .vanillaItem(ItemId.ELEMENT_43)
                .build();
    }

    public static void initElement44() {
        if (ItemTypes.ELEMENT_44 != null) return;
        ItemTypes.ELEMENT_44 = ItemTypeBuilder
                .builder(ItemElement44Stack.class)
                .vanillaItem(ItemId.ELEMENT_44)
                .build();
    }

    public static void initElement45() {
        if (ItemTypes.ELEMENT_45 != null) return;
        ItemTypes.ELEMENT_45 = ItemTypeBuilder
                .builder(ItemElement45Stack.class)
                .vanillaItem(ItemId.ELEMENT_45)
                .build();
    }

    public static void initElement46() {
        if (ItemTypes.ELEMENT_46 != null) return;
        ItemTypes.ELEMENT_46 = ItemTypeBuilder
                .builder(ItemElement46Stack.class)
                .vanillaItem(ItemId.ELEMENT_46)
                .build();
    }

    public static void initElement47() {
        if (ItemTypes.ELEMENT_47 != null) return;
        ItemTypes.ELEMENT_47 = ItemTypeBuilder
                .builder(ItemElement47Stack.class)
                .vanillaItem(ItemId.ELEMENT_47)
                .build();
    }

    public static void initElement48() {
        if (ItemTypes.ELEMENT_48 != null) return;
        ItemTypes.ELEMENT_48 = ItemTypeBuilder
                .builder(ItemElement48Stack.class)
                .vanillaItem(ItemId.ELEMENT_48)
                .build();
    }

    public static void initElement49() {
        if (ItemTypes.ELEMENT_49 != null) return;
        ItemTypes.ELEMENT_49 = ItemTypeBuilder
                .builder(ItemElement49Stack.class)
                .vanillaItem(ItemId.ELEMENT_49)
                .build();
    }

    public static void initElement5() {
        if (ItemTypes.ELEMENT_5 != null) return;
        ItemTypes.ELEMENT_5 = ItemTypeBuilder
                .builder(ItemElement5Stack.class)
                .vanillaItem(ItemId.ELEMENT_5)
                .build();
    }

    public static void initElement50() {
        if (ItemTypes.ELEMENT_50 != null) return;
        ItemTypes.ELEMENT_50 = ItemTypeBuilder
                .builder(ItemElement50Stack.class)
                .vanillaItem(ItemId.ELEMENT_50)
                .build();
    }

    public static void initElement51() {
        if (ItemTypes.ELEMENT_51 != null) return;
        ItemTypes.ELEMENT_51 = ItemTypeBuilder
                .builder(ItemElement51Stack.class)
                .vanillaItem(ItemId.ELEMENT_51)
                .build();
    }

    public static void initElement52() {
        if (ItemTypes.ELEMENT_52 != null) return;
        ItemTypes.ELEMENT_52 = ItemTypeBuilder
                .builder(ItemElement52Stack.class)
                .vanillaItem(ItemId.ELEMENT_52)
                .build();
    }

    public static void initElement53() {
        if (ItemTypes.ELEMENT_53 != null) return;
        ItemTypes.ELEMENT_53 = ItemTypeBuilder
                .builder(ItemElement53Stack.class)
                .vanillaItem(ItemId.ELEMENT_53)
                .build();
    }

    public static void initElement54() {
        if (ItemTypes.ELEMENT_54 != null) return;
        ItemTypes.ELEMENT_54 = ItemTypeBuilder
                .builder(ItemElement54Stack.class)
                .vanillaItem(ItemId.ELEMENT_54)
                .build();
    }

    public static void initElement55() {
        if (ItemTypes.ELEMENT_55 != null) return;
        ItemTypes.ELEMENT_55 = ItemTypeBuilder
                .builder(ItemElement55Stack.class)
                .vanillaItem(ItemId.ELEMENT_55)
                .build();
    }

    public static void initElement56() {
        if (ItemTypes.ELEMENT_56 != null) return;
        ItemTypes.ELEMENT_56 = ItemTypeBuilder
                .builder(ItemElement56Stack.class)
                .vanillaItem(ItemId.ELEMENT_56)
                .build();
    }

    public static void initElement57() {
        if (ItemTypes.ELEMENT_57 != null) return;
        ItemTypes.ELEMENT_57 = ItemTypeBuilder
                .builder(ItemElement57Stack.class)
                .vanillaItem(ItemId.ELEMENT_57)
                .build();
    }

    public static void initElement58() {
        if (ItemTypes.ELEMENT_58 != null) return;
        ItemTypes.ELEMENT_58 = ItemTypeBuilder
                .builder(ItemElement58Stack.class)
                .vanillaItem(ItemId.ELEMENT_58)
                .build();
    }

    public static void initElement59() {
        if (ItemTypes.ELEMENT_59 != null) return;
        ItemTypes.ELEMENT_59 = ItemTypeBuilder
                .builder(ItemElement59Stack.class)
                .vanillaItem(ItemId.ELEMENT_59)
                .build();
    }

    public static void initElement6() {
        if (ItemTypes.ELEMENT_6 != null) return;
        ItemTypes.ELEMENT_6 = ItemTypeBuilder
                .builder(ItemElement6Stack.class)
                .vanillaItem(ItemId.ELEMENT_6)
                .build();
    }

    public static void initElement60() {
        if (ItemTypes.ELEMENT_60 != null) return;
        ItemTypes.ELEMENT_60 = ItemTypeBuilder
                .builder(ItemElement60Stack.class)
                .vanillaItem(ItemId.ELEMENT_60)
                .build();
    }

    public static void initElement61() {
        if (ItemTypes.ELEMENT_61 != null) return;
        ItemTypes.ELEMENT_61 = ItemTypeBuilder
                .builder(ItemElement61Stack.class)
                .vanillaItem(ItemId.ELEMENT_61)
                .build();
    }

    public static void initElement62() {
        if (ItemTypes.ELEMENT_62 != null) return;
        ItemTypes.ELEMENT_62 = ItemTypeBuilder
                .builder(ItemElement62Stack.class)
                .vanillaItem(ItemId.ELEMENT_62)
                .build();
    }

    public static void initElement63() {
        if (ItemTypes.ELEMENT_63 != null) return;
        ItemTypes.ELEMENT_63 = ItemTypeBuilder
                .builder(ItemElement63Stack.class)
                .vanillaItem(ItemId.ELEMENT_63)
                .build();
    }

    public static void initElement64() {
        if (ItemTypes.ELEMENT_64 != null) return;
        ItemTypes.ELEMENT_64 = ItemTypeBuilder
                .builder(ItemElement64Stack.class)
                .vanillaItem(ItemId.ELEMENT_64)
                .build();
    }

    public static void initElement65() {
        if (ItemTypes.ELEMENT_65 != null) return;
        ItemTypes.ELEMENT_65 = ItemTypeBuilder
                .builder(ItemElement65Stack.class)
                .vanillaItem(ItemId.ELEMENT_65)
                .build();
    }

    public static void initElement66() {
        if (ItemTypes.ELEMENT_66 != null) return;
        ItemTypes.ELEMENT_66 = ItemTypeBuilder
                .builder(ItemElement66Stack.class)
                .vanillaItem(ItemId.ELEMENT_66)
                .build();
    }

    public static void initElement67() {
        if (ItemTypes.ELEMENT_67 != null) return;
        ItemTypes.ELEMENT_67 = ItemTypeBuilder
                .builder(ItemElement67Stack.class)
                .vanillaItem(ItemId.ELEMENT_67)
                .build();
    }

    public static void initElement68() {
        if (ItemTypes.ELEMENT_68 != null) return;
        ItemTypes.ELEMENT_68 = ItemTypeBuilder
                .builder(ItemElement68Stack.class)
                .vanillaItem(ItemId.ELEMENT_68)
                .build();
    }

    public static void initElement69() {
        if (ItemTypes.ELEMENT_69 != null) return;
        ItemTypes.ELEMENT_69 = ItemTypeBuilder
                .builder(ItemElement69Stack.class)
                .vanillaItem(ItemId.ELEMENT_69)
                .build();
    }

    public static void initElement7() {
        if (ItemTypes.ELEMENT_7 != null) return;
        ItemTypes.ELEMENT_7 = ItemTypeBuilder
                .builder(ItemElement7Stack.class)
                .vanillaItem(ItemId.ELEMENT_7)
                .build();
    }

    public static void initElement70() {
        if (ItemTypes.ELEMENT_70 != null) return;
        ItemTypes.ELEMENT_70 = ItemTypeBuilder
                .builder(ItemElement70Stack.class)
                .vanillaItem(ItemId.ELEMENT_70)
                .build();
    }

    public static void initElement71() {
        if (ItemTypes.ELEMENT_71 != null) return;
        ItemTypes.ELEMENT_71 = ItemTypeBuilder
                .builder(ItemElement71Stack.class)
                .vanillaItem(ItemId.ELEMENT_71)
                .build();
    }

    public static void initElement72() {
        if (ItemTypes.ELEMENT_72 != null) return;
        ItemTypes.ELEMENT_72 = ItemTypeBuilder
                .builder(ItemElement72Stack.class)
                .vanillaItem(ItemId.ELEMENT_72)
                .build();
    }

    public static void initElement73() {
        if (ItemTypes.ELEMENT_73 != null) return;
        ItemTypes.ELEMENT_73 = ItemTypeBuilder
                .builder(ItemElement73Stack.class)
                .vanillaItem(ItemId.ELEMENT_73)
                .build();
    }

    public static void initElement74() {
        if (ItemTypes.ELEMENT_74 != null) return;
        ItemTypes.ELEMENT_74 = ItemTypeBuilder
                .builder(ItemElement74Stack.class)
                .vanillaItem(ItemId.ELEMENT_74)
                .build();
    }

    public static void initElement75() {
        if (ItemTypes.ELEMENT_75 != null) return;
        ItemTypes.ELEMENT_75 = ItemTypeBuilder
                .builder(ItemElement75Stack.class)
                .vanillaItem(ItemId.ELEMENT_75)
                .build();
    }

    public static void initElement76() {
        if (ItemTypes.ELEMENT_76 != null) return;
        ItemTypes.ELEMENT_76 = ItemTypeBuilder
                .builder(ItemElement76Stack.class)
                .vanillaItem(ItemId.ELEMENT_76)
                .build();
    }

    public static void initElement77() {
        if (ItemTypes.ELEMENT_77 != null) return;
        ItemTypes.ELEMENT_77 = ItemTypeBuilder
                .builder(ItemElement77Stack.class)
                .vanillaItem(ItemId.ELEMENT_77)
                .build();
    }

    public static void initElement78() {
        if (ItemTypes.ELEMENT_78 != null) return;
        ItemTypes.ELEMENT_78 = ItemTypeBuilder
                .builder(ItemElement78Stack.class)
                .vanillaItem(ItemId.ELEMENT_78)
                .build();
    }

    public static void initElement79() {
        if (ItemTypes.ELEMENT_79 != null) return;
        ItemTypes.ELEMENT_79 = ItemTypeBuilder
                .builder(ItemElement79Stack.class)
                .vanillaItem(ItemId.ELEMENT_79)
                .build();
    }

    public static void initElement8() {
        if (ItemTypes.ELEMENT_8 != null) return;
        ItemTypes.ELEMENT_8 = ItemTypeBuilder
                .builder(ItemElement8Stack.class)
                .vanillaItem(ItemId.ELEMENT_8)
                .build();
    }

    public static void initElement80() {
        if (ItemTypes.ELEMENT_80 != null) return;
        ItemTypes.ELEMENT_80 = ItemTypeBuilder
                .builder(ItemElement80Stack.class)
                .vanillaItem(ItemId.ELEMENT_80)
                .build();
    }

    public static void initElement81() {
        if (ItemTypes.ELEMENT_81 != null) return;
        ItemTypes.ELEMENT_81 = ItemTypeBuilder
                .builder(ItemElement81Stack.class)
                .vanillaItem(ItemId.ELEMENT_81)
                .build();
    }

    public static void initElement82() {
        if (ItemTypes.ELEMENT_82 != null) return;
        ItemTypes.ELEMENT_82 = ItemTypeBuilder
                .builder(ItemElement82Stack.class)
                .vanillaItem(ItemId.ELEMENT_82)
                .build();
    }

    public static void initElement83() {
        if (ItemTypes.ELEMENT_83 != null) return;
        ItemTypes.ELEMENT_83 = ItemTypeBuilder
                .builder(ItemElement83Stack.class)
                .vanillaItem(ItemId.ELEMENT_83)
                .build();
    }

    public static void initElement84() {
        if (ItemTypes.ELEMENT_84 != null) return;
        ItemTypes.ELEMENT_84 = ItemTypeBuilder
                .builder(ItemElement84Stack.class)
                .vanillaItem(ItemId.ELEMENT_84)
                .build();
    }

    public static void initElement85() {
        if (ItemTypes.ELEMENT_85 != null) return;
        ItemTypes.ELEMENT_85 = ItemTypeBuilder
                .builder(ItemElement85Stack.class)
                .vanillaItem(ItemId.ELEMENT_85)
                .build();
    }

    public static void initElement86() {
        if (ItemTypes.ELEMENT_86 != null) return;
        ItemTypes.ELEMENT_86 = ItemTypeBuilder
                .builder(ItemElement86Stack.class)
                .vanillaItem(ItemId.ELEMENT_86)
                .build();
    }

    public static void initElement87() {
        if (ItemTypes.ELEMENT_87 != null) return;
        ItemTypes.ELEMENT_87 = ItemTypeBuilder
                .builder(ItemElement87Stack.class)
                .vanillaItem(ItemId.ELEMENT_87)
                .build();
    }

    public static void initElement88() {
        if (ItemTypes.ELEMENT_88 != null) return;
        ItemTypes.ELEMENT_88 = ItemTypeBuilder
                .builder(ItemElement88Stack.class)
                .vanillaItem(ItemId.ELEMENT_88)
                .build();
    }

    public static void initElement89() {
        if (ItemTypes.ELEMENT_89 != null) return;
        ItemTypes.ELEMENT_89 = ItemTypeBuilder
                .builder(ItemElement89Stack.class)
                .vanillaItem(ItemId.ELEMENT_89)
                .build();
    }

    public static void initElement9() {
        if (ItemTypes.ELEMENT_9 != null) return;
        ItemTypes.ELEMENT_9 = ItemTypeBuilder
                .builder(ItemElement9Stack.class)
                .vanillaItem(ItemId.ELEMENT_9)
                .build();
    }

    public static void initElement90() {
        if (ItemTypes.ELEMENT_90 != null) return;
        ItemTypes.ELEMENT_90 = ItemTypeBuilder
                .builder(ItemElement90Stack.class)
                .vanillaItem(ItemId.ELEMENT_90)
                .build();
    }

    public static void initElement91() {
        if (ItemTypes.ELEMENT_91 != null) return;
        ItemTypes.ELEMENT_91 = ItemTypeBuilder
                .builder(ItemElement91Stack.class)
                .vanillaItem(ItemId.ELEMENT_91)
                .build();
    }

    public static void initElement92() {
        if (ItemTypes.ELEMENT_92 != null) return;
        ItemTypes.ELEMENT_92 = ItemTypeBuilder
                .builder(ItemElement92Stack.class)
                .vanillaItem(ItemId.ELEMENT_92)
                .build();
    }

    public static void initElement93() {
        if (ItemTypes.ELEMENT_93 != null) return;
        ItemTypes.ELEMENT_93 = ItemTypeBuilder
                .builder(ItemElement93Stack.class)
                .vanillaItem(ItemId.ELEMENT_93)
                .build();
    }

    public static void initElement94() {
        if (ItemTypes.ELEMENT_94 != null) return;
        ItemTypes.ELEMENT_94 = ItemTypeBuilder
                .builder(ItemElement94Stack.class)
                .vanillaItem(ItemId.ELEMENT_94)
                .build();
    }

    public static void initElement95() {
        if (ItemTypes.ELEMENT_95 != null) return;
        ItemTypes.ELEMENT_95 = ItemTypeBuilder
                .builder(ItemElement95Stack.class)
                .vanillaItem(ItemId.ELEMENT_95)
                .build();
    }

    public static void initElement96() {
        if (ItemTypes.ELEMENT_96 != null) return;
        ItemTypes.ELEMENT_96 = ItemTypeBuilder
                .builder(ItemElement96Stack.class)
                .vanillaItem(ItemId.ELEMENT_96)
                .build();
    }

    public static void initElement97() {
        if (ItemTypes.ELEMENT_97 != null) return;
        ItemTypes.ELEMENT_97 = ItemTypeBuilder
                .builder(ItemElement97Stack.class)
                .vanillaItem(ItemId.ELEMENT_97)
                .build();
    }

    public static void initElement98() {
        if (ItemTypes.ELEMENT_98 != null) return;
        ItemTypes.ELEMENT_98 = ItemTypeBuilder
                .builder(ItemElement98Stack.class)
                .vanillaItem(ItemId.ELEMENT_98)
                .build();
    }

    public static void initElement99() {
        if (ItemTypes.ELEMENT_99 != null) return;
        ItemTypes.ELEMENT_99 = ItemTypeBuilder
                .builder(ItemElement99Stack.class)
                .vanillaItem(ItemId.ELEMENT_99)
                .build();
    }

    public static void initElytra() {
        if (ItemTypes.ELYTRA != null) return;
        ItemTypes.ELYTRA = ItemTypeBuilder
                .builder(ItemElytraStack.class)
                .vanillaItem(ItemId.ELYTRA)
                .build();
    }

    public static void initEmerald() {
        if (ItemTypes.EMERALD != null) return;
        ItemTypes.EMERALD = ItemTypeBuilder
                .builder(ItemEmeraldStack.class)
                .vanillaItem(ItemId.EMERALD)
                .build();
    }

    public static void initEmeraldBlock() {
        if (ItemTypes.EMERALD_BLOCK != null) return;
        ItemTypes.EMERALD_BLOCK = ItemTypeBuilder
                .builder(ItemEmeraldBlockStack.class)
                .vanillaItem(ItemId.EMERALD_BLOCK)
                .build();
    }

    public static void initEmeraldOre() {
        if (ItemTypes.EMERALD_ORE != null) return;
        ItemTypes.EMERALD_ORE = ItemTypeBuilder
                .builder(ItemEmeraldOreStack.class)
                .vanillaItem(ItemId.EMERALD_ORE)
                .build();
    }

    public static void initEmptyMap() {
        if (ItemTypes.EMPTY_MAP != null) return;
        ItemTypes.EMPTY_MAP = ItemTypeBuilder
                .builder(ItemEmptyMapStack.class)
                .vanillaItem(ItemId.EMPTY_MAP)
                .build();
    }

    public static void initEnchantedBook() {
        if (ItemTypes.ENCHANTED_BOOK != null) return;
        ItemTypes.ENCHANTED_BOOK = ItemTypeBuilder
                .builder(ItemEnchantedBookStack.class)
                .vanillaItem(ItemId.ENCHANTED_BOOK)
                .build();
    }

    public static void initEnchantedGoldenApple() {
        if (ItemTypes.ENCHANTED_GOLDEN_APPLE != null) return;
        ItemTypes.ENCHANTED_GOLDEN_APPLE = ItemTypeBuilder
                .builder(ItemEnchantedGoldenAppleStack.class)
                .vanillaItem(ItemId.ENCHANTED_GOLDEN_APPLE)
                .build();
    }

    public static void initEnchantingTable() {
        if (ItemTypes.ENCHANTING_TABLE != null) return;
        ItemTypes.ENCHANTING_TABLE = ItemTypeBuilder
                .builder(ItemEnchantingTableStack.class)
                .vanillaItem(ItemId.ENCHANTING_TABLE)
                .build();
    }

    public static void initEndBrickStairs() {
        if (ItemTypes.END_BRICK_STAIRS != null) return;
        ItemTypes.END_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemEndBrickStairsStack.class)
                .vanillaItem(ItemId.END_BRICK_STAIRS)
                .build();
    }

    public static void initEndBricks() {
        if (ItemTypes.END_BRICKS != null) return;
        ItemTypes.END_BRICKS = ItemTypeBuilder
                .builder(ItemEndBricksStack.class)
                .vanillaItem(ItemId.END_BRICKS)
                .build();
    }

    public static void initEndCrystal() {
        if (ItemTypes.END_CRYSTAL != null) return;
        ItemTypes.END_CRYSTAL = ItemTypeBuilder
                .builder(ItemEndCrystalStack.class)
                .vanillaItem(ItemId.END_CRYSTAL)
                .build();
    }

    public static void initEndGateway() {
        if (ItemTypes.END_GATEWAY != null) return;
        ItemTypes.END_GATEWAY = ItemTypeBuilder
                .builder(ItemEndGatewayStack.class)
                .vanillaItem(ItemId.END_GATEWAY)
                .build();
    }

    public static void initEndPortal() {
        if (ItemTypes.END_PORTAL != null) return;
        ItemTypes.END_PORTAL = ItemTypeBuilder
                .builder(ItemEndPortalStack.class)
                .vanillaItem(ItemId.END_PORTAL)
                .build();
    }

    public static void initEndPortalFrame() {
        if (ItemTypes.END_PORTAL_FRAME != null) return;
        ItemTypes.END_PORTAL_FRAME = ItemTypeBuilder
                .builder(ItemEndPortalFrameStack.class)
                .vanillaItem(ItemId.END_PORTAL_FRAME)
                .build();
    }

    public static void initEndRod() {
        if (ItemTypes.END_ROD != null) return;
        ItemTypes.END_ROD = ItemTypeBuilder
                .builder(ItemEndRodStack.class)
                .vanillaItem(ItemId.END_ROD)
                .build();
    }

    public static void initEndStone() {
        if (ItemTypes.END_STONE != null) return;
        ItemTypes.END_STONE = ItemTypeBuilder
                .builder(ItemEndStoneStack.class)
                .vanillaItem(ItemId.END_STONE)
                .build();
    }

    public static void initEndStoneBrickDoubleSlab() {
        if (ItemTypes.END_STONE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.END_STONE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemEndStoneBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.END_STONE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initEndStoneBrickSlab() {
        if (ItemTypes.END_STONE_BRICK_SLAB != null) return;
        ItemTypes.END_STONE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemEndStoneBrickSlabStack.class)
                .vanillaItem(ItemId.END_STONE_BRICK_SLAB)
                .build();
    }

    public static void initEnderChest() {
        if (ItemTypes.ENDER_CHEST != null) return;
        ItemTypes.ENDER_CHEST = ItemTypeBuilder
                .builder(ItemEnderChestStack.class)
                .vanillaItem(ItemId.ENDER_CHEST)
                .build();
    }

    public static void initEnderDragonSpawnEgg() {
        if (ItemTypes.ENDER_DRAGON_SPAWN_EGG != null) return;
        ItemTypes.ENDER_DRAGON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEnderDragonSpawnEggStack.class)
                .vanillaItem(ItemId.ENDER_DRAGON_SPAWN_EGG)
                .build();
    }

    public static void initEnderEye() {
        if (ItemTypes.ENDER_EYE != null) return;
        ItemTypes.ENDER_EYE = ItemTypeBuilder
                .builder(ItemEnderEyeStack.class)
                .vanillaItem(ItemId.ENDER_EYE)
                .build();
    }

    public static void initEnderPearl() {
        if (ItemTypes.ENDER_PEARL != null) return;
        ItemTypes.ENDER_PEARL = ItemTypeBuilder
                .builder(ItemEnderPearlStack.class)
                .vanillaItem(ItemId.ENDER_PEARL)
                .build();
    }

    public static void initEndermanSpawnEgg() {
        if (ItemTypes.ENDERMAN_SPAWN_EGG != null) return;
        ItemTypes.ENDERMAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEndermanSpawnEggStack.class)
                .vanillaItem(ItemId.ENDERMAN_SPAWN_EGG)
                .build();
    }

    public static void initEndermiteSpawnEgg() {
        if (ItemTypes.ENDERMITE_SPAWN_EGG != null) return;
        ItemTypes.ENDERMITE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEndermiteSpawnEggStack.class)
                .vanillaItem(ItemId.ENDERMITE_SPAWN_EGG)
                .build();
    }

    public static void initEvokerSpawnEgg() {
        if (ItemTypes.EVOKER_SPAWN_EGG != null) return;
        ItemTypes.EVOKER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEvokerSpawnEggStack.class)
                .vanillaItem(ItemId.EVOKER_SPAWN_EGG)
                .build();
    }

    public static void initExperienceBottle() {
        if (ItemTypes.EXPERIENCE_BOTTLE != null) return;
        ItemTypes.EXPERIENCE_BOTTLE = ItemTypeBuilder
                .builder(ItemExperienceBottleStack.class)
                .vanillaItem(ItemId.EXPERIENCE_BOTTLE)
                .build();
    }

    public static void initExplorerPotterySherd() {
        if (ItemTypes.EXPLORER_POTTERY_SHERD != null) return;
        ItemTypes.EXPLORER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemExplorerPotterySherdStack.class)
                .vanillaItem(ItemId.EXPLORER_POTTERY_SHERD)
                .build();
    }

    public static void initExposedChiseledCopper() {
        if (ItemTypes.EXPOSED_CHISELED_COPPER != null) return;
        ItemTypes.EXPOSED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemExposedChiseledCopperStack.class)
                .vanillaItem(ItemId.EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initExposedCopper() {
        if (ItemTypes.EXPOSED_COPPER != null) return;
        ItemTypes.EXPOSED_COPPER = ItemTypeBuilder
                .builder(ItemExposedCopperStack.class)
                .vanillaItem(ItemId.EXPOSED_COPPER)
                .build();
    }

    public static void initExposedCopperBulb() {
        if (ItemTypes.EXPOSED_COPPER_BULB != null) return;
        ItemTypes.EXPOSED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemExposedCopperBulbStack.class)
                .vanillaItem(ItemId.EXPOSED_COPPER_BULB)
                .build();
    }

    public static void initExposedCopperDoor() {
        if (ItemTypes.EXPOSED_COPPER_DOOR != null) return;
        ItemTypes.EXPOSED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemExposedCopperDoorStack.class)
                .vanillaItem(ItemId.EXPOSED_COPPER_DOOR)
                .build();
    }

    public static void initExposedCopperGrate() {
        if (ItemTypes.EXPOSED_COPPER_GRATE != null) return;
        ItemTypes.EXPOSED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemExposedCopperGrateStack.class)
                .vanillaItem(ItemId.EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initExposedCopperTrapdoor() {
        if (ItemTypes.EXPOSED_COPPER_TRAPDOOR != null) return;
        ItemTypes.EXPOSED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemExposedCopperTrapdoorStack.class)
                .vanillaItem(ItemId.EXPOSED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initExposedCutCopper() {
        if (ItemTypes.EXPOSED_CUT_COPPER != null) return;
        ItemTypes.EXPOSED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemExposedCutCopperStack.class)
                .vanillaItem(ItemId.EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initExposedCutCopperSlab() {
        if (ItemTypes.EXPOSED_CUT_COPPER_SLAB != null) return;
        ItemTypes.EXPOSED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemExposedCutCopperSlabStack.class)
                .vanillaItem(ItemId.EXPOSED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initExposedCutCopperStairs() {
        if (ItemTypes.EXPOSED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.EXPOSED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemExposedCutCopperStairsStack.class)
                .vanillaItem(ItemId.EXPOSED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initExposedDoubleCutCopperSlab() {
        if (ItemTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemExposedDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initEyeArmorTrimSmithingTemplate() {
        if (ItemTypes.EYE_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.EYE_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemEyeArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.EYE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initFarmland() {
        if (ItemTypes.FARMLAND != null) return;
        ItemTypes.FARMLAND = ItemTypeBuilder
                .builder(ItemFarmlandStack.class)
                .vanillaItem(ItemId.FARMLAND)
                .build();
    }

    public static void initFeather() {
        if (ItemTypes.FEATHER != null) return;
        ItemTypes.FEATHER = ItemTypeBuilder
                .builder(ItemFeatherStack.class)
                .vanillaItem(ItemId.FEATHER)
                .build();
    }

    public static void initFence() {
        if (ItemTypes.FENCE != null) return;
        ItemTypes.FENCE = ItemTypeBuilder
                .builder(ItemFenceStack.class)
                .vanillaItem(ItemId.FENCE)
                .build();
    }

    public static void initFenceGate() {
        if (ItemTypes.FENCE_GATE != null) return;
        ItemTypes.FENCE_GATE = ItemTypeBuilder
                .builder(ItemFenceGateStack.class)
                .vanillaItem(ItemId.FENCE_GATE)
                .build();
    }

    public static void initFermentedSpiderEye() {
        if (ItemTypes.FERMENTED_SPIDER_EYE != null) return;
        ItemTypes.FERMENTED_SPIDER_EYE = ItemTypeBuilder
                .builder(ItemFermentedSpiderEyeStack.class)
                .vanillaItem(ItemId.FERMENTED_SPIDER_EYE)
                .build();
    }

    public static void initFern() {
        if (ItemTypes.FERN != null) return;
        ItemTypes.FERN = ItemTypeBuilder
                .builder(ItemFernStack.class)
                .vanillaItem(ItemId.FERN)
                .build();
    }

    public static void initFieldMasonedBannerPattern() {
        if (ItemTypes.FIELD_MASONED_BANNER_PATTERN != null) return;
        ItemTypes.FIELD_MASONED_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemFieldMasonedBannerPatternStack.class)
                .vanillaItem(ItemId.FIELD_MASONED_BANNER_PATTERN)
                .build();
    }

    public static void initFilledMap() {
        if (ItemTypes.FILLED_MAP != null) return;
        ItemTypes.FILLED_MAP = ItemTypeBuilder
                .builder(ItemFilledMapStack.class)
                .vanillaItem(ItemId.FILLED_MAP)
                .build();
    }

    public static void initFire() {
        if (ItemTypes.FIRE != null) return;
        ItemTypes.FIRE = ItemTypeBuilder
                .builder(ItemFireStack.class)
                .vanillaItem(ItemId.FIRE)
                .build();
    }

    public static void initFireCharge() {
        if (ItemTypes.FIRE_CHARGE != null) return;
        ItemTypes.FIRE_CHARGE = ItemTypeBuilder
                .builder(ItemFireChargeStack.class)
                .vanillaItem(ItemId.FIRE_CHARGE)
                .build();
    }

    public static void initFireCoral() {
        if (ItemTypes.FIRE_CORAL != null) return;
        ItemTypes.FIRE_CORAL = ItemTypeBuilder
                .builder(ItemFireCoralStack.class)
                .vanillaItem(ItemId.FIRE_CORAL)
                .build();
    }

    public static void initFireCoralBlock() {
        if (ItemTypes.FIRE_CORAL_BLOCK != null) return;
        ItemTypes.FIRE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemFireCoralBlockStack.class)
                .vanillaItem(ItemId.FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initFireCoralFan() {
        if (ItemTypes.FIRE_CORAL_FAN != null) return;
        ItemTypes.FIRE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemFireCoralFanStack.class)
                .vanillaItem(ItemId.FIRE_CORAL_FAN)
                .build();
    }

    public static void initFireCoralWallFan() {
        if (ItemTypes.FIRE_CORAL_WALL_FAN != null) return;
        ItemTypes.FIRE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemFireCoralWallFanStack.class)
                .vanillaItem(ItemId.FIRE_CORAL_WALL_FAN)
                .build();
    }

    public static void initFireworkRocket() {
        if (ItemTypes.FIREWORK_ROCKET != null) return;
        ItemTypes.FIREWORK_ROCKET = ItemTypeBuilder
                .builder(ItemFireworkRocketStack.class)
                .vanillaItem(ItemId.FIREWORK_ROCKET)
                .build();
    }

    public static void initFireworkStar() {
        if (ItemTypes.FIREWORK_STAR != null) return;
        ItemTypes.FIREWORK_STAR = ItemTypeBuilder
                .builder(ItemFireworkStarStack.class)
                .vanillaItem(ItemId.FIREWORK_STAR)
                .build();
    }

    public static void initFishingRod() {
        if (ItemTypes.FISHING_ROD != null) return;
        ItemTypes.FISHING_ROD = ItemTypeBuilder
                .builder(ItemFishingRodStack.class)
                .vanillaItem(ItemId.FISHING_ROD)
                .build();
    }

    public static void initFletchingTable() {
        if (ItemTypes.FLETCHING_TABLE != null) return;
        ItemTypes.FLETCHING_TABLE = ItemTypeBuilder
                .builder(ItemFletchingTableStack.class)
                .vanillaItem(ItemId.FLETCHING_TABLE)
                .build();
    }

    public static void initFlint() {
        if (ItemTypes.FLINT != null) return;
        ItemTypes.FLINT = ItemTypeBuilder
                .builder(ItemFlintStack.class)
                .vanillaItem(ItemId.FLINT)
                .build();
    }

    public static void initFlintAndSteel() {
        if (ItemTypes.FLINT_AND_STEEL != null) return;
        ItemTypes.FLINT_AND_STEEL = ItemTypeBuilder
                .builder(ItemFlintAndSteelStack.class)
                .vanillaItem(ItemId.FLINT_AND_STEEL)
                .build();
    }

    public static void initFlowArmorTrimSmithingTemplate() {
        if (ItemTypes.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemFlowArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initFlowBannerPattern() {
        if (ItemTypes.FLOW_BANNER_PATTERN != null) return;
        ItemTypes.FLOW_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemFlowBannerPatternStack.class)
                .vanillaItem(ItemId.FLOW_BANNER_PATTERN)
                .build();
    }

    public static void initFlowPotterySherd() {
        if (ItemTypes.FLOW_POTTERY_SHERD != null) return;
        ItemTypes.FLOW_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemFlowPotterySherdStack.class)
                .vanillaItem(ItemId.FLOW_POTTERY_SHERD)
                .build();
    }

    public static void initFlowerBannerPattern() {
        if (ItemTypes.FLOWER_BANNER_PATTERN != null) return;
        ItemTypes.FLOWER_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemFlowerBannerPatternStack.class)
                .vanillaItem(ItemId.FLOWER_BANNER_PATTERN)
                .build();
    }

    public static void initFlowerPot() {
        if (ItemTypes.FLOWER_POT != null) return;
        ItemTypes.FLOWER_POT = ItemTypeBuilder
                .builder(ItemFlowerPotStack.class)
                .vanillaItem(ItemId.FLOWER_POT)
                .build();
    }

    public static void initFloweringAzalea() {
        if (ItemTypes.FLOWERING_AZALEA != null) return;
        ItemTypes.FLOWERING_AZALEA = ItemTypeBuilder
                .builder(ItemFloweringAzaleaStack.class)
                .vanillaItem(ItemId.FLOWERING_AZALEA)
                .build();
    }

    public static void initFlowingLava() {
        if (ItemTypes.FLOWING_LAVA != null) return;
        ItemTypes.FLOWING_LAVA = ItemTypeBuilder
                .builder(ItemFlowingLavaStack.class)
                .vanillaItem(ItemId.FLOWING_LAVA)
                .build();
    }

    public static void initFlowingWater() {
        if (ItemTypes.FLOWING_WATER != null) return;
        ItemTypes.FLOWING_WATER = ItemTypeBuilder
                .builder(ItemFlowingWaterStack.class)
                .vanillaItem(ItemId.FLOWING_WATER)
                .build();
    }

    public static void initFoxSpawnEgg() {
        if (ItemTypes.FOX_SPAWN_EGG != null) return;
        ItemTypes.FOX_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemFoxSpawnEggStack.class)
                .vanillaItem(ItemId.FOX_SPAWN_EGG)
                .build();
    }

    public static void initFrame() {
        if (ItemTypes.FRAME != null) return;
        ItemTypes.FRAME = ItemTypeBuilder
                .builder(ItemFrameStack.class)
                .vanillaItem(ItemId.FRAME)
                .build();
    }

    public static void initFriendPotterySherd() {
        if (ItemTypes.FRIEND_POTTERY_SHERD != null) return;
        ItemTypes.FRIEND_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemFriendPotterySherdStack.class)
                .vanillaItem(ItemId.FRIEND_POTTERY_SHERD)
                .build();
    }

    public static void initFrogSpawn() {
        if (ItemTypes.FROG_SPAWN != null) return;
        ItemTypes.FROG_SPAWN = ItemTypeBuilder
                .builder(ItemFrogSpawnStack.class)
                .vanillaItem(ItemId.FROG_SPAWN)
                .build();
    }

    public static void initFrogSpawnEgg() {
        if (ItemTypes.FROG_SPAWN_EGG != null) return;
        ItemTypes.FROG_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemFrogSpawnEggStack.class)
                .vanillaItem(ItemId.FROG_SPAWN_EGG)
                .build();
    }

    public static void initFrostedIce() {
        if (ItemTypes.FROSTED_ICE != null) return;
        ItemTypes.FROSTED_ICE = ItemTypeBuilder
                .builder(ItemFrostedIceStack.class)
                .vanillaItem(ItemId.FROSTED_ICE)
                .build();
    }

    public static void initFurnace() {
        if (ItemTypes.FURNACE != null) return;
        ItemTypes.FURNACE = ItemTypeBuilder
                .builder(ItemFurnaceStack.class)
                .vanillaItem(ItemId.FURNACE)
                .build();
    }

    public static void initGhastSpawnEgg() {
        if (ItemTypes.GHAST_SPAWN_EGG != null) return;
        ItemTypes.GHAST_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGhastSpawnEggStack.class)
                .vanillaItem(ItemId.GHAST_SPAWN_EGG)
                .build();
    }

    public static void initGhastTear() {
        if (ItemTypes.GHAST_TEAR != null) return;
        ItemTypes.GHAST_TEAR = ItemTypeBuilder
                .builder(ItemGhastTearStack.class)
                .vanillaItem(ItemId.GHAST_TEAR)
                .build();
    }

    public static void initGildedBlackstone() {
        if (ItemTypes.GILDED_BLACKSTONE != null) return;
        ItemTypes.GILDED_BLACKSTONE = ItemTypeBuilder
                .builder(ItemGildedBlackstoneStack.class)
                .vanillaItem(ItemId.GILDED_BLACKSTONE)
                .build();
    }

    public static void initGlass() {
        if (ItemTypes.GLASS != null) return;
        ItemTypes.GLASS = ItemTypeBuilder
                .builder(ItemGlassStack.class)
                .vanillaItem(ItemId.GLASS)
                .build();
    }

    public static void initGlassBottle() {
        if (ItemTypes.GLASS_BOTTLE != null) return;
        ItemTypes.GLASS_BOTTLE = ItemTypeBuilder
                .builder(ItemGlassBottleStack.class)
                .vanillaItem(ItemId.GLASS_BOTTLE)
                .build();
    }

    public static void initGlassPane() {
        if (ItemTypes.GLASS_PANE != null) return;
        ItemTypes.GLASS_PANE = ItemTypeBuilder
                .builder(ItemGlassPaneStack.class)
                .vanillaItem(ItemId.GLASS_PANE)
                .build();
    }

    public static void initGlisteringMelonSlice() {
        if (ItemTypes.GLISTERING_MELON_SLICE != null) return;
        ItemTypes.GLISTERING_MELON_SLICE = ItemTypeBuilder
                .builder(ItemGlisteringMelonSliceStack.class)
                .vanillaItem(ItemId.GLISTERING_MELON_SLICE)
                .build();
    }

    public static void initGlobeBannerPattern() {
        if (ItemTypes.GLOBE_BANNER_PATTERN != null) return;
        ItemTypes.GLOBE_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemGlobeBannerPatternStack.class)
                .vanillaItem(ItemId.GLOBE_BANNER_PATTERN)
                .build();
    }

    public static void initGlowBerries() {
        if (ItemTypes.GLOW_BERRIES != null) return;
        ItemTypes.GLOW_BERRIES = ItemTypeBuilder
                .builder(ItemGlowBerriesStack.class)
                .vanillaItem(ItemId.GLOW_BERRIES)
                .build();
    }

    public static void initGlowFrame() {
        if (ItemTypes.GLOW_FRAME != null) return;
        ItemTypes.GLOW_FRAME = ItemTypeBuilder
                .builder(ItemGlowFrameStack.class)
                .vanillaItem(ItemId.GLOW_FRAME)
                .build();
    }

    public static void initGlowInkSac() {
        if (ItemTypes.GLOW_INK_SAC != null) return;
        ItemTypes.GLOW_INK_SAC = ItemTypeBuilder
                .builder(ItemGlowInkSacStack.class)
                .vanillaItem(ItemId.GLOW_INK_SAC)
                .build();
    }

    public static void initGlowLichen() {
        if (ItemTypes.GLOW_LICHEN != null) return;
        ItemTypes.GLOW_LICHEN = ItemTypeBuilder
                .builder(ItemGlowLichenStack.class)
                .vanillaItem(ItemId.GLOW_LICHEN)
                .build();
    }

    public static void initGlowSquidSpawnEgg() {
        if (ItemTypes.GLOW_SQUID_SPAWN_EGG != null) return;
        ItemTypes.GLOW_SQUID_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGlowSquidSpawnEggStack.class)
                .vanillaItem(ItemId.GLOW_SQUID_SPAWN_EGG)
                .build();
    }

    public static void initGlowStick() {
        if (ItemTypes.GLOW_STICK != null) return;
        ItemTypes.GLOW_STICK = ItemTypeBuilder
                .builder(ItemGlowStickStack.class)
                .vanillaItem(ItemId.GLOW_STICK)
                .build();
    }

    public static void initGlowingobsidian() {
        if (ItemTypes.GLOWINGOBSIDIAN != null) return;
        ItemTypes.GLOWINGOBSIDIAN = ItemTypeBuilder
                .builder(ItemGlowingobsidianStack.class)
                .vanillaItem(ItemId.GLOWINGOBSIDIAN)
                .build();
    }

    public static void initGlowstone() {
        if (ItemTypes.GLOWSTONE != null) return;
        ItemTypes.GLOWSTONE = ItemTypeBuilder
                .builder(ItemGlowstoneStack.class)
                .vanillaItem(ItemId.GLOWSTONE)
                .build();
    }

    public static void initGlowstoneDust() {
        if (ItemTypes.GLOWSTONE_DUST != null) return;
        ItemTypes.GLOWSTONE_DUST = ItemTypeBuilder
                .builder(ItemGlowstoneDustStack.class)
                .vanillaItem(ItemId.GLOWSTONE_DUST)
                .build();
    }

    public static void initGoatHorn() {
        if (ItemTypes.GOAT_HORN != null) return;
        ItemTypes.GOAT_HORN = ItemTypeBuilder
                .builder(ItemGoatHornStack.class)
                .vanillaItem(ItemId.GOAT_HORN)
                .build();
    }

    public static void initGoatSpawnEgg() {
        if (ItemTypes.GOAT_SPAWN_EGG != null) return;
        ItemTypes.GOAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGoatSpawnEggStack.class)
                .vanillaItem(ItemId.GOAT_SPAWN_EGG)
                .build();
    }

    public static void initGoldBlock() {
        if (ItemTypes.GOLD_BLOCK != null) return;
        ItemTypes.GOLD_BLOCK = ItemTypeBuilder
                .builder(ItemGoldBlockStack.class)
                .vanillaItem(ItemId.GOLD_BLOCK)
                .build();
    }

    public static void initGoldIngot() {
        if (ItemTypes.GOLD_INGOT != null) return;
        ItemTypes.GOLD_INGOT = ItemTypeBuilder
                .builder(ItemGoldIngotStack.class)
                .vanillaItem(ItemId.GOLD_INGOT)
                .build();
    }

    public static void initGoldNugget() {
        if (ItemTypes.GOLD_NUGGET != null) return;
        ItemTypes.GOLD_NUGGET = ItemTypeBuilder
                .builder(ItemGoldNuggetStack.class)
                .vanillaItem(ItemId.GOLD_NUGGET)
                .build();
    }

    public static void initGoldOre() {
        if (ItemTypes.GOLD_ORE != null) return;
        ItemTypes.GOLD_ORE = ItemTypeBuilder
                .builder(ItemGoldOreStack.class)
                .vanillaItem(ItemId.GOLD_ORE)
                .build();
    }

    public static void initGoldenApple() {
        if (ItemTypes.GOLDEN_APPLE != null) return;
        ItemTypes.GOLDEN_APPLE = ItemTypeBuilder
                .builder(ItemGoldenAppleStack.class)
                .vanillaItem(ItemId.GOLDEN_APPLE)
                .build();
    }

    public static void initGoldenAxe() {
        if (ItemTypes.GOLDEN_AXE != null) return;
        ItemTypes.GOLDEN_AXE = ItemTypeBuilder
                .builder(ItemGoldenAxeStack.class)
                .vanillaItem(ItemId.GOLDEN_AXE)
                .build();
    }

    public static void initGoldenBoots() {
        if (ItemTypes.GOLDEN_BOOTS != null) return;
        ItemTypes.GOLDEN_BOOTS = ItemTypeBuilder
                .builder(ItemGoldenBootsStack.class)
                .vanillaItem(ItemId.GOLDEN_BOOTS)
                .build();
    }

    public static void initGoldenCarrot() {
        if (ItemTypes.GOLDEN_CARROT != null) return;
        ItemTypes.GOLDEN_CARROT = ItemTypeBuilder
                .builder(ItemGoldenCarrotStack.class)
                .vanillaItem(ItemId.GOLDEN_CARROT)
                .build();
    }

    public static void initGoldenChestplate() {
        if (ItemTypes.GOLDEN_CHESTPLATE != null) return;
        ItemTypes.GOLDEN_CHESTPLATE = ItemTypeBuilder
                .builder(ItemGoldenChestplateStack.class)
                .vanillaItem(ItemId.GOLDEN_CHESTPLATE)
                .build();
    }

    public static void initGoldenHelmet() {
        if (ItemTypes.GOLDEN_HELMET != null) return;
        ItemTypes.GOLDEN_HELMET = ItemTypeBuilder
                .builder(ItemGoldenHelmetStack.class)
                .vanillaItem(ItemId.GOLDEN_HELMET)
                .build();
    }

    public static void initGoldenHoe() {
        if (ItemTypes.GOLDEN_HOE != null) return;
        ItemTypes.GOLDEN_HOE = ItemTypeBuilder
                .builder(ItemGoldenHoeStack.class)
                .vanillaItem(ItemId.GOLDEN_HOE)
                .build();
    }

    public static void initGoldenHorseArmor() {
        if (ItemTypes.GOLDEN_HORSE_ARMOR != null) return;
        ItemTypes.GOLDEN_HORSE_ARMOR = ItemTypeBuilder
                .builder(ItemGoldenHorseArmorStack.class)
                .vanillaItem(ItemId.GOLDEN_HORSE_ARMOR)
                .build();
    }

    public static void initGoldenLeggings() {
        if (ItemTypes.GOLDEN_LEGGINGS != null) return;
        ItemTypes.GOLDEN_LEGGINGS = ItemTypeBuilder
                .builder(ItemGoldenLeggingsStack.class)
                .vanillaItem(ItemId.GOLDEN_LEGGINGS)
                .build();
    }

    public static void initGoldenPickaxe() {
        if (ItemTypes.GOLDEN_PICKAXE != null) return;
        ItemTypes.GOLDEN_PICKAXE = ItemTypeBuilder
                .builder(ItemGoldenPickaxeStack.class)
                .vanillaItem(ItemId.GOLDEN_PICKAXE)
                .build();
    }

    public static void initGoldenRail() {
        if (ItemTypes.GOLDEN_RAIL != null) return;
        ItemTypes.GOLDEN_RAIL = ItemTypeBuilder
                .builder(ItemGoldenRailStack.class)
                .vanillaItem(ItemId.GOLDEN_RAIL)
                .build();
    }

    public static void initGoldenShovel() {
        if (ItemTypes.GOLDEN_SHOVEL != null) return;
        ItemTypes.GOLDEN_SHOVEL = ItemTypeBuilder
                .builder(ItemGoldenShovelStack.class)
                .vanillaItem(ItemId.GOLDEN_SHOVEL)
                .build();
    }

    public static void initGoldenSword() {
        if (ItemTypes.GOLDEN_SWORD != null) return;
        ItemTypes.GOLDEN_SWORD = ItemTypeBuilder
                .builder(ItemGoldenSwordStack.class)
                .vanillaItem(ItemId.GOLDEN_SWORD)
                .build();
    }

    public static void initGranite() {
        if (ItemTypes.GRANITE != null) return;
        ItemTypes.GRANITE = ItemTypeBuilder
                .builder(ItemGraniteStack.class)
                .vanillaItem(ItemId.GRANITE)
                .build();
    }

    public static void initGraniteDoubleSlab() {
        if (ItemTypes.GRANITE_DOUBLE_SLAB != null) return;
        ItemTypes.GRANITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemGraniteDoubleSlabStack.class)
                .vanillaItem(ItemId.GRANITE_DOUBLE_SLAB)
                .build();
    }

    public static void initGraniteSlab() {
        if (ItemTypes.GRANITE_SLAB != null) return;
        ItemTypes.GRANITE_SLAB = ItemTypeBuilder
                .builder(ItemGraniteSlabStack.class)
                .vanillaItem(ItemId.GRANITE_SLAB)
                .build();
    }

    public static void initGraniteStairs() {
        if (ItemTypes.GRANITE_STAIRS != null) return;
        ItemTypes.GRANITE_STAIRS = ItemTypeBuilder
                .builder(ItemGraniteStairsStack.class)
                .vanillaItem(ItemId.GRANITE_STAIRS)
                .build();
    }

    public static void initGrassBlock() {
        if (ItemTypes.GRASS_BLOCK != null) return;
        ItemTypes.GRASS_BLOCK = ItemTypeBuilder
                .builder(ItemGrassBlockStack.class)
                .vanillaItem(ItemId.GRASS_BLOCK)
                .build();
    }

    public static void initGrassPath() {
        if (ItemTypes.GRASS_PATH != null) return;
        ItemTypes.GRASS_PATH = ItemTypeBuilder
                .builder(ItemGrassPathStack.class)
                .vanillaItem(ItemId.GRASS_PATH)
                .build();
    }

    public static void initGravel() {
        if (ItemTypes.GRAVEL != null) return;
        ItemTypes.GRAVEL = ItemTypeBuilder
                .builder(ItemGravelStack.class)
                .vanillaItem(ItemId.GRAVEL)
                .build();
    }

    public static void initGrayCandle() {
        if (ItemTypes.GRAY_CANDLE != null) return;
        ItemTypes.GRAY_CANDLE = ItemTypeBuilder
                .builder(ItemGrayCandleStack.class)
                .vanillaItem(ItemId.GRAY_CANDLE)
                .build();
    }

    public static void initGrayCandleCake() {
        if (ItemTypes.GRAY_CANDLE_CAKE != null) return;
        ItemTypes.GRAY_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemGrayCandleCakeStack.class)
                .vanillaItem(ItemId.GRAY_CANDLE_CAKE)
                .build();
    }

    public static void initGrayCarpet() {
        if (ItemTypes.GRAY_CARPET != null) return;
        ItemTypes.GRAY_CARPET = ItemTypeBuilder
                .builder(ItemGrayCarpetStack.class)
                .vanillaItem(ItemId.GRAY_CARPET)
                .build();
    }

    public static void initGrayConcrete() {
        if (ItemTypes.GRAY_CONCRETE != null) return;
        ItemTypes.GRAY_CONCRETE = ItemTypeBuilder
                .builder(ItemGrayConcreteStack.class)
                .vanillaItem(ItemId.GRAY_CONCRETE)
                .build();
    }

    public static void initGrayConcretePowder() {
        if (ItemTypes.GRAY_CONCRETE_POWDER != null) return;
        ItemTypes.GRAY_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemGrayConcretePowderStack.class)
                .vanillaItem(ItemId.GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initGrayDye() {
        if (ItemTypes.GRAY_DYE != null) return;
        ItemTypes.GRAY_DYE = ItemTypeBuilder
                .builder(ItemGrayDyeStack.class)
                .vanillaItem(ItemId.GRAY_DYE)
                .build();
    }

    public static void initGrayGlazedTerracotta() {
        if (ItemTypes.GRAY_GLAZED_TERRACOTTA != null) return;
        ItemTypes.GRAY_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemGrayGlazedTerracottaStack.class)
                .vanillaItem(ItemId.GRAY_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initGrayShulkerBox() {
        if (ItemTypes.GRAY_SHULKER_BOX != null) return;
        ItemTypes.GRAY_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemGrayShulkerBoxStack.class)
                .vanillaItem(ItemId.GRAY_SHULKER_BOX)
                .build();
    }

    public static void initGrayStainedGlass() {
        if (ItemTypes.GRAY_STAINED_GLASS != null) return;
        ItemTypes.GRAY_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemGrayStainedGlassStack.class)
                .vanillaItem(ItemId.GRAY_STAINED_GLASS)
                .build();
    }

    public static void initGrayStainedGlassPane() {
        if (ItemTypes.GRAY_STAINED_GLASS_PANE != null) return;
        ItemTypes.GRAY_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemGrayStainedGlassPaneStack.class)
                .vanillaItem(ItemId.GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGrayTerracotta() {
        if (ItemTypes.GRAY_TERRACOTTA != null) return;
        ItemTypes.GRAY_TERRACOTTA = ItemTypeBuilder
                .builder(ItemGrayTerracottaStack.class)
                .vanillaItem(ItemId.GRAY_TERRACOTTA)
                .build();
    }

    public static void initGrayWool() {
        if (ItemTypes.GRAY_WOOL != null) return;
        ItemTypes.GRAY_WOOL = ItemTypeBuilder
                .builder(ItemGrayWoolStack.class)
                .vanillaItem(ItemId.GRAY_WOOL)
                .build();
    }

    public static void initGreenCandle() {
        if (ItemTypes.GREEN_CANDLE != null) return;
        ItemTypes.GREEN_CANDLE = ItemTypeBuilder
                .builder(ItemGreenCandleStack.class)
                .vanillaItem(ItemId.GREEN_CANDLE)
                .build();
    }

    public static void initGreenCandleCake() {
        if (ItemTypes.GREEN_CANDLE_CAKE != null) return;
        ItemTypes.GREEN_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemGreenCandleCakeStack.class)
                .vanillaItem(ItemId.GREEN_CANDLE_CAKE)
                .build();
    }

    public static void initGreenCarpet() {
        if (ItemTypes.GREEN_CARPET != null) return;
        ItemTypes.GREEN_CARPET = ItemTypeBuilder
                .builder(ItemGreenCarpetStack.class)
                .vanillaItem(ItemId.GREEN_CARPET)
                .build();
    }

    public static void initGreenConcrete() {
        if (ItemTypes.GREEN_CONCRETE != null) return;
        ItemTypes.GREEN_CONCRETE = ItemTypeBuilder
                .builder(ItemGreenConcreteStack.class)
                .vanillaItem(ItemId.GREEN_CONCRETE)
                .build();
    }

    public static void initGreenConcretePowder() {
        if (ItemTypes.GREEN_CONCRETE_POWDER != null) return;
        ItemTypes.GREEN_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemGreenConcretePowderStack.class)
                .vanillaItem(ItemId.GREEN_CONCRETE_POWDER)
                .build();
    }

    public static void initGreenDye() {
        if (ItemTypes.GREEN_DYE != null) return;
        ItemTypes.GREEN_DYE = ItemTypeBuilder
                .builder(ItemGreenDyeStack.class)
                .vanillaItem(ItemId.GREEN_DYE)
                .build();
    }

    public static void initGreenGlazedTerracotta() {
        if (ItemTypes.GREEN_GLAZED_TERRACOTTA != null) return;
        ItemTypes.GREEN_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemGreenGlazedTerracottaStack.class)
                .vanillaItem(ItemId.GREEN_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initGreenShulkerBox() {
        if (ItemTypes.GREEN_SHULKER_BOX != null) return;
        ItemTypes.GREEN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemGreenShulkerBoxStack.class)
                .vanillaItem(ItemId.GREEN_SHULKER_BOX)
                .build();
    }

    public static void initGreenStainedGlass() {
        if (ItemTypes.GREEN_STAINED_GLASS != null) return;
        ItemTypes.GREEN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemGreenStainedGlassStack.class)
                .vanillaItem(ItemId.GREEN_STAINED_GLASS)
                .build();
    }

    public static void initGreenStainedGlassPane() {
        if (ItemTypes.GREEN_STAINED_GLASS_PANE != null) return;
        ItemTypes.GREEN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemGreenStainedGlassPaneStack.class)
                .vanillaItem(ItemId.GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGreenTerracotta() {
        if (ItemTypes.GREEN_TERRACOTTA != null) return;
        ItemTypes.GREEN_TERRACOTTA = ItemTypeBuilder
                .builder(ItemGreenTerracottaStack.class)
                .vanillaItem(ItemId.GREEN_TERRACOTTA)
                .build();
    }

    public static void initGreenWool() {
        if (ItemTypes.GREEN_WOOL != null) return;
        ItemTypes.GREEN_WOOL = ItemTypeBuilder
                .builder(ItemGreenWoolStack.class)
                .vanillaItem(ItemId.GREEN_WOOL)
                .build();
    }

    public static void initGrindstone() {
        if (ItemTypes.GRINDSTONE != null) return;
        ItemTypes.GRINDSTONE = ItemTypeBuilder
                .builder(ItemGrindstoneStack.class)
                .vanillaItem(ItemId.GRINDSTONE)
                .build();
    }

    public static void initGuardianSpawnEgg() {
        if (ItemTypes.GUARDIAN_SPAWN_EGG != null) return;
        ItemTypes.GUARDIAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGuardianSpawnEggStack.class)
                .vanillaItem(ItemId.GUARDIAN_SPAWN_EGG)
                .build();
    }

    public static void initGunpowder() {
        if (ItemTypes.GUNPOWDER != null) return;
        ItemTypes.GUNPOWDER = ItemTypeBuilder
                .builder(ItemGunpowderStack.class)
                .vanillaItem(ItemId.GUNPOWDER)
                .build();
    }

    public static void initGusterBannerPattern() {
        if (ItemTypes.GUSTER_BANNER_PATTERN != null) return;
        ItemTypes.GUSTER_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemGusterBannerPatternStack.class)
                .vanillaItem(ItemId.GUSTER_BANNER_PATTERN)
                .build();
    }

    public static void initGusterPotterySherd() {
        if (ItemTypes.GUSTER_POTTERY_SHERD != null) return;
        ItemTypes.GUSTER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemGusterPotterySherdStack.class)
                .vanillaItem(ItemId.GUSTER_POTTERY_SHERD)
                .build();
    }

    public static void initHangingRoots() {
        if (ItemTypes.HANGING_ROOTS != null) return;
        ItemTypes.HANGING_ROOTS = ItemTypeBuilder
                .builder(ItemHangingRootsStack.class)
                .vanillaItem(ItemId.HANGING_ROOTS)
                .build();
    }

    public static void initHardBlackStainedGlass() {
        if (ItemTypes.HARD_BLACK_STAINED_GLASS != null) return;
        ItemTypes.HARD_BLACK_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardBlackStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_BLACK_STAINED_GLASS)
                .build();
    }

    public static void initHardBlackStainedGlassPane() {
        if (ItemTypes.HARD_BLACK_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_BLACK_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardBlackStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBlueStainedGlass() {
        if (ItemTypes.HARD_BLUE_STAINED_GLASS != null) return;
        ItemTypes.HARD_BLUE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardBlueStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardBlueStainedGlassPane() {
        if (ItemTypes.HARD_BLUE_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_BLUE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardBlueStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBrownStainedGlass() {
        if (ItemTypes.HARD_BROWN_STAINED_GLASS != null) return;
        ItemTypes.HARD_BROWN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardBrownStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_BROWN_STAINED_GLASS)
                .build();
    }

    public static void initHardBrownStainedGlassPane() {
        if (ItemTypes.HARD_BROWN_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_BROWN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardBrownStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardCyanStainedGlass() {
        if (ItemTypes.HARD_CYAN_STAINED_GLASS != null) return;
        ItemTypes.HARD_CYAN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardCyanStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_CYAN_STAINED_GLASS)
                .build();
    }

    public static void initHardCyanStainedGlassPane() {
        if (ItemTypes.HARD_CYAN_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_CYAN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardCyanStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGlass() {
        if (ItemTypes.HARD_GLASS != null) return;
        ItemTypes.HARD_GLASS = ItemTypeBuilder
                .builder(ItemHardGlassStack.class)
                .vanillaItem(ItemId.HARD_GLASS)
                .build();
    }

    public static void initHardGlassPane() {
        if (ItemTypes.HARD_GLASS_PANE != null) return;
        ItemTypes.HARD_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_GLASS_PANE)
                .build();
    }

    public static void initHardGrayStainedGlass() {
        if (ItemTypes.HARD_GRAY_STAINED_GLASS != null) return;
        ItemTypes.HARD_GRAY_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardGrayStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardGrayStainedGlassPane() {
        if (ItemTypes.HARD_GRAY_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_GRAY_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardGrayStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGreenStainedGlass() {
        if (ItemTypes.HARD_GREEN_STAINED_GLASS != null) return;
        ItemTypes.HARD_GREEN_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardGreenStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_GREEN_STAINED_GLASS)
                .build();
    }

    public static void initHardGreenStainedGlassPane() {
        if (ItemTypes.HARD_GREEN_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_GREEN_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardGreenStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightBlueStainedGlass() {
        if (ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS != null) return;
        ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardLightBlueStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardLightBlueStainedGlassPane() {
        if (ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardLightBlueStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightGrayStainedGlass() {
        if (ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS != null) return;
        ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardLightGrayStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardLightGrayStainedGlassPane() {
        if (ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardLightGrayStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLimeStainedGlass() {
        if (ItemTypes.HARD_LIME_STAINED_GLASS != null) return;
        ItemTypes.HARD_LIME_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardLimeStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_LIME_STAINED_GLASS)
                .build();
    }

    public static void initHardLimeStainedGlassPane() {
        if (ItemTypes.HARD_LIME_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_LIME_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardLimeStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardMagentaStainedGlass() {
        if (ItemTypes.HARD_MAGENTA_STAINED_GLASS != null) return;
        ItemTypes.HARD_MAGENTA_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardMagentaStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initHardMagentaStainedGlassPane() {
        if (ItemTypes.HARD_MAGENTA_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_MAGENTA_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardMagentaStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardOrangeStainedGlass() {
        if (ItemTypes.HARD_ORANGE_STAINED_GLASS != null) return;
        ItemTypes.HARD_ORANGE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardOrangeStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initHardOrangeStainedGlassPane() {
        if (ItemTypes.HARD_ORANGE_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_ORANGE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardOrangeStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPinkStainedGlass() {
        if (ItemTypes.HARD_PINK_STAINED_GLASS != null) return;
        ItemTypes.HARD_PINK_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardPinkStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_PINK_STAINED_GLASS)
                .build();
    }

    public static void initHardPinkStainedGlassPane() {
        if (ItemTypes.HARD_PINK_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_PINK_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardPinkStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPurpleStainedGlass() {
        if (ItemTypes.HARD_PURPLE_STAINED_GLASS != null) return;
        ItemTypes.HARD_PURPLE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardPurpleStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initHardPurpleStainedGlassPane() {
        if (ItemTypes.HARD_PURPLE_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_PURPLE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardPurpleStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardRedStainedGlass() {
        if (ItemTypes.HARD_RED_STAINED_GLASS != null) return;
        ItemTypes.HARD_RED_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardRedStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_RED_STAINED_GLASS)
                .build();
    }

    public static void initHardRedStainedGlassPane() {
        if (ItemTypes.HARD_RED_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_RED_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardRedStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardStainedGlass() {
        if (ItemTypes.HARD_STAINED_GLASS != null) return;
        ItemTypes.HARD_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_STAINED_GLASS)
                .build();
    }

    public static void initHardStainedGlassPane() {
        if (ItemTypes.HARD_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardWhiteStainedGlass() {
        if (ItemTypes.HARD_WHITE_STAINED_GLASS != null) return;
        ItemTypes.HARD_WHITE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardWhiteStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_WHITE_STAINED_GLASS)
                .build();
    }

    public static void initHardWhiteStainedGlassPane() {
        if (ItemTypes.HARD_WHITE_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_WHITE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardWhiteStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardYellowStainedGlass() {
        if (ItemTypes.HARD_YELLOW_STAINED_GLASS != null) return;
        ItemTypes.HARD_YELLOW_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemHardYellowStainedGlassStack.class)
                .vanillaItem(ItemId.HARD_YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initHardYellowStainedGlassPane() {
        if (ItemTypes.HARD_YELLOW_STAINED_GLASS_PANE != null) return;
        ItemTypes.HARD_YELLOW_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemHardYellowStainedGlassPaneStack.class)
                .vanillaItem(ItemId.HARD_YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardenedClay() {
        if (ItemTypes.HARDENED_CLAY != null) return;
        ItemTypes.HARDENED_CLAY = ItemTypeBuilder
                .builder(ItemHardenedClayStack.class)
                .vanillaItem(ItemId.HARDENED_CLAY)
                .build();
    }

    public static void initHayBlock() {
        if (ItemTypes.HAY_BLOCK != null) return;
        ItemTypes.HAY_BLOCK = ItemTypeBuilder
                .builder(ItemHayBlockStack.class)
                .vanillaItem(ItemId.HAY_BLOCK)
                .build();
    }

    public static void initHeartOfTheSea() {
        if (ItemTypes.HEART_OF_THE_SEA != null) return;
        ItemTypes.HEART_OF_THE_SEA = ItemTypeBuilder
                .builder(ItemHeartOfTheSeaStack.class)
                .vanillaItem(ItemId.HEART_OF_THE_SEA)
                .build();
    }

    public static void initHeartPotterySherd() {
        if (ItemTypes.HEART_POTTERY_SHERD != null) return;
        ItemTypes.HEART_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemHeartPotterySherdStack.class)
                .vanillaItem(ItemId.HEART_POTTERY_SHERD)
                .build();
    }

    public static void initHeartbreakPotterySherd() {
        if (ItemTypes.HEARTBREAK_POTTERY_SHERD != null) return;
        ItemTypes.HEARTBREAK_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemHeartbreakPotterySherdStack.class)
                .vanillaItem(ItemId.HEARTBREAK_POTTERY_SHERD)
                .build();
    }

    public static void initHeavyCore() {
        if (ItemTypes.HEAVY_CORE != null) return;
        ItemTypes.HEAVY_CORE = ItemTypeBuilder
                .builder(ItemHeavyCoreStack.class)
                .vanillaItem(ItemId.HEAVY_CORE)
                .build();
    }

    public static void initHeavyWeightedPressurePlate() {
        if (ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE != null) return;
        ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemHeavyWeightedPressurePlateStack.class)
                .vanillaItem(ItemId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .build();
    }

    public static void initHoglinSpawnEgg() {
        if (ItemTypes.HOGLIN_SPAWN_EGG != null) return;
        ItemTypes.HOGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHoglinSpawnEggStack.class)
                .vanillaItem(ItemId.HOGLIN_SPAWN_EGG)
                .build();
    }

    public static void initHoneyBlock() {
        if (ItemTypes.HONEY_BLOCK != null) return;
        ItemTypes.HONEY_BLOCK = ItemTypeBuilder
                .builder(ItemHoneyBlockStack.class)
                .vanillaItem(ItemId.HONEY_BLOCK)
                .build();
    }

    public static void initHoneyBottle() {
        if (ItemTypes.HONEY_BOTTLE != null) return;
        ItemTypes.HONEY_BOTTLE = ItemTypeBuilder
                .builder(ItemHoneyBottleStack.class)
                .vanillaItem(ItemId.HONEY_BOTTLE)
                .build();
    }

    public static void initHoneycomb() {
        if (ItemTypes.HONEYCOMB != null) return;
        ItemTypes.HONEYCOMB = ItemTypeBuilder
                .builder(ItemHoneycombStack.class)
                .vanillaItem(ItemId.HONEYCOMB)
                .build();
    }

    public static void initHoneycombBlock() {
        if (ItemTypes.HONEYCOMB_BLOCK != null) return;
        ItemTypes.HONEYCOMB_BLOCK = ItemTypeBuilder
                .builder(ItemHoneycombBlockStack.class)
                .vanillaItem(ItemId.HONEYCOMB_BLOCK)
                .build();
    }

    public static void initHopper() {
        if (ItemTypes.HOPPER != null) return;
        ItemTypes.HOPPER = ItemTypeBuilder
                .builder(ItemHopperStack.class)
                .vanillaItem(ItemId.HOPPER)
                .build();
    }

    public static void initHopperMinecart() {
        if (ItemTypes.HOPPER_MINECART != null) return;
        ItemTypes.HOPPER_MINECART = ItemTypeBuilder
                .builder(ItemHopperMinecartStack.class)
                .vanillaItem(ItemId.HOPPER_MINECART)
                .build();
    }

    public static void initHornCoral() {
        if (ItemTypes.HORN_CORAL != null) return;
        ItemTypes.HORN_CORAL = ItemTypeBuilder
                .builder(ItemHornCoralStack.class)
                .vanillaItem(ItemId.HORN_CORAL)
                .build();
    }

    public static void initHornCoralBlock() {
        if (ItemTypes.HORN_CORAL_BLOCK != null) return;
        ItemTypes.HORN_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemHornCoralBlockStack.class)
                .vanillaItem(ItemId.HORN_CORAL_BLOCK)
                .build();
    }

    public static void initHornCoralFan() {
        if (ItemTypes.HORN_CORAL_FAN != null) return;
        ItemTypes.HORN_CORAL_FAN = ItemTypeBuilder
                .builder(ItemHornCoralFanStack.class)
                .vanillaItem(ItemId.HORN_CORAL_FAN)
                .build();
    }

    public static void initHornCoralWallFan() {
        if (ItemTypes.HORN_CORAL_WALL_FAN != null) return;
        ItemTypes.HORN_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemHornCoralWallFanStack.class)
                .vanillaItem(ItemId.HORN_CORAL_WALL_FAN)
                .build();
    }

    public static void initHorseSpawnEgg() {
        if (ItemTypes.HORSE_SPAWN_EGG != null) return;
        ItemTypes.HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHorseSpawnEggStack.class)
                .vanillaItem(ItemId.HORSE_SPAWN_EGG)
                .build();
    }

    public static void initHostArmorTrimSmithingTemplate() {
        if (ItemTypes.HOST_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.HOST_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemHostArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initHowlPotterySherd() {
        if (ItemTypes.HOWL_POTTERY_SHERD != null) return;
        ItemTypes.HOWL_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemHowlPotterySherdStack.class)
                .vanillaItem(ItemId.HOWL_POTTERY_SHERD)
                .build();
    }

    public static void initHuskSpawnEgg() {
        if (ItemTypes.HUSK_SPAWN_EGG != null) return;
        ItemTypes.HUSK_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHuskSpawnEggStack.class)
                .vanillaItem(ItemId.HUSK_SPAWN_EGG)
                .build();
    }

    public static void initIce() {
        if (ItemTypes.ICE != null) return;
        ItemTypes.ICE = ItemTypeBuilder
                .builder(ItemIceStack.class)
                .vanillaItem(ItemId.ICE)
                .build();
    }

    public static void initIceBomb() {
        if (ItemTypes.ICE_BOMB != null) return;
        ItemTypes.ICE_BOMB = ItemTypeBuilder
                .builder(ItemIceBombStack.class)
                .vanillaItem(ItemId.ICE_BOMB)
                .build();
    }

    public static void initInfestedChiseledStoneBricks() {
        if (ItemTypes.INFESTED_CHISELED_STONE_BRICKS != null) return;
        ItemTypes.INFESTED_CHISELED_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemInfestedChiseledStoneBricksStack.class)
                .vanillaItem(ItemId.INFESTED_CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedCobblestone() {
        if (ItemTypes.INFESTED_COBBLESTONE != null) return;
        ItemTypes.INFESTED_COBBLESTONE = ItemTypeBuilder
                .builder(ItemInfestedCobblestoneStack.class)
                .vanillaItem(ItemId.INFESTED_COBBLESTONE)
                .build();
    }

    public static void initInfestedCrackedStoneBricks() {
        if (ItemTypes.INFESTED_CRACKED_STONE_BRICKS != null) return;
        ItemTypes.INFESTED_CRACKED_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemInfestedCrackedStoneBricksStack.class)
                .vanillaItem(ItemId.INFESTED_CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedDeepslate() {
        if (ItemTypes.INFESTED_DEEPSLATE != null) return;
        ItemTypes.INFESTED_DEEPSLATE = ItemTypeBuilder
                .builder(ItemInfestedDeepslateStack.class)
                .vanillaItem(ItemId.INFESTED_DEEPSLATE)
                .build();
    }

    public static void initInfestedMossyStoneBricks() {
        if (ItemTypes.INFESTED_MOSSY_STONE_BRICKS != null) return;
        ItemTypes.INFESTED_MOSSY_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemInfestedMossyStoneBricksStack.class)
                .vanillaItem(ItemId.INFESTED_MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initInfestedStone() {
        if (ItemTypes.INFESTED_STONE != null) return;
        ItemTypes.INFESTED_STONE = ItemTypeBuilder
                .builder(ItemInfestedStoneStack.class)
                .vanillaItem(ItemId.INFESTED_STONE)
                .build();
    }

    public static void initInfestedStoneBricks() {
        if (ItemTypes.INFESTED_STONE_BRICKS != null) return;
        ItemTypes.INFESTED_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemInfestedStoneBricksStack.class)
                .vanillaItem(ItemId.INFESTED_STONE_BRICKS)
                .build();
    }

    public static void initInfoUpdate() {
        if (ItemTypes.INFO_UPDATE != null) return;
        ItemTypes.INFO_UPDATE = ItemTypeBuilder
                .builder(ItemInfoUpdateStack.class)
                .vanillaItem(ItemId.INFO_UPDATE)
                .build();
    }

    public static void initInfoUpdate2() {
        if (ItemTypes.INFO_UPDATE2 != null) return;
        ItemTypes.INFO_UPDATE2 = ItemTypeBuilder
                .builder(ItemInfoUpdate2Stack.class)
                .vanillaItem(ItemId.INFO_UPDATE2)
                .build();
    }

    public static void initInkSac() {
        if (ItemTypes.INK_SAC != null) return;
        ItemTypes.INK_SAC = ItemTypeBuilder
                .builder(ItemInkSacStack.class)
                .vanillaItem(ItemId.INK_SAC)
                .build();
    }

    public static void initInvisibleBedrock() {
        if (ItemTypes.INVISIBLE_BEDROCK != null) return;
        ItemTypes.INVISIBLE_BEDROCK = ItemTypeBuilder
                .builder(ItemInvisibleBedrockStack.class)
                .vanillaItem(ItemId.INVISIBLE_BEDROCK)
                .build();
    }

    public static void initIronAxe() {
        if (ItemTypes.IRON_AXE != null) return;
        ItemTypes.IRON_AXE = ItemTypeBuilder
                .builder(ItemIronAxeStack.class)
                .vanillaItem(ItemId.IRON_AXE)
                .build();
    }

    public static void initIronBars() {
        if (ItemTypes.IRON_BARS != null) return;
        ItemTypes.IRON_BARS = ItemTypeBuilder
                .builder(ItemIronBarsStack.class)
                .vanillaItem(ItemId.IRON_BARS)
                .build();
    }

    public static void initIronBlock() {
        if (ItemTypes.IRON_BLOCK != null) return;
        ItemTypes.IRON_BLOCK = ItemTypeBuilder
                .builder(ItemIronBlockStack.class)
                .vanillaItem(ItemId.IRON_BLOCK)
                .build();
    }

    public static void initIronBoots() {
        if (ItemTypes.IRON_BOOTS != null) return;
        ItemTypes.IRON_BOOTS = ItemTypeBuilder
                .builder(ItemIronBootsStack.class)
                .vanillaItem(ItemId.IRON_BOOTS)
                .build();
    }

    public static void initIronChestplate() {
        if (ItemTypes.IRON_CHESTPLATE != null) return;
        ItemTypes.IRON_CHESTPLATE = ItemTypeBuilder
                .builder(ItemIronChestplateStack.class)
                .vanillaItem(ItemId.IRON_CHESTPLATE)
                .build();
    }

    public static void initIronDoor() {
        if (ItemTypes.IRON_DOOR != null) return;
        ItemTypes.IRON_DOOR = ItemTypeBuilder
                .builder(ItemIronDoorStack.class)
                .vanillaItem(ItemId.IRON_DOOR)
                .build();
    }

    public static void initIronGolemSpawnEgg() {
        if (ItemTypes.IRON_GOLEM_SPAWN_EGG != null) return;
        ItemTypes.IRON_GOLEM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemIronGolemSpawnEggStack.class)
                .vanillaItem(ItemId.IRON_GOLEM_SPAWN_EGG)
                .build();
    }

    public static void initIronHelmet() {
        if (ItemTypes.IRON_HELMET != null) return;
        ItemTypes.IRON_HELMET = ItemTypeBuilder
                .builder(ItemIronHelmetStack.class)
                .vanillaItem(ItemId.IRON_HELMET)
                .build();
    }

    public static void initIronHoe() {
        if (ItemTypes.IRON_HOE != null) return;
        ItemTypes.IRON_HOE = ItemTypeBuilder
                .builder(ItemIronHoeStack.class)
                .vanillaItem(ItemId.IRON_HOE)
                .build();
    }

    public static void initIronHorseArmor() {
        if (ItemTypes.IRON_HORSE_ARMOR != null) return;
        ItemTypes.IRON_HORSE_ARMOR = ItemTypeBuilder
                .builder(ItemIronHorseArmorStack.class)
                .vanillaItem(ItemId.IRON_HORSE_ARMOR)
                .build();
    }

    public static void initIronIngot() {
        if (ItemTypes.IRON_INGOT != null) return;
        ItemTypes.IRON_INGOT = ItemTypeBuilder
                .builder(ItemIronIngotStack.class)
                .vanillaItem(ItemId.IRON_INGOT)
                .build();
    }

    public static void initIronLeggings() {
        if (ItemTypes.IRON_LEGGINGS != null) return;
        ItemTypes.IRON_LEGGINGS = ItemTypeBuilder
                .builder(ItemIronLeggingsStack.class)
                .vanillaItem(ItemId.IRON_LEGGINGS)
                .build();
    }

    public static void initIronNugget() {
        if (ItemTypes.IRON_NUGGET != null) return;
        ItemTypes.IRON_NUGGET = ItemTypeBuilder
                .builder(ItemIronNuggetStack.class)
                .vanillaItem(ItemId.IRON_NUGGET)
                .build();
    }

    public static void initIronOre() {
        if (ItemTypes.IRON_ORE != null) return;
        ItemTypes.IRON_ORE = ItemTypeBuilder
                .builder(ItemIronOreStack.class)
                .vanillaItem(ItemId.IRON_ORE)
                .build();
    }

    public static void initIronPickaxe() {
        if (ItemTypes.IRON_PICKAXE != null) return;
        ItemTypes.IRON_PICKAXE = ItemTypeBuilder
                .builder(ItemIronPickaxeStack.class)
                .vanillaItem(ItemId.IRON_PICKAXE)
                .build();
    }

    public static void initIronShovel() {
        if (ItemTypes.IRON_SHOVEL != null) return;
        ItemTypes.IRON_SHOVEL = ItemTypeBuilder
                .builder(ItemIronShovelStack.class)
                .vanillaItem(ItemId.IRON_SHOVEL)
                .build();
    }

    public static void initIronSword() {
        if (ItemTypes.IRON_SWORD != null) return;
        ItemTypes.IRON_SWORD = ItemTypeBuilder
                .builder(ItemIronSwordStack.class)
                .vanillaItem(ItemId.IRON_SWORD)
                .build();
    }

    public static void initIronTrapdoor() {
        if (ItemTypes.IRON_TRAPDOOR != null) return;
        ItemTypes.IRON_TRAPDOOR = ItemTypeBuilder
                .builder(ItemIronTrapdoorStack.class)
                .vanillaItem(ItemId.IRON_TRAPDOOR)
                .build();
    }

    public static void initItemAcaciaDoor() {
        if (ItemTypes.ITEM_ACACIA_DOOR != null) return;
        ItemTypes.ITEM_ACACIA_DOOR = ItemTypeBuilder
                .builder(ItemItemAcaciaDoorStack.class)
                .vanillaItem(ItemId.ITEM_ACACIA_DOOR)
                .build();
    }

    public static void initItemBed() {
        if (ItemTypes.ITEM_BED != null) return;
        ItemTypes.ITEM_BED = ItemTypeBuilder
                .builder(ItemItemBedStack.class)
                .vanillaItem(ItemId.ITEM_BED)
                .build();
    }

    public static void initItemBeetroot() {
        if (ItemTypes.ITEM_BEETROOT != null) return;
        ItemTypes.ITEM_BEETROOT = ItemTypeBuilder
                .builder(ItemItemBeetrootStack.class)
                .vanillaItem(ItemId.ITEM_BEETROOT)
                .build();
    }

    public static void initItemBirchDoor() {
        if (ItemTypes.ITEM_BIRCH_DOOR != null) return;
        ItemTypes.ITEM_BIRCH_DOOR = ItemTypeBuilder
                .builder(ItemItemBirchDoorStack.class)
                .vanillaItem(ItemId.ITEM_BIRCH_DOOR)
                .build();
    }

    public static void initItemBrewingStand() {
        if (ItemTypes.ITEM_BREWING_STAND != null) return;
        ItemTypes.ITEM_BREWING_STAND = ItemTypeBuilder
                .builder(ItemItemBrewingStandStack.class)
                .vanillaItem(ItemId.ITEM_BREWING_STAND)
                .build();
    }

    public static void initItemCake() {
        if (ItemTypes.ITEM_CAKE != null) return;
        ItemTypes.ITEM_CAKE = ItemTypeBuilder
                .builder(ItemItemCakeStack.class)
                .vanillaItem(ItemId.ITEM_CAKE)
                .build();
    }

    public static void initItemCamera() {
        if (ItemTypes.ITEM_CAMERA != null) return;
        ItemTypes.ITEM_CAMERA = ItemTypeBuilder
                .builder(ItemItemCameraStack.class)
                .vanillaItem(ItemId.ITEM_CAMERA)
                .build();
    }

    public static void initItemCampfire() {
        if (ItemTypes.ITEM_CAMPFIRE != null) return;
        ItemTypes.ITEM_CAMPFIRE = ItemTypeBuilder
                .builder(ItemItemCampfireStack.class)
                .vanillaItem(ItemId.ITEM_CAMPFIRE)
                .build();
    }

    public static void initItemCauldron() {
        if (ItemTypes.ITEM_CAULDRON != null) return;
        ItemTypes.ITEM_CAULDRON = ItemTypeBuilder
                .builder(ItemItemCauldronStack.class)
                .vanillaItem(ItemId.ITEM_CAULDRON)
                .build();
    }

    public static void initItemChain() {
        if (ItemTypes.ITEM_CHAIN != null) return;
        ItemTypes.ITEM_CHAIN = ItemTypeBuilder
                .builder(ItemItemChainStack.class)
                .vanillaItem(ItemId.ITEM_CHAIN)
                .build();
    }

    public static void initItemCrimsonDoor() {
        if (ItemTypes.ITEM_CRIMSON_DOOR != null) return;
        ItemTypes.ITEM_CRIMSON_DOOR = ItemTypeBuilder
                .builder(ItemItemCrimsonDoorStack.class)
                .vanillaItem(ItemId.ITEM_CRIMSON_DOOR)
                .build();
    }

    public static void initItemDarkOakDoor() {
        if (ItemTypes.ITEM_DARK_OAK_DOOR != null) return;
        ItemTypes.ITEM_DARK_OAK_DOOR = ItemTypeBuilder
                .builder(ItemItemDarkOakDoorStack.class)
                .vanillaItem(ItemId.ITEM_DARK_OAK_DOOR)
                .build();
    }

    public static void initItemFlowerPot() {
        if (ItemTypes.ITEM_FLOWER_POT != null) return;
        ItemTypes.ITEM_FLOWER_POT = ItemTypeBuilder
                .builder(ItemItemFlowerPotStack.class)
                .vanillaItem(ItemId.ITEM_FLOWER_POT)
                .build();
    }

    public static void initItemFrame() {
        if (ItemTypes.ITEM_FRAME != null) return;
        ItemTypes.ITEM_FRAME = ItemTypeBuilder
                .builder(ItemItemFrameStack.class)
                .vanillaItem(ItemId.ITEM_FRAME)
                .build();
    }

    public static void initItemGlowFrame() {
        if (ItemTypes.ITEM_GLOW_FRAME != null) return;
        ItemTypes.ITEM_GLOW_FRAME = ItemTypeBuilder
                .builder(ItemItemGlowFrameStack.class)
                .vanillaItem(ItemId.ITEM_GLOW_FRAME)
                .build();
    }

    public static void initItemHopper() {
        if (ItemTypes.ITEM_HOPPER != null) return;
        ItemTypes.ITEM_HOPPER = ItemTypeBuilder
                .builder(ItemItemHopperStack.class)
                .vanillaItem(ItemId.ITEM_HOPPER)
                .build();
    }

    public static void initItemIronDoor() {
        if (ItemTypes.ITEM_IRON_DOOR != null) return;
        ItemTypes.ITEM_IRON_DOOR = ItemTypeBuilder
                .builder(ItemItemIronDoorStack.class)
                .vanillaItem(ItemId.ITEM_IRON_DOOR)
                .build();
    }

    public static void initItemJungleDoor() {
        if (ItemTypes.ITEM_JUNGLE_DOOR != null) return;
        ItemTypes.ITEM_JUNGLE_DOOR = ItemTypeBuilder
                .builder(ItemItemJungleDoorStack.class)
                .vanillaItem(ItemId.ITEM_JUNGLE_DOOR)
                .build();
    }

    public static void initItemKelp() {
        if (ItemTypes.ITEM_KELP != null) return;
        ItemTypes.ITEM_KELP = ItemTypeBuilder
                .builder(ItemItemKelpStack.class)
                .vanillaItem(ItemId.ITEM_KELP)
                .build();
    }

    public static void initItemMangroveDoor() {
        if (ItemTypes.ITEM_MANGROVE_DOOR != null) return;
        ItemTypes.ITEM_MANGROVE_DOOR = ItemTypeBuilder
                .builder(ItemItemMangroveDoorStack.class)
                .vanillaItem(ItemId.ITEM_MANGROVE_DOOR)
                .build();
    }

    public static void initItemNetherSprouts() {
        if (ItemTypes.ITEM_NETHER_SPROUTS != null) return;
        ItemTypes.ITEM_NETHER_SPROUTS = ItemTypeBuilder
                .builder(ItemItemNetherSproutsStack.class)
                .vanillaItem(ItemId.ITEM_NETHER_SPROUTS)
                .build();
    }

    public static void initItemNetherWart() {
        if (ItemTypes.ITEM_NETHER_WART != null) return;
        ItemTypes.ITEM_NETHER_WART = ItemTypeBuilder
                .builder(ItemItemNetherWartStack.class)
                .vanillaItem(ItemId.ITEM_NETHER_WART)
                .build();
    }

    public static void initItemReeds() {
        if (ItemTypes.ITEM_REEDS != null) return;
        ItemTypes.ITEM_REEDS = ItemTypeBuilder
                .builder(ItemItemReedsStack.class)
                .vanillaItem(ItemId.ITEM_REEDS)
                .build();
    }

    public static void initItemSkull() {
        if (ItemTypes.ITEM_SKULL != null) return;
        ItemTypes.ITEM_SKULL = ItemTypeBuilder
                .builder(ItemItemSkullStack.class)
                .vanillaItem(ItemId.ITEM_SKULL)
                .build();
    }

    public static void initItemSoulCampfire() {
        if (ItemTypes.ITEM_SOUL_CAMPFIRE != null) return;
        ItemTypes.ITEM_SOUL_CAMPFIRE = ItemTypeBuilder
                .builder(ItemItemSoulCampfireStack.class)
                .vanillaItem(ItemId.ITEM_SOUL_CAMPFIRE)
                .build();
    }

    public static void initItemSpruceDoor() {
        if (ItemTypes.ITEM_SPRUCE_DOOR != null) return;
        ItemTypes.ITEM_SPRUCE_DOOR = ItemTypeBuilder
                .builder(ItemItemSpruceDoorStack.class)
                .vanillaItem(ItemId.ITEM_SPRUCE_DOOR)
                .build();
    }

    public static void initItemWarpedDoor() {
        if (ItemTypes.ITEM_WARPED_DOOR != null) return;
        ItemTypes.ITEM_WARPED_DOOR = ItemTypeBuilder
                .builder(ItemItemWarpedDoorStack.class)
                .vanillaItem(ItemId.ITEM_WARPED_DOOR)
                .build();
    }

    public static void initItemWheat() {
        if (ItemTypes.ITEM_WHEAT != null) return;
        ItemTypes.ITEM_WHEAT = ItemTypeBuilder
                .builder(ItemItemWheatStack.class)
                .vanillaItem(ItemId.ITEM_WHEAT)
                .build();
    }

    public static void initItemWoodenDoor() {
        if (ItemTypes.ITEM_WOODEN_DOOR != null) return;
        ItemTypes.ITEM_WOODEN_DOOR = ItemTypeBuilder
                .builder(ItemItemWoodenDoorStack.class)
                .vanillaItem(ItemId.ITEM_WOODEN_DOOR)
                .build();
    }

    public static void initJigsaw() {
        if (ItemTypes.JIGSAW != null) return;
        ItemTypes.JIGSAW = ItemTypeBuilder
                .builder(ItemJigsawStack.class)
                .vanillaItem(ItemId.JIGSAW)
                .build();
    }

    public static void initJukebox() {
        if (ItemTypes.JUKEBOX != null) return;
        ItemTypes.JUKEBOX = ItemTypeBuilder
                .builder(ItemJukeboxStack.class)
                .vanillaItem(ItemId.JUKEBOX)
                .build();
    }

    public static void initJungleBoat() {
        if (ItemTypes.JUNGLE_BOAT != null) return;
        ItemTypes.JUNGLE_BOAT = ItemTypeBuilder
                .builder(ItemJungleBoatStack.class)
                .vanillaItem(ItemId.JUNGLE_BOAT)
                .build();
    }

    public static void initJungleButton() {
        if (ItemTypes.JUNGLE_BUTTON != null) return;
        ItemTypes.JUNGLE_BUTTON = ItemTypeBuilder
                .builder(ItemJungleButtonStack.class)
                .vanillaItem(ItemId.JUNGLE_BUTTON)
                .build();
    }

    public static void initJungleChestBoat() {
        if (ItemTypes.JUNGLE_CHEST_BOAT != null) return;
        ItemTypes.JUNGLE_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemJungleChestBoatStack.class)
                .vanillaItem(ItemId.JUNGLE_CHEST_BOAT)
                .build();
    }

    public static void initJungleDoor() {
        if (ItemTypes.JUNGLE_DOOR != null) return;
        ItemTypes.JUNGLE_DOOR = ItemTypeBuilder
                .builder(ItemJungleDoorStack.class)
                .vanillaItem(ItemId.JUNGLE_DOOR)
                .build();
    }

    public static void initJungleDoubleSlab() {
        if (ItemTypes.JUNGLE_DOUBLE_SLAB != null) return;
        ItemTypes.JUNGLE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemJungleDoubleSlabStack.class)
                .vanillaItem(ItemId.JUNGLE_DOUBLE_SLAB)
                .build();
    }

    public static void initJungleFence() {
        if (ItemTypes.JUNGLE_FENCE != null) return;
        ItemTypes.JUNGLE_FENCE = ItemTypeBuilder
                .builder(ItemJungleFenceStack.class)
                .vanillaItem(ItemId.JUNGLE_FENCE)
                .build();
    }

    public static void initJungleFenceGate() {
        if (ItemTypes.JUNGLE_FENCE_GATE != null) return;
        ItemTypes.JUNGLE_FENCE_GATE = ItemTypeBuilder
                .builder(ItemJungleFenceGateStack.class)
                .vanillaItem(ItemId.JUNGLE_FENCE_GATE)
                .build();
    }

    public static void initJungleHangingSign() {
        if (ItemTypes.JUNGLE_HANGING_SIGN != null) return;
        ItemTypes.JUNGLE_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemJungleHangingSignStack.class)
                .vanillaItem(ItemId.JUNGLE_HANGING_SIGN)
                .build();
    }

    public static void initJungleLeaves() {
        if (ItemTypes.JUNGLE_LEAVES != null) return;
        ItemTypes.JUNGLE_LEAVES = ItemTypeBuilder
                .builder(ItemJungleLeavesStack.class)
                .vanillaItem(ItemId.JUNGLE_LEAVES)
                .build();
    }

    public static void initJungleLog() {
        if (ItemTypes.JUNGLE_LOG != null) return;
        ItemTypes.JUNGLE_LOG = ItemTypeBuilder
                .builder(ItemJungleLogStack.class)
                .vanillaItem(ItemId.JUNGLE_LOG)
                .build();
    }

    public static void initJunglePlanks() {
        if (ItemTypes.JUNGLE_PLANKS != null) return;
        ItemTypes.JUNGLE_PLANKS = ItemTypeBuilder
                .builder(ItemJunglePlanksStack.class)
                .vanillaItem(ItemId.JUNGLE_PLANKS)
                .build();
    }

    public static void initJunglePressurePlate() {
        if (ItemTypes.JUNGLE_PRESSURE_PLATE != null) return;
        ItemTypes.JUNGLE_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemJunglePressurePlateStack.class)
                .vanillaItem(ItemId.JUNGLE_PRESSURE_PLATE)
                .build();
    }

    public static void initJungleSapling() {
        if (ItemTypes.JUNGLE_SAPLING != null) return;
        ItemTypes.JUNGLE_SAPLING = ItemTypeBuilder
                .builder(ItemJungleSaplingStack.class)
                .vanillaItem(ItemId.JUNGLE_SAPLING)
                .build();
    }

    public static void initJungleSign() {
        if (ItemTypes.JUNGLE_SIGN != null) return;
        ItemTypes.JUNGLE_SIGN = ItemTypeBuilder
                .builder(ItemJungleSignStack.class)
                .vanillaItem(ItemId.JUNGLE_SIGN)
                .build();
    }

    public static void initJungleSlab() {
        if (ItemTypes.JUNGLE_SLAB != null) return;
        ItemTypes.JUNGLE_SLAB = ItemTypeBuilder
                .builder(ItemJungleSlabStack.class)
                .vanillaItem(ItemId.JUNGLE_SLAB)
                .build();
    }

    public static void initJungleStairs() {
        if (ItemTypes.JUNGLE_STAIRS != null) return;
        ItemTypes.JUNGLE_STAIRS = ItemTypeBuilder
                .builder(ItemJungleStairsStack.class)
                .vanillaItem(ItemId.JUNGLE_STAIRS)
                .build();
    }

    public static void initJungleStandingSign() {
        if (ItemTypes.JUNGLE_STANDING_SIGN != null) return;
        ItemTypes.JUNGLE_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemJungleStandingSignStack.class)
                .vanillaItem(ItemId.JUNGLE_STANDING_SIGN)
                .build();
    }

    public static void initJungleTrapdoor() {
        if (ItemTypes.JUNGLE_TRAPDOOR != null) return;
        ItemTypes.JUNGLE_TRAPDOOR = ItemTypeBuilder
                .builder(ItemJungleTrapdoorStack.class)
                .vanillaItem(ItemId.JUNGLE_TRAPDOOR)
                .build();
    }

    public static void initJungleWallSign() {
        if (ItemTypes.JUNGLE_WALL_SIGN != null) return;
        ItemTypes.JUNGLE_WALL_SIGN = ItemTypeBuilder
                .builder(ItemJungleWallSignStack.class)
                .vanillaItem(ItemId.JUNGLE_WALL_SIGN)
                .build();
    }

    public static void initJungleWood() {
        if (ItemTypes.JUNGLE_WOOD != null) return;
        ItemTypes.JUNGLE_WOOD = ItemTypeBuilder
                .builder(ItemJungleWoodStack.class)
                .vanillaItem(ItemId.JUNGLE_WOOD)
                .build();
    }

    public static void initKelp() {
        if (ItemTypes.KELP != null) return;
        ItemTypes.KELP = ItemTypeBuilder
                .builder(ItemKelpStack.class)
                .vanillaItem(ItemId.KELP)
                .build();
    }

    public static void initLadder() {
        if (ItemTypes.LADDER != null) return;
        ItemTypes.LADDER = ItemTypeBuilder
                .builder(ItemLadderStack.class)
                .vanillaItem(ItemId.LADDER)
                .build();
    }

    public static void initLantern() {
        if (ItemTypes.LANTERN != null) return;
        ItemTypes.LANTERN = ItemTypeBuilder
                .builder(ItemLanternStack.class)
                .vanillaItem(ItemId.LANTERN)
                .build();
    }

    public static void initLapisBlock() {
        if (ItemTypes.LAPIS_BLOCK != null) return;
        ItemTypes.LAPIS_BLOCK = ItemTypeBuilder
                .builder(ItemLapisBlockStack.class)
                .vanillaItem(ItemId.LAPIS_BLOCK)
                .build();
    }

    public static void initLapisLazuli() {
        if (ItemTypes.LAPIS_LAZULI != null) return;
        ItemTypes.LAPIS_LAZULI = ItemTypeBuilder
                .builder(ItemLapisLazuliStack.class)
                .vanillaItem(ItemId.LAPIS_LAZULI)
                .build();
    }

    public static void initLapisOre() {
        if (ItemTypes.LAPIS_ORE != null) return;
        ItemTypes.LAPIS_ORE = ItemTypeBuilder
                .builder(ItemLapisOreStack.class)
                .vanillaItem(ItemId.LAPIS_ORE)
                .build();
    }

    public static void initLargeAmethystBud() {
        if (ItemTypes.LARGE_AMETHYST_BUD != null) return;
        ItemTypes.LARGE_AMETHYST_BUD = ItemTypeBuilder
                .builder(ItemLargeAmethystBudStack.class)
                .vanillaItem(ItemId.LARGE_AMETHYST_BUD)
                .build();
    }

    public static void initLargeFern() {
        if (ItemTypes.LARGE_FERN != null) return;
        ItemTypes.LARGE_FERN = ItemTypeBuilder
                .builder(ItemLargeFernStack.class)
                .vanillaItem(ItemId.LARGE_FERN)
                .build();
    }

    public static void initLava() {
        if (ItemTypes.LAVA != null) return;
        ItemTypes.LAVA = ItemTypeBuilder
                .builder(ItemLavaStack.class)
                .vanillaItem(ItemId.LAVA)
                .build();
    }

    public static void initLavaBucket() {
        if (ItemTypes.LAVA_BUCKET != null) return;
        ItemTypes.LAVA_BUCKET = ItemTypeBuilder
                .builder(ItemLavaBucketStack.class)
                .vanillaItem(ItemId.LAVA_BUCKET)
                .build();
    }

    public static void initLead() {
        if (ItemTypes.LEAD != null) return;
        ItemTypes.LEAD = ItemTypeBuilder
                .builder(ItemLeadStack.class)
                .vanillaItem(ItemId.LEAD)
                .build();
    }

    public static void initLeather() {
        if (ItemTypes.LEATHER != null) return;
        ItemTypes.LEATHER = ItemTypeBuilder
                .builder(ItemLeatherStack.class)
                .vanillaItem(ItemId.LEATHER)
                .build();
    }

    public static void initLeatherBoots() {
        if (ItemTypes.LEATHER_BOOTS != null) return;
        ItemTypes.LEATHER_BOOTS = ItemTypeBuilder
                .builder(ItemLeatherBootsStack.class)
                .vanillaItem(ItemId.LEATHER_BOOTS)
                .build();
    }

    public static void initLeatherChestplate() {
        if (ItemTypes.LEATHER_CHESTPLATE != null) return;
        ItemTypes.LEATHER_CHESTPLATE = ItemTypeBuilder
                .builder(ItemLeatherChestplateStack.class)
                .vanillaItem(ItemId.LEATHER_CHESTPLATE)
                .build();
    }

    public static void initLeatherHelmet() {
        if (ItemTypes.LEATHER_HELMET != null) return;
        ItemTypes.LEATHER_HELMET = ItemTypeBuilder
                .builder(ItemLeatherHelmetStack.class)
                .vanillaItem(ItemId.LEATHER_HELMET)
                .build();
    }

    public static void initLeatherHorseArmor() {
        if (ItemTypes.LEATHER_HORSE_ARMOR != null) return;
        ItemTypes.LEATHER_HORSE_ARMOR = ItemTypeBuilder
                .builder(ItemLeatherHorseArmorStack.class)
                .vanillaItem(ItemId.LEATHER_HORSE_ARMOR)
                .build();
    }

    public static void initLeatherLeggings() {
        if (ItemTypes.LEATHER_LEGGINGS != null) return;
        ItemTypes.LEATHER_LEGGINGS = ItemTypeBuilder
                .builder(ItemLeatherLeggingsStack.class)
                .vanillaItem(ItemId.LEATHER_LEGGINGS)
                .build();
    }

    public static void initLeaves() {
        if (ItemTypes.LEAVES != null) return;
        ItemTypes.LEAVES = ItemTypeBuilder
                .builder(ItemLeavesStack.class)
                .vanillaItem(ItemId.LEAVES)
                .build();
    }

    public static void initLeaves2() {
        if (ItemTypes.LEAVES2 != null) return;
        ItemTypes.LEAVES2 = ItemTypeBuilder
                .builder(ItemLeaves2Stack.class)
                .vanillaItem(ItemId.LEAVES2)
                .build();
    }

    public static void initLectern() {
        if (ItemTypes.LECTERN != null) return;
        ItemTypes.LECTERN = ItemTypeBuilder
                .builder(ItemLecternStack.class)
                .vanillaItem(ItemId.LECTERN)
                .build();
    }

    public static void initLever() {
        if (ItemTypes.LEVER != null) return;
        ItemTypes.LEVER = ItemTypeBuilder
                .builder(ItemLeverStack.class)
                .vanillaItem(ItemId.LEVER)
                .build();
    }

    public static void initLightBlock() {
        if (ItemTypes.LIGHT_BLOCK != null) return;
        ItemTypes.LIGHT_BLOCK = ItemTypeBuilder
                .builder(ItemLightBlockStack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK)
                .build();
    }

    public static void initLightBlock0() {
        if (ItemTypes.LIGHT_BLOCK_0 != null) return;
        ItemTypes.LIGHT_BLOCK_0 = ItemTypeBuilder
                .builder(ItemLightBlock0Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_0)
                .build();
    }

    public static void initLightBlock1() {
        if (ItemTypes.LIGHT_BLOCK_1 != null) return;
        ItemTypes.LIGHT_BLOCK_1 = ItemTypeBuilder
                .builder(ItemLightBlock1Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_1)
                .build();
    }

    public static void initLightBlock10() {
        if (ItemTypes.LIGHT_BLOCK_10 != null) return;
        ItemTypes.LIGHT_BLOCK_10 = ItemTypeBuilder
                .builder(ItemLightBlock10Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_10)
                .build();
    }

    public static void initLightBlock11() {
        if (ItemTypes.LIGHT_BLOCK_11 != null) return;
        ItemTypes.LIGHT_BLOCK_11 = ItemTypeBuilder
                .builder(ItemLightBlock11Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_11)
                .build();
    }

    public static void initLightBlock12() {
        if (ItemTypes.LIGHT_BLOCK_12 != null) return;
        ItemTypes.LIGHT_BLOCK_12 = ItemTypeBuilder
                .builder(ItemLightBlock12Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_12)
                .build();
    }

    public static void initLightBlock13() {
        if (ItemTypes.LIGHT_BLOCK_13 != null) return;
        ItemTypes.LIGHT_BLOCK_13 = ItemTypeBuilder
                .builder(ItemLightBlock13Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_13)
                .build();
    }

    public static void initLightBlock14() {
        if (ItemTypes.LIGHT_BLOCK_14 != null) return;
        ItemTypes.LIGHT_BLOCK_14 = ItemTypeBuilder
                .builder(ItemLightBlock14Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_14)
                .build();
    }

    public static void initLightBlock15() {
        if (ItemTypes.LIGHT_BLOCK_15 != null) return;
        ItemTypes.LIGHT_BLOCK_15 = ItemTypeBuilder
                .builder(ItemLightBlock15Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_15)
                .build();
    }

    public static void initLightBlock2() {
        if (ItemTypes.LIGHT_BLOCK_2 != null) return;
        ItemTypes.LIGHT_BLOCK_2 = ItemTypeBuilder
                .builder(ItemLightBlock2Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_2)
                .build();
    }

    public static void initLightBlock3() {
        if (ItemTypes.LIGHT_BLOCK_3 != null) return;
        ItemTypes.LIGHT_BLOCK_3 = ItemTypeBuilder
                .builder(ItemLightBlock3Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_3)
                .build();
    }

    public static void initLightBlock4() {
        if (ItemTypes.LIGHT_BLOCK_4 != null) return;
        ItemTypes.LIGHT_BLOCK_4 = ItemTypeBuilder
                .builder(ItemLightBlock4Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_4)
                .build();
    }

    public static void initLightBlock5() {
        if (ItemTypes.LIGHT_BLOCK_5 != null) return;
        ItemTypes.LIGHT_BLOCK_5 = ItemTypeBuilder
                .builder(ItemLightBlock5Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_5)
                .build();
    }

    public static void initLightBlock6() {
        if (ItemTypes.LIGHT_BLOCK_6 != null) return;
        ItemTypes.LIGHT_BLOCK_6 = ItemTypeBuilder
                .builder(ItemLightBlock6Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_6)
                .build();
    }

    public static void initLightBlock7() {
        if (ItemTypes.LIGHT_BLOCK_7 != null) return;
        ItemTypes.LIGHT_BLOCK_7 = ItemTypeBuilder
                .builder(ItemLightBlock7Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_7)
                .build();
    }

    public static void initLightBlock8() {
        if (ItemTypes.LIGHT_BLOCK_8 != null) return;
        ItemTypes.LIGHT_BLOCK_8 = ItemTypeBuilder
                .builder(ItemLightBlock8Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_8)
                .build();
    }

    public static void initLightBlock9() {
        if (ItemTypes.LIGHT_BLOCK_9 != null) return;
        ItemTypes.LIGHT_BLOCK_9 = ItemTypeBuilder
                .builder(ItemLightBlock9Stack.class)
                .vanillaItem(ItemId.LIGHT_BLOCK_9)
                .build();
    }

    public static void initLightBlueCandle() {
        if (ItemTypes.LIGHT_BLUE_CANDLE != null) return;
        ItemTypes.LIGHT_BLUE_CANDLE = ItemTypeBuilder
                .builder(ItemLightBlueCandleStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_CANDLE)
                .build();
    }

    public static void initLightBlueCandleCake() {
        if (ItemTypes.LIGHT_BLUE_CANDLE_CAKE != null) return;
        ItemTypes.LIGHT_BLUE_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemLightBlueCandleCakeStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_CANDLE_CAKE)
                .build();
    }

    public static void initLightBlueCarpet() {
        if (ItemTypes.LIGHT_BLUE_CARPET != null) return;
        ItemTypes.LIGHT_BLUE_CARPET = ItemTypeBuilder
                .builder(ItemLightBlueCarpetStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_CARPET)
                .build();
    }

    public static void initLightBlueConcrete() {
        if (ItemTypes.LIGHT_BLUE_CONCRETE != null) return;
        ItemTypes.LIGHT_BLUE_CONCRETE = ItemTypeBuilder
                .builder(ItemLightBlueConcreteStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_CONCRETE)
                .build();
    }

    public static void initLightBlueConcretePowder() {
        if (ItemTypes.LIGHT_BLUE_CONCRETE_POWDER != null) return;
        ItemTypes.LIGHT_BLUE_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemLightBlueConcretePowderStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initLightBlueDye() {
        if (ItemTypes.LIGHT_BLUE_DYE != null) return;
        ItemTypes.LIGHT_BLUE_DYE = ItemTypeBuilder
                .builder(ItemLightBlueDyeStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_DYE)
                .build();
    }

    public static void initLightBlueGlazedTerracotta() {
        if (ItemTypes.LIGHT_BLUE_GLAZED_TERRACOTTA != null) return;
        ItemTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemLightBlueGlazedTerracottaStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initLightBlueShulkerBox() {
        if (ItemTypes.LIGHT_BLUE_SHULKER_BOX != null) return;
        ItemTypes.LIGHT_BLUE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLightBlueShulkerBoxStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_SHULKER_BOX)
                .build();
    }

    public static void initLightBlueStainedGlass() {
        if (ItemTypes.LIGHT_BLUE_STAINED_GLASS != null) return;
        ItemTypes.LIGHT_BLUE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemLightBlueStainedGlassStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initLightBlueStainedGlassPane() {
        if (ItemTypes.LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        ItemTypes.LIGHT_BLUE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemLightBlueStainedGlassPaneStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightBlueTerracotta() {
        if (ItemTypes.LIGHT_BLUE_TERRACOTTA != null) return;
        ItemTypes.LIGHT_BLUE_TERRACOTTA = ItemTypeBuilder
                .builder(ItemLightBlueTerracottaStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_TERRACOTTA)
                .build();
    }

    public static void initLightBlueWool() {
        if (ItemTypes.LIGHT_BLUE_WOOL != null) return;
        ItemTypes.LIGHT_BLUE_WOOL = ItemTypeBuilder
                .builder(ItemLightBlueWoolStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_WOOL)
                .build();
    }

    public static void initLightGrayCandle() {
        if (ItemTypes.LIGHT_GRAY_CANDLE != null) return;
        ItemTypes.LIGHT_GRAY_CANDLE = ItemTypeBuilder
                .builder(ItemLightGrayCandleStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_CANDLE)
                .build();
    }

    public static void initLightGrayCandleCake() {
        if (ItemTypes.LIGHT_GRAY_CANDLE_CAKE != null) return;
        ItemTypes.LIGHT_GRAY_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemLightGrayCandleCakeStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_CANDLE_CAKE)
                .build();
    }

    public static void initLightGrayCarpet() {
        if (ItemTypes.LIGHT_GRAY_CARPET != null) return;
        ItemTypes.LIGHT_GRAY_CARPET = ItemTypeBuilder
                .builder(ItemLightGrayCarpetStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_CARPET)
                .build();
    }

    public static void initLightGrayConcrete() {
        if (ItemTypes.LIGHT_GRAY_CONCRETE != null) return;
        ItemTypes.LIGHT_GRAY_CONCRETE = ItemTypeBuilder
                .builder(ItemLightGrayConcreteStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_CONCRETE)
                .build();
    }

    public static void initLightGrayConcretePowder() {
        if (ItemTypes.LIGHT_GRAY_CONCRETE_POWDER != null) return;
        ItemTypes.LIGHT_GRAY_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemLightGrayConcretePowderStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initLightGrayDye() {
        if (ItemTypes.LIGHT_GRAY_DYE != null) return;
        ItemTypes.LIGHT_GRAY_DYE = ItemTypeBuilder
                .builder(ItemLightGrayDyeStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_DYE)
                .build();
    }

    public static void initLightGrayShulkerBox() {
        if (ItemTypes.LIGHT_GRAY_SHULKER_BOX != null) return;
        ItemTypes.LIGHT_GRAY_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLightGrayShulkerBoxStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_SHULKER_BOX)
                .build();
    }

    public static void initLightGrayStainedGlass() {
        if (ItemTypes.LIGHT_GRAY_STAINED_GLASS != null) return;
        ItemTypes.LIGHT_GRAY_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemLightGrayStainedGlassStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initLightGrayStainedGlassPane() {
        if (ItemTypes.LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        ItemTypes.LIGHT_GRAY_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemLightGrayStainedGlassPaneStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightGrayTerracotta() {
        if (ItemTypes.LIGHT_GRAY_TERRACOTTA != null) return;
        ItemTypes.LIGHT_GRAY_TERRACOTTA = ItemTypeBuilder
                .builder(ItemLightGrayTerracottaStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_TERRACOTTA)
                .build();
    }

    public static void initLightGrayWool() {
        if (ItemTypes.LIGHT_GRAY_WOOL != null) return;
        ItemTypes.LIGHT_GRAY_WOOL = ItemTypeBuilder
                .builder(ItemLightGrayWoolStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_WOOL)
                .build();
    }

    public static void initLightWeightedPressurePlate() {
        if (ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE != null) return;
        ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemLightWeightedPressurePlateStack.class)
                .vanillaItem(ItemId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .build();
    }

    public static void initLightningRod() {
        if (ItemTypes.LIGHTNING_ROD != null) return;
        ItemTypes.LIGHTNING_ROD = ItemTypeBuilder
                .builder(ItemLightningRodStack.class)
                .vanillaItem(ItemId.LIGHTNING_ROD)
                .build();
    }

    public static void initLilac() {
        if (ItemTypes.LILAC != null) return;
        ItemTypes.LILAC = ItemTypeBuilder
                .builder(ItemLilacStack.class)
                .vanillaItem(ItemId.LILAC)
                .build();
    }

    public static void initLilyOfTheValley() {
        if (ItemTypes.LILY_OF_THE_VALLEY != null) return;
        ItemTypes.LILY_OF_THE_VALLEY = ItemTypeBuilder
                .builder(ItemLilyOfTheValleyStack.class)
                .vanillaItem(ItemId.LILY_OF_THE_VALLEY)
                .build();
    }

    public static void initLimeCandle() {
        if (ItemTypes.LIME_CANDLE != null) return;
        ItemTypes.LIME_CANDLE = ItemTypeBuilder
                .builder(ItemLimeCandleStack.class)
                .vanillaItem(ItemId.LIME_CANDLE)
                .build();
    }

    public static void initLimeCandleCake() {
        if (ItemTypes.LIME_CANDLE_CAKE != null) return;
        ItemTypes.LIME_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemLimeCandleCakeStack.class)
                .vanillaItem(ItemId.LIME_CANDLE_CAKE)
                .build();
    }

    public static void initLimeCarpet() {
        if (ItemTypes.LIME_CARPET != null) return;
        ItemTypes.LIME_CARPET = ItemTypeBuilder
                .builder(ItemLimeCarpetStack.class)
                .vanillaItem(ItemId.LIME_CARPET)
                .build();
    }

    public static void initLimeConcrete() {
        if (ItemTypes.LIME_CONCRETE != null) return;
        ItemTypes.LIME_CONCRETE = ItemTypeBuilder
                .builder(ItemLimeConcreteStack.class)
                .vanillaItem(ItemId.LIME_CONCRETE)
                .build();
    }

    public static void initLimeConcretePowder() {
        if (ItemTypes.LIME_CONCRETE_POWDER != null) return;
        ItemTypes.LIME_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemLimeConcretePowderStack.class)
                .vanillaItem(ItemId.LIME_CONCRETE_POWDER)
                .build();
    }

    public static void initLimeDye() {
        if (ItemTypes.LIME_DYE != null) return;
        ItemTypes.LIME_DYE = ItemTypeBuilder
                .builder(ItemLimeDyeStack.class)
                .vanillaItem(ItemId.LIME_DYE)
                .build();
    }

    public static void initLimeGlazedTerracotta() {
        if (ItemTypes.LIME_GLAZED_TERRACOTTA != null) return;
        ItemTypes.LIME_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemLimeGlazedTerracottaStack.class)
                .vanillaItem(ItemId.LIME_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initLimeShulkerBox() {
        if (ItemTypes.LIME_SHULKER_BOX != null) return;
        ItemTypes.LIME_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLimeShulkerBoxStack.class)
                .vanillaItem(ItemId.LIME_SHULKER_BOX)
                .build();
    }

    public static void initLimeStainedGlass() {
        if (ItemTypes.LIME_STAINED_GLASS != null) return;
        ItemTypes.LIME_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemLimeStainedGlassStack.class)
                .vanillaItem(ItemId.LIME_STAINED_GLASS)
                .build();
    }

    public static void initLimeStainedGlassPane() {
        if (ItemTypes.LIME_STAINED_GLASS_PANE != null) return;
        ItemTypes.LIME_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemLimeStainedGlassPaneStack.class)
                .vanillaItem(ItemId.LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLimeTerracotta() {
        if (ItemTypes.LIME_TERRACOTTA != null) return;
        ItemTypes.LIME_TERRACOTTA = ItemTypeBuilder
                .builder(ItemLimeTerracottaStack.class)
                .vanillaItem(ItemId.LIME_TERRACOTTA)
                .build();
    }

    public static void initLimeWool() {
        if (ItemTypes.LIME_WOOL != null) return;
        ItemTypes.LIME_WOOL = ItemTypeBuilder
                .builder(ItemLimeWoolStack.class)
                .vanillaItem(ItemId.LIME_WOOL)
                .build();
    }

    public static void initLingeringPotion() {
        if (ItemTypes.LINGERING_POTION != null) return;
        ItemTypes.LINGERING_POTION = ItemTypeBuilder
                .builder(ItemLingeringPotionStack.class)
                .vanillaItem(ItemId.LINGERING_POTION)
                .build();
    }

    public static void initLitBlastFurnace() {
        if (ItemTypes.LIT_BLAST_FURNACE != null) return;
        ItemTypes.LIT_BLAST_FURNACE = ItemTypeBuilder
                .builder(ItemLitBlastFurnaceStack.class)
                .vanillaItem(ItemId.LIT_BLAST_FURNACE)
                .build();
    }

    public static void initLitDeepslateRedstoneOre() {
        if (ItemTypes.LIT_DEEPSLATE_REDSTONE_ORE != null) return;
        ItemTypes.LIT_DEEPSLATE_REDSTONE_ORE = ItemTypeBuilder
                .builder(ItemLitDeepslateRedstoneOreStack.class)
                .vanillaItem(ItemId.LIT_DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initLitFurnace() {
        if (ItemTypes.LIT_FURNACE != null) return;
        ItemTypes.LIT_FURNACE = ItemTypeBuilder
                .builder(ItemLitFurnaceStack.class)
                .vanillaItem(ItemId.LIT_FURNACE)
                .build();
    }

    public static void initLitPumpkin() {
        if (ItemTypes.LIT_PUMPKIN != null) return;
        ItemTypes.LIT_PUMPKIN = ItemTypeBuilder
                .builder(ItemLitPumpkinStack.class)
                .vanillaItem(ItemId.LIT_PUMPKIN)
                .build();
    }

    public static void initLitRedstoneLamp() {
        if (ItemTypes.LIT_REDSTONE_LAMP != null) return;
        ItemTypes.LIT_REDSTONE_LAMP = ItemTypeBuilder
                .builder(ItemLitRedstoneLampStack.class)
                .vanillaItem(ItemId.LIT_REDSTONE_LAMP)
                .build();
    }

    public static void initLitRedstoneOre() {
        if (ItemTypes.LIT_REDSTONE_ORE != null) return;
        ItemTypes.LIT_REDSTONE_ORE = ItemTypeBuilder
                .builder(ItemLitRedstoneOreStack.class)
                .vanillaItem(ItemId.LIT_REDSTONE_ORE)
                .build();
    }

    public static void initLitSmoker() {
        if (ItemTypes.LIT_SMOKER != null) return;
        ItemTypes.LIT_SMOKER = ItemTypeBuilder
                .builder(ItemLitSmokerStack.class)
                .vanillaItem(ItemId.LIT_SMOKER)
                .build();
    }

    public static void initLlamaSpawnEgg() {
        if (ItemTypes.LLAMA_SPAWN_EGG != null) return;
        ItemTypes.LLAMA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemLlamaSpawnEggStack.class)
                .vanillaItem(ItemId.LLAMA_SPAWN_EGG)
                .build();
    }

    public static void initLodestone() {
        if (ItemTypes.LODESTONE != null) return;
        ItemTypes.LODESTONE = ItemTypeBuilder
                .builder(ItemLodestoneStack.class)
                .vanillaItem(ItemId.LODESTONE)
                .build();
    }

    public static void initLodestoneCompass() {
        if (ItemTypes.LODESTONE_COMPASS != null) return;
        ItemTypes.LODESTONE_COMPASS = ItemTypeBuilder
                .builder(ItemLodestoneCompassStack.class)
                .vanillaItem(ItemId.LODESTONE_COMPASS)
                .build();
    }

    public static void initLog() {
        if (ItemTypes.LOG != null) return;
        ItemTypes.LOG = ItemTypeBuilder
                .builder(ItemLogStack.class)
                .vanillaItem(ItemId.LOG)
                .build();
    }

    public static void initLog2() {
        if (ItemTypes.LOG2 != null) return;
        ItemTypes.LOG2 = ItemTypeBuilder
                .builder(ItemLog2Stack.class)
                .vanillaItem(ItemId.LOG2)
                .build();
    }

    public static void initLoom() {
        if (ItemTypes.LOOM != null) return;
        ItemTypes.LOOM = ItemTypeBuilder
                .builder(ItemLoomStack.class)
                .vanillaItem(ItemId.LOOM)
                .build();
    }

    public static void initMace() {
        if (ItemTypes.MACE != null) return;
        ItemTypes.MACE = ItemTypeBuilder
                .builder(ItemMaceStack.class)
                .vanillaItem(ItemId.MACE)
                .build();
    }

    public static void initMagentaCandle() {
        if (ItemTypes.MAGENTA_CANDLE != null) return;
        ItemTypes.MAGENTA_CANDLE = ItemTypeBuilder
                .builder(ItemMagentaCandleStack.class)
                .vanillaItem(ItemId.MAGENTA_CANDLE)
                .build();
    }

    public static void initMagentaCandleCake() {
        if (ItemTypes.MAGENTA_CANDLE_CAKE != null) return;
        ItemTypes.MAGENTA_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemMagentaCandleCakeStack.class)
                .vanillaItem(ItemId.MAGENTA_CANDLE_CAKE)
                .build();
    }

    public static void initMagentaCarpet() {
        if (ItemTypes.MAGENTA_CARPET != null) return;
        ItemTypes.MAGENTA_CARPET = ItemTypeBuilder
                .builder(ItemMagentaCarpetStack.class)
                .vanillaItem(ItemId.MAGENTA_CARPET)
                .build();
    }

    public static void initMagentaConcrete() {
        if (ItemTypes.MAGENTA_CONCRETE != null) return;
        ItemTypes.MAGENTA_CONCRETE = ItemTypeBuilder
                .builder(ItemMagentaConcreteStack.class)
                .vanillaItem(ItemId.MAGENTA_CONCRETE)
                .build();
    }

    public static void initMagentaConcretePowder() {
        if (ItemTypes.MAGENTA_CONCRETE_POWDER != null) return;
        ItemTypes.MAGENTA_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemMagentaConcretePowderStack.class)
                .vanillaItem(ItemId.MAGENTA_CONCRETE_POWDER)
                .build();
    }

    public static void initMagentaDye() {
        if (ItemTypes.MAGENTA_DYE != null) return;
        ItemTypes.MAGENTA_DYE = ItemTypeBuilder
                .builder(ItemMagentaDyeStack.class)
                .vanillaItem(ItemId.MAGENTA_DYE)
                .build();
    }

    public static void initMagentaGlazedTerracotta() {
        if (ItemTypes.MAGENTA_GLAZED_TERRACOTTA != null) return;
        ItemTypes.MAGENTA_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemMagentaGlazedTerracottaStack.class)
                .vanillaItem(ItemId.MAGENTA_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initMagentaShulkerBox() {
        if (ItemTypes.MAGENTA_SHULKER_BOX != null) return;
        ItemTypes.MAGENTA_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemMagentaShulkerBoxStack.class)
                .vanillaItem(ItemId.MAGENTA_SHULKER_BOX)
                .build();
    }

    public static void initMagentaStainedGlass() {
        if (ItemTypes.MAGENTA_STAINED_GLASS != null) return;
        ItemTypes.MAGENTA_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemMagentaStainedGlassStack.class)
                .vanillaItem(ItemId.MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initMagentaStainedGlassPane() {
        if (ItemTypes.MAGENTA_STAINED_GLASS_PANE != null) return;
        ItemTypes.MAGENTA_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemMagentaStainedGlassPaneStack.class)
                .vanillaItem(ItemId.MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initMagentaTerracotta() {
        if (ItemTypes.MAGENTA_TERRACOTTA != null) return;
        ItemTypes.MAGENTA_TERRACOTTA = ItemTypeBuilder
                .builder(ItemMagentaTerracottaStack.class)
                .vanillaItem(ItemId.MAGENTA_TERRACOTTA)
                .build();
    }

    public static void initMagentaWool() {
        if (ItemTypes.MAGENTA_WOOL != null) return;
        ItemTypes.MAGENTA_WOOL = ItemTypeBuilder
                .builder(ItemMagentaWoolStack.class)
                .vanillaItem(ItemId.MAGENTA_WOOL)
                .build();
    }

    public static void initMagma() {
        if (ItemTypes.MAGMA != null) return;
        ItemTypes.MAGMA = ItemTypeBuilder
                .builder(ItemMagmaStack.class)
                .vanillaItem(ItemId.MAGMA)
                .build();
    }

    public static void initMagmaCream() {
        if (ItemTypes.MAGMA_CREAM != null) return;
        ItemTypes.MAGMA_CREAM = ItemTypeBuilder
                .builder(ItemMagmaCreamStack.class)
                .vanillaItem(ItemId.MAGMA_CREAM)
                .build();
    }

    public static void initMagmaCubeSpawnEgg() {
        if (ItemTypes.MAGMA_CUBE_SPAWN_EGG != null) return;
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMagmaCubeSpawnEggStack.class)
                .vanillaItem(ItemId.MAGMA_CUBE_SPAWN_EGG)
                .build();
    }

    public static void initMangroveBoat() {
        if (ItemTypes.MANGROVE_BOAT != null) return;
        ItemTypes.MANGROVE_BOAT = ItemTypeBuilder
                .builder(ItemMangroveBoatStack.class)
                .vanillaItem(ItemId.MANGROVE_BOAT)
                .build();
    }

    public static void initMangroveButton() {
        if (ItemTypes.MANGROVE_BUTTON != null) return;
        ItemTypes.MANGROVE_BUTTON = ItemTypeBuilder
                .builder(ItemMangroveButtonStack.class)
                .vanillaItem(ItemId.MANGROVE_BUTTON)
                .build();
    }

    public static void initMangroveChestBoat() {
        if (ItemTypes.MANGROVE_CHEST_BOAT != null) return;
        ItemTypes.MANGROVE_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemMangroveChestBoatStack.class)
                .vanillaItem(ItemId.MANGROVE_CHEST_BOAT)
                .build();
    }

    public static void initMangroveDoor() {
        if (ItemTypes.MANGROVE_DOOR != null) return;
        ItemTypes.MANGROVE_DOOR = ItemTypeBuilder
                .builder(ItemMangroveDoorStack.class)
                .vanillaItem(ItemId.MANGROVE_DOOR)
                .build();
    }

    public static void initMangroveDoubleSlab() {
        if (ItemTypes.MANGROVE_DOUBLE_SLAB != null) return;
        ItemTypes.MANGROVE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemMangroveDoubleSlabStack.class)
                .vanillaItem(ItemId.MANGROVE_DOUBLE_SLAB)
                .build();
    }

    public static void initMangroveFence() {
        if (ItemTypes.MANGROVE_FENCE != null) return;
        ItemTypes.MANGROVE_FENCE = ItemTypeBuilder
                .builder(ItemMangroveFenceStack.class)
                .vanillaItem(ItemId.MANGROVE_FENCE)
                .build();
    }

    public static void initMangroveFenceGate() {
        if (ItemTypes.MANGROVE_FENCE_GATE != null) return;
        ItemTypes.MANGROVE_FENCE_GATE = ItemTypeBuilder
                .builder(ItemMangroveFenceGateStack.class)
                .vanillaItem(ItemId.MANGROVE_FENCE_GATE)
                .build();
    }

    public static void initMangroveHangingSign() {
        if (ItemTypes.MANGROVE_HANGING_SIGN != null) return;
        ItemTypes.MANGROVE_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemMangroveHangingSignStack.class)
                .vanillaItem(ItemId.MANGROVE_HANGING_SIGN)
                .build();
    }

    public static void initMangroveLeaves() {
        if (ItemTypes.MANGROVE_LEAVES != null) return;
        ItemTypes.MANGROVE_LEAVES = ItemTypeBuilder
                .builder(ItemMangroveLeavesStack.class)
                .vanillaItem(ItemId.MANGROVE_LEAVES)
                .build();
    }

    public static void initMangroveLog() {
        if (ItemTypes.MANGROVE_LOG != null) return;
        ItemTypes.MANGROVE_LOG = ItemTypeBuilder
                .builder(ItemMangroveLogStack.class)
                .vanillaItem(ItemId.MANGROVE_LOG)
                .build();
    }

    public static void initMangrovePlanks() {
        if (ItemTypes.MANGROVE_PLANKS != null) return;
        ItemTypes.MANGROVE_PLANKS = ItemTypeBuilder
                .builder(ItemMangrovePlanksStack.class)
                .vanillaItem(ItemId.MANGROVE_PLANKS)
                .build();
    }

    public static void initMangrovePressurePlate() {
        if (ItemTypes.MANGROVE_PRESSURE_PLATE != null) return;
        ItemTypes.MANGROVE_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemMangrovePressurePlateStack.class)
                .vanillaItem(ItemId.MANGROVE_PRESSURE_PLATE)
                .build();
    }

    public static void initMangrovePropagule() {
        if (ItemTypes.MANGROVE_PROPAGULE != null) return;
        ItemTypes.MANGROVE_PROPAGULE = ItemTypeBuilder
                .builder(ItemMangrovePropaguleStack.class)
                .vanillaItem(ItemId.MANGROVE_PROPAGULE)
                .build();
    }

    public static void initMangroveRoots() {
        if (ItemTypes.MANGROVE_ROOTS != null) return;
        ItemTypes.MANGROVE_ROOTS = ItemTypeBuilder
                .builder(ItemMangroveRootsStack.class)
                .vanillaItem(ItemId.MANGROVE_ROOTS)
                .build();
    }

    public static void initMangroveSign() {
        if (ItemTypes.MANGROVE_SIGN != null) return;
        ItemTypes.MANGROVE_SIGN = ItemTypeBuilder
                .builder(ItemMangroveSignStack.class)
                .vanillaItem(ItemId.MANGROVE_SIGN)
                .build();
    }

    public static void initMangroveSlab() {
        if (ItemTypes.MANGROVE_SLAB != null) return;
        ItemTypes.MANGROVE_SLAB = ItemTypeBuilder
                .builder(ItemMangroveSlabStack.class)
                .vanillaItem(ItemId.MANGROVE_SLAB)
                .build();
    }

    public static void initMangroveStairs() {
        if (ItemTypes.MANGROVE_STAIRS != null) return;
        ItemTypes.MANGROVE_STAIRS = ItemTypeBuilder
                .builder(ItemMangroveStairsStack.class)
                .vanillaItem(ItemId.MANGROVE_STAIRS)
                .build();
    }

    public static void initMangroveStandingSign() {
        if (ItemTypes.MANGROVE_STANDING_SIGN != null) return;
        ItemTypes.MANGROVE_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemMangroveStandingSignStack.class)
                .vanillaItem(ItemId.MANGROVE_STANDING_SIGN)
                .build();
    }

    public static void initMangroveTrapdoor() {
        if (ItemTypes.MANGROVE_TRAPDOOR != null) return;
        ItemTypes.MANGROVE_TRAPDOOR = ItemTypeBuilder
                .builder(ItemMangroveTrapdoorStack.class)
                .vanillaItem(ItemId.MANGROVE_TRAPDOOR)
                .build();
    }

    public static void initMangroveWallSign() {
        if (ItemTypes.MANGROVE_WALL_SIGN != null) return;
        ItemTypes.MANGROVE_WALL_SIGN = ItemTypeBuilder
                .builder(ItemMangroveWallSignStack.class)
                .vanillaItem(ItemId.MANGROVE_WALL_SIGN)
                .build();
    }

    public static void initMangroveWood() {
        if (ItemTypes.MANGROVE_WOOD != null) return;
        ItemTypes.MANGROVE_WOOD = ItemTypeBuilder
                .builder(ItemMangroveWoodStack.class)
                .vanillaItem(ItemId.MANGROVE_WOOD)
                .build();
    }

    public static void initMedicine() {
        if (ItemTypes.MEDICINE != null) return;
        ItemTypes.MEDICINE = ItemTypeBuilder
                .builder(ItemMedicineStack.class)
                .vanillaItem(ItemId.MEDICINE)
                .build();
    }

    public static void initMediumAmethystBud() {
        if (ItemTypes.MEDIUM_AMETHYST_BUD != null) return;
        ItemTypes.MEDIUM_AMETHYST_BUD = ItemTypeBuilder
                .builder(ItemMediumAmethystBudStack.class)
                .vanillaItem(ItemId.MEDIUM_AMETHYST_BUD)
                .build();
    }

    public static void initMelonBlock() {
        if (ItemTypes.MELON_BLOCK != null) return;
        ItemTypes.MELON_BLOCK = ItemTypeBuilder
                .builder(ItemMelonBlockStack.class)
                .vanillaItem(ItemId.MELON_BLOCK)
                .build();
    }

    public static void initMelonSeeds() {
        if (ItemTypes.MELON_SEEDS != null) return;
        ItemTypes.MELON_SEEDS = ItemTypeBuilder
                .builder(ItemMelonSeedsStack.class)
                .vanillaItem(ItemId.MELON_SEEDS)
                .build();
    }

    public static void initMelonSlice() {
        if (ItemTypes.MELON_SLICE != null) return;
        ItemTypes.MELON_SLICE = ItemTypeBuilder
                .builder(ItemMelonSliceStack.class)
                .vanillaItem(ItemId.MELON_SLICE)
                .build();
    }

    public static void initMelonStem() {
        if (ItemTypes.MELON_STEM != null) return;
        ItemTypes.MELON_STEM = ItemTypeBuilder
                .builder(ItemMelonStemStack.class)
                .vanillaItem(ItemId.MELON_STEM)
                .build();
    }

    public static void initMilkBucket() {
        if (ItemTypes.MILK_BUCKET != null) return;
        ItemTypes.MILK_BUCKET = ItemTypeBuilder
                .builder(ItemMilkBucketStack.class)
                .vanillaItem(ItemId.MILK_BUCKET)
                .build();
    }

    public static void initMinecart() {
        if (ItemTypes.MINECART != null) return;
        ItemTypes.MINECART = ItemTypeBuilder
                .builder(ItemMinecartStack.class)
                .vanillaItem(ItemId.MINECART)
                .build();
    }

    public static void initMinerPotterySherd() {
        if (ItemTypes.MINER_POTTERY_SHERD != null) return;
        ItemTypes.MINER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemMinerPotterySherdStack.class)
                .vanillaItem(ItemId.MINER_POTTERY_SHERD)
                .build();
    }

    public static void initMobSpawner() {
        if (ItemTypes.MOB_SPAWNER != null) return;
        ItemTypes.MOB_SPAWNER = ItemTypeBuilder
                .builder(ItemMobSpawnerStack.class)
                .vanillaItem(ItemId.MOB_SPAWNER)
                .build();
    }

    public static void initMojangBannerPattern() {
        if (ItemTypes.MOJANG_BANNER_PATTERN != null) return;
        ItemTypes.MOJANG_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemMojangBannerPatternStack.class)
                .vanillaItem(ItemId.MOJANG_BANNER_PATTERN)
                .build();
    }

    public static void initMonsterEgg() {
        if (ItemTypes.MONSTER_EGG != null) return;
        ItemTypes.MONSTER_EGG = ItemTypeBuilder
                .builder(ItemMonsterEggStack.class)
                .vanillaItem(ItemId.MONSTER_EGG)
                .build();
    }

    public static void initMooshroomSpawnEgg() {
        if (ItemTypes.MOOSHROOM_SPAWN_EGG != null) return;
        ItemTypes.MOOSHROOM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMooshroomSpawnEggStack.class)
                .vanillaItem(ItemId.MOOSHROOM_SPAWN_EGG)
                .build();
    }

    public static void initMossBlock() {
        if (ItemTypes.MOSS_BLOCK != null) return;
        ItemTypes.MOSS_BLOCK = ItemTypeBuilder
                .builder(ItemMossBlockStack.class)
                .vanillaItem(ItemId.MOSS_BLOCK)
                .build();
    }

    public static void initMossCarpet() {
        if (ItemTypes.MOSS_CARPET != null) return;
        ItemTypes.MOSS_CARPET = ItemTypeBuilder
                .builder(ItemMossCarpetStack.class)
                .vanillaItem(ItemId.MOSS_CARPET)
                .build();
    }

    public static void initMossyCobblestone() {
        if (ItemTypes.MOSSY_COBBLESTONE != null) return;
        ItemTypes.MOSSY_COBBLESTONE = ItemTypeBuilder
                .builder(ItemMossyCobblestoneStack.class)
                .vanillaItem(ItemId.MOSSY_COBBLESTONE)
                .build();
    }

    public static void initMossyCobblestoneDoubleSlab() {
        if (ItemTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB != null) return;
        ItemTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemMossyCobblestoneDoubleSlabStack.class)
                .vanillaItem(ItemId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initMossyCobblestoneSlab() {
        if (ItemTypes.MOSSY_COBBLESTONE_SLAB != null) return;
        ItemTypes.MOSSY_COBBLESTONE_SLAB = ItemTypeBuilder
                .builder(ItemMossyCobblestoneSlabStack.class)
                .vanillaItem(ItemId.MOSSY_COBBLESTONE_SLAB)
                .build();
    }

    public static void initMossyCobblestoneStairs() {
        if (ItemTypes.MOSSY_COBBLESTONE_STAIRS != null) return;
        ItemTypes.MOSSY_COBBLESTONE_STAIRS = ItemTypeBuilder
                .builder(ItemMossyCobblestoneStairsStack.class)
                .vanillaItem(ItemId.MOSSY_COBBLESTONE_STAIRS)
                .build();
    }

    public static void initMossyStoneBrickDoubleSlab() {
        if (ItemTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemMossyStoneBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initMossyStoneBrickSlab() {
        if (ItemTypes.MOSSY_STONE_BRICK_SLAB != null) return;
        ItemTypes.MOSSY_STONE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemMossyStoneBrickSlabStack.class)
                .vanillaItem(ItemId.MOSSY_STONE_BRICK_SLAB)
                .build();
    }

    public static void initMossyStoneBrickStairs() {
        if (ItemTypes.MOSSY_STONE_BRICK_STAIRS != null) return;
        ItemTypes.MOSSY_STONE_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemMossyStoneBrickStairsStack.class)
                .vanillaItem(ItemId.MOSSY_STONE_BRICK_STAIRS)
                .build();
    }

    public static void initMossyStoneBricks() {
        if (ItemTypes.MOSSY_STONE_BRICKS != null) return;
        ItemTypes.MOSSY_STONE_BRICKS = ItemTypeBuilder
                .builder(ItemMossyStoneBricksStack.class)
                .vanillaItem(ItemId.MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initMournerPotterySherd() {
        if (ItemTypes.MOURNER_POTTERY_SHERD != null) return;
        ItemTypes.MOURNER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemMournerPotterySherdStack.class)
                .vanillaItem(ItemId.MOURNER_POTTERY_SHERD)
                .build();
    }

    public static void initMovingBlock() {
        if (ItemTypes.MOVING_BLOCK != null) return;
        ItemTypes.MOVING_BLOCK = ItemTypeBuilder
                .builder(ItemMovingBlockStack.class)
                .vanillaItem(ItemId.MOVING_BLOCK)
                .build();
    }

    public static void initMud() {
        if (ItemTypes.MUD != null) return;
        ItemTypes.MUD = ItemTypeBuilder
                .builder(ItemMudStack.class)
                .vanillaItem(ItemId.MUD)
                .build();
    }

    public static void initMudBrickDoubleSlab() {
        if (ItemTypes.MUD_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.MUD_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemMudBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.MUD_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initMudBrickSlab() {
        if (ItemTypes.MUD_BRICK_SLAB != null) return;
        ItemTypes.MUD_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemMudBrickSlabStack.class)
                .vanillaItem(ItemId.MUD_BRICK_SLAB)
                .build();
    }

    public static void initMudBrickStairs() {
        if (ItemTypes.MUD_BRICK_STAIRS != null) return;
        ItemTypes.MUD_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemMudBrickStairsStack.class)
                .vanillaItem(ItemId.MUD_BRICK_STAIRS)
                .build();
    }

    public static void initMudBrickWall() {
        if (ItemTypes.MUD_BRICK_WALL != null) return;
        ItemTypes.MUD_BRICK_WALL = ItemTypeBuilder
                .builder(ItemMudBrickWallStack.class)
                .vanillaItem(ItemId.MUD_BRICK_WALL)
                .build();
    }

    public static void initMudBricks() {
        if (ItemTypes.MUD_BRICKS != null) return;
        ItemTypes.MUD_BRICKS = ItemTypeBuilder
                .builder(ItemMudBricksStack.class)
                .vanillaItem(ItemId.MUD_BRICKS)
                .build();
    }

    public static void initMuddyMangroveRoots() {
        if (ItemTypes.MUDDY_MANGROVE_ROOTS != null) return;
        ItemTypes.MUDDY_MANGROVE_ROOTS = ItemTypeBuilder
                .builder(ItemMuddyMangroveRootsStack.class)
                .vanillaItem(ItemId.MUDDY_MANGROVE_ROOTS)
                .build();
    }

    public static void initMuleSpawnEgg() {
        if (ItemTypes.MULE_SPAWN_EGG != null) return;
        ItemTypes.MULE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMuleSpawnEggStack.class)
                .vanillaItem(ItemId.MULE_SPAWN_EGG)
                .build();
    }

    public static void initMushroomStew() {
        if (ItemTypes.MUSHROOM_STEW != null) return;
        ItemTypes.MUSHROOM_STEW = ItemTypeBuilder
                .builder(ItemMushroomStewStack.class)
                .vanillaItem(ItemId.MUSHROOM_STEW)
                .build();
    }

    public static void initMusicDisc11() {
        if (ItemTypes.MUSIC_DISC_11 != null) return;
        ItemTypes.MUSIC_DISC_11 = ItemTypeBuilder
                .builder(ItemMusicDisc11Stack.class)
                .vanillaItem(ItemId.MUSIC_DISC_11)
                .build();
    }

    public static void initMusicDisc13() {
        if (ItemTypes.MUSIC_DISC_13 != null) return;
        ItemTypes.MUSIC_DISC_13 = ItemTypeBuilder
                .builder(ItemMusicDisc13Stack.class)
                .vanillaItem(ItemId.MUSIC_DISC_13)
                .build();
    }

    public static void initMusicDisc5() {
        if (ItemTypes.MUSIC_DISC_5 != null) return;
        ItemTypes.MUSIC_DISC_5 = ItemTypeBuilder
                .builder(ItemMusicDisc5Stack.class)
                .vanillaItem(ItemId.MUSIC_DISC_5)
                .build();
    }

    public static void initMusicDiscBlocks() {
        if (ItemTypes.MUSIC_DISC_BLOCKS != null) return;
        ItemTypes.MUSIC_DISC_BLOCKS = ItemTypeBuilder
                .builder(ItemMusicDiscBlocksStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_BLOCKS)
                .build();
    }

    public static void initMusicDiscCat() {
        if (ItemTypes.MUSIC_DISC_CAT != null) return;
        ItemTypes.MUSIC_DISC_CAT = ItemTypeBuilder
                .builder(ItemMusicDiscCatStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_CAT)
                .build();
    }

    public static void initMusicDiscChirp() {
        if (ItemTypes.MUSIC_DISC_CHIRP != null) return;
        ItemTypes.MUSIC_DISC_CHIRP = ItemTypeBuilder
                .builder(ItemMusicDiscChirpStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_CHIRP)
                .build();
    }

    public static void initMusicDiscCreator() {
        if (ItemTypes.MUSIC_DISC_CREATOR != null) return;
        ItemTypes.MUSIC_DISC_CREATOR = ItemTypeBuilder
                .builder(ItemMusicDiscCreatorStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_CREATOR)
                .build();
    }

    public static void initMusicDiscCreatorMusicBox() {
        if (ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX != null) return;
        ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX = ItemTypeBuilder
                .builder(ItemMusicDiscCreatorMusicBoxStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_CREATOR_MUSIC_BOX)
                .build();
    }

    public static void initMusicDiscFar() {
        if (ItemTypes.MUSIC_DISC_FAR != null) return;
        ItemTypes.MUSIC_DISC_FAR = ItemTypeBuilder
                .builder(ItemMusicDiscFarStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_FAR)
                .build();
    }

    public static void initMusicDiscMall() {
        if (ItemTypes.MUSIC_DISC_MALL != null) return;
        ItemTypes.MUSIC_DISC_MALL = ItemTypeBuilder
                .builder(ItemMusicDiscMallStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_MALL)
                .build();
    }

    public static void initMusicDiscMellohi() {
        if (ItemTypes.MUSIC_DISC_MELLOHI != null) return;
        ItemTypes.MUSIC_DISC_MELLOHI = ItemTypeBuilder
                .builder(ItemMusicDiscMellohiStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_MELLOHI)
                .build();
    }

    public static void initMusicDiscOtherside() {
        if (ItemTypes.MUSIC_DISC_OTHERSIDE != null) return;
        ItemTypes.MUSIC_DISC_OTHERSIDE = ItemTypeBuilder
                .builder(ItemMusicDiscOthersideStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_OTHERSIDE)
                .build();
    }

    public static void initMusicDiscPigstep() {
        if (ItemTypes.MUSIC_DISC_PIGSTEP != null) return;
        ItemTypes.MUSIC_DISC_PIGSTEP = ItemTypeBuilder
                .builder(ItemMusicDiscPigstepStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_PIGSTEP)
                .build();
    }

    public static void initMusicDiscPrecipice() {
        if (ItemTypes.MUSIC_DISC_PRECIPICE != null) return;
        ItemTypes.MUSIC_DISC_PRECIPICE = ItemTypeBuilder
                .builder(ItemMusicDiscPrecipiceStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_PRECIPICE)
                .build();
    }

    public static void initMusicDiscRelic() {
        if (ItemTypes.MUSIC_DISC_RELIC != null) return;
        ItemTypes.MUSIC_DISC_RELIC = ItemTypeBuilder
                .builder(ItemMusicDiscRelicStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_RELIC)
                .build();
    }

    public static void initMusicDiscStal() {
        if (ItemTypes.MUSIC_DISC_STAL != null) return;
        ItemTypes.MUSIC_DISC_STAL = ItemTypeBuilder
                .builder(ItemMusicDiscStalStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_STAL)
                .build();
    }

    public static void initMusicDiscStrad() {
        if (ItemTypes.MUSIC_DISC_STRAD != null) return;
        ItemTypes.MUSIC_DISC_STRAD = ItemTypeBuilder
                .builder(ItemMusicDiscStradStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_STRAD)
                .build();
    }

    public static void initMusicDiscWait() {
        if (ItemTypes.MUSIC_DISC_WAIT != null) return;
        ItemTypes.MUSIC_DISC_WAIT = ItemTypeBuilder
                .builder(ItemMusicDiscWaitStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_WAIT)
                .build();
    }

    public static void initMusicDiscWard() {
        if (ItemTypes.MUSIC_DISC_WARD != null) return;
        ItemTypes.MUSIC_DISC_WARD = ItemTypeBuilder
                .builder(ItemMusicDiscWardStack.class)
                .vanillaItem(ItemId.MUSIC_DISC_WARD)
                .build();
    }

    public static void initMutton() {
        if (ItemTypes.MUTTON != null) return;
        ItemTypes.MUTTON = ItemTypeBuilder
                .builder(ItemMuttonStack.class)
                .vanillaItem(ItemId.MUTTON)
                .build();
    }

    public static void initMycelium() {
        if (ItemTypes.MYCELIUM != null) return;
        ItemTypes.MYCELIUM = ItemTypeBuilder
                .builder(ItemMyceliumStack.class)
                .vanillaItem(ItemId.MYCELIUM)
                .build();
    }

    public static void initNameTag() {
        if (ItemTypes.NAME_TAG != null) return;
        ItemTypes.NAME_TAG = ItemTypeBuilder
                .builder(ItemNameTagStack.class)
                .vanillaItem(ItemId.NAME_TAG)
                .build();
    }

    public static void initNautilusShell() {
        if (ItemTypes.NAUTILUS_SHELL != null) return;
        ItemTypes.NAUTILUS_SHELL = ItemTypeBuilder
                .builder(ItemNautilusShellStack.class)
                .vanillaItem(ItemId.NAUTILUS_SHELL)
                .build();
    }

    public static void initNetherBrick() {
        if (ItemTypes.NETHER_BRICK != null) return;
        ItemTypes.NETHER_BRICK = ItemTypeBuilder
                .builder(ItemNetherBrickStack.class)
                .vanillaItem(ItemId.NETHER_BRICK)
                .build();
    }

    public static void initNetherBrickDoubleSlab() {
        if (ItemTypes.NETHER_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.NETHER_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemNetherBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.NETHER_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initNetherBrickFence() {
        if (ItemTypes.NETHER_BRICK_FENCE != null) return;
        ItemTypes.NETHER_BRICK_FENCE = ItemTypeBuilder
                .builder(ItemNetherBrickFenceStack.class)
                .vanillaItem(ItemId.NETHER_BRICK_FENCE)
                .build();
    }

    public static void initNetherBrickSlab() {
        if (ItemTypes.NETHER_BRICK_SLAB != null) return;
        ItemTypes.NETHER_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemNetherBrickSlabStack.class)
                .vanillaItem(ItemId.NETHER_BRICK_SLAB)
                .build();
    }

    public static void initNetherBrickStairs() {
        if (ItemTypes.NETHER_BRICK_STAIRS != null) return;
        ItemTypes.NETHER_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemNetherBrickStairsStack.class)
                .vanillaItem(ItemId.NETHER_BRICK_STAIRS)
                .build();
    }

    public static void initNetherGoldOre() {
        if (ItemTypes.NETHER_GOLD_ORE != null) return;
        ItemTypes.NETHER_GOLD_ORE = ItemTypeBuilder
                .builder(ItemNetherGoldOreStack.class)
                .vanillaItem(ItemId.NETHER_GOLD_ORE)
                .build();
    }

    public static void initNetherSprouts() {
        if (ItemTypes.NETHER_SPROUTS != null) return;
        ItemTypes.NETHER_SPROUTS = ItemTypeBuilder
                .builder(ItemNetherSproutsStack.class)
                .vanillaItem(ItemId.NETHER_SPROUTS)
                .build();
    }

    public static void initNetherStar() {
        if (ItemTypes.NETHER_STAR != null) return;
        ItemTypes.NETHER_STAR = ItemTypeBuilder
                .builder(ItemNetherStarStack.class)
                .vanillaItem(ItemId.NETHER_STAR)
                .build();
    }

    public static void initNetherWart() {
        if (ItemTypes.NETHER_WART != null) return;
        ItemTypes.NETHER_WART = ItemTypeBuilder
                .builder(ItemNetherWartStack.class)
                .vanillaItem(ItemId.NETHER_WART)
                .build();
    }

    public static void initNetherWartBlock() {
        if (ItemTypes.NETHER_WART_BLOCK != null) return;
        ItemTypes.NETHER_WART_BLOCK = ItemTypeBuilder
                .builder(ItemNetherWartBlockStack.class)
                .vanillaItem(ItemId.NETHER_WART_BLOCK)
                .build();
    }

    public static void initNetherbrick0() {
        if (ItemTypes.NETHERBRICK != null) return;
        ItemTypes.NETHERBRICK = ItemTypeBuilder
                .builder(ItemNetherbrick0Stack.class)
                .vanillaItem(ItemId.NETHERBRICK)
                .build();
    }

    public static void initNetheriteAxe() {
        if (ItemTypes.NETHERITE_AXE != null) return;
        ItemTypes.NETHERITE_AXE = ItemTypeBuilder
                .builder(ItemNetheriteAxeStack.class)
                .vanillaItem(ItemId.NETHERITE_AXE)
                .build();
    }

    public static void initNetheriteBlock() {
        if (ItemTypes.NETHERITE_BLOCK != null) return;
        ItemTypes.NETHERITE_BLOCK = ItemTypeBuilder
                .builder(ItemNetheriteBlockStack.class)
                .vanillaItem(ItemId.NETHERITE_BLOCK)
                .build();
    }

    public static void initNetheriteBoots() {
        if (ItemTypes.NETHERITE_BOOTS != null) return;
        ItemTypes.NETHERITE_BOOTS = ItemTypeBuilder
                .builder(ItemNetheriteBootsStack.class)
                .vanillaItem(ItemId.NETHERITE_BOOTS)
                .build();
    }

    public static void initNetheriteChestplate() {
        if (ItemTypes.NETHERITE_CHESTPLATE != null) return;
        ItemTypes.NETHERITE_CHESTPLATE = ItemTypeBuilder
                .builder(ItemNetheriteChestplateStack.class)
                .vanillaItem(ItemId.NETHERITE_CHESTPLATE)
                .build();
    }

    public static void initNetheriteHelmet() {
        if (ItemTypes.NETHERITE_HELMET != null) return;
        ItemTypes.NETHERITE_HELMET = ItemTypeBuilder
                .builder(ItemNetheriteHelmetStack.class)
                .vanillaItem(ItemId.NETHERITE_HELMET)
                .build();
    }

    public static void initNetheriteHoe() {
        if (ItemTypes.NETHERITE_HOE != null) return;
        ItemTypes.NETHERITE_HOE = ItemTypeBuilder
                .builder(ItemNetheriteHoeStack.class)
                .vanillaItem(ItemId.NETHERITE_HOE)
                .build();
    }

    public static void initNetheriteIngot() {
        if (ItemTypes.NETHERITE_INGOT != null) return;
        ItemTypes.NETHERITE_INGOT = ItemTypeBuilder
                .builder(ItemNetheriteIngotStack.class)
                .vanillaItem(ItemId.NETHERITE_INGOT)
                .build();
    }

    public static void initNetheriteLeggings() {
        if (ItemTypes.NETHERITE_LEGGINGS != null) return;
        ItemTypes.NETHERITE_LEGGINGS = ItemTypeBuilder
                .builder(ItemNetheriteLeggingsStack.class)
                .vanillaItem(ItemId.NETHERITE_LEGGINGS)
                .build();
    }

    public static void initNetheritePickaxe() {
        if (ItemTypes.NETHERITE_PICKAXE != null) return;
        ItemTypes.NETHERITE_PICKAXE = ItemTypeBuilder
                .builder(ItemNetheritePickaxeStack.class)
                .vanillaItem(ItemId.NETHERITE_PICKAXE)
                .build();
    }

    public static void initNetheriteScrap() {
        if (ItemTypes.NETHERITE_SCRAP != null) return;
        ItemTypes.NETHERITE_SCRAP = ItemTypeBuilder
                .builder(ItemNetheriteScrapStack.class)
                .vanillaItem(ItemId.NETHERITE_SCRAP)
                .build();
    }

    public static void initNetheriteShovel() {
        if (ItemTypes.NETHERITE_SHOVEL != null) return;
        ItemTypes.NETHERITE_SHOVEL = ItemTypeBuilder
                .builder(ItemNetheriteShovelStack.class)
                .vanillaItem(ItemId.NETHERITE_SHOVEL)
                .build();
    }

    public static void initNetheriteSword() {
        if (ItemTypes.NETHERITE_SWORD != null) return;
        ItemTypes.NETHERITE_SWORD = ItemTypeBuilder
                .builder(ItemNetheriteSwordStack.class)
                .vanillaItem(ItemId.NETHERITE_SWORD)
                .build();
    }

    public static void initNetheriteUpgradeSmithingTemplate() {
        if (ItemTypes.NETHERITE_UPGRADE_SMITHING_TEMPLATE != null) return;
        ItemTypes.NETHERITE_UPGRADE_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemNetheriteUpgradeSmithingTemplateStack.class)
                .vanillaItem(ItemId.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .build();
    }

    public static void initNetherrack() {
        if (ItemTypes.NETHERRACK != null) return;
        ItemTypes.NETHERRACK = ItemTypeBuilder
                .builder(ItemNetherrackStack.class)
                .vanillaItem(ItemId.NETHERRACK)
                .build();
    }

    public static void initNetherreactor() {
        if (ItemTypes.NETHERREACTOR != null) return;
        ItemTypes.NETHERREACTOR = ItemTypeBuilder
                .builder(ItemNetherreactorStack.class)
                .vanillaItem(ItemId.NETHERREACTOR)
                .build();
    }

    public static void initNormalStoneDoubleSlab() {
        if (ItemTypes.NORMAL_STONE_DOUBLE_SLAB != null) return;
        ItemTypes.NORMAL_STONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemNormalStoneDoubleSlabStack.class)
                .vanillaItem(ItemId.NORMAL_STONE_DOUBLE_SLAB)
                .build();
    }

    public static void initNormalStoneSlab() {
        if (ItemTypes.NORMAL_STONE_SLAB != null) return;
        ItemTypes.NORMAL_STONE_SLAB = ItemTypeBuilder
                .builder(ItemNormalStoneSlabStack.class)
                .vanillaItem(ItemId.NORMAL_STONE_SLAB)
                .build();
    }

    public static void initNormalStoneStairs() {
        if (ItemTypes.NORMAL_STONE_STAIRS != null) return;
        ItemTypes.NORMAL_STONE_STAIRS = ItemTypeBuilder
                .builder(ItemNormalStoneStairsStack.class)
                .vanillaItem(ItemId.NORMAL_STONE_STAIRS)
                .build();
    }

    public static void initNoteblock() {
        if (ItemTypes.NOTEBLOCK != null) return;
        ItemTypes.NOTEBLOCK = ItemTypeBuilder
                .builder(ItemNoteblockStack.class)
                .vanillaItem(ItemId.NOTEBLOCK)
                .build();
    }

    public static void initNpcSpawnEgg() {
        if (ItemTypes.NPC_SPAWN_EGG != null) return;
        ItemTypes.NPC_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemNpcSpawnEggStack.class)
                .vanillaItem(ItemId.NPC_SPAWN_EGG)
                .build();
    }

    public static void initOakBoat() {
        if (ItemTypes.OAK_BOAT != null) return;
        ItemTypes.OAK_BOAT = ItemTypeBuilder
                .builder(ItemOakBoatStack.class)
                .vanillaItem(ItemId.OAK_BOAT)
                .build();
    }

    public static void initOakChestBoat() {
        if (ItemTypes.OAK_CHEST_BOAT != null) return;
        ItemTypes.OAK_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemOakChestBoatStack.class)
                .vanillaItem(ItemId.OAK_CHEST_BOAT)
                .build();
    }

    public static void initOakDoubleSlab() {
        if (ItemTypes.OAK_DOUBLE_SLAB != null) return;
        ItemTypes.OAK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemOakDoubleSlabStack.class)
                .vanillaItem(ItemId.OAK_DOUBLE_SLAB)
                .build();
    }

    public static void initOakFence() {
        if (ItemTypes.OAK_FENCE != null) return;
        ItemTypes.OAK_FENCE = ItemTypeBuilder
                .builder(ItemOakFenceStack.class)
                .vanillaItem(ItemId.OAK_FENCE)
                .build();
    }

    public static void initOakHangingSign() {
        if (ItemTypes.OAK_HANGING_SIGN != null) return;
        ItemTypes.OAK_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemOakHangingSignStack.class)
                .vanillaItem(ItemId.OAK_HANGING_SIGN)
                .build();
    }

    public static void initOakLeaves() {
        if (ItemTypes.OAK_LEAVES != null) return;
        ItemTypes.OAK_LEAVES = ItemTypeBuilder
                .builder(ItemOakLeavesStack.class)
                .vanillaItem(ItemId.OAK_LEAVES)
                .build();
    }

    public static void initOakLog() {
        if (ItemTypes.OAK_LOG != null) return;
        ItemTypes.OAK_LOG = ItemTypeBuilder
                .builder(ItemOakLogStack.class)
                .vanillaItem(ItemId.OAK_LOG)
                .build();
    }

    public static void initOakPlanks() {
        if (ItemTypes.OAK_PLANKS != null) return;
        ItemTypes.OAK_PLANKS = ItemTypeBuilder
                .builder(ItemOakPlanksStack.class)
                .vanillaItem(ItemId.OAK_PLANKS)
                .build();
    }

    public static void initOakSapling() {
        if (ItemTypes.OAK_SAPLING != null) return;
        ItemTypes.OAK_SAPLING = ItemTypeBuilder
                .builder(ItemOakSaplingStack.class)
                .vanillaItem(ItemId.OAK_SAPLING)
                .build();
    }

    public static void initOakSign() {
        if (ItemTypes.OAK_SIGN != null) return;
        ItemTypes.OAK_SIGN = ItemTypeBuilder
                .builder(ItemOakSignStack.class)
                .vanillaItem(ItemId.OAK_SIGN)
                .build();
    }

    public static void initOakSlab() {
        if (ItemTypes.OAK_SLAB != null) return;
        ItemTypes.OAK_SLAB = ItemTypeBuilder
                .builder(ItemOakSlabStack.class)
                .vanillaItem(ItemId.OAK_SLAB)
                .build();
    }

    public static void initOakStairs() {
        if (ItemTypes.OAK_STAIRS != null) return;
        ItemTypes.OAK_STAIRS = ItemTypeBuilder
                .builder(ItemOakStairsStack.class)
                .vanillaItem(ItemId.OAK_STAIRS)
                .build();
    }

    public static void initOakWood() {
        if (ItemTypes.OAK_WOOD != null) return;
        ItemTypes.OAK_WOOD = ItemTypeBuilder
                .builder(ItemOakWoodStack.class)
                .vanillaItem(ItemId.OAK_WOOD)
                .build();
    }

    public static void initObserver() {
        if (ItemTypes.OBSERVER != null) return;
        ItemTypes.OBSERVER = ItemTypeBuilder
                .builder(ItemObserverStack.class)
                .vanillaItem(ItemId.OBSERVER)
                .build();
    }

    public static void initObsidian() {
        if (ItemTypes.OBSIDIAN != null) return;
        ItemTypes.OBSIDIAN = ItemTypeBuilder
                .builder(ItemObsidianStack.class)
                .vanillaItem(ItemId.OBSIDIAN)
                .build();
    }

    public static void initOcelotSpawnEgg() {
        if (ItemTypes.OCELOT_SPAWN_EGG != null) return;
        ItemTypes.OCELOT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemOcelotSpawnEggStack.class)
                .vanillaItem(ItemId.OCELOT_SPAWN_EGG)
                .build();
    }

    public static void initOchreFroglight() {
        if (ItemTypes.OCHRE_FROGLIGHT != null) return;
        ItemTypes.OCHRE_FROGLIGHT = ItemTypeBuilder
                .builder(ItemOchreFroglightStack.class)
                .vanillaItem(ItemId.OCHRE_FROGLIGHT)
                .build();
    }

    public static void initOminousBottle() {
        if (ItemTypes.OMINOUS_BOTTLE != null) return;
        ItemTypes.OMINOUS_BOTTLE = ItemTypeBuilder
                .builder(ItemOminousBottleStack.class)
                .vanillaItem(ItemId.OMINOUS_BOTTLE)
                .build();
    }

    public static void initOminousTrialKey() {
        if (ItemTypes.OMINOUS_TRIAL_KEY != null) return;
        ItemTypes.OMINOUS_TRIAL_KEY = ItemTypeBuilder
                .builder(ItemOminousTrialKeyStack.class)
                .vanillaItem(ItemId.OMINOUS_TRIAL_KEY)
                .build();
    }

    public static void initOrangeCandle() {
        if (ItemTypes.ORANGE_CANDLE != null) return;
        ItemTypes.ORANGE_CANDLE = ItemTypeBuilder
                .builder(ItemOrangeCandleStack.class)
                .vanillaItem(ItemId.ORANGE_CANDLE)
                .build();
    }

    public static void initOrangeCandleCake() {
        if (ItemTypes.ORANGE_CANDLE_CAKE != null) return;
        ItemTypes.ORANGE_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemOrangeCandleCakeStack.class)
                .vanillaItem(ItemId.ORANGE_CANDLE_CAKE)
                .build();
    }

    public static void initOrangeCarpet() {
        if (ItemTypes.ORANGE_CARPET != null) return;
        ItemTypes.ORANGE_CARPET = ItemTypeBuilder
                .builder(ItemOrangeCarpetStack.class)
                .vanillaItem(ItemId.ORANGE_CARPET)
                .build();
    }

    public static void initOrangeConcrete() {
        if (ItemTypes.ORANGE_CONCRETE != null) return;
        ItemTypes.ORANGE_CONCRETE = ItemTypeBuilder
                .builder(ItemOrangeConcreteStack.class)
                .vanillaItem(ItemId.ORANGE_CONCRETE)
                .build();
    }

    public static void initOrangeConcretePowder() {
        if (ItemTypes.ORANGE_CONCRETE_POWDER != null) return;
        ItemTypes.ORANGE_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemOrangeConcretePowderStack.class)
                .vanillaItem(ItemId.ORANGE_CONCRETE_POWDER)
                .build();
    }

    public static void initOrangeDye() {
        if (ItemTypes.ORANGE_DYE != null) return;
        ItemTypes.ORANGE_DYE = ItemTypeBuilder
                .builder(ItemOrangeDyeStack.class)
                .vanillaItem(ItemId.ORANGE_DYE)
                .build();
    }

    public static void initOrangeGlazedTerracotta() {
        if (ItemTypes.ORANGE_GLAZED_TERRACOTTA != null) return;
        ItemTypes.ORANGE_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemOrangeGlazedTerracottaStack.class)
                .vanillaItem(ItemId.ORANGE_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initOrangeShulkerBox() {
        if (ItemTypes.ORANGE_SHULKER_BOX != null) return;
        ItemTypes.ORANGE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemOrangeShulkerBoxStack.class)
                .vanillaItem(ItemId.ORANGE_SHULKER_BOX)
                .build();
    }

    public static void initOrangeStainedGlass() {
        if (ItemTypes.ORANGE_STAINED_GLASS != null) return;
        ItemTypes.ORANGE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemOrangeStainedGlassStack.class)
                .vanillaItem(ItemId.ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initOrangeStainedGlassPane() {
        if (ItemTypes.ORANGE_STAINED_GLASS_PANE != null) return;
        ItemTypes.ORANGE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemOrangeStainedGlassPaneStack.class)
                .vanillaItem(ItemId.ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initOrangeTerracotta() {
        if (ItemTypes.ORANGE_TERRACOTTA != null) return;
        ItemTypes.ORANGE_TERRACOTTA = ItemTypeBuilder
                .builder(ItemOrangeTerracottaStack.class)
                .vanillaItem(ItemId.ORANGE_TERRACOTTA)
                .build();
    }

    public static void initOrangeTulip() {
        if (ItemTypes.ORANGE_TULIP != null) return;
        ItemTypes.ORANGE_TULIP = ItemTypeBuilder
                .builder(ItemOrangeTulipStack.class)
                .vanillaItem(ItemId.ORANGE_TULIP)
                .build();
    }

    public static void initOrangeWool() {
        if (ItemTypes.ORANGE_WOOL != null) return;
        ItemTypes.ORANGE_WOOL = ItemTypeBuilder
                .builder(ItemOrangeWoolStack.class)
                .vanillaItem(ItemId.ORANGE_WOOL)
                .build();
    }

    public static void initOxeyeDaisy() {
        if (ItemTypes.OXEYE_DAISY != null) return;
        ItemTypes.OXEYE_DAISY = ItemTypeBuilder
                .builder(ItemOxeyeDaisyStack.class)
                .vanillaItem(ItemId.OXEYE_DAISY)
                .build();
    }

    public static void initOxidizedChiseledCopper() {
        if (ItemTypes.OXIDIZED_CHISELED_COPPER != null) return;
        ItemTypes.OXIDIZED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemOxidizedChiseledCopperStack.class)
                .vanillaItem(ItemId.OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initOxidizedCopper() {
        if (ItemTypes.OXIDIZED_COPPER != null) return;
        ItemTypes.OXIDIZED_COPPER = ItemTypeBuilder
                .builder(ItemOxidizedCopperStack.class)
                .vanillaItem(ItemId.OXIDIZED_COPPER)
                .build();
    }

    public static void initOxidizedCopperBulb() {
        if (ItemTypes.OXIDIZED_COPPER_BULB != null) return;
        ItemTypes.OXIDIZED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemOxidizedCopperBulbStack.class)
                .vanillaItem(ItemId.OXIDIZED_COPPER_BULB)
                .build();
    }

    public static void initOxidizedCopperDoor() {
        if (ItemTypes.OXIDIZED_COPPER_DOOR != null) return;
        ItemTypes.OXIDIZED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemOxidizedCopperDoorStack.class)
                .vanillaItem(ItemId.OXIDIZED_COPPER_DOOR)
                .build();
    }

    public static void initOxidizedCopperGrate() {
        if (ItemTypes.OXIDIZED_COPPER_GRATE != null) return;
        ItemTypes.OXIDIZED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemOxidizedCopperGrateStack.class)
                .vanillaItem(ItemId.OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initOxidizedCopperTrapdoor() {
        if (ItemTypes.OXIDIZED_COPPER_TRAPDOOR != null) return;
        ItemTypes.OXIDIZED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemOxidizedCopperTrapdoorStack.class)
                .vanillaItem(ItemId.OXIDIZED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initOxidizedCutCopper() {
        if (ItemTypes.OXIDIZED_CUT_COPPER != null) return;
        ItemTypes.OXIDIZED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemOxidizedCutCopperStack.class)
                .vanillaItem(ItemId.OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initOxidizedCutCopperSlab() {
        if (ItemTypes.OXIDIZED_CUT_COPPER_SLAB != null) return;
        ItemTypes.OXIDIZED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemOxidizedCutCopperSlabStack.class)
                .vanillaItem(ItemId.OXIDIZED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initOxidizedCutCopperStairs() {
        if (ItemTypes.OXIDIZED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.OXIDIZED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemOxidizedCutCopperStairsStack.class)
                .vanillaItem(ItemId.OXIDIZED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initOxidizedDoubleCutCopperSlab() {
        if (ItemTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemOxidizedDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initPackedIce() {
        if (ItemTypes.PACKED_ICE != null) return;
        ItemTypes.PACKED_ICE = ItemTypeBuilder
                .builder(ItemPackedIceStack.class)
                .vanillaItem(ItemId.PACKED_ICE)
                .build();
    }

    public static void initPackedMud() {
        if (ItemTypes.PACKED_MUD != null) return;
        ItemTypes.PACKED_MUD = ItemTypeBuilder
                .builder(ItemPackedMudStack.class)
                .vanillaItem(ItemId.PACKED_MUD)
                .build();
    }

    public static void initPainting() {
        if (ItemTypes.PAINTING != null) return;
        ItemTypes.PAINTING = ItemTypeBuilder
                .builder(ItemPaintingStack.class)
                .vanillaItem(ItemId.PAINTING)
                .build();
    }

    public static void initPandaSpawnEgg() {
        if (ItemTypes.PANDA_SPAWN_EGG != null) return;
        ItemTypes.PANDA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPandaSpawnEggStack.class)
                .vanillaItem(ItemId.PANDA_SPAWN_EGG)
                .build();
    }

    public static void initPaper() {
        if (ItemTypes.PAPER != null) return;
        ItemTypes.PAPER = ItemTypeBuilder
                .builder(ItemPaperStack.class)
                .vanillaItem(ItemId.PAPER)
                .build();
    }

    public static void initParrotSpawnEgg() {
        if (ItemTypes.PARROT_SPAWN_EGG != null) return;
        ItemTypes.PARROT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemParrotSpawnEggStack.class)
                .vanillaItem(ItemId.PARROT_SPAWN_EGG)
                .build();
    }

    public static void initPearlescentFroglight() {
        if (ItemTypes.PEARLESCENT_FROGLIGHT != null) return;
        ItemTypes.PEARLESCENT_FROGLIGHT = ItemTypeBuilder
                .builder(ItemPearlescentFroglightStack.class)
                .vanillaItem(ItemId.PEARLESCENT_FROGLIGHT)
                .build();
    }

    public static void initPeony() {
        if (ItemTypes.PEONY != null) return;
        ItemTypes.PEONY = ItemTypeBuilder
                .builder(ItemPeonyStack.class)
                .vanillaItem(ItemId.PEONY)
                .build();
    }

    public static void initPetrifiedOakDoubleSlab() {
        if (ItemTypes.PETRIFIED_OAK_DOUBLE_SLAB != null) return;
        ItemTypes.PETRIFIED_OAK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPetrifiedOakDoubleSlabStack.class)
                .vanillaItem(ItemId.PETRIFIED_OAK_DOUBLE_SLAB)
                .build();
    }

    public static void initPetrifiedOakSlab() {
        if (ItemTypes.PETRIFIED_OAK_SLAB != null) return;
        ItemTypes.PETRIFIED_OAK_SLAB = ItemTypeBuilder
                .builder(ItemPetrifiedOakSlabStack.class)
                .vanillaItem(ItemId.PETRIFIED_OAK_SLAB)
                .build();
    }

    public static void initPhantomMembrane() {
        if (ItemTypes.PHANTOM_MEMBRANE != null) return;
        ItemTypes.PHANTOM_MEMBRANE = ItemTypeBuilder
                .builder(ItemPhantomMembraneStack.class)
                .vanillaItem(ItemId.PHANTOM_MEMBRANE)
                .build();
    }

    public static void initPhantomSpawnEgg() {
        if (ItemTypes.PHANTOM_SPAWN_EGG != null) return;
        ItemTypes.PHANTOM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPhantomSpawnEggStack.class)
                .vanillaItem(ItemId.PHANTOM_SPAWN_EGG)
                .build();
    }

    public static void initPigSpawnEgg() {
        if (ItemTypes.PIG_SPAWN_EGG != null) return;
        ItemTypes.PIG_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPigSpawnEggStack.class)
                .vanillaItem(ItemId.PIG_SPAWN_EGG)
                .build();
    }

    public static void initPiglinBannerPattern() {
        if (ItemTypes.PIGLIN_BANNER_PATTERN != null) return;
        ItemTypes.PIGLIN_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemPiglinBannerPatternStack.class)
                .vanillaItem(ItemId.PIGLIN_BANNER_PATTERN)
                .build();
    }

    public static void initPiglinBruteSpawnEgg() {
        if (ItemTypes.PIGLIN_BRUTE_SPAWN_EGG != null) return;
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPiglinBruteSpawnEggStack.class)
                .vanillaItem(ItemId.PIGLIN_BRUTE_SPAWN_EGG)
                .build();
    }

    public static void initPiglinSpawnEgg() {
        if (ItemTypes.PIGLIN_SPAWN_EGG != null) return;
        ItemTypes.PIGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPiglinSpawnEggStack.class)
                .vanillaItem(ItemId.PIGLIN_SPAWN_EGG)
                .build();
    }

    public static void initPillagerSpawnEgg() {
        if (ItemTypes.PILLAGER_SPAWN_EGG != null) return;
        ItemTypes.PILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPillagerSpawnEggStack.class)
                .vanillaItem(ItemId.PILLAGER_SPAWN_EGG)
                .build();
    }

    public static void initPinkCandle() {
        if (ItemTypes.PINK_CANDLE != null) return;
        ItemTypes.PINK_CANDLE = ItemTypeBuilder
                .builder(ItemPinkCandleStack.class)
                .vanillaItem(ItemId.PINK_CANDLE)
                .build();
    }

    public static void initPinkCandleCake() {
        if (ItemTypes.PINK_CANDLE_CAKE != null) return;
        ItemTypes.PINK_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemPinkCandleCakeStack.class)
                .vanillaItem(ItemId.PINK_CANDLE_CAKE)
                .build();
    }

    public static void initPinkCarpet() {
        if (ItemTypes.PINK_CARPET != null) return;
        ItemTypes.PINK_CARPET = ItemTypeBuilder
                .builder(ItemPinkCarpetStack.class)
                .vanillaItem(ItemId.PINK_CARPET)
                .build();
    }

    public static void initPinkConcrete() {
        if (ItemTypes.PINK_CONCRETE != null) return;
        ItemTypes.PINK_CONCRETE = ItemTypeBuilder
                .builder(ItemPinkConcreteStack.class)
                .vanillaItem(ItemId.PINK_CONCRETE)
                .build();
    }

    public static void initPinkConcretePowder() {
        if (ItemTypes.PINK_CONCRETE_POWDER != null) return;
        ItemTypes.PINK_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemPinkConcretePowderStack.class)
                .vanillaItem(ItemId.PINK_CONCRETE_POWDER)
                .build();
    }

    public static void initPinkDye() {
        if (ItemTypes.PINK_DYE != null) return;
        ItemTypes.PINK_DYE = ItemTypeBuilder
                .builder(ItemPinkDyeStack.class)
                .vanillaItem(ItemId.PINK_DYE)
                .build();
    }

    public static void initPinkGlazedTerracotta() {
        if (ItemTypes.PINK_GLAZED_TERRACOTTA != null) return;
        ItemTypes.PINK_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemPinkGlazedTerracottaStack.class)
                .vanillaItem(ItemId.PINK_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initPinkPetals() {
        if (ItemTypes.PINK_PETALS != null) return;
        ItemTypes.PINK_PETALS = ItemTypeBuilder
                .builder(ItemPinkPetalsStack.class)
                .vanillaItem(ItemId.PINK_PETALS)
                .build();
    }

    public static void initPinkShulkerBox() {
        if (ItemTypes.PINK_SHULKER_BOX != null) return;
        ItemTypes.PINK_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemPinkShulkerBoxStack.class)
                .vanillaItem(ItemId.PINK_SHULKER_BOX)
                .build();
    }

    public static void initPinkStainedGlass() {
        if (ItemTypes.PINK_STAINED_GLASS != null) return;
        ItemTypes.PINK_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemPinkStainedGlassStack.class)
                .vanillaItem(ItemId.PINK_STAINED_GLASS)
                .build();
    }

    public static void initPinkStainedGlassPane() {
        if (ItemTypes.PINK_STAINED_GLASS_PANE != null) return;
        ItemTypes.PINK_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemPinkStainedGlassPaneStack.class)
                .vanillaItem(ItemId.PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPinkTerracotta() {
        if (ItemTypes.PINK_TERRACOTTA != null) return;
        ItemTypes.PINK_TERRACOTTA = ItemTypeBuilder
                .builder(ItemPinkTerracottaStack.class)
                .vanillaItem(ItemId.PINK_TERRACOTTA)
                .build();
    }

    public static void initPinkTulip() {
        if (ItemTypes.PINK_TULIP != null) return;
        ItemTypes.PINK_TULIP = ItemTypeBuilder
                .builder(ItemPinkTulipStack.class)
                .vanillaItem(ItemId.PINK_TULIP)
                .build();
    }

    public static void initPinkWool() {
        if (ItemTypes.PINK_WOOL != null) return;
        ItemTypes.PINK_WOOL = ItemTypeBuilder
                .builder(ItemPinkWoolStack.class)
                .vanillaItem(ItemId.PINK_WOOL)
                .build();
    }

    public static void initPiston() {
        if (ItemTypes.PISTON != null) return;
        ItemTypes.PISTON = ItemTypeBuilder
                .builder(ItemPistonStack.class)
                .vanillaItem(ItemId.PISTON)
                .build();
    }

    public static void initPistonArmCollision() {
        if (ItemTypes.PISTON_ARM_COLLISION != null) return;
        ItemTypes.PISTON_ARM_COLLISION = ItemTypeBuilder
                .builder(ItemPistonArmCollisionStack.class)
                .vanillaItem(ItemId.PISTON_ARM_COLLISION)
                .build();
    }

    public static void initPitcherCrop() {
        if (ItemTypes.PITCHER_CROP != null) return;
        ItemTypes.PITCHER_CROP = ItemTypeBuilder
                .builder(ItemPitcherCropStack.class)
                .vanillaItem(ItemId.PITCHER_CROP)
                .build();
    }

    public static void initPitcherPlant() {
        if (ItemTypes.PITCHER_PLANT != null) return;
        ItemTypes.PITCHER_PLANT = ItemTypeBuilder
                .builder(ItemPitcherPlantStack.class)
                .vanillaItem(ItemId.PITCHER_PLANT)
                .build();
    }

    public static void initPitcherPod() {
        if (ItemTypes.PITCHER_POD != null) return;
        ItemTypes.PITCHER_POD = ItemTypeBuilder
                .builder(ItemPitcherPodStack.class)
                .vanillaItem(ItemId.PITCHER_POD)
                .build();
    }

    public static void initPlanks() {
        if (ItemTypes.PLANKS != null) return;
        ItemTypes.PLANKS = ItemTypeBuilder
                .builder(ItemPlanksStack.class)
                .vanillaItem(ItemId.PLANKS)
                .build();
    }

    public static void initPlentyPotterySherd() {
        if (ItemTypes.PLENTY_POTTERY_SHERD != null) return;
        ItemTypes.PLENTY_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemPlentyPotterySherdStack.class)
                .vanillaItem(ItemId.PLENTY_POTTERY_SHERD)
                .build();
    }

    public static void initPodzol() {
        if (ItemTypes.PODZOL != null) return;
        ItemTypes.PODZOL = ItemTypeBuilder
                .builder(ItemPodzolStack.class)
                .vanillaItem(ItemId.PODZOL)
                .build();
    }

    public static void initPointedDripstone() {
        if (ItemTypes.POINTED_DRIPSTONE != null) return;
        ItemTypes.POINTED_DRIPSTONE = ItemTypeBuilder
                .builder(ItemPointedDripstoneStack.class)
                .vanillaItem(ItemId.POINTED_DRIPSTONE)
                .build();
    }

    public static void initPoisonousPotato() {
        if (ItemTypes.POISONOUS_POTATO != null) return;
        ItemTypes.POISONOUS_POTATO = ItemTypeBuilder
                .builder(ItemPoisonousPotatoStack.class)
                .vanillaItem(ItemId.POISONOUS_POTATO)
                .build();
    }

    public static void initPolarBearSpawnEgg() {
        if (ItemTypes.POLAR_BEAR_SPAWN_EGG != null) return;
        ItemTypes.POLAR_BEAR_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPolarBearSpawnEggStack.class)
                .vanillaItem(ItemId.POLAR_BEAR_SPAWN_EGG)
                .build();
    }

    public static void initPolishedAndesite() {
        if (ItemTypes.POLISHED_ANDESITE != null) return;
        ItemTypes.POLISHED_ANDESITE = ItemTypeBuilder
                .builder(ItemPolishedAndesiteStack.class)
                .vanillaItem(ItemId.POLISHED_ANDESITE)
                .build();
    }

    public static void initPolishedAndesiteDoubleSlab() {
        if (ItemTypes.POLISHED_ANDESITE_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_ANDESITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedAndesiteDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_ANDESITE_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedAndesiteSlab() {
        if (ItemTypes.POLISHED_ANDESITE_SLAB != null) return;
        ItemTypes.POLISHED_ANDESITE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedAndesiteSlabStack.class)
                .vanillaItem(ItemId.POLISHED_ANDESITE_SLAB)
                .build();
    }

    public static void initPolishedAndesiteStairs() {
        if (ItemTypes.POLISHED_ANDESITE_STAIRS != null) return;
        ItemTypes.POLISHED_ANDESITE_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedAndesiteStairsStack.class)
                .vanillaItem(ItemId.POLISHED_ANDESITE_STAIRS)
                .build();
    }

    public static void initPolishedBasalt() {
        if (ItemTypes.POLISHED_BASALT != null) return;
        ItemTypes.POLISHED_BASALT = ItemTypeBuilder
                .builder(ItemPolishedBasaltStack.class)
                .vanillaItem(ItemId.POLISHED_BASALT)
                .build();
    }

    public static void initPolishedBlackstone() {
        if (ItemTypes.POLISHED_BLACKSTONE != null) return;
        ItemTypes.POLISHED_BLACKSTONE = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE)
                .build();
    }

    public static void initPolishedBlackstoneBrickDoubleSlab() {
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedBlackstoneBrickSlab() {
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_SLAB != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneBrickSlabStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_SLAB)
                .build();
    }

    public static void initPolishedBlackstoneBrickStairs() {
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_STAIRS != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneBrickStairsStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .build();
    }

    public static void initPolishedBlackstoneBrickWall() {
        if (ItemTypes.POLISHED_BLACKSTONE_BRICK_WALL != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BRICK_WALL = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneBrickWallStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICK_WALL)
                .build();
    }

    public static void initPolishedBlackstoneBricks() {
        if (ItemTypes.POLISHED_BLACKSTONE_BRICKS != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BRICKS = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneBricksStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initPolishedBlackstoneButton() {
        if (ItemTypes.POLISHED_BLACKSTONE_BUTTON != null) return;
        ItemTypes.POLISHED_BLACKSTONE_BUTTON = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneButtonStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_BUTTON)
                .build();
    }

    public static void initPolishedBlackstoneDoubleSlab() {
        if (ItemTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedBlackstonePressurePlate() {
        if (ItemTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE != null) return;
        ItemTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemPolishedBlackstonePressurePlateStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                .build();
    }

    public static void initPolishedBlackstoneSlab() {
        if (ItemTypes.POLISHED_BLACKSTONE_SLAB != null) return;
        ItemTypes.POLISHED_BLACKSTONE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneSlabStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_SLAB)
                .build();
    }

    public static void initPolishedBlackstoneStairs() {
        if (ItemTypes.POLISHED_BLACKSTONE_STAIRS != null) return;
        ItemTypes.POLISHED_BLACKSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneStairsStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_STAIRS)
                .build();
    }

    public static void initPolishedBlackstoneWall() {
        if (ItemTypes.POLISHED_BLACKSTONE_WALL != null) return;
        ItemTypes.POLISHED_BLACKSTONE_WALL = ItemTypeBuilder
                .builder(ItemPolishedBlackstoneWallStack.class)
                .vanillaItem(ItemId.POLISHED_BLACKSTONE_WALL)
                .build();
    }

    public static void initPolishedDeepslate() {
        if (ItemTypes.POLISHED_DEEPSLATE != null) return;
        ItemTypes.POLISHED_DEEPSLATE = ItemTypeBuilder
                .builder(ItemPolishedDeepslateStack.class)
                .vanillaItem(ItemId.POLISHED_DEEPSLATE)
                .build();
    }

    public static void initPolishedDeepslateDoubleSlab() {
        if (ItemTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedDeepslateDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedDeepslateSlab() {
        if (ItemTypes.POLISHED_DEEPSLATE_SLAB != null) return;
        ItemTypes.POLISHED_DEEPSLATE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedDeepslateSlabStack.class)
                .vanillaItem(ItemId.POLISHED_DEEPSLATE_SLAB)
                .build();
    }

    public static void initPolishedDeepslateStairs() {
        if (ItemTypes.POLISHED_DEEPSLATE_STAIRS != null) return;
        ItemTypes.POLISHED_DEEPSLATE_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedDeepslateStairsStack.class)
                .vanillaItem(ItemId.POLISHED_DEEPSLATE_STAIRS)
                .build();
    }

    public static void initPolishedDeepslateWall() {
        if (ItemTypes.POLISHED_DEEPSLATE_WALL != null) return;
        ItemTypes.POLISHED_DEEPSLATE_WALL = ItemTypeBuilder
                .builder(ItemPolishedDeepslateWallStack.class)
                .vanillaItem(ItemId.POLISHED_DEEPSLATE_WALL)
                .build();
    }

    public static void initPolishedDiorite() {
        if (ItemTypes.POLISHED_DIORITE != null) return;
        ItemTypes.POLISHED_DIORITE = ItemTypeBuilder
                .builder(ItemPolishedDioriteStack.class)
                .vanillaItem(ItemId.POLISHED_DIORITE)
                .build();
    }

    public static void initPolishedDioriteDoubleSlab() {
        if (ItemTypes.POLISHED_DIORITE_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_DIORITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedDioriteDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_DIORITE_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedDioriteSlab() {
        if (ItemTypes.POLISHED_DIORITE_SLAB != null) return;
        ItemTypes.POLISHED_DIORITE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedDioriteSlabStack.class)
                .vanillaItem(ItemId.POLISHED_DIORITE_SLAB)
                .build();
    }

    public static void initPolishedDioriteStairs() {
        if (ItemTypes.POLISHED_DIORITE_STAIRS != null) return;
        ItemTypes.POLISHED_DIORITE_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedDioriteStairsStack.class)
                .vanillaItem(ItemId.POLISHED_DIORITE_STAIRS)
                .build();
    }

    public static void initPolishedGranite() {
        if (ItemTypes.POLISHED_GRANITE != null) return;
        ItemTypes.POLISHED_GRANITE = ItemTypeBuilder
                .builder(ItemPolishedGraniteStack.class)
                .vanillaItem(ItemId.POLISHED_GRANITE)
                .build();
    }

    public static void initPolishedGraniteDoubleSlab() {
        if (ItemTypes.POLISHED_GRANITE_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_GRANITE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedGraniteDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_GRANITE_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedGraniteSlab() {
        if (ItemTypes.POLISHED_GRANITE_SLAB != null) return;
        ItemTypes.POLISHED_GRANITE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedGraniteSlabStack.class)
                .vanillaItem(ItemId.POLISHED_GRANITE_SLAB)
                .build();
    }

    public static void initPolishedGraniteStairs() {
        if (ItemTypes.POLISHED_GRANITE_STAIRS != null) return;
        ItemTypes.POLISHED_GRANITE_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedGraniteStairsStack.class)
                .vanillaItem(ItemId.POLISHED_GRANITE_STAIRS)
                .build();
    }

    public static void initPolishedTuff() {
        if (ItemTypes.POLISHED_TUFF != null) return;
        ItemTypes.POLISHED_TUFF = ItemTypeBuilder
                .builder(ItemPolishedTuffStack.class)
                .vanillaItem(ItemId.POLISHED_TUFF)
                .build();
    }

    public static void initPolishedTuffDoubleSlab() {
        if (ItemTypes.POLISHED_TUFF_DOUBLE_SLAB != null) return;
        ItemTypes.POLISHED_TUFF_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPolishedTuffDoubleSlabStack.class)
                .vanillaItem(ItemId.POLISHED_TUFF_DOUBLE_SLAB)
                .build();
    }

    public static void initPolishedTuffSlab() {
        if (ItemTypes.POLISHED_TUFF_SLAB != null) return;
        ItemTypes.POLISHED_TUFF_SLAB = ItemTypeBuilder
                .builder(ItemPolishedTuffSlabStack.class)
                .vanillaItem(ItemId.POLISHED_TUFF_SLAB)
                .build();
    }

    public static void initPolishedTuffStairs() {
        if (ItemTypes.POLISHED_TUFF_STAIRS != null) return;
        ItemTypes.POLISHED_TUFF_STAIRS = ItemTypeBuilder
                .builder(ItemPolishedTuffStairsStack.class)
                .vanillaItem(ItemId.POLISHED_TUFF_STAIRS)
                .build();
    }

    public static void initPolishedTuffWall() {
        if (ItemTypes.POLISHED_TUFF_WALL != null) return;
        ItemTypes.POLISHED_TUFF_WALL = ItemTypeBuilder
                .builder(ItemPolishedTuffWallStack.class)
                .vanillaItem(ItemId.POLISHED_TUFF_WALL)
                .build();
    }

    public static void initPoppedChorusFruit() {
        if (ItemTypes.POPPED_CHORUS_FRUIT != null) return;
        ItemTypes.POPPED_CHORUS_FRUIT = ItemTypeBuilder
                .builder(ItemPoppedChorusFruitStack.class)
                .vanillaItem(ItemId.POPPED_CHORUS_FRUIT)
                .build();
    }

    public static void initPoppy() {
        if (ItemTypes.POPPY != null) return;
        ItemTypes.POPPY = ItemTypeBuilder
                .builder(ItemPoppyStack.class)
                .vanillaItem(ItemId.POPPY)
                .build();
    }

    public static void initPorkchop() {
        if (ItemTypes.PORKCHOP != null) return;
        ItemTypes.PORKCHOP = ItemTypeBuilder
                .builder(ItemPorkchopStack.class)
                .vanillaItem(ItemId.PORKCHOP)
                .build();
    }

    public static void initPortal() {
        if (ItemTypes.PORTAL != null) return;
        ItemTypes.PORTAL = ItemTypeBuilder
                .builder(ItemPortalStack.class)
                .vanillaItem(ItemId.PORTAL)
                .build();
    }

    public static void initPotato() {
        if (ItemTypes.POTATO != null) return;
        ItemTypes.POTATO = ItemTypeBuilder
                .builder(ItemPotatoStack.class)
                .vanillaItem(ItemId.POTATO)
                .build();
    }

    public static void initPotatoes() {
        if (ItemTypes.POTATOES != null) return;
        ItemTypes.POTATOES = ItemTypeBuilder
                .builder(ItemPotatoesStack.class)
                .vanillaItem(ItemId.POTATOES)
                .build();
    }

    public static void initPotion() {
        if (ItemTypes.POTION != null) return;
        ItemTypes.POTION = ItemTypeBuilder
                .builder(ItemPotionStack.class)
                .vanillaItem(ItemId.POTION)
                .build();
    }

    public static void initPowderSnow() {
        if (ItemTypes.POWDER_SNOW != null) return;
        ItemTypes.POWDER_SNOW = ItemTypeBuilder
                .builder(ItemPowderSnowStack.class)
                .vanillaItem(ItemId.POWDER_SNOW)
                .build();
    }

    public static void initPowderSnowBucket() {
        if (ItemTypes.POWDER_SNOW_BUCKET != null) return;
        ItemTypes.POWDER_SNOW_BUCKET = ItemTypeBuilder
                .builder(ItemPowderSnowBucketStack.class)
                .vanillaItem(ItemId.POWDER_SNOW_BUCKET)
                .build();
    }

    public static void initPoweredComparator() {
        if (ItemTypes.POWERED_COMPARATOR != null) return;
        ItemTypes.POWERED_COMPARATOR = ItemTypeBuilder
                .builder(ItemPoweredComparatorStack.class)
                .vanillaItem(ItemId.POWERED_COMPARATOR)
                .build();
    }

    public static void initPoweredRepeater() {
        if (ItemTypes.POWERED_REPEATER != null) return;
        ItemTypes.POWERED_REPEATER = ItemTypeBuilder
                .builder(ItemPoweredRepeaterStack.class)
                .vanillaItem(ItemId.POWERED_REPEATER)
                .build();
    }

    public static void initPrismarine() {
        if (ItemTypes.PRISMARINE != null) return;
        ItemTypes.PRISMARINE = ItemTypeBuilder
                .builder(ItemPrismarineStack.class)
                .vanillaItem(ItemId.PRISMARINE)
                .build();
    }

    public static void initPrismarineBrickDoubleSlab() {
        if (ItemTypes.PRISMARINE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.PRISMARINE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPrismarineBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.PRISMARINE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initPrismarineBrickSlab() {
        if (ItemTypes.PRISMARINE_BRICK_SLAB != null) return;
        ItemTypes.PRISMARINE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemPrismarineBrickSlabStack.class)
                .vanillaItem(ItemId.PRISMARINE_BRICK_SLAB)
                .build();
    }

    public static void initPrismarineBricks() {
        if (ItemTypes.PRISMARINE_BRICKS != null) return;
        ItemTypes.PRISMARINE_BRICKS = ItemTypeBuilder
                .builder(ItemPrismarineBricksStack.class)
                .vanillaItem(ItemId.PRISMARINE_BRICKS)
                .build();
    }

    public static void initPrismarineBricksStairs() {
        if (ItemTypes.PRISMARINE_BRICKS_STAIRS != null) return;
        ItemTypes.PRISMARINE_BRICKS_STAIRS = ItemTypeBuilder
                .builder(ItemPrismarineBricksStairsStack.class)
                .vanillaItem(ItemId.PRISMARINE_BRICKS_STAIRS)
                .build();
    }

    public static void initPrismarineCrystals() {
        if (ItemTypes.PRISMARINE_CRYSTALS != null) return;
        ItemTypes.PRISMARINE_CRYSTALS = ItemTypeBuilder
                .builder(ItemPrismarineCrystalsStack.class)
                .vanillaItem(ItemId.PRISMARINE_CRYSTALS)
                .build();
    }

    public static void initPrismarineDoubleSlab() {
        if (ItemTypes.PRISMARINE_DOUBLE_SLAB != null) return;
        ItemTypes.PRISMARINE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPrismarineDoubleSlabStack.class)
                .vanillaItem(ItemId.PRISMARINE_DOUBLE_SLAB)
                .build();
    }

    public static void initPrismarineShard() {
        if (ItemTypes.PRISMARINE_SHARD != null) return;
        ItemTypes.PRISMARINE_SHARD = ItemTypeBuilder
                .builder(ItemPrismarineShardStack.class)
                .vanillaItem(ItemId.PRISMARINE_SHARD)
                .build();
    }

    public static void initPrismarineSlab() {
        if (ItemTypes.PRISMARINE_SLAB != null) return;
        ItemTypes.PRISMARINE_SLAB = ItemTypeBuilder
                .builder(ItemPrismarineSlabStack.class)
                .vanillaItem(ItemId.PRISMARINE_SLAB)
                .build();
    }

    public static void initPrismarineStairs() {
        if (ItemTypes.PRISMARINE_STAIRS != null) return;
        ItemTypes.PRISMARINE_STAIRS = ItemTypeBuilder
                .builder(ItemPrismarineStairsStack.class)
                .vanillaItem(ItemId.PRISMARINE_STAIRS)
                .build();
    }

    public static void initPrizePotterySherd() {
        if (ItemTypes.PRIZE_POTTERY_SHERD != null) return;
        ItemTypes.PRIZE_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemPrizePotterySherdStack.class)
                .vanillaItem(ItemId.PRIZE_POTTERY_SHERD)
                .build();
    }

    public static void initPufferfish() {
        if (ItemTypes.PUFFERFISH != null) return;
        ItemTypes.PUFFERFISH = ItemTypeBuilder
                .builder(ItemPufferfishStack.class)
                .vanillaItem(ItemId.PUFFERFISH)
                .build();
    }

    public static void initPufferfishBucket() {
        if (ItemTypes.PUFFERFISH_BUCKET != null) return;
        ItemTypes.PUFFERFISH_BUCKET = ItemTypeBuilder
                .builder(ItemPufferfishBucketStack.class)
                .vanillaItem(ItemId.PUFFERFISH_BUCKET)
                .build();
    }

    public static void initPufferfishSpawnEgg() {
        if (ItemTypes.PUFFERFISH_SPAWN_EGG != null) return;
        ItemTypes.PUFFERFISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPufferfishSpawnEggStack.class)
                .vanillaItem(ItemId.PUFFERFISH_SPAWN_EGG)
                .build();
    }

    public static void initPumpkin() {
        if (ItemTypes.PUMPKIN != null) return;
        ItemTypes.PUMPKIN = ItemTypeBuilder
                .builder(ItemPumpkinStack.class)
                .vanillaItem(ItemId.PUMPKIN)
                .build();
    }

    public static void initPumpkinPie() {
        if (ItemTypes.PUMPKIN_PIE != null) return;
        ItemTypes.PUMPKIN_PIE = ItemTypeBuilder
                .builder(ItemPumpkinPieStack.class)
                .vanillaItem(ItemId.PUMPKIN_PIE)
                .build();
    }

    public static void initPumpkinSeeds() {
        if (ItemTypes.PUMPKIN_SEEDS != null) return;
        ItemTypes.PUMPKIN_SEEDS = ItemTypeBuilder
                .builder(ItemPumpkinSeedsStack.class)
                .vanillaItem(ItemId.PUMPKIN_SEEDS)
                .build();
    }

    public static void initPumpkinStem() {
        if (ItemTypes.PUMPKIN_STEM != null) return;
        ItemTypes.PUMPKIN_STEM = ItemTypeBuilder
                .builder(ItemPumpkinStemStack.class)
                .vanillaItem(ItemId.PUMPKIN_STEM)
                .build();
    }

    public static void initPurpleCandle() {
        if (ItemTypes.PURPLE_CANDLE != null) return;
        ItemTypes.PURPLE_CANDLE = ItemTypeBuilder
                .builder(ItemPurpleCandleStack.class)
                .vanillaItem(ItemId.PURPLE_CANDLE)
                .build();
    }

    public static void initPurpleCandleCake() {
        if (ItemTypes.PURPLE_CANDLE_CAKE != null) return;
        ItemTypes.PURPLE_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemPurpleCandleCakeStack.class)
                .vanillaItem(ItemId.PURPLE_CANDLE_CAKE)
                .build();
    }

    public static void initPurpleCarpet() {
        if (ItemTypes.PURPLE_CARPET != null) return;
        ItemTypes.PURPLE_CARPET = ItemTypeBuilder
                .builder(ItemPurpleCarpetStack.class)
                .vanillaItem(ItemId.PURPLE_CARPET)
                .build();
    }

    public static void initPurpleConcrete() {
        if (ItemTypes.PURPLE_CONCRETE != null) return;
        ItemTypes.PURPLE_CONCRETE = ItemTypeBuilder
                .builder(ItemPurpleConcreteStack.class)
                .vanillaItem(ItemId.PURPLE_CONCRETE)
                .build();
    }

    public static void initPurpleConcretePowder() {
        if (ItemTypes.PURPLE_CONCRETE_POWDER != null) return;
        ItemTypes.PURPLE_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemPurpleConcretePowderStack.class)
                .vanillaItem(ItemId.PURPLE_CONCRETE_POWDER)
                .build();
    }

    public static void initPurpleDye() {
        if (ItemTypes.PURPLE_DYE != null) return;
        ItemTypes.PURPLE_DYE = ItemTypeBuilder
                .builder(ItemPurpleDyeStack.class)
                .vanillaItem(ItemId.PURPLE_DYE)
                .build();
    }

    public static void initPurpleGlazedTerracotta() {
        if (ItemTypes.PURPLE_GLAZED_TERRACOTTA != null) return;
        ItemTypes.PURPLE_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemPurpleGlazedTerracottaStack.class)
                .vanillaItem(ItemId.PURPLE_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initPurpleShulkerBox() {
        if (ItemTypes.PURPLE_SHULKER_BOX != null) return;
        ItemTypes.PURPLE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemPurpleShulkerBoxStack.class)
                .vanillaItem(ItemId.PURPLE_SHULKER_BOX)
                .build();
    }

    public static void initPurpleStainedGlass() {
        if (ItemTypes.PURPLE_STAINED_GLASS != null) return;
        ItemTypes.PURPLE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemPurpleStainedGlassStack.class)
                .vanillaItem(ItemId.PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initPurpleStainedGlassPane() {
        if (ItemTypes.PURPLE_STAINED_GLASS_PANE != null) return;
        ItemTypes.PURPLE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemPurpleStainedGlassPaneStack.class)
                .vanillaItem(ItemId.PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPurpleTerracotta() {
        if (ItemTypes.PURPLE_TERRACOTTA != null) return;
        ItemTypes.PURPLE_TERRACOTTA = ItemTypeBuilder
                .builder(ItemPurpleTerracottaStack.class)
                .vanillaItem(ItemId.PURPLE_TERRACOTTA)
                .build();
    }

    public static void initPurpleWool() {
        if (ItemTypes.PURPLE_WOOL != null) return;
        ItemTypes.PURPLE_WOOL = ItemTypeBuilder
                .builder(ItemPurpleWoolStack.class)
                .vanillaItem(ItemId.PURPLE_WOOL)
                .build();
    }

    public static void initPurpurBlock() {
        if (ItemTypes.PURPUR_BLOCK != null) return;
        ItemTypes.PURPUR_BLOCK = ItemTypeBuilder
                .builder(ItemPurpurBlockStack.class)
                .vanillaItem(ItemId.PURPUR_BLOCK)
                .build();
    }

    public static void initPurpurDoubleSlab() {
        if (ItemTypes.PURPUR_DOUBLE_SLAB != null) return;
        ItemTypes.PURPUR_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemPurpurDoubleSlabStack.class)
                .vanillaItem(ItemId.PURPUR_DOUBLE_SLAB)
                .build();
    }

    public static void initPurpurSlab() {
        if (ItemTypes.PURPUR_SLAB != null) return;
        ItemTypes.PURPUR_SLAB = ItemTypeBuilder
                .builder(ItemPurpurSlabStack.class)
                .vanillaItem(ItemId.PURPUR_SLAB)
                .build();
    }

    public static void initPurpurStairs() {
        if (ItemTypes.PURPUR_STAIRS != null) return;
        ItemTypes.PURPUR_STAIRS = ItemTypeBuilder
                .builder(ItemPurpurStairsStack.class)
                .vanillaItem(ItemId.PURPUR_STAIRS)
                .build();
    }

    public static void initQuartz() {
        if (ItemTypes.QUARTZ != null) return;
        ItemTypes.QUARTZ = ItemTypeBuilder
                .builder(ItemQuartzStack.class)
                .vanillaItem(ItemId.QUARTZ)
                .build();
    }

    public static void initQuartzBlock() {
        if (ItemTypes.QUARTZ_BLOCK != null) return;
        ItemTypes.QUARTZ_BLOCK = ItemTypeBuilder
                .builder(ItemQuartzBlockStack.class)
                .vanillaItem(ItemId.QUARTZ_BLOCK)
                .build();
    }

    public static void initQuartzBricks() {
        if (ItemTypes.QUARTZ_BRICKS != null) return;
        ItemTypes.QUARTZ_BRICKS = ItemTypeBuilder
                .builder(ItemQuartzBricksStack.class)
                .vanillaItem(ItemId.QUARTZ_BRICKS)
                .build();
    }

    public static void initQuartzDoubleSlab() {
        if (ItemTypes.QUARTZ_DOUBLE_SLAB != null) return;
        ItemTypes.QUARTZ_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemQuartzDoubleSlabStack.class)
                .vanillaItem(ItemId.QUARTZ_DOUBLE_SLAB)
                .build();
    }

    public static void initQuartzOre() {
        if (ItemTypes.QUARTZ_ORE != null) return;
        ItemTypes.QUARTZ_ORE = ItemTypeBuilder
                .builder(ItemQuartzOreStack.class)
                .vanillaItem(ItemId.QUARTZ_ORE)
                .build();
    }

    public static void initQuartzPillar() {
        if (ItemTypes.QUARTZ_PILLAR != null) return;
        ItemTypes.QUARTZ_PILLAR = ItemTypeBuilder
                .builder(ItemQuartzPillarStack.class)
                .vanillaItem(ItemId.QUARTZ_PILLAR)
                .build();
    }

    public static void initQuartzSlab() {
        if (ItemTypes.QUARTZ_SLAB != null) return;
        ItemTypes.QUARTZ_SLAB = ItemTypeBuilder
                .builder(ItemQuartzSlabStack.class)
                .vanillaItem(ItemId.QUARTZ_SLAB)
                .build();
    }

    public static void initQuartzStairs() {
        if (ItemTypes.QUARTZ_STAIRS != null) return;
        ItemTypes.QUARTZ_STAIRS = ItemTypeBuilder
                .builder(ItemQuartzStairsStack.class)
                .vanillaItem(ItemId.QUARTZ_STAIRS)
                .build();
    }

    public static void initRabbit() {
        if (ItemTypes.RABBIT != null) return;
        ItemTypes.RABBIT = ItemTypeBuilder
                .builder(ItemRabbitStack.class)
                .vanillaItem(ItemId.RABBIT)
                .build();
    }

    public static void initRabbitFoot() {
        if (ItemTypes.RABBIT_FOOT != null) return;
        ItemTypes.RABBIT_FOOT = ItemTypeBuilder
                .builder(ItemRabbitFootStack.class)
                .vanillaItem(ItemId.RABBIT_FOOT)
                .build();
    }

    public static void initRabbitHide() {
        if (ItemTypes.RABBIT_HIDE != null) return;
        ItemTypes.RABBIT_HIDE = ItemTypeBuilder
                .builder(ItemRabbitHideStack.class)
                .vanillaItem(ItemId.RABBIT_HIDE)
                .build();
    }

    public static void initRabbitSpawnEgg() {
        if (ItemTypes.RABBIT_SPAWN_EGG != null) return;
        ItemTypes.RABBIT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemRabbitSpawnEggStack.class)
                .vanillaItem(ItemId.RABBIT_SPAWN_EGG)
                .build();
    }

    public static void initRabbitStew() {
        if (ItemTypes.RABBIT_STEW != null) return;
        ItemTypes.RABBIT_STEW = ItemTypeBuilder
                .builder(ItemRabbitStewStack.class)
                .vanillaItem(ItemId.RABBIT_STEW)
                .build();
    }

    public static void initRail() {
        if (ItemTypes.RAIL != null) return;
        ItemTypes.RAIL = ItemTypeBuilder
                .builder(ItemRailStack.class)
                .vanillaItem(ItemId.RAIL)
                .build();
    }

    public static void initRaiserArmorTrimSmithingTemplate() {
        if (ItemTypes.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemRaiserArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initRapidFertilizer() {
        if (ItemTypes.RAPID_FERTILIZER != null) return;
        ItemTypes.RAPID_FERTILIZER = ItemTypeBuilder
                .builder(ItemRapidFertilizerStack.class)
                .vanillaItem(ItemId.RAPID_FERTILIZER)
                .build();
    }

    public static void initRavagerSpawnEgg() {
        if (ItemTypes.RAVAGER_SPAWN_EGG != null) return;
        ItemTypes.RAVAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemRavagerSpawnEggStack.class)
                .vanillaItem(ItemId.RAVAGER_SPAWN_EGG)
                .build();
    }

    public static void initRawCopper() {
        if (ItemTypes.RAW_COPPER != null) return;
        ItemTypes.RAW_COPPER = ItemTypeBuilder
                .builder(ItemRawCopperStack.class)
                .vanillaItem(ItemId.RAW_COPPER)
                .build();
    }

    public static void initRawCopperBlock() {
        if (ItemTypes.RAW_COPPER_BLOCK != null) return;
        ItemTypes.RAW_COPPER_BLOCK = ItemTypeBuilder
                .builder(ItemRawCopperBlockStack.class)
                .vanillaItem(ItemId.RAW_COPPER_BLOCK)
                .build();
    }

    public static void initRawGold() {
        if (ItemTypes.RAW_GOLD != null) return;
        ItemTypes.RAW_GOLD = ItemTypeBuilder
                .builder(ItemRawGoldStack.class)
                .vanillaItem(ItemId.RAW_GOLD)
                .build();
    }

    public static void initRawGoldBlock() {
        if (ItemTypes.RAW_GOLD_BLOCK != null) return;
        ItemTypes.RAW_GOLD_BLOCK = ItemTypeBuilder
                .builder(ItemRawGoldBlockStack.class)
                .vanillaItem(ItemId.RAW_GOLD_BLOCK)
                .build();
    }

    public static void initRawIron() {
        if (ItemTypes.RAW_IRON != null) return;
        ItemTypes.RAW_IRON = ItemTypeBuilder
                .builder(ItemRawIronStack.class)
                .vanillaItem(ItemId.RAW_IRON)
                .build();
    }

    public static void initRawIronBlock() {
        if (ItemTypes.RAW_IRON_BLOCK != null) return;
        ItemTypes.RAW_IRON_BLOCK = ItemTypeBuilder
                .builder(ItemRawIronBlockStack.class)
                .vanillaItem(ItemId.RAW_IRON_BLOCK)
                .build();
    }

    public static void initRecoveryCompass() {
        if (ItemTypes.RECOVERY_COMPASS != null) return;
        ItemTypes.RECOVERY_COMPASS = ItemTypeBuilder
                .builder(ItemRecoveryCompassStack.class)
                .vanillaItem(ItemId.RECOVERY_COMPASS)
                .build();
    }

    public static void initRedCandle() {
        if (ItemTypes.RED_CANDLE != null) return;
        ItemTypes.RED_CANDLE = ItemTypeBuilder
                .builder(ItemRedCandleStack.class)
                .vanillaItem(ItemId.RED_CANDLE)
                .build();
    }

    public static void initRedCandleCake() {
        if (ItemTypes.RED_CANDLE_CAKE != null) return;
        ItemTypes.RED_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemRedCandleCakeStack.class)
                .vanillaItem(ItemId.RED_CANDLE_CAKE)
                .build();
    }

    public static void initRedCarpet() {
        if (ItemTypes.RED_CARPET != null) return;
        ItemTypes.RED_CARPET = ItemTypeBuilder
                .builder(ItemRedCarpetStack.class)
                .vanillaItem(ItemId.RED_CARPET)
                .build();
    }

    public static void initRedConcrete() {
        if (ItemTypes.RED_CONCRETE != null) return;
        ItemTypes.RED_CONCRETE = ItemTypeBuilder
                .builder(ItemRedConcreteStack.class)
                .vanillaItem(ItemId.RED_CONCRETE)
                .build();
    }

    public static void initRedConcretePowder() {
        if (ItemTypes.RED_CONCRETE_POWDER != null) return;
        ItemTypes.RED_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemRedConcretePowderStack.class)
                .vanillaItem(ItemId.RED_CONCRETE_POWDER)
                .build();
    }

    public static void initRedDye() {
        if (ItemTypes.RED_DYE != null) return;
        ItemTypes.RED_DYE = ItemTypeBuilder
                .builder(ItemRedDyeStack.class)
                .vanillaItem(ItemId.RED_DYE)
                .build();
    }

    public static void initRedFlower() {
        if (ItemTypes.RED_FLOWER != null) return;
        ItemTypes.RED_FLOWER = ItemTypeBuilder
                .builder(ItemRedFlowerStack.class)
                .vanillaItem(ItemId.RED_FLOWER)
                .build();
    }

    public static void initRedGlazedTerracotta() {
        if (ItemTypes.RED_GLAZED_TERRACOTTA != null) return;
        ItemTypes.RED_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemRedGlazedTerracottaStack.class)
                .vanillaItem(ItemId.RED_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initRedMushroom() {
        if (ItemTypes.RED_MUSHROOM != null) return;
        ItemTypes.RED_MUSHROOM = ItemTypeBuilder
                .builder(ItemRedMushroomStack.class)
                .vanillaItem(ItemId.RED_MUSHROOM)
                .build();
    }

    public static void initRedMushroomBlock() {
        if (ItemTypes.RED_MUSHROOM_BLOCK != null) return;
        ItemTypes.RED_MUSHROOM_BLOCK = ItemTypeBuilder
                .builder(ItemRedMushroomBlockStack.class)
                .vanillaItem(ItemId.RED_MUSHROOM_BLOCK)
                .build();
    }

    public static void initRedNetherBrick() {
        if (ItemTypes.RED_NETHER_BRICK != null) return;
        ItemTypes.RED_NETHER_BRICK = ItemTypeBuilder
                .builder(ItemRedNetherBrickStack.class)
                .vanillaItem(ItemId.RED_NETHER_BRICK)
                .build();
    }

    public static void initRedNetherBrickDoubleSlab() {
        if (ItemTypes.RED_NETHER_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.RED_NETHER_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemRedNetherBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.RED_NETHER_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initRedNetherBrickSlab() {
        if (ItemTypes.RED_NETHER_BRICK_SLAB != null) return;
        ItemTypes.RED_NETHER_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemRedNetherBrickSlabStack.class)
                .vanillaItem(ItemId.RED_NETHER_BRICK_SLAB)
                .build();
    }

    public static void initRedNetherBrickStairs() {
        if (ItemTypes.RED_NETHER_BRICK_STAIRS != null) return;
        ItemTypes.RED_NETHER_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemRedNetherBrickStairsStack.class)
                .vanillaItem(ItemId.RED_NETHER_BRICK_STAIRS)
                .build();
    }

    public static void initRedSand() {
        if (ItemTypes.RED_SAND != null) return;
        ItemTypes.RED_SAND = ItemTypeBuilder
                .builder(ItemRedSandStack.class)
                .vanillaItem(ItemId.RED_SAND)
                .build();
    }

    public static void initRedSandstone() {
        if (ItemTypes.RED_SANDSTONE != null) return;
        ItemTypes.RED_SANDSTONE = ItemTypeBuilder
                .builder(ItemRedSandstoneStack.class)
                .vanillaItem(ItemId.RED_SANDSTONE)
                .build();
    }

    public static void initRedSandstoneDoubleSlab() {
        if (ItemTypes.RED_SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.RED_SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemRedSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.RED_SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initRedSandstoneSlab() {
        if (ItemTypes.RED_SANDSTONE_SLAB != null) return;
        ItemTypes.RED_SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemRedSandstoneSlabStack.class)
                .vanillaItem(ItemId.RED_SANDSTONE_SLAB)
                .build();
    }

    public static void initRedSandstoneStairs() {
        if (ItemTypes.RED_SANDSTONE_STAIRS != null) return;
        ItemTypes.RED_SANDSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemRedSandstoneStairsStack.class)
                .vanillaItem(ItemId.RED_SANDSTONE_STAIRS)
                .build();
    }

    public static void initRedShulkerBox() {
        if (ItemTypes.RED_SHULKER_BOX != null) return;
        ItemTypes.RED_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemRedShulkerBoxStack.class)
                .vanillaItem(ItemId.RED_SHULKER_BOX)
                .build();
    }

    public static void initRedStainedGlass() {
        if (ItemTypes.RED_STAINED_GLASS != null) return;
        ItemTypes.RED_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemRedStainedGlassStack.class)
                .vanillaItem(ItemId.RED_STAINED_GLASS)
                .build();
    }

    public static void initRedStainedGlassPane() {
        if (ItemTypes.RED_STAINED_GLASS_PANE != null) return;
        ItemTypes.RED_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemRedStainedGlassPaneStack.class)
                .vanillaItem(ItemId.RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initRedTerracotta() {
        if (ItemTypes.RED_TERRACOTTA != null) return;
        ItemTypes.RED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemRedTerracottaStack.class)
                .vanillaItem(ItemId.RED_TERRACOTTA)
                .build();
    }

    public static void initRedTulip() {
        if (ItemTypes.RED_TULIP != null) return;
        ItemTypes.RED_TULIP = ItemTypeBuilder
                .builder(ItemRedTulipStack.class)
                .vanillaItem(ItemId.RED_TULIP)
                .build();
    }

    public static void initRedWool() {
        if (ItemTypes.RED_WOOL != null) return;
        ItemTypes.RED_WOOL = ItemTypeBuilder
                .builder(ItemRedWoolStack.class)
                .vanillaItem(ItemId.RED_WOOL)
                .build();
    }

    public static void initRedstone() {
        if (ItemTypes.REDSTONE != null) return;
        ItemTypes.REDSTONE = ItemTypeBuilder
                .builder(ItemRedstoneStack.class)
                .vanillaItem(ItemId.REDSTONE)
                .build();
    }

    public static void initRedstoneBlock() {
        if (ItemTypes.REDSTONE_BLOCK != null) return;
        ItemTypes.REDSTONE_BLOCK = ItemTypeBuilder
                .builder(ItemRedstoneBlockStack.class)
                .vanillaItem(ItemId.REDSTONE_BLOCK)
                .build();
    }

    public static void initRedstoneLamp() {
        if (ItemTypes.REDSTONE_LAMP != null) return;
        ItemTypes.REDSTONE_LAMP = ItemTypeBuilder
                .builder(ItemRedstoneLampStack.class)
                .vanillaItem(ItemId.REDSTONE_LAMP)
                .build();
    }

    public static void initRedstoneOre() {
        if (ItemTypes.REDSTONE_ORE != null) return;
        ItemTypes.REDSTONE_ORE = ItemTypeBuilder
                .builder(ItemRedstoneOreStack.class)
                .vanillaItem(ItemId.REDSTONE_ORE)
                .build();
    }

    public static void initRedstoneTorch() {
        if (ItemTypes.REDSTONE_TORCH != null) return;
        ItemTypes.REDSTONE_TORCH = ItemTypeBuilder
                .builder(ItemRedstoneTorchStack.class)
                .vanillaItem(ItemId.REDSTONE_TORCH)
                .build();
    }

    public static void initRedstoneWire() {
        if (ItemTypes.REDSTONE_WIRE != null) return;
        ItemTypes.REDSTONE_WIRE = ItemTypeBuilder
                .builder(ItemRedstoneWireStack.class)
                .vanillaItem(ItemId.REDSTONE_WIRE)
                .build();
    }

    public static void initReinforcedDeepslate() {
        if (ItemTypes.REINFORCED_DEEPSLATE != null) return;
        ItemTypes.REINFORCED_DEEPSLATE = ItemTypeBuilder
                .builder(ItemReinforcedDeepslateStack.class)
                .vanillaItem(ItemId.REINFORCED_DEEPSLATE)
                .build();
    }

    public static void initRepeater() {
        if (ItemTypes.REPEATER != null) return;
        ItemTypes.REPEATER = ItemTypeBuilder
                .builder(ItemRepeaterStack.class)
                .vanillaItem(ItemId.REPEATER)
                .build();
    }

    public static void initRepeatingCommandBlock() {
        if (ItemTypes.REPEATING_COMMAND_BLOCK != null) return;
        ItemTypes.REPEATING_COMMAND_BLOCK = ItemTypeBuilder
                .builder(ItemRepeatingCommandBlockStack.class)
                .vanillaItem(ItemId.REPEATING_COMMAND_BLOCK)
                .build();
    }

    public static void initReserved6() {
        if (ItemTypes.RESERVED6 != null) return;
        ItemTypes.RESERVED6 = ItemTypeBuilder
                .builder(ItemReserved6Stack.class)
                .vanillaItem(ItemId.RESERVED6)
                .build();
    }

    public static void initRespawnAnchor() {
        if (ItemTypes.RESPAWN_ANCHOR != null) return;
        ItemTypes.RESPAWN_ANCHOR = ItemTypeBuilder
                .builder(ItemRespawnAnchorStack.class)
                .vanillaItem(ItemId.RESPAWN_ANCHOR)
                .build();
    }

    public static void initRibArmorTrimSmithingTemplate() {
        if (ItemTypes.RIB_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.RIB_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemRibArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.RIB_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initRoseBush() {
        if (ItemTypes.ROSE_BUSH != null) return;
        ItemTypes.ROSE_BUSH = ItemTypeBuilder
                .builder(ItemRoseBushStack.class)
                .vanillaItem(ItemId.ROSE_BUSH)
                .build();
    }

    public static void initRottenFlesh() {
        if (ItemTypes.ROTTEN_FLESH != null) return;
        ItemTypes.ROTTEN_FLESH = ItemTypeBuilder
                .builder(ItemRottenFleshStack.class)
                .vanillaItem(ItemId.ROTTEN_FLESH)
                .build();
    }

    public static void initSaddle() {
        if (ItemTypes.SADDLE != null) return;
        ItemTypes.SADDLE = ItemTypeBuilder
                .builder(ItemSaddleStack.class)
                .vanillaItem(ItemId.SADDLE)
                .build();
    }

    public static void initSalmon() {
        if (ItemTypes.SALMON != null) return;
        ItemTypes.SALMON = ItemTypeBuilder
                .builder(ItemSalmonStack.class)
                .vanillaItem(ItemId.SALMON)
                .build();
    }

    public static void initSalmonBucket() {
        if (ItemTypes.SALMON_BUCKET != null) return;
        ItemTypes.SALMON_BUCKET = ItemTypeBuilder
                .builder(ItemSalmonBucketStack.class)
                .vanillaItem(ItemId.SALMON_BUCKET)
                .build();
    }

    public static void initSalmonSpawnEgg() {
        if (ItemTypes.SALMON_SPAWN_EGG != null) return;
        ItemTypes.SALMON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSalmonSpawnEggStack.class)
                .vanillaItem(ItemId.SALMON_SPAWN_EGG)
                .build();
    }

    public static void initSand() {
        if (ItemTypes.SAND != null) return;
        ItemTypes.SAND = ItemTypeBuilder
                .builder(ItemSandStack.class)
                .vanillaItem(ItemId.SAND)
                .build();
    }

    public static void initSandstone() {
        if (ItemTypes.SANDSTONE != null) return;
        ItemTypes.SANDSTONE = ItemTypeBuilder
                .builder(ItemSandstoneStack.class)
                .vanillaItem(ItemId.SANDSTONE)
                .build();
    }

    public static void initSandstoneDoubleSlab() {
        if (ItemTypes.SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initSandstoneSlab() {
        if (ItemTypes.SANDSTONE_SLAB != null) return;
        ItemTypes.SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemSandstoneSlabStack.class)
                .vanillaItem(ItemId.SANDSTONE_SLAB)
                .build();
    }

    public static void initSandstoneStairs() {
        if (ItemTypes.SANDSTONE_STAIRS != null) return;
        ItemTypes.SANDSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemSandstoneStairsStack.class)
                .vanillaItem(ItemId.SANDSTONE_STAIRS)
                .build();
    }

    public static void initSapling() {
        if (ItemTypes.SAPLING != null) return;
        ItemTypes.SAPLING = ItemTypeBuilder
                .builder(ItemSaplingStack.class)
                .vanillaItem(ItemId.SAPLING)
                .build();
    }

    public static void initScaffolding() {
        if (ItemTypes.SCAFFOLDING != null) return;
        ItemTypes.SCAFFOLDING = ItemTypeBuilder
                .builder(ItemScaffoldingStack.class)
                .vanillaItem(ItemId.SCAFFOLDING)
                .build();
    }

    public static void initScrapePotterySherd() {
        if (ItemTypes.SCRAPE_POTTERY_SHERD != null) return;
        ItemTypes.SCRAPE_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemScrapePotterySherdStack.class)
                .vanillaItem(ItemId.SCRAPE_POTTERY_SHERD)
                .build();
    }

    public static void initSculk() {
        if (ItemTypes.SCULK != null) return;
        ItemTypes.SCULK = ItemTypeBuilder
                .builder(ItemSculkStack.class)
                .vanillaItem(ItemId.SCULK)
                .build();
    }

    public static void initSculkCatalyst() {
        if (ItemTypes.SCULK_CATALYST != null) return;
        ItemTypes.SCULK_CATALYST = ItemTypeBuilder
                .builder(ItemSculkCatalystStack.class)
                .vanillaItem(ItemId.SCULK_CATALYST)
                .build();
    }

    public static void initSculkSensor() {
        if (ItemTypes.SCULK_SENSOR != null) return;
        ItemTypes.SCULK_SENSOR = ItemTypeBuilder
                .builder(ItemSculkSensorStack.class)
                .vanillaItem(ItemId.SCULK_SENSOR)
                .build();
    }

    public static void initSculkShrieker() {
        if (ItemTypes.SCULK_SHRIEKER != null) return;
        ItemTypes.SCULK_SHRIEKER = ItemTypeBuilder
                .builder(ItemSculkShriekerStack.class)
                .vanillaItem(ItemId.SCULK_SHRIEKER)
                .build();
    }

    public static void initSculkVein() {
        if (ItemTypes.SCULK_VEIN != null) return;
        ItemTypes.SCULK_VEIN = ItemTypeBuilder
                .builder(ItemSculkVeinStack.class)
                .vanillaItem(ItemId.SCULK_VEIN)
                .build();
    }

    public static void initSeaLantern() {
        if (ItemTypes.SEA_LANTERN != null) return;
        ItemTypes.SEA_LANTERN = ItemTypeBuilder
                .builder(ItemSeaLanternStack.class)
                .vanillaItem(ItemId.SEA_LANTERN)
                .build();
    }

    public static void initSeaPickle() {
        if (ItemTypes.SEA_PICKLE != null) return;
        ItemTypes.SEA_PICKLE = ItemTypeBuilder
                .builder(ItemSeaPickleStack.class)
                .vanillaItem(ItemId.SEA_PICKLE)
                .build();
    }

    public static void initSeagrass() {
        if (ItemTypes.SEAGRASS != null) return;
        ItemTypes.SEAGRASS = ItemTypeBuilder
                .builder(ItemSeagrassStack.class)
                .vanillaItem(ItemId.SEAGRASS)
                .build();
    }

    public static void initSentryArmorTrimSmithingTemplate() {
        if (ItemTypes.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemSentryArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initShaperArmorTrimSmithingTemplate() {
        if (ItemTypes.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemShaperArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initSheafPotterySherd() {
        if (ItemTypes.SHEAF_POTTERY_SHERD != null) return;
        ItemTypes.SHEAF_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemSheafPotterySherdStack.class)
                .vanillaItem(ItemId.SHEAF_POTTERY_SHERD)
                .build();
    }

    public static void initShears() {
        if (ItemTypes.SHEARS != null) return;
        ItemTypes.SHEARS = ItemTypeBuilder
                .builder(ItemShearsStack.class)
                .vanillaItem(ItemId.SHEARS)
                .build();
    }

    public static void initSheepSpawnEgg() {
        if (ItemTypes.SHEEP_SPAWN_EGG != null) return;
        ItemTypes.SHEEP_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSheepSpawnEggStack.class)
                .vanillaItem(ItemId.SHEEP_SPAWN_EGG)
                .build();
    }

    public static void initShelterPotterySherd() {
        if (ItemTypes.SHELTER_POTTERY_SHERD != null) return;
        ItemTypes.SHELTER_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemShelterPotterySherdStack.class)
                .vanillaItem(ItemId.SHELTER_POTTERY_SHERD)
                .build();
    }

    public static void initShield() {
        if (ItemTypes.SHIELD != null) return;
        ItemTypes.SHIELD = ItemTypeBuilder
                .builder(ItemShieldStack.class)
                .vanillaItem(ItemId.SHIELD)
                .build();
    }

    public static void initShortGrass() {
        if (ItemTypes.SHORT_GRASS != null) return;
        ItemTypes.SHORT_GRASS = ItemTypeBuilder
                .builder(ItemShortGrassStack.class)
                .vanillaItem(ItemId.SHORT_GRASS)
                .build();
    }

    public static void initShroomlight() {
        if (ItemTypes.SHROOMLIGHT != null) return;
        ItemTypes.SHROOMLIGHT = ItemTypeBuilder
                .builder(ItemShroomlightStack.class)
                .vanillaItem(ItemId.SHROOMLIGHT)
                .build();
    }

    public static void initShulkerBox() {
        if (ItemTypes.SHULKER_BOX != null) return;
        ItemTypes.SHULKER_BOX = ItemTypeBuilder
                .builder(ItemShulkerBoxStack.class)
                .vanillaItem(ItemId.SHULKER_BOX)
                .build();
    }

    public static void initShulkerShell() {
        if (ItemTypes.SHULKER_SHELL != null) return;
        ItemTypes.SHULKER_SHELL = ItemTypeBuilder
                .builder(ItemShulkerShellStack.class)
                .vanillaItem(ItemId.SHULKER_SHELL)
                .build();
    }

    public static void initShulkerSpawnEgg() {
        if (ItemTypes.SHULKER_SPAWN_EGG != null) return;
        ItemTypes.SHULKER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemShulkerSpawnEggStack.class)
                .vanillaItem(ItemId.SHULKER_SPAWN_EGG)
                .build();
    }

    public static void initSilenceArmorTrimSmithingTemplate() {
        if (ItemTypes.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemSilenceArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initSilverGlazedTerracotta() {
        if (ItemTypes.SILVER_GLAZED_TERRACOTTA != null) return;
        ItemTypes.SILVER_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemSilverGlazedTerracottaStack.class)
                .vanillaItem(ItemId.SILVER_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initSilverfishSpawnEgg() {
        if (ItemTypes.SILVERFISH_SPAWN_EGG != null) return;
        ItemTypes.SILVERFISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSilverfishSpawnEggStack.class)
                .vanillaItem(ItemId.SILVERFISH_SPAWN_EGG)
                .build();
    }

    public static void initSkeletonHorseSpawnEgg() {
        if (ItemTypes.SKELETON_HORSE_SPAWN_EGG != null) return;
        ItemTypes.SKELETON_HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSkeletonHorseSpawnEggStack.class)
                .vanillaItem(ItemId.SKELETON_HORSE_SPAWN_EGG)
                .build();
    }

    public static void initSkeletonSpawnEgg() {
        if (ItemTypes.SKELETON_SPAWN_EGG != null) return;
        ItemTypes.SKELETON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSkeletonSpawnEggStack.class)
                .vanillaItem(ItemId.SKELETON_SPAWN_EGG)
                .build();
    }

    public static void initSkull() {
        if (ItemTypes.SKULL != null) return;
        ItemTypes.SKULL = ItemTypeBuilder
                .builder(ItemSkullStack.class)
                .vanillaItem(ItemId.SKULL)
                .build();
    }

    public static void initSkullBannerPattern() {
        if (ItemTypes.SKULL_BANNER_PATTERN != null) return;
        ItemTypes.SKULL_BANNER_PATTERN = ItemTypeBuilder
                .builder(ItemSkullBannerPatternStack.class)
                .vanillaItem(ItemId.SKULL_BANNER_PATTERN)
                .build();
    }

    public static void initSkullPotterySherd() {
        if (ItemTypes.SKULL_POTTERY_SHERD != null) return;
        ItemTypes.SKULL_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemSkullPotterySherdStack.class)
                .vanillaItem(ItemId.SKULL_POTTERY_SHERD)
                .build();
    }

    public static void initSlime() {
        if (ItemTypes.SLIME != null) return;
        ItemTypes.SLIME = ItemTypeBuilder
                .builder(ItemSlimeStack.class)
                .vanillaItem(ItemId.SLIME)
                .build();
    }

    public static void initSlimeBall() {
        if (ItemTypes.SLIME_BALL != null) return;
        ItemTypes.SLIME_BALL = ItemTypeBuilder
                .builder(ItemSlimeBallStack.class)
                .vanillaItem(ItemId.SLIME_BALL)
                .build();
    }

    public static void initSlimeSpawnEgg() {
        if (ItemTypes.SLIME_SPAWN_EGG != null) return;
        ItemTypes.SLIME_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSlimeSpawnEggStack.class)
                .vanillaItem(ItemId.SLIME_SPAWN_EGG)
                .build();
    }

    public static void initSmallAmethystBud() {
        if (ItemTypes.SMALL_AMETHYST_BUD != null) return;
        ItemTypes.SMALL_AMETHYST_BUD = ItemTypeBuilder
                .builder(ItemSmallAmethystBudStack.class)
                .vanillaItem(ItemId.SMALL_AMETHYST_BUD)
                .build();
    }

    public static void initSmallDripleafBlock() {
        if (ItemTypes.SMALL_DRIPLEAF_BLOCK != null) return;
        ItemTypes.SMALL_DRIPLEAF_BLOCK = ItemTypeBuilder
                .builder(ItemSmallDripleafBlockStack.class)
                .vanillaItem(ItemId.SMALL_DRIPLEAF_BLOCK)
                .build();
    }

    public static void initSmithingTable() {
        if (ItemTypes.SMITHING_TABLE != null) return;
        ItemTypes.SMITHING_TABLE = ItemTypeBuilder
                .builder(ItemSmithingTableStack.class)
                .vanillaItem(ItemId.SMITHING_TABLE)
                .build();
    }

    public static void initSmoker() {
        if (ItemTypes.SMOKER != null) return;
        ItemTypes.SMOKER = ItemTypeBuilder
                .builder(ItemSmokerStack.class)
                .vanillaItem(ItemId.SMOKER)
                .build();
    }

    public static void initSmoothBasalt() {
        if (ItemTypes.SMOOTH_BASALT != null) return;
        ItemTypes.SMOOTH_BASALT = ItemTypeBuilder
                .builder(ItemSmoothBasaltStack.class)
                .vanillaItem(ItemId.SMOOTH_BASALT)
                .build();
    }

    public static void initSmoothQuartz() {
        if (ItemTypes.SMOOTH_QUARTZ != null) return;
        ItemTypes.SMOOTH_QUARTZ = ItemTypeBuilder
                .builder(ItemSmoothQuartzStack.class)
                .vanillaItem(ItemId.SMOOTH_QUARTZ)
                .build();
    }

    public static void initSmoothQuartzDoubleSlab() {
        if (ItemTypes.SMOOTH_QUARTZ_DOUBLE_SLAB != null) return;
        ItemTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothQuartzDoubleSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                .build();
    }

    public static void initSmoothQuartzSlab() {
        if (ItemTypes.SMOOTH_QUARTZ_SLAB != null) return;
        ItemTypes.SMOOTH_QUARTZ_SLAB = ItemTypeBuilder
                .builder(ItemSmoothQuartzSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_QUARTZ_SLAB)
                .build();
    }

    public static void initSmoothQuartzStairs() {
        if (ItemTypes.SMOOTH_QUARTZ_STAIRS != null) return;
        ItemTypes.SMOOTH_QUARTZ_STAIRS = ItemTypeBuilder
                .builder(ItemSmoothQuartzStairsStack.class)
                .vanillaItem(ItemId.SMOOTH_QUARTZ_STAIRS)
                .build();
    }

    public static void initSmoothRedSandstone() {
        if (ItemTypes.SMOOTH_RED_SANDSTONE != null) return;
        ItemTypes.SMOOTH_RED_SANDSTONE = ItemTypeBuilder
                .builder(ItemSmoothRedSandstoneStack.class)
                .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE)
                .build();
    }

    public static void initSmoothRedSandstoneDoubleSlab() {
        if (ItemTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothRedSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initSmoothRedSandstoneSlab() {
        if (ItemTypes.SMOOTH_RED_SANDSTONE_SLAB != null) return;
        ItemTypes.SMOOTH_RED_SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothRedSandstoneSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_SLAB)
                .build();
    }

    public static void initSmoothRedSandstoneStairs() {
        if (ItemTypes.SMOOTH_RED_SANDSTONE_STAIRS != null) return;
        ItemTypes.SMOOTH_RED_SANDSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemSmoothRedSandstoneStairsStack.class)
                .vanillaItem(ItemId.SMOOTH_RED_SANDSTONE_STAIRS)
                .build();
    }

    public static void initSmoothSandstone() {
        if (ItemTypes.SMOOTH_SANDSTONE != null) return;
        ItemTypes.SMOOTH_SANDSTONE = ItemTypeBuilder
                .builder(ItemSmoothSandstoneStack.class)
                .vanillaItem(ItemId.SMOOTH_SANDSTONE)
                .build();
    }

    public static void initSmoothSandstoneDoubleSlab() {
        if (ItemTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB != null) return;
        ItemTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothSandstoneDoubleSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                .build();
    }

    public static void initSmoothSandstoneSlab() {
        if (ItemTypes.SMOOTH_SANDSTONE_SLAB != null) return;
        ItemTypes.SMOOTH_SANDSTONE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothSandstoneSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_SANDSTONE_SLAB)
                .build();
    }

    public static void initSmoothSandstoneStairs() {
        if (ItemTypes.SMOOTH_SANDSTONE_STAIRS != null) return;
        ItemTypes.SMOOTH_SANDSTONE_STAIRS = ItemTypeBuilder
                .builder(ItemSmoothSandstoneStairsStack.class)
                .vanillaItem(ItemId.SMOOTH_SANDSTONE_STAIRS)
                .build();
    }

    public static void initSmoothStone() {
        if (ItemTypes.SMOOTH_STONE != null) return;
        ItemTypes.SMOOTH_STONE = ItemTypeBuilder
                .builder(ItemSmoothStoneStack.class)
                .vanillaItem(ItemId.SMOOTH_STONE)
                .build();
    }

    public static void initSmoothStoneDoubleSlab() {
        if (ItemTypes.SMOOTH_STONE_DOUBLE_SLAB != null) return;
        ItemTypes.SMOOTH_STONE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothStoneDoubleSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_STONE_DOUBLE_SLAB)
                .build();
    }

    public static void initSmoothStoneSlab() {
        if (ItemTypes.SMOOTH_STONE_SLAB != null) return;
        ItemTypes.SMOOTH_STONE_SLAB = ItemTypeBuilder
                .builder(ItemSmoothStoneSlabStack.class)
                .vanillaItem(ItemId.SMOOTH_STONE_SLAB)
                .build();
    }

    public static void initSnifferEgg() {
        if (ItemTypes.SNIFFER_EGG != null) return;
        ItemTypes.SNIFFER_EGG = ItemTypeBuilder
                .builder(ItemSnifferEggStack.class)
                .vanillaItem(ItemId.SNIFFER_EGG)
                .build();
    }

    public static void initSnifferSpawnEgg() {
        if (ItemTypes.SNIFFER_SPAWN_EGG != null) return;
        ItemTypes.SNIFFER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSnifferSpawnEggStack.class)
                .vanillaItem(ItemId.SNIFFER_SPAWN_EGG)
                .build();
    }

    public static void initSnortPotterySherd() {
        if (ItemTypes.SNORT_POTTERY_SHERD != null) return;
        ItemTypes.SNORT_POTTERY_SHERD = ItemTypeBuilder
                .builder(ItemSnortPotterySherdStack.class)
                .vanillaItem(ItemId.SNORT_POTTERY_SHERD)
                .build();
    }

    public static void initSnoutArmorTrimSmithingTemplate() {
        if (ItemTypes.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemSnoutArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initSnow() {
        if (ItemTypes.SNOW != null) return;
        ItemTypes.SNOW = ItemTypeBuilder
                .builder(ItemSnowStack.class)
                .vanillaItem(ItemId.SNOW)
                .build();
    }

    public static void initSnowGolemSpawnEgg() {
        if (ItemTypes.SNOW_GOLEM_SPAWN_EGG != null) return;
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSnowGolemSpawnEggStack.class)
                .vanillaItem(ItemId.SNOW_GOLEM_SPAWN_EGG)
                .build();
    }

    public static void initSnowLayer() {
        if (ItemTypes.SNOW_LAYER != null) return;
        ItemTypes.SNOW_LAYER = ItemTypeBuilder
                .builder(ItemSnowLayerStack.class)
                .vanillaItem(ItemId.SNOW_LAYER)
                .build();
    }

    public static void initSnowball() {
        if (ItemTypes.SNOWBALL != null) return;
        ItemTypes.SNOWBALL = ItemTypeBuilder
                .builder(ItemSnowballStack.class)
                .vanillaItem(ItemId.SNOWBALL)
                .build();
    }

    public static void initSoulCampfire() {
        if (ItemTypes.SOUL_CAMPFIRE != null) return;
        ItemTypes.SOUL_CAMPFIRE = ItemTypeBuilder
                .builder(ItemSoulCampfireStack.class)
                .vanillaItem(ItemId.SOUL_CAMPFIRE)
                .build();
    }

    public static void initSoulFire() {
        if (ItemTypes.SOUL_FIRE != null) return;
        ItemTypes.SOUL_FIRE = ItemTypeBuilder
                .builder(ItemSoulFireStack.class)
                .vanillaItem(ItemId.SOUL_FIRE)
                .build();
    }

    public static void initSoulLantern() {
        if (ItemTypes.SOUL_LANTERN != null) return;
        ItemTypes.SOUL_LANTERN = ItemTypeBuilder
                .builder(ItemSoulLanternStack.class)
                .vanillaItem(ItemId.SOUL_LANTERN)
                .build();
    }

    public static void initSoulSand() {
        if (ItemTypes.SOUL_SAND != null) return;
        ItemTypes.SOUL_SAND = ItemTypeBuilder
                .builder(ItemSoulSandStack.class)
                .vanillaItem(ItemId.SOUL_SAND)
                .build();
    }

    public static void initSoulSoil() {
        if (ItemTypes.SOUL_SOIL != null) return;
        ItemTypes.SOUL_SOIL = ItemTypeBuilder
                .builder(ItemSoulSoilStack.class)
                .vanillaItem(ItemId.SOUL_SOIL)
                .build();
    }

    public static void initSoulTorch() {
        if (ItemTypes.SOUL_TORCH != null) return;
        ItemTypes.SOUL_TORCH = ItemTypeBuilder
                .builder(ItemSoulTorchStack.class)
                .vanillaItem(ItemId.SOUL_TORCH)
                .build();
    }

    public static void initSparkler() {
        if (ItemTypes.SPARKLER != null) return;
        ItemTypes.SPARKLER = ItemTypeBuilder
                .builder(ItemSparklerStack.class)
                .vanillaItem(ItemId.SPARKLER)
                .build();
    }

    public static void initSpawnEgg() {
        if (ItemTypes.SPAWN_EGG != null) return;
        ItemTypes.SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSpawnEggStack.class)
                .vanillaItem(ItemId.SPAWN_EGG)
                .build();
    }

    public static void initSpiderEye() {
        if (ItemTypes.SPIDER_EYE != null) return;
        ItemTypes.SPIDER_EYE = ItemTypeBuilder
                .builder(ItemSpiderEyeStack.class)
                .vanillaItem(ItemId.SPIDER_EYE)
                .build();
    }

    public static void initSpiderSpawnEgg() {
        if (ItemTypes.SPIDER_SPAWN_EGG != null) return;
        ItemTypes.SPIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSpiderSpawnEggStack.class)
                .vanillaItem(ItemId.SPIDER_SPAWN_EGG)
                .build();
    }

    public static void initSpireArmorTrimSmithingTemplate() {
        if (ItemTypes.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemSpireArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initSplashPotion() {
        if (ItemTypes.SPLASH_POTION != null) return;
        ItemTypes.SPLASH_POTION = ItemTypeBuilder
                .builder(ItemSplashPotionStack.class)
                .vanillaItem(ItemId.SPLASH_POTION)
                .build();
    }

    public static void initSponge() {
        if (ItemTypes.SPONGE != null) return;
        ItemTypes.SPONGE = ItemTypeBuilder
                .builder(ItemSpongeStack.class)
                .vanillaItem(ItemId.SPONGE)
                .build();
    }

    public static void initSporeBlossom() {
        if (ItemTypes.SPORE_BLOSSOM != null) return;
        ItemTypes.SPORE_BLOSSOM = ItemTypeBuilder
                .builder(ItemSporeBlossomStack.class)
                .vanillaItem(ItemId.SPORE_BLOSSOM)
                .build();
    }

    public static void initSpruceBoat() {
        if (ItemTypes.SPRUCE_BOAT != null) return;
        ItemTypes.SPRUCE_BOAT = ItemTypeBuilder
                .builder(ItemSpruceBoatStack.class)
                .vanillaItem(ItemId.SPRUCE_BOAT)
                .build();
    }

    public static void initSpruceButton() {
        if (ItemTypes.SPRUCE_BUTTON != null) return;
        ItemTypes.SPRUCE_BUTTON = ItemTypeBuilder
                .builder(ItemSpruceButtonStack.class)
                .vanillaItem(ItemId.SPRUCE_BUTTON)
                .build();
    }

    public static void initSpruceChestBoat() {
        if (ItemTypes.SPRUCE_CHEST_BOAT != null) return;
        ItemTypes.SPRUCE_CHEST_BOAT = ItemTypeBuilder
                .builder(ItemSpruceChestBoatStack.class)
                .vanillaItem(ItemId.SPRUCE_CHEST_BOAT)
                .build();
    }

    public static void initSpruceDoor() {
        if (ItemTypes.SPRUCE_DOOR != null) return;
        ItemTypes.SPRUCE_DOOR = ItemTypeBuilder
                .builder(ItemSpruceDoorStack.class)
                .vanillaItem(ItemId.SPRUCE_DOOR)
                .build();
    }

    public static void initSpruceDoubleSlab() {
        if (ItemTypes.SPRUCE_DOUBLE_SLAB != null) return;
        ItemTypes.SPRUCE_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemSpruceDoubleSlabStack.class)
                .vanillaItem(ItemId.SPRUCE_DOUBLE_SLAB)
                .build();
    }

    public static void initSpruceFence() {
        if (ItemTypes.SPRUCE_FENCE != null) return;
        ItemTypes.SPRUCE_FENCE = ItemTypeBuilder
                .builder(ItemSpruceFenceStack.class)
                .vanillaItem(ItemId.SPRUCE_FENCE)
                .build();
    }

    public static void initSpruceFenceGate() {
        if (ItemTypes.SPRUCE_FENCE_GATE != null) return;
        ItemTypes.SPRUCE_FENCE_GATE = ItemTypeBuilder
                .builder(ItemSpruceFenceGateStack.class)
                .vanillaItem(ItemId.SPRUCE_FENCE_GATE)
                .build();
    }

    public static void initSpruceHangingSign() {
        if (ItemTypes.SPRUCE_HANGING_SIGN != null) return;
        ItemTypes.SPRUCE_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemSpruceHangingSignStack.class)
                .vanillaItem(ItemId.SPRUCE_HANGING_SIGN)
                .build();
    }

    public static void initSpruceLeaves() {
        if (ItemTypes.SPRUCE_LEAVES != null) return;
        ItemTypes.SPRUCE_LEAVES = ItemTypeBuilder
                .builder(ItemSpruceLeavesStack.class)
                .vanillaItem(ItemId.SPRUCE_LEAVES)
                .build();
    }

    public static void initSpruceLog() {
        if (ItemTypes.SPRUCE_LOG != null) return;
        ItemTypes.SPRUCE_LOG = ItemTypeBuilder
                .builder(ItemSpruceLogStack.class)
                .vanillaItem(ItemId.SPRUCE_LOG)
                .build();
    }

    public static void initSprucePlanks() {
        if (ItemTypes.SPRUCE_PLANKS != null) return;
        ItemTypes.SPRUCE_PLANKS = ItemTypeBuilder
                .builder(ItemSprucePlanksStack.class)
                .vanillaItem(ItemId.SPRUCE_PLANKS)
                .build();
    }

    public static void initSprucePressurePlate() {
        if (ItemTypes.SPRUCE_PRESSURE_PLATE != null) return;
        ItemTypes.SPRUCE_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemSprucePressurePlateStack.class)
                .vanillaItem(ItemId.SPRUCE_PRESSURE_PLATE)
                .build();
    }

    public static void initSpruceSapling() {
        if (ItemTypes.SPRUCE_SAPLING != null) return;
        ItemTypes.SPRUCE_SAPLING = ItemTypeBuilder
                .builder(ItemSpruceSaplingStack.class)
                .vanillaItem(ItemId.SPRUCE_SAPLING)
                .build();
    }

    public static void initSpruceSign() {
        if (ItemTypes.SPRUCE_SIGN != null) return;
        ItemTypes.SPRUCE_SIGN = ItemTypeBuilder
                .builder(ItemSpruceSignStack.class)
                .vanillaItem(ItemId.SPRUCE_SIGN)
                .build();
    }

    public static void initSpruceSlab() {
        if (ItemTypes.SPRUCE_SLAB != null) return;
        ItemTypes.SPRUCE_SLAB = ItemTypeBuilder
                .builder(ItemSpruceSlabStack.class)
                .vanillaItem(ItemId.SPRUCE_SLAB)
                .build();
    }

    public static void initSpruceStairs() {
        if (ItemTypes.SPRUCE_STAIRS != null) return;
        ItemTypes.SPRUCE_STAIRS = ItemTypeBuilder
                .builder(ItemSpruceStairsStack.class)
                .vanillaItem(ItemId.SPRUCE_STAIRS)
                .build();
    }

    public static void initSpruceStandingSign() {
        if (ItemTypes.SPRUCE_STANDING_SIGN != null) return;
        ItemTypes.SPRUCE_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemSpruceStandingSignStack.class)
                .vanillaItem(ItemId.SPRUCE_STANDING_SIGN)
                .build();
    }

    public static void initSpruceTrapdoor() {
        if (ItemTypes.SPRUCE_TRAPDOOR != null) return;
        ItemTypes.SPRUCE_TRAPDOOR = ItemTypeBuilder
                .builder(ItemSpruceTrapdoorStack.class)
                .vanillaItem(ItemId.SPRUCE_TRAPDOOR)
                .build();
    }

    public static void initSpruceWallSign() {
        if (ItemTypes.SPRUCE_WALL_SIGN != null) return;
        ItemTypes.SPRUCE_WALL_SIGN = ItemTypeBuilder
                .builder(ItemSpruceWallSignStack.class)
                .vanillaItem(ItemId.SPRUCE_WALL_SIGN)
                .build();
    }

    public static void initSpruceWood() {
        if (ItemTypes.SPRUCE_WOOD != null) return;
        ItemTypes.SPRUCE_WOOD = ItemTypeBuilder
                .builder(ItemSpruceWoodStack.class)
                .vanillaItem(ItemId.SPRUCE_WOOD)
                .build();
    }

    public static void initSpyglass() {
        if (ItemTypes.SPYGLASS != null) return;
        ItemTypes.SPYGLASS = ItemTypeBuilder
                .builder(ItemSpyglassStack.class)
                .vanillaItem(ItemId.SPYGLASS)
                .build();
    }

    public static void initSquidSpawnEgg() {
        if (ItemTypes.SQUID_SPAWN_EGG != null) return;
        ItemTypes.SQUID_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSquidSpawnEggStack.class)
                .vanillaItem(ItemId.SQUID_SPAWN_EGG)
                .build();
    }

    public static void initStainedGlass() {
        if (ItemTypes.STAINED_GLASS != null) return;
        ItemTypes.STAINED_GLASS = ItemTypeBuilder
                .builder(ItemStainedGlassStack.class)
                .vanillaItem(ItemId.STAINED_GLASS)
                .build();
    }

    public static void initStainedGlassPane() {
        if (ItemTypes.STAINED_GLASS_PANE != null) return;
        ItemTypes.STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemStainedGlassPaneStack.class)
                .vanillaItem(ItemId.STAINED_GLASS_PANE)
                .build();
    }

    public static void initStainedHardenedClay() {
        if (ItemTypes.STAINED_HARDENED_CLAY != null) return;
        ItemTypes.STAINED_HARDENED_CLAY = ItemTypeBuilder
                .builder(ItemStainedHardenedClayStack.class)
                .vanillaItem(ItemId.STAINED_HARDENED_CLAY)
                .build();
    }

    public static void initStandingBanner() {
        if (ItemTypes.STANDING_BANNER != null) return;
        ItemTypes.STANDING_BANNER = ItemTypeBuilder
                .builder(ItemStandingBannerStack.class)
                .vanillaItem(ItemId.STANDING_BANNER)
                .build();
    }

    public static void initStandingSign() {
        if (ItemTypes.STANDING_SIGN != null) return;
        ItemTypes.STANDING_SIGN = ItemTypeBuilder
                .builder(ItemStandingSignStack.class)
                .vanillaItem(ItemId.STANDING_SIGN)
                .build();
    }

    public static void initStick() {
        if (ItemTypes.STICK != null) return;
        ItemTypes.STICK = ItemTypeBuilder
                .builder(ItemStickStack.class)
                .vanillaItem(ItemId.STICK)
                .build();
    }

    public static void initStickyPiston() {
        if (ItemTypes.STICKY_PISTON != null) return;
        ItemTypes.STICKY_PISTON = ItemTypeBuilder
                .builder(ItemStickyPistonStack.class)
                .vanillaItem(ItemId.STICKY_PISTON)
                .build();
    }

    public static void initStickyPistonArmCollision() {
        if (ItemTypes.STICKY_PISTON_ARM_COLLISION != null) return;
        ItemTypes.STICKY_PISTON_ARM_COLLISION = ItemTypeBuilder
                .builder(ItemStickyPistonArmCollisionStack.class)
                .vanillaItem(ItemId.STICKY_PISTON_ARM_COLLISION)
                .build();
    }

    public static void initStone() {
        if (ItemTypes.STONE != null) return;
        ItemTypes.STONE = ItemTypeBuilder
                .builder(ItemStoneStack.class)
                .vanillaItem(ItemId.STONE)
                .build();
    }

    public static void initStoneAxe() {
        if (ItemTypes.STONE_AXE != null) return;
        ItemTypes.STONE_AXE = ItemTypeBuilder
                .builder(ItemStoneAxeStack.class)
                .vanillaItem(ItemId.STONE_AXE)
                .build();
    }

    public static void initStoneBlockSlab() {
        if (ItemTypes.STONE_BLOCK_SLAB != null) return;
        ItemTypes.STONE_BLOCK_SLAB = ItemTypeBuilder
                .builder(ItemStoneBlockSlabStack.class)
                .vanillaItem(ItemId.STONE_BLOCK_SLAB)
                .build();
    }

    public static void initStoneBlockSlab2() {
        if (ItemTypes.STONE_BLOCK_SLAB2 != null) return;
        ItemTypes.STONE_BLOCK_SLAB2 = ItemTypeBuilder
                .builder(ItemStoneBlockSlab2Stack.class)
                .vanillaItem(ItemId.STONE_BLOCK_SLAB2)
                .build();
    }

    public static void initStoneBlockSlab3() {
        if (ItemTypes.STONE_BLOCK_SLAB3 != null) return;
        ItemTypes.STONE_BLOCK_SLAB3 = ItemTypeBuilder
                .builder(ItemStoneBlockSlab3Stack.class)
                .vanillaItem(ItemId.STONE_BLOCK_SLAB3)
                .build();
    }

    public static void initStoneBlockSlab4() {
        if (ItemTypes.STONE_BLOCK_SLAB4 != null) return;
        ItemTypes.STONE_BLOCK_SLAB4 = ItemTypeBuilder
                .builder(ItemStoneBlockSlab4Stack.class)
                .vanillaItem(ItemId.STONE_BLOCK_SLAB4)
                .build();
    }

    public static void initStoneBrickDoubleSlab() {
        if (ItemTypes.STONE_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.STONE_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemStoneBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.STONE_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initStoneBrickSlab() {
        if (ItemTypes.STONE_BRICK_SLAB != null) return;
        ItemTypes.STONE_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemStoneBrickSlabStack.class)
                .vanillaItem(ItemId.STONE_BRICK_SLAB)
                .build();
    }

    public static void initStoneBrickStairs() {
        if (ItemTypes.STONE_BRICK_STAIRS != null) return;
        ItemTypes.STONE_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemStoneBrickStairsStack.class)
                .vanillaItem(ItemId.STONE_BRICK_STAIRS)
                .build();
    }

    public static void initStoneBricks() {
        if (ItemTypes.STONE_BRICKS != null) return;
        ItemTypes.STONE_BRICKS = ItemTypeBuilder
                .builder(ItemStoneBricksStack.class)
                .vanillaItem(ItemId.STONE_BRICKS)
                .build();
    }

    public static void initStoneButton() {
        if (ItemTypes.STONE_BUTTON != null) return;
        ItemTypes.STONE_BUTTON = ItemTypeBuilder
                .builder(ItemStoneButtonStack.class)
                .vanillaItem(ItemId.STONE_BUTTON)
                .build();
    }

    public static void initStoneHoe() {
        if (ItemTypes.STONE_HOE != null) return;
        ItemTypes.STONE_HOE = ItemTypeBuilder
                .builder(ItemStoneHoeStack.class)
                .vanillaItem(ItemId.STONE_HOE)
                .build();
    }

    public static void initStonePickaxe() {
        if (ItemTypes.STONE_PICKAXE != null) return;
        ItemTypes.STONE_PICKAXE = ItemTypeBuilder
                .builder(ItemStonePickaxeStack.class)
                .vanillaItem(ItemId.STONE_PICKAXE)
                .build();
    }

    public static void initStonePressurePlate() {
        if (ItemTypes.STONE_PRESSURE_PLATE != null) return;
        ItemTypes.STONE_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemStonePressurePlateStack.class)
                .vanillaItem(ItemId.STONE_PRESSURE_PLATE)
                .build();
    }

    public static void initStoneShovel() {
        if (ItemTypes.STONE_SHOVEL != null) return;
        ItemTypes.STONE_SHOVEL = ItemTypeBuilder
                .builder(ItemStoneShovelStack.class)
                .vanillaItem(ItemId.STONE_SHOVEL)
                .build();
    }

    public static void initStoneStairs() {
        if (ItemTypes.STONE_STAIRS != null) return;
        ItemTypes.STONE_STAIRS = ItemTypeBuilder
                .builder(ItemStoneStairsStack.class)
                .vanillaItem(ItemId.STONE_STAIRS)
                .build();
    }

    public static void initStoneSword() {
        if (ItemTypes.STONE_SWORD != null) return;
        ItemTypes.STONE_SWORD = ItemTypeBuilder
                .builder(ItemStoneSwordStack.class)
                .vanillaItem(ItemId.STONE_SWORD)
                .build();
    }

    public static void initStonebrick() {
        if (ItemTypes.STONEBRICK != null) return;
        ItemTypes.STONEBRICK = ItemTypeBuilder
                .builder(ItemStonebrickStack.class)
                .vanillaItem(ItemId.STONEBRICK)
                .build();
    }

    public static void initStonecutter() {
        if (ItemTypes.STONECUTTER != null) return;
        ItemTypes.STONECUTTER = ItemTypeBuilder
                .builder(ItemStonecutterStack.class)
                .vanillaItem(ItemId.STONECUTTER)
                .build();
    }

    public static void initStonecutterBlock() {
        if (ItemTypes.STONECUTTER_BLOCK != null) return;
        ItemTypes.STONECUTTER_BLOCK = ItemTypeBuilder
                .builder(ItemStonecutterBlockStack.class)
                .vanillaItem(ItemId.STONECUTTER_BLOCK)
                .build();
    }

    public static void initStraySpawnEgg() {
        if (ItemTypes.STRAY_SPAWN_EGG != null) return;
        ItemTypes.STRAY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemStraySpawnEggStack.class)
                .vanillaItem(ItemId.STRAY_SPAWN_EGG)
                .build();
    }

    public static void initStriderSpawnEgg() {
        if (ItemTypes.STRIDER_SPAWN_EGG != null) return;
        ItemTypes.STRIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemStriderSpawnEggStack.class)
                .vanillaItem(ItemId.STRIDER_SPAWN_EGG)
                .build();
    }

    public static void initString() {
        if (ItemTypes.STRING != null) return;
        ItemTypes.STRING = ItemTypeBuilder
                .builder(ItemStringStack.class)
                .vanillaItem(ItemId.STRING)
                .build();
    }

    public static void initStrippedAcaciaLog() {
        if (ItemTypes.STRIPPED_ACACIA_LOG != null) return;
        ItemTypes.STRIPPED_ACACIA_LOG = ItemTypeBuilder
                .builder(ItemStrippedAcaciaLogStack.class)
                .vanillaItem(ItemId.STRIPPED_ACACIA_LOG)
                .build();
    }

    public static void initStrippedAcaciaWood() {
        if (ItemTypes.STRIPPED_ACACIA_WOOD != null) return;
        ItemTypes.STRIPPED_ACACIA_WOOD = ItemTypeBuilder
                .builder(ItemStrippedAcaciaWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_ACACIA_WOOD)
                .build();
    }

    public static void initStrippedBambooBlock() {
        if (ItemTypes.STRIPPED_BAMBOO_BLOCK != null) return;
        ItemTypes.STRIPPED_BAMBOO_BLOCK = ItemTypeBuilder
                .builder(ItemStrippedBambooBlockStack.class)
                .vanillaItem(ItemId.STRIPPED_BAMBOO_BLOCK)
                .build();
    }

    public static void initStrippedBirchLog() {
        if (ItemTypes.STRIPPED_BIRCH_LOG != null) return;
        ItemTypes.STRIPPED_BIRCH_LOG = ItemTypeBuilder
                .builder(ItemStrippedBirchLogStack.class)
                .vanillaItem(ItemId.STRIPPED_BIRCH_LOG)
                .build();
    }

    public static void initStrippedBirchWood() {
        if (ItemTypes.STRIPPED_BIRCH_WOOD != null) return;
        ItemTypes.STRIPPED_BIRCH_WOOD = ItemTypeBuilder
                .builder(ItemStrippedBirchWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_BIRCH_WOOD)
                .build();
    }

    public static void initStrippedCherryLog() {
        if (ItemTypes.STRIPPED_CHERRY_LOG != null) return;
        ItemTypes.STRIPPED_CHERRY_LOG = ItemTypeBuilder
                .builder(ItemStrippedCherryLogStack.class)
                .vanillaItem(ItemId.STRIPPED_CHERRY_LOG)
                .build();
    }

    public static void initStrippedCherryWood() {
        if (ItemTypes.STRIPPED_CHERRY_WOOD != null) return;
        ItemTypes.STRIPPED_CHERRY_WOOD = ItemTypeBuilder
                .builder(ItemStrippedCherryWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_CHERRY_WOOD)
                .build();
    }

    public static void initStrippedCrimsonHyphae() {
        if (ItemTypes.STRIPPED_CRIMSON_HYPHAE != null) return;
        ItemTypes.STRIPPED_CRIMSON_HYPHAE = ItemTypeBuilder
                .builder(ItemStrippedCrimsonHyphaeStack.class)
                .vanillaItem(ItemId.STRIPPED_CRIMSON_HYPHAE)
                .build();
    }

    public static void initStrippedCrimsonStem() {
        if (ItemTypes.STRIPPED_CRIMSON_STEM != null) return;
        ItemTypes.STRIPPED_CRIMSON_STEM = ItemTypeBuilder
                .builder(ItemStrippedCrimsonStemStack.class)
                .vanillaItem(ItemId.STRIPPED_CRIMSON_STEM)
                .build();
    }

    public static void initStrippedDarkOakLog() {
        if (ItemTypes.STRIPPED_DARK_OAK_LOG != null) return;
        ItemTypes.STRIPPED_DARK_OAK_LOG = ItemTypeBuilder
                .builder(ItemStrippedDarkOakLogStack.class)
                .vanillaItem(ItemId.STRIPPED_DARK_OAK_LOG)
                .build();
    }

    public static void initStrippedDarkOakWood() {
        if (ItemTypes.STRIPPED_DARK_OAK_WOOD != null) return;
        ItemTypes.STRIPPED_DARK_OAK_WOOD = ItemTypeBuilder
                .builder(ItemStrippedDarkOakWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_DARK_OAK_WOOD)
                .build();
    }

    public static void initStrippedJungleLog() {
        if (ItemTypes.STRIPPED_JUNGLE_LOG != null) return;
        ItemTypes.STRIPPED_JUNGLE_LOG = ItemTypeBuilder
                .builder(ItemStrippedJungleLogStack.class)
                .vanillaItem(ItemId.STRIPPED_JUNGLE_LOG)
                .build();
    }

    public static void initStrippedJungleWood() {
        if (ItemTypes.STRIPPED_JUNGLE_WOOD != null) return;
        ItemTypes.STRIPPED_JUNGLE_WOOD = ItemTypeBuilder
                .builder(ItemStrippedJungleWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_JUNGLE_WOOD)
                .build();
    }

    public static void initStrippedMangroveLog() {
        if (ItemTypes.STRIPPED_MANGROVE_LOG != null) return;
        ItemTypes.STRIPPED_MANGROVE_LOG = ItemTypeBuilder
                .builder(ItemStrippedMangroveLogStack.class)
                .vanillaItem(ItemId.STRIPPED_MANGROVE_LOG)
                .build();
    }

    public static void initStrippedMangroveWood() {
        if (ItemTypes.STRIPPED_MANGROVE_WOOD != null) return;
        ItemTypes.STRIPPED_MANGROVE_WOOD = ItemTypeBuilder
                .builder(ItemStrippedMangroveWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_MANGROVE_WOOD)
                .build();
    }

    public static void initStrippedOakLog() {
        if (ItemTypes.STRIPPED_OAK_LOG != null) return;
        ItemTypes.STRIPPED_OAK_LOG = ItemTypeBuilder
                .builder(ItemStrippedOakLogStack.class)
                .vanillaItem(ItemId.STRIPPED_OAK_LOG)
                .build();
    }

    public static void initStrippedOakWood() {
        if (ItemTypes.STRIPPED_OAK_WOOD != null) return;
        ItemTypes.STRIPPED_OAK_WOOD = ItemTypeBuilder
                .builder(ItemStrippedOakWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_OAK_WOOD)
                .build();
    }

    public static void initStrippedSpruceLog() {
        if (ItemTypes.STRIPPED_SPRUCE_LOG != null) return;
        ItemTypes.STRIPPED_SPRUCE_LOG = ItemTypeBuilder
                .builder(ItemStrippedSpruceLogStack.class)
                .vanillaItem(ItemId.STRIPPED_SPRUCE_LOG)
                .build();
    }

    public static void initStrippedSpruceWood() {
        if (ItemTypes.STRIPPED_SPRUCE_WOOD != null) return;
        ItemTypes.STRIPPED_SPRUCE_WOOD = ItemTypeBuilder
                .builder(ItemStrippedSpruceWoodStack.class)
                .vanillaItem(ItemId.STRIPPED_SPRUCE_WOOD)
                .build();
    }

    public static void initStrippedWarpedHyphae() {
        if (ItemTypes.STRIPPED_WARPED_HYPHAE != null) return;
        ItemTypes.STRIPPED_WARPED_HYPHAE = ItemTypeBuilder
                .builder(ItemStrippedWarpedHyphaeStack.class)
                .vanillaItem(ItemId.STRIPPED_WARPED_HYPHAE)
                .build();
    }

    public static void initStrippedWarpedStem() {
        if (ItemTypes.STRIPPED_WARPED_STEM != null) return;
        ItemTypes.STRIPPED_WARPED_STEM = ItemTypeBuilder
                .builder(ItemStrippedWarpedStemStack.class)
                .vanillaItem(ItemId.STRIPPED_WARPED_STEM)
                .build();
    }

    public static void initStructureBlock() {
        if (ItemTypes.STRUCTURE_BLOCK != null) return;
        ItemTypes.STRUCTURE_BLOCK = ItemTypeBuilder
                .builder(ItemStructureBlockStack.class)
                .vanillaItem(ItemId.STRUCTURE_BLOCK)
                .build();
    }

    public static void initStructureVoid() {
        if (ItemTypes.STRUCTURE_VOID != null) return;
        ItemTypes.STRUCTURE_VOID = ItemTypeBuilder
                .builder(ItemStructureVoidStack.class)
                .vanillaItem(ItemId.STRUCTURE_VOID)
                .build();
    }

    public static void initSugar() {
        if (ItemTypes.SUGAR != null) return;
        ItemTypes.SUGAR = ItemTypeBuilder
                .builder(ItemSugarStack.class)
                .vanillaItem(ItemId.SUGAR)
                .build();
    }

    public static void initSugarCane() {
        if (ItemTypes.SUGAR_CANE != null) return;
        ItemTypes.SUGAR_CANE = ItemTypeBuilder
                .builder(ItemSugarCaneStack.class)
                .vanillaItem(ItemId.SUGAR_CANE)
                .build();
    }

    public static void initSunflower() {
        if (ItemTypes.SUNFLOWER != null) return;
        ItemTypes.SUNFLOWER = ItemTypeBuilder
                .builder(ItemSunflowerStack.class)
                .vanillaItem(ItemId.SUNFLOWER)
                .build();
    }

    public static void initSuspiciousGravel() {
        if (ItemTypes.SUSPICIOUS_GRAVEL != null) return;
        ItemTypes.SUSPICIOUS_GRAVEL = ItemTypeBuilder
                .builder(ItemSuspiciousGravelStack.class)
                .vanillaItem(ItemId.SUSPICIOUS_GRAVEL)
                .build();
    }

    public static void initSuspiciousSand() {
        if (ItemTypes.SUSPICIOUS_SAND != null) return;
        ItemTypes.SUSPICIOUS_SAND = ItemTypeBuilder
                .builder(ItemSuspiciousSandStack.class)
                .vanillaItem(ItemId.SUSPICIOUS_SAND)
                .build();
    }

    public static void initSuspiciousStew() {
        if (ItemTypes.SUSPICIOUS_STEW != null) return;
        ItemTypes.SUSPICIOUS_STEW = ItemTypeBuilder
                .builder(ItemSuspiciousStewStack.class)
                .vanillaItem(ItemId.SUSPICIOUS_STEW)
                .build();
    }

    public static void initSweetBerries() {
        if (ItemTypes.SWEET_BERRIES != null) return;
        ItemTypes.SWEET_BERRIES = ItemTypeBuilder
                .builder(ItemSweetBerriesStack.class)
                .vanillaItem(ItemId.SWEET_BERRIES)
                .build();
    }

    public static void initSweetBerryBush() {
        if (ItemTypes.SWEET_BERRY_BUSH != null) return;
        ItemTypes.SWEET_BERRY_BUSH = ItemTypeBuilder
                .builder(ItemSweetBerryBushStack.class)
                .vanillaItem(ItemId.SWEET_BERRY_BUSH)
                .build();
    }

    public static void initTadpoleBucket() {
        if (ItemTypes.TADPOLE_BUCKET != null) return;
        ItemTypes.TADPOLE_BUCKET = ItemTypeBuilder
                .builder(ItemTadpoleBucketStack.class)
                .vanillaItem(ItemId.TADPOLE_BUCKET)
                .build();
    }

    public static void initTadpoleSpawnEgg() {
        if (ItemTypes.TADPOLE_SPAWN_EGG != null) return;
        ItemTypes.TADPOLE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTadpoleSpawnEggStack.class)
                .vanillaItem(ItemId.TADPOLE_SPAWN_EGG)
                .build();
    }

    public static void initTallGrass() {
        if (ItemTypes.TALL_GRASS != null) return;
        ItemTypes.TALL_GRASS = ItemTypeBuilder
                .builder(ItemTallGrassStack.class)
                .vanillaItem(ItemId.TALL_GRASS)
                .build();
    }

    public static void initTallgrass0() {
        if (ItemTypes.TALLGRASS != null) return;
        ItemTypes.TALLGRASS = ItemTypeBuilder
                .builder(ItemTallgrass0Stack.class)
                .vanillaItem(ItemId.TALLGRASS)
                .build();
    }

    public static void initTarget() {
        if (ItemTypes.TARGET != null) return;
        ItemTypes.TARGET = ItemTypeBuilder
                .builder(ItemTargetStack.class)
                .vanillaItem(ItemId.TARGET)
                .build();
    }

    public static void initTideArmorTrimSmithingTemplate() {
        if (ItemTypes.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemTideArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initTintedGlass() {
        if (ItemTypes.TINTED_GLASS != null) return;
        ItemTypes.TINTED_GLASS = ItemTypeBuilder
                .builder(ItemTintedGlassStack.class)
                .vanillaItem(ItemId.TINTED_GLASS)
                .build();
    }

    public static void initTnt() {
        if (ItemTypes.TNT != null) return;
        ItemTypes.TNT = ItemTypeBuilder
                .builder(ItemTntStack.class)
                .vanillaItem(ItemId.TNT)
                .build();
    }

    public static void initTntMinecart() {
        if (ItemTypes.TNT_MINECART != null) return;
        ItemTypes.TNT_MINECART = ItemTypeBuilder
                .builder(ItemTntMinecartStack.class)
                .vanillaItem(ItemId.TNT_MINECART)
                .build();
    }

    public static void initTorch() {
        if (ItemTypes.TORCH != null) return;
        ItemTypes.TORCH = ItemTypeBuilder
                .builder(ItemTorchStack.class)
                .vanillaItem(ItemId.TORCH)
                .build();
    }

    public static void initTorchflower() {
        if (ItemTypes.TORCHFLOWER != null) return;
        ItemTypes.TORCHFLOWER = ItemTypeBuilder
                .builder(ItemTorchflowerStack.class)
                .vanillaItem(ItemId.TORCHFLOWER)
                .build();
    }

    public static void initTorchflowerCrop() {
        if (ItemTypes.TORCHFLOWER_CROP != null) return;
        ItemTypes.TORCHFLOWER_CROP = ItemTypeBuilder
                .builder(ItemTorchflowerCropStack.class)
                .vanillaItem(ItemId.TORCHFLOWER_CROP)
                .build();
    }

    public static void initTorchflowerSeeds() {
        if (ItemTypes.TORCHFLOWER_SEEDS != null) return;
        ItemTypes.TORCHFLOWER_SEEDS = ItemTypeBuilder
                .builder(ItemTorchflowerSeedsStack.class)
                .vanillaItem(ItemId.TORCHFLOWER_SEEDS)
                .build();
    }

    public static void initTotemOfUndying() {
        if (ItemTypes.TOTEM_OF_UNDYING != null) return;
        ItemTypes.TOTEM_OF_UNDYING = ItemTypeBuilder
                .builder(ItemTotemOfUndyingStack.class)
                .vanillaItem(ItemId.TOTEM_OF_UNDYING)
                .build();
    }

    public static void initTraderLlamaSpawnEgg() {
        if (ItemTypes.TRADER_LLAMA_SPAWN_EGG != null) return;
        ItemTypes.TRADER_LLAMA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTraderLlamaSpawnEggStack.class)
                .vanillaItem(ItemId.TRADER_LLAMA_SPAWN_EGG)
                .build();
    }

    public static void initTrapdoor() {
        if (ItemTypes.TRAPDOOR != null) return;
        ItemTypes.TRAPDOOR = ItemTypeBuilder
                .builder(ItemTrapdoorStack.class)
                .vanillaItem(ItemId.TRAPDOOR)
                .build();
    }

    public static void initTrappedChest() {
        if (ItemTypes.TRAPPED_CHEST != null) return;
        ItemTypes.TRAPPED_CHEST = ItemTypeBuilder
                .builder(ItemTrappedChestStack.class)
                .vanillaItem(ItemId.TRAPPED_CHEST)
                .build();
    }

    public static void initTrialKey() {
        if (ItemTypes.TRIAL_KEY != null) return;
        ItemTypes.TRIAL_KEY = ItemTypeBuilder
                .builder(ItemTrialKeyStack.class)
                .vanillaItem(ItemId.TRIAL_KEY)
                .build();
    }

    public static void initTrialSpawner() {
        if (ItemTypes.TRIAL_SPAWNER != null) return;
        ItemTypes.TRIAL_SPAWNER = ItemTypeBuilder
                .builder(ItemTrialSpawnerStack.class)
                .vanillaItem(ItemId.TRIAL_SPAWNER)
                .build();
    }

    public static void initTrident() {
        if (ItemTypes.TRIDENT != null) return;
        ItemTypes.TRIDENT = ItemTypeBuilder
                .builder(ItemTridentStack.class)
                .vanillaItem(ItemId.TRIDENT)
                .build();
    }

    public static void initTripWire() {
        if (ItemTypes.TRIP_WIRE != null) return;
        ItemTypes.TRIP_WIRE = ItemTypeBuilder
                .builder(ItemTripWireStack.class)
                .vanillaItem(ItemId.TRIP_WIRE)
                .build();
    }

    public static void initTripwireHook() {
        if (ItemTypes.TRIPWIRE_HOOK != null) return;
        ItemTypes.TRIPWIRE_HOOK = ItemTypeBuilder
                .builder(ItemTripwireHookStack.class)
                .vanillaItem(ItemId.TRIPWIRE_HOOK)
                .build();
    }

    public static void initTropicalFish() {
        if (ItemTypes.TROPICAL_FISH != null) return;
        ItemTypes.TROPICAL_FISH = ItemTypeBuilder
                .builder(ItemTropicalFishStack.class)
                .vanillaItem(ItemId.TROPICAL_FISH)
                .build();
    }

    public static void initTropicalFishBucket() {
        if (ItemTypes.TROPICAL_FISH_BUCKET != null) return;
        ItemTypes.TROPICAL_FISH_BUCKET = ItemTypeBuilder
                .builder(ItemTropicalFishBucketStack.class)
                .vanillaItem(ItemId.TROPICAL_FISH_BUCKET)
                .build();
    }

    public static void initTropicalFishSpawnEgg() {
        if (ItemTypes.TROPICAL_FISH_SPAWN_EGG != null) return;
        ItemTypes.TROPICAL_FISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTropicalFishSpawnEggStack.class)
                .vanillaItem(ItemId.TROPICAL_FISH_SPAWN_EGG)
                .build();
    }

    public static void initTubeCoral() {
        if (ItemTypes.TUBE_CORAL != null) return;
        ItemTypes.TUBE_CORAL = ItemTypeBuilder
                .builder(ItemTubeCoralStack.class)
                .vanillaItem(ItemId.TUBE_CORAL)
                .build();
    }

    public static void initTubeCoralBlock() {
        if (ItemTypes.TUBE_CORAL_BLOCK != null) return;
        ItemTypes.TUBE_CORAL_BLOCK = ItemTypeBuilder
                .builder(ItemTubeCoralBlockStack.class)
                .vanillaItem(ItemId.TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initTubeCoralFan() {
        if (ItemTypes.TUBE_CORAL_FAN != null) return;
        ItemTypes.TUBE_CORAL_FAN = ItemTypeBuilder
                .builder(ItemTubeCoralFanStack.class)
                .vanillaItem(ItemId.TUBE_CORAL_FAN)
                .build();
    }

    public static void initTubeCoralWallFan() {
        if (ItemTypes.TUBE_CORAL_WALL_FAN != null) return;
        ItemTypes.TUBE_CORAL_WALL_FAN = ItemTypeBuilder
                .builder(ItemTubeCoralWallFanStack.class)
                .vanillaItem(ItemId.TUBE_CORAL_WALL_FAN)
                .build();
    }

    public static void initTuff() {
        if (ItemTypes.TUFF != null) return;
        ItemTypes.TUFF = ItemTypeBuilder
                .builder(ItemTuffStack.class)
                .vanillaItem(ItemId.TUFF)
                .build();
    }

    public static void initTuffBrickDoubleSlab() {
        if (ItemTypes.TUFF_BRICK_DOUBLE_SLAB != null) return;
        ItemTypes.TUFF_BRICK_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemTuffBrickDoubleSlabStack.class)
                .vanillaItem(ItemId.TUFF_BRICK_DOUBLE_SLAB)
                .build();
    }

    public static void initTuffBrickSlab() {
        if (ItemTypes.TUFF_BRICK_SLAB != null) return;
        ItemTypes.TUFF_BRICK_SLAB = ItemTypeBuilder
                .builder(ItemTuffBrickSlabStack.class)
                .vanillaItem(ItemId.TUFF_BRICK_SLAB)
                .build();
    }

    public static void initTuffBrickStairs() {
        if (ItemTypes.TUFF_BRICK_STAIRS != null) return;
        ItemTypes.TUFF_BRICK_STAIRS = ItemTypeBuilder
                .builder(ItemTuffBrickStairsStack.class)
                .vanillaItem(ItemId.TUFF_BRICK_STAIRS)
                .build();
    }

    public static void initTuffBrickWall() {
        if (ItemTypes.TUFF_BRICK_WALL != null) return;
        ItemTypes.TUFF_BRICK_WALL = ItemTypeBuilder
                .builder(ItemTuffBrickWallStack.class)
                .vanillaItem(ItemId.TUFF_BRICK_WALL)
                .build();
    }

    public static void initTuffBricks() {
        if (ItemTypes.TUFF_BRICKS != null) return;
        ItemTypes.TUFF_BRICKS = ItemTypeBuilder
                .builder(ItemTuffBricksStack.class)
                .vanillaItem(ItemId.TUFF_BRICKS)
                .build();
    }

    public static void initTuffDoubleSlab() {
        if (ItemTypes.TUFF_DOUBLE_SLAB != null) return;
        ItemTypes.TUFF_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemTuffDoubleSlabStack.class)
                .vanillaItem(ItemId.TUFF_DOUBLE_SLAB)
                .build();
    }

    public static void initTuffSlab() {
        if (ItemTypes.TUFF_SLAB != null) return;
        ItemTypes.TUFF_SLAB = ItemTypeBuilder
                .builder(ItemTuffSlabStack.class)
                .vanillaItem(ItemId.TUFF_SLAB)
                .build();
    }

    public static void initTuffStairs() {
        if (ItemTypes.TUFF_STAIRS != null) return;
        ItemTypes.TUFF_STAIRS = ItemTypeBuilder
                .builder(ItemTuffStairsStack.class)
                .vanillaItem(ItemId.TUFF_STAIRS)
                .build();
    }

    public static void initTuffWall() {
        if (ItemTypes.TUFF_WALL != null) return;
        ItemTypes.TUFF_WALL = ItemTypeBuilder
                .builder(ItemTuffWallStack.class)
                .vanillaItem(ItemId.TUFF_WALL)
                .build();
    }

    public static void initTurtleEgg() {
        if (ItemTypes.TURTLE_EGG != null) return;
        ItemTypes.TURTLE_EGG = ItemTypeBuilder
                .builder(ItemTurtleEggStack.class)
                .vanillaItem(ItemId.TURTLE_EGG)
                .build();
    }

    public static void initTurtleHelmet() {
        if (ItemTypes.TURTLE_HELMET != null) return;
        ItemTypes.TURTLE_HELMET = ItemTypeBuilder
                .builder(ItemTurtleHelmetStack.class)
                .vanillaItem(ItemId.TURTLE_HELMET)
                .build();
    }

    public static void initTurtleScute() {
        if (ItemTypes.TURTLE_SCUTE != null) return;
        ItemTypes.TURTLE_SCUTE = ItemTypeBuilder
                .builder(ItemTurtleScuteStack.class)
                .vanillaItem(ItemId.TURTLE_SCUTE)
                .build();
    }

    public static void initTurtleSpawnEgg() {
        if (ItemTypes.TURTLE_SPAWN_EGG != null) return;
        ItemTypes.TURTLE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTurtleSpawnEggStack.class)
                .vanillaItem(ItemId.TURTLE_SPAWN_EGG)
                .build();
    }

    public static void initTwistingVines() {
        if (ItemTypes.TWISTING_VINES != null) return;
        ItemTypes.TWISTING_VINES = ItemTypeBuilder
                .builder(ItemTwistingVinesStack.class)
                .vanillaItem(ItemId.TWISTING_VINES)
                .build();
    }

    public static void initUnderwaterTorch() {
        if (ItemTypes.UNDERWATER_TORCH != null) return;
        ItemTypes.UNDERWATER_TORCH = ItemTypeBuilder
                .builder(ItemUnderwaterTorchStack.class)
                .vanillaItem(ItemId.UNDERWATER_TORCH)
                .build();
    }

    public static void initUndyedShulkerBox() {
        if (ItemTypes.UNDYED_SHULKER_BOX != null) return;
        ItemTypes.UNDYED_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemUndyedShulkerBoxStack.class)
                .vanillaItem(ItemId.UNDYED_SHULKER_BOX)
                .build();
    }

    public static void initUnknown() {
        if (ItemTypes.UNKNOWN != null) return;
        ItemTypes.UNKNOWN = ItemTypeBuilder
                .builder(ItemUnknownStack.class)
                .vanillaItem(ItemId.UNKNOWN)
                .build();
    }

    public static void initUnlitRedstoneTorch() {
        if (ItemTypes.UNLIT_REDSTONE_TORCH != null) return;
        ItemTypes.UNLIT_REDSTONE_TORCH = ItemTypeBuilder
                .builder(ItemUnlitRedstoneTorchStack.class)
                .vanillaItem(ItemId.UNLIT_REDSTONE_TORCH)
                .build();
    }

    public static void initUnpoweredComparator() {
        if (ItemTypes.UNPOWERED_COMPARATOR != null) return;
        ItemTypes.UNPOWERED_COMPARATOR = ItemTypeBuilder
                .builder(ItemUnpoweredComparatorStack.class)
                .vanillaItem(ItemId.UNPOWERED_COMPARATOR)
                .build();
    }

    public static void initUnpoweredRepeater() {
        if (ItemTypes.UNPOWERED_REPEATER != null) return;
        ItemTypes.UNPOWERED_REPEATER = ItemTypeBuilder
                .builder(ItemUnpoweredRepeaterStack.class)
                .vanillaItem(ItemId.UNPOWERED_REPEATER)
                .build();
    }

    public static void initVault() {
        if (ItemTypes.VAULT != null) return;
        ItemTypes.VAULT = ItemTypeBuilder
                .builder(ItemVaultStack.class)
                .vanillaItem(ItemId.VAULT)
                .build();
    }

    public static void initVerdantFroglight() {
        if (ItemTypes.VERDANT_FROGLIGHT != null) return;
        ItemTypes.VERDANT_FROGLIGHT = ItemTypeBuilder
                .builder(ItemVerdantFroglightStack.class)
                .vanillaItem(ItemId.VERDANT_FROGLIGHT)
                .build();
    }

    public static void initVexArmorTrimSmithingTemplate() {
        if (ItemTypes.VEX_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.VEX_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemVexArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.VEX_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initVexSpawnEgg() {
        if (ItemTypes.VEX_SPAWN_EGG != null) return;
        ItemTypes.VEX_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVexSpawnEggStack.class)
                .vanillaItem(ItemId.VEX_SPAWN_EGG)
                .build();
    }

    public static void initVillagerSpawnEgg() {
        if (ItemTypes.VILLAGER_SPAWN_EGG != null) return;
        ItemTypes.VILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVillagerSpawnEggStack.class)
                .vanillaItem(ItemId.VILLAGER_SPAWN_EGG)
                .build();
    }

    public static void initVindicatorSpawnEgg() {
        if (ItemTypes.VINDICATOR_SPAWN_EGG != null) return;
        ItemTypes.VINDICATOR_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVindicatorSpawnEggStack.class)
                .vanillaItem(ItemId.VINDICATOR_SPAWN_EGG)
                .build();
    }

    public static void initVine() {
        if (ItemTypes.VINE != null) return;
        ItemTypes.VINE = ItemTypeBuilder
                .builder(ItemVineStack.class)
                .vanillaItem(ItemId.VINE)
                .build();
    }

    public static void initWallBanner() {
        if (ItemTypes.WALL_BANNER != null) return;
        ItemTypes.WALL_BANNER = ItemTypeBuilder
                .builder(ItemWallBannerStack.class)
                .vanillaItem(ItemId.WALL_BANNER)
                .build();
    }

    public static void initWallSign() {
        if (ItemTypes.WALL_SIGN != null) return;
        ItemTypes.WALL_SIGN = ItemTypeBuilder
                .builder(ItemWallSignStack.class)
                .vanillaItem(ItemId.WALL_SIGN)
                .build();
    }

    public static void initWanderingTraderSpawnEgg() {
        if (ItemTypes.WANDERING_TRADER_SPAWN_EGG != null) return;
        ItemTypes.WANDERING_TRADER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWanderingTraderSpawnEggStack.class)
                .vanillaItem(ItemId.WANDERING_TRADER_SPAWN_EGG)
                .build();
    }

    public static void initWardArmorTrimSmithingTemplate() {
        if (ItemTypes.WARD_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.WARD_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemWardArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initWardenSpawnEgg() {
        if (ItemTypes.WARDEN_SPAWN_EGG != null) return;
        ItemTypes.WARDEN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWardenSpawnEggStack.class)
                .vanillaItem(ItemId.WARDEN_SPAWN_EGG)
                .build();
    }

    public static void initWarpedButton() {
        if (ItemTypes.WARPED_BUTTON != null) return;
        ItemTypes.WARPED_BUTTON = ItemTypeBuilder
                .builder(ItemWarpedButtonStack.class)
                .vanillaItem(ItemId.WARPED_BUTTON)
                .build();
    }

    public static void initWarpedDoor() {
        if (ItemTypes.WARPED_DOOR != null) return;
        ItemTypes.WARPED_DOOR = ItemTypeBuilder
                .builder(ItemWarpedDoorStack.class)
                .vanillaItem(ItemId.WARPED_DOOR)
                .build();
    }

    public static void initWarpedDoubleSlab() {
        if (ItemTypes.WARPED_DOUBLE_SLAB != null) return;
        ItemTypes.WARPED_DOUBLE_SLAB = ItemTypeBuilder
                .builder(ItemWarpedDoubleSlabStack.class)
                .vanillaItem(ItemId.WARPED_DOUBLE_SLAB)
                .build();
    }

    public static void initWarpedFence() {
        if (ItemTypes.WARPED_FENCE != null) return;
        ItemTypes.WARPED_FENCE = ItemTypeBuilder
                .builder(ItemWarpedFenceStack.class)
                .vanillaItem(ItemId.WARPED_FENCE)
                .build();
    }

    public static void initWarpedFenceGate() {
        if (ItemTypes.WARPED_FENCE_GATE != null) return;
        ItemTypes.WARPED_FENCE_GATE = ItemTypeBuilder
                .builder(ItemWarpedFenceGateStack.class)
                .vanillaItem(ItemId.WARPED_FENCE_GATE)
                .build();
    }

    public static void initWarpedFungus() {
        if (ItemTypes.WARPED_FUNGUS != null) return;
        ItemTypes.WARPED_FUNGUS = ItemTypeBuilder
                .builder(ItemWarpedFungusStack.class)
                .vanillaItem(ItemId.WARPED_FUNGUS)
                .build();
    }

    public static void initWarpedFungusOnAStick() {
        if (ItemTypes.WARPED_FUNGUS_ON_A_STICK != null) return;
        ItemTypes.WARPED_FUNGUS_ON_A_STICK = ItemTypeBuilder
                .builder(ItemWarpedFungusOnAStickStack.class)
                .vanillaItem(ItemId.WARPED_FUNGUS_ON_A_STICK)
                .build();
    }

    public static void initWarpedHangingSign() {
        if (ItemTypes.WARPED_HANGING_SIGN != null) return;
        ItemTypes.WARPED_HANGING_SIGN = ItemTypeBuilder
                .builder(ItemWarpedHangingSignStack.class)
                .vanillaItem(ItemId.WARPED_HANGING_SIGN)
                .build();
    }

    public static void initWarpedHyphae() {
        if (ItemTypes.WARPED_HYPHAE != null) return;
        ItemTypes.WARPED_HYPHAE = ItemTypeBuilder
                .builder(ItemWarpedHyphaeStack.class)
                .vanillaItem(ItemId.WARPED_HYPHAE)
                .build();
    }

    public static void initWarpedNylium() {
        if (ItemTypes.WARPED_NYLIUM != null) return;
        ItemTypes.WARPED_NYLIUM = ItemTypeBuilder
                .builder(ItemWarpedNyliumStack.class)
                .vanillaItem(ItemId.WARPED_NYLIUM)
                .build();
    }

    public static void initWarpedPlanks() {
        if (ItemTypes.WARPED_PLANKS != null) return;
        ItemTypes.WARPED_PLANKS = ItemTypeBuilder
                .builder(ItemWarpedPlanksStack.class)
                .vanillaItem(ItemId.WARPED_PLANKS)
                .build();
    }

    public static void initWarpedPressurePlate() {
        if (ItemTypes.WARPED_PRESSURE_PLATE != null) return;
        ItemTypes.WARPED_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemWarpedPressurePlateStack.class)
                .vanillaItem(ItemId.WARPED_PRESSURE_PLATE)
                .build();
    }

    public static void initWarpedRoots() {
        if (ItemTypes.WARPED_ROOTS != null) return;
        ItemTypes.WARPED_ROOTS = ItemTypeBuilder
                .builder(ItemWarpedRootsStack.class)
                .vanillaItem(ItemId.WARPED_ROOTS)
                .build();
    }

    public static void initWarpedSign() {
        if (ItemTypes.WARPED_SIGN != null) return;
        ItemTypes.WARPED_SIGN = ItemTypeBuilder
                .builder(ItemWarpedSignStack.class)
                .vanillaItem(ItemId.WARPED_SIGN)
                .build();
    }

    public static void initWarpedSlab() {
        if (ItemTypes.WARPED_SLAB != null) return;
        ItemTypes.WARPED_SLAB = ItemTypeBuilder
                .builder(ItemWarpedSlabStack.class)
                .vanillaItem(ItemId.WARPED_SLAB)
                .build();
    }

    public static void initWarpedStairs() {
        if (ItemTypes.WARPED_STAIRS != null) return;
        ItemTypes.WARPED_STAIRS = ItemTypeBuilder
                .builder(ItemWarpedStairsStack.class)
                .vanillaItem(ItemId.WARPED_STAIRS)
                .build();
    }

    public static void initWarpedStandingSign() {
        if (ItemTypes.WARPED_STANDING_SIGN != null) return;
        ItemTypes.WARPED_STANDING_SIGN = ItemTypeBuilder
                .builder(ItemWarpedStandingSignStack.class)
                .vanillaItem(ItemId.WARPED_STANDING_SIGN)
                .build();
    }

    public static void initWarpedStem() {
        if (ItemTypes.WARPED_STEM != null) return;
        ItemTypes.WARPED_STEM = ItemTypeBuilder
                .builder(ItemWarpedStemStack.class)
                .vanillaItem(ItemId.WARPED_STEM)
                .build();
    }

    public static void initWarpedTrapdoor() {
        if (ItemTypes.WARPED_TRAPDOOR != null) return;
        ItemTypes.WARPED_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWarpedTrapdoorStack.class)
                .vanillaItem(ItemId.WARPED_TRAPDOOR)
                .build();
    }

    public static void initWarpedWallSign() {
        if (ItemTypes.WARPED_WALL_SIGN != null) return;
        ItemTypes.WARPED_WALL_SIGN = ItemTypeBuilder
                .builder(ItemWarpedWallSignStack.class)
                .vanillaItem(ItemId.WARPED_WALL_SIGN)
                .build();
    }

    public static void initWarpedWartBlock() {
        if (ItemTypes.WARPED_WART_BLOCK != null) return;
        ItemTypes.WARPED_WART_BLOCK = ItemTypeBuilder
                .builder(ItemWarpedWartBlockStack.class)
                .vanillaItem(ItemId.WARPED_WART_BLOCK)
                .build();
    }

    public static void initWater() {
        if (ItemTypes.WATER != null) return;
        ItemTypes.WATER = ItemTypeBuilder
                .builder(ItemWaterStack.class)
                .vanillaItem(ItemId.WATER)
                .build();
    }

    public static void initWaterBucket() {
        if (ItemTypes.WATER_BUCKET != null) return;
        ItemTypes.WATER_BUCKET = ItemTypeBuilder
                .builder(ItemWaterBucketStack.class)
                .vanillaItem(ItemId.WATER_BUCKET)
                .build();
    }

    public static void initWaterlily() {
        if (ItemTypes.WATERLILY != null) return;
        ItemTypes.WATERLILY = ItemTypeBuilder
                .builder(ItemWaterlilyStack.class)
                .vanillaItem(ItemId.WATERLILY)
                .build();
    }

    public static void initWaxedChiseledCopper() {
        if (ItemTypes.WAXED_CHISELED_COPPER != null) return;
        ItemTypes.WAXED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedChiseledCopperStack.class)
                .vanillaItem(ItemId.WAXED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedCopper() {
        if (ItemTypes.WAXED_COPPER != null) return;
        ItemTypes.WAXED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedCopperStack.class)
                .vanillaItem(ItemId.WAXED_COPPER)
                .build();
    }

    public static void initWaxedCopperBulb() {
        if (ItemTypes.WAXED_COPPER_BULB != null) return;
        ItemTypes.WAXED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemWaxedCopperBulbStack.class)
                .vanillaItem(ItemId.WAXED_COPPER_BULB)
                .build();
    }

    public static void initWaxedCopperDoor() {
        if (ItemTypes.WAXED_COPPER_DOOR != null) return;
        ItemTypes.WAXED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemWaxedCopperDoorStack.class)
                .vanillaItem(ItemId.WAXED_COPPER_DOOR)
                .build();
    }

    public static void initWaxedCopperGrate() {
        if (ItemTypes.WAXED_COPPER_GRATE != null) return;
        ItemTypes.WAXED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemWaxedCopperGrateStack.class)
                .vanillaItem(ItemId.WAXED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedCopperTrapdoor() {
        if (ItemTypes.WAXED_COPPER_TRAPDOOR != null) return;
        ItemTypes.WAXED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWaxedCopperTrapdoorStack.class)
                .vanillaItem(ItemId.WAXED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initWaxedCutCopper() {
        if (ItemTypes.WAXED_CUT_COPPER != null) return;
        ItemTypes.WAXED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemWaxedCutCopperStack.class)
                .vanillaItem(ItemId.WAXED_CUT_COPPER)
                .build();
    }

    public static void initWaxedCutCopperSlab() {
        if (ItemTypes.WAXED_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedCutCopperStairs() {
        if (ItemTypes.WAXED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.WAXED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemWaxedCutCopperStairsStack.class)
                .vanillaItem(ItemId.WAXED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initWaxedDoubleCutCopperSlab() {
        if (ItemTypes.WAXED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedExposedChiseledCopper() {
        if (ItemTypes.WAXED_EXPOSED_CHISELED_COPPER != null) return;
        ItemTypes.WAXED_EXPOSED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedExposedChiseledCopperStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopper() {
        if (ItemTypes.WAXED_EXPOSED_COPPER != null) return;
        ItemTypes.WAXED_EXPOSED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedExposedCopperStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopperBulb() {
        if (ItemTypes.WAXED_EXPOSED_COPPER_BULB != null) return;
        ItemTypes.WAXED_EXPOSED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemWaxedExposedCopperBulbStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_BULB)
                .build();
    }

    public static void initWaxedExposedCopperDoor() {
        if (ItemTypes.WAXED_EXPOSED_COPPER_DOOR != null) return;
        ItemTypes.WAXED_EXPOSED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemWaxedExposedCopperDoorStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_DOOR)
                .build();
    }

    public static void initWaxedExposedCopperGrate() {
        if (ItemTypes.WAXED_EXPOSED_COPPER_GRATE != null) return;
        ItemTypes.WAXED_EXPOSED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemWaxedExposedCopperGrateStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedExposedCopperTrapdoor() {
        if (ItemTypes.WAXED_EXPOSED_COPPER_TRAPDOOR != null) return;
        ItemTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWaxedExposedCopperTrapdoorStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initWaxedExposedCutCopper() {
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER != null) return;
        ItemTypes.WAXED_EXPOSED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemWaxedExposedCutCopperStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initWaxedExposedCutCopperSlab() {
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedExposedCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedExposedCutCopperStairs() {
        if (ItemTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemWaxedExposedCutCopperStairsStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initWaxedExposedDoubleCutCopperSlab() {
        if (ItemTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedExposedDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedOxidizedChiseledCopper() {
        if (ItemTypes.WAXED_OXIDIZED_CHISELED_COPPER != null) return;
        ItemTypes.WAXED_OXIDIZED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedOxidizedChiseledCopperStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopper() {
        if (ItemTypes.WAXED_OXIDIZED_COPPER != null) return;
        ItemTypes.WAXED_OXIDIZED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCopperStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopperBulb() {
        if (ItemTypes.WAXED_OXIDIZED_COPPER_BULB != null) return;
        ItemTypes.WAXED_OXIDIZED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCopperBulbStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_BULB)
                .build();
    }

    public static void initWaxedOxidizedCopperDoor() {
        if (ItemTypes.WAXED_OXIDIZED_COPPER_DOOR != null) return;
        ItemTypes.WAXED_OXIDIZED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCopperDoorStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_DOOR)
                .build();
    }

    public static void initWaxedOxidizedCopperGrate() {
        if (ItemTypes.WAXED_OXIDIZED_COPPER_GRATE != null) return;
        ItemTypes.WAXED_OXIDIZED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCopperGrateStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedOxidizedCopperTrapdoor() {
        if (ItemTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR != null) return;
        ItemTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCopperTrapdoorStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initWaxedOxidizedCutCopper() {
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER != null) return;
        ItemTypes.WAXED_OXIDIZED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCutCopperStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCutCopperSlab() {
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedOxidizedCutCopperStairs() {
        if (ItemTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemWaxedOxidizedCutCopperStairsStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initWaxedOxidizedDoubleCutCopperSlab() {
        if (ItemTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedOxidizedDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedWeatheredChiseledCopper() {
        if (ItemTypes.WAXED_WEATHERED_CHISELED_COPPER != null) return;
        ItemTypes.WAXED_WEATHERED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedWeatheredChiseledCopperStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopper() {
        if (ItemTypes.WAXED_WEATHERED_COPPER != null) return;
        ItemTypes.WAXED_WEATHERED_COPPER = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCopperStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopperBulb() {
        if (ItemTypes.WAXED_WEATHERED_COPPER_BULB != null) return;
        ItemTypes.WAXED_WEATHERED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCopperBulbStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_BULB)
                .build();
    }

    public static void initWaxedWeatheredCopperDoor() {
        if (ItemTypes.WAXED_WEATHERED_COPPER_DOOR != null) return;
        ItemTypes.WAXED_WEATHERED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCopperDoorStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_DOOR)
                .build();
    }

    public static void initWaxedWeatheredCopperGrate() {
        if (ItemTypes.WAXED_WEATHERED_COPPER_GRATE != null) return;
        ItemTypes.WAXED_WEATHERED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCopperGrateStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedWeatheredCopperTrapdoor() {
        if (ItemTypes.WAXED_WEATHERED_COPPER_TRAPDOOR != null) return;
        ItemTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCopperTrapdoorStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initWaxedWeatheredCutCopper() {
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER != null) return;
        ItemTypes.WAXED_WEATHERED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCutCopperStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCutCopperSlab() {
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWaxedWeatheredCutCopperStairs() {
        if (ItemTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemWaxedWeatheredCutCopperStairsStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initWaxedWeatheredDoubleCutCopperSlab() {
        if (ItemTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWaxedWeatheredDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWayfinderArmorTrimSmithingTemplate() {
        if (ItemTypes.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemWayfinderArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initWeatheredChiseledCopper() {
        if (ItemTypes.WEATHERED_CHISELED_COPPER != null) return;
        ItemTypes.WEATHERED_CHISELED_COPPER = ItemTypeBuilder
                .builder(ItemWeatheredChiseledCopperStack.class)
                .vanillaItem(ItemId.WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWeatheredCopper() {
        if (ItemTypes.WEATHERED_COPPER != null) return;
        ItemTypes.WEATHERED_COPPER = ItemTypeBuilder
                .builder(ItemWeatheredCopperStack.class)
                .vanillaItem(ItemId.WEATHERED_COPPER)
                .build();
    }

    public static void initWeatheredCopperBulb() {
        if (ItemTypes.WEATHERED_COPPER_BULB != null) return;
        ItemTypes.WEATHERED_COPPER_BULB = ItemTypeBuilder
                .builder(ItemWeatheredCopperBulbStack.class)
                .vanillaItem(ItemId.WEATHERED_COPPER_BULB)
                .build();
    }

    public static void initWeatheredCopperDoor() {
        if (ItemTypes.WEATHERED_COPPER_DOOR != null) return;
        ItemTypes.WEATHERED_COPPER_DOOR = ItemTypeBuilder
                .builder(ItemWeatheredCopperDoorStack.class)
                .vanillaItem(ItemId.WEATHERED_COPPER_DOOR)
                .build();
    }

    public static void initWeatheredCopperGrate() {
        if (ItemTypes.WEATHERED_COPPER_GRATE != null) return;
        ItemTypes.WEATHERED_COPPER_GRATE = ItemTypeBuilder
                .builder(ItemWeatheredCopperGrateStack.class)
                .vanillaItem(ItemId.WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWeatheredCopperTrapdoor() {
        if (ItemTypes.WEATHERED_COPPER_TRAPDOOR != null) return;
        ItemTypes.WEATHERED_COPPER_TRAPDOOR = ItemTypeBuilder
                .builder(ItemWeatheredCopperTrapdoorStack.class)
                .vanillaItem(ItemId.WEATHERED_COPPER_TRAPDOOR)
                .build();
    }

    public static void initWeatheredCutCopper() {
        if (ItemTypes.WEATHERED_CUT_COPPER != null) return;
        ItemTypes.WEATHERED_CUT_COPPER = ItemTypeBuilder
                .builder(ItemWeatheredCutCopperStack.class)
                .vanillaItem(ItemId.WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWeatheredCutCopperSlab() {
        if (ItemTypes.WEATHERED_CUT_COPPER_SLAB != null) return;
        ItemTypes.WEATHERED_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWeatheredCutCopperSlabStack.class)
                .vanillaItem(ItemId.WEATHERED_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWeatheredCutCopperStairs() {
        if (ItemTypes.WEATHERED_CUT_COPPER_STAIRS != null) return;
        ItemTypes.WEATHERED_CUT_COPPER_STAIRS = ItemTypeBuilder
                .builder(ItemWeatheredCutCopperStairsStack.class)
                .vanillaItem(ItemId.WEATHERED_CUT_COPPER_STAIRS)
                .build();
    }

    public static void initWeatheredDoubleCutCopperSlab() {
        if (ItemTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        ItemTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = ItemTypeBuilder
                .builder(ItemWeatheredDoubleCutCopperSlabStack.class)
                .vanillaItem(ItemId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .build();
    }

    public static void initWeb() {
        if (ItemTypes.WEB != null) return;
        ItemTypes.WEB = ItemTypeBuilder
                .builder(ItemWebStack.class)
                .vanillaItem(ItemId.WEB)
                .build();
    }

    public static void initWeepingVines() {
        if (ItemTypes.WEEPING_VINES != null) return;
        ItemTypes.WEEPING_VINES = ItemTypeBuilder
                .builder(ItemWeepingVinesStack.class)
                .vanillaItem(ItemId.WEEPING_VINES)
                .build();
    }

    public static void initWheat() {
        if (ItemTypes.WHEAT != null) return;
        ItemTypes.WHEAT = ItemTypeBuilder
                .builder(ItemWheatStack.class)
                .vanillaItem(ItemId.WHEAT)
                .build();
    }

    public static void initWheatSeeds() {
        if (ItemTypes.WHEAT_SEEDS != null) return;
        ItemTypes.WHEAT_SEEDS = ItemTypeBuilder
                .builder(ItemWheatSeedsStack.class)
                .vanillaItem(ItemId.WHEAT_SEEDS)
                .build();
    }

    public static void initWhiteCandle() {
        if (ItemTypes.WHITE_CANDLE != null) return;
        ItemTypes.WHITE_CANDLE = ItemTypeBuilder
                .builder(ItemWhiteCandleStack.class)
                .vanillaItem(ItemId.WHITE_CANDLE)
                .build();
    }

    public static void initWhiteCandleCake() {
        if (ItemTypes.WHITE_CANDLE_CAKE != null) return;
        ItemTypes.WHITE_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemWhiteCandleCakeStack.class)
                .vanillaItem(ItemId.WHITE_CANDLE_CAKE)
                .build();
    }

    public static void initWhiteCarpet() {
        if (ItemTypes.WHITE_CARPET != null) return;
        ItemTypes.WHITE_CARPET = ItemTypeBuilder
                .builder(ItemWhiteCarpetStack.class)
                .vanillaItem(ItemId.WHITE_CARPET)
                .build();
    }

    public static void initWhiteConcrete() {
        if (ItemTypes.WHITE_CONCRETE != null) return;
        ItemTypes.WHITE_CONCRETE = ItemTypeBuilder
                .builder(ItemWhiteConcreteStack.class)
                .vanillaItem(ItemId.WHITE_CONCRETE)
                .build();
    }

    public static void initWhiteConcretePowder() {
        if (ItemTypes.WHITE_CONCRETE_POWDER != null) return;
        ItemTypes.WHITE_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemWhiteConcretePowderStack.class)
                .vanillaItem(ItemId.WHITE_CONCRETE_POWDER)
                .build();
    }

    public static void initWhiteDye() {
        if (ItemTypes.WHITE_DYE != null) return;
        ItemTypes.WHITE_DYE = ItemTypeBuilder
                .builder(ItemWhiteDyeStack.class)
                .vanillaItem(ItemId.WHITE_DYE)
                .build();
    }

    public static void initWhiteGlazedTerracotta() {
        if (ItemTypes.WHITE_GLAZED_TERRACOTTA != null) return;
        ItemTypes.WHITE_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemWhiteGlazedTerracottaStack.class)
                .vanillaItem(ItemId.WHITE_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initWhiteShulkerBox() {
        if (ItemTypes.WHITE_SHULKER_BOX != null) return;
        ItemTypes.WHITE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemWhiteShulkerBoxStack.class)
                .vanillaItem(ItemId.WHITE_SHULKER_BOX)
                .build();
    }

    public static void initWhiteStainedGlass() {
        if (ItemTypes.WHITE_STAINED_GLASS != null) return;
        ItemTypes.WHITE_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemWhiteStainedGlassStack.class)
                .vanillaItem(ItemId.WHITE_STAINED_GLASS)
                .build();
    }

    public static void initWhiteStainedGlassPane() {
        if (ItemTypes.WHITE_STAINED_GLASS_PANE != null) return;
        ItemTypes.WHITE_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemWhiteStainedGlassPaneStack.class)
                .vanillaItem(ItemId.WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initWhiteTerracotta() {
        if (ItemTypes.WHITE_TERRACOTTA != null) return;
        ItemTypes.WHITE_TERRACOTTA = ItemTypeBuilder
                .builder(ItemWhiteTerracottaStack.class)
                .vanillaItem(ItemId.WHITE_TERRACOTTA)
                .build();
    }

    public static void initWhiteTulip() {
        if (ItemTypes.WHITE_TULIP != null) return;
        ItemTypes.WHITE_TULIP = ItemTypeBuilder
                .builder(ItemWhiteTulipStack.class)
                .vanillaItem(ItemId.WHITE_TULIP)
                .build();
    }

    public static void initWhiteWool() {
        if (ItemTypes.WHITE_WOOL != null) return;
        ItemTypes.WHITE_WOOL = ItemTypeBuilder
                .builder(ItemWhiteWoolStack.class)
                .vanillaItem(ItemId.WHITE_WOOL)
                .build();
    }

    public static void initWildArmorTrimSmithingTemplate() {
        if (ItemTypes.WILD_ARMOR_TRIM_SMITHING_TEMPLATE != null) return;
        ItemTypes.WILD_ARMOR_TRIM_SMITHING_TEMPLATE = ItemTypeBuilder
                .builder(ItemWildArmorTrimSmithingTemplateStack.class)
                .vanillaItem(ItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)
                .build();
    }

    public static void initWindCharge() {
        if (ItemTypes.WIND_CHARGE != null) return;
        ItemTypes.WIND_CHARGE = ItemTypeBuilder
                .builder(ItemWindChargeStack.class)
                .vanillaItem(ItemId.WIND_CHARGE)
                .build();
    }

    public static void initWitchSpawnEgg() {
        if (ItemTypes.WITCH_SPAWN_EGG != null) return;
        ItemTypes.WITCH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitchSpawnEggStack.class)
                .vanillaItem(ItemId.WITCH_SPAWN_EGG)
                .build();
    }

    public static void initWitherRose() {
        if (ItemTypes.WITHER_ROSE != null) return;
        ItemTypes.WITHER_ROSE = ItemTypeBuilder
                .builder(ItemWitherRoseStack.class)
                .vanillaItem(ItemId.WITHER_ROSE)
                .build();
    }

    public static void initWitherSkeletonSpawnEgg() {
        if (ItemTypes.WITHER_SKELETON_SPAWN_EGG != null) return;
        ItemTypes.WITHER_SKELETON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitherSkeletonSpawnEggStack.class)
                .vanillaItem(ItemId.WITHER_SKELETON_SPAWN_EGG)
                .build();
    }

    public static void initWitherSpawnEgg() {
        if (ItemTypes.WITHER_SPAWN_EGG != null) return;
        ItemTypes.WITHER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitherSpawnEggStack.class)
                .vanillaItem(ItemId.WITHER_SPAWN_EGG)
                .build();
    }

    public static void initWolfArmor() {
        if (ItemTypes.WOLF_ARMOR != null) return;
        ItemTypes.WOLF_ARMOR = ItemTypeBuilder
                .builder(ItemWolfArmorStack.class)
                .vanillaItem(ItemId.WOLF_ARMOR)
                .build();
    }

    public static void initWolfSpawnEgg() {
        if (ItemTypes.WOLF_SPAWN_EGG != null) return;
        ItemTypes.WOLF_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWolfSpawnEggStack.class)
                .vanillaItem(ItemId.WOLF_SPAWN_EGG)
                .build();
    }

    public static void initWood() {
        if (ItemTypes.WOOD != null) return;
        ItemTypes.WOOD = ItemTypeBuilder
                .builder(ItemWoodStack.class)
                .vanillaItem(ItemId.WOOD)
                .build();
    }

    public static void initWoodenAxe() {
        if (ItemTypes.WOODEN_AXE != null) return;
        ItemTypes.WOODEN_AXE = ItemTypeBuilder
                .builder(ItemWoodenAxeStack.class)
                .vanillaItem(ItemId.WOODEN_AXE)
                .build();
    }

    public static void initWoodenButton() {
        if (ItemTypes.WOODEN_BUTTON != null) return;
        ItemTypes.WOODEN_BUTTON = ItemTypeBuilder
                .builder(ItemWoodenButtonStack.class)
                .vanillaItem(ItemId.WOODEN_BUTTON)
                .build();
    }

    public static void initWoodenDoor() {
        if (ItemTypes.WOODEN_DOOR != null) return;
        ItemTypes.WOODEN_DOOR = ItemTypeBuilder
                .builder(ItemWoodenDoorStack.class)
                .vanillaItem(ItemId.WOODEN_DOOR)
                .build();
    }

    public static void initWoodenHoe() {
        if (ItemTypes.WOODEN_HOE != null) return;
        ItemTypes.WOODEN_HOE = ItemTypeBuilder
                .builder(ItemWoodenHoeStack.class)
                .vanillaItem(ItemId.WOODEN_HOE)
                .build();
    }

    public static void initWoodenPickaxe() {
        if (ItemTypes.WOODEN_PICKAXE != null) return;
        ItemTypes.WOODEN_PICKAXE = ItemTypeBuilder
                .builder(ItemWoodenPickaxeStack.class)
                .vanillaItem(ItemId.WOODEN_PICKAXE)
                .build();
    }

    public static void initWoodenPressurePlate() {
        if (ItemTypes.WOODEN_PRESSURE_PLATE != null) return;
        ItemTypes.WOODEN_PRESSURE_PLATE = ItemTypeBuilder
                .builder(ItemWoodenPressurePlateStack.class)
                .vanillaItem(ItemId.WOODEN_PRESSURE_PLATE)
                .build();
    }

    public static void initWoodenShovel() {
        if (ItemTypes.WOODEN_SHOVEL != null) return;
        ItemTypes.WOODEN_SHOVEL = ItemTypeBuilder
                .builder(ItemWoodenShovelStack.class)
                .vanillaItem(ItemId.WOODEN_SHOVEL)
                .build();
    }

    public static void initWoodenSlab() {
        if (ItemTypes.WOODEN_SLAB != null) return;
        ItemTypes.WOODEN_SLAB = ItemTypeBuilder
                .builder(ItemWoodenSlabStack.class)
                .vanillaItem(ItemId.WOODEN_SLAB)
                .build();
    }

    public static void initWoodenSword() {
        if (ItemTypes.WOODEN_SWORD != null) return;
        ItemTypes.WOODEN_SWORD = ItemTypeBuilder
                .builder(ItemWoodenSwordStack.class)
                .vanillaItem(ItemId.WOODEN_SWORD)
                .build();
    }

    public static void initWool() {
        if (ItemTypes.WOOL != null) return;
        ItemTypes.WOOL = ItemTypeBuilder
                .builder(ItemWoolStack.class)
                .vanillaItem(ItemId.WOOL)
                .build();
    }

    public static void initWritableBook() {
        if (ItemTypes.WRITABLE_BOOK != null) return;
        ItemTypes.WRITABLE_BOOK = ItemTypeBuilder
                .builder(ItemWritableBookStack.class)
                .vanillaItem(ItemId.WRITABLE_BOOK)
                .build();
    }

    public static void initWrittenBook() {
        if (ItemTypes.WRITTEN_BOOK != null) return;
        ItemTypes.WRITTEN_BOOK = ItemTypeBuilder
                .builder(ItemWrittenBookStack.class)
                .vanillaItem(ItemId.WRITTEN_BOOK)
                .build();
    }

    public static void initYellowCandle() {
        if (ItemTypes.YELLOW_CANDLE != null) return;
        ItemTypes.YELLOW_CANDLE = ItemTypeBuilder
                .builder(ItemYellowCandleStack.class)
                .vanillaItem(ItemId.YELLOW_CANDLE)
                .build();
    }

    public static void initYellowCandleCake() {
        if (ItemTypes.YELLOW_CANDLE_CAKE != null) return;
        ItemTypes.YELLOW_CANDLE_CAKE = ItemTypeBuilder
                .builder(ItemYellowCandleCakeStack.class)
                .vanillaItem(ItemId.YELLOW_CANDLE_CAKE)
                .build();
    }

    public static void initYellowCarpet() {
        if (ItemTypes.YELLOW_CARPET != null) return;
        ItemTypes.YELLOW_CARPET = ItemTypeBuilder
                .builder(ItemYellowCarpetStack.class)
                .vanillaItem(ItemId.YELLOW_CARPET)
                .build();
    }

    public static void initYellowConcrete() {
        if (ItemTypes.YELLOW_CONCRETE != null) return;
        ItemTypes.YELLOW_CONCRETE = ItemTypeBuilder
                .builder(ItemYellowConcreteStack.class)
                .vanillaItem(ItemId.YELLOW_CONCRETE)
                .build();
    }

    public static void initYellowConcretePowder() {
        if (ItemTypes.YELLOW_CONCRETE_POWDER != null) return;
        ItemTypes.YELLOW_CONCRETE_POWDER = ItemTypeBuilder
                .builder(ItemYellowConcretePowderStack.class)
                .vanillaItem(ItemId.YELLOW_CONCRETE_POWDER)
                .build();
    }

    public static void initYellowDye() {
        if (ItemTypes.YELLOW_DYE != null) return;
        ItemTypes.YELLOW_DYE = ItemTypeBuilder
                .builder(ItemYellowDyeStack.class)
                .vanillaItem(ItemId.YELLOW_DYE)
                .build();
    }

    public static void initYellowGlazedTerracotta() {
        if (ItemTypes.YELLOW_GLAZED_TERRACOTTA != null) return;
        ItemTypes.YELLOW_GLAZED_TERRACOTTA = ItemTypeBuilder
                .builder(ItemYellowGlazedTerracottaStack.class)
                .vanillaItem(ItemId.YELLOW_GLAZED_TERRACOTTA)
                .build();
    }

    public static void initYellowShulkerBox() {
        if (ItemTypes.YELLOW_SHULKER_BOX != null) return;
        ItemTypes.YELLOW_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemYellowShulkerBoxStack.class)
                .vanillaItem(ItemId.YELLOW_SHULKER_BOX)
                .build();
    }

    public static void initYellowStainedGlass() {
        if (ItemTypes.YELLOW_STAINED_GLASS != null) return;
        ItemTypes.YELLOW_STAINED_GLASS = ItemTypeBuilder
                .builder(ItemYellowStainedGlassStack.class)
                .vanillaItem(ItemId.YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initYellowStainedGlassPane() {
        if (ItemTypes.YELLOW_STAINED_GLASS_PANE != null) return;
        ItemTypes.YELLOW_STAINED_GLASS_PANE = ItemTypeBuilder
                .builder(ItemYellowStainedGlassPaneStack.class)
                .vanillaItem(ItemId.YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initYellowTerracotta() {
        if (ItemTypes.YELLOW_TERRACOTTA != null) return;
        ItemTypes.YELLOW_TERRACOTTA = ItemTypeBuilder
                .builder(ItemYellowTerracottaStack.class)
                .vanillaItem(ItemId.YELLOW_TERRACOTTA)
                .build();
    }

    public static void initYellowWool() {
        if (ItemTypes.YELLOW_WOOL != null) return;
        ItemTypes.YELLOW_WOOL = ItemTypeBuilder
                .builder(ItemYellowWoolStack.class)
                .vanillaItem(ItemId.YELLOW_WOOL)
                .build();
    }

    public static void initZoglinSpawnEgg() {
        if (ItemTypes.ZOGLIN_SPAWN_EGG != null) return;
        ItemTypes.ZOGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZoglinSpawnEggStack.class)
                .vanillaItem(ItemId.ZOGLIN_SPAWN_EGG)
                .build();
    }

    public static void initZombieHorseSpawnEgg() {
        if (ItemTypes.ZOMBIE_HORSE_SPAWN_EGG != null) return;
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieHorseSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_HORSE_SPAWN_EGG)
                .build();
    }

    public static void initZombiePigmanSpawnEgg() {
        if (ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG != null) return;
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombiePigmanSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
                .build();
    }

    public static void initZombieSpawnEgg() {
        if (ItemTypes.ZOMBIE_SPAWN_EGG != null) return;
        ItemTypes.ZOMBIE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_SPAWN_EGG)
                .build();
    }

    public static void initZombieVillagerSpawnEgg() {
        if (ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG != null) return;
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieVillagerSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
                .build();
    }
}
