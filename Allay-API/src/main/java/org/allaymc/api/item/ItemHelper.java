package org.allaymc.api.item;

import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.registry.ItemTypeRegistry;
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
}
