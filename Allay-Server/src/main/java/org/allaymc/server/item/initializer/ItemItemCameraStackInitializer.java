package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemCameraStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCameraStackInitializer {
  static void init() {
    ItemTypes.ITEM_CAMERA_TYPE = ItemTypeBuilder
            .builder(ItemItemCameraStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAMERA)
            .build();
  }
}
