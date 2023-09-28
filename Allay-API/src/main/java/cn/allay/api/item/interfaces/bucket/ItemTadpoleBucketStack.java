package cn.allay.api.item.interfaces.bucket;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleBucketStack extends ItemStack {
  ItemType<ItemTadpoleBucketStack> TADPOLE_BUCKET_TYPE = ItemTypeBuilder
          .builder(ItemTadpoleBucketStack.class)
          .vanillaItem(VanillaItemId.TADPOLE_BUCKET)
          .build();
}
