package org.allaymc.api.item.fishing;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code FishingLootTable} selects fishing catches from the registered
 * category and entry weights. Category probabilities combine Luck of the Sea
 * and Lure levels, and open-water state determines whether a treasure roll
 * remains treasure or becomes junk. The selector then reads all entries in
 * that chosen category and performs a second draw using their relative
 * weights.
 *
 * <p>
 * An empty category or nonpositive total weight produces a new cod stack
 * instead of an absent catch. Otherwise the selected entry creates the result,
 * with the final entry providing a fallback if cumulative comparisons do not
 * match. The implementation checks category water eligibility directly and
 * does not consult each entry's {@code requiresOpenWater} property.
 *
 * @author daoge_cmd
 */
public final class FishingLootTable {

    /**
     * Prevents construction of the static fishing-loot selection utility
     * class.
     */
    private FishingLootTable() {
    }

    /**
     * Selects a category and then a weighted registered entry using the
     * supplied fishing conditions. {@code luckOfTheSeaLevel} increases
     * treasure probability, while {@code lureLevel} reduces it; both levels
     * reduce junk probability and fish receives the remainder. The expected
     * enchantment range is {@code 0} through {@code 3}, but this method does
     * not enforce that range.
     *
     * <p>
     * A treasure roll without open water becomes a junk roll before candidate
     * entries are gathered. Empty categories and nonpositive aggregate weights
     * return cod; other registered weights are used without finite or
     * nonnegative validation. The chosen entry's factory supplies the result,
     * and the final candidate handles a draw left unmatched by cumulative
     * weight comparisons.
     */
    public static ItemStack selectLoot(int luckOfTheSeaLevel, int lureLevel, boolean isOpenWater) {
        var random = ThreadLocalRandom.current();

        // These constants are category probabilities. Luck raises treasure while both
        // enchantments reduce junk, leaving fish with the remaining probability mass.
        float treasureChance = Math.max(0, 0.05f + 0.021f * luckOfTheSeaLevel - 0.021f * lureLevel);
        float junkChance = Math.max(0, 0.10f - 0.0195f * luckOfTheSeaLevel - 0.0195f * lureLevel);

        float fishChance = 1.0f - treasureChance - junkChance;

        float roll = random.nextFloat();
        FishingLootCategory selectedCategory;
        if (roll < fishChance) {
            selectedCategory = FishingLootCategory.FISH;
        } else if (roll < fishChance + treasureChance) {
            // Redirect this treasure-category roll before entry selection, so missing
            // open water produces a junk catch rather than choosing a treasure entry.
            selectedCategory = isOpenWater ? FishingLootCategory.TREASURE : FishingLootCategory.JUNK;
        } else {
            selectedCategory = FishingLootCategory.JUNK;
        }

        List<FishingLoot> categoryLoots = new ArrayList<>();
        float totalWeight = 0;

        for (var loot : Registries.FISHING_LOOTS.getContent().values()) {
            if (loot.getCategory() == selectedCategory) {
                categoryLoots.add(loot);
                totalWeight += loot.getWeight();
            }
        }

        // Empty categories and nonpositive total weights cannot support a weighted
        // draw. Return cod so the fishing outcome still receives an item result.
        if (categoryLoots.isEmpty() || totalWeight <= 0) {
            return ItemTypes.COD.createItemStack();
        }

        float weightRoll = random.nextFloat() * totalWeight;
        float currentWeight = 0;

        for (var loot : categoryLoots) {
            currentWeight += loot.getWeight();
            if (weightRoll < currentWeight) {
                return loot.createItemStack();
            }
        }

        // Floating-point accumulation or unusual registered weights can leave a draw
        // unmatched. Use the final candidate instead of returning no catch item.
        return categoryLoots.getLast().createItemStack();
    }
}
