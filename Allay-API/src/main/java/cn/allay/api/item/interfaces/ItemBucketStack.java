package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBucketStack extends ItemStack {
    ItemType<ItemBucketStack> BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemBucketStack.class)
            .vanillaItem(VanillaItemId.BUCKET)
            .build();
}
