package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class SimpleEnchantmentInstance implements EnchantmentInstance {
    protected final EnchantmentType type;
    protected final int level;
}
