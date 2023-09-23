package cn.allay.api.item.interfaces.witherspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherSpawnEggStack extends ItemStack {
    ItemType<ItemWitherSpawnEggStack> WITHER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWitherSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WITHER_SPAWN_EGG)
            .build();
}
