package cn.allay.api.item.interfaces.witherskeletonspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherSkeletonSpawnEggStack extends ItemStack {
    ItemType<ItemWitherSkeletonSpawnEggStack> WITHER_SKELETON_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWitherSkeletonSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WITHER_SKELETON_SPAWN_EGG)
            .build();
}
