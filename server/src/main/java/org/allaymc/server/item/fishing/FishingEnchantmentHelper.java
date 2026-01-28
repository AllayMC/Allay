package org.allaymc.server.item.fishing;

import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;

/**
 * Helper class for applying enchantments to fishing loot.
 *
 * @author daoge_cmd
 * @see <a href="https://minecraft.wiki/w/Fishing#Treasure">Fishing Treasure</a>
 */
@UtilityClass
public final class FishingEnchantmentHelper {

    /**
     * The base level used for enchanted books from fishing (level 30 equivalent).
     */
    private static final int ENCHANTED_BOOK_LEVEL = 30;

    /**
     * The minimum base level for treasure items (bow, fishing rod).
     */
    private static final int TREASURE_MIN_LEVEL = 22;

    /**
     * The maximum base level for treasure items (bow, fishing rod).
     */
    private static final int TREASURE_MAX_LEVEL = 30;

    /**
     * Apply enchantments to an enchanted book from fishing.
     * <p>
     * Uses level-30 enchantment table algorithm, but the chance of multiple
     * enchantments is not reduced (modifiedLevel is not halved between rolls).
     * <p>
     * Filters enchantments by {@link EnchantmentType#isFishable()}.
     *
     * @param enchantedBook the enchanted book to apply enchantments to
     * @see <a href="https://minecraft.wiki/w/Fishing#Treasure">Fishing Treasure</a>
     */
    public static void applyBookEnchantments(ItemStack enchantedBook) {
        // Books have enchantValue of 1, and chance is NOT reduced for multiple enchantments
        applyEnchantments(enchantedBook, ENCHANTED_BOOK_LEVEL, 1, false, FishingEnchantmentHelper::getAvailableEnchantmentsForBook);
    }

    /**
     * Apply enchantments to treasure items (bow, fishing rod) using level 22-30.
     * <p>
     * Uses the enchantment table algorithm logic similar to {@code EnchantmentOptionGenerator}.
     *
     * @param item the treasure item to apply enchantments to
     * @see <a href="https://minecraft.wiki/w/Fishing#Treasure">Fishing Treasure</a>
     */
    public static void applyTreasureEnchantments(ItemStack item) {
        var random = ThreadLocalRandom.current();
        int baseLevel = random.nextInt(TREASURE_MIN_LEVEL, TREASURE_MAX_LEVEL + 1);
        int enchantValue = item.getItemType().getItemData().enchantValue();
        // Chance IS reduced (halved) for multiple enchantments
        applyEnchantments(item, baseLevel, enchantValue, true, level -> getAvailableEnchantments(level, item));
    }

    /**
     * Core enchantment application logic.
     *
     * @param item               the item to enchant
     * @param baseLevel          the base enchantment level
     * @param enchantValue       the item's enchantability value
     * @param reduceChance       whether to halve modifiedLevel for additional enchantments
     * @param enchantmentSupplier function to get available enchantments for a given modified level
     */
    private static void applyEnchantments(
            ItemStack item,
            int baseLevel,
            int enchantValue,
            boolean reduceChance,
            IntFunction<List<EnchantmentInstance>> enchantmentSupplier
    ) {
        var random = ThreadLocalRandom.current();

        // Calculate modified level
        int modifiedLevel = baseLevel + random.nextInt(enchantValue / 4 + 1) + random.nextInt(enchantValue / 4 + 1) + 1;
        double bonus = 1 + (random.nextDouble() + random.nextDouble() - 1) * 0.15;
        modifiedLevel = Math.max(1, (int) Math.round(modifiedLevel * bonus));

        // Get available enchantments at this level
        var availableEnchantments = enchantmentSupplier.apply(modifiedLevel);
        if (availableEnchantments.isEmpty()) return;

        // Select first enchantment
        var lastEnchantment = getRandomWeightedEnchantment(availableEnchantments);
        if (lastEnchantment != null) {
            item.addEnchantment(lastEnchantment.getType(), lastEnchantment.getLevel());
        }

        // Chance for additional enchantments: (modifiedLevel + 1) / 50
        while (random.nextInt(50) < modifiedLevel + 1) {
            var finalLast = lastEnchantment;
            availableEnchantments = availableEnchantments.stream()
                    .filter(e -> e.getType() != finalLast.getType()
                            && !e.getType().isIncompatibleWith(finalLast.getType()))
                    .toList();

            if (availableEnchantments.isEmpty()) break;

            lastEnchantment = getRandomWeightedEnchantment(availableEnchantments);
            if (lastEnchantment != null) {
                item.addEnchantment(lastEnchantment.getType(), lastEnchantment.getLevel());
            }

            if (reduceChance) {
                modifiedLevel /= 2;
            }
        }
    }

    private static List<EnchantmentInstance> getAvailableEnchantments(int modifiedLevel, ItemStack item) {
        List<EnchantmentInstance> list = new ArrayList<>();
        for (var enchantment : getFishableEnchantmentsForItem(item)) {
            for (int lvl = enchantment.getMaxLevel(); lvl > 0; lvl--) {
                if (modifiedLevel >= enchantment.getMinModifiedLevel(lvl) && modifiedLevel <= enchantment.getMaxModifiedLevel(lvl)) {
                    list.add(enchantment.createInstance(lvl));
                    break;
                }
            }
        }
        return list;
    }

    private static List<EnchantmentType> getFishableEnchantmentsForItem(ItemStack item) {
        return Registries.ENCHANTMENTS
                .getContent()
                .m1().values()
                .stream()
                .filter(item::checkEnchantmentCompatibility)
                .filter(EnchantmentType::isFishable)
                .filter(type -> type.canBeAppliedTo(item.getItemType()))
                .toList();
    }

    /**
     * Get available enchantments for a book at a given modified level.
     * Books can have any fishable enchantment regardless of applicable type.
     */
    private static List<EnchantmentInstance> getAvailableEnchantmentsForBook(int modifiedLevel) {
        List<EnchantmentInstance> list = new ArrayList<>();
        for (var enchantment : getAllFishableEnchantments()) {
            for (int lvl = enchantment.getMaxLevel(); lvl > 0; lvl--) {
                if (modifiedLevel >= enchantment.getMinModifiedLevel(lvl) && modifiedLevel <= enchantment.getMaxModifiedLevel(lvl)) {
                    list.add(enchantment.createInstance(lvl));
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Get all fishable enchantments (for enchanted books).
     */
    private static List<EnchantmentType> getAllFishableEnchantments() {
        return Registries.ENCHANTMENTS
                .getContent()
                .m1().values()
                .stream()
                .filter(EnchantmentType::isFishable)
                .toList();
    }

    private static EnchantmentInstance getRandomWeightedEnchantment(List<EnchantmentInstance> enchantments) {
        if (enchantments.isEmpty()) {
            return null;
        }

        var random = ThreadLocalRandom.current();

        int totalWeight = 0;
        for (var enchantment : enchantments) {
            totalWeight += enchantment.getType().getRarity().getWeight();
        }

        EnchantmentInstance result = null;
        int randomWeight = random.nextInt(1, totalWeight + 1);

        for (var enchantment : enchantments) {
            randomWeight -= enchantment.getType().getRarity().getWeight();
            if (randomWeight <= 0) {
                result = enchantment;
                break;
            }
        }
        return result;
    }
}
