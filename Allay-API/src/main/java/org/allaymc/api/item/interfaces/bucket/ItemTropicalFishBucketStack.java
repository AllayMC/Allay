package org.allaymc.api.item.interfaces.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishBucketStack extends ItemStack {
  ItemType<ItemTropicalFishBucketStack> TROPICAL_FISH_BUCKET_TYPE = ItemTypeBuilder
          .builder(ItemTropicalFishBucketStack.class)
          .vanillaItem(VanillaItemId.TROPICAL_FISH_BUCKET)
          .build();
}
