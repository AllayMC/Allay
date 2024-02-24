package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemSalmonBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSalmonBucketStackInitializer {
  static void init() {
    ItemTypes.SALMON_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemSalmonBucketStack.class)
            .vanillaItem(VanillaItemId.SALMON_BUCKET)
            .build();
  }
}
