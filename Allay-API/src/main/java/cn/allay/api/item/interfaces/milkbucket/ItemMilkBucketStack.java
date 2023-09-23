package cn.allay.api.item.interfaces.milkbucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMilkBucketStack extends ItemStack {
    ItemType<ItemMilkBucketStack> MILK_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemMilkBucketStack.class)
            .vanillaItem(VanillaItemId.MILK_BUCKET)
            .build();
}
