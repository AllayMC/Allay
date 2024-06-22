package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class SimpleEnchantmentInstance implements EnchantmentInstance {
    protected final EnchantmentType type;
    protected final int level;
}
