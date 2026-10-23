package org.allaymc.api.blockentity.type;

import org.allaymc.api.blockentity.interfaces.*;

/**
 * {@code BlockEntityTypes} exposes the built-in block-entity factories
 * published during registry bootstrap. The fields are {@code null} before their
 * initializer runs and retain the assigned type object afterward. Replacing a
 * name-based registry entry does not automatically update these references, so
 * registry lookup and a retained field can differ.
 *
 * @author daoge_cmd
 */
public final class BlockEntityTypes {
    /**
     * Banner block-entity factory.
     */
    public static BlockEntityType<BlockEntityBanner> BANNER;
    /**
     * Barrel block-entity factory.
     */
    public static BlockEntityType<BlockEntityBarrel> BARREL;
    /**
     * Dispenser block-entity factory.
     */
    public static BlockEntityType<BlockEntityDispenser> DISPENSER;
    /**
     * Dropper block-entity factory.
     */
    public static BlockEntityType<BlockEntityDropper> DROPPER;
    /**
     * Bell block-entity factory.
     */
    public static BlockEntityType<BlockEntityBell> BELL;
    /**
     * Command block block-entity factory.
     */
    public static BlockEntityType<BlockEntityCommandBlock> COMMAND_BLOCK;
    /**
     * Comparator block-entity factory.
     */
    public static BlockEntityType<BlockEntityComparator> COMPARATOR;
    /**
     * Bed block-entity factory.
     */
    public static BlockEntityType<BlockEntityBed> BED;
    /**
     * Chest block-entity factory.
     */
    public static BlockEntityType<BlockEntityChest> CHEST;
    /**
     * Decorated pot block-entity factory.
     */
    public static BlockEntityType<BlockEntityDecoratedPot> DECORATED_POT;
    /**
     * Hopper block-entity factory.
     */
    public static BlockEntityType<BlockEntityHopper> HOPPER;
    /**
     * Shulker box block-entity factory.
     */
    public static BlockEntityType<BlockEntityShulkerBox> SHULKER_BOX;
    /**
     * Furnace block-entity factory.
     */
    public static BlockEntityType<BlockEntityFurnace> FURNACE;
    /**
     * Blast furnace block-entity factory.
     */
    public static BlockEntityType<BlockEntityBlastFurnace> BLAST_FURNACE;
    /**
     * Smoker block-entity factory.
     */
    public static BlockEntityType<BlockEntitySmoker> SMOKER;
    /**
     * Sign block-entity factory.
     */
    public static BlockEntityType<BlockEntitySign> SIGN;
    /**
     * Hanging sign block-entity factory.
     */
    public static BlockEntityType<BlockEntityHangingSign> HANGING_SIGN;
    /**
     * Enchant table block-entity factory.
     */
    public static BlockEntityType<BlockEntityEnchantTable> ENCHANT_TABLE;
    /**
     * Jukebox block-entity factory.
     */
    public static BlockEntityType<BlockEntityJukebox> JUKEBOX;
    /**
     * Lectern block-entity factory.
     */
    public static BlockEntityType<BlockEntityLectern> LECTERN;
    /**
     * Beacon block-entity factory.
     */
    public static BlockEntityType<BlockEntityBeacon> BEACON;
    /**
     * Brewing stand block-entity factory.
     */
    public static BlockEntityType<BlockEntityBrewingStand> BREWING_STAND;
    /**
     * Ender chest block-entity factory.
     */
    public static BlockEntityType<BlockEntityEnderChest> ENDER_CHEST;
    /**
     * Flower pot block-entity factory.
     */
    public static BlockEntityType<BlockEntityFlowerPot> FLOWER_POT;
    /**
     * Item frame block-entity factory.
     */
    public static BlockEntityType<BlockEntityItemFrame> ITEM_FRAME;
    /**
     * Glow item frame block-entity factory.
     */
    public static BlockEntityType<BlockEntityGlowItemFrame> GLOW_ITEM_FRAME;
    /**
     * Noteblock block-entity factory.
     */
    public static BlockEntityType<BlockEntityNoteblock> NOTEBLOCK;
    /**
     * Head block-entity factory.
     */
    public static BlockEntityType<BlockEntityHead> HEAD;
    /**
     * Target block-entity factory.
     */
    public static BlockEntityType<BlockEntityTarget> TARGET;
    /**
     * Chiseled bookshelf block-entity factory.
     */
    public static BlockEntityType<BlockEntityChiseledBookshelf> CHISELED_BOOKSHELF;
    /**
     * Shelf block-entity factory.
     */
    public static BlockEntityType<BlockEntityShelf> SHELF;
    /**
     * Campfire block-entity factory.
     */
    public static BlockEntityType<BlockEntityCampfire> CAMPFIRE;
    /**
     * Soul campfire block-entity factory.
     */
    public static BlockEntityType<BlockEntityCampfire> SOUL_CAMPFIRE;
    /**
     * Piston arm block-entity factory.
     */
    public static BlockEntityType<BlockEntityPistonArm> PISTON_ARM;
    /**
     * Moving block block-entity factory.
     */
    public static BlockEntityType<BlockEntityMovingBlock> MOVING_BLOCK;
    /**
     * Cauldron block-entity factory.
     */
    public static BlockEntityType<BlockEntityCauldron> CAULDRON;
}
