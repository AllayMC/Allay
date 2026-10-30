package org.allaymc.server.block;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.jetbrains.annotations.ApiStatus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code FortuneDropHelper} supplies randomized drop counts and bonus decisions
 * parameterized by an item's Fortune level. A missing tool contributes level zero, so
 * callers can use the same routines for attributed and tool-free breaks. Weighted
 * counts expand a random upper bound, binomial counts add successful trials, and
 * discrete counts add a capped level bonus.
 *
 * <p>
 * The helper keeps these distributions separate because increasing the maximum count
 * differs from adding trials or changing a bonus probability. Callers provide valid
 * ranges and probability inputs; only selected bounds are checked locally. Count
 * helpers use inclusive ranges, while the divisor and fixed-probability helpers clamp
 * their computed chances at certainty without normalizing all invalid input
 * combinations.
 *
 * @author IWareQ | PocketMine Team
 */
@ApiStatus.Experimental
@UtilityClass
public class FortuneDropHelper {
    /**
     * {@code RANDOM} supplies the draws used by every Fortune distribution and bonus
     * decision in this helper.
     */
    private static final Random RANDOM = ThreadLocalRandom.current();

    /**
     * Returns an inclusive random drop count with a Fortune-dependent upper-bound
     * bonus. A {@code null} {@code usedItem} contributes Fortune level zero, and
     * {@code maxBase} must be at least {@code minBase}.
     *
     * <p>
     * Level zero uses the base range directly. At higher levels, one draw decides
     * whether the maximum expands to {@code maxBase * (fortuneLevel + 1)}; another
     * draw selects the count. A bonus draw therefore expands possible results rather
     * than guaranteeing the expanded maximum.
     *
     * @throws IllegalArgumentException if {@code maxBase} is smaller than {@code minBase}, or the computed integer range is invalid
     */
    public static int weighted(ItemStack usedItem, int minBase, int maxBase) {
        Preconditions.checkArgument(maxBase >= minBase, "Maximum drop amount must be greater than or equal to minimum drop amount");

        var fortuneLevel = getFortuneLevel(usedItem);
        if (fortuneLevel == 0) {
            return randomBetween(minBase, maxBase);
        }

        // Fortune changes the chance of expanding the maximum; the subsequent
        // inclusive draw still allows counts from the original range.
        var addBonusDrop = RANDOM.nextDouble() > 2d / (fortuneLevel + 2);
        var maxCount = addBonusDrop ? maxBase * (fortuneLevel + 1) : maxBase;
        return randomBetween(minBase, maxCount);
    }

    /**
     * Returns the minimum plus successes from three base trials and additional
     * Fortune trials. Each trial succeeds with probability {@code 8d / 15d}, and a
     * {@code null} tool contributes no Fortune levels.
     */
    public static int binomial(ItemStack usedItem, int min) {
        return binomial(usedItem, min, 3, 8d / 15d);
    }

    /**
     * Returns {@code min} plus successes across {@code minTrials} and the tool's
     * Fortune level. Each independent draw compares against {@code chance}; a
     * {@code null} tool supplies level zero.
     *
     * <p>
     * The caller chooses the base workload and per-trial probability. This method
     * does not validate either input, so nonpositive total trials leave the minimum
     * unchanged. Fortune increases the number of opportunities rather than increasing
     * the success chance for an individual trial.
     */
    public static int binomial(ItemStack usedItem, int min, int minTrials, double chance) {
        var fortuneLevel = getFortuneLevel(usedItem);
        var trials = minTrials + fortuneLevel;

        var successes = min;
        for (int i = 0; i < trials; i++) {
            if (RANDOM.nextDouble() < chance) {
                successes++;
            }
        }

        return successes;
    }

    /**
     * Returns an inclusive random count with Fortune added to the upper bound and
     * limited by {@code maxCap}. A {@code null} tool supplies level zero; the cap
     * must leave a valid range above {@code baseMin}.
     *
     * @throws IllegalArgumentException if {@code baseMax} is smaller than {@code baseMin}, or the adjusted integer range is invalid
     */
    public static int discrete(ItemStack usedItem, int baseMin, int baseMax, int maxCap) {
        Preconditions.checkArgument(baseMax >= baseMin, "Maximum base drop amount must be greater than or equal to minimum base drop amount");

        var fortuneLevel = getFortuneLevel(usedItem);
        // The cap limits the Fortune-adjusted maximum without increasing the minimum
        // count.
        var maxDrops = Math.min(baseMax + fortuneLevel, maxCap);
        return randomBetween(baseMin, maxDrops);
    }

    /**
     * Returns whether a Fortune-adjusted reciprocal-probability draw grants a bonus.
     * A {@code null} tool supplies level zero; the divisor is clamped to at least
     * {@code 1}, making that boundary certain.
     */
    public static boolean bonusChanceDivisor(ItemStack usedItem, int divisorBase, int divisorSubtractPerLevel) {
        var fortuneLevel = getFortuneLevel(usedItem);
        // Clamping the divisor to one makes sufficiently large Fortune bonuses
        // certain.
        var divisor = Math.max(1, divisorBase - (fortuneLevel * divisorSubtractPerLevel));
        return RANDOM.nextInt(divisor) == 0;
    }

    /**
     * Returns whether a Fortune-adjusted fixed-probability draw grants a bonus. A
     * {@code null} tool supplies level zero; the computed chance is capped at
     * {@code 1} but receives no lower-bound clamp.
     */
    public static boolean bonusChanceFixed(ItemStack usedItem, double chanceBase, double addedChancePerLevel) {
        var fortuneLevel = getFortuneLevel(usedItem);
        // The upper clamp keeps accumulated Fortune bonuses at certainty.
        var chance = Math.min(1d, chanceBase + (fortuneLevel * addedChancePerLevel));
        return RANDOM.nextDouble() < chance;
    }

    /**
     * Returns the item's Fortune enchantment level or zero for an absent item. A
     * {@code null} {@code item} therefore uses the same base distributions as an
     * unenchanted tool.
     */
    private static int getFortuneLevel(ItemStack item) {
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
    }

    /**
     * Returns a uniformly selected integer between the supplied inclusive bounds. The
     * computed range width must remain positive, so reversed bounds or integer
     * overflow can cause {@code IllegalArgumentException}.
     */
    private static int randomBetween(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
