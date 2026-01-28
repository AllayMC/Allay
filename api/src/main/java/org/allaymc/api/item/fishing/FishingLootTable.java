package org.allaymc.api.item.fishing;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for selecting fishing loot based on enchantments and conditions.
 *
 * @author daoge_cmd
 */
public final class FishingLootTable {

    private FishingLootTable() {
    }

    /**
     * Selects a random loot item based on the given parameters.
     * <p>
     * In Bedrock Edition, Lure also affects loot chances (unlike Java Edition).
     *
     * @param luckOfTheSeaLevel the level of Luck of the Sea enchantment (0-3)
     * @param lureLevel         the level of Lure enchantment (0-3)
     * @param isOpenWater       whether the fishing hook is in open water (required for treasure)
     * @return the selected item stack
     * @see <a href="https://minecraft.wiki/w/Fishing">Fishing</a>
     * @see <a href="https://minecraft.wiki/w/Luck_of_the_Sea">Luck of the Sea</a>
     */
    public static ItemStack selectLoot(int luckOfTheSeaLevel, int lureLevel, boolean isOpenWater) {
        var random = ThreadLocalRandom.current();

        // Calculate category chances based on enchantments (Bedrock Edition)
        // Base chances: Fish 85%, Treasure 5%, Junk 10%
        // Luck of the Sea: +2.1% treasure per level, -1.95% junk per level
        // Lure (Bedrock only): -2.1% treasure per level, -1.95% junk per level
        // In BE, Lure increases fish chance at the cost of treasure and junk
        float treasureChance = Math.max(0, 0.05f + 0.021f * luckOfTheSeaLevel - 0.021f * lureLevel);
        float junkChance = Math.max(0, 0.10f - 0.0195f * luckOfTheSeaLevel - 0.0195f * lureLevel);

        float fishChance = 1.0f - treasureChance - junkChance;

        // Select category
        float roll = random.nextFloat();
        FishingLootCategory selectedCategory;
        if (roll < fishChance) {
            selectedCategory = FishingLootCategory.FISH;
        } else if (roll < fishChance + treasureChance) {
            // Treasure requires open water; if not in open water, becomes junk
            selectedCategory = isOpenWater ? FishingLootCategory.TREASURE : FishingLootCategory.JUNK;
        } else {
            selectedCategory = FishingLootCategory.JUNK;
        }

        // Get all loots in the selected category
        List<FishingLoot> categoryLoots = new ArrayList<>();
        float totalWeight = 0;

        for (var loot : Registries.FISHING_LOOTS.getContent().values()) {
            if (loot.getCategory() == selectedCategory) {
                categoryLoots.add(loot);
                totalWeight += loot.getWeight();
            }
        }

        // Fallback to cod if no loot found
        if (categoryLoots.isEmpty() || totalWeight <= 0) {
            return ItemTypes.COD.createItemStack();
        }

        // Weighted random selection within category
        float weightRoll = random.nextFloat() * totalWeight;
        float currentWeight = 0;

        for (var loot : categoryLoots) {
            currentWeight += loot.getWeight();
            if (weightRoll < currentWeight) {
                return loot.createItemStack();
            }
        }

        // Fallback (should not reach here)
        return categoryLoots.getLast().createItemStack();
    }
}
