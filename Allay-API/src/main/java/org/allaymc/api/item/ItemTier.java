package org.allaymc.api.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.tag.ItemTag;

import static org.allaymc.api.data.VanillaItemTags.*;

/**
 * Allay Project 2024/6/12
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ItemTier {
    WOODEN(WOODEN_TIER),
    STONE(STONE_TIER),
    IRON(IRON_TIER),
    DIAMOND(DIAMOND_TIER),
    NETHERITE(NETHERITE_TIER),
    GOLD(GOLDEN_TIER);

    public static final ItemTier[] ORDER_OF_QUALITY = {GOLD, NETHERITE, DIAMOND, IRON, STONE, WOODEN};

    private final ItemTag itemTag;

    public boolean isBetterThan(ItemTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
