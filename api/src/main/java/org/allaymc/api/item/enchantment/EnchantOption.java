package org.allaymc.api.item.enchantment;

import java.util.List;

/**
 * {@code EnchantOption} represents one enchanting-table choice, including its
 * displayed name, resource requirements, and selected enchantments. The
 * canonical constructor performs no validation and retains {@code enchantments}
 * without copying it; consumers receive the same list through
 * {@code enchantments()} and must coordinate mutations with its other users.
 *
 * @param optionName displayed option name
 * @param requiredLapisLazuliCount lapis item requirement for selecting this
 * option
 * @param requiredXpLevel minimum player experience level required to select the
 * option
 * @param enchantments selected enchantment instance list
 *
 * @author daoge_cmd
 */
public record EnchantOption(
        String optionName, int requiredLapisLazuliCount,
        int requiredXpLevel, List<EnchantmentInstance> enchantments
) {
}
