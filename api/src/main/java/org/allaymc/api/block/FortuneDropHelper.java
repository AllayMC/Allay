package org.allaymc.api.block;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
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

    public static int weighted(ItemStack usedItem, int minBase, int maxBase) {
        Preconditions.checkArgument(maxBase >= minBase, "Maximum drop amount must be greater than or equal to minimum drop amount");

        var fortuneLevel = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;

        var addBonusDrop = fortuneLevel > 0 && RANDOM.nextDouble() > 2d / (fortuneLevel + 2);
        var maxCount = addBonusDrop ? maxBase * (fortuneLevel + 1) : maxBase;
        return RANDOM.nextInt((maxCount - minBase) + 1) + minBase;
    }

    public static int binomial(ItemStack usedItem, int min) {
        return binomial(usedItem, min, 3, 4d / 7d);
    }

    public static int binomial(ItemStack usedItem, int min, int minTrials, double chance) {
        var fortuneLevel = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;

        var successes = min;
        var trials = minTrials + fortuneLevel;
        for (int i = 0; i < trials; i++) {
            if (RANDOM.nextDouble() < chance) {
                successes++;
            }
        }

        return successes;
    }

    public static int discrete(ItemStack usedItem, int baseMin, int baseMax, int maxCap) {
        Preconditions.checkArgument(baseMax >= baseMin, "Maximum base drop amount must be greater than or equal to minimum base drop amount");

        var fortuneLevel = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
        var maxDrops = Math.min(baseMax + fortuneLevel, maxCap);
        return RANDOM.nextInt((maxDrops - baseMin) + 1) + baseMin;
    }

    public static boolean bonusChanceDivisor(ItemStack usedItem, int divisorBase, int divisorSubtractPerLevel) {
        var fortuneLevel = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
        return RANDOM.nextInt(Math.max(1, divisorBase - (fortuneLevel * divisorSubtractPerLevel))) == 0;
    }

    public static boolean bonusChanceFixed(ItemStack usedItem, double chanceBase, double addedChancePerLevel) {
        var fortuneLevel = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
        var chance = Math.min(1, chanceBase + (fortuneLevel * addedChancePerLevel));
        return RANDOM.nextDouble() < chance;
    }
}
