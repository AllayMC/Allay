package org.allaymc.api.item;

import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public final class ItemHelper {
    public static ItemStack fromNBT(NbtMap nbt) {
        int count = nbt.getByte("Count");
        int meta = nbt.getShort("Damage");
        var name = nbt.getString("Name");
        var itemType = Objects.requireNonNull(ItemTypeRegistry.getRegistry().get(new Identifier(name)), "Unknown item type " + name + "while loading container items!");
        return itemType.createItemStack(
                SimpleItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .extraTag(nbt.getCompound("tag"))
                        .build());
    }

    /**
     * 获取物品的物品等级 <br>
     * @return ItemTier对象, 或者null如果此物品不存在物品等级
     */
    public static ItemTier getItemTier(ItemType<?> itemType) {
        for (var tier : ItemTier.ORDER_OF_QUALITY) {
            if (itemType.hasItemTag(tier.getItemTag())) {
                return tier;
            }
        }
        return null;
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
