package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishBucketStack extends ItemStack {
    ItemType<ItemTropicalFishBucketStack> TROPICAL_FISH_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishBucketStack.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH_BUCKET)
            .build();
}
