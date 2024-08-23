package org.allaymc.api.item;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.data.ArmorTier;
import org.allaymc.api.data.ToolTier;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Arrays;
import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@UtilityClass
@Slf4j
public final class ItemHelper {
    public static ItemStack fromNBT(NbtMap nbt) {
        try {
            // NOTICE: Unlike BlockStateUpdater, the second argument of the updateItemState() method
            // represents the version that needs to be upgraded to instead of the current version
            nbt = ItemStateUpdaters.updateItemState(nbt, ItemStateUpdaters.LATEST_VERSION);
            int count = nbt.getByte("Count", (byte) 1);
            int meta = nbt.getShort("Damage");
            var name = nbt.getString("Name");
            var itemType = Objects.requireNonNull(Registries.ITEMS.get(new Identifier(name)), "Unknown item type " + name + "while loading container items!");
            return itemType.createItemStack(
                    SimpleItemStackInitInfo
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
     * @return The {@link ToolTier} object, or {@code null} if the item does not have an tool tier.
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

    public static boolean isTool(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_TOOL);
    }

    public static boolean isSword(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_SWORD);
    }

    public static boolean isPickaxe(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_PICKAXE);
    }

    public static boolean isHoe(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_HOE);
    }

    public static boolean isAxe(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_AXE);
    }

    public static boolean isShovel(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_SHOVEL);
    }

    public static boolean isArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.IS_ARMOR);
    }

    public static boolean isTrimmableArmor(ItemType<?> itemType) {
        return itemType.hasItemTag(VanillaItemTags.TRIMMABLE_ARMORS);
    }
}
