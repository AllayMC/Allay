package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemPufferfishBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishBucketStackInitializer {
  static void init() {
    ItemTypes.PUFFERFISH_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemPufferfishBucketStack.class)
            .vanillaItem(VanillaItemId.PUFFERFISH_BUCKET)
            .build();
  }
}
