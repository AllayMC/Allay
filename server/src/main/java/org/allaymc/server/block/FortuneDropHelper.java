package org.allaymc.server.block;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.jetbrains.annotations.ApiStatus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ | PocketMine Team
 */
@ApiStatus.Experimental
@UtilityClass
public class FortuneDropHelper {
    private static final Random RANDOM = ThreadLocalRandom.current();

    /**
     * Calculates drop amount using weighted Fortune mechanics.
     * <p>
     * This algorithm gives a chance to multiply the maximum drops based on Fortune level.
     * The probability of bonus drops is: 1 - (2 / (fortune_level + 2))
     *
     * @param usedItem the item used to break the block (maybe null for no tool)
     * @param minBase  minimum number of drops without Fortune
     * @param maxBase  maximum number of drops without Fortune
     * @return the calculated drop amount
     * @throws IllegalArgumentException if maxBase < minBase
     */
    public static int weighted(ItemStack usedItem, int minBase, int maxBase) {
        Preconditions.checkArgument(maxBase >= minBase, "Maximum drop amount must be greater than or equal to minimum drop amount");

        var fortuneLevel = getFortuneLevel(usedItem);
        if (fortuneLevel == 0) {
            return randomBetween(minBase, maxBase);
        }

        // Probability increases with Fortune level: 1/3, 1/2, 3/5, 2/3...
        var addBonusDrop = RANDOM.nextDouble() > 2d / (fortuneLevel + 2);
        var maxCount = addBonusDrop ? maxBase * (fortuneLevel + 1) : maxBase;
        return randomBetween(minBase, maxCount);
    }

    /**
     * Calculates drop amount using binomial distribution with default parameters.
     * <p>
     * Uses 3 base trials with 8/15 success probability per trial.
     * This is the standard Fortune algorithm for most ores.
     *
     * @param usedItem the item used to break the block (maybe null)
     * @param min      guaranteed minimum number of drops
     * @return the calculated drop amount (min + successful trials)
     */
    public static int binomial(ItemStack usedItem, int min) {
        return binomial(usedItem, min, 3, 8d / 15d);
    }

    /**
     * Calculates drop amount using binomial distribution with custom parameters.
     * <p>
     * Performs (minTrials + fortune_level) trials, each with the specified success chance.
     * The final drop amount is min + number of successful trials.
     *
     * @param usedItem  the item used to break the block (maybe null)
     * @param min       guaranteed minimum number of drops
     * @param minTrials base number of trials without Fortune
     * @param chance    success probability for each trial (0.0 to 1.0)
     * @return the calculated drop amount
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
     * Calculates drop amount using discrete uniform distribution with Fortune bonus.
     * <p>
     * Adds Fortune level directly to the maximum drops, capped at maxCap.
     * The result is uniformly distributed between baseMin and the adjusted maximum.
     *
     * @param usedItem the item used to break the block (maybe null)
     * @param baseMin  minimum number of drops
     * @param baseMax  maximum number of drops without Fortune
     * @param maxCap   absolute maximum drops regardless of Fortune level
     * @return the calculated drop amount
     * @throws IllegalArgumentException if baseMax < baseMin
     */
    public static int discrete(ItemStack usedItem, int baseMin, int baseMax, int maxCap) {
        Preconditions.checkArgument(baseMax >= baseMin, "Maximum base drop amount must be greater than or equal to minimum base drop amount");

        var fortuneLevel = getFortuneLevel(usedItem);
        var maxDrops = Math.min(baseMax + fortuneLevel, maxCap);
        return randomBetween(baseMin, maxDrops);
    }

    /**
     * Determines if a bonus drop should occur using a divisor-based probability.
     * <p>
     * The probability of success is: 1 / max(1, divisorBase - (fortune_level * divisorSubtractPerLevel))
     * <p>
     * Example: divisorBase=10, divisorSubtractPerLevel=2
     * <ul>
     *   <li>Fortune 0: 1/10 chance (10%)</li>
     *   <li>Fortune 1: 1/8 chance (12.5%)</li>
     *   <li>Fortune 2: 1/6 chance (16.7%)</li>
     *   <li>Fortune 3: 1/4 chance (25%)</li>
     * </ul>
     *
     * @param usedItem                the item used to break the block (maybe null)
     * @param divisorBase             the base divisor for probability calculation
     * @param divisorSubtractPerLevel amount to subtract from divisor per Fortune level
     * @return true if the bonus drop should occur
     */
    public static boolean bonusChanceDivisor(ItemStack usedItem, int divisorBase, int divisorSubtractPerLevel) {
        var fortuneLevel = getFortuneLevel(usedItem);
        var divisor = Math.max(1, divisorBase - (fortuneLevel * divisorSubtractPerLevel));
        return RANDOM.nextInt(divisor) == 0;
    }

    /**
     * Determines if a bonus drop should occur using a fixed probability increase.
     * <p>
     * The probability of success is: min(1.0, chanceBase + (fortune_level * addedChancePerLevel))
     * <p>
     * Example: chanceBase=0.1, addedChancePerLevel=0.05
     * <ul>
     *   <li>Fortune 0: 10% chance</li>
     *   <li>Fortune 1: 15% chance</li>
     *   <li>Fortune 2: 20% chance</li>
     *   <li>Fortune 3: 25% chance</li>
     * </ul>
     *
     * @param usedItem            the item used to break the block (maybe null)
     * @param chanceBase          base probability without Fortune (0.0 to 1.0)
     * @param addedChancePerLevel probability increase per Fortune level
     * @return true if the bonus drop should occur
     */
    public static boolean bonusChanceFixed(ItemStack usedItem, double chanceBase, double addedChancePerLevel) {
        var fortuneLevel = getFortuneLevel(usedItem);
        var chance = Math.min(1d, chanceBase + (fortuneLevel * addedChancePerLevel));
        return RANDOM.nextDouble() < chance;
    }

    private static int getFortuneLevel(ItemStack item) {
        return item != null ? item.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
    }

    private static int randomBetween(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
