package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemMilkBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMilkBucketStackInitializer {
  static void init() {
    ItemTypes.MILK_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemMilkBucketStack.class)
            .vanillaItem(VanillaItemId.MILK_BUCKET)
            .build();
  }
}
