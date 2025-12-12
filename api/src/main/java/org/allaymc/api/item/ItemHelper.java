package org.allaymc.api.item;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.data.ToolTier;
import org.allaymc.api.item.type.ItemType;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
@UtilityClass
@Slf4j
public final class ItemHelper {
    /**
     * Get the tool tier of an item.
     *
     * @param itemType The {@link ItemType} of the item
     * @return The {@link ToolTier} object, or {@code null} if the item does not have a tool tier
     */
    public static ToolTier getToolTier(ItemType<?> itemType) {
        return Arrays.stream(ToolTier.ORDER_OF_QUALITY)
                .filter(tier -> itemType.hasItemTag(tier.getItemTag()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get the armor tier of an item.
     *
     * @param itemType The {@link ItemType} of the item
     * @return The {@link ArmorTier} object, or {@code null} if the item does not have an armor tier
     */
    public static ArmorTier getArmorTier(ItemType<?> itemType) {
        return Arrays.stream(ArmorTier.ORDER_OF_QUALITY)
                .filter(tier -> itemType.hasItemTag(tier.getItemTag()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Check if an item is a tool.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a tool, {@code false} otherwise.
     */
    public static boolean isTool(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_TOOL);
    }

    /**
     * Check if an item is a sword.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a sword, {@code false} otherwise.
     */
    public static boolean isSword(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SWORD);
    }

    /**
     * Check if an item is a pickaxe.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a pickaxe, {@code false} otherwise.
     */
    public static boolean isPickaxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_PICKAXE);
    }

    /**
     * Check if an item is a hoe.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a hoe, {@code false} otherwise.
     */
    public static boolean isHoe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_HOE);
    }

    /**
     * Check if an item is an axe.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is an axe, {@code false} otherwise.
     */
    public static boolean isAxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_AXE);
    }

    /**
     * Check if an item is a shovel.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a shovel, {@code false} otherwise.
     */
    public static boolean isShovel(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SHOVEL);
    }

    /**
     * Check if an item is a weapon.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a weapon, {@code false} otherwise.
     */
    public static boolean isArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_ARMOR);
    }

    /**
     * Check if an item is a head.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a head, {@code false} otherwise.
     */
    public static boolean isHead(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.HEAD);
    }

    /**
     * Check if an item is a trimmable armor.
     *
     * @param itemType The {@link ItemType} of the item
     * @return {@code true} if the item is a trimmable armor, {@code false} otherwise.
     */
    public static boolean isTrimmableArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.TRIMMABLE_ARMORS);
    }

    /**
     * Checks if the given item is classified as a spear.
     *
     * @param itemType The {@link ItemType} of the item to check.
     * @return {@code true} if the item is a spear, {@code false} otherwise.
     */
    public static boolean isSpear(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SPEAR);
    }
}
