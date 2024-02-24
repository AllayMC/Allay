package org.allaymc.server.item.initializer.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bucket.ItemPowderSnowBucketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPowderSnowBucketStackInitializer {
  static void init() {
    ItemTypes.POWDER_SNOW_BUCKET_TYPE = ItemTypeBuilder
            .builder(ItemPowderSnowBucketStack.class)
            .vanillaItem(VanillaItemId.POWDER_SNOW_BUCKET)
            .build();
  }
}
