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
    WOODEN(WOODEN_TIER, 0),
    GOLD(GOLDEN_TIER, 0),
    STONE(STONE_TIER, 1),
    IRON(IRON_TIER, 2),
    DIAMOND(DIAMOND_TIER, 3),
    NETHERITE(NETHERITE_TIER, 4);

    public static final ToolTier[] ORDER_OF_QUALITY = {
            NETHERITE, DIAMOND, IRON, STONE, GOLD, WOODEN
    };

    private final ItemTag itemTag;
    private final int miningLevel;

    public boolean isBetterThan(ToolTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
