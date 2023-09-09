package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishBucketStack extends ItemStack {
    ItemType<ItemPufferfishBucketStack> PUFFERFISH_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemPufferfishBucketStack.class)
            .vanillaItem(VanillaItemId.PUFFERFISH_BUCKET)
            .build();
}
