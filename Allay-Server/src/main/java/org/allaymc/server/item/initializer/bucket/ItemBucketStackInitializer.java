package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBucketStackInitializer {
  static void init() {
    ItemTypes.BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemBucketStack.class)
            .vanillaItem(VanillaItemId.BUCKET)
            .build();
  }
}
