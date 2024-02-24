package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemCodBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCodBucketStackInitializer {
  static void init() {
    ItemTypes.COD_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemCodBucketStack.class)
            .vanillaItem(VanillaItemId.COD_BUCKET)
            .build();
  }
}
