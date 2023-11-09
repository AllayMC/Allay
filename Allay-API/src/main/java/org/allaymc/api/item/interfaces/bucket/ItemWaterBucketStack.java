package org.allaymc.api.item.interfaces.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
