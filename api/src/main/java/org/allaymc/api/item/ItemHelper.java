package org.allaymc.api.item;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.data.ToolTier;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.tag.ItemCustomTags;
import org.allaymc.api.item.tag.ItemTags;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
@UtilityClass
@Slf4j
public final class ItemHelper {

    /**
     * Create an {@link ItemStack} from an NBT tag.
     * <p>
     * This method will also update the item state to the latest version.
     *
     * @param nbt The NBT tag.
     *
     * @return The {@link ItemStack} object.
     */
    public static ItemStack fromNBT(NbtMap nbt) {
        try {
            nbt = ItemStateUpdaters.updateItemState(nbt, ItemStateUpdaters.LATEST_VERSION);
            int count = nbt.getByte("Count", (byte) 1);
            int meta = nbt.getShort("Damage");
            var name = nbt.getString("Name");
            var itemType = Objects.requireNonNull(Registries.ITEMS.get(new Identifier(name)), "Unknown item type " + name + "while loading container items!");
            return itemType.createItemStack(
                    ItemStackInitInfo
                            .builder()
                            .count(count)
                            .meta(meta)
                            .extraTag(nbt.getCompound("tag"))
                            .build()
            );
        } catch (Throwable t) {
            log.error("Failed to load item from NBT", t);
            return ItemAirStack.AIR_STACK;
        }
    }

    /**
     * Get the tool tier of an item.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return The {@link ToolTier} object, or {@code null} if the item does not have a tool tier.
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
     * @param itemType The {@link ItemType} of the item.
     *
     * @return The {@link ArmorTier} object, or {@code null} if the item does not have an armor tier.
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
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a tool, {@code false} otherwise.
     */
    public static boolean isTool(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_TOOL);
    }

    /**
     * Check if an item is a sword.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a sword, {@code false} otherwise.
     */
    public static boolean isSword(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SWORD);
    }

    /**
     * Check if an item is a pickaxe.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a pickaxe, {@code false} otherwise.
     */
    public static boolean isPickaxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_PICKAXE);
    }

    /**
     * Check if an item is a hoe.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a hoe, {@code false} otherwise.
     */
    public static boolean isHoe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_HOE);
    }

    /**
     * Check if an item is an axe.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is an axe, {@code false} otherwise.
     */
    public static boolean isAxe(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_AXE);
    }

    /**
     * Check if an item is a shovel.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a shovel, {@code false} otherwise.
     */
    public static boolean isShovel(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_SHOVEL);
    }

    /**
     * Check if an item is a weapon.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a weapon, {@code false} otherwise.
     */
    public static boolean isArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.IS_ARMOR);
    }

    /**
     * Check if an item is a head.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a head, {@code false} otherwise.
     */
    public static boolean isHead(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemCustomTags.HEAD);
    }

    /**
     * Check if an item is a trimmable armor.
     *
     * @param itemType The {@link ItemType} of the item.
     *
     * @return {@code true} if the item is a trimmable armor, {@code false} otherwise.
     */
    public static boolean isTrimmableArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(ItemTags.TRIMMABLE_ARMORS);
    }
}
