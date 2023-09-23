package cn.allay.api.item.interfaces.axolotlbucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAxolotlBucketStack extends ItemStack {
    ItemType<ItemAxolotlBucketStack> AXOLOTL_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemAxolotlBucketStack.class)
            .vanillaItem(VanillaItemId.AXOLOTL_BUCKET)
            .build();
}
