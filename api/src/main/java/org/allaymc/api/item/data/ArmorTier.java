package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.allaymc.api.item.data.ItemTags.*;

/**
 * {@code ArmorTier} associates armor materials with the item tags used to
 * identify their tier. {@code isBetterThan} compares enum declaration order,
 * while {@code ORDER_OF_QUALITY} defines the separate first-match order used
 * when an item carries multiple tier tags.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ArmorTier {
    /**
     * Leather armor material tier.
     */
    LEATHER(LEATHER_TIER),
    /**
     * Iron armor material tier.
     */
    IRON(IRON_TIER),
    /**
     * Chainmail armor material tier.
     */
    CHAIN(CHAINMAIL_TIER),
    /**
     * Golden armor material tier.
     */
    GOLD(GOLDEN_TIER),
    /**
     * Diamond armor material tier.
     */
    DIAMOND(DIAMOND_TIER),
    /**
     * Netherite armor material tier.
     */
    NETHERITE(NETHERITE_TIER);

    /**
     * {@code ORDER_OF_QUALITY} lists tier tags in lookup priority order for
     * {@code ItemHelper.getArmorTier}. The public array is mutable, so replacing
     * its entries changes subsequent tier selection.
     */
    public static final ArmorTier[] ORDER_OF_QUALITY = {
            NETHERITE, DIAMOND, GOLD, CHAIN, IRON, LEATHER
    };

    /**
     * Item tag identifying this armor material tier.
     */
    private final ItemTag itemTag;

    /**
     * Returns whether this tier occurs later than {@code tier} in enum declaration
     * order. Equal tiers return {@code false}; this comparison does not use the
     * mutable lookup order in {@code ORDER_OF_QUALITY}.
     *
     * @throws NullPointerException if {@code tier} is {@code null}
     */
    public boolean isBetterThan(ArmorTier tier) {
        return this.ordinal() > tier.ordinal();
    }
}
