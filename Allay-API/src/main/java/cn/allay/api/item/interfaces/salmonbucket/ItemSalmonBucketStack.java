package cn.allay.api.item.interfaces.salmonbucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSalmonBucketStack extends ItemStack {
    ItemType<ItemSalmonBucketStack> SALMON_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemSalmonBucketStack.class)
            .vanillaItem(VanillaItemId.SALMON_BUCKET)
            .build();
}
