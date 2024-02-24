package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemTropicalFishBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishBucketStackInitializer {
  static void init() {
    ItemTypes.TROPICAL_FISH_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishBucketStack.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH_BUCKET)
            .build();
  }
}
