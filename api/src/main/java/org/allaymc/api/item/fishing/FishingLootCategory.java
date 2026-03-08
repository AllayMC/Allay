package org.allaymc.api.item.fishing;

/**
 * Represents the category of fishing loot.
 *
 * @author daoge_cmd
 */
public enum FishingLootCategory {
    /**
     * Fish category (85% base chance).
     * Includes: Cod, Salmon, Tropical Fish, Pufferfish.
     */
    FISH,

    /**
     * Treasure category (5% base chance).
     * Includes: Bow, Enchanted Book, Fishing Rod, Name Tag, Nautilus Shell, Saddle.
     */
    TREASURE,

    /**
     * Junk category (10% base chance).
     * Includes: Bowl, Fishing Rod, Leather, Leather Boots, Rotten Flesh, Stick, String, Water Bottle, Bone, Ink Sac, Tripwire Hook.
     */
    JUNK
}
