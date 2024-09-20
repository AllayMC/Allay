package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.tag.ItemTag;

import static org.allaymc.api.item.tag.ItemTags.*;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ToolTier {
    WOODEN(WOODEN_TIER),
    GOLD(GOLDEN_TIER),
    STONE(STONE_TIER),
    IRON(IRON_TIER),
    DIAMOND(DIAMOND_TIER),
    NETHERITE(NETHERITE_TIER);

    public static final ToolTier[] ORDER_OF_QUALITY = {
            NETHERITE, DIAMOND, IRON, STONE, GOLD, WOODEN
    };

    private final ItemTag itemTag;

    public boolean isBetterThan(ToolTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
