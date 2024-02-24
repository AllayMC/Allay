package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemLavaBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLavaBucketStackInitializer {
  static void init() {
    ItemTypes.LAVA_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemLavaBucketStack.class)
            .vanillaItem(VanillaItemId.LAVA_BUCKET)
            .build();
  }
}
