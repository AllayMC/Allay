package org.allaymc.api.item;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.data.ToolTier;
import org.allaymc.api.item.type.ItemType;

import java.util.Arrays;

/**
 * {@code ItemHelper} classifies item types through their registered item tags.
 * Tier queries search the corresponding quality-order array and return its first
 * matching tier, while category queries test one tag without constructing or
 * examining an item stack.
 *
 * @author daoge_cmd
 */
@UtilityClass
@Slf4j
public final class ItemHelper {
    /**
     * Returns the first tool tier whose tag belongs to {@code itemType}.
     * {@code ToolTier.ORDER_OF_QUALITY} determines precedence when several tier tags
     * match; the result is {@code null} when no tier tag matches.
     */
    public static ToolTier getToolTier(ItemType<?> itemType) {
        return Arrays.stream(ToolTier.ORDER_OF_QUALITY)
                .filter(tier -> itemType.hasItemTag(tier.getItemTag()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the first armor tier whose tag belongs to {@code itemType}.
     * {@code ArmorTier.ORDER_OF_QUALITY} determines precedence when several tier tags
     * match; the result is {@code null} when no tier tag matches.
     */
    public static ArmorTier getArmorTier(ItemType<?> itemType) {
        return Arrays.stream(ArmorTier.ORDER_OF_QUALITY)
                .filter(tier -> itemType.hasItemTag(tier.getItemTag()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_TOOL} classification tag.
     */
    public static boolean isTool(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_TOOL);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_SWORD} classification tag.
     */
    public static boolean isSword(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SWORD);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_PICKAXE} classification tag.
     */
    public static boolean isPickaxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_PICKAXE);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_HOE} classification tag.
     */
    public static boolean isHoe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_HOE);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_AXE} classification tag.
     */
    public static boolean isAxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_AXE);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_SHOVEL} classification tag.
     */
    public static boolean isShovel(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SHOVEL);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_ARMOR} classification tag.
     */
    public static boolean isArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_ARMOR);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.HEAD} classification tag.
     */
    public static boolean isHead(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.HEAD);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.TRIMMABLE_ARMORS} classification tag.
     */
    public static boolean isTrimmableArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.TRIMMABLE_ARMORS);
    }

    /**
     * Returns whether {@code itemType} carries the {@code ItemTags.IS_SPEAR} classification tag.
     */
    public static boolean isSpear(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SPEAR);
    }
}
