package org.allaymc.server.blockentity.data;

/**
 * {@code BlockEntityId} defines Bedrock NBT names used to identify persisted
 * block entities. These case-sensitive names differ from namespaced block
 * identifiers and may include types without a registered server implementation.
 * Type initializers choose a name here for registry lookup and serialized
 * identity.
 */
public interface BlockEntityId {
    /**
     * Banner persistence identifier.
     */
    String BANNER = "Banner";

    /**
     * Barrel persistence identifier.
     */
    String BARREL = "Barrel";

    /**
     * Beacon persistence identifier.
     */
    String BEACON = "Beacon";

    /**
     * Bed persistence identifier.
     */
    String BED = "Bed";

    /**
     * Beehive persistence identifier.
     */
    String BEEHIVE = "Beehive";

    /**
     * Bell persistence identifier.
     */
    String BELL = "Bell";

    /**
     * Blast furnace persistence identifier.
     */
    String BLAST_FURNACE = "BlastFurnace";

    /**
     * Brewing stand persistence identifier.
     */
    String BREWING_STAND = "BrewingStand";

    /**
     * Brushable block persistence identifier.
     */
    String BRUSHABLE_BLOCK = "BrushableBlock";

    /**
     * Calibrated sculk sensor persistence identifier.
     */
    String CALIBRATED_SCULK_SENSOR = "CalibratedSculkSensor";

    /**
     * Campfire persistence identifier.
     */
    String CAMPFIRE = "Campfire";

    /**
     * Cauldron persistence identifier.
     */
    String CAULDRON = "Cauldron";

    /**
     * Chest persistence identifier.
     */
    String CHEST = "Chest";

    /**
     * Chiseled bookshelf persistence identifier.
     */
    String CHISELED_BOOKSHELF = "ChiseledBookshelf";

    /**
     * Command block persistence identifier.
     */
    String COMMAND_BLOCK = "CommandBlock";

    /**
     * Comparator persistence identifier.
     */
    String COMPARATOR = "Comparator";

    /**
     * Conduit persistence identifier.
     */
    String CONDUIT = "Conduit";

    /**
     * Daylight detector persistence identifier.
     */
    String DAYLIGHT_DETECTOR = "DaylightDetector";

    /**
     * Decorated pot persistence identifier.
     */
    String DECORATED_POT = "DecoratedPot";

    /**
     * Dispenser persistence identifier.
     */
    String DISPENSER = "Dispenser";

    /**
     * Dropper persistence identifier.
     */
    String DROPPER = "Dropper";

    /**
     * Enchant table persistence identifier.
     */
    String ENCHANT_TABLE = "EnchantTable";

    /**
     * End portal persistence identifier.
     */
    String END_PORTAL = "EndPortal";

    /**
     * Ender chest persistence identifier.
     */
    String ENDER_CHEST = "EnderChest";

    /**
     * Flower pot persistence identifier.
     */
    String FLOWER_POT = "FlowerPot";

    /**
     * Furnace persistence identifier.
     */
    String FURNACE = "Furnace";

    /**
     * Glow item frame persistence identifier.
     */
    String GLOW_ITEM_FRAME = "GlowItemFrame";

    /**
     * Hanging sign persistence identifier.
     */
    String HANGING_SIGN = "HangingSign";

    /**
     * Hopper persistence identifier.
     */
    String HOPPER = "Hopper";

    /**
     * Item frame persistence identifier.
     */
    String ITEM_FRAME = "ItemFrame";

    /**
     * Jukebox persistence identifier.
     */
    String JUKEBOX = "Jukebox";

    /**
     * Lectern persistence identifier.
     */
    String LECTERN = "Lectern";

    /**
     * Lodestone persistence identifier.
     */
    String LODESTONE = "Lodestone";

    /**
     * Mob spawner persistence identifier.
     */
    String MOB_SPAWNER = "MobSpawner";

    /**
     * Music persistence identifier.
     */
    String MUSIC = "Music";

    /**
     * Piston arm persistence identifier.
     */
    String PISTON_ARM = "PistonArm";

    /**
     * Sculk catalyst persistence identifier.
     */
    String SCULK_CATALYST = "SculkCatalyst";

    /**
     * Sculk sensor persistence identifier.
     */
    String SCULK_SENSOR = "SculkSensor";

    /**
     * Sculk shrieker persistence identifier.
     */
    String SCULK_SHRIEKER = "SculkShrieker";

    /**
     * Shelf persistence identifier.
     */
    String SHELF = "Shelf";

    /**
     * Shulker box persistence identifier.
     */
    String SHULKER_BOX = "ShulkerBox";

    /**
     * Sign persistence identifier.
     */
    String SIGN = "Sign";

    /**
     * Skull persistence identifier.
     */
    String SKULL = "Skull";

    /**
     * Smoker persistence identifier.
     */
    String SMOKER = "Smoker";

    /**
     * Structure block persistence identifier.
     */
    String STRUCTURE_BLOCK = "StructureBlock";

    /**
     * Moving block persistence identifier.
     */
    String MOVING_BLOCK = "MovingBlock";

    /**
     * Nether reactor persistence identifier.
     */
    String NETHER_REACTOR = "NetherReactor";

    /**
     * End gateway persistence identifier.
     */
    String END_GATEWAY = "EndGateway";

    /**
     * Target persistence identifier.
     */
    String TARGET = "Target";
}
