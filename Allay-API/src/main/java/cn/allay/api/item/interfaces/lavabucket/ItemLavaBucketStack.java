package cn.allay.api.item.interfaces.lavabucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLavaBucketStack extends ItemStack {
    ItemType<ItemLavaBucketStack> LAVA_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemLavaBucketStack.class)
            .vanillaItem(VanillaItemId.LAVA_BUCKET)
            .build();
}
