package cn.allay.api.item.interfaces.waterbucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterBucketStack extends ItemStack {
    ItemType<ItemWaterBucketStack> WATER_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemWaterBucketStack.class)
            .vanillaItem(VanillaItemId.WATER_BUCKET)
            .build();
}
