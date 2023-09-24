package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkeletonHorseSpawnEggStack extends ItemStack {
    ItemType<ItemSkeletonHorseSpawnEggStack> SKELETON_HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSkeletonHorseSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SKELETON_HORSE_SPAWN_EGG)
            .build();
}
