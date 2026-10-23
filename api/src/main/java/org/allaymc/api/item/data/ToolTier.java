package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.allaymc.api.item.data.ItemTags.*;

/**
 * {@code ToolTier} associates tool materials with identifying item tags and
 * mining levels. {@code isBetterThan} compares enum declaration order, while
 * {@code ORDER_OF_QUALITY} supplies the first-match lookup priority for items
 * with several tier tags; the stored mining level is separate metadata.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ToolTier {
    /**
     * Wooden tool material tier.
     */
    WOODEN(WOODEN_TIER, 0),
    /**
     * Golden tool material tier.
     */
    GOLD(GOLDEN_TIER, 0),
    /**
     * Stone tool material tier.
     */
    STONE(STONE_TIER, 1),
    /**
     * Iron tool material tier.
     */
    IRON(IRON_TIER, 2),
    /**
     * Diamond tool material tier.
     */
    DIAMOND(DIAMOND_TIER, 3),
    /**
     * Netherite tool material tier.
     */
    NETHERITE(NETHERITE_TIER, 4);

    /**
     * {@code ORDER_OF_QUALITY} lists tier tags in lookup priority order for
     * {@code ItemHelper.getToolTier}. The public array is mutable, so replacing
     * its entries changes subsequent tier selection.
     */
    public static final ToolTier[] ORDER_OF_QUALITY = {
            NETHERITE, DIAMOND, IRON, STONE, GOLD, WOODEN
    };

    /**
     * Item tag identifying this tool material tier.
     */
    private final ItemTag itemTag;
    /**
     * Numeric mining capability level of this material.
     */
    private final int miningLevel;

    /**
     * Returns whether this tier occurs later than {@code tier} in enum declaration
     * order. Equal tiers return {@code false}; this comparison uses neither
     * {@code miningLevel} nor the mutable lookup order in {@code ORDER_OF_QUALITY}.
     *
     * @throws NullPointerException if {@code tier} is {@code null}
     */
    public boolean isBetterThan(ToolTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
