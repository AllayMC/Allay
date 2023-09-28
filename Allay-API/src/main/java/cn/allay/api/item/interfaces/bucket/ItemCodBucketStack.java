package cn.allay.api.item.interfaces.bucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCodBucketStack extends ItemStack {
  ItemType<ItemCodBucketStack> COD_BUCKET_TYPE = ItemTypeBuilder
          .builder(ItemCodBucketStack.class)
          .vanillaItem(VanillaItemId.COD_BUCKET)
          .build();
}
