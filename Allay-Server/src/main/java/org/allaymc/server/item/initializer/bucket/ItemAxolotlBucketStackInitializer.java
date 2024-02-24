package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemAxolotlBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAxolotlBucketStackInitializer {
  static void init() {
    ItemTypes.AXOLOTL_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemAxolotlBucketStack.class)
            .vanillaItem(VanillaItemId.AXOLOTL_BUCKET)
            .build();
  }
}
