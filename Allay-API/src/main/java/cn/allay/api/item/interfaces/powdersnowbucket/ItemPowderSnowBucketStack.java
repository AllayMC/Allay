package cn.allay.api.item.interfaces.powdersnowbucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPowderSnowBucketStack extends ItemStack {
    ItemType<ItemPowderSnowBucketStack> POWDER_SNOW_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemPowderSnowBucketStack.class)
            .vanillaItem(VanillaItemId.POWDER_SNOW_BUCKET)
            .build();
}
