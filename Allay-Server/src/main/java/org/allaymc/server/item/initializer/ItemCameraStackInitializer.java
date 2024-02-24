package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCameraStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCameraStackInitializer {
  static void init() {
    ItemTypes.CAMERA_TYPE = ItemTypeBuilder
            .builder(ItemCameraStack.class)
            .vanillaItem(VanillaItemId.CAMERA)
            .build();
  }
}
