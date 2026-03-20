package org.allaymc.server.item.component;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.enchantment.EnchantmentTypes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class ItemArmorBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemArmorBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) return true;

        var possibility = 0.6f + 0.4f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }
}
