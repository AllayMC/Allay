package org.allaymc.api.item.interfaces.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishBucketStack extends ItemStack {
  ItemType<ItemPufferfishBucketStack> PUFFERFISH_BUCKET_TYPE = ItemTypeBuilder
          .builder(ItemPufferfishBucketStack.class)
          .vanillaItem(VanillaItemId.PUFFERFISH_BUCKET)
          .build();
}
