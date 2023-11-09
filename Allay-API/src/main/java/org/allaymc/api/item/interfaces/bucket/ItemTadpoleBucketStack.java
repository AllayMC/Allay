package org.allaymc.api.item.interfaces.bucket;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleBucketStack extends ItemStack {
  ItemType<ItemTadpoleBucketStack> TADPOLE_BUCKET_TYPE = ItemTypeBuilder
          .builder(ItemTadpoleBucketStack.class)
          .vanillaItem(VanillaItemId.TADPOLE_BUCKET)
          .build();
}
