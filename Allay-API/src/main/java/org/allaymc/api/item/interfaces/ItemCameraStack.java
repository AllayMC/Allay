package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCameraStack extends ItemStack {
  ItemType<ItemCameraStack> CAMERA_TYPE = ItemTypeBuilder
          .builder(ItemCameraStack.class)
          .vanillaItem(VanillaItemId.CAMERA)
          .build();
}
