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
 * {@code FishingEnchantmentHelper} applies randomized enchantments to fishing
 * treasure items and enchanted books. The two entry points choose different
 * base levels and candidate rules before sharing a weighted draw. Ordinary
 * items filter existing compatibility and item applicability, while books
 * consider every fishable enchantment with an eligible level.
 *
 * <p>
 * The shared draw selects one candidate and progressively removes the selected
 * type plus candidates that declare incompatibility with it. Additional
 * selections use the remaining list, so removed candidates do not return on
 * later rounds. Treasure items halve the modified level after additional
 * selections, reducing later continuation chances, while books retain that
 * level. Each successful selection mutates the supplied stack immediately
 * without rollback.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class FishingEnchantmentHelper {

    /**
     * {@code ENCHANTED_BOOK_LEVEL} supplies the base level for fishing books
     * before enchantability variation and weighted candidate selection.
     */
    private static final int ENCHANTED_BOOK_LEVEL = 30;

    // TREASURE_MIN_LEVEL and TREASURE_MAX_LEVEL bound one inclusive random base
    // level for ordinary treasure. Book generation uses ENCHANTED_BOOK_LEVEL instead
    // of drawing within that treasure interval.
    /**
     * Inclusive treasure base-level minimum.
     */
    private static final int TREASURE_MIN_LEVEL = 22;

    /**
     * Inclusive treasure base-level maximum.
     */
    private static final int TREASURE_MAX_LEVEL = 30;

    /**
     * Adds randomized fishable enchantments to the supplied book using a base
     * level of {@code 30}. Book candidates bypass item-type applicability
     * checks, and additional selections keep their modified level instead of
     * halving it.
     */
    public static void applyBookEnchantments(ItemStack enchantedBook) {
        applyEnchantments(enchantedBook, ENCHANTED_BOOK_LEVEL, 1, false, FishingEnchantmentHelper::getAvailableEnchantmentsForBook);
    }

    /**
     * Adds randomized compatible enchantments to the supplied treasure item.
     * The base level is chosen inclusively from {@code 22} through {@code 30},
     * and this path uses item enchantability and halves continuation level
     * after extra selections.
     */
    public static void applyTreasureEnchantments(ItemStack item) {
        var random = ThreadLocalRandom.current();
        int baseLevel = random.nextInt(TREASURE_MIN_LEVEL, TREASURE_MAX_LEVEL + 1);
        int enchantValue = item.getItemType().getItemData().enchantValue();
        applyEnchantments(item, baseLevel, enchantValue, true, level -> getAvailableEnchantments(level, item));
    }

    /**
     * Adds a weighted sequence of enchantments selected from one randomized
     * modified level. The calculation combines {@code baseLevel}, two
     * enchantability draws, and a bounded random multiplier, then requests
     * initial candidates from {@code enchantmentSupplier}. An empty candidate
     * list leaves the supplied stack unchanged.
     *
     * <p>
     * After the first selection, each successful continuation removes the
     * previous type and candidates that declare incompatibility with it before
     * another weighted draw. The progressively filtered list retains earlier
     * exclusions. When {@code reduceChance} is {@code true}, extra selections
     * halve the continuation level, but candidate eligibility is not
     * recomputed at that smaller level. Each addition immediately mutates
     * {@code item}, so later failures do not undo earlier additions.
     */
    private static void applyEnchantments(
            ItemStack item,
            int baseLevel,
            int enchantValue,
            boolean reduceChance,
            IntFunction<List<EnchantmentInstance>> enchantmentSupplier
    ) {
        var random = ThreadLocalRandom.current();

        // Two enchantability draws perturb the base before fifteen-percent random
        // variation. Clamp the result to one so level-window checks receive a positive level.
        int modifiedLevel = baseLevel + random.nextInt(enchantValue / 4 + 1) + random.nextInt(enchantValue / 4 + 1) + 1;
        double bonus = 1 + (random.nextDouble() + random.nextDouble() - 1) * 0.15;
        modifiedLevel = Math.max(1, (int) Math.round(modifiedLevel * bonus));

        var availableEnchantments = enchantmentSupplier.apply(modifiedLevel);
        if (availableEnchantments.isEmpty()) return;

        var lastEnchantment = getRandomWeightedEnchantment(availableEnchantments);
        if (lastEnchantment != null) {
            item.addEnchantment(lastEnchantment.getType(), lastEnchantment.getLevel());
        }

        // Continuation rolls use fifty equally likely integers. Keep exclusions in
        // the shrinking list so later draws cannot restore earlier incompatible candidates.
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

            // Halving affects subsequent continuation rolls only; the remaining candidates
            // retain the levels chosen from the original modified-level windows.
            if (reduceChance) {
                modifiedLevel /= 2;
            }
        }
    }

    /**
     * Returns each eligible item enchantment at its highest matching level.
     * Candidates first pass existing-item compatibility, fishability, and
     * item-type applicability checks; each type's levels are examined from
     * maximum downward.
     *
     * <p>
     * A level is included only when {@code modifiedLevel} lies inside that
     * level's inclusive minimum and maximum window. A type with no matching
     * window contributes no candidate, and selecting one level stops
     * examination of that type's lower levels. The returned list is newly
     * allocated and mutable.
     */
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

    /**
     * Returns an unmodifiable list of registered enchantments eligible for the
     * supplied item. Existing-enchantment compatibility, fishability, and
     * item-type applicability must all pass; this helper leaves per-level
     * eligibility to its caller.
     */
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
     * Returns each fishable enchantment at its highest level matching the
     * modified level. Book selection bypasses item applicability and
     * preexisting stack compatibility because this helper receives no item.
     *
     * <p>
     * Each type's levels are examined from maximum downward against their
     * inclusive modified-level windows. A type with no matching window
     * contributes no candidate, while the first matching level prevents lower
     * levels of the same type from entering the newly allocated mutable list.
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
     * Returns an unmodifiable list of every registered fishable enchantment
     * type. This book-oriented scan does not check any item's applicability or
     * current enchantments; later processing chooses a level for each type.
     */
    private static List<EnchantmentType> getAllFishableEnchantments() {
        return Registries.ENCHANTMENTS
                .getContent()
                .m1().values()
                .stream()
                .filter(EnchantmentType::isFishable)
                .toList();
    }

    /**
     * Returns one supplied candidate using its enchantment rarity as a
     * relative weight. An empty list returns {@code null}; nonempty input must
     * have positive rarity weights whose sum fits the integer draw range. The
     * selected instance is returned directly rather than copied.
     *
     * <p>
     * The draw starts at one and subtracts each candidate's weight until its
     * interval contains the roll. Candidate order therefore defines adjacent
     * intervals without changing valid relative shares, and integer arithmetic
     * avoids fractional probability accumulation in this selection.
     */
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
