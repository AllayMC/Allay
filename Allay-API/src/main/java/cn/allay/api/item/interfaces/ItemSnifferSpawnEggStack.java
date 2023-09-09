package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnifferSpawnEggStack extends ItemStack {
    ItemType<ItemSnifferSpawnEggStack> SNIFFER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSnifferSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SNIFFER_SPAWN_EGG)
            .build();
}
