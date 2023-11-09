package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCameraStack extends ItemStack {
  ItemType<ItemItemCameraStack> ITEM_CAMERA_TYPE = ItemTypeBuilder
          .builder(ItemItemCameraStack.class)
          .vanillaItem(VanillaItemId.ITEM_CAMERA)
          .build();
}
