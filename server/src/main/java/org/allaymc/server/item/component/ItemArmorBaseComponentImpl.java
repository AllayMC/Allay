package org.allaymc.server.item.component;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.enchantment.EnchantmentTypes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ItemArmorBaseComponentImpl} applies armor-specific Unbreaking
 * probabilities to inherited item durability handling. Armor always accepts a
 * damage attempt without that enchantment; enchanted armor retains a fixed
 * sixty-percent damage probability plus a level-dependent contribution, unlike
 * the ordinary item rule.
 *
 * @author daoge_cmd
 */
public class ItemArmorBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates an armor base component from the supplied stack initialization data.
     */
    public ItemArmorBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns whether the current armor damage attempt passes its Unbreaking
     * check. An unenchanted item always passes; enchanted armor uses probability
     * {@code 0.6 + 0.4 / (level + 1)} before inherited damage processing continues.
     */
    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) return true;

        // Armor retains a sixty-percent damage floor despite higher Unbreaking levels.
        var possibility = 0.6f + 0.4f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }
}
