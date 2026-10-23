package org.allaymc.server.item.enchantment;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.server.utils.AllayRandom;
import org.jctools.maps.NonBlockingHashMapLong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * {@code EnchantmentOptionGenerator} produces enchanting-table choices from an
 * input item, player seed, nearby bookshelves, and registered enchantment
 * rules. Bookshelf access determines required experience levels, and item
 * enchantability perturbs the modified power used to select eligible
 * enchantment levels. Each accepted input produces three options with lapis
 * requirements from one through three.
 *
 * <p>
 * Candidate selection uses rarity weights and removes types that conflict with
 * each selected predecessor before trying additional enchantments at reduced
 * power. Generated choices are published in {@code ENCHANT_OPTIONS} under
 * increasing network IDs. Consumers remove selected or abandoned IDs
 * explicitly; removing a selected choice also prevents another lookup from
 * obtaining the same registration. The option records and their enchantment
 * lists remain shared with callers.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class EnchantmentOptionGenerator {

    // TODO: Derive the enchanting ID boundary from recipe network IDs so large
    // recipe tables cannot be routed to the enchanting action handler.
    /**
     * {@code NETWORK_ID_COUNTER_INITIAL_VALUE} separates enchanting choices
     * from ordinary recipe IDs in {@code CraftRecipeActionProcessor}. The fixed
     * boundary assumes ordinary recipe IDs stay below this value.
     */
    public static final int NETWORK_ID_COUNTER_INITIAL_VALUE = 100000;

    /**
     * {@code ENCHANT_OPTIONS} retains published choices by network ID until
     * selection or explicit cleanup removes them. The stored option and list
     * references remain shared with returned pairs.
     */
    private static final NonBlockingHashMapLong<EnchantOption> ENCHANT_OPTIONS = new NonBlockingHashMapLong<>();
    /**
     * {@code NETWORK_ID_COUNTER} allocates a distinct incrementing ID for each
     * published option until integer wraparound; removal does not recycle IDs.
     */
    private static final AtomicInteger NETWORK_ID_COUNTER = new AtomicInteger(NETWORK_ID_COUNTER_INITIAL_VALUE);
    /**
     * {@code MAX_BOOKSHELF_COUNT} caps the effective bookshelf contribution
     * used by required-level calculation, regardless of additional accessible
     * shelves.
     */
    private static final int MAX_BOOKSHELF_COUNT = 15;

    /**
     * {@code WORDS} supplies the immutable word pool sampled when constructing
     * each displayed enchanting option name.
     */
    private static final List<String> WORDS = List.of(
            "air", "animal", "ball", "beast", "berata",
            "bless", "cold", "creature", "cthulhu", "cube",
            "curse", "darkness", "demon", "destroy", "dry",
            "earth", "elder", "elemental", "embiggen", "enchant",
            "fhtagnbaguette", "fiddle", "fire", "free", "fresh",
            "galvanize", "grow", "hot", "humanoid", "ignite",
            "imbue", "inside", "klaatu", "light", "limited",
            "xyzzy", "mental", "mglwnafh", "niktu", "of",
            "other", "phnglui", "physical", "range", "rlyeh",
            "scrolls", "self", "shorten", "shrink", "snuff",
            "sphere", "spirit", "stale", "stretch", "the",
            "towards", "twist", "undead", "water", "wet",
            "wgahnagl", "cool_loong", "allay", "daoge", "atri",
            "mdx", "iwareq", "liulihaocai"
    );

    /**
     * Generates three registered enchanting choices from the supplied item and
     * seed. A {@code null} item, an already enchanted item, or zero
     * enchantability returns an empty list without publishing any choice.
     *
     * <p>
     * Accessible bookshelves influence the common base requirement, then each
     * option receives its own required level and lapis cost. The result pairs
     * expose the same options retained in the global registry; callers remove
     * selected choices with {@code removeEnchantOption} and clean up abandoned
     * IDs with {@code removeEnchantOptions}. The input stack remains unchanged.
     */
    public static List<Pair<Integer, EnchantOption>> generateEnchantOptions(Position3ic enchantTablePos, ItemStack input, int seed) {
        if (input == null || input.hasEnchantments() || input.getItemType().getItemData().enchantValue() == 0) {
            return Collections.emptyList();
        }

        AllayRandom random = new AllayRandom(seed);

        int bookshelfCount = countBookshelves(enchantTablePos);
        // Whole bookshelf pairs supply the fixed half-count contribution. An odd
        // shelf still widens the random range but adds no further fixed level.
        int baseRequiredLevel = random.nextRange(1, 8) + (bookshelfCount >> 1) + random.nextRange(0, bookshelfCount);

        // Experience eligibility is checked in whole levels. Floor the first two
        // scaled requirements independently; a base of five requires levels one
        // and four, and the first option keeps its one-level minimum.
        return List.of(
                createEnchantOption(random, input, 1, (int) Math.floor(Math.max(baseRequiredLevel / 3D, 1))),
                createEnchantOption(random, input, 2, (int) Math.floor(baseRequiredLevel * 2D / 3 + 1)),
                createEnchantOption(random, input, 3, Math.max(baseRequiredLevel, bookshelfCount * 2))
        );
    }

    /**
     * Removes and returns the registered choice for one network ID. Missing or
     * previously removed IDs return {@code null}; a successful removal consumes
     * the registration while any previously returned option references remain
     * usable.
     */
    public EnchantOption removeEnchantOption(int networkId) {
        return ENCHANT_OPTIONS.remove(networkId);
    }

    /**
     * Removes every network ID supplied by the caller during choice cleanup.
     * Missing IDs are ignored, and removing registry entries does not modify
     * the option objects or lists already held by consumers.
     */
    public void removeEnchantOptions(Collection<Long> options) {
        for (var option : options) {
            ENCHANT_OPTIONS.remove(option);
        }
    }

    /**
     * Creates and publishes one option after modifying its required level with
     * item enchantability and random power variation. Eligible candidates
     * contribute their highest normal level whose inclusive bounds contain the
     * modified power, and rarity weights choose among those candidates.
     *
     * <p>
     * Each continuation removes the previous type and candidates that reject
     * it, while retaining earlier filtering decisions. Halving modified power
     * reduces later continuation chances without rebuilding the original
     * eligible levels. A weighted draw can return {@code null}, so an option
     * may contain no enchantments; the published network ID and returned pair
     * still refer to that same option.
     */
    private static Pair<Integer, EnchantOption> createEnchantOption(AllayRandom random, ItemStack inputItem, int requiredLapisLazuliCount, int requiredXpLevel) {
        int modifiedLevel = requiredXpLevel;

        int enchantValue = inputItem.getItemType().getItemData().enchantValue();
        modifiedLevel = modifiedLevel + random.nextInt(enchantValue / 4) + random.nextInt(enchantValue / 2) + 1;

        // Two uniform draws vary the modified power around its current value,
        // after which rounding determines which integer eligibility bounds can match.
        double bonus = 1 + (random.nextFloat() + random.nextFloat() - 1) * 0.15;
        modifiedLevel = (int) Math.round(modifiedLevel * bonus);
        // Keep the modified power within positive enchanting-level queries.
        if (modifiedLevel < 1) modifiedLevel = 1;

        List<EnchantmentInstance> resultEnchantments = new ArrayList<>();
        List<EnchantmentInstance> availableEnchantments = getAvailableEnchantments(modifiedLevel, inputItem);
        if (!availableEnchantments.isEmpty()) {
            final AtomicReference<EnchantmentInstance> lastEnchantment = new AtomicReference<>(getRandomWeightedEnchantment(random, availableEnchantments));
            if (lastEnchantment.get() != null) {
                resultEnchantments.add(lastEnchantment.get());
            }

            while (random.nextInt(1, 50) <= modifiedLevel) {
                if (!resultEnchantments.isEmpty()) {
                    availableEnchantments = availableEnchantments.stream()
                            .filter(e -> e.getType().getId() != lastEnchantment.get().getType().getId() && !e.getType().isIncompatibleWith(lastEnchantment.get().getType()))
                            .collect(Collectors.toList());
                }
                if (availableEnchantments.isEmpty()) {
                    break;
                }
                var enchantment = getRandomWeightedEnchantment(random, availableEnchantments);
                if (enchantment != null) {
                    resultEnchantments.add(enchantment);
                    lastEnchantment.set(enchantment);
                }
                // Later choices retain their original eligible levels, but reduced
                // continuation power makes another selection progressively less likely.
                modifiedLevel /= 2;
            }
        }

        var networkId = NETWORK_ID_COUNTER.getAndIncrement();
        var option = new EnchantOption(generateRandomOptionName(random), requiredLapisLazuliCount, requiredXpLevel, resultEnchantments);
        ENCHANT_OPTIONS.put(networkId, option);
        return new Pair<>(networkId, option);
    }

    /**
     * Counts usable bookshelves on the outer ring two blocks from the
     * enchanting table, capped at {@code MAX_BOOKSHELF_COUNT}. Each horizontal
     * candidate represents a two-block-high stack, and both intervening
     * positions must be exactly {@code BlockTypes.AIR} before either shelf can
     * contribute.
     *
     * <p>
     * The intervening horizontal position clamps each offset to one block from
     * the table, preserving the gap check for straight and diagonal shelf
     * positions. A blocker at either height skips that entire candidate column.
     * Once the cap is reached, additional shelf positions are irrelevant to
     * required-level calculation and are not read.
     */
    private static int countBookshelves(Position3ic enchantTablePos) {
        int bookshelfCount = 0;
        var dimension = enchantTablePos.dimension();

        for (int x = -2; x <= 2; x++) {
            outer:
            for (int z = -2; z <= 2; z++) {
                // Inner positions are gaps between the table and shelf ring;
                // counting them would allow shelves to occupy the required intervening space.
                if (Math.abs(x) != 2 && Math.abs(z) != 2) {
                    continue;
                }

                // Both gap heights must stay clear for either shelf in this column.
                // A blocker therefore skips the column instead of only one shelf.
                for (int y = 0; y <= 1; y++) {
                    if (dimension.getBlockState(
                            enchantTablePos.x() + Math.max(Math.min(x, 1), -1),
                            enchantTablePos.y() + y,
                            enchantTablePos.z() + Math.max(Math.min(z, 1), -1)
                    ).getBlockType() != BlockTypes.AIR) {
                        continue outer;
                    }
                }

                for (int y = 0; y <= 1; y++) {
                    if (dimension.getBlockState(enchantTablePos.x() + x, enchantTablePos.y() + y, enchantTablePos.z() + z).getBlockType() == BlockTypes.BOOKSHELF) {
                        bookshelfCount++;
                        // Further shelves cannot increase the effective enchanting requirement.
                        if (bookshelfCount == MAX_BOOKSHELF_COUNT) {
                            return bookshelfCount;
                        }
                    }
                }
            }
        }

        return bookshelfCount;
    }

    /**
     * Selects an instance by subtracting rarity weights from an inclusive
     * random draw. An empty candidate list returns {@code null}; otherwise the
     * draw ranges from {@code 1} through the sum of all weights.
     *
     * <p>
     * Selection requires the remaining draw to become strictly negative after
     * subtraction. Consequently a draw equal to the total weight finishes at
     * zero and returns {@code null}, even with nonempty candidates. Boundary
     * values also move to the following interval rather than the preceding
     * candidate, so callers must allow an absent result instead of assuming
     * every draw selects an instance.
     */
    private static EnchantmentInstance getRandomWeightedEnchantment(AllayRandom random, List<EnchantmentInstance> enchantments) {
        if (enchantments.isEmpty()) {
            return null;
        }

        int totalWeight = 0;
        for (var enchantment : enchantments) {
            totalWeight += enchantment.getType().getRarity().getWeight();
        }

        EnchantmentInstance result = null;
        int randomWeight = random.nextRange(1, totalWeight);

        // The draw includes the total weight, but selection requires a negative
        // remainder. That endpoint stays unselected and leaves result null after the loop.
        for (var enchantment : enchantments) {
            randomWeight -= enchantment.getType().getRarity().getWeight();
            if (randomWeight < 0) {
                result = enchantment;
                break;
            }
        }
        return result;
    }

    /**
     * Builds a displayed name from three to five sampled words. Every word is
     * preceded by a space, including the first, and the loop resamples its
     * length limit on each condition check.
     */
    private static String generateRandomOptionName(AllayRandom random) {
        StringBuilder builder = new StringBuilder();
        // AllayRandom includes its upper bound. Resampling this condition permits
        // three through five words without choosing one fixed length before iteration.
        for (int i = 0; i < 3 + random.nextInt(2); i++) {
            builder.append(" ").append(WORDS.get(random.nextInt(WORDS.size() - 1)));
        }
        return builder.toString();
    }

    /**
     * Returns the highest eligible normal level for each primary enchantment
     * accepted by the item. Both modified-power bounds are inclusive, and each
     * type is tested from its configured maximum down to {@code 1}.
     *
     * <p>
     * The descending search matters when one power value fits several levels:
     * the first match adds that level and stops searching the type, so the
     * result contains at most one instance per type. Types with no matching
     * interval contribute nothing, and instances come from each type's cache
     * rather than independent allocations.
     */
    private static List<EnchantmentInstance> getAvailableEnchantments(int modifiedLevel, ItemStack item) {
        List<EnchantmentInstance> list = new ArrayList<>();
        for (var enchantment : getPrimaryEnchantmentsForItem(item)) {
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
     * Returns registered types accepted by item compatibility and
     * enchanting-table availability checks. Normal items also require type
     * applicability, while items reporting
     * {@code isAllEnchantmentsAvailableInEnchantTable()} bypass only that final
     * applicability test; the returned list is unmodifiable.
     */
    private static List<EnchantmentType> getPrimaryEnchantmentsForItem(ItemStack item) {
        return Registries.ENCHANTMENTS
                .getContent()
                .m1().values()
                .stream()
                .filter(item::checkEnchantmentCompatibility)
                .filter(EnchantmentType::isAvailableInEnchantTable)
                .filter(type -> item.isAllEnchantmentsAvailableInEnchantTable() || type.canBeAppliedTo(item.getItemType()))
                .toList();
    }

}
