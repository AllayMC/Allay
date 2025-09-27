package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.allaymc.api.item.data.ItemTags.*;

/**
 * Represents tiers of armor, each associated with a specific item tag.
 * <p>
 * Armor tiers determine the quality and strength of armor pieces in a hierarchical order.
 * The hierarchy is represented by the ordinal value of each tier and is based on the order defined.
 * <p>
 * The defined tiers include:
 * <ul>
 *   <li>LEATHER: Basic tier.</li>
 *   <li>IRON: Moderate tier.</li>
 *   <li>CHAIN: Comparable to IRON but with unique texture.</li>
 *   <li>GOLD: Low durability but high enchant ability tier.</li>
 *   <li>DIAMOND: Advanced tier with higher durability.</li>
 *   <li>NETHERITE: Premium tier with superior durability and resistance.</li>
 * </ul>
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ArmorTier {
    LEATHER(LEATHER_TIER),
    IRON(IRON_TIER),
    CHAIN(CHAINMAIL_TIER),
    GOLD(GOLDEN_TIER),
    DIAMOND(DIAMOND_TIER),
    NETHERITE(NETHERITE_TIER);

    public static final ArmorTier[] ORDER_OF_QUALITY = {
            NETHERITE, DIAMOND, GOLD, CHAIN, IRON, LEATHER
    };

    private final ItemTag itemTag;

    /**
     * Determines whether the current armor tier is better than the specified armor tier.
     *
     * @param tier the armor tier to compare against
     * @return {@code true} if the current armor tier is better than the specified tier, {@code false} otherwise
     */
    public boolean isBetterThan(ArmorTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
