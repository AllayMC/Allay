package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemWaterBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterBucketStackInitializer {
  static void init() {
    ItemTypes.WATER_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemWaterBucketStack.class)
            .vanillaItem(VanillaItemId.WATER_BUCKET)
            .build();
  }
}
