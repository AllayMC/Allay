package org.allaymc.api.item.enchantment;

import java.util.List;

/**
 * EnchantOption represents an enchant option in the enchant table.
 *
 * @author daoge_cmd
 */
public record EnchantOption(
        String optionName, int requiredLapisLazuliCount,
        int requiredXpLevel, List<EnchantmentInstance> enchantments
) {
}
