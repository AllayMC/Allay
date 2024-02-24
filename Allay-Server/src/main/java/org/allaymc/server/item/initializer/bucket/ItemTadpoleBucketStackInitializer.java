package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemTadpoleBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleBucketStackInitializer {
  static void init() {
    ItemTypes.TADPOLE_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemTadpoleBucketStack.class)
            .vanillaItem(VanillaItemId.TADPOLE_BUCKET)
            .build();
  }
}
